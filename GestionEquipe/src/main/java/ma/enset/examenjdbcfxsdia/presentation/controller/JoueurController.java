package ma.enset.examenjdbcfxsdia.presentation.controller;

import javafx.beans.property.SimpleIntegerProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.FileChooser;
import ma.enset.examenjdbcfxsdia.dao.EquipeDAOImpl;
import ma.enset.examenjdbcfxsdia.dao.JoueurDAOImpl;
import ma.enset.examenjdbcfxsdia.dao.entities.Equipe;
import ma.enset.examenjdbcfxsdia.dao.entities.Joueur;
import ma.enset.examenjdbcfxsdia.service.IEquipeJoueurServiceImpl;

import java.io.File;
import java.net.URL;
import java.util.ResourceBundle;

public class JoueurController implements Initializable {

    @FXML
    private Button Imprimer;

    @FXML
    private TableColumn<Joueur, Integer> colonneEquipeID;

    @FXML
    private TableColumn<Joueur, Integer> colonneID;

    @FXML
    private TableColumn<Joueur, String> colonneNom;

    @FXML
    private TableColumn<Joueur, Integer> colonneNumero;

    @FXML
    private TableColumn<Joueur, String> colonnePosition;

    @FXML
    private TableView<Joueur> tableView;

    @FXML
    private TextField textFieldEquipeID;

    @FXML
    private TextField textFieldFichierImprimer;

    @FXML
    private TextField textFieldNom;

    @FXML
    private TextField textFieldNumero;

    @FXML
    private TextField textFieldPosition;
    ObservableList<Joueur> data = FXCollections.observableArrayList();
    IEquipeJoueurServiceImpl metier = new IEquipeJoueurServiceImpl(new JoueurDAOImpl());

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        colonneID.setCellValueFactory(new PropertyValueFactory<>("id"));
        colonneNom.setCellValueFactory(new PropertyValueFactory<>("nom"));
        colonnePosition.setCellValueFactory(new PropertyValueFactory<>("position"));
        colonneNumero.setCellValueFactory(new PropertyValueFactory<>("numero"));
        colonneEquipeID.setCellValueFactory(cellData -> new SimpleIntegerProperty(cellData.getValue().getEquipe().getId()).asObject());
        loadJoeur();
        tableView.setItems(data);

    }
    public void loadJoeur(){
        data.clear();
        data.addAll(metier.getAllJoueurs());
    }

    @FXML
    void ajouterJoueur(ActionEvent event) {
        Joueur joueur = new Joueur();
        joueur.setNom(textFieldNom.getText());
        joueur.setNumero(Integer.parseInt(textFieldNumero.getText()));
        joueur.setPosition(textFieldPosition.getText());
        Equipe equipe = new Equipe();
        equipe.setId(Integer.parseInt(textFieldEquipeID.getText()));
        joueur.setEquipe(equipe);
        metier.addJoueur(joueur);
        textFieldNom.setText("");
        textFieldNumero.setText("");
        textFieldPosition.setText("");
        textFieldEquipeID.setText("");
        loadJoeur();





    }

    @FXML
    void supprimerJoueur(ActionEvent event) {
        Joueur joueur = tableView.getSelectionModel().getSelectedItem();
        metier.deleteJoueur(joueur.getId());
        loadJoeur();



    }
    @FXML
    private Button confirmerModification;

    @FXML
    private Button modifier;
    @FXML
    void printJoueurInfoToPDFButton(ActionEvent event) {
        Joueur selectedJoueur = tableView.getSelectionModel().getSelectedItem();

        if (selectedJoueur != null) {
            FileChooser fileChooser = new FileChooser();
            fileChooser.getExtensionFilters().add(new FileChooser.ExtensionFilter("PDF Files", "*.pdf"));
            File selectedFile = fileChooser.showSaveDialog(null);

            if (selectedFile != null) {
                metier.printJoueurInfoToPDF(selectedJoueur);
            }
        }
    }
    @FXML
    void cofirmerModofication(ActionEvent event) {
        Joueur joueur = tableView.getSelectionModel().getSelectedItem();

        if (joueur != null) {
            joueur.setNom(textFieldNom.getText());
            joueur.setPosition(textFieldPosition.getText());
            joueur.setNumero(Integer.parseInt(textFieldNumero.getText()));
            Equipe equipe = new Equipe();
            equipe.setId(Integer.parseInt(textFieldEquipeID.getText()));
            joueur.setEquipe(equipe);
            metier.updateJoueur(joueur);
            loadJoeur();
            // Réinitialiser les champs de texte après la modification
            textFieldNom.setText("");
            textFieldPosition.setText("");
            textFieldNumero.setText("");
            textFieldEquipeID.setText("");
        } else {
            // Affichez un message d'erreur si aucun produit n'est sélectionné
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setContentText("Sélectionnez un joueur à modifier");
            alert.show();
        }
    }

    @FXML
    void modifier(ActionEvent event) {
        Joueur joueur = tableView.getSelectionModel().getSelectedItem();

        if (joueur != null) {

            textFieldNom.setText(joueur.getNom());
            textFieldPosition.setText(String.valueOf(joueur.getPosition()));
            textFieldNumero.setText(String.valueOf(joueur.getNumero()));
            textFieldEquipeID.setText(String.valueOf(joueur.getEquipe()));


        } else {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setContentText("Sélectionnez un joueur à modifier");
            alert.show();
        }
    }


}
