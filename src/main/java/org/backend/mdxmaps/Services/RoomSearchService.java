package org.backend.mdxmaps.Services;

import org.apache.solr.client.solrj.SolrClient;
import org.apache.solr.client.solrj.SolrQuery;
import org.apache.solr.client.solrj.SolrServerException;
import org.apache.solr.client.solrj.impl.HttpSolrClient;
import org.apache.solr.client.solrj.response.QueryResponse;
import org.apache.solr.common.SolrDocumentList;
import org.backend.mdxmaps.Model.Solr.RoomSearchResponseObject;

import java.io.IOException;
import java.util.ArrayList;
import java.util.concurrent.Callable;

import static java.util.stream.Collectors.toList;

/**
 * Created by Emmanuel Keboh on 06/03/2017.
 */
public class RoomSearchService implements Callable {

    public static String NAME = "name";
    public static String BUILDING = "building";
    public static String GOOGLE_MAP_LEVEL = "gMapLevel";
    public static String DESCRIPTION = "description";
    public static String LAT_LNG = "latLng";
    public static String DIRECTIONS_AVAILABLE = "directionsAvailable";

    private String query;

    public RoomSearchService(String query) {
        this.query = query;
    }


    @Override
    public Object call() throws Exception {
        String solrURL = "http://localhost:8983/solr/searchRooms";

        SolrClient solrClient = new HttpSolrClient.Builder(solrURL).build();

        SolrQuery solrQuery = new SolrQuery();
        solrQuery.setQuery(query);
        solrQuery.set("rows", 20);
        solrQuery.setRequestHandler("/query");

        SolrDocumentList list = new SolrDocumentList();
        try {
            QueryResponse response = solrClient.query(solrQuery);
            list = response.getResults();
        } catch (IOException | SolrServerException e) {
            e.printStackTrace();
        }

        ArrayList<RoomSearchResponseObject> response = getRoomResponseList(list);

        return response;
    }

    private ArrayList<RoomSearchResponseObject> getRoomResponseList(SolrDocumentList list) {

        return (ArrayList<RoomSearchResponseObject>) list.stream()
                .map(doc -> RoomSearchResponseObject.create(
                        (String) doc.getFieldValue(NAME), (String) doc.getFieldValue(BUILDING),
                        (String) doc.getFieldValue(DESCRIPTION), (String) doc.getFieldValue(LAT_LNG),
                        (Integer) doc.getFieldValue(GOOGLE_MAP_LEVEL),
                        (Boolean) doc.getFieldValue(DIRECTIONS_AVAILABLE)))
                .collect(toList());

    }
}
