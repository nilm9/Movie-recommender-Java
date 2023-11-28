package cat.urv.deim.models;

import java.util.Objects;

public class Pelicula implements Comparable<Pelicula> {
    private int id;

    private String titol;
    private double puntuacioTotal;
    private int numPuntuacions;

    public Pelicula(int id, String titol) {
        this.id = id;
        this.titol = titol;
        this.puntuacioTotal = 0.0;
        this.numPuntuacions = 0;
    }

    public Pelicula( String titol) {

        this.titol = titol;

    }

    public int getId() {
        return id;
    }


    public String getTitol() {
        return titol;
    }

    public double getPuntuacioMitjana() {
        if (numPuntuacions == 0) {
            return 0.0; // Retornar 0 si no hi ha puntuacions per evitar divisions per zero
        }
        return puntuacioTotal / numPuntuacions;
    }

    public void afegirPuntuacio(double puntuacio) {
        puntuacioTotal += puntuacio;
        numPuntuacions++;
    }

    @Override
    public int compareTo(Pelicula o) {
        if(titol!=null)
            return titol.compareTo(o.getTitol());
        System.out.println("La pelicula amb id: "+id+" no té títol.");
        return -1;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Pelicula pelicula = (Pelicula) o;
        if (id!=0)
            return pelicula.getId() == id;
        return titol.equals(((Pelicula) o).getTitol());
    }

    @Override
    public int hashCode() {
        return Objects.hash(titol);
    }

    @Override
    public String toString() {
        return titol;
    }
}
