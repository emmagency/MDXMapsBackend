package org.backend.mdxmaps.service.search;

import org.apache.solr.client.solrj.SolrClient;
import org.apache.solr.client.solrj.SolrQuery;
import org.apache.solr.client.solrj.SolrServerException;
import org.apache.solr.client.solrj.impl.HttpSolrClient;
import org.apache.solr.client.solrj.request.QueryRequest;
import org.apache.solr.client.solrj.response.QueryResponse;
import org.apache.solr.common.SolrDocumentList;
import org.backend.mdxmaps.model.solr.Nearby;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Callable;
import java.util.stream.Collectors;

/**
 * Created by Emmanuel Keboh on 22/08/2017.
 */
public class NearbySearchService implements Callable<List<Nearby>> {
    private static String NAME = "name";
    private static String SUB_NAME = "subName";
    private static String ADDRESS = "address";
    private static String CATEGORY = "category";
    private static String LATLNG = "latLng";

    private String query, solrNearbyUrl;
    private boolean isListing;
    private int rows;

    public NearbySearchService(String query, boolean isListing, int rows, String solrNearbyUrl) {
        this.query = query;
        this.isListing = isListing;
        this.rows = rows;
        this.solrNearbyUrl = solrNearbyUrl;
    }

    @Override
    public List<Nearby> call() throws Exception {
        SolrClient solrClient = new HttpSolrClient.Builder(solrNearbyUrl).build();

        SolrQuery solrQuery = new SolrQuery();
        solrQuery.setRequestHandler("/query");
        solrQuery.setQuery(constructQuery(query, isListing, solrQuery));
        solrQuery.set("rows", rows > 0 ? rows : 5);

        QueryRequest request = new QueryRequest(solrQuery);
        request.setBasicAuthCredentials("admin", "N6uCdnrW");

        QueryResponse response;
        try {
            //response = solrClient.query(solrQuery);
            response = request.process(solrClient);
        } catch (IOException | SolrServerException e) {
            e.printStackTrace();
            return null;
        }

        return getNearbyResponseList(response.getResults());
    }

    private String constructQuery(String query, boolean isListing, SolrQuery solrQuery) {
        if (isListing) {
            solrQuery.set("q.op", "AND");
            solrQuery.setRequestHandler("/select");
            return "{!child of=\"isParent:true\"}name:" + query;
        }
        return query;
    }

    private ArrayList<Nearby> getNearbyResponseList(SolrDocumentList solrDocuments) {
        return (ArrayList<Nearby>) solrDocuments.stream()
                .map(doc -> new Nearby((String) doc.getFieldValue(NAME),
                        (String) doc.getFieldValue(CATEGORY), (String) doc.getFieldValue(SUB_NAME),
                        (String) doc.getFieldValue(ADDRESS), (String) doc.getFieldValue(LATLNG)))
                .collect(Collectors.toList());
    }
}