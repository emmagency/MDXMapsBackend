package org.backend.mdxmaps.model.enums;

/**
 * Created by Emmanuel Keboh on 08/04/2017.
 */
public enum Constants {

    SOLR_ROOMS_URL("solrRoomsUrl"),
    SOLR_NEARBY_URL("solrNearbyUrl"),
    SOLR_TRANSPORT_URL("solrTransportUrl");

    private String value;

    Constants(String value) {
        this.value = value;
    }

    public String getValue() {
        return value;
    }
}
