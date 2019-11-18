/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ui;

import businessLogic.Administrator;
import businessLogic.User;
import customImplementations.Node;
import data.Flight;
import data.Passenger;
import data.Route;
import data.WriteAndRead;
import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStreamReader;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.LinkedList;

/**
 *
 * @author danie
 */
public class UI {

    public static String welcome() throws IOException, FileNotFoundException, ParseException {
        System.out.println("Bienvenido al Terminal. Por favor indique el número correspondiente a su rol: " + "\n");
        System.out.println("1-------- Administrador");
        System.out.println("2-------- Cliente");
        System.out.println("3-------- Pruebas");

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
        /*System.out.println("4-------- Agregar Destino");
        System.out.println("5-------- Eliminar Destino");*/
        System.out.println("4-------- Mostrar Destinos");
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
                //defo.addFlight();
                break;
            case 3:
                searchFlights(defo);
                System.out.println("\nIngrese 1 para volver al menú principal: ");
                UI.verification(1);
                UI.AdminMenu(defo);
                break;  
            case 4:
                for (Flight fligths:defo.getAirline().getFlights()){
                    System.out.println(fligths.getRoute().getDestination());
                }
                UI.verification(1);
                UI.AdminMenu(defo);
                break;
                /*
                defo.addDestination();
                System.out.println("\nIngrese 1 para volver al menú principal: ");
                UI.verification(1);
                UI.AdminMenu(defo);
                */
            case 5:
                break;
                /*
                defo.deleteDestination();
                System.out.println("\nIngrese 1 para volver al menú principal: ");
                UI.verification(1);
                UI.AdminMenu(defo);
                break;
                */
            case 6:
                /*
                for(String i:destinations) System.out.println(i);
                System.out.println("\nIngrese 1 para volver al menú principal: ");
                UI.verification(1);
                UI.AdminMenu(defo);
                */
                break;
            case 7:
            
                break;
            case 8:
                //WriteAndRead.writer(defo.getAirline().getFlights(), "vuelos.txt");
                WriteAndRead.writeFlights(defo);
                /*
                WriteAndRead.writeDestinations();
                for(Flight i: flights){
                WriteAndRead.writePassengers(i);
                }*/              
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
        for (Flight i : defo.getAirline().getFlights()) {
            System.out.println(i.toString());
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
            
            int index=defo.getAirline().getFlights().indexOf(posFlights.get(posIndex));
            
            modify(index,defo);
           
        }

    }
    
