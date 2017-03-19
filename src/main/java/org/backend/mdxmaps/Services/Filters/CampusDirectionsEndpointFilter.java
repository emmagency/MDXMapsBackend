package org.backend.mdxmaps.Services.Filters;

import javax.servlet.http.HttpServletRequest;
import javax.ws.rs.container.ContainerRequestContext;
import javax.ws.rs.container.ContainerRequestFilter;
import javax.ws.rs.core.Context;
import javax.ws.rs.ext.Provider;
import java.io.IOException;

/**
 * Created by Emmanuel Keboh on 18/03/2017.
 */
@Provider
@Binders.CampusDirection
public class CampusDirectionsEndpointFilter implements ContainerRequestFilter {

    @Context
    private HttpServletRequest httpServletRequest;

    @Override
    public void filter(ContainerRequestContext containerRequestContext) throws IOException {

        System.out.println("Filter Running!");

    }

}
