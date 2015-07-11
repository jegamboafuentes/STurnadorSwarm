package com.baz.scc.test.turnadorswarm.main;

import com.baz.scc.commons.util.CjCRSpringContext;
import com.baz.scc.test.turnadorswarm.logic.CjCRTurnadorProcess;
import com.baz.scc.test.turnadorswarm.support.CjCRAppConfig;
import org.apache.log4j.Logger;
import org.springframework.stereotype.Component;


/**
 * <br><br>Copyright 2013 Banco Azteca. Todos los derechos reservados.
 * 
 * @author Giovanni Farf\u00E1n Brice\u00F1o
 */

@Component
public class CjCRMain {

    public static final Logger log = Logger.getLogger(CjCRMain.class);
    
    public static void main(String[] args) {
        try {
            CjCRSpringContext.init();
            
            CjCRTurnadorProcess process = CjCRSpringContext.getBean(CjCRTurnadorProcess.class);
            CjCRAppConfig appConfig = CjCRSpringContext.getBean(CjCRAppConfig.class);
            
            appConfig.init();
            process.process();
        } catch(Exception ex) {
            log.error(String.format("Error en aplicaci\u00F3n - %s", 
                    ex.getMessage()), ex);
        }
    }
}
