package org.backend.mdxmaps.Model.ResponseObjects;

import com.fasterxml.jackson.annotation.JsonInclude;
import org.backend.mdxmaps.Model.LatLng;

import java.util.ArrayList;

/**
 * Created by Anonymous on 27/11/2016.
 */

/*SBSL: Same building, same level*/
@JsonInclude(JsonInclude.Include.NON_EMPTY)
public class SBSLResponseObject {

    private ArrayList<LatLng> route;
    private double distance;

    private SBSLResponseObject(double distance, ArrayList<LatLng> route) {
        this.distance = distance;
        this.route = route;
    }

    public ArrayList<LatLng> getRoute() {
        return route;
    }

    public double getDistance() {
        return distance;
    }

    public static SBSLResponseObject createRouteObject(double distance, ArrayList<LatLng> route) {
        return new SBSLResponseObject(distance, route);
    }


}
