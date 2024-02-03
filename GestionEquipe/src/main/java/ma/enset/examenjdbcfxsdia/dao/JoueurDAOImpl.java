package ma.enset.examenjdbcfxsdia.dao;

import ma.enset.examenjdbcfxsdia.dao.entities.Equipe;
import ma.enset.examenjdbcfxsdia.dao.entities.Joueur;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class JoueurDAOImpl implements JoueurDAO {

    @Override
    public List<Joueur> findAll() {
        List<Joueur> joueurs = new ArrayList<>();
        Connection connection = DBSingleton.getConnection();
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;

        try {
            String query = "SELECT joueur.*, equipe.nom AS nom_equipe " +
                    "FROM joueur " +
                    "INNER JOIN equipe ON joueur.equipe_id = equipe.id";
            preparedStatement = connection.prepareStatement(query);
            resultSet = preparedStatement.executeQuery();

            while (resultSet.next()) {
                Joueur joueur = new Joueur();
                joueur.setId(resultSet.getInt("id"));
                joueur.setNom(resultSet.getString("nom"));
                joueur.setPosition(resultSet.getString("position"));
                joueur.setNumero(resultSet.getInt("numero"));

                Equipe equipe = new Equipe();
                equipe.setId(resultSet.getInt("equipe_id"));
                equipe.setNom(resultSet.getString("nom_equipe"));  // Settez le nom de l'équipe
                joueur.setEquipe(equipe);

                joueurs.add(joueur);
            }

        } catch (SQLException e) {
            e.printStackTrace();
            // Gestion des exceptions
        }

        return joueurs;
    }




    @Override
    public Joueur findById(int id) {
        Joueur joueur = null;
        Connection connection = DBSingleton.getConnection();
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;

        try {
            String query = "SELECT joueur.*, equipe.nom AS nom_equipe " +
                    "FROM joueur " +
                    "INNER JOIN equipe ON joueur.equipe_id = equipe.id " +
                    "WHERE joueur.id = ?";
            preparedStatement = connection.prepareStatement(query);
            preparedStatement.setInt(1, id);
            resultSet = preparedStatement.executeQuery();

            if (resultSet.next()) {
                joueur = new Joueur();
                joueur.setId(resultSet.getInt("id"));
                joueur.setNom(resultSet.getString("nom"));
                joueur.setPosition(resultSet.getString("position"));
                joueur.setNumero(resultSet.getInt("numero"));

                Equipe equipe = new Equipe();
                equipe.setId(resultSet.getInt("equipe_id"));
                equipe.setNom(resultSet.getString("nom_equipe"));  // Settez le nom de l'équipe
                joueur.setEquipe(equipe);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return joueur;
    }

    @Override
    public void save(Joueur joueur) {
        Connection connection = DBSingleton.getConnection();
        PreparedStatement preparedStatement = null;

        try {
            String query = "INSERT INTO joueur (nom, position, numero,equipe_id) VALUES (?, ?, ?,?)";
            preparedStatement = connection.prepareStatement(query, Statement.RETURN_GENERATED_KEYS);

            preparedStatement.setString(1, joueur.getNom());
            preparedStatement.setString(2, joueur.getPosition());
            preparedStatement.setInt(3, joueur.getNumero());
            preparedStatement.setInt(4,joueur.getEquipe().getId());

            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void delete(int id) {
        Connection connection = DBSingleton.getConnection();
        PreparedStatement preparedStatement = null;

        try {
            String query = "DELETE FROM joueur WHERE id = ?";
            preparedStatement = connection.prepareStatement(query);
            preparedStatement.setInt(1, id);
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void update(Joueur joueur) {
        Connection connection = DBSingleton.getConnection();
        PreparedStatement preparedStatement = null;

        try {
            String query = "UPDATE joueur SET nom = ?, position = ?, numero = ? WHERE id = ?";
            preparedStatement = connection.prepareStatement(query);

            preparedStatement.setString(1, joueur.getNom());
            preparedStatement.setString(2, joueur.getPosition());
            preparedStatement.setInt(3, joueur.getNumero());
            preparedStatement.setInt(4,joueur.getId());
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void printJoueurInfoToPDF(Joueur joueur) {
        // Spécifiez le chemin du fichier texte
        String filePath = "C:\\Users\\Taha Manna\\Desktop\\" + joueur.getNom() + "_info.txt";

        try (BufferedWriter writer = new BufferedWriter(new FileWriter(new File(filePath)))) {
            // Écrivez les informations du joueur dans le fichier texte
            writer.write("Informations du joueur :\n");
            writer.write("ID : " + joueur.getId() + "\n");
            writer.write("Nom : " + joueur.getNom() + "\n");
            writer.write("Position : " + joueur.getPosition() + "\n");
            writer.write("Numéro : " + joueur.getNumero() + "\n");

            System.out.println("Les informations du joueur ont été écrites dans le fichier avec succès.");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
