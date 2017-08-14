package org.backend.mdxmaps.services;

import org.backend.mdxmaps.model.Routing;
import org.backend.mdxmaps.model.enums.MOT;

import java.util.Arrays;

/**
 * Created by Emmanuel Keboh on 22/12/2016.
 */
public final class MOTValidator {
    private MOTValidator() {
    }

    public static boolean validate(Routing start, Routing end) {
        if (start.getBuilding().equals(end.getBuilding())) {
            return start.getLevel() != end.getLevel();
        } else {
            return (start.getLevel() != 0 || end.getLevel() != 0);
        }
    }

    public static MOT autoResolve(Routing start, Routing end) {
        return validate(start, end) ? MOT.STAIRS : MOT.NULL;
    }


    public static boolean isReceivedMOTValid(String mot) {
        return Arrays.stream(MOT.values()).anyMatch(MOT -> MOT.toString().equalsIgnoreCase(mot));
    }
}
