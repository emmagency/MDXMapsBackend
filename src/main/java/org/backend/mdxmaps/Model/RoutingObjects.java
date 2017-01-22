package org.backend.mdxmaps.Model;

import org.backend.mdxmaps.Model.Enums.ObjectType;
import org.backend.mdxmaps.Services.RoutingObjectsGetterUtilService;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;

import static org.backend.mdxmaps.Model.Enums.ObjectType.BASIC_CONNECTOR;
import static org.backend.mdxmaps.Model.Enums.ObjectType.DOOR;
import static org.backend.mdxmaps.Model.Enums.ObjectType.ELEVATOR;
import static org.backend.mdxmaps.Model.Enums.ObjectType.STAIR;

/**
 * Created by Emmanuel Keboh on 27/11/2016.
 */

public class RoutingObjects {

    //Connectors, room and buildings attribute
    String name;

    //Connectors and Room Attributes
    int gMapLevel;
    int actualLevel;
    LatLng latLng;

    //Connectors-Only Attributes
    int[] primeLanes;
    String[] adjacentConnectors;
    ObjectType type;
    String isWheelChairAccessible;

    //Room-Only Attributes
    String[] laneConnectors;
    int lane;
    String building;

    //Building-Only Attributes
    private boolean hasStairs;
    private boolean hasElevators;
    private boolean wheelChairAccessible;

    /*Buildings*/
    public final static String COLLEGE = "College Building";
    public final static String HATHCROFT = "Hathcroft Building";
    public final static String WILLIAMS = "Williams Building";
    public final static String SHEPPARDLIBRARY = "Sheppard Library";
    public final static String CIRCLE_CAFE = "Circle Cafe";
    public final static String BARN = "Barn";
    public final static String PORTACABIN_A = "Portacabin A";
    public final static String PORTACABIN_B = "Portacabin B";
    public final static String PORTACABIN_A_EXT = "Portacabin A.";
    public final static String PORTACABIN_67 = "Portacabin 6 & 7";
    public final static String MDXHOUSE = "MDX House";
    public final static String BUILDING9 = "Building 9";
    public final static String BUILDING10 = "Building 10";
    public final static String GROVE_BLOCK_A = "Grove Block A";
    public final static String GROVE_BLOCK_B = "Grove Block B";
    public final static String GROVE_BLOCK_C = "Grove Block C";
    public final static String VINE = "The Vine";
    private final static String OUTSIDE = "Outside";

    //TODO Create constructor for outside connectors removing primeLanes, gMap and actual levels

    //Constructor for connectors, stairs, elevators & doors
    public RoutingObjects(String name, ObjectType type, int[] primeLanes, int gMapLevel,
                          int actualLevel, LatLng latLng, String[] adjacentConnectors, String isWheelChairAccessible) {
        this.name = name;
        this.type = type;
        this.primeLanes = primeLanes;
        this.gMapLevel = gMapLevel;
        this.actualLevel = actualLevel;
        this.latLng = latLng;
        this.adjacentConnectors = adjacentConnectors;
        this.isWheelChairAccessible = isWheelChairAccessible;
    }

    //Constructor for outside objects
    public RoutingObjects(String name, ObjectType type, LatLng latLng, String[] adjacentConnectors, String isWheelChairAccessible) {
        this.name = name;
        this.type = type;
        this.latLng = latLng;
        this.adjacentConnectors = adjacentConnectors;
        this.isWheelChairAccessible = isWheelChairAccessible;
    }

    //Constructor for Rooms
    public RoutingObjects(String name, int lane, String building, int gMapLevel, int actualLevel, LatLng latLng, String[] laneConnectors) {
        this.name = name;
        this.lane = lane;
        this.building = building;
        this.gMapLevel = gMapLevel;
        this.actualLevel = actualLevel;
        this.latLng = latLng;
        this.laneConnectors = laneConnectors;
    }

    //Constructor for buildings
    public RoutingObjects(String name, boolean hasStairs, boolean hasElevators, boolean wheelChairAccessible) {
        this.name = name;
        this.hasStairs = hasStairs;
        this.hasElevators = hasElevators;
        this.wheelChairAccessible = wheelChairAccessible;
    }

    public RoutingObjects() {
    }


    //Connectors and Rooms
    public String getName() {
        return name;
    }

    public int getgMapLevel() {
        return gMapLevel;
    }

    public LatLng getLatLng() {
        return latLng;
    }

    public int getActualLevel() {
        return actualLevel;
    }

    //Just Connectors
    public int[] getPrimeLanes() {
        return primeLanes;
    }

    public String[] getAdjacentConnectors() {
        return adjacentConnectors;
    }

    public ObjectType getType() {
        return type;
    }

    public String getIsWheelChairAccessible() {
        return isWheelChairAccessible;
    }

    //Just Rooms
    public String[] getLaneConnectors() {
        return laneConnectors;
    }

    public int getLane() {
        return lane;
    }

    public String getBuilding() {
        return building;
    }

    public RoutingObjects getBuildingObject() {
        return getBuilding(building);
    }

    //Buildings
    public boolean isBuildingWheelChairAccessible() {
        return wheelChairAccessible;
    }

    public boolean hasStairs() {
        return hasStairs;
    }

    public boolean hasElevators() {
        return hasElevators;
    }

    public static HashMap<String, ArrayList<RoutingObjects>> connectorMapInit(String building, int actualLevel) {
        HashMap<String, ArrayList<RoutingObjects>> connectorMap = new HashMap<>();
        switch (building) {
            case COLLEGE:
                connectorMap.put(COLLEGE, getCollegeConnectors(actualLevel));
                break;
            case HATHCROFT:
                connectorMap.put(HATHCROFT, getHatchcroftConnectors(actualLevel));
                break;
            case WILLIAMS:
                connectorMap.put(WILLIAMS, getWilliamsConnectors(actualLevel));
                break;
            case SHEPPARDLIBRARY:
                connectorMap.put(SHEPPARDLIBRARY, getShepherdConnectors(actualLevel));
                break;
            case GROVE_BLOCK_A:
                connectorMap.put(GROVE_BLOCK_A, getGroveBlockAConnectors(actualLevel));
                break;
            case GROVE_BLOCK_B:
                connectorMap.put(GROVE_BLOCK_B, getGroveBlockBConnectors(actualLevel));
                break;
            case GROVE_BLOCK_C:
                connectorMap.put(GROVE_BLOCK_C, getGroveBlockCConnectors(actualLevel));
                break;
            case MDXHOUSE:
                connectorMap.put(MDXHOUSE, getMDXHouseConnectors(actualLevel));
                break;
            case BUILDING9:
                connectorMap.put(BUILDING9, getBuilding9Connetors());
                break;
            case BUILDING10:
                connectorMap.put(BUILDING10, getBuilding10Connetors(actualLevel));
            case VINE:
                connectorMap.put(VINE, getVineConnectors(actualLevel));
                break;
            case PORTACABIN_A:
                connectorMap.put(PORTACABIN_A, getPortacabinAConnectors(actualLevel));
                break;
            case PORTACABIN_A_EXT:
                connectorMap.put(PORTACABIN_A_EXT, getPortacabinAEXTConnectors());
                break;
            case PORTACABIN_B:
                connectorMap.put(PORTACABIN_B, getPortacabinBConnectors());
            case PORTACABIN_67:
                connectorMap.put(PORTACABIN_67, getPortacabin67Connectors());
                break;
            case BARN:
                connectorMap.put(BARN, getBarnConnectors());
                break;
            case CIRCLE_CAFE:
                connectorMap.put(CIRCLE_CAFE, getCircleCafeConnectors());
                break;
        }
        return connectorMap;
    }
    
       /*
        * IMPORTANT: Adjacent connectors string array for DOORS
        * Position 0 = Name of adjacent connector inside the building
        * Position 1 = Name of adjacent connector outside the building
        *
        * Prime Lanes int array for DOORS
        * Position 0 = Lane inside the building which the door belongs to
        * */

    private static ArrayList<RoutingObjects> getCollegeConnectors(int actualLevel) {
        ArrayList<RoutingObjects> list = new ArrayList<>();

        switch (actualLevel) {
            case 0:
                //Ground floor connectors, stairs, elevators and doors
                list.add(new RoutingObjects("A", BASIC_CONNECTOR, new int[]{1, 12}, 2, 0, new LatLng(51.589945, -0.228274), new String[]{"B", "U"}, "Y"));
                list.add(new RoutingObjects("B", BASIC_CONNECTOR, new int[]{1, 4}, 2, 0, new LatLng(51.589977, -0.228560), new String[]{"A", "C", "S"}, "Y"));
                list.add(new RoutingObjects("C", BASIC_CONNECTOR, new int[]{1, 5}, 2, 0, new LatLng(51.590003, -0.228806), new String[]{"B", "V", "Q"}, "Y"));
                list.add(new RoutingObjects("D", BASIC_CONNECTOR, new int[]{1, 6}, 2, 0, new LatLng(51.590030, -0.229051), new String[]{"V", "E", "O"}, "Y"));
                list.add(new RoutingObjects("E", BASIC_CONNECTOR, new int[]{1, 7}, 2, 0, new LatLng(51.590054, -0.229273), new String[]{"D", "I", "Z"}, "Y"));
                list.add(new RoutingObjects("G", BASIC_CONNECTOR, new int[]{8, 14}, 2, 0, new LatLng(51.589910, -0.229450), new String[]{"H", "J"}, "Y"));
                list.add(new RoutingObjects("H", BASIC_CONNECTOR, new int[]{14}, 2, 0, new LatLng(51.589903, -0.229378), new String[]{"G", "I"}, "N"));
                list.add(new RoutingObjects("I", BASIC_CONNECTOR, new int[]{7, 14}, 2, 0, new LatLng(51.589898, -0.229318), new String[]{"E", "H", "M"}, "Y"));
                list.add(new RoutingObjects("J", BASIC_CONNECTOR, new int[]{8, 10}, 2, 0, new LatLng(51.589819, -0.229477), new String[]{"G", "L"}, "Y"));
                list.add(new RoutingObjects("K", BASIC_CONNECTOR, new int[]{3, 9}, 2, 0, new LatLng(51.589703, -0.229464), new String[]{"L", "N", "Y"}, "Y"));
                list.add(new RoutingObjects("L", BASIC_CONNECTOR, new int[]{9, 10}, 2, 0, new LatLng(51.589809, -0.229434), new String[]{"J", "K", "M"}, "Y"));
                list.add(new RoutingObjects("M", BASIC_CONNECTOR, new int[]{7, 10}, 2, 0, new LatLng(51.589802, -0.229347), new String[]{"I", "L"}, "Y"));
                list.add(new RoutingObjects("N", BASIC_CONNECTOR, new int[]{3, 6}, 2, 0, new LatLng(51.589668, -0.229155), new String[]{"K", "O", "X"}, "Y"));
                list.add(new RoutingObjects("O", BASIC_CONNECTOR, new int[]{2, 6}, 2, 0, new LatLng(51.589850, -0.229099), new String[]{"D", "N", "W"}, "Y"));
                list.add(new RoutingObjects("P", BASIC_CONNECTOR, new int[]{3, 5}, 2, 0, new LatLng(51.589639, -0.228897), new String[]{"X", "Q", "R"}, "Y"));
                list.add(new RoutingObjects("Q", BASIC_CONNECTOR, new int[]{2, 5}, 2, 0, new LatLng(51.589825, -0.228839), new String[]{"C", "W", "P", "S"}, "Y"));
                list.add(new RoutingObjects("R", BASIC_CONNECTOR, new int[]{3, 4}, 2, 0, new LatLng(51.589610, -0.228666), new String[]{"P", "S", "T"}, "Y"));
                list.add(new RoutingObjects("S", BASIC_CONNECTOR, new int[]{2, 4}, 2, 0, new LatLng(51.589806, -0.228613), new String[]{"B", "Q", "R"}, "Y"));
                list.add(new RoutingObjects("T", BASIC_CONNECTOR, new int[]{3, 13}, 2, 0, new LatLng(51.589582, -0.228378), new String[]{"R"}, "Y"));
                list.add(new RoutingObjects("U", BASIC_CONNECTOR, new int[]{12, 15}, 2, 0, new LatLng(51.590054, -0.228247), new String[]{"A"}, "Y"));
                list.add(new RoutingObjects("V", BASIC_CONNECTOR, new int[]{1, 16}, 2, 0, new LatLng(51.590022, -0.228981), new String[]{"C", "D"}, "Y"));
                list.add(new RoutingObjects("W", BASIC_CONNECTOR, new int[]{2, 16, 17}, 2, 0, new LatLng(51.589844, -0.229032), new String[]{"O", "Q"}, "Y"));
                list.add(new RoutingObjects("X", BASIC_CONNECTOR, new int[]{3, 17}, 2, 0, new LatLng(51.589659, -0.229084), new String[]{"N", "P"}, "Y"));
                list.add(new RoutingObjects("Y", BASIC_CONNECTOR, new int[]{3}, 2, 0, new LatLng(51.589723, -0.229636), new String[]{"K"}, "Y"));
                list.add(new RoutingObjects("Z", BASIC_CONNECTOR, new int[]{1}, 2, 0, new LatLng(51.590086, -0.229538), new String[]{"E"}, "Y"));

                list.add(new RoutingObjects("S1", STAIR, new int[]{3}, 2, 0, new LatLng(51.589662, -0.229134), new String[]{"null"}, "N"));
                list.add(new RoutingObjects("S2", STAIR, new int[]{1}, 2, 0, new LatLng(51.590033, -0.229028), new String[]{"null"}, "N"));
                list.add(new RoutingObjects("S3", STAIR, new int[]{13}, 2, 0, new LatLng(51.589615, -0.228371), new String[]{"null"}, "N"));
                list.add(new RoutingObjects("S4", STAIR, new int[]{12}, 2, 0, new LatLng(51.589921, -0.228284), new String[]{"null"}, "N"));
                list.add(new RoutingObjects("S5", STAIR, new int[]{4}, 2, 0, new LatLng(51.589675, -0.228648), new String[]{"null"}, "N"));
                list.add(new RoutingObjects("S6", STAIR, new int[]{4}, 2, 0, new LatLng(51.589918, -0.228575), new String[]{"null"}, "N"));

                list.add(new RoutingObjects("E1", ELEVATOR, new int[]{5}, 2, 0, new LatLng(51.589727, -0.228875), new String[]{"null"}, "Y"));
                list.add(new RoutingObjects("E2", ELEVATOR, new int[]{1}, 2, 0, new LatLng(51.590027, -0.228994), new String[]{"null"}, "Y"));

                list.add(new RoutingObjects("D1", DOOR, new int[]{6}, 2, 0, new LatLng(51.590099, -0.229035), new String[]{"D", "K"}, "Y"));
                list.add(new RoutingObjects("D2", DOOR, new int[]{6}, 2, 0, new LatLng(51.589599, -0.229185), new String[]{"N", "R"}, "Y"));
                list.add(new RoutingObjects("D3", DOOR, new int[]{3}, 2, 0, new LatLng(51.589731, -0.229684), new String[]{"Y", "O"}, "N"));
                list.add(new RoutingObjects("D4", DOOR, new int[]{1}, 2, 0, new LatLng(51.590091, -0.229583), new String[]{"Z", "N"}, "N"));
                list.add(new RoutingObjects("D5", DOOR, new int[]{15}, 2, 0, new LatLng(51.590084, -0.228142), new String[]{"U", "A"}, "N"));
                break;
            case 1:
                //First floor connectors, stairs and elevators
                list.add(new RoutingObjects("A", BASIC_CONNECTOR, new int[]{1, 3}, 1, 1, new LatLng(51.589943, -0.228275), new String[]{"B", "G"}, "Y"));
                list.add(new RoutingObjects("B", BASIC_CONNECTOR, new int[]{1, 4}, 1, 1, new LatLng(51.589988, -0.228687), new String[]{"A", "D", "N"}, "Y"));
                list.add(new RoutingObjects("C", BASIC_CONNECTOR, new int[]{1, 6}, 1, 1, new LatLng(51.590015, -0.228915), new String[]{"N", "F", "O"}, "Y"));
                list.add(new RoutingObjects("D", BASIC_CONNECTOR, new int[]{4, 5}, 1, 1, new LatLng(51.589949, -0.228696), new String[]{"B", "M"}, "Y"));
                list.add(new RoutingObjects("E", BASIC_CONNECTOR, new int[]{2, 11}, 1, 1, new LatLng(51.589698, -0.229416), new String[]{"P", "J"}, "Y"));
                list.add(new RoutingObjects("F", BASIC_CONNECTOR, new int[]{6}, 1, 1, new LatLng(51.589836, -0.228956), new String[]{"C", "J", "L", "M"}, "Y"));
                list.add(new RoutingObjects("G", BASIC_CONNECTOR, new int[]{2, 3}, 1, 1, new LatLng(51.589582, -0.228378), new String[]{"A", "H"}, "Y"));
                list.add(new RoutingObjects("H", BASIC_CONNECTOR, new int[]{2, 8}, 1, 1, new LatLng(51.589628, -0.228792), new String[]{"G", "I", "K"}, "Y"));
                list.add(new RoutingObjects("I", BASIC_CONNECTOR, new int[]{7, 8}, 1, 1, new LatLng(51.589669, -0.228773), new String[]{"H", "L"}, "Y"));
                list.add(new RoutingObjects("J", BASIC_CONNECTOR, new int[]{2, 6}, 1, 1, new LatLng(51.589654, -0.229015), new String[]{"E", "F", "K"}, "Y"));
                list.add(new RoutingObjects("K", BASIC_CONNECTOR, new int[]{2, 9}, 1, 1, new LatLng(51.589642, -0.228922), new String[]{"H", "J", "L"}, "Y"));
                list.add(new RoutingObjects("L", BASIC_CONNECTOR, new int[]{7, 9}, 1, 1, new LatLng(51.589724, -0.228898), new String[]{"F", "I", "K"}, "Y"));
                list.add(new RoutingObjects("M", BASIC_CONNECTOR, new int[]{5, 10}, 1, 1, new LatLng(51.589931, -0.228836), new String[]{"D", "F", "N"}, "Y"));
                list.add(new RoutingObjects("N", BASIC_CONNECTOR, new int[]{1, 10}, 1, 1, new LatLng(51.590005, -0.228819), new String[]{"B", "C", "M"}, "Y"));
                list.add(new RoutingObjects("O", BASIC_CONNECTOR, new int[]{1, 13}, 1, 1, new LatLng(51.590066, -0.229359), new String[]{"Q", "C"}, "Y"));
                list.add(new RoutingObjects("P", BASIC_CONNECTOR, new int[]{12, 11}, 1, 1, new LatLng(51.590033, -0.229321), new String[]{"E", "Q"}, "Y"));
                list.add(new RoutingObjects("Q", BASIC_CONNECTOR, new int[]{12, 13}, 1, 1, new LatLng(51.590037, -0.229370), new String[]{"O", "P"}, "Y"));

                list.add(new RoutingObjects("S1", STAIR, new int[]{2}, 1, 1, new LatLng(51.589662, -0.229134), new String[]{"null"}, "N"));
                list.add(new RoutingObjects("S2", STAIR, new int[]{1}, 1, 1, new LatLng(51.590033, -0.229028), new String[]{"null"}, "N"));
                list.add(new RoutingObjects("S3", STAIR, new int[]{3}, 1, 1, new LatLng(51.589615, -0.228371), new String[]{"null"}, "N"));
                list.add(new RoutingObjects("S4", STAIR, new int[]{3}, 1, 1, new LatLng(51.589921, -0.228284), new String[]{"null"}, "N"));
                list.add(new RoutingObjects("S5", STAIR, new int[]{7, 8}, 1, 1, new LatLng(51.589668, -0.228775), new String[]{"null"}, "N"));
                list.add(new RoutingObjects("S6", STAIR, new int[]{4, 5}, 1, 1, new LatLng(51.589947, -0.228689), new String[]{"null"}, "N"));

                list.add(new RoutingObjects("E1", ELEVATOR, new int[]{7, 9}, 1, 1, new LatLng(51.589727, -0.228875), new String[]{"null"}, "Y"));
                list.add(new RoutingObjects("E2", ELEVATOR, new int[]{1}, 1, 1, new LatLng(51.590027, -0.228994), new String[]{"null"}, "Y"));
                break;
            case 2:
                list.add(new RoutingObjects("A", BASIC_CONNECTOR, new int[]{1, 2}, 0, 2, new LatLng(51.589583, -0.228376), new String[]{"A"}, "Y"));
                list.add(new RoutingObjects("B", BASIC_CONNECTOR, new int[]{3, 2}, 0, 2, new LatLng(51.589647, -0.228961), new String[]{"A", "C", "T"}, "Y"));
                list.add(new RoutingObjects("C", BASIC_CONNECTOR, new int[]{5, 2}, 0, 2, new LatLng(51.589650, -0.229003), new String[]{"B", "D", "Q"}, "Y"));
                list.add(new RoutingObjects("D", BASIC_CONNECTOR, new int[]{6, 2}, 0, 2, new LatLng(51.589656, -0.229049), new String[]{"F", "C", "G"}, "Y"));
                list.add(new RoutingObjects("F", BASIC_CONNECTOR, new int[]{2, 10}, 0, 2, new LatLng(51.589675, -0.229197), new String[]{"D", "J"}, "N"));
                list.add(new RoutingObjects("G", BASIC_CONNECTOR, new int[]{6, 7}, 0, 2, new LatLng(51.589682, -0.229042), new String[]{"D", "H"}, "Y"));
                list.add(new RoutingObjects("H", BASIC_CONNECTOR, new int[]{8, 7}, 0, 2, new LatLng(51.589694, -0.229150), new String[]{"G", "I"}, "Y"));
                list.add(new RoutingObjects("I", BASIC_CONNECTOR, new int[]{8, 9}, 0, 2, new LatLng(51.589807, -0.229117), new String[]{"H", "J"}, "Y"));
                list.add(new RoutingObjects("J", BASIC_CONNECTOR, new int[]{10, 9}, 0, 2, new LatLng(51.589813, -0.229155), new String[]{"F", "I", "K"}, "Y"));
                list.add(new RoutingObjects("K", BASIC_CONNECTOR, new int[]{10, 11}, 0, 2, new LatLng(51.590038, -0.229089), new String[]{"J", "L"}, "N"));
                list.add(new RoutingObjects("L", BASIC_CONNECTOR, new int[]{5, 11}, 0, 2, new LatLng(51.590018, -0.228909), new String[]{"O", "K", "M"}, "Y"));
                list.add(new RoutingObjects("M", BASIC_CONNECTOR, new int[]{12, 11}, 0, 2, new LatLng(51.590008, -0.228827), new String[]{"L", "N"}, "Y"));
                list.add(new RoutingObjects("N", BASIC_CONNECTOR, new int[]{12, 13}, 0, 2, new LatLng(51.589948, -0.228838), new String[]{"O", "M"}, "Y"));
                list.add(new RoutingObjects("O", BASIC_CONNECTOR, new int[]{5}, 0, 2, new LatLng(51.589937, -0.228931), new String[]{"Q", "N", "L"}, "Y"));
                list.add(new RoutingObjects("Q", BASIC_CONNECTOR, new int[]{5}, 0, 2, new LatLng(51.589738, -0.228989), new String[]{"C", "O", "R"}, "Y"));
                list.add(new RoutingObjects("R", BASIC_CONNECTOR, new int[]{3, 14}, 0, 2, new LatLng(51.589733, -0.228938), new String[]{"Q", "T"}, "Y"));
                list.add(new RoutingObjects("T", BASIC_CONNECTOR, new int[]{3, 4}, 0, 2, new LatLng(51.589712, -0.228942), new String[]{"B", "R"}, "Y"));

                list.add(new RoutingObjects("E1", ELEVATOR, new int[]{14}, 0, 2, new LatLng(51.589726, -0.228853), new String[]{"null"}, "Y"));
                list.add(new RoutingObjects("E2", ELEVATOR, new int[]{11}, 0, 2, new LatLng(51.590028, -0.228982), new String[]{"null"}, "Y"));


                list.add(new RoutingObjects("S1", STAIR, new int[]{2}, 0, 2, new LatLng(51.589666, -0.229138), new String[]{"null"}, "N"));
                list.add(new RoutingObjects("S2", STAIR, new int[]{11}, 0, 2, new LatLng(51.590033, -0.229046), new String[]{"null"}, "N"));
                list.add(new RoutingObjects("S3", STAIR, new int[]{1}, 0, 2, new LatLng(51.589615, -0.228367), new String[]{"null"}, "N"));
                list.add(new RoutingObjects("S5", STAIR, new int[]{4}, 0, 2, new LatLng(51.589682, -0.228869), new String[]{"null"}, "N"));
                list.add(new RoutingObjects("S6", STAIR, new int[]{13}, 0, 2, new LatLng(51.589957, -0.228789), new String[]{"null"}, "N"));
                break;
        }

        return list;
    }

