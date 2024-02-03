package ma.enset.examenjdbcfxsdia.presentation.controller;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ListView;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import ma.enset.examenjdbcfxsdia.dao.EquipeDAOImpl;
import ma.enset.examenjdbcfxsdia.dao.entities.Equipe;
import ma.enset.examenjdbcfxsdia.dao.entities.Joueur;
import ma.enset.examenjdbcfxsdia.service.IEquipeJoueurServiceImpl;

import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;

public class EquipeController implements Initializable {

    @FXML
    private TextField JoueursByEquipeTextField;

    @FXML
    private TextField NomTextField;

    @FXML
    private TextField VilleTextField;

    @FXML
    private Button afficherButton;

    @FXML
    private Button ajouterButton;

    @FXML
    private TableColumn<Equipe, Integer> colonneID;

    @FXML
    private TextField labelChercherParMotCle;

    @FXML
    private ListView<String> listView;

    @FXML
    private TableColumn<Equipe, Integer> nomColonne;

    @FXML
    private Button supprimerButton;

    @FXML
    private TableView<Equipe> tableView;

    @FXML
    private TableColumn<Equipe, String> villeColonne;
    ObservableList<Equipe> data = FXCollections.observableArrayList();


    IEquipeJoueurServiceImpl metier = new IEquipeJoueurServiceImpl(new EquipeDAOImpl());
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        colonneID.setCellValueFactory(new PropertyValueFactory<>("id"));
        nomColonne.setCellValueFactory(new PropertyValueFactory<>("nom"));
        villeColonne.setCellValueFactory(new PropertyValueFactory<>("ville"));
        loadEquipe();
        tableView.setItems(data);
        labelChercherParMotCle.textProperty().addListener((o , oldValue , newValue)->{
            data.clear();
            data.addAll((metier.searchEquipesByKeyword(newValue)));

        });
    }
    @FXML
    private Button confirmerButton;
    @FXML
    private Button modifierButton;
    public void loadEquipe(){
        data.clear();
        data.addAll(metier.getAllEquipes());
    }
    @FXML
    void afficherButton(ActionEvent event) {
        int idEquipe = Integer.parseInt(JoueursByEquipeTextField.getText());
        Equipe equipe = new Equipe();
        equipe.setId(idEquipe);
        List<Joueur> j = metier.getJoueursByEquipe(equipe);
        afficherJouers(j);
    }

    @FXML
    void ajouterJoueur(ActionEvent event) {
        Equipe equipe = new Equipe();
        equipe.setNom(NomTextField.getText());
        equipe.setVille(VilleTextField.getText());
        metier.addEquipe(equipe);
        NomTextField.setText("");
        VilleTextField.setText("");
        loadEquipe();
    }

    @FXML
    void supprimerJoueur(ActionEvent event) {
            Equipe equipe =tableView.getSelectionModel().getSelectedItem();
            metier.deleteEquipe(equipe.getId());
            loadEquipe();
    }
    private void afficherJouers(List<Joueur> joueurs) {
        listView.getItems().clear();

        for (Joueur j : joueurs) {
            listView.getItems().add(j.toString());
        }
    }
    private Equipe selectedEquipeForModification;

// ... (existing code)

    @FXML
    void modifierEquipe(ActionEvent event) {
        // Get the selected equipe for modification
        selectedEquipeForModification = tableView.getSelectionModel().getSelectedItem();

        if (selectedEquipeForModification != null) {
            // Load equipe details into text fields for modification
            NomTextField.setText(selectedEquipeForModification.getNom());
            VilleTextField.setText(selectedEquipeForModification.getVille());
        }
    }

    @FXML
    void confirmerModification(ActionEvent event) {
        if (selectedEquipeForModification != null) {
            // Update the details of the selected equipe
            selectedEquipeForModification.setNom(NomTextField.getText());
            selectedEquipeForModification.setVille(VilleTextField.getText());

            // Call the service method to update the equipe in the database
            metier.updateEquipe(selectedEquipeForModification);

            // Clear the text fields and reload the equipes
            clearTextFields();
            loadEquipe();
        }
    }

    // Helper method to clear text fields
    private void clearTextFields() {
        NomTextField.clear();
        VilleTextField.clear();
    }

}
