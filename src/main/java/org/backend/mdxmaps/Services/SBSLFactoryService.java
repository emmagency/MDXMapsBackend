package org.backend.mdxmaps.Services;

import org.backend.mdxmaps.Model.RouteCalculation;

/**
 * Created by Emmanuel Keboh on 18/12/2016.
 */

/*SBSL: Same building, same level*/
public class SBSLFactoryService implements RouteCalculation {

    private String building, mot;
    private int level;

    public SBSLFactoryService(String building, String mot, int level) {
        this.building = building;
        this.mot = mot;
        this.level = level;
    }

    public String getBuilding() {
        return building;
    }

    public String getMot() {
        return mot;
    }

    public int getLevel() {
        return level;
    }


    @Override
    public ResponseService getRoute() {
        return null;
    }

}
