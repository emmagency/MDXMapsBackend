package org.backend.mdxmaps.Services;


/**
 * Created by Emmanuel Keboh on 14/12/2016.
 */
public class ResponseService {

    private Object entity;
    private Status status;
    private String infoMessage;
    private String errorMessage;

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

    public void setErrorMessage(String errorMessage) {
        this.errorMessage = errorMessage;
    }

    public String getErrorMessage() {
        return errorMessage;
    }

    public void setInfoMessage(String infoMessage) {
        this.infoMessage = infoMessage;
    }

    public String getInfoMessage() {
        return infoMessage;
    }

    public enum Status {
        OK(), //Successful operation
        INFO(), //Successful operation with info user should know about. e.g app used stairs for a building with no elevators
        ERROR() //Self explanatory innit?
    }
}
