package com.application.start.Gere_BaseDonne.Insert_ALL_Produit_DB;



import java.io.*;
import java.sql.*;

public  class Insert_Produits_To_BD_From_CSV {


    public static void Insert_Produits_Into_Table() throws IOException, SQLException {

        String filePath = "C:\\Users\\hp\\OneDrive\\Bureau\\Projet Pharmacie\\awlPratique\\Start001\\src\\main\\java\\com\\application\\start\\Gere_BaseDonne\\Insert_ALL_Produit_DB\\Insert_Produits_To_BD_From_CSV.txt" ;

        int batchSize=20 ;

        Connection con = null;
        try {
            con = DriverManager.getConnection("jdbc:mysql://localhost:3306/pharmacieapp", "root", "");
            con.setAutoCommit(false);


            String insertCmd = "Insert into liste_produits (CodeProduits,NOM,DCI1,DOSAGE1,UNITE_DOSAGE1,FORME,PRESENTATION,PPV,PH,PRIX_BR,PRINCEPS_GENERIQUE,TAUX_REMBOURSEMENT,Selected) values(?,?,?,?,?,?,?,?,?,?,?,?,false)";

            PreparedStatement psInsert = con.prepareStatement(insertCmd);

            BufferedReader lineReader = new BufferedReader(new FileReader(filePath));

            //String CodeProduits,NOM,DCI1,DOSAGE1,UNITE_DOSAGE1,FORME,PRESENTATION,PPV,PH,PRIX_BR,PRINCEPS_GENERIQUE,TAUX_REMBOURSEMENT ;

            String lineText = null;
            int count = 0;
            lineReader.readLine();
            while ((lineText = lineReader.readLine()) != null) {
                String[] data = lineText.split(";");

                String CodeProduits = data[0];
                String NOM = data[1];
                String DCI1 = data[2];
                String DOSAGE1 = data[3];
                String UNITE_DOSAGE1 = data[4];
                String FORME = data[5];
                String PRESENTATION = data[6];
                String PPV = data[7];
                String PH = data[8];
                String PRIX_BR =data[9];




                String PRINCEPS_GENERIQUE = data[10];
                String TAUX_REMBOURSEMENT = data[11];

                psInsert.setString(1, CodeProduits);
                psInsert.setString(2, NOM);
                psInsert.setString(3, DCI1);
                psInsert.setString(4, DOSAGE1);
                psInsert.setString(5, UNITE_DOSAGE1);
                psInsert.setString(6, FORME);
                psInsert.setString(7, PRESENTATION);
                psInsert.setString(8, PPV);
                psInsert.setString(9, PH);
                psInsert.setString(10, PRIX_BR);
                psInsert.setString(11, PRINCEPS_GENERIQUE);
                psInsert.setString(12, TAUX_REMBOURSEMENT);

                psInsert.addBatch();

                if (count % batchSize == 0) {
                    psInsert.executeBatch();
                }
            }
            lineReader.close();
            psInsert.executeBatch();
            System.out.println("Traitement Valid ");
            con.commit();
            con.close();


        } catch (SQLException e) {


        }


    }

    public static Float removeDecimalPoint(String str) {


        try {
            if (str.isEmpty())
                return 0f ;

            if (str.contains("."))
                str =  str.replaceAll(".", "");

            if (str.contains(","))
                str =  str.replaceAll(",", ".");

            if (str.contains(" "))
                str =  str.replaceAll(" ", "");


            return Float.parseFloat(str) ;
        }
        catch (Exception e){}

        return 0f ;






    }


    public static void main(String[] args) throws SQLException, IOException {

         Insert_Produits_To_BD_From_CSV.Insert_Produits_Into_Table();

        System.out.println(Insert_Produits_To_BD_From_CSV.removeDecimalPoint(""));

    }





}
