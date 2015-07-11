package com.baz.scc.test.turnadorswarm.util;

import com.baz.scc.commons.web.client.util.CjCRJsonExtract;
import com.baz.scc.test.turnadorswarm.model.CjCREmpleadoCredential;
import com.baz.scc.test.turnadorswarm.model.CjCRPropiedadesRequest;
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

public class CjCREmpleadoCredentialJsonAdapter implements JsonSerializer<CjCREmpleadoCredential>,
        JsonDeserializer<CjCREmpleadoCredential>{
    
    @Override
    public JsonElement serialize(CjCREmpleadoCredential credential, Type typeOfSrc,
            JsonSerializationContext context) {
        GsonBuilder gsonBuilder = new GsonBuilder();
        JsonObject json = new JsonObject();
        Gson gson;
        
        gsonBuilder.registerTypeAdapter(CjCRPropiedadesRequest.class, 
                new CjCRPropiedadesRequestJsonAdapter());
        
        gson = gsonBuilder.create();
        
        json.addProperty("NoEmpleado", credential.getNoEmpleado());
        json.addProperty("NumeroEmpleado", credential.getCipherNumero());
        json.addProperty("PasswordEmpleado", credential.getCipherPassword());
        json.addProperty("Sucursal", credential.getSucursal());
        
        if(credential.getPropiedades() != null) {
            json.add("Propiedades", gson.toJsonTree(credential.getPropiedades()));
        }
        
        return json;
    }
    
    @Override
    public CjCREmpleadoCredential deserialize(JsonElement jsonElement, Type typeOfT, 
    JsonDeserializationContext context) throws JsonParseException {
        Type type = new TypeToken<CjCRPropiedadesRequest>(){}.getType();
        GsonBuilder gsonBuilder = new GsonBuilder();
        CjCREmpleadoCredential credential = new CjCREmpleadoCredential();
        CjCRPropiedadesRequest propiedades;
        JsonObject json;                
        Gson gson;
        
        if(jsonElement.isJsonObject()) {
            gsonBuilder.registerTypeAdapter(CjCRPropiedadesRequest.class, 
                new CjCRPropiedadesRequestJsonAdapter());
        
            gson = gsonBuilder.create();        
            json = jsonElement.getAsJsonObject();

            propiedades = gson.fromJson(json.get("Propiedades"), type);
            
            credential.setNoEmpleado(CjCRJsonExtract.getString(json, "NoEmpleado"));
            credential.setNumeroEmpleado(CjCRJsonExtract.getString(json, "NumeroEmpleado"));
            credential.setPasswordEmpleado(CjCRJsonExtract.getString(json, "PasswordEmpleado"));
            credential.setSucursal(CjCRJsonExtract.getInt(json, "Sucursal"));
            
            credential.setPropiedades(propiedades);
        }
        
        return credential;
    }
}
