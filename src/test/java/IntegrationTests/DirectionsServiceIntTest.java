package IntegrationTests;

import org.backend.mdxmaps.model.responseObjects.directions.MainDirectionsResponse;
import org.backend.mdxmaps.model.responseObjects.directions.Route;
import org.backend.mdxmaps.service.DirectionsService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.runners.MockitoJUnitRunner;

import java.util.ArrayList;
import java.util.Collections;

import static org.backend.mdxmaps.service.ResponseService.Status.OK;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

/**
 * Created by Emmanuel Keboh on 29/01/2017.
 */

@RunWith(MockitoJUnitRunner.class)
public class DirectionsServiceIntTest {

    private MainDirectionsResponse response = null;

    private ArrayList<Double> distances = new ArrayList<>();

    @Test
    public void CG03ToCG60() {

        try {
            response = (MainDirectionsResponse) new DirectionsService("CG03", "CG60", null, false, "").call();
        } catch (Exception e) {
            e.printStackTrace();
        }

        assertEquals(OK, response.getStatus());

        ArrayList<Route> newRoutes = response.getRoutes();
        newRoutes.forEach(route -> distances.add(route.getDistance()));

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
            response = (MainDirectionsResponse) new DirectionsService("CG03", "CG60", "disabled", false, "").call();
        } catch (Exception e) {
            e.printStackTrace();
        }

        assertEquals(OK, response.getStatus());
        ArrayList<Route> newRoutes = response.getRoutes();
        newRoutes.forEach(route -> distances.add(route.getDistance()));

        assertTrue(distances.size() == 11);

        assertTrue(Collections.frequency(distances, 97.0) == 1 && distances.get(0) == 97.0);
        assertTrue(Collections.frequency(distances, 154.0) == 3 && distances.get(1) == 154.0);
        assertTrue(Collections.frequency(distances, 155.0) == 3);
        assertTrue(Collections.frequency(distances, 186.0) == 1);
        assertTrue(Collections.frequency(distances, 190.0) == 1);
        assertTrue(Collections.frequency(distances, 195.0) == 1);
        assertTrue(Collections.frequency(distances, 222.0) == 1 && distances.get(10) == 222.0);
    }

    @Test
    public void CG03ToC110() {
        try {
            response = (MainDirectionsResponse) new DirectionsService("CG03", "C110", null, false, "").call();
        } catch (Exception e) {
            e.printStackTrace();
        }

        assertEquals(OK, response.getStatus());

        assertEquals("Walk to the stairs", response.getRoutes().get(0).getSteps().get(0).getDescription());
        assertEquals("Go up 1 level", response.getRoutes().get(0).getSteps().get(1).getDescription());
        assertEquals("Walk to C110", response.getRoutes().get(0).getSteps().get(2).getDescription());

        ArrayList<Route> newRoutes = response.getRoutes();
        newRoutes.forEach(route -> distances.add(route.getDistance()));

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
            response = (MainDirectionsResponse) new DirectionsService("CG03", "C110", "elevators", false, "").call();
        } catch (Exception e) {
            e.printStackTrace();
        }

        assertEquals(OK, response.getStatus());

        assertEquals("Walk to the elevators", response.getRoutes().get(0).getSteps().get(0).getDescription());
        assertEquals("Go up 1 level", response.getRoutes().get(0).getSteps().get(1).getDescription());
        assertEquals("Walk to C110", response.getRoutes().get(0).getSteps().get(2).getDescription());

        ArrayList<Route> newRoutes = response.getRoutes();
        newRoutes.forEach(route -> distances.add(route.getDistance()));

        assertTrue(Collections.frequency(distances, 75.0) == 1 && distances.get(0) == 75.0);
        assertTrue(Collections.frequency(distances, 149.0) == 1 && distances.get(1) == 149.0);

        assertTrue(distances.size() == 2);
    }

    @Test
    public void CG03ToC115Disabled() {
        try {
            response = (MainDirectionsResponse) new DirectionsService("CG03", "C115", "disabled", false, "").call();
        } catch (Exception e) {
            e.printStackTrace();
        }

        assertEquals(OK, response.getStatus());

        assertEquals("Get to the elevators", response.getRoutes().get(0).getSteps().get(0).getDescription());
        assertEquals("Go up 1 level", response.getRoutes().get(0).getSteps().get(1).getDescription());
        assertEquals("Get to C115", response.getRoutes().get(0).getSteps().get(2).getDescription());

        ArrayList<Route> newRoutes = response.getRoutes();
        newRoutes.forEach(route -> distances.add(route.getDistance()));

        assertTrue(Collections.frequency(distances, 76.0) == 1 && distances.get(0) == 76.0);
        assertTrue(Collections.frequency(distances, 86.0) == 1 && distances.get(1) == 86.0);
    }
}
