package com.baz.scc.test.turnadorswarm.model;


/**
 * <br><br>Copyright 2013 Banco Azteca. Todos los derechos reservados.
 * 
 * @author Giovanni Farf\u00E1n Brice\u00F1o
 */

public class CjCRTurnoBehavior {
   
    private CjCRTurno turno;
    private int behavior;
    private int status;
    private int cola;
    
    public CjCRTurno getTurno() {
        return turno;
    }
    
    public void setTurno(CjCRTurno turno) {
        this.turno = turno;
    }    
    
    public int getBehavior() {
        return behavior;
    }
    
    public void setBehavior(int behavior) {
        this.behavior = behavior;
    }
    
    public int getStatus() {
        return status;
    }
    
    public void setStatus(int status) {
        this.status = status;
    }
    
    public int getCola() {
        return cola;
    }
    
    public void setCola(int cola) {
        this.cola = cola;
    }
}
