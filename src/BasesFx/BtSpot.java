/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package BasesFx;

import javafx.event.Event;
import javafx.event.EventHandler;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

/**
 *
 * @author USUARIO
 */
public class BtSpot extends Button {
    private boolean activa;
    private int fila;
    private int columna;

    public void setActiva() {
        this.activa =! activa;
    }

    public boolean isActiva() {
        return activa;
    }
    
    public void setImage(ImageView a){
        this.setGraphic(a);
    }
    
    public BtSpot(){
        super();
        this.activa = true;
        
    }
    
    public void cambiarImagen(String ruta){
        this.setOnAction(new CambiarImagen(ruta));
    }
    
    private class CambiarImagen implements EventHandler{
        String ruta;
        public CambiarImagen(String ruta){
            this.ruta = ruta;
        }
        @Override
        public void handle(Event event) {   
            if(ruta.equals("Silla_Creada.jpg")){
                ruta = "Silla_Eliminada.jpg";
            }
            else if(ruta.equals("Silla_Creada_Medio.jpg")){
                ruta = "Silla_Eliminada_Medio.jpg";
            }
            else if(ruta.equals("Silla_Creada_Peque.jpg")){
                ruta = "Silla_Eliminada_Peque.jpg";
            }
            else if(ruta.equals("Silla_Eliminada.jpg")){
                ruta = "Silla_Creada.jpg";
            }
            else if(ruta.equals("Silla_Eliminada_Medio.jpg")){
                ruta = "Silla_Creada_Medio.jpg";
            }
            else if(ruta.equals("Silla_Eliminada_Peque.jpg")){
                ruta = "Silla_Creada_Peque.jpg";
            }
            Image image = new Image(getClass().getResourceAsStream(ruta));
            setImage(new ImageView(image));
            setActiva();
        }
    }
}
