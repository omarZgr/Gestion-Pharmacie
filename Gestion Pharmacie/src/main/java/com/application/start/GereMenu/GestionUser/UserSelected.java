package com.application.start.GereMenu.GestionUser;

import java.util.Date;

public class UserSelected {

    private int id ;
    private   String nom ;
    private String prenom ;
    private String Tel ;
    private String CIN ;
    private String userName ;
    private String password ;
    private String typeUser ;
    private  Date dateInscription ;
    private Date dateFin ;
    private  boolean etatOF_User ;
    private  String pharmacie ;
    private  boolean admin ;

    public UserSelected(int id,String nom, String prenom, String tel, String CIN, String userName, String password, String typeUser, Date dateInscription, Date dateFin, boolean etatOF_User, String pharmacie, boolean admin) {
       this.id = id ;
        this.nom = nom;
        this.prenom = prenom;
        Tel = tel;
        this.CIN = CIN;
        this.userName = userName;
        this.password = password;
        this.typeUser = typeUser;
        this.dateInscription = dateInscription;
        this.dateFin = dateFin;
        this.etatOF_User = etatOF_User;
        this.pharmacie = pharmacie;
        this.admin = admin;
    }

    public UserSelected(int id,String nom, String prenom, String tel, String CIN, String userName, String password, String typeUser, Date dateInscription, Date dateFin, boolean etatOF_User) {
        this.id = id ;
        this.nom = nom;
        this.prenom = prenom;
        Tel = tel;
        this.CIN = CIN;
        this.userName = userName;
        this.password = password;
        this.typeUser = typeUser;
        this.dateInscription = dateInscription;
        this.dateFin = dateFin;
        this.etatOF_User = etatOF_User;
    }

    public UserSelected(){} ;


    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public String getPrenom() {
        return prenom;
    }

    public void setPrenom(String prenom) {
        this.prenom = prenom;
    }

    public String getTel() {
        return Tel;
    }

    public void setTel(String tel) {
        Tel = tel;
    }

    public String getCIN() {
        return CIN;
    }

    public void setCIN(String CIN) {
        this.CIN = CIN;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getTypeUser() {
        return typeUser;
    }

    public void setTypeUser(String typeUser) {
        this.typeUser = typeUser;
    }

    public Date getDateInscription() {
        return dateInscription;
    }

    public void setDateInscription(Date dateInscription) {
        this.dateInscription = dateInscription;
    }

    public Date getDateFin() {
        return dateFin;
    }

    public void setDateFin(Date dateFin) {
        this.dateFin = dateFin;
    }

    public boolean isEtatOF_User() {
        return etatOF_User;
    }

    public void setEtatOF_User(boolean etatOF_User) {
        this.etatOF_User = etatOF_User;
    }

    public String getPharmacie() {
        return pharmacie;
    }

    public void setPharmacie(String pharmacie) {
        this.pharmacie = pharmacie;
    }

    public boolean isAdmin() {
        return admin;
    }

    public void setAdmin(boolean admin) {
        this.admin = admin;
    }

    @Override
    public String toString() {
        return "UserSelected{" +
                "id=" + id +
                ", nom='" + nom + '\'' +
                ", prenom='" + prenom + '\'' +
                ", Tel='" + Tel + '\'' +
                ", CIN='" + CIN + '\'' +
                ", userName='" + userName + '\'' +
                ", password='" + password + '\'' +
                ", typeUser='" + typeUser + '\'' +
                ", dateInscription=" + dateInscription +
                ", dateFin=" + dateFin +
                ", etatOF_User=" + etatOF_User +
                ", pharmacie='" + pharmacie + '\'' +
                ", admin=" + admin +
                '}';
    }
}
