package com.baz.scc.test.turnadorswarm.model;


/**
 * <br><br>Copyright 2013 Banco Azteca. Todos los derechos reservados.
 * 
 * @author Giovanni Farf\u00E1n Brice\u00F1o
 */

public class CjCRPropiedadesRequest {

    private String nombreEstacion;
    private String numeroIp;
    private int tipoEstacion;
        
    public String getNombreEstacion() {
        return nombreEstacion;
    }
    
    public void setNombreEstacion(String nombreEstacion) {
        this.nombreEstacion = nombreEstacion;
    }    
    
    public String getNumeroIp() {
        return numeroIp;
    }
    
    public void setNumeroIp(String numeroIp) {
        this.numeroIp = numeroIp;
    } 
    
    public int getTipoEstacion() {
        return tipoEstacion;
    }
    
    public void setTipoEstacion(int tipoEstacion) {
        this.tipoEstacion = tipoEstacion;
    }
}
