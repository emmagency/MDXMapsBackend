package org.backend.mdxmaps.model;

import com.fasterxml.jackson.annotation.JsonInclude;

/**
 * Created by Emmanuel Keboh on 30/07/2017.
 */
@JsonInclude(JsonInclude.Include.NON_EMPTY)
public class DirectionsRequestParams {

    private String start, destination, mot;

    private DirectionsRequestParams(String start, String destination, String mot) {
        this.start = start;
        this.destination = destination;
        this.mot = mot;
    }

    public static DirectionsRequestParams create(String start, String destination, String mot) {
        return new DirectionsRequestParams(start, destination, mot);
    }

    public String getStart() {
        return start;
    }

    public String getDestination() {
        return destination;
    }

    public String getMot() {
        return mot;
    }
}
