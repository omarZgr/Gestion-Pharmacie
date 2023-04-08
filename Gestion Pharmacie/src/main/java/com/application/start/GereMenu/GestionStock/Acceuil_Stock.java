package com.application.start.GereMenu.GestionStock;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXComboBox;
import com.jfoenix.controls.JFXTextField;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class Acceuil_Stock implements Initializable {

    @FXML
    private TableColumn<listeStock, String> Code;

    @FXML
    private TableColumn<listeStock, String>  Dosage;

    @FXML
    private TableColumn<listeStock, String>  Localisation;

    @FXML
    private TableColumn<listeStock, String>  Nom;

    @FXML
    private TableColumn<listeStock, Integer>  Quantite;

    @FXML
    private TableColumn<listeStock, String>  T_Rem;

    @FXML
    private TableView<listeStock> TableStock;

    @FXML
    private JFXComboBox<?> comboStock;

    @FXML
    private JFXButton details;

    @FXML
    private JFXTextField inputProduit;

    @FXML
    void chercher(MouseEvent event) {

    }

    @FXML
    void gereAction(ActionEvent event) throws IOException {
        if (event.getSource() == details) /// !!Modifier path
            OpenNewPage(event,"/com/application/start/Gere_Projet/User/addUser.fxml","Add User") ;

    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

    }

    private void OpenNewPage(ActionEvent event,String path,String tilte) throws IOException {

        Stage stage = new Stage() ;
        URL fxmlfile = getClass().getResource(path) ;
        Parent root = FXMLLoader.load(fxmlfile);
        stage.setTitle(tilte);
        stage.setScene(new Scene(root));
        stage.show();
    }

}
