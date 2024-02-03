package ma.enset.examenjdbcfx.dao;

import ma.enset.examenjdbcfx.dao.entities.Consultation;
import ma.enset.examenjdbcfx.dao.entities.Medecin;
import ma.enset.examenjdbcfx.dao.entities.Patient;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class ConsultationDAOImpl implements IConsultationDAO {

    @Override
    public void ajouter(Consultation consultation) {
        Connection connection = DBSingleton.getConnection();
        PreparedStatement preparedStatement = null;

        try {
            // Assurez-vous que le patient et le médecin associés à la consultation existent déjà dans la base de données
            if (consultation.getPatient() != null && consultation.getMedecin() != null
                    && patientExists(consultation.getPatient().getIdPatient())
                    && medecinExists(consultation.getMedecin().getIdMedecin())) {

                String query = "INSERT INTO consultation (date_consultation, id_patient, id_medecin) VALUES (?, ?, ?)";
                preparedStatement = connection.prepareStatement(query, Statement.RETURN_GENERATED_KEYS);
                preparedStatement.setDate(1, new java.sql.Date(consultation.getDateConsultation().getTime()));
                preparedStatement.setInt(2, consultation.getPatient().getIdPatient());
                preparedStatement.setInt(3, consultation.getMedecin().getIdMedecin());

                preparedStatement.executeUpdate();
            }
        } catch (SQLException e) {
            e.printStackTrace();
            // Gestion des exceptions
        }
    }

    @Override
    public void supprimer(Integer id) {
        Connection connection = DBSingleton.getConnection();
        PreparedStatement preparedStatement = null;

        try {
            String query = "DELETE FROM consultation WHERE id_consultation = ?";
            preparedStatement = connection.prepareStatement(query);
            preparedStatement.setInt(1, id);

            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
            // Gestion des exceptions
        }
    }

    @Override
    public List<Consultation> afficherListe() {
        Connection connection = DBSingleton.getConnection();
        List<Consultation> consultations = new ArrayList<>();
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;

        try {
            String query = "SELECT c.id_consultation, c.date_consultation, c.id_patient, c.id_medecin, " +
                    "p.nom AS nom_patient, p.prenom AS prenom_patient, " +
                    "m.nom AS nom_medecin, m.prenom AS prenom_medecin " +
                    "FROM consultation c " +
                    "JOIN patient p ON c.id_patient = p.id_patient " +
                    "JOIN medecin m ON c.id_medecin = m.id_medecin";
            preparedStatement = connection.prepareStatement(query);
            resultSet = preparedStatement.executeQuery();

            while (resultSet.next()) {
                Consultation consultation = new Consultation();
                consultation.setIdConsultation(resultSet.getInt("id_consultation"));
                consultation.setDateConsultation(resultSet.getDate("date_consultation"));

                // Création du patient avec son ID
                Patient patient = new Patient();
                patient.setIdPatient(resultSet.getInt("id_patient"));
                patient.setNom(resultSet.getString("nom_patient"));
                patient.setPrenom(resultSet.getString("prenom_patient"));

                // Création du médecin avec son ID
                Medecin medecin = new Medecin();
                medecin.setIdMedecin(resultSet.getInt("id_medecin"));
                medecin.setNom(resultSet.getString("nom_medecin"));
                medecin.setPrenom(resultSet.getString("prenom_medecin"));

                consultation.setPatient(patient);
                consultation.setMedecin(medecin);

                consultations.add(consultation);
            }
        } catch (SQLException e) {
            e.printStackTrace();
            // Gestion des exceptions
        }

        return consultations;
    }
    private boolean patientExists(int idPatient) {
        Connection connection = DBSingleton.getConnection();
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;

        try {
            String query = "SELECT id_patient FROM patient WHERE id_patient = ?";
            preparedStatement = connection.prepareStatement(query);
            preparedStatement.setInt(1, idPatient);
            resultSet = preparedStatement.executeQuery();

            return resultSet.next();  // La méthode renvoie true si le patient existe, sinon false
        } catch (SQLException e) {
            e.printStackTrace();
            // Gestion des exceptions
            return false;
        }
    }

    private boolean medecinExists(int idMedecin) {
        Connection connection = DBSingleton.getConnection();
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;

        try {
            String query = "SELECT id_medecin FROM medecin WHERE id_medecin = ?";
            preparedStatement = connection.prepareStatement(query);
            preparedStatement.setInt(1, idMedecin);
            resultSet = preparedStatement.executeQuery();

            return resultSet.next();  // La méthode renvoie true si le médecin existe, sinon false
        } catch (SQLException e) {
            e.printStackTrace();
            // Gestion des exceptions
            return false;
        }
    }

}
