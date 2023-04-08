package com.application.start.GereMenu.GestionFournisseur;

import com.application.start.Gere_BaseDonne.Fournisseur.S_AcceuilFournisseur;
import com.application.start.Gere_BaseDonne.prepare;
import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXTextField;
import com.jfoenix.controls.JFXToggleButton;

import javafx.animation.PauseTransition;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.KeyCode;
import javafx.scene.input.MouseEvent;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.util.Duration;


import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.ResourceBundle;

public class Acceuil_Fournisseur implements Initializable {



    @FXML
    private JFXToggleButton ViderTable;

    @FXML
    private JFXButton Next;

    @FXML
    private JFXButton Details;


    @FXML
    private Label notification;

    @FXML
    private JFXToggleButton ByCode;

    @FXML
    private JFXToggleButton ByNom;
    @FXML
    private TableColumn<ListeProduit, String> Code;

    @FXML
    private TableColumn<ListeProduit, String> Dosage;

    @FXML
    private TableColumn<ListeProduit, String> Facture;

    @FXML
    private TableColumn<ListeProduit, String> Nom;

    @FXML
    private TableColumn<ListeProduit, String> Quantite;

    @FXML
    private TableColumn<ListeProduit, CheckBox> Select;

    @FXML
    private TableView<ListeProduit> TableStock;

    @FXML
    private JFXButton details;

    @FXML
    private JFXTextField inputProduit;




    @FXML
    void gereAction(ActionEvent event) throws SQLException, IOException {

       if (event.getSource() ==  Details)
       {
           if (prepare_Details())// modifier path
               OpenNewPage(event,"/com/application/start/Gere_Projet/Fournisseur/GereAction/Details.fxml","Detail Product") ;
           else
               notification_Doit_chosir_Une_Row("Selecet row ") ;

       }

       else
       {
           if (event.getSource()==ViderTable)
           {
               if (ViderTable.isSelected())
                   traite_ViderTable() ;
           }

           else
           {
               if (event.getSource() == Next)
               {
                   trait_Next(event) ;
               }
           }
       }




    }

    private void trait_Next(ActionEvent event) throws SQLException, IOException {

        int sizePanier = S_AcceuilFournisseur.getSize_Panier() ;

        if (sizePanier!=0)
            OpenNewPage(event,"/com/application/start/Gere_Projet/Fournisseur/GereAction/NextPage.fxml","Panier");
        else
           if (sizePanier==0)
               notification_Doit_chosir_Une_Row("Panier Vide") ;
           else
               notification_Doit_chosir_Une_Row("Error in Panier") ;



    }

    private void traite_ViderTable() throws SQLException {
        S_AcceuilFournisseur.inisialise_Produit_Select_BD();
        gere_Notification("Vider Table") ;
        PauseTransition pause = new PauseTransition(Duration.seconds(1));
        pause.setOnFinished(e -> ViderTable.setSelected(false));
        pause.play();

    }

    public static PrductSelected prductSelected = new PrductSelected() ;

    private boolean prepare_Details() throws SQLException {

        int index = TableStock.getSelectionModel().getSelectedIndex();


        if (index!=-1)
        {
            ListeProduit rowSelected = TableStock.getSelectionModel().getSelectedItem() ;
            String code = rowSelected.getCode() ;

            int idSelected = S_AcceuilFournisseur.getID_Product_By_Code(code) ;

            if (idSelected!=-1)
            {
                PrductSelected data  = S_AcceuilFournisseur.getData_Product_Selcted(idSelected) ;

                prductSelected.setId(data.getId());
                prductSelected.setCode(data.getCode());
                prductSelected.setNom(data.getNom());
                prductSelected.setDCI1(data.getDCI1());
                prductSelected.setDosage(data.getDosage());
                prductSelected.setUNITE_DOSAGE1(data.getUNITE_DOSAGE1());
                prductSelected.setFORME(data.getFORME());
                prductSelected.setPRESENTATION(data.getPRESENTATION());
                prductSelected.setPPV(data.getPPV());
                prductSelected.setPH(data.getPH());
                prductSelected.setPRIX_BR(data.getPRIX_BR());
                prductSelected.setTAUX_REMBOURSEMENT(data.getTAUX_REMBOURSEMENT());

                return true ;


            }

        }

        return false ;

    }


    @FXML
    void ClickedINTable(MouseEvent event) {

        Node sourceNode =(Node) event.getSource() ;

        if ("#checkBox".equals(sourceNode.getId()))
        {
            System.out.println("brkti ealia");
        }

    }



    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        try {
            prepareTable() ;

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        inputProduit.setOnKeyPressed(event -> {
            try {
                chercher() ;
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }

        });

        int index = TableStock.getSelectionModel().getSelectedIndex() ;

        System.out.println("Cell selected : "+TableStock.getSelectionModel().getSelectedCells().get(index));


        gere_Selected() ;




    }

    ArrayList<ListeProduit> dataListe ;
    private void prepareTable() throws SQLException {

        dataListe = S_AcceuilFournisseur.prepareTablePrduits() ;
        linkTo_Table(dataListe);

    }

