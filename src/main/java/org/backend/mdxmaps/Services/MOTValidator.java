package org.backend.mdxmaps.Services;

import org.backend.mdxmaps.Model.RoutingObjects;

/**
 * Created by Emmanuel Keboh on 22/12/2016.
 */
public final class MOTValidator {
    private MOTValidator() {
    }

    public static boolean validate(RoutingObjects start, RoutingObjects end) {

        if (start.getBuilding().equals(end.getBuilding())) {
            return start.getActualLevel() != end.getActualLevel();
        } else {
            return (start.getActualLevel() != 0 || end.getActualLevel() != 0);
        }

    }
}
