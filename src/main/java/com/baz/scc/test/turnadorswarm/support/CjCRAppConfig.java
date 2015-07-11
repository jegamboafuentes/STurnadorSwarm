package com.baz.scc.test.turnadorswarm.support;

import com.baz.scc.commons.model.CjCRCajaConfig;
import com.baz.scc.commons.support.CjCRDataXmlExtract;
import com.baz.scc.commons.util.CjCRParseUtils;
import com.baz.scc.test.turnadorswarm.logic.CjCRCredentialsManager;
import com.baz.scc.test.turnadorswarm.logic.CjCRTurnadorContext;
import com.baz.scc.test.turnadorswarm.model.CjCREmpleadoCredential;
import com.baz.scc.test.turnadorswarm.model.CjCREmpleadoPool;
import com.baz.scc.test.turnadorswarm.model.CjCRPropiedadesRequest;
import com.baz.scc.test.turnadorswarm.model.CjCRTurnadorConfig;
import com.baz.scc.test.turnadorswarm.model.CjCRTurnoBehaviorConfig;
import com.baz.scc.test.turnadorswarm.model.CjCRUnidadNegocio;
import com.baz.scc.test.turnadorswarm.service.CjCRCredentialsServiceClient;
import com.baz.scc.test.turnadorswarm.service.CjCRNegocioServiceClient;
import com.baz.scc.test.turnadorswarm.service.CjCRTurnadorServiceClient;
import java.util.List;
import java.util.Map;
import java.util.Random;
import java.util.Set;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.util.Assert;


/**
 * <br><br>Copyright 2013 Banco Azteca. Todos los derechos reservados.
 * 
 * @author Giovanni Farf\u00E1n Brice\u00F1o
 */

@Component
public class CjCRAppConfig {
    
    private static final Logger log = Logger.getLogger(CjCRAppConfig.class);
    
    @Autowired
    private CjCRTurnadorContext turnadorContext;
    
    @Autowired
    private CjCREmpleadoPoolFactory empleadoPoolFactory;
    
    @Autowired
    private CjCRNegocioServiceClient negocioServiceClient;
    
    @Autowired
    private CjCRCredentialsServiceClient credentialsServiceClient;
    
    @Autowired
    private CjCRCredentialsManager credentialsManager;
    
    @Autowired
    private CjCRTurnadorConfig turnadorConfig;
    
    @Autowired
    private CjCRTurnadorServiceClient turnadorServiceClient;
    
    private boolean bInit;
    
    public void init() {
        if(!bInit) {
            bInit = true;
            
            try {
                initCredentialsManager();
                initUnidadesNegocioAtencion();
                initContextConfig();
                validateConfig();
            } catch(Exception ex) {
                log.error("Error en obtenci\u00F3n de datos", ex);

                throw new RuntimeException("Inicializaci\u00F3n con error");
            }
        }
    }
    
    private void initCredentialsManager() throws Exception {
        List<CjCREmpleadoCredential> credentials;
        
        if(!turnadorConfig.isCredentialsFile()) {
            log.info("Obteniendo credenciales de empleados");
            
            credentials = credentialsServiceClient.getEmpleadosAuthorization(
                turnadorConfig.getNoEmpleados());
        
            for(CjCREmpleadoCredential credential : credentials) {
                credential.setSucursal(turnadorConfig.getSucursal());
            
                credentialsManager.addCredential(credential);
            }
        }        
    }
    
    private void initUnidadesNegocioAtencion() throws Exception {
        CjCREmpleadoPool empleadoPool;
        CjCREmpleadoCredential credential;
        CjCRDataXmlExtract extract;
        int idUnidadNegocio;
        
        log.info("Configurando unidades de negocio");
        
        extract = new CjCRDataXmlExtract(
                ClassLoader.getSystemResourceAsStream(turnadorConfig.getNegocioAtencionData()));
        
        for(CjCRDataXmlExtract entryUnidad : extract.getChildren()) {
            idUnidadNegocio = entryUnidad.getInt("idUnidadNegocio");
            
            turnadorContext.addUnidadNegocio(idUnidadNegocio);
            
            for(CjCRDataXmlExtract entryEmpleado : entryUnidad.getChildren("empleados")) {
                if(turnadorConfig.isCredentialsFile()) {
                    initEmpleadoCredential(entryEmpleado);
                }
                
                credential = credentialsManager.getCredential();
                empleadoPool = empleadoPoolFactory.get(credential.getNoEmpleado());
                
                credentialsManager.configureCredential(credential.getNoEmpleado());                
                turnadorContext.addEmpleado(idUnidadNegocio, empleadoPool);
            }
        }
        
        validateUnidadesNegocio();    
    }
    
