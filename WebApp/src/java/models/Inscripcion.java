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
public class Inscripcion {
    int id;
    Date fecha;
    Materia materia;
    Alumno alumno;

    public Inscripcion() {}
    
    public Inscripcion(Date fecha, Materia m, Alumno a) {
        this.fecha = fecha;
        this.materia = m;
        this.alumno = a;
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

    public Alumno getAlumno() {
        return alumno;
    }

    public void setAlumno(Alumno alumno) {
        this.alumno = alumno;
    }
    
    @Override
    public String toString() {
        return String.format("%s - El alumno %s se ha inscrito a la materia %s", fecha, alumno, materia);
    }
    
}
