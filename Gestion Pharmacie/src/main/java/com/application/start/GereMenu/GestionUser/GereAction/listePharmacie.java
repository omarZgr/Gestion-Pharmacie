package com.application.start.GereMenu.GestionUser.GereAction;

import java.sql.SQLException;

public class listePharmacie {

    String nomPrive ;
    String ville ;

    public listePharmacie(String nomPrive, String ville) {
        this.nomPrive = nomPrive;
        this.ville = ville;
    }

    public listePharmacie(){} ;


    @Override
    public String toString() {
        return nomPrive + "-" + ville ;
    }

    public static void main(String[] args) throws SQLException {

        listePharmacie test = new listePharmacie("pharmacie01","mdiq") ;

        String nomPrv = test.toString().split("-")[0] ;

        System.out.println("NomPrv est : " +nomPrv);

        System.out.println("pharmacie01".equals(nomPrv));

    }

}
