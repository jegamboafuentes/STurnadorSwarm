package com.baz.scc.test.turnadorswarm.support;

import com.baz.scc.commons.web.client.support.CjCRJsonSimpleManager;
import com.baz.scc.test.turnadorswarm.model.CjCRResourceCredential;
import com.google.gson.reflect.TypeToken;
import java.util.List;
import org.springframework.stereotype.Component;


/**
 * <br><br>Copyright 2013 Banco Azteca. Todos los derechos reservados.
 * 
 * @author Giovanni Farf\u00E1n Brice\u00F1o
 */

@Component
public class CjCRResourceCredentialJsonManager extends CjCRJsonSimpleManager<CjCRResourceCredential> {

    @Override
    public TypeToken<CjCRResourceCredential> getObjectType() {
        return new TypeToken<CjCRResourceCredential>(){};
    }

    @Override
    public TypeToken<List<CjCRResourceCredential>> getListType() {
        return new TypeToken<List<CjCRResourceCredential>>(){};
    }
}