    private void initEmpleadoCredential(CjCRDataXmlExtract empleadoExtract) throws Exception {
        CjCRDataXmlExtract propiedadesExtract = empleadoExtract.getChild("propiedades");
        CjCREmpleadoCredential credential = new CjCREmpleadoCredential();
        CjCRPropiedadesRequest propiedades = new CjCRPropiedadesRequest();
        
        credential.setNoEmpleado(empleadoExtract.getString("noEmpleado"));
        credential.setNumeroEmpleado(empleadoExtract.getString("numeroEmpleado"));
        credential.setPasswordEmpleado(empleadoExtract.getString("passwordEmpleado"));
            
        propiedades.setNombreEstacion(propiedadesExtract.getString("nombreEstacion"));
        propiedades.setNumeroIp(propiedadesExtract.getString("numeroIp"));
        propiedades.setTipoEstacion(propiedadesExtract.getInt("tipoEstacion"));
        
        credential.setSucursal(turnadorConfig.getSucursal());
        credential.setPropiedades(propiedades);
        
        credentialsManager.addCredential(credential);
    }
    
    private void initContextConfig() {
        CjCRCajaConfig cajaConfig;
        int turnosCaducidad;
        
        log.info("Estableciendo configuraci\u00F3n de caja");
        
        cajaConfig = turnadorServiceClient.getTurnosCaducidad();
        turnosCaducidad = CjCRParseUtils.toInteger(cajaConfig.getValor());
        
        turnadorConfig.setTurnosCaducidad(turnosCaducidad);
    }
    
    private void validateConfig() {
        Random random = new Random();
        int noTurnos = turnadorConfig.getNoTurnos();
        CjCRTurnoBehaviorConfig turnoConfig;
        int percent = 0;
        int assigned;
        
        Assert.isTrue(noTurnos > 0, "Cantidad de turnos inv\u00E1lida");
        
        for(Map.Entry<Integer, CjCRTurnoBehaviorConfig> entry : 
                turnadorConfig.getTurnosConfig().entrySet()) {
            turnoConfig = entry.getValue();
            percent += turnoConfig.getPercent();
            assigned = (int) (turnadorConfig.getNoTurnos() * 
                    ((float) turnoConfig.getPercent() / 100f));
            noTurnos -= assigned;
            
            turnoConfig.setAssigned(assigned);
        }
        
        turnoConfig = turnadorConfig.getTurnosConfig().get(
                random.nextInt(turnadorConfig.getTurnosConfig().size()));
        assigned = turnoConfig.getAssigned() + noTurnos;
        
        turnoConfig.setAssigned(assigned);
        
        Assert.isTrue(percent == 100, "Configuraci\u00F3n de comportamiento de turnos inv\u00E1lida");
    }
    
    private void validateUnidadesNegocio() {
        List<CjCRUnidadNegocio> unidadesNegocio;
        Set<Integer> idsUnidadNegocio;
        int validas = 0;
        
        log.info("Validando unidades de negocio");
        
        unidadesNegocio = negocioServiceClient.getNegocio();
        idsUnidadNegocio = turnadorContext.getIdsUnidadesNegocio();
        
        for(CjCRUnidadNegocio unidadNegocio : unidadesNegocio) {
            if(idsUnidadNegocio.contains(unidadNegocio.getIdUnidadNegocio())) {
               validas++; 
            }else{
                log.warn("Falta UDN "+unidadNegocio.getIdUnidadNegocio());
            }
        }
        
        if(validas != idsUnidadNegocio.size()) {
            throw new IllegalArgumentException("Unidades de negocio no establecidas");
        }
    }
}
