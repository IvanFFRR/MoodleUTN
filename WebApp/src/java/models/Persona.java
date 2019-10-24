/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package models;

import java.sql.Date;

/**
 *
 * @author IVAN
 */
public abstract class Persona {
    int id;
    int legajo;
    String nombre;
    String apellido;
    int tipoDocumento;
    int documento;
    Date fechaDeNacimiento;
    String email;
    String telefono;
    
    @Override
    public String toString() {
        return String.format("%s %s", nombre, apellido);
    }
}
