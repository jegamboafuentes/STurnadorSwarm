package com.baz.scc.test.turnadorswarm.support;

import com.baz.scc.commons.web.client.support.CjCRJsonManager;
import com.baz.scc.test.turnadorswarm.model.CjCREmpleadoCredential;
import com.baz.scc.test.turnadorswarm.util.CjCREmpleadoCredentialJsonAdapter;
import com.google.gson.reflect.TypeToken;
import java.util.List;
import org.springframework.stereotype.Component;


/**
 * <br><br>Copyright 2013 Banco Azteca. Todos los derechos reservados.
 * 
 * @author Giovanni Farf\u00E1n Brice\u00F1o
 */

@Component
public class CjCREmpleadoCredentialJsonManager extends 
        CjCRJsonManager<CjCREmpleadoCredential, CjCREmpleadoCredentialJsonAdapter> {

    @Override
    public Class<CjCREmpleadoCredential> getObjectClass() {
        return CjCREmpleadoCredential.class;
    }

    @Override
    public Class<CjCREmpleadoCredentialJsonAdapter> getAdapterClass() {
        return CjCREmpleadoCredentialJsonAdapter.class;
    }

    @Override
    public TypeToken<CjCREmpleadoCredential> getObjectType() {
        return new TypeToken<CjCREmpleadoCredential>(){};
    }

    @Override
    public TypeToken<List<CjCREmpleadoCredential>> getListType() {
        return new TypeToken<List<CjCREmpleadoCredential>>(){};
    }
}
