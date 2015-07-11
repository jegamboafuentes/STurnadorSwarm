package com.baz.scc.test.turnadorswarm.model;

import java.util.ArrayList;
import java.util.List;


/**
 * <br><br>Copyright 2013 Banco Azteca. Todos los derechos reservados.
 * 
 * @author Giovanni Farf\u00E1n Brice\u00F1o
 */

public class CjCREmpleadoPool {
    
    private final List<CjCRCualidad> cualidades;
    
    private String noEmpleado;
    
    public CjCREmpleadoPool() {
        cualidades = new ArrayList<CjCRCualidad>();
    }
    
    public String getNoEmpleado() {
        return noEmpleado;
    }
    
    public void setNoEmpleado(String noEmpleado) {
        this.noEmpleado = noEmpleado;
    }
    
    public List<CjCRCualidad> getCualidades() {
        return cualidades;
    }
}
