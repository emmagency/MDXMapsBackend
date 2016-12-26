package org.backend.mdxmaps.Services;


/**
 * Created by Emmanuel Keboh on 14/12/2016.
 */
public class ResponseService {

    private Object entity;
    private Status status;
    private String message;

    //ToDo set to private
    public ResponseService() {
    }

    private ResponseService(Status status, Object entity) {
        this.status = status;
        this.entity = entity;
    }

    private ResponseService(Status status, String message) {
        this.status = status;
        this.message = message;
    }

    public void setStatus(Status status) {
        this.status = status;
    }

    public Status getStatus() {
        return status;
    }

    public void setEntity(Object entity) {
        this.entity = entity;
    }

    public Object getEntity() {
        return entity;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getMessage() {
        return message;
    }

    public static ResponseService create(Status status, Object entity) {
        return new ResponseService(status, entity);
    }

    public static ResponseService create(Status status, String message) {
        return new ResponseService(status, message);
    }

    public enum Status {
        OK(), //Successful operation
        INFO(), //Successful operation with info user should know about. e.g app used stairs for a building with no elevators
        ERROR() //Self explanatory innit?
    }
}
