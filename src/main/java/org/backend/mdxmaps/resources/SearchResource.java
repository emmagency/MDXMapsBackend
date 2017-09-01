package org.backend.mdxmaps.resources;

import org.backend.mdxmaps.model.responseObjects.search.CampusSearchResponse;
import org.backend.mdxmaps.model.responseObjects.search.MainSearchResponse;
import org.backend.mdxmaps.model.responseObjects.search.NearbySearchResponse;
import org.backend.mdxmaps.services.search.CampusSearchService;
import org.backend.mdxmaps.services.search.NearbySearchService;

import javax.inject.Singleton;
import javax.servlet.ServletContext;
import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import javax.servlet.annotation.WebListener;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.Configuration;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.Collections;
import java.util.List;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

import static org.backend.mdxmaps.model.enums.Constants.SOLR_NEARBY_URL;
import static org.backend.mdxmaps.model.enums.Constants.SOLR_ROOMS_URL;

/**
 * Created by Emmanuel Keboh on 06/03/2017.
 */
@Path("search")
@Singleton
@WebListener
public class SearchResource implements ServletContextListener {

    private ExecutorService service = Executors.newCachedThreadPool();

    @Context
    private ServletContext servletContext;

    @Context
    private Configuration configuration;

    //Main Search
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Response searchAll(@QueryParam("q") String query) {
        return Response.ok(MainSearchResponse.create(querySolrForRooms(query),
                querySolrForNearbyDocs(query, null), null)).build();
    }

    @GET
    @Path("campus")
    @Produces(MediaType.APPLICATION_JSON)
    public Response searchOnCampusData(@QueryParam("q") String query) {
        return Response.ok(querySolrForRooms(query)).build();
    }

    @GET
    @Path("nearbylisting")
    @Produces(MediaType.APPLICATION_JSON)
    public Response searchNearby(@QueryParam("q") String query, @QueryParam("c") String type) {
        return Response.ok(querySolrForNearbyDocs(query, type)).build();
    }

    @GET
    @Path("transport")
    @Produces(MediaType.APPLICATION_JSON)
    public Response tflQuery() {
        return null;
    }

    private List<CampusSearchResponse> querySolrForRooms(String query) {
        if (query.length() > 1) {
            try {
                return service.submit(new CampusSearchService(query,
                        (String) configuration.getProperty(SOLR_ROOMS_URL.getValue()))).get();
            } catch (InterruptedException | ExecutionException e) {
                e.printStackTrace();
                return null;
            }
        }
        return Collections.emptyList();
    }

    private List<NearbySearchResponse> querySolrForNearbyDocs(String query, String type) {
        if (query.length() > 1) {
            try {
                return service.submit(new NearbySearchService(query, type,
                        (String) configuration.getProperty(SOLR_NEARBY_URL.getValue()))).get();
            } catch (InterruptedException | ExecutionException e) {
                e.printStackTrace();
                return null;
            }
        }
        return Collections.emptyList();
    }

    @Override
    public void contextInitialized(ServletContextEvent servletContextEvent) {
        System.out.println("Search ServletContextListener started");
    }

    @Override
    public void contextDestroyed(ServletContextEvent servletContextEvent) {
        service.shutdownNow();
        try {
            service.awaitTermination(10000, TimeUnit.MILLISECONDS);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println("Search ServletContextListener destroyed");
    }
}
