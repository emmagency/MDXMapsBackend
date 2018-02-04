package org.backend.mdxmaps.service.factoryServices;

import com.google.common.collect.Multimap;
import org.backend.mdxmaps.model.DirectionsRequestParams;
import org.backend.mdxmaps.model.LatLng;
import org.backend.mdxmaps.model.OperationFactory;
import org.backend.mdxmaps.model.Routing;
import org.backend.mdxmaps.model.enums.MOT;
import org.backend.mdxmaps.model.responseObjects.directions.MainDirectionsResponse;
import org.backend.mdxmaps.model.responseObjects.directions.Route;
import org.backend.mdxmaps.model.responseObjects.directions.Step;
import org.backend.mdxmaps.service.ResponseService;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import static java.util.stream.Collectors.toList;
import static org.backend.mdxmaps.model.enums.MOT.DISABLED;
import static org.backend.mdxmaps.service.IconResolverService.WALK;
import static org.backend.mdxmaps.service.IconResolverService.WHEELCHAIR;
import static org.backend.mdxmaps.service.ResponseService.Status.OK;
import static org.backend.mdxmaps.service.routeCalculators.SingleLevelSLOCalculator.performSingleLevelSLO;
import static org.backend.mdxmaps.service.util.TravelTimeCalc.getTravelTime;

/**
 * Created by Emmanuel Keboh on 18/12/2016.
 */

/*SBSL: Same building, same level*/
public class SBSLFactoryService implements OperationFactory {

    private MOT mot;
    private Routing start, destination;

    //ToDo Add optional field for specifying the required number of routes

    private SBSLFactoryService(Routing start, Routing destination, MOT mot) {
        this.start = start;
        this.destination = destination;
        this.mot = mot;

    }

    public static SBSLFactoryService create(Routing start, Routing destination, MOT mot) {
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
                    String.format("Sorry, there are no available wheelchair routes from %s to %s", start, destination)
                    : "Something went wrong, couldn't find a route. Please tell us about this issue.");
        }
        //int maxRoutes = 0;

        ArrayList<Route> routes = new ArrayList<>();

        SLOroutes.keySet()
                .forEach(distance -> {
                    List<ArrayList<LatLng>> keyValues = (List<ArrayList<LatLng>>) SLOroutes.get(distance);

                    routes.addAll(keyValues.parallelStream()
                            .map(latLngs -> Route.createRoute(distance, getTravelTime(distance, mot), getSteps(latLngs)))
                            .collect(toList()));
                });
        return ResponseService.create(OK, MainDirectionsResponse.create(OK,
                DirectionsRequestParams.create(start.getName(), destination.getName(), mot.toString()), routes));
    }

    private List<Step> getSteps(ArrayList<LatLng> latLngs) {
        String description = String.format("%s to %s", mot == DISABLED ? "Get" : "Walk", destination.getName());
        String iconHref = mot == DISABLED ? WHEELCHAIR : WALK;
        return Collections.singletonList(Step.createStep(description, start.getgMapLevel(), iconHref, latLngs));
    }
}