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
import java.util.Calendar;


/**
 *
 * @author IVAN
 */
public class DataAccess {
    Connection cnn; 
    final String cstring, user, pass;

    public DataAccess() {
        this.cstring = "jdbc:sqlserver://localhost;databaseName=MoodleUTN";
        this.pass = "maximo";
        this.user = "sa";      
    }
    
    public void Conectar() {
        try {
            Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver").newInstance();
            cnn = DriverManager.getConnection(cstring, user, pass);
        } catch (ClassNotFoundException | IllegalAccessException | InstantiationException | SQLException e) {
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
    
    public ArrayList<Alumno> getAlumnos() {
        ArrayList<Alumno> lista = new ArrayList<>();
        String sql = "SELECT * from Alumnos";
        
        try {
            Conectar();
            Statement st = cnn.createStatement();
            ResultSet rs = st.executeQuery(sql);            
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
                a.setId(rs.getInt("id"));
                
                lista.add(a);
            }
            
        } catch (SQLException e) {
            System.out.println("Error al mostrar alumnos: " + e.getMessage());
        } finally {
            Desconectar();
        }
        
        return lista;
    }
    
    public ArrayList<Alumno> getAlumnos(Materia m) {
        ArrayList<Alumno> lista = new ArrayList<>();
        String sql = "SELECT a.* FROM Alumnos a, Materias m, Inscripciones i WHERE a.id = i.alumno AND m.id = i.materia AND m.id = ?";
        
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
                a.setId(rs.getInt("id"));
                
                lista.add(a);
            }
            
        } catch (SQLException e) {
            System.out.println("Error al mostrar alumnos: " + e.getMessage());
        } finally {
            Desconectar();
        }

