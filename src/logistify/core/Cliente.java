/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package logistify.core;

import java.util.ArrayList;

/**
 *
 * @author rc
 */
public class Cliente {
    private int id_cliente;
    private String direccion_cliente;
    private String razon_social_cliente;
    private String nombre_cliente;
    private String telefono_cliente;
    private String cuil_cliente;
    private String cuit_cliente;
    private ArrayList<Disponibilidad> disponibilidades;
    
    public Cliente(int id_cliente, String direccion_cliente, String razon_social_cliente, String nombre_cliente, String telefono_cliente, String cuil_cliente, String cuit_cliente) {
        this.id_cliente = id_cliente;
        this.direccion_cliente = direccion_cliente;
        this.razon_social_cliente = razon_social_cliente;
        this.nombre_cliente = nombre_cliente;
        this.telefono_cliente = telefono_cliente;
        this.cuil_cliente = cuil_cliente;
        this.cuit_cliente = cuit_cliente;
        this.disponibilidades = new ArrayList<Disponibilidad>();
    }
    boolean tenesDisponibleElDia(String diaAFiltrar) {
        for(Disponibilidad disponibilidad:disponibilidades){
            if(disponibilidad.tenesElDia(diaAFiltrar)){
                return true;
            }
        }
        return false;
    }

    public String getDireccion_cliente() {
        return direccion_cliente;
    }
    
}
