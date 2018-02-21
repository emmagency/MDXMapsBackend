import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.solr.client.solrj.SolrClient;
import org.apache.solr.client.solrj.SolrQuery;
import org.apache.solr.client.solrj.SolrServerException;
import org.apache.solr.client.solrj.impl.HttpSolrClient;
import org.apache.solr.client.solrj.response.QueryResponse;
import org.apache.solr.common.SolrDocumentList;
import org.backend.mdxmaps.model.solr.Campus;
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

//    @Test
//    public void generateOutsideJSON() {
//        List<BlahClass> blah = BlahClass.getAllGroceries();
//        try {
//            new ObjectMapper().writerWithDefaultPrettyPrinter().writeValue(new File("C:\\Users\\keboh\\Documents\\WorkArea\\blah.json"), blah);
//        } catch (IOException e) {
//            e.printStackTrace();
//        }
//
//    }

//    @Test
//    public void fishingExercise() {
//        List<Campus> campus = Campus.getRoomsForSolr();
//
//        List<Routing> routing = Routing.getRooms();
//
//        ArrayList<Campus> list = new ArrayList<>();
//
//        for (int i=0; i<campus.size(); i++) {
//            boolean found = false;
//            if (campus.get(i).isDirectionsAvailable()) {
//                for (int j=0; j<routing.size(); j++) {
//                    if (routing.get(j).getName().equals(campus.get(i).getName())) {
//                        found = true;
//                        break;
//                    }
//                }
//            }
//            if (!found && campus.get(i).isDirectionsAvailable()) {
//                list.add(campus.get(i));
//            }
//        }
//
//        list.forEach(item -> System.out.println(item.getName()));
//    }

    //Requires a local running instance of Solr. Replace solrURL appropriately
    @Test
    public void testSolr() {
        String solrURL = "http://localhost:8983/solr/onCampus";

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
