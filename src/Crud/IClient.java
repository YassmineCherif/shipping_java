/*
// * To change this license header, choose License Headers in Project Properties.
// * To change this template file, choose Tools | Templates
// * and open the template in the editor.
// */
package Crud;

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
