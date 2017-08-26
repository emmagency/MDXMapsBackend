package org.backend.mdxmaps.services;

import org.backend.mdxmaps.model.Routing;
import org.backend.mdxmaps.services.factoryServices.DifferentBuildingFactoryService;
import org.backend.mdxmaps.services.factoryServices.SBDLFactoryService;
import org.backend.mdxmaps.services.factoryServices.SBSLFactoryService;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

import static org.backend.mdxmaps.model.enums.MOT.DISABLED;
import static org.backend.mdxmaps.model.enums.MOT.ELEVATORS;
import static org.backend.mdxmaps.model.enums.MOT.NULL;
import static org.backend.mdxmaps.model.enums.MOT.STAIRS;
import static org.backend.mdxmaps.services.ResponseService.Status.ERROR;
import static org.backend.mdxmaps.services.ResponseService.Status.INFO;
import static org.backend.mdxmaps.services.ResponseService.Status.OK;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertTrue;
import static org.mockito.Mockito.when;

/**
 * Created by Emmanuel Keboh on 29/12/2016.
 */
@RunWith(MockitoJUnitRunner.class)
public class ResolveOperationTypeServiceTest {

    @Mock
    Routing start, end, startBuilding, destBuilding;

    @Mock
    ResponseService response;

    @Before
    public void setUp() {
        when(start.getBuilding()).thenReturn("CB");
        when(end.getBuilding()).thenReturn("CB");

        when(start.getLevel()).thenReturn(0);
        when(end.getLevel()).thenReturn(0);

        when(start.getBuildingObject()).thenReturn(startBuilding);
        when(end.getBuildingObject()).thenReturn(destBuilding);

        when(startBuilding.isBuildingWheelChairAccessible()).thenReturn(true);
        when(destBuilding.isBuildingWheelChairAccessible()).thenReturn(true);

        when(startBuilding.hasStairs()).thenReturn(true);
        when(destBuilding.hasStairs()).thenReturn(true);

        when(startBuilding.hasElevators()).thenReturn(true);
        when(destBuilding.hasElevators()).thenReturn(true);
    }

    @Test
    public void shouldTestNULLCases() {
        response = new ResolveOperationTypeService(start, end, NULL).resolveOPType();
        assertEquals(OK, response.getStatus());
        assertTrue(response.getEntity() instanceof SBSLFactoryService);

        when(end.getBuilding()).thenReturn("Meh!");
        response = new ResolveOperationTypeService(start, end, NULL).resolveOPType();
        assertTrue(response.getEntity() instanceof DifferentBuildingFactoryService);
    }

    @Test
    public void shouldTestDISABLEDCasesForSameBuilding() {
        //Ground floor, wheelchair accessible building
        response = new ResolveOperationTypeService(start, end, DISABLED).resolveOPType();
        assertEquals(OK, response.getStatus());
        assertTrue(response.getEntity() instanceof SBSLFactoryService);

        //Different floor, wheelchair accessible building with elevators
        when(end.getLevel()).thenReturn(1);
        response = new ResolveOperationTypeService(start, end, DISABLED).resolveOPType();
        assertEquals(OK, response.getStatus());
        assertTrue(response.getEntity() instanceof SBDLFactoryService);

        //Different floor, wheelchair accessible building with no elevators
        when(startBuilding.hasElevators()).thenReturn(false);
        response = new ResolveOperationTypeService(start, end, DISABLED).resolveOPType();
        assertEquals(ERROR, response.getStatus());
        assertEquals("There are no elevators in CB", response.getMessage());

        //None-wheelchair accessible building
        when(startBuilding.isBuildingWheelChairAccessible()).thenReturn(false);
        response = new ResolveOperationTypeService(start, end, DISABLED).resolveOPType();
        assertEquals(ERROR, response.getStatus());
        assertNull(response.getEntity());
        assertEquals("CB is not wheelchair accessible", response.getMessage());
    }

