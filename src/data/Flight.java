/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package data;

import businessLogic.Administrator;
import customImplementations.PassengerAVL;
import java.io.IOException;
import java.io.Serializable;
import java.util.Date;
import ui.UI;
import java.lang.Exception;

/**
 *
 * @author danie
 */
public class Flight implements Serializable {
    
    private static final long serialVersionUID = 1L;
    private Passenger[][] passengers;
    private Route route;
    private int numberOfFlight;
    private Date date;
    private Airline airline;

    public Flight(Route route, int numberOfFlight, Date date, Airline airline,Administrator admin) {
        this.route=route;
        this.numberOfFlight = numberOfFlight;
        this.date=date;
        this.airline=airline;
        this.passengers= new Passenger[10][6]; //60 pasajeros inicialmente
    }

    public Passenger[][] getPassengers() {
        return passengers;
    }

    public Airline getAirline() {
        return airline;
    }
    
    

    public Passenger addPassenger(Administrator admin) throws IOException {

        int id = Integer.valueOf(UI.recorder("su número de documento: "));
        String name = UI.recorder("su nombre: ");
        String email = UI.recorder("su e-mail: ");
        String tel = UI.recorder("su número de teléfono: ");
        UI.printPlane(this);

        Passenger p = new Passenger();
        
        Seat passengerSeat=checkAvailableSeats();

        p = new Passenger(id, name, email,tel,passengerSeat,this); //instanciarlo pero todavía no agregarlo
        passengers[passengerSeat.getRow()][passengerSeat.getColumn()] = p; //añadir al avión
        PassengerAVL passengertree=admin.getAirline().getPassengertree(); //cargamos el árbol de pasajeros.
        passengertree.root=passengertree.insert(passengertree.root, p); //añadir al arbol de pasajeros
      
        return p;  
    }
    
    public Passenger addPassengerInterface(Administrator admin, int id, String name, String email, String tel, String seat) throws IOException, Exception {

        Passenger p = new Passenger();
        
        Seat passengerSeat=checkAvailableSeatsInterface(seat);

        p = new Passenger(id, name, email,tel,passengerSeat,this); //instanciarlo pero todavía no agregarlo
        passengers[passengerSeat.getRow()][passengerSeat.getColumn()] = p; //añadir al avión
        PassengerAVL passengertree=admin.getAirline().getPassengertree(); //cargamos el árbol de pasajeros.
        passengertree.root=passengertree.insert(passengertree.root, p); //añadir al arbol de pasajeros
      
        return p;  
    }
    
    public Seat checkAvailableSeatsInterface(String seat) throws IOException, Exception {   //método para verificar que un asiento seleccionado esté libre

        boolean available = false;
        
        int columnNumber=0;
        int row=0;
        int column=0;

        while (!available) {
            String seatCoord[] = seat.split(" ");
            row = Integer.valueOf(seatCoord[0]) - 1;
            column = seatCoord[1].charAt(0);
            column = Character.toLowerCase(column);
            columnNumber = (int)column - 97; //97 es el ASCII de letra minúscula a

            if (passengers[row][columnNumber] == null) {
                available = true; //se sale del while porque selecciono un asiento válido
                Seat seatObject = new Seat(row, columnNumber);
                return seatObject;
            } else {
                throw new Exception();
            }

        }
        
        Seat seatObject = new Seat(row, columnNumber);
        return seatObject;

          
    }
    
    public Seat checkAvailableSeats() throws IOException {   //método para verificar que un asiento seleccionado esté libre
        UI.printPlane(this); //Muestre la acomodacion de este avion 
       
        String seat = UI.recorder("su nuevo asiento escribiendo el número "
                + "y la letra correspondientes separados por un espacio:");

        boolean available = false;
        
        int columnNumber=0;
        int row=0;
        int column=0;

        while (!available) {
            String seatCoord[] = seat.split(" ");
            row = Integer.valueOf(seatCoord[0]) - 1;
            column = seatCoord[1].charAt(0);
            column = Character.toLowerCase(column);
            columnNumber = (int)column - 97; //97 es el ASCII de letra minúscula a

            if (passengers[row][columnNumber] == null) {
                available = true; //se sale del while porque selecciono un asiento válido
                Seat seatObject = new Seat(row, columnNumber);
                return seatObject;
            } else {
                seat = UI.recorder("un asiento libre");
            }

        }
        
        Seat seatObject = new Seat(row, columnNumber);
        return seatObject;

          
    }
    
    
    

    public Route getRoute() {
        return route;
    }
   
    public int getNumberOfFlight() {
        return numberOfFlight;
    }

    public void setNumberOfFlight(int numberOfFlight) {
        this.numberOfFlight = numberOfFlight;
    }

 
    
    
    
    public String getDateFormat(){
    return Integer.toString(date.getDate())+"/"+Integer.toString(date.getMonth()+1)+"/"+Integer.toString(date.getYear());
    }
    public Date getDate(){
        return date;
    }
 /*   
    public boolean fullFlight(){
        return this.plane.fullPlane();
    }
    
    public void addPassenger(Passenger p){
        plane.addPassenger(p);
    }
    
    public Plane getPlane(){
        return this.plane;
    }    
  */  
    
    public void addPassenger(Passenger p){
        
    }
    
    
    

    public void setDate(Date Date) {
        this.date = Date;
    }

    @Override
    public String toString() {
        return "Flight{" + "route=" + route + ", numberOfFlight=" + numberOfFlight + ", date=" + date + ", airline=" + airline.getName() + '}';
    }


   
    
    

   

    
    
    
    
    
    
    
    
    
    
}
