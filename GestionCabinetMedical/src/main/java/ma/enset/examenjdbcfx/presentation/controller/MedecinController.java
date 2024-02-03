package ma.enset.examenjdbcfx.presentation.controller;


import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.ListView;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import ma.enset.examenjdbcfx.dao.MedecinDAOImpl;
import ma.enset.examenjdbcfx.dao.entities.Consultation;
import ma.enset.examenjdbcfx.dao.entities.Medecin;
import ma.enset.examenjdbcfx.service.ICabinetMetier;
import ma.enset.examenjdbcfx.service.ImplCabinetMetier;

import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;

public class MedecinController implements Initializable {

    @FXML
    private TableView<Consultation> tableViewConsultation;
    @FXML
    private ListView<String> listViewMedConsultation;
    @FXML
    private TableView<Medecin> tableViewMedecin;
    @FXML
    private TableColumn<Medecin, String> email;
    @FXML
    private TableColumn<Medecin, String> idMedecin;
    @FXML
    private TableColumn<Medecin, String> nom;
    @FXML
    private TableColumn<Medecin, String> prenom;
    @FXML
    private TableColumn<Medecin, String> tel;
    @FXML
    private TextField textFieldEmail;
    @FXML
    private TextField textFieldMedConsul;

    @FXML
    private TextField textFieldNom;

    @FXML
    private TextField textFieldPrenom;

    @FXML
    private TextField textFieldTelephone;

    ObservableList<Medecin> data = FXCollections.observableArrayList();

    ICabinetMetier metier = new ImplCabinetMetier(new MedecinDAOImpl());

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        idMedecin.setCellValueFactory(new PropertyValueFactory<>("idMedecin"));
        nom.setCellValueFactory(new PropertyValueFactory<>("nom"));
        prenom.setCellValueFactory(new PropertyValueFactory<>("prenom"));
        email.setCellValueFactory(new PropertyValueFactory<>("email"));
        tel.setCellValueFactory(new PropertyValueFactory<>("tel"));
        loadMedecin();
        tableViewMedecin.setItems(data);
    }
    @FXML
    void ajouterMedecin(ActionEvent event) {
        Medecin m = new Medecin();

        m.setNom(textFieldNom.getText());
        m.setPrenom(textFieldPrenom.getText());
        m.setEmail(textFieldEmail.getText());
        m.setTel(textFieldTelephone.getText());
        metier.ajouterMedecin(m);
        textFieldNom.setText("");
        textFieldPrenom.setText("");
        textFieldEmail.setText("");
        textFieldTelephone.setText("");
        loadMedecin();

    }
    public void loadMedecin(){
        data.clear();
        data.addAll(metier.afficherListeMedecins());

    }

    @FXML
    void supprimerMedecin(ActionEvent event) {
        Medecin m = tableViewMedecin.getSelectionModel().getSelectedItem();
        metier.supprimerMedecin(m.getIdMedecin());
        loadMedecin();

    }
    @FXML
    void afficherConsultationMedecin(ActionEvent event) {
        int idMedecin = Integer.parseInt(textFieldMedConsul.getText());
        List<Consultation> consultations = metier.getConsultationsMedecin(idMedecin);
        afficherConsultations(consultations);
    }

    private void afficherConsultations(List<Consultation> consultations) {
        listViewMedConsultation.getItems().clear();

        for (Consultation consultation : consultations) {
            listViewMedConsultation.getItems().add(consultation.toString());
        }
    }



}
