package org.backend.mdxmaps.service.util;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.SerializerProvider;
import com.fasterxml.jackson.databind.ser.std.StdSerializer;
import org.backend.mdxmaps.model.solr.Campus;

import java.io.IOException;

import static org.backend.mdxmaps.service.search.CampusSearchService.BUILDING;
import static org.backend.mdxmaps.service.search.CampusSearchService.DESCRIPTION;
import static org.backend.mdxmaps.service.search.CampusSearchService.DIRECTIONS_AVAILABLE;
import static org.backend.mdxmaps.service.search.CampusSearchService.GOOGLE_MAP_LEVEL;
import static org.backend.mdxmaps.service.search.CampusSearchService.LAT_LNG;
import static org.backend.mdxmaps.service.search.CampusSearchService.LEVEL;
import static org.backend.mdxmaps.service.search.CampusSearchService.NAME;

/**
 * Created by Emmanuel Keboh on 05/03/2017.
 */
public class CustomRoomJSONSerializerForSolrIndexing extends StdSerializer<Campus> {

    public CustomRoomJSONSerializerForSolrIndexing() {
        this(null);
    }

    public CustomRoomJSONSerializerForSolrIndexing(Class<Campus> t) {
        super(t);
    }

    @Override
    public void serialize(Campus campus, JsonGenerator jsonGenerator, SerializerProvider serializerProvider) throws IOException {
        jsonGenerator.writeStartObject();
        jsonGenerator.writeStringField(NAME, campus.getName());
        if (campus.getBuilding() != null) {
            jsonGenerator.writeStringField(BUILDING, campus.getBuilding().getValue());
        }
        jsonGenerator.writeStringField(LEVEL, campus.getLevel());
        jsonGenerator.writeNumberField(GOOGLE_MAP_LEVEL, campus.getgMapLevel());
        jsonGenerator.writeStringField(DESCRIPTION, campus.getDescription());
        jsonGenerator.writeStringField(LAT_LNG, campus.getLatLng().toString());
        jsonGenerator.writeBooleanField(DIRECTIONS_AVAILABLE, campus.isDirectionsAvailable());
        jsonGenerator.writeEndObject();
    }
}