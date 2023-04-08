package com.application.start.GereMenu.GestionUser.GereAction.Remove;

import com.application.start.GereMenu.GestionUser.Acceuil_User;
import com.application.start.Gere_BaseDonne.prepare;
import com.jfoenix.controls.JFXToggleButton;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;

import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ResourceBundle;

public class Remove implements Initializable {

    @FXML
    private DatePicker DateDebut;

    @FXML
    private ComboBox<String> OutputTypeUser;

    @FXML
    private TextField Password;

    @FXML
    private TextField Tel;

    @FXML
    private TextField UserName;

    @FXML
    private JFXToggleButton outputAdmin;

    @FXML
    private TextField outputCIN;

    @FXML
    private ComboBox<String> outputComboPharmacie;

    @FXML
    private TextField outputNom;

    @FXML
    private TextField outputPrenom;

    @FXML
    void removeUser(MouseEvent event) throws SQLException {

        int idSelected = Acceuil_User.userSelected.getId() ;

        deleteUser(idSelected,event) ;




    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        getData();

    }

    private void getData() {
        outputNom.setText(Acceuil_User.userSelected.getNom());
        outputPrenom.setText(Acceuil_User.userSelected.getPrenom());
        Tel.setText(Acceuil_User.userSelected.getTel());
        outputCIN.setText(Acceuil_User.userSelected.getCIN());
        UserName.setText(Acceuil_User.userSelected.getUserName());
        Password.setText(Acceuil_User.userSelected.getPassword());
        OutputTypeUser.setValue(Acceuil_User.userSelected.getTypeUser());
        java.sql.Date date = (java.sql.Date) Acceuil_User.userSelected.getDateInscription();
        DateDebut.setValue(date.toLocalDate());

        if (Acceuil_User.userSelected.getTypeUser().equals("Phero")) {
            outputComboPharmacie.setValue(Acceuil_User.userSelected.getPharmacie());
            outputAdmin.setSelected(Acceuil_User.userSelected.isAdmin());
        }
    }

    private void deleteUser(int id,MouseEvent event) throws SQLException {

        Connection etat = prepare.getConnection() ;
        DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        LocalDateTime timeNow = LocalDateTime.now();
        String dateFin = dtf.format(timeNow) ;

        String sql = "UPDATE  users SET etat =false,dateFin=? WHERE idUser =?" ;
        PreparedStatement ps = etat.prepareStatement(sql) ;
        ps.setString(1,dateFin);
        ps.setInt(2,id);
        int op=  ps.executeUpdate() ;

        if (op>0)
        {
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Information");
            alert.setHeaderText("Remove Succes");
            alert.setContentText("");
            alert.showAndWait() ;
            Stage stage = (Stage) ((Button)event.getSource()).getScene().getWindow();
            stage.close();
        }
        else
        {
            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.setTitle("Warnning");
            alert.setHeaderText("Error in remove");
            alert.setContentText("");
            alert.showAndWait() ;
        }

    }

}
