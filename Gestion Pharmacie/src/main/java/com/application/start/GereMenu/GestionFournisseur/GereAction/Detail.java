package com.application.start.GereMenu.GestionFournisseur.GereAction;

import com.application.start.GereMenu.GestionFournisseur.Acceuil_Fournisseur;
import com.jfoenix.controls.JFXButton;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;

import java.net.URL;
import java.util.ResourceBundle;

public class Detail implements Initializable {

    @FXML
    private JFXButton Back;

    @FXML
    private Label DCI1;

    @FXML
    private Label FORME;

    @FXML
    private Label PH;

    @FXML
    private Label PPV;

    @FXML
    private Label PRESENTATION;

    @FXML
    private Label PRIX_BR;

    @FXML
    private Label TAUX_REMBOURSEMENT;

    @FXML
    private Label UNITE_DOSAGE1;

    @FXML
    private Label code;

    @FXML
    private Label dosage;

    @FXML
    private Label nom;

    @FXML
    void back(MouseEvent event) {

    }

    @FXML
    void gereAction(ActionEvent event) {
        if (event.getSource() == Back)
        {
            Stage stage = (Stage) ((Button) event.getSource()).getScene().getWindow();
            stage.close();
        }

    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        getData() ;
    }

    private void getData()
    {
        code.setText(Acceuil_Fournisseur.prductSelected.getCode());
        nom.setText(Acceuil_Fournisseur.prductSelected.getNom());
        DCI1.setText(Acceuil_Fournisseur.prductSelected.getDCI1());
        dosage.setText(Acceuil_Fournisseur.prductSelected.getDosage());
        UNITE_DOSAGE1.setText(Acceuil_Fournisseur.prductSelected.getUNITE_DOSAGE1());
        FORME.setText(Acceuil_Fournisseur.prductSelected.getFORME());
        PRESENTATION.setText(Acceuil_Fournisseur.prductSelected.getPRESENTATION());
        PPV.setText(Acceuil_Fournisseur.prductSelected.getPPV());
        PH.setText(Acceuil_Fournisseur.prductSelected.getPH());
        PRIX_BR.setText(Acceuil_Fournisseur.prductSelected.getPRIX_BR());
        TAUX_REMBOURSEMENT.setText(Acceuil_Fournisseur.prductSelected.getTAUX_REMBOURSEMENT());

    }
}
