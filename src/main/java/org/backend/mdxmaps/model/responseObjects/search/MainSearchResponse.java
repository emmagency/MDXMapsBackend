package org.backend.mdxmaps.model.responseObjects.search;

import com.fasterxml.jackson.annotation.JsonInclude;
import org.backend.mdxmaps.model.solr.Campus;
import org.backend.mdxmaps.model.solr.Nearby;
import org.backend.mdxmaps.model.solr.Transport;

import java.util.List;

/**
 * Created by Emmanuel keboh on 13/07/2017.
 */
public final class MainSearchResponse {

    private List<Campus> campus;
    private List<Nearby> nearby;
    private List<Transport> transport;

    @JsonInclude(JsonInclude.Include.NON_EMPTY)
    private String errorMessage;

    private MainSearchResponse(List<Campus> campus, List<Nearby> nearby, List<Transport> transport) {
        this.campus = campus;
        this.nearby = nearby;
        this.transport = transport;

    }

    private MainSearchResponse(String errorMessage) {
        this.errorMessage = errorMessage;
    }

    public List<Campus> getCampus() {
        return campus;
    }

    public List<Nearby> getNearby() {
        return nearby;
    }

    public List<Transport> getTransport() {
        return transport;
    }

    public String getErrorMessage() {
        return errorMessage;
    }

    public static MainSearchResponse create(List<Campus> campus, List<Nearby> nearby,
                                            List<Transport> transport) {
        if (campus == null && nearby == null && transport == null) {
            return new MainSearchResponse("Apologies! We've run into some problems. Please try again at a later time");
        }
        return new MainSearchResponse(campus, nearby, transport);
    }
}