    private static ArrayList<RoutingObjects> getHatchcroftConnectors(int actualLevel) {
        ArrayList<RoutingObjects> list = new ArrayList<>();
        switch (actualLevel) {
            case 0:
                list.add(new RoutingObjects("A", BASIC_CONNECTOR, new int[]{1, 2}, 2, 0, new LatLng(51.589162, -0.229212), new String[]{"B", "G", "H"}, "Y"));
                list.add(new RoutingObjects("B", BASIC_CONNECTOR, new int[]{2, 3}, 2, 0, new LatLng(51.588936, -0.229291), new String[]{"A"}, "Y"));
                list.add(new RoutingObjects("C", BASIC_CONNECTOR, new int[]{1, 4}, 2, 0, new LatLng(51.589107, -0.228772), new String[]{"G", "D"}, "Y"));
                list.add(new RoutingObjects("D", BASIC_CONNECTOR, new int[]{4, 5}, 2, 0, new LatLng(51.589090, -0.228778), new String[]{"C", "F"}, "Y"));
                list.add(new RoutingObjects("D", BASIC_CONNECTOR, new int[]{4, 5}, 2, 0, new LatLng(51.589090, -0.228778), new String[]{"C", "F"}, "Y"));
                list.add(new RoutingObjects("E", BASIC_CONNECTOR, new int[]{6, 7}, 2, 0, new LatLng(51.589096, -0.228650), new String[]{"F"}, "Y"));
                list.add(new RoutingObjects("F", BASIC_CONNECTOR, new int[]{5, 6}, 2, 0, new LatLng(51.589077, -0.228657), new String[]{"D", "E"}, "Y"));
                list.add(new RoutingObjects("G", BASIC_CONNECTOR, new int[]{1, 8}, 2, 0, new LatLng(51.589158, -0.229166), new String[]{"A", "C"}, "Y"));
                list.add(new RoutingObjects("H", BASIC_CONNECTOR, new int[]{1, 9}, 2, 0, new LatLng(51.589177, -0.229386), new String[]{"A"}, "Y"));


                list.add(new RoutingObjects("S1", STAIR, new int[]{8}, 2, 0, new LatLng(51.589233, -0.229137), new String[]{"null"}, "N"));
                list.add(new RoutingObjects("S2", STAIR, new int[]{7}, 2, 0, new LatLng(51.589073, -0.228539), new String[]{"null"}, "N"));
                list.add(new RoutingObjects("S3", STAIR, new int[]{1}, 2, 0, new LatLng(51.589218, -0.229718), new String[]{"null"}, "N"));
                list.add(new RoutingObjects("S4", STAIR, new int[]{3}, 2, 0, new LatLng(51.588936, -0.229256), new String[]{"null"}, "N"));

                list.add(new RoutingObjects("E1", ELEVATOR, new int[]{1}, 2, 0, new LatLng(51.589152, -0.229131), new String[]{"null"}, "Y"));
                list.add(new RoutingObjects("E2", ELEVATOR, new int[]{9}, 2, 0, new LatLng(51.589157, -0.229394), new String[]{"null"}, "Y"));

                list.add(new RoutingObjects("D1", DOOR, new int[]{2}, 2, 0, new LatLng(51.589222, -0.229192), new String[]{"A", "S"}, "Y"));
                list.add(new RoutingObjects("D2", DOOR, new int[]{3}, 2, 0, new LatLng(51.588942, -0.229339), new String[]{"B", "V"}, "Y"));
                break;

            case 1:
                list.add(new RoutingObjects("A", BASIC_CONNECTOR, new int[]{1, 6}, 1, 1, new LatLng(51.589208, -0.229651), new String[]{"C"}, "Y"));
                list.add(new RoutingObjects("B", BASIC_CONNECTOR, new int[]{1, 2}, 1, 1, new LatLng(51.589154, -0.229159), new String[]{"F"}, "Y"));
                list.add(new RoutingObjects("C", BASIC_CONNECTOR, new int[]{1, 5}, 1, 1, new LatLng(51.589186, -0.229466), new String[]{"A", "D", "G"}, "Y"));
                list.add(new RoutingObjects("D", BASIC_CONNECTOR, new int[]{4, 5}, 1, 1, new LatLng(51.589003, -0.229521), new String[]{"C", "E"}, "Y"));
                list.add(new RoutingObjects("E", BASIC_CONNECTOR, new int[]{3, 4}, 1, 1, new LatLng(51.588973, -0.229280), new String[]{"F", "D"}, "Y"));
                list.add(new RoutingObjects("F", BASIC_CONNECTOR, new int[]{1, 3}, 1, 1, new LatLng(51.589162, -0.229229), new String[]{"G", "B", "E"}, "Y"));
                list.add(new RoutingObjects("G", BASIC_CONNECTOR, new int[]{1, 7}, 1, 1, new LatLng(51.589177, -0.229387), new String[]{"C", "F"}, "Y"));

                list.add(new RoutingObjects("S1", STAIR, new int[]{2}, 1, 1, new LatLng(51.589229, -0.229137), new String[]{"null"}, "N"));
                list.add(new RoutingObjects("S2", STAIR, new int[]{1}, 1, 1, new LatLng(51.589082, -0.228566), new String[]{"null"}, "N"));
                list.add(new RoutingObjects("S3", STAIR, new int[]{1}, 1, 1, new LatLng(51.589221, -0.229723), new String[]{"null"}, "N"));
                list.add(new RoutingObjects("S4", STAIR, new int[]{3}, 1, 1, new LatLng(51.589024, -0.229266), new String[]{"null"}, "N"));

                list.add(new RoutingObjects("E1", ELEVATOR, new int[]{1}, 1, 1, new LatLng(51.589152, -0.229131), new String[]{"null"}, "Y"));
                list.add(new RoutingObjects("E2", ELEVATOR, new int[]{7}, 1, 1, new LatLng(51.589157, -0.229394), new String[]{"null"}, "Y"));
                break;

            case 2:
                list.add(new RoutingObjects("A", BASIC_CONNECTOR, new int[]{1, 2}, 0, 2, new LatLng(51.589153, -0.229163), new String[]{"B"}, "Y"));
                list.add(new RoutingObjects("B", BASIC_CONNECTOR, new int[]{1, 3}, 0, 2, new LatLng(51.589177, -0.229388), new String[]{"A"}, "Y"));

                list.add(new RoutingObjects("S1", STAIR, new int[]{2}, 0, 2, new LatLng(51.589229, -0.229137), new String[]{"null"}, "N"));
                list.add(new RoutingObjects("S2", STAIR, new int[]{1}, 0, 2, new LatLng(51.589082, -0.228566), new String[]{"null"}, "N"));
                list.add(new RoutingObjects("S3", STAIR, new int[]{1}, 0, 2, new LatLng(51.589221, -0.229723), new String[]{"null"}, "N"));

                list.add(new RoutingObjects("E1", ELEVATOR, new int[]{1}, 0, 2, new LatLng(51.589152, -0.229131), new String[]{"null"}, "Y"));
                list.add(new RoutingObjects("E2", ELEVATOR, new int[]{3}, 0, 2, new LatLng(51.589157, -0.229394), new String[]{"null"}, "Y"));
                break;

        }

        return list;
    }

    private static ArrayList<RoutingObjects> getWilliamsConnectors(int actualLevel) {
        ArrayList<RoutingObjects> list = new ArrayList<>();
        switch (actualLevel) {
            case 0:
                list.add(new RoutingObjects("A", BASIC_CONNECTOR, new int[]{1, 2}, 1, 0, new LatLng(51.590663, -0.228260), new String[]{"B", "J", "M"}, "Y"));
                list.add(new RoutingObjects("B", BASIC_CONNECTOR, new int[]{2, 3}, 1, 0, new LatLng(51.590649, -0.228101), new String[]{"A", "C"}, "Y"));
                list.add(new RoutingObjects("C", BASIC_CONNECTOR, new int[]{3, 4}, 1, 0, new LatLng(51.590400, -0.228173), new String[]{"B", "L"}, "Y"));
                list.add(new RoutingObjects("D", BASIC_CONNECTOR, new int[]{4, 10}, 1, 0, new LatLng(51.590444, -0.228568), new String[]{"L", "E", "K"}, "Y"));
                list.add(new RoutingObjects("E", BASIC_CONNECTOR, new int[]{4, 5}, 1, 0, new LatLng(51.590463, -0.228728), new String[]{"D", "H", "G"}, "Y"));
                //list.add(new RoutingObjects("F", BASIC_CONNECTOR, new int[] {4, 6}, 1, 0, new LatLng(51.590479, -0.228873), new String[] {"E", "H"}, "Y"));
                list.add(new RoutingObjects("G", BASIC_CONNECTOR, new int[]{5, 8}, 1, 0, new LatLng(51.590505, -0.228718), new String[]{"E", "I"}, "Y"));
                list.add(new RoutingObjects("H", BASIC_CONNECTOR, new int[]{4, 7}, 1, 0, new LatLng(51.590524, -0.229225), new String[]{"E", "I"}, "Y"));
                list.add(new RoutingObjects("I", BASIC_CONNECTOR, new int[]{7, 8}, 1, 0, new LatLng(51.590562, -0.229212), new String[]{"G", "H"}, "Y"));
                list.add(new RoutingObjects("J", BASIC_CONNECTOR, new int[]{1, 9}, 1, 0, new LatLng(51.590575, -0.228284), new String[]{"A"}, "Y"));
                list.add(new RoutingObjects("K", BASIC_CONNECTOR, new int[]{10}, 1, 0, new LatLng(51.590516, -0.228559), new String[]{"D"}, "Y"));
                list.add(new RoutingObjects("L", BASIC_CONNECTOR, new int[]{4, 11}, 1, 0, new LatLng(51.590420, -0.228379), new String[]{"C", "D"}, "Y"));
                list.add(new RoutingObjects("M", BASIC_CONNECTOR, new int[]{1}, 1, 0, new LatLng(51.590731, -0.228240), new String[]{"A"}, "Y"));

                list.add(new RoutingObjects("S1", STAIR, new int[]{10}, 1, 0, new LatLng(51.590492, -0.228563), new String[]{"null"}, "N"));
                list.add(new RoutingObjects("S2", STAIR, new int[]{11}, 1, 0, new LatLng(51.590396, -0.228385), new String[]{"null"}, "N"));
                list.add(new RoutingObjects("S3", STAIR, new int[]{9}, 1, 0, new LatLng(51.590572, -0.228343), new String[]{"null"}, "N"));

                list.add(new RoutingObjects("E1", ELEVATOR, new int[]{10}, 1, 0, new LatLng(51.590478, -0.228537), new String[]{"null"}, "Y"));

                list.add(new RoutingObjects("D1", DOOR, new int[]{3}, 1, 0, new LatLng(51.590358, -0.228184), new String[]{"C", "B"}, "Y"));
                list.add(new RoutingObjects("D2", DOOR, new int[]{10}, 1, 0, new LatLng(51.590406, -0.228582), new String[]{"D", "C"}, "Y"));
                //list.add(new RoutingObjects("D3", DOOR, new int[] {6}, 1, 0, new LatLng(51.590431, -0.228888), new String[] {"F", "D"}, "N"));
                list.add(new RoutingObjects("D4", DOOR, new int[]{10}, 1, 0, new LatLng(51.590538, -0.228547), new String[]{"K", "I1"}, "N"));
                list.add(new RoutingObjects("D5", DOOR, new int[]{1}, 1, 0, new LatLng(51.590566, -0.228286), new String[]{"J", "S1"}, "Y"));
                list.add(new RoutingObjects("D6", DOOR, new int[]{1}, 1, 0, new LatLng(51.590738, -0.228239), new String[]{"M", "T1"}, "N"));
                break;

            case 1:
                list.add(new RoutingObjects("A", BASIC_CONNECTOR, new int[]{1, 3}, 0, 1, new LatLng(51.590629, -0.228278), new String[]{"B", "C"}, "Y"));
                list.add(new RoutingObjects("B", BASIC_CONNECTOR, new int[]{1, 2}, 0, 1, new LatLng(51.590586, -0.228294), new String[]{"A"}, "Y"));
                list.add(new RoutingObjects("C", BASIC_CONNECTOR, new int[]{3, 4}, 0, 1, new LatLng(51.590608, -0.228093), new String[]{"A", "D"}, "Y"));
                list.add(new RoutingObjects("D", BASIC_CONNECTOR, new int[]{4, 5}, 0, 1, new LatLng(51.590413, -0.228157), new String[]{"C", "F"}, "Y"));
                list.add(new RoutingObjects("E", BASIC_CONNECTOR, new int[]{5, 6}, 0, 1, new LatLng(51.590460, -0.228555), new String[]{"F"}, "Y"));
                list.add(new RoutingObjects("F", BASIC_CONNECTOR, new int[]{5, 7}, 0, 1, new LatLng(51.590441, -0.228396), new String[]{"E", "D"}, "Y"));

                list.add(new RoutingObjects("S1", STAIR, new int[]{6}, 0, 1, new LatLng(51.590495, -0.228555), new String[]{"null"}, "N"));
                list.add(new RoutingObjects("S2", STAIR, new int[]{7}, 0, 1, new LatLng(51.590400, -0.228409), new String[]{"null"}, "N"));
                list.add(new RoutingObjects("S3", STAIR, new int[]{2}, 0, 1, new LatLng(51.590593, -0.228351), new String[]{"null"}, "N"));

                list.add(new RoutingObjects("E1", ELEVATOR, new int[]{6}, 0, 1, new LatLng(51.590478, -0.228537), new String[]{"null"}, "Y"));
                break;
        }

        return list;
    }

    private static ArrayList<RoutingObjects> getMDXHouseConnectors(int actualLevel) {
        ArrayList<RoutingObjects> list = new ArrayList<>();
        switch (actualLevel) {
            case -1:
                list.add(new RoutingObjects("A", BASIC_CONNECTOR, new int[]{1}, 1, -1, new LatLng(51.590121, -0.230623), new String[]{"B"}, "Y"));
                list.add(new RoutingObjects("B", BASIC_CONNECTOR, new int[]{1, 2}, 1, -1, new LatLng(51.590129, -0.230725), new String[]{"A", "C"}, "Y"));
                list.add(new RoutingObjects("C", BASIC_CONNECTOR, new int[]{2, 3}, 1, -1, new LatLng(51.589826, -0.230786), new String[]{"B"}, "Y"));

                list.add(new RoutingObjects("S1", STAIR, new int[]{1}, 1, -1, new LatLng(51.590124, -0.230585), new String[]{"null"}, "N"));

                list.add(new RoutingObjects("E1", ELEVATOR, new int[]{2}, 1, -1, new LatLng(51.590152, -0.230719), new String[]{"null"}, "Y"));
                break;
            case 0:
                list.add(new RoutingObjects("A", BASIC_CONNECTOR, new int[]{1, 2}, 0, 0, new LatLng(51.590136, -0.230650), new String[]{"B", "C"}, "Y"));
                list.add(new RoutingObjects("B", BASIC_CONNECTOR, new int[]{1, 3}, 0, 0, new LatLng(51.590144, -0.230771), new String[]{"A"}, "Y"));
                list.add(new RoutingObjects("C", BASIC_CONNECTOR, new int[]{2, 4}, 0, 0, new LatLng(51.590112, -0.230654), new String[]{"A"}, "Y"));

                list.add(new RoutingObjects("S1", STAIR, new int[]{4}, 0, 0, new LatLng(51.590108, -0.230594), new String[]{"null"}, "N"));
                list.add(new RoutingObjects("E1", ELEVATOR, new int[]{3}, 0, 0, new LatLng(51.590185, -0.230748), new String[]{"null"}, "Y"));

                list.add(new RoutingObjects("D1", DOOR, new int[]{1}, 0, 0, new LatLng(51.590134, -0.230584), new String[]{"A", "D1"}, "Y"));
                break;
        }

        return list;
    }

    private static ArrayList<RoutingObjects> getBuilding9Connetors() {
        ArrayList<RoutingObjects> list = new ArrayList<>();
        list.add(new RoutingObjects("A", BASIC_CONNECTOR, new int[]{1, 2}, 0, 0, new LatLng(51.588713, -0.229279), new String[]{"B", "C"}, "Y"));
        list.add(new RoutingObjects("B", BASIC_CONNECTOR, new int[]{2, 3}, 0, 0, new LatLng(51.588765, -0.229443), new String[]{"A", "D"}, "Y"));
        list.add(new RoutingObjects("C", BASIC_CONNECTOR, new int[]{1}, 0, 0, new LatLng(51.588608, -0.229364), new String[]{"A"}, "Y"));
        list.add(new RoutingObjects("D", BASIC_CONNECTOR, new int[]{3}, 0, 0, new LatLng(51.588660, -0.229530), new String[]{"B"}, "Y"));

        list.add(new RoutingObjects("D1", DOOR, new int[]{3}, 0, 0, new LatLng(51.588651, -0.229538), new String[]{"D", "R1"}, "Y"));
        list.add(new RoutingObjects("D2", DOOR, new int[]{1}, 0, 0, new LatLng(51.588601, -0.229370), new String[]{"C", "Z1"}, "Y"));

        return list;
    }

