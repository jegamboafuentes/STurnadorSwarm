package com.baz.scc.test.turnadorswarm.logic;

import com.baz.scc.test.turnadorswarm.model.CjCRTurno;
import com.baz.scc.test.turnadorswarm.model.CjCRTurnoBehavior;
import com.baz.scc.test.turnadorswarm.support.CjCRSwarmStatus;
import org.springframework.beans.factory.config.BeanDefinition;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;


/**
 * <br><br>Copyright 2013 Banco Azteca. Todos los derechos reservados.
 * 
 * @author Giovanni Farf\u00E1n Brice\u00F1o
 */

@Component("turnoTask")
@Scope(BeanDefinition.SCOPE_PROTOTYPE)
public class CjCRTurnoTask extends CjCRTurnadorTask {
    
    private final CjCRTurnoBehavior turnoBehavior;
    
    public CjCRTurnoTask(CjCRTurnoBehavior turnoBehavior) {
        this.turnoBehavior = turnoBehavior;
    }
    
    @Override
    public Boolean call() throws Exception {
        CjCRTurno turno;
        
        try {
            esperar(turnadorConfig.getFactorTurnoMin(),
                    turnadorConfig.getFactorTurnoMax());
            
            if(turnoBehavior.getBehavior() != CjCRSwarmStatus.TURNO_BEHAVIOR_VIRTUAL) {
                turno = turnosServiceClient.generarTurno(turnoBehavior.getTurno());
                
                if(turno.getFecha() > 0) {
                    log.info(String.format("Turno generado - idUnidadNegocio: %d, fecha: %d, idTurno: %d", 
                            turno.getIdUnidadNegocio(), turno.getFecha(), turno.getIdTurno()));
                    
                    turnoBehavior.setTurno(turno);
                } else {
                    return false;
                }
            }
            
            return true;
        } catch(Exception ex) {
            log.error("Error en tarea de turno", ex);
            
            throw ex;
        }
    }
}
