package cat.urv.deim.models;
import cat.urv.deim.exceptions.ElementNoTrobat;

import java.util.*;


public class Recomanador {

    private MultiLlista<Pelicula, Usuari, Integer> graf;

    public Recomanador(MultiLlista<Pelicula, Usuari, Integer> graf) {
        this.graf = graf;
    }

    private void ordenarPelisYValoracions(Object[] auxPelicula, Object[] auxValoracions) {
        for (int i = 0; i < auxValoracions.length - 1; i++) {
            for (int j = 0; j < auxValoracions.length - i - 1; j++) {
                if ((Double) auxValoracions[j] < (Double) auxValoracions[j + 1]) {
                    // intercambia valoracions[j] y valoracions[j+1]
                    Object temp = auxValoracions[j];
                    auxValoracions[j] = auxValoracions[j + 1];
                    auxValoracions[j + 1] = temp;

                    // intercambia pelicula[j] y pelicula[j+1]
                    temp = auxPelicula[j];
                    auxPelicula[j] = auxPelicula[j + 1];
                    auxPelicula[j + 1] = temp;
                }
            }
        }
    }

    public LlistaDoblementEncadenada<Pelicula> recomanarPelis(Usuari usuari) throws Exception {

        // Creem un mapa per guardar les puntuacions predites de les pel·lícules
        HashMapIndirecte<Pelicula, Double> puntuacions = new HashMapIndirecte<>();

        // Obtenim la llista d'usuaris que han valorat pel·lícules
        Object[] usuarisObject = graf.getClausConjuntB();
        LlistaDoblementEncadenada<Usuari> usuarisList = new LlistaDoblementEncadenada<>();

        for (Object obj : usuarisObject) {
            if (obj instanceof Usuari) {
                usuarisList.inserir((Usuari) obj);
            }
        }

        Object[] usuaris = usuarisList.elements();

        // Per cada usuari, calculem la similitud amb l'usuari actual i actualitzem les puntuacions predites
        for (Object obj : usuaris) {
            Usuari u = (Usuari) obj;

            // Si l'usuari és el mateix que l'usuari que es compara, es salta l'iteració
            if (u.equals(usuari)) {
                System.out.println("Respecte l'usuari: "+usuari.getNom()+":");
                continue;
            }


            double similitud = calcularSimilitud(usuari, u);

            //System.out.println("Similitud de l'usuari: "+u.getNom()+" amb l'usuari: "+usuari.getNom()+" ---> "+similitud);
            List<Pelicula> pelis = graf.columna(u);
            for (Pelicula p : pelis) {
                double valoracio = graf.element(p, u);
                puntuacions.inserir(p, puntuacions.getOrDefault(p, 0.0) + similitud * valoracio);
            }
        }

        // Ordenem les pel·lícules segons les puntuacions predites i retornem les tres millors
        LlistaDoblementEncadenadaOrdenada<Pelicula> recomanacions = puntuacions.keySet();

        //recomanacions.sort((p1, p2) -> Double.compare(puntuacions.get(p2), puntuacions.get(p1)));
        Object[] auxPelicula = puntuacions.obtenirClaus();
        Object[] auxValoracions = puntuacions.obtenirValors();

        //funcio auxiliar per ordenar el output
        ordenarPelisYValoracions(auxPelicula, auxValoracions);

        LlistaDoblementEncadenada<Pelicula> resultat = new LlistaDoblementEncadenada<>();
        List<Pelicula> peliculesVistes = graf.columna(usuari);

        int count = 0;
        for (Object obj : auxPelicula) {
            if (obj instanceof Pelicula) {
                Pelicula pelicula = (Pelicula) obj;
                if (!peliculesVistes.contains(pelicula)) {
                    resultat.inserir(pelicula);
                    count++;
                    if (count == 3) {
                        break; // Se han añadido las tres películas, salir del bucle
                    }
                }
            }
        }

        return resultat;

    }

    private double calcularSimilitud(Usuari u1, Usuari u2) throws ElementNoTrobat {
        double sumX = 0.0, sumY = 0.0, sumXY = 0.0;
        double sumXSq = 0.0, sumYSq = 0.0;
        int n = 0;

        List<Pelicula> peliculesVistesU1 = graf.columna(u1);
        List<Pelicula> peliculesVistesU2 = graf.columna(u2);

        for (Pelicula p1 : peliculesVistesU1) {
            if (peliculesVistesU2.contains(p1) && graf.existeix(p1, u2)) {
                n++;
                double x = graf.element(p1, u1);

                double y = graf.element(p1, u2);

                sumXY += x * y;
                sumX += x;
                sumY += y;
                sumXSq += x * x;
                sumYSq += y * y;
            }
        }

        if (n == 0) return 0.0;

        double denominator = Math.sqrt(sumXSq - (sumX * sumX) / n) *
                Math.sqrt(sumYSq - (sumY * sumY) / n);

        if (denominator == 0) return 0.0;

        return (sumXY - ((sumX * sumY) / n)) / denominator;
    }


}

