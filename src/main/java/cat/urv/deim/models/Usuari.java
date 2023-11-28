package cat.urv.deim.models;

import java.util.Objects;

public class Usuari implements Comparable<Usuari> {
    private String nom;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Usuari usuari = (Usuari) o;
        return usuari.getNom().equals(nom);
    }

    @Override
    public int hashCode() {
        return Objects.hash(nom);
    }

    public Usuari(String nom) {
        this.nom = nom;
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    @Override
    public int compareTo(Usuari o) {
        return nom.compareTo(o.getNom());
    }

}
