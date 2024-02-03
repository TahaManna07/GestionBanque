package ma.enset.examenjdbcfx.service;

import ma.enset.examenjdbcfx.dao.IConsultationDAO;
import ma.enset.examenjdbcfx.dao.IMedecinDAO;
import ma.enset.examenjdbcfx.dao.IPatientDAO;
import ma.enset.examenjdbcfx.dao.entities.Consultation;
import ma.enset.examenjdbcfx.dao.entities.Medecin;
import ma.enset.examenjdbcfx.dao.entities.Patient;

import java.util.List;

public class ImplCabinetMetier implements  ICabinetMetier{

    IMedecinDAO medecinDAO;
    IPatientDAO patientDAO;
    IConsultationDAO consultationDAO;

    public ImplCabinetMetier(IMedecinDAO medecinDAO) {
        this.medecinDAO = medecinDAO;
    }

    public ImplCabinetMetier(IPatientDAO patientDAO) {
        this.patientDAO = patientDAO;
    }

    public ImplCabinetMetier(IConsultationDAO consultationDAO) {
        this.consultationDAO = consultationDAO;
    }

    @Override
    public void ajouterPatient(Patient patient) {
        patientDAO.ajouter(patient);
    }

    @Override
    public void supprimerPatient(Integer idPatient) {
        patientDAO.supprimer(idPatient);
    }

    @Override
    public List<Patient> afficherListePatients() {
        return patientDAO.afficherListe();
    }

    @Override
    public List<Consultation> getConsultationsPatient(int idPatient) {
        return patientDAO.getConsultationsPatient(idPatient);
    }

    @Override
    public List<Patient> rechercherPatientsParMotCle(String motCle) {
        return patientDAO.rechercherParMotCle(motCle);
    }

    @Override
    public void ajouterMedecin(Medecin medecin) {
        medecinDAO.ajouter(medecin);
    }

    @Override
    public void supprimerMedecin(Integer idMedecin) {
        medecinDAO.supprimer(idMedecin);
    }

    @Override
    public List<Medecin> afficherListeMedecins() {
        return medecinDAO.afficherListe();
    }

    @Override
    public List<Consultation> getConsultationsMedecin(int idMedecin) {
        return medecinDAO.getConsultationsMedecin(idMedecin);
    }

    @Override
    public void ajouterConsultation(Consultation consultation) {
        consultationDAO.ajouter(consultation);
    }

    @Override
    public void supprimerConsultation(Integer idConsultation) {
        consultationDAO.supprimer(idConsultation);
    }

    @Override
    public List<Consultation> afficherListeConsultations() {
        return consultationDAO.afficherListe();
    }
}
