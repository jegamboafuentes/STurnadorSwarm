package com.baz.scc.test.turnadorswarm.logic;

import com.baz.scc.commons.support.CjCRProcessor;
import com.baz.scc.test.turnadorswarm.model.CjCRTurnadorConfig;
import com.baz.scc.test.turnadorswarm.support.CjCRTurnadorTaskFactory;
import java.util.ArrayList;
import java.util.List;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;


/** 
 * <br><br>Copyright 2013 Banco Azteca. Todos los derechos reservados.
 * 
 * @author Giovanni Farf\u00E1n Brice\u00F1o
 */

@Component
public class CjCRTurnadorProcess {
    
    private static final Logger log = Logger.getLogger(CjCRTurnadorProcess.class);
    
    @Autowired
    private CjCRTurnadorTaskFactory turnadorTaskFactory;
    
    @Autowired
    private CjCRTurnadorConfig turnadorConfig;
    
    private List<CjCRTurnadorTask> turnadorTasks;
    
    private void configure() {
        List<CjCREmpleadoTask> empleadosTask = turnadorTaskFactory.getEmpleadoTasks();
        CjCRTurnoTask turnoTask;
        CjCREmpleadoTask empleadoTask;
        
        turnadorTasks = new ArrayList<CjCRTurnadorTask>();
        
        log.info("Configurando tareas de turnador");
        
        for(int i = 0; i < turnadorConfig.getNoTurnos(); i++) {
            turnoTask = turnadorTaskFactory.getTurnoTask();
            
            if(turnoTask != null) {
                turnadorTasks.add(turnoTask);
            }
            
            if(empleadosTask.size() > 0) {
                empleadoTask = empleadosTask.remove(0);
                
                turnadorTasks.add(empleadoTask);
            }
        }
        
        turnadorTasks.addAll(empleadosTask);
    }
    
    public void process() {
        CjCRProcessor<CjCRTurnadorTask, Boolean> processor = 
                new CjCRProcessor<CjCRTurnadorTask, Boolean>();
        
        configure();
        
        processor.setNoTasks(50);
        
        log.info("Iniciando tareas de turnador");
        
        processor.processBackground(turnadorTasks);
    }
}
