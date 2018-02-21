package org.backend.mdxmaps.service.routeCalculators;

import com.google.common.collect.Multimap;
import com.google.common.collect.MultimapBuilder;
import org.backend.mdxmaps.model.LatLng;
import org.backend.mdxmaps.model.Routing;
import org.backend.mdxmaps.model.enums.Building;
import org.backend.mdxmaps.model.enums.MOT;
import org.backend.mdxmaps.model.enums.ObjectType;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static java.util.Collections.min;
import static java.util.stream.Collectors.toList;
import static org.backend.mdxmaps.model.enums.MOT.STAIRS;
import static org.backend.mdxmaps.model.enums.ObjectType.ELEVATOR;
import static org.backend.mdxmaps.model.enums.ObjectType.ROOM;
import static org.backend.mdxmaps.model.enums.ObjectType.STAIR;
import static org.backend.mdxmaps.service.routeCalculators.SingleLevelSLOCalculator.performSingleLevelSLO;
import static org.backend.mdxmaps.service.util.RoutingObjectsGetterUtilService.filterConnectorObjectsByType;
import static org.backend.mdxmaps.service.util.RoutingObjectsGetterUtilService.getConnectorObjectFromName;
import static org.backend.mdxmaps.service.util.RoutingObjectsGetterUtilService.getConnectors;
import static org.backend.mdxmaps.service.util.UtilService.calculateSingleRouteDistance;

/**
 * Created by Emmanuel Keboh on 10/01/2017.
 */

/**
 * ED - Elevation or De-Elevation point i.e Stairs & Elevators
 */
public final class MultiLevelSLOCalculator {

    public static Multimap<Double, ArrayList<ArrayList<LatLng>>> performMultiLevelSLO(Routing startObject, Routing destObject, MOT modeOfTravel) {
        return multiLevelSLO(startObject, destObject, modeOfTravel, null);
    }

    public static Multimap<Double, ArrayList<ArrayList<LatLng>>> performMultiLevelSLO(Routing startObject, Routing destObject, MOT modeOfTravel, Building building) {
        return multiLevelSLO(startObject, destObject, modeOfTravel, building);
    }

    private static Multimap<Double, ArrayList<ArrayList<LatLng>>> multiLevelSLO(Routing startObject, Routing destObject, MOT modeOfTravel, Building location) {

        ArrayList<ArrayList<ArrayList<LatLng>>> allRoutes = new ArrayList<>();
        Routing currentEDConnector;

        Building building = location != null ? location : startObject.getBuilding() != null ? startObject.getBuilding() : destObject.getBuilding();

        ObjectType type = modeOfTravel != STAIRS ? ELEVATOR : STAIR;

        ArrayList<Routing> destinationEDConnectors =
                filterConnectorObjectsByType(getConnectors(building, destObject.getLevel()),
                        type);

        ArrayList<Routing> startEDConnectors =
                (ArrayList<Routing>) filterConnectorObjectsByType(getConnectors(building,
                        startObject.getLevel()), type).stream()
                        .filter(startEDObject -> checkIfEDIsValid(destinationEDConnectors, startEDObject.getName()))
                        .collect(toList());

        for (int i = 0; i < startEDConnectors.size(); i++) {
            for (int j = 0; j < 2; j++) {
                if (j == 0) { //Start room/connector to elevation or de-elevation point
                    currentEDConnector = startEDConnectors.get(i); //<<--Current ED at start level
                    boolean sameLanesForStart;
                    int startObjectLane = startObject.getType() == ROOM ? startObject.getLane() : startObject.getPrimeLanes()[0];

                    //Check if currentED is in same lane as start room
                    sameLanesForStart = Arrays.stream(currentEDConnector.getPrimeLanes())
                            .anyMatch(lane -> startObjectLane == lane);

                    if (sameLanesForStart) {
                        allRoutes.add(new ArrayList<ArrayList<LatLng>>());
                        allRoutes.get(i).add(new ArrayList<>());
                        allRoutes.get(i).get(j).add(startObject.getLatLng());
                        allRoutes.get(i).get(j).add(currentEDConnector.getLatLng());
                    } else {

                        Multimap<Double, ArrayList<LatLng>> firstLevelSLO =
                                performSingleLevelSLO(startObject, currentEDConnector, modeOfTravel, building);

                        if (firstLevelSLO != null) {
                            List<ArrayList<LatLng>> bestRoutes =
                                    (List<ArrayList<LatLng>>) firstLevelSLO.get(min(firstLevelSLO.keySet()));

                            allRoutes.add(new ArrayList<ArrayList<LatLng>>());
                            allRoutes.get(i).add(bestRoutes.get(0));
                        } else {
                            startEDConnectors.remove(i);
                            i--;
                            break;
                        }

                    }
                } else { //Elevation/De-Elevation point to destination room/connector

                    currentEDConnector = getConnectorObjectFromName(destinationEDConnectors,
                            startEDConnectors.get(i).getName()); //<<-- Previous Current ED's destn level equivalent

                    int destObjectLane = destObject.getType() == ROOM ? destObject.getLane() : destObject.getPrimeLanes()[0];

                    boolean sameLanesForDestination = Arrays.stream(currentEDConnector.getPrimeLanes())
                            .anyMatch(lane -> destObjectLane == lane);

                    if (sameLanesForDestination) {
                        allRoutes.get(i).add(new ArrayList<LatLng>());
                        allRoutes.get(i).get(j).add(currentEDConnector.getLatLng());
                        allRoutes.get(i).get(j).add(destObject.getLatLng());
                    } else {

                        Multimap<Double, ArrayList<LatLng>> secondLevelSLO =
                                performSingleLevelSLO(currentEDConnector, destObject, modeOfTravel, building);

                        if (secondLevelSLO != null) {
                            List<ArrayList<LatLng>> bestRoutes =
                                    (List<ArrayList<LatLng>>) secondLevelSLO.get(min(secondLevelSLO.keySet()));

                            allRoutes.get(i).add(bestRoutes.get(0));
                        } else {
                            //Remove firstLevelSLO calculated route
                            allRoutes.remove(i);
                            startEDConnectors.remove(i);
                            i--;
                        }
                    }
                }
            }
        }

        if (!allRoutes.isEmpty()) {
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
     * @return boolean
     */
    private static boolean checkIfEDIsValid(ArrayList<Routing> destLevelEDPoints, String edName) {
        return destLevelEDPoints.parallelStream()
                .anyMatch(destLevelED -> destLevelED.getName().equals(edName));
    }

}
