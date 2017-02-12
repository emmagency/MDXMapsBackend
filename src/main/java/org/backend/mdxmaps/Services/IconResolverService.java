package org.backend.mdxmaps.Services;

import org.backend.mdxmaps.Model.Enums.MOT;

import java.util.ArrayList;

import static org.backend.mdxmaps.Model.Enums.MOT.NULL;
import static org.backend.mdxmaps.Model.Enums.MOT.STAIRS;

/**
 * Created by Emmanuel Keboh on 11/02/2017.
 */
public final class IconResolverService {
    private IconResolverService() {
    }

    public static ArrayList<String> resolveSBDLIcons(MOT mot, String direction) {

        ArrayList<String> iconsList = new ArrayList<>();

        switch (mot) {
            case STAIRS:
                iconsList.add("walk");
                if (direction.equals("up")) {
                    iconsList.add("stairs_up");
                } else {
                    iconsList.add("stairs_down");
                }
                iconsList.add("walk");
                break;
            case ELEVATORS:
                iconsList.add("walk");
                iconsList.add("elevators");
                iconsList.add("walk");
                break;
            case DISABLED:
                iconsList.add("wheelchair");
                iconsList.add("elevators");
                iconsList.add("wheelchair");
        }
        return iconsList;
    }

    public static ArrayList<String> resolveDifferentBuildingIcons(boolean disabled, MOT startEDMethod, MOT destEDMethod, boolean isStartED,
                                                                  boolean isDestED, String startDir, String destDir) {
        ArrayList<String> iconsList = new ArrayList<>();

        if (disabled) {
            if (isStartED && isDestED) {
                iconsList.add("wheelchair");
                iconsList.add("elevators");
                iconsList.add("wheelchair");
                iconsList.add("elevators");
                iconsList.add("wheelchair");
            } else if ((!isStartED && isDestED) || (isStartED)) {
                iconsList.add("wheelchair");
                iconsList.add("elevators");
                iconsList.add("wheelchair");
            } else {
                iconsList.add("wheelchair");
            }
            return iconsList;
        }

        if (startEDMethod == NULL && destEDMethod == NULL) {
            iconsList.add("walk");
            return iconsList;
        }

        if (isStartED && isDestED) {
            iconsList.add("walk");
            if (startEDMethod == STAIRS) {
                if (startDir.equals("up")) {
                    iconsList.add("stairs_up");
                } else {
                    iconsList.add("stairs_down");
                }
            } else {
                iconsList.add("elevators");
            }
            iconsList.add("walk");
            if (destEDMethod == STAIRS) {
                if (destDir.equals("up")) {
                    iconsList.add("stairs_up");
                } else {
                    iconsList.add("stairs_down");
                }
            } else {
                iconsList.add("elevators");
            }
            iconsList.add("walk");
        } else if (!isStartED) {
            iconsList.add("walk");
            if (destEDMethod == STAIRS) {
                if (destDir.equals("up")) {
                    iconsList.add("stairs_up");
                } else {
                    iconsList.add("stairs_down");
                }

            } else {
                iconsList.add("elevators");
            }
            iconsList.add("walk");
        } else {
            iconsList.add("walk");
            if (startEDMethod == STAIRS) {
                if (startDir.equals("up")) {
                    iconsList.add("stairs_up");
                } else {
                    iconsList.add("stairs_down");
                }

            } else {
                iconsList.add("elevators");
            }
            iconsList.add("walk");
        }

        return iconsList;
    }
}
