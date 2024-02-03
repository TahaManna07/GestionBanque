package ma.enset.examenjdbcfx.presentation.controller;

import javafx.beans.property.SimpleIntegerProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import ma.enset.examenjdbcfx.dao.ConsultationDAOImpl;
import ma.enset.examenjdbcfx.dao.PatientDAOImpl;
import ma.enset.examenjdbcfx.dao.entities.Consultation;
import ma.enset.examenjdbcfx.dao.entities.Medecin;
import ma.enset.examenjdbcfx.dao.entities.Patient;
import ma.enset.examenjdbcfx.service.ICabinetMetier;
import ma.enset.examenjdbcfx.service.ImplCabinetMetier;

import java.net.URL;
import java.time.LocalDate;
import java.util.Date;
import java.util.ResourceBundle;

public class ConsultationController implements Initializable  {

    @FXML
    private TableColumn<Consultation, Integer> IdPatientColonne;

    @FXML
    private TableView<Consultation> consultationTableView;

    @FXML
    private TableColumn<Consultation, Date> dateDeConsultationColonne;

    @FXML
    private DatePicker dateTextField;

    @FXML
    private TableColumn<Consultation, Integer> idConsultationColonne;

    @FXML
    private TableColumn<Consultation, Integer> idMedecinColonne;

    @FXML
    private TextField idMedecinTextfield;

    @FXML
    private TextField idPatientTextfield;
    ObservableList<Consultation> data = FXCollections.observableArrayList();
    ICabinetMetier metier = new ImplCabinetMetier(new ConsultationDAOImpl());

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

        idConsultationColonne.setCellValueFactory(new PropertyValueFactory<>("idConsultation"));
        dateDeConsultationColonne.setCellValueFactory(new PropertyValueFactory<>("dateConsultation"));
        idMedecinColonne.setCellValueFactory(cellData -> new SimpleIntegerProperty(cellData.getValue().getMedecin().getIdMedecin()).asObject());
        IdPatientColonne.setCellValueFactory(cellData -> new SimpleIntegerProperty(cellData.getValue().getPatient().getIdPatient()).asObject());
        loadConsultation();
        consultationTableView.setItems(data);
    }
    private void loadConsultation(){
        data.clear();
        data.addAll(metier.afficherListeConsultations());
    }
    @FXML
    void ajouterConsultation(ActionEvent event) {
        Consultation c = new Consultation();

        // Créer une instance de Patient et lui affecter l'ID
        Patient patient = new Patient();
        patient.setIdPatient(Integer.parseInt(idPatientTextfield.getText()));
        c.setPatient(patient);

        // Créer une instance de Medecin et lui affecter l'ID
        Medecin medecin = new Medecin();
        medecin.setIdMedecin(Integer.parseInt(idMedecinTextfield.getText()));
        c.setMedecin(medecin);

        LocalDate localDate = dateTextField.getValue();
        if (localDate != null) {
            // Convertir LocalDate en Date
            Date dateNaissance = java.sql.Date.valueOf(localDate);
            c.setDateConsultation(dateNaissance);
        }

        metier.ajouterConsultation(c);
        idPatientTextfield.setText("");
        idMedecinTextfield.setText("");
        dateTextField.setValue(null);
        loadConsultation();
    }


    @FXML
    void supprimerConsultation(ActionEvent event) {
        Consultation c = consultationTableView.getSelectionModel().getSelectedItem();
        metier.supprimerConsultation(c.getIdConsultation());
        loadConsultation();
    }


}
