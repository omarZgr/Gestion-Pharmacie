package com.application.start.GereMenu.GestionUser.GereAction.Add;

import com.application.start.GereMenu.GestionUser.GereAction.listePharmacie;
import com.application.start.Gere_BaseDonne.Compte.check.Gere_Compte.C_AddUser;
import com.application.start.Gere_BaseDonne.Compte.select.Gere_Compte.S_AddUser;
import com.application.start.Gere_BaseDonne.prepare;
import com.jfoenix.controls.JFXCheckBox;
import com.jfoenix.controls.JFXToggleButton;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.input.MouseEvent;
import javafx.stage.FileChooser;
import javafx.stage.Stage;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;
import java.net.URL;
import java.sql.*;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.ResourceBundle;

public class Add implements Initializable   {

    @FXML
    private ComboBox<String> comboPharmacie;

    @FXML
    private TextField inputCIN;

    @FXML
    private TextField inputNom;

    @FXML
    private TextField inputPassword;

    @FXML
    private TextField inputPrenom;

    @FXML
    private TextField inputTel;

    @FXML
    private TextField inputUserName;

    @FXML
    private Label notification;

    @FXML
    private JFXToggleButton admin;

    @FXML
    private ComboBox<String> typeUser;

    @FXML
    private JFXCheckBox validationImg;

    @FXML
    void addUser(MouseEvent event) throws FileNotFoundException, SQLException {

        String nom = inputNom.getText() ;
        String prenom = inputPrenom.getText() ;
        String tel = inputTel.getText() ;
        String CIN = inputCIN.getText() ;
        String userName = inputUserName.getText() ;
        String password = inputPassword.getText() ;
        String typeUserSelected = typeUser.getValue() ;
        String pharamcieSelected = comboPharmacie.getValue() ;
        Boolean admination = admin.isSelected() ;
        Boolean phootoValide = validationImg.isSelected() ;

       //|| typeUserSelected.isEmpty() || pharamcieSelected.isEmpty() || !phootoValide

        if (nom.isEmpty() || prenom.isEmpty() || tel.isEmpty() || CIN.isEmpty() || userName.isEmpty() || password.isEmpty() ||   typeUserSelected.isEmpty() || !phootoValide)
        {
            notification.setText("Champs Empty");
            notification.setVisible(true);
        }
        else
        {
                if (C_AddUser.checkCIN(CIN))
            {
                notification.setText("CIN Already Exist");
                notification.setVisible(true);
            }
            else
                if (C_AddUser.checkTel(tel))
                {
                    notification.setText("Tel Already Exist");
                    notification.setVisible(true);
                }
                else
                    if (C_AddUser.checkUserName(userName))
                    {
                        notification.setText("UserName Already Exist");
                        notification.setVisible(true);
                    }
                    else
                    {
                        if (typeUserSelected.equals("Phermo"))
                        {
                            if (pharamcieSelected.isEmpty() )
                            {
                                notification.setText("UserName Already Exist");
                                notification.setVisible(true);
                            }
                            else
                                enregistrerUser(nom,prenom,tel,CIN,userName,password,typeUserSelected,pharamcieSelected,admination,imag,event) ;
                        }
                        else
                            if (typeUserSelected.equals("Livreur"))
                            enregistrerUser(nom,prenom,tel,CIN,userName,password,typeUserSelected,imag,event) ;

                    }

        }


    }

    private void enregistrerUser(String nom, String prenom, String tel, String cin, String userName, String password, String typeUserSelected, String pharamcieSelected, Boolean admination, File imag,MouseEvent event) throws FileNotFoundException, SQLException {

        Connection etat = prepare.getConnection() ;

        DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        LocalDateTime timeNow = LocalDateTime.now();
        String dateInscription = dtf.format(timeNow) ;
        InputStream inputStream = new FileInputStream(imag);


        String insertPersone = "Insert into persons (Nom,Prenom,CIN,Tel,DateInscrire) values(?,?,?,?,?)" ;
        String insertUser = "Insert into users (idUser,userName,passwordd,etat,img) values(?,?,?,true,?)" ;
        String insertUser_Special = "Insert into phero (idPhero,idPharmacie,responsabilte) values(?,?,?)" ;

        PreparedStatement psPersone = etat.prepareStatement(insertPersone, Statement.RETURN_GENERATED_KEYS) ;
        PreparedStatement psUser = etat.prepareStatement(insertUser) ;
        PreparedStatement psUser_Special = etat.prepareStatement(insertUser_Special) ;

        psPersone.setString(1,nom);
        psPersone.setString(2,prenom);
        psPersone.setString(3,cin);
        psPersone.setString(4,tel);
        psPersone.setString(5,dateInscription);

        int peroneInsertedRows = psPersone.executeUpdate() ;
        int idUser ;

        if (peroneInsertedRows>0)
        {
            ResultSet personIDResult = psPersone.getGeneratedKeys();
            if (personIDResult.next())
            {

                 idUser = personIDResult.getInt(1) ;
                psUser.setInt(1,idUser);
                psUser.setString(2,userName);
                psUser.setString(3,password);
                psUser.setBlob(4, inputStream);

                int UserInsertedRows = psUser.executeUpdate() ;

                if (UserInsertedRows>0)
                {
                    String nomPrv = pharamcieSelected.split("-")[0];
                    int idPharmacieSelected = S_AddUser.getID_OF_Pharmacie(nomPrv);

                    psUser_Special.setInt(1,idUser);
                    psUser_Special.setInt(2,idPharmacieSelected);
                    psUser_Special.setBoolean(3,admination);

                    int UserSpecialInsertedRows = psUser_Special.executeUpdate() ;

                    returnInsert(UserSpecialInsertedRows,event);

                }

            }



        }




    }

