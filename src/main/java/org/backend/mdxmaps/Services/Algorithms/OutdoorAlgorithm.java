package org.backend.mdxmaps.Services.Algorithms;

import org.backend.mdxmaps.Model.LatLng;
import org.backend.mdxmaps.Model.RoutingObjects;

import java.util.ArrayList;

/**
 * Created by Emmanuel Keboh on 27/11/2016.
 */
public class OutdoorAlgorithm {

    ArrayList<ArrayList<String>> validRoutes = new ArrayList<>();

    //CAQ: Current Alpha Queue
    ArrayList<String> CAQ = new ArrayList<>();
    ArrayList<ArrayList<String>> visited = new ArrayList<>();
    String startConnector;
    String destinationConnector;
    LatLng startPoint;
    LatLng destinationPoint;
    boolean wheelchair;
    ArrayList<RoutingObjects> allOutdoorConnectors;
    ArrayList<RoutingObjects> previouslyUsedConnectors;
    int acceptedDistanceFromLine; //in meters

    public ArrayList<ArrayList<String>> sameLevelOp(String startConnector, String destinationConnector, boolean wheelchair) {
        //Log.d("Gency", "Start: "+startConnector+" to "+destinationConnector);
        allOutdoorConnectors = new RoutingObjects().getConnectors("Outside", 0);
        previouslyUsedConnectors = new ArrayList<>();
        this.wheelchair = wheelchair;
        this.startConnector = startConnector;
        this.destinationConnector = destinationConnector;

        startPoint = getConnectorObjectFromName(startConnector).getLocation();
        destinationPoint = getConnectorObjectFromName(destinationConnector).getLocation();

        CAQ.add(startConnector);
//        int restart = 0;
//        int drop = 0;

        acceptedDistanceFromLine =
                calculateDistance(startPoint.latitude, startPoint.longitude, destinationPoint.latitude, destinationPoint.longitude) < 60 ?
                        20 : 45;

        //Main loop
        for (int i = 0; i < 1; i++) {

            //Pass last item in queue
            String result = sameLevelOpWorkerMethod(CAQ.get(CAQ.size() - 1));

            switch (result) {
                case "restart":
                    i -= 1;
//                    Log.d("Gency", "Restart: "+String.valueOf(restart++));
                    break;
                case "drop":
                    //Current alpha object has a 'visited' arrayList, remove it
                    if (CAQ.size() == visited.size()) {
                        visited.remove(CAQ.size() - 1);
                    }
                    //Remove current alpha
                    CAQ.remove(CAQ.size() - 1);
                    i -= 1;
//                    Log.d("Gency", "Drop: "+String.valueOf(drop++));
                    break;
                default:
                    //Done with iteration
                    visited.clear();
                    if (validRoutes.size() == 0) {
                        if (acceptedDistanceFromLine <= 150) {
                            acceptedDistanceFromLine += 10;
                            i -= 1;
//                            Log.d("Gency", "Restarting: "+String.valueOf(acceptedDistanceFromLine));
                        }
                    }
                    //                   Log.d("Gency", "Done: "+String.valueOf(done++));
                    break;
            }

        }

        //Return all valid routes
        CAQ.clear();
//        Log.d("Gency", "Returning now");
        return validRoutes;
    }

    public String sameLevelOpWorkerMethod(String currentAlpha) {

        //Check if current alpha connector isn't destination connector
        if (!currentAlpha.equals(destinationConnector)) {

            RoutingObjects currentAlphaObject = getConnectorObjectFromName(currentAlpha);

            //Get valid adjacents for current alpha
            ArrayList<RoutingObjects> allAdjacents = getAdjacents(currentAlphaObject);

            //There are indeed valid adjacents we can visit
            if (allAdjacents.size() != 0) {

                //Get closest adjacent to destination (If more than 1)
                if (allAdjacents.size() > 1) {
                    sortAdjacentsByDistanceToDestination(allAdjacents);
                }

                for (int k = 0; k < allAdjacents.size(); k++) {
                    if (calculateDistanceFromLine(allAdjacents.get(k).getLocation(), startPoint, destinationPoint) > acceptedDistanceFromLine) {
                        if (CAQ.size() == visited.size()) {
                            visited.get(CAQ.size() - 1).add(allAdjacents.get(k).getName());
                        } else {
                            visited.add(new ArrayList<String>());
                            visited.get(CAQ.size() - 1).add(allAdjacents.get(k).getName());
                        }
                    } else {
                        if (CAQ.size() == visited.size()) {
                            visited.get(CAQ.size() - 1).add(allAdjacents.get(k).getName());
                        } else {
                            visited.add(new ArrayList<String>());
                            visited.get(CAQ.size() - 1).add(allAdjacents.get(k).getName());
                        }
                        CAQ.add(allAdjacents.get(k).getName());
                        return "restart";
                    }
                }

                //If it gets here, that means all the adjacents didn't meet the acceptedDistanceFromLine condition
                if (startConnector.equals(currentAlpha)) {
                    return "done";
                } else {
                    return "drop";
                }

            } else { //There are no valid adjacents to visit
                //If current alpha is a prime then we're done because there's no where else to go based on the valid adjacent rule
                if (startConnector.equals(currentAlpha)) {
                    return "done";
                } else {
                    //It isn't a prime hence we can drop it and move one step backward
                    return "drop";
                }
            }
        } else {
            //Current alpha is our destination
            validRoutes.add(new ArrayList<>(CAQ));
            return "drop";
        }
    }

