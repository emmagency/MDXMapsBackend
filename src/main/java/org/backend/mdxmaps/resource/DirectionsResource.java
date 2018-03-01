package org.backend.mdxmaps.resource;

import com.google.common.collect.Multimap;
import org.backend.mdxmaps.model.LatLng;
import org.backend.mdxmaps.model.Routing;
import org.backend.mdxmaps.model.Vertex;
import org.backend.mdxmaps.model.enums.MOT;
import org.backend.mdxmaps.model.responseObjects.directions.TestDirections;
import org.backend.mdxmaps.service.DirectionsService;
import org.backend.mdxmaps.service.ResponseService;
import org.backend.mdxmaps.service.algorithms.AStarAlgorithm;
import org.backend.mdxmaps.service.algorithms.KPathAlgorithm;
import org.backend.mdxmaps.service.algorithms.OutdoorAlgorithm;
import org.backend.mdxmaps.service.filters.Binders;
import org.backend.mdxmaps.service.util.UtilService;

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
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

import static java.util.stream.Collectors.toList;
import static org.backend.mdxmaps.model.enums.Constants.SOLR_ROOMS_URL;
import static org.backend.mdxmaps.service.ResponseService.Status.ERROR;
import static org.backend.mdxmaps.service.util.UtilService.calculateMultipleRoutesDistanceAndSort;
import static org.backend.mdxmaps.service.util.UtilService.transformValidRoutesStringToObjects;
import static org.backend.mdxmaps.service.util.UtilService.validRouteObjectsToLatLngTransformer;

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
                return Response.ok(ResponseService.create(ERROR,
                        "Error with url parameters!")).build();
            }
        }

        return Response.ok(ResponseService.create(ERROR,
                "Error with url parameters!")).build();
    }

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    @Path("/legacy")
    public List<TestDirections> legacyDirections(@QueryParam("s") String start, @QueryParam("d") String destination,
                                                 @QueryParam("m") String mode, @QueryParam("n") int numberOfRoutes) {
        if (start != null && destination != null) {
            ArrayList<Routing> outsideConnectors = Routing.getOutsideConnectors();
            ArrayList<ArrayList<String>> result = new OutdoorAlgorithm().sameLevelOp(start, destination, outsideConnectors,
                    mode.equalsIgnoreCase(MOT.DISABLED.toString()));
            ArrayList<ArrayList<Routing>> resultObjects = transformValidRoutesStringToObjects(result, outsideConnectors);

            ArrayList<ArrayList<LatLng>> outsideRoutesLatLng = (ArrayList<ArrayList<LatLng>>) resultObjects.parallelStream()
                    .map(validRouteObjectsToLatLngTransformer())
                    .collect(toList());

            Multimap<Double, ArrayList<LatLng>> sortedRoutes = calculateMultipleRoutesDistanceAndSort(outsideRoutesLatLng);

            ArrayList<TestDirections> response = new ArrayList<>();

            sortedRoutes.keySet().forEach(distance -> sortedRoutes.get(distance).
                    forEach(latlng -> response.add(new TestDirections(distance, latlng))));

            if (numberOfRoutes < response.size()) {
                return response.subList(0, numberOfRoutes);
            } else {
                return response;
            }
        }

        return new ArrayList<>();
    }

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    @Path("/aStar")
    public List<TestDirections> aStarDirections(@QueryParam("s") String start, @QueryParam("d") String destination,
                                                @QueryParam("m") String mode, @QueryParam("n") int numberOfRoutes) {
        if (start != null && destination != null) {

            boolean disabled = mode.equalsIgnoreCase(MOT.DISABLED.toString());
            LinkedList<Vertex> shortestPath = AStarAlgorithm.calculateOutsideRoute(start, destination,
                    disabled, Vertex.getOutsideVertices());

            ArrayList<ArrayList<Vertex>> allPaths = KPathAlgorithm.calculateKSPs(new ArrayList<>(shortestPath), numberOfRoutes > 0 ? numberOfRoutes - 1 : 3,
                    destination, disabled);

            return allPaths.stream()
                    .map(path ->
                            new TestDirections(IntStream.range(0, path.size() - 1)
                                    .mapToDouble(i -> UtilService.calculateDistance(path.get(i), path.get(i + 1)))
                                    .sum(),
                                    (ArrayList<LatLng>) path.stream()
                                            .map(Vertex::getLatLng)
                                            .collect(Collectors.toList())))
                    .collect(toList());

//            ArrayList<LatLng> routeLatLng = (ArrayList<LatLng>) shortestPath.stream()
//                    .map(Vertex::getLatLng)
//                    .collect(Collectors.toList());
//            TestDirections result = new TestDirections(UtilService.calculateRouteDistance(routeLatLng), routeLatLng);
//            return new ArrayList<>(Collections.singletonList(result));
        }

        return new ArrayList<>();
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
