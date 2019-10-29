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
public class Credenciales {
    int user;
    int pass;

    public Credenciales(int user, int pass) {
        this.user = user;
        this.pass = pass;
    }

    public int getUser() {
        return user;
    }

    public void setUser(int user) {
        this.user = user;
    }

    public int getPass() {
        return pass;
    }

    public void setPass(int pass) {
        this.pass = pass;
    }

    @Override
    public String toString() {
        return "¡Bienvenido " + user + "!";
    }
    
    
    
    
    
}
