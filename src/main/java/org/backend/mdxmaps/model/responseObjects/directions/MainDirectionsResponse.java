package org.backend.mdxmaps.model.responseObjects.directions;

import com.fasterxml.jackson.annotation.JsonInclude;
import org.backend.mdxmaps.model.DirectionsRequestParams;
import org.backend.mdxmaps.services.ResponseService.Status;

import java.util.ArrayList;

/**
 * Created by Emmanuel Keboh on 09/01/2017.
 */
@JsonInclude(JsonInclude.Include.NON_EMPTY)
public class MainDirectionsResponse {

    private Status status;
    private String message;
    private DirectionsRequestParams directionsRequestParams;

    private ArrayList<Route> routes;

    public MainDirectionsResponse() {
    }

    private MainDirectionsResponse(Status status, DirectionsRequestParams directionsRequestParams, ArrayList<Route> routes) {
        this.status = status;
        this.directionsRequestParams = directionsRequestParams;
        this.routes = routes;
    }

    public ArrayList<Route> getRoutes() {
        return routes;
    }


    public void setStatus(Status status) {
        this.status = status;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public Status getStatus() {
        return status;
    }

    public String getMessage() {
        return message;
    }

    public DirectionsRequestParams getDirectionsRequestParams() {
        return directionsRequestParams;
    }

    public static MainDirectionsResponse create(Status status, DirectionsRequestParams directionsRequestParams, ArrayList<Route> routes) {
        return new MainDirectionsResponse(status, directionsRequestParams, routes);
    }
}