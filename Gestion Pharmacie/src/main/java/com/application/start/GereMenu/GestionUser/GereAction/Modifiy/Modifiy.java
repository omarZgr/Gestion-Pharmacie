package com.application.start.GereMenu.GestionUser.GereAction.Modifiy;

import com.application.start.GereMenu.GestionUser.Acceuil_User;
import com.application.start.Gere_BaseDonne.Compte.check.Gere_Compte.C_ModifiyUser;
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
import java.util.Date;
import java.util.ResourceBundle;

public class Modifiy implements Initializable {

    @FXML
    private ComboBox<String> OutputTypeUser;

    @FXML
    private TextField Password;

    @FXML
    private TextField Tel;

    @FXML
    private TextField UserName;

    @FXML
    private Label notification;

    @FXML
    private JFXToggleButton outputAdmin;

    @FXML
    private JFXToggleButton outputEtat;

    @FXML
    private TextField outputCIN;

    @FXML
    private ComboBox<String> outputComboPharmacie;

    @FXML
    private TextField outputNom;

    @FXML
    private TextField outputPrenom;


    @FXML
    void modifiyUser(MouseEvent event) throws SQLException {
        trit01(event) ;
    }

    private void updateDonneLivreur_Add(int idUser, String tel, String password,Boolean etatT , MouseEvent event) throws SQLException {

        Connection etat = prepare.getConnection();


        String updatePersone = "UPDATE persons SET Tel =?  WHERE idPersonne  =?";
        String updateUser = "UPDATE  users SET passwordd=?,etat=?,dateFin=? WHERE idUser  =?";

        PreparedStatement psPersone = etat.prepareStatement(updatePersone);
        PreparedStatement psUser = etat.prepareStatement(updateUser);

        System.out.println("!!!!!!!!!!!!!!!  [updateDonneLivreur_Add]");
        System.out.println("idUser : "+idUser);
        System.out.println("etat : "+etatT);
        System.out.println("!!!!!!!!!!!!!!!");


        psPersone.setString(1, tel);
        psPersone.setInt(2, idUser);

        psUser.setString(1, password);
        psUser.setBoolean(2, etatT);
        psUser.setDate(3, null);
        psUser.setInt(4, idUser);

        int opPersone = psPersone.executeUpdate();
        int opUser = psUser.executeUpdate();

        if (opPersone > 0 && opUser > 0) {
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Information");
            alert.setHeaderText("Update Succes");
            alert.setContentText("");
            alert.showAndWait();
            Stage stage = (Stage) ((Button) event.getSource()).getScene().getWindow();
            stage.close();
        } else {
            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.setTitle("Warnning");
            alert.setHeaderText("Error in update tele");
            alert.setContentText("");
            alert.showAndWait();
        }

    }

    private void updateDonneLivreur_Remove(int idUser, String tel, String password,Boolean etatT ,MouseEvent event) throws SQLException {

        Connection etat = prepare.getConnection();

        DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        LocalDateTime timeNow = LocalDateTime.now();
        String dateFin = dtf.format(timeNow) ;

        System.out.println("!!!!!!!!!!!!!!!  [updateDonneLivreur_Remove]");
        System.out.println("idUser : "+idUser);
        System.out.println("etat : "+etatT);
        System.out.println("!!!!!!!!!!!!!!!");



        String updatePersone = "UPDATE persons SET Tel =?  WHERE idPersonne  =?";
        String updateUser = "UPDATE  users SET passwordd=?,etat=?,dateFin=?  WHERE idUser  =?";

        PreparedStatement psPersone = etat.prepareStatement(updatePersone);
        PreparedStatement psUser = etat.prepareStatement(updateUser);

        psPersone.setString(1, tel);
        psPersone.setInt(2, idUser);

        psUser.setString(1, password);
        psUser.setBoolean(2, etatT);
        psUser.setString(3, dateFin);
        psUser.setInt(4, idUser);


        int opPersone = psPersone.executeUpdate();
        int opUser = psUser.executeUpdate();


        if (opPersone > 0 && opUser > 0) {
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Information");
            alert.setHeaderText("Update Succes");
            alert.setContentText("");
            alert.showAndWait();
            Stage stage = (Stage) ((Button) event.getSource()).getScene().getWindow();
            stage.close();
        } else {
            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.setTitle("Warnning");
            alert.setHeaderText("Error in update");
            alert.setContentText("");
            alert.showAndWait();
        }

    }

