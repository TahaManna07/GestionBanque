package ma.enset.examenjdbcfxsdia.dao;

import ma.enset.examenjdbcfxsdia.dao.entities.Equipe;
import ma.enset.examenjdbcfxsdia.dao.entities.Joueur;

import java.util.List;

public interface EquipeDAO extends DAO<Equipe>{
    List<Equipe> searchByKeyword(String keyword);
    List<Joueur> getJoueursByEquipe(Equipe equipe);
}
