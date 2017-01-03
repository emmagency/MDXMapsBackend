package org.backend.mdxmaps.Services;

import net.bytebuddy.utility.RandomString;
import org.backend.mdxmaps.Model.RoutingObjects;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import java.util.ArrayList;
import java.util.Arrays;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.when;

/**
 * Created by Emmanuel Keboh on 03/01/2017.
 */

@RunWith(MockitoJUnitRunner.class)
public class UtilServiceTest {

    @Mock
    private RoutingObjects object1;

    @Mock
    private RoutingObjects object2;

    @Mock
    private RoutingObjects object3;

    @Mock
    private RoutingObjects object4;

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

}