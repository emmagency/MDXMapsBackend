package IntegrationTests;

import org.backend.mdxmaps.Model.ResponseObjects.MainResponseObject;
import org.backend.mdxmaps.Model.ResponseObjects.SBDLResponseObject;
import org.backend.mdxmaps.Model.ResponseObjects.SBSLResponseObject;
import org.backend.mdxmaps.Services.DirectionsService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.runners.MockitoJUnitRunner;

import java.util.ArrayList;
import java.util.Collections;

import static org.backend.mdxmaps.Model.Enums.OperationType.SBDL;
import static org.backend.mdxmaps.Model.Enums.OperationType.SBSL;
import static org.backend.mdxmaps.Services.ResponseService.Status.OK;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

/**
 * Created by Emmanuel Keboh on 29/01/2017.
 */

@RunWith(MockitoJUnitRunner.class)
public class DirectionsServiceIntTest {

    private MainResponseObject<?> response = null;

    private ArrayList<Double> distances = new ArrayList<>();

    @Test
    public void CG03ToCG60() {

        try {
            response = (MainResponseObject<?>) new DirectionsService("CG03", "CG60", null).call();
        } catch (Exception e) {
            e.printStackTrace();
        }

        assertEquals(OK, response.getStatus());
        assertEquals(SBSL, response.getType());

        ArrayList<SBSLResponseObject> routes = (ArrayList<SBSLResponseObject>) response.getRoutes();
        routes.forEach(route -> distances.add(route.getDistance()));

        assertTrue(Collections.frequency(distances, 97.0) == 1 && distances.get(0) == 97.0);
        assertTrue(Collections.frequency(distances, 109.0) == 1 && distances.get(1) == 109.0);
        assertTrue(Collections.frequency(distances, 132.0) == 3);
        assertTrue(Collections.frequency(distances, 133.0) == 3);
        assertTrue(Collections.frequency(distances, 154.0) == 3);
        assertTrue(Collections.frequency(distances, 155.0) == 3);
        assertTrue(Collections.frequency(distances, 164.0) == 1);
        assertTrue(Collections.frequency(distances, 168.0) == 1);
        assertTrue(Collections.frequency(distances, 173.0) == 1);
        assertTrue(Collections.frequency(distances, 186.0) == 1);
        assertTrue(Collections.frequency(distances, 190.0) == 1);
        assertTrue(Collections.frequency(distances, 195.0) == 1);
        assertTrue(Collections.frequency(distances, 200.0) == 1);
        assertTrue(Collections.frequency(distances, 222.0) == 1);

        assertTrue(distances.size() == 22);

//        routes.forEach(route -> {
//                    System.out.println("Distance: "+route.getDistance());
//                    route.getRoute().forEach(latLng -> System.out.println("[" + latLng.latitude + "," + latLng.longitude + "],"));
//                    System.out.println("--------------------------------------------------");
//                }
//        );

    }

    @Test
    public void CG03ToCG60Disabled() {
        try {
            response = (MainResponseObject<?>) new DirectionsService("CG03", "CG60", "disabled").call();
        } catch (Exception e) {
            e.printStackTrace();
        }

        assertEquals(OK, response.getStatus());
        assertEquals(SBSL, response.getType());

        ArrayList<SBSLResponseObject> routes = (ArrayList<SBSLResponseObject>) response.getRoutes();
        routes.forEach(route -> distances.add(route.getDistance()));

        assertTrue(Collections.frequency(distances, 97.0) == 1 && distances.get(0) == 97.0);
        assertTrue(Collections.frequency(distances, 154.0) == 3 && distances.get(1) == 154.0);
        assertTrue(Collections.frequency(distances, 155.0) == 3);
        assertTrue(Collections.frequency(distances, 186.0) == 1);
        assertTrue(Collections.frequency(distances, 190.0) == 1);
        assertTrue(Collections.frequency(distances, 195.0) == 1);
        assertTrue(Collections.frequency(distances, 222.0) == 1);

        assertTrue(distances.size() == 11);
    }

    @Test
    public void CG03ToC110() {
        try {
            response = (MainResponseObject<?>) new DirectionsService("CG03", "C110", null).call();
        } catch (Exception e) {
            e.printStackTrace();
        }

        assertEquals(OK, response.getStatus());
        assertEquals(SBDL, response.getType());

        assertEquals("Walk to the stairs and go up 1 level(s)", response.getRouteDescription().get(0));
        assertEquals("Walk to C110", response.getRouteDescription().get(1));

        ArrayList<SBDLResponseObject> routes = (ArrayList<SBDLResponseObject>) response.getRoutes();
        routes.forEach(route -> distances.add(route.getDistance()));

        assertTrue(Collections.frequency(distances, 56.0) == 1 && distances.get(0) == 56.0);
        assertTrue(Collections.frequency(distances, 59.0) == 1 && distances.get(1) == 59.0);
        assertTrue(Collections.frequency(distances, 80.0) == 1);
        assertTrue(Collections.frequency(distances, 110.0) == 1);
        assertTrue(Collections.frequency(distances, 154.0) == 1);
        assertTrue(Collections.frequency(distances, 175.0) == 1);

        assertTrue(distances.size() == 6);

    }

    @Test
    public void CG03ToC110Elevators() {
        try {
            response = (MainResponseObject<?>) new DirectionsService("CG03", "C110", "elevators").call();
        } catch (Exception e) {
            e.printStackTrace();
        }

        assertEquals(OK, response.getStatus());
        assertEquals(SBDL, response.getType());

        assertEquals("Walk to the elevators and go up 1 level(s)", response.getRouteDescription().get(0));
        assertEquals("Walk to C110", response.getRouteDescription().get(1));

        ArrayList<SBDLResponseObject> routes = (ArrayList<SBDLResponseObject>) response.getRoutes();
        routes.forEach(route -> distances.add(route.getDistance()));

        assertTrue(Collections.frequency(distances, 75.0) == 1 && distances.get(0) == 75.0);
        assertTrue(Collections.frequency(distances, 149.0) == 1 && distances.get(1) == 149.0);

        assertTrue(distances.size() == 2);
    }

    @Test
    public void CG03ToC115Disabled() {
        try {
            response = (MainResponseObject<?>) new DirectionsService("CG03", "C115", "disabled").call();
        } catch (Exception e) {
            e.printStackTrace();
        }

        assertEquals(OK, response.getStatus());
        assertEquals(SBDL, response.getType());

        assertEquals("Get to the elevators and go up 1 level(s)", response.getRouteDescription().get(0));
        assertEquals("Get to C115", response.getRouteDescription().get(1));

        ArrayList<SBDLResponseObject> routes = (ArrayList<SBDLResponseObject>) response.getRoutes();
        routes.forEach(route -> distances.add(route.getDistance()));

        assertTrue(Collections.frequency(distances, 76.0) == 1 && distances.get(0) == 76.0);
        assertTrue(Collections.frequency(distances, 86.0) == 1 && distances.get(1) == 86.0);
    }

}