    private static ArrayList<RoutingObjects> getBuilding10Connetors(int actualLevel) {
        ArrayList<RoutingObjects> list = new ArrayList<>();
        switch (actualLevel) {
            case 0:
                list.add(new RoutingObjects("A", BASIC_CONNECTOR, new int[]{1}, 1, 0, new LatLng(51.589812, -0.229875), new String[]{"B"}, "Y"));
                list.add(new RoutingObjects("B", BASIC_CONNECTOR, new int[]{1, 2}, 1, 0, new LatLng(51.589842, -0.229865), new String[]{"A"}, "Y"));

                list.add(new RoutingObjects("S1", STAIR, new int[]{1}, 1, 0, new LatLng(51.589866, -0.229858), new String[]{"null"}, "N"));
                list.add(new RoutingObjects("D1", DOOR, new int[]{1}, 1, 0, new LatLng(51.589806, -0.229879), new String[]{"A", "P"}, "N"));
                break;
            case 1:
                list.add(new RoutingObjects("A", BASIC_CONNECTOR, new int[]{1}, 0, 1, new LatLng(51.589845, -0.229861), new String[]{""}, "Y"));

                list.add(new RoutingObjects("S1", STAIR, new int[]{1}, 0, 1, new LatLng(51.589859, -0.229856), new String[]{"null"}, "N"));
        }

        return list;
    }

    private static ArrayList<RoutingObjects> getShepherdConnectors(int actualLevel) {
        ArrayList<RoutingObjects> list = new ArrayList<>();
        switch (actualLevel) {
            case -1:
                list.add(new RoutingObjects("A", BASIC_CONNECTOR, new int[]{1, 2}, 4, -1, new LatLng(51.590422, -0.229399), new String[]{"B"}, "Y"));
                list.add(new RoutingObjects("B", BASIC_CONNECTOR, new int[]{2, 6}, 4, -1, new LatLng(51.590438, -0.229440), new String[]{"A", "C"}, "Y"));
                list.add(new RoutingObjects("C", BASIC_CONNECTOR, new int[]{2, 3}, 4, -1, new LatLng(51.590494, -0.229566), new String[]{"B", "D"}, "Y"));
                list.add(new RoutingObjects("D", BASIC_CONNECTOR, new int[]{3, 4}, 4, -1, new LatLng(51.590564, -0.229551), new String[]{"C", "E"}, "Y"));
                list.add(new RoutingObjects("E", BASIC_CONNECTOR, new int[]{5, 4}, 4, -1, new LatLng(51.590579, -0.229718), new String[]{"D"}, "Y"));


                list.add(new RoutingObjects("S1", STAIR, new int[]{5}, 4, -1, new LatLng(51.590548, -0.229726), new String[]{"null"}, "N"));
                list.add(new RoutingObjects("S2", STAIR, new int[]{3}, 4, -1, new LatLng(51.590787, -0.229484), new String[]{"null"}, "N"));

                list.add(new RoutingObjects("E1", ELEVATOR, new int[]{6}, 4, -1, new LatLng(51.590399, -0.229477), new String[]{"null"}, "Y"));
                break;
            case 0:
                list.add(new RoutingObjects("A", BASIC_CONNECTOR, new int[]{2, 7}, 3, 0, new LatLng(51.590368, -0.229511), new String[]{"B"}, "Y"));
                list.add(new RoutingObjects("B", BASIC_CONNECTOR, new int[]{1, 2}, 3, 0, new LatLng(51.590392, -0.229569), new String[]{"A", "C"}, "Y"));
                list.add(new RoutingObjects("C", BASIC_CONNECTOR, new int[]{3, 2}, 3, 0, new LatLng(51.590469, -0.229754), new String[]{"B", "D"}, "Y"));
                list.add(new RoutingObjects("D", BASIC_CONNECTOR, new int[]{3, 4}, 3, 0, new LatLng(51.590583, -0.229723), new String[]{"C", "E"}, "Y"));
                list.add(new RoutingObjects("E", BASIC_CONNECTOR, new int[]{5, 4}, 3, 0, new LatLng(51.590563, -0.229552), new String[]{"D", "F"}, "Y"));
                list.add(new RoutingObjects("F", BASIC_CONNECTOR, new int[]{5, 6}, 3, 0, new LatLng(51.590780, -0.229489), new String[]{"E"}, "Y"));

                list.add(new RoutingObjects("S1", STAIR, new int[]{3}, 3, 0, new LatLng(51.590548, -0.229726), new String[]{"null"}, "N"));
                list.add(new RoutingObjects("S2", STAIR, new int[]{3}, 3, 0, new LatLng(51.590780, -0.229489), new String[]{"null"}, "N"));

                list.add(new RoutingObjects("E1", ELEVATOR, new int[]{7}, 3, 0, new LatLng(51.590392, -0.229483), new String[]{"null"}, "Y"));

                list.add(new RoutingObjects("D1", DOOR, new int[]{1}, 3, 0, new LatLng(51.590429, -0.229534), new String[]{"B", "N1"}, "Y"));
                break;

            case 1:
                list.add(new RoutingObjects("A", BASIC_CONNECTOR, new int[]{1, 2}, 2, 1, new LatLng(51.590780, -0.229489), new String[]{"B"}, "Y"));
                list.add(new RoutingObjects("B", BASIC_CONNECTOR, new int[]{3, 2}, 2, 1, new LatLng(51.590563, -0.229552), new String[]{"A", "C"}, "Y"));
                list.add(new RoutingObjects("C", BASIC_CONNECTOR, new int[]{3, 4}, 2, 1, new LatLng(51.590583, -0.229723), new String[]{"B", "D"}, "Y"));
                list.add(new RoutingObjects("D", BASIC_CONNECTOR, new int[]{5, 4}, 2, 1, new LatLng(51.590461, -0.229754), new String[]{"C", "E"}, "Y"));
                list.add(new RoutingObjects("E", BASIC_CONNECTOR, new int[]{5, 6}, 2, 1, new LatLng(51.590379, -0.229562), new String[]{"D", "F"}, "Y"));
                list.add(new RoutingObjects("F", BASIC_CONNECTOR, new int[]{5, 7}, 2, 1, new LatLng(51.590360, -0.229518), new String[]{"E"}, "Y"));

                list.add(new RoutingObjects("E1", ELEVATOR, new int[]{7}, 2, 1, new LatLng(51.590392, -0.229483), new String[]{"null"}, "Y"));

                list.add(new RoutingObjects("S1", STAIR, new int[]{4}, 2, 1, new LatLng(51.590548, -0.229726), new String[]{"null"}, "N"));
                list.add(new RoutingObjects("S2", STAIR, new int[]{2}, 2, 1, new LatLng(51.590780, -0.229489), new String[]{"null"}, "N"));
                break;

            case 2:
                list.add(new RoutingObjects("A", BASIC_CONNECTOR, new int[]{1, 2}, 1, 2, new LatLng(51.590780, -0.229489), new String[]{"B"}, "Y"));
                list.add(new RoutingObjects("B", BASIC_CONNECTOR, new int[]{3, 2}, 1, 2, new LatLng(51.590563, -0.229552), new String[]{"A", "C"}, "Y"));
                list.add(new RoutingObjects("C", BASIC_CONNECTOR, new int[]{3, 4}, 1, 2, new LatLng(51.590583, -0.229723), new String[]{"B", "D"}, "Y"));
                list.add(new RoutingObjects("D", BASIC_CONNECTOR, new int[]{5, 4}, 1, 2, new LatLng(51.590461, -0.229754), new String[]{"C", "E"}, "Y"));
                list.add(new RoutingObjects("E", BASIC_CONNECTOR, new int[]{5, 6}, 1, 2, new LatLng(51.590360, -0.229518), new String[]{"D"}, "Y"));

                list.add(new RoutingObjects("E1", ELEVATOR, new int[]{6}, 1, 2, new LatLng(51.590392, -0.229483), new String[]{"null"}, "Y"));

                list.add(new RoutingObjects("S1", STAIR, new int[]{4}, 1, 2, new LatLng(51.590548, -0.229726), new String[]{"null"}, "N"));
                list.add(new RoutingObjects("S2", STAIR, new int[]{2}, 1, 2, new LatLng(51.590780, -0.229489), new String[]{"null"}, "N"));
                break;

            case 3:
                list.add(new RoutingObjects("A", BASIC_CONNECTOR, new int[]{1, 2}, 0, 3, new LatLng(51.590461, -0.229754), new String[]{"B"}, "Y"));
                list.add(new RoutingObjects("B", BASIC_CONNECTOR, new int[]{2, 3}, 0, 3, new LatLng(51.590360, -0.229518), new String[]{"A"}, "Y"));

                list.add(new RoutingObjects("E1", ELEVATOR, new int[]{3}, 0, 3, new LatLng(51.590392, -0.229483), new String[]{"null"}, "Y"));
                list.add(new RoutingObjects("S1", STAIR, new int[]{1}, 0, 3, new LatLng(51.590548, -0.229726), new String[]{"null"}, "N"));
                break;
        }

        return list;
    }

    private static ArrayList<RoutingObjects> getCircleCafeConnectors() {
        ArrayList<RoutingObjects> list = new ArrayList<>();
        list.add(new RoutingObjects("A", BASIC_CONNECTOR, new int[]{1, 2}, 3, 0, new LatLng(51.590482, -0.229790), new String[]{""}, "Y"));
        list.add(new RoutingObjects("D1", DOOR, new int[]{1}, 3, 0, new LatLng(51.590522, -0.229743), new String[]{"A", "O1"}, "Y"));
        list.add(new RoutingObjects("D2", DOOR, new int[]{1}, 3, 0, new LatLng(51.590420, -0.229863), new String[]{"A", "A1"}, "Y"));
        return list;
    }

    private static ArrayList<RoutingObjects> getGroveBlockAConnectors(int actualLevel) {
        ArrayList<RoutingObjects> list = new ArrayList<>();
        switch (actualLevel) {
            case -1:
                list.add(new RoutingObjects("A", BASIC_CONNECTOR, new int[]{1, 6}, 5, -1, new LatLng(51.588786, -0.230623), new String[]{"B"}, "Y"));
                list.add(new RoutingObjects("B", BASIC_CONNECTOR, new int[]{1, 2}, 5, -1, new LatLng(51.588710, -0.230637), new String[]{"A", "C"}, "Y"));
                list.add(new RoutingObjects("C", BASIC_CONNECTOR, new int[]{3, 2}, 5, -1, new LatLng(51.588701, -0.230593), new String[]{"B", "D"}, "Y"));
                list.add(new RoutingObjects("D", BASIC_CONNECTOR, new int[]{3, 4}, 5, -1, new LatLng(51.588602, -0.230637), new String[]{"C", "E"}, "Y"));
                list.add(new RoutingObjects("E", BASIC_CONNECTOR, new int[]{5, 4}, 5, -1, new LatLng(51.588597, -0.230606), new String[]{"D", "G"}, "Y"));
                list.add(new RoutingObjects("F", BASIC_CONNECTOR, new int[]{7, 8}, 5, -1, new LatLng(51.588519, -0.230682), new String[]{"G"}, "Y"));
                list.add(new RoutingObjects("G", BASIC_CONNECTOR, new int[]{7, 5}, 5, -1, new LatLng(51.588511, -0.230641), new String[]{"E", "F"}, "Y"));

                list.add(new RoutingObjects("E1", ELEVATOR, new int[]{1}, 5, -1, new LatLng(51.588763, -0.230630), new String[]{"null"}, "Y"));
                list.add(new RoutingObjects("E2", ELEVATOR, new int[]{8}, 5, -1, new LatLng(51.588763, -0.230630), new String[]{"null"}, "Y"));

                list.add(new RoutingObjects("S4", STAIR, new int[]{6}, 5, -1, new LatLng(51.588787, -0.230592), new String[]{"null"}, "N"));
                list.add(new RoutingObjects("S5", STAIR, new int[]{5}, 5, -1, new LatLng(51.588434, -0.230673), new String[]{"null"}, "N"));

                break;
            case 0:
                list.add(new RoutingObjects("A", BASIC_CONNECTOR, new int[]{1, 3}, 4, 0, new LatLng(51.588831, -0.230624), new String[]{"B"}, "Y"));
                list.add(new RoutingObjects("B", BASIC_CONNECTOR, new int[]{1, 2}, 4, 0, new LatLng(51.588798, -0.230633), new String[]{"A", "C"}, "Y"));
                list.add(new RoutingObjects("C", BASIC_CONNECTOR, new int[]{1, 4, 5}, 4, 0, new LatLng(51.588698, -0.230634), new String[]{"D", "H", "B"}, "Y"));
                list.add(new RoutingObjects("D", BASIC_CONNECTOR, new int[]{11, 5}, 4, 0, new LatLng(51.588719, -0.230758), new String[]{"C", "E"}, "Y"));
                list.add(new RoutingObjects("E", BASIC_CONNECTOR, new int[]{6, 5}, 4, 0, new LatLng(51.588734, -0.230900), new String[]{"D", "F"}, "Y"));
                list.add(new RoutingObjects("F", BASIC_CONNECTOR, new int[]{6, 7}, 4, 0, new LatLng(51.588596, -0.231010), new String[]{"E", "G"}, "Y"));
                list.add(new RoutingObjects("G", BASIC_CONNECTOR, new int[]{1, 7, 9, 10}, 4, 0, new LatLng(51.588510, -0.230673), new String[]{"F", "H"}, "Y"));
                list.add(new RoutingObjects("H", BASIC_CONNECTOR, new int[]{1, 8}, 4, 0, new LatLng(51.588612, -0.230631), new String[]{"G", "C"}, "Y"));

                list.add(new RoutingObjects("E1", ELEVATOR, new int[]{1}, 4, 0, new LatLng(51.588755, -0.230633), new String[]{"null"}, "Y"));
                list.add(new RoutingObjects("E2", ELEVATOR, new int[]{10}, 4, 0, new LatLng(51.588494, -0.230701), new String[]{"null"}, "Y"));

                list.add(new RoutingObjects("S1", STAIR, new int[]{4}, 4, 0, new LatLng(51.588613, -0.230227), new String[]{"null"}, "N"));
                list.add(new RoutingObjects("S2", STAIR, new int[]{9}, 4, 0, new LatLng(51.588460, -0.230299), new String[]{"null"}, "N"));
                list.add(new RoutingObjects("S3", STAIR, new int[]{3}, 4, 0, new LatLng(51.588841, -0.230702), new String[]{"null"}, "N"));
                list.add(new RoutingObjects("S4", STAIR, new int[]{2}, 4, 0, new LatLng(51.588795, -0.230587), new String[]{"null"}, "N"));
                list.add(new RoutingObjects("S5", STAIR, new int[]{10}, 4, 0, new LatLng(51.588422, -0.230699), new String[]{"null"}, "N"));


                list.add(new RoutingObjects("D1", DOOR, new int[]{1}, 4, 0, new LatLng(51.588854, -0.230615), new String[]{"A", "L1"}, "Y"));
                break;
            case 1:
                list.add(new RoutingObjects("A", BASIC_CONNECTOR, new int[]{1, 14}, 3, 1, new LatLng(51.588803, -0.230641), new String[]{"B"}, "Y"));
                list.add(new RoutingObjects("B", BASIC_CONNECTOR, new int[]{1, 2}, 3, 1, new LatLng(51.588704, -0.230653), new String[]{"A", "C", "N"}, "Y"));
                list.add(new RoutingObjects("C", BASIC_CONNECTOR, new int[]{3, 2}, 3, 1, new LatLng(51.588718, -0.230760), new String[]{"B", "D"}, "Y"));
                list.add(new RoutingObjects("D", BASIC_CONNECTOR, new int[]{4, 2}, 3, 1, new LatLng(51.588736, -0.230901), new String[]{"C", "E"}, "Y"));
                list.add(new RoutingObjects("E", BASIC_CONNECTOR, new int[]{4, 5, 6}, 3, 1, new LatLng(51.588592, -0.231008), new String[]{"D", "F", "H"}, "Y"));
                list.add(new RoutingObjects("F", BASIC_CONNECTOR, new int[]{5, 7}, 3, 1, new LatLng(51.588549, -0.231032), new String[]{"E", "G"}, "Y"));
                list.add(new RoutingObjects("G", BASIC_CONNECTOR, new int[]{8, 7}, 3, 1, new LatLng(51.588485, -0.230788), new String[]{"F", "H"}, "Y"));
                list.add(new RoutingObjects("H", BASIC_CONNECTOR, new int[]{8, 6}, 3, 1, new LatLng(51.588528, -0.230767), new String[]{"G", "I", "E"}, "Y"));
                list.add(new RoutingObjects("I", BASIC_CONNECTOR, new int[]{9, 6, 10}, 3, 1, new LatLng(51.588503, -0.230652), new String[]{"O", "J", "H"}, "Y"));
                list.add(new RoutingObjects("J", BASIC_CONNECTOR, new int[]{11, 10}, 3, 1, new LatLng(51.588490, -0.230533), new String[]{"K", "I", "P"}, "Y"));
                list.add(new RoutingObjects("K", BASIC_CONNECTOR, new int[]{12, 10}, 3, 1, new LatLng(51.588470, -0.230399), new String[]{"L", "J"}, "Y"));
                list.add(new RoutingObjects("L", BASIC_CONNECTOR, new int[]{12, 13}, 3, 1, new LatLng(51.588634, -0.230327), new String[]{"M", "K"}, "Y"));
                list.add(new RoutingObjects("M", BASIC_CONNECTOR, new int[]{11, 13}, 3, 1, new LatLng(51.588662, -0.230449), new String[]{"L", "N", "P"}, "Y"));
                list.add(new RoutingObjects("N", BASIC_CONNECTOR, new int[]{9, 13}, 3, 1, new LatLng(51.588695, -0.230611), new String[]{"M", "O", "B"}, "Y"));
                list.add(new RoutingObjects("O", BASIC_CONNECTOR, new int[]{9, 15}, 3, 1, new LatLng(51.588549, -0.230641), new String[]{"N", "I", "P"}, "Y"));
                list.add(new RoutingObjects("P", BASIC_CONNECTOR, new int[]{11, 15}, 3, 1, new LatLng(51.588533, -0.230514), new String[]{"M", "J", "O"}, "Y"));

                //S3 ends on this level, S6 starts on this level
                list.add(new RoutingObjects("S1", STAIR, new int[]{13}, 3, 1, new LatLng(51.588612, -0.230221), new String[]{"null"}, "N"));
                list.add(new RoutingObjects("S2", STAIR, new int[]{10}, 3, 1, new LatLng(51.588460, -0.230308), new String[]{"null"}, "N"));
                list.add(new RoutingObjects("S3", STAIR, new int[]{14}, 3, 1, new LatLng(51.588808, -0.230714), new String[]{"null"}, "N"));
                list.add(new RoutingObjects("S4", STAIR, new int[]{14}, 3, 1, new LatLng(51.588793, -0.230590), new String[]{"null"}, "N"));
                list.add(new RoutingObjects("S5", STAIR, new int[]{9}, 3, 1, new LatLng(51.588413, -0.230699), new String[]{"null"}, "N"));
                list.add(new RoutingObjects("S6", STAIR, new int[]{15}, 3, 1, new LatLng(51.588547, -0.230613), new String[]{"null"}, "N"));

                list.add(new RoutingObjects("E1", ELEVATOR, new int[]{1}, 3, 1, new LatLng(51.588732, -0.230633), new String[]{"null"}, "Y"));
                list.add(new RoutingObjects("E2", ELEVATOR, new int[]{9}, 3, 1, new LatLng(51.588495, -0.230687), new String[]{"null"}, "Y"));
                break;
            case 2:
                list.add(new RoutingObjects("A", BASIC_CONNECTOR, new int[]{1, 13}, 2, 2, new LatLng(51.588776, -0.230544), new String[]{"B"}, "Y"));
                list.add(new RoutingObjects("B", BASIC_CONNECTOR, new int[]{1, 2, 11}, 2, 2, new LatLng(51.588686, -0.230564), new String[]{"A", "C", "O", "N"}, "Y"));
                list.add(new RoutingObjects("C", BASIC_CONNECTOR, new int[]{7, 2}, 2, 2, new LatLng(51.588694, -0.230610), new String[]{"B", "D", "P"}, "Y"));
                list.add(new RoutingObjects("D", BASIC_CONNECTOR, new int[]{5, 2}, 2, 2, new LatLng(51.588721, -0.230767), new String[]{"C", "E", "I"}, "Y"));
                list.add(new RoutingObjects("E", BASIC_CONNECTOR, new int[]{4, 2}, 2, 2, new LatLng(51.588724, -0.230795), new String[]{"D", "F", "H"}, "Y"));
                list.add(new RoutingObjects("F", BASIC_CONNECTOR, new int[]{3, 2}, 2, 2, new LatLng(51.588737, -0.230899), new String[]{"E", "G"}, "Y"));
                list.add(new RoutingObjects("G", BASIC_CONNECTOR, new int[]{3, 6}, 2, 2, new LatLng(51.588593, -0.231005), new String[]{"F", "H"}, "Y"));
                list.add(new RoutingObjects("H", BASIC_CONNECTOR, new int[]{4, 6}, 2, 2, new LatLng(51.588567, -0.230914), new String[]{"I", "G", "E"}, "Y"));
                list.add(new RoutingObjects("I", BASIC_CONNECTOR, new int[]{5, 6}, 2, 2, new LatLng(51.588537, -0.230806), new String[]{"J", "H", "D"}, "Y"));
                list.add(new RoutingObjects("J", BASIC_CONNECTOR, new int[]{10, 6, 7}, 2, 2, new LatLng(51.588506, -0.230659), new String[]{"K", "I", "P"}, "Y"));
                list.add(new RoutingObjects("K", BASIC_CONNECTOR, new int[]{10, 8}, 2, 2, new LatLng(51.588490, -0.230552), new String[]{"L", "J", "N"}, "Y"));
                list.add(new RoutingObjects("L", BASIC_CONNECTOR, new int[]{10, 9}, 2, 2, new LatLng(51.588468, -0.230406), new String[]{"K", "M"}, "Y"));
                list.add(new RoutingObjects("M", BASIC_CONNECTOR, new int[]{11, 9}, 2, 2, new LatLng(51.588634, -0.230322), new String[]{"L", "N"}, "Y"));
                list.add(new RoutingObjects("N", BASIC_CONNECTOR, new int[]{11, 8}, 2, 2, new LatLng(51.588665, -0.230450), new String[]{"M", "K", "B"}, "Y"));
                list.add(new RoutingObjects("O", BASIC_CONNECTOR, new int[]{1, 12}, 2, 2, new LatLng(51.588628, -0.230576), new String[]{"B", "P"}, "Y"));
                list.add(new RoutingObjects("P", BASIC_CONNECTOR, new int[]{7, 12}, 2, 2, new LatLng(51.588638, -0.230630), new String[]{"C", "O", "J"}, "Y"));

                //S6 and S2 ends on this level
                list.add(new RoutingObjects("S1", STAIR, new int[]{11}, 2, 2, new LatLng(51.588609, -0.230234), new String[]{"null"}, "N"));
                list.add(new RoutingObjects("S2", STAIR, new int[]{10}, 2, 2, new LatLng(51.588462, -0.230295), new String[]{"null"}, "N"));
                list.add(new RoutingObjects("S4", STAIR, new int[]{13}, 2, 2, new LatLng(51.588782, -0.230593), new String[]{"null"}, "N"));
                list.add(new RoutingObjects("S5", STAIR, new int[]{7}, 2, 2, new LatLng(51.588414, -0.230694), new String[]{"null"}, "N"));
                list.add(new RoutingObjects("S6", STAIR, new int[]{1}, 2, 2, new LatLng(51.588594, -0.230594), new String[]{"null"}, "N"));

                list.add(new RoutingObjects("E1", ELEVATOR, new int[]{1}, 2, 2, new LatLng(51.588731, -0.230559), new String[]{"null"}, "Y"));
                list.add(new RoutingObjects("E2", ELEVATOR, new int[]{7}, 2, 2, new LatLng(51.588495, -0.230690), new String[]{"null"}, "Y"));

                break;
            case 3:
                list.add(new RoutingObjects("A", BASIC_CONNECTOR, new int[]{1, 2}, 1, 3, new LatLng(51.588698, -0.230637), new String[]{"B"}, "Y"));
                list.add(new RoutingObjects("B", BASIC_CONNECTOR, new int[]{1, 3}, 1, 3, new LatLng(51.588754, -0.230631), new String[]{"A"}, "Y"));


                //S1 ends here.
                //Left out S5 and E2 on this level and the next for reasons best known to me. Grove building is fvk#ng weird
                list.add(new RoutingObjects("S1", STAIR, new int[]{2}, 1, 3, new LatLng(51.588611, -0.230222), new String[]{"null"}, "N"));
                list.add(new RoutingObjects("S4", STAIR, new int[]{3}, 1, 3, new LatLng(51.588758, -0.230597), new String[]{"null"}, "N"));

                list.add(new RoutingObjects("E1", ELEVATOR, new int[]{1}, 1, 3, new LatLng(51.588735, -0.230617), new String[]{"null"}, "Y"));
                break;
            case 4:
                list.add(new RoutingObjects("A", BASIC_CONNECTOR, new int[]{1, 2}, 0, 4, new LatLng(51.588750, -0.230639), new String[]{""}, "Y"));

                list.add(new RoutingObjects("S4", STAIR, new int[]{2}, 0, 4, new LatLng(51.588754, -0.230597), new String[]{"null"}, "N"));
                list.add(new RoutingObjects("E1", ELEVATOR, new int[]{1}, 0, 4, new LatLng(51.588733, -0.230631), new String[]{"null"}, "Y"));

                break;

        }
        return list;
    }

