package com.baz.scc.test.turnadorswarm.util;

import com.baz.scc.test.turnadorswarm.model.CjCRAutenticacionResponse;
import com.baz.scc.test.turnadorswarm.model.CjCRMensajeRespuesta;
import com.baz.scc.test.turnadorswarm.model.CjCRPropiedadesResponse;
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

public class CjCRAutenticacionResponseJsonAdapter implements JsonSerializer<CjCRAutenticacionResponse>,
        JsonDeserializer<CjCRAutenticacionResponse>{
    
    @Override
    public JsonElement serialize(CjCRAutenticacionResponse response, Type typeOfSrc,
            JsonSerializationContext context) {
        GsonBuilder gsonBuilder = new GsonBuilder();
        JsonObject json = new JsonObject();
        Gson gson;
        
        gsonBuilder.registerTypeAdapter(CjCRMensajeRespuesta.class, 
                new CjCRMensajeRespuestaJsonAdapter());
        gsonBuilder.registerTypeAdapter(CjCRPropiedadesResponse.class, 
                new CjCRPropiedadesResponseJsonAdapter());
        
        gson = gsonBuilder.create();
        
        if(response.getMensaje() != null) {
            json.add("CjCRMensajeRespuesta", gson.toJsonTree(response.getMensaje()));
        }
        
        if(response.getPropiedades() != null) {
            json.add("Propiedades", gson.toJsonTree(response.getPropiedades()));
        }
        
        return json;
    }
    
    @Override
    public CjCRAutenticacionResponse deserialize(JsonElement jsonElement, Type typeOfT, 
    JsonDeserializationContext context) throws JsonParseException {
        GsonBuilder gsonBuilder = new GsonBuilder();
        CjCRAutenticacionResponse response = new CjCRAutenticacionResponse();
        CjCRMensajeRespuesta mensaje;
        CjCRPropiedadesResponse propiedades;
        JsonObject json;                
        Gson gson;
        Type type;
        
        if(jsonElement.isJsonObject()) {
            gsonBuilder.registerTypeAdapter(CjCRMensajeRespuesta.class, 
                    new CjCRMensajeRespuestaJsonAdapter());
            gsonBuilder.registerTypeAdapter(CjCRPropiedadesResponse.class, 
                    new CjCRPropiedadesResponseJsonAdapter());
        
            gson = gsonBuilder.create();        
            json = jsonElement.getAsJsonObject();

            type = new TypeToken<CjCRMensajeRespuesta>(){}.getType();            
            mensaje = gson.fromJson(json.get("CjCRMensajeRespuesta"), type);
            
            type = new TypeToken<CjCRPropiedadesResponse>(){}.getType();
            propiedades = gson.fromJson(json.get("Propiedades"), type);
            
            response.setMensaje(mensaje);
            response.setPropiedades(propiedades);
        }
        
        return response;
    }
}
