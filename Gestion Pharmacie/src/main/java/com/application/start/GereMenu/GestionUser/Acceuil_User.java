package com.application.start.GereMenu.GestionUser;

import com.application.start.Gere_BaseDonne.Compte.check.Gere_Compte.C_ModifiyUser;
import com.application.start.Gere_BaseDonne.Compte.select.Gere_Compte.S_AcceuilUser;
import com.application.start.Gere_BaseDonne.Compte.select.Gere_Compte.S_ModifiyUser;
import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXComboBox;
import com.jfoenix.controls.JFXTextField;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import javafx.stage.Modality;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.ResourceBundle;

public class Acceuil_User implements Initializable {

    @FXML
    private RadioButton Actif;

    @FXML
    private TableColumn<ListeUser, String> Nom;

    @FXML
    private  TableColumn<ListeUser, String> Prenom;

    @FXML
    private TableView<ListeUser> TableUser;

    @FXML
    private  TableColumn<ListeUser, String> Tel;

    @FXML
    private JFXTextField inputPrenom;

    @FXML
    private JFXComboBox<String> typeUser;

    @FXML
    private JFXButton add;

    @FXML
    private JFXButton details;

     @FXML
    private JFXButton modifiy;

    @FXML
    private JFXButton remove;


    @FXML
    void chercher(MouseEvent event) throws SQLException {
       ArrayList<ListeUser> result =  traitment003() ;
        System.out.println("Size Table Normal : " +result.size());
        setDataFor_Table(result) ;

    }

    @FXML
    void gereAction(ActionEvent event) throws IOException, SQLException {

        if (event.getSource() == add)
            OpenNewPage(event,"/com/application/start/Gere_Projet/User/addUser.fxml","Add User") ;
        else
            if (event.getSource() == modifiy)
            {
                if (prepare_Modfiy_Remove_Detile())
                {
                    System.out.println(userSelected.toString());
                    System.out.println("------------");
                    OpenNewPage(event,"/com/application/start/Gere_Projet/User/modifiy.fxml","Modifiy User") ;
                }
                else
                    notification_Doit_chosir_Une_Row() ;
            }
            else
            if (event.getSource() == remove)
            {
                if (prepare_Modfiy_Remove_Detile())
                {
                    System.out.println(userSelected.toString());
                    System.out.println("------------");
                    OpenNewPage(event,"/com/application/start/Gere_Projet/User/remove.fxml","Remove User") ;
                }
                else
                    notification_Doit_chosir_Une_Row() ;
            }
            else
            if (event.getSource() == details)
            {
                if (prepare_Modfiy_Remove_Detile())
                {
                    System.out.println(userSelected.toString());
                    System.out.println("------------");
                    OpenNewPage(event,"/com/application/start/Gere_Projet/User/Suivi.fxml","Detail User") ;
                }
                else
                    notification_Doit_chosir_Une_Row() ;
            }

        }





    public static UserSelected userSelected = new UserSelected() ;

    private boolean prepare_Modfiy_Remove_Detile() throws SQLException {

        int index = TableUser.getSelectionModel().getSelectedIndex();
        System.out.println("****Row selected is : "+index);

        if (index!=-1)
        {

             ;
            ListeUser rowSelected = TableUser.getSelectionModel().getSelectedItem();
            String tel = rowSelected.getTel() ;


            int idSelected = S_ModifiyUser.getID_OF_User(tel) ;
            System.out.println("On va cercher id : "+idSelected + " par Tel : "+tel);

            UserSelected  data;


            if (C_ModifiyUser.checkPhermo(idSelected))
            {
                data =  S_ModifiyUser.getData_PhermoSelected(idSelected) ;

                userSelected.setId(idSelected);
                userSelected.setNom(data.getNom());
                userSelected.setPrenom(data.getPrenom());
                userSelected.setTel(data.getTel());
                userSelected.setCIN(data.getCIN());
                userSelected.setUserName(data.getUserName());
                userSelected.setPassword(data.getPassword());
                userSelected.setTypeUser(data.getTypeUser());
                userSelected.setPharmacie(data.getPharmacie());
                userSelected.setAdmin(data.isAdmin());
                userSelected.setEtatOF_User(data.isEtatOF_User());
                userSelected.setDateInscription(data.getDateInscription());
                userSelected.setDateFin(data.getDateFin());
                return  true ;

            }

            else
            {
                if (C_ModifiyUser.checkLivreur(idSelected))
                {
                    data =  S_ModifiyUser.getData_LivreurSelected(idSelected) ;
                    userSelected.setId(idSelected);
                    userSelected.setNom(data.getNom());
                    userSelected.setPrenom(data.getPrenom());
                    userSelected.setTel(data.getTel());
                    userSelected.setCIN(data.getCIN());
                    userSelected.setUserName(data.getUserName());
                    userSelected.setPassword(data.getPassword());
                    userSelected.setTypeUser(data.getTypeUser());
                    userSelected.setPharmacie(data.getPharmacie());
                    userSelected.setAdmin(data.isAdmin());
                    userSelected.setEtatOF_User(data.isEtatOF_User());
                    userSelected.setDateInscription(data.getDateInscription());
                    userSelected.setDateFin(data.getDateFin());
                    return  true ;
                }
            }



        }

        return false ;

    }

