package org.backend.mdxmaps.services;

import org.backend.mdxmaps.model.OperationFactory;
import org.backend.mdxmaps.model.Routing;
import org.backend.mdxmaps.model.enums.MOT;
import org.backend.mdxmaps.model.responseObjects.directions.MainDirectionsResponse;

import java.util.concurrent.Callable;

import static org.backend.mdxmaps.services.MOTValidator.autoResolve;
import static org.backend.mdxmaps.services.MOTValidator.isReceivedMOTValid;
import static org.backend.mdxmaps.services.MOTValidator.validate;
import static org.backend.mdxmaps.services.ResponseService.Status.ERROR;
import static org.backend.mdxmaps.services.ResponseService.Status.INFO;
import static org.backend.mdxmaps.services.util.RoutingObjectsGetterUtilService.getRoomObjectFromName;

/**
 * Created by Emmanuel Keboh on 27/11/2016.
 */
public class DirectionsService implements Callable<Object> {

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

        Routing start = getRoomObjectFromName(this.start.toUpperCase());
        Routing destination = getRoomObjectFromName(end.toUpperCase());

        if (start == null || destination == null) {
            return ResponseService.create(ERROR, start == null && destination == null ?
                    "Couldn't find rooms " + this.start + " & " + end + ". Please check your input and try again." :
                    start == null ? "Couldn't find room '" + this.start + "'. Please check your input and try again."
                            : "Couldn't find room '" + end + "'. Please check your input and try again.");
        }

        if (start.getName().equals(destination.getName())) {
            return ResponseService.create(ERROR, "Start and Destination appears to be the same. " +
                    "Apologies, I don't go around in circles. Check your input and try again.");
        }

        MOT motEnum;
        if (receivedMot == null || receivedMot.equals("null") || !isReceivedMOTValid(receivedMot)) {
            //User didn't provide preferred mot or passed mot is incorrect
            motEnum = autoResolve(start, destination);
        } else {
            //User provided a correct MOT, let's just confirm if it's needed for this operation
            motEnum = MOT.valueOf(receivedMot.toUpperCase());
            if (!motEnum.equals(MOT.DISABLED)) {
                if (!validate(start, destination)) {
                    motEnum = MOT.NULL;
                }
            }
        }

        //Logic to determine type of operation
        ResponseService oPType =
                new ResolveOperationTypeService(start, destination, motEnum).resolveOPType();

        if (oPType.getStatus() == ERROR) {
            return oPType;
        }

        //Everything checked out, let's do some calculations!
        ResponseService result = ((OperationFactory) oPType.getEntity()).getRoute();

        if (oPType.getStatus() == INFO && result.getEntity() != null) {
            ((MainDirectionsResponse) result.getEntity()).setStatus(INFO);
            ((MainDirectionsResponse) result.getEntity()).setMessage(oPType.getMessage());
        }

        return result.getEntity() != null ? result.getEntity() : result;
    }
}
