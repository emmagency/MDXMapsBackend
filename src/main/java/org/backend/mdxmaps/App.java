package org.backend.mdxmaps;

import javax.ws.rs.ApplicationPath;
import javax.ws.rs.core.Application;
import java.util.HashMap;
import java.util.Map;

import static org.backend.mdxmaps.model.enums.Constants.SOLR_ROOMS_URL;

/**
 * Created by Emmanuel Keboh on 27/11/2016.
 */
@ApplicationPath("api")
public class App extends Application {
    @Override
    public Map<String, Object> getProperties() {
        return new HashMap<String, Object>() {
            {
                put(SOLR_ROOMS_URL.getValue(), "http://localhost:8983/solr/onCampus");
            }
        };
    }
}
