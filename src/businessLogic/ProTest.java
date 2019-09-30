/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package businessLogic;
import data.Airline;
import data.Flight;
import data.Passenger;
import data.Plane;
import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.text.ParseException;
import java.util.LinkedList;
import java.util.Scanner;
import ui.UI;

/**
 *
 * @author danie
 */
public class ProTest {
    
    public static LinkedList<String> destinations = new LinkedList<>();
    public static LinkedList<Flight> flights = new LinkedList<>();
    
    public static Plane trialPlane = new Plane(1);
    public static Airline airline1 = new Airline("Aerolineas PAYASO", 123456);

    public static void main(String[] args) throws IOException, FileNotFoundException, ClassNotFoundException, ParseException {

        

        String role = UI.welcome();

        if (role.equals("Admin")) {
            Administrator defo = new Administrator(); //Sólo instancia si está en el rol de administrador
            UI.AdminMenu(defo);
        } else if(role.equals("User")) {
            User user = new User();
            UI.UserMenu(user);
        } else if (role.equals("Bench")){
            System.out.println("INSERTAR EN LA MITAD");
            
            Bench.insertion(10000,"10_mil_datos.txt");
            Bench.insertion(100000, "100_mil_data.txt");
            Bench.insertion(1000000, "1million.txt");
            
            System.out.println();
            
            System.out.println("ELIMINAR EN LA MITAD");
            
            
            Bench.delete(10000,"10_mil_datos.txt");
            Bench.delete(100000, "100_mil_data.txt");
            Bench.delete(1000000, "1million.txt");
            
                        System.out.println();
            
            System.out.println("INSERTAR AL FINAL");
            
            
            Bench.append(10000,"10_mil_datos.txt");
            Bench.append(100000, "100_mil_data.txt");
            Bench.append(1000000, "1million.txt");
            
        }
                long startTime = System.nanoTime();
                LinkedList<Passenger> passengers = new LinkedList<Passenger>();
                String destine;
                Path path = Paths.get("100_mil_data.txt");
                Scanner scanner = new Scanner(path);

                while(scanner.hasNextLine()){
                    String line = scanner.nextLine();
                    String[] data = line.split(" ");
                    Passenger p1 = new Passenger(Integer.parseInt(data[2]), data[0] + " " + data[1], data[3]);
                    passengers.add(p1);
                }
                
                scanner.close();
                
                /*BufferedReader bf = new BufferedReader(new FileReader("100_mil_datos.txt"));
                while ((destine = bf.readLine())!=null) {
                    String[] data = destine.split(" ");
                    Passenger p1 = new Passenger(Integer.parseInt(data[2]), data[0] + " " + data[1], data[3]);
                    
                    passengers.add(p1);
                }*/
                //passengers.print();
                
                        //for (Passenger i: passengers) System.out.println(i.data());
                        
                        //for(Passenger i: passengersLinked){
                          //  if(9999==i.getId())System.out.println("La persona fue encontrada");
                        //}
                
                        //for(Passenger i: passenger)
                long endTime = System.nanoTime();

            
    }

}