    public ArrayList<RoutingObjects> getAdjacents(RoutingObjects connector) {

        //Get all adjacent connectors (String values)
        String[] adjacentNames = connector.getAdjacentConnectors();

        ArrayList<RoutingObjects> allAdjacentConnectors = new ArrayList<>();

        //sort through all connectors and extract all adjacent connectors objects
        for (String adjacentName : adjacentNames) {
            for (int i = 0; i < allOutdoorConnectors.size(); i++) {
                if (adjacentName.equals(allOutdoorConnectors.get(i).getName())) {
                    allAdjacentConnectors.add(allOutdoorConnectors.get(i));
                    break; //Found, exit immediately
                }
            }

        }

        if (wheelchair) {
            for (int i = 0; i < allAdjacentConnectors.size(); i++) {
                if (allAdjacentConnectors.get(i).getIsWheelChairAccessible().equals("N")) {
                    allAdjacentConnectors.remove(i);
                    i--;
                }
            }
        }

        //If current Alpha has previously been used to visit other connectors. Check its visited list to see if any reported
        //adjacents are there. Remove if there is.
        if (CAQ.size() == visited.size()) {
            for (String adjacentName : adjacentNames) {
                if (visited.get(visited.size() - 1).contains(adjacentName)) {
                    for (int i = 0; i < allAdjacentConnectors.size(); i++) {
                        if (allAdjacentConnectors.get(i).getName().equals(adjacentName)) {
                            allAdjacentConnectors.remove(i);
                            break;
                        }
                    }
                }
            }
        }

        //For each string in connector adjacents string attribute
        for (String adjacentName : adjacentNames) {
            //Valid adjacent cannot be a queue item
            if (CAQ.contains(adjacentName)) {
                //Loop through adjacentConnectors object array and remove non-complying object(s) that match adjacentName
                for (int i = 0; i < allAdjacentConnectors.size(); i++) {
                    if (allAdjacentConnectors.get(i).getName().equals(adjacentName)) {
                        allAdjacentConnectors.remove(i);
                        break;
                    }
                }
            }
        }


        return allAdjacentConnectors;

    }

    private RoutingObjects getConnectorObjectFromName(String name) {
        //First check smaller data set. Return if found
        for (int i = 0; i < previouslyUsedConnectors.size(); i++) {
            if (previouslyUsedConnectors.get(i).getName().equals(name)) {
                return previouslyUsedConnectors.get(i);
            }
        }

        //Not found in smaller list. Check larger data set. Add to smaller data set when found and return
        for (int i = 0; i < allOutdoorConnectors.size(); i++) {
            if (allOutdoorConnectors.get(i).getName().equals(name)) {
                previouslyUsedConnectors.add(allOutdoorConnectors.get(i));
                return allOutdoorConnectors.get(i);
            }
        }

        //FYI, It will never get here.
        //One of the if statement will always match but the compiler doesn't know that.
        return new RoutingObjects();
    }

    //This performs sorting by referencing. i.e the passed arg arrayList is edited, caller method will have the changes
    private void sortAdjacentsByDistanceToDestination(ArrayList<RoutingObjects> allAdjacents) {
        ArrayList<Double> allDistances = new ArrayList<>();

        for (int i = 0; i < allAdjacents.size(); i++) {
            allDistances.add(calculateDistance(allAdjacents.get(i).getLocation().latitude, allAdjacents.get(i).getLocation().longitude,
                    destinationPoint.latitude, destinationPoint.longitude));
        }

        //Perform sorting
        Double tempDistance;
        RoutingObjects tempObject;
        for (int i = 0; i < allDistances.size(); i++) {
            for (int j = i + 1; j < allDistances.size(); j++) {
                if (allDistances.get(i) > allDistances.get(j)) {
                    //First operate on distance array list
                    tempDistance = allDistances.get(j);
                    allDistances.set(j, allDistances.get(i));
                    allDistances.set(i, tempDistance);
                    //Then on allAdjacents arrayList to balance the positioning
                    tempObject = allAdjacents.get(j);
                    allAdjacents.set(j, allAdjacents.get(i));
                    allAdjacents.set(i, tempObject);
                }
            }
        }
    }

    private double calculateDistance(double startLat, double startLng,
                                     double endLat, double endLng) {

        double latDistance = Math.toRadians(startLat - endLat);
        double lngDistance = Math.toRadians(startLng - endLng);

        double a = Math.sin(latDistance / 2) * Math.sin(latDistance / 2)
                + Math.cos(Math.toRadians(startLat)) * Math.cos(Math.toRadians(endLat))
                * Math.sin(lngDistance / 2) * Math.sin(lngDistance / 2);

        double c = 2 * Math.atan2(Math.sqrt(a), Math.sqrt(1 - a));
        //AVERAGE_RADIUS_OF_EARTH = 6371km
        return (double) (Math.round(6371000 * c));

    }

    private double calculateDistanceFromLine(LatLng point, LatLng startPoint, LatLng destinationPoint) {

        double d0 = calculateDistance(point.latitude, point.longitude, startPoint.latitude, startPoint.longitude);
        double d1 = calculateDistance(startPoint.latitude, startPoint.longitude, destinationPoint.latitude, destinationPoint.longitude);
        double d3 = calculateDistance(point.latitude, point.longitude, destinationPoint.latitude, destinationPoint.longitude);

        double halfP = (d0 + d1 + d3) * 0.5;

        double area = Math.sqrt(halfP * (halfP - d0) * (halfP - d1) * (halfP - d3));
        return (2 * area) / calculateDistance(startPoint.latitude, startPoint.longitude,
                destinationPoint.latitude, destinationPoint.longitude);
    }


}
