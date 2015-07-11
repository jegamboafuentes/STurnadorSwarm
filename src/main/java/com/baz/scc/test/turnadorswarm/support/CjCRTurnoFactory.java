package com.baz.scc.test.turnadorswarm.support;

import com.baz.scc.test.turnadorswarm.logic.CjCRTurnadorContext;
import com.baz.scc.test.turnadorswarm.model.CjCRTurno;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;


/**
 * <br><br>Copyright 2013 Banco Azteca. Todos los derechos reservados.
 * 
 * @author Giovanni Farf\u00E1n Brice\u00F1o
 */

@Component
public class CjCRTurnoFactory {
    
    @Autowired
    private CjCRTurnadorContext turnadorContext;
    
    public CjCRTurno get() {
        CjCRTurno turno = new CjCRTurno();
        
        turno.setIdOrigen(getIdOrigen());
        turno.setIdUnidadNegocio(getIdUnidadNegocio());
        
        return turno;
    }
    
    public CjCRTurno get(int fecha, int idTurno) {
        CjCRTurno turno = new CjCRTurno();
        
        turno.setFecha(fecha);
        turno.setIdTurno(idTurno);
        
        return turno;
    }
    
    private int getIdUnidadNegocio() {
        List<Integer> idsUnidadNegocio = new ArrayList<Integer>(
                turnadorContext.getIdsUnidadesNegocio());
        Random random = new Random();
        int index = (random.nextInt(idsUnidadNegocio.size()));
        
        return idsUnidadNegocio.get(index);
    }
    
    private int getIdOrigen() {
        Random random = new Random();
        
        return (random.nextInt(2) + 1);
    }
}
