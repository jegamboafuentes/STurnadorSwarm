package com.baz.scc.test.turnadorswarm.service;

import com.baz.scc.commons.model.CjCRSummary;
import com.baz.scc.commons.web.client.model.CjCRClientResponse;
import com.baz.scc.commons.web.client.support.CjCRRestClient;
import com.baz.scc.commons.web.client.support.CjCRRestClient.RestResource;
import com.baz.scc.commons.web.client.support.CjCRServiceClientBase;
import com.baz.scc.test.turnadorswarm.logic.CjCRAuthorizationManager;
import com.baz.scc.test.turnadorswarm.model.CjCREmpleadoPool;
import com.baz.scc.test.turnadorswarm.model.CjCRServiceConfig;
import com.baz.scc.test.turnadorswarm.model.CjCRSummaryTurno;
import com.baz.scc.test.turnadorswarm.model.CjCRTurno;
import com.baz.scc.test.turnadorswarm.support.CjCRSummaryJsonManager;
import com.baz.scc.test.turnadorswarm.support.CjCRSummaryTurnoJsonManager;
import com.baz.scc.test.turnadorswarm.support.CjCRTurnoJsonManager;
import java.util.List;
import javax.ws.rs.core.HttpHeaders;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


/**
 * <br><br>Copyright 2013 Banco Azteca. Todos los derechos reservados.
 * 
 * @author Giovanni Farf\u00E1n Brice\u00F1o
 */

@Service
public class CjCRTurnosServiceClient extends CjCRServiceClientBase {

    @Autowired
    private CjCRTurnoJsonManager turnoJsonManager;
    
    @Autowired
    private CjCRSummaryJsonManager summaryJsonManager;
    
    @Autowired
    private CjCRSummaryTurnoJsonManager summaryTurnoJsonManager;
    
    @Autowired
    private CjCRAuthorizationManager authotizationManager;
    
    @Autowired
    private CjCRServiceConfig serviceConfig;
    
    @Override
    protected void init() {
        resourceBase = serviceConfig.getTurnosUrlBase();
        
        resources.put("turno", "turno/");
        resources.put("getturno", "turno/{fecha}/{idTurno}/");
        resources.put("getturnoasignado", "turno/{fecha}/{idUnidadNegocio}/{noEmpleado}/asignado/");
        resources.put("getturnosasignados", "turno/{fecha}/negocio/asignados/");
        resources.put("completar", "turno/completar/");
        resources.put("posponer", "turno/porponer/");
        resources.put("cancelar", "turno/cancelar/");
        resources.put("atender", "turno/{noEmpleado}/atender/");
        resources.put("atendervirtual", "turno/virtual/{noEmpleado}/atender/");
        resources.put("apropiar", "turno/{noEmpleado}/apropiar/");
        resources.put("finalizar", "turno/{noEmpleado}/finalizar/");
        
        restClient = new CjCRRestClient(resourceBase, resources);
    }
    
    public CjCRTurno generarTurno(CjCRTurno turno) {        
        RestResource resource = new RestResource("turno");
        String json = turnoJsonManager.getJson(turno);
        CjCRClientResponse response;
        
        resource.setJson(json);        
        resource.addHeader(HttpHeaders.AUTHORIZATION, 
                authotizationManager.getAuthorization());
        
        response = restClient.post(resource);
        
        return turnoJsonManager.getObject(response.getResult());
    }
    
    public CjCRTurno getTurno(CjCRTurno turno) {
        RestResource resource = new RestResource("getturno");
        CjCRClientResponse response;
        
        resource.addPath(turno.getFecha());
        resource.addPath(turno.getIdTurno());
        resource.addHeader(HttpHeaders.AUTHORIZATION, 
                authotizationManager.getAuthorization());
        
        response = restClient.get(resource);
        
        return turnoJsonManager.getObject(response.getResult());
    }
    
    public CjCRTurno getTurnoAsignado(int fecha, int idUnidadNegocio, String noEmpleado) {
        RestResource resource = new RestResource("getturnoasignado");
        CjCRClientResponse response;
        
        resource.addPath(fecha);
        resource.addPath(idUnidadNegocio);
        resource.addPath(noEmpleado);
        
        response = restClient.get(resource);
        
        return turnoJsonManager.getObject(response.getResult());
    }
    
