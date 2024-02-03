package ma.enset.examenjdbcfxsdia.dao;

import ma.enset.examenjdbcfxsdia.dao.entities.Equipe;
import ma.enset.examenjdbcfxsdia.dao.entities.Joueur;

import java.util.List;

public class Test1 {
    public static void main(String[] args) {
        EquipeDAO equipeDAO = new EquipeDAOImpl();

        // Création d'une nouvelle équipe
        Equipe nouvelleEquipe = new Equipe();
        nouvelleEquipe.setNom("Nouvelle Equipe");
        nouvelleEquipe.setVille("Nouvelle Ville");
        //equipeDAO.save(nouvelleEquipe);
        System.out.println("Nouvelle équipe créée : " + nouvelleEquipe);

        // Affichage de toutes les équipes
        List<Equipe> equipes = equipeDAO.findAll();
        System.out.println("Liste de toutes les équipes : ");
        for (Equipe equipe : equipes) {
            System.out.println(equipe);
        }
        // Recherche d'équipes par mot-clé
        String keyword = "R";
        List<Equipe> equipesByKeywork = equipeDAO.searchByKeyword(keyword);
        System.out.println("Résultats de la recherche par mot-clé '" + keyword + "' : ");
        for (Equipe equipe : equipesByKeywork) {
            System.out.println(equipe);
        }
        // Mise à jour d'une équipe existante
        Equipe equipeAUpdater = equipeDAO.findById(5); // Remplacez par l'ID de l'équipe à mettre à jour
        equipeAUpdater.setNom("Nouveau Nom");
        equipeAUpdater.setVille("Nouvelle Ville");
        equipeDAO.update(equipeAUpdater);
        System.out.println("\nÉquipe mise à jour : " + equipeAUpdater);

        /*
        // Affichage de la liste des joueurs pour une équipe
        Equipe equipeAvecJoueurs = equipes.get(0);
        List<Joueur> joueursDeLEquipe = equipeDAO.getJoueursByEquipe(equipeAvecJoueurs);
        System.out.println("Liste des joueurs pour l'équipe " + equipeAvecJoueurs.getNom() + " : ");
        for (Joueur joueur : joueursDeLEquipe) {
            System.out.println(joueur);
        }*/
        System.out.println("********************");
        Equipe equipeAvecJoueursc = equipes.get(1);
        List<Joueur> joueursDeLEquipec = equipeDAO.getJoueursByEquipe(equipeAvecJoueursc);
        System.out.println("Liste des joueurs pour l'équipe " + equipeAvecJoueursc.getNom() + " : ");
        for (Joueur joueur : joueursDeLEquipec) {
            System.out.println(joueur);
        }

        // Suppression d'une équipe
        //equipeDAO.delete(4);
            /*
        List<Equipe> equipesApresSuppression = equipeDAO.findAll();
        System.out.println("Liste après suppression : ");
        for (Equipe equipe : equipesApresSuppression) {
            System.out.println(equipe);
        }*/
        // Mise à jour d'une équipe existante






    }
    }

