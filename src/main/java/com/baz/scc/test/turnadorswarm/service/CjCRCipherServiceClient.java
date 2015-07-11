package com.baz.scc.test.turnadorswarm.service;

import com.baz.scc.commons.web.client.model.CjCRClientResponse;
import com.baz.scc.commons.web.client.support.CjCRRestClient;
import com.baz.scc.commons.web.client.support.CjCRRestClient.RestResource;
import com.baz.scc.commons.web.client.support.CjCRServiceClientBase;
import com.baz.scc.commons.web.client.support.CjCRStringJsonManager;
import com.baz.scc.test.turnadorswarm.model.CjCRServiceConfig;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


/**
 * <br><br>Copyright 2013 Banco Azteca. Todos los derechos reservados.
 * 
 * @author Giovanni Farf\u00E1n Brice\u00F1o
 */

@Service
public class CjCRCipherServiceClient extends CjCRServiceClientBase {
    
    @Autowired
    private CjCRStringJsonManager stringJsonManager;
    
    @Autowired
    private CjCRServiceConfig serviceConfig;
    
    @Override
    protected void init() {
        resourceBase = serviceConfig.getCipherUrlBase();
        
        resources.put("cipher", "elements");
        
        restClient = new CjCRRestClient(resourceBase, resources);
    }
    
    public List<String> cipher(List<String> elements) {
        RestResource resource = new RestResource("cipher");
        String json = stringJsonManager.getJson(elements);
        CjCRClientResponse response;
        
        resource.setJson(json);
        
        response = restClient.post(resource);
        
        return stringJsonManager.getList(response.getResult());
    }
}