    @Test
    public void shouldTestDISABLEDCasesForDifferentBuildings() {

        when(end.getBuilding()).thenReturn("HC");

        //Wheelchair-accessible buildings with elevators, ground floors
        response = new ResolveOperationTypeService(start, end, DISABLED).resolveOPType();
        assertEquals(OK, response.getStatus());
        assertTrue(response.getEntity() instanceof DifferentBuildingFactoryService);
        assertEquals(NULL, ((DifferentBuildingFactoryService) response.getEntity()).getStartEDMethod());
        assertEquals(NULL, ((DifferentBuildingFactoryService) response.getEntity()).getStartEDMethod());

        //Both wheelchair-accessible buildings, ground floor from start to 1st floor in destination
        when(end.getLevel()).thenReturn(1);
        response = new ResolveOperationTypeService(start, end, DISABLED).resolveOPType();
        assertEquals(OK, response.getStatus());
        assertEquals(NULL, ((DifferentBuildingFactoryService) response.getEntity()).getStartEDMethod());
        assertEquals(ELEVATORS, ((DifferentBuildingFactoryService) response.getEntity()).getDestEDMethod());

        //Both wheelchair-accessible buildings, 1st floor from start to ground floor in destination
        when(start.getLevel()).thenReturn(1);
        when(end.getLevel()).thenReturn(0);
        response = new ResolveOperationTypeService(start, end, DISABLED).resolveOPType();
        assertEquals(OK, response.getStatus());
        assertEquals(ELEVATORS, ((DifferentBuildingFactoryService) response.getEntity()).getStartEDMethod());
        assertEquals(NULL, ((DifferentBuildingFactoryService) response.getEntity()).getDestEDMethod());

        //Both wheelchair-accessible buildings with elevators, non-ground floors
        when(start.getLevel()).thenReturn(1);
        when(end.getLevel()).thenReturn(1);
        response = new ResolveOperationTypeService(start, end, DISABLED).resolveOPType();
        assertEquals(OK, response.getStatus());
        assertEquals(ELEVATORS, ((DifferentBuildingFactoryService) response.getEntity()).getStartEDMethod());
        assertEquals(ELEVATORS, ((DifferentBuildingFactoryService) response.getEntity()).getDestEDMethod());


        //Start building non wheelchair-accessible
        when(startBuilding.isBuildingWheelChairAccessible()).thenReturn(false);
        response = new ResolveOperationTypeService(start, end, DISABLED).resolveOPType();
        assertEquals(ERROR, response.getStatus());
        assertEquals("CB is not wheelchair accessible", response.getMessage());
        assertNull(response.getEntity());
        when(startBuilding.isBuildingWheelChairAccessible()).thenReturn(true);

        //Destination building non wheelchair-accessible
        when(destBuilding.isBuildingWheelChairAccessible()).thenReturn(false);
        response = new ResolveOperationTypeService(start, end, DISABLED).resolveOPType();
        assertEquals(ERROR, response.getStatus());
        assertEquals("HC is not wheelchair accessible", response.getMessage());
        assertNull(response.getEntity());

        //Both buildings not wheelchair-accessible
        when(startBuilding.isBuildingWheelChairAccessible()).thenReturn(false);
        response = new ResolveOperationTypeService(start, end, DISABLED).resolveOPType();
        assertEquals(ERROR, response.getStatus());
        assertEquals("Both CB & HC are not wheelchair accessible",
                response.getMessage());
        assertNull(response.getEntity());

        //Both wheelchair-accessible buildings, trying to get to 1st floor of destination building without elevators
        when(startBuilding.isBuildingWheelChairAccessible()).thenReturn(true);
        when(destBuilding.isBuildingWheelChairAccessible()).thenReturn(true);
        when(destBuilding.hasElevators()).thenReturn(false);
        when(end.getName()).thenReturn("HC101");
        response = new ResolveOperationTypeService(start, end, DISABLED).resolveOPType();
        assertEquals(ERROR, response.getStatus());
        assertEquals("There are no elevators in HC to get to HC101", response.getMessage());
        assertNull(response.getEntity());

        //Both wheelchair-accessible buildings, coming from 1st floor of start building without elevators
        when(destBuilding.hasElevators()).thenReturn(true);
        when(startBuilding.hasElevators()).thenReturn(false);
        response = new ResolveOperationTypeService(start, end, DISABLED).resolveOPType();
        assertEquals(ERROR, response.getStatus());
        assertEquals("There are no elevators in CB", response.getMessage());
        assertNull(response.getEntity());

        //Both wheelchair-accessible buildings, but no elevators in both
        when(destBuilding.hasElevators()).thenReturn(false);
        response = new ResolveOperationTypeService(start, end, DISABLED).resolveOPType();
        assertEquals(ERROR, response.getStatus());
        assertEquals("There are no elevators in CB and HC", response.getMessage());
        assertNull(response.getEntity());
    }

