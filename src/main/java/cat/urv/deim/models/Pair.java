package cat.urv.deim.models;

public class Pair {
    private final Pelicula pelicula;
    private final double valoracion;

    public Pair(Pelicula pelicula, double valoracion) {
        this.pelicula = pelicula;
        this.valoracion = valoracion;
    }

    public Pelicula getPelicula() {
        return pelicula;
    }

    public double getValoracion() {
        return valoracion;
    }
}