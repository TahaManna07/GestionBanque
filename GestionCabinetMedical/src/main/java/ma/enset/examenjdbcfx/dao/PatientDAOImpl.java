package ma.enset.examenjdbcfx.dao;

import ma.enset.examenjdbcfx.dao.entities.Consultation;
import ma.enset.examenjdbcfx.dao.entities.Medecin;
import ma.enset.examenjdbcfx.dao.entities.Patient;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class PatientDAOImpl implements IPatientDAO {

    @Override
    public void ajouter(Patient p) {
        Connection connection = DBSingleton.getConnection();
        PreparedStatement preparedStatement = null;

        try {

            preparedStatement = connection.prepareStatement("INSERT INTO patient (nom, prenom, cin, telephone, email, date_naissance) VALUES (?, ?, ?, ?, ?, ?)");
                preparedStatement.setString(1, p.getNom());
                preparedStatement.setString(2, p.getPrenom());
                preparedStatement.setString(3, p.getCin());
                preparedStatement.setString(4, p.getTelephone());
                preparedStatement.setString(5, p.getEmail());

                // Vérifiez si la date de naissance n'est pas null avant de l'ajouter
                if (p.getDateNaissance() != null) {
                    preparedStatement.setDate(6, new java.sql.Date(p.getDateNaissance().getTime()));
                } else {
                    preparedStatement.setNull(6, Types.DATE);
                }

                preparedStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
            // Gestion des exceptions
        }
    }



    public void supprimer(Integer idPatient) {
        Connection connection = DBSingleton.getConnection();
        PreparedStatement preparedStatement = null;

        try {
            // Supprimer les consultations associées au patient
            String deleteConsultationsQuery = "DELETE FROM consultation WHERE id_patient = ?";
            preparedStatement = connection.prepareStatement(deleteConsultationsQuery);
            preparedStatement.setInt(1, idPatient);
            preparedStatement.executeUpdate();

            // Supprimer le patient
            String deletePatientQuery = "DELETE FROM patient WHERE id_patient = ?";
            preparedStatement = connection.prepareStatement(deletePatientQuery);
            preparedStatement.setInt(1, idPatient);
            preparedStatement.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
            // Gestion des exceptions
        }
    }


    @Override
    public List<Patient> afficherListe() {
        Connection connection = DBSingleton.getConnection();
        List<Patient> patients = new ArrayList<>();
        Statement statement = null;
        ResultSet resultSet = null;

        try {
            statement = connection.createStatement();
            resultSet = statement.executeQuery("SELECT * FROM patient");

            while (resultSet.next()) {
                Patient patient = new Patient();
                patient.setIdPatient(resultSet.getInt("id_patient"));
                patient.setNom(resultSet.getString("nom"));
                patient.setPrenom(resultSet.getString("prenom"));
                patient.setCin(resultSet.getString("cin"));
                patient.setTelephone(resultSet.getString("telephone"));
                patient.setEmail(resultSet.getString("email"));
                patient.setDateNaissance(resultSet.getDate("date_naissance"));

                patients.add(patient);
            }
        } catch (SQLException e) {
            e.printStackTrace();
            // Gestion des exceptions
        }

        return patients;
    }

    @Override
    public List<Consultation> getConsultationsPatient(int idPatient) {
        Connection connection = DBSingleton.getConnection();
        List<Consultation> consultations = new ArrayList<>();
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;

        try {
            String query = "SELECT c.id_consultation, c.date_consultation, m.id_medecin, m.nom AS nom_medecin, m.prenom AS prenom_medecin, p.id_patient " +
                    "FROM consultation c " +
                    "INNER JOIN medecin m ON c.id_medecin = m.id_medecin " +
                    "INNER JOIN patient p ON c.id_patient = p.id_patient " +
                    "WHERE c.id_patient = ?";
            preparedStatement = connection.prepareStatement(query);
            preparedStatement.setInt(1, idPatient);

            resultSet = preparedStatement.executeQuery();

            while (resultSet.next()) {
                Consultation consultation = new Consultation();
                consultation.setIdConsultation(resultSet.getInt("id_consultation"));
                consultation.setDateConsultation(resultSet.getDate("date_consultation"));

                // Création de l'objet Medecin
                Medecin medecin = new Medecin();
                medecin.setIdMedecin(resultSet.getInt("id_medecin"));
                medecin.setNom(resultSet.getString("nom_medecin"));
                medecin.setPrenom(resultSet.getString("prenom_medecin"));

                // Création de l'objet Patient
                Patient patient = new Patient();
                patient.setIdPatient(resultSet.getInt("id_patient"));

                consultation.setMedecin(medecin);
                consultation.setPatient(patient); // Assurez-vous que cette méthode est définie dans la classe Consultation
                consultations.add(consultation);
            }
        } catch (SQLException e) {
            e.printStackTrace();
            // Gestion des exceptions
        }

        return consultations;
    }


    @Override
    public List<Patient> rechercherParMotCle(String motCle) {
        Connection connection = DBSingleton.getConnection();
        List<Patient> patients = new ArrayList<>();
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;

        try {
            String query = "SELECT * FROM patient WHERE nom LIKE ? OR prenom LIKE ? OR email LIKE ? OR cin LIKE ? OR telephone LIKE ? OR date_naissance LIKE ?   ";
            preparedStatement = connection.prepareStatement(query);
            preparedStatement.setString(1, "%" + motCle + "%");
            preparedStatement.setString(2, "%" + motCle + "%");
            preparedStatement.setString(3, "%" + motCle + "%");
            preparedStatement.setString(4, "%" + motCle + "%");
            preparedStatement.setString(5, "%" + motCle + "%");
            preparedStatement.setString(6, "%" + motCle + "%");


            resultSet = preparedStatement.executeQuery();

            while (resultSet.next()) {
                Patient patient = new Patient();
                patient.setIdPatient(resultSet.getInt("id_patient"));
                patient.setNom(resultSet.getString("nom"));
                patient.setPrenom(resultSet.getString("prenom"));
                patient.setCin(resultSet.getString("cin"));
                patient.setTelephone(resultSet.getString("telephone"));
                patient.setEmail(resultSet.getString("email"));
                patient.setDateNaissance(resultSet.getDate("date_naissance"));

                patients.add(patient);
            }
        } catch (SQLException e) {
            e.printStackTrace();
            // Gestion des exceptions
        }

        return patients;
    }
}
