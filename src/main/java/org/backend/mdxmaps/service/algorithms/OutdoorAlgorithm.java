package org.backend.mdxmaps.service.algorithms;

import org.backend.mdxmaps.model.LatLng;
import org.backend.mdxmaps.model.Routing;

import java.util.ArrayList;

import static org.backend.mdxmaps.model.Routing.getOutsideConnectors;
import static org.backend.mdxmaps.service.util.UtilService.calculateDistance;

/**
 * Created by Emmanuel Keboh on 27/11/2016.
 */
public class OutdoorAlgorithm {

    private ArrayList<ArrayList<String>> validRoutes = new ArrayList<>();

    //CAQ: Current Alpha Queue
    private ArrayList<String> CAQ = new ArrayList<>();
    private ArrayList<ArrayList<String>> visited = new ArrayList<>();
    private String startConnector;
    private String destinationConnector;
    private LatLng startPoint;
    private LatLng destinationPoint;
    private boolean wheelchair;
    private ArrayList<Routing> allOutdoorConnectors;
    private ArrayList<Routing> previouslyUsedConnectors;
    private int acceptedDistanceFromLine; //in meters

    //ToDO Switch to static method and rename
    public ArrayList<ArrayList<String>> sameLevelOp(String startConnector, String destinationConnector, boolean wheelchair) {
        allOutdoorConnectors = getOutsideConnectors();
        previouslyUsedConnectors = new ArrayList<>();
        this.wheelchair = wheelchair;
        this.startConnector = startConnector;
        this.destinationConnector = destinationConnector;

        startPoint = getConnectorObjectFromName(startConnector).getLatLng();
        destinationPoint = getConnectorObjectFromName(destinationConnector).getLatLng();

        CAQ.add(startConnector);

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
                    break;
                case "drop":
                    //Current alpha object has a 'visited' arrayList, remove it
                    if (CAQ.size() == visited.size()) {
                        visited.remove(CAQ.size() - 1);
                    }
                    //Remove current alpha
                    CAQ.remove(CAQ.size() - 1);
                    i -= 1;
                    break;
                default:
                    //Done with iteration
                    visited.clear();
                    if (validRoutes.size() == 0) {
                        if (acceptedDistanceFromLine <= 150) {
                            acceptedDistanceFromLine += 10;
                            i -= 1;
                        }
                    }
                    break;
            }

        }
        CAQ.clear();
        return validRoutes;
    }

    public String sameLevelOpWorkerMethod(String currentAlpha) {

        //Check if current alpha connector isn't destination connector
        if (!currentAlpha.equals(destinationConnector)) {

            Routing currentAlphaObject = getConnectorObjectFromName(currentAlpha);

            //Get valid adjacents for current alpha
            ArrayList<Routing> allAdjacents = getAdjacents(currentAlphaObject);

            //There are indeed valid adjacents we can visit
            if (allAdjacents.size() != 0) {

                //Get closest adjacent to destination (If more than 1)
                if (allAdjacents.size() > 1) {
                    sortAdjacentsByDistanceToDestination(allAdjacents);
                }

                for (int k = 0; k < allAdjacents.size(); k++) {
                    if (calculateDistanceFromLine(allAdjacents.get(k).getLatLng(), startPoint, destinationPoint) > acceptedDistanceFromLine) {
                        if (CAQ.size() == visited.size()) {
                            visited.get(CAQ.size() - 1).add(allAdjacents.get(k).getName());
                        } else {
                            visited.add(new ArrayList<>());
                            visited.get(CAQ.size() - 1).add(allAdjacents.get(k).getName());
                        }
                    } else {
                        if (CAQ.size() == visited.size()) {
                            visited.get(CAQ.size() - 1).add(allAdjacents.get(k).getName());
                        } else {
                            visited.add(new ArrayList<>());
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

    public ArrayList<Routing> getAdjacents(Routing connector) {

        //Get all adjacent connectors (String values)
        String[] adjacentNames = connector.getAdjacentConnectors();

        ArrayList<Routing> allAdjacentConnectors = new ArrayList<>();

        //sort through all connectors and extract all adjacent connectors objects
        for (String adjacentName : adjacentNames) {
            allOutdoorConnectors.stream()
                    .filter(outdoorConnector -> adjacentName.equals(outdoorConnector.getName()))
                    .findFirst()
                    .ifPresent(allAdjacentConnectors::add);
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

    private Routing getConnectorObjectFromName(String name) {
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
        return new Routing();
    }

    //N.B This performs sorting by referencing.
    private void sortAdjacentsByDistanceToDestination(ArrayList<Routing> allAdjacents) {
        ArrayList<Double> allDistances = new ArrayList<>();

        for (int i = 0; i < allAdjacents.size(); i++) {
            allDistances.add(calculateDistance(allAdjacents.get(i).getLatLng().latitude, allAdjacents.get(i).getLatLng().longitude,
                    destinationPoint.latitude, destinationPoint.longitude));
        }

        //Perform sorting
        Double tempDistance;
        Routing tempObject;
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
