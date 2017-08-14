package org.backend.mdxmaps.services;

import org.apache.solr.client.solrj.SolrClient;
import org.apache.solr.client.solrj.SolrQuery;
import org.apache.solr.client.solrj.SolrServerException;
import org.apache.solr.client.solrj.impl.HttpSolrClient;
import org.apache.solr.client.solrj.response.QueryResponse;
import org.apache.solr.common.SolrDocumentList;
import org.backend.mdxmaps.model.solr.CampusSearchResponse;

import java.io.IOException;
import java.util.ArrayList;
import java.util.concurrent.Callable;

import static java.util.stream.Collectors.toList;

/**
 * Created by Emmanuel Keboh on 06/03/2017.
 */
public class RoomSearchService implements Callable<ArrayList<CampusSearchResponse>> {

    public static String NAME = "name";
    public static String BUILDING = "building";
    public static String GOOGLE_MAP_LEVEL = "gMapLevel";
    public static String DESCRIPTION = "description";
    public static String LAT_LNG = "latLng";
    public static String DIRECTIONS_AVAILABLE = "isDirectionsAvailable";
    public final String solrRoomsUrl;

    private String query;

    public RoomSearchService(String query, String solrRoomsUrl) {
        this.query = query;
        this.solrRoomsUrl = solrRoomsUrl;
    }


    @Override
    public ArrayList<CampusSearchResponse> call() throws Exception {
        SolrClient solrClient = new HttpSolrClient.Builder(solrRoomsUrl).build();

        SolrQuery solrQuery = new SolrQuery();
        solrQuery.setQuery(query);
        solrQuery.set("rows", 5);
        solrQuery.setRequestHandler("/query");

        SolrDocumentList list = new SolrDocumentList();
        try {
            QueryResponse response = solrClient.query(solrQuery);
            list = response.getResults();
        } catch (IOException | SolrServerException e) {
            e.printStackTrace();
        }

        return getRoomResponseList(list);
    }

    private ArrayList<CampusSearchResponse> getRoomResponseList(SolrDocumentList list) {

        return (ArrayList<CampusSearchResponse>) list.stream()
                .map(doc -> CampusSearchResponse.create(
                        (String) doc.getFieldValue(NAME), (String) doc.getFieldValue(BUILDING),
                        (String) doc.getFieldValue(DESCRIPTION), (String) doc.getFieldValue(LAT_LNG),
                        (Integer) doc.getFieldValue(GOOGLE_MAP_LEVEL),
                        (Boolean) doc.getFieldValue(DIRECTIONS_AVAILABLE)))
                .collect(toList());

    }
}
