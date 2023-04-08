package com.application.start.GereMenu.GestionFournisseur.GereAction.Next;

import com.jfoenix.controls.JFXToggleButton;
import javafx.scene.control.TextField;

public class ListePanier {

    int id ;
    String Nom ;
    String dosage ;
    String prix ;
    String facture ;
    int quantite ;


    public ListePanier(int id,String nom, String dosage, String facture, String prix,int quantite) {
        this.id = id ;
        this.Nom = nom;
        this.dosage = dosage;
        this.facture = facture;
        this.prix = prix;
        this.quantite = quantite;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNom() {
        return Nom;
    }

    public void setNom(String nom) {
        Nom = nom;
    }

    public String getDosage() {
        return dosage;
    }

    public void setDosage(String dosage) {
        this.dosage = dosage;
    }

    public String getFacture() {
        return facture;
    }

    public void setFacture(String facture) {
        this.facture = facture;
    }

    public String getPrix() {
        return prix;
    }

    public void setPrix(String prix) {
        this.prix = prix;
    }

    public int getQuantite() {
        return quantite;
    }

    public void setQuantite(int quantite) {
        this.quantite = quantite;
    }
}
