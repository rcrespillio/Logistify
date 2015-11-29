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
public class LogistifyCore {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        Ordenador ord = new Ordenador("Gral Lavalle 148, Avellaneda",Utilities.horaASegundos(8, 0, 0));
        ArrayList<Pedido> pedidos = getHardcodeo();
        ord.realizarOrdenamiento(pedidos);
    }

    private static ArrayList<Pedido> getHardcodeo() {
        ArrayList<Pedido> pedidos = new ArrayList<>();
        Cliente a = new Cliente(0, "Gral Arredondo 24, Avellaneda", "", "Pepe", "", "", "");
        Cliente b = new Cliente(0, "Gral Guemes 1000, Avellaneda", "", "Juan", "", "", "");
        Cliente c = new Cliente(0, "Gral Machain 300, Avellaneda", "", "Juan", "", "", "");
        pedidos.add(new Pedido(0, "", "Pendiente", a, (float)100.0, new Date(), new Date(),new Date()));
        pedidos.add(new Pedido(0, "", "Pendiente", b, (float)100.0, new Date(), new Date(),new Date()));
        pedidos.add(new Pedido(0, "", "Pendiente", c, (float)100.0, new Date(), new Date(),new Date()));
        return pedidos;
    }
    
}
