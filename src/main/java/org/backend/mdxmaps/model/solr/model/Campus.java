package org.backend.mdxmaps.model.solr.model;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import org.backend.mdxmaps.model.LatLng;
import org.backend.mdxmaps.services.util.CustomRoomJSONSerializerForSolrIndexing;

import java.util.ArrayList;

import static org.backend.mdxmaps.model.Routing.BARN;
import static org.backend.mdxmaps.model.Routing.BUILDING10;
import static org.backend.mdxmaps.model.Routing.BUILDING9;
import static org.backend.mdxmaps.model.Routing.CIRCLE_CAFE;
import static org.backend.mdxmaps.model.Routing.COLLEGE;
import static org.backend.mdxmaps.model.Routing.GROVE_BLOCK_A;
import static org.backend.mdxmaps.model.Routing.GROVE_BLOCK_B;
import static org.backend.mdxmaps.model.Routing.GROVE_BLOCK_C;
import static org.backend.mdxmaps.model.Routing.HATCHCROFT;
import static org.backend.mdxmaps.model.Routing.MDXHOUSE;
import static org.backend.mdxmaps.model.Routing.PORTAKABIN_2;
import static org.backend.mdxmaps.model.Routing.PORTAKABIN_6_7;
import static org.backend.mdxmaps.model.Routing.PORTAKABIN_8;
import static org.backend.mdxmaps.model.Routing.PORTAKABIN_A;
import static org.backend.mdxmaps.model.Routing.PORTAKABIN_B;
import static org.backend.mdxmaps.model.Routing.RAVENSFIELDS;
import static org.backend.mdxmaps.model.Routing.SHEPPARDLIBRARY;
import static org.backend.mdxmaps.model.Routing.TOWNHALL;
import static org.backend.mdxmaps.model.Routing.VINE;
import static org.backend.mdxmaps.model.Routing.WILLIAMS;

/**
 * Created by Emmanuel Keboh on 11/03/2017.
 */
@JsonSerialize(using = CustomRoomJSONSerializerForSolrIndexing.class)
public class Campus {

    private String name;
    private LatLng latLng;
    private String level;
    private int gMapLevel;
    private String building;
    private String description;
    private boolean isDirectionsAvailable;


    public Campus(String name, boolean isDirectionsAvailable, LatLng latLng, String level, int gMapLevel, String building,
                  String description) {
        this.name = name;
        this.isDirectionsAvailable = isDirectionsAvailable;
        this.latLng = latLng;
        this.level = level;
        this.gMapLevel = gMapLevel;
        this.building = building;
        this.description = description;
    }

    public String getName() {
        return name;
    }

    public LatLng getLatLng() {
        return latLng;
    }

    public String getLevel() {
        return level;
    }

    public int getgMapLevel() {
        return gMapLevel;
    }

    public String getBuilding() {
        return building;
    }

    public String getDescription() {
        return description;
    }

    public boolean isDirectionsAvailable() {
        return isDirectionsAvailable;
    }

