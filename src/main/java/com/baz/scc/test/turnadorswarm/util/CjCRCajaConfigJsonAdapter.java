package com.baz.scc.test.turnadorswarm.util;

import com.baz.scc.commons.model.CjCRCajaConfig;
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

public class CjCRCajaConfigJsonAdapter implements JsonSerializer<CjCRCajaConfig>,
        JsonDeserializer<CjCRCajaConfig>{
    
    @Override
    public JsonElement serialize(CjCRCajaConfig cajaConfig, Type typeOfSrc,
            JsonSerializationContext context) {
        JsonObject json = new JsonObject();
        
        json.addProperty("Modulo", cajaConfig.getModulo());
        json.addProperty("Folio", cajaConfig.getFolio());
        json.addProperty("ModuloDesc", cajaConfig.getModuloDesc());
        json.addProperty("FolioDesc", cajaConfig.getFolioDesc());
        json.addProperty("Valor", cajaConfig.getValor());
        json.addProperty("Status", cajaConfig.getStatus());
        
        return json;
    }
    
    @Override
    public CjCRCajaConfig deserialize(JsonElement jsonElement, Type typeOfT, 
    JsonDeserializationContext context) throws JsonParseException {
        JsonObject json = (JsonObject) jsonElement;
        CjCRCajaConfig cajaConfig = new CjCRCajaConfig();
        
        cajaConfig.setModulo(CjCRJsonExtract.getInt(json, "Modulo"));
        cajaConfig.setFolio(CjCRJsonExtract.getInt(json, "Folio"));
        cajaConfig.setModuloDesc(CjCRJsonExtract.getString(json, "ModuloDes"));
        cajaConfig.setFolioDesc(CjCRJsonExtract.getString(json, "FolioDes"));
        cajaConfig.setValor(CjCRJsonExtract.getString(json, "Valor"));
        cajaConfig.setStatus(CjCRJsonExtract.getInt(json, "Status"));
        
        return cajaConfig;
    }
}
