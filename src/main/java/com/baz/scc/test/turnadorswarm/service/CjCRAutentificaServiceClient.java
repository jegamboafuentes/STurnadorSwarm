package com.baz.scc.test.turnadorswarm.service;

import com.baz.scc.commons.web.client.model.CjCRClientResponse;
import com.baz.scc.commons.web.client.support.CjCRRestClient;
import com.baz.scc.commons.web.client.support.CjCRRestClient.RestResource;
import com.baz.scc.commons.web.client.support.CjCRServiceClientBase;
import com.baz.scc.test.turnadorswarm.model.CjCRAutenticacionResponse;
import com.baz.scc.test.turnadorswarm.model.CjCREmpleadoCredential;
import com.baz.scc.test.turnadorswarm.model.CjCRServiceConfig;
import com.baz.scc.test.turnadorswarm.support.CjCRAutenticacionResponseJsonManager;
import com.baz.scc.test.turnadorswarm.support.CjCREmpleadoCredentialJsonManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


/**
 * <br><br>Copyright 2013 Banco Azteca. Todos los derechos reservados.
 * 
 * @author Giovanni Farf\u00E1n Brice\u00F1o
 */

@Service
public class CjCRAutentificaServiceClient extends CjCRServiceClientBase {
    
    @Autowired
    private CjCREmpleadoCredentialJsonManager empleadoCredentialJsonManager;
    
    @Autowired
    private CjCRAutenticacionResponseJsonManager autenticacionResponseJsonManager;    
    
    @Autowired
    private CjCRServiceConfig serviceConfig;
    
    @Override
    protected void init() {
        resourceBase = serviceConfig.getAutentificaUrlBase();
        
        resources.put("autentifica", "Acceso/AutentificaEmpleado");
        
        restClient = new CjCRRestClient(resourceBase, resources);
    }
    
    public CjCRAutenticacionResponse autenticar(CjCREmpleadoCredential credential) {
        RestResource resource = new RestResource("autentifica");
        String json = empleadoCredentialJsonManager.getJson(credential);
        CjCRClientResponse response;
        
        resource.setJson(json);
        
        response = restClient.post(resource);
        
        return autenticacionResponseJsonManager.getObject(response.getResult());
    }
}