    private void notification_Doit_chosir_Une_Row() {

        Alert alert = new Alert(Alert.AlertType.WARNING);
        alert.setTitle("WARNNING");
        alert.setHeaderText("Selecet row ");
        alert.setContentText("");
        alert.showAndWait() ;
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
       try {
            prepareTable() ;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        typeUser.getItems().addAll("ALL","Phermo","Livreur") ;
        typeUser.setValue("ALL");
    }

    private void prepareTable() throws SQLException {
        ArrayList<ListeUser> table = S_AcceuilUser.prepareTableUser();
        System.out.println("Size Table Normal : " +table.size());
        setDataFor_Table(table) ;
    }

    private void setDataFor_Table(ArrayList<ListeUser> table){
        if (table !=null)
    {


        Prenom.setCellValueFactory(new PropertyValueFactory<ListeUser,String>("prenom"));
        Nom.setCellValueFactory(new PropertyValueFactory<ListeUser,String>("nom"));
        Tel.setCellValueFactory(new PropertyValueFactory<ListeUser,String>("tel"));
        ObservableList<ListeUser> list = FXCollections.observableArrayList(table);
        TableUser.getItems().setAll(list) ;

    }
    }

    private ArrayList<ListeUser> traitment003() throws SQLException {
        String type_User = typeUser.getValue() ;
        String prenom = inputPrenom.getText() ;
        boolean radioActif = Actif.isSelected() ;

        if (type_User.equals("ALL"))
        {
            if (prenom.isEmpty() && radioActif)
            {
                System.out.println("All - vide - Active");
                return S_AcceuilUser.prepareTableUser() ;
            }
            else
                if (prenom.isEmpty() && !radioActif)
                {
                    System.out.println("ALL - vide - NonActive");
                    return S_AcceuilUser.prepareTableUserSelected_ALL_NonActive() ;
                }
               else
                   if (!prenom.isEmpty() && radioActif)
                   {
                       System.out.println("ALL - nonVide - Active");
                       return S_AcceuilUser.prepareTableUserSelectedBy_ALL_Prenom_Active(prenom) ;
                   }
                   else
                       if (!prenom.isEmpty() && !radioActif)
                       {
                           System.out.println("ALL - nonVide - NonActive");
                           return S_AcceuilUser.prepareTableUserSelectedBy_ALL_Prenom_NonActive(prenom) ;
                       }
        }

        else
        {
            if (type_User.equals("Phermo"))
            {
                if (prenom.isEmpty() && radioActif)
                {
                    System.out.println(type_User+" vide - active");
                    return S_AcceuilUser.prepareTableUserSelectedBy_Phermo_Active() ;
                }
                else
                if (prenom.isEmpty() && !radioActif)
                {
                    System.out.println(type_User+" vide - Nonactive");
                    return S_AcceuilUser.prepareTableUserSelectedBy_Phermo_NonActive() ;
                }
                else
                if (!prenom.isEmpty() && radioActif)
                {
                    System.out.println(type_User+" Nonvide - active");
                    return S_AcceuilUser.prepareTableUserSelectedBy_Phermo_Prenom_Active(prenom) ;
                }
                else
                {
                    System.out.println(type_User+" Nonvide - Nonactive");
                    return S_AcceuilUser.prepareTableUserSelectedBy_Phermo_Prenom_NonActive(prenom) ;
                }
            }

            else
            {
                if (type_User.equals("Livreur"))
                {
                    if (prenom.isEmpty() && radioActif)
                    {
                        System.out.println(type_User+" vide - active");
                        return S_AcceuilUser.prepareTableUserSelectedBy_Livreur_Active() ;
                    }
                    else
                    if (prenom.isEmpty() && !radioActif)
                    {
                        System.out.println(type_User+" vide - Nonactive");
                        return S_AcceuilUser.prepareTableUserSelectedBy_Livreur_NonActive() ;
                    }
                    else
                    if (!prenom.isEmpty() && radioActif)
                    {
                        System.out.println(type_User+" Nonvide - active");
                        return S_AcceuilUser.prepareTableUserSelectedBy_Livreur_Prenom_Active(prenom) ;
                    }
                    else
                    {
                        System.out.println(type_User+" Nonvide - Nonactive");
                        return S_AcceuilUser.prepareTableUserSelectedBy_Livreur_Prenom_NonActive(prenom) ;
                    }
                }

            }

        }






        return null ;

    }

    private void OpenNewPage(ActionEvent event,String path,String tilte) throws IOException {

        Stage stage = new Stage() ;
        stage.initModality(Modality.APPLICATION_MODAL);
        URL fxmlfile = getClass().getResource(path) ;
        Parent root = FXMLLoader.load(fxmlfile);
        stage.setTitle(tilte);
        stage.setScene(new Scene(root));
        stage.show();
    }




}
