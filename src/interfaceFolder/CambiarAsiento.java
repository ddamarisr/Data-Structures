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
import data.Seat;
import data.WriteAndRead;
import javafx.event.Event;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.layout.VBox;
import static javafx.scene.paint.Color.RED;
import javafx.stage.Stage;
import ui.UI;

/**
 *
 * @author USUARIO
 */
public class CambiarAsiento implements Vista {
    private Scene escena;
    private TextFields tfLugar;
    private Label lbError;
    private Buttons btVolver;
    private Buttons btGuardar;
    private Passenger p;
    
    public CambiarAsiento(Passenger p){
        this.p = p;
        Labels lbLugar = new Labels("Ingrese el nuevo lugar de reserva con el número de fila y letra de columna separados por un espacio: ");
        tfLugar = new TextFields();
        lbError = new Label("Error: Verifique que los datos son correctos.");
        lbError.setTextFill(RED);
        lbError.setVisible(false);
        btVolver = new Buttons("Volver");
        btGuardar = new Buttons("Guardar");
        VBoxs layout = new VBoxs();
        layout.getChildren().add(lbLugar);
        layout.getChildren().add(tfLugar);
        layout.getChildren().add(lbError);
        layout.getChildren().add(btGuardar);
        VBox picture = new VBox();
        for(String i: UI.printPlaneInterfaz(p.getFlight())){
            Label lbLine = new Label(i);
            picture.getChildren().add(lbLine);
        }
        btVolver = new Buttons("Volver");
        layout.getChildren().add(picture);
        layout.getChildren().add(btVolver);
        this.escena = new Scene(layout, 500, 500);
        
        btVolver.setOnAction(new CambiarAsiento.EventoVolver());
        btGuardar.setOnAction(new CambiarAsiento.EventoGuardar());
    }
    
    @Override
    public Scene getScene() {
        return escena;
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
    
    private class EventoGuardar implements EventHandler{
        @Override
        public void handle(Event event) {      
            try {
                lbError.setVisible(false);
                Seat newSeat = p.getFlight().checkAvailableSeatsInterface(tfLugar.getText());
                p.getFlight().getPassengers()[p.getSeat().getRow()][p.getSeat().getColumn()] = null; //liberar espacio del avion
                p.setSeat(newSeat); //Cambiar el asiento asignado
                p.getFlight().getPassengers()[p.getSeat().getRow()][p.getSeat().getColumn()] = p; //añadir al avión en la nueva posicion
                WriteAndRead.writeFlights(Controlador.defo);
                Singleton singleton = Singleton.getSingleton();
                Stage stage = singleton.getStage();
                stage.setScene(Controlador.ventanas.pop().getScene());
                stage.show();
            } catch (Exception e) {
                lbError.setVisible(true);
            }
            
        }
    }
}