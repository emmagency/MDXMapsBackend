package org.backend.mdxmaps.Resources;

import org.backend.mdxmaps.Services.RoomSearchService;

import javax.inject.Singleton;
import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import javax.servlet.annotation.WebListener;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

/**
 * Created by Emmanuel Keboh on 06/03/2017.
 */
@Path("search")
@Singleton
@WebListener
public class SearchResource implements ServletContextListener {

    private ExecutorService service = Executors.newCachedThreadPool();

    @GET
    @Path("rooms")
    @Produces(MediaType.APPLICATION_JSON)
    public Response search(@QueryParam("q") String query) {
        if (query.length() > 1) {
            try {
                return Response.ok(service.submit(new RoomSearchService(query)).get()).build();
            } catch (InterruptedException | ExecutionException e) {
                e.printStackTrace();
            }
        }

        return Response.ok().build();
    }

    @GET
    @Path("tfl")
    @Produces(MediaType.APPLICATION_JSON)
    public Response tflQuery() {
        return null;
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
