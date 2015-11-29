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
 * @author rc
 */
public class Ordenador {
    private int diasAContemplar = 7;// Cantidad de dias a futuro que se van a contemplar para armar la ruta;
    private Punto laEmpresa; //Destino de donde arrancan las rutas
    private int horaComienzoDelDia;
    
    public Ordenador(String direccionInicial,int horaComienzoDelDia){
        this.laEmpresa = new Punto(direccionInicial);
        this.horaComienzoDelDia = horaComienzoDelDia;
    }
    public ArrayList<Ruta> realizarOrdenamiento(ArrayList<Pedido> pedidos){
        ArrayList<Ruta> rutas = new ArrayList<>(); // Donde se guardan las rutas de las distintas fechas
        System.out.println("Cant pedidos: "+pedidos.size());
        for(int i = 0;i<diasAContemplar;i++){ // Se realiza el ordenamiento para la cantidad de dias a futuro seteada anteriormente;
            Date fechaAContemplar = Utilities.getFechaPasados(i); // Devuelve la fecha pasados n dias desde hoy;
            ArrayList<Ruta> rutasPosibles = new ArrayList<>(); // Donde se guardan las rutas posibles para una fecha dada
            ArrayList<Punto> locacionesOrdenadas = new ArrayList<>();
            this.crearRutasPosibles(laEmpresa, (ArrayList<Pedido>) pedidos.clone(),rutasPosibles,locacionesOrdenadas,fechaAContemplar);
            System.out.println("Para el dia "+fechaAContemplar+": "+rutasPosibles.size());
            for(Ruta rt:rutasPosibles){
                rt.print();
            }
            //String dia = Utilities.getDia(fechaAContemplar); Devuelve un String correspondiente al dia de la fecha dada (LU,MA,MI,etc)            
        }
        return rutas;
    }

    private void crearRutasPosibles(Punto locacion, ArrayList<Pedido> pedidosPendientes, ArrayList<Ruta> rutasPosibles, ArrayList<Punto> locacionesOrdenadas,Date fechaAContemplar) {
        ArrayList<Pedido> pedidosPendientesCopia = (ArrayList<Pedido>) pedidosPendientes.clone(); // Se crea una copia de los pedidos pendientes para pasarle a la siguiente funcion recursiva
        if(locacion.tienePedidoAsoc()){ // Si el destino recibido tiene un pedido asociado
            pedidosPendientesCopia.remove(locacion.getPedidoAEntregar()); // se saca de los pendientes (Los destinos no tienen pedido asociado cuando son los que salen o llegan a la empresa)
        }
        locacionesOrdenadas.add(locacion); // Se agrega el punto actual a el ordenamiento
        if(!pedidosPendientesCopia.isEmpty()){ // Si hay pedidos
            for(Pedido pedidoPendiente:pedidosPendientesCopia){ // Se recorren todos los pedidos restantes habiendo sacado previamente el seleccionado
                this.crearRutasPosibles(new Punto(pedidoPendiente), (ArrayList<Pedido>) pedidosPendientesCopia, rutasPosibles, (ArrayList<Punto>) locacionesOrdenadas.clone(),fechaAContemplar); // Por cada uno se ejecuta la funcion recursiva
            }
        }else{ // Cuando llega al final se agrega la vuelta a la empresa. Despues se crea una ruta, se le agrega el ordenamiento y se guarda
            locacionesOrdenadas.add(laEmpresa);
            Ruta r = new Ruta(fechaAContemplar,locacionesOrdenadas,this.horaComienzoDelDia);
            rutasPosibles.add(r);
        }
    }
}
