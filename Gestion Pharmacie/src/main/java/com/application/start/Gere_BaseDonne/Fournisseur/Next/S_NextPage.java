package com.application.start.Gere_BaseDonne.Fournisseur.Next;

import com.application.start.GereMenu.GestionFournisseur.GereAction.Next.ListePanier;
import com.application.start.GereMenu.GestionFournisseur.PrductSelected;
import com.application.start.Gere_BaseDonne.prepare;

import java.math.BigDecimal;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.DecimalFormat;
import java.text.DecimalFormatSymbols;
import java.util.ArrayList;

public class S_NextPage {

    public static ArrayList<ListePanier> getData_Panier() throws SQLException {

        Connection etat = prepare.getConnection() ;

        ArrayList<ListePanier>  Data = new ArrayList<>();

        if(  !etat.isClosed()) {
            PreparedStatement ps;
            ResultSet rs;
            String query = "SELECT * from liste_produits where  Selected=true";
            ps = etat.prepareStatement(query);
            rs = ps.executeQuery();
            int idListe_Produits ;
            String NOM,DOSAGE1,PRIX_BR,Facture ;
            int Quantite  ;



            while (rs.next()) {
                idListe_Produits = rs.getInt("idListe_Produits") ;
                NOM = rs.getString("NOM") ;
                DOSAGE1 = rs.getString("DOSAGE1") ;
                PRIX_BR = rs.getString("PRIX_BR") ;
                Quantite= rs.getInt("Quantite");

                Facture = String.valueOf(Calcul_Facture(PRIX_BR,Quantite));

                //APRES !!! Modifiy quantite FORM Stock

                Data.add(new ListePanier(idListe_Produits,NOM,DOSAGE1,Facture,PRIX_BR,Quantite)) ;

            }

            return  Data ;
        }

        return  null ;


    }
    private static Float Calcul_Facture(String prix,int quantite) {

        Float sum = null;
        DecimalFormatSymbols symbols = new DecimalFormatSymbols();
        symbols.setDecimalSeparator(',');
        symbols.setGroupingSeparator('.');
        DecimalFormat format = new DecimalFormat("#,##0.00", symbols);

            String formattedNumber = prix.trim().replace(".", "").replace(",", ".");
            Float prix_New_Format = Float.valueOf(formattedNumber);

        return (Float) prix_New_Format * quantite;

    }

    public static String QuantiteActuel_Dans_Stock_Global(int idProduit) throws SQLException {
        Connection etat = prepare.getConnection() ;
        String Quantite ;



        if(  !etat.isClosed()) {
            PreparedStatement ps;
            ResultSet rs;
            String query = "SELECT Quantite_Du_Stock from liste_produits where  idListe_Produits=?";
            ps = etat.prepareStatement(query);
            ps.setInt(1,idProduit);
            rs = ps.executeQuery();

            if (rs.next()) {

                Quantite = rs.getString("Quantite_Du_Stock") ;

                return Quantite ;
            }

        }

        return "Error in get Stock Global" ;
    }




    public static void main(String[] args) throws SQLException {

        ArrayList<ListePanier> data = S_NextPage.getData_Panier() ;
       System.out.println("SIze panier : "+data.size());
    }


}
