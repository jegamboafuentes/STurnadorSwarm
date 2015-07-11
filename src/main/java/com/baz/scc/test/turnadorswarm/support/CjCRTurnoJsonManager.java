package com.baz.scc.test.turnadorswarm.support;

import com.baz.scc.commons.web.client.support.CjCRJsonManager;
import com.baz.scc.test.turnadorswarm.model.CjCRTurno;
import com.baz.scc.test.turnadorswarm.util.CjCRTurnoJsonAdapter;
import com.google.gson.reflect.TypeToken;
import java.util.List;
import org.springframework.stereotype.Component;


/**
 * <br><br>Copyright 2013 Banco Azteca. Todos los derechos reservados.
 * 
 * @author Giovanni Farf\u00E1n Brice\u00F1o
 */

@Component
public class CjCRTurnoJsonManager extends CjCRJsonManager<CjCRTurno, CjCRTurnoJsonAdapter> {

    @Override
    public Class<CjCRTurno> getObjectClass() {
        return CjCRTurno.class;
    }

    @Override
    public Class<CjCRTurnoJsonAdapter> getAdapterClass() {
        return CjCRTurnoJsonAdapter.class;
    }

    @Override
    public TypeToken getObjectType() {
        return new TypeToken<CjCRTurno>(){};
    }

    @Override
    public TypeToken<List<CjCRTurno>> getListType() {
        return new TypeToken<List<CjCRTurno>>(){};
    }
}
