package com.baz.scc.test.turnadorswarm.model;


/**
 * <br><br>Copyright 2013 Banco Azteca. Todos los derechos reservados.
 * 
 * @author Giovanni Farf\u00E1n Brice\u00F1o
 */

public class CjCRTurno {

    private int fecha;
    private int idTurno;
    private int estado;
    private int idOrigen;
    private int idUnidadNegocio;
    private int turnoSeguimiento;
    private int prioridad;
    private CjCREmpleadoPool empleado;

    public int getFecha() {
        return fecha;
    }
    
    public void setFecha(int fecha) {
        this.fecha = fecha;
    }
    
    public int getIdTurno() {
        return idTurno;
    }

    public void setIdTurno(int idTurno) {
        this.idTurno = idTurno;
    }

    public int getEstado() {
        return estado;
    }
    
    public void setEstado(int estado) {
        this.estado = estado;
    }
    
    public int getIdOrigen() {
        return idOrigen;
    }

    public void setIdOrigen(int idOrigen) {
        this.idOrigen = idOrigen;
    }

    public int getIdUnidadNegocio() {
        return idUnidadNegocio;
    }

    public void setIdUnidadNegocio(int idUnidadNegocio) {
        this.idUnidadNegocio = idUnidadNegocio;
    }

    public int getTurnoSeguimiento() {
        return turnoSeguimiento;
    }

    public void setTurnoSeguimiento(int turnoSeguimiento) {
        this.turnoSeguimiento = turnoSeguimiento;
    }
    
    public int getPrioridad() {
        return prioridad;
    }
    
    public void setPrioridad(int prioridad) {
        this.prioridad = prioridad;
    }
    
    public CjCREmpleadoPool getEmpleado() {
        return empleado;
    }
    
    public void setEmpleado(CjCREmpleadoPool empleado) {
        this.empleado = empleado;
    }
}
