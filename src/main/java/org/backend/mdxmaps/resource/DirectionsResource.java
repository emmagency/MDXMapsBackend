package org.backend.mdxmaps.resource;

import org.backend.mdxmaps.service.DirectionsService;
import org.backend.mdxmaps.service.ResponseService;
import org.backend.mdxmaps.service.filters.Binders;

import javax.inject.Singleton;
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
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

import static org.backend.mdxmaps.model.enums.Constants.SOLR_ROOMS_URL;
import static org.backend.mdxmaps.service.ResponseService.Status.ERROR;

/**
 * Created by Emmanuel Keboh on 27/11/2016.
 */

@Path("directions")
@WebListener
@Singleton
public class DirectionsResource implements ServletContextListener {

    private ExecutorService service = Executors.newCachedThreadPool();

    @Context
    private Configuration configuration;

    @GET
    @Binders.CampusDirection
    @Produces(MediaType.APPLICATION_JSON)
    public Response getDirections(@QueryParam("s") String start, @QueryParam("d") String end,
                                  @QueryParam("m") String mot) {
        if (start != null && !start.equals("") && end != null && !end.equals("")) {
            try {
                String solrRoomsUrl = (String) configuration.getProperty(SOLR_ROOMS_URL.getValue());
                return Response.ok(service.submit(new DirectionsService(start, end, mot, true, solrRoomsUrl)).get()).build();
            } catch (InterruptedException | ExecutionException e) {
                e.printStackTrace();
            }
        }

        return Response.ok(ResponseService.create(ERROR,
                "Error with url parameters!")).build();
    }

    @Override
    public void contextInitialized(ServletContextEvent servletContextEvent) {
        System.out.println("Directions ServletContextListener started");
    }

    @Override
    public void contextDestroyed(ServletContextEvent servletContextEvent) {
        service.shutdownNow();
        try {
            service.awaitTermination(10000, TimeUnit.MILLISECONDS);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println("Directions ServletContextListener destroyed");
    }
}
