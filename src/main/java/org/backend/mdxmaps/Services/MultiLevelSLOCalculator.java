package org.backend.mdxmaps.Services;

import com.google.common.collect.Multimap;
import com.google.common.collect.MultimapBuilder;
import org.backend.mdxmaps.Model.Enums.MOT;
import org.backend.mdxmaps.Model.Enums.ObjectTypes;
import org.backend.mdxmaps.Model.LatLng;
import org.backend.mdxmaps.Model.RoutingObjects;
import org.backend.mdxmaps.Services.Algorithms.IndoorAlgorithm;

import java.util.ArrayList;
import java.util.Arrays;

import static java.util.stream.Collectors.toList;
import static org.backend.mdxmaps.Model.Enums.MOT.DISABLED;
import static org.backend.mdxmaps.Model.Enums.MOT.STAIRS;
import static org.backend.mdxmaps.Model.Enums.ObjectTypes.ROOM;
import static org.backend.mdxmaps.Services.RoutingObjectsGetterUtilService.getAllPrimes;
import static org.backend.mdxmaps.Services.RoutingObjectsGetterUtilService.getBestRouteFromSortedMultiMap;
import static org.backend.mdxmaps.Services.RoutingObjectsGetterUtilService.getConnectorObjectFromName;
import static org.backend.mdxmaps.Services.RoutingObjectsGetterUtilService.getSameLanesBCForConnetorObject;
import static org.backend.mdxmaps.Services.UtilService.calculateMultipleRoutesDistanceAndSort;
import static org.backend.mdxmaps.Services.UtilService.calculateSingleRouteDistance;
import static org.backend.mdxmaps.Services.UtilService.filterConnectorObjectsByType;
import static org.backend.mdxmaps.Services.UtilService.plugInStartAndEndObjects;
import static org.backend.mdxmaps.Services.UtilService.removeNonDisabledRoutes;
import static org.backend.mdxmaps.Services.UtilService.transformValidRoutesStringToObjects;
import static org.backend.mdxmaps.Services.UtilService.validRouteObjectToLatLngTransformer;

/**
 * Created by Emmanuel Keboh on 10/01/2017.
 */

/**
 * ED - Elevation or De-Elevation point i.e Stairs & Elevators
 */
public final class MultiLevelSLOCalculator {

