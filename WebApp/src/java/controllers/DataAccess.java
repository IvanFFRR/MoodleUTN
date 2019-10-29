/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controllers;

import java.sql.Connection;
import java.sql.Date;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import models.*;
import java.util.ArrayList;


/**
 *
 * @author IVAN
 */
public class DataAccess {
    Connection cnn; 
    String cstring = "jdbc:sqlserver://localhost;databaseName=MoodleUTN";
    String user = "sa";
    String pass = "maximo";
    
    public void Conectar() {
        try {
            cnn = DriverManager.getConnection(cstring, user, pass);
        } catch (SQLException e) {
            System.out.println("Error al conectar a la base de datos. ERROR:" + e.getMessage());
        }
    }
    
    public void Desconectar() {
        try {
            if (!cnn.isClosed()) {
                cnn.close();
            }
        } catch (SQLException e) {
            System.out.println("Error al desconectar la base de datos. ERROR: " + e.getMessage());
        }
    }
    
    public ArrayList<Alumno> getAlumnos(Materia m) {
        ArrayList<Alumno> lista = new ArrayList<>();
        String sql = "SELECT a.* FROM Alumnos a, Materias m, Inscripciones i WHERE a.id = i.alumno AND m.id = i.materia AND m.id = @materia";
        
        try {
            Conectar();
            PreparedStatement ps = cnn.prepareStatement(sql);
            ps.setInt(1, m.getId());
            ResultSet rs = ps.executeQuery();
            while(rs.next()) {
                Alumno a = new Alumno(
                        rs.getInt("legajo"),
                        rs.getString("nombre"),
                        rs.getString("apellido"),
                        rs.getInt("numeroDocumento"),
                        rs.getDate("fechaNacimiento"),
                        rs.getString("calle"),
                        rs.getInt("altura"),
                        rs.getInt("codigoPostal"),
                        rs.getString("email"),
                        rs.getString("telefono")
                );
                
                lista.add(a);
            }
            
        } catch (SQLException e) {
            System.out.println("Error al mostrar alumnos: " + e.getMessage());
        } finally {
            Desconectar();
        }

        return lista;
    }
    
    public ArrayList<Materia> getMaterias(Profesor p) {
        ArrayList<Materia> lista = new ArrayList<>();
        String sql = "SELECT m.* FROM Materias m, Profesores p WHERE p.id = m.profesor AND p.id = @profesor";
        
        try {
            Conectar();
            PreparedStatement ps = cnn.prepareStatement(sql);
            ps.setInt(1, p.getId());
            ResultSet rs = ps.executeQuery();
            while(rs.next()) {
                Materia m = new Materia(
                        rs.getString("nombre"),
                        p
                );
                lista.add(m);
            }
        } catch (SQLException e) {
            System.out.println("Error al cargar las materias: " + e.getMessage());
        } finally {
            Desconectar();
        }
        return lista;
    }
    
    public ArrayList<Materia> getMaterias() {
        ArrayList<Materia> lista = new ArrayList<>();
        String sql = "SELECT m.* FROM Materias m, Profesores p WHERE p.id = m.profesor";
        
        try {
            Conectar();
            Statement st = cnn.createStatement();
            ResultSet rs = st.executeQuery(sql);
            
            while(rs.next()) {
                Profesor p = new Profesor(); 
                p.setId(rs.getInt("profesor"));
                
                Materia m = new Materia(
                        rs.getString("nombre"),
                        p
                );
                
                lista.add(m);
            }
        } catch (SQLException e) {
            System.out.println("Error al cargar las materias: " + e.getMessage());
        } finally {
            Desconectar();
        }
        return lista;
    }
    
