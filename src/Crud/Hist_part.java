/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Crud;

/**
 *
 * @author yasoulanda
 */
public class Hist_part {
    private String nom; 
        private String numtel;
        private String login;
        private String ref;
        private String prix;
        
        public Hist_part (String nom, String numtel, String login,String ref, String prixcolis){
        this.nom = nom;
        this.numtel = numtel;
        this.login = login;
        this.ref = ref;
        this.prix = prixcolis;
      }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public String getNumtel() {
        return numtel;
    }

    public void setNumtel(String numtel) {
        this.numtel = numtel;
    }

    public String getRefcolis() {
        return login;
    }

    public void setRefcolis(String refcolis) {
        this.login = refcolis;
    }

    public String getPrixcolis() {
        return prix;
    }

    public void setPrixcolis(String prixcolis) {
        this.prix = prixcolis;
    }

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public String getRef() {
        return ref;
    }

    public void setRef(String ref) {
        this.ref = ref;
    }

    public String getPrix() {
        return prix;
    }

    public void setPrix(String prix) {
        this.prix = prix;
    }

    @Override
    public String toString() {
        return "Hist_part{" + "nom=" + nom + ", numtel=" + numtel + ", login=" + login + ", ref=" + ref + ", prix=" + prix + '}';
    }

  
        
}