    public static void modify(int index, Administrator defo) throws IOException, ParseException {
        
        System.out.println("El registro seleccionado es: "+ defo.getAirline().getFlights().get(index));
        
        System.out.println("");
        
        System.out.println("Por favor ingrese el numero correspondiente a la opción que desea realizar: ");
        System.out.println("1-------- Cancelar Vuelo");
        System.out.println("2-------- Modificar Información del Vuelo");
        System.out.println("3---------Ver lista de pasajeros");
        System.out.println("4---------Consultar acomodación");
        System.out.println("5-------- Volver a buscar");
        System.out.println("6-------- Regresar al menú");

        int key = verification(6);

        switch (key) {
            case 1:
                defo.deleteFlight(index);
                System.out.println("El vuelo ha sido cancelado con éxito");
                break;
            case 2:
                UI.edit(index, defo);
                break;
            case 3:
                UI.PassengersList(defo,index);
                break;
            case 4:
                UI.printPlane(defo.getAirline().getFlights().get(index));
                break;
            case 5:
                UI.searchFlights(defo);
                break;
            case 6:
                UI.AdminMenu(defo);
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
        System.out.println(defo.getAirline().getFlights().get(index));
        System.out.println();
    }
    
    public static void UserMenu(User user,Administrator admin) throws IOException, ParseException{
        System.out.println("");
        System.out.println("Bienvenido al sistema de reservas de vuelos.");
        System.out.println("Por favor ingrese el numero correspondiente a la opción que desea llevar a cabo: "+ "\n");
        System.out.println("1-------- Iniciar Reservación"); 
        System.out.println("2-------- Buscar Reservación");//busqueda en avl
        System.out.println("3-------- Salir");
        
        
        int opcion = verification(3);
        
        switch (opcion) {
            case 1:
                userFindFlight(user,admin);
                break;
                        
            case 2:
                UI.userFindreservation(user, admin);
             
                /*WriteAndRead.writeFlights();
                WriteAndRead.writeDestinations();
                for(Flight i: flights){
                WriteAndRead.writePassengers(i);
                } */               
                break;
            case 3:
                WriteAndRead.writeFlights(admin);
                System.exit(0);
                break;
            default:
                break;
        }
    }
    
    public static void userUpdatereservations(User user, Administrator defo, Passenger p) throws IOException, ParseException {
        System.out.println("Por favor ingrese el numero correspondiente a la opción que desea llevar a cabo: " + "\n");
        System.out.println("1-------- Cambiar asiento");
        System.out.println("2-------- Cancelar reservación");
        System.out.println("3-------- Modificar datos personales");
        System.out.println("4-------- Regresar al menú principal");

        int opcion = verification(4);

        switch (opcion) {
            case 1:
                user.changeSeats(p);
                break;
            case 2:
                user.deleteReservation(defo, p);
                break;
            case 3:
                break;
            case 4:
                UI.UserMenu(user, defo);
                break;

            default:
                break;

        }
        
        System.out.println("Su cambio ha sido guardado exitosamente. Presione 1 para avanzar al menu");
        UI.verification(1);
        UI.UserMenu(user, defo);
    }
    
    public static void userFindreservation(User user, Administrator defo) throws IOException, ParseException {
        System.out.println("");
        System.out.println("Bienvenido al sistema de búsqueda de reservaciones");

        String id = UI.recorder("el número de la reserva (documento del pasajero): ");

        user.findReservations(defo, id);

        Passenger found = user.findReservations(defo, id);

        if (found == null) {
            System.out.println("La reserva NO fue encontrada");
            System.out.print("Presione 1 para reintentar la busqueda o presione 2 para volver al menú de usuario: ");

            int opcion = verification(3);

            switch (opcion) {
                case 1:
                    UI.userFindreservation(user, defo);
                    break;

                case 2:
                    UI.UserMenu(user, defo);
                    break;
                default:
                    break;

            }

        } else {
            System.out.println("Su reserva ha sido encontrada: " + found);
            UI.userUpdatereservations(user, defo, found);
        }
    }
    
    
    public static void userFindFlight(User user, Administrator defo) throws IOException, ParseException {

        System.out.println("Bienvenido a la interfaz de búsqueda. Para iniciar "
                + "su reservación seleccione su ruta de interés: " + "\n");
        
        
        ArrayList <Route> uniqueRoutes=new ArrayList<>();
        
        for (Flight fligths : defo.getAirline().getFlights()) {
            Route temp = fligths.getRoute();
            if (!uniqueRoutes.contains(temp)) {
                uniqueRoutes.add(temp); //no presentar rutas duplicadas 
            }
        }

        int i = 1;

        for (Route route : uniqueRoutes) {
            System.out.println(i + ". " + route);
            i++;
        }

        int key = verification(uniqueRoutes.size());

        ArrayList<Flight> posFlights = user.FindFlightbyRoute(defo, uniqueRoutes.get(key - 1));

        System.out.println("Para la ruta seleccionada contamos con vuelos en las"
                + " fechas indicadas\n");

        i = 1;

        for (Flight flight : posFlights) {
            System.out.println(i+". Fecha:  " + flight.getDate() + " Número de Vuelo: "
                    + flight.getNumberOfFlight());
            i++;
        }

        System.out.print("Indique el número correspondiente la opción que"+""
                + "desea reservar\n");
        
        key = verification(posFlights.size());
        
        Passenger added=posFlights.get(key-1).addPassenger(defo);
        
        System.out.println("RESERVA EXITOSA");
        System.out.println("Recuerde que su número de reserva es: "+added.getId());
        System.out.println("Presione 1 para avanzar al menu");
        UI.verification(1);
        UI.UserMenu(user, defo);

        
    }


    
    public static void printPlane(Flight flight) {
        
        System.out.println();
        Passenger[][] passengers = flight.getPassengers();
        System.out.println("Vista de los asientos del vuelo");
        
        System.out.println();
        System.out.printf("%14s", "ABC" + "  " + "DEF");
        System.out.println();
        
        for (int i = 0; i < passengers.length; i++) {
            System.out.printf("%-6d", (i + 1));
            for (int j = 0; j < passengers[0].length; j++) {
                if (j == (passengers[0].length / 2)) {
                    System.out.print("  ");
                }
                if (passengers[i][j] == null) {
                    System.out.print("_");
                } else {
                    System.out.print("X");
                }
            }
            System.out.println();
        }
        
        System.out.println("X=Ocupado");

    }
    
    public static ArrayList<String> printPlaneInterfaz(Flight flight) {
        Passenger[][] passengers = flight.getPassengers();
        ArrayList<String> lines = new ArrayList<>();
        lines.add("   ABC" + "  " + "DEF");        
        for (int i = 0; i < passengers.length; i++) {
            String line = "";
            line+=Integer.toString(i+1);
            if(i!=9) line+="   ";
            else line+="  ";
            for (int j = 0; j < passengers[0].length; j++) {
                if (j == (passengers[0].length / 2)) {
                    line+="  ";
                }
                if (passengers[i][j] == null) {
                    line+="_";
                } else {
                    line+="X";
                }
            }
            lines.add(line);
        }
        return lines;
    }
    
    
    
    public static void PassengersList(Administrator admin,int index){
        Passenger passengers[][]=admin.getAirline().getFlights().get(index).getPassengers();
        for (int i=0;i<passengers.length;i++){
            for (int j=0;j<passengers[0].length;j++){
                if(passengers[i][j]!=null){
                    System.out.println(passengers[i][j]);
                }
            }
        }
        
    }

    
    
    /*
    public static void reserve(User user) throws IOException{
        System.out.println("Escoja un lugar de origen: ");
        for(String i: destinations){
            System.out.println(i);
        }
        
        System.out.println("Escoja un destino: ");
        for(String i: destinations){
            System.out.println(i);
        }
        String input=recorder(""); //la función recorder es un Bufferedreader 
        
        if(!destinations.contains(input)) {
            System.out.println("No se ha encontrado ningún destino con el nombre especificado");
            UserMenu(user);
        } else  {
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
        
    }*/
    
}
