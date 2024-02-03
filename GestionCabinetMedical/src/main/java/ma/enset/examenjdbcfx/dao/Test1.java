package ma.enset.examenjdbcfx.dao;

import ma.enset.examenjdbcfx.dao.entities.Consultation;
import ma.enset.examenjdbcfx.dao.entities.Medecin;

import java.util.ArrayList;
import java.util.List;

public class Test1 {
    public static void main(String[] args) {

        /*MedecinDAOImpl medcins = new MedecinDAOImpl();
        Medecin m1 = new Medecin();
        m1.setNom("HAHA");
        m1.setPrenom("dkjfb");
        m1.setTel("0655870980");
        m1.setEmail("154@gmail.com");*/
        //medcins.ajouter(m1);
       //medcins.supprimer(7);
       // medcins.supprimer(6);
        //List<Medecin> medecinList = medcins.afficherListe();

        /*for(Medecin m : medecinList ){
            System.out.print("ID : "+m.getIdMedecin() + " ");
            System.out.print("Nom : "+m.getNom()+ " ");
            System.out.print("Prenom : "+m.getPrenom()+ " ");
            System.out.print("Email : "+m.getEmail()+ " ");

            System.out.print("Tel : "+m.getTel()+ " ");

            System.out.println();
        }*/
        IMedecinDAO medecinDAO = new MedecinDAOImpl();

        // Ajout d'un médecin pour effectuer le test
        Medecin medecinTest = new Medecin();
        medecinTest.setNom("NomTest");
        medecinTest.setPrenom("PrenomTest");
        medecinTest.setEmail("test@example.com");
        medecinTest.setTel("123456789");
        medecinDAO.ajouter(medecinTest);

        // Récupération de la liste des médecins (vous pouvez visualiser pour vérifier si le médecin test a été ajouté)
        List<Medecin> medecins = medecinDAO.afficherListe();
        System.out.println("Liste des médecins :");
        for (Medecin medecin : medecins) {
            System.out.println(medecin);
        }

        // Récupération des consultations du médecin test
        List<Consultation> consultationsMedecinTest = medecinDAO.getConsultationsMedecin(3);
        System.out.println("Consultations du médecin test :");
        for (Consultation consultation : consultationsMedecinTest) {
            System.out.print(consultation.getIdConsultation() + " ");
            System.out.print(consultation.getDateConsultation() + " ");
            System.out.print(consultation.getPatient().getIdPatient() + " ");
            System.out.print(consultation.getMedecin().getIdMedecin()+ " ");
            System.out.println();
        }
    }

    }

