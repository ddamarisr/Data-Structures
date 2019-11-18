/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package businessLogic;
import data.Airline;
import interfaceFolder.Controlador;
import interfaceFolder.Inicio;
import interfaceFolder.Singleton;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.text.ParseException;
import javafx.application.Application;
import javafx.stage.Stage;
import ui.UI;

/**
 *
 * @author danie
 */
public class ProTest extends Application {
    

    public static void main(String[] args) throws IOException, FileNotFoundException, ClassNotFoundException, ParseException {
        /*
        Airline airline1 = new Airline("Aerolineas PAYASO", 123456);//Acá creamos los objetos aerolínea. Los vuelos se cargan automáticamente
        
        Administrator defo = new Administrator(airline1);

        String role = UI.welcome();

        if (role.equals("Admin")) {
            UI.AdminMenu(defo);
        } else if (role.equals("User")) {
            User user = new User();
            UI.UserMenu(user, defo);
        } else if (role.equals("Bench")) {
            System.out.println("INSERTAR EN LA MITAD");

            Bench.insertion(10000, "10_mil_datos.txt");
            Bench.insertion(100000, "100_mil_datos.txt");
            Bench.insertion(1000000, "1million.txt");

            System.out.println();

            System.out.println("ELIMINAR EN LA MITAD");

            Bench.delete(10000, "10_mil_datos.txt");
            Bench.delete(100000, "100_mil_datos.txt");
            Bench.delete(1000000, "1million.txt");

            System.out.println();

            System.out.println("INSERTAR AL FINAL");

            Bench.append(10000, "10_mil_datos.txt");
            Bench.append(100000, "100_mil_datos.txt");
            Bench.append(1000000, "1million.txt");
        }
             */
        Application.launch(args);
    }
   
    public void start(Stage primaryStage) throws Exception {
        Singleton singleton = Singleton.getSingleton();
        singleton.setStage(primaryStage);
        Controlador controlador = new Controlador();
        primaryStage.setTitle("Airline");
        primaryStage.setScene(new Inicio().getScene());
        primaryStage.show();
    }
/*
    @Override
    public void start(Stage primaryStage) throws Exception {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
   */
}
