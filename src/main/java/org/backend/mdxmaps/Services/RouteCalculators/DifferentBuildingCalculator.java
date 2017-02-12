package org.backend.mdxmaps.Services.RouteCalculators;

import com.google.common.collect.Multimap;
import com.google.common.collect.MultimapBuilder;
import org.backend.mdxmaps.Model.Enums.MOT;
import org.backend.mdxmaps.Model.LatLng;
import org.backend.mdxmaps.Model.RoutingObjects;
import org.backend.mdxmaps.Services.Algorithms.OutdoorAlgorithm;
import org.backend.mdxmaps.Services.UtilService;

import java.util.ArrayList;
import java.util.List;

import static java.util.Collections.min;
import static java.util.stream.Collectors.toList;
import static org.backend.mdxmaps.Model.Enums.MOT.DISABLED;
import static org.backend.mdxmaps.Model.Enums.MOT.NULL;
import static org.backend.mdxmaps.Model.Enums.ObjectType.DOOR;
import static org.backend.mdxmaps.Model.RoutingObjects.getOutsideConnectors;
import static org.backend.mdxmaps.Services.RouteCalculators.MultiLevelSLOCalculator.performMultiLevelSLO;
import static org.backend.mdxmaps.Services.RouteCalculators.SingleLevelSLOCalculator.performSingleLevelSLO;
import static org.backend.mdxmaps.Services.RoutingObjectsGetterUtilService.filterConnectorObjectsByType;
import static org.backend.mdxmaps.Services.RoutingObjectsGetterUtilService.getConnectors;
import static org.backend.mdxmaps.Services.RoutingObjectsGetterUtilService.removeNonDisabledObjects;
import static org.backend.mdxmaps.Services.UtilService.calculateMultipleRoutesDistanceAndSort;
import static org.backend.mdxmaps.Services.UtilService.calculateOutsideRouteDistance;
import static org.backend.mdxmaps.Services.UtilService.transformValidRoutesStringToObjects;
import static org.backend.mdxmaps.Services.UtilService.validRouteObjectsToLatLngTransformer;

/**
 * Created by Emmanuel Keboh on 22/01/2017.
 */
public class DifferentBuildingCalculator {

