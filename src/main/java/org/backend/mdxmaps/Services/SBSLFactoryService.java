package org.backend.mdxmaps.Services;

import com.google.common.collect.Multimap;
import org.backend.mdxmaps.Model.Enums.MOT;
import org.backend.mdxmaps.Model.LatLng;
import org.backend.mdxmaps.Model.OperationFactory;
import org.backend.mdxmaps.Model.ResponseObjects.SBSLResponseObject;
import org.backend.mdxmaps.Model.RoutingObjects;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

import static org.backend.mdxmaps.Model.Enums.MOT.DISABLED;
import static org.backend.mdxmaps.Model.Enums.OperationType.SBSL;
import static org.backend.mdxmaps.Model.ResponseObjects.MainResponseObject.createMainResponseObject;
import static org.backend.mdxmaps.Services.ResponseService.Status.OK;
import static org.backend.mdxmaps.Services.RouteCalculators.SingleLevelSLOCalculator.performSingleLevelSLO;

/**
 * Created by Emmanuel Keboh on 18/12/2016.
 */

/*SBSL: Same building, same level*/
public class SBSLFactoryService implements OperationFactory {

    private MOT mot;
    private RoutingObjects start, destination;

    //ToDo Add optional field for specifying the required number of routes

    private SBSLFactoryService(RoutingObjects start, RoutingObjects destination, MOT mot) {
        this.start = start;
        this.destination = destination;
        this.mot = mot;

    }

    static SBSLFactoryService create(RoutingObjects start, RoutingObjects destination, MOT mot) {
        return new SBSLFactoryService(start, destination, mot);
    }

    public MOT getMot() {
        return mot;
    }

    @Override
    public ResponseService getRoute() {

        Multimap<Double, ArrayList<LatLng>> SLOroutes = performSingleLevelSLO(start, destination, mot);
        if (SLOroutes == null) {
            return ResponseService.create(ResponseService.Status.ERROR, mot == DISABLED ?
                    "No available wheelchair routes" : "Something went wrong, couldn't find a route. Please tell us about this issue.");
        }

        ArrayList<SBSLResponseObject> routes = new ArrayList<>();
        //int maxRoutes = 0;

        SLOroutes.keySet()
                .forEach(distance -> {
                    List<ArrayList<LatLng>> keyValues = (List<ArrayList<LatLng>>) SLOroutes.get(distance);
                    routes.addAll(keyValues.parallelStream()
                            .map(route -> SBSLResponseObject.createRouteObject(distance, route))
                            .collect(Collectors.toList()));
                });

        ArrayList<String> icon = new ArrayList<>(Arrays.asList(mot == DISABLED ? "wheelchair" : "walk"));
        return ResponseService.create(OK, createMainResponseObject(OK, SBSL, icon, routes));
    }
}