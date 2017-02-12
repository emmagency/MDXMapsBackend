package org.backend.mdxmaps.Services;

import com.google.common.collect.Multimap;
import org.backend.mdxmaps.Model.Enums.MOT;
import org.backend.mdxmaps.Model.LatLng;
import org.backend.mdxmaps.Model.OperationFactory;
import org.backend.mdxmaps.Model.RoutingObjects;

import java.util.ArrayList;
import java.util.List;

import static java.util.Collections.min;
import static java.util.Collections.singletonList;
import static org.backend.mdxmaps.Model.Enums.OperationType.DIFFB;
import static org.backend.mdxmaps.Model.ResponseObjects.DiffBuildingResponseObject.createRouteObject;
import static org.backend.mdxmaps.Model.ResponseObjects.MainResponseObject.createMainResponseObject;
import static org.backend.mdxmaps.Services.IconResolverService.resolveDifferentBuildingIcons;
import static org.backend.mdxmaps.Services.ResponseService.Status.OK;
import static org.backend.mdxmaps.Services.RouteCalculators.DifferentBuildingCalculator.performDifferentBuildingCalculation;

/**
 * Created by Emmanuel Keboh on 18/12/2016.
 */
public class DiffBuildingFactoryService implements OperationFactory {

    MOT startEDMethod, destEDMethod;
    boolean isStartED, isDestED, disabled;
    RoutingObjects startObject, destinationObject;

    public MOT getStartEDMethod() {
        return startEDMethod;
    }

    public MOT getDestEDMethod() {
        return destEDMethod;
    }

    public boolean isStartED() {
        return isStartED;
    }

    public boolean isDestED() {
        return isDestED;
    }

    private DiffBuildingFactoryService(RoutingObjects startObject, RoutingObjects destinationObject, MOT startEDMethod,
                                       MOT destEDMethod, boolean disabled, boolean isStartED, boolean isDestED) {
        this.startObject = startObject;
        this.destinationObject = destinationObject;
        this.startEDMethod = startEDMethod;
        this.destEDMethod = destEDMethod;
        this.disabled = disabled;
        this.isStartED = isStartED;
        this.isDestED = isDestED;
    }

    public static DiffBuildingFactoryService create(RoutingObjects startObject, RoutingObjects destinationObject, MOT startEDMethod,
                                                    MOT destEDMethod, boolean disabled, boolean isStartED, boolean isDestED) {
        return new DiffBuildingFactoryService(startObject, destinationObject, startEDMethod, destEDMethod, disabled, isStartED, isDestED);
    }

    @Override
    public ResponseService getRoute() {
        Multimap<Double, ArrayList<ArrayList<ArrayList<LatLng>>>> routes = performDifferentBuildingCalculation(startObject, destinationObject, startEDMethod,
                destEDMethod, disabled, isStartED, isDestED);

        if (routes != null) {
            double bestRouteDistance = min(routes.keySet());
            ArrayList<ArrayList<ArrayList<LatLng>>> bestRoute =
                    ((List<ArrayList<ArrayList<ArrayList<LatLng>>>>) routes.get(bestRouteDistance)).get(0);

            ArrayList<String> routeDescription = new ArrayList<>();

            String movementMethod = disabled ? "Get" : "Walk";
            String startDir = "";
            if (isStartED) {
                startDir = startObject.getActualLevel() > 0 ? "down" : "up";
                routeDescription.add(String.format("%s to the %s and go %s %d level(s)", movementMethod, startEDMethod.toString().toLowerCase(),
                        startDir, Math.abs(startObject.getActualLevel())));
            }
            routeDescription.add(String.format("%s to the door and exit from %s", movementMethod, startObject.getBuilding()));
            routeDescription.add(String.format("%s to %s", movementMethod, destinationObject.getBuilding()));
            String destDir = "";
            if (isDestED) {
                destDir = destinationObject.getActualLevel() > 0 ? "up" : "down";
                routeDescription.add(String.format("Enter %s, %s to the %s and go %s %d level(s)", destinationObject.getBuilding(), movementMethod,
                        destEDMethod.toString().toLowerCase(), destDir, Math.abs(destinationObject.getActualLevel())));
                routeDescription.add(String.format("%s to %s", movementMethod, destinationObject.getName()));
            } else {
                routeDescription.add(String.format("Enter %s and %s to %s", destinationObject.getBuilding(), movementMethod, destinationObject.getName()));
            }

            ArrayList<String> iconsList = resolveDifferentBuildingIcons(disabled, startEDMethod, destEDMethod, isStartED, isDestED, startDir, destDir);

            return ResponseService.create(OK, createMainResponseObject(OK, DIFFB, routeDescription, iconsList, singletonList(createRouteObject(bestRouteDistance, bestRoute))));
        }

        return null;
    }
}
