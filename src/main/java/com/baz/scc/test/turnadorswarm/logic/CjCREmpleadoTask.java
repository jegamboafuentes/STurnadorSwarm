package com.baz.scc.test.turnadorswarm.logic;

import com.baz.scc.commons.model.CjCRSummary;
import com.baz.scc.commons.util.CjCRUtils;
import com.baz.scc.test.turnadorswarm.logic.CjCREmpleadoFlowChain.StatusFlow;
import com.baz.scc.test.turnadorswarm.model.CjCREmpleadoPool;
import com.baz.scc.test.turnadorswarm.model.CjCRSummaryTurno;
import com.baz.scc.test.turnadorswarm.model.CjCRTurno;
import com.baz.scc.test.turnadorswarm.model.CjCRTurnoBehavior;
import com.baz.scc.test.turnadorswarm.service.CjCRPoolServiceClient;
import com.baz.scc.test.turnadorswarm.support.CjCRSwarmStatus;
import java.util.ArrayList;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.config.BeanDefinition;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;


/**
 * <br><br>Copyright 2013 Banco Azteca. Todos los derechos reservados.
 * 
 * @author Giovanni Farf\u00E1n Brice\u00F1o
 */

@Component("empleadoTask")
@Scope(BeanDefinition.SCOPE_PROTOTYPE)
public class CjCREmpleadoTask extends CjCRTurnadorTask {

    @Autowired
    private CjCRPoolServiceClient poolServiceClient;
    
    @Autowired
    private CjCREmpleadoFlow empleadoFlow;
    
    private final List<CjCRTurnoBehavior> atendidos;
    private final CjCREmpleadoPool empleado;
    private final int idUnidadNegocio;
    int i = 0;
    
    public CjCREmpleadoTask(int idUnidadNegocio, CjCREmpleadoPool empleado) {
        this.atendidos = new ArrayList<CjCRTurnoBehavior>();
        this.idUnidadNegocio = idUnidadNegocio;
        this.empleado = empleado;
    }
        
    @Override
    public Boolean call() throws Exception {
        try {
            CjCRSummary summary = poolServiceClient.setNoDisponible(
                    idUnidadNegocio, empleado);
            log.info("Empece");
            CjCRSummary summary2 = poolServiceClient.setDisponible(idUnidadNegocio, empleado);
            log.info("Termine");
            CjCRTurnoBehavior turnoBehavior;
            StatusFlow statusFlow;

            while(summary.isComplete() 
                    && turnadorContext.hasTurnoPendiente(idUnidadNegocio)) {
                turnoBehavior = turnadorContext.getTurnoBehavior(idUnidadNegocio);
                statusFlow = empleadoFlow.processTurno(this, turnoBehavior);
                
                if(statusFlow.getComplete()) {
                    atendidos.add(turnoBehavior);
                    turnadorContext.consumeTurno(idUnidadNegocio, turnoBehavior);
                } else if(turnoBehavior != null) {
                    turnadorContext.addTurnoBehavior(turnoBehavior, statusFlow.getUnsorted());
                }
                
//                log.info("Sigo en el while :(" + i);
//                i++;
            }

            finalizeTask();

            return true;
        } catch(Exception ex) {
            log.error("Error en tarea de empleado", ex);
            
            return false;
        }
    }
    
    public boolean setDisponible() {
        CjCRSummary summary = poolServiceClient.setDisponible(idUnidadNegocio, empleado);
        
        esperar(turnadorConfig.getFactorDisponibleMin(),
                turnadorConfig.getFactorDisponibleMax());
        
        return summary.isComplete();
    }
    
    public boolean validateAsignacion(CjCRTurno turno, boolean iniciado) {
        CjCREmpleadoPool empleadoAsignado;
        
        if(turno != null) {
            empleadoAsignado = turno.getEmpleado();
            
            if(empleadoAsignado != null 
                    && empleadoAsignado.getNoEmpleado().equals(empleado.getNoEmpleado())) {
                if(iniciado) {
                    log.info(String.format(
                            "Turno asignado - idUnidadNegocio: %d, noEmpleado: %s, fecha: %d, idTurno: %d", 
                            idUnidadNegocio, empleado.getNoEmpleado(), turno.getFecha(), turno.getIdTurno()));

                    esperar(turnadorConfig.getFactorAsignacionMin(), 
                            turnadorConfig.getFactorAsignacionMax());
                }
                
                return true;
            }
        }
        
        return false;
    }
    
