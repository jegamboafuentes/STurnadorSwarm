package com.baz.scc.test.turnadorswarm.util;

import com.baz.scc.commons.web.client.util.CjCRJsonExtract;
import com.baz.scc.test.turnadorswarm.model.CjCREmpleadoPool;
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

public class CjCREmpleadoPoolJsonAdapter implements JsonSerializer<CjCREmpleadoPool>,
        JsonDeserializer<CjCREmpleadoPool> {
    
    @Override
    public JsonElement serialize(CjCREmpleadoPool empleadoPool, Type typeOfSrc,
            JsonSerializationContext context) {
        JsonObject json = new JsonObject();
        
        json.addProperty("NoEmpleado", empleadoPool.getNoEmpleado());
        
        return json;
    }
    
    @Override
    public CjCREmpleadoPool deserialize(JsonElement jsonElement, Type typeOfT, 
    JsonDeserializationContext context) throws JsonParseException {
        JsonObject json = (JsonObject) jsonElement;
        CjCREmpleadoPool empleadoPool = new CjCREmpleadoPool();
        
        empleadoPool.setNoEmpleado(CjCRJsonExtract.getString(json, "NoEmpleado"));
        
        return empleadoPool;
    }
}