    public List<CjCRTurno> getTurnosAsignados(int fecha) {        
        RestResource resource = new RestResource("getturnosasignados");
        CjCRClientResponse response;
        
        resource.addPath(fecha);
        
        response = restClient.get(resource);
        
        return turnoJsonManager.getList(response.getResult());
    }
    
    public CjCRSummary completarTurnos(String noEmpleado) {
        RestResource resource = new RestResource("completar");
        CjCRClientResponse response;
        
        resource.addHeader(HttpHeaders.AUTHORIZATION, 
                authotizationManager.getAuthorization(
                noEmpleado));
        
        response = restClient.put(resource);
        
        return summaryJsonManager.getObject(response.getResult());
    }
    
    public CjCRSummary posponerTurno(CjCREmpleadoPool empleado, CjCRTurno turno) {
        RestResource resource = new RestResource("posponer");
        String json = turnoJsonManager.getJson(turno);
        CjCRClientResponse response;
        
        resource.setJson(json);
        resource.addHeader(HttpHeaders.AUTHORIZATION, 
                authotizationManager.getAuthorization(
                        empleado.getNoEmpleado()));
        
        response = restClient.put(resource);
        
        return summaryJsonManager.getObject(response.getResult());
    }
    
    public CjCRSummary cancelarTurno(CjCREmpleadoPool empleado, CjCRTurno turno) {
        RestResource resource = new RestResource("cancelar");
        String json = turnoJsonManager.getJson(turno);
        CjCRClientResponse response;
        
        resource.setJson(json);
        resource.addHeader(HttpHeaders.AUTHORIZATION, 
                authotizationManager.getAuthorization(
                        empleado.getNoEmpleado()));
        
        response = restClient.put(resource);
        
        return summaryJsonManager.getObject(response.getResult());
    }
    
    public CjCRSummary atenderTurno(CjCREmpleadoPool empleado, CjCRTurno turno) {
        RestResource resource = new RestResource("atender");
        String json = turnoJsonManager.getJson(turno);
        CjCRClientResponse response;
        
        resource.setJson(json);        
        resource.addPath(empleado.getNoEmpleado());        
        resource.addHeader(HttpHeaders.AUTHORIZATION, 
                authotizationManager.getAuthorization(
                empleado.getNoEmpleado()));
        
        response = restClient.put(resource);
        
        return summaryJsonManager.getObject(response.getResult());
    }
    
    public CjCRSummaryTurno atenderTurnoVirtual(CjCREmpleadoPool empleado, CjCRTurno turno) {
        RestResource resource = new RestResource("atendervirtual");
        String json = turnoJsonManager.getJson(turno);
        CjCRClientResponse response;
        
        resource.setJson(json);        
        resource.addPath(empleado.getNoEmpleado());        
        resource.addHeader(HttpHeaders.AUTHORIZATION, 
                authotizationManager.getAuthorization(
                empleado.getNoEmpleado()));
        
        response = restClient.put(resource);
        
        return summaryTurnoJsonManager.getObject(response.getResult());
    }
    
    public CjCRSummary apropiarTurno(CjCREmpleadoPool empleado, CjCRTurno turno) {
        RestResource resource = new RestResource("apropiar");
        String json = turnoJsonManager.getJson(turno);
        CjCRClientResponse response;
        
        resource.setJson(json);        
        resource.addPath(empleado.getNoEmpleado());        
        resource.addHeader(HttpHeaders.AUTHORIZATION, 
                authotizationManager.getAuthorization(
                empleado.getNoEmpleado()));
        
        response = restClient.put(resource);
        
        return summaryJsonManager.getObject(response.getResult());
    }
    
    public CjCRSummary finalizarTurno(CjCREmpleadoPool empleado, CjCRTurno turno) {
        RestResource resource = new RestResource("finalizar");
        String json = turnoJsonManager.getJson(turno);
        CjCRClientResponse response;
        
        resource.setJson(json);        
        resource.addPath(empleado.getNoEmpleado());        
        resource.addHeader(HttpHeaders.AUTHORIZATION, 
                authotizationManager.getAuthorization(
                empleado.getNoEmpleado()));
        
        response = restClient.put(resource);
        
        return summaryJsonManager.getObject(response.getResult());
    }
}
