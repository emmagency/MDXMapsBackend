package org.backend.mdxmaps.Services.Util;

/**
 * Created by Emmanuel Keboh on 22/12/2016.
 */

import org.backend.mdxmaps.Model.Enums.ObjectType;
import org.backend.mdxmaps.Model.RoutingObjects;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Objects;

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
                        .findFirst().orElse(null))
                .filter(Objects::nonNull)
                .collect(toList());
    }

    public static RoutingObjects getConnectorObjectFromName(ArrayList<RoutingObjects> connectors, String name) {
        return connectors.parallelStream()
                .filter(connector -> connector.getName().equals(name))
                .findFirst().orElse(null);
    }

    public static ArrayList<RoutingObjects> getRequiredConnectorObjectsForRoutes(ArrayList<ArrayList<String>> validRoutes,
                                                                                 ArrayList<RoutingObjects> connectorObjects) {
        ArrayList<String> requiredConnectorsString = new ArrayList<>();

        validRoutes.forEach(stringsArrayList -> stringsArrayList.forEach(string -> {
            if (!requiredConnectorsString.contains(string)) {
                requiredConnectorsString.add(string);
            }
        }));

        return (ArrayList<RoutingObjects>) requiredConnectorsString.parallelStream()
                .map(string -> connectorObjects.parallelStream()
                        .filter(connectorObject -> connectorObject.getName().equals(string))
                        .findFirst().orElse(null))
                .filter(Objects::nonNull)
                .collect(toList());
    }

    public static RoutingObjects getRoomObjectFromName(String name) {
        return getRooms().parallelStream()
                .filter(room -> room.getName().equals(name))
                .findFirst().orElse(null);
    }

    //Get same lane basic connectors from connector object
    public static ArrayList<RoutingObjects> getSameLanesBCForConnectorObject(ArrayList<RoutingObjects> basicConnectors, RoutingObjects connectorObject) {
        return (ArrayList<RoutingObjects>) basicConnectors.stream()
                .filter(basicConnector -> Arrays.stream(basicConnector.getPrimeLanes())
                        .anyMatch(connectorPrime -> Arrays.stream(connectorObject.getPrimeLanes())
                                .anyMatch(edObjectPrime -> edObjectPrime == connectorPrime)))
                .collect(toList());
    }

    public static ArrayList<RoutingObjects> getConnectors(String building, int actualLevel) {
        return connectorMapInit(building, actualLevel).get(building);
    }

    public static ArrayList<ArrayList<RoutingObjects>> removeNonDisabledRoutes(ArrayList<ArrayList<RoutingObjects>> validRoutes) {
        return (ArrayList<ArrayList<RoutingObjects>>) validRoutes.parallelStream()
                .filter(route -> route.parallelStream()
                        .noneMatch(connectorObject -> connectorObject.getIsWheelChairAccessible().equals("N")))
                .collect(toList());
    }

    public static ArrayList<RoutingObjects> removeNonDisabledObjects(ArrayList<RoutingObjects> objects) {
        return (ArrayList<RoutingObjects>) objects.parallelStream()
                .filter(object -> !object.getIsWheelChairAccessible().equals("N"))
                .collect(toList());
    }

    public static ArrayList<RoutingObjects> filterConnectorObjectsByType(ArrayList<RoutingObjects> connectors, ObjectType type) {
        return (ArrayList<RoutingObjects>) connectors.parallelStream()
                .filter(connector -> connector.getType() == type)
                .collect(toList());
    }
}
