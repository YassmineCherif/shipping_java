/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GUI;

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
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import javafx.scene.control.TextFormatter;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.util.Duration;
import javafx.util.StringConverter;
import org.controlsfx.control.Notifications;
/**
 * FXML Controller class
 *
 * @author yasoulanda
 */
public class CreateClientController implements Initializable {

    @FXML private JFXButton fermer;
    private Button deja;
    @FXML private JFXTextField nom;
    @FXML private JFXTextField prenom ;
    @FXML private JFXTextField numtel;
    @FXML private JFXTextField email;
    @FXML private JFXTextField adresse;
    @FXML private JFXTextField login;
    @FXML private JFXTextField mdp;


    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        
        final String numberFormatter = "[\\d*]*";
        
        numtel.setTextFormatter(new TextFormatter<>(change ->
        (change.getControlNewText().matches(numberFormatter)) ? change : null));
        
    }   
    

       public void Tester(ActionEvent  event) throws IOException, SQLException{
           String nomText = nom.getText();
           String prenomText = prenom.getText();
           String numtelText = numtel.getText();
           String emailText = email.getText();
           String adresseText = adresse.getText();
           String loginText = login.getText();
           String mdpText = mdp.getText();
           Boolean valid = true ;
           
            //ancun champs vide
            if (nomText.length() == 0 || prenomText.length() == 0 || numtelText.length() == 0 || emailText.length() == 0 || adresseText.length() == 0 || loginText.length() == 0 || mdpText.length() == 0) {
             error("Les champs ne peuvent pas être vides");
             valid = false ;            
            }else {
           //verifier l'email only one @ and at least one . 
            if (!emailText.matches("^[^@]+@[^@.]+[.][^@.]+$")) {
              error(" Verifier votre email");
              valid = false ;
            }
            //numero doit etre de taille 8 et contrient que des chiffres
            if ((numtelText.length() != 8)||(!numtelText.matches("\\d+"))) {
              error("Verifier votre numéro de téléphone !");
              valid = false ;            
            }


            }

        // tous les controles de saisies validés
       if(valid == true ){ 
           
           
           try {
               // Login client existe ou non deja 
            Connection conn = MyConnection.getInstance().getConnexion();
            PreparedStatement stmt = conn.prepareStatement("SELECT * FROM client WHERE login = ?");
            stmt.setString(1, loginText);
            ResultSet rs = stmt.executeQuery();
          if (rs.next()) {
            error("Le client existe déjà (Login) ! ");
            return;
        }
         } catch (SQLException ex) {
        System.out.println(ex);
        return;
         }
           
        try {
            //Inserer le client
            Connection conn = MyConnection.getInstance().getConnexion();
            PreparedStatement stmt = conn.prepareStatement("INSERT INTO client (nom, prenom, numtel, email, adresse, login, mdp) VALUES (?, ?, ?, ?, ?, ?, ?)");
            stmt.setString(1, nomText);
            stmt.setString(2, prenomText);
            stmt.setString(3, numtelText);
            stmt.setString(4, emailText);
            stmt.setString(5, adresseText);
            stmt.setString(6, loginText);
            stmt.setString(7, mdpText);
            stmt.executeUpdate();
            check();
         } catch (SQLException ex) {
            System.out.println(ex);
        }
       } 
        
    }
       
       
        @FXML
        public void cancel (ActionEvent e){

               Stage stage = (Stage) fermer.getScene().getWindow();
               stage.close();
    }
    @FXML
            public void connecte (ActionEvent e) throws IOException{
        Stage stage = new Stage ();
        Parent root = FXMLLoader.load(getClass().getResource("Login.fxml"));  
        Scene scene = new Scene (root);
        stage.setScene(scene);
        stage.initStyle(StageStyle.UNDECORATED);
        stage.show();
        ((Node)e.getSource()).getScene().getWindow().hide();
    
}
            
            public void check () throws IOException{
     Image image = new Image("Images/accept.png");
     Notifications notification = Notifications.create();
     notification.graphic(new ImageView(image));
     notification.title("Succès");
     notification.text("Compte crée avec succés ! ");
     notification.hideAfter(Duration.seconds(2));
     notification.position(Pos.CENTER);
     notification.show();
    }
            
    public void error (String msg){
    Image image = new Image("Images/cross.png");
     Notifications notification = Notifications.create();
     notification.graphic(new ImageView(image));
     notification.title("Erreur");
     notification.text(msg);
     notification.hideAfter(Duration.seconds(3));
     notification.position(Pos.CENTER);
     notification.show();
    }
    
}
