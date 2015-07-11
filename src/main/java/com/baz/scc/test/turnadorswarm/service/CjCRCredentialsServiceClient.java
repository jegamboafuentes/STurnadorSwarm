package com.baz.scc.test.turnadorswarm.service;

import com.baz.scc.commons.web.client.model.CjCRClientResponse;
import com.baz.scc.commons.web.client.support.CjCRRestClient;
import com.baz.scc.commons.web.client.support.CjCRRestClient.RestResource;
import com.baz.scc.commons.web.client.support.CjCRServiceClientBase;
import com.baz.scc.test.turnadorswarm.model.CjCREmpleadoCredential;
import com.baz.scc.test.turnadorswarm.model.CjCRServiceConfig;
import com.baz.scc.test.turnadorswarm.support.CjCREmpleadoCredentialJsonManager;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


/**
 * <br><br>Copyright 2013 Banco Azteca. Todos los derechos reservados.
 * 
 * @author Giovanni Farf\u00E1n Brice\u00F1o
 */

@Service
public class CjCRCredentialsServiceClient extends CjCRServiceClientBase {
    
    @Autowired
    private CjCREmpleadoCredentialJsonManager empleadoCredentialJsonManager;
    
    @Autowired
    private CjCRServiceConfig serviceConfig;
    
    @Override
    protected void init() {
        resourceBase = serviceConfig.getCredentialsUrlBase();
        
        resources.put("authorization", "empleados/{no}/authorization");
        resources.put("connection", "empleados/{no}/connection");
        
        restClient = new CjCRRestClient(resourceBase, resources);
    }
    
    public List<CjCREmpleadoCredential> getEmpleadosAuthorization(int no) {
        RestResource resource = new RestResource("empleados");
        CjCRClientResponse response;
        
        resource.addPath(no);
        
        response = restClient.get(resource);
        
        return empleadoCredentialJsonManager.getList(response.getResult());
    }
    
    public List<CjCREmpleadoCredential> getEmpleadosConnection(int no) {
        RestResource resource = new RestResource("connection");
        CjCRClientResponse response;
        
        resource.addPath(no);
        
        response = restClient.get(resource);
        
        return empleadoCredentialJsonManager.getList(response.getResult());
    }
}
