package org.backend.mdxmaps.Services;

import org.backend.mdxmaps.Model.RoutingObjects;
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
public class MOTValidatorTest {

    @Mock
    private RoutingObjects start;

    @Mock
    private RoutingObjects end;

    @Test
    public void testValidate() throws Exception {

        when(start.getBuilding()).thenReturn("College Building");
        when(end.getBuilding()).thenReturn("College Building");
        when(start.getActualLevel()).thenReturn(1);
        when(end.getActualLevel()).thenReturn(1);

        assertTrue(MOTValidator.validate(start, end));

    }

    @Test
    public void confirmPassedMOTIsAllowed() {

    }

}