    @SuppressWarnings("Duplicates")
    public static Multimap<Double, ArrayList<ArrayList<ArrayList<LatLng>>>> performDifferentBuildingCalculation(RoutingObjects startRoomObject, RoutingObjects destinationRoomObject,
                                                                                                                MOT startEDMethod, MOT destEDMethod, boolean disabled,
                                                                                                                boolean isStartED, boolean isDestED) {

        ArrayList<RoutingObjects> startBuildingDoors = filterConnectorObjectsByType(getConnectors(startRoomObject.getBuilding(), 0), DOOR);
        ArrayList<RoutingObjects> destinationBuildingDoors = filterConnectorObjectsByType(getConnectors(destinationRoomObject.getBuilding(), 0), DOOR);

        if (disabled) {
            startBuildingDoors = removeNonDisabledObjects(startBuildingDoors);
            destinationBuildingDoors = removeNonDisabledObjects(destinationBuildingDoors);
        }

        if (startBuildingDoors.isEmpty() || destinationBuildingDoors.isEmpty()) return null;

        startBuildingDoors.trimToSize();
        destinationBuildingDoors.trimToSize();

        Multimap<RoutingObjects, ArrayList<ArrayList<LatLng>>> startIndoorRoutesPerDoor = MultimapBuilder.treeKeys().linkedListValues().build();

        //Calculate route for start room to all possible start doors
        startBuildingDoors.forEach(door -> {
            Multimap<Double, ArrayList<ArrayList<LatLng>>> routes = !isStartED ?
                    singleLevelSLOFormatter(performSingleLevelSLO(startRoomObject, door, disabled ? DISABLED : NULL)) :
                    performMultiLevelSLO(startRoomObject, door, startEDMethod, startRoomObject.getBuilding());

            if (routes != null) {
                List<ArrayList<ArrayList<LatLng>>> arrayLists = (List<ArrayList<ArrayList<LatLng>>>) routes.get(min(routes.keySet()));
                startIndoorRoutesPerDoor.put(door, arrayLists.get(0));
            }
        });

        //Calculate route from all possible doors at destination building to destination room
        Multimap<RoutingObjects, ArrayList<ArrayList<LatLng>>> destinationIndoorRoutesPerDoor = MultimapBuilder.treeKeys().linkedListValues().build();
        destinationBuildingDoors.forEach(door -> {
            Multimap<Double, ArrayList<ArrayList<LatLng>>> routes = !isDestED ?
                    singleLevelSLOFormatter(performSingleLevelSLO(door, destinationRoomObject, disabled ? DISABLED : NULL)) :
                    performMultiLevelSLO(door, destinationRoomObject, destEDMethod, destinationRoomObject.getBuilding());

            if (routes != null) {
                List<ArrayList<ArrayList<LatLng>>> arrayLists = (List<ArrayList<ArrayList<LatLng>>>) routes.get(min(routes.keySet()));
                destinationIndoorRoutesPerDoor.put(door, arrayLists.get(0));
            }
        });

        //ToDo Throw exception here indicating where the exact problem lies
        if (startIndoorRoutesPerDoor.isEmpty() || destinationIndoorRoutesPerDoor.isEmpty()) return null;

        Multimap<Double, ArrayList<ArrayList<ArrayList<LatLng>>>> finalRoutes = MultimapBuilder.treeKeys().linkedListValues().build();

        //Calculate outside route from each door at start building to each door at destination building
        startIndoorRoutesPerDoor.keySet().forEach(startDoor ->
                destinationIndoorRoutesPerDoor.keySet().forEach(destinationDoor -> {
                    ArrayList<LatLng> outsideRoute = calculateOutsideRoute(startDoor, destinationDoor, disabled);
                    if (outsideRoute != null) {
                        ArrayList<ArrayList<ArrayList<LatLng>>> currentRoute = new ArrayList<>();
                        currentRoute.add(((List<ArrayList<ArrayList<LatLng>>>) startIndoorRoutesPerDoor.get(startDoor)).get(0));//StartBuilding Route
                        currentRoute.add(new ArrayList<>());
                        currentRoute.get(1).add(outsideRoute); //Outside route
                        currentRoute.add(((List<ArrayList<ArrayList<LatLng>>>) destinationIndoorRoutesPerDoor.get(destinationDoor)).get(0)); //DestinationBuilding Route
                        currentRoute.trimToSize();

                        finalRoutes.put(calculateOutsideRouteDistance(currentRoute), currentRoute);
                    }

                }));


        return finalRoutes.isEmpty() ? null : finalRoutes;
    }

    //Returns the best route only
    private static Multimap<Double, ArrayList<ArrayList<LatLng>>> singleLevelSLOFormatter(Multimap<Double, ArrayList<LatLng>> routes) {
        if (routes != null) {
            Multimap<Double, ArrayList<ArrayList<LatLng>>> map = MultimapBuilder.treeKeys().linkedListValues().build();
            Double lowestDistance = min(routes.keySet());
            ArrayList<ArrayList<LatLng>> route = new ArrayList<>();
            route.add(((List<ArrayList<LatLng>>) routes.get(lowestDistance)).get(0));
            route.trimToSize();
            map.put(lowestDistance, route);
            return map;
        }
        return null;
    }

    //Filters for the best outside route
    private static ArrayList<LatLng> calculateOutsideRoute(RoutingObjects startDoor, RoutingObjects destinationDoor, boolean disabled) {
        ArrayList<ArrayList<String>> outsideRoutesString = new OutdoorAlgorithm().sameLevelOp(startDoor.getAdjacentConnectors()[1], destinationDoor.getAdjacentConnectors()[1], disabled);

        if (outsideRoutesString.isEmpty()) return null;

        ArrayList<ArrayList<RoutingObjects>> outsideRoutesObjects = transformValidRoutesStringToObjects(outsideRoutesString, getOutsideConnectors());

        outsideRoutesObjects = UtilService.plugInStartAndEndObjects(startDoor, destinationDoor, outsideRoutesObjects);

        ArrayList<ArrayList<LatLng>> outsideRoutesLatLng = (ArrayList<ArrayList<LatLng>>) outsideRoutesObjects.parallelStream()
                .map(validRouteObjectsToLatLngTransformer())
                .collect(toList());

        Multimap<Double, ArrayList<LatLng>> sortedRoutes = calculateMultipleRoutesDistanceAndSort(outsideRoutesLatLng);
        return ((List<ArrayList<LatLng>>) sortedRoutes.get(min(sortedRoutes.keySet()))).get(0);
    }
}
