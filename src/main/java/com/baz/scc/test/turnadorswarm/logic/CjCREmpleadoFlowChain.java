package com.baz.scc.test.turnadorswarm.logic;

import com.baz.scc.test.turnadorswarm.model.CjCRTurno;
import com.baz.scc.test.turnadorswarm.model.CjCRTurnoBehavior;
import com.baz.scc.test.turnadorswarm.support.CjCRSwarmStatus;
import java.util.Random;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;


/**
 * <br><br>Copyright 2013 Banco Azteca. Todos los derechos reservados.
 * 
 * @author Giovanni Farf\u00E1n Brice\u00F1o
 */

public interface CjCREmpleadoFlowChain {

    public StatusFlow processTurno(CjCREmpleadoTask empleadoTask, 
            CjCRTurnoBehavior turnoBehavior);
    
    public static class StatusFlow {
        
        private boolean complete;
        private boolean unsorted;
                
        public boolean getComplete() {
            return complete;
        }
        
        public void setComplete(boolean complete) {
            this.complete = complete;
        }
        
        public boolean getUnsorted() {
            return unsorted;
        }
        
        public void setUnsorted(boolean unsorted) {
            this.unsorted = unsorted;
        }
    }
}

abstract class CjCREmpleadoFlowStep implements CjCREmpleadoFlowChain {
    
    protected boolean flowPosponer(CjCREmpleadoTask empleadoTask, 
            CjCRTurnoBehavior turnoBehavior) {
        boolean bOperation = empleadoTask.validateAsignacion(turnoBehavior.getTurno(), true);
        Random random = new Random();
        
        if(bOperation) {
            if(random.nextBoolean()){
                bOperation = empleadoTask.atenderTurno(turnoBehavior.getTurno(), false);
            }
            
            bOperation = (bOperation && empleadoTask.posponerTurno(turnoBehavior.getTurno()));
            
            turnoBehavior.setStatus(CjCRSwarmStatus.TURNO_FLOW_REVISION);
        }
        
        return bOperation;
    }
    
    protected abstract boolean flowRevision(CjCREmpleadoTask empleadoTask, 
            CjCRTurnoBehavior turnoBehavior);
}

@Component
class CjCREmpleadoFlow implements CjCREmpleadoFlowChain {
    
    @Autowired    
    private CjCREmpleadoFlowBasico succesor;
    
    @Override
    public StatusFlow processTurno(CjCREmpleadoTask empleadoTask, 
            CjCRTurnoBehavior turnoBehavior) {
        StatusFlow statusFlow = new StatusFlow();
        
        if(turnoBehavior != null) {
            return succesor.processTurno(empleadoTask, turnoBehavior);
        }
        
        return statusFlow;
    }
}

@Component
class CjCREmpleadoFlowBasico implements CjCREmpleadoFlowChain {
    
    @Autowired    
    private CjCREmpleadoFlowPospuesto succesor;
    
    @Override
    public StatusFlow processTurno(CjCREmpleadoTask empleadoTask, 
            CjCRTurnoBehavior turnoBehavior) {
        StatusFlow statusFlow = new StatusFlow();
        boolean bOperation;
        
        if(turnoBehavior.getBehavior() == CjCRSwarmStatus.TURNO_BEHAVIOR_BASICO) {
            bOperation = empleadoTask.validateAsignacion(turnoBehavior.getTurno(), true);            
            bOperation = (bOperation && 
                    empleadoTask.atenderTurno(turnoBehavior.getTurno(), false));
            bOperation = (bOperation && 
                    empleadoTask.finalizarTurno(turnoBehavior.getTurno(), false));
            
            statusFlow.setComplete(bOperation);
            
            return statusFlow;
        }
        
        return succesor.processTurno(empleadoTask, turnoBehavior);
    }
}

@Component
class CjCREmpleadoFlowPospuesto extends CjCREmpleadoFlowStep {
    
    @Autowired    
    private CjCREmpleadoFlowApropiado succesor;
    
    @Override
    public StatusFlow processTurno(CjCREmpleadoTask empleadoTask, 
            CjCRTurnoBehavior turnoBehavior) {
        StatusFlow statusFlow = new StatusFlow();
        boolean bOperation = true;
        
        if(turnoBehavior.getBehavior() == CjCRSwarmStatus.TURNO_BEHAVIOR_POSPUESTO) {
            switch(turnoBehavior.getStatus()) {
                case CjCRSwarmStatus.TURNO_FLOW_INICIADO:
                    bOperation = flowPosponer(empleadoTask, turnoBehavior);
                    break;
                case CjCRSwarmStatus.TURNO_FLOW_REVISION:
                    bOperation = flowRevision(empleadoTask, turnoBehavior);
                    break;
            }
            
            statusFlow.setComplete(bOperation);
            statusFlow.setUnsorted(true);
            
            return statusFlow;
        }
        
        return succesor.processTurno(empleadoTask, turnoBehavior);        
    }
        
