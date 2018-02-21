package org.backend.mdxmaps.model.responseObjects.search;

import com.fasterxml.jackson.annotation.JsonInclude;
import org.backend.mdxmaps.model.solr.Transport;

import java.util.List;

/**
 * Created by Emmanuel Keboh on 13/07/2017.
 */
public class TransportSearchResponse {

    @JsonInclude(JsonInclude.Include.NON_EMPTY)
    private String errorMessage;

    private List<Transport> Transport;

    private TransportSearchResponse() {
    }

    private TransportSearchResponse(String errorMessage) {
        this.errorMessage = errorMessage;
    }

    private TransportSearchResponse(List<Transport> Transport) {
        this.Transport = Transport;
    }

    public String getErrorMessage() {
        return errorMessage;
    }

    public List<Transport> getTransport() {
        return Transport;
    }

    public static TransportSearchResponse create(List<Transport> Transport) {
        if (Transport == null) {
            return new TransportSearchResponse("Apologies! We've run into some problems. Please try again at a later time");
        }
        return new TransportSearchResponse(Transport);
    }


}