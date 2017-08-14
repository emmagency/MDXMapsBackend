package org.backend.mdxmaps.model.responseObjects.directions;

import java.util.List;

/**
 * Created by Emmanuel Keboh on 09/08/2017.
 */
public class Route {
    private double distance;
    private int travelTime;
    private List<Step> steps;

    private Route(double distance, int travelTime, List<Step> steps) {
        this.distance = distance;
        this.travelTime = travelTime;
        this.steps = steps;
    }

    public static Route createRoute(double distance, int travelTime, List<Step> steps) {
        return new Route(distance, travelTime, steps);
    }

    public double getDistance() {
        return distance;
    }

    public int getTravelTime() {
        return travelTime;
    }

    public List<Step> getSteps() {
        return steps;
    }
}
