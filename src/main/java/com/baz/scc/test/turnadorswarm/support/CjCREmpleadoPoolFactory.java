package com.baz.scc.test.turnadorswarm.support;

import com.baz.scc.test.turnadorswarm.model.CjCREmpleadoPool;
import org.springframework.stereotype.Component;


/**
 * <br><br>Copyright 2013 Banco Azteca. Todos los derechos reservados.
 * 
 * @author Giovanni Farf\u00E1n Brice\u00F1o
 */

@Component
public class CjCREmpleadoPoolFactory {
    
    public CjCREmpleadoPool get(String noEmpleado) {
        CjCREmpleadoPool empleadoPool = new CjCREmpleadoPool();
        
        empleadoPool.setNoEmpleado(noEmpleado);
        
        return empleadoPool;
    }
}
