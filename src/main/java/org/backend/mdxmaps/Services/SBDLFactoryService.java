package org.backend.mdxmaps.Services;

import org.backend.mdxmaps.Model.RouteCalculation;

/**
 * Created by Emmanuel Keboh on 18/12/2016.
 */

/*SBDL: Same building, different levels*/
public class SBDLFactoryService implements RouteCalculation {

    private String building, mot;
    private int startLevel, destinationLevel;

    public SBDLFactoryService(String building, String mot, int startLevel, int destinationLevel) {
        this.building = building;
        this.mot = mot;
        this.startLevel = startLevel;
        this.destinationLevel = destinationLevel;
    }

    public String getBuilding() {
        return building;
    }

    public String getMot() {
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
