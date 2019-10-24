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
public class Descarga {
    int id;
    Date fecha;
    Alumno alumno;
    Recurso recurso;

    public Descarga() {}
    
    public Descarga(Date fecha, Alumno a, Recurso r) {
        this.fecha = fecha;
        alumno = a;
        recurso = r;
    }
    
    @Override
    public String toString() {
        return String.format("%s - El alumno %s ha descargado %s", fecha, alumno, recurso);
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

    public Alumno getAlumno() {
        return alumno;
    }

    public void setAlumno(Alumno alumno) {
        this.alumno = alumno;
    }

    public Recurso getRecurso() {
        return recurso;
    }

    public void setRecurso(Recurso recurso) {
        this.recurso = recurso;
    }
    
    
}
