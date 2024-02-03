package ma.enset.examenjdbcfx.presentation.controller;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.DatePicker;
import javafx.scene.control.ListView;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import ma.enset.examenjdbcfx.dao.MedecinDAOImpl;
import ma.enset.examenjdbcfx.dao.PatientDAOImpl;
import ma.enset.examenjdbcfx.dao.entities.Consultation;
import ma.enset.examenjdbcfx.dao.entities.Medecin;
import ma.enset.examenjdbcfx.dao.entities.Patient;
import ma.enset.examenjdbcfx.service.ICabinetMetier;
import ma.enset.examenjdbcfx.service.ImplCabinetMetier;

import java.net.URL;
import java.time.LocalDate;
import java.util.Date;
import java.util.List;
import java.util.ResourceBundle;

public class PatientController  implements Initializable {

    @FXML
    private TableColumn<Patient, String> cin;

    @FXML
    private TableColumn<Patient, Date> date;

    @FXML
    private DatePicker dateTextField;

    @FXML
    private TableColumn<Patient, String> email;

    @FXML
    private TableColumn<Patient, Integer> idPatient;

    @FXML
    private ListView<String> listViewMedConsultation;

    @FXML
    private TableColumn<Patient, String> nom;

    @FXML
    private TableColumn<Patient, String> prenom;

    @FXML
    private TableView<Patient> tableViewPatient;

    @FXML
    private TableColumn<Patient, String> tel;

    @FXML
    private TextField textFieldEmail;

    @FXML
    private TextField textFieldNom;

    @FXML
    private TextField textFieldPatientConsul;

    @FXML
    private TextField textFieldPrenom;

    @FXML
    private TextField textFieldCin;

    @FXML
    private TextField textFieldSearchID;

    @FXML
    private TextField textFieldTelephone;
    ObservableList<Patient> data = FXCollections.observableArrayList();

    ICabinetMetier metier = new ImplCabinetMetier(new PatientDAOImpl());
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        idPatient.setCellValueFactory(new PropertyValueFactory<>("idPatient"));
        nom.setCellValueFactory(new PropertyValueFactory<>("nom"));
        prenom.setCellValueFactory(new PropertyValueFactory<>("prenom"));
        cin.setCellValueFactory(new PropertyValueFactory<>("cin"));
        tel.setCellValueFactory(new PropertyValueFactory<>("telephone"));
        email.setCellValueFactory(new PropertyValueFactory<>("email"));
        date.setCellValueFactory(new PropertyValueFactory<>("dateNaissance"));
        loadPatient();
        tableViewPatient.setItems(data);

        textFieldSearchID.textProperty().addListener((o , oldValue , newValue )->{
            data.clear();
            data.addAll(metier.rechercherPatientsParMotCle(newValue));
        });



    }
    public void loadPatient(){
        data.clear();
        data.addAll(metier.afficherListePatients());

    }
    @FXML
    void ajouterPatient(ActionEvent event) {
        Patient p = new Patient();
        p.setNom(textFieldNom.getText());
        p.setPrenom(textFieldPrenom.getText());
        p.setEmail(textFieldEmail.getText());
        p.setCin(textFieldCin.getText());
        p.setTelephone(textFieldTelephone.getText());
        LocalDate localDate = dateTextField.getValue();
        if (localDate != null) {
            // Convertir LocalDate en Date
            Date dateNaissance = java.sql.Date.valueOf(localDate);
            p.setDateNaissance(dateNaissance);
        }
        metier.ajouterPatient(p);

        textFieldNom.setText("");
        textFieldCin.setText("");
        textFieldTelephone.setText("");
        textFieldEmail.setText("");
        textFieldPrenom.setText("");
        dateTextField.setValue(null);

        loadPatient();

    }

    @FXML
    void supprimerPatient(ActionEvent event) {
        Patient p = tableViewPatient.getSelectionModel().getSelectedItem();
        metier.supprimerPatient(p.getIdPatient());
        loadPatient();
    }
    @FXML
    void LabelChercherParID(ActionEvent event) {

    }

    @FXML
    void afficherConsultationPatient(ActionEvent event) {
        int idPatient = Integer.parseInt(textFieldPatientConsul.getText());
        List<Consultation> consultations = metier.getConsultationsPatient(idPatient);
        afficherConsultations(consultations);
    }
    private void afficherConsultations(List<Consultation> consultations) {
        listViewMedConsultation.getItems().clear();
        for (Consultation consultation : consultations) {
            listViewMedConsultation.getItems().add(consultation.toString());
        }
    }




}
