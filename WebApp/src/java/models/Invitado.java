/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package models;

/**
 *
 * @author IVAN
 */
public class Invitado {
    int id;
    String nombre;
    String apellido;

    public Invitado() {
        id = 001;
        nombre = "Invitado";
        apellido = String.format("#%d", getRandomNumber());
    }

    public int getId() {
        return id;
    }

    public String getNombre() {
        return nombre;
    }

    public String getApellido() {
        return apellido;
    }
    
    private int getRandomNumber() {
        double rand = (Math.random() * ((999999 - 100000) +1)) + 100000 ;
        return (int)rand;
    }
    
}
