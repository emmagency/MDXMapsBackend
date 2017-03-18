package org.backend.mdxmaps.Model.Solr;

import com.fasterxml.jackson.annotation.JsonInclude;

/**
 * Created by Emmanuel Keboh on 12/03/2017.
 */
@JsonInclude(JsonInclude.Include.NON_EMPTY)
public final class RoomSearchResponseObject {

    private String name, building, description;
    private String latLng;
    private int gMapLevel;
    private boolean isDirectionsAvailable;

    private RoomSearchResponseObject(String name, String building, String description, String latLng, int gMapLevel, boolean isDirectionsAvailable) {
        this.name = name;
        this.building = building;
        this.description = description;
        this.latLng = latLng;
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

    public int getgMapLevel() {
        return gMapLevel;
    }

    public boolean isDirectionsAvailable() {
        return isDirectionsAvailable;
    }

    private RoomSearchResponseObject() {
    }

    public static RoomSearchResponseObject create(String name, String building, String description, String latLng,
                                                  int gMapLevel, boolean isDirectionsAvailable) {
        return new RoomSearchResponseObject(name, building, description, latLng, gMapLevel, isDirectionsAvailable);
    }

}
