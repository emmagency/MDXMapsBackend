package org.backend.mdxmaps.Services.Util;

import com.fasterxml.jackson.core.JsonGenerationException;
import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.SerializerProvider;
import com.fasterxml.jackson.databind.ser.std.StdSerializer;
import org.backend.mdxmaps.Model.Solr.SolrRooms;

import java.io.IOException;

import static org.backend.mdxmaps.Services.RoomSearchService.BUILDING;
import static org.backend.mdxmaps.Services.RoomSearchService.DESCRIPTION;
import static org.backend.mdxmaps.Services.RoomSearchService.DIRECTIONS_AVAILABLE;
import static org.backend.mdxmaps.Services.RoomSearchService.GOOGLE_MAP_LEVEL;
import static org.backend.mdxmaps.Services.RoomSearchService.LAT_LNG;
import static org.backend.mdxmaps.Services.RoomSearchService.NAME;

/**
 * Created by Emmanuel Keboh on 05/03/2017.
 */
public class CustomRoomJSONSerializerForSolrIndexing extends StdSerializer<SolrRooms> {

    public CustomRoomJSONSerializerForSolrIndexing(Class<SolrRooms> t) {
        super(t);
    }

    @Override
    public void serialize(SolrRooms solrRoom, JsonGenerator jsonGenerator, SerializerProvider serializerProvider) throws IOException, JsonGenerationException {
        jsonGenerator.writeStartObject();
        jsonGenerator.writeStringField(NAME, solrRoom.getRoomNumber());
        jsonGenerator.writeStringField(BUILDING, solrRoom.getBuildingName());
        jsonGenerator.writeNumberField(GOOGLE_MAP_LEVEL, solrRoom.getLevel());
        jsonGenerator.writeStringField(DESCRIPTION, solrRoom.getDescription());
        jsonGenerator.writeStringField(LAT_LNG, solrRoom.getLatLng().toString());
        jsonGenerator.writeBooleanField(DIRECTIONS_AVAILABLE, solrRoom.isDirectionsAvailable());
        jsonGenerator.writeEndObject();
    }
}