    private static ArrayList<RoutingObjects> getGroveBlockBConnectors(int actualLevel) {
        ArrayList<RoutingObjects> list = new ArrayList<>();
        switch (actualLevel) {
            case 0:
                list.add(new RoutingObjects("A", BASIC_CONNECTOR, new int[]{1, 3}, 4, 0, new LatLng(51.588955, -0.230486), new String[]{"B"}, "Y"));
                list.add(new RoutingObjects("B", BASIC_CONNECTOR, new int[]{1, 2}, 4, 0, new LatLng(51.588908, -0.230244), new String[]{"A"}, "Y"));

                list.add(new RoutingObjects("S1", STAIR, new int[]{3}, 4, 0, new LatLng(51.589012, -0.230456), new String[]{"null"}, "N"));
                list.add(new RoutingObjects("S2", STAIR, new int[]{1}, 4, 0, new LatLng(51.588875, -0.230086), new String[]{"null"}, "N"));

                list.add(new RoutingObjects("E1", ELEVATOR, new int[]{1}, 4, 0, new LatLng(51.588952, -0.230430), new String[]{"null"}, "Y"));

                list.add(new RoutingObjects("D1", DOOR, new int[]{1}, 4, 0, new LatLng(51.588957, -0.230488), new String[]{"A", "A10"}, "Y"));
                break;
            case 1:
                list.add(new RoutingObjects("A", BASIC_CONNECTOR, new int[]{1, 2}, 3, 1, new LatLng(51.588950, -0.230485), new String[]{"B"}, "Y"));
                list.add(new RoutingObjects("B", BASIC_CONNECTOR, new int[]{3, 2}, 3, 1, new LatLng(51.588882, -0.230141), new String[]{"A", "C"}, "Y"));
                list.add(new RoutingObjects("C", BASIC_CONNECTOR, new int[]{3, 4}, 3, 1, new LatLng(51.588955, -0.230107), new String[]{"B"}, "Y"));

                list.add(new RoutingObjects("S1", STAIR, new int[]{1}, 3, 1, new LatLng(51.589022, -0.230451), new String[]{"null"}, "N"));
                list.add(new RoutingObjects("S2", STAIR, new int[]{2}, 3, 1, new LatLng(51.588874, -0.230086), new String[]{"null"}, "N"));
                list.add(new RoutingObjects("E1", ELEVATOR, new int[]{2}, 3, 1, new LatLng(51.588941, -0.230438), new String[]{"null"}, "Y"));
                break;
            case 2:
                list.add(new RoutingObjects("A", BASIC_CONNECTOR, new int[]{1, 2}, 2, 2, new LatLng(51.588949, -0.230470), new String[]{"B"}, "Y"));
                list.add(new RoutingObjects("B", BASIC_CONNECTOR, new int[]{3, 2}, 2, 2, new LatLng(51.588883, -0.230145), new String[]{"A"}, "Y"));

                list.add(new RoutingObjects("S1", STAIR, new int[]{1}, 2, 2, new LatLng(51.589019, -0.230423), new String[]{"null"}, "N"));
                list.add(new RoutingObjects("S2", STAIR, new int[]{2}, 2, 2, new LatLng(51.588876, -0.230086), new String[]{"null"}, "N"));
                list.add(new RoutingObjects("E1", ELEVATOR, new int[]{2}, 2, 2, new LatLng(51.588944, -0.230440), new String[]{"null"}, "Y"));
                break;
        }
        return list;
    }

    private static ArrayList<RoutingObjects> getGroveBlockCConnectors(int actualLevel) {
        ArrayList<RoutingObjects> list = new ArrayList<>();
        switch (actualLevel) {
            case 0:
                list.add(new RoutingObjects("A", BASIC_CONNECTOR, new int[]{1, 2}, 4, 0, new LatLng(51.589186, -0.230249), new String[]{"E"}, "Y"));
                list.add(new RoutingObjects("B", BASIC_CONNECTOR, new int[]{3, 4}, 4, 0, new LatLng(51.589270, -0.230040), new String[]{"C"}, "Y"));
                list.add(new RoutingObjects("C", BASIC_CONNECTOR, new int[]{3, 2}, 4, 0, new LatLng(51.589280, -0.230142), new String[]{"B", "D", "E"}, "Y"));
                list.add(new RoutingObjects("D", BASIC_CONNECTOR, new int[]{3}, 4, 0, new LatLng(51.589301, -0.230184), new String[]{"C"}, "Y"));
                list.add(new RoutingObjects("E", BASIC_CONNECTOR, new int[]{5, 2}, 4, 0, new LatLng(51.589231, -0.230196), new String[]{"A", "C"}, "Y"));

                list.add(new RoutingObjects("S1", STAIR, new int[]{3}, 4, 0, new LatLng(51.589273, -0.230221), new String[]{"null"}, "N"));

                list.add(new RoutingObjects("E1", ELEVATOR, new int[]{5}, 4, 0, new LatLng(51.589241, -0.230215), new String[]{"null"}, "Y"));

                list.add(new RoutingObjects("D1", DOOR, new int[]{3}, 4, 0, new LatLng(51.589312, -0.230166), new String[]{"D", "A9"}, "Y"));
                list.add(new RoutingObjects("D2", DOOR, new int[]{1}, 4, 0, new LatLng(51.589167, -0.230253), new String[]{"A", "A12"}, "Y"));
                break;
            case 1:
                list.add(new RoutingObjects("A", BASIC_CONNECTOR, new int[]{1, 2}, 3, 1, new LatLng(51.589215, -0.230285), new String[]{"B"}, "Y"));
                list.add(new RoutingObjects("B", BASIC_CONNECTOR, new int[]{3, 2}, 3, 1, new LatLng(51.589207, -0.230184), new String[]{"A"}, "Y"));

                list.add(new RoutingObjects("S1", STAIR, new int[]{1}, 3, 1, new LatLng(51.589261, -0.230234), new String[]{"null"}, "N"));
                list.add(new RoutingObjects("E1", ELEVATOR, new int[]{2}, 3, 1, new LatLng(51.589214, -0.230244), new String[]{"null"}, "Y"));
        }

        return list;
    }

    private static ArrayList<RoutingObjects> getVineConnectors(int actualLevel) {
        ArrayList<RoutingObjects> list = new ArrayList<>();
        switch (actualLevel) {
            case 0:
                list.add(new RoutingObjects("A", BASIC_CONNECTOR, new int[]{1, 2}, 1, 0, new LatLng(51.590611, -0.230630), new String[]{"B"}, "Y"));
                list.add(new RoutingObjects("B", BASIC_CONNECTOR, new int[]{3, 2}, 1, 0, new LatLng(51.590640, -0.230913), new String[]{"A"}, "Y"));
                list.add(new RoutingObjects("S1", STAIR, new int[]{2}, 1, 0, new LatLng(51.590609, -0.230608), new String[]{"null"}, "N"));
                list.add(new RoutingObjects("S2", STAIR, new int[]{2}, 1, 0, new LatLng(51.590643, -0.230935), new String[]{"null"}, "N"));

                list.add(new RoutingObjects("D1", DOOR, new int[]{1}, 1, 0, new LatLng(51.590599, -0.230635), new String[]{"A", "A8"}, "Y"));
                list.add(new RoutingObjects("D2", DOOR, new int[]{3}, 1, 0, new LatLng(51.590630, -0.230917), new String[]{"B", "A7"}, "Y"));
                break;
            case 1:
                list.add(new RoutingObjects("A", BASIC_CONNECTOR, new int[]{3, 2}, 0, 1, new LatLng(51.590637, -0.230908), new String[]{"B"}, "Y"));
                list.add(new RoutingObjects("B", BASIC_CONNECTOR, new int[]{1, 2}, 0, 1, new LatLng(51.590610, -0.230630), new String[]{"A"}, "Y"));
                list.add(new RoutingObjects("S1", STAIR, new int[]{1}, 0, 1, new LatLng(51.590645, -0.230625), new String[]{"null"}, "N"));
                list.add(new RoutingObjects("S2", STAIR, new int[]{3}, 0, 1, new LatLng(51.590673, -0.230901), new String[]{"null"}, "N"));
                break;
        }

        return list;
    }

    private static ArrayList<RoutingObjects> getPortacabinAConnectors(int actualLevel) {
        ArrayList<RoutingObjects> list = new ArrayList<>();
        switch (actualLevel) {
            case 0:
                //list.add(new RoutingObjects("A", BASIC_CONNECTOR, new int[] {1}, 1, 0, new LatLng(51.589771, -0.230222), new String[] {"B"}, "Y"));
                list.add(new RoutingObjects("A", BASIC_CONNECTOR, new int[]{1, 2}, 1, 0, new LatLng(51.589760, -0.230224), new String[]{""}, "Y"));
                list.add(new RoutingObjects("D1", DOOR, new int[]{1}, 1, 0, new LatLng(51.589771, -0.230243), new String[]{"A", "A13"}, "Y"));
                list.add(new RoutingObjects("S1", STAIR, new int[]{1}, 1, 0, new LatLng(51.589754, -0.230190), new String[]{"null"}, "N"));
                break;
            case 1:
                list.add(new RoutingObjects("A", BASIC_CONNECTOR, new int[]{1, 2}, 0, 1, new LatLng(51.589769, -0.230224), new String[]{""}, "Y"));
                list.add(new RoutingObjects("S1", STAIR, new int[]{1}, 0, 1, new LatLng(51.589766, -0.230160), new String[]{"null"}, "N"));
                break;
        }

        return list;
    }

    private static ArrayList<RoutingObjects> getPortacabinAEXTConnectors() {
        ArrayList<RoutingObjects> list = new ArrayList<>();
        list.add(new RoutingObjects("A", BASIC_CONNECTOR, new int[]{1}, 1, 0, new LatLng(51.589871, -0.230213), new String[]{""}, "Y"));
        list.add(new RoutingObjects("D1", DOOR, new int[]{1}, 1, 0, new LatLng(51.589871, -0.230228), new String[]{"A", "A14"}, "Y"));

        return list;
    }

    private static ArrayList<RoutingObjects> getPortacabinBConnectors() {
        ArrayList<RoutingObjects> list = new ArrayList<>();
        list.add(new RoutingObjects("A", BASIC_CONNECTOR, new int[]{1}, 0, 0, new LatLng(51.590717, -0.229117), new String[]{""}, "Y"));
        list.add(new RoutingObjects("D1", DOOR, new int[]{1}, 0, 0, new LatLng(51.590721, -0.229157), new String[]{"A", "A3"}, "Y"));
        return list;
    }


    private static ArrayList<RoutingObjects> getPortacabin67Connectors() {
        ArrayList<RoutingObjects> list = new ArrayList<>();
        list.add(new RoutingObjects("A", BASIC_CONNECTOR, new int[]{1, 2}, 1, 0, new LatLng(51.589716, -0.229821), new String[]{"B"}, "N"));
        list.add(new RoutingObjects("B", BASIC_CONNECTOR, new int[]{1, 3}, 1, 0, new LatLng(51.589732, -0.229955), new String[]{"A"}, "N"));
        list.add(new RoutingObjects("D1", DOOR, new int[]{2}, 1, 0, new LatLng(51.589724, -0.229817), new String[]{"A", "A15"}, "N"));
        list.add(new RoutingObjects("D2", DOOR, new int[]{3}, 1, 0, new LatLng(51.589737, -0.229951), new String[]{"B", "A16"}, "N"));

        return list;
    }

    private static ArrayList<RoutingObjects> getBarnConnectors() {
        ArrayList<RoutingObjects> list = new ArrayList<>();
        list.add(new RoutingObjects("A", BASIC_CONNECTOR, new int[]{1, 2}, 0, 0, new LatLng(51.590938, -0.228504), new String[]{"B"}, "Y"));
        list.add(new RoutingObjects("B", BASIC_CONNECTOR, new int[]{2, 3}, 0, 0, new LatLng(51.591057, -0.228516), new String[]{"A"}, "Y"));
        list.add(new RoutingObjects("D1", DOOR, new int[]{1}, 0, 0, new LatLng(51.590938, -0.228426), new String[]{"A", "U1"}, "Y"));
        return list;
    }


