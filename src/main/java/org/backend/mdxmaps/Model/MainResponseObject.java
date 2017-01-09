package org.backend.mdxmaps.Model;

import com.fasterxml.jackson.annotation.JsonInclude;
import org.backend.mdxmaps.Services.ResponseService.Status;

import java.util.List;

/**
 * Created by Emmanuel Keboh on 09/01/2017.
 */
@JsonInclude(JsonInclude.Include.NON_EMPTY)
public class MainResponseObject<T extends List> {

    private Status status;
    private OperationTypes type;
    private String message;
    private T routes;

    public MainResponseObject() {
    }

    private MainResponseObject(Status status, OperationTypes type, String message, T routes) {
        this.status = status;
        this.type = type;
        this.message = message;
        this.routes = routes;
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

    public T getRoutes() {
        return routes;
    }

    public static <T extends List> MainResponseObject createMainResponseObject(Status status, OperationTypes type, String message, T routes) {
        return new MainResponseObject<>(status, type, message, routes);
    }
}