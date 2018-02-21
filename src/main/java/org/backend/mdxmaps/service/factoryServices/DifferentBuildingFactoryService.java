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

import static java.util.Collections.min;
import static org.backend.mdxmaps.model.enums.MOT.DISABLED;
import static org.backend.mdxmaps.model.enums.MOT.NULL;
import static org.backend.mdxmaps.model.responseObjects.directions.Route.createRoute;
import static org.backend.mdxmaps.model.responseObjects.directions.Step.createStep;
import static org.backend.mdxmaps.service.IconResolverService.resolveDifferentBuildingIcons;
import static org.backend.mdxmaps.service.ResponseService.Status.OK;
import static org.backend.mdxmaps.service.routeCalculators.DifferentBuildingCalculator.performDifferentBuildingCalculation;
import static org.backend.mdxmaps.service.util.TravelTimeCalc.getTravelTime;

/**
 * Created by Emmanuel Keboh on 18/12/2016.
 */

public class DifferentBuildingFactoryService implements OperationFactory {

    private MOT startEDMethod, destEDMethod;
    private boolean isStartED, isDestED, disabled;
    private Routing start, destination;
    private ArrayList<String> iconsList;

    public MOT getStartEDMethod() {
        return startEDMethod;
    }

    public MOT getDestEDMethod() {
        return destEDMethod;
    }

    private DifferentBuildingFactoryService(Routing start, Routing destination, MOT startEDMethod,
                                            MOT destEDMethod, boolean disabled, boolean isStartED, boolean isDestED) {
        this.start = start;
        this.destination = destination;
        this.startEDMethod = startEDMethod;
        this.destEDMethod = destEDMethod;
        this.disabled = disabled;
        this.isStartED = isStartED;
        this.isDestED = isDestED;
    }

    public static DifferentBuildingFactoryService create(Routing startObject, Routing destinationObject, MOT startEDMethod,
                                                         MOT destEDMethod, boolean disabled, boolean isStartED, boolean isDestED) {
        return new DifferentBuildingFactoryService(startObject, destinationObject, startEDMethod, destEDMethod, disabled, isStartED, isDestED);
    }

    @Override
    public ResponseService getRoute() {
        Multimap<Double, ArrayList<ArrayList<ArrayList<LatLng>>>> calculatedRoutes = performDifferentBuildingCalculation(start, destination, startEDMethod,
                destEDMethod, disabled, isStartED, isDestED);

        if (calculatedRoutes == null) {
            return ResponseService.create(ResponseService.Status.ERROR,
                    "Something went wrong, couldn't find a route. Please tell us about this issue.");
        }

        double bestRouteDistance = min(calculatedRoutes.keySet());
        ArrayList<ArrayList<ArrayList<LatLng>>> bestRoute =
                ((List<ArrayList<ArrayList<ArrayList<LatLng>>>>) calculatedRoutes.get(bestRouteDistance)).get(0);

        String startDir = "";
        if (isStartED) {
            startDir = start.getLevel() > 0 ? "down" : "up";
        }
        String destDir = "";
        if (isDestED) {
            destDir = destination.getLevel() > 0 ? "up" : "down";
        }

        iconsList = resolveDifferentBuildingIcons(disabled, startEDMethod, destEDMethod, isStartED, isDestED, startDir, destDir);

        ArrayList<Route> routes = new ArrayList<>();
        routes.add(createRoute(bestRouteDistance, getTravelTime(bestRouteDistance, disabled ? DISABLED : NULL), getSteps(bestRoute)));

        return ResponseService.create(OK, MainDirectionsResponse.create(OK,
                DirectionsRequestParams.create(start.getName(), destination.getName(), null), routes));
    }

    private List<Step> getSteps(ArrayList<ArrayList<ArrayList<LatLng>>> route) {
        String movementMethod = disabled ? "Get" : "Walk";
        ArrayList<Step> steps = new ArrayList<>();

        //Inside steps
        if (isStartED) {
            steps.add(createStep(String.format("%s to the %s", movementMethod, startEDMethod.toString().toLowerCase()),
                    start.getGMapLevel(), iconsList.get(0), route.get(0).get(0)));
            steps.add(createStep(String.format("Go %s %d %s", start.getLevel() > 0 ? "down" : "up", Math.abs(start.getLevel()),
                    Math.abs(start.getLevel()) > 1 ? "levels" : "level"), null, iconsList.get(1),
                    Collections.singletonList(route.get(0).get(0).get(route.get(0).get(0).size() - 1))));
            steps.add(createStep(String.format("%s to the door and exit from %s", movementMethod, start.getBuilding()),
                    Routing.getGmapIntForGroundFloor(start.getBuilding()), iconsList.get(2), route.get(0).get(1)));
            for (int i = 0; i < 3; i++) {
                iconsList.remove(0);
            }
        } else {
            steps.add(createStep(String.format("%s to the door and exit from %s", movementMethod, start.getBuilding()),
                    start.getGMapLevel(), iconsList.get(0), route.get(0).get(0)));
            iconsList.remove(0);
        }

        //Outside Step
        steps.add(createStep(String.format("%s to %s", movementMethod, destination.getBuilding()), null,
                iconsList.get(0), route.get(1).get(0)));
        iconsList.remove(0);

        //Destination steps
        if (isDestED) {
            steps.add(createStep(String.format("Enter %s and %s to the %s", destination.getBuilding(), movementMethod, destEDMethod.toString().toLowerCase()),
                    Routing.getGmapIntForGroundFloor(destination.getBuilding()), iconsList.get(0), route.get(2).get(0)));
            steps.add(createStep(String.format("Go %s %d %s", destination.getLevel() > 0 ? "up" : "down", Math.abs(destination.getLevel()),
                    Math.abs(destination.getLevel()) > 1 ? "levels" : "level"), null, iconsList.get(1),
                    Collections.singletonList(route.get(2).get(0).get(route.get(2).get(0).size() - 1))));
            steps.add(createStep(String.format("%s to %s", movementMethod, destination.getName()), destination.getGMapLevel(),
                    iconsList.get(2), route.get(2).get(1)));
        } else {
            steps.add(createStep(String.format("Enter %s and %s to %s", destination.getBuilding(), movementMethod, destination.getName()),
                    destination.getGMapLevel(), iconsList.get(0), route.get(2).get(0)));
        }

        return steps;
    }
}