    public static ArrayList<RoutingObjects> getOutsideConnectors() {
        ArrayList<RoutingObjects> list = new ArrayList<>();

        list.add(new RoutingObjects("A", BASIC_CONNECTOR, new LatLng(51.590097, -0.228139), new String[]{"B"}, "Y"));
        list.add(new RoutingObjects("B", BASIC_CONNECTOR, new LatLng(51.590315, -0.228194), new String[]{"A", "C", "V1"}, "Y"));
        list.add(new RoutingObjects("C", BASIC_CONNECTOR, new LatLng(51.590350, -0.228599), new String[]{"B", "E", "I1"}, "Y"));
        list.add(new RoutingObjects("E", BASIC_CONNECTOR, new LatLng(51.590379, -0.228964), new String[]{"C", "F", "K"}, "Y"));
        list.add(new RoutingObjects("F", BASIC_CONNECTOR, new LatLng(51.590401, -0.229140), new String[]{"E", "G", "N1"}, "Y"));
        list.add(new RoutingObjects("G", BASIC_CONNECTOR, new LatLng(51.590435, -0.229140), new String[]{"F", "H"}, "Y"));
        list.add(new RoutingObjects("H", BASIC_CONNECTOR, new LatLng(51.590471, -0.229376), new String[]{"G", "I"}, "Y"));
        list.add(new RoutingObjects("I", BASIC_CONNECTOR, new LatLng(51.590657, -0.229313), new String[]{"H", "A3", "P1"}, "Y"));
        list.add(new RoutingObjects("J", BASIC_CONNECTOR, new LatLng(51.590830, -0.229251), new String[]{"P1", "H1"}, "Y"));
        list.add(new RoutingObjects("K", BASIC_CONNECTOR, new LatLng(51.590168, -0.229023), new String[]{"E", "L", "R"}, "Y"));
        list.add(new RoutingObjects("L", BASIC_CONNECTOR, new LatLng(51.590187, -0.229187), new String[]{"F", "K", "M"}, "Y"));
        list.add(new RoutingObjects("M", BASIC_CONNECTOR, new LatLng(51.590159, -0.229616), new String[]{"L", "N", "Z"}, "Y"));
        list.add(new RoutingObjects("N", BASIC_CONNECTOR, new LatLng(51.590096, -0.229637), new String[]{"M", "Q"}, "Y"));
        list.add(new RoutingObjects("O", BASIC_CONNECTOR, new LatLng(51.589733, -0.229727), new String[]{"A15", "T", "Q"}, "Y"));
        list.add(new RoutingObjects("P", BASIC_CONNECTOR, new LatLng(51.589749, -0.229884), new String[]{"A15", "A16"}, "Y"));
        list.add(new RoutingObjects("Q", BASIC_CONNECTOR, new LatLng(51.589979, -0.229679), new String[]{"O", "N"}, "Y"));
        list.add(new RoutingObjects("R", BASIC_CONNECTOR, new LatLng(51.589575, -0.229187), new String[]{"S", "K"}, "Y"));
        list.add(new RoutingObjects("S", BASIC_CONNECTOR, new LatLng(51.589486, -0.229187), new String[]{"R", "U", "T"}, "Y"));
        list.add(new RoutingObjects("T", BASIC_CONNECTOR, new LatLng(51.589551, -0.229763), new String[]{"O", "S", "A11", "W"}, "Y"));
        list.add(new RoutingObjects("U", BASIC_CONNECTOR, new LatLng(51.588938, -0.229297), new String[]{"S", "V"}, "Y"));
        list.add(new RoutingObjects("V", BASIC_CONNECTOR, new LatLng(51.589014, -0.229861), new String[]{"A11", "U", "A12", "K1"}, "Y"));
        list.add(new RoutingObjects("W", BASIC_CONNECTOR, new LatLng(51.589572, -0.230001), new String[]{"T", "E1", "A9"}, "Y"));
        list.add(new RoutingObjects("X", BASIC_CONNECTOR, new LatLng(51.589761, -0.230020), new String[]{"A16", "Y"}, "Y"));
        list.add(new RoutingObjects("Y", BASIC_CONNECTOR, new LatLng(51.589967, -0.229947), new String[]{"X", "Z", "B1"}, "Y"));
        list.add(new RoutingObjects("Z", BASIC_CONNECTOR, new LatLng(51.590174, -0.229934), new String[]{"M", "Y", "D1", "A1"}, "N"));
        list.add(new RoutingObjects("A1", BASIC_CONNECTOR, new LatLng(51.590377, -0.229911), new String[]{"Z", "F1", "O1"}, "Y"));
        list.add(new RoutingObjects("B1", BASIC_CONNECTOR, new LatLng(51.589979, -0.230105), new String[]{"Y", "C1"}, "N"));
        list.add(new RoutingObjects("C1", BASIC_CONNECTOR, new LatLng(51.589993, -0.230266), new String[]{"D1", "A14", "B1"}, "Y"));
        list.add(new RoutingObjects("D1", BASIC_CONNECTOR, new LatLng(51.590108, -0.230247), new String[]{"Z", "C1", "F1"}, "Y"));
        list.add(new RoutingObjects("E1", BASIC_CONNECTOR, new LatLng(51.589620, -0.230408), new String[]{"W", "A13"}, "Y"));
        list.add(new RoutingObjects("F1", BASIC_CONNECTOR, new LatLng(51.590471, -0.230036), new String[]{"A1", "D1", "G1"}, "Y"));
        list.add(new RoutingObjects("G1", BASIC_CONNECTOR, new LatLng(51.590642, -0.229904), new String[]{"F1", "H1", "O1", "A5"}, "Y"));
        list.add(new RoutingObjects("H1", BASIC_CONNECTOR, new LatLng(51.590891, -0.229726), new String[]{"J", "G1"}, "Y"));
        list.add(new RoutingObjects("I1", BASIC_CONNECTOR, new LatLng(51.590564, -0.228539), new String[]{"C", "A3", "S1", "U1"}, "N"));
        list.add(new RoutingObjects("J1", BASIC_CONNECTOR, new LatLng(51.589134, -0.230500), new String[]{"A12", "A9", "A10"}, "Y"));
        list.add(new RoutingObjects("K1", BASIC_CONNECTOR, new LatLng(51.588787, -0.229967), new String[]{"V", "L1", "Q1"}, "Y"));
        list.add(new RoutingObjects("L1", BASIC_CONNECTOR, new LatLng(51.588917, -0.230597), new String[]{"A10", "K1"}, "Y"));
        list.add(new RoutingObjects("N1", BASIC_CONNECTOR, new LatLng(51.590443, -0.229521), new String[]{"F", "O1"}, "Y"));
        list.add(new RoutingObjects("O1", BASIC_CONNECTOR, new LatLng(51.590540, -0.229722), new String[]{"G1", "N1", "A1"}, "Y"));
        list.add(new RoutingObjects("P1", BASIC_CONNECTOR, new LatLng(51.590736, -0.229285), new String[]{"I", "J"}, "N"));
        list.add(new RoutingObjects("Q1", BASIC_CONNECTOR, new LatLng(51.588700, -0.229998), new String[]{"K1", "X1", "Y1"}, "Y"));
        list.add(new RoutingObjects("R1", BASIC_CONNECTOR, new LatLng(51.588579, -0.229606), new String[]{"Y1", "Z1"}, "Y"));
        list.add(new RoutingObjects("T1", BASIC_CONNECTOR, new LatLng(51.590758, -0.228237), new String[]{"U1", "W1"}, "N"));
        list.add(new RoutingObjects("S1", BASIC_CONNECTOR, new LatLng(51.590540, -0.228306), new String[]{"I1", "W1", "A2"}, "Y"));
        list.add(new RoutingObjects("U1", BASIC_CONNECTOR, new LatLng(51.590801, -0.228478), new String[]{"I1", "T1", "A2"}, "Y"));
        list.add(new RoutingObjects("V1", BASIC_CONNECTOR, new LatLng(51.590650, -0.228098), new String[]{"B", "W1"}, "Y"));
        list.add(new RoutingObjects("W1", BASIC_CONNECTOR, new LatLng(51.590664, -0.228257), new String[]{"S1", "T1", "V1"}, "Y"));
        list.add(new RoutingObjects("X1", BASIC_CONNECTOR, new LatLng(51.588455, -0.230132), new String[]{"Q1"}, "Y"));
        list.add(new RoutingObjects("Z1", BASIC_CONNECTOR, new LatLng(51.588526, -0.229428), new String[]{"R1"}, "Y"));
        list.add(new RoutingObjects("Y1", BASIC_CONNECTOR, new LatLng(51.588643, -0.229776), new String[]{"Q1", "R1"}, "Y"));
        list.add(new RoutingObjects("A2", BASIC_CONNECTOR, new LatLng(51.590578, -0.228537), new String[]{"S1", "U1"}, "Y"));
        list.add(new RoutingObjects("A3", BASIC_CONNECTOR, new LatLng(51.590646, -0.229174), new String[]{"I", "A4", "I1"}, "Y"));
        list.add(new RoutingObjects("A4", BASIC_CONNECTOR, new LatLng(51.590736, -0.229152), new String[]{"A3"}, "Y"));
        list.add(new RoutingObjects("A5", BASIC_CONNECTOR, new LatLng(51.590669, -0.230289), new String[]{"G1", "A6"}, "Y"));
        list.add(new RoutingObjects("A6", BASIC_CONNECTOR, new LatLng(51.590508, -0.230537), new String[]{"A8", "A5"}, "Y"));
        list.add(new RoutingObjects("A7", BASIC_CONNECTOR, new LatLng(51.590537, -0.230941), new String[]{"A8"}, "Y"));
        list.add(new RoutingObjects("A8", BASIC_CONNECTOR, new LatLng(51.590508, -0.230666), new String[]{"A6", "A7"}, "Y"));
        list.add(new RoutingObjects("A9", BASIC_CONNECTOR, new LatLng(51.589402, -0.230089), new String[]{"W", "J1", "A11"}, "Y"));
        list.add(new RoutingObjects("A10", BASIC_CONNECTOR, new LatLng(51.588977, -0.230572), new String[]{"L1", "J1"}, "Y"));
        list.add(new RoutingObjects("A11", BASIC_CONNECTOR, new LatLng(51.589368, -0.229769), new String[]{"T", "V", "A9"}, "Y"));
        list.add(new RoutingObjects("A12", BASIC_CONNECTOR, new LatLng(51.589093, -0.230278), new String[]{"J1", "V"}, "Y"));
        list.add(new RoutingObjects("A13", BASIC_CONNECTOR, new LatLng(51.589778, -0.230348), new String[]{"E1", "A14"}, "Y"));
        list.add(new RoutingObjects("A14", BASIC_CONNECTOR, new LatLng(51.589878, -0.230309), new String[]{"A13", "C1"}, "Y"));
        list.add(new RoutingObjects("A15", BASIC_CONNECTOR, new LatLng(51.589739, -0.229810), new String[]{"O", "P"}, "Y"));
        list.add(new RoutingObjects("A16", BASIC_CONNECTOR, new LatLng(51.589754, -0.229946), new String[]{"P", "X"}, "Y"));

        return list;
    }

