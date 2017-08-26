package org.backend.mdxmaps.services.search;

import org.apache.solr.client.solrj.SolrClient;
import org.apache.solr.client.solrj.SolrQuery;
import org.apache.solr.client.solrj.SolrServerException;
import org.apache.solr.client.solrj.impl.HttpSolrClient;
import org.apache.solr.client.solrj.response.QueryResponse;
import org.apache.solr.common.SolrDocumentList;
import org.backend.mdxmaps.model.responseObjects.search.CampusSearchResponse;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.concurrent.Callable;

import static java.util.stream.Collectors.toList;

/**
 * Created by Emmanuel Keboh on 06/03/2017.
 */
public class RoomSearchService implements Callable<List<CampusSearchResponse>> {

    public static String NAME = "name";
    public static String BUILDING = "building";
    public static String LEVEL = "level";
    public static String GOOGLE_MAP_LEVEL = "gMapLevel";
    public static String DESCRIPTION = "description";
    public static String LAT_LNG = "latLng";
    public static String DIRECTIONS_AVAILABLE = "isDirectionsAvailable";
    private final String solrRoomsUrl;

    private String query;

    public RoomSearchService(String query, String solrRoomsUrl) {
        this.query = query;
        this.solrRoomsUrl = solrRoomsUrl;
    }


    @Override
    public List<CampusSearchResponse> call() throws Exception {
        SolrClient solrClient = new HttpSolrClient.Builder(solrRoomsUrl).build();

        SolrQuery solrQuery = new SolrQuery(query);
        solrQuery.set("rows", 5);
        solrQuery.setRequestHandler("/query");

        QueryResponse response;
        try {
            response = solrClient.query(solrQuery);
        } catch (IOException | SolrServerException e) {
            e.printStackTrace();
            return Collections.emptyList();
        }

        return getRoomResponseList(response.getResults());
    }

    private ArrayList<CampusSearchResponse> getRoomResponseList(SolrDocumentList list) {

        return (ArrayList<CampusSearchResponse>) list.stream()
                .map(doc -> CampusSearchResponse.create(
                        (String) doc.getFieldValue(NAME), (String) doc.getFieldValue(BUILDING),
                        (String) doc.getFieldValue(DESCRIPTION), (String) doc.getFieldValue(LAT_LNG),
                        (String) doc.getFieldValue(LEVEL), (Integer) doc.getFieldValue(GOOGLE_MAP_LEVEL),
                        (Boolean) doc.getFieldValue(DIRECTIONS_AVAILABLE)))
                .collect(toList());
    }
}
