package org.backend.mdxmaps.model.responseObjects.search;

import com.fasterxml.jackson.annotation.JsonInclude;
import org.backend.mdxmaps.model.solr.Nearby;

import java.util.List;

/**
 * Created by Emmanuel Keboh on 13/07/2017.
 */
public final class NearbySearchResponse {

    @JsonInclude(JsonInclude.Include.NON_EMPTY)
    private String errorMessage;

    private List<Nearby> nearby;

    private NearbySearchResponse(String errorMessage) {
        this.errorMessage = errorMessage;
    }

    private NearbySearchResponse(List<Nearby> nearby) {
        this.nearby = nearby;
    }

    public String getErrorMessage() {
        return errorMessage;
    }

    public List<Nearby> getNearby() {
        return nearby;
    }

    public static NearbySearchResponse create(List<Nearby> nearby) {
        if (nearby == null) {
            return new NearbySearchResponse("Apologies! We've run into some problems. Please try again at a later time");
        }
        return new NearbySearchResponse(nearby);
    }
}
