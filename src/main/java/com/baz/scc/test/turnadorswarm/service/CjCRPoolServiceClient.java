package com.baz.scc.test.turnadorswarm.service;

import com.baz.scc.commons.model.CjCRSummary;
import com.baz.scc.commons.web.client.model.CjCRClientResponse;
import com.baz.scc.commons.web.client.support.CjCRRestClient;
import com.baz.scc.commons.web.client.support.CjCRRestClient.RestResource;
import com.baz.scc.commons.web.client.support.CjCRServiceClientBase;
import com.baz.scc.test.turnadorswarm.logic.CjCRAuthorizationManager;
import com.baz.scc.test.turnadorswarm.model.CjCREmpleadoPool;
import com.baz.scc.test.turnadorswarm.model.CjCRServiceConfig;
import com.baz.scc.test.turnadorswarm.support.CjCRSummaryJsonManager;
import javax.ws.rs.core.HttpHeaders;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


/**
 * <br><br>Copyright 2013 Banco Azteca. Todos los derechos reservados.
 * 
 * @author Giovanni Farf\u00E1n Brice\u00F1o
 */

@Service
public class CjCRPoolServiceClient extends CjCRServiceClientBase {
    
    @Autowired
    private CjCRSummaryJsonManager summaryJsonManager;
    
    @Autowired
    private CjCRAuthorizationManager authotizationManager;
    
    @Autowired
    private CjCRServiceConfig serviceConfig;
    
    @Override
    protected void init() {
        resourceBase = serviceConfig.getPoolUrlBase();
        
//        resources.put("nodisponible", "pool/{idUnidadNegocio}/{noEmpleado}/nodisponible/");
//        resources.put("disponible", "pool/{idUnidadNegocio}/{noEmpleado}/disponible/");
        resources.put("nodisponible", "pool/{noEmpleado}/nodisponible/");
        resources.put("disponible", "pool/{noEmpleado}/disponible/");
        
        restClient = new CjCRRestClient(resourceBase, resources);
    }
    
    public CjCRSummary setNoDisponible(int idUnidadNegocio, CjCREmpleadoPool empleado) {
        RestResource resource = new RestResource("nodisponible");
        CjCRClientResponse response;
        
        //resource.addPath(idUnidadNegocio);
        resource.addPath(empleado.getNoEmpleado());
        resource.addHeader(HttpHeaders.AUTHORIZATION, 
                authotizationManager.getAuthorization(
                empleado.getNoEmpleado()));
        
        response = restClient.put(resource);
        
        return summaryJsonManager.getObject(response.getResult());
    }
    
    public CjCRSummary setDisponible(int idUnidadNegocio, CjCREmpleadoPool empleado) {
        RestResource resource = new RestResource("disponible");
        CjCRClientResponse response;
        
        //resource.addPath(idUnidadNegocio);
        resource.addPath(empleado.getNoEmpleado());
        resource.addHeader(HttpHeaders.AUTHORIZATION, 
                authotizationManager.getAuthorization(
                empleado.getNoEmpleado()));
        
        response = restClient.put(resource);
        
        return summaryJsonManager.getObject(response.getResult());
    }
}
