package org.backend.mdxmaps.Services;

import com.google.common.collect.Multimap;
import net.bytebuddy.utility.RandomString;
import org.backend.mdxmaps.Model.LatLng;
import org.backend.mdxmaps.Model.RoutingObjects;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.when;

/**
 * Created by Emmanuel Keboh on 03/01/2017.
 */

@RunWith(MockitoJUnitRunner.class)
public class UtilServiceTest {

    @Mock
    private RoutingObjects object1, object2, object3, object4;

    @Test
    public void shouldFilterConnectorObjectsByType() {
        String type = new RandomString(5).toString();
        when(object1.getType()).thenReturn(type);
        when(object2.getType()).thenReturn("");
        when(object3.getType()).thenReturn(type);
        when(object4.getType()).thenReturn(type);

        assertEquals(3, UtilService.filterConnectorObjectsByType(
                new ArrayList<>(Arrays.asList(object1, object2, object3, object4)), type
        ).size());
    }

    @Test
    public void shouldRemoveNonDisabledRoutes() {
        when(object1.getIsWheelChairAccessible()).thenReturn("N");
        when(object2.getIsWheelChairAccessible()).thenReturn("Y");
        when(object3.getIsWheelChairAccessible()).thenReturn("Y");
        when(object4.getIsWheelChairAccessible()).thenReturn("Y");

        ArrayList<ArrayList<RoutingObjects>> routes = new ArrayList<>();
        routes.add(new ArrayList<>(Arrays.asList(object1, object2)));
        routes.add(new ArrayList<>(Arrays.asList(object3, object4)));

        assertEquals(1, UtilService.removeNonDisabledRoutes(routes).size());
    }

    @Test
    public void shouldCalculateMultipleRoutesDistanceAndSort() {
        Multimap<Double, ArrayList<LatLng>> sortedRoutes = UtilService.calculateMultipleRoutesDistanceAndSort(getDummyData());
        assertEquals(3, sortedRoutes.size());
        List<Double> keys = new ArrayList<>(sortedRoutes.keySet());
        assertEquals(Double.valueOf(97), keys.get(0));
        assertEquals(Double.valueOf(109), keys.get(1));
        assertEquals(Double.valueOf(132), keys.get(2));

    }

    @Test
    public void shouldCalculateDistance() throws Exception {
        Double distance = UtilService.calculateDistance(51.589594, -0.228487, 51.589703, -0.229464);
        assertEquals(Double.valueOf(69.0), distance);
    }

    public ArrayList<ArrayList<LatLng>> getDummyData() {
        ArrayList<ArrayList<LatLng>> routes = new ArrayList<>();
        ArrayList<LatLng> route1 = new ArrayList<>();
        route1.add(new LatLng(51.589594, -0.228487));
        route1.add(new LatLng(51.589703, -0.229464));
        route1.add(new LatLng(51.589809, -0.229434));
        route1.add(new LatLng(51.589819, -0.229477));
        route1.add(new LatLng(51.58993, -0.229445));

        ArrayList<LatLng> route2 = new ArrayList<>();
        route2.add(new LatLng(51.589594, -0.228487));
        route2.add(new LatLng(51.589703, -0.229464));
        route2.add(new LatLng(51.589809, -0.229434));
        route2.add(new LatLng(51.589802, -0.229347));
        route2.add(new LatLng(51.589898, -0.229318));
        route2.add(new LatLng(51.589903, -0.229378));
        route2.add(new LatLng(51.58991, -0.22945));
        route2.add(new LatLng(51.58993, -0.229445));

        ArrayList<LatLng> route3 = new ArrayList<>();
        route3.add(new LatLng(51.589594, -0.228487));
        route3.add(new LatLng(51.58961, -0.228666));
        route3.add(new LatLng(51.589806, -0.228613));
        route3.add(new LatLng(51.589977, -0.22856));
        route3.add(new LatLng(51.590003, -0.228806));
        route3.add(new LatLng(51.590022, -0.228981));
        route3.add(new LatLng(51.59003, -0.229051));
        route3.add(new LatLng(51.590054, -0.229273));
        route3.add(new LatLng(51.589898, -0.229318));
        route3.add(new LatLng(51.589903, -0.229378));
        route3.add(new LatLng(51.58991, -0.22945));
        route3.add(new LatLng(51.58993, -0.229445));

        routes.add(route2);
        routes.add(route3);
        routes.add(route1);
        return routes;
    }
}