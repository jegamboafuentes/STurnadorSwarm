package com.baz.scc.test.turnadorswarm.support;


/**
 * <br><br>Copyright 2013 Banco Azteca. Todos los derechos reservados
 * 
 * @author Giovanni Farf\u00E1n Brice\u00F1o
 */

public abstract class CjCRSwarmStatus {
   
    public static final int TURNO_GENERADO = 1;
    public static final int TURNO_ASIGNADO = 2;
    public static final int TURNO_EN_ATENCION = 3;
    public static final int TURNO_ATENDIDO = 4;
    public static final int TURNO_CADUCADO = 5;
    public static final int TURNO_CANCELADO = 6;
    public static final int TURNO_POSPUESTO = 7;
        
    public static final int TURNO_BEHAVIOR_BASICO = 0;
    public static final int TURNO_BEHAVIOR_POSPUESTO = 1;
    public static final int TURNO_BEHAVIOR_APROPIADO = 2;
    public static final int TURNO_BEHAVIOR_CANCELADO = 3;
    public static final int TURNO_BEHAVIOR_VIRTUAL = 4;
    public static final int TURNO_BEHAVIOR_CADUCADO = 5;
    
    public static final int TURNO_FLOW_INICIADO = 0;
    public static final int TURNO_FLOW_REVISION = 1;
    public static final int TURNO_FLOW_FINALIZACION = 2;
    
    public static final int TURNO_ASIGNADO_OTRO_EMPLEADO = -5;
}
