package ma.enset.examenjdbcfx.presentation;

import ma.enset.examenjdbcfx.dao.ConsultationDAOImpl;
import ma.enset.examenjdbcfx.dao.MedecinDAOImpl;
import ma.enset.examenjdbcfx.dao.PatientDAOImpl;
import ma.enset.examenjdbcfx.dao.entities.Consultation;
import ma.enset.examenjdbcfx.dao.entities.Medecin;
import ma.enset.examenjdbcfx.dao.entities.Patient;
import ma.enset.examenjdbcfx.service.ICabinetMetier;
import ma.enset.examenjdbcfx.service.ImplCabinetMetier;

import java.util.ArrayList;
import java.util.List;

public class AppTest {
    public static void main(String[] args) {
        ICabinetMetier iCabinetMetier0 = new ImplCabinetMetier(new MedecinDAOImpl());
        ICabinetMetier iCabinetMetier1 = new ImplCabinetMetier(new PatientDAOImpl());
        ICabinetMetier iCabinetMetier2 = new ImplCabinetMetier(new ConsultationDAOImpl());

        List<Medecin> medecinList = iCabinetMetier0.afficherListeMedecins();
        List<Patient> patientList = iCabinetMetier1.afficherListePatients();
        List<Consultation> consultationList = iCabinetMetier2.afficherListeConsultations();

        for (Medecin m : medecinList) {
            System.out.println(m);

        }
        System.out.println("*************************");
        for (Patient p : patientList) {
            System.out.println(p);

        }
        System.out.println("*************************");

        for (Consultation c : consultationList) {
            System.out.println(c);

        }


    }
}