    public static ArrayList<RoutingObjects> getRooms() {
        ArrayList<RoutingObjects> list = new ArrayList<>();

        //College Ground Floor
        list.add(new RoutingObjects("College Building", 2, COLLEGE, 2, 0, new LatLng(51.589825, -0.228839), new String[]{"O", "Q", "S", "W"}));
        list.add(new RoutingObjects("Quad", 2, COLLEGE, 2, 0, new LatLng(51.589825, -0.228839), new String[]{"O", "Q", "S", "W"}));
        list.add(new RoutingObjects("CG03", 3, COLLEGE, 2, 0, new LatLng(51.589594, -0.228487), new String[]{"R", "P", "T", "N", "K", "X", "Y"}));
        list.add(new RoutingObjects("CG04", 3, COLLEGE, 2, 0, new LatLng(51.589605, -0.228577), new String[]{"R", "P", "T", "N", "K", "X", "Y"}));
        list.add(new RoutingObjects("CG06", 3, COLLEGE, 2, 0, new LatLng(51.589614, -0.228666), new String[]{"R", "P", "T", "N", "K", "X", "Y"}));
        list.add(new RoutingObjects("CG07", 3, COLLEGE, 2, 0, new LatLng(51.589618, -0.228698), new String[]{"R", "P", "T", "N", "K", "X", "Y"}));
        list.add(new RoutingObjects("CG08", 3, COLLEGE, 2, 0, new LatLng(51.589633, -0.228832), new String[]{"R", "P", "T", "N", "K", "X", "Y"}));
        list.add(new RoutingObjects("CG09", 3, COLLEGE, 2, 0, new LatLng(51.589643, -0.228925), new String[]{"R", "P", "T", "N", "K", "X", "Y"}));
        list.add(new RoutingObjects("CG10", 3, COLLEGE, 2, 0, new LatLng(51.589653, -0.229011), new String[]{"R", "P", "T", "N", "K", "X", "Y"}));
        list.add(new RoutingObjects("CG11", 3, COLLEGE, 2, 0, new LatLng(51.589658, -0.229049), new String[]{"R", "P", "T", "N", "K", "X", "Y"}));
        list.add(new RoutingObjects("CG12A", 3, COLLEGE, 2, 0, new LatLng(51.589678, -0.229225), new String[]{"R", "P", "T", "N", "K", "X", "Y"}));
        list.add(new RoutingObjects("CG12B", 3, COLLEGE, 2, 0, new LatLng(51.589682, -0.229284), new String[]{"R", "P", "T", "N", "K", "X", "Y"}));
        list.add(new RoutingObjects("CG13", 3, COLLEGE, 2, 0, new LatLng(51.589705, -0.229462), new String[]{"R", "P", "T", "N", "K", "X", "Y"}));
        list.add(new RoutingObjects("CG14", 3, COLLEGE, 2, 0, new LatLng(51.589719, -0.229596), new String[]{"R", "P", "T", "N", "K", "X", "Y"}));
        list.add(new RoutingObjects("CG15", 3, COLLEGE, 2, 0, new LatLng(51.589678, -0.229225), new String[]{"R", "P", "T", "N", "K", "X", "Y"}));
        list.add(new RoutingObjects("CG16", 9, COLLEGE, 2, 0, new LatLng(51.589763, -0.229446), new String[]{"K", "L"}));
        list.add(new RoutingObjects("CG30", 17, COLLEGE, 2, 0, new LatLng(51.589753, -0.229059), new String[]{"W", "X"}));
        list.add(new RoutingObjects("CG36", 16, COLLEGE, 2, 0, new LatLng(51.589957, -0.228998), new String[]{"W", "V"}));
        list.add(new RoutingObjects("CG37", 16, COLLEGE, 2, 0, new LatLng(51.589920, -0.229009), new String[]{"W", "V"}));
        list.add(new RoutingObjects("CG41", 1, COLLEGE, 2, 0, new LatLng(51.590062, -0.229314), new String[]{"C", "D", "B", "A", "E", "V", "Z"}));
        list.add(new RoutingObjects("CG43", 1, COLLEGE, 2, 0, new LatLng(51.590052, -0.229228), new String[]{"C", "D", "B", "A", "E", "V", "Z"}));
        list.add(new RoutingObjects("CG45", 1, COLLEGE, 2, 0, new LatLng(51.590043, -0.229142), new String[]{"C", "D", "B", "A", "E", "V", "Z"}));
        list.add(new RoutingObjects("CG47", 1, COLLEGE, 2, 0, new LatLng(51.590003, -0.228806), new String[]{"C", "D", "B", "A", "E", "V", "Z"}));
        list.add(new RoutingObjects("CG48", 1, COLLEGE, 2, 0, new LatLng(51.589984, -0.228639), new String[]{"B", "C", "D", "A", "E", "V", "Z"}));
        list.add(new RoutingObjects("CG49", 1, COLLEGE, 2, 0, new LatLng(51.589974, -0.228544), new String[]{"B", "C", "D", "A", "E", "V", "Z"}));
        list.add(new RoutingObjects("CG51", 1, COLLEGE, 2, 0, new LatLng(51.589964, -0.228452), new String[]{"B", "C", "D", "A", "E", "V", "Z"}));

        list.add(new RoutingObjects("CG60", 8, COLLEGE, 2, 0, new LatLng(51.589930, -0.229445), new String[]{"J", "G"}));
        list.add(new RoutingObjects("CG62", 1, COLLEGE, 2, 0, new LatLng(51.590062, -0.229314), new String[]{"C", "D", "B", "A", "E", "V", "Z"}));
        list.add(new RoutingObjects("CG62 (Disabled)", 8, COLLEGE, 2, 0, new LatLng(51.589925, -0.229401), new String[]{"J", "G"}));
        list.add(new RoutingObjects("CG76", 6, COLLEGE, 2, 0, new LatLng(51.589921, -0.229081), new String[]{"D", "O", "N"}));
        list.add(new RoutingObjects("CG77", 6, COLLEGE, 2, 0, new LatLng(51.589864, -0.229097), new String[]{"O", "N", "D"}));
        list.add(new RoutingObjects("Costa", 5, COLLEGE, 2, 0, new LatLng(51.589755, -0.228960), new String[]{"P", "Q", "C"}));


        //College First Floor
        list.add(new RoutingObjects("C101", 3, COLLEGE, 1, 1, new LatLng(51.589676, -0.228350), new String[]{"G", "A"}));
        list.add(new RoutingObjects("C104", 2, COLLEGE, 1, 1, new LatLng(51.589593, -0.228483), new String[]{"G", "H", "J", "K", "E"}));
        list.add(new RoutingObjects("C105", 2, COLLEGE, 1, 1, new LatLng(51.589597, -0.228529), new String[]{"G", "H", "J", "K", "E"}));
        list.add(new RoutingObjects("C106", 2, COLLEGE, 1, 1, new LatLng(51.589615, -0.228697), new String[]{"G", "H", "J", "K", "E"}));
        list.add(new RoutingObjects("C107", 2, COLLEGE, 1, 1, new LatLng(51.589643, -0.228925), new String[]{"G", "H", "J", "K", "E"}));
        list.add(new RoutingObjects("C109", 2, COLLEGE, 1, 1, new LatLng(51.589653, -0.229016), new String[]{"G", "H", "J", "K", "E"}));
        list.add(new RoutingObjects("C110", 2, COLLEGE, 1, 1, new LatLng(51.589685, -0.229287), new String[]{"G", "H", "J", "K", "E"}));
        list.add(new RoutingObjects("C111", 2, COLLEGE, 1, 1, new LatLng(51.589689, -0.229335), new String[]{"G", "H", "J", "K", "E"}));
        list.add(new RoutingObjects("C113", 2, COLLEGE, 1, 1, new LatLng(51.589703, -0.229463), new String[]{"G", "H", "J", "K", "E"}));
        list.add(new RoutingObjects("C114", 2, COLLEGE, 1, 1, new LatLng(51.589659, -0.229051), new String[]{"G", "H", "J", "K", "E"}));
        list.add(new RoutingObjects("C115", 1, COLLEGE, 1, 1, new LatLng(51.590018, -0.228946), new String[]{"A", "B", "C", "N", "O"}));
        list.add(new RoutingObjects("C117", 11, COLLEGE, 1, 1, new LatLng(51.589771, -0.229396), new String[]{"E", "P"}));
        list.add(new RoutingObjects("C118", 11, COLLEGE, 1, 1, new LatLng(51.589793, -0.229392), new String[]{"E", "P"}));
        list.add(new RoutingObjects("C120", 11, COLLEGE, 1, 1, new LatLng(51.589906, -0.229359), new String[]{"E", "P"}));
        list.add(new RoutingObjects("C121", 12, COLLEGE, 1, 1, new LatLng(51.590037, -0.229370), new String[]{"P", "Q"}));
        list.add(new RoutingObjects("C122", 1, COLLEGE, 1, 1, new LatLng(51.590066, -0.229359), new String[]{"A", "B", "C", "N", "O"}));
        list.add(new RoutingObjects("C126", 1, COLLEGE, 1, 1, new LatLng(51.590062, -0.229321), new String[]{"A", "B", "C", "N", "O"}));
        list.add(new RoutingObjects("C127", 1, COLLEGE, 1, 1, new LatLng(51.590048, -0.229176), new String[]{"A", "B", "C", "N", "O"}));
        list.add(new RoutingObjects("C128", 1, COLLEGE, 1, 1, new LatLng(51.590044, -0.229137), new String[]{"A", "B", "C", "N", "O"}));
        list.add(new RoutingObjects("C131", 1, COLLEGE, 1, 1, new LatLng(51.590011, -0.228863), new String[]{"A", "B", "C", "N", "O"}));
        list.add(new RoutingObjects("C132", 1, COLLEGE, 1, 1, new LatLng(51.590001, -0.228770), new String[]{"A", "B", "C", "N", "O"}));
        list.add(new RoutingObjects("C135", 1, COLLEGE, 1, 1, new LatLng(51.589966, -0.228461), new String[]{"A", "B", "C", "N", "O"}));
        list.add(new RoutingObjects("C136", 1, COLLEGE, 1, 1, new LatLng(51.589951, -0.228320), new String[]{"A", "B", "C", "N", "O"}));
        list.add(new RoutingObjects("C138", 3, COLLEGE, 1, 1, new LatLng(51.589849, -0.228304), new String[]{"G", "A"}));

        //College Second Floor
        list.add(new RoutingObjects("C204", 2, COLLEGE, 0, 2, new LatLng(51.589597, -0.228527), new String[]{"A", "B", "C", "D", "F"}));
        list.add(new RoutingObjects("C205", 2, COLLEGE, 0, 2, new LatLng(51.589614, -0.228658), new String[]{"A", "B", "C", "D", "F"}));
        list.add(new RoutingObjects("C206", 2, COLLEGE, 0, 2, new LatLng(51.589618, -0.228699), new String[]{"A", "B", "C", "D", "F"}));
        list.add(new RoutingObjects("C207", 2, COLLEGE, 0, 2, new LatLng(51.589641, -0.228921), new String[]{"A", "B", "C", "D", "F"}));
        list.add(new RoutingObjects("C208", 2, COLLEGE, 0, 2, new LatLng(51.589652, -0.229013), new String[]{"A", "B", "C", "D", "F"}));
        list.add(new RoutingObjects("C210", 6, COLLEGE, 0, 2, new LatLng(51.589693, -0.229043), new String[]{"D", "G"}));
        list.add(new RoutingObjects("C211", 11, COLLEGE, 0, 2, new LatLng(51.590017, -0.228938), new String[]{"K", "L", "M"}));
        list.add(new RoutingObjects("C212", 10, COLLEGE, 0, 2, new LatLng(51.589702, -0.229186), new String[]{"F", "J", "K"}));
        list.add(new RoutingObjects("C213A", 10, COLLEGE, 0, 2, new LatLng(51.589785, -0.229163), new String[]{"F", "J", "K"}));
        list.add(new RoutingObjects("C213B", 10, COLLEGE, 0, 2, new LatLng(51.589812, -0.229153), new String[]{"F", "J", "K"}));
        list.add(new RoutingObjects("C214", 10, COLLEGE, 0, 2, new LatLng(51.589903, -0.229131), new String[]{"F", "J", "K"}));
        list.add(new RoutingObjects("C215", 10, COLLEGE, 0, 2, new LatLng(51.589957, -0.229114), new String[]{"F", "J", "K"}));
        list.add(new RoutingObjects("C216B", 10, COLLEGE, 0, 2, new LatLng(51.589989, -0.229107), new String[]{"F", "J", "K"}));
        list.add(new RoutingObjects("C217", 11, COLLEGE, 0, 2, new LatLng(51.590015, -0.228898), new String[]{"K", "L", "M"}));
        list.add(new RoutingObjects("C218", 11, COLLEGE, 0, 2, new LatLng(51.590012, -0.228858), new String[]{"K", "L", "M"}));


        //Williams Ground Floor
        list.add(new RoutingObjects("Williams Building", 4, WILLIAMS, 1, 0, new LatLng(51.590479, -0.228873), new String[]{"E", "H"}));
        list.add(new RoutingObjects("Library @ Williams", 3, WILLIAMS, 1, 0, new LatLng(51.590630, -0.228110), new String[]{"C", "B"}));
        list.add(new RoutingObjects("WG07", 4, WILLIAMS, 1, 0, new LatLng(51.590479, -0.228873), new String[]{"E", "H"}));
        list.add(new RoutingObjects("WG32", 8, WILLIAMS, 1, 0, new LatLng(51.590511, -0.228757), new String[]{"G", "I"}));
        list.add(new RoutingObjects("WG33", 5, WILLIAMS, 1, 0, new LatLng(51.590505, -0.228718), new String[]{"G", "E"}));
        list.add(new RoutingObjects("WG35", 4, WILLIAMS, 1, 0, new LatLng(51.590435, -0.228471), new String[]{"D", "L"}));
        list.add(new RoutingObjects("WG37", 3, WILLIAMS, 1, 0, new LatLng(51.590417, -0.228166), new String[]{"C", "B"}));
        list.add(new RoutingObjects("WG38", 3, WILLIAMS, 1, 0, new LatLng(51.590437, -0.228161), new String[]{"C", "B"}));
        list.add(new RoutingObjects("WG39", 3, WILLIAMS, 1, 0, new LatLng(51.590449, -0.228157), new String[]{"C", "B"}));
        list.add(new RoutingObjects("WG42", 3, WILLIAMS, 1, 0, new LatLng(51.590535, -0.228132), new String[]{"C", "B"}));
        list.add(new RoutingObjects("WG44", 3, WILLIAMS, 1, 0, new LatLng(51.590562, -0.228125), new String[]{"C", "B"}));
        list.add(new RoutingObjects("WG45", 3, WILLIAMS, 1, 0, new LatLng(51.590594, -0.228121), new String[]{"C", "B"}));
        list.add(new RoutingObjects("WG46", 3, WILLIAMS, 1, 0, new LatLng(51.590621, -0.228117), new String[]{"C", "B"}));
        list.add(new RoutingObjects("WG47", 1, WILLIAMS, 1, 0, new LatLng(51.590628, -0.228268), new String[]{"A", "J", "M"}));
        list.add(new RoutingObjects("WG48", 1, WILLIAMS, 1, 0, new LatLng(51.590628, -0.228268), new String[]{"A", "J", "M"}));
        list.add(new RoutingObjects("WG49", 1, WILLIAMS, 1, 0, new LatLng(51.590652, -0.228263), new String[]{"A", "J", "M"}));
        list.add(new RoutingObjects("WG50", 1, WILLIAMS, 1, 0, new LatLng(51.590703, -0.228250), new String[]{"A", "J", "M"}));
        list.add(new RoutingObjects("WG51", 1, WILLIAMS, 1, 0, new LatLng(51.590703, -0.228250), new String[]{"A", "J", "M"}));

        //Williams First Floor
        list.add(new RoutingObjects("W142", 5, WILLIAMS, 0, 1, new LatLng(51.590437, -0.228342), new String[]{"E", "F", "D"}));
        list.add(new RoutingObjects("W143", 4, WILLIAMS, 0, 1, new LatLng(51.590433, -0.228150), new String[]{"D", "C"}));
        list.add(new RoutingObjects("W144", 4, WILLIAMS, 0, 1, new LatLng(51.590457, -0.228145), new String[]{"D", "C"}));
        list.add(new RoutingObjects("W145", 4, WILLIAMS, 0, 1, new LatLng(51.590473, -0.228142), new String[]{"D", "C"}));
        list.add(new RoutingObjects("W147", 4, WILLIAMS, 0, 1, new LatLng(51.590589, -0.228099), new String[]{"D", "C"}));
        list.add(new RoutingObjects("W148", 1, WILLIAMS, 0, 1, new LatLng(51.590666, -0.228269), new String[]{"A", "B"}));
        list.add(new RoutingObjects("W149", 1, WILLIAMS, 0, 1, new LatLng(51.590682, -0.228266), new String[]{"A", "B"}));
        list.add(new RoutingObjects("W150", 1, WILLIAMS, 0, 1, new LatLng(51.590712, -0.228257), new String[]{"A", "B"}));
        list.add(new RoutingObjects("W151", 4, WILLIAMS, 0, 1, new LatLng(51.590707, -0.228064), new String[]{"D", "C"}));
        list.add(new RoutingObjects("W152", 4, WILLIAMS, 0, 1, new LatLng(51.590731, -0.228057), new String[]{"D", "C"}));
        list.add(new RoutingObjects("W153", 4, WILLIAMS, 0, 1, new LatLng(51.590799, -0.228039), new String[]{"D", "C"}));
        list.add(new RoutingObjects("W154", 4, WILLIAMS, 0, 1, new LatLng(51.590731, -0.228057), new String[]{"D", "C"}));
        list.add(new RoutingObjects("W155", 4, WILLIAMS, 0, 1, new LatLng(51.590707, -0.228064), new String[]{"D", "C"}));
        list.add(new RoutingObjects("W156", 4, WILLIAMS, 0, 1, new LatLng(51.590612, -0.228093), new String[]{"D", "C"}));
        list.add(new RoutingObjects("W157", 4, WILLIAMS, 0, 1, new LatLng(51.590589, -0.228099), new String[]{"D", "C"}));
        list.add(new RoutingObjects("W158", 4, WILLIAMS, 0, 1, new LatLng(51.590492, -0.228129), new String[]{"D", "C"}));
        list.add(new RoutingObjects("W159", 4, WILLIAMS, 0, 1, new LatLng(51.590453, -0.228141), new String[]{"D", "C"}));
        list.add(new RoutingObjects("W160", 4, WILLIAMS, 0, 1, new LatLng(51.590350, -0.228169), new String[]{"D", "C"}));


        //Hatchcroft Ground Floor
        list.add(new RoutingObjects("Hatchcroft Building", 1, HATHCROFT, 2, 0, new LatLng(51.589154, -0.229178), new String[]{"A", "C", "G", "H"}));
        list.add(new RoutingObjects("HG01", 1, HATHCROFT, 2, 0, new LatLng(51.589138, -0.229016), new String[]{"A", "C", "G", "H"}));
        list.add(new RoutingObjects("HG02", 1, HATHCROFT, 2, 0, new LatLng(51.589122, -0.228864), new String[]{"A", "C", "G", "H"}));
        list.add(new RoutingObjects("HG03", 1, HATHCROFT, 2, 0, new LatLng(51.589103, -0.228762), new String[]{"A", "C", "G", "H"}));
        list.add(new RoutingObjects("HG04", 5, HATHCROFT, 2, 0, new LatLng(51.589088, -0.228738), new String[]{"D", "F"}));
        list.add(new RoutingObjects("HG05", 6, HATHCROFT, 2, 0, new LatLng(51.589100, -0.228649), new String[]{"E", "F"}));
        list.add(new RoutingObjects("HG06", 7, HATHCROFT, 2, 0, new LatLng(51.589085, -0.228600), new String[]{"E"}));
        list.add(new RoutingObjects("HG07", 7, HATHCROFT, 2, 0, new LatLng(51.589085, -0.228600), new String[]{"E"}));
        //HG08 Toilet
        list.add(new RoutingObjects("HG08", 5, HATHCROFT, 2, 0, new LatLng(51.589077, -0.228653), new String[]{"D", "F"}));
        list.add(new RoutingObjects("HG09", 5, HATHCROFT, 2, 0, new LatLng(51.589078, -0.228689), new String[]{"D", "F"}));
        list.add(new RoutingObjects("HG10", 1, HATHCROFT, 2, 0, new LatLng(51.589109, -0.228809), new String[]{"A", "C", "G", "H"}));
        list.add(new RoutingObjects("HG11", 1, HATHCROFT, 2, 0, new LatLng(51.589122, -0.228864), new String[]{"A", "C", "G", "H"}));
        list.add(new RoutingObjects("HG12", 1, HATHCROFT, 2, 0, new LatLng(51.589122, -0.228864), new String[]{"A", "C", "G", "H"}));
        list.add(new RoutingObjects("HG13", 1, HATHCROFT, 2, 0, new LatLng(51.589138, -0.229016), new String[]{"A", "C", "G", "H"}));
        list.add(new RoutingObjects("HG14", 2, HATHCROFT, 2, 0, new LatLng(51.589105, -0.229233), new String[]{"A", "B"}));
        list.add(new RoutingObjects("HG19", 2, HATHCROFT, 2, 0, new LatLng(51.589054, -0.229264), new String[]{"A", "B"}));
        //HG20 & HG21 Toilet
        list.add(new RoutingObjects("HG20", 2, HATHCROFT, 2, 0, new LatLng(51.589140, -0.229230), new String[]{"A", "B"}));
        list.add(new RoutingObjects("HG21", 2, HATHCROFT, 2, 0, new LatLng(51.589140, -0.229230), new String[]{"A", "B"}));
        list.add(new RoutingObjects("HG24", 1, HATHCROFT, 2, 0, new LatLng(51.589200, -0.229583), new String[]{"A", "C", "G", "H"}));
        list.add(new RoutingObjects("HG27", 1, HATHCROFT, 2, 0, new LatLng(51.589200, -0.229583), new String[]{"A", "C", "G", "H"}));
        list.add(new RoutingObjects("HG28", 1, HATHCROFT, 2, 0, new LatLng(51.589200, -0.229583), new String[]{"A", "C", "G", "H"}));
        list.add(new RoutingObjects("HG29", 1, HATHCROFT, 2, 0, new LatLng(51.589209, -0.229642), new String[]{"A", "C", "G", "H"}));
        list.add(new RoutingObjects("HG30", 1, HATHCROFT, 2, 0, new LatLng(51.589196, -0.229521), new String[]{"A", "C", "G", "H"}));
        list.add(new RoutingObjects("HG31", 1, HATHCROFT, 2, 0, new LatLng(51.589182, -0.229423), new String[]{"A", "C", "G", "H"}));
        list.add(new RoutingObjects("HG33", 1, HATHCROFT, 2, 0, new LatLng(51.589175, -0.229333), new String[]{"A", "C", "G", "H"}));

        //Hatchcroft First Floor
        list.add(new RoutingObjects("H101", 1, HATHCROFT, 1, 1, new LatLng(51.589136, -0.229010), new String[]{"A", "B", "C", "F", "G"}));
        list.add(new RoutingObjects("H102", 1, HATHCROFT, 1, 1, new LatLng(51.589122, -0.228895), new String[]{"A", "B", "C", "F", "G"}));
        list.add(new RoutingObjects("H104", 1, HATHCROFT, 1, 1, new LatLng(51.589107, -0.228772), new String[]{"A", "B", "C", "F", "G"}));
        list.add(new RoutingObjects("H105", 1, HATHCROFT, 1, 1, new LatLng(51.589092, -0.228649), new String[]{"A", "B", "C", "F", "G"}));
        list.add(new RoutingObjects("H106", 1, HATHCROFT, 1, 1, new LatLng(51.589095, -0.228680), new String[]{"A", "B", "C", "F", "G"}));
        list.add(new RoutingObjects("H106", 1, HATHCROFT, 1, 1, new LatLng(51.589095, -0.228680), new String[]{"A", "B", "C", "F", "G"}));
        list.add(new RoutingObjects("H109", 1, HATHCROFT, 1, 1, new LatLng(51.589109, -0.228814), new String[]{"A", "B", "C", "F", "G"}));
        list.add(new RoutingObjects("H110", 1, HATHCROFT, 1, 1, new LatLng(51.589109, -0.228814), new String[]{"A", "B", "C", "F", "G"}));
        list.add(new RoutingObjects("H111", 1, HATHCROFT, 1, 1, new LatLng(51.589109, -0.228814), new String[]{"A", "B", "C", "F", "G"}));
        list.add(new RoutingObjects("H113", 1, HATHCROFT, 1, 1, new LatLng(51.589143, -0.229093), new String[]{"A", "B", "C", "F", "G"}));
        list.add(new RoutingObjects("H116", 3, HATHCROFT, 1, 1, new LatLng(51.589059, -0.229254), new String[]{"E", "F"}));
        list.add(new RoutingObjects("H117", 4, HATHCROFT, 1, 1, new LatLng(51.588980, -0.229334), new String[]{"D", "E"}));
        list.add(new RoutingObjects("H118", 4, HATHCROFT, 1, 1, new LatLng(51.588985, -0.229385), new String[]{"D", "E"}));
        list.add(new RoutingObjects("H119", 4, HATHCROFT, 1, 1, new LatLng(51.588991, -0.229434), new String[]{"D", "E"}));
        list.add(new RoutingObjects("H120", 4, HATHCROFT, 1, 1, new LatLng(51.588996, -0.229479), new String[]{"D", "E"}));
        list.add(new RoutingObjects("H121", 4, HATHCROFT, 1, 1, new LatLng(51.589003, -0.229523), new String[]{"D", "E"}));
        list.add(new RoutingObjects("H122", 4, HATHCROFT, 1, 1, new LatLng(51.589003, -0.229523), new String[]{"D", "E"}));
        list.add(new RoutingObjects("H123", 5, HATHCROFT, 1, 1, new LatLng(51.589033, -0.229516), new String[]{"D", "C"}));
        list.add(new RoutingObjects("H124", 5, HATHCROFT, 1, 1, new LatLng(51.589063, -0.229507), new String[]{"D", "C"}));
        list.add(new RoutingObjects("H125", 5, HATHCROFT, 1, 1, new LatLng(51.589096, -0.229496), new String[]{"D", "C"}));
        list.add(new RoutingObjects("H128", 3, HATHCROFT, 1, 1, new LatLng(51.589141, -0.229233), new String[]{"E", "F"}));
        list.add(new RoutingObjects("H129", 3, HATHCROFT, 1, 1, new LatLng(51.589141, -0.229233), new String[]{"E", "F"}));
        list.add(new RoutingObjects("H133", 1, HATHCROFT, 1, 1, new LatLng(51.589195, -0.229554), new String[]{"A", "B", "C", "F", "G"}));
        list.add(new RoutingObjects("H135", 6, HATHCROFT, 1, 1, new LatLng(51.589179, -0.229660), new String[]{"A"}));
        list.add(new RoutingObjects("H136", 6, HATHCROFT, 1, 1, new LatLng(51.589179, -0.229660), new String[]{"A"}));
        list.add(new RoutingObjects("H137", 1, HATHCROFT, 1, 1, new LatLng(51.589179, -0.229660), new String[]{"A", "B", "C", "F", "G"}));
        list.add(new RoutingObjects("H139", 1, HATHCROFT, 1, 1, new LatLng(51.589200, -0.229558), new String[]{"A", "B", "C", "F", "G"}));
        list.add(new RoutingObjects("H141A", 1, HATHCROFT, 1, 1, new LatLng(51.589194, -0.229501), new String[]{"A", "B", "C", "F", "G"}));
        list.add(new RoutingObjects("H141B", 1, HATHCROFT, 1, 1, new LatLng(51.589167, -0.229286), new String[]{"A", "B", "C", "F", "G"}));

        //Hatchcroft Second Floor
        list.add(new RoutingObjects("H201", 1, HATHCROFT, 0, 2, new LatLng(51.589131, -0.228970), new String[]{"A", "B"}));
        list.add(new RoutingObjects("H202", 1, HATHCROFT, 0, 2, new LatLng(51.589126, -0.228912), new String[]{"A", "B"}));
        list.add(new RoutingObjects("H203", 1, HATHCROFT, 0, 2, new LatLng(51.589110, -0.228769), new String[]{"A", "B"}));
        list.add(new RoutingObjects("H205", 1, HATHCROFT, 0, 2, new LatLng(51.589099, -0.228650), new String[]{"A", "B"}));
        list.add(new RoutingObjects("H206", 1, HATHCROFT, 0, 2, new LatLng(51.589087, -0.228617), new String[]{"A", "B"}));
        list.add(new RoutingObjects("H207", 1, HATHCROFT, 0, 2, new LatLng(51.589096, -0.228693), new String[]{"A", "B"}));
        list.add(new RoutingObjects("H208", 1, HATHCROFT, 0, 2, new LatLng(51.589105, -0.228761), new String[]{"A", "B"}));
        list.add(new RoutingObjects("H211", 1, HATHCROFT, 0, 2, new LatLng(51.589142, -0.229133), new String[]{"A", "B"}));
        list.add(new RoutingObjects("H216", 3, HATHCROFT, 0, 2, new LatLng(51.589156, -0.229398), new String[]{"B"}));
        list.add(new RoutingObjects("H217", 3, HATHCROFT, 0, 2, new LatLng(51.589156, -0.229398), new String[]{"B"}));
        list.add(new RoutingObjects("H219", 1, HATHCROFT, 0, 2, new LatLng(51.589193, -0.229510), new String[]{"A", "B"}));
        list.add(new RoutingObjects("H220", 1, HATHCROFT, 0, 2, new LatLng(51.589200, -0.229580), new String[]{"A", "B"}));
        list.add(new RoutingObjects("H221", 1, HATHCROFT, 0, 2, new LatLng(51.589210, -0.229636), new String[]{"A", "B"}));
        list.add(new RoutingObjects("H222", 1, HATHCROFT, 0, 2, new LatLng(51.589200, -0.229580), new String[]{"A", "B"}));
        list.add(new RoutingObjects("H223", 1, HATHCROFT, 0, 2, new LatLng(51.589187, -0.229450), new String[]{"A", "B"}));
        list.add(new RoutingObjects("H224", 1, HATHCROFT, 0, 2, new LatLng(51.589167, -0.229274), new String[]{"A", "B"}));

        //MDX HOUSE GROUND FLOOR
        list.add(new RoutingObjects("MDX House", 3, MDXHOUSE, 0, 0, new LatLng(51.589925, -0.230887), new String[]{"B"}));

        //MDX BASEMENT
        list.add(new RoutingObjects("Gym", 2, MDXHOUSE, 1, -1, new LatLng(51.589951, -0.230764), new String[]{"B", "C"}));
        list.add(new RoutingObjects("Student Union", 2, MDXHOUSE, 1, -1, new LatLng(51.590135, -0.230729), new String[]{"B", "C"}));
        list.add(new RoutingObjects("Real Tennis Club", 3, MDXHOUSE, 1, -1, new LatLng(51.589809, -0.230640), new String[]{"C"}));
        list.add(new RoutingObjects("Sports-Dance Studio", 3, MDXHOUSE, 1, -1, new LatLng(51.589812, -0.230558), new String[]{"C"}));

        //Building 9
        list.add(new RoutingObjects("Building 9", 3, BUILDING9, 0, 0, new LatLng(51.588683, -0.229511), new String[]{"B", "D"}));
        list.add(new RoutingObjects("BG01", 3, BUILDING9, 0, 0, new LatLng(51.588683, -0.229511), new String[]{"B", "D"}));
        list.add(new RoutingObjects("BG02", 3, BUILDING9, 0, 0, new LatLng(51.588737, -0.229469), new String[]{"B", "D"}));
        list.add(new RoutingObjects("BG03", 3, BUILDING9, 0, 0, new LatLng(51.588724, -0.229476), new String[]{"B", "D"}));
        list.add(new RoutingObjects("BG04", 3, BUILDING9, 0, 0, new LatLng(51.588739, -0.229466), new String[]{"B", "D"}));
        list.add(new RoutingObjects("BG05", 2, BUILDING9, 0, 0, new LatLng(51.588758, -0.229413), new String[]{"A", "B"}));
        list.add(new RoutingObjects("BG07", 2, BUILDING9, 0, 0, new LatLng(51.588725, -0.229312), new String[]{"A", "B"}));
        list.add(new RoutingObjects("BG10", 1, BUILDING9, 0, 0, new LatLng(51.588639, -0.229341), new String[]{"A", "C"}));
        list.add(new RoutingObjects("BG11", 1, BUILDING9, 0, 0, new LatLng(51.588639, -0.229341), new String[]{"A", "C"}));

        //Building 10 Ground Floor
        list.add(new RoutingObjects("Building 10", 1, BUILDING10, 1, 0, new LatLng(51.589824, -0.229877), new String[]{"A", "B"}));
        list.add(new RoutingObjects("BTG01", 1, BUILDING10, 1, 0, new LatLng(51.589824, -0.229877), new String[]{"A", "B"}));
        list.add(new RoutingObjects("BTG02", 1, BUILDING10, 1, 0, new LatLng(51.589842, -0.229873), new String[]{"A", "B"}));
        list.add(new RoutingObjects("BTG04", 2, BUILDING10, 1, 0, new LatLng(51.589840, -0.229819), new String[]{"B"}));

        //Building 10 First Floor
        list.add(new RoutingObjects("BT101", 1, BUILDING10, 0, 1, new LatLng(51.589832, -0.229867), new String[]{"A"}));
        list.add(new RoutingObjects("BT103", 1, BUILDING10, 0, 1, new LatLng(51.589832, -0.229867), new String[]{"A"}));

        //SHEPPARD BASEMENT
        list.add(new RoutingObjects("SB01", 3, SHEPPARDLIBRARY, 4, -1, new LatLng(51.590649, -0.229524), new String[]{"C", "D"}));
        list.add(new RoutingObjects("SB03", 3, SHEPPARDLIBRARY, 4, -1, new LatLng(51.590743, -0.229495), new String[]{"C", "D"}));
        list.add(new RoutingObjects("SB05", 1, SHEPPARDLIBRARY, 4, -1, new LatLng(51.590373, -0.229460), new String[]{"A"}));
        list.add(new RoutingObjects("SB12A", 1, SHEPPARDLIBRARY, 4, -1, new LatLng(51.590285, -0.229563), new String[]{"A"}));
        list.add(new RoutingObjects("SB16", 2, SHEPPARDLIBRARY, 4, -1, new LatLng(51.590464, -0.229512), new String[]{"A", "B", "C"}));

        //SHEPPARD GROUND FLOOR
        list.add(new RoutingObjects("Sheppard Library", 1, SHEPPARDLIBRARY, 3, 0, new LatLng(51.590360, -0.229605), new String[]{"B"}));
        list.add(new RoutingObjects("SG01", 1, SHEPPARDLIBRARY, 3, 0, new LatLng(51.590360, -0.229605), new String[]{"B"}));
        list.add(new RoutingObjects("SG02", 2, SHEPPARDLIBRARY, 3, 0, new LatLng(51.590319, -0.229392), new String[]{"A", "B", "C"}));
        list.add(new RoutingObjects("SG09", 6, SHEPPARDLIBRARY, 3, 0, new LatLng(51.590768, -0.229416), new String[]{"F"}));
        list.add(new RoutingObjects("SG10", 6, SHEPPARDLIBRARY, 3, 0, new LatLng(51.590768, -0.229416), new String[]{"F"}));
        list.add(new RoutingObjects("SG11", 6, SHEPPARDLIBRARY, 3, 0, new LatLng(51.590772, -0.229454), new String[]{"F"}));
        list.add(new RoutingObjects("SG12A", 5, SHEPPARDLIBRARY, 3, 0, new LatLng(51.590720, -0.229506), new String[]{"E", "F"}));
        list.add(new RoutingObjects("SG12B", 5, SHEPPARDLIBRARY, 3, 0, new LatLng(51.590634, -0.229532), new String[]{"E", "F"}));
        list.add(new RoutingObjects("SG13A", 5, SHEPPARDLIBRARY, 3, 0, new LatLng(51.590615, -0.229539), new String[]{"E", "F"}));
        list.add(new RoutingObjects("SG13B", 5, SHEPPARDLIBRARY, 3, 0, new LatLng(51.590530, -0.229562), new String[]{"E", "F"}));

        list.add(new RoutingObjects("Circle Café", 2, CIRCLE_CAFE, 3, 0, new LatLng(51.590508, -0.229859), new String[]{"A"}));

        //SHEPPARD FIRST FLOOR
        list.add(new RoutingObjects("S101", 3, SHEPPARDLIBRARY, 2, 1, new LatLng(51.590571, -0.229627), new String[]{"B", "C"}));
        list.add(new RoutingObjects("S103", 1, SHEPPARDLIBRARY, 2, 1, new LatLng(51.590766, -0.229407), new String[]{"A"}));
        list.add(new RoutingObjects("S104", 1, SHEPPARDLIBRARY, 2, 1, new LatLng(51.590766, -0.229407), new String[]{"A"}));
        list.add(new RoutingObjects("S105", 1, SHEPPARDLIBRARY, 2, 1, new LatLng(51.590773, -0.229463), new String[]{"A"}));
        list.add(new RoutingObjects("S106", 2, SHEPPARDLIBRARY, 2, 1, new LatLng(51.590627, -0.229531), new String[]{"B", "A"}));
        list.add(new RoutingObjects("S107", 3, SHEPPARDLIBRARY, 2, 1, new LatLng(51.590563, -0.229552), new String[]{"B", "C"}));
        list.add(new RoutingObjects("S112", 5, SHEPPARDLIBRARY, 2, 1, new LatLng(51.590473, -0.229780), new String[]{"D", "E", "F"}));
        list.add(new RoutingObjects("S119", 5, SHEPPARDLIBRARY, 2, 1, new LatLng(51.590412, -0.229637), new String[]{"D", "E", "F"}));
        list.add(new RoutingObjects("S110", 6, SHEPPARDLIBRARY, 2, 1, new LatLng(51.590419, -0.229508), new String[]{"E"}));
        list.add(new RoutingObjects("S111", 5, SHEPPARDLIBRARY, 2, 1, new LatLng(51.590311, -0.229404), new String[]{"E", "F", "D"}));
        list.add(new RoutingObjects("S112", 5, SHEPPARDLIBRARY, 2, 1, new LatLng(51.590311, -0.229404), new String[]{"E", "F", "D"}));

        //SHEPPARD SECOND FLOOR
        list.add(new RoutingObjects("S201", 3, SHEPPARDLIBRARY, 1, 2, new LatLng(51.590571, -0.229627), new String[]{"B", "C"}));
        list.add(new RoutingObjects("S203", 1, SHEPPARDLIBRARY, 1, 2, new LatLng(51.590767, -0.229409), new String[]{"A"}));
        list.add(new RoutingObjects("S204", 1, SHEPPARDLIBRARY, 1, 2, new LatLng(51.590767, -0.229409), new String[]{"A"}));
        list.add(new RoutingObjects("S205", 1, SHEPPARDLIBRARY, 1, 2, new LatLng(51.590773, -0.229461), new String[]{"A"}));
        list.add(new RoutingObjects("S206", 2, SHEPPARDLIBRARY, 1, 2, new LatLng(51.590725, -0.229501), new String[]{"A", "B"}));
        list.add(new RoutingObjects("S215", 5, SHEPPARDLIBRARY, 1, 2, new LatLng(51.590412, -0.229635), new String[]{"D", "E"}));
        list.add(new RoutingObjects("S216", 5, SHEPPARDLIBRARY, 1, 2, new LatLng(51.590309, -0.229406), new String[]{"D", "E"}));
        list.add(new RoutingObjects("S217", 5, SHEPPARDLIBRARY, 1, 2, new LatLng(51.590309, -0.229406), new String[]{"D", "E"}));
        list.add(new RoutingObjects("S218", 5, SHEPPARDLIBRARY, 1, 2, new LatLng(51.590466, -0.229790), new String[]{"D", "E"}));
        list.add(new RoutingObjects("S219", 5, SHEPPARDLIBRARY, 1, 2, new LatLng(51.590466, -0.229790), new String[]{"D", "E"}));

        //SHEPPARD THIRD FLOOR
        list.add(new RoutingObjects("S302", 2, SHEPPARDLIBRARY, 0, 3, new LatLng(51.590403, -0.229620), new String[]{"B", "A"}));
        list.add(new RoutingObjects("S303", 2, SHEPPARDLIBRARY, 0, 3, new LatLng(51.590307, -0.229403), new String[]{"B", "A"}));
        list.add(new RoutingObjects("S304", 2, SHEPPARDLIBRARY, 0, 3, new LatLng(51.590307, -0.229403), new String[]{"B", "A"}));
        list.add(new RoutingObjects("S306", 2, SHEPPARDLIBRARY, 0, 3, new LatLng(51.590473, -0.229781), new String[]{"B", "A"}));

        //VINE GROUND FLOOR

        list.add(new RoutingObjects("Vine building", 2, VINE, 1, 0, new LatLng(51.590621, -0.230778), new String[]{"B", "A"}));
        list.add(new RoutingObjects("VG01", 2, VINE, 1, 0, new LatLng(51.590621, -0.230778), new String[]{"B", "A"}));
        list.add(new RoutingObjects("VG04", 3, VINE, 1, 0, new LatLng(51.590708, -0.230891), new String[]{"B"}));
        list.add(new RoutingObjects("VG08", 1, VINE, 1, 0, new LatLng(51.590678, -0.230613), new String[]{"A"}));
        list.add(new RoutingObjects("VG02", 2, VINE, 1, 0, new LatLng(51.590626, -0.230808), new String[]{"B", "A"}));
        list.add(new RoutingObjects("VG05", 2, VINE, 1, 0, new LatLng(51.590630, -0.230825), new String[]{"B", "A"}));
        list.add(new RoutingObjects("VG06", 2, VINE, 1, 0, new LatLng(51.590621, -0.230736), new String[]{"B", "A"}));
        list.add(new RoutingObjects("VG07", 2, VINE, 1, 0, new LatLng(51.590619, -0.230715), new String[]{"B", "A"}));

        //VINE FIRST FLOOR
        list.add(new RoutingObjects("V101", 2, VINE, 0, 1, new LatLng(51.590621, -0.230778), new String[]{"B", "A"}));
        list.add(new RoutingObjects("V102", 2, VINE, 0, 1, new LatLng(51.590626, -0.230808), new String[]{"B", "A"}));
        list.add(new RoutingObjects("V103", 2, VINE, 0, 1, new LatLng(51.590630, -0.230825), new String[]{"B", "A"}));
        list.add(new RoutingObjects("V104", 2, VINE, 0, 1, new LatLng(51.590621, -0.230736), new String[]{"B", "A"}));
        list.add(new RoutingObjects("V105", 2, VINE, 0, 1, new LatLng(51.590619, -0.230715), new String[]{"B", "A"}));

        list.add(new RoutingObjects("Grove Building", 4, GROVE_BLOCK_A, 4, 0, new LatLng(51.588695, -0.230596), new String[]{"C", "D", "E"}));

        //GROVE BLOCK A BASEMENT
        list.add(new RoutingObjects("GB01", 1, GROVE_BLOCK_A, 5, -1, new LatLng(51.588857, -0.230609), new String[]{"A", "B"}));
        list.add(new RoutingObjects("GB04", 2, GROVE_BLOCK_A, 5, -1, new LatLng(51.588690, -0.230542), new String[]{"C", "B"}));
        list.add(new RoutingObjects("GB05", 3, GROVE_BLOCK_A, 5, -1, new LatLng(51.588684, -0.230601), new String[]{"C", "D"}));
        list.add(new RoutingObjects("GB07", 3, GROVE_BLOCK_A, 5, -1, new LatLng(51.588660, -0.230613), new String[]{"C", "D"}));
        list.add(new RoutingObjects("GB09", 4, GROVE_BLOCK_A, 5, -1, new LatLng(51.588611, -0.230608), new String[]{"E", "D"}));
        list.add(new RoutingObjects("GB10", 4, GROVE_BLOCK_A, 5, -1, new LatLng(51.588591, -0.230534), new String[]{"E", "D"}));
        list.add(new RoutingObjects("GB12", 4, GROVE_BLOCK_A, 5, -1, new LatLng(51.588573, -0.230539), new String[]{"E", "D"}));
        list.add(new RoutingObjects("GB17A", 7, GROVE_BLOCK_A, 5, -1, new LatLng(51.588548, -0.230800), new String[]{"F", "G"}));
        list.add(new RoutingObjects("GB17B", 3, GROVE_BLOCK_A, 5, -1, new LatLng(51.588591, -0.230645), new String[]{"C", "D"}));
        list.add(new RoutingObjects("GB19A", 3, GROVE_BLOCK_A, 5, -1, new LatLng(51.588628, -0.230627), new String[]{"C", "D"}));
        list.add(new RoutingObjects("GB19B", 3, GROVE_BLOCK_A, 5, -1, new LatLng(51.588660, -0.230613), new String[]{"C", "D"}));
        list.add(new RoutingObjects("GB21", 2, GROVE_BLOCK_A, 5, -1, new LatLng(51.588721, -0.230703), new String[]{"C", "B"}));
        list.add(new RoutingObjects("GB22", 1, GROVE_BLOCK_A, 5, -1, new LatLng(51.588729, -0.230639), new String[]{"A", "B"}));
        list.add(new RoutingObjects("GB23", 1, GROVE_BLOCK_A, 5, -1, new LatLng(51.588770, -0.230635), new String[]{"A", "B"}));


        //GROVE BLOCK A GROUND FLOOR
        list.add(new RoutingObjects("GG01", 4, GROVE_BLOCK_A, 4, 0, new LatLng(51.588695, -0.230596), new String[]{"C", "D", "E"}));
        list.add(new RoutingObjects("GG04", 4, GROVE_BLOCK_A, 4, 0, new LatLng(51.588637, -0.230321), new String[]{"C", "D", "E"}));
        list.add(new RoutingObjects("GG07", 8, GROVE_BLOCK_A, 4, 0, new LatLng(51.588598, -0.230581), new String[]{"H"}));
        list.add(new RoutingObjects("GG11", 1, GROVE_BLOCK_A, 4, 0, new LatLng(51.588549, -0.230636), new String[]{"A", "B", "C", "H", "G"}));
        list.add(new RoutingObjects("GG12", 1, GROVE_BLOCK_A, 4, 0, new LatLng(51.588549, -0.230636), new String[]{"A", "B", "C", "H", "G"}));
        list.add(new RoutingObjects("GG13", 1, GROVE_BLOCK_A, 4, 0, new LatLng(51.588549, -0.230636), new String[]{"A", "B", "C", "H", "G"}));
        list.add(new RoutingObjects("GG14", 9, GROVE_BLOCK_A, 4, 0, new LatLng(51.588471, -0.230401), new String[]{"G"}));
        list.add(new RoutingObjects("GG15", 9, GROVE_BLOCK_A, 4, 0, new LatLng(51.588471, -0.230401), new String[]{"G"}));
        list.add(new RoutingObjects("GG18", 9, GROVE_BLOCK_A, 4, 0, new LatLng(51.588489, -0.230567), new String[]{"G"}));
        list.add(new RoutingObjects("GG20", 9, GROVE_BLOCK_A, 4, 0, new LatLng(51.588489, -0.230567), new String[]{"G"}));
        list.add(new RoutingObjects("GG21", 9, GROVE_BLOCK_A, 4, 0, new LatLng(51.588489, -0.230567), new String[]{"G"}));
        list.add(new RoutingObjects("GG22", 10, GROVE_BLOCK_A, 4, 0, new LatLng(51.588487, -0.230657), new String[]{"G"}));
        list.add(new RoutingObjects("GG23", 10, GROVE_BLOCK_A, 4, 0, new LatLng(51.588487, -0.230657), new String[]{"G"}));
        list.add(new RoutingObjects("GG25", 7, GROVE_BLOCK_A, 4, 0, new LatLng(51.588536, -0.230798), new String[]{"G", "F"}));
        list.add(new RoutingObjects("GG28", 7, GROVE_BLOCK_A, 4, 0, new LatLng(51.588536, -0.230798), new String[]{"G", "F"}));
        list.add(new RoutingObjects("GG29", 7, GROVE_BLOCK_A, 4, 0, new LatLng(51.588536, -0.230798), new String[]{"G", "F"}));
        list.add(new RoutingObjects("GG30", 7, GROVE_BLOCK_A, 4, 0, new LatLng(51.588536, -0.230798), new String[]{"G", "F"}));
        list.add(new RoutingObjects("GG31", 7, GROVE_BLOCK_A, 4, 0, new LatLng(51.588589, -0.231001), new String[]{"G", "F"}));
        list.add(new RoutingObjects("GG33", 6, GROVE_BLOCK_A, 4, 0, new LatLng(51.588610, -0.230999), new String[]{"E", "F"}));
        list.add(new RoutingObjects("GG34", 6, GROVE_BLOCK_A, 4, 0, new LatLng(51.588643, -0.230975), new String[]{"E", "F"}));
        list.add(new RoutingObjects("GG35", 6, GROVE_BLOCK_A, 4, 0, new LatLng(51.588683, -0.230950), new String[]{"E", "F"}));
        list.add(new RoutingObjects("GG36", 6, GROVE_BLOCK_A, 4, 0, new LatLng(51.588735, -0.230904), new String[]{"E", "F"}));
        list.add(new RoutingObjects("GG37", 5, GROVE_BLOCK_A, 4, 0, new LatLng(51.588728, -0.230820), new String[]{"E", "D", "C"}));
        list.add(new RoutingObjects("GG38", 11, GROVE_BLOCK_A, 4, 0, new LatLng(51.588758, -0.230773), new String[]{"D"}));
        list.add(new RoutingObjects("GG39", 11, GROVE_BLOCK_A, 4, 0, new LatLng(51.588758, -0.230773), new String[]{"D"}));
        list.add(new RoutingObjects("GG41", 11, GROVE_BLOCK_A, 4, 0, new LatLng(51.588685, -0.230764), new String[]{"D"}));
        list.add(new RoutingObjects("GG44", 11, GROVE_BLOCK_A, 4, 0, new LatLng(51.588662, -0.230768), new String[]{"D"}));
        list.add(new RoutingObjects("GG45", 11, GROVE_BLOCK_A, 4, 0, new LatLng(51.588685, -0.230764), new String[]{"D"}));
        list.add(new RoutingObjects("GG46", 5, GROVE_BLOCK_A, 4, 0, new LatLng(51.588732, -0.230880), new String[]{"E", "D", "C"}));
        list.add(new RoutingObjects("GG47", 5, GROVE_BLOCK_A, 4, 0, new LatLng(51.588713, -0.230706), new String[]{"E", "D", "C"}));
        list.add(new RoutingObjects("GG48", 5, GROVE_BLOCK_A, 4, 0, new LatLng(51.588707, -0.230670), new String[]{"E", "D", "C"}));


        //GROVE BLOCK A FIRST FLOOR
        list.add(new RoutingObjects("Grove Atrium", 11, GROVE_BLOCK_A, 3, 1, new LatLng(51.588581, -0.230482), new String[]{"J", "M", "P"}));
        list.add(new RoutingObjects("Coffee Pod", 11, GROVE_BLOCK_A, 3, 1, new LatLng(51.588590, -0.230527), new String[]{"J", "M", "P"}));
        list.add(new RoutingObjects("G101", 13, GROVE_BLOCK_A, 3, 1, new LatLng(51.588690, -0.230571), new String[]{"L", "M", "N"}));
        list.add(new RoutingObjects("G104A", 13, GROVE_BLOCK_A, 3, 1, new LatLng(51.588669, -0.230459), new String[]{"L", "M", "N"}));
        list.add(new RoutingObjects("G104B", 13, GROVE_BLOCK_A, 3, 1, new LatLng(51.588669, -0.230459), new String[]{"L", "M", "N"}));
        list.add(new RoutingObjects("G105", 13, GROVE_BLOCK_A, 3, 1, new LatLng(51.588633, -0.230316), new String[]{"L", "M", "N"}));
        list.add(new RoutingObjects("G107", 12, GROVE_BLOCK_A, 3, 1, new LatLng(51.588441, -0.230401), new String[]{"L", "K"}));
        list.add(new RoutingObjects("G110", 12, GROVE_BLOCK_A, 3, 1, new LatLng(51.588418, -0.230410), new String[]{"L", "K"}));
        list.add(new RoutingObjects("G115", 11, GROVE_BLOCK_A, 3, 1, new LatLng(51.588458, -0.230544), new String[]{"J", "M", "P"}));
        list.add(new RoutingObjects("G116", 11, GROVE_BLOCK_A, 3, 1, new LatLng(51.588458, -0.230544), new String[]{"J", "M", "P"}));
        list.add(new RoutingObjects("G117", 9, GROVE_BLOCK_A, 3, 1, new LatLng(51.588490, -0.230643), new String[]{"I", "O", "N"}));
        list.add(new RoutingObjects("G118", 9, GROVE_BLOCK_A, 3, 1, new LatLng(51.588490, -0.230643), new String[]{"I", "O", "N"}));
        list.add(new RoutingObjects("G120", 8, GROVE_BLOCK_A, 3, 1, new LatLng(51.588473, -0.230791), new String[]{"G", "H"}));
        list.add(new RoutingObjects("G121", 8, GROVE_BLOCK_A, 3, 1, new LatLng(51.588457, -0.230804), new String[]{"G", "H"}));
        list.add(new RoutingObjects("G122", 8, GROVE_BLOCK_A, 3, 1, new LatLng(51.588457, -0.230804), new String[]{"G", "H"}));
        list.add(new RoutingObjects("G122A", 8, GROVE_BLOCK_A, 3, 1, new LatLng(51.588457, -0.230804), new String[]{"G", "H"}));
        list.add(new RoutingObjects("G123A", 8, GROVE_BLOCK_A, 3, 1, new LatLng(51.588457, -0.230804), new String[]{"G", "H"}));
        list.add(new RoutingObjects("G123B", 5, GROVE_BLOCK_A, 3, 1, new LatLng(51.588517, -0.231047), new String[]{"E", "F"}));
        list.add(new RoutingObjects("G124", 7, GROVE_BLOCK_A, 3, 1, new LatLng(51.588493, -0.230826), new String[]{"G", "F"}));
        list.add(new RoutingObjects("G125", 7, GROVE_BLOCK_A, 3, 1, new LatLng(51.588493, -0.230826), new String[]{"G", "F"}));
        list.add(new RoutingObjects("G126", 7, GROVE_BLOCK_A, 3, 1, new LatLng(51.588504, -0.230859), new String[]{"G", "F"}));
        list.add(new RoutingObjects("G127", 7, GROVE_BLOCK_A, 3, 1, new LatLng(51.588504, -0.230859), new String[]{"G", "F"}));
        list.add(new RoutingObjects("G128", 7, GROVE_BLOCK_A, 3, 1, new LatLng(51.588513, -0.230900), new String[]{"G", "F"}));
        list.add(new RoutingObjects("G129", 7, GROVE_BLOCK_A, 3, 1, new LatLng(51.588513, -0.230900), new String[]{"G", "F"}));
        list.add(new RoutingObjects("G130", 7, GROVE_BLOCK_A, 3, 1, new LatLng(51.588523, -0.230941), new String[]{"G", "F"}));
        list.add(new RoutingObjects("G131", 7, GROVE_BLOCK_A, 3, 1, new LatLng(51.588513, -0.230900), new String[]{"G", "F"}));
        list.add(new RoutingObjects("G132", 7, GROVE_BLOCK_A, 3, 1, new LatLng(51.588533, -0.230975), new String[]{"G", "F"}));
        list.add(new RoutingObjects("G133", 7, GROVE_BLOCK_A, 3, 1, new LatLng(51.588523, -0.230941), new String[]{"G", "F"}));
        list.add(new RoutingObjects("G134", 7, GROVE_BLOCK_A, 3, 1, new LatLng(51.588542, -0.231003), new String[]{"G", "F"}));
        list.add(new RoutingObjects("G135", 7, GROVE_BLOCK_A, 3, 1, new LatLng(51.588533, -0.230975), new String[]{"G", "F"}));
        list.add(new RoutingObjects("G136", 7, GROVE_BLOCK_A, 3, 1, new LatLng(51.588542, -0.231003), new String[]{"G", "F"}));
        list.add(new RoutingObjects("G137", 5, GROVE_BLOCK_A, 3, 1, new LatLng(51.588517, -0.231047), new String[]{"E", "F"}));
        list.add(new RoutingObjects("G137A", 5, GROVE_BLOCK_A, 3, 1, new LatLng(51.588517, -0.231047), new String[]{"E", "F"}));
        list.add(new RoutingObjects("G140", 4, GROVE_BLOCK_A, 3, 1, new LatLng(51.588633, -0.230982), new String[]{"E", "D"}));
        list.add(new RoutingObjects("G141", 4, GROVE_BLOCK_A, 3, 1, new LatLng(51.588651, -0.230971), new String[]{"E", "D"}));
        list.add(new RoutingObjects("G146", 3, GROVE_BLOCK_A, 3, 1, new LatLng(51.588703, -0.230762), new String[]{"C"}));
        list.add(new RoutingObjects("G146A", 3, GROVE_BLOCK_A, 3, 1, new LatLng(51.588703, -0.230762), new String[]{"C"}));
        list.add(new RoutingObjects("G147", 3, GROVE_BLOCK_A, 3, 1, new LatLng(51.588669, -0.230758), new String[]{"C"}));
        list.add(new RoutingObjects("G148", 3, GROVE_BLOCK_A, 3, 1, new LatLng(51.588669, -0.230758), new String[]{"C"}));
        list.add(new RoutingObjects("G149", 8, GROVE_BLOCK_A, 3, 1, new LatLng(51.588580, -0.230755), new String[]{"G", "H"}));


        //GROVE BLOCK A SECOND FLOOR
        list.add(new RoutingObjects("G201", 11, GROVE_BLOCK_A, 2, 2, new LatLng(51.588666, -0.230452), new String[]{"M", "N", "B"}));
        list.add(new RoutingObjects("G203", 11, GROVE_BLOCK_A, 2, 2, new LatLng(51.588634, -0.230327), new String[]{"M", "N", "B"}));
        list.add(new RoutingObjects("G204", 11, GROVE_BLOCK_A, 2, 2, new LatLng(51.588634, -0.230327), new String[]{"M", "N", "B"}));
        list.add(new RoutingObjects("G205", 11, GROVE_BLOCK_A, 2, 2, new LatLng(51.588634, -0.230327), new String[]{"M", "N", "B"}));
        list.add(new RoutingObjects("G206", 9, GROVE_BLOCK_A, 2, 2, new LatLng(51.588442, -0.230414), new String[]{"L", "M"}));
        list.add(new RoutingObjects("G207", 9, GROVE_BLOCK_A, 2, 2, new LatLng(51.588442, -0.230414), new String[]{"L", "M"}));
        list.add(new RoutingObjects("G209", 8, GROVE_BLOCK_A, 2, 2, new LatLng(51.588460, -0.230557), new String[]{"N", "K"}));
        list.add(new RoutingObjects("G210", 8, GROVE_BLOCK_A, 2, 2, new LatLng(51.588460, -0.230557), new String[]{"N", "K"}));
        list.add(new RoutingObjects("G211", 7, GROVE_BLOCK_A, 2, 2, new LatLng(51.588490, -0.230643), new String[]{"J", "P", "C"}));
        list.add(new RoutingObjects("G212", 7, GROVE_BLOCK_A, 2, 2, new LatLng(51.588490, -0.230643), new String[]{"J", "P", "C"}));
        list.add(new RoutingObjects("G214A", 6, GROVE_BLOCK_A, 2, 2, new LatLng(51.588530, -0.230766), new String[]{"J", "I", "H", "G"}));
        list.add(new RoutingObjects("G214B", 6, GROVE_BLOCK_A, 2, 2, new LatLng(51.588566, -0.230908), new String[]{"J", "I", "H", "G"}));
        list.add(new RoutingObjects("G214C", 6, GROVE_BLOCK_A, 2, 2, new LatLng(51.588593, -0.231005), new String[]{"J", "I", "H", "G"}));
        list.add(new RoutingObjects("G230", 5, GROVE_BLOCK_A, 2, 2, new LatLng(51.588620, -0.230779), new String[]{"D", "I"}));
        list.add(new RoutingObjects("G231", 2, GROVE_BLOCK_A, 2, 2, new LatLng(51.588709, -0.230674), new String[]{"C", "D", "E", "F"}));
        list.add(new RoutingObjects("G234", 1, GROVE_BLOCK_A, 2, 2, new LatLng(51.588705, -0.230544), new String[]{"A", "B", "O"}));
        list.add(new RoutingObjects("G235", 1, GROVE_BLOCK_A, 2, 2, new LatLng(51.588705, -0.230544), new String[]{"A", "B", "O"}));

        //GROVE BLOCK A THIRD FLOOR
        list.add(new RoutingObjects("G301", 2, GROVE_BLOCK_A, 1, 3, new LatLng(51.588666, -0.230452), new String[]{"A"}));
        list.add(new RoutingObjects("G304", 2, GROVE_BLOCK_A, 1, 3, new LatLng(51.588693, -0.230595), new String[]{"A"}));
        list.add(new RoutingObjects("G307", 2, GROVE_BLOCK_A, 1, 3, new LatLng(51.588708, -0.230660), new String[]{"A"}));
        list.add(new RoutingObjects("G308", 2, GROVE_BLOCK_A, 1, 3, new LatLng(51.588714, -0.230711), new String[]{"A"}));
        list.add(new RoutingObjects("G309", 1, GROVE_BLOCK_A, 1, 3, new LatLng(51.588755, -0.230633), new String[]{"A", "B"}));

        //GROVE BLOCK A FOURTH FLOOR
        list.add(new RoutingObjects("G401", 1, GROVE_BLOCK_A, 0, 4, new LatLng(51.588755, -0.230633), new String[]{"A"}));
        list.add(new RoutingObjects("G404", 1, GROVE_BLOCK_A, 0, 4, new LatLng(51.588740, -0.230650), new String[]{"A"}));
        list.add(new RoutingObjects("G405", 1, GROVE_BLOCK_A, 0, 4, new LatLng(51.588717, -0.230658), new String[]{"A"}));
        list.add(new RoutingObjects("G406A", 1, GROVE_BLOCK_A, 0, 4, new LatLng(51.588716, -0.230648), new String[]{"A"}));

        //GROVE BLOCK B GROUND FLOOR
        list.add(new RoutingObjects("GG71", 1, GROVE_BLOCK_B, 2, 0, new LatLng(51.588949, -0.230396), new String[]{"A", "B"}));
        list.add(new RoutingObjects("GG78", 1, GROVE_BLOCK_B, 2, 0, new LatLng(51.588900, -0.230214), new String[]{"A", "B"}));
        list.add(new RoutingObjects("GG79", 1, GROVE_BLOCK_B, 2, 0, new LatLng(51.588897, -0.230192), new String[]{"A", "B"}));
        list.add(new RoutingObjects("GG72", 2, GROVE_BLOCK_B, 2, 0, new LatLng(51.588965, -0.230216), new String[]{"B"}));
        list.add(new RoutingObjects("GG73", 2, GROVE_BLOCK_B, 2, 0, new LatLng(51.588965, -0.230216), new String[]{"B"}));
        list.add(new RoutingObjects("GG74", 2, GROVE_BLOCK_B, 2, 0, new LatLng(51.588993, -0.230200), new String[]{"B"}));
        list.add(new RoutingObjects("GG75", 2, GROVE_BLOCK_B, 2, 0, new LatLng(51.588993, -0.230200), new String[]{"B"}));
        list.add(new RoutingObjects("GG77", 2, GROVE_BLOCK_B, 2, 0, new LatLng(51.588993, -0.230200), new String[]{"B"}));
        list.add(new RoutingObjects("GG77", 2, GROVE_BLOCK_B, 2, 0, new LatLng(51.588965, -0.230216), new String[]{"B"}));

        //GROVE BLOCK B FIRST FLOOR
        list.add(new RoutingObjects("G170", 2, GROVE_BLOCK_B, 1, 1, new LatLng(51.588927, -0.230365), new String[]{"A", "B"}));
        list.add(new RoutingObjects("G171", 2, GROVE_BLOCK_B, 1, 1, new LatLng(51.588925, -0.230347), new String[]{"A", "B"}));
        list.add(new RoutingObjects("G172", 2, GROVE_BLOCK_B, 1, 1, new LatLng(51.588905, -0.230247), new String[]{"A", "B"}));
        list.add(new RoutingObjects("G174", 2, GROVE_BLOCK_B, 1, 1, new LatLng(51.588896, -0.230199), new String[]{"A", "B"}));
        list.add(new RoutingObjects("G175", 2, GROVE_BLOCK_B, 1, 1, new LatLng(51.588890, -0.230162), new String[]{"A", "B"}));
        list.add(new RoutingObjects("G176", 3, GROVE_BLOCK_B, 1, 1, new LatLng(51.588940, -0.230120), new String[]{"C", "B"}));
        list.add(new RoutingObjects("G177", 3, GROVE_BLOCK_B, 1, 1, new LatLng(51.588960, -0.230111), new String[]{"C", "B"}));
        list.add(new RoutingObjects("G178", 3, GROVE_BLOCK_B, 1, 1, new LatLng(51.588960, -0.230111), new String[]{"C", "B"}));
        list.add(new RoutingObjects("G179", 4, GROVE_BLOCK_B, 1, 1, new LatLng(51.588952, -0.230076), new String[]{"C"}));
        list.add(new RoutingObjects("G180", 4, GROVE_BLOCK_B, 1, 1, new LatLng(51.588936, -0.230044), new String[]{"C"}));
        list.add(new RoutingObjects("G181", 3, GROVE_BLOCK_B, 1, 1, new LatLng(51.588923, -0.230113), new String[]{"C", "B"}));
        list.add(new RoutingObjects("G182", 3, GROVE_BLOCK_B, 1, 1, new LatLng(51.588905, -0.230121), new String[]{"C", "B"}));

        //GROVE BLOCK B SECOND FLOOR
        list.add(new RoutingObjects("G270", 2, GROVE_BLOCK_B, 0, 2, new LatLng(51.588932, -0.230382), new String[]{"A", "B"}));
        list.add(new RoutingObjects("G271", 2, GROVE_BLOCK_B, 0, 2, new LatLng(51.588925, -0.230348), new String[]{"A", "B"}));
        list.add(new RoutingObjects("G272", 2, GROVE_BLOCK_B, 0, 2, new LatLng(51.588906, -0.230249), new String[]{"A", "B"}));
        list.add(new RoutingObjects("G274", 3, GROVE_BLOCK_B, 0, 2, new LatLng(51.588917, -0.230128), new String[]{"B"}));

        //GROVE BLOCK C GROUND FLOOR
        list.add(new RoutingObjects("GG90", 3, GROVE_BLOCK_C, 2, 0, new LatLng(51.589272, -0.230063), new String[]{"C", "B", "D"}));
        list.add(new RoutingObjects("GG92", 3, GROVE_BLOCK_C, 2, 0, new LatLng(51.589265, -0.229971), new String[]{"C", "B", "D"}));
        list.add(new RoutingObjects("GG93", 4, GROVE_BLOCK_C, 2, 0, new LatLng(51.589237, -0.230050), new String[]{"B"}));
        list.add(new RoutingObjects("GG94", 4, GROVE_BLOCK_C, 2, 0, new LatLng(51.589237, -0.230050), new String[]{"B"}));
        list.add(new RoutingObjects("GG96", 1, GROVE_BLOCK_C, 2, 0, new LatLng(51.589178, -0.230249), new String[]{"A"}));
        list.add(new RoutingObjects("GG97", 2, GROVE_BLOCK_C, 2, 0, new LatLng(51.589194, -0.230243), new String[]{"A", "E", "C"}));
        list.add(new RoutingObjects("GG98", 2, GROVE_BLOCK_C, 2, 0, new LatLng(51.589205, -0.230231), new String[]{"A", "E", "C"}));

        //GROVE BLOCK C FIRST FLOOR
        list.add(new RoutingObjects("G190", 3, GROVE_BLOCK_C, 1, 1, new LatLng(51.589256, -0.230171), new String[]{"B"}));
        list.add(new RoutingObjects("G191", 2, GROVE_BLOCK_C, 1, 1, new LatLng(51.589210, -0.230185), new String[]{"A", "B"}));
        list.add(new RoutingObjects("G192", 3, GROVE_BLOCK_C, 1, 1, new LatLng(51.589256, -0.230171), new String[]{"B"}));

        //PORTACABIN A GROUND FLOOR
        list.add(new RoutingObjects("Portakabin A", 2, PORTACABIN_A, 1, 0, new LatLng(51.589736, -0.230232), new String[]{"A"}));
        list.add(new RoutingObjects("PAG03", 2, PORTACABIN_A, 1, 0, new LatLng(51.589736, -0.230232), new String[]{"A"}));
        list.add(new RoutingObjects("PAG04", 2, PORTACABIN_A, 1, 0, new LatLng(51.589736, -0.230232), new String[]{"A"}));
        list.add(new RoutingObjects("PAG07", 2, PORTACABIN_A, 1, 0, new LatLng(51.589711, -0.230239), new String[]{"A"}));
        list.add(new RoutingObjects("PAG08", 2, PORTACABIN_A, 1, 0, new LatLng(51.589711, -0.230239), new String[]{"A"}));

        //PORTACABIN A FIRST FLOOR
        list.add(new RoutingObjects("PA101", 2, PORTACABIN_A, 0, 1, new LatLng(51.589736, -0.230232), new String[]{"A"}));
        list.add(new RoutingObjects("PA102", 2, PORTACABIN_A, 0, 1, new LatLng(51.589736, -0.230232), new String[]{"A"}));
        list.add(new RoutingObjects("PA103", 2, PORTACABIN_A, 0, 1, new LatLng(51.589711, -0.230239), new String[]{"A"}));
        list.add(new RoutingObjects("PA104", 2, PORTACABIN_A, 0, 1, new LatLng(51.589711, -0.230239), new String[]{"A"}));

        //PORTACABIN A EXT GROUND FLOOR
        list.add(new RoutingObjects("PAG01", 1, PORTACABIN_A_EXT, 1, 0, new LatLng(51.589881, -0.230205), new String[]{"A"}));
        list.add(new RoutingObjects("PAG02", 1, PORTACABIN_A_EXT, 1, 0, new LatLng(51.589857, -0.230211), new String[]{"A"}));
        list.add(new RoutingObjects("PAG05", 1, PORTACABIN_A_EXT, 1, 0, new LatLng(51.589866, -0.230170), new String[]{"A"}));
        list.add(new RoutingObjects("PAG06", 1, PORTACABIN_A_EXT, 1, 0, new LatLng(51.589866, -0.230170), new String[]{"A"}));

        //PORTACABIN 6 & 7

        list.add(new RoutingObjects("Portakabin 6 & 7", 3, PORTACABIN_67, 1, 0, new LatLng(51.589699, -0.229967), new String[]{"B"}));
        list.add(new RoutingObjects("P6G01", 3, PORTACABIN_67, 1, 0, new LatLng(51.589699, -0.229967), new String[]{"B"}));
        list.add(new RoutingObjects("P6G02", 2, PORTACABIN_67, 1, 0, new LatLng(51.589686, -0.229844), new String[]{"A"}));
        list.add(new RoutingObjects("P7101", 2, PORTACABIN_67, 1, 0, new LatLng(51.589686, -0.229844), new String[]{"A"}));
        list.add(new RoutingObjects("P7102", 3, PORTACABIN_67, 1, 0, new LatLng(51.589699, -0.229967), new String[]{"B"}));

        //PORTACABIN B
        list.add(new RoutingObjects("Portakabin B", 1, PORTACABIN_B, 0, 0, new LatLng(51.590717, -0.229092), new String[]{"A"}));
        list.add(new RoutingObjects("PBG01", 1, PORTACABIN_B, 0, 0, new LatLng(51.590717, -0.229092), new String[]{"A"}));
        list.add(new RoutingObjects("PBG02", 1, PORTACABIN_B, 0, 0, new LatLng(51.590717, -0.229092), new String[]{"A"}));

        //BARN
        list.add(new RoutingObjects("Barn", 1, BARN, 0, 0, new LatLng(51.590952, -0.228580), new String[]{"A"}));
        list.add(new RoutingObjects("BAG01", 1, BARN, 0, 0, new LatLng(51.590952, -0.228580), new String[]{"A"}));
        list.add(new RoutingObjects("BAG02", 3, BARN, 0, 0, new LatLng(51.591056, -0.228586), new String[]{"B"}));
        return list;
    }

