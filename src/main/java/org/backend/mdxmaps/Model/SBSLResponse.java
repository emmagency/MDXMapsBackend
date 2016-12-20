package org.backend.mdxmaps.Model;

import java.util.ArrayList;

/**
 * Created by Anonymous on 27/11/2016.
 */

/*SBSL: Same building, same level*/
public class SBSLResponse {

    public ArrayList<ArrayList<LatLng>> sbslFinalistsLatLng;

    public ArrayList<ArrayList<String>> routeDescription;

    public SBSLResponse(ArrayList<ArrayList<LatLng>> sbslFinalistsLatLng, ArrayList<ArrayList<String>> routeDescription) {
        this.sbslFinalistsLatLng = sbslFinalistsLatLng;
        this.routeDescription = routeDescription;
    }


}
