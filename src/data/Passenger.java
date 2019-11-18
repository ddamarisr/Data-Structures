/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package data;

import java.io.Serializable;

/**
 *
 * @author danie
 */
public class Passenger implements Serializable {
    
    private static final long serialVersionUID = 1L;
    
    private int id;
    private String name;
    private String mail;
    private String tel;
    private Seat seat;
    private Flight flight;
    
    
    
    public Passenger(int id, String name, String mail,String tel,Seat seat,Flight flight){
        this.id = id;
        this.name = name;
        this.mail = mail;
        this.seat=seat;
        this.tel=tel;
        this.flight=flight;
    }

    public Passenger(int id, String name, String mail) {
        this.id = id;
        this.name = name;
        this.mail = mail;
    }

    public Passenger() {
    }
    

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getMail() {
        return mail;
    }

    public String getTel() {
        return tel;
    }

    public Flight getFlight() {
        return flight;
    }

    public void setSeat(Seat seat) {
        this.seat = seat;
    }

    public Seat getSeat() {
        return seat;
    }
    
    
    
    

    @Override
    public String toString() {
        return "Passenger{" + "id=" + id + ", name=" + name + ", mail=" + mail + ", tel=" + tel + ", seat=" + seat +",flight="+flight.getNumberOfFlight()+ '}';
    }
    
    
    
    
    public String data(){
        String names = this.name;
        names.replace(" ", "-");
        return Integer.toString(id) + " " + names + " " + mail;
    }
}
