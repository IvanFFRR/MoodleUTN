/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controllers;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

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
}
