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
public class Alumno extends Persona  {
    
    String calle;
    int altura;
    int codigoPostal;
    
    public Alumno() {}
    
    public Alumno(int legajo, String nombre, String apellido, int documento, Date fdn, String calle, int altura, int cp, String email, String telefono) {
        this.legajo = legajo;
        this.nombre = nombre;
        this.apellido = apellido;
        this.documento = documento;
        this.fechaDeNacimiento = fdn;
        this.calle = calle;
        this.altura = altura;
        this.codigoPostal = cp;
        this.email = email;
        this.telefono = telefono;
    }

    public Alumno(int id, int legajo, String nombre, String apellido, int documento, Date fechaDeNacimiento, String calle, int altura, int codigoPostal, String email, String telefono) {
        this.id = id;
        this.legajo = legajo;
        this.nombre = nombre;
        this.apellido = apellido;
        this.documento = documento;
        this.fechaDeNacimiento = fechaDeNacimiento;
        this.calle = calle;
        this.altura = altura;
        this.codigoPostal = codigoPostal;
        this.email = email;
        this.telefono = telefono;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getLegajo() {
        return legajo;
    }

    public void setLegajo(int legajo) {
        this.legajo = legajo;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getApellido() {
        return apellido;
    }

    public void setApellido(String apellido) {
        this.apellido = apellido;
    }

    public int getDocumento() {
        return documento;
    }

    public void setDocumento(int documento) {
        this.documento = documento;
    }

    public Date getFechaDeNacimiento() {
        return fechaDeNacimiento;
    }

    public void setFechaDeNacimiento(Date fechaDeNacimiento) {
        this.fechaDeNacimiento = fechaDeNacimiento;
    }

    public String getCalle() {
        return calle;
    }

    public void setCalle(String calle) {
        this.calle = calle;
    }

    public int getAltura() {
        return altura;
    }

    public void setAltura(int altura) {
        this.altura = altura;
    }

    public int getCodigoPostal() {
        return codigoPostal;
    }

    public void setCodigoPostal(int codigoPostal) {
        this.codigoPostal = codigoPostal;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getTelefono() {
        return telefono;
    }

    public void setTelefono(String telefono) {
        this.telefono = telefono;
    }
    
    @Override
    public String toString() {
        return String.format("%s %s (%d)", nombre, apellido, legajo);
    }
}
