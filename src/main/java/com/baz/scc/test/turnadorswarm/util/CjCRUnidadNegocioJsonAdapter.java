package com.baz.scc.test.turnadorswarm.util;

import com.baz.scc.commons.web.client.util.CjCRJsonExtract;
import com.baz.scc.test.turnadorswarm.model.CjCRUnidadNegocio;
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

public class CjCRUnidadNegocioJsonAdapter implements JsonSerializer<CjCRUnidadNegocio>,
        JsonDeserializer<CjCRUnidadNegocio>{
    
    @Override
    public JsonElement serialize(CjCRUnidadNegocio unidadNegocio, Type typeOfSrc,
            JsonSerializationContext context) {
        JsonObject json = new JsonObject();
        
        json.addProperty("IdUnidadNegocio", unidadNegocio.getIdUnidadNegocio());
        json.addProperty("Descripcion", unidadNegocio.getDescripcion());
        json.addProperty("Prestamos", unidadNegocio.isPrestamos());
        json.addProperty("UrlImagen", unidadNegocio.getUrlImagen());
        
        return json;
    }
    
    @Override
    public CjCRUnidadNegocio deserialize(JsonElement jsonElement, Type typeOfT, 
            JsonDeserializationContext context) throws JsonParseException {
        CjCRUnidadNegocio unidadNegocio = new CjCRUnidadNegocio();
        JsonObject json;
        
        if(jsonElement.isJsonObject()) {
            json = jsonElement.getAsJsonObject();

            unidadNegocio.setIdUnidadNegocio(CjCRJsonExtract.getInt(json, "IdUnidadNegocio"));
            unidadNegocio.setDescripcion(CjCRJsonExtract.getString(json, "Descripcion"));
            unidadNegocio.setPrestamos(CjCRJsonExtract.getBoolean(json, "Prestamos"));
            unidadNegocio.setDescripcion(CjCRJsonExtract.getString(json, "UrlImagen"));
        }
        
        return unidadNegocio;
    }
}
