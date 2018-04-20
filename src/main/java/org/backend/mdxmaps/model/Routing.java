package org.backend.mdxmaps.model;

import org.backend.mdxmaps.model.enums.Building;
import org.backend.mdxmaps.model.enums.ObjectType;
import org.backend.mdxmaps.service.util.RoutingObjectsGetterUtilService;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Objects;

import static org.backend.mdxmaps.model.enums.Building.BARN;
import static org.backend.mdxmaps.model.enums.Building.BUILDING10;
import static org.backend.mdxmaps.model.enums.Building.BUILDING9;
import static org.backend.mdxmaps.model.enums.Building.CIRCLE_CAFE;
import static org.backend.mdxmaps.model.enums.Building.COLLEGE;
import static org.backend.mdxmaps.model.enums.Building.GROVE_BLOCK_A;
import static org.backend.mdxmaps.model.enums.Building.GROVE_BLOCK_B;
import static org.backend.mdxmaps.model.enums.Building.GROVE_BLOCK_C;
import static org.backend.mdxmaps.model.enums.Building.HATCHCROFT;
import static org.backend.mdxmaps.model.enums.Building.MDXHOUSE;
import static org.backend.mdxmaps.model.enums.Building.PORTAKABIN_6_7;
import static org.backend.mdxmaps.model.enums.Building.PORTAKABIN_A;
import static org.backend.mdxmaps.model.enums.Building.PORTAKABIN_A_EXT;
import static org.backend.mdxmaps.model.enums.Building.PORTAKABIN_B;
import static org.backend.mdxmaps.model.enums.Building.SHEPPARDLIBRARY;
import static org.backend.mdxmaps.model.enums.Building.VINE;
import static org.backend.mdxmaps.model.enums.Building.WILLIAMS;
import static org.backend.mdxmaps.model.enums.ObjectType.BASIC_CONNECTOR;
import static org.backend.mdxmaps.model.enums.ObjectType.DOOR;
import static org.backend.mdxmaps.model.enums.ObjectType.ELEVATOR;
import static org.backend.mdxmaps.model.enums.ObjectType.ROOM;
import static org.backend.mdxmaps.model.enums.ObjectType.STAIR;

/**
 * Created by Emmanuel Keboh on 27/11/2016.
 */

public class Routing implements Comparable<Routing> {

    //Connectors, room and buildings attribute
    private String name;

    //Connectors and Room Attributes
    private int gMapLevel;
    private int level;
    private LatLng latLng;

    //Connectors and Buildings Attributes
    private boolean isWheelChairAccessible;

    //Connectors-Only Attributes
    private int[] primeLanes;
    private String[] adjacentConnectors;
    private ObjectType type;


    //Room-Only Attributes
    private String[] laneConnectors;
    private int lane;
    private Building building;

    //Building-Only Attributes
    private boolean hasStairs;
    private boolean hasElevators;


    //Constructor for connectors, stairs, elevators & doors
    public Routing(String name, ObjectType type, int[] primeLanes, int gMapLevel,
                   int level, LatLng latLng, String[] adjacentConnectors, boolean isWheelChairAccessible) {
        this.name = name;
        this.type = type;
        this.primeLanes = primeLanes;
        this.gMapLevel = gMapLevel;
        this.level = level;
        this.latLng = latLng;
        this.adjacentConnectors = adjacentConnectors;
        this.isWheelChairAccessible = isWheelChairAccessible;
    }

    //Constructor for outside objects
    public Routing(String name, ObjectType type, LatLng latLng, String[] adjacentConnectors, boolean isWheelChairAccessible) {
        this.name = name;
        this.type = type;
        this.latLng = latLng;
        this.adjacentConnectors = adjacentConnectors;
        this.isWheelChairAccessible = isWheelChairAccessible;
    }

    //Constructor for rooms
    public Routing(String name, ObjectType type, int lane, Building building, int gMapLevel, int level, LatLng latLng, String[] laneConnectors) {
        this.name = name;
        this.type = type;
        this.lane = lane;
        this.building = building;
        this.gMapLevel = gMapLevel;
        this.level = level;
        this.latLng = latLng;
        this.laneConnectors = laneConnectors;
    }

    //Constructor for buildings
    public Routing(Building building, boolean hasStairs, boolean hasElevators, boolean wheelChairAccessible) {
        this.building = building;
        this.hasStairs = hasStairs;
        this.hasElevators = hasElevators;
        this.isWheelChairAccessible = wheelChairAccessible;
    }

    public Routing() {
    }


    //Connectors and Campus
    public String getName() {
        return name;
    }

    public int getGMapLevel() {
        return gMapLevel;
    }

    public LatLng getLatLng() {
        return latLng;
    }

