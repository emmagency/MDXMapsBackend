package org.backend.mdxmaps.service.util;

/**
 * Created by Emmanuel Keboh on 22/12/2016.
 */

import org.backend.mdxmaps.model.Routing;
import org.backend.mdxmaps.model.enums.ObjectType;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Objects;

import static java.util.stream.Collectors.toList;
import static org.backend.mdxmaps.model.Routing.connectorMapInit;
import static org.backend.mdxmaps.model.Routing.getRooms;

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
    public static ArrayList<Routing> getAllPrimes(String roomNumber, ArrayList<Routing> connectorObjects) {
        //Get connectors (stored as string values)
        String[] laneConnectors = getRoomObjectFromName(roomNumber).getLaneConnectors();

        return (ArrayList<Routing>) Arrays.stream(laneConnectors)
                .map(laneConnector -> connectorObjects.parallelStream()
                        .filter(connectorObject -> connectorObject.getName().equals(laneConnector))
                        .findFirst().orElse(null))
                .filter(Objects::nonNull)
                .collect(toList());
    }

    public static Routing getConnectorObjectFromName(ArrayList<Routing> connectors, String name) {
        return connectors.parallelStream()
                .filter(connector -> connector.getName().equals(name))
                .findFirst().orElse(null);
    }

    public static ArrayList<Routing> getRequiredConnectorObjectsForRoutes(ArrayList<ArrayList<String>> validRoutes,
                                                                          ArrayList<Routing> connectorObjects) {
        ArrayList<String> requiredConnectorsString = new ArrayList<>();

        validRoutes.forEach(stringsArrayList -> stringsArrayList.forEach(string -> {
            if (!requiredConnectorsString.contains(string)) {
                requiredConnectorsString.add(string);
            }
        }));

        return (ArrayList<Routing>) requiredConnectorsString.parallelStream()
                .map(string -> connectorObjects.parallelStream()
                        .filter(connectorObject -> connectorObject.getName().equals(string))
                        .findFirst().orElse(null))
                .filter(Objects::nonNull)
                .collect(toList());
    }

    public static Routing getRoomObjectFromName(String name) {
        return getRooms().parallelStream()
                .filter(room -> room.getName().equalsIgnoreCase(name))
                .findFirst().orElse(null);
    }

    //Get same lane basic connectors from connector object
    public static ArrayList<Routing> getSameLanesBCForConnectorObject(ArrayList<Routing> basicConnectors, Routing connectorObject) {
        return (ArrayList<Routing>) basicConnectors.stream()
                .filter(basicConnector -> Arrays.stream(basicConnector.getPrimeLanes())
                        .anyMatch(connectorPrime -> Arrays.stream(connectorObject.getPrimeLanes())
                                .anyMatch(edObjectPrime -> edObjectPrime == connectorPrime)))
                .collect(toList());
    }

    public static ArrayList<Routing> getConnectors(String building, int actualLevel) {
        return connectorMapInit(building, actualLevel).get(building);
    }

    public static ArrayList<ArrayList<Routing>> removeNonDisabledRoutes(ArrayList<ArrayList<Routing>> validRoutes) {
        return (ArrayList<ArrayList<Routing>>) validRoutes.parallelStream()
                .filter(route -> route.parallelStream()
                        .noneMatch(connectorObject -> connectorObject.getIsWheelChairAccessible().equals("N")))
                .collect(toList());
    }

    public static ArrayList<Routing> removeNonDisabledObjects(ArrayList<Routing> objects) {
        return (ArrayList<Routing>) objects.parallelStream()
                .filter(object -> !object.getIsWheelChairAccessible().equals("N"))
                .collect(toList());
    }

    public static ArrayList<Routing> filterConnectorObjectsByType(ArrayList<Routing> connectors, ObjectType type) {
        return (ArrayList<Routing>) connectors.parallelStream()
                .filter(connector -> connector.getType() == type)
                .collect(toList());
    }
}
