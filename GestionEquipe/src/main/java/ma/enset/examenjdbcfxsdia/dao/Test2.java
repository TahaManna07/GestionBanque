package ma.enset.examenjdbcfxsdia.dao;

import ma.enset.examenjdbcfxsdia.dao.entities.Equipe;
import ma.enset.examenjdbcfxsdia.dao.entities.Joueur;

import java.util.List;

public class Test2 {
    public static void main(String[] args) {
        JoueurDAO joueurDAO = new JoueurDAOImpl();
        List<Joueur> jouers = joueurDAO.findAll();
        System.out.println("Liste des départements :");
        for (Joueur t : jouers) {
            System.out.println(t);
        }
        System.out.println("*************");
        Joueur nouveauJoueur = new Joueur();
        nouveauJoueur.setNom("NouveauJoueur");
        nouveauJoueur.setPosition("NouvellePosition");
        nouveauJoueur.setNumero(99);
        Equipe equipe = new Equipe();
        equipe.setId(5);
        nouveauJoueur.setEquipe(equipe);
        //joueurDAO.save(nouveauJoueur);
        //System.out.println("\nNouveau joueur ajouté : " + nouveauJoueur);
            /*
        int joueurIdToDelete = 1; // Remplacez par l'id du joueur que vous voulez supprimer
        //joueurDAO.delete(joueurIdToDelete);
        //System.out.println("\nJoueur supprimé avec l'id : " + joueurIdToDelete);
        // Modifier le joueur avec l'ID choisi
        // Trouver un joueur par ID
        Joueur joueurTrouve = joueurDAO.findById(8);

        if (joueurTrouve != null) {
            System.out.println("\nInformations du joueur trouvé : " + joueurTrouve);
        } else {
            System.out.println("\nJoueur avec l'ID " + joueurTrouve + " non trouvé.");
        }
        */
        Joueur joueurMaj = new Joueur();
        joueurMaj.setId(2);  // ID du joueur que vous souhaitez mettre à jour
        joueurMaj.setNom("SosoauNom");
        joueurMaj.setPosition("NouvellePosition");
        joueurMaj.setNumero(99);

        // Création d'une instance de JoueurDAO

        // Appel de la méthode update
        joueurDAO.update(joueurMaj);
    }
}

