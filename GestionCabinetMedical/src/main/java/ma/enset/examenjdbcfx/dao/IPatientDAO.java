package ma.enset.examenjdbcfx.dao;

import ma.enset.examenjdbcfx.dao.entities.Consultation;
import ma.enset.examenjdbcfx.dao.entities.Patient;

import java.util.List;

public interface IPatientDAO extends DAO<Patient,Integer> {
    List<Consultation> getConsultationsPatient(int idPatient);
    List<Patient> rechercherParMotCle(String motCle);
}
