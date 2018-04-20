package org.backend.mdxmaps.service.algorithms;

import org.backend.mdxmaps.model.Vertex;
import org.backend.mdxmaps.service.util.UtilService;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;
import java.util.PriorityQueue;
import java.util.stream.IntStream;

import static org.backend.mdxmaps.service.util.UtilService.getClone;

/**
 * Created by Emmanuel Keboh on 27/02/2018.
 */
public class KPathAlgorithm {

    public static ArrayList<ArrayList<Vertex>> calculateKSPs(ArrayList<Vertex> sp, int numberOfKRoutes, String goal, List<Vertex> vertices) {

        ArrayList<ArrayList<Vertex>> A = new ArrayList<>();
        A.add(sp);

        PriorityQueue<ArrayList<Vertex>> B = new PriorityQueue<>(11, Comparator.comparingDouble(KPathAlgorithm::calculateKSPDistance));

        for (int k = 1; k <= numberOfKRoutes; k++) {
            for (int i = 0; i < A.get(k - 1).size() - 1; i++) {
                List<Vertex> graph = getClone(vertices);
                Vertex spurNode = A.get(k - 1).get(i);
                List<Vertex> rootPath = new ArrayList<>(A.get(k - 1).subList(0, i));

                for (ArrayList<Vertex> path : A) {
                    if (i < path.size() - 1) {
                        if (rootPath.equals(path.subList(0, i))) {
                            removeEdge(path.get(i).getName(), path.get(i + 1).getName(), graph);
                        }
                    }
                }

                removeNodes(spurNode, new ArrayList<>(rootPath), graph);

                List<Vertex> spurPath =
                        AStarAlgorithm.getAStarShortestPath(spurNode.getName(), goal, graph);

                if (spurPath != null) {
                    rootPath.addAll(spurPath);
                    //System.out.println(spurNode.getName());
                    if (rootPath.get(0).equals(sp.get(0)) && rootPath.get(rootPath.size() - 1).equals(sp.get(sp.size() - 1))) {
                        B.offer(new ArrayList<>(rootPath));
                    }
                }

            }

            if (B.isEmpty()) break;

            //System.out.println("Adding to A");
            A.add(new ArrayList<>(B.remove()));

        }

        //ToDo Get other k routes from B if at the end of iteration, not enough routes exists in A to match the number of numberOfKRoutes routes requested.
        return A;
    }

    private static void removeEdge(String firstV, String secondV, List<Vertex> graph) {
        String[] neighbors = new String[1];
        Vertex v = graph.stream().filter(vertex -> vertex.getName().equals(firstV)).findFirst().orElse(null);
        List<String> n = new ArrayList<>(Arrays.asList(v.getNeighbors()));
        n.remove(secondV);
        neighbors = n.toArray(neighbors);
        v.setNeighbors(neighbors);

        v = graph.stream().filter(vertex -> vertex.getName().equals(secondV)).findFirst().orElse(null);
        n = new ArrayList<>(Arrays.asList(v.getNeighbors()));
        n.remove(firstV);
        neighbors = new String[1];
        neighbors = n.toArray(neighbors);
        v.setNeighbors(neighbors);
    }

    private static void removeNodes(Vertex spurNode, List<Vertex> rootPath, List<Vertex> graph) {
        rootPath.remove(spurNode);
        graph.removeAll(rootPath);
    }

    private static double calculateKSPDistance(ArrayList<Vertex> route) {
        return IntStream.range(0, route.size() - 1)
                .mapToDouble(i -> UtilService.calculateDistance(route.get(i), route.get(i + 1)))
                .sum();
    }

}
