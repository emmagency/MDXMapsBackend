package org.backend.mdxmaps.Resources;

import org.backend.mdxmaps.Model.ServerStatusObject;
import org.backend.mdxmaps.Services.DirectionsService;
import org.backend.mdxmaps.Services.ResponseService;

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
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

import static org.backend.mdxmaps.Services.ResponseService.Status.ERROR;

/**
 * Created by Emmanuel Keboh on 27/11/2016.
 */

@Path("directions")
@WebListener
@Singleton
public class DirectionsResource implements ServletContextListener {

    private ExecutorService service = Executors.newCachedThreadPool();

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Response getDirections(@QueryParam("start") String start, @QueryParam("end") String end,
                                  @QueryParam("mot") String mot) {

        if (start != null && !start.equals("") && end != null && !end.equals("")) {
            try {
                return Response.ok(service.submit(new DirectionsService(start, end, mot)).get()).build();
            } catch (InterruptedException | ExecutionException e) {
                e.printStackTrace();
            }
        }

        return Response.ok(ResponseService.create(ERROR,
                "Something went wrong. FIND IT!!!!!!")).build();

    }

    @GET
    @Path("status")
    @Produces(MediaType.APPLICATION_JSON)
    public Response status() {
        ThreadPoolExecutor executor = (ThreadPoolExecutor) service;
        return Response.ok(new ServerStatusObject(executor.getActiveCount(), executor.getCompletedTaskCount(), executor.getCorePoolSize(),
                executor.getMaximumPoolSize(), executor.getPoolSize(), executor.getTaskCount())).build();
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