    public ArrayList<Profesor> getProfesores() {
        ArrayList<Profesor> lista = new ArrayList<>();
        String sql =  "SELECT * FROM Profesores";
        
        try {
            Conectar();
            Statement st = cnn.createStatement();
            ResultSet rs = st.executeQuery(sql);
            
            while(rs.next()) {
                Profesor p = new Profesor(
                        rs.getInt("legajo"),
                        rs.getString("nombre"),
                        rs.getString("apellido"),
                        rs.getInt("documento"),
                        rs.getDate("fechaNacimiento"),
                        rs.getString("email"),
                        rs.getString("telefono")
                );
                
                lista.add(p);
            }
        } catch (SQLException e) {
            System.out.println("Error al cargar los profesores: " + e.getMessage());
        } finally {
            Desconectar();
        }
        
        return lista;
    }
    
    public ArrayList<Recurso> getRecursos(Materia m) {
        ArrayList<Recurso> lista = new ArrayList<>();
        String sql = "SELECT r.* FROM Recursos r, Materias m WHERE r.materia = m.id AND m.id = @materia";
        
        try {
            Conectar();
            PreparedStatement ps = cnn.prepareStatement(sql);
            ps.setInt(1, m.getId());
            
            ResultSet rs = ps.executeQuery();
            while(rs.next()) {
                Recurso r = new Recurso(
                        rs.getDate("fecha"),
                        m,
                        rs.getString("recurso")
                );
                lista.add(r);
            }
        } catch (SQLException e) {
            System.out.println("Error al cargar el recurso: " + e.getMessage());
        } finally {
            Desconectar();
        }
        return lista;
    }
    
    
    public ArrayList<Descarga> getDescargas(Recurso r) {
        ArrayList<Descarga> lista = new ArrayList<>();
        String sql = "SELECT d.fecha, a.nombre FROM Descargas d, Alumnos a, Materias m, Recursos r "
                + "WHERE a.id = d.alumno AND d.recurso = r.id AND r.materia = m.id "
                + "AND r.id = @r"; 
        
        try {
            Conectar();
            PreparedStatement ps = cnn.prepareStatement(sql);
            ps.setInt(1, r.getId());
            ResultSet rs = ps.executeQuery();
            
            while(rs.next()) {
                Date fecha = rs.getDate(1);
                String nombreAlumno = rs.getString(2);
                                
                Alumno a = new Alumno();
                a.setNombre(nombreAlumno);
                
                Descarga d = new Descarga(fecha, a, r);
                lista.add(d);                
            }
            
        } catch (SQLException e) {
            System.out.println("Error al obtener una descarga: " + e.getMessage());
        }
        
        return lista;
    }
    
    public ArrayList<Descarga> getDescargas(Alumno a) {
        ArrayList<Descarga> lista = new ArrayList<>();
        String sql = "SELECT d.fecha, m.nombre AS 'Materia' , r.recurso "
                + "FROM Descargas d, Alumnos a, Materias m, Recursos r "
                + "WHERE a.id = d.alumno AND d.recurso = r.id AND r.materia = m.id"
                + "AND a.id = @id";
        
        try {
            Conectar();
            PreparedStatement ps = cnn.prepareStatement(sql);
            ps.setInt(1, a.getId());
            ResultSet rs = ps.executeQuery();
            
            while(rs.next()) {
                Date fecha = rs.getDate(1);
                String nombreMateria = rs.getString(2);
                String rutaRecurso = rs.getString(3);
                
                Materia m = new Materia();
                m.setNombre(nombreMateria);
                Recurso r = new Recurso();
                r.setRuta(rutaRecurso);
                r.setMateria(m);
                
                Descarga d = new Descarga(fecha, a, r);
                
                lista.add(d);               
                
            }
        } catch (SQLException e) {
            System.out.println("Error al obtener una descarga: " + e.getMessage());
        } finally {
            Desconectar();
        }
        
        return lista;
    }
    
