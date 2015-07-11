
package com.baz.scc.test.turnadorswarm.model;


/**
 * <br><br>Copyright 2013 Banco Azteca. Todos los derechos reservados.
 * 
 * @author Giovanni Farf\u00E1n Brice\u00F1o
 */

public class CjCRAutenticacionResponse {
    
    private CjCRMensajeRespuesta mensaje;
    private CjCRPropiedadesResponse propiedades;
    
    public CjCRMensajeRespuesta getMensaje() {
        return mensaje;
    }
    
    public void setMensaje(CjCRMensajeRespuesta mensaje) {
        this.mensaje = mensaje;
    }
    
    public CjCRPropiedadesResponse getPropiedades() {
        return propiedades;
    }
    
    public void setPropiedades(CjCRPropiedadesResponse propiedades) {
        this.propiedades = propiedades;
    }
}
