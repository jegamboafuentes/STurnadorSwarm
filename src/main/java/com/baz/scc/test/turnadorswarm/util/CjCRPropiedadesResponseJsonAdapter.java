package com.baz.scc.test.turnadorswarm.util;

import com.baz.scc.commons.web.client.util.CjCRJsonExtract;
import com.baz.scc.test.turnadorswarm.model.CjCRPropiedadesResponse;
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

public class CjCRPropiedadesResponseJsonAdapter implements JsonSerializer<CjCRPropiedadesResponse>, 
        JsonDeserializer<CjCRPropiedadesResponse>{
    
    @Override
    public JsonElement serialize(CjCRPropiedadesResponse propiedades, Type typeOfSrc,
            JsonSerializationContext context) {
        JsonObject json = new JsonObject();
        
        json.addProperty("AccesoConcedido", propiedades.isAccesoConcedido());
        json.addProperty("EstacionTrabajo", propiedades.getEstacionTrabajo());
        json.addProperty("Ip", propiedades.getIp());
        json.addProperty("MensajeAcceso", propiedades.getMensajeAcceso());
        json.addProperty("NumeroEmpleado", propiedades.getNumeroEmpleado());
        json.addProperty("PasswordEmpleado", propiedades.getPasswordEmpleado());
        json.addProperty("Sucursal", propiedades.getSucursal());
        json.addProperty("Token", propiedades.getToken());
        
        return json;
    }
    
    @Override
    public CjCRPropiedadesResponse deserialize(JsonElement jsonElement, Type typeOfT, 
    JsonDeserializationContext context) throws JsonParseException {
        JsonObject json = (JsonObject) jsonElement;
        CjCRPropiedadesResponse propiedades = new CjCRPropiedadesResponse();
        
        propiedades.setAccesoConcedido(CjCRJsonExtract.getBoolean(json, "AccesoConcedido"));
        propiedades.setEstacionTrabajo(CjCRJsonExtract.getString(json, "EstacionTrabajo"));
        propiedades.setIp(CjCRJsonExtract.getString(json, "Ip"));
        propiedades.setMensajeAcceso(CjCRJsonExtract.getString(json, "MensajeAcceso"));
        propiedades.setNumeroEmpleado(CjCRJsonExtract.getString(json, "NumeroEmpleado"));
        propiedades.setPasswordEmpleado(CjCRJsonExtract.getString(json, "PasswordEmpleado"));
        propiedades.setSucursal(CjCRJsonExtract.getInt(json, "Sucursal"));
        propiedades.setToken(CjCRJsonExtract.getString(json, "Token"));
        
        return propiedades;
    }
}
