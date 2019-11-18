/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package BasesFx;

import javafx.scene.control.Label;

/**
 *
 * @author USUARIO
 */
public class Labels extends Label {
    public Labels(){
        super();
        this.setScaleY(1.3);
    }
    public Labels(String nombre){
        super(nombre);
        this.setScaleY(1.3);
    }
}
