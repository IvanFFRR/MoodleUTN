/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package models;

import java.sql.Date;
/**
 *
 * @author alumno
 */
public class Recurso {
    int id;
    Date fecha;
    Materia materia;
    String ruta;
    
    public Recurso() {}
    
    public Recurso(Date fecha, Materia m, String ruta) {
        this.fecha = fecha;
        this.materia = m;
        this.ruta = ruta;
    }
    
    @Override
    public String toString() {
        return String.format("%s - %s (%s)", fecha, ruta, materia);
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Date getFecha() {
        return fecha;
    }

    public void setFecha(Date fecha) {
        this.fecha = fecha;
    }

    public Materia getMateria() {
        return materia;
    }

    public void setMateria(Materia materia) {
        this.materia = materia;
    }

    public String getRuta() {
        return ruta;
    }

    public void setRuta(String ruta) {
        this.ruta = ruta;
    }
    
    
         
}
