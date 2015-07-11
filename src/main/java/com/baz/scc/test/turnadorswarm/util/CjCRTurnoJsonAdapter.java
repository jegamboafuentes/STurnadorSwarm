package com.baz.scc.test.turnadorswarm.util;

import com.baz.scc.commons.web.client.util.CjCRJsonExtract;
import com.baz.scc.test.turnadorswarm.model.CjCREmpleadoPool;
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

public class CjCRTurnoJsonAdapter implements JsonSerializer<CjCRTurno>, JsonDeserializer<CjCRTurno> {
    
    @Override
    public JsonElement serialize(CjCRTurno turno, Type typeOfSrc,
            JsonSerializationContext context) {
        GsonBuilder gsonBuilder = new GsonBuilder();
        JsonObject json = new JsonObject();
        Gson gson;
        
        gsonBuilder.registerTypeAdapter(CjCREmpleadoPool.class, 
                new CjCREmpleadoPoolJsonAdapter());
        
        gson = gsonBuilder.create();
        
        json.addProperty("Fecha", turno.getFecha());
        json.addProperty("IdTurno", turno.getIdTurno());
        json.addProperty("Estado", turno.getEstado());
        json.addProperty("IdOrigen", turno.getIdOrigen());
        json.addProperty("IdUnidadNegocio", turno.getIdUnidadNegocio());
        json.addProperty("TurnoSeguimiento", turno.getTurnoSeguimiento());
        json.addProperty("Prioridad", turno.getPrioridad());
        
        if(turno.getEmpleado() != null) {
            json.add("Empleado", gson.toJsonTree(turno.getEmpleado()));
        }
        
        return json;
    }

    @Override
    public CjCRTurno deserialize(JsonElement jsonElement, Type typeOfT, 
            JsonDeserializationContext context) throws JsonParseException {
        Type type = new TypeToken<CjCREmpleadoPool>(){}.getType();
        GsonBuilder gsonBuilder = new GsonBuilder();
        CjCRTurno turno = new CjCRTurno();
        CjCREmpleadoPool empleado;
        JsonObject json;                
        Gson gson;
        
        if(jsonElement.isJsonObject()) {
            gsonBuilder.registerTypeAdapter(CjCREmpleadoPool.class, 
                new CjCREmpleadoPoolJsonAdapter());
        
            gson = gsonBuilder.create();        
            json = jsonElement.getAsJsonObject();

            empleado = gson.fromJson(json.get("Empleado"), type);
            
            turno.setFecha(CjCRJsonExtract.getInt(json, "Fecha"));
            turno.setIdTurno(CjCRJsonExtract.getInt(json, "IdTurno"));
            turno.setEstado(CjCRJsonExtract.getInt(json, "Estado"));
            turno.setIdOrigen(CjCRJsonExtract.getInt(json, "IdOrigen"));
            turno.setIdUnidadNegocio(CjCRJsonExtract.getInt(json, "IdUnidadNegocio"));
            turno.setTurnoSeguimiento(CjCRJsonExtract.getInt(json, "TurnoSeguimiento"));
            turno.setPrioridad(CjCRJsonExtract.getInt(json, "Prioridad"));
            
            turno.setEmpleado(empleado);
        }
        
        return turno;
    }
}
