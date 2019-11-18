/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package interfaceFolder;

import BasesFx.Buttons;
import BasesFx.HBoxs;
import BasesFx.Labels;
import BasesFx.VBoxs;
import javafx.event.Event;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.stage.Stage;

/**
 *
 * @author USUARIO
 */
public class Emergente implements Vista{
        Scene escena;
        Stage stage;
        Buttons btSi;
        Buttons btNo;
        
        public Emergente(Stage stage, String mensaje){
            Labels lbSeguro = new Labels(mensaje);
            btSi = new Buttons("Si");
            btNo = new Buttons("No");
            HBoxs hbox = new HBoxs();
            hbox.getChildren().add(btSi);
            hbox.getChildren().add(btNo);
            VBoxs layout = new VBoxs();
            layout.getChildren().add(lbSeguro);
            layout.getChildren().add(hbox);
            this.escena = new Scene(layout, 100, 300);
            
            btSi.setOnAction(new Emergente.EventoSi());
            
        }

        @Override
        public Scene getScene() {
            return escena;
        }
        
        
        private class EventoSi implements EventHandler{
        @Override
            public void handle(Event event) {      
                AgregarVuelo agregar = new AgregarVuelo();
                Controlador.ventanas.push(new Administrador());
                Singleton singleton = Singleton.getSingleton();
                Stage stage = singleton.getStage();
                stage.setScene(agregar.getScene());
                stage.show();
            }
        }
    
    }
