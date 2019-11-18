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
public class Seat implements Serializable{
    private static final long serialVersionUID = 1L;
    private int row;
    private int column;

    public Seat(int row, int column) {
        this.row = row;
        this.column = column;
    }

    @Override
    public String toString() {
        return "Seat{" + "row=" + row + ", column=" + column + '}';
    }

    public int getRow() {
        return row;
    }

    public int getColumn() {
        return column;
    }
    
    
    
   
}
