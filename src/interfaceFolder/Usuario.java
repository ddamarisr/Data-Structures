/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package interfaceFolder;

import BasesFx.Buttons;
import BasesFx.VBoxs;
import javafx.event.Event;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.stage.Stage;

/**
 *
 * @author USUARIO
 */
public class Usuario implements Vista{
    private Scene escena;
    Buttons btIniciar;
    Buttons btBuscar;
    Buttons btVolver;
    
    @Override
    public Scene getScene() {
        return escena;
    }
    
    public Usuario(){
        btIniciar = new Buttons("Iniciar Reservación");
        btBuscar = new Buttons("Buscar Reservación");
        btVolver = new Buttons("Volver");
        
        VBoxs layout = new VBoxs();
        layout.getChildren().add(btIniciar);
        layout.getChildren().add(btBuscar);
        layout.getChildren().add(btVolver);
        this.escena = new Scene(layout, 300, 300);
        
        btIniciar.setOnAction(new Usuario.EventoIniciar());
        btBuscar.setOnAction(new Usuario.EventoBuscar());
        btVolver.setOnAction(new Usuario.EventoVolver());
    }
    
        
        
    private class EventoIniciar implements EventHandler{
        @Override
        public void handle(Event event) {      
            IniciarReserva reserva = new IniciarReserva();
            Controlador.ventanas.push(new Usuario());
            Singleton singleton = Singleton.getSingleton();
            Stage stage = singleton.getStage();
            stage.setScene(reserva.getScene());
            stage.show();
        }
    }
    
    private class EventoBuscar implements EventHandler{
        @Override
        public void handle(Event event) {      
            BuscarReserva buscar = new BuscarReserva();
            Controlador.ventanas.push(new Usuario());
            Singleton singleton = Singleton.getSingleton();
            Stage stage = singleton.getStage();
            stage.setScene(buscar.getScene());
            stage.show();
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