    private void linkTo_Table(ArrayList<ListeProduit> dataListe)
    {
        if (dataListe != null)
        {
            Select.setCellValueFactory(new PropertyValueFactory<ListeProduit,CheckBox>("remark"));
            Code.setCellValueFactory(new PropertyValueFactory<ListeProduit,String>("code"));
            Nom.setCellValueFactory(new PropertyValueFactory<ListeProduit,String>("nom"));
            Dosage.setCellValueFactory(new PropertyValueFactory<ListeProduit,String>("dosage"));
            Facture.setCellValueFactory(new PropertyValueFactory<ListeProduit,String>("Facture"));
            Quantite.setCellValueFactory(new PropertyValueFactory<ListeProduit,String>("quantite"));
            ObservableList<ListeProduit> list = FXCollections.observableArrayList();
            TableStock.getItems().setAll(dataListe) ;

        }

    }

    private void chercher() throws SQLException {

        String produitChercher = inputProduit.getText() ;
        String produitChercher_ForBD = "%"+produitChercher+"%" ;

        if (produitChercher.isEmpty())
            prepareTable() ;
        else
        if (ByCode.isSelected())
            Cherchech_BY_Code(produitChercher_ForBD) ;
        else
            Cherchech_BY_Nom(produitChercher_ForBD) ;

    }

    private void Cherchech_BY_Nom(String produitChercher) throws SQLException {

        ArrayList<ListeProduit> data = S_AcceuilFournisseur.prepareTablePrduitsSelectedBy_Nom(produitChercher) ;
        linkTo_Table(data) ;
    }

    private void Cherchech_BY_Code(String produitChercher) throws SQLException {
        ArrayList<ListeProduit> data = S_AcceuilFournisseur.prepareTablePrduitsSelectedBy_Code(produitChercher) ;
        linkTo_Table(data) ;
    }

    private void gere_Selected() {
        TableStock.setOnMouseClicked(event -> {
            ListeProduit selectedLing = TableStock.getSelectionModel().getSelectedItem();
            boolean isSelected = selectedLing.getRemark().isSelected();
            if (event.getClickCount() == 2) {
                if (isSelected) {
                    selectedLing.getRemark().setSelected(false);
                    selectedLing.setSelected(false);
                    try {
                        Remove_Selected_to_BD(selectedLing.getCode()) ;
                    } catch (SQLException e) {
                        throw new RuntimeException(e);
                    }

                } else {
                    selectedLing.getRemark().setSelected(true);
                    selectedLing.setSelected(true);
                    try {
                        Set_Selected_to_BD(selectedLing.getCode()) ;
                    } catch (SQLException e) {
                        throw new RuntimeException(e);
                    }

                }
            }

        });

        TableStock.setOnKeyPressed(event -> {
            ListeProduit selectedLing = TableStock.getSelectionModel().getSelectedItem();
            boolean isSelected = selectedLing.getRemark().isSelected();
            if (event.getCode() == KeyCode.SPACE || event.getCode() == KeyCode.ENTER) {
                // Perform any other necessary actions...
                if (isSelected) {
                    selectedLing.getRemark().setSelected(false);
                    selectedLing.setSelected(false);
                    try {
                        Remove_Selected_to_BD(selectedLing.getCode()) ;
                    } catch (SQLException e) {
                        throw new RuntimeException(e);
                    }


                } else {
                    selectedLing.getRemark().setSelected(true);
                    selectedLing.setSelected(true);
                    try {
                        Set_Selected_to_BD(selectedLing.getCode()) ;
                    } catch (SQLException e) {
                        throw new RuntimeException(e);
                    }

                }

            }

        });



    }



    private void Remove_Selected_to_BD(String code) throws SQLException {

        Connection etat = prepare.getConnection();


        String update = "UPDATE liste_produits SET Selected =false WHERE CodeProduits  =?";
        PreparedStatement psPersone = etat.prepareStatement(update);
        psPersone.setString(1, code);
        int test = psPersone.executeUpdate();// Check selected

        if (test>0)
        {
            gere_Notification("InSelected") ;
        }
        else {
            gere_Notification("Error in InSelected");

        }
    }

    private void Set_Selected_to_BD(String code) throws SQLException {
        Connection etat = prepare.getConnection();


        String update = "UPDATE liste_produits SET Selected =true WHERE CodeProduits  =?";
        PreparedStatement psPersone = etat.prepareStatement(update);
        psPersone.setString(1, code);
        int test = psPersone.executeUpdate();// Check selected

        if (test>0)
        {
            gere_Notification("Selected") ;

        }
        else {
            gere_Notification("Error in Selected");

        }




        }

    private void gere_Notification(String txt)
    {
        notification.setText(txt);
        notification.setVisible(true);
        PauseTransition pause = new PauseTransition(Duration.seconds(1));
        pause.setOnFinished(e -> notification.setVisible(false));
        pause.play();

    }


    private void notification_Doit_chosir_Une_Row(String txt) {

        Alert alert = new Alert(Alert.AlertType.WARNING);
        alert.setTitle("WARNNING");
        alert.setHeaderText(txt);
        alert.setContentText("");
        alert.showAndWait() ;
    }

    private void OpenNewPage(ActionEvent event,String path,String tilte) throws IOException {

        Stage stage = new Stage() ;
        stage.initModality(Modality.APPLICATION_MODAL);

        URL fxmlfile = getClass().getResource(path) ;
        Parent root = FXMLLoader.load(fxmlfile);
        stage.setTitle(tilte);
        stage.setScene(new Scene(root));
        stage.setResizable(false);
        stage.show();
    }






}
