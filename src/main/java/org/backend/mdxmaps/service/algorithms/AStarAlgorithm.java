package org.backend.mdxmaps.service.algorithms;

import org.backend.mdxmaps.model.Vertex;
import org.backend.mdxmaps.service.util.UtilService;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.LinkedList;
import java.util.List;
import java.util.PriorityQueue;

/**
 * Created by Emmanuel Keboh on 25/02/2018.
 */
public class AStarAlgorithm {

    public static LinkedList<Vertex> calculateOutsideRoute(String start, String goal, boolean disabled, List<Vertex> vertices) {
        PriorityQueue<Vertex> queue = new PriorityQueue<>(11, Comparator.comparingDouble(Vertex::getF));
        List<Vertex> closedList = new ArrayList<>();

        Vertex startVertex = getVertexFromName(start, vertices);
        Vertex goalVertex = getVertexFromName(goal, vertices);

        startVertex.setFrom(null);
        startVertex.setG(0);
        startVertex.setH(UtilService.calculateDistance(startVertex, goalVertex));

        queue.offer(startVertex);

        Vertex current;

        while (!queue.isEmpty()) {
            current = queue.poll();
            if (current.equals(goalVertex)) {
                return reconstructPath(current, closedList);
            }

            closedList.add(current);

            List<Vertex> neighbors = getNeighbours(current, vertices);
            if (disabled) {
                removeNonDisabledVertices(neighbors);
            }

            for (Vertex neighbor : neighbors) {
                if (neighbor != null) {
                    if (closedList.contains(neighbor)) {
                        continue; //Neighbor has already been evaluated
                    }

                    double tempG = current.getG() + UtilService.calculateDistance(current, neighbor);
                    if (tempG >= neighbor.getG()) {
                        if (!queue.contains(neighbor)) {
                            queue.offer(neighbor); //Discover new node
                        }
                        continue; //There is already a better path to this neighbor, skip.
                    }

                    neighbor.setFrom(current.getName());
                    neighbor.setG(tempG);
                    neighbor.setH(UtilService.calculateDistance(neighbor, goalVertex));

                    if (!queue.contains(neighbor)) {
                        queue.offer(neighbor); //Discover new node
                    } else {
                        //Neighbor was previously learnt from a different vertex.
                        queue.remove(neighbor);
                        queue.offer(neighbor);
                    }
                }

            }
        }

        return null;
    }

    private static Vertex getVertexFromName(String name, List<Vertex> vertices) {
        return vertices.parallelStream()
                .filter(vertex -> vertex.getName().equals(name))
                .findFirst().orElse(null);
    }

    private static ArrayList<Vertex> getNeighbours(Vertex vertex, List<Vertex> vertices) {
        ArrayList<Vertex> neighborList = new ArrayList<>();
        for (String name : vertex.getNeighbors()) {
            neighborList.add(getVertexFromName(name, vertices));
        }
        return neighborList;
    }

    private static void removeNonDisabledVertices(List<Vertex> vertices) {
        for (int i = 0; i < vertices.size(); i++) {
            if (!vertices.get(i).isWheelChairAccessible()) {
                vertices.remove(i);
                i--;
            }
        }
    }

    private static LinkedList<Vertex> reconstructPath(Vertex currentVertex, List<Vertex> closedList) {
        LinkedList<Vertex> path = new LinkedList<>();
        path.add(currentVertex);

        while (currentVertex.getFrom() != null) {
            currentVertex = getVertexFromName(currentVertex.getFrom(), closedList);
            path.add(currentVertex);
        }

        Collections.reverse(path);
        return path;
    }

}
