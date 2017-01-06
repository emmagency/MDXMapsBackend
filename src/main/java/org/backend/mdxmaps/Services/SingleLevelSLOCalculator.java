package org.backend.mdxmaps.Services;


import com.google.common.collect.Multimap;
import org.backend.mdxmaps.Model.LatLng;
import org.backend.mdxmaps.Model.MOT;
import org.backend.mdxmaps.Model.RoutingObjects;
import org.backend.mdxmaps.Services.Algorithms.IndoorAlgorithm;

import java.util.ArrayList;
import java.util.stream.Collectors;

import static org.backend.mdxmaps.Model.MOT.DISABLED;
import static org.backend.mdxmaps.Services.RoutingObjectsGetterUtilService.getAllPrimes;
import static org.backend.mdxmaps.Services.UtilService.calculateMultipleRoutesDistanceAndSort;
import static org.backend.mdxmaps.Services.UtilService.removeNonDisabledRoutes;

/**
 * Created by Emmanuel Keboh on 21/12/2016.
 */
public final class SingleLevelSLOCalculator {

    private SingleLevelSLOCalculator() {
    }

    public static Multimap<Double, ArrayList<LatLng>> performSingleLevelSLO(RoutingObjects start, RoutingObjects end, MOT mot) {

        //Get the actual connector objects and filter objects by type
        ArrayList<RoutingObjects> connectorObjects =
                UtilService.filterConnectorObjectsByType(new RoutingObjects().getConnectors(start.getBuilding(),
                        start.getActualLevel()), "Basic Connector");

        //Run algorithm
        ArrayList<ArrayList<String>> validRoutes = new IndoorAlgorithm().sameLevelOp(
                getAllPrimes(start.getName(), connectorObjects), end.getLane(), start.getBuilding(), start.getActualLevel());

        ArrayList<String> requiredConnectors = new ArrayList<>();

        validRoutes.forEach(strings -> strings.forEach(string -> {
            if (!requiredConnectors.contains(string)) {
                requiredConnectors.add(string);
            }
        }));

        ArrayList<RoutingObjects> requiredConnectorsObjects = (ArrayList<RoutingObjects>) requiredConnectors.stream()
                .map(string -> connectorObjects.stream().filter(connectorObject ->
                        connectorObject.getName().equals(string)).findFirst().get())
                .collect(Collectors.toList());

        ArrayList<ArrayList<RoutingObjects>> validRoutesObjects;

        //finalValidRoutesObjects required because of Java's final or effectively final rule for variables used in lambdas/anonymous classes
        ArrayList<ArrayList<RoutingObjects>> finalValidRoutesObjects = new ArrayList<>();
        validRoutes.forEach(strings -> finalValidRoutesObjects.add((ArrayList<RoutingObjects>) strings.stream()
                .map(string -> requiredConnectorsObjects.stream()
                        .filter(connectorObject -> connectorObject.getName().equals(string))
                        .findFirst().get())
                .collect(Collectors.toList())));

        if (mot.equals(DISABLED)) {
            validRoutesObjects = removeNonDisabledRoutes(finalValidRoutesObjects);
        } else {
            validRoutesObjects = finalValidRoutesObjects;
        }

        if (validRoutesObjects.size() != 0) {

            ArrayList<ArrayList<LatLng>> allLatLngs = new ArrayList<>();

            validRoutesObjects.forEach(route -> allLatLngs.add((ArrayList<LatLng>) route.stream()
                    .map(RoutingObjects::getLatLng)
                    .collect(Collectors.toList())));

            allLatLngs.forEach(route -> {
                route.add(0, start.getLatLng());
                route.add(end.getLatLng());
            });
            return calculateMultipleRoutesDistanceAndSort(allLatLngs);

        } else {
            return null;
        }

    }


}
