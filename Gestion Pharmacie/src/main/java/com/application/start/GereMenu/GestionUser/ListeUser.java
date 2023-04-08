package com.application.start.GereMenu.GestionUser;

public class ListeUser {

    String Nom ;
    String Prenom ;
    String Tel ;

    public ListeUser(String nom, String prenom, String tel) {
        Nom = nom;
        Prenom = prenom;
        Tel = tel;
    }

    public String getNom() {
        return Nom;
    }

    public void setNom(String nom) {
        Nom = nom;
    }

    public String getPrenom() {
        return Prenom;
    }

    public void setPrenom(String prenom) {
        Prenom = prenom;
    }

    public String getTel() {
        return Tel;
    }

    public void setTel(String tel) {
        Tel = tel;
    }
}
