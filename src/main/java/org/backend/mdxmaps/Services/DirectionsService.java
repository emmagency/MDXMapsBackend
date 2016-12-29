package org.backend.mdxmaps.Services;

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
    private String mot;

    public DirectionsService(String start, String end, String mot) {
        this.start = start;
        this.end = end;
        this.mot = mot;
    }

    private String getStart() {
        return start;
    }


    private String getEnd() {
        return end;
    }


    private String getMot() {
        return mot;
    }

    @Override
    public Object call() throws Exception {

        RoutingObjects startRoom = getRoomObjectFromName(start);
        RoutingObjects destinationRoom = getRoomObjectFromName(end);

        if (startRoom == null || destinationRoom == null) {
            return ResponseService.create(ERROR, startRoom == null && destinationRoom == null ?
                    "Couldn't find rooms" + start + " & " + end + ". Please check your input and try again." :
                    startRoom == null ? "Couldn't find room" + start + ". Please check your input and try again."
                            : "Couldn't find room" + end + ". Please check your input and try again.");
        }

        //Logic to ensure user sends a valid mot to guard against console editing in browsers.
        if (!mot.equals("disabled")) {
            if (!MOTValidator.validate(startRoom, destinationRoom)) {
                mot = "null";
            }
        }

        //Logic to determine type of operation
        ResponseService motCheck = new MOTCheckService(startRoom, destinationRoom, mot).doMOTCheck();

        if (motCheck.getStatus() == ERROR) {
            return ResponseService.create(ERROR, motCheck.getMessage());
        }

        //Everything checked out, let's do some calculations!
        ResponseService result = ((RouteCalculation) motCheck.getEntity()).getRoute();


        return result.getEntity();
    }
}
