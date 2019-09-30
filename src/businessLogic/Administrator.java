/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package businessLogic;

import static businessLogic.ProTest.airline1;
import static businessLogic.ProTest.destinations;
import static businessLogic.ProTest.flights;
import data.Airline;
import data.Flight;
import data.Plane;
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

    private SimpleDateFormat sdf=new SimpleDateFormat("dd/MM/yyyy");

    public Administrator() {
    }
   
    public void addFlight(Airline airline, Plane plane) throws IOException, ParseException{
        
        String origen=UI.recorder("la ciudad de origen");
        String destination=UI.recorder("la ciudad de destino");
        int number=Integer.parseInt(UI.recorder("el número de vuelo"));
        Date date=sdf.parse(UI.recorder("La fecha del vuelo como dd/mm/aaaa"));
        Flight newFlight=new Flight(origen, destination, number, date, plane, airline);
        flights.add(newFlight);
        UI.AdminMenu(this);
    }
    
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
    }
    
    public LinkedList<Flight> findFlight(int key) throws IOException, ParseException{
        
        LinkedList<Flight> posFlights = new LinkedList<>();
        switch (key) {
            
            case 1:
                {
                    String origin = recorder("Ingrese la ciudad de origen");
                    String destination = recorder("Ingrese la ciudad de destino");
                    for (Flight temp : flights) {
                        if (origin.equalsIgnoreCase(temp.getOrigin()) && destination.equalsIgnoreCase(temp.getDestination())) {
                            posFlights.add(temp);
                        }
                    }       break;
                }
            case 2:
                {
                    String flightNum = recorder("Ingrese el número de vuelo");
                    int flNum = Integer.valueOf(flightNum);
                    for (Flight temp : flights) {
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
        flights.remove(index);
    }
    
    public void modFlightInfo(int index,int key) throws IOException, ParseException { //Update

        switch (key) {
            case 1:
                Date date=sdf.parse(UI.recorder("Nueva fecha del vuelo como dd/mm/aaaa"));
                flights.get(index).setDate(date);
                break;
            case 2:
                int newFlightNumber=Integer.parseInt(UI.recorder("Nuevo número del vuelo"));
                flights.get(index).setNumberOfFlight(newFlightNumber);
                break;
            default:
                break;
        }
    }   
}
