package com.baz.scc.test.turnadorswarm.util;

import com.baz.scc.commons.web.client.util.CjCRJsonExtract;
import com.baz.scc.test.turnadorswarm.model.CjCRMensajeRespuesta;
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

public class CjCRMensajeRespuestaJsonAdapter implements JsonSerializer<CjCRMensajeRespuesta>, 
        JsonDeserializer<CjCRMensajeRespuesta>{
    
    @Override
    public JsonElement serialize(CjCRMensajeRespuesta mensaje, Type typeOfSrc,
            JsonSerializationContext context) {
        JsonObject json = new JsonObject();
        
        json.addProperty("DescripcionError", mensaje.getDescripcionError());
        json.addProperty("NumeroError", mensaje.getNumeroError());
        
        return json;
    }
    
    @Override
    public CjCRMensajeRespuesta deserialize(JsonElement jsonElement, Type typeOfT, 
            JsonDeserializationContext context) throws JsonParseException {
        JsonObject json = (JsonObject) jsonElement;
        CjCRMensajeRespuesta mensaje = new CjCRMensajeRespuesta();
        
        mensaje.setDescripcionError(CjCRJsonExtract.getString(json, "DescripcionError"));
        mensaje.setNumeroError(CjCRJsonExtract.getInt(json, "NumeroError"));
        
        return mensaje;
    }
}
