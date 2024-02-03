package ma.enset.examenjdbcfx.dao;

import ma.enset.examenjdbcfx.dao.entities.Consultation;
import ma.enset.examenjdbcfx.dao.entities.Patient;

import java.util.List;

public class Test2 {
    public static void main(String[] args) {
        PatientDAOImpl patientDAO = new PatientDAOImpl();
        Patient p1 = new Patient();
        p1.setNom("taha");
        p1.setPrenom("dddd");
        p1.setCin("151551");
        p1.setEmail("151515@gmail");
        p1.setTelephone("0655870980");
        p1.setDateNaissance(java.sql.Date.valueOf("1990-01-01"));
        //patientDAO.ajouter(p1);
        List<Patient> patients = patientDAO.afficherListe();
        System.out.println("Liste des patients :");
        for (Patient patient : patients) {
            System.out.println(patient);
        }
        System.out.println("=================");
        List<Consultation> consultationsPatientTest = patientDAO.getConsultationsPatient(2);
        System.out.println("Consultations du patient test :");
        for (Consultation consultation : consultationsPatientTest) {
            System.out.println(consultation);
        }
        String motCle = "yassine";
        List<Patient> patientsRecherches = patientDAO.rechercherParMotCle(motCle);
        System.out.println("Résultats de la recherche par mot-clé '" + motCle + "' :");
        for (Patient patient : patientsRecherches) {
            System.out.println(patient);
        }
       // patientDAO.supprimer(7);
        //patientDAO.supprimer(8);

    }

}
