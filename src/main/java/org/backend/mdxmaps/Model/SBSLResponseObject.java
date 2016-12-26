package org.backend.mdxmaps.Model;

import org.backend.mdxmaps.Services.ResponseService.Status;

import java.util.ArrayList;

/**
 * Created by Anonymous on 27/11/2016.
 */

/*SBSL: Same building, same level*/
public class SBSLResponseObject {

    public Status status;
    public String message;
    public ArrayList<SBSLResponseObject> routes;

    public ArrayList<LatLng> route;
    public Double distance;

    private SBSLResponseObject(Status status, String message, ArrayList<SBSLResponseObject> routes) {
        this.status = status;
        this.message = message;
        this.routes = routes;
    }

    private SBSLResponseObject(ArrayList<LatLng> route, Double distance) {
        this.route = route;
        this.distance = distance;
    }

    public static SBSLResponseObject createMainResponseObject(Status status, String message, ArrayList<SBSLResponseObject> routes) {
        return new SBSLResponseObject(status, message, routes);
    }


    public static SBSLResponseObject createRouteObject(ArrayList<LatLng> route, Double distance) {
        return new SBSLResponseObject(route, distance);
    }


}
