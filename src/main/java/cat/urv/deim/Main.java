package cat.urv.deim;

import cat.urv.deim.exceptions.ElementNoTrobat;
import cat.urv.deim.io.FileLoader;
import cat.urv.deim.io.FileLoaderMovies;
import cat.urv.deim.io.FileLoaderTests;
import cat.urv.deim.models.*;

import java.util.List;


public class Main {
    public static void main(String[] args) throws ElementNoTrobat {




        //String rutaFitxer = "movie_users_20_1000.txt"; my computer doesn't have enough power to load all the file
        MultiLlista<Pelicula, Usuari, Integer> multiLlista = new MultiLlista<>();

        //Dataset de proves
       //FileLoaderTests.carregarDades(multiLlista, "dummyDataset.txt");

        //Dataset combinat
        FileLoaderMovies.carregarPellicules("movies.txt");
        FileLoaderMovies.carregarDades(multiLlista, "movie_users_10_20.txt");



        Recomanador recomanador = new Recomanador(multiLlista);

        try {
            //Incrementar multiLlista.getClausConjuntB()[2] per mirar altres usuaris
            LlistaDoblementEncadenada<Pelicula> recomanacions = recomanador.recomanarPelis((Usuari) (multiLlista.getClausConjuntB()[2]));

            int contador = 1;
            for (Object p : recomanacions.elements()) {
                if (contador <4) {
                    System.out.println(contador +" -> "+ ((Pelicula)p).getTitol());
                    contador++;
                } else {
                    break;
                }
            }
            System.out.println("Breu explicació del criteri:\n Hem calculat una mesura de similitud amb tots els altres usuaris.\n Per cada pel·lícula que l'usuari no ha vist encara, hem predit la seva valoració com a mitjana de les valoracions d'aquells usuaris similars que han vist la pel·lícula.\n Llavors, recomanarem les pel·lícules amb les valoracions predites més altes.  " );

        } catch (Exception e) {
            System.out.println("An error occurred: " + e.getMessage());
            e.printStackTrace();
        }


    }

    }

