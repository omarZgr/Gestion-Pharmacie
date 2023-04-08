package com.application.start.Gere_BaseDonne.Compte.select.Gere_Compte;

import com.application.start.GereMenu.GestionUser.GereAction.listePharmacie;
import com.application.start.GereMenu.GestionUser.UserSelected;
import com.application.start.Gere_BaseDonne.prepare;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;

public class S_ModifiyUser {

    public static int getID_OF_User(String tel)
    {
        Connection etat = prepare.getConnection() ;

        try {
            if(  !etat.isClosed())
            {
                PreparedStatement ps ;
                ResultSet rs ;
                String query = "SELECT idPersonne  from persons  WHERE Tel =? " ;
                ps = etat.prepareStatement(query) ;
                ps.setString(1,tel);
                rs = ps.executeQuery() ;
                if (rs.next()){
                    return rs.getInt(1);
                }
            }
        } catch (SQLException e) {
        }

        return -1 ;

    }

    public static UserSelected getData_LivreurSelected(int id) throws SQLException {
        Connection etat = prepare.getConnection() ;


        if(  !etat.isClosed()) {
            PreparedStatement ps;
            ResultSet rs;
            String query = "SELECT Nom,Prenom,CIN,Tel,DateInscrire,userName,passwordd,dateFin,etat from persons join users on users.idUser=persons.idPersonne join livreur on livreur.idLivreur  =users.idUser where persons.idPersonne=?" ;
            ps = etat.prepareStatement(query);
            ps.setInt(1,id);
            rs = ps.executeQuery();

            String nom,prenom,tel,cin,userName,password ;
            Date dateInscrire,dateFin ;
            boolean etatOF_User ;

            while (rs.next()) {
                nom = rs.getString("Nom") ;
                prenom = rs.getString("Prenom") ;
                tel = rs.getString("Tel") ;
                userName = rs.getString("userName") ;
                password = rs.getString("passwordd") ;
                etatOF_User = rs.getBoolean("etat") ;
                cin = rs.getString("CIN") ;
                dateInscrire = rs.getDate("DateInscrire") ;
                dateFin = rs.getDate("dateFin") ;

                return new UserSelected(id,nom,prenom,tel,cin,userName,password,"Livreur",dateInscrire,dateFin,etatOF_User) ;


            }
        }
        return null;

    }
    public static UserSelected getData_PhermoSelected(int id) throws SQLException {
        Connection etat = prepare.getConnection() ;


        if(  !etat.isClosed()) {
            PreparedStatement ps;
            ResultSet rs;
            String query = "SELECT Nom,Prenom,CIN,Tel,DateInscrire,userName,passwordd,dateFin,etat,responsabilte,NomPrive,Ville from persons join users on users.idUser=persons.idPersonne join phero on phero.idPhero =users.idUser join pharmacie on pharmacie.idPharmacie=phero.idPharmacie where persons.idPersonne =?";
            ps = etat.prepareStatement(query);
            ps.setInt(1, id);
            rs = ps.executeQuery();

            String nom, prenom, tel, cin, userName, password, pharmacieSelected;
            Date dateInscrire, dateFin;
            boolean etatOF_User, resoponsabilite;

            if (rs.next()) {
                nom = rs.getString("Nom");
                prenom = rs.getString("Prenom");
                tel = rs.getString("Tel");
                userName = rs.getString("userName");
                password = rs.getString("passwordd");
                etatOF_User = rs.getBoolean("etat");
                resoponsabilite = rs.getBoolean("responsabilte");
                cin = rs.getString("CIN");
                dateInscrire = rs.getDate("DateInscrire");
                dateFin = rs.getDate("dateFin");
                pharmacieSelected = rs.getString("NomPrive") + "-" + rs.getString("Ville");

                return new UserSelected(id, nom, prenom, tel, cin, userName, password, "Phero", dateInscrire, dateFin, etatOF_User, pharmacieSelected, resoponsabilite);

            }

        }

        return null;

    }

    public static listePharmacie getPharmacieOF_PhermoSelected(int id) throws SQLException {
        Connection etat = prepare.getConnection() ;

         new listePharmacie() ;

        if(  !etat.isClosed()) {
            PreparedStatement ps;
            ResultSet rs;
            String query = "SELECT NomPrive,Ville from pharmacie join phero on phero.idPharmacie = pharmacie.idPharmacie  where phero.idPhero =?";
            ps = etat.prepareStatement(query);
            ps.setInt(1,id);
            rs = ps.executeQuery();
            String nomPrive,ville;

            while (rs.next()) {
                nomPrive = rs.getString("NomPrive") ;
                ville = rs.getString("Ville") ;

                return   new listePharmacie(nomPrive,ville) ;


            }
        }
        return null;


    }

    public static void main(String[] args) throws SQLException {

       UserSelected data = S_ModifiyUser.getData_PhermoSelected(141) ;

       System.out.println(data.toString());



    }




}