    @Test
    public void shouldTestSTAIRSCasesForSameBuilding() {
        when(end.getLevel()).thenReturn(1);
        response = new ResolveOperationTypeService(start, end, STAIRS).resolveOPType();
        assertEquals(OK, response.getStatus());
        assertTrue(response.getEntity() instanceof SBDLFactoryService);
        assertEquals(STAIRS, ((SBDLFactoryService) response.getEntity()).getMot());

        //Should switch to elevators if no stairs. (Not applicable to any of the buildings but completes the logic)
        when(startBuilding.hasStairs()).thenReturn(false);
        response = new ResolveOperationTypeService(start, end, STAIRS).resolveOPType();
        assertEquals(INFO, response.getStatus());
        assertEquals("No stairs in CB. App switched to elevators for this building", response.getMessage());
        assertTrue(response.getEntity() instanceof SBDLFactoryService);
        assertEquals(ELEVATORS, ((SBDLFactoryService) response.getEntity()).getMot());

    }

    @Test
    public void shouldTestSTAIRSForDifferentBuildings() {
        when(end.getBuilding()).thenReturn("HC");

        //Ground floor in start building, basement in destination building
        when(end.getLevel()).thenReturn(-1);
        response = new ResolveOperationTypeService(start, end, STAIRS).resolveOPType();
        assertEquals(OK, response.getStatus());
        assertEquals(NULL, ((DifferentBuildingFactoryService) response.getEntity()).getStartEDMethod());
        assertEquals(STAIRS, ((DifferentBuildingFactoryService) response.getEntity()).getDestEDMethod());

        //1st floor at start, ground floor in destination
        when(start.getLevel()).thenReturn(1);
        when(end.getLevel()).thenReturn(0);
        response = new ResolveOperationTypeService(start, end, STAIRS).resolveOPType();
        assertEquals(OK, response.getStatus());
        assertEquals(STAIRS, ((DifferentBuildingFactoryService) response.getEntity()).getStartEDMethod());
        assertEquals(NULL, ((DifferentBuildingFactoryService) response.getEntity()).getDestEDMethod());

        //Non-ground floor for both buildings
        when(end.getLevel()).thenReturn(1);
        response = new ResolveOperationTypeService(start, end, STAIRS).resolveOPType();
        assertEquals(OK, response.getStatus());
        assertTrue(response.getEntity() instanceof DifferentBuildingFactoryService);
        assertEquals(STAIRS, ((DifferentBuildingFactoryService) response.getEntity()).getStartEDMethod());
        assertEquals(STAIRS, ((DifferentBuildingFactoryService) response.getEntity()).getDestEDMethod());

        //Start building doesn't have stairs, use elevators. (Not applicable to any of the buildings but completes the logic)
        when(startBuilding.hasStairs()).thenReturn(false);
        response = new ResolveOperationTypeService(start, end, STAIRS).resolveOPType();
        assertEquals(INFO, response.getStatus());
        assertEquals("No stairs in CB. App switched to elevators for this building", response.getMessage());
        assertTrue(response.getEntity() instanceof DifferentBuildingFactoryService);

        //No stairs in both buildings (Not applicable to any of the buildings but completes the logic)
        when(destBuilding.hasStairs()).thenReturn(false);
        response = new ResolveOperationTypeService(start, end, STAIRS).resolveOPType();
        assertEquals(INFO, response.getStatus());
        assertEquals("No stairs in both buildings, app switched to elevators in CB & HC", response.getMessage());
        assertTrue(response.getEntity() instanceof DifferentBuildingFactoryService);

        //No stairs in destination building. (Not applicable to any of the buildings but completes the logic)
        when(startBuilding.hasStairs()).thenReturn(true);
        response = new ResolveOperationTypeService(start, end, STAIRS).resolveOPType();
        assertEquals(INFO, response.getStatus());
        assertEquals("No stairs in HC. App switched to elevators for this building", response.getMessage());
        assertTrue(response.getEntity() instanceof DifferentBuildingFactoryService);

    }

