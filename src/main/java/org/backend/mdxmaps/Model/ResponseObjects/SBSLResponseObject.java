package org.backend.mdxmaps.Model.ResponseObjects;

import com.fasterxml.jackson.annotation.JsonInclude;
import org.backend.mdxmaps.Model.LatLng;

import java.util.ArrayList;

/**
 * Created by Anonymous on 27/11/2016.
 */

/*SBSL: Same building, same level*/
@JsonInclude(JsonInclude.Include.NON_EMPTY)
public class SBSLResponseObject extends MainResponseObject {

    private ArrayList<LatLng> route;
    private Double distance;

    private SBSLResponseObject(ArrayList<LatLng> route, Double distance) {
        this.route = route;
        this.distance = distance;
    }

    public ArrayList<LatLng> getRoute() {
        return route;
    }

    public Double getDistance() {
        return distance;
    }

    public static SBSLResponseObject createRouteObject(ArrayList<LatLng> route, Double distance) {
        return new SBSLResponseObject(route, distance);
    }


}
