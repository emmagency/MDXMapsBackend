package org.backend.mdxmaps.service;

import org.backend.mdxmaps.model.enums.MOT;

import java.util.ArrayList;

import static org.backend.mdxmaps.model.enums.MOT.NULL;
import static org.backend.mdxmaps.model.enums.MOT.STAIRS;

/**
 * Created by Emmanuel Keboh on 11/02/2017.
 */

public final class IconResolverService {

    public static String WALK = "assets/icons/walk.svg";
    public static String WHEELCHAIR = "assets/icons/disabled.svg";
    private static String ELEVATORS = "assets/icons/elevator.svg";
    private static String STAIRS_UP = "assets/icons/stairs-up.svg";
    private static String STAIRS_DOWN = "assets/icons/stairs-down.svg";
    private static String DOOR = "assets/icons/door.svg";

    private IconResolverService() {
    }

    public static ArrayList<String> resolveSBDLIcons(MOT mot, String direction) {

        ArrayList<String> iconsList = new ArrayList<>();

        switch (mot) {
            case STAIRS:
                iconsList.add(WALK);
                if (direction.equals("up")) {
                    iconsList.add(STAIRS_UP);
                } else {
                    iconsList.add(STAIRS_DOWN);
                }
                iconsList.add(WALK);
                break;
            case ELEVATORS:
                iconsList.add(WALK);
                iconsList.add(ELEVATORS);
                iconsList.add(WALK);
                break;
            case DISABLED:
                iconsList.add(WHEELCHAIR);
                iconsList.add(ELEVATORS);
                iconsList.add(WHEELCHAIR);
        }
        return iconsList;
    }

    public static ArrayList<String> resolveDifferentBuildingIcons(boolean disabled, MOT startEDMethod, MOT destEDMethod, boolean isStartED,
                                                                  boolean isDestED, String startDir, String destDir) {
        ArrayList<String> iconsList = new ArrayList<>();

        if (disabled) {
            if (isStartED && isDestED) {
                iconsList.add(WHEELCHAIR);
                iconsList.add(ELEVATORS);
                iconsList.add(DOOR);
                iconsList.add(WHEELCHAIR);
                iconsList.add(DOOR);
                iconsList.add(ELEVATORS);
                iconsList.add(WHEELCHAIR);
            } else if (!isStartED && isDestED) {
                iconsList.add(DOOR);
                iconsList.add(WHEELCHAIR);
                iconsList.add(DOOR);
                iconsList.add(ELEVATORS);
                iconsList.add(WHEELCHAIR);
            } else if (isStartED) {
                iconsList.add(WHEELCHAIR);
                iconsList.add(ELEVATORS);
                iconsList.add(DOOR);
                iconsList.add(WHEELCHAIR);
                iconsList.add(DOOR);
            } else {
                iconsList.add(DOOR);
                iconsList.add(WHEELCHAIR);
                iconsList.add(DOOR);
            }
            return iconsList;
        }

        if (startEDMethod == NULL && destEDMethod == NULL) {
            iconsList.add(DOOR);
            iconsList.add(WALK);
            iconsList.add(DOOR);
            return iconsList;
        }

        if (isStartED && isDestED) {
            iconsList.add(WALK);
            plugInEDIcons(iconsList, startDir, startEDMethod);
            iconsList.add(DOOR);
            iconsList.add(WALK);
            iconsList.add(DOOR);
            plugInEDIcons(iconsList, destDir, destEDMethod);
            iconsList.add(WALK);
        } else if (!isStartED && isDestED) {
            iconsList.add(DOOR);
            iconsList.add(WALK);
            iconsList.add(DOOR);
            plugInEDIcons(iconsList, destDir, destEDMethod);
            iconsList.add(WALK);
        } else {
            iconsList.add(WALK);
            plugInEDIcons(iconsList, startDir, startEDMethod);
            iconsList.add(DOOR);
            iconsList.add(WALK);
            iconsList.add(DOOR);
        }
        return iconsList;
    }

    private static void plugInEDIcons(ArrayList<String> iconsList, String direction, MOT EDMethod) {
        if (EDMethod == STAIRS) {
            if (direction.equals("up")) {
                iconsList.add(STAIRS_UP);
            } else {
                iconsList.add(STAIRS_DOWN);
            }
        } else {
            iconsList.add(ELEVATORS);
        }
    }
}
