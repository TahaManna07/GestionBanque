package ma.enset.examenjdbcfx.dao;

import ma.enset.examenjdbcfx.dao.ConsultationDAOImpl;
import ma.enset.examenjdbcfx.dao.IConsultationDAO;
import ma.enset.examenjdbcfx.dao.entities.Consultation;
import ma.enset.examenjdbcfx.dao.entities.Medecin;
import ma.enset.examenjdbcfx.dao.entities.Patient;

import java.util.List;

public class Test3 {

    public static void main(String[] args) {
        // Création d'une instance de ConsultationDAOImpl
        ConsultationDAOImpl consultationDAO = new ConsultationDAOImpl();

        // Création d'un patient et d'un médecin fictifs pour la consultation
        Patient patient = new Patient();
        patient.setIdPatient(9);  // Remplacez cela par l'id réel du patient
        Medecin medecin = new Medecin();
        medecin.setIdMedecin(3);  // Remplacez cela par l'id réel du médecin

        // Création d'une nouvelle consultation
        Consultation consultation = new Consultation();
        consultation.setDateConsultation(java.sql.Date.valueOf("2023-01-01"));
        consultation.setPatient(patient);
        consultation.setMedecin(medecin);

        // Ajout de la consultation
        //consultationDAO.ajouter(consultation);
        List<Consultation> consultations = consultationDAO.afficherListe();
        System.out.println("Liste des consultations :");
        for (Consultation c : consultations) {
            System.out.print(c.getIdConsultation());
            System.out.print(c.getDateConsultation());
            System.out.print(c.getPatient().getIdPatient());
            System.out.print(c.getMedecin().getIdMedecin());
            System.out.println();
        }
        consultationDAO.supprimer(5);
    }



}
