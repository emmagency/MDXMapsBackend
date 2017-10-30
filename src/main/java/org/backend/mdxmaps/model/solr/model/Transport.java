package org.backend.mdxmaps.model.solr.model;

import com.fasterxml.jackson.annotation.JsonInclude;

/**
 * Created by Emmanuel Keboh on 10/09/2017.
 */
@JsonInclude(JsonInclude.Include.NON_EMPTY)
public class Transport {

    private String name, category, serves, towards, latLng;

    public Transport(String name, String category, String serves, String towards, String latLng) {
        this.name = name;
        this.category = category;
        this.serves = serves;
        this.towards = towards;
        this.latLng = latLng;
    }

    public String getName() {
        return name;
    }

    public String getCategory() {
        return category;
    }

    public String getServes() {
        return serves;
    }

    public String getTowards() {
        return towards;
    }

    public String getLatLng() {
        return latLng;
    }
}
