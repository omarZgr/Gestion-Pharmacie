package com.application.start;

import com.application.start.Gere_BaseDonne.Fournisseur.S_AcceuilFournisseur;
import com.jfoenix.controls.JFXButton;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.ResourceBundle;

public class EspacePatronController implements Initializable {

    @FXML
    private JFXButton Account;

    @FXML
    private BorderPane Box;

    @FXML
    private JFXButton Fournisseur;

    @FXML
    void swapMenu(ActionEvent event) throws IOException, SQLException {
        if (event.getSource() == Account)
        {
            System.out.println("Clicked in Account");
            preparePage("/com/application/start/Gere_Projet/User/AcceuilUser.fxml");
        }
        else
            if (event.getSource() == Fournisseur)
            {
                System.out.println("Clicked in Fournisseur");
                preparePage("/com/application/start/Gere_Projet/Fournisseur/AcceuilFournisseur.fxml");

            }



    }
    private void preparePage(String path) throws IOException {
        URL fxmlfile = getClass().getResource(path) ;
        AnchorPane view = FXMLLoader.load(fxmlfile);
        Box.setCenter(view);
    }



    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

        try {
            preparePage("/com/application/start/Gere_Projet/User/AcceuilUser.fxml");
            S_AcceuilFournisseur.inisialise_Produit_Select_BD();

        } catch (IOException | SQLException e) {
            throw new RuntimeException(e);
        }


    }
}
