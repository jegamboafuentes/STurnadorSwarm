package com.baz.scc.test.turnadorswarm.logic;

import com.baz.scc.commons.util.CjCRStructureUtils;
import com.baz.scc.test.turnadorswarm.model.CjCRAutenticacionResponse;
import com.baz.scc.test.turnadorswarm.model.CjCREmpleadoCredential;
import com.baz.scc.test.turnadorswarm.model.CjCRPropiedadesResponse;
import com.baz.scc.test.turnadorswarm.model.CjCRTurnadorConfig;
import com.baz.scc.test.turnadorswarm.service.CjCRAutentificaServiceClient;
import com.baz.scc.test.turnadorswarm.service.CjCRCipherServiceClient;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Queue;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;


/**
 * <br><br>Copyright 2013 Banco Azteca. Todos los derechos reservados.
 * 
 * @author Giovanni Farf\u00E1n Brice\u00F1o
 */

@Component
public class CjCRCredentialsManager {
        
    private static final Logger log = Logger.getLogger(CjCRCredentialsManager.class);
    
    private final Map<String, CjCREmpleadoCredential> credentials;
    private final Queue<CjCREmpleadoCredential> available;
    
    @Autowired
    private CjCRCipherServiceClient cipherServiceClient;
    
    @Autowired
    private CjCRAutentificaServiceClient autenticatificaServiceClient;
    
    @Autowired
    private CjCRTurnadorConfig turnadorConfig;
    
    public CjCRCredentialsManager() {
        credentials = CjCRStructureUtils.getMap();
        available = new ArrayDeque<CjCREmpleadoCredential>();
    }
    
    public void addCredential(CjCREmpleadoCredential credential) {
        credentials.put(credential.getNoEmpleado(), credential);
        available.add(credential);
    }
    
    public void configureCredential(String noEmpleado) throws IllegalAccessException {
        CjCREmpleadoCredential credential = credentials.get(noEmpleado);
        
        if(credential != null) {
            if(!turnadorConfig.isDefaultCredential()) {
                cipherData(credential);
                autenticar(credential);
            }
        } else {
            throw new IllegalArgumentException("Credencial inv\u00E1lida");
        }
    }
    
    public CjCREmpleadoCredential getCredential() {
        CjCREmpleadoCredential credential = available.poll();
        
        if(credential == null) {
            throw new IllegalStateException("Sin credenciales disponibles");
        }
        
        return credential;
    }
    
    public CjCREmpleadoCredential getCredential(String noEmpleado) {        
        return credentials.get(noEmpleado);
    }
    
    private void cipherData(CjCREmpleadoCredential credential) {
        List<String> elements = new ArrayList<String>();
        
        log.info(String.format("Cifrando datos de empleado %s", credential.getNumeroEmpleado()));
        
        elements.add(Integer.toString(credential.getSucursal()));
        elements.add(credential.getNumeroEmpleado());
        elements.add(credential.getPasswordEmpleado());
        
        try {
            elements = cipherServiceClient.cipher(elements);
        
            credential.setCipherNumero(elements.get(0));
            credential.setCipherPassword(elements.get(1));
        } catch(Exception ex) {
            log.error("Error en cifrado de datos", ex);
        }
    }
    
    private void autenticar(CjCREmpleadoCredential credential) throws IllegalAccessException {
        CjCRAutenticacionResponse response = autenticatificaServiceClient.autenticar(credential);
        CjCRPropiedadesResponse propiedades = response.getPropiedades();
        
        log.info(String.format("Autenticando empleado %s", credential.getNumeroEmpleado()));
        
        if(propiedades != null && propiedades.isAccesoConcedido()) {
            credential.setToken(propiedades.getToken());
        } else {
            throw new IllegalAccessException(
                    String.format("Acceso inv�lido, %s - %s", 
                            response.getMensaje().getDescripcionError(),
                            credential.getNumeroEmpleado()));
        }
    }
}
