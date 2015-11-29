/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package logistify.core;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;

/**
 *
 * @author Usuario
 */
public class Disponibilidad {

    private int id;
    private int hora_desde;
    private int hora_hasta;
    private String dia;

    public Disponibilidad(int id, int hora_desde, int hora_hasta, String dia) {
        this.id = id;
        this.hora_desde = hora_desde;
        this.hora_hasta = hora_hasta;
        this.dia = dia;
    }
    public boolean tenesElDia(String diaAFiltrar){
        return dia.equals(diaAFiltrar);
    }

    public int getHora_desde() {
        return hora_desde;
    }

    public int getHora_hasta() {
        return hora_hasta;
    }
    
    
}
