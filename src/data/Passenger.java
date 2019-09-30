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
public class Passenger {
    private int id;
    private String name;
    private String mail;
    private int tel;
    
    public Passenger(int id, String name, String mail){
        this.id = id;
        this.name = name;
        this.mail = mail;
    }
    public Passenger(int id, String name, String mail, int tel ){
        this.id = id;
        this.name = name;
        this.mail = mail;
        this.tel = tel;
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

    public int getTel() {
        return tel;
    }
    
    public String data(){
        String names = this.name;
        names.replace(" ", "-");
        return Integer.toString(id) + " " + names + " " + mail;
    }
}