    private void enregistrerUser(String nom, String prenom, String tel, String cin, String userName, String password, String typeUserSelected, File imag,MouseEvent event) throws FileNotFoundException, SQLException {

        Connection etat = prepare.getConnection() ;

        DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        LocalDateTime timeNow = LocalDateTime.now();
        String dateInscription = dtf.format(timeNow) ;
        InputStream inputStream = new FileInputStream(imag);


        String insertPersone = "Insert into persons (Nom,Prenom,CIN,Tel,DateInscrire) values(?,?,?,?,?)" ;
        String insertUser = "Insert into users (idUser,userName,passwordd,etat,img) values(?,?,?,true,?)" ;
        String insertUser_Special = "Insert into livreur (idLivreur ,padge) values(?,?)" ;

        PreparedStatement psPersone = etat.prepareStatement(insertPersone, Statement.RETURN_GENERATED_KEYS) ;
        PreparedStatement psUser = etat.prepareStatement(insertUser) ;
        PreparedStatement psUser_Special = etat.prepareStatement(insertUser_Special) ;

        psPersone.setString(1,nom);
        psPersone.setString(2,prenom);
        psPersone.setString(3,cin);
        psPersone.setString(4,tel);
        psPersone.setString(5,dateInscription);

        int peroneInsertedRows = psPersone.executeUpdate() ;
        int idUser ;

        if (peroneInsertedRows>0)
        {
            ResultSet personIDResult = psPersone.getGeneratedKeys();
            if (personIDResult.next())
            {

                idUser = personIDResult.getInt(1) ;
                psUser.setInt(1,idUser);
                psUser.setString(2,userName);
                psUser.setString(3,password);
                psUser.setBlob(4, inputStream);

                int UserInsertedRows = psUser.executeUpdate() ;

                if (UserInsertedRows>0)
                {
                   String padge = CreationPadge.generateRandomString() ;

                    psUser_Special.setInt(1,idUser);
                    psUser_Special.setString(2,padge);

                    int UserSpecialInsertedRows = psUser_Special.executeUpdate() ;
                    returnInsert(UserSpecialInsertedRows,event);
                }
            }
        }

    }

    private void returnInsert(int result,MouseEvent event) throws SQLException {
        if (result >0)
        {
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Succes");
            alert.setHeaderText("Add User Succes");
            alert.setContentText("");
            alert.showAndWait() ;
            Stage stage = (Stage) ((Button)event.getSource()).getScene().getWindow();
            stage.close();
        }
        else
        {
            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.setTitle("WARNNING");
            alert.setHeaderText("Probleme in Add User ");
            alert.setContentText("");
            alert.showAndWait() ;
        }
    }

    File imag = new File("") ;
    @FXML
    void chooseImg(MouseEvent event) {

        FileChooser fileChooser = new FileChooser();
        fileChooser.setTitle("Select Image");
        fileChooser.getExtensionFilters().addAll(
                new FileChooser.ExtensionFilter("Image Files", "*.png", "*.jpg", "*.jpeg")
        );

        imag = fileChooser.showOpenDialog(new Stage());
        if (imag != null) {
            validationImg.setSelected(true);
        }
        else  validationImg.setSelected(false);

    }

    @FXML
    void reset(MouseEvent event) {

        inputNom.setText("");
        inputPrenom.setText("");
        inputTel.setText("");
        inputCIN.setText("");
        inputUserName.setText("");
        inputPassword.setText("");
        typeUser.setValue("");
        admin.setSelected(false);
        comboPharmacie.setValue("");
        comboPharmacie.setDisable(true);
        admin.setDisable(true);


    }

    @FXML
    void checkTypeUser(MouseEvent event) {

        try {
            String typeUserSelected = typeUser.getValue() ;

            if (!typeUserSelected.isEmpty())
            {
                if (typeUserSelected.equals("Phermo"))
                {
                    comboPharmacie.setDisable(false);
                    admin.setDisable(false);
                }
                else
                {
                    admin.setSelected(false);
                    comboPharmacie.setValue("");
                    comboPharmacie.setDisable(true);
                    admin.setDisable(true);
                }
            }
        } catch (Exception e) {

        }

    }


    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

        typeUser.getItems().addAll("Phermo","Livreur") ;
        typeUser.setValue("");
        comboPharmacie.setValue("");

        try {
            prepareListePharmacie() ;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        checkInput() ;


    }

    private void checkInput()
    {
        inputNom.textProperty().addListener((observable, oldValue, newValue) -> {
            if (!newValue.matches("\\sa-zA-Z*")) {
                inputNom.setText(newValue.replaceAll("[^\\sa-zA-Z]", ""));
            }
        });

        inputPrenom.textProperty().addListener((observable, oldValue, newValue) -> {
            if (!newValue.matches("\\sa-zA-Z*")) {
                inputPrenom.setText(newValue.replaceAll("[^\\sa-zA-Z]", ""));
            }
        });

        inputTel.textProperty().addListener((observable, oldValue, newValue) -> {
            if (!newValue.matches("\\d*")) {
                inputTel.setText(newValue.replaceAll("[^\\d]", ""));
            }
        });

    }

    private void prepareListePharmacie() throws SQLException {

        ArrayList<listePharmacie> data = S_AddUser.prepareComboBoxPharmacie() ;
        ArrayList<String> result = new ArrayList<>();

        for (int i=0;i<data.size();i++)
        {
           result.add(data.get(i).toString()) ;
        }

        comboPharmacie.getItems().addAll(result) ;

    }
}
