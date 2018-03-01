package org.backend.mdxmaps.service.algorithms;

import org.backend.mdxmaps.model.Vertex;

import java.util.ArrayList;
import java.util.List;

import static org.backend.mdxmaps.service.util.UtilService.getClone;

/**
 * Created by Emmanuel Keboh on 01/03/2018.
 */
public class RoutingAlgorithm {

    public static ArrayList<ArrayList<Vertex>> getPaths(String start, String destination, List<Vertex> vertices, int numberOfPaths) {

        List<Vertex> shortestPath = AStarAlgorithm.getAStarShortestPath(start, destination, getClone(vertices));

        if (shortestPath != null) {
            return KPathAlgorithm.calculateKSPs(new ArrayList<>(shortestPath), numberOfPaths, destination, vertices);
        } else {
            return null;
        }

    }


}
