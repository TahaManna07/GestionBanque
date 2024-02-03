package ma.enset.examenjdbcfxsdia.service;

import ma.enset.examenjdbcfxsdia.dao.EquipeDAOImpl;
import ma.enset.examenjdbcfxsdia.dao.entities.Equipe;
import ma.enset.examenjdbcfxsdia.dao.entities.Joueur;

import java.util.List;

public class AppConsole {
    public static void main(String[] args) {
                // Création de l'instance du service

                IEquipeJoueurService equipeJoueurService = new IEquipeJoueurServiceImpl(new EquipeDAOImpl());
/*
                // Test des fonctionnalités pour les joueurs
                System.out.println("----- Joueurs -----");

                // Affichage de tous les joueurs
                System.out.println("Tous les joueurs :");
                List<Joueur> joueurs = equipeJoueurService.getAllJoueurs();
                for (Joueur joueur : joueurs) {
                    System.out.println(joueur);
                }

                // Ajout d'un nouveau joueur
                Joueur nouveauJoueur = new Joueur();
                nouveauJoueur.setNom("Taha");
                nouveauJoueur.setPosition("Milieu ");
                nouveauJoueur.setNumero(10);

                Equipe equipe = new Equipe();
                equipe.setId(1);// Remplacez par l'ID de l'équipe associée
                nouveauJoueur.setEquipe(equipe);

                equipeJoueurService.addJoueur(nouveauJoueur);
                System.out.println("\nNouveau joueur ajouté : " + nouveauJoueur);

                // Mise à jour du joueur ajouté
                nouveauJoueur.setNom("Souhail ");
                equipeJoueurService.updateJoueur(nouveauJoueur);
                System.out.println("\nJoueur mis à jour : " + nouveauJoueur);

                // Suppression du joueur ajouté
                equipeJoueurService.deleteJoueur(14);*/
        // Test des fonctionnalités pour les équipes
            System.out.println("\n----- Équipes -----");

        // Affichage de toutes les équipes
            System.out.println("Toutes les équipes :");
        List<Equipe> equipes = equipeJoueurService.getAllEquipes();
        for (Equipe equipe : equipes) {
            System.out.println(equipe);
        }

        // Ajout d'une nouvelle équipe
        Equipe nouvelleEquipe = new Equipe();
        nouvelleEquipe.setNom("Chabab Media");
        nouvelleEquipe.setVille("Media Ville");

        equipeJoueurService.addEquipe(nouvelleEquipe);
        //System.out.println("\nNouvelle équipe ajoutée : " + nouvelleEquipe);

        // Mise à jour de l'équipe ajoutée
        nouvelleEquipe.setNom("Radchad Barnoussi");
        equipeJoueurService.updateEquipe(nouvelleEquipe);
        System.out.println("\nÉquipe mise à jour : " + nouvelleEquipe);

        // Suppression de l'équipe ajoutée

        equipeJoueurService.deleteEquipe(5);

        // Recherche des équipes par mot-clé
        System.out.println("\nRecherche des équipes par mot-clé (par exemple, 'FC') :");
        List<Equipe> equipesTrouvees = equipeJoueurService.searchEquipesByKeyword("Edsdsd");
        for (Equipe equipe : equipesTrouvees) {
            System.out.println(equipe);
        }

        // Affichage des joueurs d'une équipe spécifique
        Equipe equipePourJoueurs = equipeJoueurService.getEquipeById(6); // Remplacez par l'ID de l'équipe
        System.out.println("\nJoueurs de l'équipe avec l'ID " + equipePourJoueurs.getId() + " :");
        List<Joueur> joueursDeLEquipe = equipeJoueurService.getJoueursByEquipe(equipePourJoueurs);
        for (Joueur joueur : joueursDeLEquipe) {
            System.out.println(joueur);
        }



            }
        }

