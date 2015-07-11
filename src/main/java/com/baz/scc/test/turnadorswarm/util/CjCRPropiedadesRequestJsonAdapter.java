package com.baz.scc.test.turnadorswarm.util;

import com.baz.scc.commons.web.client.util.CjCRJsonExtract;
import com.baz.scc.test.turnadorswarm.model.CjCRPropiedadesRequest;
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

public class CjCRPropiedadesRequestJsonAdapter implements JsonSerializer<CjCRPropiedadesRequest>, 
        JsonDeserializer<CjCRPropiedadesRequest>{
    
    @Override
    public JsonElement serialize(CjCRPropiedadesRequest propiedades, Type typeOfSrc,
            JsonSerializationContext context) {
        JsonObject json = new JsonObject();
        
        json.addProperty("NombreEstacion", propiedades.getNombreEstacion());
        json.addProperty("NumeroIp", propiedades.getNumeroIp());
        json.addProperty("TipoEstacion", propiedades.getTipoEstacion());
        
        return json;
    }
    
    @Override
    public CjCRPropiedadesRequest deserialize(JsonElement jsonElement, Type typeOfT, 
    JsonDeserializationContext context) throws JsonParseException {
        JsonObject json = (JsonObject) jsonElement;
        CjCRPropiedadesRequest propiedades = new CjCRPropiedadesRequest();
        
        propiedades.setNombreEstacion(CjCRJsonExtract.getString(json, "NombreEstacion"));
        propiedades.setNumeroIp(CjCRJsonExtract.getString(json, "NumeroIp"));
        propiedades.setTipoEstacion(CjCRJsonExtract.getInt(json, "TipoEstacion"));
        
        return propiedades;
    }
}
