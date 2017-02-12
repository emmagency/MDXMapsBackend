package org.backend.mdxmaps.Services;

/**
 * Created by Emmanuel Keboh on 21/12/2016.
 */

import com.google.common.collect.Multimap;
import com.google.common.collect.MultimapBuilder;
import org.backend.mdxmaps.Model.LatLng;
import org.backend.mdxmaps.Model.RoutingObjects;

import java.util.ArrayList;
import java.util.Objects;
import java.util.function.Function;
import java.util.stream.IntStream;

import static java.util.stream.Collectors.toList;
import static org.backend.mdxmaps.Services.RoutingObjectsGetterUtilService.getRequiredConnectorObjectsForRoutes;

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

    public static Double calculateOutsideRouteDistance(ArrayList<ArrayList<ArrayList<LatLng>>> route) {
        return IntStream.range(0, route.size())
                .mapToDouble(i -> calculateSingleRouteDistance(route.get(i)))
                .sum();
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

    public static ArrayList<ArrayList<RoutingObjects>> transformValidRoutesStringToObjects(ArrayList<ArrayList<String>> validRoutes,
                                                                                           ArrayList<RoutingObjects> connectorObjects) {

        ArrayList<RoutingObjects> requiredConnectorsObjects = getRequiredConnectorObjectsForRoutes(validRoutes, connectorObjects);

        return (ArrayList<ArrayList<RoutingObjects>>) validRoutes.stream()
                .map(stringRoute -> (ArrayList<RoutingObjects>) stringRoute.stream()
                        .map(string -> requiredConnectorsObjects.stream()
                                .filter(connectorObject -> connectorObject.getName().equals(string))
                                .findFirst().orElse(null))
                        .filter(Objects::nonNull)
                        .collect(toList()))
                .collect(toList());

    }

    public static Function<ArrayList<RoutingObjects>, ArrayList<LatLng>> validRouteObjectsToLatLngTransformer() {
        return validRoute -> (ArrayList<LatLng>) validRoute.stream()
                .map(RoutingObjects::getLatLng)
                .collect(toList());
    }

    /**
     * @param start       the start object
     * @param end         the destination object
     * @param validRoutes the list of routes
     * @return list
     */
    public static ArrayList<ArrayList<RoutingObjects>> plugInStartAndEndObjects(RoutingObjects start, RoutingObjects end,
                                                                         ArrayList<ArrayList<RoutingObjects>> validRoutes) {
        return (ArrayList<ArrayList<RoutingObjects>>) validRoutes.stream()
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
