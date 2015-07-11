package com.baz.scc.test.turnadorswarm.logic;

import com.baz.scc.test.turnadorswarm.model.CjCRTurnadorConfig;
import com.baz.scc.test.turnadorswarm.service.CjCRTurnosServiceClient;
import java.util.Random;
import java.util.concurrent.Callable;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;


/**
 * <br><br>Copyright 2013 Banco Azteca. Todos los derechos reservados.
 * 
 * @author Giovanni Farf\u00E1n Brice\u00F1o
 */

public abstract class CjCRTurnadorTask implements Callable<Boolean> {
    
    protected static final Logger log = Logger.getLogger(CjCRTurnadorTask.class);
        
    @Autowired
    protected CjCRTurnosServiceClient turnosServiceClient;
    
    @Autowired
    protected CjCRTurnadorContext turnadorContext;
    
    @Autowired
    protected CjCRTurnadorConfig turnadorConfig;
    
    protected void esperar(int factorMin, int factorMax) {
        
        try {
            Thread.sleep(turnadorConfig.getTiempoMinimo() 
                    * getFactor(1, turnadorConfig.getFactorEspera()) 
                    * getFactor(factorMin, factorMax));
        } catch(Exception ex) {
            log.warn(String.format("Error en espera - %s", ex.getMessage()));
        }
    }
    
    private int getFactor(int factorMin, int factorMax) {
        Random random = new Random();
        
        return (random.nextInt(factorMax) + factorMin);
    }
}
