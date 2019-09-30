/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package businessLogic;

import static businessLogic.ProTest.flights;
import data.Flight;
import data.Passenger;
import java.io.IOException;
import java.util.Date;
import ui.UI;

/**
 *
 * @author USUARIO
 */
public class User {
    public void addPassenger(Flight f) throws IOException{
        String name=UI.recorder("el nombre completo de la persona");
        int id = Integer.parseInt(UI.recorder("el número del documento"));
        String mail =UI.recorder("el correo electronico");
        Passenger p= new Passenger(id, name, mail);
        f.addPassenger(p);
    }
}
