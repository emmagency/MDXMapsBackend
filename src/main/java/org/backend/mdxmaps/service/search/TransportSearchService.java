package org.backend.mdxmaps.service.search;

import org.apache.solr.client.solrj.SolrClient;
import org.apache.solr.client.solrj.SolrQuery;
import org.apache.solr.client.solrj.SolrServerException;
import org.apache.solr.client.solrj.impl.HttpSolrClient;
import org.apache.solr.client.solrj.response.QueryResponse;
import org.apache.solr.common.SolrDocumentList;
import org.backend.mdxmaps.model.solr.model.Transport;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Callable;
import java.util.stream.Collectors;

/**
 * Created by Emmanuel Keboh on 30/10/2017.
 */
public class TransportSearchService implements Callable<List<Transport>> {
    private static String NAME = "name";

    private static String SERVES = "serves";
    private static String CATEGORY = "category";
    private static String TOWARDS = "towards";
    private static String LATLNG = "latLng";

    private String query, solrTransportUrl;
    private boolean isListing;
    private int rows;

    public TransportSearchService(String query, boolean isListing, int rows, String solrTransportUrl) {
        this.query = query;
        this.solrTransportUrl = solrTransportUrl;
        this.isListing = isListing;
        this.rows = rows;
    }


    @Override
    public List<Transport> call() throws Exception {
        SolrClient solrClient = new HttpSolrClient.Builder(solrTransportUrl).build();

        SolrQuery solrQuery = new SolrQuery();
        solrQuery.setQuery(constructQuery(query, isListing, solrQuery));
        solrQuery.set("rows", rows > 0 ? rows : 5);
        solrQuery.setRequestHandler("/query");

        QueryResponse response;
        try {
            response = solrClient.query(solrQuery);
        } catch (IOException | SolrServerException e) {
            e.printStackTrace();
            return null;
        }

        return getTransportResponseList(response.getResults());

    }


    private String constructQuery(String query, boolean isListing, SolrQuery solrQuery) {
        if (isListing) {
            solrQuery.set("q.op", "AND");
            return "{!child of=\"isParent:true\"}name:" + query;
        }
        return query;
    }

    private ArrayList<Transport> getTransportResponseList(SolrDocumentList solrDocuments) {
        return (ArrayList<Transport>) solrDocuments.stream()
                .map(doc -> new Transport((String) doc.getFieldValue(NAME),
                        (String) doc.getFieldValue(CATEGORY), (String) doc.getFieldValue(SERVES),
                        (String) doc.getFieldValue(TOWARDS), (String) doc.getFieldValue(LATLNG)))
                .collect(Collectors.toList());
    }
}
