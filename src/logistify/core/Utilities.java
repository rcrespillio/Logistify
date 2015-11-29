/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package logistify.core;

import java.util.Calendar;
import java.util.Date;

/**
 *
 * @author rc
 */
public class Utilities {
    public static String numeroADia(int diaSemana){ // numero de dia de la semana a String coincidente con la notacion que se usa en la bd
        switch(diaSemana){
            case 1: return "DO";
            case 2: return "LU";
            case 3: return "MA";
            case 4: return "MI";
            case 5: return "JU";
            case 6: return "VI";
            case 7: return "SA";
            default: return null;
        }
    }
    public static int horaASegundos(int hora,int min,int seg){ // Hora a segundos
        return hora*60*60+min*60+seg;
    }
    static String mAkm(int duracionTotal) { // Metros a kilometros en string
        int km = duracionTotal/1000;
        int m = duracionTotal%1000;
        return km+"km";
    }
    public static String segundosAHora(int segDato){ // Segundos a hora
        int hor=segDato/3600;  
        int min=(segDato-(3600*hor))/60;  
        int seg=segDato-((hor*3600)+(min*60));  
        return hor+"h "+min+"m "+seg+"s";
    }
    public static Date getFechaPasados(int diasAContemplar) { // Devuelve la fecha pasados n dias desde hoy;
        Calendar c = Calendar.getInstance(); 
        c.setTime(new Date()); 
        c.add(Calendar.DATE,diasAContemplar);
        return c.getTime();
    }

    public static String getDia(Date fechaAContemplar) { //Devuelve un String correspondiente al dia de la fecha dada (LU,MA,MI,etc)
        Calendar c = Calendar.getInstance();
        c.setTime(fechaAContemplar);
        int dayOfWeek = c.get(Calendar.DAY_OF_WEEK);
        return numeroADia(dayOfWeek);
    }
}
