package com.application.start.Gere_BaseDonne.Compte.check.Gere_Compte;

import com.application.start.Gere_BaseDonne.Compte.select.Gere_Compte.S_ModifiyUser;
import com.application.start.Gere_BaseDonne.prepare;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class C_ModifiyUser {

    public static boolean checkPhermo(int id) throws SQLException {
        Connection etat = prepare.getConnection();


        if (!etat.isClosed()) {
            PreparedStatement ps;
            ResultSet rs;
            String query = "SELECT * from persons join users on users.idUser=persons.idPersonne  JOIN phero ON  phero.idPhero=users.idUser where persons.idPersonne=?";
            ps = etat.prepareStatement(query);
            ps.setInt(1, id);
            rs = ps.executeQuery();

            if (rs.next()) return true;

        }

        return false;

    }

    public static boolean checkLivreur(int id) throws SQLException {
        Connection etat = prepare.getConnection();


        if (!etat.isClosed()) {
            PreparedStatement ps;
            ResultSet rs;
            String query = "SELECT * from persons join users on users.idUser=persons.idPersonne  JOIN livreur ON  livreur.idLivreur =users.idUser where persons.idPersonne=?";
            ps = etat.prepareStatement(query);
            ps.setInt(1, id);
            rs = ps.executeQuery();

            if (rs.next()) return true;

        }

        return false;

    }

    public static boolean checkTel_without_Parametre(int id,String Tel)
    {
        Connection etat = prepare.getConnection() ;

        try {
            if(  !etat.isClosed())
            {
                PreparedStatement ps ;
                ResultSet rs ;
                String query = "SELECT * from persons  WHERE persons.Tel=? and persons.idPersonne !=?" ;
                ps = etat.prepareStatement(query) ;
                ps.setString(1,Tel);
                ps.setInt(2,id);
                rs = ps.executeQuery() ;
                if (rs.next()){
                    return true;
                }
            }
        } catch (SQLException e) {
        }

        return false ;

    }


    public static void main(String[] args) throws SQLException {

        System.out.println(C_ModifiyUser.checkPhermo(141));


    }




}
