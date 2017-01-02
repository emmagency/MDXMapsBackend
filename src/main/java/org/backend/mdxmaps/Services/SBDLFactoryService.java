package org.backend.mdxmaps.Services;

import org.backend.mdxmaps.Model.MOT;
import org.backend.mdxmaps.Model.RouteCalculation;

/**
 * Created by Emmanuel Keboh on 18/12/2016.
 */

/*SBDL: Same building, different levels*/
public class SBDLFactoryService implements RouteCalculation {

    private String building;
    private MOT mot;
    private int startLevel, destinationLevel;

    public SBDLFactoryService(String building, MOT mot, int startLevel, int destinationLevel) {
        this.building = building;
        this.mot = mot;
        this.startLevel = startLevel;
        this.destinationLevel = destinationLevel;
    }

    public String getBuilding() {
        return building;
    }

    public MOT getMot() {
        return mot;
    }

    public int getStartLevel() {
        return startLevel;
    }

    public int getDestinationLevel() {
        return destinationLevel;
    }

    @Override
    public ResponseService getRoute() {
        return null;
    }
}
