package com.baz.scc.test.turnadorswarm.model;

import java.util.ArrayList;
import java.util.List;


/**
 * <br><br>Copyright 2013 Banco Azteca. Todos los derechos reservados.
 * 
 * @author Giovanni Farf\u00E1n Brice\u00F1o
 */

public class CjCRUnidadNegocio {
    
    private final List<CjCRTurno> turnos;
    private final List<CjCREmpleadoPool> empleados;
    
    private int idUnidadNegocio;
    private String descripcion;
    private boolean prestamos;
    private String urlImagen;    
    
    public CjCRUnidadNegocio() {
        turnos = new ArrayList<CjCRTurno>();
        empleados = new ArrayList<CjCREmpleadoPool>();
    }
    
    public int getIdUnidadNegocio() {
        return idUnidadNegocio;
    }

    public void setIdUnidadNegocio(int idUnidadNegocio) {
        this.idUnidadNegocio = idUnidadNegocio;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public boolean isPrestamos() {
        return prestamos;
    }

    public void setPrestamos(boolean prestamos) {
        this.prestamos = prestamos;
    }

    public String getUrlImagen() {
        return urlImagen;
    }

    public void setUrlImagen(String urlImagen) {
        this.urlImagen = urlImagen;
    }
    
    public List<CjCRTurno> getTurnos() {
        return turnos;
    }
    
    public List<CjCREmpleadoPool> getEmpleados() {
        return empleados;
    }
}
