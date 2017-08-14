package org.backend.mdxmaps.services;

import org.backend.mdxmaps.model.enums.MOT;

/**
 * Created by Emmanuel Keboh on 28/07/2017.
 */
public class TravelTimeCalc {
    public static int getTravelTime(double distance, MOT mot) {
        //ToDo Get average distance for wheelchair users
        return distance <= 80 ? 1 : (int) distance / 80;
    }
}
