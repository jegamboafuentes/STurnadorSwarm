package com.baz.scc.test.turnadorswarm.service;

import com.baz.scc.commons.model.CjCRCajaConfig;
import com.baz.scc.commons.model.CjCRSummary;
import com.baz.scc.commons.web.client.model.CjCRClientResponse;
import com.baz.scc.commons.web.client.support.CjCRRestClient;
import com.baz.scc.commons.web.client.support.CjCRRestClient.RestResource;
import com.baz.scc.commons.web.client.support.CjCRServiceClientBase;
import com.baz.scc.test.turnadorswarm.logic.CjCRAuthorizationManager;
import com.baz.scc.test.turnadorswarm.model.CjCREmpleadoPool;
import com.baz.scc.test.turnadorswarm.model.CjCRServiceConfig;
import com.baz.scc.test.turnadorswarm.support.CjCRCajaConfigJsonManager;
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
public class CjCRTurnadorServiceClient extends CjCRServiceClientBase {
    
    @Autowired
    private CjCRSummaryJsonManager summaryJsonManager;
    
    @Autowired
    private CjCRCajaConfigJsonManager cajaConfigJsonManager;
    
    @Autowired
    private CjCRAuthorizationManager authotizationManager;
    
    @Autowired
    private CjCRServiceConfig serviceConfig;
    
    @Override
    protected void init() {
        resourceBase = serviceConfig.getTurnadorUrlBase();
        
        resources.put("activarplan", "turnador/activar/contingencia/");
        resources.put("desactivarplan", "turnador/desactivar/contingencia/");
        resources.put("display", "turnador/display/duracion/");
        resources.put("caducidad", "turnador/turnos/caducidad/");
        
        restClient = new CjCRRestClient(resourceBase, resources);
    }
    
    public CjCRSummary activarContingencia(CjCREmpleadoPool empleado) {
        RestResource resource = new RestResource("activarplan");
        CjCRClientResponse response;
        
        resource.addHeader(HttpHeaders.AUTHORIZATION, 
                authotizationManager.getAuthorization(
                        empleado.getNoEmpleado()));
        
        response = restClient.put(resource);
        
        return summaryJsonManager.getObject(response.getResult());
    }
    
    public CjCRSummary desactivarContingencia(CjCREmpleadoPool empleado) {
        RestResource resource = new RestResource("desactivarplan");
        CjCRClientResponse response;
        
        resource.addHeader(HttpHeaders.AUTHORIZATION, 
                authotizationManager.getAuthorization(
                        empleado.getNoEmpleado()));
        
        response = restClient.put(resource);
        
        return summaryJsonManager.getObject(response.getResult());
    }
    
    public CjCRCajaConfig getDisplayDuracion() {
        RestResource resource = new RestResource("display");
        CjCRClientResponse response;
        
        resource.addHeader(HttpHeaders.AUTHORIZATION, 
                authotizationManager.getAuthorization());
        
        response = restClient.get(resource);
        
        return cajaConfigJsonManager.getObject(response.getResult());
    }
    
    public CjCRCajaConfig getTurnosCaducidad() {
        RestResource resource = new RestResource("caducidad");
        CjCRClientResponse response;
        
        resource.addHeader(HttpHeaders.AUTHORIZATION, 
                authotizationManager.getAuthorization());
        
        response = restClient.get(resource);
        
        return cajaConfigJsonManager.getObject(response.getResult());
    }
}
