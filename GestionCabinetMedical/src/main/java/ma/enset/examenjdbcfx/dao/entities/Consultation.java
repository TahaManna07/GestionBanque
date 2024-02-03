package ma.enset.examenjdbcfx.dao.entities;

import java.io.Serializable;
import java.util.Date;

public class Consultation implements Serializable {
    private int idConsultation;
    private Date dateConsultation;
    private Patient patient;
    private Medecin medecin;
    public Consultation(){

    }
    public Consultation(int idConsultation, Date dateConsultation, Patient patient, Medecin medecin) {
        this.idConsultation = idConsultation;
        this.dateConsultation = dateConsultation;
        this.patient = patient;
        this.medecin = medecin;
    }

    public int getIdConsultation() {
        return idConsultation;
    }

    public void setIdConsultation(int idConsultation) {
        this.idConsultation = idConsultation;
    }

    public Date getDateConsultation() {
        return dateConsultation;
    }

    public void setDateConsultation(Date dateConsultation) {
        this.dateConsultation = dateConsultation;
    }

    public Patient getPatient() {
        return patient;
    }

    public void setPatient(Patient patient) {
        this.patient = patient;
    }

    public Medecin getMedecin() {
        return medecin;
    }

    public void setMedecin(Medecin medecin) {
        this.medecin = medecin;
    }

    @Override
    public String toString() {
        return "Consultation{" +
                "idConsultation=" + idConsultation +
                ", dateConsultation=" + dateConsultation +
                ", IDpatient=" + patient.getIdPatient() +
                ", IDmedecin=" + medecin.getIdMedecin() +
                '}';
    }
}
