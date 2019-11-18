/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package businessLogic;

import data.Passenger;

/**
 *
 * @author Estudiante
 */
public class DynamicArray {
    private Passenger[] array;
    private int size;
    
    public DynamicArray(int n){
        this.array = new Passenger[n];
        this.size=0;
    }
    
    public void pushBack(Passenger n){
        if(array.length==size) extendArray();
        this.array[size] = n;
        size++;
    }
    
    public void extendArray(){
        Passenger[] array2 = new Passenger[this.array.length*2];
        size=0;
        for(Passenger i: array){
            array2[size]=i;
            size++;
        }
        this.array=array2;
    }
    
    public Passenger get(int i){
        if(i>=size&&i<0)System.out.println("Error: Index out of range");
        return array[i];
    }
    
    public void set(int i, Passenger n){
        if(i<size&&i>=0)array[i]=n;
        else System.out.println("Error: Index out of range");
    }
    
    public void remove(int i){
        if(i>=size&&i<0) System.out.println("Error: Index out of range");
        for(int j=i; j<size-1;j++){
            array[j]=array[j+1];
        }
        size--;        
    }
    
    public int size(){
        return size;
    }
    
    public void print(){
        for (int i=0;i<size;i++){
            System.out.println(array[i].data());
        }
    }    
}
