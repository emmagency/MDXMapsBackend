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
    private Double distance;

    private SBDLResponseObject(ArrayList<ArrayList<LatLng>> route, Double distance) {
        this.route = route;
        this.distance = distance;
    }

    public ArrayList<ArrayList<LatLng>> getRoute() {
        return route;
    }

    public Double getDistance() {
        return distance;
    }

    public static SBDLResponseObject createRouteObject(ArrayList<ArrayList<LatLng>> route, Double distance) {
        return new SBDLResponseObject(route, distance);
    }
}
