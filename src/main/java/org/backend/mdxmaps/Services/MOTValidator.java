package org.backend.mdxmaps.Services;

import org.backend.mdxmaps.Model.MOT;
import org.backend.mdxmaps.Model.RoutingObjects;

import java.util.Arrays;

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

    public static MOT autoResolve(RoutingObjects start, RoutingObjects end) {
        return validate(start, end) ? MOT.STAIRS : MOT.NULL;
    }


    public static boolean validateReceivedMOTTypeExists(String mot) {
        return Arrays.stream(MOT.values()).anyMatch(MOT -> MOT.toString().equalsIgnoreCase(mot));
    }
}
