package org.backend.mdxmaps.Model.ResponseObjects;

import com.fasterxml.jackson.annotation.JsonInclude;
import org.backend.mdxmaps.Model.LatLng;

import java.util.ArrayList;

/**
 * Created by Emmanuel Keboh on 18/12/2016.
 */

/*SBDL: Same building, different levels*/
@JsonInclude(JsonInclude.Include.NON_EMPTY)
public class SBDLResponseObject extends MainResponseObject {
    private ArrayList<ArrayList<LatLng>> route;
    private double distance;

    private SBDLResponseObject(double distance, ArrayList<ArrayList<LatLng>> route) {
        this.distance = distance;
        this.route = route;
    }

    public ArrayList<ArrayList<LatLng>> getRoute() {
        return route;
    }

    public double getDistance() {
        return distance;
    }

    public static SBDLResponseObject createRouteObject(double distance, ArrayList<ArrayList<LatLng>> route) {
        return new SBDLResponseObject(distance, route);
    }
}
