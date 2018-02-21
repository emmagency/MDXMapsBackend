package org.backend.mdxmaps.service.util;

/**
 * Created by Emmanuel Keboh on 21/12/2016.
 */

import com.google.common.collect.Multimap;
import com.google.common.collect.MultimapBuilder;
import org.backend.mdxmaps.model.LatLng;
import org.backend.mdxmaps.model.Routing;

import java.util.ArrayList;
import java.util.Objects;
import java.util.function.Function;
import java.util.stream.IntStream;

import static java.util.stream.Collectors.toList;
import static org.backend.mdxmaps.service.util.RoutingObjectsGetterUtilService.getRequiredConnectorObjectsForRoutes;

/**
 * Provides helper methods to carry out trivial operations such as calculations, object transformation, etc.
 */

public final class UtilService {

    private UtilService() {
    }

    /**
     * @param allLatLngs Arraylist of routes
     * @return A sorted, non-duplicated key-value multimap containing all the passed routes with distance as key
     */
    public static Multimap<Double, ArrayList<LatLng>> calculateMultipleRoutesDistanceAndSort(ArrayList<ArrayList<LatLng>> allLatLngs) {

        Multimap<Double, ArrayList<LatLng>> multimap = MultimapBuilder.treeKeys().linkedListValues().build();
        allLatLngs.forEach(allLatLng ->
                {
                    double distance = IntStream.range(0, allLatLng.size() - 1)
                            .mapToDouble(i -> calculateDistance(allLatLng.get(i).latitude,
                                    allLatLng.get(i).longitude,
                                    allLatLng.get(i + 1).latitude,
                                    allLatLng.get(i + 1).longitude))
                            .sum();
                    multimap.put(distance, allLatLng);
                }
        );
        return multimap;
    }

    public static double calculateOutsideRouteDistance(ArrayList<ArrayList<ArrayList<LatLng>>> route) {
        return route.stream().mapToDouble(UtilService::calculateSingleRouteDistance).sum();
    }

    public static double calculateSingleRouteDistance(ArrayList<ArrayList<LatLng>> route) {
        return route.stream()
                .mapToDouble(singleRoute -> IntStream.range(0, singleRoute.size() - 1)
                        .mapToDouble(i -> calculateDistance(singleRoute.get(i).latitude,
                                singleRoute.get(i).longitude,
                                singleRoute.get(i + 1).latitude,
                                singleRoute.get(i + 1).longitude))
                        .sum())
                .sum();
    }

    public static ArrayList<ArrayList<Routing>> transformValidRoutesStringToObjects(ArrayList<ArrayList<String>> validRoutes,
                                                                                    ArrayList<Routing> connectorObjects) {

        ArrayList<Routing> requiredConnectorsObjects = getRequiredConnectorObjectsForRoutes(validRoutes, connectorObjects);

        return (ArrayList<ArrayList<Routing>>) validRoutes.stream()
                .map(stringRoute -> (ArrayList<Routing>) stringRoute.stream()
                        .map(string -> requiredConnectorsObjects.stream()
                                .filter(connectorObject -> connectorObject.getName().equals(string))
                                .findFirst().orElse(null))
                        .filter(Objects::nonNull)
                        .collect(toList()))
                .collect(toList());

    }

    public static Function<ArrayList<Routing>, ArrayList<LatLng>> validRouteObjectsToLatLngTransformer() {
        return validRoute -> (ArrayList<LatLng>) validRoute.stream()
                .map(Routing::getLatLng)
                .collect(toList());
    }

    /**
     * @param start       the start object
     * @param end         the destination object
     * @param validRoutes the list of routes
     * @return list
     */
    public static ArrayList<ArrayList<Routing>> plugInStartAndEndObjects(Routing start, Routing end,
                                                                         ArrayList<ArrayList<Routing>> validRoutes) {
        return (ArrayList<ArrayList<Routing>>) validRoutes.stream()
                .map(route -> {
                    route.add(0, start);
                    route.add(end);
                    return route;
                }).collect(toList());
    }

    public static double calculateDistance(double startLat, double startLng,
                                           double endLat, double endLng) {

        double latDistance = Math.toRadians(startLat - endLat);
        double lngDistance = Math.toRadians(startLng - endLng);

        double a = Math.sin(latDistance / 2) * Math.sin(latDistance / 2)
                + Math.cos(Math.toRadians(startLat)) * Math.cos(Math.toRadians(endLat))
                * Math.sin(lngDistance / 2) * Math.sin(lngDistance / 2);

        double c = 2 * Math.atan2(Math.sqrt(a), Math.sqrt(1 - a));
        //AVERAGE_RADIUS_OF_EARTH = 6371km
        return (double) (Math.round(6371000 * c));

    }
}
