package org.backend.mdxmaps.Services;

import com.google.common.collect.Multimap;
import org.backend.mdxmaps.Model.Enums.MOT;
import org.backend.mdxmaps.Model.LatLng;
import org.backend.mdxmaps.Model.RouteCalculation;
import org.backend.mdxmaps.Model.RoutingObjects;
import org.backend.mdxmaps.Model.SBDLResponseObject;

import java.util.ArrayList;
import java.util.List;

import static java.util.stream.Collectors.toList;
import static org.backend.mdxmaps.Model.Enums.MOT.DISABLED;
import static org.backend.mdxmaps.Model.Enums.ObjectTypes.ROOM;
import static org.backend.mdxmaps.Model.OperationTypes.SBDL;
import static org.backend.mdxmaps.Services.MultiLevelSLOCalculator.performMultiLevelSLO;
import static org.backend.mdxmaps.Services.ResponseService.Status.OK;

/**
 * Created by Emmanuel Keboh on 18/12/2016.
 */

/*SBDL: Same building, different levels*/
public class SBDLFactoryService implements RouteCalculation {

    private RoutingObjects startObject, destinationObject;
    private MOT mot;

    private SBDLFactoryService(RoutingObjects startObject, RoutingObjects destinationObject, MOT mot) {
        this.startObject = startObject;
        this.destinationObject = destinationObject;
        this.mot = mot;
    }

    public static SBDLFactoryService create(RoutingObjects startObject, RoutingObjects destinationObject, MOT mot) {
        return new SBDLFactoryService(startObject, destinationObject, mot);
    }

    public RoutingObjects getStartObject() {
        return startObject;
    }

    public RoutingObjects getDestinationObject() {
        return destinationObject;
    }

    public MOT getMot() {
        return mot;
    }

    @Override
    public ResponseService getRoute() {

        Multimap<Double, ArrayList<ArrayList<LatLng>>> SBDLRoutes = performMultiLevelSLO(startObject, destinationObject, mot, ROOM, ROOM);

        if (SBDLRoutes == null) {
            return ResponseService.create(ResponseService.Status.ERROR, mot == DISABLED ?
                    "No available wheelchair routes." : "Something went wrong, couldn't find a route. Please tell us about this issue.");
        }

        ArrayList<SBDLResponseObject> routes = new ArrayList<>();

        SBDLRoutes.keySet()
                .forEach(distance -> {
                    List<ArrayList<ArrayList<LatLng>>> keyValues = (List<ArrayList<ArrayList<LatLng>>>) SBDLRoutes.get(distance);
                    routes.addAll(keyValues.parallelStream()
                            .map(route -> SBDLResponseObject.createRouteObject(route, distance))
                            .collect(toList()));

                });

        return ResponseService.create(OK, SBDLResponseObject.createMainResponseObject(OK, SBDL, "Test Description", "Test Message",
                routes));
    }
}
