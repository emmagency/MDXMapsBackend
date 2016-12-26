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

/**
 * Provides helper methods to carry out trivial operations such as calculations, filtering, sorting, etc.
 */

public final class UtilService {
    private UtilService() {
    }

    /**
     * Method to filter connector objects by connector type
     *
     * @param connectors Arraylist of connecters to be filter
     * @param type       Connector type we want
     * @return The filtered list.
     */
    public static ArrayList<RoutingObjects> filterConnectorObjectsByType(ArrayList<RoutingObjects> connectors, String type) {

        for (int i = 0; i < connectors.size(); i++) {
            if (!connectors.get(i).getType().equals(type)) {
                connectors.remove(i);
                i -= 1;
            }
        }
        return connectors;
    }

    public static ArrayList<ArrayList<RoutingObjects>> removeNonDisabledRoutes(ArrayList<ArrayList<RoutingObjects>> validRoutes) {

        return (ArrayList<ArrayList<RoutingObjects>>) validRoutes.stream()
                .filter(route -> !route.stream()
                        .filter(connectorObject -> connectorObject.getIsWheelChairAccessible().equals("N"))
                        .findFirst().isPresent())
                .collect(Collectors.toList());
    }

    /**
     * @param allLatLngs ArrayList of all routes. Each sub ArrayList is a full route
     * @return A sorted, non-duplicated key-value multimap containing all the passed routes with distance as key
     */
    public static Multimap<Double, ArrayList<LatLng>> calculateMultipleRoutesDistanceAndSort(ArrayList<ArrayList<LatLng>> allLatLngs) {

        //ListMultimap<Double, ArrayList<LatLng>> multimap = ArrayListMultimap.create();

        Multimap<Double, ArrayList<LatLng>> multimap = MultimapBuilder.treeKeys().linkedListValues().build();

        for (int i = 0; i < allLatLngs.size(); i++) {
            double distance = 0;
            for (int j = 0; j < allLatLngs.get(i).size() - 1; j++) {
                double temp = calculateDistance(allLatLngs.get(i).get(j).latitude,
                        allLatLngs.get(i).get(j).longitude,
                        allLatLngs.get(i).get(j + 1).latitude,
                        allLatLngs.get(i).get(j + 1).longitude);
                distance += temp;
            }
            multimap.put(distance, allLatLngs.get(i));
        }

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
