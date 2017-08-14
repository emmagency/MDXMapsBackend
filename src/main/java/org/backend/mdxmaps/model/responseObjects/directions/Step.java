package org.backend.mdxmaps.model.responseObjects.directions;

import com.fasterxml.jackson.annotation.JsonInclude;
import org.backend.mdxmaps.model.LatLng;

import java.util.List;

/**
 * Created by Emmanuel Keboh on 09/08/2017.
 */
@JsonInclude(JsonInclude.Include.NON_EMPTY)
public class Step {
    private String description;
    private Integer gMapLevel;
    private String iconHref;
    private List<LatLng> latLng;

    private Step(String description, Integer gMapLevel, String iconHref, List<LatLng> latLng) {
        this.description = description;
        this.gMapLevel = gMapLevel;
        this.iconHref = iconHref;
        this.latLng = latLng;
    }

    public static Step createStep(String description, Integer gMapLevel, String iconHref, List<LatLng> latLng) {
        return new Step(description, gMapLevel, iconHref, latLng);
    }

    public String getDescription() {
        return description;
    }

    public Integer getgMapLevel() {
        return gMapLevel;
    }

    public String getIconHref() {
        return iconHref;
    }

    public List<LatLng> getLatLng() {
        return latLng;
    }
}
