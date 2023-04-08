package com.application.start.Gere_BaseDonne.Compte.check.Gere_Compte;

import com.application.start.Gere_BaseDonne.prepare;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class C_AddUser {

    public static boolean checkCIN(String CIN)
    {
        Connection etat = prepare.getConnection() ;

        try {
            if(  !etat.isClosed())
            {
                PreparedStatement ps ;
                ResultSet rs ;
                String query = "SELECT * from persons  WHERE persons.CIN=? " ;
                ps = etat.prepareStatement(query) ;
                ps.setString(1,CIN);
                rs = ps.executeQuery() ;
                if (rs.next()){
                    return true;
                }
            }
        } catch (SQLException e) {
        }

        return false ;

    }

    public static boolean checkTel(String Tel)
    {
        Connection etat = prepare.getConnection() ;

        try {
            if(  !etat.isClosed())
            {
                PreparedStatement ps ;
                ResultSet rs ;
                String query = "SELECT * from persons  WHERE persons.Tel=? " ;
                ps = etat.prepareStatement(query) ;
                ps.setString(1,Tel);
                rs = ps.executeQuery() ;
                if (rs.next()){
                    return true;
                }
            }
        } catch (SQLException e) {
        }

        return false ;

    }

    public static boolean checkUserName(String userName)
    {
        Connection etat = prepare.getConnection() ;

        try {
            if(  !etat.isClosed())
            {
                PreparedStatement ps ;
                ResultSet rs ;
                String query = "SELECT * from users  WHERE userName =? " ;
                ps = etat.prepareStatement(query) ;
                ps.setString(1,userName);
                rs = ps.executeQuery() ;
                if (rs.next()){
                    return true;
                }
            }
        } catch (SQLException e) {
        }

        return false ;

    }



}
