package org.backend.mdxmaps.Services;

import org.backend.mdxmaps.Model.Enums.MOT;
import org.backend.mdxmaps.Model.MainResponseObject;
import org.backend.mdxmaps.Model.RouteCalculation;
import org.backend.mdxmaps.Model.RoutingObjects;

import java.util.concurrent.Callable;

import static org.backend.mdxmaps.Services.MOTValidator.autoResolve;
import static org.backend.mdxmaps.Services.MOTValidator.validate;
import static org.backend.mdxmaps.Services.MOTValidator.validateReceivedMOTTypeExists;
import static org.backend.mdxmaps.Services.ResponseService.Status.ERROR;
import static org.backend.mdxmaps.Services.ResponseService.Status.INFO;
import static org.backend.mdxmaps.Services.RoutingObjectsGetterUtilService.getRoomObjectFromName;

/**
 * Created by Emmanuel Keboh on 27/11/2016.
 */
public class DirectionsService implements Callable {

    private String start, end;
    private String receivedMot;

    public DirectionsService(String start, String end, String receivedMot) {
        this.start = start;
        this.end = end;
        this.receivedMot = receivedMot;
    }

    private String getStart() {
        return start;
    }


    private String getEnd() {
        return end;
    }


    private String getReceivedMot() {
        return receivedMot;
    }

    @Override
    public Object call() throws Exception {

        RoutingObjects startRoom = getRoomObjectFromName(start.toUpperCase());
        RoutingObjects destinationRoom = getRoomObjectFromName(end.toUpperCase());
        MOT motEnum;

        if (startRoom == null || destinationRoom == null) {
            return ResponseService.create(ERROR, startRoom == null && destinationRoom == null ?
                    "Couldn't find rooms" + start + " & " + end + ". Please check your input and try again." :
                    startRoom == null ? "Couldn't find room '" + start + "'. Please check your input and try again."
                            : "Couldn't find room '" + end + "'. Please check your input and try again.");
        }

        if (receivedMot == null || !validateReceivedMOTTypeExists(receivedMot)) {
            //User didn't provide preferred mot or passed mot is incorrect
            motEnum = autoResolve(startRoom, destinationRoom);
        } else {
            //User provided a correct MOT, let's just confirm if it's needed for this operation
            motEnum = MOT.valueOf(receivedMot.toUpperCase());
            if (!motEnum.equals(MOT.DISABLED)) {
                if (!validate(startRoom, destinationRoom)) {
                    motEnum = MOT.NULL;
                }
            }
        }

        //Logic to determine type of operation
        ResponseService resolveOPType =
                new ResolveOperationTypeService(startRoom, destinationRoom, motEnum).resolveOPType();

        if (resolveOPType.getStatus() == ERROR) {
            return resolveOPType;
        }

        //Everything checked out, let's do some calculations!
        ResponseService result = ((RouteCalculation) resolveOPType.getEntity()).getRoute();

        if (resolveOPType.getStatus() == INFO && result.getEntity() != null) {
            ((MainResponseObject) result.getEntity()).setStatus(INFO);
            ((MainResponseObject) result.getEntity()).setMessage(resolveOPType.getMessage());
        }

        return result.getEntity() != null ? result.getEntity() : result;
    }
}
