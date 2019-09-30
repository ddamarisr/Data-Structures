/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ui;

import businessLogic.Administrator;
import static businessLogic.ProTest.airline1;
import static businessLogic.ProTest.destinations;
import static businessLogic.ProTest.flights;
import static businessLogic.ProTest.trialPlane;
import businessLogic.User;
import data.Flight;
import data.WriteAndRead;
import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStreamReader;
import java.text.ParseException;
import java.util.LinkedList;

/**
 *
 * @author danie
 */
public class UI {

    public static String welcome() throws IOException, FileNotFoundException, ParseException {
        
        try {
            WriteAndRead.readFlights();
        } catch (Exception e) {
        }
        try {
            WriteAndRead.readDestinations();
        } catch (Exception e) {
        }
        try {
            WriteAndRead.readPassangers();
        } catch (Exception e) {
        }

        System.out.println("Bienvenido al Terminal. Por favor indique el número correspondiente a su rol: " + "\n");
        System.out.println("1-------- Administrador");
        System.out.println("2-------- Cliente");
        System.out.println("3-------- Prueba de datos");

        int key = verification(3);

        switch (key) {
            case 1:
                return "Admin";
            case 2:
                return "User";
            case 3:
                return "Bench";
            default:
                break;
        }
        
        return ""; 
    }

    public static int verification(int upper) throws IOException { //Este método sirve para verificar las entradas por consola cuando son números
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        String input = reader.readLine();
        char verification = input.charAt(0);
        int value = Character.getNumericValue(verification);

        if (value <= upper && value >= 1) {
            return value;
        } else {
            System.out.println("\nPor favor ingrese una opción válida\n");
            return verification(upper);
        }
    }
    
    public static void AdminMenu(Administrator defo) throws IOException, ParseException {
            
        //System.out.println("Bienvenido al Terminal para administrativos de " + defo.getAirline().getName() + " NIT:" + defo.getAirline().getNit()+ "\n");

        System.out.println("Por favor ingrese el numero correspondiente a la opción que desea llevar a cabo: "+ "\n");

        System.out.println("1-------- Mostrar Vuelos"); // Vamos a eliminar y añadir datos. No queda de otra que usar listas enlazadas para los vuelos
        System.out.println("2-------- Agregar Vuelos");
        System.out.println("3-------- Buscar Vuelos");
        System.out.println("4-------- Agregar Destino");
        System.out.println("5-------- Eliminar Destino");
        System.out.println("8-------- Salir");
        
        int opcion = verification(8);
        
        switch (opcion) {
            case 1:
                UI.showFlights(defo);
                System.out.println("\nIngrese 1 para volver al menú principal: ");
                UI.verification(1);
                UI.AdminMenu(defo);
                break;
                
            case 2:
                defo.addFlight(airline1,trialPlane);
                break;
            case 3:
                searchFlights(defo);
  
                break;  
            case 4:
                defo.addDestination();
                break;   
            case 5:
               
                break;
            case 6:
                
                break;
            case 7:
            
                break;
            case 8:
                //WriteAndRead.writer(defo.getAirline().getFlights(), "vuelos.txt");
                
                WriteAndRead.writeFlights();
                WriteAndRead.writeDestinations();
                for(Flight i: flights){
                WriteAndRead.writePassengers(i);
                }                
                System.exit(0);
                break;
            default:
                break;
       
        }
    }
    
    public static String recorder(String message) throws IOException{
        BufferedReader reader=new BufferedReader(new InputStreamReader(System.in));
        System.out.println("Por favor ingrese "+message);
        String input=reader.readLine();
        return input;
    }
   /* 
    public static void createFlight(Administrator defo) throws IOException, ParseException{
        
        if(defo.getAirline().getAvailablePlanes()==0){
            System.out.println("ATENCIÓN: NO HAY AVIONES DISPONIBLES");
            System.out.println("Ingrese 1 para continuar al menú principal");
            int opcion=UI.verification(1);
            UI.AdminMenu(defo);
        }
        
        System.out.println();
        System.out.println("\nPor favor proporcione los siguientes datos \n");
        defo.addFlight();
        System.out.println("\nAgregado con éxito.\n");
        System.out.println("Si desea seguir agregando vuelos ingrese 1, de lo contrario ingrese 2 para regresar al menu principal: ");
        int opcion=UI.verification(2);
        switch(opcion){
            case 1:
                UI.createFlight(defo);
                break;  
            case 2:
                UI.AdminMenu(defo);
                break;
            default: 
                break;
        }
    }
    
*/
    public static void showFlights(Administrator defo) {
        for (Flight i : flights) {
            System.out.println(i);
        }
    }

