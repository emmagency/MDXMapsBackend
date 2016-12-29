package org.backend.mdxmaps.Services;

import org.backend.mdxmaps.Model.RoutingObjects;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import static org.mockito.Mockito.when;

/**
 * Created by Emmanuel Keboh on 29/12/2016.
 */
@RunWith(MockitoJUnitRunner.class)
public class MOTCheckServiceTest {

    @Mock
    RoutingObjects start;

    @Mock
    RoutingObjects end;

    @Test
    public void doMOTCheckForSBSL() throws Exception {

        when(start.getBuilding()).thenReturn("College Building");
        when(end.getBuilding()).thenReturn("College Building");
        when(start.getActualLevel()).thenReturn(1);
        when(end.getActualLevel()).thenReturn(1);

        //ResponseService response = new MOTCheckService(start, end, )
    }

}