package ma.enset.examenjdbcfxsdia.dao;

import ma.enset.examenjdbcfxsdia.dao.entities.Joueur;

public class test3 {
    public static void main(String[] args) {
        Joueur joueurTest = new Joueur();
        joueurTest.setId(1);
        joueurTest.setNom("John Doe");
        joueurTest.setPosition("Attaquant");
        joueurTest.setNumero(10);

        // Créez une instance de JoueurDAO
        JoueurDAO joueurDAO = new JoueurDAOImpl();

        // Testez la méthode printJoueurInfoToFile
        joueurDAO.printJoueurInfoToPDF(joueurTest);
    }
}
