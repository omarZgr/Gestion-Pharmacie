package com.application.start.Gere_BaseDonne.Compte.select.Gere_Compte;



import com.application.start.GereMenu.GestionUser.ListeUser;
import com.application.start.Gere_BaseDonne.prepare;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class S_AcceuilUser {


    public static ArrayList<ListeUser> prepareTableUser() throws SQLException {
        Connection etat = prepare.getConnection() ;

        ArrayList<ListeUser > Data = new ArrayList<>() ;

        if(  !etat.isClosed()) {
            int id;
            PreparedStatement ps;
            ResultSet rs;
            String query = "SELECT Nom,Prenom,Tel from persons join users on users.idUser=persons.idPersonne   where users.etat=true";
            ps = etat.prepareStatement(query);
            rs = ps.executeQuery();

            String nom,prenom,tel ;

            while (rs.next()) {
                nom = rs.getString("Nom") ;
                prenom = rs.getString("Prenom") ;
                tel = rs.getString("Tel") ;

                Data.add(new ListeUser(nom,prenom,tel));
            }
        }
        return Data;


    }
    public static ArrayList<ListeUser> prepareTableUserSelected_ALL_NonActive() throws SQLException {
        Connection etat = prepare.getConnection() ;

        ArrayList<ListeUser > Data = new ArrayList<>() ;

        if(  !etat.isClosed()) {
            int id;
            PreparedStatement ps;
            ResultSet rs;
            String query = "SELECT Nom,Prenom,Tel from persons join users on users.idUser=persons.idPersonne ";
            ps = etat.prepareStatement(query);
            rs = ps.executeQuery();

            String nom,prenom,tel ;

            while (rs.next()) {
                nom = rs.getString(1) ;
                prenom = rs.getString("Prenom") ;
                tel = rs.getString("Tel") ;

                Data.add(new ListeUser(nom,prenom,tel));
            }
        }
        return Data;


    }
    public static ArrayList<ListeUser> prepareTableUserSelectedBy_ALL_Prenom_Active(String prenomSelected) throws SQLException {
        Connection etat = prepare.getConnection() ;

        ArrayList<ListeUser > Data = new ArrayList<>() ;

        if(  !etat.isClosed()) {
            int id;
            PreparedStatement ps;
            ResultSet rs;
            String query = "SELECT Nom,Prenom,Tel from persons join users on users.idUser=persons.idPersonne where persons.Prenom=? and users.etat=true";
            ps = etat.prepareStatement(query);
            ps.setString(1,prenomSelected);
            rs = ps.executeQuery();

            String nom,prenom,tel ;

            while (rs.next()) {
                nom = rs.getString("Nom") ;
                prenom = rs.getString("Prenom") ;
                tel = rs.getString("Tel") ;

                Data.add(new ListeUser(nom,prenom,tel));
            }
        }
        return Data;


    }
    public static ArrayList<ListeUser> prepareTableUserSelectedBy_ALL_Prenom_NonActive(String prenomSelected) throws SQLException {
        Connection etat = prepare.getConnection() ;

        ArrayList<ListeUser > Data = new ArrayList<>() ;

        if(  !etat.isClosed()) {
            int id;
            PreparedStatement ps;
            ResultSet rs;
            String query = "SELECT Nom,Prenom,Tel from persons join users on users.idUser=persons.idPersonne where persons.Prenom=?";
            ps = etat.prepareStatement(query);
            ps.setString(1,prenomSelected);
            rs = ps.executeQuery();

            String nom,prenom,tel ;

            while (rs.next()) {
                nom = rs.getString("Nom") ;
                prenom = rs.getString("Prenom") ;
                tel = rs.getString("Tel") ;

                Data.add(new ListeUser(nom,prenom,tel));
            }
        }
        return Data;


    }



    public static ArrayList<ListeUser> prepareTableUserSelectedBy_Phermo_Active() throws SQLException {
        Connection etat = prepare.getConnection() ;

        ArrayList<ListeUser > Data = new ArrayList<>() ;

        if(  !etat.isClosed()) {
            int id;
            PreparedStatement ps;
            ResultSet rs;
            String query = "SELECT persons.Nom,persons.Prenom,persons.Tel from persons join users on users.idUser=persons.idPersonne  JOIN phero ON  phero.idPhero=users.idUser where users.etat=true";
            ps = etat.prepareStatement(query);
            rs = ps.executeQuery();

            String nom,prenom,tel ;

            while (rs.next()) {
                nom = rs.getString("Nom") ;
                prenom = rs.getString("Prenom") ;
                tel = rs.getString("Tel") ;

                Data.add(new ListeUser(nom,prenom,tel));
            }
        }
        return Data;


    }
    public static ArrayList<ListeUser> prepareTableUserSelectedBy_Phermo_NonActive() throws SQLException {
        Connection etat = prepare.getConnection() ;

        ArrayList<ListeUser > Data = new ArrayList<>() ;

        if(  !etat.isClosed()) {
            int id;
            PreparedStatement ps;
            ResultSet rs;
            String query = "SELECT persons.Nom,persons.Prenom,persons.Tel from persons join users on users.idUser=persons.idPersonne  JOIN phero ON  phero.idPhero=users.idUser";
            ps = etat.prepareStatement(query);
            rs = ps.executeQuery();

            String nom,prenom,tel ;

            while (rs.next()) {
                nom = rs.getString("Nom") ;
                prenom = rs.getString("Prenom") ;
                tel = rs.getString("Tel") ;

                Data.add(new ListeUser(nom,prenom,tel));
            }
        }
        return Data;


    }
    public static ArrayList<ListeUser> prepareTableUserSelectedBy_TypeUser_prenom(String typeUser,String prenomSelected) throws SQLException {
        Connection etat = prepare.getConnection() ;

        ArrayList<ListeUser > Data = new ArrayList<>() ;

        if(  !etat.isClosed()) {
            int id;
            PreparedStatement ps;
            ResultSet rs;
            String query = "SELECT nom,Prenom,Tel from users where typeUser=? and Prenom=? and etat=true";
            ps = etat.prepareStatement(query);
            ps.setString(1,typeUser);
            ps.setString(2,prenomSelected);
            rs = ps.executeQuery();

            String nom,prenom,tel ;

            while (rs.next()) {
                nom = rs.getString("Nom") ;
                prenom = rs.getString("Prenom") ;
                tel = rs.getString("Tel") ;

                Data.add(new ListeUser(nom,prenom,tel));
            }
        }
        return Data;


    }
    public static ArrayList<ListeUser> prepareTableUserSelectedBy_Phermo_Prenom_Active(String prenomSelected) throws SQLException {
        Connection etat = prepare.getConnection() ;

        ArrayList<ListeUser > Data = new ArrayList<>() ;

        if(  !etat.isClosed()) {
            int id;
            PreparedStatement ps;
            ResultSet rs;
            String query = "SELECT persons.Nom,persons.Prenom,persons.Tel from persons join users on users.idUser=persons.idPersonne  JOIN phero ON  phero.idPhero=users.idUser where users.etat=true and persons.Prenom=?";
            ps = etat.prepareStatement(query);
            ps.setString(1,prenomSelected);
            rs = ps.executeQuery();

            String nom,prenom,tel ;

            while (rs.next()) {
                nom = rs.getString("Nom") ;
                prenom = rs.getString("Prenom") ;
                tel = rs.getString("Tel") ;

                Data.add(new ListeUser(nom,prenom,tel));
            }
        }
        return Data;


    }
    public static ArrayList<ListeUser> prepareTableUserSelectedBy_Phermo_Prenom_NonActive(String prenomSelected) throws SQLException {
        Connection etat = prepare.getConnection() ;

        ArrayList<ListeUser > Data = new ArrayList<>() ;

        if(  !etat.isClosed()) {
            int id;
            PreparedStatement ps;
            ResultSet rs;
            String query = "SELECT persons.Nom,persons.Prenom,persons.Tel from persons join users on users.idUser=persons.idPersonne  JOIN phero ON  phero.idPhero=users.idUser where  persons.Prenom=?";
            ps = etat.prepareStatement(query);
            ps.setString(1,prenomSelected);
            rs = ps.executeQuery();

            String nom,prenom,tel ;

            while (rs.next()) {
                nom = rs.getString("nom") ;
                prenom = rs.getString("Prenom") ;
                tel = rs.getString("Tel") ;

                Data.add(new ListeUser(nom,prenom,tel));
            }
        }
        return Data;


    }



    public static ArrayList<ListeUser> prepareTableUserSelectedBy_Livreur_Active() throws SQLException {
        Connection etat = prepare.getConnection() ;

        ArrayList<ListeUser > Data = new ArrayList<>() ;

        if(  !etat.isClosed()) {
            int id;
            PreparedStatement ps;
            ResultSet rs;
            String query = "SELECT persons.Nom,persons.Prenom,persons.Tel from persons join users on users.idUser=persons.idPersonne  JOIN livreur ON  livreur.idLivreur =users.idUser where users.etat=true";
            ps = etat.prepareStatement(query);
            rs = ps.executeQuery();

            String nom,prenom,tel ;

            while (rs.next()) {
                nom = rs.getString("nom") ;
                prenom = rs.getString("Prenom") ;
                tel = rs.getString("Tel") ;

                Data.add(new ListeUser(nom,prenom,tel));
            }
        }
        return Data;


    }

    public static ArrayList<ListeUser> prepareTableUserSelectedBy_Livreur_NonActive() throws SQLException {
        Connection etat = prepare.getConnection() ;

        ArrayList<ListeUser > Data = new ArrayList<>() ;

        if(  !etat.isClosed()) {
            int id;
            PreparedStatement ps;
            ResultSet rs;
            String query = "SELECT persons.Nom,persons.Prenom,persons.Tel from persons join users on users.idUser=persons.idPersonne  JOIN livreur ON  livreur.idLivreur =users.idUser";
            ps = etat.prepareStatement(query);
            rs = ps.executeQuery();

            String nom,prenom,tel ;

            while (rs.next()) {
                nom = rs.getString("nom") ;
                prenom = rs.getString("Prenom") ;
                tel = rs.getString("Tel") ;

                Data.add(new ListeUser(nom,prenom,tel));
            }
        }
        return Data;


    }

    public static ArrayList<ListeUser> prepareTableUserSelectedBy_Livreur_Prenom_Active(String prenomSelected) throws SQLException {
        Connection etat = prepare.getConnection() ;

        ArrayList<ListeUser > Data = new ArrayList<>() ;

        if(  !etat.isClosed()) {
            int id;
            PreparedStatement ps;
            ResultSet rs;
            String query = "SELECT persons.Nom,persons.Prenom,persons.Tel from persons join users on users.idUser=persons.idPersonne  JOIN livreur ON  livreur.idLivreur =users.idUser where users.etat=true and persons.Prenom=?";
            ps = etat.prepareStatement(query);
            ps.setString(1,prenomSelected);
            rs = ps.executeQuery();

            String nom,prenom,tel ;

            while (rs.next()) {
                nom = rs.getString("nom") ;
                prenom = rs.getString("Prenom") ;
                tel = rs.getString("Tel") ;

                Data.add(new ListeUser(nom,prenom,tel));
            }
        }
        return Data;


    }

    public static ArrayList<ListeUser> prepareTableUserSelectedBy_Livreur_Prenom_NonActive(String prenomSelected) throws SQLException {
        Connection etat = prepare.getConnection() ;

        ArrayList<ListeUser > Data = new ArrayList<>() ;

        if(  !etat.isClosed()) {
            int id;
            PreparedStatement ps;
            ResultSet rs;
            String query = "SELECT persons.Nom,persons.Prenom,persons.Tel from persons join users on users.idUser=persons.idPersonne JOIN livreur ON  livreur.idLivreur =users.idUser where  persons.Prenom=?";
            ps = etat.prepareStatement(query);
            ps.setString(1,prenomSelected);
            rs = ps.executeQuery();

            String nom,prenom,tel ;

            while (rs.next()) {
                nom = rs.getString("nom") ;
                prenom = rs.getString("Prenom") ;
                tel = rs.getString("Tel") ;

                Data.add(new ListeUser(nom,prenom,tel));
            }
        }
        return Data;


    }





    public static void main(String[] args) throws SQLException {

        String prenom = "omar" ;
        ArrayList<ListeUser> data = S_AcceuilUser.prepareTableUser() ;
        for (int i=0;i<data.size();i++)
        {
            System.out.println(data.get(i));
        }

    }









}
