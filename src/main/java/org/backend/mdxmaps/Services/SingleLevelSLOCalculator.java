package org.backend.mdxmaps.Services;


import com.google.common.collect.Multimap;
import org.backend.mdxmaps.Model.Enums.MOT;
import org.backend.mdxmaps.Model.LatLng;
import org.backend.mdxmaps.Model.RoutingObjects;
import org.backend.mdxmaps.Services.Algorithms.IndoorAlgorithm;

import java.util.ArrayList;
import java.util.stream.Collectors;

import static org.backend.mdxmaps.Model.Enums.MOT.DISABLED;
import static org.backend.mdxmaps.Services.RoutingObjectsGetterUtilService.getAllPrimes;
import static org.backend.mdxmaps.Services.UtilService.calculateMultipleRoutesDistanceAndSort;
import static org.backend.mdxmaps.Services.UtilService.filterConnectorObjectsByType;
import static org.backend.mdxmaps.Services.UtilService.plugInStartAndEndObjects;
import static org.backend.mdxmaps.Services.UtilService.removeNonDisabledRoutes;
import static org.backend.mdxmaps.Services.UtilService.transformValidRoutesStringToObjects;
import static org.backend.mdxmaps.Services.UtilService.validRouteObjectToLatLngTransformer;

/**
 * Created by Emmanuel Keboh on 21/12/2016.
 */
public final class SingleLevelSLOCalculator {

    private SingleLevelSLOCalculator() {
    }

    public static Multimap<Double, ArrayList<LatLng>> performSingleLevelSLO(RoutingObjects start, RoutingObjects end, MOT mot) {

        //Get the actual connector objects and filter objects by type
        ArrayList<RoutingObjects> connectorObjects =
                filterConnectorObjectsByType(new RoutingObjects().getConnectors(start.getBuilding(),
                        start.getActualLevel()), "Basic Connector");

        //Run algorithm
        ArrayList<ArrayList<String>> validRoutes = new IndoorAlgorithm().sameLevelOp(
                getAllPrimes(start.getName(), connectorObjects), end.getLane(), start.getBuilding(), start.getActualLevel());

        //Transform algorithm result to objects
        ArrayList<ArrayList<RoutingObjects>> validRoutesObjects = transformValidRoutesStringToObjects(validRoutes, connectorObjects);

        //ToDO Throw Exception if validRoutes is empty
        if (mot.equals(DISABLED)) {
            validRoutesObjects = removeNonDisabledRoutes(validRoutesObjects);
        }

        if (validRoutesObjects.size() != 0) {
            validRoutesObjects = plugInStartAndEndObjects(start, end, validRoutesObjects);

            ArrayList<ArrayList<LatLng>> allLatLngs = (ArrayList<ArrayList<LatLng>>) validRoutesObjects.stream()
                    .map(validRouteObjectToLatLngTransformer())
                    .collect(Collectors.toList());

            return calculateMultipleRoutesDistanceAndSort(allLatLngs);
        }

        return null;
    }


}
