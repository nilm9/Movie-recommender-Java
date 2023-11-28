package cat.urv.deim.io;
import cat.urv.deim.exceptions.*;
import cat.urv.deim.models.*;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;


public class FileLoaderTests {
    public static void carregarDades(MultiLlista<Pelicula, Usuari, Integer> multiLlista, String rutaFitxer) {
        try (BufferedReader br = new BufferedReader(new FileReader(rutaFitxer))) {
            String linia;
            while ((linia = br.readLine()) != null) {
                String[] valors = linia.split(",");
                String peliculaName = valors[0];
                String usuariName = valors[1];
                int valoracio = Integer.parseInt(valors[2]);

                try {
                    Pelicula pelicula = new Pelicula(peliculaName);
                    Usuari usuari = new Usuari(usuariName);
                    multiLlista.inserir(pelicula, usuari, valoracio);
                } catch (ElementRepetit | ElementNoTrobat e) {
                    e.printStackTrace();
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}