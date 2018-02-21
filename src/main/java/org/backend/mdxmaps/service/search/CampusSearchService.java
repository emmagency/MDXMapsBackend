package org.backend.mdxmaps.service.search;

import org.apache.solr.client.solrj.SolrClient;
import org.apache.solr.client.solrj.SolrQuery;
import org.apache.solr.client.solrj.SolrServerException;
import org.apache.solr.client.solrj.impl.HttpSolrClient;
import org.apache.solr.client.solrj.response.QueryResponse;
import org.apache.solr.common.SolrDocumentList;
import org.backend.mdxmaps.model.LatLng;
import org.backend.mdxmaps.model.solr.Campus;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Callable;

import static java.util.stream.Collectors.toList;

/**
 * Created by Emmanuel Keboh on 06/03/2017.
 */
public class CampusSearchService implements Callable<List<Campus>> {

    public static String NAME = "name";
    public static String BUILDING = "building";
    public static String LEVEL = "level";
    public static String GOOGLE_MAP_LEVEL = "gMapLevel";
    public static String DESCRIPTION = "description";
    public static String LAT_LNG = "latLng";
    public static String DIRECTIONS_AVAILABLE = "isDirectionsAvailable";
    private final String solrRoomsUrl;

    private String query;
    private int rows;
    private boolean isDirectionsAvailable;

    public CampusSearchService(String query, int rows, boolean isDirectionsAvailable, String solrRoomsUrl) {
        this.query = query;
        this.rows = rows;
        this.isDirectionsAvailable = isDirectionsAvailable;
        this.solrRoomsUrl = solrRoomsUrl;
    }

    @Override
    public List<Campus> call() throws Exception {
        SolrClient solrClient = new HttpSolrClient.Builder(solrRoomsUrl).build();

        SolrQuery solrQuery = new SolrQuery(isDirectionsAvailable ? constructQuery(query) : query);
        solrQuery.set("rows", rows > 0 ? rows : 5);
        solrQuery.setRequestHandler("/query");

        QueryResponse response;
        try {
            response = solrClient.query(solrQuery);
        } catch (IOException | SolrServerException e) {
            e.printStackTrace();
            return null;
        }

        return getRoomResponseList(response.getResults());
    }

    private String constructQuery(String query) {
        return query + " AND isDirectionsAvailable:true";
    }

    private ArrayList<Campus> getRoomResponseList(SolrDocumentList list) {

        return (ArrayList<Campus>) list.stream()
                .map(doc -> new Campus(
                        (String) doc.getFieldValue(NAME), (String) doc.getFieldValue(BUILDING),
                        (String) doc.getFieldValue(DESCRIPTION), new LatLng((String) doc.getFieldValue(LAT_LNG)),
                        (String) doc.getFieldValue(LEVEL), (Integer) doc.getFieldValue(GOOGLE_MAP_LEVEL),
                        (Boolean) doc.getFieldValue(DIRECTIONS_AVAILABLE)))
                .collect(toList());
    }
}