    public boolean posponerTurno(CjCRTurno turno) {
        CjCRSummary summary;
        
        log.info(String.format(
                "Posponiendo turno - idUnidadNegocio: %d, noEmpleado: %s, fecha: %d, idTurno: %d", 
                idUnidadNegocio, empleado.getNoEmpleado(), turno.getFecha(), turno.getIdTurno()));
        
        summary = turnosServiceClient.posponerTurno(empleado, turno);

        esperar(turnadorConfig.getFactorDisponibleMin(), 
                turnadorConfig.getFactorDisponibleMax());
        
        if(!summary.isComplete()) {
            log.warn(String.format(
                    "Conflicto en actualizaci\u00F3n de estado de turno - idUnidadNegocio: %d, noEmpleado: %s, fecha: %d, idTurno: %d - %s", 
                    idUnidadNegocio, empleado.getNoEmpleado(), turno.getFecha(), turno.getIdTurno(), summary.getDetail()));
        }
        
        return summary.isComplete();
    }
    
    public boolean cancelarTurno(CjCRTurno turno) {
        CjCRSummary summary;
        
        log.info(String.format(
                "Cancelando turno - idUnidadNegocio: %d, noEmpleado: %s, fecha: %d, idTurno: %d", 
                idUnidadNegocio, empleado.getNoEmpleado(), turno.getFecha(), turno.getIdTurno()));
        
        summary = turnosServiceClient.cancelarTurno(empleado, turno);

        esperar(turnadorConfig.getFactorDisponibleMin(), 
                turnadorConfig.getFactorDisponibleMax());
        
        if(!summary.isComplete()) {
            log.warn(String.format(
                    "Conflicto en cancelaci\u00F3n de turno - idUnidadNegocio: %d, noEmpleado: %s, fecha: %d, idTurno: %d - %s", 
                    idUnidadNegocio, empleado.getNoEmpleado(), turno.getFecha(), turno.getIdTurno(), summary.getDetail()));
        }
        
        return summary.isComplete();
    }
    
    public boolean atenderTurno(CjCRTurno turno, boolean apropiacion) {
        CjCRSummary summary = turnosServiceClient.atenderTurno(empleado, turno);
        
        if(!apropiacion) {
            log.info(String.format(
                    "Atendiendo turno - idUnidadNegocio: %d, noEmpleado: %s, fecha: %d, idTurno: %d", 
                    idUnidadNegocio, empleado.getNoEmpleado(), turno.getFecha(), turno.getIdTurno()));

            esperar(turnadorConfig.getFactorAtencionMin(), 
                    turnadorConfig.getFactorAtencionMax());
        } else {
            if(summary.getStatus() == CjCRSwarmStatus.TURNO_ASIGNADO_OTRO_EMPLEADO) {
                log.info(String.format(
                    "Estado de atenci\u00F3n de turno - idUnidadNegocio: %d, fecha: %d - %s", 
                    idUnidadNegocio, turno.getFecha(), summary.getDetail()));
                
                summary.setComplete(true);
            }
        }
        
        if(!summary.isComplete()) {
            log.warn(String.format(
                    "Conflicto en atenci\u00F3n de turno - idUnidadNegocio: %d, noEmpleado: %s, fecha: %d, idTurno: %d - %s", 
                    idUnidadNegocio, empleado.getNoEmpleado(), turno.getFecha(), turno.getIdTurno(), summary.getDetail()));
        }
        
        return summary.isComplete();
    }
    
