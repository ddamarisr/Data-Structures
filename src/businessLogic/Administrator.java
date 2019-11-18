/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package businessLogic;


import data.Airline;
import data.Flight;
import data.Route;
import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.LinkedList;
import ui.UI;
import static ui.UI.recorder;

/**
 *
 * @author danie
 */
public class Administrator {
    
    private Airline airline;

    private SimpleDateFormat sdf=new SimpleDateFormat("dd/MM/yyyy");

    public Administrator(Airline airline) {
        this.airline=airline;
    }

    public Airline getAirline() {
        return airline;
    }

    /*
    public void addFlight() throws IOException, ParseException{ 
        this.airline.addFlight(this);
        UI.AdminMenu(this);
    }
    */
    
    public void addFlight(Administrator defo, String origen, String destination, String num, String dat) throws IOException, ParseException{ 
        this.airline.addFlight(this, origen, destination, num, dat);
    }
    /*
    public void addDestination() throws IOException, ParseException{
        
        String destination=UI.recorder("el destino que desea agregar");
        destinations.add(destination);
        UI.AdminMenu(this);
    }
    
    public void deleteDestination() throws IOException, ParseException{
        
        String destination = UI.recorder("el destino que desea eliminar");
        try {
            destinations.remove(destination);
        } catch (Exception e) {
        }                
        UI.AdminMenu(this);
    }*/
    
    public LinkedList<Flight> findFlight(int key) throws IOException, ParseException{
        
        LinkedList<Flight> posFlights = new LinkedList<>();
        
        switch (key) {
            
            case 1:
                {
                    String origin = recorder("Ingrese la ciudad de origen");
                    String destination = recorder("Ingrese la ciudad de destino");
                    Route otherRoute=new Route(origin,destination);
                    for (Flight temp : airline.getFlights()) {
                        if ( otherRoute.equals(temp.getRoute()))
                        {
                            posFlights.add(temp);
                        }
                    }       break;
                }
            case 2:
                {
                    String flightNum = recorder("Ingrese el número de vuelo");
                    int flNum = Integer.valueOf(flightNum);
                    for (Flight temp : airline.getFlights()) {
                        if (flNum == temp.getNumberOfFlight()) {
                            posFlights.add(temp);
                        }
                    }       break;
                }
            case 3:
                UI.AdminMenu(this);
                break;
            default:
                break;
        }
        
        return posFlights;
    }
    
    public void deleteFlight(int index) throws IOException, ParseException {
        airline.getFlights().remove(index);
    }
    
    public void modFlightInfo(int index,int key) throws IOException, ParseException { //Update

        switch (key) {
            case 1:
                Date date=sdf.parse(UI.recorder("Nueva fecha del vuelo como dd/mm/aaaa"));
                airline.getFlights().get(index).setDate(date);
                break;
            case 2:
                int newFlightNumber=Integer.parseInt(UI.recorder("Nuevo número del vuelo"));
                airline.getFlights().get(index).setNumberOfFlight(newFlightNumber);
                break;
            default:
                break;
        }
    }   
}
