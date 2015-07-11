package com.baz.scc.test.turnadorswarm.support;

import com.baz.scc.test.turnadorswarm.model.CjCRUnidadNegocio;
import java.util.Random;
import org.springframework.stereotype.Component;


/**
 * <br><br>Copyright 2013 Banco Azteca. Todos los derechos reservados.
 * 
 * @author Giovanni Farf\u00E1n Brice\u00F1o
 */

@Component
public class CjCRUnidadNegocioFactory {
    
    private static final int NO_UNIDADES_NEGOCIO = 7;
    
    public CjCRUnidadNegocio get() {
        int idUnidadNegocio = getIdUnidadNegocio();
        
        return get(idUnidadNegocio);
    }
    
    public CjCRUnidadNegocio get(int idUnidadNegocio) {
        CjCRUnidadNegocio unidadNegocio = new CjCRUnidadNegocio();
        
        unidadNegocio.setIdUnidadNegocio(idUnidadNegocio);
        
        return unidadNegocio;
    }
    
    private int getIdUnidadNegocio() {
        Random random = new Random();
        
        return random.nextInt(NO_UNIDADES_NEGOCIO) + 1;
    }
}
