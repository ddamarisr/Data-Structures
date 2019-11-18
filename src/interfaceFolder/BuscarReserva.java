/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package interfaceFolder;

import BasesFx.Buttons;
import BasesFx.Labels;
import BasesFx.TextFields;
import BasesFx.VBoxs;
import data.Passenger;
import data.WriteAndRead;
import java.io.IOException;
import javafx.event.Event;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import static javafx.scene.paint.Color.RED;
import javafx.stage.Stage;

/**
 *
 * @author USUARIO
 */
public class BuscarReserva implements Vista{
    private Scene escena;
    private TextFields tfDocumento;
    private Buttons btBuscar;
    private Labels lbEncontrado;
    private Label lbError;
    private Buttons btCambiar;
    private Buttons btCancelar;
    private Buttons btVolver;
    private Passenger found;
    
    public BuscarReserva(){
    Labels lbDocumento = new Labels("Ingrese el numero de documento: ");
    tfDocumento = new TextFields();
    btBuscar = new Buttons("Buscar");
    lbError = new Label("No se ha encontrado el documento especificado");
    lbError.setTextFill(RED);
    lbError.setVisible(false);
    lbEncontrado = new Labels("Su reserva ha sido encontrada: ");
    lbEncontrado.setVisible(false);
    btCambiar = new Buttons("Cambiar Asiento");
    btCambiar.setVisible(false);
    btCancelar = new Buttons("Cancelar Reservación");
    btCancelar.setVisible(false);
    btVolver = new Buttons("Volver");
    VBoxs layout = new VBoxs();
    layout.getChildren().add(lbDocumento);
    layout.getChildren().add(tfDocumento);
    layout.getChildren().add(btBuscar);
    layout.getChildren().add(lbError);
    layout.getChildren().add(lbEncontrado);
    layout.getChildren().add(btCambiar);
    layout.getChildren().add(btCancelar);
    layout.getChildren().add(btVolver);
    this.escena = new Scene(layout, 400, 400);
    
    btCancelar.setOnAction(new BuscarReserva.EventoCancelar());
    btVolver.setOnAction(new BuscarReserva.EventoVolver());
    btBuscar.setOnAction(new BuscarReserva.EventoBuscar());
    btCambiar.setOnAction(new BuscarReserva.EventoCambiar());
    }

    @Override
    public Scene getScene() {
        return escena;
    }
    
    public void setVisibleOpciones(){
        lbEncontrado.setVisible(true);
        btCambiar.setVisible(true);
        btCancelar.setVisible(true);
    }
    
    public void setInvisibleOpciones(){
        lbEncontrado.setVisible(false);
        btCambiar.setVisible(false);
        btCancelar.setVisible(false);
    }    
    
    public void setVisibleError(){
        lbError.setVisible(true);
    }
    
    
    private class EventoBuscar implements EventHandler{
        @Override
        public void handle(Event event) { 
            try {
                setInvisibleOpciones();
                lbError.setVisible(false);
                found = Controlador.user.findReservations(Controlador.defo, tfDocumento.getText());
                if(found!=null){
                    setVisibleOpciones();
                }
                else{
                    lbError.setVisible(true);
                }
            } catch (Exception e) {
                lbError.setVisible(true);
            }
            
        }
    }
    
    private class EventoCambiar implements EventHandler{
        @Override
        public void handle(Event event) {      
            CambiarAsiento cambiar = new CambiarAsiento(found);
            Controlador.ventanas.push(new BuscarReserva());
            Singleton singleton = Singleton.getSingleton();
            Stage stage = singleton.getStage();
            stage.setScene(cambiar.getScene());
            stage.show();
        }
    }
    
    private class EventoCancelar implements EventHandler{
        @Override
        public void handle(Event event) {  
            try {
                Controlador.user.deleteReservation(Controlador.defo, found);
                WriteAndRead.writeFlights(Controlador.defo);
                Singleton singleton = Singleton.getSingleton();
                Stage stage = singleton.getStage();
                stage.setScene(Controlador.ventanas.pop().getScene());
                stage.show();
                
            } catch (IOException ex) {
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
