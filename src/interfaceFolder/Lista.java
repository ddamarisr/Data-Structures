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
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.Event;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.control.ListView;
import javafx.stage.Stage;

/**
 *
 * @author USUARIO
 */
public class Lista {
    private Stage stage;
    private Scene escena;
    private Buttons btCerrar;
    private Labels lbMensaje;
    private ListView<String> list = new ListView<String>();
    private ObservableList<String> items;
    
    public Lista(int a, Stage stage){
        this.stage = stage;
        if(a==1){
            lbMensaje = new Labels("Estos son los vuelos disponibles: ");       
            items = FXCollections.observableArrayList();
            for (Flight i : Controlador.defo.getAirline().getFlights()) {
                items.add(i.toString());
            }
            list.setItems(items);
        }
        else if (a==2){
            lbMensaje = new Labels("Esta es la lista de destinos: ");
            items = FXCollections.observableArrayList();
            for (Flight fligths:Controlador.defo.getAirline().getFlights()){
                    items.add(fligths.getRoute().getDestination());
                }
            list.setItems(items);
        }
        btCerrar= new Buttons("Cerrar");
        VBoxs layout = new VBoxs();
        layout.getChildren().add(lbMensaje);
        layout.getChildren().add(list);
        layout.getChildren().add(btCerrar);
        this.escena = new Scene(layout, 830, 300);
        btCerrar.setOnAction(new Lista.EventoCerrar());
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
