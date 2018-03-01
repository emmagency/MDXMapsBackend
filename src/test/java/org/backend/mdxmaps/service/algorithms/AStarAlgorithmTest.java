package org.backend.mdxmaps.service.algorithms;

import org.backend.mdxmaps.model.LatLng;
import org.backend.mdxmaps.model.Vertex;
import org.backend.mdxmaps.model.responseObjects.directions.TestDirections;
import org.backend.mdxmaps.resource.DirectionsResource;
import org.backend.mdxmaps.service.util.UtilService;
import org.junit.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Created by Emmanuel Keboh on 25/02/2018.
 */
public class AStarAlgorithmTest {
    @Test
    public void getOutsideRoute() throws Exception {
        String start = "Z1";
        String end = "J1";
        int k = 7;
        boolean disabled = false;
        LinkedList<Vertex> route = AStarAlgorithm.calculateOutsideRoute(start, end, disabled, Vertex.getOutsideVertices());

        ArrayList<ArrayList<Vertex>> arrayLists = KPathAlgorithm.calculateKSPs(new ArrayList<>(route), k, end, disabled);
        //System.out.println(arrayLists.size());

        for (ArrayList<Vertex> v : arrayLists) {
            ArrayList<LatLng> routeLatLng = (ArrayList<LatLng>) v.stream()
                    .map(Vertex::getLatLng)
                    .collect(Collectors.toList());
            System.out.println("A Star: " + UtilService.calculateRouteDistance(routeLatLng));
        }

        System.out.println();

//        ArrayList<LatLng> routeLatLng = (ArrayList<LatLng>) route.stream()
//                .map(Vertex::getLatLng)
//                .collect(Collectors.toList());
//
//        System.out.println("A Star: " + UtilService.calculateRouteDistance(routeLatLng));

        List<TestDirections> testDirections = new DirectionsResource().legacyDirections(start, end, "", k);
        for (int i = 0; i < testDirections.size(); i++) {
            System.out.println("Legacy: " + testDirections.get(i).distance);
        }
    }

    @Test
    public void testEqulaity() throws Exception {
        Vertex a = new Vertex("A", new LatLng(1, 1), null, true);
        Vertex b = new Vertex("B", new LatLng(2, 1), null, false);
        Vertex c = new Vertex("C", new LatLng(3, 2), null, true);
        Vertex d = new Vertex("D", new LatLng(5, 4), null, false);
        List<Vertex> list = new ArrayList<>(Arrays.asList(a, b, c, d));

        System.out.println(list.subList(0, 5));
    }

}