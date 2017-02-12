package org.backend.mdxmaps.Services;

import org.backend.mdxmaps.Model.Enums.MOT;
import org.backend.mdxmaps.Model.RoutingObjects;
import org.backend.mdxmaps.Services.ResponseService.Status;

import static org.backend.mdxmaps.Model.Enums.MOT.ELEVATORS;
import static org.backend.mdxmaps.Model.Enums.MOT.NULL;
import static org.backend.mdxmaps.Model.Enums.MOT.STAIRS;

/**
 * Created by Emmanuel Keboh on 14/12/2016.
 */
public class ResolveOperationTypeService {

    private String destRoom;
    private MOT mot, startEDMethod, destEDMethod;
    private int startLevel, destLevel;
    private boolean startED, destED = false;
    private RoutingObjects startObject, destinationObject;

    public ResolveOperationTypeService(RoutingObjects startObject, RoutingObjects destinationObject, MOT mot) {
        this.startLevel = startObject.getActualLevel();
        this.destLevel = destinationObject.getActualLevel();
        this.destRoom = destinationObject.getName();
        this.mot = mot;
        this.startObject = startObject;
        this.destinationObject = destinationObject;
    }

    @SuppressWarnings("Duplicates")
    public ResponseService resolveOPType() {
        ResponseService response = new ResponseService();

        switch (mot) {
            case NULL:
                //It's either sbsl or diff buildings ground floors
                response.setStatus(ResponseService.Status.OK);
                if (startObject.getBuilding().equals(destinationObject.getBuilding())) {
                    response.setEntity(SBSLFactoryService.create(startObject, destinationObject, NULL));
                } else {
                    response.setEntity(DiffBuildingFactoryService.create(startObject, destinationObject, NULL, NULL, false, false, false));
                }
                break;

            case DISABLED:
                boolean startCheck;
                boolean destCheck;

                if (!startObject.getBuilding().equals(destinationObject.getBuilding())) { //Different Buildings
                    if (destinationObject.getBuildingObject().isBuildingWheelChairAccessible() &&
                            startObject.getBuildingObject().isBuildingWheelChairAccessible()) {
                        if (startLevel != 0) {
                            if (startObject.getBuildingObject().hasElevators()) {
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
                            if (destinationObject.getBuildingObject().hasElevators()) {
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
                            response.setMessage("There are no elevators in " + startObject.getBuilding() + " and " + destinationObject.getBuilding());
                        } else if (startCheck && !destCheck) {
                            response.setStatus(Status.ERROR);
                            response.setMessage("There are no elevators in " + destinationObject.getBuilding() + " to get to " + destRoom);
                        } else if (!startCheck) {
                            response.setStatus(Status.ERROR);
                            response.setMessage("There are no elevators in " + startObject.getBuilding());
                        } else {
                            if (!startED && !destED) {
                                startEDMethod = NULL;
                                destEDMethod = NULL;
                            } else if (!startED && destED) {
                                startEDMethod = NULL;
                                destEDMethod = ELEVATORS;
                            } else if (!destED && startED) {
                                startEDMethod = ELEVATORS;
                                destEDMethod = NULL;
                            } else {
                                startEDMethod = ELEVATORS;
                                destEDMethod = ELEVATORS;
                            }
                            response.setStatus(Status.OK);
                            response.setEntity(DiffBuildingFactoryService.create(startObject, destinationObject, startEDMethod, destEDMethod, true, startED, destED));
                        }
                    } else {
                        if (!destinationObject.getBuildingObject().isBuildingWheelChairAccessible() &&
                                !startObject.getBuildingObject().isBuildingWheelChairAccessible()) {
                            response.setStatus(Status.ERROR);
                            response.setMessage("Both " + startObject.getBuilding() + " & " + destinationObject.getBuilding() + " are not wheelchair accessible");
                        } else if (!startObject.getBuildingObject().isBuildingWheelChairAccessible()) {
                            response.setStatus(Status.ERROR);
                            response.setMessage(startObject.getBuilding() + " is not wheelchair accessible");
                        } else {
                            response.setStatus(Status.ERROR);
                            response.setMessage(destinationObject.getBuilding() + " is not wheelchair accessible");
                        }
                    }
                } else { //Same Building.
                    if (startObject.getBuildingObject().isBuildingWheelChairAccessible()) {
                        if (startLevel != 0 || destLevel != 0) {
                            if (startObject.getBuildingObject().hasElevators()) {
                                response.setStatus(Status.OK);
                                response.setEntity(SBDLFactoryService.create(startObject, destinationObject, mot));
                            } else {
                                //Building doesn't have elevators
                                response.setStatus(Status.ERROR);
                                response.setMessage("There are no elevators in " + startObject.getBuilding());
                            }
                        } else {
                            response.setStatus(Status.OK);
                            response.setEntity(SBSLFactoryService.create(startObject, destinationObject, mot));
                        }
                    } else {
                        //Building isn't wheelchair accessible
                        response.setStatus(Status.ERROR);
                        response.setMessage(startObject.getBuilding() + " is not wheelchair accessible");
                    }
                }
                break;

            case STAIRS:
                if (startObject.getBuilding().equals(destinationObject.getBuilding())) { //Same Building
                    if (startObject.getBuildingObject().hasStairs()) {
                        response.setStatus(Status.OK);
                    } else {
                        //No stairs, inform user app will use elevators
                        response.setStatus(Status.INFO);
                        response.setMessage("No stairs in " + startObject.getBuilding() + ". App switched to elevators for this building");
                        mot = ELEVATORS;
                    }
                    response.setEntity(SBDLFactoryService.create(startObject, destinationObject, mot));
                } else { //Diff buildings
                    if (startLevel != 0) {
                        if (startObject.getBuildingObject().hasStairs()) {
                            startED = true;
                            startEDMethod = STAIRS;
                            response.setStatus(Status.OK);
                        } else {
                            //No stairs, inform user app will use elevators for this building
                            startED = true;
                            startEDMethod = ELEVATORS;
                            response.setStatus(Status.INFO);
                            response.setMessage("No stairs in " + startObject.getBuilding() + ". App switched to elevators for this building");
                        }
                    } else {
                        startED = false;
                        startEDMethod = NULL;
                        response.setStatus(Status.OK);
                    }

                    if (destLevel != 0) {
                        if (destinationObject.getBuildingObject().hasStairs()) {
                            destED = true;
                            destEDMethod = STAIRS;
                        } else {
                            //No stairs, inform user app will use elevators for this building
                            destED = true;
                            destEDMethod = ELEVATORS;
                            response.setStatus(Status.INFO);
                            if (response.getMessage() == null) {
                                response.setMessage("No stairs in " + destinationObject.getBuilding() + ". App switched to elevators for this building");
                            } else {
                                response.setMessage("No stairs in both buildings, app switched to elevators in " + startObject.getBuilding() +
                                        " & " + destinationObject.getBuilding());
                            }
                        }
                    } else {
                        destED = false;
                        destEDMethod = NULL;
                    }

                    response.setEntity(DiffBuildingFactoryService.create(startObject, destinationObject, startEDMethod, destEDMethod, false, startED, destED));
                }
                break;

            case ELEVATORS:
                if (startObject.getBuilding().equals(destinationObject.getBuilding())) { //Same Building
                    if (startObject.getBuildingObject().hasElevators()) {
                        response.setStatus(Status.OK);
                    } else {
                        //No elevators
                        mot = STAIRS;
                        response.setStatus(Status.INFO);
                        response.setMessage("No elevators in " + startObject.getBuilding() + ". App switched to stairs for this building");
                    }
                    response.setEntity(SBDLFactoryService.create(startObject, destinationObject, mot));
                } else { //Diff Buildings
                    if (startLevel != 0) {
                        if (startObject.getBuildingObject().hasElevators()) {
                            startED = true;
                            startEDMethod = ELEVATORS;
                            response.setStatus(Status.OK);
                        } else {
                            //No elevators, inform user app will use stairs for this building
                            startED = true;
                            startEDMethod = STAIRS;
                            response.setStatus(Status.INFO);
                            response.setMessage("No elevators in " + startObject.getBuilding() + ". App switched to stairs for this building");
                        }
                    } else {
                        startED = false;
                        startEDMethod = NULL;
                        response.setStatus(Status.OK);
                    }

                    if (destLevel != 0) {
                        if (destinationObject.getBuildingObject().hasElevators()) {
                            destED = true;
                            destEDMethod = ELEVATORS;
                        } else {
                            //No elevators, inform user app will use stairs for this building
                            destED = true;
                            destEDMethod = STAIRS;
                            response.setStatus(Status.INFO);
                            if (response.getMessage() == null) {
                                response.setMessage("No elevators in " + destinationObject.getBuilding() + ". App switched to stairs for this building");
                            } else {
                                response.setMessage("No elevators in both buildings, app switched to stairs for " + startObject.getBuilding() +
                                        " & " + destinationObject.getBuilding());
                            }
                        }
                    } else {
                        destED = false;
                        destEDMethod = NULL;
                    }
                    response.setEntity(DiffBuildingFactoryService.create(startObject, destinationObject, startEDMethod, destEDMethod, false, startED, destED));
                }
                break;
        }
        return response;
    }
}
