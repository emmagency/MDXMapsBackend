package org.backend.mdxmaps.services.filters;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

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

    private static final Logger LOGGER = LoggerFactory.getLogger(CampusDirectionsEndpointFilter.class);

    @Context
    private HttpServletRequest httpServletRequest;

    @Override
    public void filter(ContainerRequestContext containerRequestContext) throws IOException {
        LOGGER.info("{} Request to {}", httpServletRequest.getMethod(),
                httpServletRequest.getRequestURL().append("/").append(httpServletRequest.getQueryString()));
    }

}
