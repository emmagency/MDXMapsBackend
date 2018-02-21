package org.backend.mdxmaps.model.solr;

import com.fasterxml.jackson.annotation.JsonInclude;

/**
 * Created by Emmanuel Keboh on 10/09/2017.
 */
@JsonInclude(JsonInclude.Include.NON_EMPTY)
public class Nearby {

    private String name, category, subName, address, latLng;

    public Nearby(String name, String category, String subName, String address, String latLng) {
        this.name = name;
        this.category = category;
        this.subName = subName;
        this.address = address;
        this.latLng = latLng;
    }

    public String getName() {
        return name;
    }

    public String getCategory() {
        return category;
    }

    public String getSubName() {
        return subName;
    }

    public String getAddress() {
        return address;
    }

    public String getLatLng() {
        return latLng;
    }
}
