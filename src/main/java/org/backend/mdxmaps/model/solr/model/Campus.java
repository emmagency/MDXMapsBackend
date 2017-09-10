package org.backend.mdxmaps.model.solr.model;

import com.fasterxml.jackson.annotation.JsonProperty;
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


    public Campus(String name, String building, String description, LatLng latLng, String level, int gMapLevel, boolean isDirectionsAvailable) {
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

    @JsonProperty(value = "isDirectionsAvailable")
    public boolean isDirectionsAvailable() {
        return isDirectionsAvailable;
    }

    public static ArrayList<Campus> getRoomsForSolr() {
        ArrayList<Campus> list = new ArrayList<>();

        /*College Building ground floor*/

        list.add(new Campus("College Building", null, "Main Building", new LatLng(51.589825, -0.2288390), "Ground Floor", 2, true));
        list.add(new Campus("Quad", COLLEGE, "Rickett Quadrangle", new LatLng(51.589825, -0.228839), "Ground Floor", 2, true));
        list.add(new Campus("CG03", COLLEGE, "Seminar Room", new LatLng(51.589537, -0.228467), "Ground Floor", 2, true));
        list.add(new Campus("CG04", COLLEGE, "Seminar Room", new LatLng(51.589553, -0.228542), "Ground Floor", 2, true));
        list.add(new Campus("CG06", COLLEGE, "Seminar Room", new LatLng(51.589565, -0.228646), "Ground Floor", 2, true));
        list.add(new Campus("CG07", COLLEGE, "Employability And Careers Offices", new LatLng(51.589579, -0.228760), "Ground Floor", 2, true));
        list.add(new Campus("CG08", COLLEGE, "Seminar Room", new LatLng(51.589586, -0.228841), "Ground Floor", 2, true));
        list.add(new Campus("CG09", COLLEGE, "Seminar Room", new LatLng(51.589587, -0.228912), "Ground Floor", 2, true));
        list.add(new Campus("CG10", COLLEGE, "Seminar Room", new LatLng(51.589602, -0.228994), "Ground Floor", 2, true));
        list.add(new Campus("CG11", COLLEGE, "Seminar Room", new LatLng(51.589609, -0.229090), "Ground Floor", 2, true));
        list.add(new Campus("CG12A", COLLEGE, "Seminar Room", new LatLng(51.589629, -0.229230), "Ground Floor", 2, true));
        list.add(new Campus("CG12B", COLLEGE, "Seminar Room", new LatLng(51.589641, -0.229313), "Ground Floor", 2, true));
        list.add(new Campus("CG13", COLLEGE, "Seminar Room", new LatLng(51.589655, -0.229430), "Ground Floor", 2, true));
        list.add(new Campus("CG14", COLLEGE, "Seminar Room", new LatLng(51.589667, -0.229564), "Ground Floor", 2, true));
        list.add(new Campus("CG15", COLLEGE, "Classroom / Seminar Room", new LatLng(51.589720, -0.229247), "Ground Floor", 2, true));
        list.add(new Campus("CG16", COLLEGE, "Classroom / Seminar Room", new LatLng(51.589747, -0.229381), "Ground Floor", 2, true));
        list.add(new Campus("CG30", COLLEGE, "Female Toilet 1", new LatLng(51.589744, -0.229049), "Ground Floor", 2, true));
        list.add(new Campus("CG36", COLLEGE, "Male Toilet 1", new LatLng(51.589959, -0.228987), "Ground Floor", 2, true));
        list.add(new Campus("CG37", COLLEGE, "Disabled Toilet 1", new LatLng(51.589917, -0.229037), "Ground Floor", 2, true));
        list.add(new Campus("CG41", COLLEGE, "Lecture Theatre", new LatLng(51.590110, -0.229381), "Ground Floor", 2, true));
        list.add(new Campus("CG43", COLLEGE, "Classroom", new LatLng(51.590093, -0.229185), "Ground Floor", 2, true));
        list.add(new Campus("CG45", COLLEGE, "Classroom", new LatLng(51.590079, -0.229100), "Ground Floor", 2, true));
        list.add(new Campus("CG47", COLLEGE, "Classroom", new LatLng(51.590051, -0.228848), "Ground Floor", 2, true));
        list.add(new Campus("CG48", COLLEGE, "Classroom", new LatLng(51.590032, -0.228668), "Ground Floor", 2, true));
        list.add(new Campus("CG49", COLLEGE, "Micro-Lab", new LatLng(51.590021, -0.228557), "Ground Floor", 2, true));
        list.add(new Campus("CG51", COLLEGE, "Classroom", new LatLng(51.590002, -0.228381), "Ground Floor", 2, true));
        list.add(new Campus("CG57", COLLEGE, "Security Reception", new LatLng(51.589920, -0.228389), "Ground Floor", 2, false));
        list.add(new Campus("CG58", COLLEGE, "First Aid Room", new LatLng(51.589860, -0.228261), "Ground Floor", 2, false));
        list.add(new Campus("CG60", COLLEGE, "Dance Room", new LatLng(51.589907, -0.229541), "Ground Floor", 2, true));
        list.add(new Campus("CG62", COLLEGE, "Classroom", new LatLng(51.589977, -0.229384), "Ground Floor", 2, true));
        list.add(new Campus("CG62 (Disabled Access)", COLLEGE, "Classroom", new LatLng(51.589977, -0.229384), "Ground Floor", 2, true));
        list.add(new Campus("CG76", COLLEGE, "Lecture Theatre", new LatLng(51.589946, -0.229180), "Ground Floor", 2, true));
        list.add(new Campus("CG77", COLLEGE, "Lecture Theatre", new LatLng(51.589827, -0.229210), "Ground Floor", 2, true));
        list.add(new Campus("CG82", COLLEGE, "Classroom", new LatLng(51.589949, -0.227878), "Ground Floor", 2, false));
        list.add(new Campus("CG83", COLLEGE, "Meeting Room", new LatLng(51.589957, -0.228055), "Ground Floor", 2, false));
        list.add(new Campus("CG84", COLLEGE, "Meeting Room", new LatLng(51.590020, -0.227944), "Ground Floor", 2, false));
        list.add(new Campus("CG81A", COLLEGE, "Male Toilet 2", new LatLng(51.590006, -0.227898), "Ground Floor", 2, false));
        list.add(new Campus("CG81B", COLLEGE, "Female Toilet 2", new LatLng(51.589993, -0.227875), "Ground Floor", 2, false));
        list.add(new Campus("Costa (Quad)", COLLEGE, "Quad", new LatLng(51.589755, -0.228960), "Ground Floor", 2, true));
        /*College building first floor*/

        list.add(new Campus("C101", COLLEGE, "Classroom", new LatLng(51.589664, -0.228424), "First Floor", 1, true));
        list.add(new Campus("C104", COLLEGE, "Classroom", new LatLng(51.589538, -0.228474), "First Floor", 1, true));
        list.add(new Campus("C105", COLLEGE, "Classroom", new LatLng(51.589559, -0.228601), "First Floor", 1, true));
        list.add(new Campus("C106", COLLEGE, "Classroom", new LatLng(51.589583, -0.228782), "First Floor", 1, true));
        list.add(new Campus("C107", COLLEGE, "Classroom", new LatLng(51.589593, -0.228890), "First Floor", 1, true));
        list.add(new Campus("C109", COLLEGE, "Classroom", new LatLng(51.589610, -0.229053), "First Floor", 1, true));
        list.add(new Campus("C110", COLLEGE, "Classroom", new LatLng(51.589631, -0.229244), "First Floor", 1, true));
        list.add(new Campus("C111", COLLEGE, "Classroom", new LatLng(51.589644, -0.229346), "First Floor", 1, true));
        list.add(new Campus("C113", COLLEGE, "Classroom", new LatLng(51.589666, -0.229552), "First Floor", 1, true));
        list.add(new Campus("C114", COLLEGE, "Lecture Theatre", new LatLng(51.589738, -0.229041), "First Floor", 1, true));
        list.add(new Campus("C115", COLLEGE, "Lecture Theatre", new LatLng(51.589960, -0.228992), "First Floor", 1, true));
        list.add(new Campus("C117", COLLEGE, "Micro Lab", new LatLng(51.589761, -0.229514), "First Floor", 1, true));
        list.add(new Campus("C118", COLLEGE, "Micro Lab", new LatLng(51.589812, -0.229499), "First Floor", 1, true));
        list.add(new Campus("C120", COLLEGE, "Micro Lab", new LatLng(51.589897, -0.229477), "First Floor", 1, true));
        list.add(new Campus("C121", COLLEGE, "Micro Lab", new LatLng(51.589954, -0.229458), "First Floor", 1, true));
        list.add(new Campus("C122", COLLEGE, "Classroom", new LatLng(51.590113, -0.229401), "First Floor", 1, true));
        list.add(new Campus("C126", COLLEGE, "Classroom", new LatLng(51.590099, -0.229252), "First Floor", 1, true));
        list.add(new Campus("C127", COLLEGE, "Classroom", new LatLng(51.590088, -0.229185), "First Floor", 1, true));
        list.add(new Campus("C128", COLLEGE, "Classroom", new LatLng(51.590075, -0.229104), "First Floor", 1, true));
        list.add(new Campus("C131", COLLEGE, "Micro Lab", new LatLng(51.590048, -0.228805), "First Floor", 1, true));
        list.add(new Campus("C132", COLLEGE, "Micro Lab", new LatLng(51.590037, -0.228692), "First Floor", 1, true));
        list.add(new Campus("C135", COLLEGE, "Data Communication Laboratory", new LatLng(51.590006, -0.228474), "First Floor", 1, true));
        list.add(new Campus("C136", COLLEGE, "Data Communication Laboratory", new LatLng(51.589998, -0.228371), "First Floor", 1, true));
        list.add(new Campus("C138", COLLEGE, "Cisco Networking Laboratory", new LatLng(51.589873, -0.228357), "First Floor", 1, true));

        /*College building Second floor*/

        list.add(new Campus("C204", COLLEGE, "Classroom", new LatLng(51.589542, -0.228469), "Second Floor", 0, true));
        list.add(new Campus("C205", COLLEGE, "Classroom", new LatLng(51.589554, -0.228562), "Second Floor", 0, true));
        list.add(new Campus("C206", COLLEGE, "Classroom", new LatLng(51.589566, -0.228654), "Second Floor", 0, true));
        list.add(new Campus("C207", COLLEGE, "Classroom", new LatLng(51.589575, -0.228773), "Second Floor", 0, true));
        list.add(new Campus("C209", COLLEGE, "Classroom", new LatLng(51.589609, -0.229042), "Second Floor", 0, true));
        list.add(new Campus("C210", COLLEGE, "Classroom", new LatLng(51.589737, -0.229047), "Second Floor", 0, true));
        list.add(new Campus("C211", COLLEGE, "Classroom", new LatLng(51.589947, -0.228999), "Second Floor", 0, true));
        list.add(new Campus("C212", COLLEGE, "Classroom", new LatLng(51.589735, -0.229268), "Second Floor", 0, true));
        list.add(new Campus("C213A", COLLEGE, "Classroom", new LatLng(51.589788, -0.229260), "Second Floor", 0, true));
        list.add(new Campus("C213B", COLLEGE, "Classroom", new LatLng(51.589828, -0.229246), "Second Floor", 0, true));
        list.add(new Campus("C214", COLLEGE, "Classroom", new LatLng(51.589885, -0.229224), "Second Floor", 0, true));
        list.add(new Campus("C215", COLLEGE, "Classroom", new LatLng(51.589953, -0.229189), "Second Floor", 0, true));
        list.add(new Campus("C216B", COLLEGE, "Classroom", new LatLng(51.590029, -0.229170), "Second Floor", 0, true));
        list.add(new Campus("C217", COLLEGE, "Classroom", new LatLng(51.590057, -0.228902), "Second Floor", 0, true));
        list.add(new Campus("C218", COLLEGE, "Classroom", new LatLng(51.590048, -0.228790), "Second Floor", 0, true));

        /*HatchCroft Building Ground Floor*/

        list.add(new Campus("Hatchcroft Building", null, "Science & Technology", new LatLng(51.589154, -0.229178), "Ground Floor", 2, true));
        list.add(new Campus("HG01", HATCHCROFT, "Spec Lab", new LatLng(51.589178, -0.229000), "Ground Floor", 2, true));
        list.add(new Campus("HG02", HATCHCROFT, "Spec Lab", new LatLng(51.589164, -0.228829), "Ground Floor", 2, true));
        list.add(new Campus("HG03", HATCHCROFT, "Spec Lab", new LatLng(51.589162, -0.228700), "Ground Floor", 2, true));
        list.add(new Campus("HG04", HATCHCROFT, "Spec Lab", new LatLng(51.589119, -0.228704), "Ground Floor", 2, true));
        list.add(new Campus("HG05", HATCHCROFT, "Spec Lab", new LatLng(51.589117, -0.228645), "Ground Floor", 2, true));
        list.add(new Campus("HG06", HATCHCROFT, "Spec Lab", new LatLng(51.589137, -0.228572), "Ground Floor", 2, true));
        list.add(new Campus("HG07", HATCHCROFT, "Spec Lab", new LatLng(51.589041, -0.228589), "Ground Floor", 2, true));
        list.add(new Campus("HG08", HATCHCROFT, "Disabled Toilet 1", new LatLng(51.589041, -0.228589), "Ground Floor", 2, true));
        list.add(new Campus("HG09", HATCHCROFT, "Spec Lab", new LatLng(51.589056, -0.228697), "Ground Floor", 2, true));
        list.add(new Campus("HG10", HATCHCROFT, "Office", new LatLng(51.589067, -0.228812), "Ground Floor", 2, true));
        list.add(new Campus("HG11", HATCHCROFT, "Spec Lab", new LatLng(51.589093, -0.228873), "Ground Floor", 2, true));
        list.add(new Campus("HG12", HATCHCROFT, "Spec Lab", new LatLng(51.589062, -0.228885), "Ground Floor", 2, true));
        list.add(new Campus("HG13", HATCHCROFT, "Spec Lab", new LatLng(51.589089, -0.229008), "Ground Floor", 2, true));
        list.add(new Campus("HG14", HATCHCROFT, "Spec Lab", new LatLng(51.589093, -0.229159), "Ground Floor", 2, true));
        list.add(new Campus("HG19", HATCHCROFT, "Lecture Theatre", new LatLng(51.589023, -0.229431), "Ground Floor", 2, true));
        list.add(new Campus("HG20", HATCHCROFT, "Female Toilet", new LatLng(51.589106, -0.229287), "Ground Floor", 2, true));
        list.add(new Campus("HG21", HATCHCROFT, "Disabled Toilet 1", new LatLng(51.589146, -0.229289), "Ground Floor", 2, true));
        list.add(new Campus("HG24", HATCHCROFT, "Spec Lab", new LatLng(51.589159, -0.229675), "Ground Floor", 2, true));
        list.add(new Campus("HG27", HATCHCROFT, "Spec Lab", new LatLng(51.589129, -0.229532), "Ground Floor", 2, true));
        list.add(new Campus("HG28", HATCHCROFT, "Spec Lab", new LatLng(51.589136, -0.229463), "Ground Floor", 2, true));
        list.add(new Campus("HG29", HATCHCROFT, "Spec Lab", new LatLng(51.589258, -0.229653), "Ground Floor", 2, true));
        list.add(new Campus("HG30", HATCHCROFT, "Spec Lab", new LatLng(51.589249, -0.229542), "Ground Floor", 2, true));
        list.add(new Campus("HG31", HATCHCROFT, "Spec Lab", new LatLng(51.589235, -0.229428), "Ground Floor", 2, true));
        list.add(new Campus("HG33", HATCHCROFT, "Spec Lab", new LatLng(51.589206, -0.229330), "Ground Floor", 2, true));

        /*HatchCroft Building 1st floor*/

        list.add(new Campus("H101", HATCHCROFT, "Spec Lab", new LatLng(51.589206, -0.229330), "First Floor", 1, true));
        list.add(new Campus("H102", HATCHCROFT, "Spec Lab", new LatLng(51.589177, -0.228925), "First Floor", 1, true));
        list.add(new Campus("H104", HATCHCROFT, "Spec Lab", new LatLng(51.589149, -0.228774), "First Floor", 1, true));
        list.add(new Campus("H105", HATCHCROFT, "Spec Lab", new LatLng(51.589138, -0.228608), "First Floor", 1, true));
        list.add(new Campus("H106", HATCHCROFT, "Spec Lab", new LatLng(51.589041, -0.228647), "First Floor", 1, true));
        list.add(new Campus("H109", HATCHCROFT, "Spec Lab", new LatLng(51.589075, -0.228880), "First Floor", 1, true));
        list.add(new Campus("H110", HATCHCROFT, "Spec Lab", new LatLng(51.589075, -0.228880), "First Floor", 1, true));
        list.add(new Campus("H111", HATCHCROFT, "Spec Lab", new LatLng(51.589075, -0.228880), "First Floor", 1, true));
        list.add(new Campus("H113", HATCHCROFT, "Spec Lab", new LatLng(51.589104, -0.229089), "First Floor", 1, true));
        list.add(new Campus("H116", HATCHCROFT, "Lecture Theatre", new LatLng(51.589025, -0.229440), "First Floor", 1, true));
        list.add(new Campus("H117", HATCHCROFT, "Spec Lab", new LatLng(51.588963, -0.229356), "First Floor", 1, true));
        list.add(new Campus("H118", HATCHCROFT, "Spec Lab", new LatLng(51.588968, -0.229395), "First Floor", 1, true));
        list.add(new Campus("H119", HATCHCROFT, "Spec Lab", new LatLng(51.588972, -0.229433), "First Floor", 1, true));
        list.add(new Campus("H120", HATCHCROFT, "Spec Lab", new LatLng(51.588982, -0.229475), "First Floor", 1, true));
        list.add(new Campus("H121", HATCHCROFT, "Spec Lab", new LatLng(51.588984, -0.229518), "First Floor", 1, true));
        list.add(new Campus("H122", HATCHCROFT, "Spec Lab", new LatLng(51.588989, -0.229552), "First Floor", 1, true));
        list.add(new Campus("H123", HATCHCROFT, "Spec Lab", new LatLng(51.589007, -0.229543), "First Floor", 1, true));
        list.add(new Campus("H124", HATCHCROFT, "Spec Lab", new LatLng(51.589044, -0.229535), "First Floor", 1, true));
        list.add(new Campus("H125", HATCHCROFT, "Spec Lab", new LatLng(51.589079, -0.229523), "First Floor", 1, true));
        list.add(new Campus("H128", HATCHCROFT, "Male Toilet", new LatLng(51.589096, -0.229294), "First Floor", 1, true));
        list.add(new Campus("H129", HATCHCROFT, "Disabled Toilet", new LatLng(51.589144, -0.229290), "First Floor", 1, true));
        list.add(new Campus("H133", HATCHCROFT, "Spec Lab", new LatLng(51.589174, -0.229586), "First Floor", 1, true));
        list.add(new Campus("H135", HATCHCROFT, "Spec Lab", new LatLng(51.589140, -0.229645), "First Floor", 1, true));
        list.add(new Campus("H136", HATCHCROFT, "Spec Lab", new LatLng(51.589154, -0.229735), "First Floor", 1, true));
        list.add(new Campus("H137", HATCHCROFT, "Spec Lab", new LatLng(51.589254, -0.229653), "First Floor", 1, true));
        list.add(new Campus("H139", HATCHCROFT, "Spec Lab", new LatLng(51.589241, -0.229522), "First Floor", 1, true));
        list.add(new Campus("H141A", HATCHCROFT, "Spec Lab", new LatLng(51.589231, -0.229414), "First Floor", 1, true));
        list.add(new Campus("H141B", HATCHCROFT, "Spec Lab", new LatLng(51.589217, -0.229299), "First Floor", 1, true));

        /*HatchCroft Building 2nd floor*/

        list.add(new Campus("H201", HATCHCROFT, "Wet Lab", new LatLng(51.589178, -0.229018), "Second Floor", 0, true));
        list.add(new Campus("H202", HATCHCROFT, "Dry Lab", new LatLng(51.589169, -0.228898), "Second Floor", 0, true));
        list.add(new Campus("H203", HATCHCROFT, "Skills Lab", new LatLng(51.589150, -0.228760), "Second Floor", 0, true));
        list.add(new Campus("H205", HATCHCROFT, "Skills Lab", new LatLng(51.589136, -0.228607), "Second Floor", 0, true));
        list.add(new Campus("H206", HATCHCROFT, "CMH Lab", new LatLng(51.589042, -0.228599), "Second Floor", 0, true));
        list.add(new Campus("H207", HATCHCROFT, "Clinical Skills Lab", new LatLng(51.589042, -0.228687), "Second Floor", 0, true));
        list.add(new Campus("H208", HATCHCROFT, "Clinical Skills Lab", new LatLng(51.589071, -0.228866), "Second Floor", 0, true));
        list.add(new Campus("H209", HATCHCROFT, "Spec Lab", new LatLng(51.589093, -0.229019), "Second Floor", 0, true));
        list.add(new Campus("H211", HATCHCROFT, "Female Toilet", new LatLng(51.589111, -0.229161), "Second Floor", 0, true));
        list.add(new Campus("H216", HATCHCROFT, "Spec Lab", new LatLng(51.589116, -0.229427), "Second Floor", 0, true));
        list.add(new Campus("H217", HATCHCROFT, "Spec Lab", new LatLng(51.589118, -0.229537), "Second Floor", 0, true));
        list.add(new Campus("H219", HATCHCROFT, "Spec Lab", new LatLng(51.589162, -0.229576), "Second Floor", 0, true));
        list.add(new Campus("H220", HATCHCROFT, "Spec Lab", new LatLng(51.589158, -0.229686), "Second Floor", 0, true));
        list.add(new Campus("H221", HATCHCROFT, "Spec Lab", new LatLng(51.589255, -0.229642), "Second Floor", 0, true));
        list.add(new Campus("H222", HATCHCROFT, "Spec Lab", new LatLng(51.589243, -0.229572), "Second Floor", 0, true));
        list.add(new Campus("H223", HATCHCROFT, "Spec Lab", new LatLng(51.589237, -0.229476), "Second Floor", 0, true));
        list.add(new Campus("H224", HATCHCROFT, "Spec Lab", new LatLng(51.589226, -0.229364), "Second Floor", 0, true));

        /*Building 9*/

        list.add(new Campus("Building 9", null, "Building 9 Description", new LatLng(51.588709, -0.229398), "Ground Floor", 0, true));
        list.add(new Campus("BG01", BUILDING9, "Classroom", new LatLng(51.588697, -0.229558), "Ground Floor", 0, true));
        list.add(new Campus("BG02", BUILDING9, "Classroom", new LatLng(51.588771, -0.229495), "Ground Floor", 0, true));
        list.add(new Campus("BG03", BUILDING9, "Toilet", new LatLng(51.588709, -0.229460), "Ground Floor", 0, true));
        list.add(new Campus("BG04", BUILDING9, "Disabled Toilet", new LatLng(51.588738, -0.229438), "Ground Floor", 0, true));
        list.add(new Campus("BG05", BUILDING9, "ICT Suite", new LatLng(51.588796, -0.229342), "Ground Floor", 0, true));
        list.add(new Campus("BG07", BUILDING9, "Meeting Room", new LatLng(51.588698, -0.229325), "Ground Floor", 0, true));
        list.add(new Campus("BG10", BUILDING9, "Classroom", new LatLng(51.588647, -0.229382), "Ground Floor", 0, true));
        list.add(new Campus("BG11", BUILDING9, "Hub Breakout", new LatLng(51.588661, -0.229456), "Ground Floor", 0, true));

        /*Building 10 ground floor*/

        list.add(new Campus("Building 10", null, "Building 10 Description", new LatLng(51.589837, -0.229845), "Ground Floor", 1, true));

        list.add(new Campus("BTG01", BUILDING10, "Simulation Room A", new LatLng(51.589815, -0.229934), "Ground Floor", 1, true));
        list.add(new Campus("BTG02", BUILDING10, "Store Room", new LatLng(51.589858, -0.229918), "Ground Floor", 1, true));
        list.add(new Campus("BTG04", BUILDING10, "Simulation Room B", new LatLng(51.589845, -0.229755), "Ground Floor", 1, true));

        /*Building 10 first floor*/

        list.add(new Campus("BT101", BUILDING10, "CPR Skills Room", new LatLng(51.589825, -0.229769), "First Floor", 0, true));
        list.add(new Campus("BT103", BUILDING10, "CPR Skills Room", new LatLng(51.589849, -0.229926), "First Floor", 0, true));

        /*Portakabin 2 ground floor*/

        list.add(new Campus("Portakabin 2", null, "Portakabin 2 Description", new LatLng(51.589994, -0.229832), "Ground Level", 1, false));
        list.add(new Campus("P2G01", PORTAKABIN_2, "Recital Room", new LatLng(51.589963, -0.229843), "Ground Level", 0, false));
        list.add(new Campus("P2G02", PORTAKABIN_2, "Recital Room", new LatLng(51.590010, -0.229825), "Ground Level", 0, false));
        list.add(new Campus("P2G03", PORTAKABIN_2, "Recital Room", new LatLng(51.589982, -0.229734), "Ground Level", 0, false));
        list.add(new Campus("P2G03A", PORTAKABIN_2, "Recital Room", new LatLng(51.590004, -0.229729), "Ground Level", 0, false));
        list.add(new Campus("P2G04", PORTAKABIN_2, "Recital Room", new LatLng(51.589960, -0.229746), "Ground Level", 0, false));
        list.add(new Campus("P2G05", PORTAKABIN_2, "Recital Room", new LatLng(51.589941, -0.229762), "Ground Level", 0, false));

        /*Portakabin 6 & 7 ground floor*/

        list.add(new Campus("Portakabin 6 & 7", null, "Portakabin 6 & 7", new LatLng(51.589662, -0.229930), "Ground Level", 1, true));
        list.add(new Campus("P6G01", PORTAKABIN_6_7, "Classroom", new LatLng(51.589661, -0.229960), "Ground Level", 1, true));
        list.add(new Campus("P6G02", PORTAKABIN_6_7, "Classroom", new LatLng(51.589654, -0.229853), "Ground Level", 1, true));

        /*Portakabin 6 & 7 first floor*/

        list.add(new Campus("P7101", PORTAKABIN_6_7, "Classroom", new LatLng(51.589653, -0.229856), "First Level", 0, true));
        list.add(new Campus("P7102", PORTAKABIN_6_7, "Classroom", new LatLng(51.589665, -0.229957), "First Level", 0, true));

        /*Portakabin 8 Ground floor*/
        list.add(new Campus("P8G01", PORTAKABIN_8, "Wash Room", new LatLng(51.590704, -0.231243), "Ground Level", 0, false));
        list.add(new Campus("P8G02", PORTAKABIN_8, "Female Prayer Room", new LatLng(51.590743, -0.231231), "Ground Level", 0, false));
        list.add(new Campus("P8G03", PORTAKABIN_8, "Male Prayer Room", new LatLng(51.590778, -0.231227), "Ground Level", 0, false));

        /*Portakabin A ground floor*/

        list.add(new Campus("Portakabin A", null, "Portakabin A Description", new LatLng(51.589795, -0.230213), "Ground Level", 1, true));
        list.add(new Campus("PAG01", PORTAKABIN_A, "Classroom", new LatLng(51.589905, -0.230136), "Ground Level", 1, true));
        list.add(new Campus("PAG02", PORTAKABIN_A, "Classroom", new LatLng(51.589819, -0.230155), "Ground Level", 1, true));
        list.add(new Campus("PAG03", PORTAKABIN_A, "Classroom", new LatLng(51.589691, -0.230168), "Ground Level", 1, true));
        list.add(new Campus("PAG04", PORTAKABIN_A, "Classroom", new LatLng(51.589702, -0.230317), "Ground Level", 1, true));
        list.add(new Campus("PAG05", PORTAKABIN_A, "Toilet 1", new LatLng(51.589863, -0.230144), "Ground Level", 1, true));
        list.add(new Campus("PAG06", PORTAKABIN_A, "Toilet 2", new LatLng(51.589863, -0.230144), "Ground Level", 1, true));
        list.add(new Campus("PAG07", PORTAKABIN_A, "Toilet 3", new LatLng(51.589697, -0.230244), "Ground Level", 1, true));
        list.add(new Campus("PAG08", PORTAKABIN_A, "Toilet 4", new LatLng(51.589697, -0.230244), "Ground Level", 1, true));

        /*Portakabin A first floor*/

        list.add(new Campus("PA101", PORTAKABIN_A, "Classroom", new LatLng(51.589691, -0.230164), "First Level", 0, true));
        list.add(new Campus("PA102", PORTAKABIN_A, "Classroom", new LatLng(51.589691, -0.230165), "First Level", 0, true));
        list.add(new Campus("PA103", PORTAKABIN_A, "Toilet 1", new LatLng(51.589691, -0.230166), "First Level", 0, true));
        list.add(new Campus("PA104", PORTAKABIN_A, "Toilet 2", new LatLng(51.589691, -0.230167), "First Level", 0, true));

        /*Portakabin B ground floor*/
        list.add(new Campus("Portakabin B", null, "Portakabin B Description", new LatLng(51.590750, -0.229020), "Ground Level", 0, true));
        list.add(new Campus("PBG01", PORTAKABIN_B, "Classroom", new LatLng(51.590750, -0.229020), "Ground Level", 0, true));
        list.add(new Campus("PBG02", PORTAKABIN_B, "Classroom", new LatLng(51.590655, -0.229057), "Ground Level", 0, true));

        /*Vine ground floor*/

        list.add(new Campus("Vine Building", null, "Vine Building Description", new LatLng(51.590637, -0.230763), "Ground Floor", 1, true));
        list.add(new Campus("VG01", VINE, "Meeting Room", new LatLng(51.590575, -0.230748), "Ground Floor", 1, true));
        list.add(new Campus("VG02", VINE, "Meeting Room", new LatLng(51.590586, -0.230860), "Ground Floor", 1, true));
        list.add(new Campus("VG04", VINE, "Male Toilet", new LatLng(51.590717, -0.230900), "Ground Floor", 1, true));
        list.add(new Campus("VG05", VINE, "Classroom", new LatLng(51.590681, -0.230840), "Ground Floor", 1, true));
        list.add(new Campus("VG06", VINE, "Classroom", new LatLng(51.590670, -0.230756), "Ground Floor", 1, true));
        list.add(new Campus("VG07", VINE, "Classroom", new LatLng(51.590662, -0.230671), "Ground Floor", 1, true));
        list.add(new Campus("VG08", VINE, "Female Toilet", new LatLng(51.590686, -0.230596), "Ground Floor", 1, true));

        /*Vine first Floor*/

        list.add(new Campus("V101", VINE, "Meeting Room", new LatLng(51.590574, -0.230743), "First Floor", 0, true));
        list.add(new Campus("V102", VINE, "Meeting Room", new LatLng(51.590590, -0.230856), "First Floor", 0, true));
        list.add(new Campus("V103", VINE, "Classroom", new LatLng(51.590676, -0.230842), "First Floor", 0, true));
        list.add(new Campus("V104", VINE, "Classroom", new LatLng(51.590671, -0.230755), "First Floor", 0, true));
        list.add(new Campus("V105", VINE, "Classroom", new LatLng(51.590663, -0.230672), "First Floor", 0, true));

        /*MDX House ground floor*/
        list.add(new Campus("MDX House", null, "MDX House", new LatLng(51.589976, -0.230764), "Ground Floor", 0, true));

        /*MDX House basement */
        list.add(new Campus("Student Union", MDXHOUSE, "MDXSU", new LatLng(51.590136, -0.230874), "Basement", 1, true));
        list.add(new Campus("Gym", MDXHOUSE, "Fitness Pod", new LatLng(51.589927, -0.230906), "Basement", 1, true));
        list.add(new Campus("Real Tennis Club", MDXHOUSE, "Real Tennis Club", new LatLng(51.589771, -0.230951), "Basement", 1, true));
        list.add(new Campus("Dance Studio", MDXHOUSE, "Dance Studio", new LatLng(51.589735, -0.230543), "Basement", 0, false));

        /*Sheppard Library*/

        list.add(new Campus("Sheppard Library", null, "Main University Library", new LatLng(51.590456, -0.229614), "Ground Floor", 3, true));//SET

        /*Sheppard Library basement*/

        list.add(new Campus("SB01", SHEPPARDLIBRARY, "Open Access Area", new LatLng(51.590661, -0.229612), "Basement", 4, true));
        list.add(new Campus("SB03", SHEPPARDLIBRARY, "Open Access Area", new LatLng(51.590748, -0.229431), "Basement", 4, true));
        list.add(new Campus("SB05", SHEPPARDLIBRARY, "Teaching Resource Room", new LatLng(51.590382, -0.229402), "Basement", 4, true));
        list.add(new Campus("SB12A", SHEPPARDLIBRARY, "Material Room", new LatLng(51.590219, -0.229574), "Basement", 4, true));
        list.add(new Campus("SB16", SHEPPARDLIBRARY, "Material Room", new LatLng(51.590417, -0.229588), "Basement", 4, true));
        list.add(new Campus("SB19", CIRCLE_CAFE, "Circle Café (Costa Coffee)", new LatLng(51.590469, -0.229837), "Basement", 4, true));

        /*Sheppard Library ground floor*/

        list.add(new Campus("SG01", SHEPPARDLIBRARY, "Unihelp", new LatLng(51.590359, -0.229603), "Ground Floor", 3, true));
        list.add(new Campus("SG02", SHEPPARDLIBRARY, "Book Sorting Room", new LatLng(51.590316, -0.229317), "Ground Floor", 3, true));
        list.add(new Campus("SG09", SHEPPARDLIBRARY, "Disabled Toilet", new LatLng(51.590763, -0.229379), "Ground Floor", 3, true));
        list.add(new Campus("SG10", SHEPPARDLIBRARY, "Female Toilet", new LatLng(51.590735, -0.229399), "Ground Floor", 3, true));
        list.add(new Campus("SG11", SHEPPARDLIBRARY, "Male Toilet", new LatLng(51.590745, -0.229455), "Ground Floor", 3, true));
        list.add(new Campus("SG12A", SHEPPARDLIBRARY, "Group Study Space / IT", new LatLng(51.590693, -0.229437), "Ground Floor", 3, true));
        list.add(new Campus("SG12B", SHEPPARDLIBRARY, "Group Study Space / IT", new LatLng(51.590642, -0.229453), "Ground Floor", 3, true));
        list.add(new Campus("SG13A", SHEPPARDLIBRARY, "Group Study Space / IT", new LatLng(51.590589, -0.229471), "Ground Floor", 3, true));
        list.add(new Campus("SG13B", SHEPPARDLIBRARY, "Group Study Space / IT", new LatLng(51.590536, -0.229480), "Ground Floor", 3, true));
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

        list.add(new Campus("S101", SHEPPARDLIBRARY, "Open Access Study Space", new LatLng(51.590660, -0.229613), "First Floor", 2, true));
        list.add(new Campus("S103", SHEPPARDLIBRARY, "Disabled Toilet", new LatLng(51.590730, -0.229399), "First Floor", 2, true));
        list.add(new Campus("S104", SHEPPARDLIBRARY, "Female Toilet", new LatLng(51.590730, -0.229399), "First Floor", 2, true));
        list.add(new Campus("S105", SHEPPARDLIBRARY, "Male Toilet", new LatLng(51.590730, -0.229399), "First Floor", 2, true));
        list.add(new Campus("S106", SHEPPARDLIBRARY, "Group Study Space / IT", new LatLng(51.590588, -0.229486), "First Floor", 2, true));
        list.add(new Campus("S107", SHEPPARDLIBRARY, "Group Study Space / IT", new LatLng(51.590641, -0.229456), "First Floor", 2, true));
        list.add(new Campus("S110", SHEPPARDLIBRARY, "Meeting Room", new LatLng(51.590440, -0.229470), "First Floor", 2, true));
        list.add(new Campus("S111", SHEPPARDLIBRARY, "Financial Market Lab", new LatLng(51.590304, -0.229313), "First Floor", 2, true));
        list.add(new Campus("S112", SHEPPARDLIBRARY, "Group Study Space / IT", new LatLng(51.590233, -0.229330), "First Floor", 2, true));
        list.add(new Campus("S118", SHEPPARDLIBRARY, "Group Study Space / IT", new LatLng(51.590500, -0.229862), "First Floor", 2, true));
        list.add(new Campus("S119", SHEPPARDLIBRARY, "Open Access / Group Study Space / Study Hub", new LatLng(51.590417, -0.229655), "First Floor", 2, true));

        /*Sheppard Library 2nd floor*/

        list.add(new Campus("S201", SHEPPARDLIBRARY, "Open Access Study Space", new LatLng(51.590659, -0.229606), "Second Floor", 1, true));
        list.add(new Campus("S203", SHEPPARDLIBRARY, "Disabled Toilet", new LatLng(51.590730, -0.229399), "Second Floor", 1, true));
        list.add(new Campus("S204", SHEPPARDLIBRARY, "Female Toilet", new LatLng(51.590730, -0.229399), "Second Floor", 1, true));
        list.add(new Campus("S205", SHEPPARDLIBRARY, "Male Toilet", new LatLng(51.590730, -0.229399), "Second Floor", 1, true));
        list.add(new Campus("S206", SHEPPARDLIBRARY, "Silent Study / PC Room", new LatLng(51.590694, -0.229433), "Second Floor", 1, true));
        list.add(new Campus("S215", SHEPPARDLIBRARY, "Open Access Silent Study Space", new LatLng(51.590351, -0.229596), "Second Floor", 1, true));
        list.add(new Campus("S216", SHEPPARDLIBRARY, "Silent Study / PC Room", new LatLng(51.590306, -0.229326), "Second Floor", 1, true));
        list.add(new Campus("S217", SHEPPARDLIBRARY, "Silent Study / PC Area", new LatLng(51.590250, -0.229364), "Second Floor", 1, true));
        list.add(new Campus("S218", SHEPPARDLIBRARY, "Bookshelves", new LatLng(51.590470, -0.229887), "Second Floor", 1, true));
        list.add(new Campus("S219", SHEPPARDLIBRARY, "Student Bookable Group Study Space", new LatLng(51.590518, -0.229841), "Second Floor", 1, true));

        /*Sheppard Library 3rd floor*/

        list.add(new Campus("S302", SHEPPARDLIBRARY, "Open Access Study Space", new LatLng(51.590365, -0.229577), "Third Floor", 0, true));
        list.add(new Campus("S303", SHEPPARDLIBRARY, "Silent Study Space Open Area", new LatLng(51.590308, -0.229326), "Third Floor", 0, true));
        list.add(new Campus("S304", SHEPPARDLIBRARY, "Silent Study Space / IT", new LatLng(51.590237, -0.229350), "Third Floor", 0, true));
        list.add(new Campus("S305", SHEPPARDLIBRARY, "Silent Study Space / IT", new LatLng(51.590490, -0.229879), "Third Floor", 0, true));

        /*Williams Building basement*/
        list.add(new Campus("WB05", WILLIAMS, "Goods In - Post Room", new LatLng(51.590582, -0.228934), "Basement", 2, false));

        /*Williams Building ground floor*/

        list.add(new Campus("Williams Building", null, "Williams Building Description", new LatLng(51.590478, -0.228681), "Ground Floor", 1, true));
        list.add(new Campus("WG07", WILLIAMS, "Female Toilet 1", new LatLng(51.590502, -0.228898), "Ground Floor", 1, true));
        list.add(new Campus("WG32", WILLIAMS, "Meeting Room", new LatLng(51.590523, -0.228726), "Ground Floor", 1, true));
        list.add(new Campus("WG33", WILLIAMS, "Translation Room", new LatLng(51.590528, -0.228629), "Ground Floor", 1, true));
        list.add(new Campus("WG35", WILLIAMS, "CDS Printing Room", new LatLng(51.590498, -0.228469), "Ground Floor", 1, true));
        list.add(new Campus("WG37", WILLIAMS, "Male Toilet", new LatLng(51.590435, -0.228221), "Ground Floor", 1, true));
        list.add(new Campus("WG38", WILLIAMS, "Disabled Toilet", new LatLng(51.590436, -0.228186), "Ground Floor", 1, true));
        list.add(new Campus("WG39", WILLIAMS, "Female Toilet 2", new LatLng(51.590464, -0.228206), "Ground Floor", 1, true));
        list.add(new Campus("WG41", WILLIAMS, "Library @ Williams", new LatLng(51.590615, -0.228059), "Ground Floor", 1, true));
        list.add(new Campus("WG44", WILLIAMS, "Study Space", new LatLng(51.590599, -0.228180), "Ground Floor", 1, true));
        list.add(new Campus("WG45", WILLIAMS, "Study Space", new LatLng(51.590599, -0.228181), "Ground Floor", 1, true));
        list.add(new Campus("WG46", WILLIAMS, "Study Space", new LatLng(51.590599, -0.228182), "Ground Floor", 1, true));
        list.add(new Campus("WG47", WILLIAMS, "LRS Office", new LatLng(51.590608, -0.228236), "Ground Floor", 1, true));
        list.add(new Campus("WG48", WILLIAMS, "Classroom", new LatLng(51.590616, -0.228343), "Ground Floor", 1, true));
        list.add(new Campus("WG49", WILLIAMS, "Classroom", new LatLng(51.590671, -0.228323), "Ground Floor", 1, true));
        list.add(new Campus("WG50", WILLIAMS, "Classroom", new LatLng(51.590725, -0.228313), "Ground Floor", 1, true));
        list.add(new Campus("WG51", WILLIAMS, "LRS Meeting Room", new LatLng(51.590699, -0.228202), "Ground Floor", 1, true));

        /*Williams Building 1st floor*/

        list.add(new Campus("W142", WILLIAMS, "Classroom", new LatLng(51.590486, -0.228347), "First Floor", 0, true));
        list.add(new Campus("W143", WILLIAMS, "Disabled Toilet", new LatLng(51.590420, -0.228196), "First Floor", 0, true));
        list.add(new Campus("W144", WILLIAMS, "Male Toilet", new LatLng(51.590439, -0.228218), "First Floor", 0, true));
        list.add(new Campus("W145", WILLIAMS, "Female Toilet", new LatLng(51.590463, -0.228206), "First Floor", 0, true));
        list.add(new Campus("W147", WILLIAMS, "Classroom", new LatLng(51.590518, -0.228192), "First Floor", 0, true));
        list.add(new Campus("W148", WILLIAMS, "Classroom", new LatLng(51.590615, -0.228330), "First Floor", 0, true));
        list.add(new Campus("W149", WILLIAMS, "Classroom", new LatLng(51.590670, -0.228327), "First Floor", 0, true));
        list.add(new Campus("W150", WILLIAMS, "Classroom", new LatLng(51.590720, -0.228296), "First Floor", 0, true));
        list.add(new Campus("W151", WILLIAMS, "Classroom", new LatLng(51.590686, -0.228169), "First Floor", 0, true));
        list.add(new Campus("W152", WILLIAMS, "Classroom", new LatLng(51.590732, -0.228148), "First Floor", 0, true));
        list.add(new Campus("W153", WILLIAMS, "Classroom", new LatLng(51.590779, -0.228097), "First Floor", 0, true));
        list.add(new Campus("W154", WILLIAMS, "Classroom", new LatLng(51.590765, -0.227971), "First Floor", 0, true));
        list.add(new Campus("W155", WILLIAMS, "Classroom", new LatLng(51.590698, -0.227986), "First Floor", 0, true));
        list.add(new Campus("W156", WILLIAMS, "Classroom", new LatLng(51.590641, -0.228003), "First Floor", 0, true));
        list.add(new Campus("W157", WILLIAMS, "Classroom", new LatLng(51.590565, -0.228022), "First Floor", 0, true));
        list.add(new Campus("W158", WILLIAMS, "Classroom", new LatLng(51.590496, -0.228045), "First Floor", 0, true));
        list.add(new Campus("W159", WILLIAMS, "Classroom", new LatLng(51.590429, -0.228076), "First Floor", 0, true));
        list.add(new Campus("W160", WILLIAMS, "Classroom", new LatLng(51.590368, -0.228092), "First Floor", 0, true));

        /*Barn ground floor*/

        list.add(new Campus("Barn", null, "Barn Description", new LatLng(51.590982, -0.228562), "Ground Floor", 0, true));
        list.add(new Campus("BAG01", BARN, "Meeting Room", new LatLng(51.590944, -0.228581), "Ground Floor", 0, true));
        list.add(new Campus("BAG02", BARN, "Classroom", new LatLng(51.591041, -0.228582), "Ground Floor", 0, true));

        /*Grove Block A Basement*/
        list.add(new Campus("GB01", GROVE_BLOCK_A, "Digital Photography And Reprographics", new LatLng(51.588518, -0.230839), "Basement", 5, true));
        list.add(new Campus("GB04", GROVE_BLOCK_A, "Digital Photography And Reprographics", new LatLng(51.588731, -0.230444), "Basement", 5, true));
        list.add(new Campus("GB05", GROVE_BLOCK_A, "Photography", new LatLng(51.588670, -0.230553), "Basement", 5, true));
        list.add(new Campus("GB07", GROVE_BLOCK_A, "Loading Bay", new LatLng(51.588586, -0.230503), "Basement", 5, true));
        //list.add(new Campus("GB08",new LatLng(51.588626, -0.230557),5,GROVE_BLOCK_A,"Large Print Room"));
        list.add(new Campus("GB09", GROVE_BLOCK_A, "Loading Bay", new LatLng(51.588626, -0.230557), "Basement", 5, true));
        list.add(new Campus("GB10", GROVE_BLOCK_A, "Colour Darkroom", new LatLng(51.588626, -0.230557), "Basement", 5, true));
        list.add(new Campus("GB12", GROVE_BLOCK_A, "Darkroom", new LatLng(51.588531, -0.230564), "Basement", 5, true));
        list.add(new Campus("GB17A", GROVE_BLOCK_A, "Photography Studio", new LatLng(51.588583, -0.230805), "Basement", 5, true));
        list.add(new Campus("GB17B", GROVE_BLOCK_A, "Photography Studio", new LatLng(51.588560, -0.230701), "Basement", 5, true));
        list.add(new Campus("GB19A", GROVE_BLOCK_A, "Photography Studio", new LatLng(51.588625, -0.230737), "Basement", 5, true));
        list.add(new Campus("GB19B", GROVE_BLOCK_A, "Photography Studio", new LatLng(51.588682, -0.230708), "Basement", 5, true));
        list.add(new Campus("GB21", GROVE_BLOCK_A, "Disabled Toilet)", new LatLng(51.588753, -0.230682), "Basement", 5, true));
        list.add(new Campus("GB22", GROVE_BLOCK_A, "Female Toilet", new LatLng(51.588753, -0.230682), "Basement", 5, true));
        list.add(new Campus("GB23", GROVE_BLOCK_A, "Male Toilet", new LatLng(51.588753, -0.230682), "Basement", 5, true));

        /*Grove Block A Ground Floor*/
        list.add(new Campus("Grove Block A", null, "Grove Block A Description", new LatLng(51.588695, -0.230596), "Ground Floor", 4, true));
        list.add(new Campus("GG01", GROVE_BLOCK_A, "Plastic/Wood/Metal Workshop", new LatLng(51.588774, -0.230414), "Ground Floor", 4, true));
        list.add(new Campus("GG04", GROVE_BLOCK_A, "Plastic/Wood/Metal Workshop", new LatLng(51.588697, -0.230232), "Ground Floor", 4, true));
        list.add(new Campus("GG07", GROVE_BLOCK_A, "Storage & Dispensing Storage", new LatLng(51.588618, -0.230458), "Ground Floor", 4, true));
        list.add(new Campus("GG11", GROVE_BLOCK_A, "3D Cad Workshop Laser Room", new LatLng(51.588553, -0.230557), "Ground Floor", 4, true));
        list.add(new Campus("GG12", GROVE_BLOCK_A, "Cam Machine Room", new LatLng(51.588520, -0.230449), "Ground Floor", 4, true));
        list.add(new Campus("GG13", GROVE_BLOCK_A, "Cad Lab Room", new LatLng(51.588515, -0.230559), "Ground Floor", 4, true));
        list.add(new Campus("GG14", GROVE_BLOCK_A, "Spray Room", new LatLng(51.588492, -0.230365), "Ground Floor", 4, true));
        list.add(new Campus("GG15", GROVE_BLOCK_A, "Ceramics & Glass Workshop", new LatLng(51.588393, -0.230387), "Ground Floor", 4, true));
        list.add(new Campus("GG17", GROVE_BLOCK_A, "Ceramics Reclaim", new LatLng(51.588353, -0.230283), "Ground Floor", 4, true));
        list.add(new Campus("GG18", GROVE_BLOCK_A, "Small Metal Workshop", new LatLng(51.588395, -0.230519), "Ground Floor", 4, true));
        list.add(new Campus("GG20", GROVE_BLOCK_A, "Jewellery Buffers", new LatLng(51.588407, -0.230624), "Ground Floor", 4, true));
        list.add(new Campus("GG21", GROVE_BLOCK_A, "Jewellery Casting", new LatLng(51.588379, -0.230634), "Ground Floor", 4, true));
        list.add(new Campus("GG22", GROVE_BLOCK_A, "Disabled Toilet 1", new LatLng(51.588479, -0.230601), "Ground Floor", 4, true));
        list.add(new Campus("GG23", GROVE_BLOCK_A, "Male Toilet 1", new LatLng(51.588457, -0.230618), "Ground Floor", 4, true));
        list.add(new Campus("GG25", GROVE_BLOCK_A, "Textile & Print Making Workshop", new LatLng(51.588467, -0.230939), "Ground Floor", 4, true));
        list.add(new Campus("GG26", GROVE_BLOCK_A, "Screen Wash", new LatLng(51.588502, -0.230777), "Ground Floor", 4, true));
        list.add(new Campus("GG29", GROVE_BLOCK_A, "Printing Textile Dye Lab", new LatLng(51.588528, -0.230917), "Ground Floor", 4, true));
        list.add(new Campus("GG30", GROVE_BLOCK_A, "Exposure Plate Making", new LatLng(51.588544, -0.230966), "Ground Floor", 4, true));
        list.add(new Campus("GG31", GROVE_BLOCK_A, "Digital Fabric Print Workshop", new LatLng(51.588567, -0.231052), "Ground Floor", 4, true));
        list.add(new Campus("GG33", GROVE_BLOCK_A, "Electronics Interface Lab", new LatLng(51.588623, -0.231026), "Ground Floor", 4, true));
        list.add(new Campus("GG34", GROVE_BLOCK_A, "Green Room", new LatLng(51.588660, -0.231003), "Ground Floor", 4, true));
        list.add(new Campus("GG35", GROVE_BLOCK_A, "Dressing Room", new LatLng(51.588686, -0.230984), "Ground Floor", 4, true));
        list.add(new Campus("GG36", GROVE_BLOCK_A, "Tv News Room", new LatLng(51.588728, -0.230949), "Ground Floor", 4, true));
        list.add(new Campus("GG37", GROVE_BLOCK_A, "Loan Space", new LatLng(51.588775, -0.230860), "Ground Floor", 4, true));
        list.add(new Campus("GG38", GROVE_BLOCK_A, "Tv News Room", new LatLng(51.588819, -0.230800), "Ground Floor", 4, true));
        list.add(new Campus("GG39", GROVE_BLOCK_A, "Tv News Room", new LatLng(51.588860, -0.230763), "Ground Floor", 4, true));
        list.add(new Campus("GG41", GROVE_BLOCK_A, "Tv Studio B", new LatLng(51.588652, -0.230704), "Ground Floor", 4, true));
        list.add(new Campus("GG44", GROVE_BLOCK_A, "Tv Studio A", new LatLng(51.588617, -0.230870), "Ground Floor", 4, true));
        list.add(new Campus("GG45", GROVE_BLOCK_A, "Office Kit Room", new LatLng(51.588686, -0.230815), "Ground Floor", 4, true));
        list.add(new Campus("GG46", GROVE_BLOCK_A, "Studio Gallery", new LatLng(51.588695, -0.230877), "Ground Floor", 4, true));
        list.add(new Campus("GG47", GROVE_BLOCK_A, "Disabled Toilet 2", new LatLng(51.588732, -0.230701), "Ground Floor", 4, true));
        list.add(new Campus("GG48", GROVE_BLOCK_A, "Female Toilet 1", new LatLng(51.588741, -0.230667), "Ground Floor", 4, true));


        /*Grove Block A First Floor*/
        list.add(new Campus("Grove Atrium", GROVE_BLOCK_A, "Grove Atrium", new LatLng(51.588613, -0.230449), "First Floor", 3, true));
        list.add(new Campus("Grove Coffee Pod", GROVE_BLOCK_A, "Grove Coffee Pod", new LatLng(51.588590, -0.230527), "First Floor", 3, true));
        list.add(new Campus("G101", GROVE_BLOCK_A, "Lab", new LatLng(51.588723, -0.230552), "First Floor", 3, true));
        list.add(new Campus("G104A", GROVE_BLOCK_A, "Animation Studio", new LatLng(51.588728, -0.230392), "First Floor", 3, true));
        list.add(new Campus("G104B", GROVE_BLOCK_A, "Animation Studio", new LatLng(51.588728, -0.230392), "First Floor", 3, true));
        list.add(new Campus("G105", GROVE_BLOCK_A, "Middlesex", new LatLng(51.588695, -0.230257), "First Floor", 3, true));
        list.add(new Campus("G107", GROVE_BLOCK_A, "Green Room", new LatLng(51.588397, -0.230322), "First Floor", 3, true));
        //list.add(new Campus("G109",new LatLng(51.588417, -0.230405),3,GROVE_BLOCK_A,"Middlesex"));
        list.add(new Campus("G110", GROVE_BLOCK_A, "Workshops", new LatLng(51.588389, -0.230401), "First Floor", 3, true));
        list.add(new Campus("G115", GROVE_BLOCK_A, "Digital Media Workshop 1", new LatLng(51.588383, -0.230525), "First Floor", 3, true));
        list.add(new Campus("G116", GROVE_BLOCK_A, "Digital Media Workshop 2", new LatLng(51.588400, -0.230620), "First Floor", 3, true));
        list.add(new Campus("G117", GROVE_BLOCK_A, "Disabled Toilet 1", new LatLng(51.588484, -0.230611), "First Floor", 3, true));
        list.add(new Campus("G118", GROVE_BLOCK_A, "Male Toilet (Grove, First Floor)", new LatLng(51.588461, -0.230623), "First Floor", 3, true));
        list.add(new Campus("G120", GROVE_BLOCK_A, "Davinci Resolve Studio 1 2", new LatLng(51.588482, -0.230778), "First Floor", 3, true));
        list.add(new Campus("G121", GROVE_BLOCK_A, "Davinci Resolve Studio 1 2", new LatLng(51.588482, -0.230778), "First Floor", 3, true));
        list.add(new Campus("G122", GROVE_BLOCK_A, "Middlesex", new LatLng(51.588482, -0.230778), "First Floor", 3, true));
        list.add(new Campus("G122A", GROVE_BLOCK_A, "Middlesex", new LatLng(51.588482, -0.230778), "First Floor", 3, true));
        list.add(new Campus("G123A", GROVE_BLOCK_A, "Digital Media Workshop 3", new LatLng(51.588482, -0.230778), "First Floor", 3, true));
        list.add(new Campus("G123B", GROVE_BLOCK_A, "Digital Media Workshop 4", new LatLng(51.588499, -0.231068), "First Floor", 3, true));
        list.add(new Campus("G124", GROVE_BLOCK_A, "Suite 11", new LatLng(51.588519, -0.230927), "First Floor", 3, true));
        list.add(new Campus("G125", GROVE_BLOCK_A, "Suite 12", new LatLng(51.588519, -0.230927), "First Floor", 3, true));
        list.add(new Campus("G126", GROVE_BLOCK_A, "Suite 9", new LatLng(51.588519, -0.230927), "First Floor", 3, true));
        list.add(new Campus("G127", GROVE_BLOCK_A, "Suite 10", new LatLng(51.588519, -0.230927), "First Floor", 3, true));
        list.add(new Campus("G128", GROVE_BLOCK_A, "Suite 7", new LatLng(51.588519, -0.230927), "First Floor", 3, true));
        list.add(new Campus("G129", GROVE_BLOCK_A, "Suite 8", new LatLng(51.588519, -0.230927), "First Floor", 3, true));
        list.add(new Campus("G130", GROVE_BLOCK_A, "Suite 5", new LatLng(51.588519, -0.230927), "First Floor", 3, true));
        list.add(new Campus("G131", GROVE_BLOCK_A, "Suite 6", new LatLng(51.588519, -0.230927), "First Floor", 3, true));
        list.add(new Campus("G132", GROVE_BLOCK_A, "Suite 3", new LatLng(51.588519, -0.230927), "First Floor", 3, true));
        list.add(new Campus("G133", GROVE_BLOCK_A, "Suite 4", new LatLng(51.588519, -0.230927), "First Floor", 3, true));
        list.add(new Campus("G134", GROVE_BLOCK_A, "Suite 1", new LatLng(51.588519, -0.230927), "First Floor", 3, true));
        list.add(new Campus("G135", GROVE_BLOCK_A, "Suite 2", new LatLng(51.588519, -0.230927), "First Floor", 3, true));
        list.add(new Campus("G136", GROVE_BLOCK_A, "Pro Tools Avid Suite", new LatLng(51.588519, -0.230927), "First Floor", 3, true));
        list.add(new Campus("G137", GROVE_BLOCK_A, "Recording Studio", new LatLng(51.588499, -0.231068), "First Floor", 3, true));
        list.add(new Campus("G137A", GROVE_BLOCK_A, "Recording Studio", new LatLng(51.588499, -0.231068), "First Floor", 3, true));
//        list.add(new Campus("G139",new LatLng(51.588607, -0.231022),3,GROVE_BLOCK_A,"Technician Office "));
        list.add(new Campus("G140", GROVE_BLOCK_A, "Office", new LatLng(51.588638, -0.231006), "First Floor", 3, true));
        list.add(new Campus("G141", GROVE_BLOCK_A, "Office", new LatLng(51.588659, -0.230993), "First Floor", 3, true));
//        list.add(new Campus("G142",new LatLng(51.588683, -0.230980),3,GROVE_BLOCK_A,"Office"));
//        list.add(new Campus("G143",new LatLng(51.588721, -0.230953),3,GROVE_BLOCK_A,"Office"));
//        list.add(new Campus("G144",new LatLng(51.588721, -0.230953),3,GROVE_BLOCK_A,"Office"));
//        list.add(new Campus("G145",new LatLng(51.588735, -0.230758),3,GROVE_BLOCK_A,"Office"));
        list.add(new Campus("G146", GROVE_BLOCK_A, "Visitors And Lighting Gallery", new LatLng(51.588686, -0.230719), "First Floor", 3, true));
        list.add(new Campus("G146A", GROVE_BLOCK_A, "Middlesex", new LatLng(51.588701, -0.230835), "First Floor", 3, true));
        list.add(new Campus("G147", GROVE_BLOCK_A, "Visitors And Lighting Gallery", new LatLng(51.588686, -0.230719), "First Floor", 3, true));
        list.add(new Campus("G148", GROVE_BLOCK_A, "Visitors And Lighting Gallery", new LatLng(51.588686, -0.230719), "First Floor", 3, true));
        list.add(new Campus("G149", GROVE_BLOCK_A, "Radio Recording Studio", new LatLng(51.588418, -0.230785), "First Floor", 3, true));
//        list.add(new Campus("G150",new LatLng(51.588418, -0.230785),3,GROVE_BLOCK_A,"Radio Recording Studio"));
//        list.add(new Campus("Disabled Toilet 2 (Grove, First Floor)",new LatLng(51.588685, -0.230481),3,GROVE_BLOCK_A,"Middlesex"));
//        list.add(new Campus("Female Toilet (Grove, First Floor)",new LatLng(51.588731, -0.230476),3,GROVE_BLOCK_A,"Middlesex"));


        /*Grove Block A Second Floor*/
        list.add(new Campus("G201", GROVE_BLOCK_A, "Fashion Workshop", new LatLng(51.588775, -0.230429), "Second Floor", 2, true));
        list.add(new Campus("G203", GROVE_BLOCK_A, "Fashion Workshop", new LatLng(51.5886039, -0.2306015), "Second Floor", 2, true));
        list.add(new Campus("G204", GROVE_BLOCK_A, "Knit & Weave Workshop", new LatLng(51.588706, -0.230237), "Second Floor", 2, true));
        list.add(new Campus("G205", GROVE_BLOCK_A, "Yarn Store", new LatLng(51.588631, -0.230173), "Second Floor", 2, true));
        list.add(new Campus("G206", GROVE_BLOCK_A, "Fashion Studio 4", new LatLng(51.588380, -0.230317), "Second Floor", 2, true));
        list.add(new Campus("G207", GROVE_BLOCK_A, "Fashion Studio 3", new LatLng(51.588387, -0.230425), "Second Floor", 2, true));
        list.add(new Campus("G209", GROVE_BLOCK_A, "Fashion Studio 2", new LatLng(51.588391, -0.230516), "Second Floor", 2, true));
        list.add(new Campus("G210", GROVE_BLOCK_A, "Fashion Studio 1", new LatLng(51.588415, -0.230627), "Second Floor", 2, true));
        list.add(new Campus("G211", GROVE_BLOCK_A, "Disabled Toilet 1", new LatLng(51.588467, -0.230613), "Second Floor", 2, true));
        list.add(new Campus("G212", GROVE_BLOCK_A, "Male Toilet", new LatLng(51.588467, -0.230613), "Second Floor", 2, true));
        list.add(new Campus("G214A", GROVE_BLOCK_A, "Fashion Studio 7", new LatLng(51.588430, -0.230771), "Second Floor", 2, true));
        list.add(new Campus("G214B", GROVE_BLOCK_A, "Fashion Studio 6", new LatLng(51.588464, -0.230918), "Second Floor", 2, true));
        list.add(new Campus("G214C", GROVE_BLOCK_A, "Fashion Studio 5", new LatLng(51.588501, -0.231039), "Second Floor", 2, true));
        list.add(new Campus("G230", GROVE_BLOCK_A, "Post Grad Research Room", new LatLng(51.588567, -0.230725), "Second Floor", 2, true));
        list.add(new Campus("G231", GROVE_BLOCK_A, "Screening Dressing Room", new LatLng(51.588752, -0.230753), "Second Floor", 2, true));
        list.add(new Campus("G234", GROVE_BLOCK_A, "Disabled Toilet 2", new LatLng(51.588724, -0.230585), "Second Floor", 2, true));
        list.add(new Campus("G235", GROVE_BLOCK_A, "Female Toilet", new LatLng(51.588724, -0.230585), "Second Floor", 2, true));

        /*Grove Block A Third Floor*/
        list.add(new Campus("G301", GROVE_BLOCK_A, "Fine Arts Studio B", new LatLng(51.588726, -0.230342), "Third Floor", 1, true));
        list.add(new Campus("G304", GROVE_BLOCK_A, "Fine Arts Studio C", new LatLng(51.588625, -0.230689), "Third Floor", 1, true));
        list.add(new Campus("G305", GROVE_BLOCK_A, "Fine Arts Studio D", new LatLng(51.588436, -0.230765), "Third Floor", 1, true));
        list.add(new Campus("G307", GROVE_BLOCK_A, "Disabled Toilet", new LatLng(51.588744, -0.230683), "Third Floor", 1, true));
        list.add(new Campus("G308", GROVE_BLOCK_A, "Female Toilet", new LatLng(51.588744, -0.230683), "Third Floor", 1, true));
        list.add(new Campus("G309", GROVE_BLOCK_A, "Fine Arts Studio A", new LatLng(51.588761, -0.230849), "Third Floor", 1, true));

        /*Grove Block A Fourth Floor*/
        list.add(new Campus("G401", GROVE_BLOCK_A, "Graphics Studio 3", new LatLng(51.588791, -0.230657), "Fourth Floor", 0, true));
        list.add(new Campus("G404", GROVE_BLOCK_A, "Female Toilet", new LatLng(51.588744, -0.230683), "Fourth Floor", 0, true));
        list.add(new Campus("G405", GROVE_BLOCK_A, "Male Toilet", new LatLng(51.588744, -0.230683), "Fourth Floor", 0, true));
        list.add(new Campus("G406A", GROVE_BLOCK_A, "Graphics Studio 2", new LatLng(51.588697, -0.230657), "Fourth Floor", 0, true));

        /*Grove Block B Ground Floor*/
        list.add(new Campus("GG71", GROVE_BLOCK_B, "PDE Teaching Room", new LatLng(51.589024, -0.230361), "Ground Floor", 2, true));
        list.add(new Campus("GG72", GROVE_BLOCK_B, "Teaching Room", new LatLng(51.588973, -0.230201), "Ground Floor", 2, true));
        list.add(new Campus("GG73", GROVE_BLOCK_B, "Teaching Room", new LatLng(51.588973, -0.230202), "Ground Floor", 2, true));
        list.add(new Campus("GG74", GROVE_BLOCK_B, "Teaching Room", new LatLng(51.588973, -0.230203), "Ground Floor", 2, true));
        list.add(new Campus("GG75", GROVE_BLOCK_B, "Ensemble Room", new LatLng(51.588973, -0.230204), "Ground Floor", 2, true));
        list.add(new Campus("GG76", GROVE_BLOCK_B, "Teaching Room", new LatLng(51.588973, -0.230205), "Ground Floor", 2, true));
        list.add(new Campus("GG77", GROVE_BLOCK_B, "Teaching Room", new LatLng(51.588973, -0.230206), "Ground Floor", 2, true));
        list.add(new Campus("GG78", GROVE_BLOCK_B, "Female Toilet 2", new LatLng(51.588917, -0.230184), "Ground Floor", 2, true));
        list.add(new Campus("GG79", GROVE_BLOCK_B, "Disabled Toilet 3", new LatLng(51.588917, -0.230185), "Ground Floor", 2, true));

        /*Grove Block B First Floor*/
        list.add(new Campus("G170", GROVE_BLOCK_B, "Male Toilet 1", new LatLng(51.588921, -0.230289), "First Floor", 1, true));
        list.add(new Campus("G171", GROVE_BLOCK_B, "Interior Design / Interior Architecture", new LatLng(51.588921, -0.230289), "First Floor", 1, true));
        list.add(new Campus("G172", GROVE_BLOCK_B, "Interior Design / Interior Architecture", new LatLng(51.588921, -0.230289), "First Floor", 1, true));
        list.add(new Campus("G174", GROVE_BLOCK_B, "Music Practice Room 2", new LatLng(51.588932, -0.230115), "First Floor", 1, true));
        list.add(new Campus("G175", GROVE_BLOCK_B, "Music Practice Room 3", new LatLng(51.588932, -0.230114), "First Floor", 1, true));
        list.add(new Campus("G176", GROVE_BLOCK_B, "Music Practice Room 4", new LatLng(51.588932, -0.230111), "First Floor", 1, true));
        list.add(new Campus("G177", GROVE_BLOCK_B, "Music Teaching Room 4", new LatLng(51.588932, -0.230110), "First Floor", 1, true));
        list.add(new Campus("G178", GROVE_BLOCK_B, "Band Room", new LatLng(51.588932, -0.230109), "First Floor", 1, true));
        list.add(new Campus("G179", GROVE_BLOCK_B, "Live Room", new LatLng(51.588932, -0.230108), "First Floor", 1, true));
        list.add(new Campus("G180", GROVE_BLOCK_B, "Recording Studio", new LatLng(51.588932, -0.230107), "First Floor", 1, true));
        list.add(new Campus("G181", GROVE_BLOCK_B, "Music Practice Room 5", new LatLng(51.588932, -0.230112), "First Floor", 1, true));
        list.add(new Campus("G182", GROVE_BLOCK_B, "Music Practice Room 6", new LatLng(51.588932, -0.230113), "First Floor", 1, true));

        /*Grove Block B Second Floor*/
        list.add(new Campus("G270", GROVE_BLOCK_B, "Disabled Toilet 3", new LatLng(51.588964, -0.230414), "Second Floor", 0, true));
        list.add(new Campus("G271", GROVE_BLOCK_B, "TA Studio 2", new LatLng(51.589027, -0.230357), "Second Floor", 0, true));
        list.add(new Campus("G272", GROVE_BLOCK_B, "TA Studio 3", new LatLng(51.588990, -0.230259), "Second Floor", 0, true));
        list.add(new Campus("G274", GROVE_BLOCK_B, "Concert Room", new LatLng(51.588965, -0.230102), "Second Floor", 0, true));


        /*Grove Block C Ground Floor*/
        list.add(new Campus("GG90", GROVE_BLOCK_C, "Student Break Out Area", new LatLng(51.589277, -0.230058), "Ground Floor", 2, true));
        list.add(new Campus("GG92", GROVE_BLOCK_C, "Dressing Room", new LatLng(51.589228, -0.229969), "Ground Floor", 2, true));
        list.add(new Campus("GG93", GROVE_BLOCK_C, "Classroom Photography", new LatLng(51.589172, -0.230001), "Ground Floor", 2, true));
        list.add(new Campus("GG94", GROVE_BLOCK_C, "Studio Life Drawing", new LatLng(51.589189, -0.230119), "Ground Floor", 2, true));
        list.add(new Campus("GG96", GROVE_BLOCK_C, "Male Toilet 2", new LatLng(51.589172, -0.230206), "Ground Floor", 2, true));
        list.add(new Campus("GG97", GROVE_BLOCK_C, "", new LatLng(51.589184, -0.230290), "Ground Floor", 2, true));
        list.add(new Campus("GG98", GROVE_BLOCK_C, "Disabled Toilet 4", new LatLng(51.589222, -0.230266), "Ground Floor", 2, true));

        /*Grove Block C First Floor*/
        list.add(new Campus("G190", GROVE_BLOCK_C, "Dance Studio", new LatLng(51.589246, -0.230051), "First Floor", 1, true));
        list.add(new Campus("G191", GROVE_BLOCK_C, "Dance Studio", new LatLng(51.589246, -0.230052), "First Floor", 1, true));
        list.add(new Campus("G192", GROVE_BLOCK_C, "Dance Studio", new LatLng(51.589246, -0.230053), "First Floor", 1, true));


         /*Ravensfield Bulding*/

        list.add(new Campus("Ravensfield House", null, "Ravensfield House Description", new LatLng(51.588633, -0.227835), "Ground Floor", 0, false));

        /*Ravensfield Ground floor*/

        list.add(new Campus("RVG01", RAVENSFIELDS, "Grab & Go", new LatLng(51.588682, -0.228016), "Ground Floor", 1, false));
        list.add(new Campus("RVG02", RAVENSFIELDS, "TTA", new LatLng(51.588743, -0.227978), "Ground Floor", 1, false));
        list.add(new Campus("RVG03", RAVENSFIELDS, "Stage Workshop", new LatLng(51.588684, -0.227899), "Ground Floor", 1, false));
        list.add(new Campus("RVG04", RAVENSFIELDS, "TTA1", new LatLng(51.588736, -0.227787), "Ground Floor", 1, false));
        list.add(new Campus("RVG04A", RAVENSFIELDS, "TTA2", new LatLng(51.588755, -0.227875), "Ground Floor", 1, false));
        list.add(new Campus("RVG06", RAVENSFIELDS, "Performance Space", new LatLng(51.588632, -0.227805), "Ground Floor", 1, false));
        list.add(new Campus("RVG09", RAVENSFIELDS, "Studio 5", new LatLng(51.588593, -0.228068), "Ground Floor", 1, false));
        list.add(new Campus("RVG11", RAVENSFIELDS, "Studio 4", new LatLng(51.588542, -0.227954), "Ground Floor", 1, false));
        list.add(new Campus("RVG14", RAVENSFIELDS, "Male Toilet", new LatLng(51.588515, -0.227837), "Ground Floor", 1, false));
        list.add(new Campus("RVG15", RAVENSFIELDS, "Disabled Toilet", new LatLng(51.588528, -0.227807), "Ground Floor", 1, false));
        list.add(new Campus("RVG16", RAVENSFIELDS, "Female Toilet", new LatLng(51.588504, -0.227799), "Ground Floor", 1, false));
        list.add(new Campus("RVG17", RAVENSFIELDS, "Studio", new LatLng(51.588639, -0.227657), "Ground Floor", 1, false));
        list.add(new Campus("RVG17A", RAVENSFIELDS, "Studio", new LatLng(51.588576, -0.227693), "Ground Floor", 1, false));
        list.add(new Campus("RVG18", RAVENSFIELDS, "Local Prop Store 2", new LatLng(51.588674, -0.227628), "Ground Floor", 1, false));
        list.add(new Campus("RVG19", RAVENSFIELDS, "Local Prop Store 1", new LatLng(51.588489, -0.227734), "Ground Floor", 1, false));

        /*Ravensfields House 1st floor*/

        list.add(new Campus("RV101", RAVENSFIELDS, "Generic Teaching Space", new LatLng(51.588676, -0.228021), "First Floor", 0, false));
        list.add(new Campus("RV103", RAVENSFIELDS, "Generic Teaching Space", new LatLng(51.588772, -0.227954), "First Floor", 0, false));
        list.add(new Campus("RV104", RAVENSFIELDS, "Generic Teaching Space", new LatLng(51.588758, -0.227871), "First Floor", 0, false));
        list.add(new Campus("RV107", RAVENSFIELDS, "Generic Teaching Space", new LatLng(51.588725, -0.227737), "First Floor", 0, false));
        list.add(new Campus("RV109", RAVENSFIELDS, "Dressing Room", new LatLng(51.588696, -0.227631), "First Floor", 0, false));
        list.add(new Campus("RV110", RAVENSFIELDS, "Fitting Room", new LatLng(51.588675, -0.227663), "First Floor", 0, false));
        list.add(new Campus("RV111", RAVENSFIELDS, "Costume Workshop", new LatLng(51.588599, -0.227673), "First Floor", 0, false));
        list.add(new Campus("RV112", RAVENSFIELDS, "Pattern Store", new LatLng(51.588659, -0.227612), "First Floor", 0, false));
        list.add(new Campus("RV113", RAVENSFIELDS, "Costume Store", new LatLng(51.588521, -0.227689), "First Floor", 0, false));
        list.add(new Campus("RV114", RAVENSFIELDS, "Laundry", new LatLng(51.588519, -0.227742), "First Floor", 0, false));
        list.add(new Campus("RV115", RAVENSFIELDS, "Male Toilet", new LatLng(51.588498, -0.227795), "First Floor", 0, false));
        list.add(new Campus("RV116", RAVENSFIELDS, "Disabled Shower", new LatLng(51.588525, -0.227809), "First Floor", 0, false));
        list.add(new Campus("RV117", RAVENSFIELDS, "Female Toilet", new LatLng(51.588512, -0.227833), "First Floor", 0, false));
        list.add(new Campus("RV118", RAVENSFIELDS, "Rehearsal Room 1", new LatLng(51.588534, -0.227907), "First Floor", 0, false));
        list.add(new Campus("RV121", RAVENSFIELDS, "Rehearsal Room 2", new LatLng(51.588557, -0.228009), "First Floor", 0, false));


        /*Town Hall*/

        list.add(new Campus("Town Hall", null, "Town Hall Description", new LatLng(51.588161, -0.229226), "Ground Floor", 0, false));

        /*Town Hall ground floor*/

        list.add(new Campus("TG02", TOWNHALL, "Office", new LatLng(51.588161, -0.229226), "Ground Floor", 3, false));
        list.add(new Campus("TG03", TOWNHALL, "Office", new LatLng(51.588161, -0.229227), "Ground Floor", 3, false));
        list.add(new Campus("TG04", TOWNHALL, "Office", new LatLng(51.588161, -0.229228), "Ground Floor", 3, false));
        list.add(new Campus("TG06", TOWNHALL, "Office", new LatLng(51.588161, -0.229226), "Ground Floor", 3, false));
        list.add(new Campus("TG07", TOWNHALL, "Office", new LatLng(51.588161, -0.229226), "Ground Floor", 3, false));
        list.add(new Campus("TG08", TOWNHALL, "Office", new LatLng(51.588182, -0.229426), "Ground Floor", 3, false));
        list.add(new Campus("TG09", TOWNHALL, "Office", new LatLng(51.588195, -0.229614), "Ground Floor", 3, false));
        list.add(new Campus("TG10", TOWNHALL, "Office", new LatLng(51.588198, -0.229667), "Ground Floor", 3, false));
        list.add(new Campus("TG11", TOWNHALL, "Office", new LatLng(51.588201, -0.229672), "Ground Floor", 3, false));
        list.add(new Campus("TG12", TOWNHALL, "Office", new LatLng(51.588208, -0.229746), "Ground Floor", 3, false));
        list.add(new Campus("TG12A", TOWNHALL, "Office", new LatLng(51.588208, -0.229746), "Ground Floor", 3, false));
        list.add(new Campus("TG12B", TOWNHALL, "Office", new LatLng(51.588208, -0.229746), "Ground Floor", 3, false));
        list.add(new Campus("TG13", TOWNHALL, "Office", new LatLng(51.588215, -0.229809), "Ground Floor", 3, false));
        list.add(new Campus("TG14", TOWNHALL, "Office", new LatLng(51.588229, -0.229927), "Ground Floor", 3, false));
        list.add(new Campus("TG15", TOWNHALL, "Office", new LatLng(51.588231, -0.230037), "Ground Floor", 3, false));
        list.add(new Campus("TG16", TOWNHALL, "Office", new LatLng(51.588231, -0.230037), "Ground Floor", 3, false));
        list.add(new Campus("TG17", TOWNHALL, "Office", new LatLng(51.588231, -0.230037), "Ground Floor", 3, false));
        list.add(new Campus("TG18", TOWNHALL, "Office", new LatLng(51.588231, -0.230037), "Ground Floor", 3, false));
        list.add(new Campus("TG19", TOWNHALL, "Office", new LatLng(51.588354, -0.230028), "Ground Floor", 3, false));
        list.add(new Campus("TG19A", TOWNHALL, "Office", new LatLng(51.588354, -0.230028), "Ground Floor", 3, false));
        list.add(new Campus("TG19B", TOWNHALL, "Office", new LatLng(51.588354, -0.230028), "Ground Floor", 3, false));
        list.add(new Campus("TG20A", TOWNHALL, "Office", new LatLng(51.588354, -0.230028), "Ground Floor", 3, false));
        list.add(new Campus("TG20B", TOWNHALL, "Office", new LatLng(51.588215, -0.229809), "Ground Floor", 3, false));
        list.add(new Campus("TG21", TOWNHALL, "Office", new LatLng(51.588208, -0.229746), "Ground Floor", 3, false));
        list.add(new Campus("TG22", TOWNHALL, "Office", new LatLng(51.588204, -0.229704), "Ground Floor", 3, false));
        list.add(new Campus("TG23", TOWNHALL, "Office", new LatLng(51.588197, -0.229643), "Ground Floor", 3, false));
        list.add(new Campus("TG26", TOWNHALL, "Office", new LatLng(51.588181, -0.229405), "Ground Floor", 3, false));
        list.add(new Campus("TG28", TOWNHALL, "Office", new LatLng(51.588161, -0.229229), "Ground Floor", 3, false));
        list.add(new Campus("TG31", TOWNHALL, "Office", new LatLng(51.588401, -0.229119), "Ground Floor", 3, false));
        list.add(new Campus("TG32", TOWNHALL, "Office", new LatLng(51.588408, -0.229154), "Ground Floor", 3, false));
        list.add(new Campus("TG33", TOWNHALL, "Office", new LatLng(51.588424, -0.229216), "Ground Floor", 3, false));
        list.add(new Campus("TG34", TOWNHALL, "Office", new LatLng(51.588446, -0.229276), "Ground Floor", 3, false));
        list.add(new Campus("TG35", TOWNHALL, "Office", new LatLng(51.588461, -0.229330), "Ground Floor", 3, false));
        list.add(new Campus("TG36", TOWNHALL, "Office", new LatLng(51.588475, -0.229383), "Ground Floor", 3, false));
        list.add(new Campus("TG37", TOWNHALL, "Office", new LatLng(51.588529, -0.229531), "Ground Floor", 3, false));
        list.add(new Campus("TG38", TOWNHALL, "Office", new LatLng(51.588555, -0.229642), "Ground Floor", 3, false));
        list.add(new Campus("TG39", TOWNHALL, "Office", new LatLng(51.588555, -0.229642), "Ground Floor", 3, false));
        list.add(new Campus("TG40", TOWNHALL, "Office", new LatLng(51.588577, -0.229704), "Ground Floor", 3, false));
        list.add(new Campus("TG44", TOWNHALL, "Office", new LatLng(51.588577, -0.229704), "Ground Floor", 3, false));
        list.add(new Campus("TG45", TOWNHALL, "Office", new LatLng(51.588575, -0.229714), "Ground Floor", 3, false));
        list.add(new Campus("TG46", TOWNHALL, "Office", new LatLng(51.588575, -0.229712), "Ground Floor", 3, false));
        list.add(new Campus("TG47", TOWNHALL, "Office", new LatLng(51.588486, -0.229877), "Ground Floor", 3, false));
        list.add(new Campus("TG47A", TOWNHALL, "Office", new LatLng(51.588486, -0.229877), "Ground Floor", 3, false));
        list.add(new Campus("TG48", TOWNHALL, "Office", new LatLng(51.588486, -0.229877), "Ground Floor", 3, false));
        list.add(new Campus("TG49", TOWNHALL, "Office", new LatLng(51.588589, -0.229809), "Ground Floor", 3, false));
        list.add(new Campus("TG52", TOWNHALL, "Office", new LatLng(51.588348, -0.229978), "Ground Floor", 3, false));
        list.add(new Campus("TG53", TOWNHALL, "Office", new LatLng(51.588348, -0.229978), "Ground Floor", 3, false));
        list.add(new Campus("TG54", TOWNHALL, "Office", new LatLng(51.588348, -0.229978), "Ground Floor", 3, false));
        list.add(new Campus("TG55", TOWNHALL, "Office", new LatLng(51.588348, -0.229978), "Ground Floor", 3, false));
        list.add(new Campus("TG56", TOWNHALL, "Office", new LatLng(51.588348, -0.229978), "Ground Floor", 3, false));
        list.add(new Campus("TG57", TOWNHALL, "Office", new LatLng(51.588348, -0.229978), "Ground Floor", 3, false));
        list.add(new Campus("TG58", TOWNHALL, "Office", new LatLng(51.588348, -0.229978), "Ground Floor", 3, false));
        list.add(new Campus("TG60", TOWNHALL, "Office", new LatLng(51.588589, -0.229809), "Ground Floor", 3, false));
        list.add(new Campus("TG60A", TOWNHALL, "Office", new LatLng(51.588592, -0.229754), "Ground Floor", 3, false));
        list.add(new Campus("TG61", TOWNHALL, "Office", new LatLng(51.588575, -0.229713), "Ground Floor", 3, false));
        list.add(new Campus("TG62", TOWNHALL, "Office", new LatLng(51.588575, -0.229714), "Ground Floor", 3, false));
        list.add(new Campus("TG63", TOWNHALL, "Office", new LatLng(51.588577, -0.229704), "Ground Floor", 3, false));
        list.add(new Campus("TG64", TOWNHALL, "Office", new LatLng(51.588577, -0.229704), "Ground Floor", 3, false));
        list.add(new Campus("TG65", TOWNHALL, "Office", new LatLng(51.588529, -0.229531), "Ground Floor", 3, false));
        list.add(new Campus("TG66", TOWNHALL, "Office", new LatLng(51.588475, -0.229383), "Ground Floor", 3, false));
        list.add(new Campus("TG67", TOWNHALL, "Office", new LatLng(51.588461, -0.229330), "Ground Floor", 3, false));
        list.add(new Campus("TG68", TOWNHALL, "Office", new LatLng(51.588446, -0.229276), "Ground Floor", 3, false));
        list.add(new Campus("TG69", TOWNHALL, "Office", new LatLng(51.588424, -0.229216), "Ground Floor", 3, false));
        list.add(new Campus("TG70", TOWNHALL, "Office", new LatLng(51.588408, -0.229154), "Ground Floor", 3, false));
        list.add(new Campus("TG71", TOWNHALL, "Office", new LatLng(51.588401, -0.229119), "Ground Floor", 3, false));
        list.add(new Campus("TG72", TOWNHALL, "Office", new LatLng(51.588388, -0.229071), "Ground Floor", 3, false));
        list.add(new Campus("TG73", TOWNHALL, "Office", new LatLng(51.588348, -0.228988), "Ground Floor", 3, false));
        list.add(new Campus("TG74", TOWNHALL, "Middlesex", new LatLng(51.588348, -0.228988), "Ground Floor", 3, false));

        /*Town Hall 1st floor*/

        list.add(new Campus("T106", TOWNHALL, "Office", new LatLng(51.588196, -0.229352), "First Floor", 2, false));
        list.add(new Campus("T107", TOWNHALL, "Office", new LatLng(51.588196, -0.229358), "First Floor", 2, false));
        list.add(new Campus("T108", TOWNHALL, "Office", new LatLng(51.588196, -0.229352), "First Floor", 2, false));
        list.add(new Campus("T109", TOWNHALL, "Office", new LatLng(51.588202, -0.229428), "First Floor", 2, false));
        list.add(new Campus("T110", TOWNHALL, "Office", new LatLng(51.588205, -0.229479), "First Floor", 2, false));
        list.add(new Campus("T110A", TOWNHALL, "Office", new LatLng(51.588205, -0.229479), "First Floor", 2, false));
        list.add(new Campus("T111", TOWNHALL, "Office", new LatLng(51.588213, -0.229546), "First Floor", 2, false));
        list.add(new Campus("T112", TOWNHALL, "Office", new LatLng(51.588219, -0.229610), "First Floor", 2, false));
        list.add(new Campus("T113", TOWNHALL, "Office", new LatLng(51.588233, -0.229704), "First Floor", 2, false));
        list.add(new Campus("T114", TOWNHALL, "Office", new LatLng(51.588233, -0.229706), "First Floor", 2, false));
        list.add(new Campus("T115", TOWNHALL, "Office", new LatLng(51.588248, -0.230010), "First Floor", 2, false));
        list.add(new Campus("T116", TOWNHALL, "Office", new LatLng(51.588248, -0.230010), "First Floor", 2, false));
        list.add(new Campus("T116A", TOWNHALL, "Office", new LatLng(51.588248, -0.230010), "First Floor", 2, false));
        list.add(new Campus("T117", TOWNHALL, "Office", new LatLng(51.588333, -0.229968), "First Floor", 2, false));
        list.add(new Campus("T118", TOWNHALL, "Office", new LatLng(51.588333, -0.229969), "First Floor", 2, false));
        list.add(new Campus("T120", TOWNHALL, "Office", new LatLng(51.588333, -0.229971), "First Floor", 2, false));
        list.add(new Campus("T121", TOWNHALL, "Office", new LatLng(51.588333, -0.229970), "First Floor", 2, false));
        list.add(new Campus("T122", TOWNHALL, "Office", new LatLng(51.588233, -0.229707), "First Floor", 2, false));
        list.add(new Campus("T123", TOWNHALL, "Office", new LatLng(51.588233, -0.229705), "First Floor", 2, false));
        list.add(new Campus("T124", TOWNHALL, "Office", new LatLng(51.588233, -0.229703), "First Floor", 2, false));
        list.add(new Campus("T125", TOWNHALL, "Office", new LatLng(51.588219, -0.229611), "First Floor", 2, false));
        list.add(new Campus("T126", TOWNHALL, "Office", new LatLng(51.588219, -0.229609), "First Floor", 2, false));
        list.add(new Campus("T127", TOWNHALL, "Office", new LatLng(51.588213, -0.229546), "First Floor", 2, false));
        list.add(new Campus("T129", TOWNHALL, "Office", new LatLng(51.588202, -0.229428), "First Floor", 2, false));
        list.add(new Campus("T130", TOWNHALL, "Office", new LatLng(51.588202, -0.229428), "First Floor", 2, false));
        list.add(new Campus("T131", TOWNHALL, "Office", new LatLng(51.588196, -0.229359), "First Floor", 2, false));
        list.add(new Campus("T143", TOWNHALL, "Office", new LatLng(51.588493, -0.229904), "First Floor", 2, false));
        list.add(new Campus("T145", TOWNHALL, "Office", new LatLng(51.588493, -0.229904), "First Floor", 2, false));
        list.add(new Campus("T146", TOWNHALL, "Office", new LatLng(51.588493, -0.229904), "First Floor", 2, false));
        list.add(new Campus("T147", TOWNHALL, "Office", new LatLng(51.588493, -0.229904), "First Floor", 2, false));
        list.add(new Campus("T148", TOWNHALL, "Office", new LatLng(51.588535, -0.229852), "First Floor", 2, false));
        list.add(new Campus("T149", TOWNHALL, "Office", new LatLng(51.588535, -0.229852), "First Floor", 2, false));
        list.add(new Campus("T150", TOWNHALL, "Office", new LatLng(51.588535, -0.229852), "First Floor", 2, false));
        list.add(new Campus("T157", TOWNHALL, "Office", new LatLng(51.588535, -0.229852), "First Floor", 2, false));

        /*Town Hall 2nd floor*/

        list.add(new Campus("T201", TOWNHALL, "Office", new LatLng(51.588196, -0.229356), "Second Floor", 1, false));
        list.add(new Campus("T202", TOWNHALL, "Office", new LatLng(51.588196, -0.229357), "Second Floor", 1, false));
        list.add(new Campus("T203", TOWNHALL, "Office", new LatLng(51.588196, -0.229355), "Second Floor", 1, false));
        list.add(new Campus("T204", TOWNHALL, "Office", new LatLng(51.588196, -0.229354), "Second Floor", 1, false));
        list.add(new Campus("T205", TOWNHALL, "Office", new LatLng(51.588196, -0.229353), "Second Floor", 1, false));
        list.add(new Campus("T207", TOWNHALL, "Office", new LatLng(51.588348, -0.229978), "Second Floor", 1, false));
        list.add(new Campus("T209", TOWNHALL, "Office", new LatLng(51.588348, -0.229978), "Second Floor", 1, false));
        list.add(new Campus("T210", TOWNHALL, "Office", new LatLng(51.588348, -0.229978), "Second Floor", 1, false));
        list.add(new Campus("T211", TOWNHALL, "Office", new LatLng(51.588348, -0.229978), "Second Floor", 1, false));

        list.add(new Campus("T303", TOWNHALL, "Office", new LatLng(51.588348, -0.229978), "Third Floor", 0, false));
        list.add(new Campus("T304", TOWNHALL, "Office", new LatLng(51.588348, -0.229978), "Third Floor", 0, false));
        list.add(new Campus("T305", TOWNHALL, "Office", new LatLng(51.588348, -0.229978), "Third Floor", 0, false));

        return list;
    }

}
