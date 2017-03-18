package org.backend.mdxmaps.Model.Solr;

import org.backend.mdxmaps.Model.LatLng;

import java.util.ArrayList;

import static org.backend.mdxmaps.Model.RoutingObjects.BARN;
import static org.backend.mdxmaps.Model.RoutingObjects.BUILDING10;
import static org.backend.mdxmaps.Model.RoutingObjects.BUILDING9;
import static org.backend.mdxmaps.Model.RoutingObjects.CIRCLE_CAFE;
import static org.backend.mdxmaps.Model.RoutingObjects.COLLEGE;
import static org.backend.mdxmaps.Model.RoutingObjects.GROVE_BLOCK_A;
import static org.backend.mdxmaps.Model.RoutingObjects.GROVE_BLOCK_B;
import static org.backend.mdxmaps.Model.RoutingObjects.GROVE_BLOCK_C;
import static org.backend.mdxmaps.Model.RoutingObjects.HATCHCROFT;
import static org.backend.mdxmaps.Model.RoutingObjects.MDXHOUSE;
import static org.backend.mdxmaps.Model.RoutingObjects.PORTAKABIN_2;
import static org.backend.mdxmaps.Model.RoutingObjects.PORTAKABIN_6_7;
import static org.backend.mdxmaps.Model.RoutingObjects.PORTAKABIN_8;
import static org.backend.mdxmaps.Model.RoutingObjects.PORTAKABIN_A;
import static org.backend.mdxmaps.Model.RoutingObjects.PORTAKABIN_B;
import static org.backend.mdxmaps.Model.RoutingObjects.RAVENSFIELDS;
import static org.backend.mdxmaps.Model.RoutingObjects.SHEPPARDLIBRARY;
import static org.backend.mdxmaps.Model.RoutingObjects.TOWNHALL;
import static org.backend.mdxmaps.Model.RoutingObjects.VINE;
import static org.backend.mdxmaps.Model.RoutingObjects.WILLIAMS;

/**
 * Created by Emmanuel Keboh on 11/03/2017.
 */
public class SolrRooms {

    private String roomNumber;
    private LatLng latLng;
    private int level;
    private String buildingName;
    private String description;
    private boolean directionsAvailable;


    public SolrRooms(String roomNumber, boolean directionsAvailable, LatLng latLng, int level, String buildingName, String description) {
        this.roomNumber = roomNumber;
        this.directionsAvailable = directionsAvailable;
        this.latLng = latLng;
        this.level = level;
        this.buildingName = buildingName;
        this.description = description;
    }

    public String getRoomNumber() {
        return roomNumber;
    }

    public LatLng getLatLng() {
        return latLng;
    }

    public int getLevel() {
        return level;
    }

    public String getBuildingName() {
        return buildingName;
    }

    public String getDescription() {
        return description;
    }

    public boolean isDirectionsAvailable() {
        return directionsAvailable;
    }