    private static RoutingObjects getBuilding(String building) {
        ArrayList<RoutingObjects> list = new ArrayList<>();
        list.add(new RoutingObjects(COLLEGE, true, true, true));
        list.add(new RoutingObjects(HATHCROFT, true, true, true));
        list.add(new RoutingObjects(WILLIAMS, true, true, true));
        list.add(new RoutingObjects(SHEPPARDLIBRARY, true, true, true));
        list.add(new RoutingObjects(VINE, true, false, true));
        list.add(new RoutingObjects(BARN, false, false, true));
        list.add(new RoutingObjects(GROVE_BLOCK_A, true, true, true));
        list.add(new RoutingObjects(GROVE_BLOCK_B, true, true, true));
        list.add(new RoutingObjects(GROVE_BLOCK_C, true, true, true));
        list.add(new RoutingObjects(BUILDING9, false, false, true));
        list.add(new RoutingObjects(MDXHOUSE, true, true, true));
        list.add(new RoutingObjects(PORTACABIN_A, true, false, true));
        list.add(new RoutingObjects(PORTACABIN_A_EXT, false, false, true));
        list.add(new RoutingObjects(PORTACABIN_67, true, false, false));
        list.add(new RoutingObjects(PORTACABIN_B, false, false, true));
        list.add(new RoutingObjects(BUILDING10, true, false, false));
        list.add(new RoutingObjects(CIRCLE_CAFE, false, false, true));

        return list.parallelStream()
                .filter(buildingObject -> buildingObject.getName().equals(building))
                .findFirst().get();
    }

    public int getGmapIntForGroundFloor(String building) {
        return RoutingObjectsGetterUtilService.getConnectors(building, 0).get(0).gMapLevel;
    }


    public boolean checkIfCurrentAlphaContainsDestination(int destination) {
        return Arrays.stream(primeLanes).anyMatch(primeLane -> primeLane == destination);
    }

}
