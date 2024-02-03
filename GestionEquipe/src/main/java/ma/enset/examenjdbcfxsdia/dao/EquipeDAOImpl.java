package ma.enset.examenjdbcfxsdia.dao;

import ma.enset.examenjdbcfxsdia.dao.entities.Equipe;
import ma.enset.examenjdbcfxsdia.dao.entities.Joueur;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class EquipeDAOImpl implements EquipeDAO {

    @Override
    public List<Equipe> findAll() {
        List<Equipe> equipes = new ArrayList<>();
        Connection connection = DBSingleton.getConnection();
        Statement statement = null;
        ResultSet resultSet = null;

        try {
            statement = connection.createStatement();
            resultSet = statement.executeQuery("SELECT * FROM equipe");

            while (resultSet.next()) {
                Equipe equipe = new Equipe();
                equipe.setId(resultSet.getInt("id"));
                equipe.setNom(resultSet.getString("nom"));
                equipe.setVille(resultSet.getString("ville"));
                equipes.add(equipe);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return equipes;
    }

    @Override
    public Equipe findById(int id) {
        Equipe equipe = null;
        Connection connection = DBSingleton.getConnection();
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;

        try {
            String query = "SELECT * FROM equipe WHERE id = ?";
            preparedStatement = connection.prepareStatement(query);
            preparedStatement.setLong(1, id);
            resultSet = preparedStatement.executeQuery();

            if (resultSet.next()) {
                equipe = new Equipe();
                equipe.setId(resultSet.getInt("id"));
                equipe.setNom(resultSet.getString("nom"));
                equipe.setVille(resultSet.getString("ville"));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return equipe;
    }

    @Override
    public void save(Equipe equipe) {
        Connection connection = DBSingleton.getConnection();
        PreparedStatement preparedStatement = null;

        try {
            String query = "INSERT INTO equipe (nom, ville) VALUES (?, ?)";
            preparedStatement = connection.prepareStatement(query, Statement.RETURN_GENERATED_KEYS);

            preparedStatement.setString(1, equipe.getNom());
            preparedStatement.setString(2, equipe.getVille());


            preparedStatement.executeUpdate();


        } catch (SQLException ee) {
            ee.printStackTrace();
        }
    }

    @Override
    public void delete(int id) {
        Connection connection = DBSingleton.getConnection();
        PreparedStatement preparedStatement = null;

        try {
            // Supprimez d'abord les joueurs liés à l'équipe
            String deleteJoueursQuery = "DELETE FROM joueur WHERE equipe_id = ?";
            preparedStatement = connection.prepareStatement(deleteJoueursQuery);
            preparedStatement.setLong(1, id);
            preparedStatement.executeUpdate();

            // Ensuite, supprimez l'équipe
            String deleteEquipeQuery = "DELETE FROM equipe WHERE id = ?";
            preparedStatement = connection.prepareStatement(deleteEquipeQuery);
            preparedStatement.setLong(1, id);
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void update(Equipe equipe) {
        Connection connection = DBSingleton.getConnection();
        PreparedStatement preparedStatement = null;

        try {
            String query = "UPDATE equipe SET nom = ?, ville = ? WHERE id = ?";
            preparedStatement = connection.prepareStatement(query);

            preparedStatement.setString(1, equipe.getNom());
            preparedStatement.setString(2, equipe.getVille());
            preparedStatement.setLong(3, equipe.getId());

            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public List<Joueur> getJoueursByEquipe(Equipe equipe) {
        List<Joueur> joueurs = new ArrayList<>();
        Connection connection = DBSingleton.getConnection();
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;

        try {
                // Utilisation de INNER JOIN pour récupérer les joueurs associés à l'équipe
                String query = "SELECT joueur.* FROM joueur " +
                        "INNER JOIN equipe ON joueur.equipe_id = equipe.id " +
                        "WHERE equipe.id = ?";
                preparedStatement = connection.prepareStatement(query);
                preparedStatement.setInt(1, equipe.getId());
                resultSet = preparedStatement.executeQuery();

                while (resultSet.next()) {
                    Joueur joueur = new Joueur();
                    joueur.setId(resultSet.getInt("id"));
                    joueur.setNom(resultSet.getString("nom"));
                    joueur.setPosition(resultSet.getString("position"));
                    joueur.setNumero(resultSet.getInt("numero"));
                    Equipe equipe1 = new Equipe();
                    equipe1.setId(resultSet.getInt("equipe_id"));
                    joueur.setEquipe(equipe1);

                    // Affichez les résultats de la requête pour déboguer

                    joueurs.add(joueur);
                }

        } catch (SQLException e) {
            e.printStackTrace();
            // Gestion des exceptions
        }
        return joueurs;
    }


    @Override
    public List<Equipe> searchByKeyword(String keyword) {
        List<Equipe> equipes = new ArrayList<>();
        Connection connection = DBSingleton.getConnection();
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;

        try {
            String query = "SELECT * FROM equipe WHERE id LIKE ? OR nom LIKE ? OR ville LIKE ?";
            preparedStatement = connection.prepareStatement(query);

            for (int i = 1; i <= 3; i++) {
                preparedStatement.setString(i, "%" + keyword + "%");
            }

            resultSet = preparedStatement.executeQuery();

            while (resultSet.next()) {
                Equipe equipe = new Equipe();
                equipe.setId(resultSet.getInt("id"));
                equipe.setNom(resultSet.getString("nom"));
                equipe.setVille(resultSet.getString("ville"));
                equipes.add(equipe);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return equipes;
    }
}
