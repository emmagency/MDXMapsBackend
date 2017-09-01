package org.backend.mdxmaps.model.responseObjects.search;

import com.fasterxml.jackson.annotation.JsonInclude;

import java.util.List;

/**
 * Created by Emmanuel keboh on 13/07/2017.
 */
public class MainSearchResponse {

    private List<CampusSearchResponse> campus;
    private List<NearbySearchResponse> nearby;
    private List<TransportSearchResponse> transport;

    @JsonInclude(JsonInclude.Include.NON_EMPTY)
    private String errorInfo;

    private MainSearchResponse(List<CampusSearchResponse> campus, List<NearbySearchResponse> nearby, List<TransportSearchResponse> transport) {
        this.campus = campus;
        this.nearby = nearby;
        this.transport = transport;
    }

    private MainSearchResponse(String errorInfo) {
        this.errorInfo = errorInfo;
    }

    public List<CampusSearchResponse> getCampus() {
        return campus;
    }

    public List<NearbySearchResponse> getNearby() {
        return nearby;
    }

    public List<TransportSearchResponse> getTransport() {
        return transport;
    }

    public String getErrorInfo() {
        return errorInfo;
    }

    public static MainSearchResponse create(List<CampusSearchResponse> campusSearchResponses, List<NearbySearchResponse> nearby,
                                            List<TransportSearchResponse> transport) {
        if (campusSearchResponses == null && nearby == null && transport == null) {
            return new MainSearchResponse("Apologies! We\'ve run into some problems.Please try again at a later time");
        }
        return new MainSearchResponse(campusSearchResponses, nearby, transport);
    }
}