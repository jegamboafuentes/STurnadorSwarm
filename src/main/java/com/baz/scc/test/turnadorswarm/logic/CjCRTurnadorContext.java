package com.baz.scc.test.turnadorswarm.logic;

import com.baz.scc.commons.model.CjCRSummary;
import com.baz.scc.test.turnadorswarm.model.CjCREmpleadoPool;
import com.baz.scc.test.turnadorswarm.model.CjCRTurnadorConfig;
import com.baz.scc.test.turnadorswarm.model.CjCRTurno;
import com.baz.scc.test.turnadorswarm.model.CjCRTurnoBehavior;
import com.baz.scc.test.turnadorswarm.model.CjCRTurnoBehaviorConfig;
import com.baz.scc.test.turnadorswarm.service.CjCRTurnadorServiceClient;
import com.baz.scc.test.turnadorswarm.service.CjCRTurnosServiceClient;
import com.baz.scc.test.turnadorswarm.support.CjCRUnidadCola;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Random;
import java.util.Set;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;


/**
 * <br><br>Copyright 2013 Banco Azteca. Todos los derechos reservados.
 * 
 * @author Giovanni Farf\u00E1n Brice\u00F1o
 */

@Component
public class CjCRTurnadorContext {
    
    private final Map<Integer, Map<String, CjCREmpleadoPool>> empleadosUnidad;
    private final Map<Integer, CjCRUnidadCola> unidadesCola;
    
    @Autowired
    private CjCRTurnadorConfig turnadorConfig;
    
    @Autowired
    private CjCRTurnosServiceClient turnosServiceClient;
    
    @Autowired
    private CjCRTurnadorServiceClient turnadorServiceClient;
    
    private boolean planContingencia;
    
    public CjCRTurnadorContext() {
        empleadosUnidad = new HashMap<Integer, Map<String, CjCREmpleadoPool>>();
        unidadesCola = new HashMap<Integer, CjCRUnidadCola>();
    }
    
    public Map<Integer, Map<String, CjCREmpleadoPool>> getEmpleadosUnidad() {
        return empleadosUnidad;
    }
    
    public Set<Integer> getIdsUnidadesNegocio() {
        return unidadesCola.keySet();
    }
    
    public void addUnidadNegocio(int idUnidadNegocio) {
        CjCRUnidadCola unidadCola = new CjCRUnidadCola(idUnidadNegocio);
        
        unidadesCola.put(idUnidadNegocio, unidadCola);
    }
    
    public void addTurnoBehavior(CjCRTurnoBehavior turnoBehavior, boolean unsorted) {
        CjCRTurno turno = turnoBehavior.getTurno();
        CjCRUnidadCola unidadCola;
        
        if(turno != null && turno.getIdUnidadNegocio() > 0) {
            unidadCola = unidadesCola.get(turno.getIdUnidadNegocio());
            
            if(unidadCola != null) {
                if(unsorted) {
                    unidadCola.setTurnoUnsorted(turnoBehavior);
                } else {
                    unidadCola.setTurno(turnoBehavior);
                }
            }
        }
    }
    
    public CjCRTurnoBehavior getTurnoBehavior(int idUnidadNegocio) {
        CjCRUnidadCola unidadCola = unidadesCola.get(idUnidadNegocio);
        CjCRTurnoBehavior turnoBehavior = null;
        CjCRTurno turno;
        
        if(unidadCola != null) {
            turnoBehavior = unidadCola.getTurno();
            
            if(turnoBehavior != null) {
                turno = turnosServiceClient.getTurno(turnoBehavior.getTurno());

                if(turno != null) {
                    turnoBehavior.setTurno(turno);
                }
            }
        }
        
        return turnoBehavior;
    }
    
    public void addEmpleado(int idUnidadNegocio, CjCREmpleadoPool empleado) {
        Map<String, CjCREmpleadoPool> empleados = empleadosUnidad.get(idUnidadNegocio);
        
        if(empleados == null) {
            empleados = new HashMap<String, CjCREmpleadoPool>();
            
            empleadosUnidad.put(idUnidadNegocio, empleados);
        }
        
        empleados.put(empleado.getNoEmpleado(), empleado);
    }
    
    public boolean hasTurnoPendiente(int idUnidadNegocio) {
        CjCRUnidadCola unidadCola = unidadesCola.get(idUnidadNegocio);
        
        if(unidadCola != null) {
            return unidadCola.hasTurnos();
        }
        
        return false;
    }
    
    public void consumeTurno(int idUnidadNegocio, CjCRTurnoBehavior turnoBehavior) {
        CjCRUnidadCola unidadCola = unidadesCola.get(idUnidadNegocio);
        
        if(unidadCola != null) {
            unidadCola.consumeTurno(turnoBehavior);
        }
    }
    
    public synchronized void activarPlanContingencia(CjCREmpleadoPool empleado) {
        CjCRSummary summary;
        
        if(!planContingencia) {
            summary = turnadorServiceClient.activarContingencia(empleado);
            planContingencia = summary.isComplete();
        }
    }
    
    public synchronized void desactivarPlanContingencia(CjCREmpleadoPool empleado) {
        CjCRSummary summary;
        
        if(planContingencia) {
            summary = turnadorServiceClient.desactivarContingencia(empleado);
            planContingencia = !summary.isComplete();
        }
    }
    
    public boolean isTurnoCadudado(CjCRTurno turno) {
        CjCRUnidadCola unidadCola = unidadesCola.get(turno.getIdUnidadNegocio());
        List<Integer> turnos;
        int index;
        
        if(unidadCola != null) {
            turnos = unidadCola.getTurnos();
            index = turnos.indexOf(turno.getIdTurno());
            
            return (turnadorConfig.getTurnosCaducidad() < turnos.size()
                    && index >= turnadorConfig.getTurnosCaducidad());
        }
        
        return false;
    }
    
    public int getBehavior() {
        CjCRTurnoBehaviorConfig turnoConfig;
        List<Integer> idsBehavior;
        Random random = new Random();
        int assigned;
        int index;
        
        cleanupTurnosConfig();
        
        idsBehavior = new ArrayList<Integer>(turnadorConfig.getTurnosConfig().keySet());
        
        if(idsBehavior.size() > 0) {
            index = idsBehavior.get(random.nextInt(idsBehavior.size()));
            turnoConfig = turnadorConfig.getTurnosConfig().get(index);
            assigned = turnoConfig.getAssigned() - 1;
                
            turnoConfig.setAssigned(assigned);
            
            return turnoConfig.getId();
        } else {
            return -1;
        }
    }
    
    private void cleanupTurnosConfig() {
        Iterator<Map.Entry<Integer, CjCRTurnoBehaviorConfig>> iterator = 
                turnadorConfig.getTurnosConfig().entrySet().iterator();        
        Map.Entry<Integer, CjCRTurnoBehaviorConfig> entry;
        
        while(iterator.hasNext()) {
            entry = iterator.next();
            
            if(entry.getValue().getAssigned() < 1) {
                iterator.remove();
            }
        }
    }
}
