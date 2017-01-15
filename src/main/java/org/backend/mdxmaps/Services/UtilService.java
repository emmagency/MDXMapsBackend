package org.backend.mdxmaps.Services;

/**
 * Created by Emmanuel Keboh on 21/12/2016.
 */

import com.google.common.collect.Multimap;
import com.google.common.collect.MultimapBuilder;
import org.backend.mdxmaps.Model.LatLng;
import org.backend.mdxmaps.Model.RoutingObjects;

import java.util.ArrayList;
import java.util.function.Function;
import java.util.stream.IntStream;

import static java.util.stream.Collectors.toList;

/**
 * Provides helper methods to carry out trivial operations such as calculations, filtering, sorting, etc.
 */

public final class UtilService {
    private UtilService() {
    }

    /**
     * Method to filter connector objects by connector type
     *
     * @param connectors Arraylist of connecters to filter
     * @param type       Connector type we want
     * @return The filtered list.
     */
    static ArrayList<RoutingObjects> filterConnectorObjectsByType(ArrayList<RoutingObjects> connectors, String type) {
        return (ArrayList<RoutingObjects>) connectors.parallelStream()
                .filter(connector -> connector.getType().equals(type))
                .collect(toList());
    }

    static ArrayList<ArrayList<RoutingObjects>> removeNonDisabledRoutes(ArrayList<ArrayList<RoutingObjects>> validRoutes) {
        return (ArrayList<ArrayList<RoutingObjects>>) validRoutes.parallelStream()
                .filter(route -> route.parallelStream()
                        .noneMatch(connectorObject -> connectorObject.getIsWheelChairAccessible().equals("N")))
                .collect(toList());
    }

    /**
     * @param allLatLngs Arraylist of routes
     * @return A sorted, non-duplicated key-value multimap containing all the passed routes with distance as key
     */
    static Multimap<Double, ArrayList<LatLng>> calculateMultipleRoutesDistanceAndSort(ArrayList<ArrayList<LatLng>> allLatLngs) {

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

    static double calculateSingleRouteDistance(ArrayList<ArrayList<LatLng>> route) {
        return route.stream()
                .mapToDouble(singleRoute -> IntStream.range(0, singleRoute.size() - 1)
                        .mapToDouble(i -> calculateDistance(singleRoute.get(i).latitude,
                                singleRoute.get(i).longitude,
                                singleRoute.get(i + 1).latitude,
                                singleRoute.get(i + 1).longitude))
                        .sum())
                .sum();
    }

    static ArrayList<ArrayList<RoutingObjects>> transformValidRoutesStringToObjects(ArrayList<ArrayList<String>> validRoutes,
                                                                                    ArrayList<RoutingObjects> connectorObjects) {
        ArrayList<String> requiredConnectors = new ArrayList<>();

        validRoutes.forEach(stringsArrayList -> stringsArrayList.forEach(string -> {
            if (!requiredConnectors.contains(string)) {
                requiredConnectors.add(string);
            }
        }));

        ArrayList<RoutingObjects> requiredConnectorsObjects =
                (ArrayList<RoutingObjects>) requiredConnectors.stream()
                        .map(string -> connectorObjects.stream()
                                .filter(connectorObject -> connectorObject.getName().equals(string))
                                .findFirst().get())
                        .collect(toList());


        return (ArrayList<ArrayList<RoutingObjects>>) validRoutes.stream()
                .map(stringRoute -> (ArrayList<RoutingObjects>) stringRoute.stream()
                        .map(string -> requiredConnectorsObjects.stream()
                                .filter(connectorObject -> connectorObject.getName().equals(string))
                                .findFirst().get())
                        .collect(toList()))
                .collect(toList());

    }

    static Function<ArrayList<RoutingObjects>, ArrayList<LatLng>> validRouteObjectToLatLngTransformer() {
        return validRoute -> (ArrayList<LatLng>) validRoute.stream()
                .map(RoutingObjects::getLatLng)
                .collect(toList());
    }

    static ArrayList<ArrayList<RoutingObjects>> plugInStartAndEndObjects(RoutingObjects start, RoutingObjects end,
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
