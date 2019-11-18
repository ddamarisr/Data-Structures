/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package customImplementations;

import data.Passenger;

/**
 *
 * @author danie
 */
public class Node { 
	Passenger key; 
        int height; 
	Node left, right; 
	Node(Passenger p) { 
		key = p; 
		height = 1; 
	} 

    public Passenger getKey() {
        return key;
    }
        
} 