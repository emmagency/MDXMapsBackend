package org.backend.mdxmaps.Services;

import com.google.common.collect.Multimap;
import org.backend.mdxmaps.Model.Enums.MOT;
import org.backend.mdxmaps.Model.LatLng;
import org.backend.mdxmaps.Model.ResponseObjects.SBDLResponseObject;
import org.backend.mdxmaps.Model.RouteCalculation;
import org.backend.mdxmaps.Model.RoutingObjects;

import java.util.ArrayList;
import java.util.List;

import static java.util.stream.Collectors.toList;
import static org.backend.mdxmaps.Model.Enums.MOT.DISABLED;
import static org.backend.mdxmaps.Model.Enums.ObjectType.ROOM;
import static org.backend.mdxmaps.Model.Enums.OperationType.SBDL;
import static org.backend.mdxmaps.Services.ResponseService.Status.OK;
import static org.backend.mdxmaps.Services.RouteCalculators.MultiLevelSLOCalculator.performMultiLevelSLO;

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

        ArrayList<String> routeDescription = new ArrayList<>();
        String direction = startObject.getActualLevel() > destinationObject.getActualLevel() ? "down" : "up";

        String firstStep = mot == DISABLED ? "Get to the elevators and go " : "Walk to the " + mot.toString().toLowerCase() + " and go ";
        firstStep = firstStep.concat(direction + " " + Math.abs(startObject.getActualLevel() - destinationObject.getActualLevel()) + " level(s)");

        routeDescription.add(firstStep);
        routeDescription.add(mot == DISABLED ? "Get to " + destinationObject.getName() : "Walk to " + destinationObject.getName());

        return ResponseService.create(OK, SBDLResponseObject.createMainResponseObject(OK, SBDL, routeDescription, null,
                routes));
    }
}
