package com.baz.scc.test.turnadorswarm.support;

import com.baz.scc.commons.web.client.support.CjCRJsonManager;
import com.baz.scc.test.turnadorswarm.model.CjCRSummaryTurno;
import com.baz.scc.test.turnadorswarm.util.CjCRSummaryTurnoJsonAdapter;
import com.google.gson.reflect.TypeToken;
import java.util.List;
import org.springframework.stereotype.Component;


/**
 * <br><br>Copyright 2013 Banco Azteca. Todos los derechos reservados.
 * 
 * @author Giovanni Farf\u00E1n Brice\u00F1o
 */

@Component
public class CjCRSummaryTurnoJsonManager extends CjCRJsonManager<CjCRSummaryTurno, CjCRSummaryTurnoJsonAdapter> {

    @Override
    public Class<CjCRSummaryTurno> getObjectClass() {
        return CjCRSummaryTurno.class;
    }

    @Override
    public Class<CjCRSummaryTurnoJsonAdapter> getAdapterClass() {
        return CjCRSummaryTurnoJsonAdapter.class;
    }

    @Override
    public TypeToken<CjCRSummaryTurno> getObjectType() {
        return new TypeToken<CjCRSummaryTurno>(){};
    }

    @Override
    public TypeToken<List<CjCRSummaryTurno>> getListType() {
        return new TypeToken<List<CjCRSummaryTurno>>(){};
    }
}
