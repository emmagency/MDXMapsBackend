package org.backend.mdxmaps.Model;

import com.fasterxml.jackson.annotation.JsonInclude;
import org.backend.mdxmaps.Services.ResponseService.Status;

import java.util.ArrayList;

/**
 * Created by Emmanuel Keboh on 09/01/2017.
 */
@JsonInclude(JsonInclude.Include.NON_EMPTY)
public class MainResponseObject<T extends ArrayList> {

    private Status status;
    private OperationTypes type;
    private String message;
    private String routeDescription;
    private T routes;

    public MainResponseObject() {
    }

    private MainResponseObject(Status status, OperationTypes type, String message, T routes) {
        this.status = status;
        this.type = type;
        this.message = message;
        this.routes = routes;
    }

    public MainResponseObject(Status status, OperationTypes type, String message, String routeDescription, T routes) {
        this.status = status;
        this.type = type;
        this.message = message;
        this.routeDescription = routeDescription;
        this.routes = routes;
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

    public OperationTypes getType() {
        return type;
    }

    public String getMessage() {
        return message;
    }

    public String getRouteDescription() {
        return routeDescription;
    }

    public T getRoutes() {
        return routes;
    }

    public static <T extends ArrayList> MainResponseObject createMainResponseObject(Status status, OperationTypes type, String message, T routes) {
        return new MainResponseObject<>(status, type, message, routes);
    }

    public static <T extends ArrayList> MainResponseObject createMainResponseObject(Status status, OperationTypes type, String routeDescription,
                                                                                    String message, T routes) {
        return new MainResponseObject<>(status, type, message, routeDescription, routes);
    }
}