    public boolean setAlumno(Alumno a) {
        boolean flag = false;
        String sql = "INSERT INTO Alumnos VALUES (@legajo, @nombre, @apellido, @tipoDocumento, @numeroDocumento, @fechaNacimiento, @calle, @altura, @codigoPostal, @email, @telefono)";
        
        try {
            Conectar();
            PreparedStatement ps = cnn.prepareStatement(sql);
            ps.setInt(1, a.getLegajo());
            ps.setString(2, a.getNombre());
            ps.setString(3, a.getApellido());
            ps.setInt(4, a.getTipoDocumento());
            ps.setInt(5, a.getDocumento());
            ps.setDate(6, a.getFechaDeNacimiento());
            ps.setString(7, a.getCalle());
            ps.setInt(8, a.getAltura());
            ps.setInt(9, a.getCodigoPostal());
            ps.setString(10, a.getEmail());
            ps.setString(11, a.getTelefono());
            flag = ps.execute();
                
        } catch (SQLException e) {
            System.out.println("Error al insertar un alumno: " + e.getMessage());
        }
        return flag;
    }
    
    public boolean setMateria(Materia m) {
        boolean flag = false;
        String sql = "INSERT INTO Materias VALUES (@nombre, @profesor)";
        
        try {
            Conectar();
            PreparedStatement ps = cnn.prepareStatement(sql);
            ps.setString(1, m.getNombre());
            ps.setInt(2, m.getProfesor().getId());
            flag = ps.execute();
        } catch (SQLException e) {
            System.out.println("Error al insertar una materia: " + e.getMessage());
        } finally {
            Desconectar();
        }
        return flag;
    }
    
    public boolean setProfesor(Profesor p) {
        boolean flag = false;
        String sql = "INSERT INTO Profesores VALUES(@legajo, @nombre, @apellido, @tipoDocumento, @documento, @fechaNacimiento, @email";
        
        try {
            Conectar();
            PreparedStatement ps = cnn.prepareCall(sql);
            ps.setInt(1, p.getLegajo());
            ps.setString(2, p.getNombre());
            ps.setString(3, p.getApellido());
            ps.setInt(4, p.getTipoDocumento());
            ps.setInt(5, p.getDocumento());
            ps.setDate(6, p.getFechaDeNacimiento());
            ps.setString(7, p.getEmail());
            flag = ps.execute();
            
        } catch (SQLException e) {
            System.out.println("Error al insertar un profesor: " + e.getMessage());
        } finally { 
            Desconectar();
        }
        return flag;
    }
    
    public boolean setInscripcion(Inscripcion i) {
        boolean flag = false;
        String sql = "INSERT INTO Inscripciones VALUES (@fecha, @materia, @alumno)";
        
        try {
            Conectar();
            PreparedStatement ps = cnn.prepareStatement(sql);
            ps.setDate(1, i.getFecha());
            ps.setInt(1, i.getMateria().getId());
            ps.setInt(3, i.getAlumno().getId());
            flag = ps.execute();
        } catch (SQLException e) {
            System.out.println("Error al insertar una inscripcion: " + e.getMessage());
        }
        return flag;
    }
    
    public boolean setRecurso(Recurso r) {
        boolean flag = false;
        String sql = "INSERT INTO Recursos VALUES (@fecha, @materia, @recurso)";
        
        try {
            Conectar();
            PreparedStatement ps = cnn.prepareStatement(sql);
            ps.setDate(1, r.getFecha());
            ps.setInt(2, r.getMateria().getId());
            ps.setString(3, r.getRuta());
            flag = ps.execute();
            
        } catch (SQLException e) {
            System.out.println("Error al insertar un recurso: " + e.getMessage());
        } finally {
            Desconectar();
        }
        return flag;
    }
    
    public boolean setDescarga(Descarga d) {
        boolean flag = false;
        String sql = "INSERT INTO Descargas VALUES (@fecha, @alumno, @recurso)";
        
        try {
            Conectar();
            PreparedStatement ps = cnn.prepareStatement(sql);
            ps.setDate(1, d.getFecha());
            ps.setInt(2, d.getAlumno().getId());
            ps.setInt(3, d.getRecurso().getId());
            flag = ps.execute();
            
        } catch (SQLException e) {
            System.out.println("Error al descargar: " + e.getMessage());
        } finally {
            Desconectar();
        }
        return flag;
    }
}
