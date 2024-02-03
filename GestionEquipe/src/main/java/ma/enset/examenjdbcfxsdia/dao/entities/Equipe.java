package ma.enset.examenjdbcfxsdia.dao.entities;

import java.io.Serializable;

public class Equipe  implements Serializable{
    private int id;
    private String nom;
    private String ville;

    public Equipe() {
    }

    public Equipe(String nom, String ville) {
        this.nom = nom;
        this.ville = ville;
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

    public String getVille() {
        return ville;
    }

    public void setVille(String ville) {
        this.ville = ville;
    }

    @Override
    public String toString() {
        return "Equipe{" +
                "id=" + id +
                ", nom='" + nom + '\'' +
                ", ville='" + ville + '\'' +
                '}';
    }
}
