package com.baz.scc.test.turnadorswarm.model;


/**
 * <br><br>Copyright 2013 Banco Azteca. Todos los derechos reservados.
 * 
 * @author Giovanni Farf\u00E1n Brice\u00F1o
 */

public class CjCREmpleadoCredential {
    
    private String noEmpleado;
    private String numeroEmpleado;
    private String passwordEmpleado;
    private String cipherNumero;
    private String cipherPassword;
    private int sucursal;    
    private String token;
    private CjCRPropiedadesRequest propiedades;
    
    public String getNoEmpleado() {
        return noEmpleado;
    }
    
    public void setNoEmpleado(String noEmpleado) {
        this.noEmpleado = noEmpleado;
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
    
    public String getCipherNumero() {
        return cipherNumero;
    }
    
    public void setCipherNumero(String cipherNumero) {
        this.cipherNumero = cipherNumero;
    }
    
    public String getCipherPassword() {
        return cipherPassword;
    }
    
    public void setCipherPassword(String cipherPassword) {
        this.cipherPassword = cipherPassword;
    }
    
    public int getSucursal() {
        return sucursal;
    }
    
    public void setSucursal(int sucursal) {
        this.sucursal = sucursal;
    }
    
    public String getToken() {
        return token;
    }
    
    public void setToken(String token) {
        this.token = token;
    }
    
    public CjCRPropiedadesRequest getPropiedades() {
        return propiedades;
    }
    
    public void setPropiedades(CjCRPropiedadesRequest propiedades) {
        this.propiedades = propiedades;
    }
}
