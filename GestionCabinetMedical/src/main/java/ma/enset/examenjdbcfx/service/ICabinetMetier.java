package ma.enset.examenjdbcfx.service;

import ma.enset.examenjdbcfx.dao.entities.Consultation;
import ma.enset.examenjdbcfx.dao.entities.Medecin;
import ma.enset.examenjdbcfx.dao.entities.Patient;

import java.util.List;

public interface ICabinetMetier {
    // Gestion des patients
    void ajouterPatient(Patient patient);
    void supprimerPatient(Integer idPatient);
    List<Patient> afficherListePatients();
    List<Consultation> getConsultationsPatient(int idPatient);
    List<Patient> rechercherPatientsParMotCle(String motCle);

    // Gestion des médecins
    void ajouterMedecin(Medecin medecin);
    void supprimerMedecin(Integer idMedecin);
    List<Medecin> afficherListeMedecins();
    List<Consultation> getConsultationsMedecin(int idMedecin);

    // Gestion des consultations
    void ajouterConsultation(Consultation consultation);
    void supprimerConsultation(Integer idConsultation);
    List<Consultation> afficherListeConsultations();
}
