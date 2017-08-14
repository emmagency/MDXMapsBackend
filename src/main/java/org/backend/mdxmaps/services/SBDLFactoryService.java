package org.backend.mdxmaps.services;

import com.google.common.collect.Multimap;
import org.backend.mdxmaps.model.DirectionsRequestParams;
import org.backend.mdxmaps.model.LatLng;
import org.backend.mdxmaps.model.OperationFactory;
import org.backend.mdxmaps.model.Routing;
import org.backend.mdxmaps.model.enums.MOT;
import org.backend.mdxmaps.model.responseObjects.directions.MainDirectionsResponse;
import org.backend.mdxmaps.model.responseObjects.directions.Route;
import org.backend.mdxmaps.model.responseObjects.directions.Step;

import java.util.ArrayList;
import java.util.List;

import static java.util.stream.Collectors.toList;
import static org.backend.mdxmaps.model.enums.MOT.DISABLED;
import static org.backend.mdxmaps.services.IconResolverService.resolveSBDLIcons;
import static org.backend.mdxmaps.services.ResponseService.Status.OK;
import static org.backend.mdxmaps.services.TravelTimeCalc.getTravelTime;
import static org.backend.mdxmaps.services.routeCalculators.MultiLevelSLOCalculator.performMultiLevelSLO;

/**
 * Created by Emmanuel Keboh on 18/12/2016.
 */

/*SBDL: Same building, different levels*/
public class SBDLFactoryService implements OperationFactory {

    private Routing start, destination;
    private MOT mot;

    private String firstStep, intermediateStep, lastStep, direction;
    private ArrayList<String> icons;

    private SBDLFactoryService(Routing start, Routing destination, MOT mot) {
        this.start = start;
        this.destination = destination;
        this.mot = mot;
    }

    public static SBDLFactoryService create(Routing startObject, Routing destinationObject, MOT mot) {
        return new SBDLFactoryService(startObject, destinationObject, mot);
    }

    public Routing getStart() {
        return start;
    }

    public Routing getDestination() {
        return destination;
    }

    public MOT getMot() {
        return mot;
    }

    @Override
    public ResponseService getRoute() {

        Multimap<Double, ArrayList<ArrayList<LatLng>>> SBDLRoutes = performMultiLevelSLO(start, destination, mot);

        if (SBDLRoutes == null) {
            return ResponseService.create(ResponseService.Status.ERROR, mot == DISABLED ?
                    "No available wheelchair routes." :
                    "Something went wrong, couldn't find a route. Please tell us about this issue.");
        }

        direction = start.getLevel() > destination.getLevel() ? "down" : "up";
        firstStep = String.format("%s", mot == DISABLED ? "Get to the elevators" : "Walk to the " + mot.toString().toLowerCase());
        intermediateStep = String.format("Go %s %d %s", direction, Math.abs(start.getLevel() - destination.getLevel()),
                Math.abs(start.getLevel() - destination.getLevel()) > 1 ? "levels" : "level");
        lastStep = String.format("%s %s", mot == DISABLED ? "Get to" : "Walk to", destination.getName());

        icons = resolveSBDLIcons(mot, direction);

        ArrayList<Route> routes = new ArrayList<>();

        SBDLRoutes.keySet()
                .forEach(distance -> {
                    List<ArrayList<ArrayList<LatLng>>> keyValues = (List<ArrayList<ArrayList<LatLng>>>) SBDLRoutes.get(distance);

                    routes.addAll(keyValues.parallelStream()
                            .map(route -> Route.createRoute(distance, getTravelTime(distance, mot), getSteps(route)))
                            .collect(toList()));
                });

        return ResponseService.create(OK, MainDirectionsResponse.create(OK,
                DirectionsRequestParams.create(start.getName(), destination.getName(), mot.toString()), routes));
    }

    private List<Step> getSteps(ArrayList<ArrayList<LatLng>> routes) {
        ArrayList<Step> steps = new ArrayList<>();
        steps.add(Step.createStep(firstStep, start.getgMapLevel(), icons.get(0), routes.get(0)));
        steps.add(Step.createStep(intermediateStep, 0, icons.get(1), null));
        steps.add(Step.createStep(lastStep, destination.getgMapLevel(), icons.get(2), routes.get(1)));
        return steps;
    }
}
