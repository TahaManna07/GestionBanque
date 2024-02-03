package ma.enset.examenjdbcfxsdia.dao.entities;

import java.io.Serializable;

public class Joueur {
    private int id;
    private String nom;
    private String position;
    private int numero;
    private Equipe equipe;

    public Joueur() {
    }

    public Joueur(String nom, String position, int numero, Equipe equipe) {
        this.nom = nom;
        this.position = position;
        this.numero = numero;
        this.equipe = equipe;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public String getPosition() {
        return position;
    }

    public void setPosition(String position) {
        this.position = position;
    }

    public int getNumero() {
        return numero;
    }

    public void setNumero(int numero) {
        this.numero = numero;
    }

    public Equipe getEquipe() {
        return equipe;
    }

    public void setEquipe(Equipe equipe) {
        this.equipe = equipe;
    }

    @Override
    public String toString() {
        return "Joueur{" +
                "id=" + id +
                ", nom='" + nom + '\'' +
                ", position='" + position + '\'' +
                ", numero=" + numero +
                ", equipe=" + equipe.getId() +
                '}';
    }
}