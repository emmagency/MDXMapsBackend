import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.solr.client.solrj.SolrClient;
import org.apache.solr.client.solrj.SolrQuery;
import org.apache.solr.client.solrj.SolrServerException;
import org.apache.solr.client.solrj.impl.HttpSolrClient;
import org.apache.solr.client.solrj.response.QueryResponse;
import org.apache.solr.common.SolrDocumentList;
import org.backend.mdxmaps.model.solr.model.Campus;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.runners.MockitoJUnitRunner;

import java.io.File;
import java.io.IOException;
import java.util.List;

import static junit.framework.TestCase.assertTrue;

/**
 * Created by Emmanuel Keboh on 04/03/2017.
 */

@RunWith(MockitoJUnitRunner.class)
public class GenerateJSON {

    @Test
    public void generateRoomsJSONData() {
        List<Campus> obj = Campus.getRoomsForSolr();
        //To generate JSON data, change the location specified below to your preferred local path and run test
        try {
            new ObjectMapper().writerWithDefaultPrettyPrinter().writeValue(new File("C:\\Users\\keboh\\Documents\\WorkArea\\rooms.json"), obj);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    //Requires a local running instance of Solr. Replace solrURL appropriately
    @Test
    public void testSolr() {
        String solrURL = "http://localhost:8983/solr/searchRooms";

        SolrClient solrClient = new HttpSolrClient.Builder(solrURL).build();

        SolrQuery solrQuery = new SolrQuery();
        solrQuery.setQuery("CG AND isDirectionsAvailable:true");
        solrQuery.set("wt", "json");
        solrQuery.set("rows", 20);
        solrQuery.setRequestHandler("/query");

        SolrDocumentList list = new SolrDocumentList();
        try {
            QueryResponse response = solrClient.query(solrQuery);
            list = response.getResults();
        } catch (IOException | SolrServerException e) {
            e.printStackTrace();
        }

        assertTrue(!list.isEmpty());
    }
}
