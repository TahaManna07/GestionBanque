package ma.enset.examenjdbcfxsdia.service;

import ma.enset.examenjdbcfxsdia.dao.entities.Equipe;
import ma.enset.examenjdbcfxsdia.dao.entities.Joueur;

import java.util.List;

public interface IEquipeJoueurService {
    List<Joueur> getAllJoueurs();

    Joueur getJoueurById(int id);

    void addJoueur(Joueur joueur);

    void updateJoueur(Joueur joueur);

    void deleteJoueur(int id);

    List<Equipe> getAllEquipes();

    Equipe getEquipeById(int id);

    void addEquipe(Equipe equipe);

    void updateEquipe(Equipe equipe);

    void deleteEquipe(int id);

    List<Joueur> getJoueursByEquipe(Equipe equipe);

    List<Equipe> searchEquipesByKeyword(String keyword);
    public void printJoueurInfoToPDF(Joueur joueur);
}
