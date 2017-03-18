import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.module.SimpleModule;
import org.apache.solr.client.solrj.SolrClient;
import org.apache.solr.client.solrj.SolrQuery;
import org.apache.solr.client.solrj.SolrServerException;
import org.apache.solr.client.solrj.impl.HttpSolrClient;
import org.apache.solr.client.solrj.response.QueryResponse;
import org.apache.solr.common.SolrDocumentList;
import org.backend.mdxmaps.Model.Solr.SolrRooms;
import org.backend.mdxmaps.Services.Util.CustomRoomJSONSerializerForSolrIndexing;
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
        ObjectMapper mapper = new ObjectMapper();

        SimpleModule simpleModule = new SimpleModule("Room Objects");
        simpleModule.addSerializer(new CustomRoomJSONSerializerForSolrIndexing(SolrRooms.class));

        mapper.registerModule(simpleModule);

        List<SolrRooms> obj = SolrRooms.getRoomsForSolr();

        //To generate JSON data, change the location specified below to your preferred local path and run test
        try {
            mapper.writerWithDefaultPrettyPrinter().writeValue(new File("C:\\Users\\Q\\Documents\\WorkArea\\roomsData.json"), obj);
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
        solrQuery.setQuery("CG AND directionsAvailable:true");
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
