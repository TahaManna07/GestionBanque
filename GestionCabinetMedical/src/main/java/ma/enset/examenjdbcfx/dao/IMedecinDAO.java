package ma.enset.examenjdbcfx.dao;

import ma.enset.examenjdbcfx.dao.entities.Consultation;
import ma.enset.examenjdbcfx.dao.entities.Medecin;

import java.util.List;

public interface IMedecinDAO extends DAO<Medecin, Integer> {
    List<Consultation> getConsultationsMedecin(int idMedecin);
}


