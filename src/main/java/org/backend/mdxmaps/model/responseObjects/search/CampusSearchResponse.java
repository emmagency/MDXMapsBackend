package org.backend.mdxmaps.model.responseObjects.search;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * Created by Emmanuel Keboh on 12/03/2017.
 */
@JsonInclude(JsonInclude.Include.NON_EMPTY)
public final class CampusSearchResponse {

    private String name, building, level, description, latLng;
    private int gMapLevel;
    private boolean isDirectionsAvailable;

    private CampusSearchResponse(String name, String building, String description, String latLng, String level,
                                 int gMapLevel, boolean isDirectionsAvailable) {
        this.name = name;
        this.building = building;
        this.description = description;
        this.latLng = latLng;
        this.level = level;
        this.gMapLevel = gMapLevel;
        this.isDirectionsAvailable = isDirectionsAvailable;
    }

    public String getName() {
        return name;
    }

    public String getBuilding() {
        return building;
    }

    public String getDescription() {
        return description;
    }

    public String getLatLng() {
        return latLng;
    }

    public String getLevel() {
        return level;
    }

    public int getgMapLevel() {
        return gMapLevel;
    }

    @JsonProperty(value = "isDirectionsAvailable")
    public boolean isDirectionsAvailable() {
        return isDirectionsAvailable;
    }

    private CampusSearchResponse() {
    }

    public static CampusSearchResponse create(String name, String building, String description, String latLng,
                                              String level, int gMapLevel, boolean isDirectionsAvailable) {
        return new CampusSearchResponse(name, building, description, latLng, level, gMapLevel, isDirectionsAvailable);
    }

}
