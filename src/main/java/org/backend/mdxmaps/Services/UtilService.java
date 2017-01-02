package org.backend.mdxmaps.Services;

/**
 * Created by Emmanuel Keboh on 21/12/2016.
 */

import com.google.common.collect.Multimap;
import com.google.common.collect.MultimapBuilder;
import org.backend.mdxmaps.Model.LatLng;
import org.backend.mdxmaps.Model.RoutingObjects;

import java.util.ArrayList;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

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
    public static ArrayList<RoutingObjects> filterConnectorObjectsByType(ArrayList<RoutingObjects> connectors, String type) {
        return (ArrayList<RoutingObjects>) connectors.parallelStream()
                .filter(connector -> connector.getType().equals(type))
                .collect(Collectors.toList());
    }

    public static ArrayList<ArrayList<RoutingObjects>> removeNonDisabledRoutes(ArrayList<ArrayList<RoutingObjects>> validRoutes) {
        return (ArrayList<ArrayList<RoutingObjects>>) validRoutes.parallelStream()
                .filter(route -> route.parallelStream()
                        .noneMatch(connectorObject -> connectorObject.getIsWheelChairAccessible().equals("N")))
                .collect(Collectors.toList());
    }

    /**
     * @param allLatLngs Each sub ArrayList is either a full or sub route, depending on the calling method
     * @return A sorted, non-duplicated key-value multimap containing all the passed routes with distance as key
     */
    public static Multimap<Double, ArrayList<LatLng>> calculateMultipleRoutesDistanceAndSort(ArrayList<ArrayList<LatLng>> allLatLngs) {

        Multimap<Double, ArrayList<LatLng>> multimap = MultimapBuilder.treeKeys().linkedListValues().build();
        allLatLngs.forEach(allLatLng ->
                {
                    double distance = IntStream.range(0, allLatLng.size() - 1)
                            .mapToDouble(j -> calculateDistance(allLatLng.get(j).latitude,
                                    allLatLng.get(j).longitude,
                                    allLatLng.get(j + 1).latitude,
                                    allLatLng.get(j + 1).longitude))
                            .sum();
                    multimap.put(distance, allLatLng);
                }
        );
        return multimap;
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
