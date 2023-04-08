package com.application.start.Gere_BaseDonne.Fournisseur;

import com.application.start.GereMenu.GestionFournisseur.ListeProduit;
import com.application.start.GereMenu.GestionFournisseur.PrductSelected;
import com.application.start.Gere_BaseDonne.prepare;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.stage.Stage;

import java.math.BigDecimal;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.DecimalFormat;
import java.text.DecimalFormatSymbols;
import java.util.ArrayList;

public class S_AcceuilFournisseur {

    public static void inisialise_Produit_Select_BD() throws SQLException {
        Connection etat = prepare.getConnection() ;
        String update = "UPDATE liste_produits SET Selected =false,Quantite=0";
        PreparedStatement psUpdate = etat.prepareStatement(update);
        psUpdate.executeUpdate();


    }

    public static ArrayList<ListeProduit> prepareTablePrduits() throws SQLException {
        Connection etat = prepare.getConnection() ;

        ArrayList<ListeProduit > Data = new ArrayList<>() ;

        if(  !etat.isClosed()) {
            int id;
            PreparedStatement ps;
            ResultSet rs;
            String query = "SELECT idListe_Produits ,CodeProduits,NOM,DOSAGE1,PRIX_BR,Selected,Quantite_Du_Stock from liste_produits ";
            ps = etat.prepareStatement(query);
            rs = ps.executeQuery();

            int idListe_Produits ;
            String CodeProduits,NOM,DOSAGE1,PRIX_BR,Quantite_Du_Stock="mama" ;
            Boolean Selected ;



            while (rs.next()) {
                idListe_Produits = rs.getInt(1) ;
                CodeProduits = rs.getString(2) ;
                NOM = rs.getString(3) ;
                DOSAGE1 = rs.getString(4) ;
                PRIX_BR = rs.getString(5) ;
                Selected = rs.getBoolean(6) ;
                Quantite_Du_Stock=rs.getString(7) ;



                Data.add(new ListeProduit(idListe_Produits,CodeProduits,NOM,DOSAGE1,PRIX_BR,Quantite_Du_Stock,Selected));
            }
        }
        return Data;


    }

    public static int getID_Product_By_Code(String code)
    {
        Connection etat = prepare.getConnection() ;

        try {
            if(  !etat.isClosed())
            {
                PreparedStatement ps ;
                ResultSet rs ;
                String query = "SELECT idListe_Produits  from liste_produits  WHERE CodeProduits =? " ;
                ps = etat.prepareStatement(query) ;
                ps.setString(1,code);
                rs = ps.executeQuery() ;
                if (rs.next()){
                    return rs.getInt(1);
                }
            }
        } catch (SQLException e) {
        }

        return -1 ;
    }

    public static PrductSelected getData_Product_Selcted(int idSelcted) throws SQLException {
        Connection etat = prepare.getConnection() ;

        PrductSelected  Data = new PrductSelected();

        if(  !etat.isClosed()) {
            int id;
            PreparedStatement ps;
            ResultSet rs;
            String query = "SELECT * from liste_produits where idListe_Produits=? ";
            ps = etat.prepareStatement(query);
            ps.setInt(1,idSelcted);
            rs = ps.executeQuery();

            int idListe_Produits ;
            String CodeProduits,NOM,DCI1,DOSAGE1,UNITE_DOSAGE1,FORME,PRESENTATION,PPV,PH,PRIX_BR,PRINCEPS_GENERIQUE,TAUX_REMBOURSEMENT ;

            while (rs.next()) {
                idListe_Produits = rs.getInt("idListe_Produits") ;
                CodeProduits = rs.getString("CodeProduits") ;
                NOM = rs.getString("NOM") ;
                DCI1 = rs.getString("DCI1") ;
                DOSAGE1 = rs.getString("DOSAGE1") ;
                UNITE_DOSAGE1 = rs.getString("UNITE_DOSAGE1") ;
                FORME = rs.getString("FORME") ;
                PRESENTATION = rs.getString("PRESENTATION") ;
                PPV = rs.getString("PPV") ;
                PH = rs.getString("PH") ;
                PRIX_BR = rs.getString("PRIX_BR") ;
                PRINCEPS_GENERIQUE = rs.getString("PRINCEPS_GENERIQUE") ;
                TAUX_REMBOURSEMENT = rs.getString("TAUX_REMBOURSEMENT") ;

                return new PrductSelected(idListe_Produits,CodeProduits,NOM,DCI1,DOSAGE1,UNITE_DOSAGE1,FORME,PRESENTATION,PPV,PH,PRIX_BR,PRINCEPS_GENERIQUE,TAUX_REMBOURSEMENT);
            }
        }

        return  null ;


    }

