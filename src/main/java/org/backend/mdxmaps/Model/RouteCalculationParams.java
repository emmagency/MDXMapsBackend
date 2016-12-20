package org.backend.mdxmaps.Model;

/**
 * Created by Emmanuel Keboh on 14/12/2016.
 */
public class RouteCalculationParams {
    String startEDMethod, destEDMethod;
    boolean startED, destED = false;

    public RouteCalculationParams(boolean startED, boolean destED, String startEDMethod, String destEDMethod) {
        this.startED = startED;
        this.destED = destED;
        this.startEDMethod = startEDMethod;
        this.destEDMethod = destEDMethod;
    }

    public String getStartEDMethod() {
        return startEDMethod;
    }

    public String getDestEDMethod() {
        return destEDMethod;
    }

    public boolean isStartED() {
        return startED;
    }

    public boolean isDestED() {
        return destED;
    }
}
