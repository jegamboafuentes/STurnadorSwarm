package com.baz.scc.test.turnadorswarm.util;

import com.baz.scc.commons.web.client.util.CjCRJsonExtract;
import com.baz.scc.test.turnadorswarm.model.CjCRSummaryTurno;
import com.baz.scc.test.turnadorswarm.model.CjCRTurno;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonDeserializationContext;
import com.google.gson.JsonDeserializer;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParseException;
import com.google.gson.JsonSerializationContext;
import com.google.gson.JsonSerializer;
import com.google.gson.reflect.TypeToken;
import java.lang.reflect.Type;


/**
 * <br><br>Copyright 2013 Banco Azteca. Todos los derechos reservados.
 * 
 * @author Giovanni Farf\u00E1n Brice\u00F1o
 */

public class CjCRSummaryTurnoJsonAdapter implements JsonSerializer<CjCRSummaryTurno>,
        JsonDeserializer<CjCRSummaryTurno>{
    
    @Override
    public JsonElement serialize(CjCRSummaryTurno summaryTurno, Type typeOfSrc,
            JsonSerializationContext context) {
        GsonBuilder gsonBuilder = new GsonBuilder();
        JsonObject json = new JsonObject();
        Gson gson;
        
        gsonBuilder.registerTypeAdapter(CjCRTurno.class, 
                new CjCRTurnoJsonAdapter());
        
        gson = gsonBuilder.create();
        
        json.addProperty("Detail", summaryTurno.getDetail());
        json.addProperty("Status", summaryTurno.getStatus());
        json.addProperty("Complete", summaryTurno.isComplete());
        
        if(summaryTurno.getTurno() != null) {
            json.add("Turno", gson.toJsonTree(summaryTurno.getTurno()));
        }
        
        return json;
    }
    
    @Override
    public CjCRSummaryTurno deserialize(JsonElement jsonElement, Type typeOfT, 
            JsonDeserializationContext context) throws JsonParseException {
        Type type = new TypeToken<CjCRTurno>(){}.getType();
        GsonBuilder gsonBuilder = new GsonBuilder();
        CjCRSummaryTurno summaryTurno = new CjCRSummaryTurno();
        CjCRTurno turno;
        JsonObject json;                
        Gson gson;
        
        if(jsonElement.isJsonObject()) {
            gsonBuilder.registerTypeAdapter(CjCRTurno.class, 
                new CjCRTurnoJsonAdapter());
        
            gson = gsonBuilder.create();        
            json = jsonElement.getAsJsonObject();
            
            turno = gson.fromJson(json.get("Turno"), type);
            
            summaryTurno.setDetail(CjCRJsonExtract.getString(json, "Detail"));
            summaryTurno.setStatus(CjCRJsonExtract.getInt(json, "Status"));
            summaryTurno.setComplete(CjCRJsonExtract.getBoolean(json, "Complete"));
            
            summaryTurno.setTurno(turno);
        }
        
        return summaryTurno;
    }
}
