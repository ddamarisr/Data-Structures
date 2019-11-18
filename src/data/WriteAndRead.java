/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package data;

import businessLogic.Administrator;
import java.io.EOFException;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.text.SimpleDateFormat;
import java.util.LinkedList;

/**
 *
 * @author danie
 */
public class WriteAndRead {
    
    private static SimpleDateFormat sdf=new SimpleDateFormat("dd/MM/yyyy");
    
    public static void writeFlights(Administrator defo)throws FileNotFoundException, IOException{
        LinkedList<Flight> flights=defo.getAirline().getFlights();
        FileOutputStream file = new FileOutputStream(new File("flights.txt"));
        ObjectOutputStream out = new ObjectOutputStream(file);
        for(Flight flight: flights){
            out.writeObject(flight);
        }
        out.close();  
        file.close();
    }
  /*  
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
    */
  /*  public static void writeDestinations() throws FileNotFoundException, IOException{
        FileOutputStream file = new FileOutputStream(new File("destinations.txt"));
        BufferedWriter write = new BufferedWriter(new OutputStreamWriter(file));
        for(String i: destinations){
            write.write(i);
            write.newLine();
        }
        write.close();      
    }*/
    
    
    //lectura de archivos txt
    public static LinkedList<Flight> reader(String path) throws FileNotFoundException,
            IOException, ClassNotFoundException {
        
        LinkedList<Flight> flights=new LinkedList();

        // The following call can throw a FileNotFoundException or an IOException.9
        // Since this is probably better dealt with in the calling function, 
        // reader is made to throw these exceptions instead.
        
        FileInputStream fi = new FileInputStream(new File(path)); //archivo
        
        ObjectInputStream oi = new ObjectInputStream(fi);//objeto

        while (true) {
            try {
                // Read the next object from the stream. If there is none, the
                // EOFException will be thrown.
                // This call might also throw a ClassNotFoundException, which can be passed
                // up or handled here.
                Flight flight= (Flight) oi.readObject();
                flights.add(flight);
                
            } catch (EOFException e) {
                // If there are no more objects to read, return what we have.
                oi.close();
                return flights;
            } 
        }
    }
 /*
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
   */ 
  /*  public static void readDestinations() throws FileNotFoundException, IOException{
        String destine;
        BufferedReader bf = new BufferedReader(new FileReader("destinations.txt"));
        while ((destine = bf.readLine())!=null) {
            destinations.add(destine);
        }
    }*/
}
