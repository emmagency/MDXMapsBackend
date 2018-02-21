package org.backend.mdxmaps.service;

import org.backend.mdxmaps.model.Routing;
import org.backend.mdxmaps.model.enums.MOT;
import org.backend.mdxmaps.service.ResponseService.Status;
import org.backend.mdxmaps.service.factoryServices.DifferentBuildingFactoryService;
import org.backend.mdxmaps.service.factoryServices.SBDLFactoryService;
import org.backend.mdxmaps.service.factoryServices.SBSLFactoryService;

import static org.backend.mdxmaps.model.enums.MOT.ELEVATORS;
import static org.backend.mdxmaps.model.enums.MOT.NULL;
import static org.backend.mdxmaps.model.enums.MOT.STAIRS;

/**
 * Created by Emmanuel Keboh on 14/12/2016.
 */
public class ResolveOperationTypeService {

    private String destRoom;
    private MOT mot, startEDMethod, destEDMethod;
    private int startLevel, destLevel;
    private boolean startED, destED = false;
    private Routing start, destination;

    public ResolveOperationTypeService(Routing start, Routing destination, MOT mot) {
        this.startLevel = start.getLevel();
        this.destLevel = destination.getLevel();
        this.destRoom = destination.getName();
        this.mot = mot;
        this.start = start;
        this.destination = destination;
    }

