package org.backend.mdxmaps.Services;

import org.backend.mdxmaps.Model.MOT;
import org.backend.mdxmaps.Model.RoutingObjects;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import static org.junit.Assert.assertTrue;
import static org.mockito.Mockito.when;

/**
 * Created by Emmanuel Keboh on 29/12/2016.
 */
@RunWith(MockitoJUnitRunner.class)
public class ResolveOperationTypeServiceTest {

    @Mock
    RoutingObjects start;

    @Mock
    RoutingObjects end;

    @Mock
    ResponseService response;

    @Before
    public void reset() {
        when(start.getBuilding()).thenReturn("CB");
        when(end.getBuilding()).thenReturn("CB");
        when(start.getActualLevel()).thenReturn(0);
        when(end.getActualLevel()).thenReturn(0);
    }

    @Test
    public void shouldTestNULLCases() {
        response = new ResolveOperationTypeService(start, end, MOT.NULL).resolveOPType();
        assertTrue(response.getEntity() instanceof SBSLFactoryService);

        when(end.getBuilding()).thenReturn("Meh!");
        response = new ResolveOperationTypeService(start, end, MOT.NULL).resolveOPType();
        assertTrue(response.getEntity() instanceof DiffBuildingFactoryService);
    }

}