    public static Multimap<Double, ArrayList<ArrayList<LatLng>>> performMultiLevelSLO(RoutingObjects startObject, RoutingObjects destObject, MOT modeOfTravel,
                                                                                      ObjectTypes startObjectType, ObjectTypes endObjectType) {

        ArrayList<ArrayList<ArrayList<LatLng>>> allRoutes = new ArrayList<>();
        RoutingObjects currentEDConnector;
        String building = startObject.getBuilding();
        ArrayList<ArrayList<String>> validRoutesStrings;
        ArrayList<ArrayList<RoutingObjects>> validRoutesObjects;
        ArrayList<ArrayList<LatLng>> validRoutesLatLng;

        String temp = "stairs";
        if (modeOfTravel != STAIRS) {
            temp = "elevators";
        }

        ArrayList<RoutingObjects> destinationEDConnectors =
                filterConnectorObjectsByType(new RoutingObjects().getConnectors(destObject.getBuilding(), destObject.getActualLevel()),
                        temp);

        ArrayList<RoutingObjects> startEDConnectors =
                (ArrayList<RoutingObjects>) filterConnectorObjectsByType(new RoutingObjects().getConnectors(building,
                        startObject.getActualLevel()), temp).stream()
                        .filter(startEDObject -> checkIfEDIsValid(destinationEDConnectors, startEDObject.getName()))
                        .collect(toList());

        for (int i = 0; i < startEDConnectors.size(); i++) {
            for (int j = 0; j < 2; j++) {
                if (j == 0) { //Start room to elevation or de-elevation point
                    currentEDConnector = startEDConnectors.get(i); //<<--Current ED at start level
                    boolean sameLanesForStart;
                    int startObjectLane = startObjectType == ROOM ? startObject.getLane() : startObject.getPrimeLanes()[0];

                    //Check if currentED is in same lane as start room
                    sameLanesForStart = Arrays.stream(currentEDConnector.getPrimeLanes())
                            .anyMatch(lane -> startObjectLane == lane);

                    if (sameLanesForStart) {
                        allRoutes.add(new ArrayList<ArrayList<LatLng>>());
                        allRoutes.get(i).add(new ArrayList<>());
                        allRoutes.get(i).get(j).add(startObject.getLatLng());
                        allRoutes.get(i).get(j).add(currentEDConnector.getLatLng());
                    } else {
                        ArrayList<RoutingObjects> basicConnectorObjects =
                                filterConnectorObjectsByType(new RoutingObjects().getConnectors(building, startObject.getActualLevel()),
                                        "Basic Connector");

                        if (startObjectType == ROOM) {
                            validRoutesStrings = new IndoorAlgorithm().sameLevelOp(
                                    getAllPrimes(startObject.getName(), basicConnectorObjects),
                                    // ^^^ Gets same lane basic connectors to use as primes ^^^
                                    currentEDConnector.getPrimeLanes()[0],
                                    building, startObject.getActualLevel());
                        } else {
                            validRoutesStrings = new IndoorAlgorithm().sameLevelOp(
                                    getSameLanesBCForConnetorObject(basicConnectorObjects, startObject),
                                    // ^^^ Gets same lane basic connectors to use as primes ^^^
                                    currentEDConnector.getPrimeLanes()[0], building, startObject.getActualLevel());
                        }

                        validRoutesObjects = transformValidRoutesStringToObjects(validRoutesStrings, basicConnectorObjects);

                        if (modeOfTravel == DISABLED) {
                            removeNonDisabledRoutes(validRoutesObjects);
                        }

                        //There might be no valid route to this elevation/de-elevation connector from that room
                        if (validRoutesObjects.size() > 0) {
                            validRoutesObjects = plugInStartAndEndObjects(startObject, currentEDConnector, validRoutesObjects);

                            validRoutesLatLng = (ArrayList<ArrayList<LatLng>>) validRoutesObjects.stream()
                                    .map(validRouteObjectToLatLngTransformer())
                                    .collect(toList());

                            allRoutes.add(new ArrayList<ArrayList<LatLng>>());
                            //Get best valid route if more than 1
                            if (validRoutesLatLng.size() > 1) {
                                allRoutes.get(i).add(getBestRouteFromSortedMultiMap(calculateMultipleRoutesDistanceAndSort(validRoutesLatLng)));
                            } else {
                                allRoutes.get(i).add(validRoutesLatLng.get(0));
                            }

                        } else { //exit immediately, no need for the second part calculation
                            startEDConnectors.remove(i);
                            i--;
                            break;
                        }

                    }
                } else { //Elevation/De-Elevation point to destination

                    currentEDConnector = getConnectorObjectFromName(destinationEDConnectors,
                            startEDConnectors.get(i).getName()); //<<-- Previous Current ED's destn level equivalent

                    //indicates that endObject is a room and not a connector
                    int destObjectLane = endObjectType == ROOM ? destObject.getLane() : destObject.getPrimeLanes()[0];

                    boolean sameLanesForDestination = Arrays.stream(currentEDConnector.getPrimeLanes())
                            .anyMatch(lane -> destObjectLane == lane);

                    if (sameLanesForDestination) {
                        allRoutes.get(i).add(new ArrayList<LatLng>());
                        allRoutes.get(i).get(j).add(currentEDConnector.getLatLng());
                        allRoutes.get(i).get(j).add(destObject.getLatLng());
                    } else {

                        ArrayList<RoutingObjects> basicConnectorObjects =
                                filterConnectorObjectsByType(new RoutingObjects().getConnectors(building, destObject.getActualLevel()),
                                        "Basic Connector");

                        validRoutesStrings = new IndoorAlgorithm().sameLevelOp(
                                getSameLanesBCForConnetorObject(basicConnectorObjects, currentEDConnector),
                                // ^^^ Gets same lane basic connectors to use as primes ^^^
                                destObjectLane, building, destObject.getActualLevel());

                        validRoutesObjects = transformValidRoutesStringToObjects(validRoutesStrings, basicConnectorObjects);

                        if (modeOfTravel == DISABLED) {
                            validRoutesObjects = removeNonDisabledRoutes(validRoutesObjects);
                        }

                        //There might be no valid wheelchair accessible route from this elevation/de-elevation connector to destination room
                        if (validRoutesObjects.size() != 0) {

                            validRoutesObjects = plugInStartAndEndObjects(currentEDConnector, destObject, validRoutesObjects);

                            validRoutesLatLng = (ArrayList<ArrayList<LatLng>>) validRoutesObjects.stream()
                                    .map(validRouteObjectToLatLngTransformer())
                                    .collect(toList());

                            //Get best valid route if more than 1
                            if (validRoutesLatLng.size() > 1) {
                                allRoutes.get(i).add(getBestRouteFromSortedMultiMap(calculateMultipleRoutesDistanceAndSort(validRoutesLatLng)));
                            } else {
                                allRoutes.get(i).add(validRoutesLatLng.get(0));
                            }

                        } else {
                            //Remove start level calculated route
                            allRoutes.remove(i);
                            startEDConnectors.remove(i);
                            i--;
                        }
                    }
                }
            }
        }

        if (allRoutes.size() > 0) {
            Multimap<Double, ArrayList<ArrayList<LatLng>>> multimap = MultimapBuilder.treeKeys().linkedListValues().build();
            allRoutes.forEach(route -> multimap.put(calculateSingleRouteDistance(route), route));
            return multimap;
        }

        return null;
    }


    /**
     * Filters startEDConnectors to only include E/D points that exists on both levels e.g Some stairs don't get to all levels
     *
     * @param destLevelEDPoints list of available ED points on destination level
     * @param edName            current start ED point
     * @return
     */
    private static boolean checkIfEDIsValid(ArrayList<RoutingObjects> destLevelEDPoints, String edName) {
        return destLevelEDPoints.parallelStream()
                .anyMatch(destLevelED -> destLevelED.getName().equals(edName));
    }

}