    @Override
    protected boolean flowRevision(CjCREmpleadoTask empleadoTask, 
            CjCRTurnoBehavior turnoBehavior) {
        boolean bOperation = empleadoTask.validateAsignacion(turnoBehavior.getTurno(), false);
        
        if(bOperation) {
            bOperation = empleadoTask.atenderTurno(turnoBehavior.getTurno(), false);
            bOperation = (bOperation && 
                    empleadoTask.finalizarTurno(turnoBehavior.getTurno(), false));
        } else {
            turnoBehavior.setBehavior(CjCRSwarmStatus.TURNO_BEHAVIOR_APROPIADO);
            turnoBehavior.setStatus(CjCRSwarmStatus.TURNO_FLOW_FINALIZACION);
            
            bOperation = true;
        }
        
        return bOperation;
    }
}

@Component
class CjCREmpleadoFlowApropiado implements CjCREmpleadoFlowChain {
    
    @Autowired    
    private CjCREmpleadoFlowCancelado succesor;
    
    @Override
    public StatusFlow processTurno(CjCREmpleadoTask empleadoTask, 
            CjCRTurnoBehavior turnoBehavior) {
        StatusFlow statusFlow = new StatusFlow();
        boolean bOperation;
        
        if(turnoBehavior.getBehavior() == CjCRSwarmStatus.TURNO_BEHAVIOR_APROPIADO) {
            bOperation = empleadoTask.validateAsignacion(turnoBehavior.getTurno(), 
                    (turnoBehavior.getStatus() != CjCRSwarmStatus.TURNO_FLOW_FINALIZACION));
            
            if(!bOperation) {
                bOperation = empleadoTask.atenderTurno(turnoBehavior.getTurno(), true);
                bOperation = (bOperation && 
                        empleadoTask.apropiarTurno(turnoBehavior.getTurno()));
                bOperation = (bOperation && 
                        empleadoTask.finalizarTurno(turnoBehavior.getTurno(), false));
            } else {
                bOperation = false;
            }
            
            statusFlow.setComplete(bOperation);
            
            return statusFlow;
        }
        
        return succesor.processTurno(empleadoTask, turnoBehavior);        
    }
}

@Component
class CjCREmpleadoFlowCancelado implements CjCREmpleadoFlowChain {
    
    @Autowired    
    private CjCREmpleadoFlowVirtual succesor;
    
    @Override
    public StatusFlow processTurno(CjCREmpleadoTask empleadoTask, 
            CjCRTurnoBehavior turnoBehavior) {
        StatusFlow statusFlow = new StatusFlow();
        boolean bOperation;
        
        if(turnoBehavior.getBehavior() == CjCRSwarmStatus.TURNO_BEHAVIOR_CANCELADO) {
            bOperation = empleadoTask.validateAsignacion(turnoBehavior.getTurno(), true);
            bOperation = (bOperation && empleadoTask.cancelarTurno(turnoBehavior.getTurno()));
            
            statusFlow.setComplete(bOperation);
            
            return statusFlow;
        }
        
        return succesor.processTurno(empleadoTask, turnoBehavior);
    }
}

@Component
class CjCREmpleadoFlowVirtual implements CjCREmpleadoFlowChain {
    
    @Autowired    
    private CjCREmpleadoFlowCaducado succesor;
    
    @Override
    public StatusFlow processTurno(CjCREmpleadoTask empleadoTask, 
            CjCRTurnoBehavior turnoBehavior) {
        StatusFlow statusFlow = new StatusFlow();
        boolean bOperation = true;
        CjCRTurno turno;
        
        if(turnoBehavior.getBehavior() == CjCRSwarmStatus.TURNO_BEHAVIOR_VIRTUAL) {
            turno = empleadoTask.atenderTurnoVirtual(turnoBehavior.getTurno());
            
            if(turno != null) {
                bOperation = empleadoTask.finalizarTurno(turno, true);
            }
            
            statusFlow.setComplete(bOperation);
            
            return statusFlow;
        }
        
        return succesor.processTurno(empleadoTask, turnoBehavior);        
    }
}

@Component
class CjCREmpleadoFlowCaducado extends CjCREmpleadoFlowStep {
    
    @Override
    public StatusFlow processTurno(CjCREmpleadoTask empleadoTask, 
            CjCRTurnoBehavior turnoBehavior) {
        StatusFlow statusFlow = new StatusFlow();
        boolean bOperation = true;
        
        if(turnoBehavior.getBehavior() == CjCRSwarmStatus.TURNO_BEHAVIOR_CADUCADO) {
            switch(turnoBehavior.getStatus()) {
                case CjCRSwarmStatus.TURNO_FLOW_INICIADO:
                    bOperation = flowPosponer(empleadoTask, turnoBehavior);
                    break;
                case CjCRSwarmStatus.TURNO_FLOW_REVISION:
                    bOperation = flowRevision(empleadoTask, turnoBehavior);
                    break;
            }
            
            statusFlow.setComplete(bOperation);
            statusFlow.setUnsorted(true);
        }
        
        return statusFlow;
    }
    
    @Override
    protected boolean flowRevision(CjCREmpleadoTask empleadoTask, 
            CjCRTurnoBehavior turnoBehavior) {
        boolean bOperation = empleadoTask.validateCaducado(turnoBehavior.getTurno());
        
        if(bOperation) {
            turnoBehavior.setStatus(CjCRSwarmStatus.TURNO_FLOW_FINALIZACION);
        }
        
        return bOperation;
    }
}
