package com.baz.scc.test.turnadorswarm.support;

import com.baz.scc.commons.model.CjCRSummary;
import com.baz.scc.commons.web.client.support.CjCRJsonManager;
import com.baz.scc.test.turnadorswarm.util.CjCRSummaryJsonAdapter;
import com.google.gson.reflect.TypeToken;
import java.util.List;
import org.springframework.stereotype.Component;


/**
 * <br><br>Copyright 2013 Banco Azteca. Todos los derechos reservados.
 * 
 * @author Giovanni Farf\u00E1n Brice\u00F1o
 */

@Component
public class CjCRSummaryJsonManager extends CjCRJsonManager<CjCRSummary, CjCRSummaryJsonAdapter> {

    @Override
    public Class<CjCRSummary> getObjectClass() {
        return CjCRSummary.class;
    }

    @Override
    public Class<CjCRSummaryJsonAdapter> getAdapterClass() {
        return CjCRSummaryJsonAdapter.class;
    }

    @Override
    public TypeToken<CjCRSummary> getObjectType() {
        return new TypeToken<CjCRSummary>(){};
    }

    @Override
    public TypeToken<List<CjCRSummary>> getListType() {
        return new TypeToken<List<CjCRSummary>>(){};
    }
}
