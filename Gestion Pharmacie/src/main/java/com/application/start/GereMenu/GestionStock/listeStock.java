package com.application.start.GereMenu.GestionStock;

public class listeStock {

    int id ;
    String code ;
    String nom;
    String dosage ;
    String t_Rem ;
    String loaclisation ;
    int quantite ;

    public listeStock(int id, String code, String nom, String dosage, String t_Rem, String loaclisation, int quantite) {
        this.id = id;
        this.code = code;
        this.nom = nom;
        this.dosage = dosage;
        this.t_Rem = t_Rem;
        this.loaclisation = loaclisation;
        this.quantite = quantite;
    }

    public listeStock(){} ;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public String getDosage() {
        return dosage;
    }

    public void setDosage(String dosage) {
        this.dosage = dosage;
    }

    public String getT_Rem() {
        return t_Rem;
    }

    public void setT_Rem(String t_Rem) {
        this.t_Rem = t_Rem;
    }

    public String getLoaclisation() {
        return loaclisation;
    }

    public void setLoaclisation(String loaclisation) {
        this.loaclisation = loaclisation;
    }

    public int getQuantite() {
        return quantite;
    }

    public void setQuantite(int quantite) {
        this.quantite = quantite;
    }
}
