package ma.enset.examenjdbcfx.dao;

import ma.enset.examenjdbcfx.dao.entities.Consultation;
import ma.enset.examenjdbcfx.dao.entities.Medecin;
import ma.enset.examenjdbcfx.dao.entities.Patient;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class MedecinDAOImpl implements IMedecinDAO {
    @Override
    public void ajouter(Medecin o) {
        Connection connection = DBSingleton.getConnection();
        try {
            PreparedStatement pstm =connection.prepareStatement("INSERT INTO MEDECIN(nom,prenom,email,tel) "+"VALUES(?,?,?,?)");
            pstm.setString(1, o.getNom());
            pstm.setString(2, o.getPrenom());
            pstm.setString(3, o.getEmail());
            pstm.setString(4, o.getTel());

            pstm.executeUpdate();



        } catch (SQLException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }

    @Override
    public void supprimer(Integer id) {
        Connection connection = DBSingleton.getConnection();
        PreparedStatement pstm = null;

        try {

            String deleteConsultationsQuery = "DELETE FROM consultation WHERE id_medecin = ?";
            pstm = connection.prepareStatement(deleteConsultationsQuery);
            pstm.setInt(1, id);
            pstm.executeUpdate();

            String deletePatientQuery = "DELETE FROM MEDECIN WHERE id_medecin=?";
            pstm = connection.prepareStatement(deletePatientQuery);
            pstm.setInt(1,id);
            pstm.executeUpdate();



        } catch (SQLException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }

    }

    @Override
    public List<Medecin> afficherListe() {
        Connection connection = DBSingleton.getConnection();
        List<Medecin> medecins = new ArrayList<>();
        try {
            PreparedStatement pstm =connection.prepareStatement("SELECT * FROM MEDECIN");

            ResultSet rst =  pstm.executeQuery();
            while(rst.next()) {
                Medecin medecin = new Medecin();
                medecin.setIdMedecin(rst.getInt("id_medecin"));
                medecin.setNom(rst.getString("NOM"));
                medecin.setPrenom(rst.getString("PRENOM"));
                medecin.setEmail(rst.getString("EMAIL"));
                medecin.setTel(rst.getString("tel"));
                medecins.add(medecin);
            }



        } catch (SQLException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        return medecins;
    }

    @Override
    public List<Consultation> getConsultationsMedecin(int idMedecin) {
        Connection connection = DBSingleton.getConnection();
        List<Consultation> consultations = new ArrayList<>();
        try {
            // Utilisez une jointure pour récupérer les consultations associées à un médecin
            String query = "SELECT c.id_consultation, c.date_consultation, p.id_patient, p.nom AS nom_patient, p.prenom AS prenom_patient, " +
                    "m.id_medecin, m.nom AS nom_medecin, m.prenom AS prenom_medecin " +
                    "FROM consultation c " +
                    "INNER JOIN medecin m ON c.id_medecin = m.id_medecin " +
                    "INNER JOIN patient p ON c.id_patient = p.id_patient " +
                    "WHERE m.id_medecin = ?";
            PreparedStatement pstm = connection.prepareStatement(query);
            pstm.setInt(1, idMedecin);

            ResultSet rst = pstm.executeQuery();
            while (rst.next()) {
                Consultation consultation = new Consultation();
                consultation.setIdConsultation(rst.getInt("id_consultation"));
                consultation.setDateConsultation(rst.getDate("date_consultation"));

                // Création de l'objet Patient
                Patient patient = new Patient();
                patient.setIdPatient(rst.getInt("id_patient"));
               // patient.setNom(rst.getString("nom_patient"));
                //patient.setPrenom(rst.getString("prenom_patient"));

                // Création de l'objet Medecin
                Medecin medecin = new Medecin();
                medecin.setIdMedecin(rst.getInt("id_medecin"));
                //medecin.setNom(rst.getString("nom_medecin"));
                //medecin.setPrenom(rst.getString("prenom_medecin"));

                // Attribution du patient et du médecin à la consultation
                consultation.setPatient(patient);
                consultation.setMedecin(medecin);

                consultations.add(consultation);
            }


        } catch (SQLException e) {
            e.printStackTrace();
        }
        return consultations;
    }
    // Implémentez les méthodes spécifiques pour les médecins
}



