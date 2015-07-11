package com.baz.scc.test.turnadorswarm.logic;

import com.baz.scc.test.turnadorswarm.model.CjCREmpleadoCredential;
import com.baz.scc.test.turnadorswarm.model.CjCRResourceCredential;
import com.baz.scc.test.turnadorswarm.model.CjCRTurnadorConfig;
import com.baz.scc.test.turnadorswarm.support.CjCRResourceCredentialJsonManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;


/**
 * <br><br>Copyright 2013 Banco Azteca. Todos los derechos reservados.
 * 
 * @author Giovanni Farf\u00E1n Brice\u00F1o
 */

@Component
public class CjCRAuthorizationManager {
    
    @Autowired
    private CjCRCredentialsManager credentialsManager;
    
    @Autowired
    private CjCRResourceCredentialJsonManager resourceCredentialJsonManager;
    
    @Autowired
    private CjCRTurnadorConfig turnadorConfig;
    
    public String getAuthorization() {
        CjCRResourceCredential resource = new CjCRResourceCredential();
        
        resource.setDt(true);
        
        return resourceCredentialJsonManager.getJson(resource);
    }
    
    public String getAuthorization(String noEmpleado) {
        CjCREmpleadoCredential credential = credentialsManager.getCredential(noEmpleado);
        CjCRResourceCredential resource = new CjCRResourceCredential();
        
        resource.setEm(credential.getCipherNumero());
        resource.setPw(credential.getCipherPassword());
        resource.setSu(credential.getSucursal());
        resource.setTk(credential.getToken());        
        resource.setDt(turnadorConfig.isDefaultCredential());
        
        return resourceCredentialJsonManager.getJson(resource);
    }
}