    public static ArrayList<ListeProduit> prepareTablePrduitsSelectedBy_Code(String CodeSelected) throws SQLException {
        Connection etat = prepare.getConnection() ;

        ArrayList<ListeProduit > Data = new ArrayList<>() ;

        if(  !etat.isClosed()) {
            int id;
            PreparedStatement ps;
            ResultSet rs;
            String query = "SELECT idListe_Produits ,CodeProduits,NOM,DOSAGE1,PRIX_BR,Selected,Quantite_Du_Stock  from liste_produits where CodeProduits like ? ";
            ps = etat.prepareStatement(query);
            ps.setString(1,CodeSelected);
            rs = ps.executeQuery();

            int idListe_Produits ;
            String CodeProduits,NOM,DOSAGE1,PRIX_BR,Quantite_Du_Stock ;
            Boolean Selected ;

            while (rs.next()) {
                idListe_Produits = rs.getInt(1) ;
                CodeProduits = rs.getString(2) ;
                NOM = rs.getString(3) ;
                DOSAGE1 = rs.getString(4) ;
                PRIX_BR = rs.getString(5) ;
                Selected = rs.getBoolean(6) ;
                Quantite_Du_Stock = rs.getString("Quantite_Du_Stock");
                Data.add(new ListeProduit(idListe_Produits,CodeProduits,NOM,DOSAGE1,PRIX_BR,Quantite_Du_Stock,Selected));
            }
        }
        return Data;


    }
    public static ArrayList<ListeProduit> prepareTablePrduitsSelectedBy_Nom(String NomSelected) throws SQLException {
        Connection etat = prepare.getConnection() ;

        ArrayList<ListeProduit > Data = new ArrayList<>() ;

        if(  !etat.isClosed()) {
            int id;
            PreparedStatement ps;
            ResultSet rs;
            String query = "SELECT idListe_Produits ,CodeProduits,NOM,DOSAGE1,PRIX_BR,Selected,Quantite_Du_Stock  from liste_produits where NOM like ?  ";
            ps = etat.prepareStatement(query);
            ps.setString(1,NomSelected);
            rs = ps.executeQuery();

            int idListe_Produits ;
            String CodeProduits,NOM,DOSAGE1,PRIX_BR,Quantite_Du_Stock ;
            Boolean Selected ;


            while (rs.next()) {
                idListe_Produits = rs.getInt(1) ;
                CodeProduits = rs.getString(2) ;
                NOM = rs.getString(3) ;
                DOSAGE1 = rs.getString(4) ;
                PRIX_BR = rs.getString(5) ;
                Selected = rs.getBoolean(6) ;
                Quantite_Du_Stock = rs.getString("Quantite_Du_Stock");


                Data.add(new ListeProduit(idListe_Produits,CodeProduits,NOM,DOSAGE1,PRIX_BR,Quantite_Du_Stock,Selected));
            }
        }
        return Data;


    }

    public static int getSize_Panier() throws SQLException {

        Connection etat = prepare.getConnection() ;

        int sizePanier = -1 ;


        if(  !etat.isClosed()) {
            int id;
            PreparedStatement ps;
            ResultSet rs;
            String query = "SELECT count(*) from liste_produits where  Selected=true";
            ps = etat.prepareStatement(query);
            rs = ps.executeQuery();


            if (rs.next()) {

                sizePanier = rs.getInt(1) ;

                return sizePanier;

            }


        }

        return  sizePanier ;


    }
    public static BigDecimal getFacture_Panier() throws SQLException {

        Connection etat = prepare.getConnection() ;

        Float sizePanier = -1f ;




        if(  !etat.isClosed()) {
            int id;
            PreparedStatement ps;
            ResultSet rs;
            String query = "SELECT PRIX_BR from liste_produits where  Selected=true";
            ps = etat.prepareStatement(query);
            rs = ps.executeQuery();
            ArrayList<String> All_PRIX_BR = new ArrayList<>() ;

            while (rs.next()) {

                All_PRIX_BR.add(rs.getString("PRIX_BR")) ;

            }

                Calcul_Facture(All_PRIX_BR) ;

                return Calcul_Facture(All_PRIX_BR);

            }


        return null;
    }

    private static BigDecimal Calcul_Facture(ArrayList<String> allPrixBr) {

        BigDecimal sum = BigDecimal.ZERO;
        DecimalFormatSymbols symbols = new DecimalFormatSymbols();
        symbols.setDecimalSeparator(',');
        symbols.setGroupingSeparator('.');
        DecimalFormat format = new DecimalFormat("#,##0.00", symbols);

        for (String number : allPrixBr) {
            String formattedNumber = number.trim().replace(".", "").replace(",", ".");
            BigDecimal value = BigDecimal.ZERO;
            if (!formattedNumber.isEmpty()) {
                value = new BigDecimal(formattedNumber);
            }
            sum = sum.add(value);
        }

        return sum;

    }


    public static void main(String[] args) throws SQLException {





    }


}
