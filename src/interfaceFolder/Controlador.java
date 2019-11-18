/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package interfaceFolder;

import businessLogic.Administrator;
import businessLogic.User;
import data.Airline;
import java.util.Stack;
import java.io.IOException;

/**
 *
 * @author USUARIO
 */
public class Controlador {
    public static Stack<Vista> ventanas;
    public static Administrator defo;
    public static User user;
    public static Airline a;
    
    public Controlador() throws IOException{
        ventanas = new Stack<Vista>();
        a = new Airline("Aerolineas PAYASO", 123456);
        defo = new Administrator(a);
        user = new User();
    }
}
