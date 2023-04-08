package com.application.start.GereMenu.GestionFournisseur;

import javafx.event.Event;
import javafx.event.EventHandler;
import javafx.scene.control.CheckBox;
import javafx.scene.input.MouseEvent;

public class ListeProduit {

    private int id ;
    private String code ;
    private String nom;
    private String dosage ;
    private String Facture ;
    private String quantite ;
    private CheckBox remark ;

    boolean selected ;

    public ListeProduit(int id,String code, String nom, String dosage, String facture, String quantite,Boolean selected) {

        this.id = id ;
        this.code = code;
        this.nom = nom;
        this.dosage = dosage;
        Facture = facture;
        this.quantite = quantite;
        this.selected = selected ;
        this.remark = new CheckBox() ;
        this.remark.setDisable(false);
        remark.setOnMouseClicked(Event::consume);


        if (selected)
            remark.setSelected(true);
        else
            remark.setSelected(false);



    }




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

    public String getFacture() {
        return Facture;
    }

    public void setFacture(String facture) {
        Facture = facture;
    }

    public String getQuantite() {
        return quantite;
    }

    public void setQuantite(String quantite) {
        this.quantite = quantite;
    }

    public CheckBox getRemark() {


        return remark;
    }

    public void setRemark(CheckBox remark) {
        this.remark = remark;
    }

    public boolean isSelected() {
        return selected;
    }

    public void setSelected(boolean selected) {
        this.selected = selected;
    }


}