    public static void searchFlights(Administrator defo) throws IOException, ParseException {

        System.out.println("Bienvenido a la interfaz de búsqueda. Por favor indique el número correspondiente al criterio que desea filtrar " + "\n");

        System.out.println("1-------- Ruta");
        System.out.println("2-------- Número de vuelo");
        System.out.println("3-------- Volver al menú");
        
        LinkedList<Flight> posFlights;

        int key = verification(3);
        
        if(key==3){
            UI.AdminMenu(defo);
        }

        posFlights = defo.findFlight(key);

        if (posFlights.isEmpty()) {

            System.out.println("NO SE ENCONTRARON REGISTROS CON ESTE CRITERIO DE BÚSQUEDA");

            System.out.println("Seleccione qué hacer a continuación");
            System.out.println("1-------- Reintentar");
            System.out.println("2-------- Volver al menú");

            key = verification(2);

            if (key == 1) {
                searchFlights(defo);
            } else if (key == 2) {
                AdminMenu(defo);
            }

        } else {

            System.out.println("LOS SIGUIENTES VUELOS CUMPLEN CON LOS CRITERIOS DE BÚSQUEDA");

            int counter = 1;

            for (Flight temp : posFlights) {

                System.out.println(counter+"." + " " + temp.toString());

                counter++;

            }
            
            System.out.println("Por favor ingrese el número correspondiente al resultado"                  
                    + " que desea consultar");
            
            int posIndex=verification(posFlights.size())-1;
            
            int index=flights.indexOf(posFlights.get(posIndex));
            
            modify(index,defo);
           
        }

    }
    
    public static void modify(int index, Administrator defo) throws IOException, ParseException {
        
        System.out.println("El registro seleccionado es: "+ flights.get(index));
        
        System.out.println("");
        
        System.out.println("Por favor ingrese el numero correspondiente a la opción que desea realizar: ");
        System.out.println("1-------- Cancelar Vuelo");
        System.out.println("2-------- Modificar Información del Vuelo");
        System.out.println("3-------- Volver a buscar");
        System.out.println("4-------- Regresar al menú");

        int key = verification(4);

        switch (key) {
            case 1:
                defo.deleteFlight(index);
                System.out.println("El vuelo ha sido cancelado con éxito");
                break;
            case 2:
                UI.edit(index, defo);
                break;
            case 3:
                UI.searchFlights(defo);
                break;
            case 4:
                UI.AdminMenu(defo);
                break;
            default:
                break;
        }
        
        System.out.println();
        System.out.println("Si desea realizar  más consultas ingrese 1, de lo contrario ingrese 2 para regresar al menú principal: ");
        key = UI.verification(2);
        switch (key) {
            case 1:
                UI.searchFlights(defo);
                break;
            case 2:
                UI.AdminMenu(defo);
                break;
            default:
                break;
        }

    }
    
    public static void edit (int index, Administrator defo) throws IOException, ParseException {
        
        System.out.println("");
        
        System.out.println("Por favor ingrese el número correspondiente a la opción que desea realizar: ");
        System.out.println("1-------- Cambiar fecha del vuelo");
        System.out.println("2-------- Cambiar número del vuelo");

        int key = verification(2);

        defo.modFlightInfo(index, key);

        System.out.println("\nModificado con éxito.");
        System.out.println("\nLos nuevos estados del vuelo son: \n");
        System.out.println(flights.get(index));
        System.out.println();
    }
    
    public static void UserMenu(User user) throws IOException{
        System.out.println("Bienvenido al sistema de reservas de vuelos.");
        System.out.println("Por favor ingrese el numero correspondiente a la opción que desea llevar a cabo: "+ "\n");

        System.out.println("1-------- Iniciar Reservación"); // Vamos a eliminar y añadir datos. No queda de otra que usar listas enlazadas para los vuelos
        System.out.println("8-------- Salir");
        
        int opcion = verification(2);
        
        switch (opcion) {
            case 1:
                reserve(user);
            case 2:
                WriteAndRead.writeFlights();
                WriteAndRead.writeDestinations();
                for(Flight i: flights){
                WriteAndRead.writePassengers(i);
                }                
                System.exit(0);
                break;
            default:
                break;
        }
    }
    
    public static void reserve(User user) throws IOException{
        System.out.println("Escoja un destino: ");
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        String input = reader.readLine();
        if(!destinations.contains(input)) System.out.println("No se ha encontrado ningún destino con el nombre especificado");
        else{
            System.out.println("Ingrese el número del vuelo que desea reservar: ");
            for(Flight i:flights){
                if(i.getDestination()==input){
                System.out.println(i.toString());
                }
            }
            String input2 = reader.readLine();
            Flight temp = null;
            for(Flight i:flights){
                if(input2==Integer.toString(i.getNumberOfFlight())) temp = i;
            }
            if(temp!=null){
                System.out.println("Ingrese sus datos personales:");
                user.addPassenger(temp);
                System.out.println("El vuelo ha sido reservado");
                UserMenu(user);
            }
            else {
                System.out.println("El dato proporcionado no es correcto");
                reserve(user);
            }
            
        }
    }
    
}
