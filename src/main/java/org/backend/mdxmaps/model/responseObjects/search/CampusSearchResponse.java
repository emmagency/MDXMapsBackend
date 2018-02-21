package org.backend.mdxmaps.model.responseObjects.search;

import com.fasterxml.jackson.annotation.JsonInclude;
import org.backend.mdxmaps.model.solr.Campus;

import java.util.List;

/**
 * Created by Emmanuel Keboh on 12/03/2017.
 */
public final class CampusSearchResponse {

    @JsonInclude(JsonInclude.Include.NON_EMPTY)
    private String errorMessage;

    private List<Campus> campus;

    private CampusSearchResponse() {
    }

    private CampusSearchResponse(String errorMessage) {
        this.errorMessage = errorMessage;
    }

    private CampusSearchResponse(List<Campus> campus) {
        this.campus = campus;
    }

    public String getErrorMessage() {
        return errorMessage;
    }

    public List<Campus> getCampus() {
        return campus;
    }

    public static CampusSearchResponse create(List<Campus> campus) {
        if (campus == null) {
            return new CampusSearchResponse("Apologies! We've run into some problems. Please try again at a later time");
        }
        return new CampusSearchResponse(campus);
    }



}
