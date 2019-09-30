/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package data;

import java.io.Serializable;
import java.util.LinkedList;
import java.util.Date;

/**
 *
 * @author danie
 */
public class Flight implements Serializable {
    
    private String origin;
    private String destination;
    private int numberOfFlight;
    private Date date;
    private Plane plane;
    private Airline airline;

    public Flight(String origin, String destination, int numberOfFlight, Date date, Plane plane, Airline airline) {
        this.origin = origin;
        this.destination = destination;
        this.numberOfFlight = numberOfFlight;
        this.date=date;
        this.plane=plane;
        this.airline=airline;
    }
    
    public String getOrigin() {
        return origin;
    }

    public String getDestination() {
        return destination;
    }

    public int getNumberOfFlight() {
        return numberOfFlight;
    }

    public void setNumberOfFlight(int numberOfFlight) {
        this.numberOfFlight = numberOfFlight;
    }
    
    public String getAirline() {
        return airline.getName();
    }
   
    public Date getDate(){
        return date;
    }
    
    public boolean fullFlight(){
        return this.plane.fullPlane();
    }
    
    public void addPassenger(Passenger p){
        plane.addPassenger(p);
    }
    
    public Plane getPlane(){
        return this.plane;
    }    
    
    private static final long serialVersionUID = 1L;
    
    private LinkedList<Passenger> passengers;
    
    private int numberOfPassengers=10;

    

    public void setDate(Date Date) {
        this.date = Date;
    }
    
    

    @Override
    public String toString() {
        return "Flight{" + ", numberOfFlight=" + numberOfFlight + "origin=" + origin + ", destination=" + destination + ", Date=" + date + '}';
    }
    

   
    
    

   

    
    
    
    
    
    
    
    
    
    
}