    private void updateDonnePhermo_Remove(int idUser, String tel, String password, Boolean adminisation,Boolean etatT ,MouseEvent event) throws SQLException {

        Connection etat = prepare.getConnection();

        DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        LocalDateTime timeNow = LocalDateTime.now();
        String dateFin = dtf.format(timeNow) ;

        System.out.println("!!!!!!!!!!!!!!!  [updateDonnePhermo_Remove]");
        System.out.println("idUser : "+idUser);
        System.out.println("etat : "+etatT);
        System.out.println("!!!!!!!!!!!!!!!");




        String updatePersone = "UPDATE persons SET Tel =?  WHERE idPersonne  =?";
        String updateUser = "UPDATE  users SET passwordd=?,etat=?,dateFin=?  WHERE idUser  =?";
        String updatePhero = "UPDATE  phero SET responsabilte =? WHERE idPhero   =?";

        PreparedStatement psPersone = etat.prepareStatement(updatePersone);
        PreparedStatement psUser = etat.prepareStatement(updateUser);
        PreparedStatement psPhero = etat.prepareStatement(updatePhero);

        psPersone.setString(1, tel);
        psPersone.setInt(2, idUser);

        psUser.setString(1, password);
        psUser.setBoolean(2, etatT);
        psUser.setString(3, dateFin);
        psUser.setInt(4, idUser);

        psPhero.setBoolean(1, adminisation);
        psPhero.setInt(2, idUser);


        int opPersone = psPersone.executeUpdate();
        int opUser = psUser.executeUpdate();
        int opPhero = psPhero.executeUpdate();


        if (opPersone > 0 && opUser > 0 && opPhero > 0) {
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Information");
            alert.setHeaderText("Update Succes");
            alert.setContentText("");
            alert.showAndWait();
            Stage stage = (Stage) ((Button) event.getSource()).getScene().getWindow();
            stage.close();
        } else {
            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.setTitle("Warnning");
            alert.setHeaderText("Error in update");
            alert.setContentText("");
            alert.showAndWait();
        }

    }

    private void updateDonnePhermo_Add(int idUser, String tel, String password, Boolean adminisation,Boolean etatT,MouseEvent event) throws SQLException {

        Connection etat = prepare.getConnection();


        String updatePersone = "UPDATE persons SET Tel =?  WHERE idPersonne  =?";
        String updateUser = "UPDATE  users SET passwordd=?,etat=?,dateFin=? WHERE idUser  =?";
        String updatePhero = "UPDATE  phero SET responsabilte =? WHERE idPhero   =?";

        PreparedStatement psPersone = etat.prepareStatement(updatePersone);
        PreparedStatement psUser = etat.prepareStatement(updateUser);
        PreparedStatement psPhero = etat.prepareStatement(updatePhero);


        System.out.println("!!!!!!!!!!!!!!!  [updateDonnePhermo_Add]");
        System.out.println("idUser : "+idUser);
        System.out.println("etat : "+etatT);
        System.out.println("!!!!!!!!!!!!!!!");


        psPersone.setString(1, tel);
        psPersone.setInt(2, idUser);

        psUser.setString(1, password);
        psUser.setBoolean(2, etatT);
        psUser.setDate(3, null);
        psUser.setInt(4, idUser);

        psPhero.setBoolean(1, adminisation);
        psPhero.setInt(2, idUser);


        int opPersone = psPersone.executeUpdate();
        int opUser = psUser.executeUpdate();
        int opPhero = psPhero.executeUpdate();


        if (opPersone > 0 && opUser > 0 && opPhero > 0) {
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Information");
            alert.setHeaderText("Update Succes");
            alert.setContentText("");
            alert.showAndWait();
            Stage stage = (Stage) ((Button) event.getSource()).getScene().getWindow();
            stage.close();
        } else {
            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.setTitle("Warnning");
            alert.setHeaderText("Error in update");
            alert.setContentText("");
            alert.showAndWait();
        }

    }

