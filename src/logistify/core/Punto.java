/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package logistify.core;

/**
 *
 * @author rc
 */
public class Punto {
    private String direccion;
    private Pedido pedidoAsociado;
    public Punto(String direccion) { // Constructor para punto sin pedido asociado
        this.direccion = direccion;
        this.pedidoAsociado = null;
    }
     public Punto(Pedido pedido) { // Constructor para punto sin pedido asociado
        this.direccion = pedido.getDireccionDeEntrega();
        this.pedidoAsociado = pedido;
    }
    public boolean tienePedidoAsoc(){
        return pedidoAsociado!=null;
    }
    public Pedido getPedidoAEntregar() {
        return pedidoAsociado;
    }

    public String getDireccion() {
        return direccion;
    }
    
    
}
