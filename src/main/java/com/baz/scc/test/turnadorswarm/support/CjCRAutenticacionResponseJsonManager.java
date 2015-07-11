package com.baz.scc.test.turnadorswarm.support;

import com.baz.scc.commons.web.client.support.CjCRJsonManager;
import com.baz.scc.test.turnadorswarm.model.CjCRAutenticacionResponse;
import com.baz.scc.test.turnadorswarm.util.CjCRAutenticacionResponseJsonAdapter;
import com.google.gson.reflect.TypeToken;
import java.util.List;
import org.springframework.stereotype.Component;


/**
 * <br><br>Copyright 2013 Banco Azteca. Todos los derechos reservados.
 * 
 * @author Giovanni Farf\u00E1n Brice\u00F1o
 */

@Component
public class CjCRAutenticacionResponseJsonManager extends 
        CjCRJsonManager<CjCRAutenticacionResponse, CjCRAutenticacionResponseJsonAdapter> {

    @Override
    public Class<CjCRAutenticacionResponse> getObjectClass() {
        return CjCRAutenticacionResponse.class;
    }

    @Override
    public Class<CjCRAutenticacionResponseJsonAdapter> getAdapterClass() {
        return CjCRAutenticacionResponseJsonAdapter.class;
    }

    @Override
    public TypeToken<CjCRAutenticacionResponse> getObjectType() {
        return new TypeToken<CjCRAutenticacionResponse>(){};
    }

    @Override
    public TypeToken<List<CjCRAutenticacionResponse>> getListType() {
        return new TypeToken<List<CjCRAutenticacionResponse>>(){};
    }
}
