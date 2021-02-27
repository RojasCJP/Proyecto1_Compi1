package Analizadores;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

public class Analizadores {

    public static void lexer()  {
        // codigo sacado de https://youtu.be/4Z6Tnit810Y
        String   rutaLexico1    = "src/Analizadores/Lexer.flex";
        String   rutaLexico2    = "src/Analizadores/LexerCup.flex";
        String[] rutaSintactico = {"-parser", "Sintax", "src/Analizadores/Sintax.cup"};
        generar(rutaLexico1, rutaLexico2, rutaSintactico);
    }

    public static void generar(String rutaLexico1, String rutaLexico2, String[] rutaSintactico)  {
        File archivoLexico1 = new File(rutaLexico1);
        jflex.Main.generate(archivoLexico1);
        File archivoLexico2 = new File(rutaLexico2);
        jflex.Main.generate(archivoLexico2);
        try {
            java_cup.Main.main(rutaSintactico);
            Path rutaSym = Paths.get("src/Analizadores/sym.java");
            Path rutaSintax = Paths.get("src/Analizadores/Sintax.java");
            if(Files.exists(rutaSym)){
                Files.delete(rutaSym);
            }
            Files.move(Paths.get("sym.java"),Paths.get("src/Analizadores/sym.java"));
            if(Files.exists(rutaSintax)){
                Files.delete(rutaSintax);
            }
            Files.move(Paths.get("Sintax.java"),Paths.get("src/Analizadores/Sintax.java"));
        } catch (Exception e) {
            e.printStackTrace();
        }

    }
}