        return lista;
    } //obtiene todos los alumnos de una determinada materia
    
    public ArrayList<Materia> getMaterias(Profesor p) {
        ArrayList<Materia> lista = new ArrayList<>();
        String sql = "SELECT m.* FROM Materias m, Profesores p WHERE p.id = m.profesor AND p.id = ?";
        
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
    } //obtiene todas las materias a cargo de un profesor
    
    public ArrayList<Materia> getMaterias(Alumno a) {
        ArrayList<Materia> lista = new ArrayList<>();
        String sql = "SELECT m.id, m.nombre, p.legajo, p.nombre, p.apellido, p.tipoDocumento, p.documento, p.fechaNacimiento, p.email FROM Materias m, Alumnos a, Inscripciones i, Profesores p WHERE m.profesor = p.id AND a.id = i.alumno AND m.id = i.materia AND a.id = ?";        
        try {
            Conectar();
            PreparedStatement ps = cnn.prepareStatement(sql);
            ps.setInt(1, a.getId());
            
            ResultSet rs = ps.executeQuery();
            
            while(rs.next()) {
                int idMateria  = rs.getInt(1);
                String nombreMateria = rs.getString(2);
                int legajo = rs.getInt(3);
                String nombreProfesor = rs.getString(4);
                String apellidoProfesor = rs.getString(5);
                int documento = rs.getInt(7);
                Date nacimiento = rs.getDate(8);
                String email = rs.getString(9);
                
                Profesor profe = new Profesor(legajo, nombreProfesor, apellidoProfesor, documento, nacimiento, email, "");
                Materia materia = new Materia(nombreMateria, profe);
                materia.setId(idMateria);
                
                lista.add(materia);
            }
        } catch (SQLException e) {
            String error = e.getMessage();
            System.out.println("Error al cargar materias: " + e.getMessage());
        }
        
        return lista;
    }
    
    public ArrayList<Materia> getNoMaterias(Alumno a) {
        ArrayList<Materia> lista = new ArrayList<>();
        String sql = "SELECT m.id, m.nombre, p.legajo, p.nombre, p.apellido, p.tipoDocumento, p.documento, p.fechaNacimiento, p.email \n" +
"                    FROM Materias m, Inscripciones i, Alumnos a, Profesores p \n" +
"                    WHERE m.profesor = p.id AND m.Nombre NOT IN (SELECT m.Nombre FROM Materias m, Inscripciones i, Alumnos a WHERE m.id = i.materia AND i.alumno = a.id AND a.id = ?) \n" +
"                    GROUP BY m.id, m.nombre, p.legajo, p.nombre, p.apellido, p.tipoDocumento, p.documento, p.fechaNacimiento, p.email";
        try {
            Conectar();
            PreparedStatement ps = cnn.prepareStatement(sql);
            ps.setInt(1, a.getId());
            
            ResultSet rs = ps.executeQuery();
            
            while(rs.next()) {
                int idMateria  = rs.getInt(1);
                String nombreMateria = rs.getString(2);
                int legajo = rs.getInt(3);
                String nombreProfesor = rs.getString(4);
                String apellidoProfesor = rs.getString(5);
                int documento = rs.getInt(7);
                Date nacimiento = rs.getDate(8);
                String email = rs.getString(9);
                
                Profesor profe = new Profesor(legajo, nombreProfesor, apellidoProfesor, documento, nacimiento, email, "");
                Materia materia = new Materia(nombreMateria, profe);
                materia.setId(idMateria);
                
                lista.add(materia);
            }
        } catch (SQLException e) {
            String error = e.getMessage();
            System.out.println("Error al cargar materias: " + e.getMessage());
        }
        
        return lista;
    }
    
    public Materia getMateria(int i) {
        String sql = "SELECT m.id, m.nombre, p.legajo, p.nombre, p.apellido, p.documento, p.fechaNacimiento, p.email FROM Materias m, Profesores p WHERE m.profesor = p.id AND m.id = ?";
        
        try {
            Conectar();
            PreparedStatement ps = cnn.prepareStatement(sql);
            ps.setInt(1, i);
            ResultSet rs = ps.executeQuery();
            
            while(rs.next()) {
                int idMateria = rs.getInt(1);
                String nombreMateria = rs.getString(2);
                int legajo = rs.getInt(3);
                String nombreProfe = rs.getString(4);
                String apellidoProfe = rs.getString(5);
                int documento = rs.getInt(6);
                Date nacimiento = rs.getDate(7);
                String email = rs.getString(8);
                
                Profesor p = new Profesor(legajo, nombreProfe, apellidoProfe, documento, nacimiento, email, ""); 
                //(int legajo, String nombre, String apellido, int documento, Date fechaDeNacimiento, String email, String telefono)
                Materia m = new Materia(nombreMateria, p);
                m.setId(idMateria);  
                return m;
            }
            
        } catch (SQLException e) {
            System.out.println("Error al cargar una materia i " + e.getMessage());
        }
       return null; 
    }
    
    public ArrayList<Materia> getMaterias() {
        ArrayList<Materia> lista = new ArrayList<>();
        String sql = "SELECT m.id, m.nombre, p.legajo, p.nombre, p.apellido, p.tipoDocumento, p.documento, p.fechaNacimiento, p.email FROM Materias m, Profesores p WHERE p.id = m.profesor";
        
        try {
            Conectar();
            Statement st = cnn.createStatement();
            ResultSet rs = st.executeQuery(sql);
            
            while(rs.next()) {
                int idMateria  = rs.getInt(1);
                String nombreMateria = rs.getString(2);
                int legajo = rs.getInt(3);
                String nombreProfesor = rs.getString(4);
                String apellidoProfesor = rs.getString(5);
                int documento = rs.getInt(7);
                Date nacimiento = rs.getDate(8);
                String email = rs.getString(9);
                
                Profesor profe = new Profesor(legajo, nombreProfesor, apellidoProfesor, documento, nacimiento, email, "");
                Materia materia = new Materia(nombreMateria, profe);
                materia.setId(idMateria);
                
                lista.add(materia);
            }
        } catch (SQLException e) {
            System.out.println("Error al cargar las materias: " + e.getMessage());
        } finally {
            Desconectar();
        }
        return lista;
    } //obtiene todas las materias
    
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
                        ""
                );
                p.setId(rs.getInt("id"));
                
                lista.add(p);
            }
        } catch (SQLException e) {
            String error = e.getMessage();
            System.out.println("Error al cargar los profesores: " + e.getMessage());
        } finally {
            Desconectar();
        }
        
        return lista;
    } //obtiene todos los profesores
    
    public ArrayList<Recurso> getRecursos(Materia m) {
        ArrayList<Recurso> lista = new ArrayList<>();
        String sql = "SELECT r.* FROM Recursos r, Materias m WHERE r.materia = m.id AND m.id = ?";
        
        try {
            Conectar();
            PreparedStatement ps = cnn.prepareStatement(sql);
            ps.setInt(1, m.getId());
            
            ResultSet rs = ps.executeQuery();
            while(rs.next()) {
                Recurso r = new Recurso(
                        rs.getDate("fecha"),
                        m,
                        rs.getString("recurso"),
                        rs.getBoolean("esPrivado")
                );
                r.setId(rs.getInt("id"));
                lista.add(r);
            }
        } catch (SQLException e) {
            System.out.println("Error al cargar el recurso: " + e.getMessage());
        } finally {
            Desconectar();
        }
        return lista;
    } //obtiene todos los recursos de una materia
        
    public ArrayList<Descarga> getDescargas(Recurso r) {
        ArrayList<Descarga> lista = new ArrayList<>();
        String sql = "SELECT d.fecha, a.nombre FROM Descargas d, Alumnos a, Materias m, Recursos r "
                + "WHERE a.id = d.alumno AND d.recurso = r.id AND r.materia = m.id "
                + "AND r.id = ?"; 
        
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
        } finally {
            Desconectar();
        }
        
        return lista;
    } //obtiene todos las descargas de un cierto recurso
    
    public ArrayList<Descarga> getDescargas(Alumno a) {
        ArrayList<Descarga> lista = new ArrayList<>();
        String sql = "SELECT d.fecha, m.nombre AS 'Materia' , r.recurso "
                + "FROM Descargas d, Alumnos a, Materias m, Recursos r "
                + "WHERE a.id = d.alumno AND d.recurso = r.id AND r.materia = m.id"
                + "AND a.id = ?";
        
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
    }//obtiene todos los recursos descargados por un determinado alumno
    
    public boolean setAlumno(Alumno a) {
        boolean flag = false;
        String sql = "INSERT INTO Alumnos VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
        
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
        String sql = "INSERT INTO Materias VALUES (?, ?)";
        
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
        String sql = "INSERT INTO Profesores VALUES(?, ?, ?, ?, ?, ?, ?)";
        
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
        String sql = "INSERT INTO Inscripciones VALUES (?, ?, ?)";
        
        try {
            Conectar();
            PreparedStatement ps = cnn.prepareStatement(sql);
            ps.setDate(1, i.getFecha());
            ps.setInt(2, i.getMateria().getId());
            ps.setInt(3, i.getAlumno().getId());
            flag = ps.execute();
        } catch (SQLException e) {
            System.out.println("Error al insertar una inscripcion: " + e.getMessage());
        }
        return flag;
    }
    
    public boolean setRecurso(Recurso r) {
        boolean flag = false;
        String sql = "INSERT INTO Recursos VALUES (?, ?, ?)";
        
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
        String sql = "INSERT INTO Descargas VALUES (?, ?, ?)";
        
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
    
    public ArrayList<Credenciales> getCredencialesAlumnos() {
        
        ArrayList<Credenciales> lista = new ArrayList<>();
        String sql = "SELECT numeroDocumento, legajo FROM Alumnos";
        
        try {
            Conectar();
            Statement st = cnn.createStatement();
            ResultSet rs = st.executeQuery(sql);
            
            while(rs.next()) {
                int iUser = rs.getInt(1);
                int iPass = rs.getInt(2);
                String usuario = Integer.toString(iUser);
                String contra = Integer.toString(iPass);
                Credenciales login = new Credenciales(usuario, contra);
                lista.add(login);
            }
        } catch (SQLException e) {
            System.out.println("Error al obtener las credenciales: " + e.getMessage());
        } finally {
            Desconectar();
        }
        
        return lista;
    } //obtiene una lista de todas las credenciales registradas
    
    public boolean setLogin(Object o) { 
        boolean flag = false;
        String sql = "INSERT INTO Logins VALUES (?, ?)";
        
        try {
            Conectar();
            PreparedStatement ps = cnn.prepareStatement(sql);
            if (o instanceof Alumno) {
                ps.setInt(1,((Alumno) o).getId());
            } else {
                if (o instanceof Profesor) {
                    ps.setInt(1,((Profesor) o).getId());
                }
            }
            ps.setDate(1, new java.sql.Date(Calendar.getInstance().getTime().getTime()));
            flag = ps.execute();
        } catch (SQLException e) {
            System.out.println("Error al crear un registro en el historial de logins: " + e.getMessage());
        } finally {
            Desconectar();
        }
        
        
        return flag;
    } //ingresa un registro en el historial de sesiones iniciadas
}
