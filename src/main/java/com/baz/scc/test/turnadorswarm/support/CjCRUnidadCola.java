package com.baz.scc.test.turnadorswarm.support;

import com.baz.scc.test.turnadorswarm.model.CjCRTurno;
import com.baz.scc.test.turnadorswarm.model.CjCRTurnoBehavior;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Random;
import java.util.Set;


/**
 * <br><br>Copyright 2013 Banco Azteca. Todos los derechos reservados.
 * 
 * @author Giovanni Farf\u00E1n Brice\u00F1o
 */

public class CjCRUnidadCola {
   
    private final List<CjCRTurnoBehavior> cola;
    private final Set<CjCRTurnoBehavior> control;
    private final List<Integer> turnos;
    private final int idUnidadNegocio;
        
    public CjCRUnidadCola(int idUnidadNegocio) {
        this.cola = new ArrayList<CjCRTurnoBehavior>();
        this.turnos = new ArrayList<Integer>();
        this.control = new HashSet<CjCRTurnoBehavior>();
        this.idUnidadNegocio = idUnidadNegocio;
    }
        
    public int getIdUnidadNegocio() {
        return idUnidadNegocio;
    }    
    
    public void setTurno(CjCRTurnoBehavior turnoBehavior) {
        synchronized(cola) {
            setTurno(turnoBehavior, 0);
        }
    }
    
    public void setTurnoUnsorted(CjCRTurnoBehavior turnoBehavior) {
        Random random = new Random();
        int index;
        
        synchronized(cola) {
            index = random.nextInt(cola.size());

            setTurno(turnoBehavior, index);
        }
    }
    
    public CjCRTurnoBehavior getTurno() {
        synchronized(cola) {
            if(cola.size() > 0) {
                return cola.remove(0);
            }
        }
        
        return null;
    }
    
    public boolean hasTurnos() {
        return (control.size() > 0);
    }
    
    public void consumeTurno(CjCRTurnoBehavior turnoBehavior) {
        control.remove(turnoBehavior);
    }
    
    public List<Integer> getTurnos() {
        Collections.sort(turnos);
        
        return new ArrayList<Integer>(turnos);
    }
    
    private void setTurno(CjCRTurnoBehavior turnoBehavior, int index) {
        CjCRTurno turno;
        
        if(turnoBehavior != null) {
            turno = turnoBehavior.getTurno();
            
            if(cola.size() > 0) {
                cola.set(index, turnoBehavior);
            } else {
                cola.add(turnoBehavior);
            }

            if(turno.getIdTurno() > 0 && !turnos.contains(turno.getIdTurno())) {
                turnos.add(turno.getIdTurno());
            }

            control.add(turnoBehavior);
        }
    }
}
