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
public class Pedido {
    private int id_pedido;
    private String observaciones_pedido;
    private String estado_pedido;
    private Cliente cliente;
    private float total_pedido;
    private Date fecha_creacion_pedido;
    private Date ventana_desde_pedido;
    private Date ventana_hasta_pedido;
    private float peso;
    
    public Pedido(int id_pedido, String observaciones_pedido, String estado_pedido, Cliente cliente, float total_pedido, Date fecha_creacion_pedido, Date ventana_desde_pedido, Date ventana_hasta_pedido,float peso) {
        this.id_pedido = id_pedido;
        this.observaciones_pedido = observaciones_pedido;
        this.estado_pedido = estado_pedido;
        this.cliente = cliente;
        this.total_pedido = total_pedido;
        this.fecha_creacion_pedido = fecha_creacion_pedido;
        this.ventana_desde_pedido = ventana_desde_pedido;
        this.ventana_hasta_pedido = ventana_hasta_pedido;
        this.peso = peso;
    }

    public boolean tePodesEntregarElDia(String diaAFiltrar) {
        return cliente.tenesDisponibleElDia(diaAFiltrar);
    }
    public int getTiempoDeCargaDescarga() {
        return Utilities.horaASegundos(0, 20, 0);
    }

    public String getDireccionDeEntrega() {
        return cliente.getDireccion_cliente();
    }
}
