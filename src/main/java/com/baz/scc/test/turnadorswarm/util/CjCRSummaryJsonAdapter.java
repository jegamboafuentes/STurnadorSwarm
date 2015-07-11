package com.baz.scc.test.turnadorswarm.util;

import com.baz.scc.commons.model.CjCRSummary;
import com.baz.scc.commons.web.client.util.CjCRJsonExtract;
import com.google.gson.JsonDeserializationContext;
import com.google.gson.JsonDeserializer;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParseException;
import com.google.gson.JsonSerializationContext;
import com.google.gson.JsonSerializer;
import java.lang.reflect.Type;


/**
 * <br><br>Copyright 2013 Banco Azteca. Todos los derechos reservados.
 * 
 * @author Giovanni Farf\u00E1n Brice\u00F1o
 */

public class CjCRSummaryJsonAdapter implements JsonSerializer<CjCRSummary>,
        JsonDeserializer<CjCRSummary>{
    
    @Override
    public JsonElement serialize(CjCRSummary summary, Type typeOfSrc,
            JsonSerializationContext context) {
        JsonObject json = new JsonObject();
        
        json.addProperty("Detail", summary.getDetail());
        json.addProperty("Status", summary.getStatus());
        json.addProperty("Complete", summary.isComplete());
        
        return json;
    }
    
    @Override
    public CjCRSummary deserialize(JsonElement jsonElement, Type typeOfT, 
    JsonDeserializationContext context) throws JsonParseException {
        JsonObject json = (JsonObject) jsonElement;
        CjCRSummary summary = new CjCRSummary();
        
        summary.setDetail(CjCRJsonExtract.getString(json, "Detail"));
        summary.setStatus(CjCRJsonExtract.getInt(json, "Status"));
        summary.setComplete(CjCRJsonExtract.getBoolean(json, "Complete"));
        
        return summary;
    }
}
