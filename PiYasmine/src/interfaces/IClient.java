/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package interfaces;

import entities.Client;
import java.util.List;

/**
 *
 * @author yasoulanda
 */
public interface IClient {
     public void ajouterClient( Client c );
     public void modifierClient( Client c );
     public void supprimerClient(Client c);
     public List<Client> afficherClients();

     


}
