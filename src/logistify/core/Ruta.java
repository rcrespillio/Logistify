/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package logistify.core;

import java.util.ArrayList;
import java.util.Date;

/**
 *
 * @author rc
 */
public class Ruta {
    private int distanciaTotal;
    private int duracionTotal;
    private Segmento inicial;
    private Date dia;

    Ruta(Date dia, ArrayList<Punto> locacionesOrdenadas,int horaDeComienzo) {
        this.dia = dia;
        this.inicial = this.generarSegmentosDesdePuntos(locacionesOrdenadas,horaDeComienzo);
    }

    public void print() {
        System.out.println("Ruta ");
        Segmento init = inicial;
        while(init!=null){
            init.print();
            init = init.getSiguienteDestino();
        }
    }

    private Segmento generarSegmentosDesdePuntos(ArrayList<Punto> locacionesOrdenadas,int horaDeComienzo) {
        Segmento inicial = new Segmento(locacionesOrdenadas.get(0), locacionesOrdenadas.get(1),Utilities.horaASegundos(8, 0, 0));
        Segmento aux = inicial;
        for(int i = 1;i<locacionesOrdenadas.size()-1;i++){
            Segmento sig = new Segmento(locacionesOrdenadas.get(i), locacionesOrdenadas.get(i+1),aux.getHoraLlegadaADestino());
            aux.setSiguienteDestino(sig);
            aux = sig;
        }
        return inicial;
    }
    
    
    
} 
