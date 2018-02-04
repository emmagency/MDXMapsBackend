package org.backend.mdxmaps.resource;

import org.backend.mdxmaps.model.ServerStatus;

import javax.inject.Singleton;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.ThreadPoolExecutor;

/**
 * Created by Emmanuel keboh on 13/07/2017.
 */
@Path("status")
@Singleton
public class ServerStatusResource {

    private ExecutorService service = Executors.newCachedThreadPool();

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Response status() {
        ThreadPoolExecutor executor = (ThreadPoolExecutor) service;
        return Response.ok(new ServerStatus(executor.getActiveCount(), executor.getCompletedTaskCount(), executor.getCorePoolSize(),
                executor.getMaximumPoolSize(), executor.getPoolSize(), executor.getTaskCount())).build();
    }

}
