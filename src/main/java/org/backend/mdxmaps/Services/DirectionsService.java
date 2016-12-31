package org.backend.mdxmaps.Services;

import org.backend.mdxmaps.Model.MOT;
import org.backend.mdxmaps.Model.RouteCalculation;
import org.backend.mdxmaps.Model.RoutingObjects;

import java.util.concurrent.Callable;

import static org.backend.mdxmaps.Services.ResponseService.Status.ERROR;
import static org.backend.mdxmaps.Services.RoutingObjectsGetterUtilService.getRoomObjectFromName;

/**
 * Created by Emmanuel Keboh on 27/11/2016.
 */
public class DirectionsService implements Callable {

    private String start, end;
    private String passedMot;

    public DirectionsService(String start, String end, String passedMot) {
        this.start = start;
        this.end = end;
        this.passedMot = passedMot;
    }

    private String getStart() {
        return start;
    }


    private String getEnd() {
        return end;
    }


    private String getPassedMot() {
        return passedMot;
    }

    @Override
    public Object call() throws Exception {

        RoutingObjects startRoom = getRoomObjectFromName(start);
        RoutingObjects destinationRoom = getRoomObjectFromName(end);
        MOT motEnum;

        if (startRoom == null || destinationRoom == null) {
            return ResponseService.create(ERROR, startRoom == null && destinationRoom == null ?
                    "Couldn't find rooms" + start + " & " + end + ". Please check your input and try again." :
                    startRoom == null ? "Couldn't find room" + start + ". Please check your input and try again."
                            : "Couldn't find room" + end + ". Please check your input and try again.");
        }

        //User doesn't pass preferred mot or passed mot is incorrect
        if (passedMot == null || !MOTValidator.validatePassedMOTTypeExists(passedMot.toUpperCase())) {
            motEnum = MOTValidator.autoResolve(startRoom, destinationRoom);
        } else {
            motEnum = MOT.valueOf(passedMot.toUpperCase());
            if (!motEnum.equals(MOT.DISABLED)) {
                if (!MOTValidator.validate(startRoom, destinationRoom)) {
                    motEnum = MOT.NULL;
                }
            }
        }

        //Logic to determine type of operation
        ResponseService motCheck = new MOTCheckService(startRoom, destinationRoom, motEnum).doMOTCheck();

        if (motCheck.getStatus() == ERROR) {
            return ResponseService.create(ERROR, motCheck.getMessage());
        }

        //Everything checked out, let's do some calculations!
        ResponseService result = ((RouteCalculation) motCheck.getEntity()).getRoute();


        return result.getEntity();
    }
}
