package org.backend.mdxmaps.services;

import org.apache.solr.client.solrj.SolrClient;
import org.apache.solr.client.solrj.SolrQuery;
import org.apache.solr.client.solrj.SolrServerException;
import org.apache.solr.client.solrj.impl.HttpSolrClient;
import org.apache.solr.client.solrj.response.QueryResponse;
import org.backend.mdxmaps.model.OperationFactory;
import org.backend.mdxmaps.model.Routing;
import org.backend.mdxmaps.model.enums.MOT;
import org.backend.mdxmaps.model.responseObjects.directions.MainDirectionsResponse;

import java.io.IOException;
import java.util.concurrent.Callable;

import static org.backend.mdxmaps.services.MOTValidator.autoResolve;
import static org.backend.mdxmaps.services.MOTValidator.isReceivedMOTValid;
import static org.backend.mdxmaps.services.MOTValidator.validate;
import static org.backend.mdxmaps.services.ResponseService.Status.ERROR;
import static org.backend.mdxmaps.services.ResponseService.Status.INFO;
import static org.backend.mdxmaps.services.ResponseService.Status.OK;
import static org.backend.mdxmaps.services.search.CampusSearchService.DIRECTIONS_AVAILABLE;
import static org.backend.mdxmaps.services.util.RoutingObjectsGetterUtilService.getRoomObjectFromName;

/**
 * Created by Emmanuel Keboh on 27/11/2016.
 */
public class DirectionsService implements Callable<Object> {

    private String start, destination, solrRoomsUrl, receivedMot;
    private boolean consultSolr;

    public DirectionsService(String start, String destination, String receivedMot, boolean consultSolr, String solrRoomsUrl) {
        this.start = start;
        this.destination = destination;
        this.receivedMot = receivedMot;
        this.consultSolr = consultSolr;
        this.solrRoomsUrl = solrRoomsUrl;
    }

    private String getStart() {
        return start;
    }


    private String getDestination() {
        return destination;
    }


    private String getReceivedMot() {
        return receivedMot;
    }

    @Override
    public Object call() throws Exception {

        if (consultSolr) {
            ResponseService solrStartCheckResponse = validateInput(start);
            if (solrStartCheckResponse.getStatus() == ERROR) {
                return solrStartCheckResponse;
            }

            ResponseService solrDestinationCheckResponse = validateInput(destination);
            if (solrDestinationCheckResponse.getStatus() == ERROR) {
                return solrDestinationCheckResponse;
            }
        }

        Routing start = getRoomObjectFromName(this.start);
        Routing destination = getRoomObjectFromName(this.destination);

        //Leave this here in case there's a data mismatch between solr data and room objects
        if (start == null || destination == null) {
            return ResponseService.create(ERROR, start == null && destination == null ?
                    "Couldn't find '" + this.start + "' & '" + this.destination + ".' Please check your input and try again." :
                    start == null ? "Couldn't find '" + this.start + "'. Please check your input and try again."
                            : "Couldn't find '" + this.destination + "'. Please check your input and try again.");
        }

        if (start.getName().equals(destination.getName())) {
            return ResponseService.create(ERROR, "Start and Destination appears to be the same. " +
                    "Apologies, I don't go around in circles.");
        }

        MOT motEnum;
        if (receivedMot == null || receivedMot.equals("null") || !isReceivedMOTValid(receivedMot)) {
            //User didn't provide preferred mot or passed mot is incorrect
            motEnum = autoResolve(start, destination);
        } else {
            //User provided a correct MOT, let's just confirm if it's needed for this operation
            motEnum = MOT.valueOf(receivedMot.toUpperCase());
            if (!motEnum.equals(MOT.DISABLED)) {
                if (!validate(start, destination)) {
                    motEnum = MOT.NULL;
                }
            }
        }

        //Logic to determine type of operation
        ResponseService oPType =
                new ResolveOperationTypeService(start, destination, motEnum).resolveOPType();

        if (oPType.getStatus() == ERROR) {
            return oPType;
        }

        //Everything checked out, let's do some calculations!
        ResponseService result = ((OperationFactory) oPType.getEntity()).getRoute();

        if (oPType.getStatus() == INFO && result.getEntity() != null) {
            ((MainDirectionsResponse) result.getEntity()).setStatus(INFO);
            ((MainDirectionsResponse) result.getEntity()).setMessage(oPType.getMessage());
        }

        return result.getEntity() != null ? result.getEntity() : result;
    }

    private ResponseService validateInput(String input) {

        SolrClient solrClient = new HttpSolrClient.Builder(solrRoomsUrl).build();
        SolrQuery solrQuery = new SolrQuery(input);
        solrQuery.setRequestHandler("/query");

        QueryResponse response;

        try {
            response = solrClient.query(solrQuery);
        } catch (IOException | SolrServerException e) {
            e.printStackTrace();
            return ResponseService.create(ERROR, "Apologies, we've run into some problems. Please try again at a later time");
        }

        if (response.getResults().size() == 0) {
            return ResponseService.create(ERROR, String.format("Couldn't find '%s'. Please check your input and try again", input));
        } else if (response.getResults().size() > 1) {
            return ResponseService.create(ERROR, String.format("There are multiple matches for '%s'. Please refine your input to be more specific", input));
        }

        boolean isDirectionsAvailable = (Boolean) response.getResults().get(0).getFieldValue(DIRECTIONS_AVAILABLE);

        if (!isDirectionsAvailable) {
            return ResponseService.create(ERROR, String.format("Sorry, directions are not available for '%s'", input));
        }

        return ResponseService.create(OK, null);

    }

}
