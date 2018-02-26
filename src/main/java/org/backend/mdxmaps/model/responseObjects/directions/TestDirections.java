package org.backend.mdxmaps.model.responseObjects.directions;

import org.backend.mdxmaps.model.LatLng;

import java.util.ArrayList;

/**
 * Created by Emmanuel Keboh on 22/02/2018.
 */
public class TestDirections {
    public double distance;
    public ArrayList<LatLng> route;

    public TestDirections(double distance, ArrayList<LatLng> route) {
        this.distance = distance;
        this.route = route;
    }
}
