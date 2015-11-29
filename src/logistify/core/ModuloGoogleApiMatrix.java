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
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLEncoder;
import java.util.ArrayList;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;



public class ModuloGoogleApiMatrix {
    
    private ArrayList<Object[]> cache;
    private static ModuloGoogleApiMatrix singleton = null;
    private int contador;
    public static ModuloGoogleApiMatrix getInstancia(){
        if(singleton==null)
            singleton = new ModuloGoogleApiMatrix();
        return singleton;
    }
    
    public ModuloGoogleApiMatrix() {
        this.cache = new ArrayList<>();
        this.contador = 0;
    }
    
    public Object[] getDistanciaTiempo(Punto origen, Punto destino){
        
        Object enCache[] = this.buscarEnCache(origen, destino);
        if(enCache!=null)
            return enCache;
        if(origen.equals(destino)){
            Object result[] ={origen, destino, 0, 0};
            cache.add(result);
            return result;
        }
            
        //ARMA URL EN FORMATO VALIDO
        String url = this.armarURL(origen.getDireccion(),destino.getDireccion());
        
        //OBTIENE EL JSON EN TEXTO PLANO
        String jsonPlano = this.readUrl(url);
        this.contador++;
        //System.out.println("CANT CONSULTAS: "+contador);
        //SE CREA EL PARSER DE JSON Y EMPIEZA A OBTENER DATOS
        JSONParser parser = new JSONParser();
        
        try {
            //PARSEA EL CODIGO PLANO
            Object obj = parser.parse(jsonPlano);
            
            //OBTIENE UN OBJETO JSON 
            JSONObject jsonObject = (JSONObject) obj;
            
            //OBTIENE EL ARRAY DENTRO DEL OBJETO jsonObject, CONTENIDO EN LA VAR DE NOMBRE ROWS DEL JSON jsonObject
            JSONArray rows = (JSONArray) jsonObject.get("rows");
            
            //OBTIENE EL 1ER ELEMENTO DEL ARRAY rows, UN OBJETO JSON
            JSONObject jsonElemObj = (JSONObject) rows.get(0);
            
            //OBTIENE EL ARRAY DENTRO DEL OBJETO jsonElemObj, CONTENIDO EN LA VAR DE NOMBRE ELEMENTS DEL JSON
            JSONArray elements = (JSONArray) jsonElemObj.get("elements");
            
            //OBTIENE EL 1ER ELEMENTO DEL ARRAY elements, UN OBJETO JSON
            JSONObject dataJson = (JSONObject) elements.get(0);
            
            //OBTIENE EL OBJETO DENTRO DEL OBJETO dataJson, CONTENIDO EN LA VAR DE NOMBRE DISTANCE DEL JSON
            JSONObject distanceObj = (JSONObject) dataJson.get("distance");
            
            //OBTIENE EL VALOR DENTRO DEL OBJETO distanceObj, CONTENIDO EN LA VAR DE VALUE DEL JSON
            long valueDI = (long) distanceObj.get("value");
            
            //OBTIENE EL OBJETO DENTRO DEL OBJETO dataJson, CONTENIDO EN LA VAR DE NOMBRE DURATION DEL JSON
            JSONObject durationObj = (JSONObject) dataJson.get("duration");
            
            //OBTIENE EL VALOR DENTRO DEL OBJETO durationObj, CONTENIDO EN LA VAR DE VALUE DEL JSON
            long valueDU = (long) durationObj.get("value");
            
            //ASIGNACION DE VALORES OBTENIDOS A VARIABLES
            int distance = (int) valueDI;
            int duration = (int) valueDU;
            
            //ARMA SEGMENTO CON LOS DATOS OBTENIDOS Y LO DEVUELVE
            Object result[] ={origen, destino, distance, duration};
            cache.add(result);
            return result;
 
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
        
        //return new Segmento(origen, destino, 15000, 1520);
    }
    public String readUrl(String url1) {
        try{
            URL oracle;
            oracle = new URL(url1);
            BufferedReader in = new BufferedReader(
            new InputStreamReader(oracle.openStream()));

            String inputLine;
            StringBuilder strbuilder = new StringBuilder();
            while ((inputLine = in.readLine()) != null)
                strbuilder.append(inputLine);
            in.close();
            return strbuilder.toString();
        }
        catch(MalformedURLException murlee){
            murlee.printStackTrace();
            return null;
        }
        catch(IOException ioe){
            ioe.printStackTrace();
            return null;
        }
    }
    public void printCanConsultas(){
        System.out.println("CANT CONSULTAS TOTALES: "+this.contador);
    }
    private String armarURL(String addr1, String addr2) {
        try {
            return "https://maps.googleapis.com/maps/api/distancematrix/json?origins="+URLEncoder.encode(addr1,"UTF-8")+"&destinations="+URLEncoder.encode(addr2,"UTF-8");
        } catch (Exception ex) {
           
            ex.printStackTrace();
            
            return null;
        }
    }
    private Object[] buscarEnCache(Punto origen, Punto destino){
        for(Object[] row:cache){
            if(((Punto)row[0]).getDireccion().equals(origen.getDireccion())&&((Punto)row[1]).getDireccion().equals(destino.getDireccion())){
                return row;
            }
        }
        return null;
    }
}
