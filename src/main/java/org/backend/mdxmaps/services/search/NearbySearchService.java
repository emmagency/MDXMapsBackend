package org.backend.mdxmaps.services.search;

import org.apache.solr.client.solrj.SolrClient;
import org.apache.solr.client.solrj.SolrQuery;
import org.apache.solr.client.solrj.SolrServerException;
import org.apache.solr.client.solrj.impl.HttpSolrClient;
import org.apache.solr.client.solrj.response.QueryResponse;
import org.apache.solr.common.SolrDocumentList;
import org.backend.mdxmaps.model.responseObjects.search.NearbySearchResponse;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Callable;
import java.util.stream.Collectors;

/**
 * Created by Emmanuel Keboh on 22/08/2017.
 */
public class NearbySearchService implements Callable<List<NearbySearchResponse>> {
    private static String NAME = "name";
    private static String SUB_NAME = "subName";
    private static String ADDRESS = "address";
    private static String CATEGORY = "category";
    private static String LATLNG = "latLng";

    private String query, type, solrNearbyUrl;

    public NearbySearchService(String query, String type, String solrNearbyUrl) {
        this.query = query;
        this.type = type;
        this.solrNearbyUrl = solrNearbyUrl;
    }

    @Override
    public List<NearbySearchResponse> call() throws Exception {
        SolrClient solrClient = new HttpSolrClient.Builder(solrNearbyUrl).build();

        SolrQuery solrQuery = new SolrQuery();
        solrQuery.setQuery(constructQuery(query, type, solrQuery));
        solrQuery.set("rows", 5);
        solrQuery.setRequestHandler("/query");

        QueryResponse response;
        try {
            response = solrClient.query(solrQuery);
        } catch (IOException | SolrServerException e) {
            e.printStackTrace();
            return null;
        }

        return getNearbyResponseList(response.getResults());
    }

    private String constructQuery(String query, String type, SolrQuery solrQuery) {
        if (type != null && type.equals("listing")) {
            solrQuery.set("q.op", "AND");
            return "{!child of=\"isParent:true\"}name:" + query;
        }
        return query;
    }

    private ArrayList<NearbySearchResponse> getNearbyResponseList(SolrDocumentList solrDocuments) {
        return (ArrayList<NearbySearchResponse>) solrDocuments.stream()
                .map(doc -> NearbySearchResponse.create((String) doc.getFieldValue(NAME),
                        (String) doc.getFieldValue(CATEGORY), (String) doc.getFieldValue(SUB_NAME),
                        (String) doc.getFieldValue(ADDRESS), (String) doc.getFieldValue(LATLNG)))
                .collect(Collectors.toList());
    }
}