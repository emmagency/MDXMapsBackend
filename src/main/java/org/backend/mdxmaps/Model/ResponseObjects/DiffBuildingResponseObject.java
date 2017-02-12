package org.backend.mdxmaps.Model.ResponseObjects;

import com.fasterxml.jackson.annotation.JsonInclude;
import org.backend.mdxmaps.Model.LatLng;

import java.util.ArrayList;

/**
 * Created by Emmanuel Keboh on 18/12/2016.
 */
@JsonInclude(JsonInclude.Include.NON_EMPTY)
public class DiffBuildingResponseObject {

    private ArrayList<ArrayList<ArrayList<LatLng>>> route;
    private double distance;

    private DiffBuildingResponseObject(double distance, ArrayList<ArrayList<ArrayList<LatLng>>> route) {
        this.distance = distance;
        this.route = route;
    }

    public ArrayList<ArrayList<ArrayList<LatLng>>> getRoute() {
        return route;
    }

    public double getDistance() {
        return distance;
    }

    public static DiffBuildingResponseObject createRouteObject(double distance, ArrayList<ArrayList<ArrayList<LatLng>>> route) {
        return new DiffBuildingResponseObject(distance, route);
    }
}
