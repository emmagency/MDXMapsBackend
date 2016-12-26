package org.backend.mdxmaps.Services;

/**
 * Created by Emmanuel Keboh on 22/12/2016.
 */

import org.backend.mdxmaps.Model.RoutingObjects;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.stream.Collectors;

import static org.backend.mdxmaps.Model.RoutingObjects.getRooms;

/**
 * Provides methods used in getting specific routing objects from a collection
 */
public final class RoutingObjectsGetterUtilService {
    private RoutingObjectsGetterUtilService() {
    }

    /**
     * @param roomNumber:       The room we're getting primes for
     * @param connectorObjects: List of all the connector objects on the same floor as the room
     * @return
     */
    public static ArrayList<RoutingObjects> getAllPrimes(String roomNumber, ArrayList<RoutingObjects> connectorObjects) {
        //Get connectors (stored as string values)
        String[] laneConnectors = getRoomObjectFromName(roomNumber).getLaneConnectors();

        return (ArrayList<RoutingObjects>) Arrays.stream(laneConnectors)
                .map(laneConnector -> connectorObjects.parallelStream()
                        .filter(connectorObject -> connectorObject.getName().equals(laneConnector))
                        .findFirst().get())
                .collect(Collectors.toList());
    }

    public static RoutingObjects getConnectorObjectFromName(ArrayList<RoutingObjects> connectors, String name) {
        return connectors.stream()
                .filter(connector -> connector.getName().equals(name))
                .findFirst().orElse(null);
    }

    public static RoutingObjects getRoomObjectFromName(String name) {
        return getRooms().stream()
                .filter(room -> room.getName().equals(name))
                .findFirst().orElse(null);
    }

}
