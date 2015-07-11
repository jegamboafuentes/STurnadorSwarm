package com.baz.scc.test.turnadorswarm.service;

import com.baz.scc.commons.web.client.model.CjCRClientResponse;
import com.baz.scc.commons.web.client.support.CjCRRestClient;
import com.baz.scc.commons.web.client.support.CjCRRestClient.RestResource;
import com.baz.scc.commons.web.client.support.CjCRServiceClientBase;
import com.baz.scc.test.turnadorswarm.model.CjCRServiceConfig;
import com.baz.scc.test.turnadorswarm.model.CjCRUnidadNegocio;
import com.baz.scc.test.turnadorswarm.support.CjCRUnidadNegocioJsonManager;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


/**
 * <br><br>Copyright 2013 Banco Azteca. Todos los derechos reservados.
 * 
 * @author Giovanni Farf\u00E1n Brice\u00F1o
 */

@Service
public class CjCRNegocioServiceClient extends CjCRServiceClientBase {
    
    @Autowired
    private CjCRUnidadNegocioJsonManager unidadNegocioJsonManager;
    
    @Autowired
    private CjCRServiceConfig serviceConfig;
    
    @Override
    protected void init() {
        resourceBase = serviceConfig.getNegocioUrlBase();
        
        resources.put("negocio", "negocio/");
        
        restClient = new CjCRRestClient(resourceBase, resources);
    }
    
    public List<CjCRUnidadNegocio> getNegocio() {
        RestResource resource = new RestResource("negocio");
        CjCRClientResponse response = restClient.get(resource);
        
        return unidadNegocioJsonManager.getList(response.getResult());
    }
}
