package org.backend.mdxmaps.Model.ResponseObjects;

import com.fasterxml.jackson.annotation.JsonInclude;
import org.backend.mdxmaps.Model.Enums.OperationType;
import org.backend.mdxmaps.Services.ResponseService.Status;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Emmanuel Keboh on 09/01/2017.
 */
@JsonInclude(JsonInclude.Include.NON_EMPTY)
public class MainResponseObject<E extends List> {

    private Status status;
    private OperationType type;
    private String message;
    private ArrayList<String> routeDescription;
    private ArrayList<String> routeDescriptionIcons;
    private E routes;

    public MainResponseObject() {
    }

    private MainResponseObject(Status status, OperationType type, ArrayList<String> routeDescriptionIcons, E routes) {
        this.status = status;
        this.type = type;
        this.routeDescriptionIcons = routeDescriptionIcons;
        this.routes = routes;
    }

    public MainResponseObject(Status status, OperationType type, ArrayList<String> routeDescription,
                              ArrayList<String> routeDescriptionIcons, E routes) {
        this.status = status;
        this.type = type;
        this.routeDescription = routeDescription;
        this.routeDescriptionIcons = routeDescriptionIcons;
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

    public ArrayList<String> getRouteDescriptionIcons() {
        return routeDescriptionIcons;
    }

    public E getRoutes() {
        return routes;
    }

    public static <E extends List> MainResponseObject createMainResponseObject(Status status, OperationType type,
                                                                               ArrayList<String> routeDescriptionIcons, E routes) {
        return new MainResponseObject<>(status, type, routeDescriptionIcons, routes);
    }

    public static <E extends List> MainResponseObject createMainResponseObject(Status status, OperationType type, ArrayList<String> routeDescription,
                                                                               ArrayList<String> routeDescriptionIcons, E routes) {
        return new MainResponseObject<>(status, type, routeDescription, routeDescriptionIcons, routes);
    }
}