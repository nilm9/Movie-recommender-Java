package cat.urv.deim.io;

import cat.urv.deim.exceptions.ElementNoTrobat;
import cat.urv.deim.exceptions.ElementRepetit;

import cat.urv.deim.models.HashMapIndirecte;
import cat.urv.deim.models.MultiLlista;
import cat.urv.deim.models.Pelicula;
import cat.urv.deim.models.Usuari;

import java.io.*;
import java.util.Scanner;

public class FileLoaderMovies {

    static HashMapIndirecte<String,String> auxMovieID = new HashMapIndirecte<>();
    static HashMapIndirecte<Integer, String>movieMap = new HashMapIndirecte<>();

    public static void carregarPellicules( String rutaFitxer) {
        try (BufferedReader lector = new BufferedReader(new FileReader(rutaFitxer))) {
            String linea;
            while ((linea = lector.readLine()) != null) {
                String[] dades = linea.split("###");
                if (dades.length == 3) {
                    int idPelicula = Integer.parseInt(dades[0]);
                    if(!dades[1].equals("NULL")){
                     int any = Integer.parseInt(dades[1]);
                    }
                    String titol = dades[2];
                    Pelicula pelicula = new Pelicula(idPelicula, titol);
                    movieMap.inserir(idPelicula, titol);
                    //graf.inserir(pelicula, new Usuari(), 0); // Fent servir un usuari buit i una valoració inicial de 0
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

   public static void carregarDades(MultiLlista<Pelicula, Usuari, Integer> multiLlista, String rutaFitxer) {

       try {
           File file = new File(rutaFitxer);
           Scanner scanner = new Scanner(file);
           Pelicula pelicula = null;

           while (scanner.hasNextLine()) {
               String line = scanner.nextLine();

               if (line.endsWith(":")) {
                   // This is a movie ID
                   String movieId = line.substring(0, line.length() - 1); // Remove colon at the end
                   int id = Integer.parseInt(movieId);
                   String nom = movieMap.element(id);
                   pelicula = new Pelicula(id, nom);

                   // String movieId = line.substring(0, line.length() - 1); // Remove colon at the end

                   //pelicula = new Pelicula(movieId);
               } else {
                   // This is a rating
                   String[] parts = line.split(",");
                   String userId = parts[0];
                   int score = Integer.parseInt(parts[1]);
                   Usuari usuari = new Usuari(userId);
                   try {
                       multiLlista.inserir(pelicula, usuari, score);
                   } catch (ElementNoTrobat | ElementRepetit e) {
                       System.out.print("Element: "+ pelicula.getTitol()+" repetit.");
                   }
               }
           }
           scanner.close();
       } catch (FileNotFoundException err) {
           System.out.println("An error occurred.");
           err.printStackTrace();
       }

}
}