package org.backend.mdxmaps.Services;

import org.backend.mdxmaps.Model.RouteCalculation;

/**
 * Created by Emmanuel Keboh on 18/12/2016.
 */
public class DiffBuildingFactoryService implements RouteCalculation {

    String startEDMethod, destEDMethod;
    boolean startED, destED;

    public DiffBuildingFactoryService(String startEDMethod, String destEDMethod, boolean startED, boolean destED) {
        this.startEDMethod = startEDMethod;
        this.destEDMethod = destEDMethod;
        this.startED = startED;
        this.destED = destED;
    }

    @Override
    public ResponseService getRoute() {
        return null;
    }
}
