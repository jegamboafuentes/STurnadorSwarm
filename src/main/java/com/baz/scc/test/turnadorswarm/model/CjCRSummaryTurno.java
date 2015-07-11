package com.baz.scc.test.turnadorswarm.model;

import com.baz.scc.commons.model.CjCRSummary;


/**
 * <br><br>Copyright 2013 Banco Azteca. Todos los derechos reservados.
 * 
 * @author Giovanni Farf\u00E1n Brice\u00F1o
 */

public class CjCRSummaryTurno extends CjCRSummary {
    
    private CjCRTurno turno;
    
    public CjCRTurno getTurno() {
        return turno;
    }
    
    public void setTurno(CjCRTurno turno) {
        this.turno = turno;
    }
}
