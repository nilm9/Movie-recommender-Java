package cat.urv.deim.test;

import cat.urv.deim.exceptions.ElementNoTrobat;
import cat.urv.deim.io.FileLoader;
import cat.urv.deim.models.*;
import org.junit.jupiter.api.BeforeEach;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;
import cat.urv.deim.io.FileLoaderTests;

public class FileLoaderTest {

    private MultiLlista<Pelicula, Usuari, Integer> multiLlista;
    private String rutaFitxer;

    @BeforeMethod
    void setup() {
        multiLlista = new MultiLlista<>();
        rutaFitxer = "prova0.txt";
        FileLoaderTests.carregarDades(multiLlista, rutaFitxer);
    }

    @Test
    public void carregarDadesMultiLlista() throws ElementNoTrobat {
        assertEquals(3, multiLlista.numColumns()); // suposem que el fitxer té 100 pel·lícules
        assertEquals(4, multiLlista.numRows()); // suposem que el fitxer té 50 usuaris

        Pelicula pelicula = new Pelicula("Film1");
        Usuari persona = new Usuari("User1");
        assertTrue(multiLlista.existeix(pelicula, persona)); // suposem que "Film1" i "User1" estan al fitxer
    }
}
