package org.backend.mdxmaps.Services;

import org.backend.mdxmaps.Model.MOT;
import org.backend.mdxmaps.Model.RoutingObjects;
import org.backend.mdxmaps.Services.ResponseService.Status;

import static org.backend.mdxmaps.Model.MOT.ELEVATORS;
import static org.backend.mdxmaps.Model.MOT.STAIRS;

/**
 * Created by Emmanuel Keboh on 14/12/2016.
 */
public class MOTCheckService {

    private String startBuilding, destBuilding, startEDMethod, destEDMethod;
    MOT mot;
    private int startLevel, destLevel;
    private boolean startED, destED = false;
    private RoutingObjects startObject, destinationObject;

    public MOTCheckService(RoutingObjects startObject, RoutingObjects destinationObject, MOT mot) {
        this.startBuilding = startObject.getBuilding();
        this.destBuilding = destinationObject.getBuilding();
        this.startLevel = startObject.getActualLevel();
        this.destLevel = destinationObject.getActualLevel();
        this.mot = mot;
        this.startObject = startObject;
        this.destinationObject = destinationObject;
    }

    public ResponseService doMOTCheck() {
        ResponseService response = new ResponseService();

        switch (mot) {
            case NULL:
                //It's either sbsl or diff buildings ground floors
                response.setStatus(ResponseService.Status.OK);
                if (startBuilding.equals(destBuilding)) {
                    response.setEntity(new SBSLFactoryService(startObject, destinationObject, MOT.NULL));
                } else {
                    response.setEntity(new DiffBuildingFactoryService(null, null, false, false));
                }
                break;

            case DISABLED:
                boolean startCheck;
                boolean destCheck;

                if (!startBuilding.equals(destBuilding)) { //Different Buildings
                    if (new RoutingObjects().getBuildingObject(destBuilding).isBuildingWheelChairAccessible() &&
                            new RoutingObjects().getBuildingObject(startBuilding).isBuildingWheelChairAccessible()) {
                        if (startLevel != 0) {
                            if (new RoutingObjects().getBuildingObject(startBuilding).hasElevators) {
                                startCheck = true;
                                startED = true;
                            } else {
                                startCheck = false;
                            }
                        } else {
                            startCheck = true;
                            startED = false;
                        }

                        if (destLevel != 0) {
                            if (new RoutingObjects().getBuildingObject(destBuilding).hasElevators) {
                                destCheck = true;
                                destED = true;
                            } else {
                                destCheck = false;
                            }
                        } else {
                            destCheck = true;
                            destED = false;
                        }

                        if (!startCheck && !destCheck) {
                            response.setStatus(Status.ERROR);
                            response.setMessage("There are no elevators in " + startBuilding + " and " + destBuilding);
                        } else if (startCheck && !destCheck) {
                            response.setStatus(Status.ERROR);
                            response.setMessage("There are no elevators in " + destBuilding + " to get to " + destBuilding);
                        } else if (!startCheck) {
                            response.setStatus(Status.ERROR);
                            response.setMessage("There are no elevators in " + startBuilding);
                        } else {
                            startEDMethod = "elevators";
                            destEDMethod = "elevators";
                            response.setStatus(Status.OK);
                            response.setEntity(new DiffBuildingFactoryService(startEDMethod, destEDMethod, startED, destED));
                        }
                    } else {
                        if (!new RoutingObjects().getBuildingObject(destBuilding).isBuildingWheelChairAccessible() &&
                                !new RoutingObjects().getBuildingObject(startBuilding).isBuildingWheelChairAccessible()) {
                            response.setStatus(Status.ERROR);
                            response.setMessage("Both " + startBuilding + " & " + destBuilding + " are not wheelchair accessible");
                        } else if (!new RoutingObjects().getBuildingObject(startBuilding).isBuildingWheelChairAccessible()) {
                            response.setStatus(Status.ERROR);
                            response.setMessage(startBuilding + " is not wheelchair accessible");
                        } else {
                            response.setStatus(Status.ERROR);
                            response.setMessage(destBuilding + " is not wheelchair accessible");
                        }
                    }
                } else { //Same Building.
                    if (new RoutingObjects().getBuildingObject(startBuilding).isBuildingWheelChairAccessible()) {
                        if (startLevel != 0 || destLevel != 0) {
                            if (new RoutingObjects().getBuildingObject(startBuilding).hasElevators) {
                                response.setStatus(Status.OK);
                                response.setEntity(new SBDLFactoryService(startBuilding, mot, startLevel, destLevel));
                            } else {
                                //Building doesn't have elevators
                                response.setStatus(Status.ERROR);
                                response.setMessage("There are no elevators in " + startBuilding);
                            }
                        } else {
                            response.setStatus(Status.OK);
                            response.setEntity(new SBSLFactoryService(startObject, destinationObject, mot));
                        }
                    } else {
                        //Building isn't wheelchair accessible
                        response.setStatus(Status.ERROR);
                        response.setMessage(startBuilding + " is not wheelchair accessible");
                    }
                }
                break;

            case STAIRS:
                if (startBuilding.equals(destBuilding)) { //Same Building
                    if (new RoutingObjects().getBuildingObject(startBuilding).hasStairs) {
                        response.setStatus(Status.OK);
                    } else {
                        //No stairs, inform user app will use elevators
                        response.setStatus(Status.INFO);
                        response.setMessage("No stairs in " + startBuilding + ". App switched to elevators for this building");
                        mot = ELEVATORS;
                    }
                    response.setEntity(new SBDLFactoryService(startBuilding, mot, startLevel, destLevel));
                } else { //Diff buildings
                    if (startLevel != 0) {
                        if (new RoutingObjects().getBuildingObject(startBuilding).hasStairs) {
                            startED = true;
                            startEDMethod = "stairs";
                            response.setStatus(Status.OK);
                        } else {
                            //No stairs, inform user app will use elevators for this building
                            startED = true;
                            startEDMethod = "elevators";
                            response.setStatus(Status.INFO);
                            response.setMessage("No stairs in " + startBuilding + ". App switched to elevators for " + startBuilding);
                        }
                    } else {
                        startED = false;
                        startEDMethod = "null";
                        response.setStatus(Status.OK);
                    }

                    if (destLevel != 0) {
                        if (new RoutingObjects().getBuildingObject(destBuilding).hasStairs) {
                            destED = true;
                            destEDMethod = "stairs";
                        } else {
                            //No stairs, inform user app will use elevators for this building
                            destED = true;
                            destEDMethod = "elevators";
                            response.setStatus(Status.INFO);
                            if (response.getMessage() == null) {
                                response.setMessage("No stairs in " + destBuilding + ". App switched to elevators for " + destBuilding);
                            } else {
                                response.setMessage("No stairs in both buildings, app switched to elevators in " + startBuilding +
                                        " & " + destBuilding);
                            }
                        }
                    } else {
                        destED = false;
                        destEDMethod = "null";
                    }

                    response.setEntity(new DiffBuildingFactoryService(startEDMethod, destEDMethod, startED, destED));
                }
                break;

            case ELEVATORS:
                if (startBuilding.equals(destBuilding)) { //Same Building
                    if (new RoutingObjects().getBuildingObject(startBuilding).hasElevators) {
                        response.setStatus(Status.OK);
                    } else {
                        //No elevators
                        mot = STAIRS;
                        response.setStatus(Status.INFO);
                        response.setMessage("No elevators in " + startBuilding + ". App switched to stairs for this building");
                    }
                    response.setEntity(new SBDLFactoryService(startBuilding, mot, startLevel, destLevel));
                } else { //Diff Buildings
                    if (startLevel != 0) {
                        if (new RoutingObjects().getBuildingObject(startBuilding).hasElevators) {
                            startED = true;
                            startEDMethod = "elevators";
                            response.setStatus(Status.OK);
                        } else {
                            //No elevators, inform user app will use stairs for this building
                            startED = true;
                            startEDMethod = "stairs";
                            response.setStatus(Status.INFO);
                            response.setMessage("No elevators in " + startBuilding + ". App switched to stairs for " + startBuilding);
                        }
                    } else {
                        startED = false;
                        startEDMethod = "null";
                        response.setStatus(Status.OK);
                    }

                    if (destLevel != 0) {
                        if (new RoutingObjects().getBuildingObject(destBuilding).hasElevators) {
                            destED = true;
                            destEDMethod = "elevators";
                        } else {
                            //No elevators, inform user app will use stairs for this building
                            destED = true;
                            destEDMethod = "stairs";
                            response.setStatus(Status.INFO);
                            if (response.getMessage() == null) {
                                response.setMessage("No elevators in " + destBuilding + ". App switched to stairs for " + destBuilding);
                            } else {
                                response.setMessage("No elevators in both buildings, app switched to stairs for " + startBuilding +
                                        " & " + destBuilding);
                            }
                        }
                    } else {
                        destED = false;
                        destEDMethod = "null";
                    }
                    response.setEntity(new DiffBuildingFactoryService(startEDMethod, destEDMethod, startED, destED));
                }
                break;
        }
        return response;
    }
}
