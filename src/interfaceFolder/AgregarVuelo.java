
package interfaceFolder;

import BasesFx.Buttons;
import BasesFx.HBoxs;
import BasesFx.Labels;
import BasesFx.TextFields;
import BasesFx.VBoxs;
import data.WriteAndRead;
import javafx.event.Event;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import static javafx.scene.paint.Color.RED;
import javafx.stage.Stage;

public class AgregarVuelo implements Vista {
    private Scene escena;
    private TextFields tfOrigen;
    private TextFields tfDestino;
    private TextFields tfVuelo;
    private TextFields tfFecha;
    private Label lbError;
    private Buttons btVolver;
    private Buttons btGuardar;
    
    public AgregarVuelo(){
        Labels lbOrigen = new Labels("Ingrese la ciudad de origen:");
        tfOrigen = new TextFields();
        Labels lbDestino = new Labels("Ingrese la ciudad de destino:");
        tfDestino = new TextFields();
        Labels lbVuelo = new Labels("Ingrese el número de vuelo:");
        tfVuelo = new TextFields();
        Labels lbFecha = new Labels("Ingrese la fecha del vuelo como dd/mm/aaaa:");
        tfFecha = new TextFields();
        lbError = new Label("Datos Incorrectos");
        lbError.setVisible(false);
        lbError.setTextFill(RED);
        btVolver = new Buttons("Volver");
        btGuardar = new Buttons("Guardar");
        VBoxs layout = new VBoxs();
        layout.getChildren().add(lbOrigen);
        layout.getChildren().add(tfOrigen);
        layout.getChildren().add(lbDestino);
        layout.getChildren().add(tfDestino);
        layout.getChildren().add(lbVuelo);
        layout.getChildren().add(tfVuelo);
        layout.getChildren().add(lbFecha);
        layout.getChildren().add(tfFecha);
        layout.getChildren().add(lbError);
        HBoxs botones = new HBoxs();
        botones.getChildren().add(btVolver);
        botones.getChildren().add(btGuardar);
        layout.getChildren().add(botones);
        
        this.escena = new Scene(layout, 500, 500);
        btVolver.setOnAction(new AgregarVuelo.EventoVolver());
        btGuardar.setOnAction(new AgregarVuelo.EventoGuardar());
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
                Controlador.defo.addFlight(Controlador.defo, tfOrigen.getText(), tfDestino.getText(), tfVuelo.getText(), tfFecha.getText());
                WriteAndRead.writeFlights(Controlador.defo);
                Singleton singleton = Singleton.getSingleton();
                Stage stage = singleton.getStage();
                stage.setScene(Controlador.ventanas.pop().getScene());
                stage.show();
                
            } 
            catch (Exception e) {
                lbError.setVisible(true);
            }
        }
    }

}
