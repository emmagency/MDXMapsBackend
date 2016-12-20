package org.backend.mdxmaps.Resources;

import org.backend.mdxmaps.Services.DirectionsService;

import javax.inject.Singleton;
import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

/**
 * Created by Emmanuel Keboh on 27/11/2016.
 */

@Path("directions")
@Singleton
public class DirectionsResource implements ServletContextListener {

    private ExecutorService service = Executors.newCachedThreadPool();

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Response getDirections(@QueryParam("start") String start, @QueryParam("end") String end,
                                  @DefaultValue("SBSL") @QueryParam("mot") String mot) {

        try {
            return Response.ok(service.submit(new DirectionsService()).get()).build();
        } catch (InterruptedException | ExecutionException e) {
            e.printStackTrace();
        }

        return null;

    }

    @Override
    public void contextInitialized(ServletContextEvent servletContextEvent) {
        System.out.println("ServletContextListener started");
    }

    @Override
    public void contextDestroyed(ServletContextEvent servletContextEvent) {
        service.shutdownNow();
        try {
            service.awaitTermination(10000, TimeUnit.MILLISECONDS);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println("ServletContextListener destroyed");
    }
}
