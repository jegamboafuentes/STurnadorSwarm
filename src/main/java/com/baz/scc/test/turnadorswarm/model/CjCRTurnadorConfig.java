package com.baz.scc.test.turnadorswarm.model;

import java.util.Map;


/**
 * <br><br>Copyright 2013 Banco Azteca. Todos los derechos reservados.
 * 
 * @author Giovanni Farf\u00E1n Brice\u00F1o
 */

public class CjCRTurnadorConfig {
   
    private Map<Integer, CjCRTurnoBehaviorConfig> turnosConfig;    
    private String negocioAtencionData;
    private boolean credentialsFile;
    private boolean defaultCredential;
    private int sucursal;
    private int noEmpleados;
    private int noTurnos;
    private int turnosCaducidad;
    private int tiempoMinimo;
    private int factorEspera;
    private int factorTurnoMin;
    private int factorTurnoMax;
    private int factorDisponibleMin;
    private int factorDisponibleMax;
    private int factorAsignacionMin;
    private int factorAsignacionMax;
    private int factorAtencionMin;
    private int factorAtencionMax; 
    
    public Map<Integer, CjCRTurnoBehaviorConfig> getTurnosConfig() {
        return turnosConfig;
    }
    
    public void setTurnosConfig(Map<Integer, CjCRTurnoBehaviorConfig> turnosConfig) {
        this.turnosConfig = turnosConfig;
    }
    
    public String getNegocioAtencionData() {
        return negocioAtencionData;
    }
    
    public void setNegocioAtencionData(String negocioAtencionData) {
        this.negocioAtencionData = negocioAtencionData;
    }
    
    public boolean isCredentialsFile() {
        return credentialsFile;
    }
    
    public void setCredentialsFile(boolean credentialsFile) {
        this.credentialsFile = credentialsFile;
    } 
    
    public boolean isDefaultCredential() {
        return defaultCredential;
    }
    
    public void setDefaultCredential(boolean defaultCredential) {
        this.defaultCredential = defaultCredential;
    }
    
    public int getSucursal() {
        return sucursal;
    }
    
    public void setSucursal(int sucursal) {
        this.sucursal = sucursal;
    }
    
    public int getNoEmpleados() {
        return noEmpleados;
    }
    
    public void setNoEmpleados(int noEmpleados) {
        this.noEmpleados = noEmpleados;
    }
    
    public int getNoTurnos() {
        return noTurnos;
    }
    
    public void setNoTurnos(int noTurnos) {
        this.noTurnos = noTurnos;
    }
    
    public int getTurnosCaducidad() {
        return turnosCaducidad;
    }
    
    public void setTurnosCaducidad(int turnosCaducidad) {
        this.turnosCaducidad = turnosCaducidad;
    }
    
    public int getTiempoMinimo() {
        return tiempoMinimo;
    }
    
    public void setTiempoMinimo(int tiempoMinimo) {
        this.tiempoMinimo = tiempoMinimo;
    }
    
    public int getFactorEspera() {
        return factorEspera;
    }
    
    public void setFactorEspera(int factorEspera) {
        this.factorEspera = factorEspera;
    }
    
    public int getFactorTurnoMin() {
        return factorTurnoMin;
    }
    
    public void setFactorTurnoMin(int factorTurnoMin) {
        this.factorTurnoMin = factorTurnoMin;
    }
    
    public int getFactorTurnoMax() {
        return factorTurnoMax;
    }
    
    public void setFactorTurnoMax(int factorTurnoMax) {
        this.factorTurnoMax = factorTurnoMax;
    }
    
    public int getFactorDisponibleMin() {
        return factorDisponibleMin;
    }
    
    public void setFactorDisponibleMin(int factorDisponibleMin) {
        this.factorDisponibleMin = factorDisponibleMin;
    }
    
    public int getFactorDisponibleMax() {
        return factorDisponibleMax;
    }
    
    public void setFactorDisponibleMax(int factorDisponibleMax) {
        this.factorDisponibleMax = factorDisponibleMax;
    }
    
    public int getFactorAsignacionMin() {
        return factorAsignacionMin;
    }
    
    public void setFactorAsignacionMin(int factorAsignacionMin) {
        this.factorAsignacionMin = factorAsignacionMin;
    }
    
    public int getFactorAsignacionMax() {
        return factorAsignacionMax;
    }
    
    public void setFactorAsignacionMax(int factorAsignacionMax) {
        this.factorAsignacionMax = factorAsignacionMax;
    }
    
    public int getFactorAtencionMin() {
        return factorAtencionMin;
    }
    
    public void setFactorAtencionMin(int factorAtencionMin) {
        this.factorAtencionMin = factorAtencionMin;
    }
    
    public int getFactorAtencionMax() {
        return factorAtencionMax;
    }
    
    public void setFactorAtencionMax(int factorAtencionMax) {
        this.factorAtencionMax = factorAtencionMax;
    }
}
