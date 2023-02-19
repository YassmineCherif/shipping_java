/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package services;
import entities.Client;
import interfaces.IClient;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import utils.MyConnection;

/**
 *
 * @author yasoulanda
 */
public class ClientService implements IClient{
    
     Connection myconn = MyConnection.getInstance().getConnexion();
    
@Override
    public void ajouterClient (Client c) {
        try{
            String sql="insert into client values(?,?,?,?,?,?,?)";
            PreparedStatement ste = myconn.prepareStatement(sql);
            ste.setInt(1,c.getId());    
            ste.setString(2,c.getNom());
            ste.setString(3,c.getPrenom());
            ste.setString(4,c.getNumtel());
            ste.setString(5,c.getEmail());
            ste.setString(6,c.getAdresse());
            ste.setInt(7,c.getId_compte());    
            
            ste.executeUpdate();
            System.out.println("client inséré");
        }catch(SQLException ex ){
        System.out.print(ex);
        }
                   
    }

    @Override
    public void modifierClient(Client c) {
         String sql="UPDATE client SET nom = ?,prenom = ?,numtel = ?,email = ?,adresse = ?, id_compte = ? WHERE id = ?";
        try {
            PreparedStatement ste=myconn.prepareStatement(sql);
            ste.setString(1,c.getNom());
            ste.setString(2,c.getPrenom());
            ste.setString(3,c.getNumtel());
            ste.setString(4,c.getEmail());
            ste.setString(5,c.getAdresse());  
            ste.setInt(6,c.getId_compte());    
            ste.setInt(7,c.getId());    

                ste.executeUpdate();
                System.out.println("client modifié");
        } catch (SQLException ex) {
            System.out.println(ex);
        } 
        }
        
    

    @Override
    public void supprimerClient(Client c) {
        try {
            PreparedStatement preparedStatement = myconn.prepareStatement("DELETE FROM client where id = ?");
            preparedStatement.setInt(1,c.getId());
            preparedStatement.executeUpdate();
             System.out.println("client cupprimé");
        } catch (SQLException ex) {
            //Logging is an API that provides the ability to trace out the errors of the applications
            // catch utilise le Logger pour enregistrer les détails de l'exception dans un fichier journal (log) 
            Logger.getLogger(ClientService.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @Override
    public List<Client> afficherClients() {
        
List<Client> clients = new ArrayList<>();
try {
    String sql = "SELECT * FROM client";
    Statement stmt = myconn.createStatement();
    ResultSet rs = stmt.executeQuery(sql);
    while (rs.next()) {
        Client cl = new Client(
            rs.getInt("id"),
            rs.getString("nom"),
            rs.getString("prenom"),
            rs.getString("numtel"),
            rs.getString("email"),
            rs.getString("adresse"),
            rs.getInt("id_compte"));
        clients.add(cl);
    }
} catch (SQLException ex) {
    // Log the exception or rethrow it
    throw new RuntimeException("Failed to get clients", ex);
} 

return clients;

    }
    }
    

