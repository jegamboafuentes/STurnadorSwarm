package com.baz.scc.test.turnadorswarm.support;

import com.baz.scc.commons.web.client.support.CjCRJsonManager;
import com.baz.scc.test.turnadorswarm.model.CjCRUnidadNegocio;
import com.baz.scc.test.turnadorswarm.util.CjCRUnidadNegocioJsonAdapter;
import com.google.gson.reflect.TypeToken;
import java.util.List;
import org.springframework.stereotype.Component;


/**
 * <br><br>Copyright 2013 Banco Azteca. Todos los derechos reservados.
 * 
 * @author Giovanni Farf\u00E1n Brice\u00F1o
 */

@Component
public class CjCRUnidadNegocioJsonManager extends CjCRJsonManager<CjCRUnidadNegocio, 
        CjCRUnidadNegocioJsonAdapter> {

    @Override
    public Class<CjCRUnidadNegocio> getObjectClass() {
        return CjCRUnidadNegocio.class;
    }

    @Override
    public Class<CjCRUnidadNegocioJsonAdapter> getAdapterClass() {
        return CjCRUnidadNegocioJsonAdapter.class;
    }

    @Override
    public TypeToken<CjCRUnidadNegocio> getObjectType() {
        return new TypeToken<CjCRUnidadNegocio>(){};
    }

    @Override
    public TypeToken<List<CjCRUnidadNegocio>> getListType() {
        return new TypeToken<List<CjCRUnidadNegocio>>(){};
    }
}
