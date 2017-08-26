package org.backend.mdxmaps.services.util;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.SerializerProvider;
import com.fasterxml.jackson.databind.ser.std.StdSerializer;
import org.backend.mdxmaps.model.solr.model.Campus;

import java.io.IOException;

import static org.backend.mdxmaps.services.search.RoomSearchService.BUILDING;
import static org.backend.mdxmaps.services.search.RoomSearchService.DESCRIPTION;
import static org.backend.mdxmaps.services.search.RoomSearchService.DIRECTIONS_AVAILABLE;
import static org.backend.mdxmaps.services.search.RoomSearchService.GOOGLE_MAP_LEVEL;
import static org.backend.mdxmaps.services.search.RoomSearchService.LAT_LNG;
import static org.backend.mdxmaps.services.search.RoomSearchService.LEVEL;
import static org.backend.mdxmaps.services.search.RoomSearchService.NAME;

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
            jsonGenerator.writeStringField(BUILDING, campus.getBuilding());
        }
        jsonGenerator.writeStringField(LEVEL, campus.getLevel());
        jsonGenerator.writeNumberField(GOOGLE_MAP_LEVEL, campus.getgMapLevel());
        jsonGenerator.writeStringField(DESCRIPTION, campus.getDescription());
        jsonGenerator.writeStringField(LAT_LNG, campus.getLatLng().toString());
        jsonGenerator.writeBooleanField(DIRECTIONS_AVAILABLE, campus.isDirectionsAvailable());
        jsonGenerator.writeEndObject();
    }
}