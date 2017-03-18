package org.backend.mdxmaps.Services.Algorithms;

import org.backend.mdxmaps.Model.RoutingObjects;

import java.util.ArrayList;

import static org.backend.mdxmaps.Services.Util.RoutingObjectsGetterUtilService.getConnectors;

/**
 * Created by Emmanuel Keboh on 27/11/2016.
 */
public class IndoorAlgorithm {

    ArrayList<ArrayList<String>> validRoutes = new ArrayList<>();

    //CAQ: Current Alpha Queue
    ArrayList<String> CAQ = new ArrayList<>();
    ArrayList<ArrayList<String>> visited = new ArrayList<>();
    ArrayList<String> allPrimeNames = new ArrayList<>();
    int destinationLane;
    ArrayList<RoutingObjects> allLevelConnectors;
    ArrayList<RoutingObjects> previouslyUsedConnectors;

    //ToDO Switch to static method, check for wheelchair users here
    public ArrayList<ArrayList<String>> sameLevelOp(ArrayList<RoutingObjects> primes, int destinationLane, String building, int actualLevel) {
        allLevelConnectors = getConnectors(building, actualLevel);
        previouslyUsedConnectors = new ArrayList<>();
        //Add all primes object names(String) to allPrimeNames array
        for (int i = 0; i < primes.size(); i++) {
            allPrimeNames.add(primes.get(i).getName());
        }

        this.destinationLane = destinationLane;
        //Main loop
        for (int i = 0; i < primes.size(); i++) {
            //Set's current alpha as first value
            if (CAQ.isEmpty()) {
                CAQ.add(0, primes.get(i).getName());
            } else {
                CAQ.set(0, primes.get(i).getName());
            }

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
                    //Done with prime
                    visited.clear();
                    CAQ.clear();
                    break;
            }
        }
        //Return all valid routes
        return validRoutes;
    }

    public String sameLevelOpWorkerMethod(String currentAlpha) {

        RoutingObjects currentAlphaObject = getConnectorObjectFromName(currentAlpha);
        //Check if current alpha connector isn't in the same lane as destination
        if (!currentAlphaObject.checkIfCurrentAlphaContainsDestination(destinationLane)) {
            //Get valid adjacents for current alpha
            ArrayList<RoutingObjects> allAdjacents = getAdjacents(currentAlphaObject);

            //There are indeed valid adjacents we can visit
            if (allAdjacents.size() != 0) {

                //If true, then add the picked adjacent to the outmost arraylist in 'visited' arraylist
                //This outmost arraylist is the 'visited' for current alpha
                if (CAQ.size() == visited.size()) {
                    visited.get(CAQ.size() - 1).add(allAdjacents.get(0).getName());
                } else {
                    //Create a new 'visited' Arraylist for current alpha and add the picked adjacent to it
                    visited.add(new ArrayList<String>());
                    visited.get(CAQ.size() - 1).add(allAdjacents.get(0).getName());
                }
                //Add picked adjacent to queue then return restart
                CAQ.add(allAdjacents.get(0).getName());

                return "restart";
            } else { //There are no valid adjacents to visit
                //If current alpha is a prime then we're done because there's no where else to go based on the valid adjacent rule
                if (allPrimeNames.contains(currentAlpha)) {
                    return "done";
                } else {
                    //It isn't a prime hence we can drop it and move one step backward
                    return "drop";
                }
            }
        } else {
            //Current alpha is on same lane as destination, add CAQ to valid routes arraylist
            validRoutes.add(new ArrayList<>(CAQ));
            //validRoutes.get(validRoutes.size()-1).addAll(CAQ);
            //If queue size is 1, then we are dealing with a prime so we are done, else we need to move one step backward
            return (CAQ.size() != 1 ? "drop" : "done");
        }

    }

    public ArrayList<RoutingObjects> getAdjacents(RoutingObjects connector) {

        //Get all adjacent connectors
        String[] adjacentNames = connector.getAdjacentConnectors();
        ArrayList<RoutingObjects> allAdjacentConnectors = new ArrayList<>();

        //sort through all connectors and extract all adjacent connectors objects
        for (String adjacentName : adjacentNames) {
            for (int i = 0; i < allLevelConnectors.size(); i++) {
                if (adjacentName.equals(allLevelConnectors.get(i).getName())) {
                    allAdjacentConnectors.add(allLevelConnectors.get(i));
                    break; //Found, exit immediately
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
            //Valid adjacent cannot be a prime or queue item
            if (allPrimeNames.contains(adjacentName) || CAQ.contains(adjacentName)) {
                //Loop through adjacentConnectors object array and remove non-complying object(s) that match adjacentName
                for (int i = 0; i < allAdjacentConnectors.size(); i++) {
                    if (allAdjacentConnectors.get(i).getName().equals(adjacentName)) {
                        allAdjacentConnectors.remove(i);
                        i -= 1;
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
        for (int i = 0; i < allLevelConnectors.size(); i++) {
            if (allLevelConnectors.get(i).getName().equals(name)) {
                previouslyUsedConnectors.add(allLevelConnectors.get(i));
                return allLevelConnectors.get(i);
            }
        }

        //FYI, It will never get here. One of the if statements above will always match.
        return new RoutingObjects();
    }
}