    @SuppressWarnings("Duplicates")
    public ResponseService resolveOPType() {
        ResponseService response = new ResponseService();

        switch (mot) {
            case NULL:
                //It's either sbsl or diff buildings ground floors
                response.setStatus(ResponseService.Status.OK);
                if (start.getBuilding().equals(destination.getBuilding())) {
                    response.setEntity(SBSLFactoryService.create(start, destination, NULL));
                } else {
                    response.setEntity(DifferentBuildingFactoryService.create(start, destination, NULL, NULL, false, false, false));
                }
                break;

            case DISABLED:
                boolean startCheck;
                boolean destCheck;

                if (!start.getBuilding().equals(destination.getBuilding())) { //Different Buildings
                    if (destination.getBuildingObject().isWheelChairAccessible() &&
                            start.getBuildingObject().isWheelChairAccessible()) {
                        if (startLevel != 0) {
                            if (start.getBuildingObject().hasElevators()) {
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
                            if (destination.getBuildingObject().hasElevators()) {
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
                            response.setMessage("There are no elevators in " + start.getBuilding() + " and " + destination.getBuilding());
//                            throw new ResolveOperationTypeException(String.format("There are no elevators in %s and %s", start.getBuilding(), destination.getBuilding()));
                        } else if (startCheck && !destCheck) {
                            response.setStatus(Status.ERROR);
                            response.setMessage("There are no elevators in " + destination.getBuilding() + " to get to " + destRoom);
//                            throw new ResolveOperationTypeException(String.format("There are no elevators in %s to get to %s", destination.getBuilding(), destRoom));
                        } else if (!startCheck) {
                            response.setStatus(Status.ERROR);
                            response.setMessage("There are no elevators in " + start.getBuilding());
//                            throw new ResolveOperationTypeException(String.format("There are no elevators in %s", start.getBuilding()));
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
                            response.setEntity(DifferentBuildingFactoryService.create(start, destination, startEDMethod, destEDMethod, true, startED, destED));
                        }
                    } else {
                        if (!destination.getBuildingObject().isWheelChairAccessible() &&
                                !start.getBuildingObject().isWheelChairAccessible()) {
                            response.setStatus(Status.ERROR);
                            response.setMessage("Both " + start.getBuilding() + " & " + destination.getBuilding() + " are not wheelchair accessible");
//                            throw new ResolveOperationTypeException(String.format("Both %s & %s are not wheelchair accessible", start.getBuilding(), destination.getBuilding()));
                        } else if (!start.getBuildingObject().isWheelChairAccessible()) {
                            response.setStatus(Status.ERROR);
                            response.setMessage(start.getBuilding() + " is not wheelchair accessible");
//                            throw new ResolveOperationTypeException(String.format("%s is not wheelchair accessible", start.getBuilding()));
                        } else {
                            response.setStatus(Status.ERROR);
                            response.setMessage(destination.getBuilding() + " is not wheelchair accessible");
//                            throw new ResolveOperationTypeException(String.format("%s is not wheelchair accessible", destination.getBuilding()));
                        }
                    }
                } else { //Same Building.
                    if (start.getBuildingObject().isWheelChairAccessible()) {
                        if (startLevel != 0 || destLevel != 0) {
                            if (start.getBuildingObject().hasElevators()) {
                                response.setStatus(Status.OK);
                                response.setEntity(SBDLFactoryService.create(start, destination, mot));
                            } else {
                                //Building doesn't have elevators
                                response.setStatus(Status.ERROR);
                                response.setMessage("There are no elevators in " + start.getBuilding());
//                                throw new ResolveOperationTypeException(String.format("There are no elevators in %s", start.getBuilding()));
                            }
                        } else {
                            response.setStatus(Status.OK);
                            response.setEntity(SBSLFactoryService.create(start, destination, mot));
                        }
                    } else {
                        //Building isn't wheelchair accessible
                        response.setStatus(Status.ERROR);
                        response.setMessage(start.getBuilding() + " is not wheelchair accessible");
//                        throw new ResolveOperationTypeException(String.format("%s is not wheelchair accessible", start.getBuilding()));
                    }
                }
                break;

            case STAIRS:
                if (start.getBuilding().equals(destination.getBuilding())) { //Same Building
                    if (start.getBuildingObject().hasStairs()) {
                        response.setStatus(Status.OK);
                    } else {
                        //No stairs, inform user app will use elevators
                        response.setStatus(Status.INFO);
                        response.setMessage("No stairs in " + start.getBuilding() + ". App switched to elevators for this building");
                        mot = ELEVATORS;
                    }
                    response.setEntity(SBDLFactoryService.create(start, destination, mot));
                } else { //Diff buildings
                    if (startLevel != 0) {
                        if (start.getBuildingObject().hasStairs()) {
                            startED = true;
                            startEDMethod = STAIRS;
                            response.setStatus(Status.OK);
                        } else {
                            //No stairs, inform user app will use elevators for this building
                            startED = true;
                            startEDMethod = ELEVATORS;
                            response.setStatus(Status.INFO);
                            response.setMessage("No stairs in " + start.getBuilding() + ". App switched to elevators for this building");
                        }
                    } else {
                        startED = false;
                        startEDMethod = NULL;
                        response.setStatus(Status.OK);
                    }

                    if (destLevel != 0) {
                        if (destination.getBuildingObject().hasStairs()) {
                            destED = true;
                            destEDMethod = STAIRS;
                        } else {
                            //No stairs, inform user app will use elevators for this building
                            destED = true;
                            destEDMethod = ELEVATORS;
                            response.setStatus(Status.INFO);
                            if (response.getMessage() == null) {
                                response.setMessage("No stairs in " + destination.getBuilding() + ". App switched to elevators for this building");
                            } else {
                                response.setMessage("No stairs in both buildings, app switched to elevators in " + start.getBuilding() +
                                        " & " + destination.getBuilding());
                            }
                        }
                    } else {
                        destED = false;
                        destEDMethod = NULL;
                    }

                    response.setEntity(DifferentBuildingFactoryService.create(start, destination, startEDMethod, destEDMethod, false, startED, destED));
                }
                break;

            case ELEVATORS:
                if (start.getBuilding().equals(destination.getBuilding())) { //Same Building
                    if (start.getBuildingObject().hasElevators()) {
                        response.setStatus(Status.OK);
                    } else {
                        //No elevators
                        mot = STAIRS;
                        response.setStatus(Status.INFO);
                        response.setMessage("No elevators in " + start.getBuilding() + ". App switched to stairs for this building");
                    }
                    response.setEntity(SBDLFactoryService.create(start, destination, mot));
                } else { //Diff Buildings
                    if (startLevel != 0) {
                        if (start.getBuildingObject().hasElevators()) {
                            startED = true;
                            startEDMethod = ELEVATORS;
                            response.setStatus(Status.OK);
                        } else {
                            //No elevators, inform user app will use stairs for this building
                            startED = true;
                            startEDMethod = STAIRS;
                            response.setStatus(Status.INFO);
                            response.setMessage("No elevators in " + start.getBuilding() + ". App switched to stairs for this building");
                        }
                    } else {
                        startED = false;
                        startEDMethod = NULL;
                        response.setStatus(Status.OK);
                    }

                    if (destLevel != 0) {
                        if (destination.getBuildingObject().hasElevators()) {
                            destED = true;
                            destEDMethod = ELEVATORS;
                        } else {
                            //No elevators, inform user app will use stairs for this building
                            destED = true;
                            destEDMethod = STAIRS;
                            response.setStatus(Status.INFO);
                            if (response.getMessage() == null) {
                                response.setMessage("No elevators in " + destination.getBuilding() + ". App switched to stairs for this building");
                            } else {
                                response.setMessage("No elevators in both buildings, app switched to stairs for " + start.getBuilding() +
                                        " & " + destination.getBuilding());
                            }
                        }
                    } else {
                        destED = false;
                        destEDMethod = NULL;
                    }
                    response.setEntity(DifferentBuildingFactoryService.create(start, destination, startEDMethod, destEDMethod, false, startED, destED));
                }
                break;
        }
        return response;
    }
}
