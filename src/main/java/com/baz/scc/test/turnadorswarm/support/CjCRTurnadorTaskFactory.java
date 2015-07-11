package com.baz.scc.test.turnadorswarm.support;

import com.baz.scc.commons.util.CjCRSpringContext;
import com.baz.scc.test.turnadorswarm.logic.CjCREmpleadoTask;
import com.baz.scc.test.turnadorswarm.logic.CjCRTurnadorContext;
import com.baz.scc.test.turnadorswarm.logic.CjCRTurnoTask;
import com.baz.scc.test.turnadorswarm.model.CjCREmpleadoPool;
import com.baz.scc.test.turnadorswarm.model.CjCRTurno;
import com.baz.scc.test.turnadorswarm.model.CjCRTurnoBehavior;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;


/**
 * <br><br>Copyright 2013 Banco Azteca. Todos los derechos reservados.
 * 
 * @author Giovanni Farf\u00E1n Brice\u00F1o
 */

@Component
public class CjCRTurnadorTaskFactory {

    private static final String TURNO_TASK_BEAN = "turnoTask";
    private static final String EMPLEADO_TASK_BEAN = "empleadoTask";
    
    @Autowired
    private CjCRTurnadorContext turnadorContext;
    
    @Autowired
    private CjCRTurnoFactory turnoFactory;
    
    public CjCRTurnoTask getTurnoTask() {
        int behavior = turnadorContext.getBehavior();
        CjCRTurnoBehavior turnoBehavior;
        CjCRTurnoTask turnoTask = null;
        CjCRTurno turno;
        
        if(behavior > -1) {
            turnoBehavior = new CjCRTurnoBehavior();
            turno = turnoFactory.get();
            
            turnoBehavior.setStatus(CjCRSwarmStatus.TURNO_FLOW_INICIADO);
            turnoBehavior.setBehavior(behavior);
            turnoBehavior.setTurno(turno);
            
            turnadorContext.addTurnoBehavior(turnoBehavior, false);
            
            turnoTask = CjCRSpringContext.getBean(TURNO_TASK_BEAN, turnoBehavior);
        }
        
        return turnoTask;
    }
    
    public CjCREmpleadoTask getEmpleadoTask(int idUnidadNegocio, CjCREmpleadoPool empleado) {
        CjCREmpleadoTask empleadoTask = CjCRSpringContext.getBean(EMPLEADO_TASK_BEAN,
                idUnidadNegocio, empleado);
        
        return empleadoTask;
    }
    
    public List<CjCREmpleadoTask> getEmpleadoTasks() {
        List<CjCREmpleadoTask> tasks = new ArrayList<CjCREmpleadoTask>();
        Map<Integer, Map<String, CjCREmpleadoPool>> empleadosUnidad = 
                turnadorContext.getEmpleadosUnidad();
        CjCREmpleadoPool empleadoPool;
        CjCREmpleadoTask task;
                
        for(Map.Entry<Integer, Map<String, CjCREmpleadoPool>> entryUnidad 
                : empleadosUnidad.entrySet()) {            
            for(Map.Entry<String, CjCREmpleadoPool> entryEmpleado 
                    : entryUnidad.getValue().entrySet()) {
                empleadoPool = entryEmpleado.getValue();                
                task = getEmpleadoTask(entryUnidad.getKey(), empleadoPool);
                
                tasks.add(task);
            }
        }
        
        return tasks;
    }
}