    @Test
    public void shouldTestELEVATORSForSameBuilding() {
        when(end.getLevel()).thenReturn(-1);
        response = new ResolveOperationTypeService(start, end, ELEVATORS).resolveOPType();
        assertEquals(OK, response.getStatus());
        assertTrue(response.getEntity() instanceof SBDLFactoryService);
        assertEquals(ELEVATORS, ((SBDLFactoryService) response.getEntity()).getMot());

        //Should switch to stairs if no stairs.
        when(startBuilding.hasElevators()).thenReturn(false);
        response = new ResolveOperationTypeService(start, end, ELEVATORS).resolveOPType();
        assertEquals(INFO, response.getStatus());
        assertEquals("No elevators in CB. App switched to stairs for this building", response.getMessage());
        assertTrue(response.getEntity() instanceof SBDLFactoryService);
        assertTrue(response.getEntity() instanceof SBDLFactoryService);
        assertEquals(STAIRS, ((SBDLFactoryService) response.getEntity()).getMot());
    }

    @Test
    public void shouldTestELEVATORSForDifferentBuildings() {
        when(end.getBuilding()).thenReturn("HC");

        //Ground floor in start building, basement in destination building
        when(end.getLevel()).thenReturn(-1);
        response = new ResolveOperationTypeService(start, end, ELEVATORS).resolveOPType();
        assertEquals(OK, response.getStatus());
        assertTrue(response.getEntity() instanceof DifferentBuildingFactoryService);
        assertEquals(NULL, ((DifferentBuildingFactoryService) response.getEntity()).getStartEDMethod());
        assertEquals(ELEVATORS, ((DifferentBuildingFactoryService) response.getEntity()).getDestEDMethod());

        //1st floor at start, ground floor in destination
        when(start.getLevel()).thenReturn(1);
        when(end.getLevel()).thenReturn(0);
        response = new ResolveOperationTypeService(start, end, ELEVATORS).resolveOPType();
        assertEquals(OK, response.getStatus());
        assertTrue(response.getEntity() instanceof DifferentBuildingFactoryService);
        assertEquals(ELEVATORS, ((DifferentBuildingFactoryService) response.getEntity()).getStartEDMethod());
        assertEquals(NULL, ((DifferentBuildingFactoryService) response.getEntity()).getDestEDMethod());

        //Non-ground floor for both buildings
        when(end.getLevel()).thenReturn(1);
        response = new ResolveOperationTypeService(start, end, ELEVATORS).resolveOPType();
        assertEquals(OK, response.getStatus());
        assertTrue(response.getEntity() instanceof DifferentBuildingFactoryService);
        assertEquals(ELEVATORS, ((DifferentBuildingFactoryService) response.getEntity()).getStartEDMethod());
        assertEquals(ELEVATORS, ((DifferentBuildingFactoryService) response.getEntity()).getDestEDMethod());

        //Start building doesn't have elevators, use stairs.
        when(startBuilding.hasElevators()).thenReturn(false);
        response = new ResolveOperationTypeService(start, end, ELEVATORS).resolveOPType();
        assertEquals(INFO, response.getStatus());
        assertEquals("No elevators in CB. App switched to stairs for this building", response.getMessage());
        assertTrue(response.getEntity() instanceof DifferentBuildingFactoryService);
        assertEquals(STAIRS, ((DifferentBuildingFactoryService) response.getEntity()).getStartEDMethod());
        assertEquals(ELEVATORS, ((DifferentBuildingFactoryService) response.getEntity()).getDestEDMethod());

        //No elevators in both buildings
        when(destBuilding.hasElevators()).thenReturn(false);
        response = new ResolveOperationTypeService(start, end, ELEVATORS).resolveOPType();
        assertEquals(INFO, response.getStatus());
        assertEquals("No elevators in both buildings, app switched to stairs for CB & HC", response.getMessage());
        assertTrue(response.getEntity() instanceof DifferentBuildingFactoryService);
        assertEquals(STAIRS, ((DifferentBuildingFactoryService) response.getEntity()).getStartEDMethod());
        assertEquals(STAIRS, ((DifferentBuildingFactoryService) response.getEntity()).getDestEDMethod());

        //No elevators in destination building
        when(startBuilding.hasElevators()).thenReturn(true);
        response = new ResolveOperationTypeService(start, end, ELEVATORS).resolveOPType();
        assertEquals(INFO, response.getStatus());
        assertEquals("No elevators in HC. App switched to stairs for this building", response.getMessage());
        assertTrue(response.getEntity() instanceof DifferentBuildingFactoryService);
        assertEquals(ELEVATORS, ((DifferentBuildingFactoryService) response.getEntity()).getStartEDMethod());
        assertEquals(STAIRS, ((DifferentBuildingFactoryService) response.getEntity()).getDestEDMethod());
    }

}