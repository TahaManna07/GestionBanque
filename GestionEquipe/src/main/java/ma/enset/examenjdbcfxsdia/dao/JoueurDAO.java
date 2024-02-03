package ma.enset.examenjdbcfxsdia.dao;

import ma.enset.examenjdbcfxsdia.dao.entities.Equipe;
import ma.enset.examenjdbcfxsdia.dao.entities.Joueur;

import java.util.List;

public interface JoueurDAO extends  DAO<Joueur>{
    void printJoueurInfoToPDF(Joueur joueur);

}