    public int getLevel() {
        return level;
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

    public boolean isWheelChairAccessible() {
        return isWheelChairAccessible;
    }

    //Just Campus
    public String[] getLaneConnectors() {
        return laneConnectors;
    }

    public int getLane() {
        return lane;
    }

    public Building getBuilding() {
        return building;
    }

    // @JsonIgnore
    public Routing getBuildingObject() {
        return getBuilding(building);
    }


    public boolean hasStairs() {
        return hasStairs;
    }

    public boolean hasElevators() {
        return hasElevators;
    }

    public static HashMap<Building, ArrayList<Routing>> connectorMapInit(Building building, int level) {
        HashMap<Building, ArrayList<Routing>> connectorMap = new HashMap<>();
        switch (building) {
            case COLLEGE:
                connectorMap.put(COLLEGE, getCollegeConnectors(level));
                break;
            case HATCHCROFT:
                connectorMap.put(HATCHCROFT, getHatchcroftConnectors(level));
                break;
            case WILLIAMS:
                connectorMap.put(WILLIAMS, getWilliamsConnectors(level));
                break;
            case SHEPPARDLIBRARY:
                connectorMap.put(SHEPPARDLIBRARY, getShepherdConnectors(level));
                break;
            case GROVE_BLOCK_A:
                connectorMap.put(GROVE_BLOCK_A, getGroveBlockAConnectors(level));
                break;
            case GROVE_BLOCK_B:
                connectorMap.put(GROVE_BLOCK_B, getGroveBlockBConnectors(level));
                break;
            case GROVE_BLOCK_C:
                connectorMap.put(GROVE_BLOCK_C, getGroveBlockCConnectors(level));
                break;
            case MDXHOUSE:
                connectorMap.put(MDXHOUSE, getMDXHouseConnectors(level));
                break;
            case BUILDING9:
                connectorMap.put(BUILDING9, getBuilding9Connetors());
                break;
            case BUILDING10:
                connectorMap.put(BUILDING10, getBuilding10Connetors(level));
            case VINE:
                connectorMap.put(VINE, getVineConnectors(level));
                break;
            case PORTAKABIN_A:
                connectorMap.put(PORTAKABIN_A, getPortacabinAConnectors(level));
                break;
            case PORTAKABIN_A_EXT:
                connectorMap.put(PORTAKABIN_A_EXT, getPortacabinAEXTConnectors());
                break;
            case PORTAKABIN_B:
                connectorMap.put(PORTAKABIN_B, getPortacabinBConnectors());
            case PORTAKABIN_6_7:
                connectorMap.put(PORTAKABIN_6_7, getPortacabin67Connectors());
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

    private static ArrayList<Routing> getCollegeConnectors(int level) {
        ArrayList<Routing> list = new ArrayList<>();

        switch (level) {
            case 0:
                //Ground floor connectors, stairs, elevators and doors
                list.add(new Routing("A", BASIC_CONNECTOR, new int[]{1, 12}, 2, 0, new LatLng(51.589945, -0.228274), new String[]{"B", "U"}, true));
                list.add(new Routing("B", BASIC_CONNECTOR, new int[]{1, 4}, 2, 0, new LatLng(51.589977, -0.228560), new String[]{"A", "C", "S"}, true));
                list.add(new Routing("C", BASIC_CONNECTOR, new int[]{1, 5}, 2, 0, new LatLng(51.590003, -0.228806), new String[]{"B", "V", "Q"}, true));
                list.add(new Routing("D", BASIC_CONNECTOR, new int[]{1, 6}, 2, 0, new LatLng(51.590030, -0.229051), new String[]{"V", "E", "O"}, true));
                list.add(new Routing("E", BASIC_CONNECTOR, new int[]{1, 7}, 2, 0, new LatLng(51.590054, -0.229273), new String[]{"D", "I", "Z"}, true));
                list.add(new Routing("G", BASIC_CONNECTOR, new int[]{8, 14}, 2, 0, new LatLng(51.589910, -0.229450), new String[]{"H", "J"}, true));
                list.add(new Routing("H", BASIC_CONNECTOR, new int[]{14}, 2, 0, new LatLng(51.589903, -0.229378), new String[]{"G", "I"}, false));
                list.add(new Routing("I", BASIC_CONNECTOR, new int[]{7, 14}, 2, 0, new LatLng(51.589898, -0.229318), new String[]{"E", "H", "M"}, true));
                list.add(new Routing("J", BASIC_CONNECTOR, new int[]{8, 10}, 2, 0, new LatLng(51.589819, -0.229477), new String[]{"G", "L"}, true));
                list.add(new Routing("K", BASIC_CONNECTOR, new int[]{3, 9}, 2, 0, new LatLng(51.589703, -0.229464), new String[]{"L", "N", "Y"}, true));
                list.add(new Routing("L", BASIC_CONNECTOR, new int[]{9, 10}, 2, 0, new LatLng(51.589809, -0.229434), new String[]{"J", "K", "M"}, true));
                list.add(new Routing("M", BASIC_CONNECTOR, new int[]{7, 10}, 2, 0, new LatLng(51.589802, -0.229347), new String[]{"I", "L"}, true));
                list.add(new Routing("N", BASIC_CONNECTOR, new int[]{3, 6}, 2, 0, new LatLng(51.589668, -0.229155), new String[]{"K", "O", "X"}, true));
                list.add(new Routing("O", BASIC_CONNECTOR, new int[]{2, 6}, 2, 0, new LatLng(51.589850, -0.229099), new String[]{"D", "N", "W"}, true));
                list.add(new Routing("P", BASIC_CONNECTOR, new int[]{3, 5}, 2, 0, new LatLng(51.589639, -0.228897), new String[]{"X", "Q", "R"}, true));
                list.add(new Routing("Q", BASIC_CONNECTOR, new int[]{2, 5}, 2, 0, new LatLng(51.589825, -0.228839), new String[]{"C", "W", "P", "S"}, true));
                list.add(new Routing("R", BASIC_CONNECTOR, new int[]{3, 4}, 2, 0, new LatLng(51.589610, -0.228666), new String[]{"P", "S", "T"}, true));
                list.add(new Routing("S", BASIC_CONNECTOR, new int[]{2, 4}, 2, 0, new LatLng(51.589806, -0.228613), new String[]{"B", "Q", "R"}, true));
                list.add(new Routing("T", BASIC_CONNECTOR, new int[]{3, 13}, 2, 0, new LatLng(51.589582, -0.228378), new String[]{"R"}, true));
                list.add(new Routing("U", BASIC_CONNECTOR, new int[]{12, 15}, 2, 0, new LatLng(51.590054, -0.228247), new String[]{"A"}, true));
                list.add(new Routing("V", BASIC_CONNECTOR, new int[]{1, 16}, 2, 0, new LatLng(51.590022, -0.228981), new String[]{"C", "D"}, true));
                list.add(new Routing("W", BASIC_CONNECTOR, new int[]{2, 16, 17}, 2, 0, new LatLng(51.589844, -0.229032), new String[]{"O", "Q"}, true));
                list.add(new Routing("X", BASIC_CONNECTOR, new int[]{3, 17}, 2, 0, new LatLng(51.589659, -0.229084), new String[]{"N", "P"}, true));
                list.add(new Routing("Y", BASIC_CONNECTOR, new int[]{3}, 2, 0, new LatLng(51.589723, -0.229636), new String[]{"K"}, true));
                list.add(new Routing("Z", BASIC_CONNECTOR, new int[]{1}, 2, 0, new LatLng(51.590086, -0.229538), new String[]{"E"}, true));

                list.add(new Routing("S1", STAIR, new int[]{3}, 2, 0, new LatLng(51.589662, -0.229134), null, false));
                list.add(new Routing("S2", STAIR, new int[]{1}, 2, 0, new LatLng(51.590033, -0.229028), null, false));
                list.add(new Routing("S3", STAIR, new int[]{13}, 2, 0, new LatLng(51.589615, -0.228371), null, false));
                list.add(new Routing("S4", STAIR, new int[]{12}, 2, 0, new LatLng(51.589921, -0.228284), null, false));
                list.add(new Routing("S5", STAIR, new int[]{4}, 2, 0, new LatLng(51.589675, -0.228648), null, false));
                list.add(new Routing("S6", STAIR, new int[]{4}, 2, 0, new LatLng(51.589918, -0.228575), null, false));

                list.add(new Routing("E1", ELEVATOR, new int[]{5}, 2, 0, new LatLng(51.589727, -0.228875), null, true));
                list.add(new Routing("E2", ELEVATOR, new int[]{1}, 2, 0, new LatLng(51.590027, -0.228994), null, true));

                list.add(new Routing("D1", DOOR, new int[]{6}, 2, 0, new LatLng(51.590099, -0.229035), new String[]{"D", "K"}, true));
                list.add(new Routing("D2", DOOR, new int[]{6}, 2, 0, new LatLng(51.589599, -0.229185), new String[]{"Y", "R"}, true));
                list.add(new Routing("D3", DOOR, new int[]{3}, 2, 0, new LatLng(51.589731, -0.229684), new String[]{"Y", "O"}, false));
                list.add(new Routing("D4", DOOR, new int[]{1}, 2, 0, new LatLng(51.590091, -0.229583), new String[]{"Z", "N"}, false));
                list.add(new Routing("D5", DOOR, new int[]{15}, 2, 0, new LatLng(51.590084, -0.228142), new String[]{"U", "A"}, false));
                break;
            case 1:
                //First floor connectors, stairs and elevators
                list.add(new Routing("A", BASIC_CONNECTOR, new int[]{1, 3}, 1, 1, new LatLng(51.589943, -0.228275), new String[]{"B", "G"}, true));
                list.add(new Routing("B", BASIC_CONNECTOR, new int[]{1, 4}, 1, 1, new LatLng(51.589988, -0.228687), new String[]{"A", "D", "N"}, true));
                list.add(new Routing("C", BASIC_CONNECTOR, new int[]{1, 6}, 1, 1, new LatLng(51.590015, -0.228915), new String[]{"N", "F", "O"}, true));
                list.add(new Routing("D", BASIC_CONNECTOR, new int[]{4, 5}, 1, 1, new LatLng(51.589949, -0.228696), new String[]{"B", "M"}, true));
                list.add(new Routing("E", BASIC_CONNECTOR, new int[]{2, 11}, 1, 1, new LatLng(51.589698, -0.229416), new String[]{"P", "J"}, true));
                list.add(new Routing("F", BASIC_CONNECTOR, new int[]{6}, 1, 1, new LatLng(51.589836, -0.228956), new String[]{"C", "J", "L", "M"}, true));
                list.add(new Routing("G", BASIC_CONNECTOR, new int[]{2, 3}, 1, 1, new LatLng(51.589582, -0.228378), new String[]{"A", "H"}, true));
                list.add(new Routing("H", BASIC_CONNECTOR, new int[]{2, 8}, 1, 1, new LatLng(51.589628, -0.228792), new String[]{"G", "I", "K"}, true));
                list.add(new Routing("I", BASIC_CONNECTOR, new int[]{7, 8}, 1, 1, new LatLng(51.589669, -0.228773), new String[]{"H", "L"}, true));
                list.add(new Routing("J", BASIC_CONNECTOR, new int[]{2, 6}, 1, 1, new LatLng(51.589654, -0.229015), new String[]{"E", "F", "K"}, true));
                list.add(new Routing("K", BASIC_CONNECTOR, new int[]{2, 9}, 1, 1, new LatLng(51.589642, -0.228922), new String[]{"H", "J", "L"}, true));
                list.add(new Routing("L", BASIC_CONNECTOR, new int[]{7, 9}, 1, 1, new LatLng(51.589724, -0.228898), new String[]{"F", "I", "K"}, true));
                list.add(new Routing("M", BASIC_CONNECTOR, new int[]{5, 10}, 1, 1, new LatLng(51.589931, -0.228836), new String[]{"D", "F", "N"}, true));
                list.add(new Routing("N", BASIC_CONNECTOR, new int[]{1, 10}, 1, 1, new LatLng(51.590005, -0.228819), new String[]{"B", "C", "M"}, true));
                list.add(new Routing("O", BASIC_CONNECTOR, new int[]{1, 13}, 1, 1, new LatLng(51.590066, -0.229359), new String[]{"Q", "C"}, true));
                list.add(new Routing("P", BASIC_CONNECTOR, new int[]{12, 11}, 1, 1, new LatLng(51.590033, -0.229321), new String[]{"E", "Q"}, true));
                list.add(new Routing("Q", BASIC_CONNECTOR, new int[]{12, 13}, 1, 1, new LatLng(51.590037, -0.229370), new String[]{"O", "P"}, true));

                list.add(new Routing("S1", STAIR, new int[]{2}, 1, 1, new LatLng(51.589662, -0.229134), null, false));
                list.add(new Routing("S2", STAIR, new int[]{1}, 1, 1, new LatLng(51.590033, -0.229028), null, false));
                list.add(new Routing("S3", STAIR, new int[]{3}, 1, 1, new LatLng(51.589615, -0.228371), null, false));
                list.add(new Routing("S4", STAIR, new int[]{3}, 1, 1, new LatLng(51.589921, -0.228284), null, false));
                list.add(new Routing("S5", STAIR, new int[]{7, 8}, 1, 1, new LatLng(51.589668, -0.228775), null, false));
                list.add(new Routing("S6", STAIR, new int[]{4, 5}, 1, 1, new LatLng(51.589947, -0.228689), null, false));

                list.add(new Routing("E1", ELEVATOR, new int[]{7, 9}, 1, 1, new LatLng(51.589727, -0.228875), null, true));
                list.add(new Routing("E2", ELEVATOR, new int[]{1}, 1, 1, new LatLng(51.590027, -0.228994), null, true));
                break;
            case 2:
                list.add(new Routing("A", BASIC_CONNECTOR, new int[]{1, 2}, 0, 2, new LatLng(51.589583, -0.228376), new String[]{"A"}, true));
                list.add(new Routing("B", BASIC_CONNECTOR, new int[]{3, 2}, 0, 2, new LatLng(51.589647, -0.228961), new String[]{"A", "C", "T"}, true));
                list.add(new Routing("C", BASIC_CONNECTOR, new int[]{5, 2}, 0, 2, new LatLng(51.589650, -0.229003), new String[]{"B", "D", "Q"}, true));
                list.add(new Routing("D", BASIC_CONNECTOR, new int[]{6, 2}, 0, 2, new LatLng(51.589656, -0.229049), new String[]{"F", "C", "G"}, true));
                list.add(new Routing("F", BASIC_CONNECTOR, new int[]{2, 10}, 0, 2, new LatLng(51.589675, -0.229197), new String[]{"D", "J"}, false));
                list.add(new Routing("G", BASIC_CONNECTOR, new int[]{6, 7}, 0, 2, new LatLng(51.589682, -0.229042), new String[]{"D", "H"}, true));
                list.add(new Routing("H", BASIC_CONNECTOR, new int[]{8, 7}, 0, 2, new LatLng(51.589694, -0.229150), new String[]{"G", "I"}, true));
                list.add(new Routing("I", BASIC_CONNECTOR, new int[]{8, 9}, 0, 2, new LatLng(51.589807, -0.229117), new String[]{"H", "J"}, true));
                list.add(new Routing("J", BASIC_CONNECTOR, new int[]{10, 9}, 0, 2, new LatLng(51.589813, -0.229155), new String[]{"F", "I", "K"}, true));
                list.add(new Routing("K", BASIC_CONNECTOR, new int[]{10, 11}, 0, 2, new LatLng(51.590038, -0.229089), new String[]{"J", "L"}, false));
                list.add(new Routing("L", BASIC_CONNECTOR, new int[]{5, 11}, 0, 2, new LatLng(51.590018, -0.228909), new String[]{"O", "K", "M"}, true));
                list.add(new Routing("M", BASIC_CONNECTOR, new int[]{12, 11}, 0, 2, new LatLng(51.590008, -0.228827), new String[]{"L", "N"}, true));
                list.add(new Routing("N", BASIC_CONNECTOR, new int[]{12, 13}, 0, 2, new LatLng(51.589948, -0.228838), new String[]{"O", "M"}, true));
                list.add(new Routing("O", BASIC_CONNECTOR, new int[]{5}, 0, 2, new LatLng(51.589937, -0.228931), new String[]{"Q", "N", "L"}, true));
                list.add(new Routing("Q", BASIC_CONNECTOR, new int[]{5}, 0, 2, new LatLng(51.589738, -0.228989), new String[]{"C", "O", "R"}, true));
                list.add(new Routing("R", BASIC_CONNECTOR, new int[]{3, 14}, 0, 2, new LatLng(51.589733, -0.228938), new String[]{"Q", "T"}, true));
                list.add(new Routing("T", BASIC_CONNECTOR, new int[]{3, 4}, 0, 2, new LatLng(51.589712, -0.228942), new String[]{"B", "R"}, true));

                list.add(new Routing("E1", ELEVATOR, new int[]{14}, 0, 2, new LatLng(51.589726, -0.228853), null, true));
                list.add(new Routing("E2", ELEVATOR, new int[]{11}, 0, 2, new LatLng(51.590028, -0.228982), null, true));


                list.add(new Routing("S1", STAIR, new int[]{2}, 0, 2, new LatLng(51.589666, -0.229138), null, false));
                list.add(new Routing("S2", STAIR, new int[]{11}, 0, 2, new LatLng(51.590033, -0.229046), null, false));
                list.add(new Routing("S3", STAIR, new int[]{1}, 0, 2, new LatLng(51.589615, -0.228367), null, false));
                list.add(new Routing("S5", STAIR, new int[]{4}, 0, 2, new LatLng(51.589682, -0.228869), null, false));
                list.add(new Routing("S6", STAIR, new int[]{13}, 0, 2, new LatLng(51.589957, -0.228789), null, false));
                break;
        }

        return list;
    }

    private static ArrayList<Routing> getHatchcroftConnectors(int level) {
        ArrayList<Routing> list = new ArrayList<>();
        switch (level) {
            case 0:
                list.add(new Routing("A", BASIC_CONNECTOR, new int[]{1, 2}, 2, 0, new LatLng(51.589162, -0.229212), new String[]{"B", "G", "H"}, true));
                list.add(new Routing("B", BASIC_CONNECTOR, new int[]{2, 3}, 2, 0, new LatLng(51.588936, -0.229291), new String[]{"A"}, true));
                list.add(new Routing("C", BASIC_CONNECTOR, new int[]{1, 4}, 2, 0, new LatLng(51.589107, -0.228772), new String[]{"G", "D"}, true));
                list.add(new Routing("D", BASIC_CONNECTOR, new int[]{4, 5}, 2, 0, new LatLng(51.589090, -0.228778), new String[]{"C", "F"}, true));
                list.add(new Routing("D", BASIC_CONNECTOR, new int[]{4, 5}, 2, 0, new LatLng(51.589090, -0.228778), new String[]{"C", "F"}, true));
                list.add(new Routing("E", BASIC_CONNECTOR, new int[]{6, 7}, 2, 0, new LatLng(51.589096, -0.228650), new String[]{"F"}, true));
                list.add(new Routing("F", BASIC_CONNECTOR, new int[]{5, 6}, 2, 0, new LatLng(51.589077, -0.228657), new String[]{"D", "E"}, true));
                list.add(new Routing("G", BASIC_CONNECTOR, new int[]{1, 8}, 2, 0, new LatLng(51.589158, -0.229166), new String[]{"A", "C"}, true));
                list.add(new Routing("H", BASIC_CONNECTOR, new int[]{1, 9}, 2, 0, new LatLng(51.589177, -0.229386), new String[]{"A"}, true));


                list.add(new Routing("S1", STAIR, new int[]{8}, 2, 0, new LatLng(51.589233, -0.229137), null, false));
                list.add(new Routing("S2", STAIR, new int[]{7}, 2, 0, new LatLng(51.589073, -0.228539), null, false));
                list.add(new Routing("S3", STAIR, new int[]{1}, 2, 0, new LatLng(51.589218, -0.229718), null, false));
                list.add(new Routing("S4", STAIR, new int[]{3}, 2, 0, new LatLng(51.588936, -0.229256), null, false));

                list.add(new Routing("E1", ELEVATOR, new int[]{1}, 2, 0, new LatLng(51.589152, -0.229131), null, true));
                list.add(new Routing("E2", ELEVATOR, new int[]{9}, 2, 0, new LatLng(51.589157, -0.229394), null, true));

                list.add(new Routing("D1", DOOR, new int[]{2}, 2, 0, new LatLng(51.589222, -0.229192), new String[]{"A", "S"}, true));
                list.add(new Routing("D2", DOOR, new int[]{3}, 2, 0, new LatLng(51.588942, -0.229339), new String[]{"B", "V"}, true));
                break;

            case 1:
                list.add(new Routing("A", BASIC_CONNECTOR, new int[]{1, 6}, 1, 1, new LatLng(51.589208, -0.229651), new String[]{"C"}, true));
                list.add(new Routing("B", BASIC_CONNECTOR, new int[]{1, 2}, 1, 1, new LatLng(51.589154, -0.229159), new String[]{"F"}, true));
                list.add(new Routing("C", BASIC_CONNECTOR, new int[]{1, 5}, 1, 1, new LatLng(51.589186, -0.229466), new String[]{"A", "D", "G"}, true));
                list.add(new Routing("D", BASIC_CONNECTOR, new int[]{4, 5}, 1, 1, new LatLng(51.589003, -0.229521), new String[]{"C", "E"}, true));
                list.add(new Routing("E", BASIC_CONNECTOR, new int[]{3, 4}, 1, 1, new LatLng(51.588973, -0.229280), new String[]{"F", "D"}, true));
                list.add(new Routing("F", BASIC_CONNECTOR, new int[]{1, 3}, 1, 1, new LatLng(51.589162, -0.229229), new String[]{"G", "B", "E"}, true));
                list.add(new Routing("G", BASIC_CONNECTOR, new int[]{1, 7}, 1, 1, new LatLng(51.589177, -0.229387), new String[]{"C", "F"}, true));

                list.add(new Routing("S1", STAIR, new int[]{2}, 1, 1, new LatLng(51.589229, -0.229137), null, false));
                list.add(new Routing("S2", STAIR, new int[]{1}, 1, 1, new LatLng(51.589082, -0.228566), null, false));
                list.add(new Routing("S3", STAIR, new int[]{1}, 1, 1, new LatLng(51.589221, -0.229723), null, false));
                list.add(new Routing("S4", STAIR, new int[]{3}, 1, 1, new LatLng(51.589024, -0.229266), null, false));

                list.add(new Routing("E1", ELEVATOR, new int[]{1}, 1, 1, new LatLng(51.589152, -0.229131), null, true));
                list.add(new Routing("E2", ELEVATOR, new int[]{7}, 1, 1, new LatLng(51.589157, -0.229394), null, true));
                break;

            case 2:
                list.add(new Routing("A", BASIC_CONNECTOR, new int[]{1, 2}, 0, 2, new LatLng(51.589153, -0.229163), new String[]{"B"}, true));
                list.add(new Routing("B", BASIC_CONNECTOR, new int[]{1, 3}, 0, 2, new LatLng(51.589177, -0.229388), new String[]{"A"}, true));

                list.add(new Routing("S1", STAIR, new int[]{2}, 0, 2, new LatLng(51.589229, -0.229137), null, false));
                list.add(new Routing("S2", STAIR, new int[]{1}, 0, 2, new LatLng(51.589082, -0.228566), null, false));
                list.add(new Routing("S3", STAIR, new int[]{1}, 0, 2, new LatLng(51.589221, -0.229723), null, false));

                list.add(new Routing("E1", ELEVATOR, new int[]{1}, 0, 2, new LatLng(51.589152, -0.229131), null, true));
                list.add(new Routing("E2", ELEVATOR, new int[]{3}, 0, 2, new LatLng(51.589157, -0.229394), null, true));
                break;

        }

        return list;
    }

    private static ArrayList<Routing> getWilliamsConnectors(int level) {
        ArrayList<Routing> list = new ArrayList<>();
        switch (level) {
            case 0:
                list.add(new Routing("A", BASIC_CONNECTOR, new int[]{1, 2}, 1, 0, new LatLng(51.590663, -0.228260), new String[]{"B", "J", "M"}, true));
                list.add(new Routing("B", BASIC_CONNECTOR, new int[]{2, 3}, 1, 0, new LatLng(51.590649, -0.228101), new String[]{"A", "C"}, true));
                list.add(new Routing("C", BASIC_CONNECTOR, new int[]{3, 4}, 1, 0, new LatLng(51.590400, -0.228173), new String[]{"B", "L"}, true));
                list.add(new Routing("D", BASIC_CONNECTOR, new int[]{4, 10}, 1, 0, new LatLng(51.590444, -0.228568), new String[]{"L", "E", "K"}, true));
                list.add(new Routing("E", BASIC_CONNECTOR, new int[]{4, 5}, 1, 0, new LatLng(51.590463, -0.228728), new String[]{"D", "H", "G"}, true));
                //list.add(new Routing("F", BASIC_CONNECTOR, new int[] {4, 6}, 1, 0, new LatLng(51.590479, -0.228873), new String[] {"E", "H"}, true));
                list.add(new Routing("G", BASIC_CONNECTOR, new int[]{5, 8}, 1, 0, new LatLng(51.590505, -0.228718), new String[]{"E", "I"}, true));
                list.add(new Routing("H", BASIC_CONNECTOR, new int[]{4, 7}, 1, 0, new LatLng(51.590524, -0.229225), new String[]{"E", "I"}, true));
                list.add(new Routing("I", BASIC_CONNECTOR, new int[]{7, 8}, 1, 0, new LatLng(51.590562, -0.229212), new String[]{"G", "H"}, true));
                list.add(new Routing("J", BASIC_CONNECTOR, new int[]{1, 9}, 1, 0, new LatLng(51.590575, -0.228284), new String[]{"A"}, true));
                list.add(new Routing("K", BASIC_CONNECTOR, new int[]{10}, 1, 0, new LatLng(51.590516, -0.228559), new String[]{"D"}, true));
                list.add(new Routing("L", BASIC_CONNECTOR, new int[]{4, 11}, 1, 0, new LatLng(51.590420, -0.228379), new String[]{"C", "D"}, true));
                list.add(new Routing("M", BASIC_CONNECTOR, new int[]{1}, 1, 0, new LatLng(51.590731, -0.228240), new String[]{"A"}, true));

                list.add(new Routing("S1", STAIR, new int[]{10}, 1, 0, new LatLng(51.590492, -0.228563), null, false));
                list.add(new Routing("S2", STAIR, new int[]{11}, 1, 0, new LatLng(51.590396, -0.228385), null, false));
                list.add(new Routing("S3", STAIR, new int[]{9}, 1, 0, new LatLng(51.590572, -0.228343), null, false));

                list.add(new Routing("E1", ELEVATOR, new int[]{10}, 1, 0, new LatLng(51.590478, -0.228537), null, true));

                list.add(new Routing("D1", DOOR, new int[]{3}, 1, 0, new LatLng(51.590358, -0.228184), new String[]{"C", "B"}, true));
                list.add(new Routing("D2", DOOR, new int[]{10}, 1, 0, new LatLng(51.590406, -0.228582), new String[]{"D", "C"}, true));
                //list.add(new Routing("D3", DOOR, new int[] {6}, 1, 0, new LatLng(51.590431, -0.228888), new String[] {"F", "D"}, false));
                list.add(new Routing("D4", DOOR, new int[]{10}, 1, 0, new LatLng(51.590538, -0.228547), new String[]{"K", "I1"}, false));
                list.add(new Routing("D5", DOOR, new int[]{1}, 1, 0, new LatLng(51.590566, -0.228286), new String[]{"J", "S1"}, true));
                list.add(new Routing("D6", DOOR, new int[]{1}, 1, 0, new LatLng(51.590738, -0.228239), new String[]{"M", "T1"}, false));
                break;

            case 1:
                list.add(new Routing("A", BASIC_CONNECTOR, new int[]{1, 3}, 0, 1, new LatLng(51.590629, -0.228278), new String[]{"B", "C"}, true));
                list.add(new Routing("B", BASIC_CONNECTOR, new int[]{1, 2}, 0, 1, new LatLng(51.590586, -0.228294), new String[]{"A"}, true));
                list.add(new Routing("C", BASIC_CONNECTOR, new int[]{3, 4}, 0, 1, new LatLng(51.590608, -0.228093), new String[]{"A", "D"}, true));
                list.add(new Routing("D", BASIC_CONNECTOR, new int[]{4, 5}, 0, 1, new LatLng(51.590413, -0.228157), new String[]{"C", "F"}, true));
                list.add(new Routing("E", BASIC_CONNECTOR, new int[]{5, 6}, 0, 1, new LatLng(51.590460, -0.228555), new String[]{"F"}, true));
                list.add(new Routing("F", BASIC_CONNECTOR, new int[]{5, 7}, 0, 1, new LatLng(51.590441, -0.228396), new String[]{"E", "D"}, true));

                list.add(new Routing("S1", STAIR, new int[]{6}, 0, 1, new LatLng(51.590495, -0.228555), null, false));
                list.add(new Routing("S2", STAIR, new int[]{7}, 0, 1, new LatLng(51.590400, -0.228409), null, false));
                list.add(new Routing("S3", STAIR, new int[]{2}, 0, 1, new LatLng(51.590593, -0.228351), null, false));

                list.add(new Routing("E1", ELEVATOR, new int[]{6}, 0, 1, new LatLng(51.590478, -0.228537), null, true));
                break;
        }

        return list;
    }

    private static ArrayList<Routing> getMDXHouseConnectors(int level) {
        ArrayList<Routing> list = new ArrayList<>();
        switch (level) {
            case -1:
                list.add(new Routing("A", BASIC_CONNECTOR, new int[]{1}, 1, -1, new LatLng(51.590121, -0.230623), new String[]{"B"}, true));
                list.add(new Routing("B", BASIC_CONNECTOR, new int[]{1, 2}, 1, -1, new LatLng(51.590129, -0.230725), new String[]{"A", "C"}, true));
                list.add(new Routing("C", BASIC_CONNECTOR, new int[]{2, 3}, 1, -1, new LatLng(51.589826, -0.230786), new String[]{"B"}, true));

                list.add(new Routing("S1", STAIR, new int[]{1}, 1, -1, new LatLng(51.590124, -0.230585), null, false));

                list.add(new Routing("E1", ELEVATOR, new int[]{2}, 1, -1, new LatLng(51.590152, -0.230719), null, true));
                break;
            case 0:
                list.add(new Routing("A", BASIC_CONNECTOR, new int[]{1, 2}, 0, 0, new LatLng(51.590136, -0.230650), new String[]{"B", "C"}, true));
                list.add(new Routing("B", BASIC_CONNECTOR, new int[]{1, 3}, 0, 0, new LatLng(51.590144, -0.230771), new String[]{"A"}, true));
                list.add(new Routing("C", BASIC_CONNECTOR, new int[]{2, 4}, 0, 0, new LatLng(51.590112, -0.230654), new String[]{"A"}, true));

                list.add(new Routing("S1", STAIR, new int[]{4}, 0, 0, new LatLng(51.590108, -0.230594), null, false));
                list.add(new Routing("E1", ELEVATOR, new int[]{3}, 0, 0, new LatLng(51.590185, -0.230748), null, true));

                list.add(new Routing("D1", DOOR, new int[]{1}, 0, 0, new LatLng(51.590134, -0.230584), new String[]{"A", "D1"}, true));
                break;
        }

        return list;
    }

    private static ArrayList<Routing> getBuilding9Connetors() {
        ArrayList<Routing> list = new ArrayList<>();
        list.add(new Routing("A", BASIC_CONNECTOR, new int[]{1, 2}, 0, 0, new LatLng(51.588713, -0.229279), new String[]{"B", "C"}, true));
        list.add(new Routing("B", BASIC_CONNECTOR, new int[]{2, 3}, 0, 0, new LatLng(51.588765, -0.229443), new String[]{"A", "D"}, true));
        list.add(new Routing("C", BASIC_CONNECTOR, new int[]{1}, 0, 0, new LatLng(51.588608, -0.229364), new String[]{"A"}, true));
        list.add(new Routing("D", BASIC_CONNECTOR, new int[]{3}, 0, 0, new LatLng(51.588660, -0.229530), new String[]{"B"}, true));

        list.add(new Routing("D1", DOOR, new int[]{3}, 0, 0, new LatLng(51.588651, -0.229538), new String[]{"D", "R1"}, true));
        list.add(new Routing("D2", DOOR, new int[]{1}, 0, 0, new LatLng(51.588601, -0.229370), new String[]{"C", "Z1"}, true));

        return list;
    }

    private static ArrayList<Routing> getBuilding10Connetors(int level) {
        ArrayList<Routing> list = new ArrayList<>();
        switch (level) {
            case 0:
                list.add(new Routing("A", BASIC_CONNECTOR, new int[]{1}, 1, 0, new LatLng(51.589812, -0.229875), new String[]{"B"}, true));
                list.add(new Routing("B", BASIC_CONNECTOR, new int[]{1, 2}, 1, 0, new LatLng(51.589842, -0.229865), new String[]{"A"}, true));

                list.add(new Routing("S1", STAIR, new int[]{1}, 1, 0, new LatLng(51.589866, -0.229858), null, false));
                list.add(new Routing("D1", DOOR, new int[]{1}, 1, 0, new LatLng(51.589806, -0.229879), new String[]{"A", "P"}, false));
                break;
            case 1:
                list.add(new Routing("A", BASIC_CONNECTOR, new int[]{1}, 0, 1, new LatLng(51.589845, -0.229861), new String[]{""}, true));

                list.add(new Routing("S1", STAIR, new int[]{1}, 0, 1, new LatLng(51.589859, -0.229856), null, false));
        }

        return list;
    }

    private static ArrayList<Routing> getShepherdConnectors(int level) {
        ArrayList<Routing> list = new ArrayList<>();
        switch (level) {
            case -1:
                list.add(new Routing("A", BASIC_CONNECTOR, new int[]{1, 2}, 4, -1, new LatLng(51.590422, -0.229399), new String[]{"B"}, true));
                list.add(new Routing("B", BASIC_CONNECTOR, new int[]{2, 6}, 4, -1, new LatLng(51.590438, -0.229440), new String[]{"A", "C"}, true));
                list.add(new Routing("C", BASIC_CONNECTOR, new int[]{2, 3}, 4, -1, new LatLng(51.590494, -0.229566), new String[]{"B", "D"}, true));
                list.add(new Routing("D", BASIC_CONNECTOR, new int[]{3, 4}, 4, -1, new LatLng(51.590564, -0.229551), new String[]{"C", "E"}, true));
                list.add(new Routing("E", BASIC_CONNECTOR, new int[]{5, 4}, 4, -1, new LatLng(51.590579, -0.229718), new String[]{"D"}, true));


                list.add(new Routing("S1", STAIR, new int[]{5}, 4, -1, new LatLng(51.590548, -0.229726), null, false));
                list.add(new Routing("S2", STAIR, new int[]{3}, 4, -1, new LatLng(51.590787, -0.229484), null, false));

                list.add(new Routing("E1", ELEVATOR, new int[]{6}, 4, -1, new LatLng(51.590399, -0.229477), null, true));
                break;
            case 0:
                list.add(new Routing("A", BASIC_CONNECTOR, new int[]{2, 7}, 3, 0, new LatLng(51.590368, -0.229511), new String[]{"B"}, true));
                list.add(new Routing("B", BASIC_CONNECTOR, new int[]{1, 2}, 3, 0, new LatLng(51.590392, -0.229569), new String[]{"A", "C"}, true));
                list.add(new Routing("C", BASIC_CONNECTOR, new int[]{3, 2}, 3, 0, new LatLng(51.590469, -0.229754), new String[]{"B", "D"}, true));
                list.add(new Routing("D", BASIC_CONNECTOR, new int[]{3, 4}, 3, 0, new LatLng(51.590583, -0.229723), new String[]{"C", "E"}, true));
                list.add(new Routing("E", BASIC_CONNECTOR, new int[]{5, 4}, 3, 0, new LatLng(51.590563, -0.229552), new String[]{"D", "F"}, true));
                list.add(new Routing("F", BASIC_CONNECTOR, new int[]{5, 6}, 3, 0, new LatLng(51.590780, -0.229489), new String[]{"E"}, true));

                list.add(new Routing("S1", STAIR, new int[]{3}, 3, 0, new LatLng(51.590548, -0.229726), null, false));
                list.add(new Routing("S2", STAIR, new int[]{3}, 3, 0, new LatLng(51.590780, -0.229489), null, false));

                list.add(new Routing("E1", ELEVATOR, new int[]{7}, 3, 0, new LatLng(51.590392, -0.229483), null, true));

                list.add(new Routing("D1", DOOR, new int[]{1}, 3, 0, new LatLng(51.590429, -0.229534), new String[]{"B", "N1"}, true));
                break;

            case 1:
                list.add(new Routing("A", BASIC_CONNECTOR, new int[]{1, 2}, 2, 1, new LatLng(51.590780, -0.229489), new String[]{"B"}, true));
                list.add(new Routing("B", BASIC_CONNECTOR, new int[]{3, 2}, 2, 1, new LatLng(51.590563, -0.229552), new String[]{"A", "C"}, true));
                list.add(new Routing("C", BASIC_CONNECTOR, new int[]{3, 4}, 2, 1, new LatLng(51.590583, -0.229723), new String[]{"B", "D"}, true));
                list.add(new Routing("D", BASIC_CONNECTOR, new int[]{5, 4}, 2, 1, new LatLng(51.590461, -0.229754), new String[]{"C", "E"}, true));
                list.add(new Routing("E", BASIC_CONNECTOR, new int[]{5, 6}, 2, 1, new LatLng(51.590379, -0.229562), new String[]{"D", "F"}, true));
                list.add(new Routing("F", BASIC_CONNECTOR, new int[]{5, 7}, 2, 1, new LatLng(51.590360, -0.229518), new String[]{"E"}, true));

                list.add(new Routing("E1", ELEVATOR, new int[]{7}, 2, 1, new LatLng(51.590392, -0.229483), null, true));

                list.add(new Routing("S1", STAIR, new int[]{4}, 2, 1, new LatLng(51.590548, -0.229726), null, false));
                list.add(new Routing("S2", STAIR, new int[]{2}, 2, 1, new LatLng(51.590780, -0.229489), null, false));
                break;

            case 2:
                list.add(new Routing("A", BASIC_CONNECTOR, new int[]{1, 2}, 1, 2, new LatLng(51.590780, -0.229489), new String[]{"B"}, true));
                list.add(new Routing("B", BASIC_CONNECTOR, new int[]{3, 2}, 1, 2, new LatLng(51.590563, -0.229552), new String[]{"A", "C"}, true));
                list.add(new Routing("C", BASIC_CONNECTOR, new int[]{3, 4}, 1, 2, new LatLng(51.590583, -0.229723), new String[]{"B", "D"}, true));
                list.add(new Routing("D", BASIC_CONNECTOR, new int[]{5, 4}, 1, 2, new LatLng(51.590461, -0.229754), new String[]{"C", "E"}, true));
                list.add(new Routing("E", BASIC_CONNECTOR, new int[]{5, 6}, 1, 2, new LatLng(51.590360, -0.229518), new String[]{"D"}, true));

                list.add(new Routing("E1", ELEVATOR, new int[]{6}, 1, 2, new LatLng(51.590392, -0.229483), null, true));

                list.add(new Routing("S1", STAIR, new int[]{4}, 1, 2, new LatLng(51.590548, -0.229726), null, false));
                list.add(new Routing("S2", STAIR, new int[]{2}, 1, 2, new LatLng(51.590780, -0.229489), null, false));
                break;

            case 3:
                list.add(new Routing("A", BASIC_CONNECTOR, new int[]{1, 2}, 0, 3, new LatLng(51.590461, -0.229754), new String[]{"B"}, true));
                list.add(new Routing("B", BASIC_CONNECTOR, new int[]{2, 3}, 0, 3, new LatLng(51.590360, -0.229518), new String[]{"A"}, true));

                list.add(new Routing("E1", ELEVATOR, new int[]{3}, 0, 3, new LatLng(51.590392, -0.229483), null, true));
                list.add(new Routing("S1", STAIR, new int[]{1}, 0, 3, new LatLng(51.590548, -0.229726), null, false));
                break;
        }

        return list;
    }

    private static ArrayList<Routing> getCircleCafeConnectors() {
        ArrayList<Routing> list = new ArrayList<>();
        list.add(new Routing("A", BASIC_CONNECTOR, new int[]{1, 2}, 3, 0, new LatLng(51.590482, -0.229790), new String[]{""}, true));
        list.add(new Routing("D1", DOOR, new int[]{1}, 3, 0, new LatLng(51.590522, -0.229743), new String[]{"A", "O1"}, true));
        list.add(new Routing("D2", DOOR, new int[]{1}, 3, 0, new LatLng(51.590420, -0.229863), new String[]{"A", "A1"}, true));
        return list;
    }

    private static ArrayList<Routing> getGroveBlockAConnectors(int level) {
        ArrayList<Routing> list = new ArrayList<>();
        switch (level) {
            case -1:
                list.add(new Routing("A", BASIC_CONNECTOR, new int[]{1, 6}, 5, -1, new LatLng(51.588786, -0.230623), new String[]{"B"}, true));
                list.add(new Routing("B", BASIC_CONNECTOR, new int[]{1, 2}, 5, -1, new LatLng(51.588710, -0.230637), new String[]{"A", "C"}, true));
                list.add(new Routing("C", BASIC_CONNECTOR, new int[]{3, 2}, 5, -1, new LatLng(51.588701, -0.230593), new String[]{"B", "D"}, true));
                list.add(new Routing("D", BASIC_CONNECTOR, new int[]{3, 4}, 5, -1, new LatLng(51.588602, -0.230637), new String[]{"C", "E"}, true));
                list.add(new Routing("E", BASIC_CONNECTOR, new int[]{5, 4}, 5, -1, new LatLng(51.588597, -0.230606), new String[]{"D", "G"}, true));
                list.add(new Routing("F", BASIC_CONNECTOR, new int[]{7, 8}, 5, -1, new LatLng(51.588519, -0.230682), new String[]{"G"}, true));
                list.add(new Routing("G", BASIC_CONNECTOR, new int[]{7, 5}, 5, -1, new LatLng(51.588511, -0.230641), new String[]{"E", "F"}, true));

                list.add(new Routing("E1", ELEVATOR, new int[]{1}, 5, -1, new LatLng(51.588763, -0.230630), null, true));
                list.add(new Routing("E2", ELEVATOR, new int[]{8}, 5, -1, new LatLng(51.588763, -0.230630), null, true));

                list.add(new Routing("S4", STAIR, new int[]{6}, 5, -1, new LatLng(51.588787, -0.230592), null, false));
                list.add(new Routing("S5", STAIR, new int[]{5}, 5, -1, new LatLng(51.588434, -0.230673), null, false));

                break;
            case 0:
                list.add(new Routing("A", BASIC_CONNECTOR, new int[]{1, 3}, 4, 0, new LatLng(51.588831, -0.230624), new String[]{"B"}, true));
                list.add(new Routing("B", BASIC_CONNECTOR, new int[]{1, 2}, 4, 0, new LatLng(51.588798, -0.230633), new String[]{"A", "C"}, true));
                list.add(new Routing("C", BASIC_CONNECTOR, new int[]{1, 4, 5}, 4, 0, new LatLng(51.588698, -0.230634), new String[]{"D", "H", "B"}, true));
                list.add(new Routing("D", BASIC_CONNECTOR, new int[]{11, 5}, 4, 0, new LatLng(51.588719, -0.230758), new String[]{"C", "E"}, true));
                list.add(new Routing("E", BASIC_CONNECTOR, new int[]{6, 5}, 4, 0, new LatLng(51.588734, -0.230900), new String[]{"D", "F"}, true));
                list.add(new Routing("F", BASIC_CONNECTOR, new int[]{6, 7}, 4, 0, new LatLng(51.588596, -0.231010), new String[]{"E", "G"}, true));
                list.add(new Routing("G", BASIC_CONNECTOR, new int[]{1, 7, 9, 10}, 4, 0, new LatLng(51.588510, -0.230673), new String[]{"F", "H"}, true));
                list.add(new Routing("H", BASIC_CONNECTOR, new int[]{1, 8}, 4, 0, new LatLng(51.588612, -0.230631), new String[]{"G", "C"}, true));

                list.add(new Routing("E1", ELEVATOR, new int[]{1}, 4, 0, new LatLng(51.588755, -0.230633), null, true));
                list.add(new Routing("E2", ELEVATOR, new int[]{10}, 4, 0, new LatLng(51.588494, -0.230701), null, true));

                list.add(new Routing("S1", STAIR, new int[]{4}, 4, 0, new LatLng(51.588613, -0.230227), null, false));
                list.add(new Routing("S2", STAIR, new int[]{9}, 4, 0, new LatLng(51.588460, -0.230299), null, false));
                list.add(new Routing("S3", STAIR, new int[]{3}, 4, 0, new LatLng(51.588841, -0.230702), null, false));
                list.add(new Routing("S4", STAIR, new int[]{2}, 4, 0, new LatLng(51.588795, -0.230587), null, false));
                list.add(new Routing("S5", STAIR, new int[]{10}, 4, 0, new LatLng(51.588422, -0.230699), null, false));


                list.add(new Routing("D1", DOOR, new int[]{1}, 4, 0, new LatLng(51.588854, -0.230615), new String[]{"A", "L1"}, true));
                break;
            case 1:
                list.add(new Routing("A", BASIC_CONNECTOR, new int[]{1, 14}, 3, 1, new LatLng(51.588803, -0.230641), new String[]{"B"}, true));
                list.add(new Routing("B", BASIC_CONNECTOR, new int[]{1, 2}, 3, 1, new LatLng(51.588704, -0.230653), new String[]{"A", "C", "N"}, true));
                list.add(new Routing("C", BASIC_CONNECTOR, new int[]{3, 2}, 3, 1, new LatLng(51.588718, -0.230760), new String[]{"B", "D"}, true));
                list.add(new Routing("D", BASIC_CONNECTOR, new int[]{4, 2}, 3, 1, new LatLng(51.588736, -0.230901), new String[]{"C", "E"}, true));
                list.add(new Routing("E", BASIC_CONNECTOR, new int[]{4, 5, 6}, 3, 1, new LatLng(51.588592, -0.231008), new String[]{"D", "F", "H"}, true));
                list.add(new Routing("F", BASIC_CONNECTOR, new int[]{5, 7}, 3, 1, new LatLng(51.588549, -0.231032), new String[]{"E", "G"}, true));
                list.add(new Routing("G", BASIC_CONNECTOR, new int[]{8, 7}, 3, 1, new LatLng(51.588485, -0.230788), new String[]{"F", "H"}, true));
                list.add(new Routing("H", BASIC_CONNECTOR, new int[]{8, 6}, 3, 1, new LatLng(51.588528, -0.230767), new String[]{"G", "I", "E"}, true));
                list.add(new Routing("I", BASIC_CONNECTOR, new int[]{9, 6, 10}, 3, 1, new LatLng(51.588503, -0.230652), new String[]{"O", "J", "H"}, true));
                list.add(new Routing("J", BASIC_CONNECTOR, new int[]{11, 10}, 3, 1, new LatLng(51.588490, -0.230533), new String[]{"K", "I", "P"}, true));
                list.add(new Routing("K", BASIC_CONNECTOR, new int[]{12, 10}, 3, 1, new LatLng(51.588470, -0.230399), new String[]{"L", "J"}, true));
                list.add(new Routing("L", BASIC_CONNECTOR, new int[]{12, 13}, 3, 1, new LatLng(51.588634, -0.230327), new String[]{"M", "K"}, true));
                list.add(new Routing("M", BASIC_CONNECTOR, new int[]{11, 13}, 3, 1, new LatLng(51.588662, -0.230449), new String[]{"L", "N", "P"}, true));
                list.add(new Routing("N", BASIC_CONNECTOR, new int[]{9, 13}, 3, 1, new LatLng(51.588695, -0.230611), new String[]{"M", "O", "B"}, true));
                list.add(new Routing("O", BASIC_CONNECTOR, new int[]{9, 15}, 3, 1, new LatLng(51.588549, -0.230641), new String[]{"N", "I", "P"}, true));
                list.add(new Routing("P", BASIC_CONNECTOR, new int[]{11, 15}, 3, 1, new LatLng(51.588533, -0.230514), new String[]{"M", "J", "O"}, true));

                //S3 ends on this level, S6 starts on this level
                list.add(new Routing("S1", STAIR, new int[]{13}, 3, 1, new LatLng(51.588612, -0.230221), null, false));
                list.add(new Routing("S2", STAIR, new int[]{10}, 3, 1, new LatLng(51.588460, -0.230308), null, false));
                list.add(new Routing("S3", STAIR, new int[]{14}, 3, 1, new LatLng(51.588808, -0.230714), null, false));
                list.add(new Routing("S4", STAIR, new int[]{14}, 3, 1, new LatLng(51.588793, -0.230590), null, false));
                list.add(new Routing("S5", STAIR, new int[]{9}, 3, 1, new LatLng(51.588413, -0.230699), null, false));
                list.add(new Routing("S6", STAIR, new int[]{15}, 3, 1, new LatLng(51.588547, -0.230613), null, false));

                list.add(new Routing("E1", ELEVATOR, new int[]{1}, 3, 1, new LatLng(51.588732, -0.230633), null, true));
                list.add(new Routing("E2", ELEVATOR, new int[]{9}, 3, 1, new LatLng(51.588495, -0.230687), null, true));
                break;
            case 2:
                list.add(new Routing("A", BASIC_CONNECTOR, new int[]{1, 13}, 2, 2, new LatLng(51.588776, -0.230544), new String[]{"B"}, true));
                list.add(new Routing("B", BASIC_CONNECTOR, new int[]{1, 2, 11}, 2, 2, new LatLng(51.588686, -0.230564), new String[]{"A", "C", "O", "N"}, true));
                list.add(new Routing("C", BASIC_CONNECTOR, new int[]{7, 2}, 2, 2, new LatLng(51.588694, -0.230610), new String[]{"B", "D", "P"}, true));
                list.add(new Routing("D", BASIC_CONNECTOR, new int[]{5, 2}, 2, 2, new LatLng(51.588721, -0.230767), new String[]{"C", "E", "I"}, true));
                list.add(new Routing("E", BASIC_CONNECTOR, new int[]{4, 2}, 2, 2, new LatLng(51.588724, -0.230795), new String[]{"D", "F", "H"}, true));
                list.add(new Routing("F", BASIC_CONNECTOR, new int[]{3, 2}, 2, 2, new LatLng(51.588737, -0.230899), new String[]{"E", "G"}, true));
                list.add(new Routing("G", BASIC_CONNECTOR, new int[]{3, 6}, 2, 2, new LatLng(51.588593, -0.231005), new String[]{"F", "H"}, true));
                list.add(new Routing("H", BASIC_CONNECTOR, new int[]{4, 6}, 2, 2, new LatLng(51.588567, -0.230914), new String[]{"I", "G", "E"}, true));
                list.add(new Routing("I", BASIC_CONNECTOR, new int[]{5, 6}, 2, 2, new LatLng(51.588537, -0.230806), new String[]{"J", "H", "D"}, true));
                list.add(new Routing("J", BASIC_CONNECTOR, new int[]{10, 6, 7}, 2, 2, new LatLng(51.588506, -0.230659), new String[]{"K", "I", "P"}, true));
                list.add(new Routing("K", BASIC_CONNECTOR, new int[]{10, 8}, 2, 2, new LatLng(51.588490, -0.230552), new String[]{"L", "J", "N"}, true));
                list.add(new Routing("L", BASIC_CONNECTOR, new int[]{10, 9}, 2, 2, new LatLng(51.588468, -0.230406), new String[]{"K", "M"}, true));
                list.add(new Routing("M", BASIC_CONNECTOR, new int[]{11, 9}, 2, 2, new LatLng(51.588634, -0.230322), new String[]{"L", "N"}, true));
                list.add(new Routing("N", BASIC_CONNECTOR, new int[]{11, 8}, 2, 2, new LatLng(51.588665, -0.230450), new String[]{"M", "K", "B"}, true));
                list.add(new Routing("O", BASIC_CONNECTOR, new int[]{1, 12}, 2, 2, new LatLng(51.588628, -0.230576), new String[]{"B", "P"}, true));
                list.add(new Routing("P", BASIC_CONNECTOR, new int[]{7, 12}, 2, 2, new LatLng(51.588638, -0.230630), new String[]{"C", "O", "J"}, true));

                //S6 and S2 ends on this level
                list.add(new Routing("S1", STAIR, new int[]{11}, 2, 2, new LatLng(51.588609, -0.230234), null, false));
                list.add(new Routing("S2", STAIR, new int[]{10}, 2, 2, new LatLng(51.588462, -0.230295), null, false));
                list.add(new Routing("S4", STAIR, new int[]{13}, 2, 2, new LatLng(51.588782, -0.230593), null, false));
                list.add(new Routing("S5", STAIR, new int[]{7}, 2, 2, new LatLng(51.588414, -0.230694), null, false));
                list.add(new Routing("S6", STAIR, new int[]{1}, 2, 2, new LatLng(51.588594, -0.230594), null, false));

                list.add(new Routing("E1", ELEVATOR, new int[]{1}, 2, 2, new LatLng(51.588731, -0.230559), null, true));
                list.add(new Routing("E2", ELEVATOR, new int[]{7}, 2, 2, new LatLng(51.588495, -0.230690), null, true));

                break;
            case 3:
                list.add(new Routing("A", BASIC_CONNECTOR, new int[]{1, 2}, 1, 3, new LatLng(51.588698, -0.230637), new String[]{"B"}, true));
                list.add(new Routing("B", BASIC_CONNECTOR, new int[]{1, 3}, 1, 3, new LatLng(51.588754, -0.230631), new String[]{"A"}, true));


                //S1 ends here.
                //Left out S5 and E2 on this level and the next for reasons best known to me. Grove building is fvk#ng weird
                list.add(new Routing("S1", STAIR, new int[]{2}, 1, 3, new LatLng(51.588611, -0.230222), null, false));
                list.add(new Routing("S4", STAIR, new int[]{3}, 1, 3, new LatLng(51.588758, -0.230597), null, false));

                list.add(new Routing("E1", ELEVATOR, new int[]{1}, 1, 3, new LatLng(51.588735, -0.230617), null, true));
                break;
            case 4:
                list.add(new Routing("A", BASIC_CONNECTOR, new int[]{1, 2}, 0, 4, new LatLng(51.588750, -0.230639), new String[]{""}, true));

                list.add(new Routing("S4", STAIR, new int[]{2}, 0, 4, new LatLng(51.588754, -0.230597), null, false));
                list.add(new Routing("E1", ELEVATOR, new int[]{1}, 0, 4, new LatLng(51.588733, -0.230631), null, true));

                break;

        }
        return list;
    }

    private static ArrayList<Routing> getGroveBlockBConnectors(int level) {
        ArrayList<Routing> list = new ArrayList<>();
        switch (level) {
            case 0:
                list.add(new Routing("A", BASIC_CONNECTOR, new int[]{1, 3}, 4, 0, new LatLng(51.588955, -0.230486), new String[]{"B"}, true));
                list.add(new Routing("B", BASIC_CONNECTOR, new int[]{1, 2}, 4, 0, new LatLng(51.588908, -0.230244), new String[]{"A"}, true));

                list.add(new Routing("S1", STAIR, new int[]{3}, 4, 0, new LatLng(51.589012, -0.230456), null, false));
                list.add(new Routing("S2", STAIR, new int[]{1}, 4, 0, new LatLng(51.588875, -0.230086), null, false));

                list.add(new Routing("E1", ELEVATOR, new int[]{1}, 4, 0, new LatLng(51.588952, -0.230430), null, true));

                list.add(new Routing("D1", DOOR, new int[]{1}, 4, 0, new LatLng(51.588957, -0.230488), new String[]{"A", "A10"}, true));
                break;
            case 1:
                list.add(new Routing("A", BASIC_CONNECTOR, new int[]{1, 2}, 3, 1, new LatLng(51.588950, -0.230485), new String[]{"B"}, true));
                list.add(new Routing("B", BASIC_CONNECTOR, new int[]{3, 2}, 3, 1, new LatLng(51.588882, -0.230141), new String[]{"A", "C"}, true));
                list.add(new Routing("C", BASIC_CONNECTOR, new int[]{3, 4}, 3, 1, new LatLng(51.588955, -0.230107), new String[]{"B"}, true));

                list.add(new Routing("S1", STAIR, new int[]{1}, 3, 1, new LatLng(51.589022, -0.230451), null, false));
                list.add(new Routing("S2", STAIR, new int[]{2}, 3, 1, new LatLng(51.588874, -0.230086), null, false));
                list.add(new Routing("E1", ELEVATOR, new int[]{2}, 3, 1, new LatLng(51.588941, -0.230438), null, true));
                break;
            case 2:
                list.add(new Routing("A", BASIC_CONNECTOR, new int[]{1, 2}, 2, 2, new LatLng(51.588949, -0.230470), new String[]{"B"}, true));
                list.add(new Routing("B", BASIC_CONNECTOR, new int[]{3, 2}, 2, 2, new LatLng(51.588883, -0.230145), new String[]{"A"}, true));

                list.add(new Routing("S1", STAIR, new int[]{1}, 2, 2, new LatLng(51.589019, -0.230423), null, false));
                list.add(new Routing("S2", STAIR, new int[]{2}, 2, 2, new LatLng(51.588876, -0.230086), null, false));
                list.add(new Routing("E1", ELEVATOR, new int[]{2}, 2, 2, new LatLng(51.588944, -0.230440), null, true));
                break;
        }
        return list;
    }

    private static ArrayList<Routing> getGroveBlockCConnectors(int level) {
        ArrayList<Routing> list = new ArrayList<>();
        switch (level) {
            case 0:
                list.add(new Routing("A", BASIC_CONNECTOR, new int[]{1, 2}, 4, 0, new LatLng(51.589186, -0.230249), new String[]{"E"}, true));
                list.add(new Routing("B", BASIC_CONNECTOR, new int[]{3, 4}, 4, 0, new LatLng(51.589270, -0.230040), new String[]{"C"}, true));
                list.add(new Routing("C", BASIC_CONNECTOR, new int[]{3, 2}, 4, 0, new LatLng(51.589280, -0.230142), new String[]{"B", "D", "E"}, true));
                list.add(new Routing("D", BASIC_CONNECTOR, new int[]{3}, 4, 0, new LatLng(51.589301, -0.230184), new String[]{"C"}, true));
                list.add(new Routing("E", BASIC_CONNECTOR, new int[]{5, 2}, 4, 0, new LatLng(51.589231, -0.230196), new String[]{"A", "C"}, true));

                list.add(new Routing("S1", STAIR, new int[]{3}, 4, 0, new LatLng(51.589273, -0.230221), null, false));

                list.add(new Routing("E1", ELEVATOR, new int[]{5}, 4, 0, new LatLng(51.589241, -0.230215), null, true));

                list.add(new Routing("D1", DOOR, new int[]{3}, 4, 0, new LatLng(51.589312, -0.230166), new String[]{"D", "A9"}, true));
                list.add(new Routing("D2", DOOR, new int[]{1}, 4, 0, new LatLng(51.589167, -0.230253), new String[]{"A", "A12"}, true));
                break;
            case 1:
                list.add(new Routing("A", BASIC_CONNECTOR, new int[]{1, 2}, 3, 1, new LatLng(51.589215, -0.230285), new String[]{"B"}, true));
                list.add(new Routing("B", BASIC_CONNECTOR, new int[]{3, 2}, 3, 1, new LatLng(51.589207, -0.230184), new String[]{"A"}, true));

                list.add(new Routing("S1", STAIR, new int[]{1}, 3, 1, new LatLng(51.589261, -0.230234), null, false));
                list.add(new Routing("E1", ELEVATOR, new int[]{2}, 3, 1, new LatLng(51.589214, -0.230244), null, true));
        }

        return list;
    }

    private static ArrayList<Routing> getVineConnectors(int level) {
        ArrayList<Routing> list = new ArrayList<>();
        switch (level) {
            case 0:
                list.add(new Routing("A", BASIC_CONNECTOR, new int[]{1, 2}, 1, 0, new LatLng(51.590611, -0.230630), new String[]{"B"}, true));
                list.add(new Routing("B", BASIC_CONNECTOR, new int[]{3, 2}, 1, 0, new LatLng(51.590640, -0.230913), new String[]{"A"}, true));
                list.add(new Routing("S1", STAIR, new int[]{2}, 1, 0, new LatLng(51.590609, -0.230608), null, false));
                list.add(new Routing("S2", STAIR, new int[]{2}, 1, 0, new LatLng(51.590643, -0.230935), null, false));

                list.add(new Routing("D1", DOOR, new int[]{1}, 1, 0, new LatLng(51.590599, -0.230635), new String[]{"A", "A8"}, true));
                list.add(new Routing("D2", DOOR, new int[]{3}, 1, 0, new LatLng(51.590630, -0.230917), new String[]{"B", "A7"}, true));
                break;
            case 1:
                list.add(new Routing("A", BASIC_CONNECTOR, new int[]{3, 2}, 0, 1, new LatLng(51.590637, -0.230908), new String[]{"B"}, true));
                list.add(new Routing("B", BASIC_CONNECTOR, new int[]{1, 2}, 0, 1, new LatLng(51.590610, -0.230630), new String[]{"A"}, true));
                list.add(new Routing("S1", STAIR, new int[]{1}, 0, 1, new LatLng(51.590645, -0.230625), null, false));
                list.add(new Routing("S2", STAIR, new int[]{3}, 0, 1, new LatLng(51.590673, -0.230901), null, false));
                break;
        }

        return list;
    }

    private static ArrayList<Routing> getPortacabinAConnectors(int level) {
        ArrayList<Routing> list = new ArrayList<>();
        switch (level) {
            case 0:
                //list.add(new Routing("A", BASIC_CONNECTOR, new int[] {1}, 1, 0, new LatLng(51.589771, -0.230222), new String[] {"B"}, true));
                list.add(new Routing("A", BASIC_CONNECTOR, new int[]{1, 2}, 1, 0, new LatLng(51.589760, -0.230224), new String[]{""}, true));
                list.add(new Routing("D1", DOOR, new int[]{1}, 1, 0, new LatLng(51.589771, -0.230243), new String[]{"A", "A13"}, true));
                list.add(new Routing("S1", STAIR, new int[]{1}, 1, 0, new LatLng(51.589754, -0.230190), null, false));
                break;
            case 1:
                list.add(new Routing("A", BASIC_CONNECTOR, new int[]{1, 2}, 0, 1, new LatLng(51.589769, -0.230224), new String[]{""}, true));
                list.add(new Routing("S1", STAIR, new int[]{1}, 0, 1, new LatLng(51.589766, -0.230160), null, false));
                break;
        }

        return list;
    }

    private static ArrayList<Routing> getPortacabinAEXTConnectors() {
        ArrayList<Routing> list = new ArrayList<>();
        list.add(new Routing("A", BASIC_CONNECTOR, new int[]{1}, 1, 0, new LatLng(51.589871, -0.230213), new String[]{""}, true));
        list.add(new Routing("D1", DOOR, new int[]{1}, 1, 0, new LatLng(51.589871, -0.230228), new String[]{"A", "A14"}, true));

        return list;
    }

    private static ArrayList<Routing> getPortacabinBConnectors() {
        ArrayList<Routing> list = new ArrayList<>();
        list.add(new Routing("A", BASIC_CONNECTOR, new int[]{1}, 0, 0, new LatLng(51.590717, -0.229117), new String[]{""}, true));
        list.add(new Routing("D1", DOOR, new int[]{1}, 0, 0, new LatLng(51.590721, -0.229157), new String[]{"A", "A3"}, true));
        return list;
    }


    private static ArrayList<Routing> getPortacabin67Connectors() {
        ArrayList<Routing> list = new ArrayList<>();
        list.add(new Routing("A", BASIC_CONNECTOR, new int[]{1, 2}, 1, 0, new LatLng(51.589716, -0.229821), new String[]{"B"}, false));
        list.add(new Routing("B", BASIC_CONNECTOR, new int[]{1, 3}, 1, 0, new LatLng(51.589732, -0.229955), new String[]{"A"}, false));
        list.add(new Routing("D1", DOOR, new int[]{2}, 1, 0, new LatLng(51.589724, -0.229817), new String[]{"A", "A15"}, false));
        list.add(new Routing("D2", DOOR, new int[]{3}, 1, 0, new LatLng(51.589737, -0.229951), new String[]{"B", "A16"}, false));

        return list;
    }

    private static ArrayList<Routing> getBarnConnectors() {
        ArrayList<Routing> list = new ArrayList<>();
        list.add(new Routing("A", BASIC_CONNECTOR, new int[]{1, 2}, 0, 0, new LatLng(51.590938, -0.228504), new String[]{"B"}, true));
        list.add(new Routing("B", BASIC_CONNECTOR, new int[]{2, 3}, 0, 0, new LatLng(51.591057, -0.228516), new String[]{"A"}, true));
        list.add(new Routing("D1", DOOR, new int[]{1}, 0, 0, new LatLng(51.590938, -0.228426), new String[]{"A", "U1"}, true));
        return list;
    }


    public static ArrayList<Routing> getOutsideConnectors() {
        ArrayList<Routing> list = new ArrayList<>();

        list.add(new Routing("A", BASIC_CONNECTOR, new LatLng(51.590097, -0.228139), new String[]{"B"}, true));
        list.add(new Routing("B", BASIC_CONNECTOR, new LatLng(51.590315, -0.228194), new String[]{"A", "C", "V1"}, true));
        list.add(new Routing("C", BASIC_CONNECTOR, new LatLng(51.590350, -0.228599), new String[]{"B", "E", "I1"}, true));
        list.add(new Routing("E", BASIC_CONNECTOR, new LatLng(51.590379, -0.228964), new String[]{"C", "F", "K"}, true));
        list.add(new Routing("F", BASIC_CONNECTOR, new LatLng(51.590401, -0.229140), new String[]{"E", "G", "N1"}, true));
        list.add(new Routing("G", BASIC_CONNECTOR, new LatLng(51.590435, -0.229140), new String[]{"F", "H"}, true));
        list.add(new Routing("H", BASIC_CONNECTOR, new LatLng(51.590471, -0.229376), new String[]{"G", "I"}, true));
        list.add(new Routing("I", BASIC_CONNECTOR, new LatLng(51.590657, -0.229313), new String[]{"H", "A3", "P1"}, true));
        list.add(new Routing("J", BASIC_CONNECTOR, new LatLng(51.590830, -0.229251), new String[]{"P1", "H1"}, true));
        list.add(new Routing("K", BASIC_CONNECTOR, new LatLng(51.590168, -0.229023), new String[]{"E", "L", "R"}, true));
        list.add(new Routing("L", BASIC_CONNECTOR, new LatLng(51.590187, -0.229187), new String[]{"F", "K", "M"}, true));
        list.add(new Routing("M", BASIC_CONNECTOR, new LatLng(51.590159, -0.229616), new String[]{"L", "N", "Z"}, true));
        list.add(new Routing("N", BASIC_CONNECTOR, new LatLng(51.590096, -0.229637), new String[]{"M", "Q"}, true));
        list.add(new Routing("O", BASIC_CONNECTOR, new LatLng(51.589733, -0.229727), new String[]{"A15", "T", "Q"}, true));
        list.add(new Routing("P", BASIC_CONNECTOR, new LatLng(51.589749, -0.229884), new String[]{"A15", "A16"}, true));
        list.add(new Routing("Q", BASIC_CONNECTOR, new LatLng(51.589979, -0.229679), new String[]{"O", "N"}, true));
        list.add(new Routing("R", BASIC_CONNECTOR, new LatLng(51.589575, -0.229187), new String[]{"S", "K"}, true));
        list.add(new Routing("S", BASIC_CONNECTOR, new LatLng(51.589382, -0.229221), new String[]{"R", "U", "T"}, true));
        list.add(new Routing("T", BASIC_CONNECTOR, new LatLng(51.589445, -0.229749), new String[]{"O", "S", "A11", "W"}, true));
        list.add(new Routing("U", BASIC_CONNECTOR, new LatLng(51.588938, -0.229297), new String[]{"S", "V"}, true));
        list.add(new Routing("V", BASIC_CONNECTOR, new LatLng(51.589014, -0.229861), new String[]{"A11", "U", "A12", "K1"}, true));
        list.add(new Routing("W", BASIC_CONNECTOR, new LatLng(51.589479, -0.230074), new String[]{"T", "E1", "A9"}, true));
        list.add(new Routing("X", BASIC_CONNECTOR, new LatLng(51.589761, -0.230020), new String[]{"A16", "Y"}, true));
        list.add(new Routing("Y", BASIC_CONNECTOR, new LatLng(51.589967, -0.229947), new String[]{"X", "Z", "B1"}, true));
        list.add(new Routing("Z", BASIC_CONNECTOR, new LatLng(51.590184, -0.229870), new String[]{"M", "Y", "D1", "A1"}, false));
        list.add(new Routing("A1", BASIC_CONNECTOR, new LatLng(51.590377, -0.229911), new String[]{"Z", "F1", "O1"}, true));
        list.add(new Routing("B1", BASIC_CONNECTOR, new LatLng(51.589979, -0.230105), new String[]{"Y", "C1"}, false));
        list.add(new Routing("C1", BASIC_CONNECTOR, new LatLng(51.589993, -0.230266), new String[]{"D1", "A14", "B1"}, true));
        list.add(new Routing("D1", BASIC_CONNECTOR, new LatLng(51.590105, -0.230258), new String[]{"Z", "C1", "F1"}, true));
        list.add(new Routing("E1", BASIC_CONNECTOR, new LatLng(51.589616, -0.230412), new String[]{"W", "A13"}, true));
        list.add(new Routing("F1", BASIC_CONNECTOR, new LatLng(51.590471, -0.230036), new String[]{"A1", "D1", "G1"}, true));
        list.add(new Routing("G1", BASIC_CONNECTOR, new LatLng(51.590642, -0.229904), new String[]{"F1", "H1", "O1", "A5"}, true));
        list.add(new Routing("H1", BASIC_CONNECTOR, new LatLng(51.590891, -0.229726), new String[]{"J", "G1"}, true));
        list.add(new Routing("I1", BASIC_CONNECTOR, new LatLng(51.590564, -0.228539), new String[]{"C", "A3", "S1", "U1"}, false));
        list.add(new Routing("J1", BASIC_CONNECTOR, new LatLng(51.589134, -0.230500), new String[]{"A12", "A9", "A10"}, true));
        list.add(new Routing("K1", BASIC_CONNECTOR, new LatLng(51.588787, -0.229967), new String[]{"V", "L1", "Q1"}, true));
        list.add(new Routing("L1", BASIC_CONNECTOR, new LatLng(51.588917, -0.230597), new String[]{"A10", "K1"}, true));
        list.add(new Routing("N1", BASIC_CONNECTOR, new LatLng(51.590443, -0.229521), new String[]{"F", "O1"}, true));
        list.add(new Routing("O1", BASIC_CONNECTOR, new LatLng(51.590540, -0.229722), new String[]{"G1", "N1", "A1"}, true));
        list.add(new Routing("P1", BASIC_CONNECTOR, new LatLng(51.590736, -0.229285), new String[]{"I", "J"}, false));
        list.add(new Routing("Q1", BASIC_CONNECTOR, new LatLng(51.588700, -0.229998), new String[]{"K1", "X1", "Y1"}, true));
        list.add(new Routing("R1", BASIC_CONNECTOR, new LatLng(51.588579, -0.229606), new String[]{"Y1", "Z1"}, true));
        list.add(new Routing("T1", BASIC_CONNECTOR, new LatLng(51.590758, -0.228237), new String[]{"U1", "W1"}, false));
        list.add(new Routing("S1", BASIC_CONNECTOR, new LatLng(51.590540, -0.228306), new String[]{"I1", "W1", "A2"}, true));
        list.add(new Routing("U1", BASIC_CONNECTOR, new LatLng(51.590801, -0.228478), new String[]{"I1", "T1", "A2"}, true));
        list.add(new Routing("V1", BASIC_CONNECTOR, new LatLng(51.590650, -0.228098), new String[]{"B", "W1"}, true));
        list.add(new Routing("W1", BASIC_CONNECTOR, new LatLng(51.590664, -0.228257), new String[]{"S1", "T1", "V1"}, true));
        list.add(new Routing("X1", BASIC_CONNECTOR, new LatLng(51.588455, -0.230132), new String[]{"Q1"}, true));
        list.add(new Routing("Z1", BASIC_CONNECTOR, new LatLng(51.588526, -0.229428), new String[]{"R1"}, true));
        list.add(new Routing("Y1", BASIC_CONNECTOR, new LatLng(51.588643, -0.229776), new String[]{"Q1", "R1"}, true));
        list.add(new Routing("A2", BASIC_CONNECTOR, new LatLng(51.590578, -0.228537), new String[]{"S1", "U1"}, true));
        list.add(new Routing("A3", BASIC_CONNECTOR, new LatLng(51.590646, -0.229174), new String[]{"I", "A4", "I1"}, true));
        list.add(new Routing("A4", BASIC_CONNECTOR, new LatLng(51.590736, -0.229152), new String[]{"A3"}, true));
        list.add(new Routing("A5", BASIC_CONNECTOR, new LatLng(51.590669, -0.230289), new String[]{"G1", "A6"}, true));
        list.add(new Routing("A6", BASIC_CONNECTOR, new LatLng(51.590508, -0.230537), new String[]{"A8", "A5"}, true));
        list.add(new Routing("A7", BASIC_CONNECTOR, new LatLng(51.590537, -0.230941), new String[]{"A8"}, true));
        list.add(new Routing("A8", BASIC_CONNECTOR, new LatLng(51.590508, -0.230666), new String[]{"A6", "A7"}, true));
        list.add(new Routing("A9", BASIC_CONNECTOR, new LatLng(51.589365, -0.230148), new String[]{"W", "J1", "A11"}, true));
        list.add(new Routing("A10", BASIC_CONNECTOR, new LatLng(51.588977, -0.230572), new String[]{"L1", "J1"}, true));
        list.add(new Routing("A11", BASIC_CONNECTOR, new LatLng(51.589368, -0.229769), new String[]{"T", "V", "A9"}, true));
        list.add(new Routing("A12", BASIC_CONNECTOR, new LatLng(51.589093, -0.230278), new String[]{"J1", "V"}, true));
        list.add(new Routing("A13", BASIC_CONNECTOR, new LatLng(51.589778, -0.230348), new String[]{"E1", "A14"}, true));
        list.add(new Routing("A14", BASIC_CONNECTOR, new LatLng(51.589878, -0.230309), new String[]{"A13", "C1"}, true));
        list.add(new Routing("A15", BASIC_CONNECTOR, new LatLng(51.589739, -0.229810), new String[]{"O", "P"}, true));
        list.add(new Routing("A16", BASIC_CONNECTOR, new LatLng(51.589754, -0.229946), new String[]{"P", "X"}, true));

        return list;
    }

    public static ArrayList<Routing> getRooms() {
        ArrayList<Routing> list = new ArrayList<>();

        //College Ground Floor
        list.add(new Routing("College Building", ROOM, 2, COLLEGE, 2, 0, new LatLng(51.589825, -0.228839), new String[]{"O", "Q", "S", "W"}));
        list.add(new Routing("Quad", ROOM, 2, COLLEGE, 2, 0, new LatLng(51.589825, -0.228839), new String[]{"O", "Q", "S", "W"}));
        list.add(new Routing("CG03", ROOM, 3, COLLEGE, 2, 0, new LatLng(51.589594, -0.228487), new String[]{"R", "P", "T", "N", "K", "X", "Y"}));
        list.add(new Routing("CG04", ROOM, 3, COLLEGE, 2, 0, new LatLng(51.589605, -0.228577), new String[]{"R", "P", "T", "N", "K", "X", "Y"}));
        list.add(new Routing("CG06", ROOM, 3, COLLEGE, 2, 0, new LatLng(51.589614, -0.228666), new String[]{"R", "P", "T", "N", "K", "X", "Y"}));
        list.add(new Routing("CG07", ROOM, 3, COLLEGE, 2, 0, new LatLng(51.589618, -0.228698), new String[]{"R", "P", "T", "N", "K", "X", "Y"}));
        list.add(new Routing("CG08", ROOM, 3, COLLEGE, 2, 0, new LatLng(51.589633, -0.228832), new String[]{"R", "P", "T", "N", "K", "X", "Y"}));
        list.add(new Routing("CG09", ROOM, 3, COLLEGE, 2, 0, new LatLng(51.589643, -0.228925), new String[]{"R", "P", "T", "N", "K", "X", "Y"}));
        list.add(new Routing("CG10", ROOM, 3, COLLEGE, 2, 0, new LatLng(51.589653, -0.229011), new String[]{"R", "P", "T", "N", "K", "X", "Y"}));
        list.add(new Routing("CG11", ROOM, 3, COLLEGE, 2, 0, new LatLng(51.589658, -0.229049), new String[]{"R", "P", "T", "N", "K", "X", "Y"}));
        list.add(new Routing("CG12A", ROOM, 3, COLLEGE, 2, 0, new LatLng(51.589678, -0.229225), new String[]{"R", "P", "T", "N", "K", "X", "Y"}));
        list.add(new Routing("CG12B", ROOM, 3, COLLEGE, 2, 0, new LatLng(51.589682, -0.229284), new String[]{"R", "P", "T", "N", "K", "X", "Y"}));
        list.add(new Routing("CG13", ROOM, 3, COLLEGE, 2, 0, new LatLng(51.589705, -0.229462), new String[]{"R", "P", "T", "N", "K", "X", "Y"}));
        list.add(new Routing("CG14", ROOM, 3, COLLEGE, 2, 0, new LatLng(51.589719, -0.229596), new String[]{"R", "P", "T", "N", "K", "X", "Y"}));
        list.add(new Routing("CG15", ROOM, 3, COLLEGE, 2, 0, new LatLng(51.589678, -0.229225), new String[]{"R", "P", "T", "N", "K", "X", "Y"}));
        list.add(new Routing("CG16", ROOM, 9, COLLEGE, 2, 0, new LatLng(51.589763, -0.229446), new String[]{"K", "L"}));
        list.add(new Routing("CG30", ROOM, 17, COLLEGE, 2, 0, new LatLng(51.589753, -0.229059), new String[]{"W", "X"}));
        list.add(new Routing("CG36", ROOM, 16, COLLEGE, 2, 0, new LatLng(51.589957, -0.228998), new String[]{"W", "V"}));
        list.add(new Routing("CG37", ROOM, 16, COLLEGE, 2, 0, new LatLng(51.589920, -0.229009), new String[]{"W", "V"}));
        list.add(new Routing("CG41", ROOM, 1, COLLEGE, 2, 0, new LatLng(51.590062, -0.229314), new String[]{"C", "D", "B", "A", "E", "V", "Z"}));
        list.add(new Routing("CG43", ROOM, 1, COLLEGE, 2, 0, new LatLng(51.590052, -0.229228), new String[]{"C", "D", "B", "A", "E", "V", "Z"}));
        list.add(new Routing("CG45", ROOM, 1, COLLEGE, 2, 0, new LatLng(51.590043, -0.229142), new String[]{"C", "D", "B", "A", "E", "V", "Z"}));
        list.add(new Routing("CG47", ROOM, 1, COLLEGE, 2, 0, new LatLng(51.590003, -0.228806), new String[]{"C", "D", "B", "A", "E", "V", "Z"}));
        list.add(new Routing("CG48", ROOM, 1, COLLEGE, 2, 0, new LatLng(51.589984, -0.228639), new String[]{"B", "C", "D", "A", "E", "V", "Z"}));
        list.add(new Routing("CG49", ROOM, 1, COLLEGE, 2, 0, new LatLng(51.589974, -0.228544), new String[]{"B", "C", "D", "A", "E", "V", "Z"}));
        list.add(new Routing("CG51", ROOM, 1, COLLEGE, 2, 0, new LatLng(51.589964, -0.228452), new String[]{"B", "C", "D", "A", "E", "V", "Z"}));

        list.add(new Routing("CG60", ROOM, 8, COLLEGE, 2, 0, new LatLng(51.589930, -0.229445), new String[]{"J", "G"}));
        list.add(new Routing("CG62", ROOM, 1, COLLEGE, 2, 0, new LatLng(51.590062, -0.229314), new String[]{"C", "D", "B", "A", "E", "V", "Z"}));
        list.add(new Routing("CG62 (Disabled Access)", ROOM, 8, COLLEGE, 2, 0, new LatLng(51.589925, -0.229401), new String[]{"J", "G"}));
        list.add(new Routing("CG76", ROOM, 6, COLLEGE, 2, 0, new LatLng(51.589921, -0.229081), new String[]{"D", "O", "N"}));
        list.add(new Routing("CG77", ROOM, 6, COLLEGE, 2, 0, new LatLng(51.589864, -0.229097), new String[]{"O", "N", "D"}));
        list.add(new Routing("Costa (Quad)", ROOM, 5, COLLEGE, 2, 0, new LatLng(51.589755, -0.228960), new String[]{"P", "Q", "C"}));


        //College First Floor
        list.add(new Routing("C101", ROOM, 3, COLLEGE, 1, 1, new LatLng(51.589676, -0.228350), new String[]{"G", "A"}));
        list.add(new Routing("C104", ROOM, 2, COLLEGE, 1, 1, new LatLng(51.589593, -0.228483), new String[]{"G", "H", "J", "K", "E"}));
        list.add(new Routing("C105", ROOM, 2, COLLEGE, 1, 1, new LatLng(51.589597, -0.228529), new String[]{"G", "H", "J", "K", "E"}));
        list.add(new Routing("C106", ROOM, 2, COLLEGE, 1, 1, new LatLng(51.589615, -0.228697), new String[]{"G", "H", "J", "K", "E"}));
        list.add(new Routing("C107", ROOM, 2, COLLEGE, 1, 1, new LatLng(51.589643, -0.228925), new String[]{"G", "H", "J", "K", "E"}));
        list.add(new Routing("C109", ROOM, 2, COLLEGE, 1, 1, new LatLng(51.589653, -0.229016), new String[]{"G", "H", "J", "K", "E"}));
        list.add(new Routing("C110", ROOM, 2, COLLEGE, 1, 1, new LatLng(51.589685, -0.229287), new String[]{"G", "H", "J", "K", "E"}));
        list.add(new Routing("C111", ROOM, 2, COLLEGE, 1, 1, new LatLng(51.589689, -0.229335), new String[]{"G", "H", "J", "K", "E"}));
        list.add(new Routing("C113", ROOM, 2, COLLEGE, 1, 1, new LatLng(51.589703, -0.229463), new String[]{"G", "H", "J", "K", "E"}));
        list.add(new Routing("C114", ROOM, 2, COLLEGE, 1, 1, new LatLng(51.589659, -0.229051), new String[]{"G", "H", "J", "K", "E"}));
        list.add(new Routing("C115", ROOM, 1, COLLEGE, 1, 1, new LatLng(51.590018, -0.228946), new String[]{"A", "B", "C", "N", "O"}));
        list.add(new Routing("C117", ROOM, 11, COLLEGE, 1, 1, new LatLng(51.589771, -0.229396), new String[]{"E", "P"}));
        list.add(new Routing("C118", ROOM, 11, COLLEGE, 1, 1, new LatLng(51.589793, -0.229392), new String[]{"E", "P"}));
        list.add(new Routing("C120", ROOM, 11, COLLEGE, 1, 1, new LatLng(51.589906, -0.229359), new String[]{"E", "P"}));
        list.add(new Routing("C121", ROOM, 12, COLLEGE, 1, 1, new LatLng(51.590037, -0.229370), new String[]{"P", "Q"}));
        list.add(new Routing("C122", ROOM, 1, COLLEGE, 1, 1, new LatLng(51.590066, -0.229359), new String[]{"A", "B", "C", "N", "O"}));
        list.add(new Routing("C126", ROOM, 1, COLLEGE, 1, 1, new LatLng(51.590062, -0.229321), new String[]{"A", "B", "C", "N", "O"}));
        list.add(new Routing("C127", ROOM, 1, COLLEGE, 1, 1, new LatLng(51.590048, -0.229176), new String[]{"A", "B", "C", "N", "O"}));
        list.add(new Routing("C128", ROOM, 1, COLLEGE, 1, 1, new LatLng(51.590044, -0.229137), new String[]{"A", "B", "C", "N", "O"}));
        list.add(new Routing("C131", ROOM, 1, COLLEGE, 1, 1, new LatLng(51.590011, -0.228863), new String[]{"A", "B", "C", "N", "O"}));
        list.add(new Routing("C132", ROOM, 1, COLLEGE, 1, 1, new LatLng(51.590001, -0.228770), new String[]{"A", "B", "C", "N", "O"}));
        list.add(new Routing("C135", ROOM, 1, COLLEGE, 1, 1, new LatLng(51.589966, -0.228461), new String[]{"A", "B", "C", "N", "O"}));
        list.add(new Routing("C136", ROOM, 1, COLLEGE, 1, 1, new LatLng(51.589951, -0.228320), new String[]{"A", "B", "C", "N", "O"}));
        list.add(new Routing("C138", ROOM, 3, COLLEGE, 1, 1, new LatLng(51.589849, -0.228304), new String[]{"G", "A"}));

        //College Second Floor
        list.add(new Routing("C204", ROOM, 2, COLLEGE, 0, 2, new LatLng(51.589597, -0.228527), new String[]{"A", "B", "C", "D", "F"}));
        list.add(new Routing("C205", ROOM, 2, COLLEGE, 0, 2, new LatLng(51.589614, -0.228658), new String[]{"A", "B", "C", "D", "F"}));
        list.add(new Routing("C206", ROOM, 2, COLLEGE, 0, 2, new LatLng(51.589618, -0.228699), new String[]{"A", "B", "C", "D", "F"}));
        list.add(new Routing("C207", ROOM, 2, COLLEGE, 0, 2, new LatLng(51.589641, -0.228921), new String[]{"A", "B", "C", "D", "F"}));
        list.add(new Routing("C209", ROOM, 2, COLLEGE, 0, 2, new LatLng(51.589652, -0.229013), new String[]{"A", "B", "C", "D", "F"}));
        list.add(new Routing("C210", ROOM, 6, COLLEGE, 0, 2, new LatLng(51.589693, -0.229043), new String[]{"D", "G"}));
        list.add(new Routing("C211", ROOM, 11, COLLEGE, 0, 2, new LatLng(51.590017, -0.228938), new String[]{"K", "L", "M"}));
        list.add(new Routing("C212", ROOM, 10, COLLEGE, 0, 2, new LatLng(51.589702, -0.229186), new String[]{"F", "J", "K"}));
        list.add(new Routing("C213A", ROOM, 10, COLLEGE, 0, 2, new LatLng(51.589785, -0.229163), new String[]{"F", "J", "K"}));
        list.add(new Routing("C213B", ROOM, 10, COLLEGE, 0, 2, new LatLng(51.589812, -0.229153), new String[]{"F", "J", "K"}));
        list.add(new Routing("C214", ROOM, 10, COLLEGE, 0, 2, new LatLng(51.589903, -0.229131), new String[]{"F", "J", "K"}));
        list.add(new Routing("C215", ROOM, 10, COLLEGE, 0, 2, new LatLng(51.589957, -0.229114), new String[]{"F", "J", "K"}));
        list.add(new Routing("C216B", ROOM, 10, COLLEGE, 0, 2, new LatLng(51.589989, -0.229107), new String[]{"F", "J", "K"}));
        list.add(new Routing("C217", ROOM, 11, COLLEGE, 0, 2, new LatLng(51.590015, -0.228898), new String[]{"K", "L", "M"}));
        list.add(new Routing("C218", ROOM, 11, COLLEGE, 0, 2, new LatLng(51.590012, -0.228858), new String[]{"K", "L", "M"}));


        //Williams Ground Floor
        list.add(new Routing("Williams Building", ROOM, 4, WILLIAMS, 1, 0, new LatLng(51.590479, -0.228873), new String[]{"E", "H"}));
        list.add(new Routing("WG07", ROOM, 4, WILLIAMS, 1, 0, new LatLng(51.590479, -0.228873), new String[]{"E", "H"}));
        list.add(new Routing("WG32", ROOM, 8, WILLIAMS, 1, 0, new LatLng(51.590511, -0.228757), new String[]{"G", "I"}));
        list.add(new Routing("WG33", ROOM, 5, WILLIAMS, 1, 0, new LatLng(51.590505, -0.228718), new String[]{"G", "E"}));
        list.add(new Routing("WG35", ROOM, 4, WILLIAMS, 1, 0, new LatLng(51.590435, -0.228471), new String[]{"D", "L"}));
        list.add(new Routing("WG37", ROOM, 3, WILLIAMS, 1, 0, new LatLng(51.590417, -0.228166), new String[]{"C", "B"}));
        list.add(new Routing("WG38", ROOM, 3, WILLIAMS, 1, 0, new LatLng(51.590437, -0.228161), new String[]{"C", "B"}));
        list.add(new Routing("WG39", ROOM, 3, WILLIAMS, 1, 0, new LatLng(51.590449, -0.228157), new String[]{"C", "B"}));
        list.add(new Routing("WG41", ROOM, 3, WILLIAMS, 1, 0, new LatLng(51.590630, -0.228110), new String[]{"C", "B"}));
        list.add(new Routing("WG42", ROOM, 3, WILLIAMS, 1, 0, new LatLng(51.590535, -0.228132), new String[]{"C", "B"}));
        list.add(new Routing("WG44", ROOM, 3, WILLIAMS, 1, 0, new LatLng(51.590562, -0.228125), new String[]{"C", "B"}));
        list.add(new Routing("WG45", ROOM, 3, WILLIAMS, 1, 0, new LatLng(51.590594, -0.228121), new String[]{"C", "B"}));
        list.add(new Routing("WG46", ROOM, 3, WILLIAMS, 1, 0, new LatLng(51.590621, -0.228117), new String[]{"C", "B"}));
        list.add(new Routing("WG47", ROOM, 1, WILLIAMS, 1, 0, new LatLng(51.590628, -0.228268), new String[]{"A", "J", "M"}));
        list.add(new Routing("WG48", ROOM, 1, WILLIAMS, 1, 0, new LatLng(51.590628, -0.228268), new String[]{"A", "J", "M"}));
        list.add(new Routing("WG49", ROOM, 1, WILLIAMS, 1, 0, new LatLng(51.590652, -0.228263), new String[]{"A", "J", "M"}));
        list.add(new Routing("WG50", ROOM, 1, WILLIAMS, 1, 0, new LatLng(51.590703, -0.228250), new String[]{"A", "J", "M"}));
        list.add(new Routing("WG51", ROOM, 1, WILLIAMS, 1, 0, new LatLng(51.590703, -0.228250), new String[]{"A", "J", "M"}));

        //Williams First Floor
        list.add(new Routing("W142", ROOM, 5, WILLIAMS, 0, 1, new LatLng(51.590437, -0.228342), new String[]{"E", "F", "D"}));
        list.add(new Routing("W143", ROOM, 4, WILLIAMS, 0, 1, new LatLng(51.590433, -0.228150), new String[]{"D", "C"}));
        list.add(new Routing("W144", ROOM, 4, WILLIAMS, 0, 1, new LatLng(51.590457, -0.228145), new String[]{"D", "C"}));
        list.add(new Routing("W145", ROOM, 4, WILLIAMS, 0, 1, new LatLng(51.590473, -0.228142), new String[]{"D", "C"}));
        list.add(new Routing("W147", ROOM, 4, WILLIAMS, 0, 1, new LatLng(51.590589, -0.228099), new String[]{"D", "C"}));
        list.add(new Routing("W148", ROOM, 1, WILLIAMS, 0, 1, new LatLng(51.590666, -0.228269), new String[]{"A", "B"}));
        list.add(new Routing("W149", ROOM, 1, WILLIAMS, 0, 1, new LatLng(51.590682, -0.228266), new String[]{"A", "B"}));
        list.add(new Routing("W150", ROOM, 1, WILLIAMS, 0, 1, new LatLng(51.590712, -0.228257), new String[]{"A", "B"}));
        list.add(new Routing("W151", ROOM, 4, WILLIAMS, 0, 1, new LatLng(51.590707, -0.228064), new String[]{"D", "C"}));
        list.add(new Routing("W152", ROOM, 4, WILLIAMS, 0, 1, new LatLng(51.590731, -0.228057), new String[]{"D", "C"}));
        list.add(new Routing("W153", ROOM, 4, WILLIAMS, 0, 1, new LatLng(51.590799, -0.228039), new String[]{"D", "C"}));
        list.add(new Routing("W154", ROOM, 4, WILLIAMS, 0, 1, new LatLng(51.590731, -0.228057), new String[]{"D", "C"}));
        list.add(new Routing("W155", ROOM, 4, WILLIAMS, 0, 1, new LatLng(51.590707, -0.228064), new String[]{"D", "C"}));
        list.add(new Routing("W156", ROOM, 4, WILLIAMS, 0, 1, new LatLng(51.590612, -0.228093), new String[]{"D", "C"}));
        list.add(new Routing("W157", ROOM, 4, WILLIAMS, 0, 1, new LatLng(51.590589, -0.228099), new String[]{"D", "C"}));
        list.add(new Routing("W158", ROOM, 4, WILLIAMS, 0, 1, new LatLng(51.590492, -0.228129), new String[]{"D", "C"}));
        list.add(new Routing("W159", ROOM, 4, WILLIAMS, 0, 1, new LatLng(51.590453, -0.228141), new String[]{"D", "C"}));
        list.add(new Routing("W160", ROOM, 4, WILLIAMS, 0, 1, new LatLng(51.590350, -0.228169), new String[]{"D", "C"}));


        //Hatchcroft Ground Floor
        list.add(new Routing("Hatchcroft Building", ROOM, 1, HATCHCROFT, 2, 0, new LatLng(51.589154, -0.229178), new String[]{"A", "C", "G", "H"}));
        list.add(new Routing("HG01", ROOM, 1, HATCHCROFT, 2, 0, new LatLng(51.589138, -0.229016), new String[]{"A", "C", "G", "H"}));
        list.add(new Routing("HG02", ROOM, 1, HATCHCROFT, 2, 0, new LatLng(51.589122, -0.228864), new String[]{"A", "C", "G", "H"}));
        list.add(new Routing("HG03", ROOM, 1, HATCHCROFT, 2, 0, new LatLng(51.589103, -0.228762), new String[]{"A", "C", "G", "H"}));
        list.add(new Routing("HG04", ROOM, 5, HATCHCROFT, 2, 0, new LatLng(51.589088, -0.228738), new String[]{"D", "F"}));
        list.add(new Routing("HG05", ROOM, 6, HATCHCROFT, 2, 0, new LatLng(51.589100, -0.228649), new String[]{"E", "F"}));
        list.add(new Routing("HG06", ROOM, 7, HATCHCROFT, 2, 0, new LatLng(51.589085, -0.228600), new String[]{"E"}));
        list.add(new Routing("HG07", ROOM, 7, HATCHCROFT, 2, 0, new LatLng(51.589085, -0.228600), new String[]{"E"}));
        //HG08 Toilet
        list.add(new Routing("HG08", ROOM, 5, HATCHCROFT, 2, 0, new LatLng(51.589077, -0.228653), new String[]{"D", "F"}));
        list.add(new Routing("HG09", ROOM, 5, HATCHCROFT, 2, 0, new LatLng(51.589078, -0.228689), new String[]{"D", "F"}));
        list.add(new Routing("HG10", ROOM, 1, HATCHCROFT, 2, 0, new LatLng(51.589109, -0.228809), new String[]{"A", "C", "G", "H"}));
        list.add(new Routing("HG11", ROOM, 1, HATCHCROFT, 2, 0, new LatLng(51.589122, -0.228864), new String[]{"A", "C", "G", "H"}));
        list.add(new Routing("HG12", ROOM, 1, HATCHCROFT, 2, 0, new LatLng(51.589122, -0.228864), new String[]{"A", "C", "G", "H"}));
        list.add(new Routing("HG13", ROOM, 1, HATCHCROFT, 2, 0, new LatLng(51.589138, -0.229016), new String[]{"A", "C", "G", "H"}));
        list.add(new Routing("HG14", ROOM, 2, HATCHCROFT, 2, 0, new LatLng(51.589105, -0.229233), new String[]{"A", "B"}));
        list.add(new Routing("HG19", ROOM, 2, HATCHCROFT, 2, 0, new LatLng(51.589054, -0.229264), new String[]{"A", "B"}));
        //HG20 & HG21 Toilet
        list.add(new Routing("HG20", ROOM, 2, HATCHCROFT, 2, 0, new LatLng(51.589140, -0.229230), new String[]{"A", "B"}));
        list.add(new Routing("HG21", ROOM, 2, HATCHCROFT, 2, 0, new LatLng(51.589140, -0.229230), new String[]{"A", "B"}));
        list.add(new Routing("HG24", ROOM, 1, HATCHCROFT, 2, 0, new LatLng(51.589200, -0.229583), new String[]{"A", "C", "G", "H"}));
        list.add(new Routing("HG27", ROOM, 1, HATCHCROFT, 2, 0, new LatLng(51.589200, -0.229583), new String[]{"A", "C", "G", "H"}));
        list.add(new Routing("HG28", ROOM, 1, HATCHCROFT, 2, 0, new LatLng(51.589200, -0.229583), new String[]{"A", "C", "G", "H"}));
        list.add(new Routing("HG29", ROOM, 1, HATCHCROFT, 2, 0, new LatLng(51.589209, -0.229642), new String[]{"A", "C", "G", "H"}));
        list.add(new Routing("HG30", ROOM, 1, HATCHCROFT, 2, 0, new LatLng(51.589196, -0.229521), new String[]{"A", "C", "G", "H"}));
        list.add(new Routing("HG31", ROOM, 1, HATCHCROFT, 2, 0, new LatLng(51.589182, -0.229423), new String[]{"A", "C", "G", "H"}));
        list.add(new Routing("HG33", ROOM, 1, HATCHCROFT, 2, 0, new LatLng(51.589175, -0.229333), new String[]{"A", "C", "G", "H"}));

        //Hatchcroft First Floor
        list.add(new Routing("H101", ROOM, 1, HATCHCROFT, 1, 1, new LatLng(51.589136, -0.229010), new String[]{"A", "B", "C", "F", "G"}));
        list.add(new Routing("H102", ROOM, 1, HATCHCROFT, 1, 1, new LatLng(51.589122, -0.228895), new String[]{"A", "B", "C", "F", "G"}));
        list.add(new Routing("H104", ROOM, 1, HATCHCROFT, 1, 1, new LatLng(51.589107, -0.228772), new String[]{"A", "B", "C", "F", "G"}));
        list.add(new Routing("H105", ROOM, 1, HATCHCROFT, 1, 1, new LatLng(51.589092, -0.228649), new String[]{"A", "B", "C", "F", "G"}));
        list.add(new Routing("H106", ROOM, 1, HATCHCROFT, 1, 1, new LatLng(51.589095, -0.228680), new String[]{"A", "B", "C", "F", "G"}));
        list.add(new Routing("H109", ROOM, 1, HATCHCROFT, 1, 1, new LatLng(51.589109, -0.228814), new String[]{"A", "B", "C", "F", "G"}));
        list.add(new Routing("H110", ROOM, 1, HATCHCROFT, 1, 1, new LatLng(51.589109, -0.228814), new String[]{"A", "B", "C", "F", "G"}));
        list.add(new Routing("H111", ROOM, 1, HATCHCROFT, 1, 1, new LatLng(51.589109, -0.228814), new String[]{"A", "B", "C", "F", "G"}));
        list.add(new Routing("H113", ROOM, 1, HATCHCROFT, 1, 1, new LatLng(51.589143, -0.229093), new String[]{"A", "B", "C", "F", "G"}));
        list.add(new Routing("H116", ROOM, 3, HATCHCROFT, 1, 1, new LatLng(51.589059, -0.229254), new String[]{"E", "F"}));
        list.add(new Routing("H117", ROOM, 4, HATCHCROFT, 1, 1, new LatLng(51.588980, -0.229334), new String[]{"D", "E"}));
        list.add(new Routing("H118", ROOM, 4, HATCHCROFT, 1, 1, new LatLng(51.588985, -0.229385), new String[]{"D", "E"}));
        list.add(new Routing("H119", ROOM, 4, HATCHCROFT, 1, 1, new LatLng(51.588991, -0.229434), new String[]{"D", "E"}));
        list.add(new Routing("H120", ROOM, 4, HATCHCROFT, 1, 1, new LatLng(51.588996, -0.229479), new String[]{"D", "E"}));
        list.add(new Routing("H121", ROOM, 4, HATCHCROFT, 1, 1, new LatLng(51.589003, -0.229523), new String[]{"D", "E"}));
        list.add(new Routing("H122", ROOM, 4, HATCHCROFT, 1, 1, new LatLng(51.589003, -0.229523), new String[]{"D", "E"}));
        list.add(new Routing("H123", ROOM, 5, HATCHCROFT, 1, 1, new LatLng(51.589033, -0.229516), new String[]{"D", "C"}));
        list.add(new Routing("H124", ROOM, 5, HATCHCROFT, 1, 1, new LatLng(51.589063, -0.229507), new String[]{"D", "C"}));
        list.add(new Routing("H125", ROOM, 5, HATCHCROFT, 1, 1, new LatLng(51.589096, -0.229496), new String[]{"D", "C"}));
        list.add(new Routing("H128", ROOM, 3, HATCHCROFT, 1, 1, new LatLng(51.589141, -0.229233), new String[]{"E", "F"}));
        list.add(new Routing("H129", ROOM, 3, HATCHCROFT, 1, 1, new LatLng(51.589141, -0.229233), new String[]{"E", "F"}));
        list.add(new Routing("H133", ROOM, 1, HATCHCROFT, 1, 1, new LatLng(51.589195, -0.229554), new String[]{"A", "B", "C", "F", "G"}));
        list.add(new Routing("H135", ROOM, 6, HATCHCROFT, 1, 1, new LatLng(51.589179, -0.229660), new String[]{"A"}));
        list.add(new Routing("H136", ROOM, 6, HATCHCROFT, 1, 1, new LatLng(51.589179, -0.229660), new String[]{"A"}));
        list.add(new Routing("H137", ROOM, 1, HATCHCROFT, 1, 1, new LatLng(51.589179, -0.229660), new String[]{"A", "B", "C", "F", "G"}));
        list.add(new Routing("H139", ROOM, 1, HATCHCROFT, 1, 1, new LatLng(51.589200, -0.229558), new String[]{"A", "B", "C", "F", "G"}));
        list.add(new Routing("H141A", ROOM, 1, HATCHCROFT, 1, 1, new LatLng(51.589194, -0.229501), new String[]{"A", "B", "C", "F", "G"}));
        list.add(new Routing("H141B", ROOM, 1, HATCHCROFT, 1, 1, new LatLng(51.589167, -0.229286), new String[]{"A", "B", "C", "F", "G"}));

        //Hatchcroft Second Floor
        list.add(new Routing("H201", ROOM, 1, HATCHCROFT, 0, 2, new LatLng(51.589131, -0.228970), new String[]{"A", "B"}));
        list.add(new Routing("H202", ROOM, 1, HATCHCROFT, 0, 2, new LatLng(51.589126, -0.228912), new String[]{"A", "B"}));
        list.add(new Routing("H203", ROOM, 1, HATCHCROFT, 0, 2, new LatLng(51.589110, -0.228769), new String[]{"A", "B"}));
        list.add(new Routing("H205", ROOM, 1, HATCHCROFT, 0, 2, new LatLng(51.589099, -0.228650), new String[]{"A", "B"}));
        list.add(new Routing("H206", ROOM, 1, HATCHCROFT, 0, 2, new LatLng(51.589087, -0.228617), new String[]{"A", "B"}));
        list.add(new Routing("H207", ROOM, 1, HATCHCROFT, 0, 2, new LatLng(51.589096, -0.228693), new String[]{"A", "B"}));
        list.add(new Routing("H208", ROOM, 1, HATCHCROFT, 0, 2, new LatLng(51.589105, -0.228761), new String[]{"A", "B"}));
        list.add(new Routing("H209", ROOM, 1, HATCHCROFT, 0, 2, new LatLng(51.589131, -0.228970), new String[]{"A", "B"}));
        list.add(new Routing("H211", ROOM, 1, HATCHCROFT, 0, 2, new LatLng(51.589142, -0.229133), new String[]{"A", "B"}));
        list.add(new Routing("H216", ROOM, 3, HATCHCROFT, 0, 2, new LatLng(51.589156, -0.229398), new String[]{"B"}));
        list.add(new Routing("H217", ROOM, 3, HATCHCROFT, 0, 2, new LatLng(51.589156, -0.229398), new String[]{"B"}));
        list.add(new Routing("H219", ROOM, 1, HATCHCROFT, 0, 2, new LatLng(51.589193, -0.229510), new String[]{"A", "B"}));
        list.add(new Routing("H220", ROOM, 1, HATCHCROFT, 0, 2, new LatLng(51.589200, -0.229580), new String[]{"A", "B"}));
        list.add(new Routing("H221", ROOM, 1, HATCHCROFT, 0, 2, new LatLng(51.589210, -0.229636), new String[]{"A", "B"}));
        list.add(new Routing("H222", ROOM, 1, HATCHCROFT, 0, 2, new LatLng(51.589200, -0.229580), new String[]{"A", "B"}));
        list.add(new Routing("H223", ROOM, 1, HATCHCROFT, 0, 2, new LatLng(51.589187, -0.229450), new String[]{"A", "B"}));
        list.add(new Routing("H224", ROOM, 1, HATCHCROFT, 0, 2, new LatLng(51.589167, -0.229274), new String[]{"A", "B"}));

        //MDX HOUSE GROUND FLOOR
        list.add(new Routing("MDX House", ROOM, 3, MDXHOUSE, 0, 0, new LatLng(51.589925, -0.230887), new String[]{"B"}));

        //MDX BASEMENT
        list.add(new Routing("Gym", ROOM, 2, MDXHOUSE, 1, -1, new LatLng(51.589951, -0.230764), new String[]{"B", "C"}));
        list.add(new Routing("Student Union", ROOM, 2, MDXHOUSE, 1, -1, new LatLng(51.590135, -0.230729), new String[]{"B", "C"}));
        list.add(new Routing("Real Tennis Club", ROOM, 3, MDXHOUSE, 1, -1, new LatLng(51.589809, -0.230640), new String[]{"C"}));
        list.add(new Routing("Sports-Dance Studio", ROOM, 3, MDXHOUSE, 1, -1, new LatLng(51.589812, -0.230558), new String[]{"C"}));

        //Building 9
        list.add(new Routing("Building 9", ROOM, 3, BUILDING9, 0, 0, new LatLng(51.588683, -0.229511), new String[]{"B", "D"}));
        list.add(new Routing("BG01", ROOM, 3, BUILDING9, 0, 0, new LatLng(51.588683, -0.229511), new String[]{"B", "D"}));
        list.add(new Routing("BG02", ROOM, 3, BUILDING9, 0, 0, new LatLng(51.588737, -0.229469), new String[]{"B", "D"}));
        list.add(new Routing("BG03", ROOM, 3, BUILDING9, 0, 0, new LatLng(51.588724, -0.229476), new String[]{"B", "D"}));
        list.add(new Routing("BG04", ROOM, 3, BUILDING9, 0, 0, new LatLng(51.588739, -0.229466), new String[]{"B", "D"}));
        list.add(new Routing("BG05", ROOM, 2, BUILDING9, 0, 0, new LatLng(51.588758, -0.229413), new String[]{"A", "B"}));
        list.add(new Routing("BG07", ROOM, 2, BUILDING9, 0, 0, new LatLng(51.588725, -0.229312), new String[]{"A", "B"}));
        list.add(new Routing("BG10", ROOM, 1, BUILDING9, 0, 0, new LatLng(51.588639, -0.229341), new String[]{"A", "C"}));
        list.add(new Routing("BG11", ROOM, 1, BUILDING9, 0, 0, new LatLng(51.588639, -0.229341), new String[]{"A", "C"}));

        //Building 10 Ground Floor
        list.add(new Routing("Building 10", ROOM, 1, BUILDING10, 1, 0, new LatLng(51.589824, -0.229877), new String[]{"A", "B"}));
        list.add(new Routing("BTG01", ROOM, 1, BUILDING10, 1, 0, new LatLng(51.589824, -0.229877), new String[]{"A", "B"}));
        list.add(new Routing("BTG02", ROOM, 1, BUILDING10, 1, 0, new LatLng(51.589842, -0.229873), new String[]{"A", "B"}));
        list.add(new Routing("BTG04", ROOM, 2, BUILDING10, 1, 0, new LatLng(51.589840, -0.229819), new String[]{"B"}));

        //Building 10 First Floor
        list.add(new Routing("BT101", ROOM, 1, BUILDING10, 0, 1, new LatLng(51.589832, -0.229867), new String[]{"A"}));
        list.add(new Routing("BT103", ROOM, 1, BUILDING10, 0, 1, new LatLng(51.589832, -0.229867), new String[]{"A"}));

        //SHEPPARD BASEMENT
        list.add(new Routing("SB01", ROOM, 3, SHEPPARDLIBRARY, 4, -1, new LatLng(51.590649, -0.229524), new String[]{"C", "D"}));
        list.add(new Routing("SB03", ROOM, 3, SHEPPARDLIBRARY, 4, -1, new LatLng(51.590743, -0.229495), new String[]{"C", "D"}));
        list.add(new Routing("SB05", ROOM, 1, SHEPPARDLIBRARY, 4, -1, new LatLng(51.590373, -0.229460), new String[]{"A"}));
        list.add(new Routing("SB12A", ROOM, 1, SHEPPARDLIBRARY, 4, -1, new LatLng(51.590285, -0.229563), new String[]{"A"}));
        list.add(new Routing("SB16", ROOM, 2, SHEPPARDLIBRARY, 4, -1, new LatLng(51.590464, -0.229512), new String[]{"A", "B", "C"}));

        //SHEPPARD GROUND FLOOR
        list.add(new Routing("Sheppard Library", ROOM, 1, SHEPPARDLIBRARY, 3, 0, new LatLng(51.590360, -0.229605), new String[]{"B"}));
        list.add(new Routing("SG01", ROOM, 1, SHEPPARDLIBRARY, 3, 0, new LatLng(51.590360, -0.229605), new String[]{"B"}));
        list.add(new Routing("SG02", ROOM, 2, SHEPPARDLIBRARY, 3, 0, new LatLng(51.590319, -0.229392), new String[]{"A", "B", "C"}));
        list.add(new Routing("SG09", ROOM, 6, SHEPPARDLIBRARY, 3, 0, new LatLng(51.590768, -0.229416), new String[]{"F"}));
        list.add(new Routing("SG10", ROOM, 6, SHEPPARDLIBRARY, 3, 0, new LatLng(51.590768, -0.229416), new String[]{"F"}));
        list.add(new Routing("SG11", ROOM, 6, SHEPPARDLIBRARY, 3, 0, new LatLng(51.590772, -0.229454), new String[]{"F"}));
        list.add(new Routing("SG12A", ROOM, 5, SHEPPARDLIBRARY, 3, 0, new LatLng(51.590720, -0.229506), new String[]{"E", "F"}));
        list.add(new Routing("SG12B", ROOM, 5, SHEPPARDLIBRARY, 3, 0, new LatLng(51.590634, -0.229532), new String[]{"E", "F"}));
        list.add(new Routing("SG13A", ROOM, 5, SHEPPARDLIBRARY, 3, 0, new LatLng(51.590615, -0.229539), new String[]{"E", "F"}));
        list.add(new Routing("SG13B", ROOM, 5, SHEPPARDLIBRARY, 3, 0, new LatLng(51.590530, -0.229562), new String[]{"E", "F"}));

        //Circle Cafe. Basement on floor plan, but ground floor here (Hence the name SB19)
        list.add(new Routing("SB19", ROOM, 2, CIRCLE_CAFE, 3, 0, new LatLng(51.590508, -0.229859), new String[]{"A"}));

        //SHEPPARD FIRST FLOOR
        list.add(new Routing("S101", ROOM, 3, SHEPPARDLIBRARY, 2, 1, new LatLng(51.590571, -0.229627), new String[]{"B", "C"}));
        list.add(new Routing("S103", ROOM, 1, SHEPPARDLIBRARY, 2, 1, new LatLng(51.590766, -0.229407), new String[]{"A"}));
        list.add(new Routing("S104", ROOM, 1, SHEPPARDLIBRARY, 2, 1, new LatLng(51.590766, -0.229407), new String[]{"A"}));
        list.add(new Routing("S105", ROOM, 1, SHEPPARDLIBRARY, 2, 1, new LatLng(51.590773, -0.229463), new String[]{"A"}));
        list.add(new Routing("S106", ROOM, 2, SHEPPARDLIBRARY, 2, 1, new LatLng(51.590627, -0.229531), new String[]{"B", "A"}));
        list.add(new Routing("S107", ROOM, 3, SHEPPARDLIBRARY, 2, 1, new LatLng(51.590563, -0.229552), new String[]{"B", "C"}));
        list.add(new Routing("S110", ROOM, 6, SHEPPARDLIBRARY, 2, 1, new LatLng(51.590419, -0.229508), new String[]{"E"}));
        list.add(new Routing("S111", ROOM, 5, SHEPPARDLIBRARY, 2, 1, new LatLng(51.590311, -0.229404), new String[]{"E", "F", "D"}));
        list.add(new Routing("S112", ROOM, 5, SHEPPARDLIBRARY, 2, 1, new LatLng(51.590311, -0.229404), new String[]{"E", "F", "D"}));
        list.add(new Routing("S118", ROOM, 5, SHEPPARDLIBRARY, 2, 1, new LatLng(51.590473, -0.229780), new String[]{"D", "E", "F"}));
        list.add(new Routing("S119", ROOM, 5, SHEPPARDLIBRARY, 2, 1, new LatLng(51.590412, -0.229637), new String[]{"D", "E", "F"}));

        //SHEPPARD SECOND FLOOR
        list.add(new Routing("S201", ROOM, 3, SHEPPARDLIBRARY, 1, 2, new LatLng(51.590571, -0.229627), new String[]{"B", "C"}));
        list.add(new Routing("S203", ROOM, 1, SHEPPARDLIBRARY, 1, 2, new LatLng(51.590767, -0.229409), new String[]{"A"}));
        list.add(new Routing("S204", ROOM, 1, SHEPPARDLIBRARY, 1, 2, new LatLng(51.590767, -0.229409), new String[]{"A"}));
        list.add(new Routing("S205", ROOM, 1, SHEPPARDLIBRARY, 1, 2, new LatLng(51.590773, -0.229461), new String[]{"A"}));
        list.add(new Routing("S206", ROOM, 2, SHEPPARDLIBRARY, 1, 2, new LatLng(51.590725, -0.229501), new String[]{"A", "B"}));
        list.add(new Routing("S215", ROOM, 5, SHEPPARDLIBRARY, 1, 2, new LatLng(51.590412, -0.229635), new String[]{"D", "E"}));
        list.add(new Routing("S216", ROOM, 5, SHEPPARDLIBRARY, 1, 2, new LatLng(51.590309, -0.229406), new String[]{"D", "E"}));
        list.add(new Routing("S217", ROOM, 5, SHEPPARDLIBRARY, 1, 2, new LatLng(51.590309, -0.229406), new String[]{"D", "E"}));
        list.add(new Routing("S218", ROOM, 5, SHEPPARDLIBRARY, 1, 2, new LatLng(51.590466, -0.229790), new String[]{"D", "E"}));
        list.add(new Routing("S219", ROOM, 5, SHEPPARDLIBRARY, 1, 2, new LatLng(51.590466, -0.229790), new String[]{"D", "E"}));

        //SHEPPARD THIRD FLOOR
        list.add(new Routing("S302", ROOM, 2, SHEPPARDLIBRARY, 0, 3, new LatLng(51.590403, -0.229620), new String[]{"B", "A"}));
        list.add(new Routing("S303", ROOM, 2, SHEPPARDLIBRARY, 0, 3, new LatLng(51.590307, -0.229403), new String[]{"B", "A"}));
        list.add(new Routing("S304", ROOM, 2, SHEPPARDLIBRARY, 0, 3, new LatLng(51.590307, -0.229403), new String[]{"B", "A"}));
        list.add(new Routing("S305", ROOM, 2, SHEPPARDLIBRARY, 0, 3, new LatLng(51.590473, -0.229781), new String[]{"B", "A"}));

        //VINE GROUND FLOOR

        list.add(new Routing("Vine Building", ROOM, 2, VINE, 1, 0, new LatLng(51.590621, -0.230778), new String[]{"B", "A"}));
        list.add(new Routing("VG01", ROOM, 2, VINE, 1, 0, new LatLng(51.590621, -0.230778), new String[]{"B", "A"}));
        list.add(new Routing("VG02", ROOM, 2, VINE, 1, 0, new LatLng(51.590626, -0.230808), new String[]{"B", "A"}));
        list.add(new Routing("VG04", ROOM, 3, VINE, 1, 0, new LatLng(51.590708, -0.230891), new String[]{"B"}));
        list.add(new Routing("VG05", ROOM, 2, VINE, 1, 0, new LatLng(51.590630, -0.230825), new String[]{"B", "A"}));
        list.add(new Routing("VG06", ROOM, 2, VINE, 1, 0, new LatLng(51.590621, -0.230736), new String[]{"B", "A"}));
        list.add(new Routing("VG07", ROOM, 2, VINE, 1, 0, new LatLng(51.590619, -0.230715), new String[]{"B", "A"}));
        list.add(new Routing("VG08", ROOM, 1, VINE, 1, 0, new LatLng(51.590678, -0.230613), new String[]{"A"}));

        //VINE FIRST FLOOR
        list.add(new Routing("V101", ROOM, 2, VINE, 0, 1, new LatLng(51.590621, -0.230778), new String[]{"B", "A"}));
        list.add(new Routing("V102", ROOM, 2, VINE, 0, 1, new LatLng(51.590626, -0.230808), new String[]{"B", "A"}));
        list.add(new Routing("V103", ROOM, 2, VINE, 0, 1, new LatLng(51.590630, -0.230825), new String[]{"B", "A"}));
        list.add(new Routing("V104", ROOM, 2, VINE, 0, 1, new LatLng(51.590621, -0.230736), new String[]{"B", "A"}));
        list.add(new Routing("V105", ROOM, 2, VINE, 0, 1, new LatLng(51.590619, -0.230715), new String[]{"B", "A"}));

        list.add(new Routing("Grove Block A", ROOM, 4, GROVE_BLOCK_A, 4, 0, new LatLng(51.588695, -0.230596), new String[]{"C", "D", "E"}));

        //GROVE BLOCK A BASEMENT
        list.add(new Routing("GB01", ROOM, 1, GROVE_BLOCK_A, 5, -1, new LatLng(51.588857, -0.230609), new String[]{"A", "B"}));
        list.add(new Routing("GB04", ROOM, 2, GROVE_BLOCK_A, 5, -1, new LatLng(51.588690, -0.230542), new String[]{"C", "B"}));
        list.add(new Routing("GB05", ROOM, 3, GROVE_BLOCK_A, 5, -1, new LatLng(51.588684, -0.230601), new String[]{"C", "D"}));
        list.add(new Routing("GB07", ROOM, 3, GROVE_BLOCK_A, 5, -1, new LatLng(51.588660, -0.230613), new String[]{"C", "D"}));
        list.add(new Routing("GB09", ROOM, 4, GROVE_BLOCK_A, 5, -1, new LatLng(51.588611, -0.230608), new String[]{"E", "D"}));
        list.add(new Routing("GB10", ROOM, 4, GROVE_BLOCK_A, 5, -1, new LatLng(51.588591, -0.230534), new String[]{"E", "D"}));
        list.add(new Routing("GB12", ROOM, 4, GROVE_BLOCK_A, 5, -1, new LatLng(51.588573, -0.230539), new String[]{"E", "D"}));
        list.add(new Routing("GB17A", ROOM, 7, GROVE_BLOCK_A, 5, -1, new LatLng(51.588548, -0.230800), new String[]{"F", "G"}));
        list.add(new Routing("GB17B", ROOM, 3, GROVE_BLOCK_A, 5, -1, new LatLng(51.588591, -0.230645), new String[]{"C", "D"}));
        list.add(new Routing("GB19A", ROOM, 3, GROVE_BLOCK_A, 5, -1, new LatLng(51.588628, -0.230627), new String[]{"C", "D"}));
        list.add(new Routing("GB19B", ROOM, 3, GROVE_BLOCK_A, 5, -1, new LatLng(51.588660, -0.230613), new String[]{"C", "D"}));
        list.add(new Routing("GB21", ROOM, 2, GROVE_BLOCK_A, 5, -1, new LatLng(51.588721, -0.230703), new String[]{"C", "B"}));
        list.add(new Routing("GB22", ROOM, 1, GROVE_BLOCK_A, 5, -1, new LatLng(51.588729, -0.230639), new String[]{"A", "B"}));
        list.add(new Routing("GB23", ROOM, 1, GROVE_BLOCK_A, 5, -1, new LatLng(51.588770, -0.230635), new String[]{"A", "B"}));


        //GROVE BLOCK A GROUND FLOOR
        list.add(new Routing("GG01", ROOM, 4, GROVE_BLOCK_A, 4, 0, new LatLng(51.588695, -0.230596), new String[]{"C", "D", "E"}));
        list.add(new Routing("GG04", ROOM, 4, GROVE_BLOCK_A, 4, 0, new LatLng(51.588637, -0.230321), new String[]{"C", "D", "E"}));
        list.add(new Routing("GG07", ROOM, 8, GROVE_BLOCK_A, 4, 0, new LatLng(51.588598, -0.230581), new String[]{"H"}));
        list.add(new Routing("GG11", ROOM, 1, GROVE_BLOCK_A, 4, 0, new LatLng(51.588549, -0.230636), new String[]{"A", "B", "C", "H", "G"}));
        list.add(new Routing("GG12", ROOM, 1, GROVE_BLOCK_A, 4, 0, new LatLng(51.588549, -0.230636), new String[]{"A", "B", "C", "H", "G"}));
        list.add(new Routing("GG13", ROOM, 1, GROVE_BLOCK_A, 4, 0, new LatLng(51.588549, -0.230636), new String[]{"A", "B", "C", "H", "G"}));
        list.add(new Routing("GG14", ROOM, 9, GROVE_BLOCK_A, 4, 0, new LatLng(51.588471, -0.230401), new String[]{"G"}));
        list.add(new Routing("GG15", ROOM, 9, GROVE_BLOCK_A, 4, 0, new LatLng(51.588471, -0.230401), new String[]{"G"}));
        list.add(new Routing("GG17", ROOM, 9, GROVE_BLOCK_A, 4, 0, new LatLng(51.588489, -0.230567), new String[]{"G"}));
        list.add(new Routing("GG18", ROOM, 9, GROVE_BLOCK_A, 4, 0, new LatLng(51.588489, -0.230567), new String[]{"G"}));
        list.add(new Routing("GG20", ROOM, 9, GROVE_BLOCK_A, 4, 0, new LatLng(51.588489, -0.230567), new String[]{"G"}));
        list.add(new Routing("GG21", ROOM, 9, GROVE_BLOCK_A, 4, 0, new LatLng(51.588489, -0.230567), new String[]{"G"}));
        list.add(new Routing("GG22", ROOM, 10, GROVE_BLOCK_A, 4, 0, new LatLng(51.588487, -0.230657), new String[]{"G"}));
        list.add(new Routing("GG23", ROOM, 10, GROVE_BLOCK_A, 4, 0, new LatLng(51.588487, -0.230657), new String[]{"G"}));
        list.add(new Routing("GG25", ROOM, 7, GROVE_BLOCK_A, 4, 0, new LatLng(51.588536, -0.230798), new String[]{"G", "F"}));
        list.add(new Routing("GG26", ROOM, 7, GROVE_BLOCK_A, 4, 0, new LatLng(51.588536, -0.230798), new String[]{"G", "F"}));
        list.add(new Routing("GG29", ROOM, 7, GROVE_BLOCK_A, 4, 0, new LatLng(51.588536, -0.230798), new String[]{"G", "F"}));
        list.add(new Routing("GG30", ROOM, 7, GROVE_BLOCK_A, 4, 0, new LatLng(51.588536, -0.230798), new String[]{"G", "F"}));
        list.add(new Routing("GG31", ROOM, 7, GROVE_BLOCK_A, 4, 0, new LatLng(51.588589, -0.231001), new String[]{"G", "F"}));
        list.add(new Routing("GG33", ROOM, 6, GROVE_BLOCK_A, 4, 0, new LatLng(51.588610, -0.230999), new String[]{"E", "F"}));
        list.add(new Routing("GG34", ROOM, 6, GROVE_BLOCK_A, 4, 0, new LatLng(51.588643, -0.230975), new String[]{"E", "F"}));
        list.add(new Routing("GG35", ROOM, 6, GROVE_BLOCK_A, 4, 0, new LatLng(51.588683, -0.230950), new String[]{"E", "F"}));
        list.add(new Routing("GG36", ROOM, 6, GROVE_BLOCK_A, 4, 0, new LatLng(51.588735, -0.230904), new String[]{"E", "F"}));
        list.add(new Routing("GG37", ROOM, 5, GROVE_BLOCK_A, 4, 0, new LatLng(51.588728, -0.230820), new String[]{"E", "D", "C"}));
        list.add(new Routing("GG38", ROOM, 11, GROVE_BLOCK_A, 4, 0, new LatLng(51.588758, -0.230773), new String[]{"D"}));
        list.add(new Routing("GG39", ROOM, 11, GROVE_BLOCK_A, 4, 0, new LatLng(51.588758, -0.230773), new String[]{"D"}));
        list.add(new Routing("GG41", ROOM, 11, GROVE_BLOCK_A, 4, 0, new LatLng(51.588685, -0.230764), new String[]{"D"}));
        list.add(new Routing("GG44", ROOM, 11, GROVE_BLOCK_A, 4, 0, new LatLng(51.588662, -0.230768), new String[]{"D"}));
        list.add(new Routing("GG45", ROOM, 11, GROVE_BLOCK_A, 4, 0, new LatLng(51.588685, -0.230764), new String[]{"D"}));
        list.add(new Routing("GG46", ROOM, 5, GROVE_BLOCK_A, 4, 0, new LatLng(51.588732, -0.230880), new String[]{"E", "D", "C"}));
        list.add(new Routing("GG47", ROOM, 5, GROVE_BLOCK_A, 4, 0, new LatLng(51.588713, -0.230706), new String[]{"E", "D", "C"}));
        list.add(new Routing("GG48", ROOM, 5, GROVE_BLOCK_A, 4, 0, new LatLng(51.588707, -0.230670), new String[]{"E", "D", "C"}));


        //GROVE BLOCK A FIRST FLOOR
        list.add(new Routing("Grove Atrium", ROOM, 11, GROVE_BLOCK_A, 3, 1, new LatLng(51.588581, -0.230482), new String[]{"J", "M", "P"}));
        list.add(new Routing("Grove Coffee Pod", ROOM, 11, GROVE_BLOCK_A, 3, 1, new LatLng(51.588590, -0.230527), new String[]{"J", "M", "P"}));
        list.add(new Routing("G101", ROOM, 13, GROVE_BLOCK_A, 3, 1, new LatLng(51.588690, -0.230571), new String[]{"L", "M", "N"}));
        list.add(new Routing("G104A", ROOM, 13, GROVE_BLOCK_A, 3, 1, new LatLng(51.588669, -0.230459), new String[]{"L", "M", "N"}));
        list.add(new Routing("G104B", ROOM, 13, GROVE_BLOCK_A, 3, 1, new LatLng(51.588669, -0.230459), new String[]{"L", "M", "N"}));
        list.add(new Routing("G105", ROOM, 13, GROVE_BLOCK_A, 3, 1, new LatLng(51.588633, -0.230316), new String[]{"L", "M", "N"}));
        list.add(new Routing("G107", ROOM, 12, GROVE_BLOCK_A, 3, 1, new LatLng(51.588441, -0.230401), new String[]{"L", "K"}));
        list.add(new Routing("G110", ROOM, 12, GROVE_BLOCK_A, 3, 1, new LatLng(51.588418, -0.230410), new String[]{"L", "K"}));
        list.add(new Routing("G115", ROOM, 11, GROVE_BLOCK_A, 3, 1, new LatLng(51.588458, -0.230544), new String[]{"J", "M", "P"}));
        list.add(new Routing("G116", ROOM, 11, GROVE_BLOCK_A, 3, 1, new LatLng(51.588458, -0.230544), new String[]{"J", "M", "P"}));
        list.add(new Routing("G117", ROOM, 9, GROVE_BLOCK_A, 3, 1, new LatLng(51.588490, -0.230643), new String[]{"I", "O", "N"}));
        list.add(new Routing("G118", ROOM, 9, GROVE_BLOCK_A, 3, 1, new LatLng(51.588490, -0.230643), new String[]{"I", "O", "N"}));
        list.add(new Routing("G120", ROOM, 8, GROVE_BLOCK_A, 3, 1, new LatLng(51.588473, -0.230791), new String[]{"G", "H"}));
        list.add(new Routing("G121", ROOM, 8, GROVE_BLOCK_A, 3, 1, new LatLng(51.588457, -0.230804), new String[]{"G", "H"}));
        list.add(new Routing("G122", ROOM, 8, GROVE_BLOCK_A, 3, 1, new LatLng(51.588457, -0.230804), new String[]{"G", "H"}));
        list.add(new Routing("G122A", ROOM, 8, GROVE_BLOCK_A, 3, 1, new LatLng(51.588457, -0.230804), new String[]{"G", "H"}));
        list.add(new Routing("G123A", ROOM, 8, GROVE_BLOCK_A, 3, 1, new LatLng(51.588457, -0.230804), new String[]{"G", "H"}));
        list.add(new Routing("G123B", ROOM, 5, GROVE_BLOCK_A, 3, 1, new LatLng(51.588517, -0.231047), new String[]{"E", "F"}));
        list.add(new Routing("G124", ROOM, 7, GROVE_BLOCK_A, 3, 1, new LatLng(51.588493, -0.230826), new String[]{"G", "F"}));
        list.add(new Routing("G125", ROOM, 7, GROVE_BLOCK_A, 3, 1, new LatLng(51.588493, -0.230826), new String[]{"G", "F"}));
        list.add(new Routing("G126", ROOM, 7, GROVE_BLOCK_A, 3, 1, new LatLng(51.588504, -0.230859), new String[]{"G", "F"}));
        list.add(new Routing("G127", ROOM, 7, GROVE_BLOCK_A, 3, 1, new LatLng(51.588504, -0.230859), new String[]{"G", "F"}));
        list.add(new Routing("G128", ROOM, 7, GROVE_BLOCK_A, 3, 1, new LatLng(51.588513, -0.230900), new String[]{"G", "F"}));
        list.add(new Routing("G129", ROOM, 7, GROVE_BLOCK_A, 3, 1, new LatLng(51.588513, -0.230900), new String[]{"G", "F"}));
        list.add(new Routing("G130", ROOM, 7, GROVE_BLOCK_A, 3, 1, new LatLng(51.588523, -0.230941), new String[]{"G", "F"}));
        list.add(new Routing("G131", ROOM, 7, GROVE_BLOCK_A, 3, 1, new LatLng(51.588513, -0.230900), new String[]{"G", "F"}));
        list.add(new Routing("G132", ROOM, 7, GROVE_BLOCK_A, 3, 1, new LatLng(51.588533, -0.230975), new String[]{"G", "F"}));
        list.add(new Routing("G133", ROOM, 7, GROVE_BLOCK_A, 3, 1, new LatLng(51.588523, -0.230941), new String[]{"G", "F"}));
        list.add(new Routing("G134", ROOM, 7, GROVE_BLOCK_A, 3, 1, new LatLng(51.588542, -0.231003), new String[]{"G", "F"}));
        list.add(new Routing("G135", ROOM, 7, GROVE_BLOCK_A, 3, 1, new LatLng(51.588533, -0.230975), new String[]{"G", "F"}));
        list.add(new Routing("G136", ROOM, 7, GROVE_BLOCK_A, 3, 1, new LatLng(51.588542, -0.231003), new String[]{"G", "F"}));
        list.add(new Routing("G137", ROOM, 5, GROVE_BLOCK_A, 3, 1, new LatLng(51.588517, -0.231047), new String[]{"E", "F"}));
        list.add(new Routing("G137A", ROOM, 5, GROVE_BLOCK_A, 3, 1, new LatLng(51.588517, -0.231047), new String[]{"E", "F"}));
        list.add(new Routing("G140", ROOM, 4, GROVE_BLOCK_A, 3, 1, new LatLng(51.588633, -0.230982), new String[]{"E", "D"}));
        list.add(new Routing("G141", ROOM, 4, GROVE_BLOCK_A, 3, 1, new LatLng(51.588651, -0.230971), new String[]{"E", "D"}));
        list.add(new Routing("G146", ROOM, 3, GROVE_BLOCK_A, 3, 1, new LatLng(51.588703, -0.230762), new String[]{"C"}));
        list.add(new Routing("G146A", ROOM, 3, GROVE_BLOCK_A, 3, 1, new LatLng(51.588703, -0.230762), new String[]{"C"}));
        list.add(new Routing("G147", ROOM, 3, GROVE_BLOCK_A, 3, 1, new LatLng(51.588669, -0.230758), new String[]{"C"}));
        list.add(new Routing("G148", ROOM, 3, GROVE_BLOCK_A, 3, 1, new LatLng(51.588669, -0.230758), new String[]{"C"}));
        list.add(new Routing("G149", ROOM, 8, GROVE_BLOCK_A, 3, 1, new LatLng(51.588580, -0.230755), new String[]{"G", "H"}));


        //GROVE BLOCK A SECOND FLOOR
        list.add(new Routing("G201", ROOM, 11, GROVE_BLOCK_A, 2, 2, new LatLng(51.588666, -0.230452), new String[]{"M", "N", "B"}));
        list.add(new Routing("G203", ROOM, 11, GROVE_BLOCK_A, 2, 2, new LatLng(51.588634, -0.230327), new String[]{"M", "N", "B"}));
        list.add(new Routing("G204", ROOM, 11, GROVE_BLOCK_A, 2, 2, new LatLng(51.588634, -0.230327), new String[]{"M", "N", "B"}));
        list.add(new Routing("G205", ROOM, 11, GROVE_BLOCK_A, 2, 2, new LatLng(51.588634, -0.230327), new String[]{"M", "N", "B"}));
        list.add(new Routing("G206", ROOM, 9, GROVE_BLOCK_A, 2, 2, new LatLng(51.588442, -0.230414), new String[]{"L", "M"}));
        list.add(new Routing("G207", ROOM, 9, GROVE_BLOCK_A, 2, 2, new LatLng(51.588442, -0.230414), new String[]{"L", "M"}));
        list.add(new Routing("G209", ROOM, 8, GROVE_BLOCK_A, 2, 2, new LatLng(51.588460, -0.230557), new String[]{"N", "K"}));
        list.add(new Routing("G210", ROOM, 8, GROVE_BLOCK_A, 2, 2, new LatLng(51.588460, -0.230557), new String[]{"N", "K"}));
        list.add(new Routing("G211", ROOM, 7, GROVE_BLOCK_A, 2, 2, new LatLng(51.588490, -0.230643), new String[]{"J", "P", "C"}));
        list.add(new Routing("G212", ROOM, 7, GROVE_BLOCK_A, 2, 2, new LatLng(51.588490, -0.230643), new String[]{"J", "P", "C"}));
        list.add(new Routing("G214A", ROOM, 6, GROVE_BLOCK_A, 2, 2, new LatLng(51.588530, -0.230766), new String[]{"J", "I", "H", "G"}));
        list.add(new Routing("G214B", ROOM, 6, GROVE_BLOCK_A, 2, 2, new LatLng(51.588566, -0.230908), new String[]{"J", "I", "H", "G"}));
        list.add(new Routing("G214C", ROOM, 6, GROVE_BLOCK_A, 2, 2, new LatLng(51.588593, -0.231005), new String[]{"J", "I", "H", "G"}));
        list.add(new Routing("G230", ROOM, 5, GROVE_BLOCK_A, 2, 2, new LatLng(51.588620, -0.230779), new String[]{"D", "I"}));
        list.add(new Routing("G231", ROOM, 2, GROVE_BLOCK_A, 2, 2, new LatLng(51.588709, -0.230674), new String[]{"C", "D", "E", "F"}));
        list.add(new Routing("G234", ROOM, 1, GROVE_BLOCK_A, 2, 2, new LatLng(51.588705, -0.230544), new String[]{"A", "B", "O"}));
        list.add(new Routing("G235", ROOM, 1, GROVE_BLOCK_A, 2, 2, new LatLng(51.588705, -0.230544), new String[]{"A", "B", "O"}));

        //GROVE BLOCK A THIRD FLOOR
        list.add(new Routing("G301", ROOM, 2, GROVE_BLOCK_A, 1, 3, new LatLng(51.588666, -0.230452), new String[]{"A"}));
        list.add(new Routing("G304", ROOM, 2, GROVE_BLOCK_A, 1, 3, new LatLng(51.588693, -0.230595), new String[]{"A"}));
        list.add(new Routing("G305", ROOM, 2, GROVE_BLOCK_A, 1, 3, new LatLng(51.588693, -0.230595), new String[]{"A"}));
        list.add(new Routing("G307", ROOM, 2, GROVE_BLOCK_A, 1, 3, new LatLng(51.588708, -0.230660), new String[]{"A"}));
        list.add(new Routing("G308", ROOM, 2, GROVE_BLOCK_A, 1, 3, new LatLng(51.588714, -0.230711), new String[]{"A"}));
        list.add(new Routing("G309", ROOM, 1, GROVE_BLOCK_A, 1, 3, new LatLng(51.588755, -0.230633), new String[]{"A", "B"}));

        //GROVE BLOCK A FOURTH FLOOR
        list.add(new Routing("G401", ROOM, 1, GROVE_BLOCK_A, 0, 4, new LatLng(51.588755, -0.230633), new String[]{"A"}));
        list.add(new Routing("G404", ROOM, 1, GROVE_BLOCK_A, 0, 4, new LatLng(51.588740, -0.230650), new String[]{"A"}));
        list.add(new Routing("G405", ROOM, 1, GROVE_BLOCK_A, 0, 4, new LatLng(51.588717, -0.230658), new String[]{"A"}));
        list.add(new Routing("G406A", ROOM, 1, GROVE_BLOCK_A, 0, 4, new LatLng(51.588716, -0.230648), new String[]{"A"}));

        //GROVE BLOCK B GROUND FLOOR
        list.add(new Routing("GG71", ROOM, 1, GROVE_BLOCK_B, 2, 0, new LatLng(51.588949, -0.230396), new String[]{"A", "B"}));
        list.add(new Routing("GG72", ROOM, 2, GROVE_BLOCK_B, 2, 0, new LatLng(51.588965, -0.230216), new String[]{"B"}));
        list.add(new Routing("GG73", ROOM, 2, GROVE_BLOCK_B, 2, 0, new LatLng(51.588965, -0.230216), new String[]{"B"}));
        list.add(new Routing("GG74", ROOM, 2, GROVE_BLOCK_B, 2, 0, new LatLng(51.588993, -0.230200), new String[]{"B"}));
        list.add(new Routing("GG75", ROOM, 2, GROVE_BLOCK_B, 2, 0, new LatLng(51.588993, -0.230200), new String[]{"B"}));
        list.add(new Routing("GG76", ROOM, 2, GROVE_BLOCK_B, 2, 0, new LatLng(51.588993, -0.230200), new String[]{"B"}));
        list.add(new Routing("GG77", ROOM, 2, GROVE_BLOCK_B, 2, 0, new LatLng(51.588965, -0.230216), new String[]{"B"}));
        list.add(new Routing("GG78", ROOM, 1, GROVE_BLOCK_B, 2, 0, new LatLng(51.588900, -0.230214), new String[]{"A", "B"}));
        list.add(new Routing("GG79", ROOM, 1, GROVE_BLOCK_B, 2, 0, new LatLng(51.588897, -0.230192), new String[]{"A", "B"}));

        //GROVE BLOCK B FIRST FLOOR
        list.add(new Routing("G170", ROOM, 2, GROVE_BLOCK_B, 1, 1, new LatLng(51.588927, -0.230365), new String[]{"A", "B"}));
        list.add(new Routing("G171", ROOM, 2, GROVE_BLOCK_B, 1, 1, new LatLng(51.588925, -0.230347), new String[]{"A", "B"}));
        list.add(new Routing("G172", ROOM, 2, GROVE_BLOCK_B, 1, 1, new LatLng(51.588905, -0.230247), new String[]{"A", "B"}));
        list.add(new Routing("G174", ROOM, 2, GROVE_BLOCK_B, 1, 1, new LatLng(51.588896, -0.230199), new String[]{"A", "B"}));
        list.add(new Routing("G175", ROOM, 2, GROVE_BLOCK_B, 1, 1, new LatLng(51.588890, -0.230162), new String[]{"A", "B"}));
        list.add(new Routing("G176", ROOM, 3, GROVE_BLOCK_B, 1, 1, new LatLng(51.588940, -0.230120), new String[]{"C", "B"}));
        list.add(new Routing("G177", ROOM, 3, GROVE_BLOCK_B, 1, 1, new LatLng(51.588960, -0.230111), new String[]{"C", "B"}));
        list.add(new Routing("G178", ROOM, 3, GROVE_BLOCK_B, 1, 1, new LatLng(51.588960, -0.230111), new String[]{"C", "B"}));
        list.add(new Routing("G179", ROOM, 4, GROVE_BLOCK_B, 1, 1, new LatLng(51.588952, -0.230076), new String[]{"C"}));
        list.add(new Routing("G180", ROOM, 4, GROVE_BLOCK_B, 1, 1, new LatLng(51.588936, -0.230044), new String[]{"C"}));
        list.add(new Routing("G181", ROOM, 3, GROVE_BLOCK_B, 1, 1, new LatLng(51.588923, -0.230113), new String[]{"C", "B"}));
        list.add(new Routing("G182", ROOM, 3, GROVE_BLOCK_B, 1, 1, new LatLng(51.588905, -0.230121), new String[]{"C", "B"}));

        //GROVE BLOCK B SECOND FLOOR
        list.add(new Routing("G270", ROOM, 2, GROVE_BLOCK_B, 0, 2, new LatLng(51.588932, -0.230382), new String[]{"A", "B"}));
        list.add(new Routing("G271", ROOM, 2, GROVE_BLOCK_B, 0, 2, new LatLng(51.588925, -0.230348), new String[]{"A", "B"}));
        list.add(new Routing("G272", ROOM, 2, GROVE_BLOCK_B, 0, 2, new LatLng(51.588906, -0.230249), new String[]{"A", "B"}));
        list.add(new Routing("G274", ROOM, 3, GROVE_BLOCK_B, 0, 2, new LatLng(51.588917, -0.230128), new String[]{"B"}));

        //GROVE BLOCK C GROUND FLOOR
        list.add(new Routing("GG90", ROOM, 3, GROVE_BLOCK_C, 2, 0, new LatLng(51.589272, -0.230063), new String[]{"C", "B", "D"}));
        list.add(new Routing("GG92", ROOM, 3, GROVE_BLOCK_C, 2, 0, new LatLng(51.589265, -0.229971), new String[]{"C", "B", "D"}));
        list.add(new Routing("GG93", ROOM, 4, GROVE_BLOCK_C, 2, 0, new LatLng(51.589237, -0.230050), new String[]{"B"}));
        list.add(new Routing("GG94", ROOM, 4, GROVE_BLOCK_C, 2, 0, new LatLng(51.589237, -0.230050), new String[]{"B"}));
        list.add(new Routing("GG96", ROOM, 1, GROVE_BLOCK_C, 2, 0, new LatLng(51.589178, -0.230249), new String[]{"A"}));
        list.add(new Routing("GG97", ROOM, 2, GROVE_BLOCK_C, 2, 0, new LatLng(51.589194, -0.230243), new String[]{"A", "E", "C"}));
        list.add(new Routing("GG98", ROOM, 2, GROVE_BLOCK_C, 2, 0, new LatLng(51.589205, -0.230231), new String[]{"A", "E", "C"}));

        //GROVE BLOCK C FIRST FLOOR
        list.add(new Routing("G190", ROOM, 3, GROVE_BLOCK_C, 1, 1, new LatLng(51.589256, -0.230171), new String[]{"B"}));
        list.add(new Routing("G191", ROOM, 2, GROVE_BLOCK_C, 1, 1, new LatLng(51.589210, -0.230185), new String[]{"A", "B"}));
        list.add(new Routing("G192", ROOM, 3, GROVE_BLOCK_C, 1, 1, new LatLng(51.589256, -0.230171), new String[]{"B"}));

        //PORTACABIN A GROUND FLOOR
        list.add(new Routing("Portakabin A", ROOM, 2, PORTAKABIN_A, 1, 0, new LatLng(51.589736, -0.230232), new String[]{"A"}));
        list.add(new Routing("PAG03", ROOM, 2, PORTAKABIN_A, 1, 0, new LatLng(51.589736, -0.230232), new String[]{"A"}));
        list.add(new Routing("PAG04", ROOM, 2, PORTAKABIN_A, 1, 0, new LatLng(51.589736, -0.230232), new String[]{"A"}));
        list.add(new Routing("PAG07", ROOM, 2, PORTAKABIN_A, 1, 0, new LatLng(51.589711, -0.230239), new String[]{"A"}));
        list.add(new Routing("PAG08", ROOM, 2, PORTAKABIN_A, 1, 0, new LatLng(51.589711, -0.230239), new String[]{"A"}));

        //PORTACABIN A FIRST FLOOR
        list.add(new Routing("PA101", ROOM, 2, PORTAKABIN_A, 0, 1, new LatLng(51.589736, -0.230232), new String[]{"A"}));
        list.add(new Routing("PA102", ROOM, 2, PORTAKABIN_A, 0, 1, new LatLng(51.589736, -0.230232), new String[]{"A"}));
        list.add(new Routing("PA103", ROOM, 2, PORTAKABIN_A, 0, 1, new LatLng(51.589711, -0.230239), new String[]{"A"}));
        list.add(new Routing("PA104", ROOM, 2, PORTAKABIN_A, 0, 1, new LatLng(51.589711, -0.230239), new String[]{"A"}));

        //PORTACABIN A EXT GROUND FLOOR
        list.add(new Routing("PAG01", ROOM, 1, PORTAKABIN_A_EXT, 1, 0, new LatLng(51.589881, -0.230205), new String[]{"A"}));
        list.add(new Routing("PAG02", ROOM, 1, PORTAKABIN_A_EXT, 1, 0, new LatLng(51.589857, -0.230211), new String[]{"A"}));
        list.add(new Routing("PAG05", ROOM, 1, PORTAKABIN_A_EXT, 1, 0, new LatLng(51.589866, -0.230170), new String[]{"A"}));
        list.add(new Routing("PAG06", ROOM, 1, PORTAKABIN_A_EXT, 1, 0, new LatLng(51.589866, -0.230170), new String[]{"A"}));

        //PORTACABIN 6 & 7

        list.add(new Routing("Portakabin 6 & 7", ROOM, 3, PORTAKABIN_6_7, 1, 0, new LatLng(51.589699, -0.229967), new String[]{"B"}));
        list.add(new Routing("P6G01", ROOM, 3, PORTAKABIN_6_7, 1, 0, new LatLng(51.589699, -0.229967), new String[]{"B"}));
        list.add(new Routing("P6G02", ROOM, 2, PORTAKABIN_6_7, 1, 0, new LatLng(51.589686, -0.229844), new String[]{"A"}));
        list.add(new Routing("P7101", ROOM, 2, PORTAKABIN_6_7, 1, 0, new LatLng(51.589686, -0.229844), new String[]{"A"}));
        list.add(new Routing("P7102", ROOM, 3, PORTAKABIN_6_7, 1, 0, new LatLng(51.589699, -0.229967), new String[]{"B"}));

        //PORTACABIN B
        list.add(new Routing("Portakabin B", ROOM, 1, PORTAKABIN_B, 0, 0, new LatLng(51.590717, -0.229092), new String[]{"A"}));
        list.add(new Routing("PBG01", ROOM, 1, PORTAKABIN_B, 0, 0, new LatLng(51.590717, -0.229092), new String[]{"A"}));
        list.add(new Routing("PBG02", ROOM, 1, PORTAKABIN_B, 0, 0, new LatLng(51.590717, -0.229092), new String[]{"A"}));

        //BARN
        list.add(new Routing("Barn", ROOM, 1, BARN, 0, 0, new LatLng(51.590952, -0.228580), new String[]{"A"}));
        list.add(new Routing("BAG01", ROOM, 1, BARN, 0, 0, new LatLng(51.590952, -0.228580), new String[]{"A"}));
        list.add(new Routing("BAG02", ROOM, 3, BARN, 0, 0, new LatLng(51.591056, -0.228586), new String[]{"B"}));
        return list;
    }

    private static Routing getBuilding(Building building) {
        ArrayList<Routing> list = new ArrayList<>();
        list.add(new Routing(COLLEGE, true, true, true));
        list.add(new Routing(HATCHCROFT, true, true, true));
        list.add(new Routing(WILLIAMS, true, true, true));
        list.add(new Routing(SHEPPARDLIBRARY, true, true, true));
        list.add(new Routing(VINE, true, false, true));
        list.add(new Routing(BARN, false, false, true));
        list.add(new Routing(GROVE_BLOCK_A, true, true, true));
        list.add(new Routing(GROVE_BLOCK_B, true, true, true));
        list.add(new Routing(GROVE_BLOCK_C, true, true, true));
        list.add(new Routing(BUILDING9, false, false, true));
        list.add(new Routing(MDXHOUSE, true, true, true));
        list.add(new Routing(PORTAKABIN_A, true, false, true));
        list.add(new Routing(PORTAKABIN_A_EXT, false, false, true));
        list.add(new Routing(PORTAKABIN_6_7, true, false, false));
        list.add(new Routing(PORTAKABIN_B, false, false, true));
        list.add(new Routing(BUILDING10, true, false, false));
        list.add(new Routing(CIRCLE_CAFE, false, false, true));

        return list.parallelStream()
                .filter(buildingObject -> buildingObject.getBuilding() == building)
                .findFirst().get();
    }

    public static int getGmapIntForGroundFloor(Building building) {
        return RoutingObjectsGetterUtilService.getConnectors(building, 0).get(0).gMapLevel;
    }


    public boolean checkIfCurrentAlphaIsInSameLaneAsDestination(int destination) {
        return Arrays.stream(primeLanes).anyMatch(primeLane -> primeLane == destination);
    }

    @Override
    public int compareTo(Routing o) {
        return name.compareTo(o.name);
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == null || !(obj instanceof Routing)) return false;
        if (obj == this) return true;

        Routing that = (Routing) obj;
        return name.equals(that.getName()) && latLng.latitude == that.latLng.latitude && latLng.longitude == that.latLng.longitude;
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, latLng.latitude, latLng.longitude);
    }

    @Override
    public String toString() {
        return name;
    }
}
