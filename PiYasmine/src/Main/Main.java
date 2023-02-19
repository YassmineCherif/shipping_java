/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
package Main;
import entities.Client;
import interfaces.IClient;
import java.util.List;
import services.ClientService;

/**
 *
 * @author yasoulanda
 */
public class Main {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
     
        Client cl = new Client (0,"jannet","cherif", "22070417","jannouta@gmail.com","rades ", 2);
 
       ClientService cs = new ClientService();
      //cs.ajouterClient(cl);
      Client cls = new Client (7,"nesrine","cherif", "54621484","ChNesrine@gmail.com","Kairaouen ", 2);
     // cs.modifierClient(cls);
      //cs.supprimerClient(cl);
      
       System.out.println(cs.afficherClients());
    


    }
    
}
