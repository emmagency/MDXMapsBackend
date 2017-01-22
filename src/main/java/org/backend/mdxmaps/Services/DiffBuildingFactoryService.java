package org.backend.mdxmaps.Services;

import org.backend.mdxmaps.Model.Enums.MOT;
import org.backend.mdxmaps.Model.RouteCalculation;

/**
 * Created by Emmanuel Keboh on 18/12/2016.
 */
public class DiffBuildingFactoryService implements RouteCalculation {

    MOT startEDMethod, destEDMethod;
    boolean startED, destED;

    public MOT getStartEDMethod() {
        return startEDMethod;
    }

    public MOT getDestEDMethod() {
        return destEDMethod;
    }

    public boolean isStartED() {
        return startED;
    }

    public boolean isDestED() {
        return destED;
    }

    public DiffBuildingFactoryService(MOT startEDMethod, MOT destEDMethod, boolean startED, boolean destED) {
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
