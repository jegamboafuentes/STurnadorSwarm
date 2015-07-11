package com.baz.scc.test.turnadorswarm.support;

import com.baz.scc.commons.model.CjCRCajaConfig;
import com.baz.scc.commons.web.client.support.CjCRJsonManager;
import com.baz.scc.test.turnadorswarm.util.CjCRCajaConfigJsonAdapter;
import com.google.gson.reflect.TypeToken;
import java.util.List;
import org.springframework.stereotype.Component;


/**
 * <br><br>Copyright 2013 Banco Azteca. Todos los derechos reservados.
 * 
 * @author Giovanni Farf\u00E1n Brice\u00F1o
 */

@Component
public class CjCRCajaConfigJsonManager extends CjCRJsonManager<CjCRCajaConfig, CjCRCajaConfigJsonAdapter> {

    @Override
    public Class<CjCRCajaConfig> getObjectClass() {
        return CjCRCajaConfig.class;
    }

    @Override
    public Class<CjCRCajaConfigJsonAdapter> getAdapterClass() {
        return CjCRCajaConfigJsonAdapter.class;
    }

    @Override
    public TypeToken<CjCRCajaConfig> getObjectType() {
        return new TypeToken<CjCRCajaConfig>(){};
    }

    @Override
    public TypeToken<List<CjCRCajaConfig>> getListType() {
        return new TypeToken<List<CjCRCajaConfig>>(){};
    }
}
