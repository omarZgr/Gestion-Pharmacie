package com.application.start.GereMenu.GestionFournisseur;

public class PrductSelected {

    private int id ;
    private  String code ;
    private String nom;
    private String DCI1 ;
    private  String dosage ;
    private  String UNITE_DOSAGE1 ;
    private   String FORME ;
    private  String PRESENTATION ;
    private  String PPV ;
    private String PH ;
    private String  PRIX_BR  ;
    private String TAUX_REMBOURSEMENT ;

    public PrductSelected(int id, String code, String nom, String DCI1, String dosage, String UNITE_DOSAGE1, String FORME, String PRESENTATION, String PPV, String PH, String PRIX_BR, String TAUX_REMBOURSEMENT, String tauxRemboursement) {
        this.id = id;
        this.code = code;
        this.nom = nom;
        this.DCI1 = DCI1;
        this.dosage = dosage;
        this.UNITE_DOSAGE1 = UNITE_DOSAGE1;
        this.FORME = FORME;
        this.PRESENTATION = PRESENTATION;
        this.PPV = PPV;
        this.PH = PH;
        this.PRIX_BR = PRIX_BR;
        this.TAUX_REMBOURSEMENT = TAUX_REMBOURSEMENT;
    }

    public PrductSelected(){} ;

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

    public String getDCI1() {
        return DCI1;
    }

    public void setDCI1(String DCI1) {
        this.DCI1 = DCI1;
    }

    public String getDosage() {
        return dosage;
    }

    public void setDosage(String dosage) {
        this.dosage = dosage;
    }

    public String getUNITE_DOSAGE1() {
        return UNITE_DOSAGE1;
    }

    public void setUNITE_DOSAGE1(String UNITE_DOSAGE1) {
        this.UNITE_DOSAGE1 = UNITE_DOSAGE1;
    }

    public String getFORME() {
        return FORME;
    }

    public void setFORME(String FORME) {
        this.FORME = FORME;
    }

    public String getPRESENTATION() {
        return PRESENTATION;
    }

    public void setPRESENTATION(String PRESENTATION) {
        this.PRESENTATION = PRESENTATION;
    }

    public String getPPV() {
        return PPV;
    }

    public void setPPV(String PPV) {
        this.PPV = PPV;
    }

    public String getPH() {
        return PH;
    }

    public void setPH(String PH) {
        this.PH = PH;
    }

    public String getPRIX_BR() {
        return PRIX_BR;
    }

    public void setPRIX_BR(String PRIX_BR) {
        this.PRIX_BR = PRIX_BR;
    }

    public String getTAUX_REMBOURSEMENT() {
        return TAUX_REMBOURSEMENT;
    }

    public void setTAUX_REMBOURSEMENT(String TAUX_REMBOURSEMENT) {
        this.TAUX_REMBOURSEMENT = TAUX_REMBOURSEMENT;
    }
}