    public static ArrayList<Campus> getRoomsForSolr() {
        ArrayList<Campus> list = new ArrayList<>();

        /*College Building ground floor*/

        list.add(new Campus("College Building", true, new LatLng(51.589825, -0.2288390), "Ground Floor", 2, null, "Main Building"));
        list.add(new Campus("Quad", true, new LatLng(51.589825, -0.228839), "Ground Floor", 2, COLLEGE, "Rickett Quadrangle"));
        list.add(new Campus("CG03", true, new LatLng(51.589537, -0.228467), "Ground Floor", 2, COLLEGE, "Seminar Room"));
        list.add(new Campus("CG04", true, new LatLng(51.589553, -0.228542), "Ground Floor", 2, COLLEGE, "Seminar Room"));
        list.add(new Campus("CG06", true, new LatLng(51.589565, -0.228646), "Ground Floor", 2, COLLEGE, "Seminar Room"));
        list.add(new Campus("CG07", true, new LatLng(51.589579, -0.228760), "Ground Floor", 2, COLLEGE, "Employability And Careers Offices"));
        list.add(new Campus("CG08", true, new LatLng(51.589586, -0.228841), "Ground Floor", 2, COLLEGE, "Seminar Room"));
        list.add(new Campus("CG09", true, new LatLng(51.589587, -0.228912), "Ground Floor", 2, COLLEGE, "Seminar Room"));
        list.add(new Campus("CG10", true, new LatLng(51.589602, -0.228994), "Ground Floor", 2, COLLEGE, "Seminar Room"));
        list.add(new Campus("CG11", true, new LatLng(51.589609, -0.229090), "Ground Floor", 2, COLLEGE, "Seminar Room"));
        list.add(new Campus("CG12A", true, new LatLng(51.589629, -0.229230), "Ground Floor", 2, COLLEGE, "Seminar Room"));
        list.add(new Campus("CG12B", true, new LatLng(51.589641, -0.229313), "Ground Floor", 2, COLLEGE, "Seminar Room"));
        list.add(new Campus("CG13", true, new LatLng(51.589655, -0.229430), "Ground Floor", 2, COLLEGE, "Seminar Room"));
        list.add(new Campus("CG14", true, new LatLng(51.589667, -0.229564), "Ground Floor", 2, COLLEGE, "Seminar Room"));
        list.add(new Campus("CG15", true, new LatLng(51.589720, -0.229247), "Ground Floor", 2, COLLEGE, "Classroom / Seminar Room"));
        list.add(new Campus("CG16", true, new LatLng(51.589747, -0.229381), "Ground Floor", 2, COLLEGE, "Classroom / Seminar Room"));
        list.add(new Campus("CG30", true, new LatLng(51.589744, -0.229049), "Ground Floor", 2, COLLEGE, "Female Toilet 1"));
        list.add(new Campus("CG36", true, new LatLng(51.589959, -0.228987), "Ground Floor", 2, COLLEGE, "Male Toilet 1"));
        list.add(new Campus("CG37", true, new LatLng(51.589917, -0.229037), "Ground Floor", 2, COLLEGE, "Disabled Toilet 1"));
        list.add(new Campus("CG41", true, new LatLng(51.590110, -0.229381), "Ground Floor", 2, COLLEGE, "Lecture Theatre"));
        list.add(new Campus("CG43", true, new LatLng(51.590093, -0.229185), "Ground Floor", 2, COLLEGE, "Classroom"));
        list.add(new Campus("CG45", true, new LatLng(51.590079, -0.229100), "Ground Floor", 2, COLLEGE, "Classroom"));
        list.add(new Campus("CG47", true, new LatLng(51.590051, -0.228848), "Ground Floor", 2, COLLEGE, "Classroom"));
        list.add(new Campus("CG48", true, new LatLng(51.590032, -0.228668), "Ground Floor", 2, COLLEGE, "Classroom"));
        list.add(new Campus("CG49", true, new LatLng(51.590021, -0.228557), "Ground Floor", 2, COLLEGE, "Micro-Lab"));
        list.add(new Campus("CG51", true, new LatLng(51.590002, -0.228381), "Ground Floor", 2, COLLEGE, "Classroom"));
        list.add(new Campus("CG57", false, new LatLng(51.589920, -0.228389), "Ground Floor", 2, COLLEGE, "Security Reception"));
        list.add(new Campus("CG58", false, new LatLng(51.589860, -0.228261), "Ground Floor", 2, COLLEGE, "First Aid Room"));
        list.add(new Campus("CG60", true, new LatLng(51.589907, -0.229541), "Ground Floor", 2, COLLEGE, "Dance Room"));
        list.add(new Campus("CG62", true, new LatLng(51.589977, -0.229384), "Ground Floor", 2, COLLEGE, "Classroom"));
        list.add(new Campus("CG62 (Disabled Access)", true, new LatLng(51.589977, -0.229384), "Ground Floor", 2, COLLEGE, "Classroom"));
        list.add(new Campus("CG76", true, new LatLng(51.589946, -0.229180), "Ground Floor", 2, COLLEGE, "Lecture Theatre"));
        list.add(new Campus("CG77", true, new LatLng(51.589827, -0.229210), "Ground Floor", 2, COLLEGE, "Lecture Theatre"));
        list.add(new Campus("CG82", false, new LatLng(51.589949, -0.227878), "Ground Floor", 2, COLLEGE, "Classroom"));
        list.add(new Campus("CG83", false, new LatLng(51.589957, -0.228055), "Ground Floor", 2, COLLEGE, "Meeting Room"));
        list.add(new Campus("CG84", false, new LatLng(51.590020, -0.227944), "Ground Floor", 2, COLLEGE, "Meeting Room"));
        list.add(new Campus("CG81A", false, new LatLng(51.590006, -0.227898), "Ground Floor", 2, COLLEGE, "Male Toilet 2"));
        list.add(new Campus("CG81B", false, new LatLng(51.589993, -0.227875), "Ground Floor", 2, COLLEGE, "Female Toilet 2"));
        list.add(new Campus("Costa (Quad)", true, new LatLng(51.589755, -0.228960), "Ground Floor", 2, COLLEGE, "Quad"));
        /*College building first floor*/

        list.add(new Campus("C101", true, new LatLng(51.589664, -0.228424), "First Floor", 1, COLLEGE, "Classroom"));
        list.add(new Campus("C104", true, new LatLng(51.589538, -0.228474), "First Floor", 1, COLLEGE, "Classroom"));
        list.add(new Campus("C105", true, new LatLng(51.589559, -0.228601), "First Floor", 1, COLLEGE, "Classroom"));
        list.add(new Campus("C106", true, new LatLng(51.589583, -0.228782), "First Floor", 1, COLLEGE, "Classroom"));
        list.add(new Campus("C107", true, new LatLng(51.589593, -0.228890), "First Floor", 1, COLLEGE, "Classroom"));
        list.add(new Campus("C109", true, new LatLng(51.589610, -0.229053), "First Floor", 1, COLLEGE, "Classroom"));
        list.add(new Campus("C110", true, new LatLng(51.589631, -0.229244), "First Floor", 1, COLLEGE, "Classroom"));
        list.add(new Campus("C111", true, new LatLng(51.589644, -0.229346), "First Floor", 1, COLLEGE, "Classroom"));
        list.add(new Campus("C113", true, new LatLng(51.589666, -0.229552), "First Floor", 1, COLLEGE, "Classroom"));
        list.add(new Campus("C114", true, new LatLng(51.589738, -0.229041), "First Floor", 1, COLLEGE, "Lecture Theatre"));
        list.add(new Campus("C115", true, new LatLng(51.589960, -0.228992), "First Floor", 1, COLLEGE, "Lecture Theatre"));
        list.add(new Campus("C117", true, new LatLng(51.589761, -0.229514), "First Floor", 1, COLLEGE, "Micro Lab"));
        list.add(new Campus("C118", true, new LatLng(51.589812, -0.229499), "First Floor", 1, COLLEGE, "Micro Lab"));
        list.add(new Campus("C120", true, new LatLng(51.589897, -0.229477), "First Floor", 1, COLLEGE, "Micro Lab"));
        list.add(new Campus("C121", true, new LatLng(51.589954, -0.229458), "First Floor", 1, COLLEGE, "Micro Lab"));
        list.add(new Campus("C122", true, new LatLng(51.590113, -0.229401), "First Floor", 1, COLLEGE, "Classroom"));
        list.add(new Campus("C126", true, new LatLng(51.590099, -0.229252), "First Floor", 1, COLLEGE, "Classroom"));
        list.add(new Campus("C127", true, new LatLng(51.590088, -0.229185), "First Floor", 1, COLLEGE, "Classroom"));
        list.add(new Campus("C128", true, new LatLng(51.590075, -0.229104), "First Floor", 1, COLLEGE, "Classroom"));
        list.add(new Campus("C131", true, new LatLng(51.590048, -0.228805), "First Floor", 1, COLLEGE, "Micro Lab"));
        list.add(new Campus("C132", true, new LatLng(51.590037, -0.228692), "First Floor", 1, COLLEGE, "Micro Lab"));
        list.add(new Campus("C135", true, new LatLng(51.590006, -0.228474), "First Floor", 1, COLLEGE, "Data Communication Laboratory"));
        list.add(new Campus("C136", true, new LatLng(51.589998, -0.228371), "First Floor", 1, COLLEGE, "Data Communication Laboratory"));
        list.add(new Campus("C138", true, new LatLng(51.589873, -0.228357), "First Floor", 1, COLLEGE, "Cisco Networking Laboratory"));

        /*College building Second floor*/

        list.add(new Campus("C204", true, new LatLng(51.589542, -0.228469), "Second Floor", 0, COLLEGE, "Classroom"));
        list.add(new Campus("C205", true, new LatLng(51.589554, -0.228562), "Second Floor", 0, COLLEGE, "Classroom"));
        list.add(new Campus("C206", true, new LatLng(51.589566, -0.228654), "Second Floor", 0, COLLEGE, "Classroom"));
        list.add(new Campus("C207", true, new LatLng(51.589575, -0.228773), "Second Floor", 0, COLLEGE, "Classroom"));
        list.add(new Campus("C209", true, new LatLng(51.589609, -0.229042), "Second Floor", 0, COLLEGE, "Classroom"));
        list.add(new Campus("C210", true, new LatLng(51.589737, -0.229047), "Second Floor", 0, COLLEGE, "Classroom"));
        list.add(new Campus("C211", true, new LatLng(51.589947, -0.228999), "Second Floor", 0, COLLEGE, "Classroom"));
        list.add(new Campus("C212", true, new LatLng(51.589735, -0.229268), "Second Floor", 0, COLLEGE, "Classroom"));
        list.add(new Campus("C213A", true, new LatLng(51.589788, -0.229260), "Second Floor", 0, COLLEGE, "Classroom"));
        list.add(new Campus("C213B", true, new LatLng(51.589828, -0.229246), "Second Floor", 0, COLLEGE, "Classroom"));
        list.add(new Campus("C214", true, new LatLng(51.589885, -0.229224), "Second Floor", 0, COLLEGE, "Classroom"));
        list.add(new Campus("C215", true, new LatLng(51.589953, -0.229189), "Second Floor", 0, COLLEGE, "Classroom"));
        list.add(new Campus("C216B", true, new LatLng(51.590029, -0.229170), "Second Floor", 0, COLLEGE, "Classroom"));
        list.add(new Campus("C217", true, new LatLng(51.590057, -0.228902), "Second Floor", 0, COLLEGE, "Classroom"));
        list.add(new Campus("C218", true, new LatLng(51.590048, -0.228790), "Second Floor", 0, COLLEGE, "Classroom"));

        /*HatchCroft Building Ground Floor*/

        list.add(new Campus("Hatchcroft Building", true, new LatLng(51.589154, -0.229178), "Ground Floor", 2, null, "Science & Technology"));
        list.add(new Campus("HG01", true, new LatLng(51.589178, -0.229000), "Ground Floor", 2, HATCHCROFT, "Spec Lab"));
        list.add(new Campus("HG02", true, new LatLng(51.589164, -0.228829), "Ground Floor", 2, HATCHCROFT, "Spec Lab"));
        list.add(new Campus("HG03", true, new LatLng(51.589162, -0.228700), "Ground Floor", 2, HATCHCROFT, "Spec Lab"));
        list.add(new Campus("HG04", true, new LatLng(51.589119, -0.228704), "Ground Floor", 2, HATCHCROFT, "Spec Lab"));
        list.add(new Campus("HG05", true, new LatLng(51.589117, -0.228645), "Ground Floor", 2, HATCHCROFT, "Spec Lab"));
        list.add(new Campus("HG06", true, new LatLng(51.589137, -0.228572), "Ground Floor", 2, HATCHCROFT, "Spec Lab"));
        list.add(new Campus("HG07", true, new LatLng(51.589041, -0.228589), "Ground Floor", 2, HATCHCROFT, "Spec Lab"));
        list.add(new Campus("HG08", true, new LatLng(51.589041, -0.228589), "Ground Floor", 2, HATCHCROFT, "Disabled Toilet 1"));
        list.add(new Campus("HG09", true, new LatLng(51.589056, -0.228697), "Ground Floor", 2, HATCHCROFT, "Spec Lab"));
        list.add(new Campus("HG10", true, new LatLng(51.589067, -0.228812), "Ground Floor", 2, HATCHCROFT, "Office"));
        list.add(new Campus("HG11", true, new LatLng(51.589093, -0.228873), "Ground Floor", 2, HATCHCROFT, "Spec Lab"));
        list.add(new Campus("HG12", true, new LatLng(51.589062, -0.228885), "Ground Floor", 2, HATCHCROFT, "Spec Lab"));
        list.add(new Campus("HG13", true, new LatLng(51.589089, -0.229008), "Ground Floor", 2, HATCHCROFT, "Spec Lab"));
        list.add(new Campus("HG14", true, new LatLng(51.589093, -0.229159), "Ground Floor", 2, HATCHCROFT, "Spec Lab"));
        list.add(new Campus("HG19", true, new LatLng(51.589023, -0.229431), "Ground Floor", 2, HATCHCROFT, "Lecture Theatre"));
        list.add(new Campus("HG20", true, new LatLng(51.589106, -0.229287), "Ground Floor", 2, HATCHCROFT, "Female Toilet"));
        list.add(new Campus("HG21", true, new LatLng(51.589146, -0.229289), "Ground Floor", 2, HATCHCROFT, "Disabled Toilet 1"));
        list.add(new Campus("HG24", true, new LatLng(51.589159, -0.229675), "Ground Floor", 2, HATCHCROFT, "Spec Lab"));
        list.add(new Campus("HG27", true, new LatLng(51.589129, -0.229532), "Ground Floor", 2, HATCHCROFT, "Spec Lab"));
        list.add(new Campus("HG28", true, new LatLng(51.589136, -0.229463), "Ground Floor", 2, HATCHCROFT, "Spec Lab"));
        list.add(new Campus("HG29", true, new LatLng(51.589258, -0.229653), "Ground Floor", 2, HATCHCROFT, "Spec Lab"));
        list.add(new Campus("HG30", true, new LatLng(51.589249, -0.229542), "Ground Floor", 2, HATCHCROFT, "Spec Lab"));
        list.add(new Campus("HG31", true, new LatLng(51.589235, -0.229428), "Ground Floor", 2, HATCHCROFT, "Spec Lab"));
        list.add(new Campus("HG33", true, new LatLng(51.589206, -0.229330), "Ground Floor", 2, HATCHCROFT, "Spec Lab"));

        /*HatchCroft Building 1st floor*/

        list.add(new Campus("H101", true, new LatLng(51.589206, -0.229330), "First Floor", 1, HATCHCROFT, "Spec Lab"));
        list.add(new Campus("H102", true, new LatLng(51.589177, -0.228925), "First Floor", 1, HATCHCROFT, "Spec Lab"));
        list.add(new Campus("H104", true, new LatLng(51.589149, -0.228774), "First Floor", 1, HATCHCROFT, "Spec Lab"));
        list.add(new Campus("H105", true, new LatLng(51.589138, -0.228608), "First Floor", 1, HATCHCROFT, "Spec Lab"));
        list.add(new Campus("H106", true, new LatLng(51.589041, -0.228647), "First Floor", 1, HATCHCROFT, "Spec Lab"));
        list.add(new Campus("H109", true, new LatLng(51.589075, -0.228880), "First Floor", 1, HATCHCROFT, "Spec Lab"));
        list.add(new Campus("H110", true, new LatLng(51.589075, -0.228880), "First Floor", 1, HATCHCROFT, "Spec Lab"));
        list.add(new Campus("H111", true, new LatLng(51.589075, -0.228880), "First Floor", 1, HATCHCROFT, "Spec Lab"));
        list.add(new Campus("H113", true, new LatLng(51.589104, -0.229089), "First Floor", 1, HATCHCROFT, "Spec Lab"));
        list.add(new Campus("H116", true, new LatLng(51.589025, -0.229440), "First Floor", 1, HATCHCROFT, "Lecture Theatre"));
        list.add(new Campus("H117", true, new LatLng(51.588963, -0.229356), "First Floor", 1, HATCHCROFT, "Spec Lab"));
        list.add(new Campus("H118", true, new LatLng(51.588968, -0.229395), "First Floor", 1, HATCHCROFT, "Spec Lab"));
        list.add(new Campus("H119", true, new LatLng(51.588972, -0.229433), "First Floor", 1, HATCHCROFT, "Spec Lab"));
        list.add(new Campus("H120", true, new LatLng(51.588982, -0.229475), "First Floor", 1, HATCHCROFT, "Spec Lab"));
        list.add(new Campus("H121", true, new LatLng(51.588984, -0.229518), "First Floor", 1, HATCHCROFT, "Spec Lab"));
        list.add(new Campus("H122", true, new LatLng(51.588989, -0.229552), "First Floor", 1, HATCHCROFT, "Spec Lab"));
        list.add(new Campus("H123", true, new LatLng(51.589007, -0.229543), "First Floor", 1, HATCHCROFT, "Spec Lab"));
        list.add(new Campus("H124", true, new LatLng(51.589044, -0.229535), "First Floor", 1, HATCHCROFT, "Spec Lab"));
        list.add(new Campus("H125", true, new LatLng(51.589079, -0.229523), "First Floor", 1, HATCHCROFT, "Spec Lab"));
        list.add(new Campus("H128", true, new LatLng(51.589096, -0.229294), "First Floor", 1, HATCHCROFT, "Male Toilet"));
        list.add(new Campus("H129", true, new LatLng(51.589144, -0.229290), "First Floor", 1, HATCHCROFT, "Disabled Toilet"));
        list.add(new Campus("H133", true, new LatLng(51.589174, -0.229586), "First Floor", 1, HATCHCROFT, "Spec Lab"));
        list.add(new Campus("H135", true, new LatLng(51.589140, -0.229645), "First Floor", 1, HATCHCROFT, "Spec Lab"));
        list.add(new Campus("H136", true, new LatLng(51.589154, -0.229735), "First Floor", 1, HATCHCROFT, "Spec Lab"));
        list.add(new Campus("H137", true, new LatLng(51.589254, -0.229653), "First Floor", 1, HATCHCROFT, "Spec Lab"));
        list.add(new Campus("H139", true, new LatLng(51.589241, -0.229522), "First Floor", 1, HATCHCROFT, "Spec Lab"));
        list.add(new Campus("H141A", true, new LatLng(51.589231, -0.229414), "First Floor", 1, HATCHCROFT, "Spec Lab"));
        list.add(new Campus("H141B", true, new LatLng(51.589217, -0.229299), "First Floor", 1, HATCHCROFT, "Spec Lab"));

        /*HatchCroft Building 2nd floor*/

        list.add(new Campus("H201", true, new LatLng(51.589178, -0.229018), "Second Floor", 0, HATCHCROFT, "Wet Lab"));
        list.add(new Campus("H202", true, new LatLng(51.589169, -0.228898), "Second Floor", 0, HATCHCROFT, "Dry Lab"));
        list.add(new Campus("H203", true, new LatLng(51.589150, -0.228760), "Second Floor", 0, HATCHCROFT, "Skills Lab"));
        list.add(new Campus("H205", true, new LatLng(51.589136, -0.228607), "Second Floor", 0, HATCHCROFT, "Skills Lab"));
        list.add(new Campus("H206", true, new LatLng(51.589042, -0.228599), "Second Floor", 0, HATCHCROFT, "CMH Lab"));
        list.add(new Campus("H207", true, new LatLng(51.589042, -0.228687), "Second Floor", 0, HATCHCROFT, "Clinical Skills Lab"));
        list.add(new Campus("H208", true, new LatLng(51.589071, -0.228866), "Second Floor", 0, HATCHCROFT, "Clinical Skills Lab"));
        list.add(new Campus("H209", true, new LatLng(51.589093, -0.229019), "Second Floor", 0, HATCHCROFT, "Spec Lab"));
        list.add(new Campus("H211", true, new LatLng(51.589111, -0.229161), "Second Floor", 0, HATCHCROFT, "Female Toilet"));
        list.add(new Campus("H216", true, new LatLng(51.589116, -0.229427), "Second Floor", 0, HATCHCROFT, "Spec Lab"));
        list.add(new Campus("H217", true, new LatLng(51.589118, -0.229537), "Second Floor", 0, HATCHCROFT, "Spec Lab"));
        list.add(new Campus("H219", true, new LatLng(51.589162, -0.229576), "Second Floor", 0, HATCHCROFT, "Spec Lab"));
        list.add(new Campus("H220", true, new LatLng(51.589158, -0.229686), "Second Floor", 0, HATCHCROFT, "Spec Lab"));
        list.add(new Campus("H221", true, new LatLng(51.589255, -0.229642), "Second Floor", 0, HATCHCROFT, "Spec Lab"));
        list.add(new Campus("H222", true, new LatLng(51.589243, -0.229572), "Second Floor", 0, HATCHCROFT, "Spec Lab"));
        list.add(new Campus("H223", true, new LatLng(51.589237, -0.229476), "Second Floor", 0, HATCHCROFT, "Spec Lab"));
        list.add(new Campus("H224", true, new LatLng(51.589226, -0.229364), "Second Floor", 0, HATCHCROFT, "Spec Lab"));

        /*Building 9*/

        list.add(new Campus("Building 9", true, new LatLng(51.588709, -0.229398), "Ground Floor", 0, null, "Building 9 Description"));
        list.add(new Campus("BG01", true, new LatLng(51.588697, -0.229558), "Ground Floor", 0, BUILDING9, "Classroom"));
        list.add(new Campus("BG02", true, new LatLng(51.588771, -0.229495), "Ground Floor", 0, BUILDING9, "Classroom"));
        list.add(new Campus("BG03", true, new LatLng(51.588709, -0.229460), "Ground Floor", 0, BUILDING9, "Toilet"));
        list.add(new Campus("BG04", true, new LatLng(51.588738, -0.229438), "Ground Floor", 0, BUILDING9, "Disabled Toilet"));
        list.add(new Campus("BG05", true, new LatLng(51.588796, -0.229342), "Ground Floor", 0, BUILDING9, "ICT Suite"));
        list.add(new Campus("BG07", true, new LatLng(51.588698, -0.229325), "Ground Floor", 0, BUILDING9, "Meeting Room"));
        list.add(new Campus("BG10", true, new LatLng(51.588647, -0.229382), "Ground Floor", 0, BUILDING9, "Classroom"));
        list.add(new Campus("BG11", true, new LatLng(51.588661, -0.229456), "Ground Floor", 0, BUILDING9, "Hub Breakout"));

        /*Building 10 ground floor*/

        list.add(new Campus("Building 10", true, new LatLng(51.589837, -0.229845), "Ground Floor", 1, null, "Building 10 Description"));

        list.add(new Campus("BTG01", true, new LatLng(51.589815, -0.229934), "Ground Floor", 1, BUILDING10, "Simulation Room A"));
        list.add(new Campus("BTG02", true, new LatLng(51.589858, -0.229918), "Ground Floor", 1, BUILDING10, "Store Room"));
        list.add(new Campus("BTG04", true, new LatLng(51.589845, -0.229755), "Ground Floor", 1, BUILDING10, "Simulation Room B"));

        /*Building 10 first floor*/

        list.add(new Campus("BT101", true, new LatLng(51.589825, -0.229769), "First Floor", 0, BUILDING10, "CPR Skills Room"));
        list.add(new Campus("BT103", true, new LatLng(51.589849, -0.229926), "First Floor", 0, BUILDING10, "CPR Skills Room"));

        /*Portakabin 2 ground floor*/

        list.add(new Campus("Portakabin 2", false, new LatLng(51.589994, -0.229832), "Ground Level", 1, null, "Portakabin 2 Description"));
        list.add(new Campus("P2G01", false, new LatLng(51.589963, -0.229843), "Ground Level", 0, PORTAKABIN_2, "Recital Room"));
        list.add(new Campus("P2G02", false, new LatLng(51.590010, -0.229825), "Ground Level", 0, PORTAKABIN_2, "Recital Room"));
        list.add(new Campus("P2G03", false, new LatLng(51.589982, -0.229734), "Ground Level", 0, PORTAKABIN_2, "Recital Room"));
        list.add(new Campus("P2G03A", false, new LatLng(51.590004, -0.229729), "Ground Level", 0, PORTAKABIN_2, "Recital Room"));
        list.add(new Campus("P2G04", false, new LatLng(51.589960, -0.229746), "Ground Level", 0, PORTAKABIN_2, "Recital Room"));
        list.add(new Campus("P2G05", false, new LatLng(51.589941, -0.229762), "Ground Level", 0, PORTAKABIN_2, "Recital Room"));

        /*Portakabin 6 & 7 ground floor*/

        list.add(new Campus("Portakabin 6 & 7", true, new LatLng(51.589662, -0.229930), "Ground Level", 1, null, "Portakabin 6 & 7"));
        list.add(new Campus("P6G01", true, new LatLng(51.589661, -0.229960), "Ground Level", 1, PORTAKABIN_6_7, "Classroom"));
        list.add(new Campus("P6G02", true, new LatLng(51.589654, -0.229853), "Ground Level", 1, PORTAKABIN_6_7, "Classroom"));

        /*Portakabin 6 & 7 first floor*/

        list.add(new Campus("P7101", true, new LatLng(51.589653, -0.229856), "First Level", 0, PORTAKABIN_6_7, "Classroom"));
        list.add(new Campus("P7102", true, new LatLng(51.589665, -0.229957), "First Level", 0, PORTAKABIN_6_7, "Classroom"));

        /*Portakabin 8 Ground floor*/
        list.add(new Campus("P8G01", false, new LatLng(51.590704, -0.231243), "Ground Level", 0, PORTAKABIN_8, "Wash Room"));
        list.add(new Campus("P8G02", false, new LatLng(51.590743, -0.231231), "Ground Level", 0, PORTAKABIN_8, "Female Prayer Room"));
        list.add(new Campus("P8G03", false, new LatLng(51.590778, -0.231227), "Ground Level", 0, PORTAKABIN_8, "Male Prayer Room"));

        /*Portakabin A ground floor*/

        list.add(new Campus("Portakabin A", true, new LatLng(51.589795, -0.230213), "Ground Level", 1, null, "Portakabin A Description"));
        list.add(new Campus("PAG01", true, new LatLng(51.589905, -0.230136), "Ground Level", 1, PORTAKABIN_A, "Classroom"));
        list.add(new Campus("PAG02", true, new LatLng(51.589819, -0.230155), "Ground Level", 1, PORTAKABIN_A, "Classroom"));
        list.add(new Campus("PAG03", true, new LatLng(51.589691, -0.230168), "Ground Level", 1, PORTAKABIN_A, "Classroom"));
        list.add(new Campus("PAG04", true, new LatLng(51.589702, -0.230317), "Ground Level", 1, PORTAKABIN_A, "Classroom"));
        list.add(new Campus("PAG05", true, new LatLng(51.589863, -0.230144), "Ground Level", 1, PORTAKABIN_A, "Toilet 1"));
        list.add(new Campus("PAG06", true, new LatLng(51.589863, -0.230144), "Ground Level", 1, PORTAKABIN_A, "Toilet 2"));
        list.add(new Campus("PAG07", true, new LatLng(51.589697, -0.230244), "Ground Level", 1, PORTAKABIN_A, "Toilet 3"));
        list.add(new Campus("PAG08", true, new LatLng(51.589697, -0.230244), "Ground Level", 1, PORTAKABIN_A, "Toilet 4"));

        /*Portakabin A first floor*/

        list.add(new Campus("PA101", true, new LatLng(51.589691, -0.230164), "First Level", 0, PORTAKABIN_A, "Classroom"));
        list.add(new Campus("PA102", true, new LatLng(51.589691, -0.230165), "First Level", 0, PORTAKABIN_A, "Classroom"));
        list.add(new Campus("PA103", true, new LatLng(51.589691, -0.230166), "First Level", 0, PORTAKABIN_A, "Toilet 1"));
        list.add(new Campus("PA104", true, new LatLng(51.589691, -0.230167), "First Level", 0, PORTAKABIN_A, "Toilet 2"));

        /*Portakabin B ground floor*/
        list.add(new Campus("Portakabin B", true, new LatLng(51.590750, -0.229020), "Ground Level", 0, null, "Portakabin B Description"));
        list.add(new Campus("PBG01", true, new LatLng(51.590750, -0.229020), "Ground Level", 0, PORTAKABIN_B, "Classroom"));
        list.add(new Campus("PBG02", true, new LatLng(51.590655, -0.229057), "Ground Level", 0, PORTAKABIN_B, "Classroom"));

        /*Vine ground floor*/

        list.add(new Campus("Vine Building", true, new LatLng(51.590637, -0.230763), "Ground Floor", 1, null, "Vine Building Description"));
        list.add(new Campus("VG01", true, new LatLng(51.590575, -0.230748), "Ground Floor", 1, VINE, "Meeting Room"));
        list.add(new Campus("VG02", true, new LatLng(51.590586, -0.230860), "Ground Floor", 1, VINE, "Meeting Room"));
        list.add(new Campus("VG04", true, new LatLng(51.590717, -0.230900), "Ground Floor", 1, VINE, "Male Toilet"));
        list.add(new Campus("VG05", true, new LatLng(51.590681, -0.230840), "Ground Floor", 1, VINE, "Classroom"));
        list.add(new Campus("VG06", true, new LatLng(51.590670, -0.230756), "Ground Floor", 1, VINE, "Classroom"));
        list.add(new Campus("VG07", true, new LatLng(51.590662, -0.230671), "Ground Floor", 1, VINE, "Classroom"));
        list.add(new Campus("VG08", true, new LatLng(51.590686, -0.230596), "Ground Floor", 1, VINE, "Female Toilet"));

        /*Vine first Floor*/

        list.add(new Campus("V101", true, new LatLng(51.590574, -0.230743), "First Floor", 0, VINE, "Meeting Room"));
        list.add(new Campus("V102", true, new LatLng(51.590590, -0.230856), "First Floor", 0, VINE, "Meeting Room"));
        list.add(new Campus("V103", true, new LatLng(51.590676, -0.230842), "First Floor", 0, VINE, "Classroom"));
        list.add(new Campus("V104", true, new LatLng(51.590671, -0.230755), "First Floor", 0, VINE, "Classroom"));
        list.add(new Campus("V105", true, new LatLng(51.590663, -0.230672), "First Floor", 0, VINE, "Classroom"));

        /*MDX House ground floor*/
        list.add(new Campus("MDX House", true, new LatLng(51.589976, -0.230764), "Ground Floor", 0, null, "MDX House"));

        /*MDX House basement */
        list.add(new Campus("Student Union", true, new LatLng(51.590136, -0.230874), "Basement", 1, MDXHOUSE, "MDXSU"));
        list.add(new Campus("Gym", true, new LatLng(51.589927, -0.230906), "Basement", 1, MDXHOUSE, "Fitness Pod"));
        list.add(new Campus("Real Tennis Club", true, new LatLng(51.589771, -0.230951), "Basement", 1, MDXHOUSE, "Real Tennis Club"));
        list.add(new Campus("Dance Studio", false, new LatLng(51.589735, -0.230543), "Basement", 0, MDXHOUSE, "Dance Studio"));

        /*Sheppard Library*/

        list.add(new Campus("Sheppard Library", true, new LatLng(51.590456, -0.229614), "Ground Floor", 3, null, "Main University Library"));//SET

        /*Sheppard Library basement*/

        list.add(new Campus("SB01", true, new LatLng(51.590661, -0.229612), "Basement", 4, SHEPPARDLIBRARY, "Open Access Area"));
        list.add(new Campus("SB03", true, new LatLng(51.590748, -0.229431), "Basement", 4, SHEPPARDLIBRARY, "Open Access Area"));
        list.add(new Campus("SB05", true, new LatLng(51.590382, -0.229402), "Basement", 4, SHEPPARDLIBRARY, "Teaching Resource Room"));
        list.add(new Campus("SB12A", true, new LatLng(51.590219, -0.229574), "Basement", 4, SHEPPARDLIBRARY, "Material Room"));
        list.add(new Campus("SB16", true, new LatLng(51.590417, -0.229588), "Basement", 4, SHEPPARDLIBRARY, "Material Room"));
        list.add(new Campus("SB19", true, new LatLng(51.590469, -0.229837), "Basement", 4, CIRCLE_CAFE, "Circle Café (Costa Coffee)"));

        /*Sheppard Library ground floor*/

        list.add(new Campus("SG01", true, new LatLng(51.590359, -0.229603), "Ground Floor", 3, SHEPPARDLIBRARY, "Unihelp"));
        list.add(new Campus("SG02", true, new LatLng(51.590316, -0.229317), "Ground Floor", 3, SHEPPARDLIBRARY, "Book Sorting Room"));
        list.add(new Campus("SG09", true, new LatLng(51.590763, -0.229379), "Ground Floor", 3, SHEPPARDLIBRARY, "Disabled Toilet"));
        list.add(new Campus("SG10", true, new LatLng(51.590735, -0.229399), "Ground Floor", 3, SHEPPARDLIBRARY, "Female Toilet"));
        list.add(new Campus("SG11", true, new LatLng(51.590745, -0.229455), "Ground Floor", 3, SHEPPARDLIBRARY, "Male Toilet"));
        list.add(new Campus("SG12A", true, new LatLng(51.590693, -0.229437), "Ground Floor", 3, SHEPPARDLIBRARY, "Group Study Space / IT"));
        list.add(new Campus("SG12B", true, new LatLng(51.590642, -0.229453), "Ground Floor", 3, SHEPPARDLIBRARY, "Group Study Space / IT"));
        list.add(new Campus("SG13A", true, new LatLng(51.590589, -0.229471), "Ground Floor", 3, SHEPPARDLIBRARY, "Group Study Space / IT"));
        list.add(new Campus("SG13B", true, new LatLng(51.590536, -0.229480), "Ground Floor", 3, SHEPPARDLIBRARY, "Group Study Space / IT"));
//        list.add(new Campus("SPG09",new LatLng(51.590238, -0.229348),3,SHEPPARDLIBRARY,"Unihelp Pod"));
//        list.add(new Campus("SPG10",new LatLng(51.590238, -0.229349),3,SHEPPARDLIBRARY,"Unihelp Pod"));
//        list.add(new Campus("SPG11",new LatLng(51.590238, -0.229350),3,SHEPPARDLIBRARY,"Unihelp Pod"));
//        list.add(new Campus("SPG12",new LatLng(51.590238, -0.229351),3,SHEPPARDLIBRARY,"Unihelp Pod"));
//        list.add(new Campus("SPG13",new LatLng(51.590238, -0.229352),3,SHEPPARDLIBRARY,"Unihelp Pod"));
//        list.add(new Campus("SPG14",new LatLng(51.590238, -0.229353),3,SHEPPARDLIBRARY,"Unihelp Pod"));
//        list.add(new Campus("SPG15",new LatLng(51.590238, -0.229354),3,SHEPPARDLIBRARY,"Unihelp Pod"));
//        list.add(new Campus("SPG16",new LatLng(51.590238, -0.229355),3,SHEPPARDLIBRARY,"Unihelp Pod"));
//        list.add(new Campus("SPG17",new LatLng(51.590238, -0.229356),3,SHEPPARDLIBRARY,"Unihelp Pod"));
//        list.add(new Campus("SPG18",new LatLng(51.590238, -0.229357),3,SHEPPARDLIBRARY,"Unihelp Pod"));
//        list.add(new Campus("SPG19",new LatLng(51.590320, -0.229655),3,SHEPPARDLIBRARY,"Unihelp Pod"));
//        list.add(new Campus("SPG20",new LatLng(51.590320, -0.229656),3,SHEPPARDLIBRARY,"Unihelp Pod"));
//        list.add(new Campus("SPG21",new LatLng(51.590320, -0.229657),3,SHEPPARDLIBRARY,"Unihelp Pod"));
//        list.add(new Campus("SPG22",new LatLng(51.590320, -0.229658),3,SHEPPARDLIBRARY,"Unihelp Pod"));

        /*Sheppard Library 1st floor*/

        list.add(new Campus("S101", true, new LatLng(51.590660, -0.229613), "First Floor", 2, SHEPPARDLIBRARY, "Open Access Study Space"));
        list.add(new Campus("S103", true, new LatLng(51.590730, -0.229399), "First Floor", 2, SHEPPARDLIBRARY, "Disabled Toilet"));
        list.add(new Campus("S104", true, new LatLng(51.590730, -0.229399), "First Floor", 2, SHEPPARDLIBRARY, "Female Toilet"));
        list.add(new Campus("S105", true, new LatLng(51.590730, -0.229399), "First Floor", 2, SHEPPARDLIBRARY, "Male Toilet"));
        list.add(new Campus("S106", true, new LatLng(51.590588, -0.229486), "First Floor", 2, SHEPPARDLIBRARY, "Group Study Space / IT"));
        list.add(new Campus("S107", true, new LatLng(51.590641, -0.229456), "First Floor", 2, SHEPPARDLIBRARY, "Group Study Space / IT"));
        list.add(new Campus("S110", true, new LatLng(51.590440, -0.229470), "First Floor", 2, SHEPPARDLIBRARY, "Meeting Room"));
        list.add(new Campus("S111", true, new LatLng(51.590304, -0.229313), "First Floor", 2, SHEPPARDLIBRARY, "Financial Market Lab"));
        list.add(new Campus("S112", true, new LatLng(51.590233, -0.229330), "First Floor", 2, SHEPPARDLIBRARY, "Group Study Space / IT"));
        list.add(new Campus("S118", true, new LatLng(51.590500, -0.229862), "First Floor", 2, SHEPPARDLIBRARY, "Group Study Space / IT"));
        list.add(new Campus("S119", true, new LatLng(51.590417, -0.229655), "First Floor", 2, SHEPPARDLIBRARY, "Open Access / Group Study Space / Study Hub"));

        /*Sheppard Library 2nd floor*/

        list.add(new Campus("S201", true, new LatLng(51.590659, -0.229606), "Second Floor", 1, SHEPPARDLIBRARY, "Open Access Study Space"));
        list.add(new Campus("S203", true, new LatLng(51.590730, -0.229399), "Second Floor", 1, SHEPPARDLIBRARY, "Disabled Toilet"));
        list.add(new Campus("S204", true, new LatLng(51.590730, -0.229399), "Second Floor", 1, SHEPPARDLIBRARY, "Female Toilet"));
        list.add(new Campus("S205", true, new LatLng(51.590730, -0.229399), "Second Floor", 1, SHEPPARDLIBRARY, "Male Toilet"));
        list.add(new Campus("S206", true, new LatLng(51.590694, -0.229433), "Second Floor", 1, SHEPPARDLIBRARY, "Silent Study / PC Room"));
        list.add(new Campus("S215", true, new LatLng(51.590351, -0.229596), "Second Floor", 1, SHEPPARDLIBRARY, "Open Access Silent Study Space"));
        list.add(new Campus("S216", true, new LatLng(51.590306, -0.229326), "Second Floor", 1, SHEPPARDLIBRARY, "Silent Study / PC Room"));
        list.add(new Campus("S217", true, new LatLng(51.590250, -0.229364), "Second Floor", 1, SHEPPARDLIBRARY, "Silent Study / PC Area"));
        list.add(new Campus("S218", true, new LatLng(51.590470, -0.229887), "Second Floor", 1, SHEPPARDLIBRARY, "Bookshelves"));
        list.add(new Campus("S219", true, new LatLng(51.590518, -0.229841), "Second Floor", 1, SHEPPARDLIBRARY, "Student Bookable Group Study Space"));

        /*Sheppard Library 3rd floor*/

        list.add(new Campus("S302", true, new LatLng(51.590365, -0.229577), "Third Floor", 0, SHEPPARDLIBRARY, "Open Access Study Space"));
        list.add(new Campus("S303", true, new LatLng(51.590308, -0.229326), "Third Floor", 0, SHEPPARDLIBRARY, "Silent Study Space Open Area"));
        list.add(new Campus("S304", true, new LatLng(51.590237, -0.229350), "Third Floor", 0, SHEPPARDLIBRARY, "Silent Study Space / IT"));
        list.add(new Campus("S305", true, new LatLng(51.590490, -0.229879), "Third Floor", 0, SHEPPARDLIBRARY, "Silent Study Space / IT"));

        /*Williams Building basement*/
        list.add(new Campus("WB05", false, new LatLng(51.590582, -0.228934), "Basement", 2, WILLIAMS, "Goods In - Post Room"));

        /*Williams Building ground floor*/

        list.add(new Campus("Williams Building", true, new LatLng(51.590478, -0.228681), "Ground Floor", 1, null, "Williams Building Description"));
        list.add(new Campus("WG07", true, new LatLng(51.590502, -0.228898), "Ground Floor", 1, WILLIAMS, "Female Toilet 1"));
        list.add(new Campus("WG32", true, new LatLng(51.590523, -0.228726), "Ground Floor", 1, WILLIAMS, "Meeting Room"));
        list.add(new Campus("WG33", true, new LatLng(51.590528, -0.228629), "Ground Floor", 1, WILLIAMS, "Translation Room"));
        list.add(new Campus("WG35", true, new LatLng(51.590498, -0.228469), "Ground Floor", 1, WILLIAMS, "CDS Printing Room"));
        list.add(new Campus("WG37", true, new LatLng(51.590435, -0.228221), "Ground Floor", 1, WILLIAMS, "Male Toilet"));
        list.add(new Campus("WG38", true, new LatLng(51.590436, -0.228186), "Ground Floor", 1, WILLIAMS, "Disabled Toilet"));
        list.add(new Campus("WG39", true, new LatLng(51.590464, -0.228206), "Ground Floor", 1, WILLIAMS, "Female Toilet 2"));
        list.add(new Campus("WG41", true, new LatLng(51.590615, -0.228059), "Ground Floor", 1, WILLIAMS, "Library @ Williams"));
        list.add(new Campus("WG44", true, new LatLng(51.590599, -0.228180), "Ground Floor", 1, WILLIAMS, "Study Space"));
        list.add(new Campus("WG45", true, new LatLng(51.590599, -0.228181), "Ground Floor", 1, WILLIAMS, "Study Space"));
        list.add(new Campus("WG46", true, new LatLng(51.590599, -0.228182), "Ground Floor", 1, WILLIAMS, "Study Space"));
        list.add(new Campus("WG47", true, new LatLng(51.590608, -0.228236), "Ground Floor", 1, WILLIAMS, "LRS Office"));
        list.add(new Campus("WG48", true, new LatLng(51.590616, -0.228343), "Ground Floor", 1, WILLIAMS, "Classroom"));
        list.add(new Campus("WG49", true, new LatLng(51.590671, -0.228323), "Ground Floor", 1, WILLIAMS, "Classroom"));
        list.add(new Campus("WG50", true, new LatLng(51.590725, -0.228313), "Ground Floor", 1, WILLIAMS, "Classroom"));
        list.add(new Campus("WG51", true, new LatLng(51.590699, -0.228202), "Ground Floor", 1, WILLIAMS, "LRS Meeting Room"));

        /*Williams Building 1st floor*/

        list.add(new Campus("W142", true, new LatLng(51.590486, -0.228347), "First Floor", 0, WILLIAMS, "Classroom"));
        list.add(new Campus("W143", true, new LatLng(51.590420, -0.228196), "First Floor", 0, WILLIAMS, "Disabled Toilet"));
        list.add(new Campus("W144", true, new LatLng(51.590439, -0.228218), "First Floor", 0, WILLIAMS, "Male Toilet"));
        list.add(new Campus("W145", true, new LatLng(51.590463, -0.228206), "First Floor", 0, WILLIAMS, "Female Toilet"));
        list.add(new Campus("W147", true, new LatLng(51.590518, -0.228192), "First Floor", 0, WILLIAMS, "Classroom"));
        list.add(new Campus("W148", true, new LatLng(51.590615, -0.228330), "First Floor", 0, WILLIAMS, "Classroom"));
        list.add(new Campus("W149", true, new LatLng(51.590670, -0.228327), "First Floor", 0, WILLIAMS, "Classroom"));
        list.add(new Campus("W150", true, new LatLng(51.590720, -0.228296), "First Floor", 0, WILLIAMS, "Classroom"));
        list.add(new Campus("W151", true, new LatLng(51.590686, -0.228169), "First Floor", 0, WILLIAMS, "Classroom"));
        list.add(new Campus("W152", true, new LatLng(51.590732, -0.228148), "First Floor", 0, WILLIAMS, "Classroom"));
        list.add(new Campus("W153", true, new LatLng(51.590779, -0.228097), "First Floor", 0, WILLIAMS, "Classroom"));
        list.add(new Campus("W154", true, new LatLng(51.590765, -0.227971), "First Floor", 0, WILLIAMS, "Classroom"));
        list.add(new Campus("W155", true, new LatLng(51.590698, -0.227986), "First Floor", 0, WILLIAMS, "Classroom"));
        list.add(new Campus("W156", true, new LatLng(51.590641, -0.228003), "First Floor", 0, WILLIAMS, "Classroom"));
        list.add(new Campus("W157", true, new LatLng(51.590565, -0.228022), "First Floor", 0, WILLIAMS, "Classroom"));
        list.add(new Campus("W158", true, new LatLng(51.590496, -0.228045), "First Floor", 0, WILLIAMS, "Classroom"));
        list.add(new Campus("W159", true, new LatLng(51.590429, -0.228076), "First Floor", 0, WILLIAMS, "Classroom"));
        list.add(new Campus("W160", true, new LatLng(51.590368, -0.228092), "First Floor", 0, WILLIAMS, "Classroom"));

        /*Barn ground floor*/

        list.add(new Campus("Barn", true, new LatLng(51.590982, -0.228562), "Ground Floor", 0, null, "Barn Description"));
        list.add(new Campus("BAG01", true, new LatLng(51.590944, -0.228581), "Ground Floor", 0, BARN, "Meeting Room"));
        list.add(new Campus("BAG02", true, new LatLng(51.591041, -0.228582), "Ground Floor", 0, BARN, "Classroom"));

        /*Grove Block A Basement*/
        list.add(new Campus("GB01", true, new LatLng(51.588518, -0.230839), "Basement", 5, GROVE_BLOCK_A, "Digital Photography And Reprographics"));
        list.add(new Campus("GB04", true, new LatLng(51.588731, -0.230444), "Basement", 5, GROVE_BLOCK_A, "Digital Photography And Reprographics"));
        list.add(new Campus("GB05", true, new LatLng(51.588670, -0.230553), "Basement", 5, GROVE_BLOCK_A, "Photography"));
        list.add(new Campus("GB07", true, new LatLng(51.588586, -0.230503), "Basement", 5, GROVE_BLOCK_A, "Loading Bay"));
        //list.add(new Campus("GB08",new LatLng(51.588626, -0.230557),5,GROVE_BLOCK_A,"Large Print Room"));
        list.add(new Campus("GB09", true, new LatLng(51.588626, -0.230557), "Basement", 5, GROVE_BLOCK_A, "Loading Bay"));
        list.add(new Campus("GB10", true, new LatLng(51.588626, -0.230557), "Basement", 5, GROVE_BLOCK_A, "Colour Darkroom"));
        list.add(new Campus("GB12", true, new LatLng(51.588531, -0.230564), "Basement", 5, GROVE_BLOCK_A, "Darkroom"));
        list.add(new Campus("GB17A", true, new LatLng(51.588583, -0.230805), "Basement", 5, GROVE_BLOCK_A, "Photography Studio"));
        list.add(new Campus("GB17B", true, new LatLng(51.588560, -0.230701), "Basement", 5, GROVE_BLOCK_A, "Photography Studio"));
        list.add(new Campus("GB19A", true, new LatLng(51.588625, -0.230737), "Basement", 5, GROVE_BLOCK_A, "Photography Studio"));
        list.add(new Campus("GB19B", true, new LatLng(51.588682, -0.230708), "Basement", 5, GROVE_BLOCK_A, "Photography Studio"));
        list.add(new Campus("GB21", true, new LatLng(51.588753, -0.230682), "Basement", 5, GROVE_BLOCK_A, "Disabled Toilet)"));
        list.add(new Campus("GB22", true, new LatLng(51.588753, -0.230682), "Basement", 5, GROVE_BLOCK_A, "Female Toilet"));
        list.add(new Campus("GB23", true, new LatLng(51.588753, -0.230682), "Basement", 5, GROVE_BLOCK_A, "Male Toilet"));

        /*Grove Block A Ground Floor*/
        list.add(new Campus("Grove Block A", true, new LatLng(51.588695, -0.230596), "Ground Floor", 4, null, "Grove Block A Description"));
        list.add(new Campus("GG01", true, new LatLng(51.588774, -0.230414), "Ground Floor", 4, GROVE_BLOCK_A, "Plastic/Wood/Metal Workshop"));
        list.add(new Campus("GG04", true, new LatLng(51.588697, -0.230232), "Ground Floor", 4, GROVE_BLOCK_A, "Plastic/Wood/Metal Workshop"));
        list.add(new Campus("GG07", true, new LatLng(51.588618, -0.230458), "Ground Floor", 4, GROVE_BLOCK_A, "Storage & Dispensing Storage"));
        list.add(new Campus("GG11", true, new LatLng(51.588553, -0.230557), "Ground Floor", 4, GROVE_BLOCK_A, "3D Cad Workshop Laser Room"));
        list.add(new Campus("GG12", true, new LatLng(51.588520, -0.230449), "Ground Floor", 4, GROVE_BLOCK_A, "Cam Machine Room"));
        list.add(new Campus("GG13", true, new LatLng(51.588515, -0.230559), "Ground Floor", 4, GROVE_BLOCK_A, "Cad Lab Room"));
        list.add(new Campus("GG14", true, new LatLng(51.588492, -0.230365), "Ground Floor", 4, GROVE_BLOCK_A, "Spray Room"));
        list.add(new Campus("GG15", true, new LatLng(51.588393, -0.230387), "Ground Floor", 4, GROVE_BLOCK_A, "Ceramics & Glass Workshop"));
        list.add(new Campus("GG17", true, new LatLng(51.588353, -0.230283), "Ground Floor", 4, GROVE_BLOCK_A, "Ceramics Reclaim"));
        list.add(new Campus("GG18", true, new LatLng(51.588395, -0.230519), "Ground Floor", 4, GROVE_BLOCK_A, "Small Metal Workshop"));
        list.add(new Campus("GG20", true, new LatLng(51.588407, -0.230624), "Ground Floor", 4, GROVE_BLOCK_A, "Jewellery Buffers"));
        list.add(new Campus("GG21", true, new LatLng(51.588379, -0.230634), "Ground Floor", 4, GROVE_BLOCK_A, "Jewellery Casting"));
        list.add(new Campus("GG22", true, new LatLng(51.588479, -0.230601), "Ground Floor", 4, GROVE_BLOCK_A, "Disabled Toilet 1"));
        list.add(new Campus("GG23", true, new LatLng(51.588457, -0.230618), "Ground Floor", 4, GROVE_BLOCK_A, "Male Toilet 1"));
        list.add(new Campus("GG25", true, new LatLng(51.588467, -0.230939), "Ground Floor", 4, GROVE_BLOCK_A, "Textile & Print Making Workshop"));
        list.add(new Campus("GG26", true, new LatLng(51.588502, -0.230777), "Ground Floor", 4, GROVE_BLOCK_A, "Screen Wash"));
        list.add(new Campus("GG29", true, new LatLng(51.588528, -0.230917), "Ground Floor", 4, GROVE_BLOCK_A, "Printing Textile Dye Lab"));
        list.add(new Campus("GG30", true, new LatLng(51.588544, -0.230966), "Ground Floor", 4, GROVE_BLOCK_A, "Exposure Plate Making"));
        list.add(new Campus("GG31", true, new LatLng(51.588567, -0.231052), "Ground Floor", 4, GROVE_BLOCK_A, "Digital Fabric Print Workshop"));
        list.add(new Campus("GG33", true, new LatLng(51.588623, -0.231026), "Ground Floor", 4, GROVE_BLOCK_A, "Electronics Interface Lab"));
        list.add(new Campus("GG34", true, new LatLng(51.588660, -0.231003), "Ground Floor", 4, GROVE_BLOCK_A, "Green Room"));
        list.add(new Campus("GG35", true, new LatLng(51.588686, -0.230984), "Ground Floor", 4, GROVE_BLOCK_A, "Dressing Room"));
        list.add(new Campus("GG36", true, new LatLng(51.588728, -0.230949), "Ground Floor", 4, GROVE_BLOCK_A, "Tv News Room"));
        list.add(new Campus("GG37", true, new LatLng(51.588775, -0.230860), "Ground Floor", 4, GROVE_BLOCK_A, "Loan Space"));
        list.add(new Campus("GG38", true, new LatLng(51.588819, -0.230800), "Ground Floor", 4, GROVE_BLOCK_A, "Tv News Room"));
        list.add(new Campus("GG39", true, new LatLng(51.588860, -0.230763), "Ground Floor", 4, GROVE_BLOCK_A, "Tv News Room"));
        list.add(new Campus("GG41", true, new LatLng(51.588652, -0.230704), "Ground Floor", 4, GROVE_BLOCK_A, "Tv Studio B"));
        list.add(new Campus("GG44", true, new LatLng(51.588617, -0.230870), "Ground Floor", 4, GROVE_BLOCK_A, "Tv Studio A"));
        list.add(new Campus("GG45", true, new LatLng(51.588686, -0.230815), "Ground Floor", 4, GROVE_BLOCK_A, "Office Kit Room"));
        list.add(new Campus("GG46", true, new LatLng(51.588695, -0.230877), "Ground Floor", 4, GROVE_BLOCK_A, "Studio Gallery"));
        list.add(new Campus("GG47", true, new LatLng(51.588732, -0.230701), "Ground Floor", 4, GROVE_BLOCK_A, "Disabled Toilet 2"));
        list.add(new Campus("GG48", true, new LatLng(51.588741, -0.230667), "Ground Floor", 4, GROVE_BLOCK_A, "Female Toilet 1"));


        /*Grove Block A First Floor*/
        list.add(new Campus("Grove Atrium", true, new LatLng(51.588613, -0.230449), "First Floor", 3, GROVE_BLOCK_A, "Grove Atrium"));
        list.add(new Campus("Grove Coffee Pod", true, new LatLng(51.588590, -0.230527), "First Floor", 3, GROVE_BLOCK_A, "Grove Coffee Pod"));
        list.add(new Campus("G101", true, new LatLng(51.588723, -0.230552), "First Floor", 3, GROVE_BLOCK_A, "Lab"));
        list.add(new Campus("G104A", true, new LatLng(51.588728, -0.230392), "First Floor", 3, GROVE_BLOCK_A, "Animation Studio"));
        list.add(new Campus("G104B", true, new LatLng(51.588728, -0.230392), "First Floor", 3, GROVE_BLOCK_A, "Animation Studio"));
        list.add(new Campus("G105", true, new LatLng(51.588695, -0.230257), "First Floor", 3, GROVE_BLOCK_A, "Middlesex"));
        list.add(new Campus("G107", true, new LatLng(51.588397, -0.230322), "First Floor", 3, GROVE_BLOCK_A, "Green Room"));
        //list.add(new Campus("G109",new LatLng(51.588417, -0.230405),3,GROVE_BLOCK_A,"Middlesex"));
        list.add(new Campus("G110", true, new LatLng(51.588389, -0.230401), "First Floor", 3, GROVE_BLOCK_A, "Workshops"));
        list.add(new Campus("G115", true, new LatLng(51.588383, -0.230525), "First Floor", 3, GROVE_BLOCK_A, "Digital Media Workshop 1"));
        list.add(new Campus("G116", true, new LatLng(51.588400, -0.230620), "First Floor", 3, GROVE_BLOCK_A, "Digital Media Workshop 2"));
        list.add(new Campus("G117", true, new LatLng(51.588484, -0.230611), "First Floor", 3, GROVE_BLOCK_A, "Disabled Toilet 1"));
        list.add(new Campus("G118", true, new LatLng(51.588461, -0.230623), "First Floor", 3, GROVE_BLOCK_A, "Male Toilet (Grove, First Floor)"));
        list.add(new Campus("G120", true, new LatLng(51.588482, -0.230778), "First Floor", 3, GROVE_BLOCK_A, "Davinci Resolve Studio 1 2"));
        list.add(new Campus("G121", true, new LatLng(51.588482, -0.230778), "First Floor", 3, GROVE_BLOCK_A, "Davinci Resolve Studio 1 2"));
        list.add(new Campus("G122", true, new LatLng(51.588482, -0.230778), "First Floor", 3, GROVE_BLOCK_A, "Middlesex"));
        list.add(new Campus("G122A", true, new LatLng(51.588482, -0.230778), "First Floor", 3, GROVE_BLOCK_A, "Middlesex"));
        list.add(new Campus("G123A", true, new LatLng(51.588482, -0.230778), "First Floor", 3, GROVE_BLOCK_A, "Digital Media Workshop 3"));
        list.add(new Campus("G123B", true, new LatLng(51.588499, -0.231068), "First Floor", 3, GROVE_BLOCK_A, "Digital Media Workshop 4"));
        list.add(new Campus("G124", true, new LatLng(51.588519, -0.230927), "First Floor", 3, GROVE_BLOCK_A, "Suite 11"));
        list.add(new Campus("G125", true, new LatLng(51.588519, -0.230927), "First Floor", 3, GROVE_BLOCK_A, "Suite 12"));
        list.add(new Campus("G126", true, new LatLng(51.588519, -0.230927), "First Floor", 3, GROVE_BLOCK_A, "Suite 9"));
        list.add(new Campus("G127", true, new LatLng(51.588519, -0.230927), "First Floor", 3, GROVE_BLOCK_A, "Suite 10"));
        list.add(new Campus("G128", true, new LatLng(51.588519, -0.230927), "First Floor", 3, GROVE_BLOCK_A, "Suite 7"));
        list.add(new Campus("G129", true, new LatLng(51.588519, -0.230927), "First Floor", 3, GROVE_BLOCK_A, "Suite 8"));
        list.add(new Campus("G130", true, new LatLng(51.588519, -0.230927), "First Floor", 3, GROVE_BLOCK_A, "Suite 5"));
        list.add(new Campus("G131", true, new LatLng(51.588519, -0.230927), "First Floor", 3, GROVE_BLOCK_A, "Suite 6"));
        list.add(new Campus("G132", true, new LatLng(51.588519, -0.230927), "First Floor", 3, GROVE_BLOCK_A, "Suite 3"));
        list.add(new Campus("G133", true, new LatLng(51.588519, -0.230927), "First Floor", 3, GROVE_BLOCK_A, "Suite 4"));
        list.add(new Campus("G134", true, new LatLng(51.588519, -0.230927), "First Floor", 3, GROVE_BLOCK_A, "Suite 1"));
        list.add(new Campus("G135", true, new LatLng(51.588519, -0.230927), "First Floor", 3, GROVE_BLOCK_A, "Suite 2"));
        list.add(new Campus("G136", true, new LatLng(51.588519, -0.230927), "First Floor", 3, GROVE_BLOCK_A, "Pro Tools Avid Suite"));
        list.add(new Campus("G137", true, new LatLng(51.588499, -0.231068), "First Floor", 3, GROVE_BLOCK_A, "Recording Studio"));
        list.add(new Campus("G137A", true, new LatLng(51.588499, -0.231068), "First Floor", 3, GROVE_BLOCK_A, "Recording Studio"));
//        list.add(new Campus("G139",new LatLng(51.588607, -0.231022),3,GROVE_BLOCK_A,"Technician Office "));
        list.add(new Campus("G140", true, new LatLng(51.588638, -0.231006), "First Floor", 3, GROVE_BLOCK_A, "Office"));
        list.add(new Campus("G141", true, new LatLng(51.588659, -0.230993), "First Floor", 3, GROVE_BLOCK_A, "Office"));
//        list.add(new Campus("G142",new LatLng(51.588683, -0.230980),3,GROVE_BLOCK_A,"Office"));
//        list.add(new Campus("G143",new LatLng(51.588721, -0.230953),3,GROVE_BLOCK_A,"Office"));
//        list.add(new Campus("G144",new LatLng(51.588721, -0.230953),3,GROVE_BLOCK_A,"Office"));
//        list.add(new Campus("G145",new LatLng(51.588735, -0.230758),3,GROVE_BLOCK_A,"Office"));
        list.add(new Campus("G146", true, new LatLng(51.588686, -0.230719), "First Floor", 3, GROVE_BLOCK_A, "Visitors And Lighting Gallery"));
        list.add(new Campus("G146A", true, new LatLng(51.588701, -0.230835), "First Floor", 3, GROVE_BLOCK_A, "Middlesex"));
        list.add(new Campus("G147", true, new LatLng(51.588686, -0.230719), "First Floor", 3, GROVE_BLOCK_A, "Visitors And Lighting Gallery"));
        list.add(new Campus("G148", true, new LatLng(51.588686, -0.230719), "First Floor", 3, GROVE_BLOCK_A, "Visitors And Lighting Gallery"));
        list.add(new Campus("G149", true, new LatLng(51.588418, -0.230785), "First Floor", 3, GROVE_BLOCK_A, "Radio Recording Studio"));
//        list.add(new Campus("G150",new LatLng(51.588418, -0.230785),3,GROVE_BLOCK_A,"Radio Recording Studio"));
//        list.add(new Campus("Disabled Toilet 2 (Grove, First Floor)",new LatLng(51.588685, -0.230481),3,GROVE_BLOCK_A,"Middlesex"));
//        list.add(new Campus("Female Toilet (Grove, First Floor)",new LatLng(51.588731, -0.230476),3,GROVE_BLOCK_A,"Middlesex"));


        /*Grove Block A Second Floor*/
        list.add(new Campus("G201", true, new LatLng(51.588775, -0.230429), "Second Floor", 2, GROVE_BLOCK_A, "Fashion Workshop"));
        list.add(new Campus("G203", true, new LatLng(51.5886039, -0.2306015), "Second Floor", 2, GROVE_BLOCK_A, "Fashion Workshop"));
        list.add(new Campus("G204", true, new LatLng(51.588706, -0.230237), "Second Floor", 2, GROVE_BLOCK_A, "Knit & Weave Workshop"));
        list.add(new Campus("G205", true, new LatLng(51.588631, -0.230173), "Second Floor", 2, GROVE_BLOCK_A, "Yarn Store"));
        list.add(new Campus("G206", true, new LatLng(51.588380, -0.230317), "Second Floor", 2, GROVE_BLOCK_A, "Fashion Studio 4"));
        list.add(new Campus("G207", true, new LatLng(51.588387, -0.230425), "Second Floor", 2, GROVE_BLOCK_A, "Fashion Studio 3"));
        list.add(new Campus("G209", true, new LatLng(51.588391, -0.230516), "Second Floor", 2, GROVE_BLOCK_A, "Fashion Studio 2"));
        list.add(new Campus("G210", true, new LatLng(51.588415, -0.230627), "Second Floor", 2, GROVE_BLOCK_A, "Fashion Studio 1"));
        list.add(new Campus("G211", true, new LatLng(51.588467, -0.230613), "Second Floor", 2, GROVE_BLOCK_A, "Disabled Toilet 1"));
        list.add(new Campus("G212", true, new LatLng(51.588467, -0.230613), "Second Floor", 2, GROVE_BLOCK_A, "Male Toilet"));
        list.add(new Campus("G214A", true, new LatLng(51.588430, -0.230771), "Second Floor", 2, GROVE_BLOCK_A, "Fashion Studio 7"));
        list.add(new Campus("G214B", true, new LatLng(51.588464, -0.230918), "Second Floor", 2, GROVE_BLOCK_A, "Fashion Studio 6"));
        list.add(new Campus("G214C", true, new LatLng(51.588501, -0.231039), "Second Floor", 2, GROVE_BLOCK_A, "Fashion Studio 5"));
        list.add(new Campus("G230", true, new LatLng(51.588567, -0.230725), "Second Floor", 2, GROVE_BLOCK_A, "Post Grad Research Room"));
        list.add(new Campus("G231", true, new LatLng(51.588752, -0.230753), "Second Floor", 2, GROVE_BLOCK_A, "Screening Dressing Room"));
        list.add(new Campus("G234", true, new LatLng(51.588724, -0.230585), "Second Floor", 2, GROVE_BLOCK_A, "Disabled Toilet 2"));
        list.add(new Campus("G235", true, new LatLng(51.588724, -0.230585), "Second Floor", 2, GROVE_BLOCK_A, "Female Toilet"));

        /*Grove Block A Third Floor*/
        list.add(new Campus("G301", true, new LatLng(51.588726, -0.230342), "Third Floor", 1, GROVE_BLOCK_A, "Fine Arts Studio B"));
        list.add(new Campus("G304", true, new LatLng(51.588625, -0.230689), "Third Floor", 1, GROVE_BLOCK_A, "Fine Arts Studio C"));
        list.add(new Campus("G305", true, new LatLng(51.588436, -0.230765), "Third Floor", 1, GROVE_BLOCK_A, "Fine Arts Studio D"));
        list.add(new Campus("G307", true, new LatLng(51.588744, -0.230683), "Third Floor", 1, GROVE_BLOCK_A, "Disabled Toilet"));
        list.add(new Campus("G308", true, new LatLng(51.588744, -0.230683), "Third Floor", 1, GROVE_BLOCK_A, "Female Toilet"));
        list.add(new Campus("G309", true, new LatLng(51.588761, -0.230849), "Third Floor", 1, GROVE_BLOCK_A, "Fine Arts Studio A"));

        /*Grove Block A Fourth Floor*/
        list.add(new Campus("G401", true, new LatLng(51.588791, -0.230657), "Fourth Floor", 0, GROVE_BLOCK_A, "Graphics Studio 3"));
        list.add(new Campus("G404", true, new LatLng(51.588744, -0.230683), "Fourth Floor", 0, GROVE_BLOCK_A, "Female Toilet"));
        list.add(new Campus("G405", true, new LatLng(51.588744, -0.230683), "Fourth Floor", 0, GROVE_BLOCK_A, "Male Toilet"));
        list.add(new Campus("G406A", true, new LatLng(51.588697, -0.230657), "Fourth Floor", 0, GROVE_BLOCK_A, "Graphics Studio 2"));

        /*Grove Block B Ground Floor*/
        list.add(new Campus("GG71", true, new LatLng(51.589024, -0.230361), "Ground Floor", 2, GROVE_BLOCK_B, "PDE Teaching Room"));
        list.add(new Campus("GG72", true, new LatLng(51.588973, -0.230201), "Ground Floor", 2, GROVE_BLOCK_B, "Teaching Room"));
        list.add(new Campus("GG73", true, new LatLng(51.588973, -0.230202), "Ground Floor", 2, GROVE_BLOCK_B, "Teaching Room"));
        list.add(new Campus("GG74", true, new LatLng(51.588973, -0.230203), "Ground Floor", 2, GROVE_BLOCK_B, "Teaching Room"));
        list.add(new Campus("GG75", true, new LatLng(51.588973, -0.230204), "Ground Floor", 2, GROVE_BLOCK_B, "Ensemble Room"));
        list.add(new Campus("GG76", true, new LatLng(51.588973, -0.230205), "Ground Floor", 2, GROVE_BLOCK_B, "Teaching Room"));
        list.add(new Campus("GG77", true, new LatLng(51.588973, -0.230206), "Ground Floor", 2, GROVE_BLOCK_B, "Teaching Room"));
        list.add(new Campus("GG78", true, new LatLng(51.588917, -0.230184), "Ground Floor", 2, GROVE_BLOCK_B, "Female Toilet 2"));
        list.add(new Campus("GG79", true, new LatLng(51.588917, -0.230185), "Ground Floor", 2, GROVE_BLOCK_B, "Disabled Toilet 3"));

        /*Grove Block B First Floor*/
        list.add(new Campus("G170", true, new LatLng(51.588921, -0.230289), "First Floor", 1, GROVE_BLOCK_B, "Male Toilet 1"));
        list.add(new Campus("G171", true, new LatLng(51.588921, -0.230289), "First Floor", 1, GROVE_BLOCK_B, "Interior Design / Interior Architecture"));
        list.add(new Campus("G172", true, new LatLng(51.588921, -0.230289), "First Floor", 1, GROVE_BLOCK_B, "Interior Design / Interior Architecture"));
        list.add(new Campus("G174", true, new LatLng(51.588932, -0.230115), "First Floor", 1, GROVE_BLOCK_B, "Music Practice Room 2"));
        list.add(new Campus("G175", true, new LatLng(51.588932, -0.230114), "First Floor", 1, GROVE_BLOCK_B, "Music Practice Room 3"));
        list.add(new Campus("G176", true, new LatLng(51.588932, -0.230111), "First Floor", 1, GROVE_BLOCK_B, "Music Practice Room 4"));
        list.add(new Campus("G177", true, new LatLng(51.588932, -0.230110), "First Floor", 1, GROVE_BLOCK_B, "Music Teaching Room 4"));
        list.add(new Campus("G178", true, new LatLng(51.588932, -0.230109), "First Floor", 1, GROVE_BLOCK_B, "Band Room"));
        list.add(new Campus("G179", true, new LatLng(51.588932, -0.230108), "First Floor", 1, GROVE_BLOCK_B, "Live Room"));
        list.add(new Campus("G180", true, new LatLng(51.588932, -0.230107), "First Floor", 1, GROVE_BLOCK_B, "Recording Studio"));
        list.add(new Campus("G181", true, new LatLng(51.588932, -0.230112), "First Floor", 1, GROVE_BLOCK_B, "Music Practice Room 5"));
        list.add(new Campus("G182", true, new LatLng(51.588932, -0.230113), "First Floor", 1, GROVE_BLOCK_B, "Music Practice Room 6"));

        /*Grove Block B Second Floor*/
        list.add(new Campus("G270", true, new LatLng(51.588964, -0.230414), "Second Floor", 0, GROVE_BLOCK_B, "Disabled Toilet 3"));
        list.add(new Campus("G271", true, new LatLng(51.589027, -0.230357), "Second Floor", 0, GROVE_BLOCK_B, "TA Studio 2"));
        list.add(new Campus("G272", true, new LatLng(51.588990, -0.230259), "Second Floor", 0, GROVE_BLOCK_B, "TA Studio 3"));
        list.add(new Campus("G274", true, new LatLng(51.588965, -0.230102), "Second Floor", 0, GROVE_BLOCK_B, "Concert Room"));


        /*Grove Block C Ground Floor*/
        list.add(new Campus("GG90", true, new LatLng(51.589277, -0.230058), "Ground Floor", 2, GROVE_BLOCK_C, "Student Break Out Area"));
        list.add(new Campus("GG92", true, new LatLng(51.589228, -0.229969), "Ground Floor", 2, GROVE_BLOCK_C, "Dressing Room"));
        list.add(new Campus("GG93", true, new LatLng(51.589172, -0.230001), "Ground Floor", 2, GROVE_BLOCK_C, "Classroom Photography"));
        list.add(new Campus("GG94", true, new LatLng(51.589189, -0.230119), "Ground Floor", 2, GROVE_BLOCK_C, "Studio Life Drawing"));
        list.add(new Campus("GG96", true, new LatLng(51.589172, -0.230206), "Ground Floor", 2, GROVE_BLOCK_C, "Male Toilet 2"));
        list.add(new Campus("GG97", true, new LatLng(51.589184, -0.230290), "Ground Floor", 2, GROVE_BLOCK_C, ""));
        list.add(new Campus("GG98", true, new LatLng(51.589222, -0.230266), "Ground Floor", 2, GROVE_BLOCK_C, "Disabled Toilet 4"));

        /*Grove Block C First Floor*/
        list.add(new Campus("G190", true, new LatLng(51.589246, -0.230051), "First Floor", 1, GROVE_BLOCK_C, "Dance Studio"));
        list.add(new Campus("G191", true, new LatLng(51.589246, -0.230052), "First Floor", 1, GROVE_BLOCK_C, "Dance Studio"));
        list.add(new Campus("G192", true, new LatLng(51.589246, -0.230053), "First Floor", 1, GROVE_BLOCK_C, "Dance Studio"));


         /*Ravensfield Bulding*/

        list.add(new Campus("Ravensfield House", false, new LatLng(51.588633, -0.227835), "Ground Floor", 0, null, "Ravensfield House Description"));

        /*Ravensfield Ground floor*/

        list.add(new Campus("RVG01", false, new LatLng(51.588682, -0.228016), "Ground Floor", 1, RAVENSFIELDS, "Grab & Go"));
        list.add(new Campus("RVG02", false, new LatLng(51.588743, -0.227978), "Ground Floor", 1, RAVENSFIELDS, "TTA"));
        list.add(new Campus("RVG03", false, new LatLng(51.588684, -0.227899), "Ground Floor", 1, RAVENSFIELDS, "Stage Workshop"));
        list.add(new Campus("RVG04", false, new LatLng(51.588736, -0.227787), "Ground Floor", 1, RAVENSFIELDS, "TTA1"));
        list.add(new Campus("RVG04A", false, new LatLng(51.588755, -0.227875), "Ground Floor", 1, RAVENSFIELDS, "TTA2"));
        list.add(new Campus("RVG06", false, new LatLng(51.588632, -0.227805), "Ground Floor", 1, RAVENSFIELDS, "Performance Space"));
        list.add(new Campus("RVG09", false, new LatLng(51.588593, -0.228068), "Ground Floor", 1, RAVENSFIELDS, "Studio 5"));
        list.add(new Campus("RVG11", false, new LatLng(51.588542, -0.227954), "Ground Floor", 1, RAVENSFIELDS, "Studio 4"));
        list.add(new Campus("RVG14", false, new LatLng(51.588515, -0.227837), "Ground Floor", 1, RAVENSFIELDS, "Male Toilet"));
        list.add(new Campus("RVG15", false, new LatLng(51.588528, -0.227807), "Ground Floor", 1, RAVENSFIELDS, "Disabled Toilet"));
        list.add(new Campus("RVG16", false, new LatLng(51.588504, -0.227799), "Ground Floor", 1, RAVENSFIELDS, "Female Toilet"));
        list.add(new Campus("RVG17", false, new LatLng(51.588639, -0.227657), "Ground Floor", 1, RAVENSFIELDS, "Studio"));
        list.add(new Campus("RVG17A", false, new LatLng(51.588576, -0.227693), "Ground Floor", 1, RAVENSFIELDS, "Studio"));
        list.add(new Campus("RVG18", false, new LatLng(51.588674, -0.227628), "Ground Floor", 1, RAVENSFIELDS, "Local Prop Store 2"));
        list.add(new Campus("RVG19", false, new LatLng(51.588489, -0.227734), "Ground Floor", 1, RAVENSFIELDS, "Local Prop Store 1"));

        /*Ravensfields House 1st floor*/

        list.add(new Campus("RV101", false, new LatLng(51.588676, -0.228021), "First Floor", 0, RAVENSFIELDS, "Generic Teaching Space"));
        list.add(new Campus("RV103", false, new LatLng(51.588772, -0.227954), "First Floor", 0, RAVENSFIELDS, "Generic Teaching Space"));
        list.add(new Campus("RV104", false, new LatLng(51.588758, -0.227871), "First Floor", 0, RAVENSFIELDS, "Generic Teaching Space"));
        list.add(new Campus("RV107", false, new LatLng(51.588725, -0.227737), "First Floor", 0, RAVENSFIELDS, "Generic Teaching Space"));
        list.add(new Campus("RV109", false, new LatLng(51.588696, -0.227631), "First Floor", 0, RAVENSFIELDS, "Dressing Room"));
        list.add(new Campus("RV110", false, new LatLng(51.588675, -0.227663), "First Floor", 0, RAVENSFIELDS, "Fitting Room"));
        list.add(new Campus("RV111", false, new LatLng(51.588599, -0.227673), "First Floor", 0, RAVENSFIELDS, "Costume Workshop"));
        list.add(new Campus("RV112", false, new LatLng(51.588659, -0.227612), "First Floor", 0, RAVENSFIELDS, "Pattern Store"));
        list.add(new Campus("RV113", false, new LatLng(51.588521, -0.227689), "First Floor", 0, RAVENSFIELDS, "Costume Store"));
        list.add(new Campus("RV114", false, new LatLng(51.588519, -0.227742), "First Floor", 0, RAVENSFIELDS, "Laundry"));
        list.add(new Campus("RV115", false, new LatLng(51.588498, -0.227795), "First Floor", 0, RAVENSFIELDS, "Male Toilet"));
        list.add(new Campus("RV116", false, new LatLng(51.588525, -0.227809), "First Floor", 0, RAVENSFIELDS, "Disabled Shower"));
        list.add(new Campus("RV117", false, new LatLng(51.588512, -0.227833), "First Floor", 0, RAVENSFIELDS, "Female Toilet"));
        list.add(new Campus("RV118", false, new LatLng(51.588534, -0.227907), "First Floor", 0, RAVENSFIELDS, "Rehearsal Room 1"));
        list.add(new Campus("RV121", false, new LatLng(51.588557, -0.228009), "First Floor", 0, RAVENSFIELDS, "Rehearsal Room 2"));


        /*Town Hall*/

        list.add(new Campus("Town Hall", false, new LatLng(51.588161, -0.229226), "Ground Floor", 0, null, "Town Hall Description"));

        /*Town Hall ground floor*/

        list.add(new Campus("TG02", false, new LatLng(51.588161, -0.229226), "Ground Floor", 3, TOWNHALL, "Office"));
        list.add(new Campus("TG03", false, new LatLng(51.588161, -0.229227), "Ground Floor", 3, TOWNHALL, "Office"));
        list.add(new Campus("TG04", false, new LatLng(51.588161, -0.229228), "Ground Floor", 3, TOWNHALL, "Office"));
        list.add(new Campus("TG06", false, new LatLng(51.588161, -0.229226), "Ground Floor", 3, TOWNHALL, "Office"));
        list.add(new Campus("TG07", false, new LatLng(51.588161, -0.229226), "Ground Floor", 3, TOWNHALL, "Office"));
        list.add(new Campus("TG08", false, new LatLng(51.588182, -0.229426), "Ground Floor", 3, TOWNHALL, "Office"));
        list.add(new Campus("TG09", false, new LatLng(51.588195, -0.229614), "Ground Floor", 3, TOWNHALL, "Office"));
        list.add(new Campus("TG10", false, new LatLng(51.588198, -0.229667), "Ground Floor", 3, TOWNHALL, "Office"));
        list.add(new Campus("TG11", false, new LatLng(51.588201, -0.229672), "Ground Floor", 3, TOWNHALL, "Office"));
        list.add(new Campus("TG12", false, new LatLng(51.588208, -0.229746), "Ground Floor", 3, TOWNHALL, "Office"));
        list.add(new Campus("TG12A", false, new LatLng(51.588208, -0.229746), "Ground Floor", 3, TOWNHALL, "Office"));
        list.add(new Campus("TG12B", false, new LatLng(51.588208, -0.229746), "Ground Floor", 3, TOWNHALL, "Office"));
        list.add(new Campus("TG13", false, new LatLng(51.588215, -0.229809), "Ground Floor", 3, TOWNHALL, "Office"));
        list.add(new Campus("TG14", false, new LatLng(51.588229, -0.229927), "Ground Floor", 3, TOWNHALL, "Office"));
        list.add(new Campus("TG15", false, new LatLng(51.588231, -0.230037), "Ground Floor", 3, TOWNHALL, "Office"));
        list.add(new Campus("TG16", false, new LatLng(51.588231, -0.230037), "Ground Floor", 3, TOWNHALL, "Office"));
        list.add(new Campus("TG17", false, new LatLng(51.588231, -0.230037), "Ground Floor", 3, TOWNHALL, "Office"));
        list.add(new Campus("TG18", false, new LatLng(51.588231, -0.230037), "Ground Floor", 3, TOWNHALL, "Office"));
        list.add(new Campus("TG19", false, new LatLng(51.588354, -0.230028), "Ground Floor", 3, TOWNHALL, "Office"));
        list.add(new Campus("TG19A", false, new LatLng(51.588354, -0.230028), "Ground Floor", 3, TOWNHALL, "Office"));
        list.add(new Campus("TG19B", false, new LatLng(51.588354, -0.230028), "Ground Floor", 3, TOWNHALL, "Office"));
        list.add(new Campus("TG20A", false, new LatLng(51.588354, -0.230028), "Ground Floor", 3, TOWNHALL, "Office"));
        list.add(new Campus("TG20B", false, new LatLng(51.588215, -0.229809), "Ground Floor", 3, TOWNHALL, "Office"));
        list.add(new Campus("TG21", false, new LatLng(51.588208, -0.229746), "Ground Floor", 3, TOWNHALL, "Office"));
        list.add(new Campus("TG22", false, new LatLng(51.588204, -0.229704), "Ground Floor", 3, TOWNHALL, "Office"));
        list.add(new Campus("TG23", false, new LatLng(51.588197, -0.229643), "Ground Floor", 3, TOWNHALL, "Office"));
        list.add(new Campus("TG26", false, new LatLng(51.588181, -0.229405), "Ground Floor", 3, TOWNHALL, "Office"));
        list.add(new Campus("TG28", false, new LatLng(51.588161, -0.229229), "Ground Floor", 3, TOWNHALL, "Office"));
        list.add(new Campus("TG31", false, new LatLng(51.588401, -0.229119), "Ground Floor", 3, TOWNHALL, "Office"));
        list.add(new Campus("TG32", false, new LatLng(51.588408, -0.229154), "Ground Floor", 3, TOWNHALL, "Office"));
        list.add(new Campus("TG33", false, new LatLng(51.588424, -0.229216), "Ground Floor", 3, TOWNHALL, "Office"));
        list.add(new Campus("TG34", false, new LatLng(51.588446, -0.229276), "Ground Floor", 3, TOWNHALL, "Office"));
        list.add(new Campus("TG35", false, new LatLng(51.588461, -0.229330), "Ground Floor", 3, TOWNHALL, "Office"));
        list.add(new Campus("TG36", false, new LatLng(51.588475, -0.229383), "Ground Floor", 3, TOWNHALL, "Office"));
        list.add(new Campus("TG37", false, new LatLng(51.588529, -0.229531), "Ground Floor", 3, TOWNHALL, "Office"));
        list.add(new Campus("TG38", false, new LatLng(51.588555, -0.229642), "Ground Floor", 3, TOWNHALL, "Office"));
        list.add(new Campus("TG39", false, new LatLng(51.588555, -0.229642), "Ground Floor", 3, TOWNHALL, "Office"));
        list.add(new Campus("TG40", false, new LatLng(51.588577, -0.229704), "Ground Floor", 3, TOWNHALL, "Office"));
        list.add(new Campus("TG44", false, new LatLng(51.588577, -0.229704), "Ground Floor", 3, TOWNHALL, "Office"));
        list.add(new Campus("TG45", false, new LatLng(51.588575, -0.229714), "Ground Floor", 3, TOWNHALL, "Office"));
        list.add(new Campus("TG46", false, new LatLng(51.588575, -0.229712), "Ground Floor", 3, TOWNHALL, "Office"));
        list.add(new Campus("TG47", false, new LatLng(51.588486, -0.229877), "Ground Floor", 3, TOWNHALL, "Office"));
        list.add(new Campus("TG47A", false, new LatLng(51.588486, -0.229877), "Ground Floor", 3, TOWNHALL, "Office"));
        list.add(new Campus("TG48", false, new LatLng(51.588486, -0.229877), "Ground Floor", 3, TOWNHALL, "Office"));
        list.add(new Campus("TG49", false, new LatLng(51.588589, -0.229809), "Ground Floor", 3, TOWNHALL, "Office"));
        list.add(new Campus("TG52", false, new LatLng(51.588348, -0.229978), "Ground Floor", 3, TOWNHALL, "Office"));
        list.add(new Campus("TG53", false, new LatLng(51.588348, -0.229978), "Ground Floor", 3, TOWNHALL, "Office"));
        list.add(new Campus("TG54", false, new LatLng(51.588348, -0.229978), "Ground Floor", 3, TOWNHALL, "Office"));
        list.add(new Campus("TG55", false, new LatLng(51.588348, -0.229978), "Ground Floor", 3, TOWNHALL, "Office"));
        list.add(new Campus("TG56", false, new LatLng(51.588348, -0.229978), "Ground Floor", 3, TOWNHALL, "Office"));
        list.add(new Campus("TG57", false, new LatLng(51.588348, -0.229978), "Ground Floor", 3, TOWNHALL, "Office"));
        list.add(new Campus("TG58", false, new LatLng(51.588348, -0.229978), "Ground Floor", 3, TOWNHALL, "Office"));
        list.add(new Campus("TG60", false, new LatLng(51.588589, -0.229809), "Ground Floor", 3, TOWNHALL, "Office"));
        list.add(new Campus("TG60A", false, new LatLng(51.588592, -0.229754), "Ground Floor", 3, TOWNHALL, "Office"));
        list.add(new Campus("TG61", false, new LatLng(51.588575, -0.229713), "Ground Floor", 3, TOWNHALL, "Office"));
        list.add(new Campus("TG62", false, new LatLng(51.588575, -0.229714), "Ground Floor", 3, TOWNHALL, "Office"));
        list.add(new Campus("TG63", false, new LatLng(51.588577, -0.229704), "Ground Floor", 3, TOWNHALL, "Office"));
        list.add(new Campus("TG64", false, new LatLng(51.588577, -0.229704), "Ground Floor", 3, TOWNHALL, "Office"));
        list.add(new Campus("TG65", false, new LatLng(51.588529, -0.229531), "Ground Floor", 3, TOWNHALL, "Office"));
        list.add(new Campus("TG66", false, new LatLng(51.588475, -0.229383), "Ground Floor", 3, TOWNHALL, "Office"));
        list.add(new Campus("TG67", false, new LatLng(51.588461, -0.229330), "Ground Floor", 3, TOWNHALL, "Office"));
        list.add(new Campus("TG68", false, new LatLng(51.588446, -0.229276), "Ground Floor", 3, TOWNHALL, "Office"));
        list.add(new Campus("TG69", false, new LatLng(51.588424, -0.229216), "Ground Floor", 3, TOWNHALL, "Office"));
        list.add(new Campus("TG70", false, new LatLng(51.588408, -0.229154), "Ground Floor", 3, TOWNHALL, "Office"));
        list.add(new Campus("TG71", false, new LatLng(51.588401, -0.229119), "Ground Floor", 3, TOWNHALL, "Office"));
        list.add(new Campus("TG72", false, new LatLng(51.588388, -0.229071), "Ground Floor", 3, TOWNHALL, "Office"));
        list.add(new Campus("TG73", false, new LatLng(51.588348, -0.228988), "Ground Floor", 3, TOWNHALL, "Office"));
        list.add(new Campus("TG74", false, new LatLng(51.588348, -0.228988), "Ground Floor", 3, TOWNHALL, "Middlesex"));

        /*Town Hall 1st floor*/

        list.add(new Campus("T106", false, new LatLng(51.588196, -0.229352), "First Floor", 2, TOWNHALL, "Office"));
        list.add(new Campus("T107", false, new LatLng(51.588196, -0.229358), "First Floor", 2, TOWNHALL, "Office"));
        list.add(new Campus("T108", false, new LatLng(51.588196, -0.229352), "First Floor", 2, TOWNHALL, "Office"));
        list.add(new Campus("T109", false, new LatLng(51.588202, -0.229428), "First Floor", 2, TOWNHALL, "Office"));
        list.add(new Campus("T110", false, new LatLng(51.588205, -0.229479), "First Floor", 2, TOWNHALL, "Office"));
        list.add(new Campus("T110A", false, new LatLng(51.588205, -0.229479), "First Floor", 2, TOWNHALL, "Office"));
        list.add(new Campus("T111", false, new LatLng(51.588213, -0.229546), "First Floor", 2, TOWNHALL, "Office"));
        list.add(new Campus("T112", false, new LatLng(51.588219, -0.229610), "First Floor", 2, TOWNHALL, "Office"));
        list.add(new Campus("T113", false, new LatLng(51.588233, -0.229704), "First Floor", 2, TOWNHALL, "Office"));
        list.add(new Campus("T114", false, new LatLng(51.588233, -0.229706), "First Floor", 2, TOWNHALL, "Office"));
        list.add(new Campus("T115", false, new LatLng(51.588248, -0.230010), "First Floor", 2, TOWNHALL, "Office"));
        list.add(new Campus("T116", false, new LatLng(51.588248, -0.230010), "First Floor", 2, TOWNHALL, "Office"));
        list.add(new Campus("T116A", false, new LatLng(51.588248, -0.230010), "First Floor", 2, TOWNHALL, "Office"));
        list.add(new Campus("T117", false, new LatLng(51.588333, -0.229968), "First Floor", 2, TOWNHALL, "Office"));
        list.add(new Campus("T118", false, new LatLng(51.588333, -0.229969), "First Floor", 2, TOWNHALL, "Office"));
        list.add(new Campus("T120", false, new LatLng(51.588333, -0.229971), "First Floor", 2, TOWNHALL, "Office"));
        list.add(new Campus("T121", false, new LatLng(51.588333, -0.229970), "First Floor", 2, TOWNHALL, "Office"));
        list.add(new Campus("T122", false, new LatLng(51.588233, -0.229707), "First Floor", 2, TOWNHALL, "Office"));
        list.add(new Campus("T123", false, new LatLng(51.588233, -0.229705), "First Floor", 2, TOWNHALL, "Office"));
        list.add(new Campus("T124", false, new LatLng(51.588233, -0.229703), "First Floor", 2, TOWNHALL, "Office"));
        list.add(new Campus("T125", false, new LatLng(51.588219, -0.229611), "First Floor", 2, TOWNHALL, "Office"));
        list.add(new Campus("T126", false, new LatLng(51.588219, -0.229609), "First Floor", 2, TOWNHALL, "Office"));
        list.add(new Campus("T127", false, new LatLng(51.588213, -0.229546), "First Floor", 2, TOWNHALL, "Office"));
        list.add(new Campus("T129", false, new LatLng(51.588202, -0.229428), "First Floor", 2, TOWNHALL, "Office"));
        list.add(new Campus("T130", false, new LatLng(51.588202, -0.229428), "First Floor", 2, TOWNHALL, "Office"));
        list.add(new Campus("T131", false, new LatLng(51.588196, -0.229359), "First Floor", 2, TOWNHALL, "Office"));
        list.add(new Campus("T143", false, new LatLng(51.588493, -0.229904), "First Floor", 2, TOWNHALL, "Office"));
        list.add(new Campus("T145", false, new LatLng(51.588493, -0.229904), "First Floor", 2, TOWNHALL, "Office"));
        list.add(new Campus("T146", false, new LatLng(51.588493, -0.229904), "First Floor", 2, TOWNHALL, "Office"));
        list.add(new Campus("T147", false, new LatLng(51.588493, -0.229904), "First Floor", 2, TOWNHALL, "Office"));
        list.add(new Campus("T148", false, new LatLng(51.588535, -0.229852), "First Floor", 2, TOWNHALL, "Office"));
        list.add(new Campus("T149", false, new LatLng(51.588535, -0.229852), "First Floor", 2, TOWNHALL, "Office"));
        list.add(new Campus("T150", false, new LatLng(51.588535, -0.229852), "First Floor", 2, TOWNHALL, "Office"));
        list.add(new Campus("T157", false, new LatLng(51.588535, -0.229852), "First Floor", 2, TOWNHALL, "Office"));

        /*Town Hall 2nd floor*/

        list.add(new Campus("T201", false, new LatLng(51.588196, -0.229356), "Second Floor", 1, TOWNHALL, "Office"));
        list.add(new Campus("T202", false, new LatLng(51.588196, -0.229357), "Second Floor", 1, TOWNHALL, "Office"));
        list.add(new Campus("T203", false, new LatLng(51.588196, -0.229355), "Second Floor", 1, TOWNHALL, "Office"));
        list.add(new Campus("T204", false, new LatLng(51.588196, -0.229354), "Second Floor", 1, TOWNHALL, "Office"));
        list.add(new Campus("T205", false, new LatLng(51.588196, -0.229353), "Second Floor", 1, TOWNHALL, "Office"));
        list.add(new Campus("T207", false, new LatLng(51.588348, -0.229978), "Second Floor", 1, TOWNHALL, "Office"));
        list.add(new Campus("T209", false, new LatLng(51.588348, -0.229978), "Second Floor", 1, TOWNHALL, "Office"));
        list.add(new Campus("T210", false, new LatLng(51.588348, -0.229978), "Second Floor", 1, TOWNHALL, "Office"));
        list.add(new Campus("T211", false, new LatLng(51.588348, -0.229978), "Second Floor", 1, TOWNHALL, "Office"));

        list.add(new Campus("T303", false, new LatLng(51.588348, -0.229978), "Third Floor", 0, TOWNHALL, "Office"));
        list.add(new Campus("T304", false, new LatLng(51.588348, -0.229978), "Third Floor", 0, TOWNHALL, "Office"));
        list.add(new Campus("T305", false, new LatLng(51.588348, -0.229978), "Third Floor", 0, TOWNHALL, "Office"));

        return list;
    }

}
