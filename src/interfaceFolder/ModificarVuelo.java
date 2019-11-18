/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package interfaceFolder;

import BasesFx.Buttons;
import BasesFx.HBoxs;
import BasesFx.Labels;
import BasesFx.TextFields;
import BasesFx.VBoxs;
import data.WriteAndRead;
import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.event.Event;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import static javafx.scene.paint.Color.RED;
import javafx.stage.Stage;

/**
 *
 * @author USUARIO
 */
public class ModificarVuelo implements Vista{
    Scene escena;
    ComboBox busqueda;
    TextFields tfFecha;
    TextFields tfVuelo;
    Buttons btCambiarFecha;
    Buttons btCambiarVuelo;
    Buttons btVolver;
    Label lbError;
    Label lbError2;
    int index;
    
    public ModificarVuelo(int index){
        this.index = index;
        HBoxs hbo1 = new HBoxs();
        HBoxs hbo2 = new HBoxs();
        Labels lbFecha = new Labels("Ingrese la nueva fecha del vuelo: ");
        tfFecha = new TextFields();
        Labels lbVuelo= new Labels("Ingrese el nuevo número del vuelo: ");
        tfVuelo = new TextFields();
        btCambiarFecha = new Buttons("Cambiar");
        btCambiarVuelo = new Buttons("Cambiar");
        btVolver = new Buttons("Volver");
        lbError = new Label("El dato es incorrecto");
        lbError.setVisible(false);
        lbError2 = new Label("El dato es incorrecto");
        lbError2.setVisible(false);
        VBoxs layout = new VBoxs();
        layout.getChildren().add(lbFecha);
        hbo1.getChildren().add(tfFecha);
        hbo1.getChildren().add(btCambiarFecha);
        layout.getChildren().add(hbo1);
        layout.getChildren().add(lbError);
        layout.getChildren().add(lbVuelo);
        hbo2.getChildren().add(tfVuelo);
        hbo2.getChildren().add(btCambiarVuelo);
        layout.getChildren().add(hbo2);
        layout.getChildren().add(lbError2);
        layout.getChildren().add(btVolver);
        this.escena = new Scene(layout,400,400);
        
        btCambiarFecha.setOnAction(new ModificarVuelo.EventoFecha());
        btCambiarVuelo.setOnAction(new ModificarVuelo.EventoVuelo());
        btVolver.setOnAction(new ModificarVuelo.EventoVolver());
        
        
    }
    @Override
    public Scene getScene() {
        return escena;
    }
    
    public Scene mensaje(){
        Labels mensaje = new Labels("El dato ha sido cambiado");
        mensaje.setTextFill(RED);
        Scene escena = new Scene(mensaje, 100, 200);
        return escena;
    }
    
    private class EventoFecha implements EventHandler{
        @Override
        public void handle(Event event) { 
            
            SimpleDateFormat sdf=new SimpleDateFormat("dd/MM/yyyy");
            if(tfFecha.getText()!=null)try {
                Controlador.defo.getAirline().getFlights().get(index).setDate(sdf.parse(tfFecha.getText()));
                WriteAndRead.writeFlights(Controlador.defo);
                Stage stage = new Stage();
                stage.setScene(mensaje());
                stage.show();
                lbError.setVisible(false);
                
            } catch (ParseException ex) {
                lbError.setVisible(true);
            } catch (IOException ex) {
                lbError.setVisible(true);
            }
        }
    }
    
    private class EventoVuelo implements EventHandler{
        @Override
        public void handle(Event event) { 
            
            SimpleDateFormat sdf=new SimpleDateFormat("dd/MM/yyyy");
            if(tfVuelo.getText()!=null)try {
                Controlador.defo.getAirline().getFlights().get(index).setNumberOfFlight(Integer.parseInt(tfVuelo.getText()));
                WriteAndRead.writeFlights(Controlador.defo);
                Stage stage = new Stage();
                stage.setScene(mensaje());
                stage.show();
                lbError2.setVisible(false);
                
            } catch (Exception ex) {
                lbError2.setVisible(true);
            }
        }
    }
    
    
    private class EventoVolver implements EventHandler{
        @Override
        public void handle(Event event) {      
            Singleton singleton = Singleton.getSingleton();
            Stage stage = singleton.getStage();
            stage.setScene(Controlador.ventanas.pop().getScene());
            stage.show();
        }
    }
}
