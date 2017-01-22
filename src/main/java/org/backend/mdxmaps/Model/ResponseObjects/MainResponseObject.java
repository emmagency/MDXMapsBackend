package org.backend.mdxmaps.Model.ResponseObjects;

import com.fasterxml.jackson.annotation.JsonInclude;
import org.backend.mdxmaps.Model.Enums.OperationType;
import org.backend.mdxmaps.Services.ResponseService.Status;

import java.util.ArrayList;

/**
 * Created by Emmanuel Keboh on 09/01/2017.
 */
@JsonInclude(JsonInclude.Include.NON_EMPTY)
public class MainResponseObject<T extends ArrayList> {

    private Status status;
    private OperationType type;
    private String message;
    private ArrayList<String> routeDescription;
    private T routes;

    public MainResponseObject() {
    }

    private MainResponseObject(Status status, OperationType type, String message, T routes) {
        this.status = status;
        this.type = type;
        this.message = message;
        this.routes = routes;
    }

    public MainResponseObject(Status status, OperationType type, String message, ArrayList<String> routeDescription, T routes) {
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

    public OperationType getType() {
        return type;
    }

    public String getMessage() {
        return message;
    }

    public ArrayList<String> getRouteDescription() {
        return routeDescription;
    }

    public T getRoutes() {
        return routes;
    }

    public static <T extends ArrayList> MainResponseObject createMainResponseObject(Status status, OperationType type, String message, T routes) {
        return new MainResponseObject<>(status, type, message, routes);
    }

    public static <T extends ArrayList> MainResponseObject createMainResponseObject(Status status, OperationType type, ArrayList<String> routeDescription,
                                                                                    String message, T routes) {
        return new MainResponseObject<>(status, type, message, routeDescription, routes);
    }
}