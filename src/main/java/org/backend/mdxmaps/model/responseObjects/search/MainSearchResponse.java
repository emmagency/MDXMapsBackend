package org.backend.mdxmaps.model.responseObjects.search;

import org.backend.mdxmaps.model.solr.CampusSearchResponse;
import org.backend.mdxmaps.model.solr.model.Nearby;
import org.backend.mdxmaps.model.solr.model.Transport;

import java.util.List;

/**
 * Created by Emmanuel keboh on 13/07/2017.
 */
public class MainSearchResponse {

    private List<CampusSearchResponse> campus;
    private List<Nearby> nearby;
    private List<Transport> transport;

    private MainSearchResponse(List<CampusSearchResponse> campus, List<Nearby> nearby, List<Transport> transport) {
        this.campus = campus;
        this.nearby = nearby;
        this.transport = transport;
    }

    public List<CampusSearchResponse> getCampus() {
        return campus;
    }

    public List<Nearby> getNearby() {
        return nearby;
    }

    public List<Transport> getTransport() {
        return transport;
    }

    public static MainSearchResponse create(List<CampusSearchResponse> CampusSearchResponse, List<Nearby> nearby,
                                            List<Transport> transport) {
        return new MainSearchResponse(CampusSearchResponse, nearby, transport);
    }
}