    public CjCRTurno atenderTurnoVirtual(CjCRTurno turno) {
        CjCRSummaryTurno summaryTurno;
        
        turnadorContext.activarPlanContingencia(empleado);
        
        summaryTurno = turnosServiceClient.atenderTurnoVirtual(empleado, turno);
        
        if(summaryTurno.isComplete()) {
            log.info(String.format(
                    "Atendiendo turno virtual - idUnidadNegocio: %d, noEmpleado: %s, fecha: %d, idTurno: %d", 
                    idUnidadNegocio, empleado.getNoEmpleado(), turno.getFecha(), turno.getIdTurno()));
            
            esperar(turnadorConfig.getFactorAtencionMin(), 
                    turnadorConfig.getFactorAtencionMax());
            
            return summaryTurno.getTurno();
        } else {
            log.warn(String.format(
                "Conflicto en atenci\u00F3n de turno virtual - idUnidadNegocio: %d, noEmpleado: %s - %s", 
                idUnidadNegocio, empleado.getNoEmpleado(), summaryTurno.getDetail()));
            
            turnadorContext.desactivarPlanContingencia(empleado);
            
            return null;
        }
    }
    
    public boolean apropiarTurno(CjCRTurno turno) {
        CjCRSummary summary;
        
        log.info(String.format(
                "Apropiando turno - idUnidadNegocio: %d, noEmpleado: %s, fecha: %d, idTurno: %d", 
                idUnidadNegocio, empleado.getNoEmpleado(), turno.getFecha(), turno.getIdTurno()));
        
        summary = turnosServiceClient.apropiarTurno(empleado, turno);

        esperar(turnadorConfig.getFactorDisponibleMin(), 
                turnadorConfig.getFactorDisponibleMax());
        
        if(!summary.isComplete()) {
            log.warn(String.format(
                    "Conflicto en apropiaci\u00F3n de turno - idUnidadNegocio: %d, noEmpleado: %s, fecha: %d, idTurno: %d - %s", 
                    idUnidadNegocio, empleado.getNoEmpleado(), turno.getFecha(), turno.getIdTurno(), summary.getDetail()));
        }
        
        return summary.isComplete();
    }
    
    public boolean finalizarTurno(CjCRTurno turno, boolean planContingencia) {
        CjCRSummary summary;
        
        log.info(String.format(
                "Finalizando turno - idUnidadNegocio: %d, noEmpleado: %s, fecha: %d, idTurno: %d", 
                idUnidadNegocio, empleado.getNoEmpleado(), turno.getFecha(), turno.getIdTurno()));
        
        summary = turnosServiceClient.finalizarTurno(empleado, turno);
        
        if(!summary.isComplete()) {
            log.warn(String.format(
                "Conflicto en finalizac\u00F3n de turno - idUnidadNegocio: %d, noEmpleado: %s, fecha: %d, idTurno: %d - %s", 
                idUnidadNegocio, empleado.getNoEmpleado(), turno.getFecha(), turno.getIdTurno(), summary.getDetail()));
        }
        
        if(planContingencia) {
            turnadorContext.desactivarPlanContingencia(empleado);
        }
        
        return summary.isComplete();
    }
    
    public boolean validateCaducado(CjCRTurno turno) {
        boolean bCaducado = true;
        
        if(turnadorContext.isTurnoCadudado(turno)) {
            turno = turnosServiceClient.getTurno(turno);
            bCaducado = (turno.getEstado() == CjCRSwarmStatus.TURNO_CADUCADO);
            
            if(bCaducado) {
                log.info(String.format(
                        "Turno caducado - idUnidadNegocio: %d, noEmpleado: %s, fecha: %d, idTurno: %d", 
                        idUnidadNegocio, empleado.getNoEmpleado(), turno.getFecha(), turno.getIdTurno()));
            }
        }
        
        return bCaducado;
    }
    
    private void finalizeTask() {
        StringBuilder result = new StringBuilder();
        
        for(CjCRTurnoBehavior turnoBehavior : atendidos) {
            result.append(turnoBehavior.getBehavior());
            result.append("-");
            result.append(turnoBehavior.getTurno().getIdTurno());
            result.append(", ");
        }
        
        result = CjCRUtils.trimComma(result);
        
        log.info(String.format("Turnos atendidos - idUnidadNegocio: %d, noEmpleado: %s, turnos: [%s]", 
                idUnidadNegocio, empleado.getNoEmpleado(), result.toString()));
    }
}
