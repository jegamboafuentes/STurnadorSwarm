package com.baz.scc.test.turnadorswarm.model;


/**
 * <br><br>Copyright 2013 Banco Azteca. Todos los derechos reservados.
 * 
 * @author Giovanni Farf\u00E1n Brice\u00F1o
 */

public class CjCRPropiedadesResponse {

    private String ip;    
    private String estacionTrabajo;
    private String mensajeAcceso;
    private String numeroEmpleado;
    private String passwordEmpleado;
    private String token;
    private int sucursal;
    private boolean accesoConcedido;

    public String getIp() {
        return ip;
    }

    public void setIp(String ip) {
        this.ip = ip;
    }

    public String getEstacionTrabajo() {
        return estacionTrabajo;
    }

    public void setEstacionTrabajo(String estacionTrabajo) {
        this.estacionTrabajo = estacionTrabajo;
    }

    public String getMensajeAcceso() {
        return mensajeAcceso;
    }

    public void setMensajeAcceso(String mensajeAcceso) {
        this.mensajeAcceso = mensajeAcceso;
    }

    public String getNumeroEmpleado() {
        return numeroEmpleado;
    }

    public void setNumeroEmpleado(String numeroEmpleado) {
        this.numeroEmpleado = numeroEmpleado;
    }

    public String getPasswordEmpleado() {
        return passwordEmpleado;
    }

    public void setPasswordEmpleado(String passwordEmpleado) {
        this.passwordEmpleado = passwordEmpleado;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public int getSucursal() {
        return sucursal;
    }

    public void setSucursal(int sucursal) {
        this.sucursal = sucursal;
    }

    public boolean isAccesoConcedido() {
        return accesoConcedido;
    }

    public void setAccesoConcedido(boolean accesoConcedido) {
        this.accesoConcedido = accesoConcedido;
    }
}
