/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package interfaceFolder;

import BasesFx.Buttons;
import BasesFx.Labels;
import BasesFx.VBoxs;
import data.Flight;
import data.Passenger;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.Event;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import ui.UI;

/**
 *
 * @author USUARIO
 */
public class ListaPasajeros {
    private Stage stage;
    private Scene escena;
    private Buttons btCerrar;
    private Labels lbMensaje;
    private ListView<String> list = new ListView<String>();
    private ObservableList<String> items;
    
    public ListaPasajeros(Stage stage, int index){
        this.stage = stage;
        lbMensaje = new Labels("Estos son los pasajeros del vuelo: ");       
        items = FXCollections.observableArrayList();
        Passenger passengers[][]=Controlador.defo.getAirline().getFlights().get(index).getPassengers();
        for (int i=0;i<passengers.length;i++){
            for (int j=0;j<passengers[0].length;j++){
                if(passengers[i][j]!=null){
                    items.add(passengers[i][j].toString());
                }
            }
        }
        list.setItems(items);
        btCerrar= new Buttons("Cerrar");
        VBoxs layout = new VBoxs();
        layout.getChildren().add(lbMensaje);
        layout.getChildren().add(list);
        VBox picture = new VBox();
        for(String i: UI.printPlaneInterfaz(Controlador.defo.getAirline().getFlights().get(index))){
            Label lbLine = new Label(i);
            picture.getChildren().add(lbLine);
        }
        layout.getChildren().add(picture);
        layout.getChildren().add(btCerrar);
        this.escena = new Scene(layout, 830, 700);
        btCerrar.setOnAction(new ListaPasajeros.EventoCerrar());
    }
    
    public Scene getScene(){
        return escena;
    }
    
    private class EventoCerrar implements EventHandler{
        @Override
        public void handle(Event event) {      
            stage.close();
        }
    }
}
