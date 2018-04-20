package org.backend.mdxmaps.service.util;

import org.backend.mdxmaps.model.enums.MOT;

/**
 * Created by Emmanuel Keboh on 28/07/2017.
 */
public class TravelTimeCalculator {

    public static int getTravelTime(double distance, MOT mot, int numberOfFlights) {
        if (mot != MOT.NULL) {
            int approxTotalTimeInMinutes = 0;
            double flightTimeInSeconds = numberOfFlights * 20; //Applies for elevators too because there may be wait times even though they're faster
            if (mot == MOT.DISABLED) {
                double wheelChairTimeInSeconds = distance <= 65 ? 1 : (distance / 1.08); //3.9km/h is average speed, which works out at 1.08m/s
                approxTotalTimeInMinutes = (int) Math.ceil((flightTimeInSeconds + wheelChairTimeInSeconds) / 60);
            } else {
                double walkTimeInSeconds = distance <= 80 ? 1 : (distance / 1.4); //1.4m/s is the average walking speed for most people
                approxTotalTimeInMinutes = (int) Math.ceil((flightTimeInSeconds + walkTimeInSeconds) / 60);
            }
            return approxTotalTimeInMinutes;
        }
        return distance <= 80 ? 1 : (int) Math.ceil(distance / 80);
    }
}
