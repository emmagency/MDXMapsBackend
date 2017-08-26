package org.backend.mdxmaps.model.responseObjects.search;

import java.util.List;

/**
 * Created by Emmanuel keboh on 13/07/2017.
 */
public class MainSearchResponse {

    private List<CampusSearchResponse> campus;
    private List<NearbySearchResponse> nearby;
    private List<TransportSearchResponse> transport;

    private MainSearchResponse(List<CampusSearchResponse> campus, List<NearbySearchResponse> nearby, List<TransportSearchResponse> transport) {
        this.campus = campus;
        this.nearby = nearby;
        this.transport = transport;
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

    public static MainSearchResponse create(List<CampusSearchResponse> CampusSearchResponse, List<NearbySearchResponse> nearby,
                                            List<TransportSearchResponse> transport) {
        return new MainSearchResponse(CampusSearchResponse, nearby, transport);
    }
}