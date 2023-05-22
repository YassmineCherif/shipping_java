/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Crud;

import java.util.Objects;

/**
 *
 * @author yasoulanda
 */
public class Client {
     private int id ; 
    private String nom; 
    private String prenom; 
    private String numtel;
    private String email;
    private String adresse;
    private String login;
    private String mdp;


    public Client() {}
    
      public Client (String nom, String numtel){
        this.nom = nom;
        this.numtel = numtel;
      }
      
     
        public Client(int id , String nom , String prenom,String numtel,String email,String adresse , String login , String mdp) {
        this.id = id;
        this.nom = nom;
        this.prenom = prenom;
        this.numtel = numtel;
        this.email = email;
        this.adresse = adresse;
        this.login=login;
        this.mdp = mdp;
        

        }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

//    public int getId_compte() {
//        return id_compte;
//    }

    public int getId() {
        return id;
    }

    public String getNumtel() {
        return numtel;
    }

    public void setNumtel(String numtel) {
        this.numtel = numtel;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getAdresse() {
        return adresse;
    }

    public void setAdresse(String adresse) {
        this.adresse = adresse;
    }

   

    public String getPrenom() {
        return prenom;
    }

    public void setPrenom(String prenom) {
        this.prenom = prenom;
    }

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public String getMdp() {
        return mdp;
    }

    public void setMdp(String mdp) {
        this.mdp = mdp;
    }



    @Override
    public int hashCode() {
        int hash = 3;
        hash = 47 * hash + Objects.hashCode(this.nom);
        hash = 47 * hash + Objects.hashCode(this.prenom);
        return hash;
    }
    

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final Client other = (Client) obj;
        return true;
    }

    @Override
    public String toString() {
        return "Client{" + "id=" + id + ", nom=" + nom + ", prenom=" + prenom + ", numtel=" + numtel + ", email=" + email + ", adresse=" + adresse + ", login=" + login + ", mdp=" + mdp + '}';
    }



}
