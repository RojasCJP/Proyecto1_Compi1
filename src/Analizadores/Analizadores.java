package Analizadores;

import java.io.File;

public class Analizadores {

    public static void lexer() {
        String ruta = "src/Analizadores/Lexer.flex";
        generarFlexer(ruta);
    }

    public static void generarFlexer(String ruta) {
        File archivo = new File(ruta);
        jflex.Main.generate(archivo);


    }
}
