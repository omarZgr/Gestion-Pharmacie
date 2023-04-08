package com.application.start.Gere_BaseDonne.Compte.select.Gere_Compte;

import com.application.start.GereMenu.GestionUser.GereAction.listePharmacie;
import com.application.start.Gere_BaseDonne.prepare;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class S_AddUser {

    public static ArrayList<listePharmacie> prepareComboBoxPharmacie() throws SQLException {
        Connection etat = prepare.getConnection() ;

        ArrayList<listePharmacie> Data = new ArrayList<>() ;

        if(  !etat.isClosed()) {
            PreparedStatement ps;
            ResultSet rs;
            String query = "SELECT NomPrive,Ville from pharmacie";
            ps = etat.prepareStatement(query);
            rs = ps.executeQuery();
            String nomPrive,ville;

            while (rs.next()) {
                nomPrive = rs.getString("NomPrive") ;
                ville = rs.getString("Ville") ;
                Data.add(new listePharmacie(nomPrive,ville));
            }
        }
        return Data;


    }

    public static int getID_OF_Pharmacie(String nomPrv)
    {
        Connection etat = prepare.getConnection() ;

        try {
            if(  !etat.isClosed())
            {
                PreparedStatement ps ;
                ResultSet rs ;
                String query = "SELECT idPharmacie from pharmacie  WHERE NomPrive=? " ;
                ps = etat.prepareStatement(query) ;
                ps.setString(1,nomPrv);
                rs = ps.executeQuery() ;
                if (rs.next()){
                    return rs.getInt(1);
                }
            }
        } catch (SQLException e) {
        }

        return -1 ;

    }


    public static void main(String[] args) throws SQLException {

      System.out.println(S_AddUser.getID_OF_Pharmacie("pharmacie01"));


    }


}
