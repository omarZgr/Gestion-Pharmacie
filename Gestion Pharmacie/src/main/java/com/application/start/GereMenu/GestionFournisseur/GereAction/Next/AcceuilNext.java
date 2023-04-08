package com.application.start.GereMenu.GestionFournisseur.GereAction.Next;

import com.application.start.GereMenu.GestionFournisseur.ListeProduit;
import com.application.start.Gere_BaseDonne.Fournisseur.Next.S_NextPage;
import com.application.start.Gere_BaseDonne.Fournisseur.S_AcceuilFournisseur;
import com.application.start.Gere_BaseDonne.prepare;
import com.jfoenix.controls.JFXButton;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.KeyCode;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;

import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

public class AcceuilNext implements Initializable {

    @FXML
    private JFXButton Back;

    @FXML
    private TextField inputQuantite;

    @FXML
    private TableColumn<ListePanier, String> Dosage;

    @FXML
    private TableColumn<ListePanier, String> Facture;

    @FXML
    private Label Montant_A_Paye;

    @FXML
    private TableColumn<ListePanier, String> Nom;

    @FXML
    private TableColumn<ListePanier, String> Quantite;

    @FXML
    private TableColumn<ListePanier, String> Prix;

    @FXML
    private TableView<ListePanier> TablePanier;

    @FXML
    private JFXButton Valider;

    @FXML
    private Label nbrPiece;

    @FXML
    private Label notification;

    @FXML
    void ClickedINTable(MouseEvent event) {

    }

    @FXML
    void gereAction(ActionEvent event) {

        if (event.getSource() == Back) {
            Stage stage = (Stage) ((Button) event.getSource()).getScene().getWindow();
            stage.close();
        } else {
            if (event.getSource() == Valider) {
                traite_Valider(event);
            }
        }

    }



    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        try {
            prepareTable();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        checkInput();

        traite_InputQuantite();

        Afficher_Facture();

    }

    private void checkInput() {
        inputQuantite.textProperty().addListener((observable, oldValue, newValue) -> {
            if (!newValue.matches("\\d*")) {
                inputQuantite.setText(newValue.replaceAll("[^\\d]", ""));
            }
        });

    }


    int idSelected ;
    private void traite_InputQuantite() {

        TablePanier.setOnMouseClicked(event -> {
            int index = TablePanier.getSelectionModel().getSelectedIndex();
            System.out.println("Ligne selcted is : " + index);
            if (index != -1) {
                if (event.getClickCount() == 2) {
                    ListePanier rowSelected = TablePanier.getSelectionModel().getSelectedItem();
                    int quantiteTable = rowSelected.getQuantite();

                    inputQuantite.setText(String.valueOf(quantiteTable));
                    inputQuantite.setOnKeyPressed(event1 ->
                    {
                        if (event1.getCode() == KeyCode.ENTER) {
                            int newQuantite = Integer.parseInt((inputQuantite.getText()));
                             idSelected = rowSelected.getId();

                            try {
                                update_Quantite(idSelected, newQuantite);
                                System.out.println("Normalment kulshi mzn");
                                Afficher_Facture();
                            } catch (SQLException e) {
                                throw new RuntimeException(e);
                            }

                        }
                    });
                }
            } else
                inputQuantite.setText("");


        });


    }

    private void Afficher_Facture() {

        List<String> factureValues = new ArrayList<>();
        String factureValue;

        for (ListePanier i : TablePanier.getItems()) {
            factureValue = Facture.getCellData(i);
            factureValues.add(factureValue);
        }

        int size = factureValues.size();
        Float sum = 0f;

        for (int i = 0; i < size; i++)
            sum += Float.parseFloat(factureValues.get(i));

        String Facture = String.valueOf(sum + " DH ");

        Montant_A_Paye.setText(Facture);
        ;


    }

    private void update_Quantite(int id, int newQuantite) throws SQLException {

        Connection etat = prepare.getConnection();


        String update = "UPDATE liste_produits SET Quantite =?  WHERE idListe_Produits =?";

        PreparedStatement psUpdate = etat.prepareStatement(update);

        psUpdate.setFloat(1, newQuantite);
        psUpdate.setInt(2, id);

        psUpdate.executeUpdate();

        dataListe = S_NextPage.getData_Panier();

        linkTo_Table(dataListe);


    }

    ArrayList<ListePanier> dataListe;

    private void prepareTable() throws SQLException {

        dataListe = S_NextPage.getData_Panier();

        String Nbr = String.valueOf(S_AcceuilFournisseur.getSize_Panier()) + " Piece ";

        nbrPiece.setText(Nbr);
        ;
        Afficher_Facture();

        linkTo_Table(dataListe);

    }

    private void linkTo_Table(ArrayList<ListePanier> dataListe) {
        if (dataListe != null) {

            Nom.setCellValueFactory(new PropertyValueFactory<ListePanier, String>("Nom"));
            Dosage.setCellValueFactory(new PropertyValueFactory<ListePanier, String>("dosage"));
            Facture.setCellValueFactory(new PropertyValueFactory<ListePanier, String>("facture"));
            Prix.setCellValueFactory(new PropertyValueFactory<ListePanier, String>("prix"));
            Quantite.setCellValueFactory(new PropertyValueFactory<ListePanier, String>("quantite"));
            TablePanier.getItems().setAll(dataListe);

        }

    }

    private void test() {

        List<String> factureValues = new ArrayList<>();
        String factureValue;

        for (ListePanier i : TablePanier.getItems()) {
            factureValue = Facture.getCellData(i);
            factureValues.add(factureValue);
        }

        int size = factureValues.size();
        Float sum = 0f;

        for (int i = 0; i < size; i++)
            sum += Float.parseFloat(factureValues.get(i));

        String Facture = String.valueOf(sum + " DH");

        Montant_A_Paye.setText(Facture);
        ;


    }

    private void traite_Valider(ActionEvent event) {

        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setTitle("Confirmation");
        alert.setHeaderText("Are you sure ?");
        alert.setContentText("");

        ButtonType yesButton = new ButtonType("Yes");
        ButtonType noButton = new ButtonType("No");

        alert.getButtonTypes().setAll(yesButton, noButton);

        alert.showAndWait().ifPresent(type -> {
            if (type == yesButton) {

                try {
                    Valider_Panier(event) ;
                } catch (SQLException e) {
                    throw new RuntimeException(e);
                }
            }


           });


              }

    private void Valider_Panier(ActionEvent event) throws SQLException {


        Connection etat = prepare.getConnection();


        String update1 = "UPDATE liste_produits SET Quantite_Du_Stock = Quantite";
        String update2 = "UPDATE liste_produits SET Quantite =0 ";

        PreparedStatement psUpdate1 = etat.prepareStatement(update1);
        PreparedStatement psUpdate2 = etat.prepareStatement(update2);

        if ( psUpdate1.executeUpdate()>0)

        {
            psUpdate2.executeUpdate() ;
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Succes");
            alert.setHeaderText("Payer Valider");
            alert.setContentText("");
            alert.showAndWait() ;
            Stage stage = (Stage) ((Button)event.getSource()).getScene().getWindow();
            stage.close();
        }





    }


}