    @FXML
    void reset(MouseEvent event) {

        notification.setVisible(false);
        Tel.setText(Acceuil_User.userSelected.getTel());
        UserName.setText(Acceuil_User.userSelected.getUserName());
        Password.setText(Acceuil_User.userSelected.getPassword());
        outputAdmin.setSelected(Acceuil_User.userSelected.isAdmin());


    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        getData();
        checkInput();
    }

    private void getData() {
        outputNom.setText(Acceuil_User.userSelected.getNom());
        outputPrenom.setText(Acceuil_User.userSelected.getPrenom());
        Tel.setText(Acceuil_User.userSelected.getTel());
        outputCIN.setText(Acceuil_User.userSelected.getCIN());
        UserName.setText(Acceuil_User.userSelected.getUserName());
        Password.setText(Acceuil_User.userSelected.getPassword());
        OutputTypeUser.setValue(Acceuil_User.userSelected.getTypeUser());
        outputEtat.setSelected(Acceuil_User.userSelected.isEtatOF_User());

        if (Acceuil_User.userSelected.getTypeUser().equals("Phero")) {
            outputAdmin.setDisable(false);
            outputComboPharmacie.setDisable(false);
            outputComboPharmacie.setValue(Acceuil_User.userSelected.getPharmacie());
            outputAdmin.setSelected(Acceuil_User.userSelected.isAdmin());
        }
    }

    private void checkInput() {

        Tel.textProperty().addListener((observable, oldValue, newValue) -> {
            if (!newValue.matches("\\d*")) {
                Tel.setText(newValue.replaceAll("[^\\d]", ""));
            }
        });

    }


    private void trit01(MouseEvent event) throws SQLException {
        String tel = Tel.getText();
        String password = Password.getText();
        String typeUser = OutputTypeUser.getValue();
        Boolean adminisation = outputAdmin.isSelected();
        Boolean etat = outputEtat.isSelected() ;
        Date initilaisteDateFin ;


        String oldTel = Acceuil_User.userSelected.getTel();
        String oldPass = Acceuil_User.userSelected.getPassword();
        Boolean oldAdminsation = Acceuil_User.userSelected.isAdmin();

        int idUser = Acceuil_User.userSelected.getId();
        System.out.println("ID of user modifiyed is : "+idUser);
        System.out.println("###################");



        if (tel.isEmpty() || password.isEmpty()) {
            notification.setText("Champs Empty");
            notification.setVisible(true);
            return;
        } else {
            if (C_ModifiyUser.checkTel_without_Parametre(idUser, tel)) {
                notification.setText("Tel Already Exist");
                notification.setVisible(true);
                return;
            } else {
                if (typeUser.equals("Phero"))
                {
                    if (etat)
                    {
                        initilaisteDateFin = null ;
                        updateDonnePhermo_Add(idUser, tel, password, adminisation,etat,event);
                    }
                    else
                        updateDonnePhermo_Remove(idUser, tel, password, adminisation,etat, event);

                }
                else
                    if (typeUser.equals("Livreur"))
                    {
                        if (etat)
                        {
                            System.out.println("etat  :" +etat);
                            updateDonneLivreur_Add(idUser, tel, password, etat, event);
                        }
                        else
                        {
                            System.out.println("etat  :" +etat);
                            updateDonneLivreur_Remove(idUser, tel, password,etat, event);
                        }

                    }
            }
        }

        Stage stage = (Stage) ((Button) event.getSource()).getScene().getWindow();
        stage.close();

    }









}