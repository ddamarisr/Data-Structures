/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package businessLogic;

import data.Passenger;
import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.text.DecimalFormat;
import java.util.LinkedList;
import java.util.Scanner;

/**
 *
 * @author danie
 */
public class Bench {
    
    public static Passenger[] insertArrayHalf(Passenger [] pasArray,Passenger passenger){
        
        Passenger newPasArray[] = new Passenger[pasArray.length+1];
        
        System.arraycopy(pasArray, 0, newPasArray, 0, (pasArray.length)/2);
        
        newPasArray[(pasArray.length)/2]=passenger;
        
        for (int i=((pasArray.length)/2)+1;i<newPasArray.length;i++){
            newPasArray[i]=pasArray[i-1];
        }
        
        return newPasArray;
        
     
    }
    
    public static Passenger[] appendArray(Passenger[] pasArray, Passenger passenger) {

        Passenger newPasArray[] = new Passenger[pasArray.length + 1];

        System.arraycopy(pasArray, 0, newPasArray, 0, pasArray.length);

        newPasArray[pasArray.length] = passenger;
        
        return newPasArray;

    }
    
    public static Passenger[] deleteArrayHalf(Passenger[] pasArray) {

        Passenger newPasArray[] = new Passenger[pasArray.length - 1];

        System.arraycopy(pasArray, 0, newPasArray, 0, pasArray.length / 2);

        for (int i = ((pasArray.length) / 2) + 1; i < newPasArray.length; i++) {
            newPasArray[i] = pasArray[i + 1];
        }

        return newPasArray;

    }
    

    public static void insertion(int n, String relative) throws IOException {

        LinkedList<Passenger> passengers = new LinkedList<>();
        Passenger pasArray[] = new Passenger[n];

        Path path = Paths.get(relative);

        Scanner scanner = new Scanner(path);
        int i = 0;

        while (scanner.hasNextLine()) {
            String line = scanner.nextLine();
            String[] data = line.split(" ");
            Passenger p1 = new Passenger(Integer.parseInt(data[2]), data[0] + " " + data[1], data[3]);
            passengers.add(p1);
            pasArray[i] = p1;
            i++;
        }
        
        System.out.println(passengers.size()+" datos");
        
        Passenger p1 = new Passenger(000000,"INSERTADO","INSERTADO");
        
        long startTime = System.nanoTime();
        
        passengers.add(n/2, p1);
        
        long endTime = System.nanoTime();
        
        long elapsedTime=endTime-startTime;
        
        double seconds = ((double)elapsedTime / 1000000);
     
        System.out.println("Tiempo en LISTA ENLAZADA : " + new DecimalFormat("#.##########").format(seconds) + " mS");
        
        startTime = System.nanoTime();
        
        pasArray=insertArrayHalf(pasArray,p1);
        
        endTime = System.nanoTime();
        
        elapsedTime=endTime-startTime;
        
        seconds = ((double)elapsedTime / 1000000);
     
        System.out.println("Tiempo en ARRAY : " + new DecimalFormat("#.##########").format(seconds) + " mS");
        
    
}
    
    public static void delete (int n, String relative) throws IOException {
        
        LinkedList<Passenger> passengers = new LinkedList<>();
        Passenger pasArray[] = new Passenger[n];

        Path path = Paths.get(relative);

        Scanner scanner = new Scanner(path);
        int i = 0;

        while (scanner.hasNextLine()) {
            String line = scanner.nextLine();
            String[] data = line.split(" ");
            Passenger p1 = new Passenger(Integer.parseInt(data[2]), data[0] + " " + data[1], data[3]);
            passengers.add(p1);
            pasArray[i] = p1;
            i++;
        }
        
        System.out.println(passengers.size()+" datos");
        
        
        long startTime = System.nanoTime();
        
        passengers.remove(n/2);
        
        long endTime = System.nanoTime();
        
        long elapsedTime=endTime-startTime;
        
        double seconds = ((double)elapsedTime / 1000000);
     
        System.out.println("Tiempo en LISTA ENLAZADA : " + new DecimalFormat("#.##########").format(seconds) + " mS");
        
        startTime = System.nanoTime();
        
        pasArray=deleteArrayHalf(pasArray);
        
        endTime = System.nanoTime();
        
        elapsedTime=endTime-startTime;
        
        seconds = ((double)elapsedTime / 1000000);
     
        System.out.println("Tiempo en ARRAY : " + new DecimalFormat("#.##########").format(seconds) + " mS");
        
    
}
    
  
    
    public static void append (int n, String relative) throws IOException {
        
        LinkedList<Passenger> passengers = new LinkedList<>();
        Passenger pasArray[] = new Passenger[n];

        Path path = Paths.get(relative);

        Scanner scanner = new Scanner(path);
        int i = 0;

        while (scanner.hasNextLine()) {
            String line = scanner.nextLine();
            String[] data = line.split(" ");
            Passenger p1 = new Passenger(Integer.parseInt(data[2]), data[0] + " " + data[1], data[3]);
            passengers.add(p1);
            pasArray[i] = p1;
            i++;
        }
        
        System.out.println(passengers.size()+" datos");
        
        Passenger p1 = new Passenger(000000,"INSERTADO","INSERTADO");
        
        long startTime = System.nanoTime();
        
        passengers.add(p1);
        
        long endTime = System.nanoTime();
        
        long elapsedTime=endTime-startTime;
        
        double seconds = ((double)elapsedTime / 1000000);
     
        System.out.println("Tiempo en LISTA ENLAZADA : " + new DecimalFormat("#.##########").format(seconds) + " mS");
        
        startTime = System.nanoTime();
        
        appendArray(pasArray,p1);
        
        endTime = System.nanoTime();
        
        elapsedTime=endTime-startTime;
        
        seconds = ((double)elapsedTime / 1000000);
     
        System.out.println("Tiempo en ARRAY : " + new DecimalFormat("#.##########").format(seconds) + " mS");
        
    
}
    
    
    
    
    
}
