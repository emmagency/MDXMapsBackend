package org.backend.mdxmaps.services.util;

import com.fasterxml.jackson.core.JsonGenerationException;
import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.SerializerProvider;
import com.fasterxml.jackson.databind.ser.std.StdSerializer;
import org.backend.mdxmaps.model.solr.model.Campus;

import java.io.IOException;

import static org.backend.mdxmaps.services.RoomSearchService.BUILDING;
import static org.backend.mdxmaps.services.RoomSearchService.DESCRIPTION;
import static org.backend.mdxmaps.services.RoomSearchService.DIRECTIONS_AVAILABLE;
import static org.backend.mdxmaps.services.RoomSearchService.GOOGLE_MAP_LEVEL;
import static org.backend.mdxmaps.services.RoomSearchService.LAT_LNG;
import static org.backend.mdxmaps.services.RoomSearchService.NAME;

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
    public void serialize(Campus campus, JsonGenerator jsonGenerator, SerializerProvider serializerProvider) throws IOException, JsonGenerationException {
        jsonGenerator.writeStartObject();
        jsonGenerator.writeStringField(NAME, campus.getName());
        jsonGenerator.writeStringField(BUILDING, campus.getBuilding());
        jsonGenerator.writeNumberField(GOOGLE_MAP_LEVEL, campus.getgMapLevel());
        jsonGenerator.writeStringField(DESCRIPTION, campus.getDescription());
        jsonGenerator.writeStringField(LAT_LNG, campus.getLatLng().toString());
        jsonGenerator.writeBooleanField(DIRECTIONS_AVAILABLE, campus.isDirectionsAvailable());
        jsonGenerator.writeEndObject();
    }
}