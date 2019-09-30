/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package data;

import static businessLogic.ProTest.airline1;
import static businessLogic.ProTest.destinations;
import static businessLogic.ProTest.flights;
import static businessLogic.ProTest.trialPlane;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.EOFException;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.OutputStreamWriter;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.LinkedList;
import ui.UI;

/**
 *
 * @author danie
 */
public class WriteAndRead {
    
    private static SimpleDateFormat sdf=new SimpleDateFormat("dd/MM/yyyy");
    
    public static void writeFlights()throws FileNotFoundException, IOException{
        FileOutputStream file = new FileOutputStream(new File("vuelos.txt"));
        BufferedWriter write = new BufferedWriter(new OutputStreamWriter(file));
        for(Flight i: flights){
            write.write(i.getAirline()+" "+i.getDestination()+ " " + i.getOrigin()+" "+ i.getDate().toString());
            write.newLine();
        }
        write.close();  
    }
    
    public static void writePassengers(Flight flight)throws FileNotFoundException, IOException{
        FileOutputStream file = new FileOutputStream(new File(Integer.toString(flight.getNumberOfFlight())+".txt"));
        BufferedWriter write = new BufferedWriter(new OutputStreamWriter(file));
        Plane temp = flight.getPlane();
        for (int i = 0; i < temp.row; i++) {
            for (int j = 0; j < temp.column; j++) {
                if(temp.getPassengers()[i][j]!=null){ 
                    Passenger p = temp.getPassengers()[i][j];
                    write.write(p.data());
                    write.newLine();
                }
            }
        }
        write.close(); 
    }
    
    public static void writeDestinations() throws FileNotFoundException, IOException{
        FileOutputStream file = new FileOutputStream(new File("destinations.txt"));
        BufferedWriter write = new BufferedWriter(new OutputStreamWriter(file));
        for(String i: destinations){
            write.write(i);
            write.newLine();
        }
        write.close();      
    }
    
    
    //lectura de archivos txt
    public static void readFlights()throws FileNotFoundException, IOException, ParseException{
        String destine;
        BufferedReader bf = new BufferedReader(new FileReader("destinos.txt"));
        while ((destine = bf.readLine())!=null) {
            String[] data = destine.split(" ");
            Flight newFlight=new Flight(data[1], data[2], Integer.parseInt(data[3]), sdf.parse(data[4]), trialPlane, airline1);
            flights.add(newFlight);
        }
    }
    
    public static void readPassangers()throws FileNotFoundException, IOException{
        String destine;
        for(Flight i: flights){
            BufferedReader bf = new BufferedReader(new FileReader(Integer.toString(i.getNumberOfFlight())+".txt"));
            while ((destine = bf.readLine())!=null) {
                String[] data = destine.split(" ");
                String name= data[1].replace("-", " ");
                Passenger p = new Passenger(Integer.parseInt(data[0]), name, data[2]);
                i.addPassenger(p);
            }
        }
    }
    
    public static void readDestinations() throws FileNotFoundException, IOException{
        String destine;
        BufferedReader bf = new BufferedReader(new FileReader("destinos.txt"));
        while ((destine = bf.readLine())!=null) {
            destinations.add(destine);
        }
    }
}
