package org.backend.mdxmaps.Services;

import org.backend.mdxmaps.Model.MOT;
import org.backend.mdxmaps.Model.RoutingObjects;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
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

    @Before
    public void reset() {
        when(start.getBuilding()).thenReturn("CB");
        when(end.getBuilding()).thenReturn("CB");
        when(start.getActualLevel()).thenReturn(0);
        when(end.getActualLevel()).thenReturn(0);
    }

    @Test
    public void shouldValidateIfAnMOTIsRequiredForSBSL() {
        assertFalse(MOTValidator.validate(start, end));
    }

    @Test
    public void shouldValidateIfAnMOTIsRequiredForSBDL() {
        when(start.getActualLevel()).thenReturn(1);
        assertTrue(MOTValidator.validate(start, end));
    }

    @Test
    public void shouldValidateIfAnMOTIsRequiredForDIFFB() {
        when(end.getBuilding()).thenReturn("HC");

        //Ground floors in both buildings. Should return false
        assertFalse(MOTValidator.validate(start, end));

        when(end.getActualLevel()).thenReturn(1);

        //At lease one building isn't ground floor. Should return true
        assertTrue(MOTValidator.validate(start, end));

    }

    @Test
    public void shouldValidateReceivedMOTDoesExists() {
        assertTrue(MOTValidator.validateReceivedMOTTypeExists("stAiRs"));
        assertFalse(MOTValidator.validateReceivedMOTTypeExists("elevate"));
        assertTrue(MOTValidator.validateReceivedMOTTypeExists("elevators"));
    }

    @Test
    public void shouldAutoResolveMOTIfNoneIsReceived() {
        //SBSL
        assertEquals(MOT.NULL, MOTValidator.autoResolve(start, end));
        //SBDL
        when(end.getActualLevel()).thenReturn(1);
        assertEquals(MOT.STAIRS, MOTValidator.autoResolve(start, end));
        //DIFFB non-ground floor
        assertEquals(MOT.STAIRS, MOTValidator.autoResolve(start, end));
        when(end.getActualLevel()).thenReturn(0);
        //DIFFB ground floors
        assertEquals(MOT.NULL, MOTValidator.autoResolve(start, end));
    }

}