    public static ArrayList<SolrRooms> getRoomsForSolr() {
        ArrayList<SolrRooms> list = new ArrayList<>();

        /*College Building ground floor*/

        list.add(new SolrRooms("College Building", true, new LatLng(51.589825, -0.2288390), 2, COLLEGE, "College Building"));
        list.add(new SolrRooms("Quad", true, new LatLng(51.589825, -0.228839), 2, COLLEGE, "Quad"));
        list.add(new SolrRooms("CG03", true, new LatLng(51.589537, -0.228467), 2, COLLEGE, "Seminar Room"));
        list.add(new SolrRooms("CG04", true, new LatLng(51.589553, -0.228542), 2, COLLEGE, "Seminar Room"));
        list.add(new SolrRooms("CG06", true, new LatLng(51.589565, -0.228646), 2, COLLEGE, "Seminar Room"));
        list.add(new SolrRooms("CG07", true, new LatLng(51.589579, -0.228760), 2, COLLEGE, "Employability And Careers Offices"));
        list.add(new SolrRooms("CG08", true, new LatLng(51.589586, -0.228841), 2, COLLEGE, "Seminar Room"));
        list.add(new SolrRooms("CG09", true, new LatLng(51.589587, -0.228912), 2, COLLEGE, "Seminar Room"));
        list.add(new SolrRooms("CG10", true, new LatLng(51.589602, -0.228994), 2, COLLEGE, "Seminar Room"));
        list.add(new SolrRooms("CG11", true, new LatLng(51.589609, -0.229090), 2, COLLEGE, "Seminar Room"));
        list.add(new SolrRooms("CG12A", true, new LatLng(51.589629, -0.229230), 2, COLLEGE, "Seminar Room"));
        list.add(new SolrRooms("CG12B", true, new LatLng(51.589641, -0.229313), 2, COLLEGE, "Seminar Room"));
        list.add(new SolrRooms("CG13", true, new LatLng(51.589655, -0.229430), 2, COLLEGE, "Seminar Room"));
        list.add(new SolrRooms("CG14", true, new LatLng(51.589667, -0.229564), 2, COLLEGE, "Seminar Room"));
        list.add(new SolrRooms("CG15", true, new LatLng(51.589720, -0.229247), 2, COLLEGE, "Classroom / Seminar Room"));
        list.add(new SolrRooms("CG16", true, new LatLng(51.589747, -0.229381), 2, COLLEGE, "Classroom / Seminar Room"));
        list.add(new SolrRooms("CG30", true, new LatLng(51.589744, -0.229049), 2, COLLEGE, "Female Toilet 1 (College, Ground Floor)"));
        list.add(new SolrRooms("CG36", true, new LatLng(51.589959, -0.228987), 2, COLLEGE, "Male Toilet 1 (College, Ground Floor)"));
        list.add(new SolrRooms("CG37", true, new LatLng(51.589917, -0.229037), 2, COLLEGE, "Disabled Toilet 1 (College, Ground Floor)"));
        list.add(new SolrRooms("CG41", true, new LatLng(51.590110, -0.229381), 2, COLLEGE, "Lecture Theatre"));
        list.add(new SolrRooms("CG43", true, new LatLng(51.590093, -0.229185), 2, COLLEGE, "Classroom"));
        list.add(new SolrRooms("CG45", true, new LatLng(51.590079, -0.229100), 2, COLLEGE, "Classroom"));
        list.add(new SolrRooms("CG47", true, new LatLng(51.590051, -0.228848), 2, COLLEGE, "Classroom"));
        list.add(new SolrRooms("CG48", true, new LatLng(51.590032, -0.228668), 2, COLLEGE, "Classroom"));
        list.add(new SolrRooms("CG49", true, new LatLng(51.590021, -0.228557), 2, COLLEGE, "Micro-Lab"));
        list.add(new SolrRooms("CG51", true, new LatLng(51.590002, -0.228381), 2, COLLEGE, "Classroom"));
        list.add(new SolrRooms("CG57", false, new LatLng(51.589920, -0.228389), 2, COLLEGE, "Security Reception"));
        list.add(new SolrRooms("CG58", false, new LatLng(51.589860, -0.228261), 2, COLLEGE, "First Aid Room"));
        list.add(new SolrRooms("CG60", true, new LatLng(51.589907, -0.229541), 2, COLLEGE, "Dance Room"));
        list.add(new SolrRooms("CG62", true, new LatLng(51.589977, -0.229384), 2, COLLEGE, "Classroom"));
        list.add(new SolrRooms("CG62 (Disabled Access)", true, new LatLng(51.589977, -0.229384), 2, COLLEGE, "Classroom"));
        list.add(new SolrRooms("CG76", true, new LatLng(51.589946, -0.229180), 2, COLLEGE, "Lecture Theatre"));
        list.add(new SolrRooms("CG77", true, new LatLng(51.589827, -0.229210), 2, COLLEGE, "Lecture Theatre"));
        list.add(new SolrRooms("CG82", false, new LatLng(51.589949, -0.227878), 2, COLLEGE, "Classroom"));
        list.add(new SolrRooms("CG83", false, new LatLng(51.589957, -0.228055), 2, COLLEGE, "Meeting Room"));
        list.add(new SolrRooms("CG84", false, new LatLng(51.590020, -0.227944), 2, COLLEGE, "Meeting Room"));
        list.add(new SolrRooms("CG81A", false, new LatLng(51.590006, -0.227898), 2, COLLEGE, "Male Toilet 2 (College, Ground Floor)"));
        list.add(new SolrRooms("CG81B", false, new LatLng(51.589993, -0.227875), 2, COLLEGE, "Female Toilet 2 (College, Ground Floor)"));
        list.add(new SolrRooms("Costa Quad", true, new LatLng(51.589755, -0.228960), 2, COLLEGE, "Quad"));
        /*College building first floor*/

        list.add(new SolrRooms("C101", true, new LatLng(51.589664, -0.228424), 1, COLLEGE, "Classroom"));
        list.add(new SolrRooms("C104", true, new LatLng(51.589538, -0.228474), 1, COLLEGE, "Classroom"));
        list.add(new SolrRooms("C105", true, new LatLng(51.589559, -0.228601), 1, COLLEGE, "Classroom"));
        list.add(new SolrRooms("C106", true, new LatLng(51.589583, -0.228782), 1, COLLEGE, "Classroom"));
        list.add(new SolrRooms("C107", true, new LatLng(51.589593, -0.228890), 1, COLLEGE, "Classroom"));
        list.add(new SolrRooms("C109", true, new LatLng(51.589610, -0.229053), 1, COLLEGE, "Classroom"));
        list.add(new SolrRooms("C110", true, new LatLng(51.589631, -0.229244), 1, COLLEGE, "Classroom"));
        list.add(new SolrRooms("C111", true, new LatLng(51.589644, -0.229346), 1, COLLEGE, "Classroom"));
        list.add(new SolrRooms("C113", true, new LatLng(51.589666, -0.229552), 1, COLLEGE, "Classroom"));
        list.add(new SolrRooms("C114", true, new LatLng(51.589738, -0.229041), 1, COLLEGE, "Lecture Theatre"));
        list.add(new SolrRooms("C115", true, new LatLng(51.589960, -0.228992), 1, COLLEGE, "Lecture Theatre"));
        list.add(new SolrRooms("C117", true, new LatLng(51.589761, -0.229514), 1, COLLEGE, "Micro Lab"));
        list.add(new SolrRooms("C118", true, new LatLng(51.589812, -0.229499), 1, COLLEGE, "Micro Lab"));
        list.add(new SolrRooms("C120", true, new LatLng(51.589897, -0.229477), 1, COLLEGE, "Micro Lab"));
        list.add(new SolrRooms("C121", true, new LatLng(51.589954, -0.229458), 1, COLLEGE, "Micro Lab"));
        list.add(new SolrRooms("C122", true, new LatLng(51.590113, -0.229401), 1, COLLEGE, "Classroom"));
        list.add(new SolrRooms("C126", true, new LatLng(51.590099, -0.229252), 1, COLLEGE, "Classroom"));
        list.add(new SolrRooms("C127", true, new LatLng(51.590088, -0.229185), 1, COLLEGE, "Classroom"));
        list.add(new SolrRooms("C128", true, new LatLng(51.590075, -0.229104), 1, COLLEGE, "Classroom"));
        list.add(new SolrRooms("C131", true, new LatLng(51.590048, -0.228805), 1, COLLEGE, "Micro Lab"));
        list.add(new SolrRooms("C132", true, new LatLng(51.590037, -0.228692), 1, COLLEGE, "Micro Lab"));
        list.add(new SolrRooms("C135", true, new LatLng(51.590006, -0.228474), 1, COLLEGE, "Data Communication Laboratory"));
        list.add(new SolrRooms("C136", true, new LatLng(51.589998, -0.228371), 1, COLLEGE, "Data Communication Laboratory"));
        list.add(new SolrRooms("C138", true, new LatLng(51.589873, -0.228357), 1, COLLEGE, "Cisco Networking Laboratory"));

        /*College building Second floor*/

        list.add(new SolrRooms("C204", true, new LatLng(51.589542, -0.228469), 0, COLLEGE, "Classroom"));
        list.add(new SolrRooms("C205", true, new LatLng(51.589554, -0.228562), 0, COLLEGE, "Classroom"));
        list.add(new SolrRooms("C206", true, new LatLng(51.589566, -0.228654), 0, COLLEGE, "Classroom"));
        list.add(new SolrRooms("C207", true, new LatLng(51.589575, -0.228773), 0, COLLEGE, "Classroom"));
        list.add(new SolrRooms("C209", true, new LatLng(51.589609, -0.229042), 0, COLLEGE, "Classroom"));
        list.add(new SolrRooms("C210", true, new LatLng(51.589737, -0.229047), 0, COLLEGE, "Classroom"));
        list.add(new SolrRooms("C211", true, new LatLng(51.589947, -0.228999), 0, COLLEGE, "Classroom"));
        list.add(new SolrRooms("C212", true, new LatLng(51.589735, -0.229268), 0, COLLEGE, "Classroom"));
        list.add(new SolrRooms("C213A", true, new LatLng(51.589788, -0.229260), 0, COLLEGE, "Classroom"));
        list.add(new SolrRooms("C213B", true, new LatLng(51.589828, -0.229246), 0, COLLEGE, "Classroom"));
        list.add(new SolrRooms("C214", true, new LatLng(51.589885, -0.229224), 0, COLLEGE, "Classroom"));
        list.add(new SolrRooms("C215", true, new LatLng(51.589953, -0.229189), 0, COLLEGE, "Classroom"));
        list.add(new SolrRooms("C216B", true, new LatLng(51.590029, -0.229170), 0, COLLEGE, "Classroom"));
        list.add(new SolrRooms("C217", true, new LatLng(51.590057, -0.228902), 0, COLLEGE, "Classroom"));
        list.add(new SolrRooms("C218", true, new LatLng(51.590048, -0.228790), 0, COLLEGE, "Classroom"));

        /*HatchCroft Building Ground Floor*/

        list.add(new SolrRooms("Hatchcroft Building", true, new LatLng(51.589154, -0.229178), 2, HATCHCROFT, "Hatchcroft Building"));
        list.add(new SolrRooms("HG01", true, new LatLng(51.589178, -0.229000), 2, HATCHCROFT, "Spec Lab"));
        list.add(new SolrRooms("HG02", true, new LatLng(51.589164, -0.228829), 2, HATCHCROFT, "Spec Lab"));
        list.add(new SolrRooms("HG03", true, new LatLng(51.589162, -0.228700), 2, HATCHCROFT, "Spec Lab"));
        list.add(new SolrRooms("HG04", true, new LatLng(51.589119, -0.228704), 2, HATCHCROFT, "Spec Lab"));
        list.add(new SolrRooms("HG05", true, new LatLng(51.589117, -0.228645), 2, HATCHCROFT, "Spec Lab"));
        list.add(new SolrRooms("HG06", true, new LatLng(51.589137, -0.228572), 2, HATCHCROFT, "Spec Lab"));
        list.add(new SolrRooms("HG07", true, new LatLng(51.589041, -0.228589), 2, HATCHCROFT, "Spec Lab"));
        list.add(new SolrRooms("HG08", true, new LatLng(51.589041, -0.228589), 2, HATCHCROFT, "Disabled Toilet 1 (Hatchcroft , Ground Floor)"));
        list.add(new SolrRooms("HG09", true, new LatLng(51.589056, -0.228697), 2, HATCHCROFT, "Spec Lab"));
        list.add(new SolrRooms("HG10", true, new LatLng(51.589067, -0.228812), 2, HATCHCROFT, "Office"));
        list.add(new SolrRooms("HG11", true, new LatLng(51.589093, -0.228873), 2, HATCHCROFT, "Spec Lab"));
        list.add(new SolrRooms("HG12", true, new LatLng(51.589062, -0.228885), 2, HATCHCROFT, "Spec Lab"));
        list.add(new SolrRooms("HG13", true, new LatLng(51.589089, -0.229008), 2, HATCHCROFT, "Spec Lab"));
        list.add(new SolrRooms("HG14", true, new LatLng(51.589093, -0.229159), 2, HATCHCROFT, "Spec Lab"));
        list.add(new SolrRooms("HG19", true, new LatLng(51.589023, -0.229431), 2, HATCHCROFT, "Lecture Theatre"));
        list.add(new SolrRooms("HG20", true, new LatLng(51.589106, -0.229287), 2, HATCHCROFT, "Female Toilet (Hatchcroft, Ground Floor)"));
        list.add(new SolrRooms("HG21)", true, new LatLng(51.589146, -0.229289), 2, HATCHCROFT, "Disabled Toilet 1 (Hatchcroft, Ground Floor"));
        list.add(new SolrRooms("HG24", true, new LatLng(51.589159, -0.229675), 2, HATCHCROFT, "Spec Lab"));
        list.add(new SolrRooms("HG27", true, new LatLng(51.589129, -0.229532), 2, HATCHCROFT, "Spec Lab"));
        list.add(new SolrRooms("HG28", true, new LatLng(51.589136, -0.229463), 2, HATCHCROFT, "Spec Lab"));
        list.add(new SolrRooms("HG29", true, new LatLng(51.589258, -0.229653), 2, HATCHCROFT, "Spec Lab"));
        list.add(new SolrRooms("HG30", true, new LatLng(51.589249, -0.229542), 2, HATCHCROFT, "Spec Lab"));
        list.add(new SolrRooms("HG31", true, new LatLng(51.589235, -0.229428), 2, HATCHCROFT, "Spec Lab"));
        list.add(new SolrRooms("HG33", true, new LatLng(51.589206, -0.229330), 2, HATCHCROFT, "Spec Lab"));

        /*HatchCroft Building 1st floor*/

        list.add(new SolrRooms("H101", true, new LatLng(51.589206, -0.229330), 1, HATCHCROFT, "Spec Lab"));
        list.add(new SolrRooms("H102", true, new LatLng(51.589177, -0.228925), 1, HATCHCROFT, "Spec Lab"));
        list.add(new SolrRooms("H104", true, new LatLng(51.589149, -0.228774), 1, HATCHCROFT, "Spec Lab"));
        list.add(new SolrRooms("H105", true, new LatLng(51.589138, -0.228608), 1, HATCHCROFT, "Spec Lab"));
        list.add(new SolrRooms("H106", true, new LatLng(51.589041, -0.228647), 1, HATCHCROFT, "Spec Lab"));
        list.add(new SolrRooms("H109", true, new LatLng(51.589075, -0.228880), 1, HATCHCROFT, "Spec Lab"));
        list.add(new SolrRooms("H110", true, new LatLng(51.589075, -0.228880), 1, HATCHCROFT, "Spec Lab"));
        list.add(new SolrRooms("H111", true, new LatLng(51.589075, -0.228880), 1, HATCHCROFT, "Spec Lab"));
        list.add(new SolrRooms("H113", true, new LatLng(51.589104, -0.229089), 1, HATCHCROFT, "Spec Lab"));
        list.add(new SolrRooms("H116", true, new LatLng(51.589025, -0.229440), 1, HATCHCROFT, "Lecture Theatre"));
        list.add(new SolrRooms("H117", true, new LatLng(51.588963, -0.229356), 1, HATCHCROFT, "Spec Lab"));
        list.add(new SolrRooms("H118", true, new LatLng(51.588968, -0.229395), 1, HATCHCROFT, "Spec Lab"));
        list.add(new SolrRooms("H119", true, new LatLng(51.588972, -0.229433), 1, HATCHCROFT, "Spec Lab"));
        list.add(new SolrRooms("H120", true, new LatLng(51.588982, -0.229475), 1, HATCHCROFT, "Spec Lab"));
        list.add(new SolrRooms("H121", true, new LatLng(51.588984, -0.229518), 1, HATCHCROFT, "Spec Lab"));
        list.add(new SolrRooms("H122", true, new LatLng(51.588989, -0.229552), 1, HATCHCROFT, "Spec Lab"));
        list.add(new SolrRooms("H123", true, new LatLng(51.589007, -0.229543), 1, HATCHCROFT, "Spec Lab"));
        list.add(new SolrRooms("H124", true, new LatLng(51.589044, -0.229535), 1, HATCHCROFT, "Spec Lab"));
        list.add(new SolrRooms("H125", true, new LatLng(51.589079, -0.229523), 1, HATCHCROFT, "Spec Lab"));
        list.add(new SolrRooms("H128", true, new LatLng(51.589096, -0.229294), 1, HATCHCROFT, "Male Toilet (Hatchcroft, First Floor"));
        list.add(new SolrRooms("H129", true, new LatLng(51.589144, -0.229290), 1, HATCHCROFT, "Disabled Toilet (Hatchcroft, First Floor)"));
        list.add(new SolrRooms("H133", true, new LatLng(51.589174, -0.229586), 1, HATCHCROFT, "Spec Lab"));
        list.add(new SolrRooms("H135", true, new LatLng(51.589140, -0.229645), 1, HATCHCROFT, "Spec Lab"));
        list.add(new SolrRooms("H136", true, new LatLng(51.589154, -0.229735), 1, HATCHCROFT, "Spec Lab"));
        list.add(new SolrRooms("H137", true, new LatLng(51.589254, -0.229653), 1, HATCHCROFT, "Spec Lab"));
        list.add(new SolrRooms("H139", true, new LatLng(51.589241, -0.229522), 1, HATCHCROFT, "Spec Lab"));
        list.add(new SolrRooms("H141A", true, new LatLng(51.589231, -0.229414), 1, HATCHCROFT, "Spec Lab"));
        list.add(new SolrRooms("H141B", true, new LatLng(51.589217, -0.229299), 1, HATCHCROFT, "Spec Lab"));

        /*HatchCroft Building 2nd floor*/

        list.add(new SolrRooms("H201", true, new LatLng(51.589178, -0.229018), 0, HATCHCROFT, "Wet Lab"));
        list.add(new SolrRooms("H202", true, new LatLng(51.589169, -0.228898), 0, HATCHCROFT, "Dry Lab"));
        list.add(new SolrRooms("H203", true, new LatLng(51.589150, -0.228760), 0, HATCHCROFT, "Skills Lab"));
        list.add(new SolrRooms("H205", true, new LatLng(51.589136, -0.228607), 0, HATCHCROFT, "Skills Lab"));
        list.add(new SolrRooms("H206", true, new LatLng(51.589042, -0.228599), 0, HATCHCROFT, "CMH Lab"));
        list.add(new SolrRooms("H207", true, new LatLng(51.589042, -0.228687), 0, HATCHCROFT, "Clinical Skills Lab"));
        list.add(new SolrRooms("H208", true, new LatLng(51.589071, -0.228866), 0, HATCHCROFT, "Clinical Skills Lab"));
        list.add(new SolrRooms("H209", true, new LatLng(51.589093, -0.229019), 0, HATCHCROFT, "Spec Lab"));
        list.add(new SolrRooms("H211", true, new LatLng(51.589111, -0.229161), 0, HATCHCROFT, "Female Toilet (Hatchcroft, Second Floor)"));
        list.add(new SolrRooms("H216", true, new LatLng(51.589116, -0.229427), 0, HATCHCROFT, "Spec Lab"));
        list.add(new SolrRooms("H217", true, new LatLng(51.589118, -0.229537), 0, HATCHCROFT, "Spec Lab"));
        list.add(new SolrRooms("H219", true, new LatLng(51.589162, -0.229576), 0, HATCHCROFT, "Spec Lab"));
        list.add(new SolrRooms("H220", true, new LatLng(51.589158, -0.229686), 0, HATCHCROFT, "Spec Lab"));
        list.add(new SolrRooms("H221", true, new LatLng(51.589255, -0.229642), 0, HATCHCROFT, "Spec Lab"));
        list.add(new SolrRooms("H222", true, new LatLng(51.589243, -0.229572), 0, HATCHCROFT, "Spec Lab"));
        list.add(new SolrRooms("H223", true, new LatLng(51.589237, -0.229476), 0, HATCHCROFT, "Spec Lab"));
        list.add(new SolrRooms("H224", true, new LatLng(51.589226, -0.229364), 0, HATCHCROFT, "Spec Lab"));

        /*Building 9*/

        list.add(new SolrRooms("Building 9", true, new LatLng(51.588709, -0.229398), 0, BUILDING9, "Building 9"));
        list.add(new SolrRooms("BG01", true, new LatLng(51.588697, -0.229558), 0, BUILDING9, "Classroom"));
        list.add(new SolrRooms("BG02", true, new LatLng(51.588771, -0.229495), 0, BUILDING9, "Classroom"));
        list.add(new SolrRooms("BG03", true, new LatLng(51.588709, -0.229460), 0, BUILDING9, "Toilet (Building 9, Ground Floor)"));
        list.add(new SolrRooms("BG04", true, new LatLng(51.588738, -0.229438), 0, BUILDING9, "Disabled Toilet (Building 9, Ground Floor)"));
        list.add(new SolrRooms("BG05", true, new LatLng(51.588796, -0.229342), 0, BUILDING9, "ICT Suite"));
        list.add(new SolrRooms("BG07", true, new LatLng(51.588698, -0.229325), 0, BUILDING9, "Meeting Room"));
        list.add(new SolrRooms("BG10", true, new LatLng(51.588647, -0.229382), 0, BUILDING9, "Classroom"));
        list.add(new SolrRooms("BG11", true, new LatLng(51.588661, -0.229456), 0, BUILDING9, "Hub Breakout"));

        /*Building 10 ground floor*/

        list.add(new SolrRooms("Building 10", true, new LatLng(51.589837, -0.229845), 1, BUILDING10, "Building 10"));

        list.add(new SolrRooms("BTG01", true, new LatLng(51.589815, -0.229934), 1, BUILDING10, "Simulation Room A"));
        list.add(new SolrRooms("BTG02", true, new LatLng(51.589858, -0.229918), 1, BUILDING10, "Store Room"));
        list.add(new SolrRooms("BTG04", true, new LatLng(51.589845, -0.229755), 1, BUILDING10, "Simulation Room B"));

        /*Building 10 first floor*/

        list.add(new SolrRooms("BT101", true, new LatLng(51.589825, -0.229769), 0, BUILDING10, "CPR Skills Room"));
        list.add(new SolrRooms("BT103", true, new LatLng(51.589849, -0.229926), 0, BUILDING10, "CPR Skills Room"));

        /*Portakabin 2 ground floor*/

        list.add(new SolrRooms("Portakabin 2", true, new LatLng(51.589994, -0.229832), 1, PORTAKABIN_2, "Portakabin 2"));
        list.add(new SolrRooms("P2G01", true, new LatLng(51.589963, -0.229843), 0, PORTAKABIN_2, "Recital Room"));
        list.add(new SolrRooms("P2G02", true, new LatLng(51.590010, -0.229825), 0, PORTAKABIN_2, "Recital Room"));
        list.add(new SolrRooms("P2G03", true, new LatLng(51.589982, -0.229734), 0, PORTAKABIN_2, "Recital Room"));
        list.add(new SolrRooms("P2G03A", true, new LatLng(51.590004, -0.229729), 0, PORTAKABIN_2, "Recital Room"));
        list.add(new SolrRooms("P2G04", true, new LatLng(51.589960, -0.229746), 0, PORTAKABIN_2, "Recital Room"));
        list.add(new SolrRooms("P2G05", true, new LatLng(51.589941, -0.229762), 0, PORTAKABIN_2, "Recital Room"));

        /*Portakabin 6 & 7 ground floor*/

        list.add(new SolrRooms("Portakabin 6 & 7", true, new LatLng(51.589662, -0.229930), 1, PORTAKABIN_6_7, "Portakabin 6 & 7"));
        list.add(new SolrRooms("P6G01", true, new LatLng(51.589661, -0.229960), 1, PORTAKABIN_6_7, "Classroom"));
        list.add(new SolrRooms("P6G02", true, new LatLng(51.589654, -0.229853), 1, PORTAKABIN_6_7, "Classroom"));

        /*Portakabin 6 & 7 first floor*/

        list.add(new SolrRooms("P7101", true, new LatLng(51.589653, -0.229856), 0, PORTAKABIN_6_7, "Classroom"));
        list.add(new SolrRooms("P7102", true, new LatLng(51.589665, -0.229957), 0, PORTAKABIN_6_7, "Classroom"));

        /*Portakabin 8 Ground floor*/
        list.add(new SolrRooms("P8G01", true, new LatLng(51.590704, -0.231243), 0, PORTAKABIN_8, "Wash Room"));
        list.add(new SolrRooms("P8G02", true, new LatLng(51.590743, -0.231231), 0, PORTAKABIN_8, "Female Prayer Room"));
        list.add(new SolrRooms("P8G03", true, new LatLng(51.590778, -0.231227), 0, PORTAKABIN_8, "Male Prayer Room"));

        /*Portakabin A ground floor*/

        list.add(new SolrRooms("Portakabin A", true, new LatLng(51.589795, -0.230213), 1, PORTAKABIN_A, "Portakabin A"));
        list.add(new SolrRooms("PAG01", true, new LatLng(51.589905, -0.230136), 1, PORTAKABIN_A, "Classroom"));
        list.add(new SolrRooms("PAG02", true, new LatLng(51.589819, -0.230155), 1, PORTAKABIN_A, "Classroom"));
        list.add(new SolrRooms("PAG03", true, new LatLng(51.589691, -0.230168), 1, PORTAKABIN_A, "Classroom"));
        list.add(new SolrRooms("PAG04", true, new LatLng(51.589702, -0.230317), 1, PORTAKABIN_A, "Classroom"));
        list.add(new SolrRooms("PAG05", true, new LatLng(51.589863, -0.230144), 1, PORTAKABIN_A, "Toilet 1(Portakabin, Ground Floor)"));
        list.add(new SolrRooms("PAG06", true, new LatLng(51.589863, -0.230144), 1, PORTAKABIN_A, "Toilet 2(Portakabin, Ground Floor)"));
        list.add(new SolrRooms("PAG07", true, new LatLng(51.589697, -0.230244), 1, PORTAKABIN_A, "Toilet 3(Portakabin, Ground Floor)"));
        list.add(new SolrRooms("PAG08", true, new LatLng(51.589697, -0.230244), 1, PORTAKABIN_A, "Toilet 4(Portakabin, Ground Floor)"));

        /*Portakabin A first floor*/

        list.add(new SolrRooms("PA101", true, new LatLng(51.589691, -0.230164), 0, PORTAKABIN_A, "Classroom"));
        list.add(new SolrRooms("PA102", true, new LatLng(51.589691, -0.230165), 0, PORTAKABIN_A, "Classroom"));
        list.add(new SolrRooms("PA103", true, new LatLng(51.589691, -0.230166), 0, PORTAKABIN_A, "Toilet 1(Portakabin, First Floor)"));
        list.add(new SolrRooms("PA104", true, new LatLng(51.589691, -0.230167), 0, PORTAKABIN_A, "Toilet 2(Portakabin, First Floor)"));

        /*Portakabin B ground floor*/
        list.add(new SolrRooms("Portakabin B", true, new LatLng(51.590750, -0.229020), 0, PORTAKABIN_B, "Portakabin B"));
        list.add(new SolrRooms("PBG01", true, new LatLng(51.590750, -0.229020), 0, PORTAKABIN_B, "Classroom"));
        list.add(new SolrRooms("PBG02", true, new LatLng(51.590655, -0.229057), 0, PORTAKABIN_B, "Classroom"));

        /*Vine ground floor*/

        list.add(new SolrRooms("Vine Building", true, new LatLng(51.590637, -0.230763), 1, VINE, "Vine Building"));
        list.add(new SolrRooms("VG01", true, new LatLng(51.590575, -0.230748), 1, VINE, "Meeting Room"));
        list.add(new SolrRooms("VG02", true, new LatLng(51.590586, -0.230860), 1, VINE, "Meeting Room"));
        list.add(new SolrRooms("VG04", true, new LatLng(51.590717, -0.230900), 1, VINE, "Male Toilet (VINE, Ground Floor)"));
        list.add(new SolrRooms("VG05", true, new LatLng(51.590681, -0.230840), 1, VINE, "Classroom"));
        list.add(new SolrRooms("VG06", true, new LatLng(51.590670, -0.230756), 1, VINE, "Classroom"));
        list.add(new SolrRooms("VG07", true, new LatLng(51.590662, -0.230671), 1, VINE, "Classroom"));
        list.add(new SolrRooms("VG08", true, new LatLng(51.590686, -0.230596), 1, VINE, "Female Toilet (VINE, Ground Floor)"));

        /*Vine first Floor*/

        list.add(new SolrRooms("V101", true, new LatLng(51.590574, -0.230743), 0, VINE, "Meeting Room"));
        list.add(new SolrRooms("V102", true, new LatLng(51.590590, -0.230856), 0, VINE, "Meeting Room"));
        list.add(new SolrRooms("V103", true, new LatLng(51.590676, -0.230842), 0, VINE, "Classroom"));
        list.add(new SolrRooms("V104", true, new LatLng(51.590671, -0.230755), 0, VINE, "Classroom"));
        list.add(new SolrRooms("V105", true, new LatLng(51.590663, -0.230672), 0, VINE, "Classroom"));

        /*MDX House ground floor*/
        list.add(new SolrRooms("MDX House", true, new LatLng(51.589976, -0.230764), 0, MDXHOUSE, "MDX House"));

        /*MDX House basement */
        list.add(new SolrRooms("Student Union", true, new LatLng(51.590136, -0.230874), 1, MDXHOUSE, "MDXSU"));
        list.add(new SolrRooms("Gym", true, new LatLng(51.589927, -0.230906), 1, MDXHOUSE, "Fitness Pod"));
        list.add(new SolrRooms("Real Tennis Club", true, new LatLng(51.589771, -0.230951), 1, MDXHOUSE, "Real Tennis Club"));
        list.add(new SolrRooms("Dance Studio", true, new LatLng(51.589735, -0.230543), 0, MDXHOUSE, "Dance Studio"));

        /*Sheppard Library*/

        list.add(new SolrRooms("Sheppard Library", true, new LatLng(51.590456, -0.229614), 3, SHEPPARDLIBRARY, "Sheppard Library"));//SET

        /*Sheppard Library basement*/

        list.add(new SolrRooms("SB01", true, new LatLng(51.590661, -0.229612), 4, SHEPPARDLIBRARY, "Open Access Area"));
        list.add(new SolrRooms("SB03", true, new LatLng(51.590748, -0.229431), 4, SHEPPARDLIBRARY, "Open Access Area"));
        list.add(new SolrRooms("SB05", true, new LatLng(51.590382, -0.229402), 4, SHEPPARDLIBRARY, "Teaching Resource Room"));
        list.add(new SolrRooms("SB12A", true, new LatLng(51.590219, -0.229574), 4, SHEPPARDLIBRARY, "Material Room"));
        list.add(new SolrRooms("SB16", true, new LatLng(51.590417, -0.229588), 4, SHEPPARDLIBRARY, "Material Room"));
        list.add(new SolrRooms("SB19", true, new LatLng(51.590469, -0.229837), 4, CIRCLE_CAFE, "Circle Café (Costa Coffee)"));

        /*Sheppard Library ground floor*/

        list.add(new SolrRooms("SG01", true, new LatLng(51.590359, -0.229603), 3, SHEPPARDLIBRARY, "Unihelp"));
        list.add(new SolrRooms("SG02", true, new LatLng(51.590316, -0.229317), 3, SHEPPARDLIBRARY, "Book Sorting Room"));
        list.add(new SolrRooms("SG09", true, new LatLng(51.590763, -0.229379), 3, SHEPPARDLIBRARY, "Disabled Toilet (Sheppard Library, Ground Floor)"));
        list.add(new SolrRooms("SG10", true, new LatLng(51.590735, -0.229399), 3, SHEPPARDLIBRARY, "Female Toilet (Sheppard Library, Ground Floor)"));
        list.add(new SolrRooms("SG11", true, new LatLng(51.590745, -0.229455), 3, SHEPPARDLIBRARY, "Male Toilet (Sheppard Library, Ground Floor)"));
        list.add(new SolrRooms("SG12A", true, new LatLng(51.590693, -0.229437), 3, SHEPPARDLIBRARY, "Group Study Space / IT"));
        list.add(new SolrRooms("SG12B", true, new LatLng(51.590642, -0.229453), 3, SHEPPARDLIBRARY, "Group Study Space / IT"));
        list.add(new SolrRooms("SG13A", true, new LatLng(51.590589, -0.229471), 3, SHEPPARDLIBRARY, "Group Study Space / IT"));
        list.add(new SolrRooms("SG13B", true, new LatLng(51.590536, -0.229480), 3, SHEPPARDLIBRARY, "Group Study Space / IT"));
//        list.add(new SolrRooms("SPG09",new LatLng(51.590238, -0.229348),3,SHEPPARDLIBRARY,"Unihelp Pod"));
//        list.add(new SolrRooms("SPG10",new LatLng(51.590238, -0.229349),3,SHEPPARDLIBRARY,"Unihelp Pod"));
//        list.add(new SolrRooms("SPG11",new LatLng(51.590238, -0.229350),3,SHEPPARDLIBRARY,"Unihelp Pod"));
//        list.add(new SolrRooms("SPG12",new LatLng(51.590238, -0.229351),3,SHEPPARDLIBRARY,"Unihelp Pod"));
//        list.add(new SolrRooms("SPG13",new LatLng(51.590238, -0.229352),3,SHEPPARDLIBRARY,"Unihelp Pod"));
//        list.add(new SolrRooms("SPG14",new LatLng(51.590238, -0.229353),3,SHEPPARDLIBRARY,"Unihelp Pod"));
//        list.add(new SolrRooms("SPG15",new LatLng(51.590238, -0.229354),3,SHEPPARDLIBRARY,"Unihelp Pod"));
//        list.add(new SolrRooms("SPG16",new LatLng(51.590238, -0.229355),3,SHEPPARDLIBRARY,"Unihelp Pod"));
//        list.add(new SolrRooms("SPG17",new LatLng(51.590238, -0.229356),3,SHEPPARDLIBRARY,"Unihelp Pod"));
//        list.add(new SolrRooms("SPG18",new LatLng(51.590238, -0.229357),3,SHEPPARDLIBRARY,"Unihelp Pod"));
//        list.add(new SolrRooms("SPG19",new LatLng(51.590320, -0.229655),3,SHEPPARDLIBRARY,"Unihelp Pod"));
//        list.add(new SolrRooms("SPG20",new LatLng(51.590320, -0.229656),3,SHEPPARDLIBRARY,"Unihelp Pod"));
//        list.add(new SolrRooms("SPG21",new LatLng(51.590320, -0.229657),3,SHEPPARDLIBRARY,"Unihelp Pod"));
//        list.add(new SolrRooms("SPG22",new LatLng(51.590320, -0.229658),3,SHEPPARDLIBRARY,"Unihelp Pod"));

        /*Sheppard Library 1st floor*/

        list.add(new SolrRooms("S101", true, new LatLng(51.590660, -0.229613), 2, SHEPPARDLIBRARY, "Open Access Study Space"));
        list.add(new SolrRooms("S103", true, new LatLng(51.590730, -0.229399), 2, SHEPPARDLIBRARY, "Disabled Toilet (Sheppard Library, First Floor)"));
        list.add(new SolrRooms("S104", true, new LatLng(51.590730, -0.229399), 2, SHEPPARDLIBRARY, "Female Toilet (Sheppard Library, First Floor)"));
        list.add(new SolrRooms("S105", true, new LatLng(51.590730, -0.229399), 2, SHEPPARDLIBRARY, "Male Toilet (Sheppard Library, First Floor)"));
        list.add(new SolrRooms("S106", true, new LatLng(51.590588, -0.229486), 2, SHEPPARDLIBRARY, "Group Study Space / IT"));
        list.add(new SolrRooms("S107", true, new LatLng(51.590641, -0.229456), 2, SHEPPARDLIBRARY, "Group Study Space / IT"));
        list.add(new SolrRooms("S110", true, new LatLng(51.590440, -0.229470), 2, SHEPPARDLIBRARY, "Meeting Room"));
        list.add(new SolrRooms("S111", true, new LatLng(51.590304, -0.229313), 2, SHEPPARDLIBRARY, "Financial Market Lab"));
        list.add(new SolrRooms("S112", true, new LatLng(51.590233, -0.229330), 2, SHEPPARDLIBRARY, "Group Study Space / IT"));
        list.add(new SolrRooms("S118", true, new LatLng(51.590500, -0.229862), 2, SHEPPARDLIBRARY, "Group Study Space / IT"));
        list.add(new SolrRooms("S119", true, new LatLng(51.590417, -0.229655), 2, SHEPPARDLIBRARY, "Open Access / Group Study Space / Study Hub"));

        /*Sheppard Library 2nd floor*/

        list.add(new SolrRooms("S201", true, new LatLng(51.590659, -0.229606), 1, SHEPPARDLIBRARY, "Open Access Study Space"));
        list.add(new SolrRooms("S203", true, new LatLng(51.590730, -0.229399), 1, SHEPPARDLIBRARY, "Disabled Toilet (Sheppard Library, Second Floor)"));
        list.add(new SolrRooms("S204", true, new LatLng(51.590730, -0.229399), 1, SHEPPARDLIBRARY, "Female Toilet (Sheppard Library, Second Floor)"));
        list.add(new SolrRooms("S205", true, new LatLng(51.590730, -0.229399), 1, SHEPPARDLIBRARY, "Male Toilet (Sheppard Library, Second Floor)"));
        list.add(new SolrRooms("S206", true, new LatLng(51.590694, -0.229433), 1, SHEPPARDLIBRARY, "Silent Study / PC Room"));
        list.add(new SolrRooms("S215", true, new LatLng(51.590351, -0.229596), 1, SHEPPARDLIBRARY, "Open Access Silent Study Space"));
        list.add(new SolrRooms("S216", true, new LatLng(51.590306, -0.229326), 1, SHEPPARDLIBRARY, "Silent Study / PC Room"));
        list.add(new SolrRooms("S217", true, new LatLng(51.590250, -0.229364), 1, SHEPPARDLIBRARY, "Silent Study / PC Area"));
        list.add(new SolrRooms("S218", true, new LatLng(51.590470, -0.229887), 1, SHEPPARDLIBRARY, "Bookshelves"));
        list.add(new SolrRooms("S219", true, new LatLng(51.590518, -0.229841), 1, SHEPPARDLIBRARY, "Student Bookable Group Study Space"));

        /*Sheppard Library 3rd floor*/

        list.add(new SolrRooms("S302", true, new LatLng(51.590365, -0.229577), 0, SHEPPARDLIBRARY, "Open Access Study Space"));
        list.add(new SolrRooms("S303", true, new LatLng(51.590308, -0.229326), 0, SHEPPARDLIBRARY, "Silent Study Space Open Area"));
        list.add(new SolrRooms("S304", true, new LatLng(51.590237, -0.229350), 0, SHEPPARDLIBRARY, "Silent Study Space / IT"));
        list.add(new SolrRooms("S305", true, new LatLng(51.590490, -0.229879), 0, SHEPPARDLIBRARY, "Silent Study Space / IT"));

        /*Williams Building basement*/
        list.add(new SolrRooms("WB05", true, new LatLng(51.590582, -0.228934), 2, WILLIAMS, "Goods In - Post Room"));

        /*Williams Building ground floor*/

        list.add(new SolrRooms("Williams Building", true, new LatLng(51.590478, -0.228681), 1, WILLIAMS, "Middlesex"));
        list.add(new SolrRooms("WG07", true, new LatLng(51.590502, -0.228898), 1, WILLIAMS, "Female Toilet 1 (Williams, Ground Floor)"));
        list.add(new SolrRooms("WG32", true, new LatLng(51.590523, -0.228726), 1, WILLIAMS, "Meeting Room"));
        list.add(new SolrRooms("WG33", true, new LatLng(51.590528, -0.228629), 1, WILLIAMS, "Translation Room"));
        list.add(new SolrRooms("WG35", true, new LatLng(51.590498, -0.228469), 1, WILLIAMS, "CDS Printing Room"));
        list.add(new SolrRooms("WG37", true, new LatLng(51.590435, -0.228221), 1, WILLIAMS, "Male Toilet (Williams, Ground Floor)"));
        list.add(new SolrRooms("WG38", true, new LatLng(51.590436, -0.228186), 1, WILLIAMS, "Disabled Toilet (Williams, Ground Floor)"));
        list.add(new SolrRooms("WG39", true, new LatLng(51.590464, -0.228206), 1, WILLIAMS, "Female Toilet 2 (Williams, Ground Floor)"));
        list.add(new SolrRooms("WG41", true, new LatLng(51.590615, -0.228059), 1, WILLIAMS, "Library @ Williams"));
        list.add(new SolrRooms("WG44", true, new LatLng(51.590599, -0.228180), 1, WILLIAMS, "Study Space"));
        list.add(new SolrRooms("WG45", true, new LatLng(51.590599, -0.228181), 1, WILLIAMS, "Study Space"));
        list.add(new SolrRooms("WG46", true, new LatLng(51.590599, -0.228182), 1, WILLIAMS, "Study Space"));
        list.add(new SolrRooms("WG47", true, new LatLng(51.590608, -0.228236), 1, WILLIAMS, "LRS Office"));
        list.add(new SolrRooms("WG48", true, new LatLng(51.590616, -0.228343), 1, WILLIAMS, "Classroom"));
        list.add(new SolrRooms("WG49", true, new LatLng(51.590671, -0.228323), 1, WILLIAMS, "Classroom"));
        list.add(new SolrRooms("WG50", true, new LatLng(51.590725, -0.228313), 1, WILLIAMS, "Classroom"));
        list.add(new SolrRooms("WG51", true, new LatLng(51.590699, -0.228202), 1, WILLIAMS, "LRS Meeting Room"));

        /*Williams Building 1st floor*/

        list.add(new SolrRooms("W142", true, new LatLng(51.590486, -0.228347), 0, WILLIAMS, "Classroom"));
        list.add(new SolrRooms("W143", true, new LatLng(51.590420, -0.228196), 0, WILLIAMS, "Disabled Toilet (Williams, First Floor)"));
        list.add(new SolrRooms("W144", true, new LatLng(51.590439, -0.228218), 0, WILLIAMS, "Male Toilet (Williams, First Floor)"));
        list.add(new SolrRooms("W145", true, new LatLng(51.590463, -0.228206), 0, WILLIAMS, "Female Toilet (Williams, First Floor)"));
        list.add(new SolrRooms("W147", true, new LatLng(51.590518, -0.228192), 0, WILLIAMS, "Classroom"));
        list.add(new SolrRooms("W148", true, new LatLng(51.590615, -0.228330), 0, WILLIAMS, "Classroom"));
        list.add(new SolrRooms("W149", true, new LatLng(51.590670, -0.228327), 0, WILLIAMS, "Classroom"));
        list.add(new SolrRooms("W150", true, new LatLng(51.590720, -0.228296), 0, WILLIAMS, "Classroom"));
        list.add(new SolrRooms("W151", true, new LatLng(51.590686, -0.228169), 0, WILLIAMS, "Classroom"));
        list.add(new SolrRooms("W152", true, new LatLng(51.590732, -0.228148), 0, WILLIAMS, "Classroom"));
        list.add(new SolrRooms("W153", true, new LatLng(51.590779, -0.228097), 0, WILLIAMS, "Classroom"));
        list.add(new SolrRooms("W154", true, new LatLng(51.590765, -0.227971), 0, WILLIAMS, "Classroom"));
        list.add(new SolrRooms("W155", true, new LatLng(51.590698, -0.227986), 0, WILLIAMS, "Classroom"));
        list.add(new SolrRooms("W156", true, new LatLng(51.590641, -0.228003), 0, WILLIAMS, "Classroom"));
        list.add(new SolrRooms("W157", true, new LatLng(51.590565, -0.228022), 0, WILLIAMS, "Classroom"));
        list.add(new SolrRooms("W158", true, new LatLng(51.590496, -0.228045), 0, WILLIAMS, "Classroom"));
        list.add(new SolrRooms("W159", true, new LatLng(51.590429, -0.228076), 0, WILLIAMS, "Classroom"));
        list.add(new SolrRooms("W160", true, new LatLng(51.590368, -0.228092), 0, WILLIAMS, "Classroom"));

        /*Barn ground floor*/

        list.add(new SolrRooms("Barn", true, new LatLng(51.590982, -0.228562), 0, BARN, "Middlesex"));
        list.add(new SolrRooms("BAG01", true, new LatLng(51.590944, -0.228581), 0, BARN, "Meeting Room"));
        list.add(new SolrRooms("BAG02", true, new LatLng(51.591041, -0.228582), 0, BARN, "Classroom"));

        /*Grove Block A Basement*/
        list.add(new SolrRooms("GB01", true, new LatLng(51.588518, -0.230839), 5, GROVE_BLOCK_A, "Digital Photography And Reprographics"));
        list.add(new SolrRooms("GB04", true, new LatLng(51.588731, -0.230444), 5, GROVE_BLOCK_A, "Digital Photography And Reprographics"));
        list.add(new SolrRooms("GB05", true, new LatLng(51.588670, -0.230553), 5, GROVE_BLOCK_A, "Photography"));
        list.add(new SolrRooms("GB07", true, new LatLng(51.588586, -0.230503), 5, GROVE_BLOCK_A, "Loading Bay"));
        //list.add(new SolrRooms("GB08",new LatLng(51.588626, -0.230557),5,GROVE_BLOCK_A,"Large Print Room"));
        list.add(new SolrRooms("GB09", true, new LatLng(51.588626, -0.230557), 5, GROVE_BLOCK_A, "Loading Bay"));
        list.add(new SolrRooms("GB10", true, new LatLng(51.588626, -0.230557), 5, GROVE_BLOCK_A, "Colour Darkroom"));
        list.add(new SolrRooms("GB12", true, new LatLng(51.588531, -0.230564), 5, GROVE_BLOCK_A, "Darkroom"));
        list.add(new SolrRooms("GB17A", true, new LatLng(51.588583, -0.230805), 5, GROVE_BLOCK_A, "Photography Studio"));
        list.add(new SolrRooms("GB17B", true, new LatLng(51.588560, -0.230701), 5, GROVE_BLOCK_A, "Photography Studio"));
        list.add(new SolrRooms("GB19A", true, new LatLng(51.588625, -0.230737), 5, GROVE_BLOCK_A, "Photography Studio"));
        list.add(new SolrRooms("GB19B", true, new LatLng(51.588682, -0.230708), 5, GROVE_BLOCK_A, "Photography Studio"));
        list.add(new SolrRooms("GB21", true, new LatLng(51.588753, -0.230682), 5, GROVE_BLOCK_A, "Disabled Toilet (Grove, Basement)"));
        list.add(new SolrRooms("GB22", true, new LatLng(51.588753, -0.230682), 5, GROVE_BLOCK_A, "Female Toilet (Grove, Basement)"));
        list.add(new SolrRooms("GB23", true, new LatLng(51.588753, -0.230682), 5, GROVE_BLOCK_A, "Male Toilet (Grove, Basement)"));

        /*Grove Block A Ground Floor*/
        list.add(new SolrRooms("Grove Block A", true, new LatLng(51.588695, -0.230596), 4, GROVE_BLOCK_A, "Grove Block A"));
        list.add(new SolrRooms("GG01", true, new LatLng(51.588774, -0.230414), 4, GROVE_BLOCK_A, "Plastic/Wood/Metal Workshop"));
        list.add(new SolrRooms("GG04", true, new LatLng(51.588697, -0.230232), 4, GROVE_BLOCK_A, "Plastic/Wood/Metal Workshop"));
        list.add(new SolrRooms("GG07", true, new LatLng(51.588618, -0.230458), 4, GROVE_BLOCK_A, "Storage & Dispensing Storage"));
        list.add(new SolrRooms("GG11", true, new LatLng(51.588553, -0.230557), 4, GROVE_BLOCK_A, "3D Cad Workshop Laser Room"));
        list.add(new SolrRooms("GG12", true, new LatLng(51.588520, -0.230449), 4, GROVE_BLOCK_A, "Cam Machine Room"));
        list.add(new SolrRooms("GG13", true, new LatLng(51.588515, -0.230559), 4, GROVE_BLOCK_A, "Cad Lab Room"));
        list.add(new SolrRooms("GG14", true, new LatLng(51.588492, -0.230365), 4, GROVE_BLOCK_A, "Spray Room"));
        list.add(new SolrRooms("GG15", true, new LatLng(51.588393, -0.230387), 4, GROVE_BLOCK_A, "Ceramics & Glass Workshop"));
        list.add(new SolrRooms("GG17", true, new LatLng(51.588353, -0.230283), 4, GROVE_BLOCK_A, "Ceramics Reclaim"));
        list.add(new SolrRooms("GG18", true, new LatLng(51.588395, -0.230519), 4, GROVE_BLOCK_A, "Small Metal Workshop"));
        list.add(new SolrRooms("GG20", true, new LatLng(51.588407, -0.230624), 4, GROVE_BLOCK_A, "Jewellery Buffers"));
        list.add(new SolrRooms("GG21", true, new LatLng(51.588379, -0.230634), 4, GROVE_BLOCK_A, "Jewellery Casting"));
        list.add(new SolrRooms("GG22", true, new LatLng(51.588479, -0.230601), 4, GROVE_BLOCK_A, "Disabled Toilet 1 (Grove, Ground Floor)"));
        list.add(new SolrRooms("GG23", true, new LatLng(51.588457, -0.230618), 4, GROVE_BLOCK_A, "Male Toilet 1 (Grove, Ground Floor)"));
        list.add(new SolrRooms("GG25", true, new LatLng(51.588467, -0.230939), 4, GROVE_BLOCK_A, "Textile & Print Making Workshop"));
        list.add(new SolrRooms("GG26", true, new LatLng(51.588502, -0.230777), 4, GROVE_BLOCK_A, "Screen Wash"));
        list.add(new SolrRooms("GG29", true, new LatLng(51.588528, -0.230917), 4, GROVE_BLOCK_A, "Printing Textile Dye Lab"));
        list.add(new SolrRooms("GG30", true, new LatLng(51.588544, -0.230966), 4, GROVE_BLOCK_A, "Exposure Plate Making"));
        list.add(new SolrRooms("GG31", true, new LatLng(51.588567, -0.231052), 4, GROVE_BLOCK_A, "Digital Fabric Print Workshop"));
        list.add(new SolrRooms("GG33", true, new LatLng(51.588623, -0.231026), 4, GROVE_BLOCK_A, "Electronics Interface Lab"));
        list.add(new SolrRooms("GG34", true, new LatLng(51.588660, -0.231003), 4, GROVE_BLOCK_A, "Green Room"));
        list.add(new SolrRooms("GG35", true, new LatLng(51.588686, -0.230984), 4, GROVE_BLOCK_A, "Dressing Room"));
        list.add(new SolrRooms("GG36", true, new LatLng(51.588728, -0.230949), 4, GROVE_BLOCK_A, "Tv News Room"));
        list.add(new SolrRooms("GG37", true, new LatLng(51.588775, -0.230860), 4, GROVE_BLOCK_A, "Loan Space"));
        list.add(new SolrRooms("GG38", true, new LatLng(51.588819, -0.230800), 4, GROVE_BLOCK_A, "Tv News Room"));
        list.add(new SolrRooms("GG39", true, new LatLng(51.588860, -0.230763), 4, GROVE_BLOCK_A, "Tv News Room"));
        list.add(new SolrRooms("GG41", true, new LatLng(51.588652, -0.230704), 4, GROVE_BLOCK_A, "Tv Studio B"));
        list.add(new SolrRooms("GG44", true, new LatLng(51.588617, -0.230870), 4, GROVE_BLOCK_A, "Tv Studio A"));
        list.add(new SolrRooms("GG45", true, new LatLng(51.588686, -0.230815), 4, GROVE_BLOCK_A, "Office Kit Room"));
        list.add(new SolrRooms("GG46", true, new LatLng(51.588695, -0.230877), 4, GROVE_BLOCK_A, "Studio Gallery"));
        list.add(new SolrRooms("GG47", true, new LatLng(51.588732, -0.230701), 4, GROVE_BLOCK_A, "Disabled Toilet 2 (Grove, Ground Floor)"));
        list.add(new SolrRooms("GG48", true, new LatLng(51.588741, -0.230667), 4, GROVE_BLOCK_A, "Female Toilet 1 (Grove, Ground Floor)"));


        /*Grove Block A First Floor*/
        list.add(new SolrRooms("Grove Atrium", true, new LatLng(51.588613, -0.230449), 3, GROVE_BLOCK_A, "Grove Atrium"));
        list.add(new SolrRooms("Grove Coffee Pod", true, new LatLng(51.588590, -0.230527), 3, GROVE_BLOCK_A, "Grove Coffee Pod"));
        list.add(new SolrRooms("G101", true, new LatLng(51.588723, -0.230552), 3, GROVE_BLOCK_A, "Lab"));
        list.add(new SolrRooms("G104A", true, new LatLng(51.588728, -0.230392), 3, GROVE_BLOCK_A, "Animation Studio"));
        list.add(new SolrRooms("G104B", true, new LatLng(51.588728, -0.230392), 3, GROVE_BLOCK_A, "Animation Studio"));
        list.add(new SolrRooms("G105", true, new LatLng(51.588695, -0.230257), 3, GROVE_BLOCK_A, "Middlesex"));
        list.add(new SolrRooms("G107", true, new LatLng(51.588397, -0.230322), 3, GROVE_BLOCK_A, "Green Room"));
        //list.add(new SolrRooms("G109",new LatLng(51.588417, -0.230405),3,GROVE_BLOCK_A,"Middlesex"));
        list.add(new SolrRooms("G110", true, new LatLng(51.588389, -0.230401), 3, GROVE_BLOCK_A, "Workshops"));
        list.add(new SolrRooms("G115", true, new LatLng(51.588383, -0.230525), 3, GROVE_BLOCK_A, "Digital Media Workshop 1"));
        list.add(new SolrRooms("G116", true, new LatLng(51.588400, -0.230620), 3, GROVE_BLOCK_A, "Digital Media Workshop 2"));
        list.add(new SolrRooms("G117", true, new LatLng(51.588484, -0.230611), 3, GROVE_BLOCK_A, "Disabled Toilet 1 (Grove, First Floor)"));
        list.add(new SolrRooms("G118", true, new LatLng(51.588461, -0.230623), 3, GROVE_BLOCK_A, "Male Toilet (Grove, First Floor)"));
        list.add(new SolrRooms("G120", true, new LatLng(51.588482, -0.230778), 3, GROVE_BLOCK_A, "Davinci Resolve Studio 1 2"));
        list.add(new SolrRooms("G121", true, new LatLng(51.588482, -0.230778), 3, GROVE_BLOCK_A, "Davinci Resolve Studio 1 2"));
        list.add(new SolrRooms("G122", true, new LatLng(51.588482, -0.230778), 3, GROVE_BLOCK_A, "Middlesex"));
        list.add(new SolrRooms("G122A", true, new LatLng(51.588482, -0.230778), 3, GROVE_BLOCK_A, "Middlesex"));
        list.add(new SolrRooms("G123A", true, new LatLng(51.588482, -0.230778), 3, GROVE_BLOCK_A, "Digital Media Workshop 3"));
        list.add(new SolrRooms("G123B", true, new LatLng(51.588499, -0.231068), 3, GROVE_BLOCK_A, "Digital Media Workshop 4"));
        list.add(new SolrRooms("G124", true, new LatLng(51.588519, -0.230927), 3, GROVE_BLOCK_A, "Suite 11"));
        list.add(new SolrRooms("G125", true, new LatLng(51.588519, -0.230927), 3, GROVE_BLOCK_A, "Suite 12"));
        list.add(new SolrRooms("G126", true, new LatLng(51.588519, -0.230927), 3, GROVE_BLOCK_A, "Suite 9"));
        list.add(new SolrRooms("G127", true, new LatLng(51.588519, -0.230927), 3, GROVE_BLOCK_A, "Suite 10"));
        list.add(new SolrRooms("G128", true, new LatLng(51.588519, -0.230927), 3, GROVE_BLOCK_A, "Suite 7"));
        list.add(new SolrRooms("G129", true, new LatLng(51.588519, -0.230927), 3, GROVE_BLOCK_A, "Suite 8"));
        list.add(new SolrRooms("G130", true, new LatLng(51.588519, -0.230927), 3, GROVE_BLOCK_A, "Suite 5"));
        list.add(new SolrRooms("G131", true, new LatLng(51.588519, -0.230927), 3, GROVE_BLOCK_A, "Suite 6"));
        list.add(new SolrRooms("G132", true, new LatLng(51.588519, -0.230927), 3, GROVE_BLOCK_A, "Suite 3"));
        list.add(new SolrRooms("G133", true, new LatLng(51.588519, -0.230927), 3, GROVE_BLOCK_A, "Suite 4"));
        list.add(new SolrRooms("G134", true, new LatLng(51.588519, -0.230927), 3, GROVE_BLOCK_A, "Suite 1"));
        list.add(new SolrRooms("G135", true, new LatLng(51.588519, -0.230927), 3, GROVE_BLOCK_A, "Suite 2"));
        list.add(new SolrRooms("G136", true, new LatLng(51.588519, -0.230927), 3, GROVE_BLOCK_A, "Pro Tools Avid Suite"));
        list.add(new SolrRooms("G137", true, new LatLng(51.588499, -0.231068), 3, GROVE_BLOCK_A, "Recording Studio"));
        list.add(new SolrRooms("G137A", true, new LatLng(51.588499, -0.231068), 3, GROVE_BLOCK_A, "Recording Studio"));
//        list.add(new SolrRooms("G139",new LatLng(51.588607, -0.231022),3,GROVE_BLOCK_A,"Technician Office "));
        list.add(new SolrRooms("G140", true, new LatLng(51.588638, -0.231006), 3, GROVE_BLOCK_A, "Office"));
        list.add(new SolrRooms("G141", true, new LatLng(51.588659, -0.230993), 3, GROVE_BLOCK_A, "Office"));
//        list.add(new SolrRooms("G142",new LatLng(51.588683, -0.230980),3,GROVE_BLOCK_A,"Office"));
//        list.add(new SolrRooms("G143",new LatLng(51.588721, -0.230953),3,GROVE_BLOCK_A,"Office"));
//        list.add(new SolrRooms("G144",new LatLng(51.588721, -0.230953),3,GROVE_BLOCK_A,"Office"));
//        list.add(new SolrRooms("G145",new LatLng(51.588735, -0.230758),3,GROVE_BLOCK_A,"Office"));
        list.add(new SolrRooms("G146", true, new LatLng(51.588686, -0.230719), 3, GROVE_BLOCK_A, "Visitors And Lighting Gallery"));
        list.add(new SolrRooms("G146A", true, new LatLng(51.588701, -0.230835), 3, GROVE_BLOCK_A, "Middlesex"));
        list.add(new SolrRooms("G147", true, new LatLng(51.588686, -0.230719), 3, GROVE_BLOCK_A, "Visitors And Lighting Gallery"));
        list.add(new SolrRooms("G148", true, new LatLng(51.588686, -0.230719), 3, GROVE_BLOCK_A, "Visitors And Lighting Gallery"));
        list.add(new SolrRooms("G149", true, new LatLng(51.588418, -0.230785), 3, GROVE_BLOCK_A, "Radio Recording Studio"));
//        list.add(new SolrRooms("G150",new LatLng(51.588418, -0.230785),3,GROVE_BLOCK_A,"Radio Recording Studio"));
//        list.add(new SolrRooms("Disabled Toilet 2 (Grove, First Floor)",new LatLng(51.588685, -0.230481),3,GROVE_BLOCK_A,"Middlesex"));
//        list.add(new SolrRooms("Female Toilet (Grove, First Floor)",new LatLng(51.588731, -0.230476),3,GROVE_BLOCK_A,"Middlesex"));


        /*Grove Block A Second Floor*/
        list.add(new SolrRooms("G201", true, new LatLng(51.588775, -0.230429), 2, GROVE_BLOCK_A, "Fashion Workshop"));
        list.add(new SolrRooms("G203", true, new LatLng(51.5886039, -0.2306015), 2, GROVE_BLOCK_A, "Fashion Workshop"));
        list.add(new SolrRooms("G204", true, new LatLng(51.588706, -0.230237), 2, GROVE_BLOCK_A, "Knit & Weave Workshop"));
        list.add(new SolrRooms("G205", true, new LatLng(51.588631, -0.230173), 2, GROVE_BLOCK_A, "Yarn Store"));
        list.add(new SolrRooms("G206", true, new LatLng(51.588380, -0.230317), 2, GROVE_BLOCK_A, "Fashion Studio 4"));
        list.add(new SolrRooms("G207", true, new LatLng(51.588387, -0.230425), 2, GROVE_BLOCK_A, "Fashion Studio 3"));
        list.add(new SolrRooms("G209", true, new LatLng(51.588391, -0.230516), 2, GROVE_BLOCK_A, "Fashion Studio 2"));
        list.add(new SolrRooms("G210", true, new LatLng(51.588415, -0.230627), 2, GROVE_BLOCK_A, "Fashion Studio 1"));
        list.add(new SolrRooms("G211", true, new LatLng(51.588467, -0.230613), 2, GROVE_BLOCK_A, "Disabled Toilet 1 (Grove, Second Floor)"));
        list.add(new SolrRooms("G212", true, new LatLng(51.588467, -0.230613), 2, GROVE_BLOCK_A, "Male Toilet (Grove, Second Floor)"));
        list.add(new SolrRooms("G214A", true, new LatLng(51.588430, -0.230771), 2, GROVE_BLOCK_A, "Fashion Studio 7"));
        list.add(new SolrRooms("G214B", true, new LatLng(51.588464, -0.230918), 2, GROVE_BLOCK_A, "Fashion Studio 6"));
        list.add(new SolrRooms("G214C", true, new LatLng(51.588501, -0.231039), 2, GROVE_BLOCK_A, "Fashion Studio 5"));
        list.add(new SolrRooms("G230", true, new LatLng(51.588567, -0.230725), 2, GROVE_BLOCK_A, "Post Grad Research Room"));
        list.add(new SolrRooms("G231", true, new LatLng(51.588752, -0.230753), 0, GROVE_BLOCK_A, "Screening Dressing Room"));
        list.add(new SolrRooms("G234", true, new LatLng(51.588724, -0.230585), 2, GROVE_BLOCK_A, "Disabled Toilet 2 (Grove, Second Floor)"));
        list.add(new SolrRooms("G235", true, new LatLng(51.588724, -0.230585), 2, GROVE_BLOCK_A, "Female Toilet (Grove, Second Floor)"));

        /*Grove Block A Third Floor*/
        list.add(new SolrRooms("G301", true, new LatLng(51.588726, -0.230342), 1, GROVE_BLOCK_A, "Fine Arts Studio B"));
        list.add(new SolrRooms("G304", true, new LatLng(51.588625, -0.230689), 1, GROVE_BLOCK_A, "Fine Arts Studio C"));
        list.add(new SolrRooms("G305", true, new LatLng(51.588436, -0.230765), 1, GROVE_BLOCK_A, "Fine Arts Studio D"));
        list.add(new SolrRooms("G307", true, new LatLng(51.588744, -0.230683), 1, GROVE_BLOCK_A, "Disabled Toilet (Grove, Third Floor)"));
        list.add(new SolrRooms("G308", true, new LatLng(51.588744, -0.230683), 1, GROVE_BLOCK_A, "Female Toilet (Grove, Third Floor)"));
        list.add(new SolrRooms("G309", true, new LatLng(51.588761, -0.230849), 1, GROVE_BLOCK_A, "Fine Arts Studio A"));

        /*Grove Block A Fourth Floor*/
        list.add(new SolrRooms("G401", true, new LatLng(51.588791, -0.230657), 0, GROVE_BLOCK_A, "Graphics Studio 3"));
        list.add(new SolrRooms("G404", true, new LatLng(51.588744, -0.230683), 0, GROVE_BLOCK_A, "Female Toilet (Grove, Fourth Floor)"));
        list.add(new SolrRooms("G405", true, new LatLng(51.588744, -0.230683), 0, GROVE_BLOCK_A, "Male Toilet (Grove, Fourth Floor)"));
        list.add(new SolrRooms("G406A", true, new LatLng(51.588697, -0.230657), 0, GROVE_BLOCK_A, "Graphics Studio 2"));

        /*Grove Block B Ground Floor*/
        list.add(new SolrRooms("GG71", true, new LatLng(51.589024, -0.230361), 2, GROVE_BLOCK_B, "PDE Teaching Room"));
        list.add(new SolrRooms("GG72", true, new LatLng(51.588973, -0.230201), 2, GROVE_BLOCK_B, "Teaching Room"));
        list.add(new SolrRooms("GG73", true, new LatLng(51.588973, -0.230202), 2, GROVE_BLOCK_B, "Teaching Room"));
        list.add(new SolrRooms("GG74", true, new LatLng(51.588973, -0.230203), 2, GROVE_BLOCK_B, "Teaching Room"));
        list.add(new SolrRooms("GG75", true, new LatLng(51.588973, -0.230204), 2, GROVE_BLOCK_B, "Ensemble Room"));
        list.add(new SolrRooms("GG76", true, new LatLng(51.588973, -0.230205), 2, GROVE_BLOCK_B, "Teaching Room"));
        list.add(new SolrRooms("GG77", true, new LatLng(51.588973, -0.230206), 2, GROVE_BLOCK_B, "Teaching Room"));
        list.add(new SolrRooms("GG78", true, new LatLng(51.588917, -0.230184), 2, GROVE_BLOCK_B, "Female Toilet 2 (Grove, Ground Floor)"));
        list.add(new SolrRooms("GG79", true, new LatLng(51.588917, -0.230185), 2, GROVE_BLOCK_B, "Disabled Toilet 3 (Grove, Ground Floor)"));

        /*Grove Block B First Floor*/
        list.add(new SolrRooms("G170", true, new LatLng(51.588921, -0.230289), 1, GROVE_BLOCK_B, "Male Toilet 1 (Grove, First Floor)"));
        list.add(new SolrRooms("G171", true, new LatLng(51.588921, -0.230289), 1, GROVE_BLOCK_B, "Interior Design / Interior Architecture"));
        list.add(new SolrRooms("G172", true, new LatLng(51.588921, -0.230289), 1, GROVE_BLOCK_B, "Interior Design / Interior Architecture"));
        list.add(new SolrRooms("G174", true, new LatLng(51.588932, -0.230115), 1, GROVE_BLOCK_B, "Music Practice Room 2"));
        list.add(new SolrRooms("G175", true, new LatLng(51.588932, -0.230114), 1, GROVE_BLOCK_B, "Music Practice Room 3"));
        list.add(new SolrRooms("G176", true, new LatLng(51.588932, -0.230111), 1, GROVE_BLOCK_B, "Music Practice Room 4"));
        list.add(new SolrRooms("G177", true, new LatLng(51.588932, -0.230110), 1, GROVE_BLOCK_B, "Music Teaching Room 4"));
        list.add(new SolrRooms("G178", true, new LatLng(51.588932, -0.230109), 1, GROVE_BLOCK_B, "Band Room"));
        list.add(new SolrRooms("G179", true, new LatLng(51.588932, -0.230108), 1, GROVE_BLOCK_B, "Live Room"));
        list.add(new SolrRooms("G180", true, new LatLng(51.588932, -0.230107), 1, GROVE_BLOCK_B, "Recording Studio"));
        list.add(new SolrRooms("G181", true, new LatLng(51.588932, -0.230112), 1, GROVE_BLOCK_B, "Music Practice Room 5"));
        list.add(new SolrRooms("G182", true, new LatLng(51.588932, -0.230113), 1, GROVE_BLOCK_B, "Music Practice Room 6"));

        /*Grove Block B Second Floor*/
        list.add(new SolrRooms("G270", true, new LatLng(51.588964, -0.230414), 0, GROVE_BLOCK_B, "Disabled Toilet 3 (Grove, Second Floor)"));
        list.add(new SolrRooms("G271", true, new LatLng(51.589027, -0.230357), 0, GROVE_BLOCK_B, "TA Studio 2"));
        list.add(new SolrRooms("G272", true, new LatLng(51.588990, -0.230259), 0, GROVE_BLOCK_B, "TA Studio 3"));
        list.add(new SolrRooms("G274", true, new LatLng(51.588965, -0.230102), 0, GROVE_BLOCK_B, "Concert Room"));


        /*Grove Block C Ground Floor*/
        list.add(new SolrRooms("GG90", true, new LatLng(51.589277, -0.230058), 2, GROVE_BLOCK_C, "Student Break Out Area"));
        list.add(new SolrRooms("GG92", true, new LatLng(51.589228, -0.229969), 2, GROVE_BLOCK_C, "Dressing Room"));
        list.add(new SolrRooms("GG93", true, new LatLng(51.589172, -0.230001), 2, GROVE_BLOCK_C, "Classroom Photography"));
        list.add(new SolrRooms("GG94", true, new LatLng(51.589189, -0.230119), 2, GROVE_BLOCK_C, "Studio Life Drawing"));
        list.add(new SolrRooms("GG96", true, new LatLng(51.589172, -0.230206), 4, GROVE_BLOCK_C, "Male Toilet 2 (Grove, Ground Floor)"));
        list.add(new SolrRooms("GG97", true, new LatLng(51.589184, -0.230290), 4, GROVE_BLOCK_C, ""));
        list.add(new SolrRooms("GG98", true, new LatLng(51.589222, -0.230266), 4, GROVE_BLOCK_C, "Disabled Toilet 4 (Grove, Ground Floor)"));

        /*Grove Block C First Floor*/
        list.add(new SolrRooms("G190", true, new LatLng(51.589246, -0.230051), 1, GROVE_BLOCK_C, "Dance Studio"));
        list.add(new SolrRooms("G191", true, new LatLng(51.589246, -0.230052), 1, GROVE_BLOCK_C, "Dance Studio"));
        list.add(new SolrRooms("G192", true, new LatLng(51.589246, -0.230053), 1, GROVE_BLOCK_C, "Dance Studio"));


         /*Ravensfield Bulding*/

        list.add(new SolrRooms("Ravensfield House", false, new LatLng(51.588633, -0.227835), 0, RAVENSFIELDS, "Ravensfield House"));

        /*Ravensfield Ground floor*/

        list.add(new SolrRooms("RVG01", false, new LatLng(51.588682, -0.228016), 1, RAVENSFIELDS, "Grab & Go"));
        list.add(new SolrRooms("RVG02", false, new LatLng(51.588743, -0.227978), 1, RAVENSFIELDS, "Tta2"));
        list.add(new SolrRooms("RVG03", false, new LatLng(51.588684, -0.227899), 1, RAVENSFIELDS, "Stage Workshop"));
        list.add(new SolrRooms("RVG04", false, new LatLng(51.588736, -0.227787), 1, RAVENSFIELDS, "Tta1"));
        list.add(new SolrRooms("RVG04A", false, new LatLng(51.588755, -0.227875), 1, RAVENSFIELDS, "Tta2"));
        list.add(new SolrRooms("RVG06", false, new LatLng(51.588632, -0.227805), 1, RAVENSFIELDS, "Performance Space"));
        list.add(new SolrRooms("RVG09", false, new LatLng(51.588593, -0.228068), 1, RAVENSFIELDS, "Studio 5"));
        list.add(new SolrRooms("RVG11", false, new LatLng(51.588542, -0.227954), 1, RAVENSFIELDS, "Studio 4"));
        list.add(new SolrRooms("RVG14", false, new LatLng(51.588515, -0.227837), 1, RAVENSFIELDS, "Male Toilet (Ravensfields, Ground Floor)"));
        list.add(new SolrRooms("RVG15", false, new LatLng(51.588528, -0.227807), 1, RAVENSFIELDS, "Disabled Toilet (Ravensfields, Ground Floor)"));
        list.add(new SolrRooms("RVG16", false, new LatLng(51.588504, -0.227799), 1, RAVENSFIELDS, "Female Toilet (Ravensfields, Ground Floor)"));
        list.add(new SolrRooms("RVG17", false, new LatLng(51.588639, -0.227657), 1, RAVENSFIELDS, "Studio"));
        list.add(new SolrRooms("RVG17A", false, new LatLng(51.588576, -0.227693), 1, RAVENSFIELDS, "Studio"));
        list.add(new SolrRooms("RVG18", false, new LatLng(51.588674, -0.227628), 1, RAVENSFIELDS, "Local Prop Store 2"));
        list.add(new SolrRooms("RVG19", false, new LatLng(51.588489, -0.227734), 1, RAVENSFIELDS, "Local Prop Store 1"));

        /*Ravensfields House 1st floor*/

        list.add(new SolrRooms("RV101", false, new LatLng(51.588676, -0.228021), 0, RAVENSFIELDS, "Generic Teaching Space"));
        list.add(new SolrRooms("RV103", false, new LatLng(51.588772, -0.227954), 0, RAVENSFIELDS, "Generic Teaching Space"));
        list.add(new SolrRooms("RV104", false, new LatLng(51.588758, -0.227871), 0, RAVENSFIELDS, "Generic Teaching Space"));
        list.add(new SolrRooms("RV107", false, new LatLng(51.588725, -0.227737), 0, RAVENSFIELDS, "Generic Teaching Space"));
        list.add(new SolrRooms("RV109", false, new LatLng(51.588696, -0.227631), 0, RAVENSFIELDS, "Dressing Room"));
        list.add(new SolrRooms("RV110", false, new LatLng(51.588675, -0.227663), 0, RAVENSFIELDS, "Fitting Room"));
        list.add(new SolrRooms("RV111", false, new LatLng(51.588599, -0.227673), 0, RAVENSFIELDS, "Costume Workshop"));
        list.add(new SolrRooms("RV112", false, new LatLng(51.588659, -0.227612), 0, RAVENSFIELDS, "Pattern Store"));
        list.add(new SolrRooms("RV113", false, new LatLng(51.588521, -0.227689), 0, RAVENSFIELDS, "Costume Store"));
        list.add(new SolrRooms("RV114", false, new LatLng(51.588519, -0.227742), 0, RAVENSFIELDS, "Laundry"));
        list.add(new SolrRooms("RV115", false, new LatLng(51.588498, -0.227795), 0, RAVENSFIELDS, "Male Toilet (Ravensfields, First Floor)"));
        list.add(new SolrRooms("RV116", false, new LatLng(51.588525, -0.227809), 0, RAVENSFIELDS, "Disabled Shower"));
        list.add(new SolrRooms("RV117", false, new LatLng(51.588512, -0.227833), 0, RAVENSFIELDS, "Female Toilet (Ravensfields, First Floor)"));
        list.add(new SolrRooms("RV118", false, new LatLng(51.588534, -0.227907), 0, RAVENSFIELDS, "Rehearsal Room 1"));
        list.add(new SolrRooms("RV121", false, new LatLng(51.588557, -0.228009), 0, RAVENSFIELDS, "Rehearsal Room 2"));


        /*Town Hall*/

        list.add(new SolrRooms("Town Hall", false, new LatLng(51.588161, -0.229226), 0, TOWNHALL, "Town Hall"));

        /*Town Hall ground floor*/

        list.add(new SolrRooms("TG02", false, new LatLng(51.588161, -0.229226), 3, TOWNHALL, "Office"));
        list.add(new SolrRooms("TG03", false, new LatLng(51.588161, -0.229227), 3, TOWNHALL, "Office"));
        list.add(new SolrRooms("TG04", false, new LatLng(51.588161, -0.229228), 3, TOWNHALL, "Office"));
        list.add(new SolrRooms("TG06", false, new LatLng(51.588161, -0.229226), 3, TOWNHALL, "Office"));
        list.add(new SolrRooms("TG07", false, new LatLng(51.588161, -0.229226), 3, TOWNHALL, "Office"));
        list.add(new SolrRooms("TG08", false, new LatLng(51.588182, -0.229426), 3, TOWNHALL, "Office"));
        list.add(new SolrRooms("TG09", false, new LatLng(51.588195, -0.229614), 3, TOWNHALL, "Office"));
        list.add(new SolrRooms("TG10", false, new LatLng(51.588198, -0.229667), 3, TOWNHALL, "Office"));
        list.add(new SolrRooms("TG11", false, new LatLng(51.588201, -0.229672), 3, TOWNHALL, "Office"));
        list.add(new SolrRooms("TG12", false, new LatLng(51.588208, -0.229746), 3, TOWNHALL, "Office"));
        list.add(new SolrRooms("TG12A", false, new LatLng(51.588208, -0.229746), 3, TOWNHALL, "Office"));
        list.add(new SolrRooms("TG12B", false, new LatLng(51.588208, -0.229746), 3, TOWNHALL, "Office"));
        list.add(new SolrRooms("TG13", false, new LatLng(51.588215, -0.229809), 3, TOWNHALL, "Office"));
        list.add(new SolrRooms("TG14", false, new LatLng(51.588229, -0.229927), 3, TOWNHALL, "Office"));
        list.add(new SolrRooms("TG15", false, new LatLng(51.588231, -0.230037), 3, TOWNHALL, "Office"));
        list.add(new SolrRooms("TG16", false, new LatLng(51.588231, -0.230037), 3, TOWNHALL, "Office"));
        list.add(new SolrRooms("TG17", false, new LatLng(51.588231, -0.230037), 3, TOWNHALL, "Office"));
        list.add(new SolrRooms("TG18", false, new LatLng(51.588231, -0.230037), 3, TOWNHALL, "Office"));
        list.add(new SolrRooms("TG19", false, new LatLng(51.588354, -0.230028), 3, TOWNHALL, "Office"));
        list.add(new SolrRooms("TG19A", false, new LatLng(51.588354, -0.230028), 3, TOWNHALL, "Office"));
        list.add(new SolrRooms("TG19B", false, new LatLng(51.588354, -0.230028), 3, TOWNHALL, "Office"));
        list.add(new SolrRooms("TG20A", false, new LatLng(51.588354, -0.230028), 3, TOWNHALL, "Office"));
        list.add(new SolrRooms("TG20B", false, new LatLng(51.588215, -0.229809), 3, TOWNHALL, "Office"));
        list.add(new SolrRooms("TG21", false, new LatLng(51.588208, -0.229746), 3, TOWNHALL, "Office"));
        list.add(new SolrRooms("TG22", false, new LatLng(51.588204, -0.229704), 3, TOWNHALL, "Office"));
        list.add(new SolrRooms("TG23", false, new LatLng(51.588197, -0.229643), 3, TOWNHALL, "Office"));
        list.add(new SolrRooms("TG26", false, new LatLng(51.588181, -0.229405), 3, TOWNHALL, "Office"));
        list.add(new SolrRooms("TG28", false, new LatLng(51.588161, -0.229229), 3, TOWNHALL, "Office"));
        list.add(new SolrRooms("TG31", false, new LatLng(51.588401, -0.229119), 3, TOWNHALL, "Office"));
        list.add(new SolrRooms("TG32", false, new LatLng(51.588408, -0.229154), 3, TOWNHALL, "Office"));
        list.add(new SolrRooms("TG33", false, new LatLng(51.588424, -0.229216), 3, TOWNHALL, "Office"));
        list.add(new SolrRooms("TG34", false, new LatLng(51.588446, -0.229276), 3, TOWNHALL, "Office"));
        list.add(new SolrRooms("TG35", false, new LatLng(51.588461, -0.229330), 3, TOWNHALL, "Office"));
        list.add(new SolrRooms("TG36", false, new LatLng(51.588475, -0.229383), 3, TOWNHALL, "Office"));
        list.add(new SolrRooms("TG37", false, new LatLng(51.588529, -0.229531), 3, TOWNHALL, "Office"));
        list.add(new SolrRooms("TG38", false, new LatLng(51.588555, -0.229642), 3, TOWNHALL, "Office"));
        list.add(new SolrRooms("TG39", false, new LatLng(51.588555, -0.229642), 3, TOWNHALL, "Office"));
        list.add(new SolrRooms("TG40", false, new LatLng(51.588577, -0.229704), 3, TOWNHALL, "Office"));
        list.add(new SolrRooms("TG44", false, new LatLng(51.588577, -0.229704), 3, TOWNHALL, "Office"));
        list.add(new SolrRooms("TG45", false, new LatLng(51.588575, -0.229714), 3, TOWNHALL, "Office"));
        list.add(new SolrRooms("TG46", false, new LatLng(51.588575, -0.229712), 3, TOWNHALL, "Office"));
        list.add(new SolrRooms("TG47", false, new LatLng(51.588486, -0.229877), 3, TOWNHALL, "Office"));
        list.add(new SolrRooms("TG47A", false, new LatLng(51.588486, -0.229877), 3, TOWNHALL, "Office"));
        list.add(new SolrRooms("TG48", false, new LatLng(51.588486, -0.229877), 3, TOWNHALL, "Office"));
        list.add(new SolrRooms("TG49", false, new LatLng(51.588589, -0.229809), 3, TOWNHALL, "Office"));
        list.add(new SolrRooms("TG52", false, new LatLng(51.588348, -0.229978), 3, TOWNHALL, "Office"));
        list.add(new SolrRooms("TG53", false, new LatLng(51.588348, -0.229978), 3, TOWNHALL, "Office"));
        list.add(new SolrRooms("TG54", false, new LatLng(51.588348, -0.229978), 3, TOWNHALL, "Office"));
        list.add(new SolrRooms("TG55", false, new LatLng(51.588348, -0.229978), 3, TOWNHALL, "Office"));
        list.add(new SolrRooms("TG56", false, new LatLng(51.588348, -0.229978), 3, TOWNHALL, "Office"));
        list.add(new SolrRooms("TG57", false, new LatLng(51.588348, -0.229978), 3, TOWNHALL, "Office"));
        list.add(new SolrRooms("TG58", false, new LatLng(51.588348, -0.229978), 3, TOWNHALL, "Office"));
        list.add(new SolrRooms("TG60", false, new LatLng(51.588589, -0.229809), 3, TOWNHALL, "Office"));
        list.add(new SolrRooms("TG60A", false, new LatLng(51.588592, -0.229754), 3, TOWNHALL, "Office"));
        list.add(new SolrRooms("TG61", false, new LatLng(51.588575, -0.229713), 3, TOWNHALL, "Office"));
        list.add(new SolrRooms("TG62", false, new LatLng(51.588575, -0.229714), 3, TOWNHALL, "Office"));
        list.add(new SolrRooms("TG63", false, new LatLng(51.588577, -0.229704), 3, TOWNHALL, "Office"));
        list.add(new SolrRooms("TG64", false, new LatLng(51.588577, -0.229704), 3, TOWNHALL, "Office"));
        list.add(new SolrRooms("TG65", false, new LatLng(51.588529, -0.229531), 3, TOWNHALL, "Office"));
        list.add(new SolrRooms("TG66", false, new LatLng(51.588475, -0.229383), 3, TOWNHALL, "Office"));
        list.add(new SolrRooms("TG67", false, new LatLng(51.588461, -0.229330), 3, TOWNHALL, "Office"));
        list.add(new SolrRooms("TG68", false, new LatLng(51.588446, -0.229276), 3, TOWNHALL, "Office"));
        list.add(new SolrRooms("TG69", false, new LatLng(51.588424, -0.229216), 3, TOWNHALL, "Office"));
        list.add(new SolrRooms("TG70", false, new LatLng(51.588408, -0.229154), 3, TOWNHALL, "Office"));
        list.add(new SolrRooms("TG71", false, new LatLng(51.588401, -0.229119), 3, TOWNHALL, "Office"));
        list.add(new SolrRooms("TG72", false, new LatLng(51.588388, -0.229071), 3, TOWNHALL, "Office"));
        list.add(new SolrRooms("TG73", false, new LatLng(51.588348, -0.228988), 3, TOWNHALL, "Office"));
        list.add(new SolrRooms("TG74", false, new LatLng(51.588348, -0.228988), 3, TOWNHALL, "Middlesex"));

        /*Town Hall 1st floor*/

        list.add(new SolrRooms("T106", false, new LatLng(51.588196, -0.229352), 2, TOWNHALL, "Office"));
        list.add(new SolrRooms("T107", false, new LatLng(51.588196, -0.229358), 2, TOWNHALL, "Office"));
        list.add(new SolrRooms("T108", false, new LatLng(51.588196, -0.229352), 2, TOWNHALL, "Office"));
        list.add(new SolrRooms("T109", false, new LatLng(51.588202, -0.229428), 2, TOWNHALL, "Office"));
        list.add(new SolrRooms("T110", false, new LatLng(51.588205, -0.229479), 2, TOWNHALL, "Office"));
        list.add(new SolrRooms("T110A", false, new LatLng(51.588205, -0.229479), 2, TOWNHALL, "Office"));
        list.add(new SolrRooms("T111", false, new LatLng(51.588213, -0.229546), 2, TOWNHALL, "Office"));
        list.add(new SolrRooms("T112", false, new LatLng(51.588219, -0.229610), 2, TOWNHALL, "Office"));
        list.add(new SolrRooms("T113", false, new LatLng(51.588233, -0.229704), 2, TOWNHALL, "Office"));
        list.add(new SolrRooms("T114", false, new LatLng(51.588233, -0.229706), 2, TOWNHALL, "Office"));
        list.add(new SolrRooms("T115", false, new LatLng(51.588248, -0.230010), 2, TOWNHALL, "Office"));
        list.add(new SolrRooms("T116", false, new LatLng(51.588248, -0.230010), 2, TOWNHALL, "Office"));
        list.add(new SolrRooms("T116A", false, new LatLng(51.588248, -0.230010), 2, TOWNHALL, "Office"));
        list.add(new SolrRooms("T117", false, new LatLng(51.588333, -0.229968), 2, TOWNHALL, "Office"));
        list.add(new SolrRooms("T118", false, new LatLng(51.588333, -0.229969), 2, TOWNHALL, "Office"));
        list.add(new SolrRooms("T120", false, new LatLng(51.588333, -0.229971), 2, TOWNHALL, "Office"));
        list.add(new SolrRooms("T121", false, new LatLng(51.588333, -0.229970), 2, TOWNHALL, "Office"));
        list.add(new SolrRooms("T122", false, new LatLng(51.588233, -0.229707), 2, TOWNHALL, "Office"));
        list.add(new SolrRooms("T123", false, new LatLng(51.588233, -0.229705), 2, TOWNHALL, "Office"));
        list.add(new SolrRooms("T124", false, new LatLng(51.588233, -0.229703), 2, TOWNHALL, "Office"));
        list.add(new SolrRooms("T125", false, new LatLng(51.588219, -0.229611), 2, TOWNHALL, "Office"));
        list.add(new SolrRooms("T126", false, new LatLng(51.588219, -0.229609), 2, TOWNHALL, "Office"));
        list.add(new SolrRooms("T127", false, new LatLng(51.588213, -0.229546), 2, TOWNHALL, "Office"));
        list.add(new SolrRooms("T129", false, new LatLng(51.588202, -0.229428), 2, TOWNHALL, "Office"));
        list.add(new SolrRooms("T130", false, new LatLng(51.588202, -0.229428), 2, TOWNHALL, "Office"));
        list.add(new SolrRooms("T131", false, new LatLng(51.588196, -0.229359), 2, TOWNHALL, "Office"));
        list.add(new SolrRooms("T143", false, new LatLng(51.588493, -0.229904), 2, TOWNHALL, "Office"));
        list.add(new SolrRooms("T145", false, new LatLng(51.588493, -0.229904), 2, TOWNHALL, "Office"));
        list.add(new SolrRooms("T146", false, new LatLng(51.588493, -0.229904), 2, TOWNHALL, "Office"));
        list.add(new SolrRooms("T147", false, new LatLng(51.588493, -0.229904), 2, TOWNHALL, "Office"));
        list.add(new SolrRooms("T148", false, new LatLng(51.588535, -0.229852), 2, TOWNHALL, "Office"));
        list.add(new SolrRooms("T149", false, new LatLng(51.588535, -0.229852), 2, TOWNHALL, "Office"));
        list.add(new SolrRooms("T150", false, new LatLng(51.588535, -0.229852), 2, TOWNHALL, "Office"));
        list.add(new SolrRooms("T157", false, new LatLng(51.588535, -0.229852), 2, TOWNHALL, "Office"));

        /*Town Hall 2nd floor*/

        list.add(new SolrRooms("T201", false, new LatLng(51.588196, -0.229356), 1, TOWNHALL, "Office"));
        list.add(new SolrRooms("T202", false, new LatLng(51.588196, -0.229357), 1, TOWNHALL, "Office"));
        list.add(new SolrRooms("T203", false, new LatLng(51.588196, -0.229355), 1, TOWNHALL, "Office"));
        list.add(new SolrRooms("T204", false, new LatLng(51.588196, -0.229354), 1, TOWNHALL, "Office"));
        list.add(new SolrRooms("T205", false, new LatLng(51.588196, -0.229353), 1, TOWNHALL, "Office"));
        list.add(new SolrRooms("T207", false, new LatLng(51.588348, -0.229978), 1, TOWNHALL, "Office"));
        list.add(new SolrRooms("T209", false, new LatLng(51.588348, -0.229978), 1, TOWNHALL, "Office"));
        list.add(new SolrRooms("T210", false, new LatLng(51.588348, -0.229978), 1, TOWNHALL, "Office"));
        list.add(new SolrRooms("T211", false, new LatLng(51.588348, -0.229978), 1, TOWNHALL, "Office"));
        list.add(new SolrRooms("T303", false, new LatLng(51.588348, -0.229978), 0, TOWNHALL, "Office"));
        list.add(new SolrRooms("T304", false, new LatLng(51.588348, -0.229978), 0, TOWNHALL, "Office"));
        list.add(new SolrRooms("T305", false, new LatLng(51.588348, -0.229978), 0, TOWNHALL, "Office"));

        return list;
    }

}
