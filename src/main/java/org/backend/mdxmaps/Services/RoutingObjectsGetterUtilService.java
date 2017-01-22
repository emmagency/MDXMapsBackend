package org.backend.mdxmaps.Services;

/**
 * Created by Emmanuel Keboh on 22/12/2016.
 */

import com.google.common.collect.Multimap;
import org.backend.mdxmaps.Model.LatLng;
import org.backend.mdxmaps.Model.RoutingObjects;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import static java.util.stream.Collectors.toList;
import static org.backend.mdxmaps.Model.RoutingObjects.connectorMapInit;
import static org.backend.mdxmaps.Model.RoutingObjects.getRooms;

/**
 * Provides methods used in getting specific routing objects from a collection
 */
public final class RoutingObjectsGetterUtilService {

    private RoutingObjectsGetterUtilService() {
    }

    /**
     * @param roomNumber The room we're getting primes for
     * @param connectorObjects List of all the connector objects on the same floor as the room
     * @return
     */
    public static ArrayList<RoutingObjects> getAllPrimes(String roomNumber, ArrayList<RoutingObjects> connectorObjects) {
        //Get connectors (stored as string values)
        String[] laneConnectors = getRoomObjectFromName(roomNumber).getLaneConnectors();

        return (ArrayList<RoutingObjects>) Arrays.stream(laneConnectors)
                .map(laneConnector -> connectorObjects.parallelStream()
                        .filter(connectorObject -> connectorObject.getName().equals(laneConnector))
                        .findFirst().get())
                .collect(toList());
    }

    public static RoutingObjects getConnectorObjectFromName(ArrayList<RoutingObjects> connectors, String name) {
        return connectors.parallelStream()
                .filter(connector -> connector.getName().equals(name))
                .findFirst().orElse(null);
    }

    public static RoutingObjects getRoomObjectFromName(String name) {
        return getRooms().parallelStream()
                .filter(room -> room.getName().equals(name))
                .findFirst().orElse(null);
    }

    //Get same lane basic connectors from connector object
    public static ArrayList<RoutingObjects> getSameLanesBCForConnetorObject(ArrayList<RoutingObjects> basicConnectors, RoutingObjects connectorObject) {
        return (ArrayList<RoutingObjects>) basicConnectors.stream()
                .filter(basicConnector -> Arrays.stream(basicConnector.getPrimeLanes())
                        .anyMatch(connectorPrime -> Arrays.stream(connectorObject.getPrimeLanes())
                                .anyMatch(edObjectPrime -> edObjectPrime == connectorPrime)))
                .collect(toList());
    }

    public static ArrayList<LatLng> getBestRouteFromSortedMultiMap(Multimap<Double, ArrayList<LatLng>> sortedValidRoutesLatLng) {
        double shortestDistance = Collections.min(sortedValidRoutesLatLng.keySet());
        return ((List<ArrayList<LatLng>>) sortedValidRoutesLatLng.get(shortestDistance)).get(0);
    }

    public static ArrayList<RoutingObjects> getConnectors(String building, int actualLevel) {
        return connectorMapInit(building, actualLevel).get(building);
    }
}
