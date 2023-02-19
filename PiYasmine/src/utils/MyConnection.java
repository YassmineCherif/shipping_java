/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package utils;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 *
 * @author yasoulanda
 */

public class MyConnection {
     String url = "jdbc:mysql://localhost:3306/taktak";// DB name
    String login= "root";
    String password;
    Connection myConexBD;
    
    public static MyConnection instance ; 

    private MyConnection() {
        try {
            myConexBD = DriverManager.getConnection(url, login, password);
            System.out.println("connexion reussie");
        } catch (SQLException ex) {
            System.out.println(ex);
        }
    }
   public static MyConnection getInstance(){
        if(instance == null){
            instance = new MyConnection();
        }
        return instance;
    }
    public Connection getConnexion(){
        return myConexBD;
    }
}

