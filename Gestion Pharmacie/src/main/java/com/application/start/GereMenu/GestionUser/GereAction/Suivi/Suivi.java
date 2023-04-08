package com.application.start.GereMenu.GestionUser.GereAction.Suivi;

import com.application.start.GereMenu.GestionUser.Acceuil_User;
import com.jfoenix.controls.JFXToggleButton;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;

import java.net.URL;
import java.util.ResourceBundle;

public class Suivi implements Initializable {

    @FXML
    private DatePicker DateDebut;

    @FXML
    private DatePicker DateFin;

    @FXML
    private ComboBox<String> OutputTypeUser;

    @FXML
    private TextField Tel;

    @FXML
    private JFXToggleButton outputAdmin;

    @FXML
    private TextField outputCIN;

    @FXML
    private ComboBox<String> outputComboPharmacie;

    @FXML
    private JFXToggleButton outputEtat;

    @FXML
    private TextField outputNom;

    @FXML
    private TextField outputPrenom;

    @FXML
    void back(MouseEvent event) {
        Stage stage = (Stage) ((Button) event.getSource()).getScene().getWindow();
        stage.close();
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        getData() ;
    }

    private void getData() {
        outputNom.setText(Acceuil_User.userSelected.getNom());
        outputPrenom.setText(Acceuil_User.userSelected.getPrenom());
        Tel.setText(Acceuil_User.userSelected.getTel());
        outputCIN.setText(Acceuil_User.userSelected.getCIN());
        OutputTypeUser.setValue(Acceuil_User.userSelected.getTypeUser());
        outputEtat.setSelected(Acceuil_User.userSelected.isEtatOF_User());

        java.sql.Date dateDebut = (java.sql.Date) Acceuil_User.userSelected.getDateInscription();
        DateDebut.setValue(dateDebut.toLocalDate());

        java.sql.Date dateFin = (java.sql.Date) Acceuil_User.userSelected.getDateFin();
        if (dateFin!=null)
        DateFin.setValue(dateFin.toLocalDate());
        else
            DateFin.setValue(null);

        if (Acceuil_User.userSelected.getTypeUser().equals("Phero")) {
            outputComboPharmacie.setDisable(false);
            outputComboPharmacie.setValue(Acceuil_User.userSelected.getPharmacie());
            outputAdmin.setSelected(Acceuil_User.userSelected.isAdmin());
        }
    }

}
