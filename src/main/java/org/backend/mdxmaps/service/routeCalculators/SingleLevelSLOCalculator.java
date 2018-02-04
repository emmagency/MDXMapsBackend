package org.backend.mdxmaps.service.routeCalculators;


import com.google.common.collect.Multimap;
import org.backend.mdxmaps.model.LatLng;
import org.backend.mdxmaps.model.Routing;
import org.backend.mdxmaps.model.enums.MOT;
import org.backend.mdxmaps.service.algorithms.IndoorAlgorithm;

import java.util.ArrayList;

import static java.util.stream.Collectors.toList;
import static org.backend.mdxmaps.model.enums.MOT.DISABLED;
import static org.backend.mdxmaps.model.enums.ObjectType.BASIC_CONNECTOR;
import static org.backend.mdxmaps.model.enums.ObjectType.ROOM;
import static org.backend.mdxmaps.service.util.RoutingObjectsGetterUtilService.filterConnectorObjectsByType;
import static org.backend.mdxmaps.service.util.RoutingObjectsGetterUtilService.getAllPrimes;
import static org.backend.mdxmaps.service.util.RoutingObjectsGetterUtilService.getConnectors;
import static org.backend.mdxmaps.service.util.RoutingObjectsGetterUtilService.getSameLanesBCForConnectorObject;
import static org.backend.mdxmaps.service.util.RoutingObjectsGetterUtilService.removeNonDisabledRoutes;
import static org.backend.mdxmaps.service.util.UtilService.calculateMultipleRoutesDistanceAndSort;
import static org.backend.mdxmaps.service.util.UtilService.plugInStartAndEndObjects;
import static org.backend.mdxmaps.service.util.UtilService.transformValidRoutesStringToObjects;
import static org.backend.mdxmaps.service.util.UtilService.validRouteObjectsToLatLngTransformer;

/**
 * Created by Emmanuel Keboh on 21/12/2016.
 */
public final class SingleLevelSLOCalculator {

    private SingleLevelSLOCalculator() {
    }

    public static Multimap<Double, ArrayList<LatLng>> performSingleLevelSLO(Routing start, Routing end, MOT mot) {
        return singleLevelSLO(start, end, mot, null);
    }

    public static Multimap<Double, ArrayList<LatLng>> performSingleLevelSLO(Routing start, Routing end, MOT mot, String building) {
        return singleLevelSLO(start, end, mot, building);
    }

    private static Multimap<Double, ArrayList<LatLng>> singleLevelSLO(Routing start, Routing end, MOT mot, String location) {

        String building = location != null ? location : start.getBuilding() != null ? start.getBuilding() : end.getBuilding();

        //Get the actual connector objects and filter objects by type
        ArrayList<Routing> connectorObjects =
                filterConnectorObjectsByType(getConnectors(building,
                        start.getLevel()), BASIC_CONNECTOR);

        ArrayList<Routing> allPrimes = start.getType() == ROOM ?
                getAllPrimes(start.getName(), connectorObjects) : getSameLanesBCForConnectorObject(connectorObjects, start);
        int destLane = end.getType() == ROOM ? end.getLane() : end.getPrimeLanes()[0];

        //Run algorithm
        ArrayList<ArrayList<String>> validRoutes = new IndoorAlgorithm().sameLevelOp(allPrimes, destLane, building, start.getLevel());

        //Transform algorithm result to objects
        ArrayList<ArrayList<Routing>> validRoutesObjects = transformValidRoutesStringToObjects(validRoutes, connectorObjects);

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
