/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package data;

import java.io.Serializable;
import java.util.Objects;

/**
 *
 * @author danie
 */
public class Route implements Serializable {
    private static final long serialVersionUID = 1L;
    private String origin;
    private String destination;

    public Route(String origin, String destination) {
        this.origin = origin;
        this.destination = destination;
    }


    @Override
    public boolean equals(Object obj) {
        
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final Route other = (Route) obj;
        if (!Objects.equals(this.origin, other.origin)) {
            return false;
        }
        if (!Objects.equals(this.destination, other.destination)) {
            return false;
        }
        return true;
    }

    public String getOrigin() {
        return origin;
    }

    public void setOrigin(String origin) {
        this.origin = origin;
    }

    public String getDestination() {
        return destination;
    }

    public void setDestination(String destination) {
        this.destination = destination;
    }
    
    

    @Override
    public String toString() {
        return "Route{" + "origin=" + origin + ", destination=" + destination + '}';
    }
    
    
    
}
