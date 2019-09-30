/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package data;

/**
 *
 * @author danie
 */
public class Plane {
    int row;
    int column;
    private Passenger[][] passengers;
    private int[] position = {0,0};
    private int capacity;
    
    public Plane(int a){
        this.capacity = 0;
        if(a==1) {
            this.passengers= new Passenger[5][6];
            this.row = 5;
            this.column = 6;
        }
        else if(a==2) {
            this.passengers= new Passenger[5][6];
            this.row = 5;
            this.column = 6;
        }
        else if(a==3) {
            this.passengers= new Passenger[5][6];
            this.row = 5;
            this.column = 6;
        }
        else System.out.println("Número inválido en la clase Plane");
    }
    
    public void addPassenger(int a, int b, Passenger p){
        this.passengers[a][b] = p;
    }
    
    public void addPassenger(Passenger p){
        if (capacity!=passengers.length){
           this.passengers[position[0]][position[1]] = p; 
           if(position[0]==row){
               position[1]+=1;
               position[0]=0;
           }
           else position[0]+=1;
        }
    }
    
    public void removePassenger(int id){
        for (int i = 0; i < this.row; i++) {
            for (int j = 0; j < this.column; j++) {
                if (this.passengers[i][j].getId()==id) this.passengers[i][j] = null;
            }
        }
    }
    
    public Passenger[][] getPassengers() {
        return passengers;
    }
    
    public void setDimensions(){
        if(this.passengers.length==30){
            this.row = 5;
            this.column = 6;
        }
    }
    
    public boolean fullPlane(){
        return passengers.length != capacity;
    }
}
