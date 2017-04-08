package org.backend.mdxmaps.Model.Enums;

/**
 * Created by Emmanuel Keboh on 08/04/2017.
 */
public enum Constants {

    SOLR_ROOMS_URL("solrRoomsUrl");

    private String value;

    private Constants(String value) {
        this.value = value;
    }

    public String getValue() {
        return value;
    }
}
