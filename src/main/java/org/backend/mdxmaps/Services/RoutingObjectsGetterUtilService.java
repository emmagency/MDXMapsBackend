package org.backend.mdxmaps.Services;

/**
 * Created by Emmanuel Keboh on 22/12/2016.
 */

import org.backend.mdxmaps.Model.RoutingObjects;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.stream.Collectors;

import static org.backend.mdxmaps.Model.RoutingObjects.BARN;
import static org.backend.mdxmaps.Model.RoutingObjects.BUILDING10;
import static org.backend.mdxmaps.Model.RoutingObjects.BUILDING9;
import static org.backend.mdxmaps.Model.RoutingObjects.CIRCLE_CAFE;
import static org.backend.mdxmaps.Model.RoutingObjects.COLLEGE;
import static org.backend.mdxmaps.Model.RoutingObjects.GROVE_BLOCK_A;
import static org.backend.mdxmaps.Model.RoutingObjects.GROVE_BLOCK_B;
import static org.backend.mdxmaps.Model.RoutingObjects.GROVE_BLOCK_C;
import static org.backend.mdxmaps.Model.RoutingObjects.HATHCROFT;
import static org.backend.mdxmaps.Model.RoutingObjects.MDXHOUSE;
import static org.backend.mdxmaps.Model.RoutingObjects.PORTACABIN_67;
import static org.backend.mdxmaps.Model.RoutingObjects.PORTACABIN_A;
import static org.backend.mdxmaps.Model.RoutingObjects.PORTACABIN_A_EXT;
import static org.backend.mdxmaps.Model.RoutingObjects.PORTACABIN_B;
import static org.backend.mdxmaps.Model.RoutingObjects.SHEPPARDLIBRARY;
import static org.backend.mdxmaps.Model.RoutingObjects.VINE;
import static org.backend.mdxmaps.Model.RoutingObjects.WILLIAMS;
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
        return connectors.parallelStream()
                .filter(connector -> connector.getName().equals(name))
                .findFirst().orElse(null);
    }

    public static RoutingObjects getRoomObjectFromName(String name) {
        return getRooms().parallelStream()
                .filter(room -> room.getName().equals(name))
                .findFirst().orElse(null);
    }

    public static RoutingObjects getBuildingObject(String building) {
        ArrayList<RoutingObjects> list = new ArrayList<>();
        list.add(new RoutingObjects(COLLEGE, true, true, true));
        list.add(new RoutingObjects(HATHCROFT, true, true, true));
        list.add(new RoutingObjects(WILLIAMS, true, true, true));
        list.add(new RoutingObjects(SHEPPARDLIBRARY, true, true, true));
        list.add(new RoutingObjects(VINE, true, false, true));
        list.add(new RoutingObjects(BARN, false, false, true));
        list.add(new RoutingObjects(GROVE_BLOCK_A, true, true, true));
        list.add(new RoutingObjects(GROVE_BLOCK_B, true, true, true));
        list.add(new RoutingObjects(GROVE_BLOCK_C, true, true, true));
        list.add(new RoutingObjects(BUILDING9, false, false, true));
        list.add(new RoutingObjects(MDXHOUSE, true, true, true));
        list.add(new RoutingObjects(PORTACABIN_A, true, false, true));
        list.add(new RoutingObjects(PORTACABIN_A_EXT, false, false, true));
        list.add(new RoutingObjects(PORTACABIN_67, true, false, false));
        list.add(new RoutingObjects(PORTACABIN_B, false, false, true));
        list.add(new RoutingObjects(BUILDING10, true, false, false));
        list.add(new RoutingObjects(CIRCLE_CAFE, false, false, true));

        return list.parallelStream()
                .filter(buildingObject -> buildingObject.getName().equals(building))
                .findFirst().get();
    }

}
