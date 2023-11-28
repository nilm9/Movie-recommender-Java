package cat.urv.deim.io;
import cat.urv.deim.exceptions.ElementNoTrobat;
import cat.urv.deim.exceptions.ElementRepetit;
import cat.urv.deim.models.*;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;


public class FileLoader {

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
                    pelicula = new Pelicula(movieId);
                } else {
                    // This is a rating
                    String[] parts = line.split(",");
                    String userId = parts[0];
                    int score = Integer.parseInt(parts[1]);
                    Usuari usuari = new Usuari(userId);

                    multiLlista.inserir(pelicula, usuari, score);
                }
            }
            scanner.close();
        } catch (FileNotFoundException e) {
            System.out.println("An error occurred.");
            e.printStackTrace();
        } catch (ElementNoTrobat | ElementRepetit e) {
            throw new RuntimeException(e);
        }
    }

}
