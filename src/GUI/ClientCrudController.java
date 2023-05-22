/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GUI;

import Crud.Client;
import Crud.MyConnection;
import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXTextField;
import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import javafx.util.Duration;
import org.controlsfx.control.Notifications;

/**
 * FXML Controller class
 *
 * @author yasoulanda
 */
public class ClientCrudController implements Initializable {

    @FXML private JFXButton fermer;
    @FXML private JFXTextField id;
    @FXML private JFXTextField nom;
    @FXML private JFXTextField prenom ;
    @FXML private JFXTextField numtel;
    @FXML private JFXTextField email;
    @FXML private JFXTextField adresse;
    @FXML private JFXTextField login;
    @FXML private JFXTextField mdp;
    @FXML private TableView <Client> tablev;
    @FXML private TableColumn <Client, Integer> idc;
    @FXML private TableColumn <Client, String> nomc; 
    @FXML private TableColumn <Client, String> prenomc;
    @FXML private TableColumn <Client, String> numtelc;    
    @FXML private TableColumn <Client, String> emailc;
    @FXML private TableColumn <Client, String> adressec;
    @FXML private TableColumn <Client, String> loginc;
    @FXML private TableColumn <Client, String> mdpc;
    @FXML private JFXButton inserer;
    @FXML private JFXButton modifier;
    @FXML private JFXButton effacer;
   // @FXML private JFXButton rafficher; 
    Connection myconn = MyConnection.getInstance().getConnexion();
    
    
@FXML
    private void ButtonAction(ActionEvent event) throws IOException {        
        
        if(event.getSource() == inserer){
            insert();
        }
        if (event.getSource() == modifier){
            update();
        }
        if(event.getSource() == effacer){
            delete();
        }

            
    }
    
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
    
        afficherClients();
        
        
    }    
    
            public void cancel (ActionEvent e){

               Stage stage = (Stage) fermer.getScene().getWindow();
               stage.close();
    }
            public void retourner (ActionEvent e) throws IOException{
        Stage stage = new Stage ();
        Parent root = FXMLLoader.load(getClass().getResource("Login.fxml"));  
        Scene scene = new Scene (root);
        stage.setScene(scene);
        stage.initStyle(StageStyle.UNDECORATED);
        stage.show();
        ((Node)e.getSource()).getScene().getWindow().hide();
    
}
            public ObservableList <Client> afficherListeClients() {
        
                ObservableList<Client> clients = FXCollections.observableArrayList();
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
                    rs.getString("login"),
                    rs.getString("mdp"));
                    clients.add(cl);
                    }
                 }catch (Exception ex) {
                   ex.printStackTrace();
                 } 

             return clients;

             }
            
            
            public void afficherClients (){
                ObservableList<Client> list = afficherListeClients();
                //idc.setCellValueFactory(new PropertyValueFactory<Client,Integer>("id"));
                nomc.setCellValueFactory(new PropertyValueFactory<Client,String>("nom"));
                prenomc.setCellValueFactory(new PropertyValueFactory<Client,String>("prenom"));
                numtelc.setCellValueFactory(new PropertyValueFactory<Client,String>("numtel"));
                emailc.setCellValueFactory(new PropertyValueFactory<Client,String>("email"));
                adressec.setCellValueFactory(new PropertyValueFactory<Client,String>("adresse"));
                loginc.setCellValueFactory(new PropertyValueFactory<Client,String>("login"));
                mdpc.setCellValueFactory(new PropertyValueFactory<Client,String>("mdp"));
                tablev.setItems(list);
            }
            
            
            private void insert() throws IOException{
        
                try {
            //Inserer le client
            PreparedStatement stmt = myconn.prepareStatement("INSERT INTO client (nom, prenom, numtel, email, adresse, login, mdp) VALUES (?, ?, ?, ?, ?, ?, ?)");
            stmt.setString(1, nom.getText());
            stmt.setString(2, prenom.getText());
            stmt.setString(3, numtel.getText());
            stmt.setString(4, email.getText());
            stmt.setString(5, adresse.getText());
            stmt.setString(6, login.getText());
            stmt.setString(7, mdp.getText());
            stmt.executeUpdate();
            check(" client inseré ");
         } catch (SQLException ex) {
            System.out.println(ex);
        }
             afficherClients();
    }
             
             
             
    private void update() throws IOException{

//        
//        String sql = "UPDATE  client SET nom  = '" + nom.getText() + "', prenom = '" + prenom.getText() + "', numtel = " +
//                numtel.getText() + "', email = '" + email.getText() + "', adresse = '" + adresse.getText() 
//                + "', login = '" + login.getText() + ", mdp = " + mdp.getText() + " WHERE id = " + id.getText() + "";
//        try {
//            PreparedStatement ste=myconn.prepareStatement(sql);
//            ste.executeUpdate();
//            check(" client modifié " );
//                
//        } catch (SQLException ex) {
//            System.out.println(ex);
//        } 
//        afficherClients();
  
        
         String sql="UPDATE client SET nom = ? ,prenom = ? ,numtel = ? ,email = ? ,adresse = ? , login = ? , mdp = ?  WHERE id = ? ";
        try {
            //pre-compiled SQL statement
            PreparedStatement ste=myconn.prepareStatement(sql);
            ste.setString(1,nom.getText());
            ste.setString(2,prenom.getText());
            ste.setString(3,numtel.getText());
            ste.setString(4,email.getText()); 
            ste.setString(5,adresse.getText());
            ste.setString(6,login.getText());  
            ste.setString(7,mdp.getText());
            ste.setString(8,id.getText());

                ste.executeUpdate();
                check(" client modifié " );
                        //afficherClients();

        } catch (SQLException ex) {
            System.out.println(ex);
        }         afficherClients();
    }
    
    
    
    private void delete() throws IOException{
       
        try {
            PreparedStatement preparedStatement = myconn.prepareStatement("DELETE FROM client where id = ?");
            preparedStatement.setString(1,id.getText());
            preparedStatement.executeUpdate();
              check(" client supprimé " );
        } catch (SQLException ex) {
              System.out.println(ex);
        }
        afficherClients();
    }
    
    
    

  public void check (String msg) throws IOException{

     Image image = new Image("Images/accept.png");
     Notifications notification = Notifications.create();
     notification.graphic(new ImageView(image));
     notification.title("Succès");
     notification.text(msg);
     notification.hideAfter(Duration.seconds(1));
     notification.position(Pos.CENTER);
     notification.show();
    }
           
}
