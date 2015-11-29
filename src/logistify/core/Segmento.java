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
public class Segmento {
    
    private Punto inicio;
    private Punto destino;
    private int distancia;
    private int tiempo;
    private int horaLlegadaAInicio;
    private int horaSalidaDesdeLaLocacion;
    private int tiempoDeEsperaEnElLugar = Utilities.horaASegundos(0, 20, 0);
    private Segmento siguienteDestino;

    public Segmento(Punto inicio, Punto destino, int horaLlegadaAInicio) {
        this.inicio = inicio;
        this.destino = destino;
        this.horaLlegadaAInicio = horaLlegadaAInicio;
        this.pedirDatosDeLaAPI();
         
    }

    public void setSiguienteDestino(Segmento siguienteDestino) {
        this.siguienteDestino = siguienteDestino;
    }

    public Segmento getSiguienteDestino() {
        return siguienteDestino;
    }
    public String getDireccionOrigen(){
        return this.inicio.getDireccion();
    }
    public String getDireccionDestino(){
        return this.destino.getDireccion();
    }

    private void pedirDatosDeLaAPI() {
        ModuloGoogleApiMatrix moduloAPI = ModuloGoogleApiMatrix.getInstancia();
        Object data[] = moduloAPI.getDistanciaTiempo(inicio, destino); // Data = {Punto origen, Punto destino, int distance, int duration}
        this.distancia = (Integer)data[2];
        this.tiempo = (Integer)data[3];
        this.horaSalidaDesdeLaLocacion = horaLlegadaAInicio+tiempoDeEsperaEnElLugar;
    }

    public int getHoraLlegadaADestino() {
        return horaSalidaDesdeLaLocacion+tiempo;
    }

    void print() {
        System.out.println("De "+this.inicio.getDireccion()+"("+Utilities.segundosAHora(this.horaSalidaDesdeLaLocacion)+") a "+destino.getDireccion()+"("+Utilities.segundosAHora(this.getHoraLlegadaADestino())+") | Viaje: "+Utilities.segundosAHora(this.tiempo)+" Distancia: "+Utilities.mAkm(this.distancia));
    }
    
}
