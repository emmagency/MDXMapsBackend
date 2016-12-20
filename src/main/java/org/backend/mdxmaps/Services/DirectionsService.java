package org.backend.mdxmaps.Services;

import org.backend.mdxmaps.Model.RouteCalculation;
import org.backend.mdxmaps.Model.RoutingObjects;

import java.util.concurrent.Callable;

/**
 * Created by Emmanuel Keboh on 27/11/2016.
 */
public class DirectionsService implements Callable {

    private RoutingObjects start, end;
    private String mot;

    public DirectionsService(RoutingObjects start, RoutingObjects end, String mot) {
        this.start = start;
        this.end = end;
        this.mot = mot;
    }

    private RoutingObjects getStart() {
        return start;
    }


    private RoutingObjects getEnd() {
        return end;
    }


    private String getMot() {
        return mot;
    }

//    public SBSLResponse getRoute(){
//        ArrayList<ArrayList<LatLng>> list = new ArrayList<>();
//        ArrayList<LatLng> list1 = new ArrayList<>();
//        list1.add(new LatLng(1234.11, 9876.34));
//        list1.add(new LatLng(1234.11, 9876.34));
//        list.add(list1);
//
//        ArrayList<LatLng> list2 = new ArrayList<>();
//        list2.add(new LatLng(1234.11, 9876.34));
//        list2.add(new LatLng(1234.11, 9876.34));
//        list.add(list2);
//
//        ArrayList<LatLng> list3 = new ArrayList<>();
//        list3.add(new LatLng(1234.11, 9876.34));
//        list3.add(new LatLng(1234.11, 9876.34));
//        list.add(list3);
//
//        ArrayList<String> routeDescription = new ArrayList<>();
//        routeDescription.add("Hello");
//        routeDescription.add("Are");
//        routeDescription.add("You?");
//
//        return new SBSLResponse(list, routeDescription);
//    }

    @Override
    public Object call() throws Exception {

        //Perform logic to ensure user sends a valid mot to guard against console editing in browsers.

        ResponseService motCheck = new MOTCheckService(start, end, mot).doMOTCheck();

        if (motCheck.getStatus() == ResponseService.Status.ERROR) {
            //Return motCheck entity here
        }

        RouteCalculation operation = (RouteCalculation) motCheck.getEntity();

        ResponseService result = operation.getRoute();


        return null;
    }
}
