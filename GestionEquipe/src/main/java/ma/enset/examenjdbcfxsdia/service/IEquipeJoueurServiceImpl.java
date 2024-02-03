package ma.enset.examenjdbcfxsdia.service;

import ma.enset.examenjdbcfxsdia.dao.EquipeDAO;
import ma.enset.examenjdbcfxsdia.dao.EquipeDAOImpl;
import ma.enset.examenjdbcfxsdia.dao.JoueurDAO;
import ma.enset.examenjdbcfxsdia.dao.JoueurDAOImpl;
import ma.enset.examenjdbcfxsdia.dao.entities.Equipe;
import ma.enset.examenjdbcfxsdia.dao.entities.Joueur;

import java.util.List;

public class IEquipeJoueurServiceImpl implements IEquipeJoueurService {
      JoueurDAO joueurDAO = new JoueurDAOImpl();
      EquipeDAO equipeDAO = new EquipeDAOImpl();

    public IEquipeJoueurServiceImpl(EquipeDAOImpl equipeDAO) {
    }

    public IEquipeJoueurServiceImpl(JoueurDAO joueurDAO) {
        this.joueurDAO = joueurDAO;
    }


    @Override
    public List<Joueur> getAllJoueurs() {
        return joueurDAO.findAll();
    }

    @Override
    public Joueur getJoueurById(int id) {
        return joueurDAO.findById(id);
    }

    @Override
    public void addJoueur(Joueur joueur) {
        joueurDAO.save(joueur);
    }

    @Override
    public void updateJoueur(Joueur joueur) {
        joueurDAO.update(joueur);
    }

    @Override
    public void deleteJoueur(int id) {
        joueurDAO.delete(id);
    }

    @Override
    public List<Equipe> getAllEquipes() {
        return equipeDAO.findAll();
    }

    @Override
    public Equipe getEquipeById(int id) {
        return equipeDAO.findById(id);
    }

    @Override
    public void addEquipe(Equipe equipe) {
        equipeDAO.save(equipe);
    }

    @Override
    public void updateEquipe(Equipe equipe) {
        equipeDAO.update(equipe);
    }

    @Override
    public void deleteEquipe(int id) {
        equipeDAO.delete(id);
    }

    @Override
    public List<Joueur> getJoueursByEquipe(Equipe equipe) {
        return equipeDAO.getJoueursByEquipe(equipe);
    }

    @Override
    public List<Equipe> searchEquipesByKeyword(String keyword) {
        return equipeDAO.searchByKeyword(keyword);
    }

    @Override
    public void printJoueurInfoToPDF(Joueur joueur) {
        joueurDAO.printJoueurInfoToPDF(joueur);
    }
}
