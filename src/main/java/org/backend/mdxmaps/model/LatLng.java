package org.backend.mdxmaps.model;

import java.util.Objects;

/**
 * Created by Emmanuel Keboh on 27/11/2016.
 */
public class LatLng {

    public final double latitude;
    public final double longitude;

    public LatLng(double latitude, double longitude) {
        this.latitude = latitude;
        this.longitude = longitude;
    }

    @Override
    public int hashCode() {
        return Objects.hash(latitude, longitude);
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == null || !(obj instanceof LatLng)) return false;

        if (this == obj) return true;

        LatLng that = (LatLng) obj;

        return Objects.equals(latitude, that.latitude) && Objects.equals(longitude, that.longitude);
    }

    @Override
    public String toString() {
        return Objects.toString(latitude + ", " + longitude);
    }
}
