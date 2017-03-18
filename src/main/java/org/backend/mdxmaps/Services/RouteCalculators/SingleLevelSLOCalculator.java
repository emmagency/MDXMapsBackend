package org.backend.mdxmaps.Services.RouteCalculators;


import com.google.common.collect.Multimap;
import org.backend.mdxmaps.Model.Enums.MOT;
import org.backend.mdxmaps.Model.LatLng;
import org.backend.mdxmaps.Model.RoutingObjects;
import org.backend.mdxmaps.Services.Algorithms.IndoorAlgorithm;

import java.util.ArrayList;

import static java.util.stream.Collectors.toList;
import static org.backend.mdxmaps.Model.Enums.MOT.DISABLED;
import static org.backend.mdxmaps.Model.Enums.ObjectType.BASIC_CONNECTOR;
import static org.backend.mdxmaps.Model.Enums.ObjectType.ROOM;
import static org.backend.mdxmaps.Services.Util.RoutingObjectsGetterUtilService.filterConnectorObjectsByType;
import static org.backend.mdxmaps.Services.Util.RoutingObjectsGetterUtilService.getAllPrimes;
import static org.backend.mdxmaps.Services.Util.RoutingObjectsGetterUtilService.getConnectors;
import static org.backend.mdxmaps.Services.Util.RoutingObjectsGetterUtilService.getSameLanesBCForConnectorObject;
import static org.backend.mdxmaps.Services.Util.RoutingObjectsGetterUtilService.removeNonDisabledRoutes;
import static org.backend.mdxmaps.Services.Util.UtilService.calculateMultipleRoutesDistanceAndSort;
import static org.backend.mdxmaps.Services.Util.UtilService.plugInStartAndEndObjects;
import static org.backend.mdxmaps.Services.Util.UtilService.transformValidRoutesStringToObjects;
import static org.backend.mdxmaps.Services.Util.UtilService.validRouteObjectsToLatLngTransformer;

/**
 * Created by Emmanuel Keboh on 21/12/2016.
 */
public final class SingleLevelSLOCalculator {

    private SingleLevelSLOCalculator() {
    }

    public static Multimap<Double, ArrayList<LatLng>> performSingleLevelSLO(RoutingObjects start, RoutingObjects end, MOT mot) {
        return singleLevelSLO(start, end, mot, null);
    }

    public static Multimap<Double, ArrayList<LatLng>> performSingleLevelSLO(RoutingObjects start, RoutingObjects end, MOT mot, String building) {
        return singleLevelSLO(start, end, mot, building);
    }

    private static Multimap<Double, ArrayList<LatLng>> singleLevelSLO(RoutingObjects start, RoutingObjects end, MOT mot, String location) {

        String building = location != null ? location : start.getBuilding() != null ? start.getBuilding() : end.getBuilding();

        //Get the actual connector objects and filter objects by type
        ArrayList<RoutingObjects> connectorObjects =
                filterConnectorObjectsByType(getConnectors(building,
                        start.getActualLevel()), BASIC_CONNECTOR);

        ArrayList<RoutingObjects> allPrimes = start.getType() == ROOM ?
                getAllPrimes(start.getName(), connectorObjects) : getSameLanesBCForConnectorObject(connectorObjects, start);
        int destLane = end.getType() == ROOM ? end.getLane() : end.getPrimeLanes()[0];

        //Run algorithm
        ArrayList<ArrayList<String>> validRoutes = new IndoorAlgorithm().sameLevelOp(allPrimes, destLane, building, start.getActualLevel());

        //Transform algorithm result to objects
        ArrayList<ArrayList<RoutingObjects>> validRoutesObjects = transformValidRoutesStringToObjects(validRoutes, connectorObjects);

        //ToDO Throw Exception if validRoutes is empty
        if (mot.equals(DISABLED)) {
            validRoutesObjects = removeNonDisabledRoutes(validRoutesObjects);
        }

        if (!validRoutesObjects.isEmpty()) {
            validRoutesObjects = plugInStartAndEndObjects(start, end, validRoutesObjects);

            ArrayList<ArrayList<LatLng>> allLatLngs = (ArrayList<ArrayList<LatLng>>) validRoutesObjects.parallelStream()
                    .map(validRouteObjectsToLatLngTransformer())
                    .collect(toList());

            return calculateMultipleRoutesDistanceAndSort(allLatLngs);
        }

        return null;
    }


}
