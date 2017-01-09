package org.backend.mdxmaps.Services;

import com.google.common.collect.Multimap;
import org.backend.mdxmaps.Model.LatLng;
import org.backend.mdxmaps.Model.MOT;
import org.backend.mdxmaps.Model.RouteCalculation;
import org.backend.mdxmaps.Model.RoutingObjects;
import org.backend.mdxmaps.Model.SBSLResponseObject;
import org.backend.mdxmaps.Services.ResponseService.Status;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import static org.backend.mdxmaps.Model.OperationTypes.SBSL;
import static org.backend.mdxmaps.Services.SingleLevelSLOCalculator.performSingleLevelSLO;

/**
 * Created by Emmanuel Keboh on 18/12/2016.
 */

/*SBSL: Same building, same level*/
public class SBSLFactoryService implements RouteCalculation {

    private MOT mot;
    private RoutingObjects start, destination;

    //ToDo Add optional field for specifying the required number of routes

    private SBSLFactoryService(RoutingObjects start, RoutingObjects destination, MOT mot) {
        this.start = start;
        this.destination = destination;
        this.mot = mot;

    }

    public static SBSLFactoryService create(RoutingObjects start, RoutingObjects destination, MOT mot) {
        return new SBSLFactoryService(start, destination, mot);
    }

    public MOT getMot() {
        return mot;
    }

    @Override
    public ResponseService getRoute() {

        Multimap<Double, ArrayList<LatLng>> SLOroutes = performSingleLevelSLO(start, destination, mot);
        if (SLOroutes == null) {
            return ResponseService.create(ResponseService.Status.ERROR, mot.toString().equals("DISABLED") ?
                    "No available wheelchair routes" : "Something went wrong, couldn't find a route");
        }

        ArrayList<SBSLResponseObject> routes = new ArrayList<>();
        //int maxRoutes = 0;

        for (Double distance : SLOroutes.keySet()) {
            List<ArrayList<LatLng>> keyValues = (List<ArrayList<LatLng>>) SLOroutes.get(distance);
            routes.addAll(keyValues.parallelStream()
                    .map(route -> SBSLResponseObject.createRouteObject(route, distance))
                    .collect(Collectors.toList()));
        }

        return ResponseService.create(Status.OK,
                SBSLResponseObject.createMainResponseObject(Status.OK, SBSL, "Test Message", routes));
    }

}
