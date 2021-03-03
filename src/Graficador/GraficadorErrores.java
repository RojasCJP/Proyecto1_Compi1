package Graficador;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;

public class GraficadorErrores {
    public static String errores  = "digraph {\n" +
            "\n" +
            "  tbl [\n" +
            "\n" +
            "    shape=plaintext\n" +
            "    label=<\n" +
            "      <table border='0' cellborder='1' color='blue' cellspacing='0'>\n" +
            "        <tr><td>#</td><td>Tipo de error</td><td>Descripcion</td><td>Linea</td><td>Columna</td></tr>";
    public static int    contador = 1;

    public void crearDot() {
        errores += "</table>\n" +
                "\n" +
                "    >];\n" +
                "\n" +
                "}";
        FileWriter  fichero  = null;
        PrintWriter pw       = null;
        int         contador = 0;
        File        dot      = new File("src/Graphviz/Errores" + contador + ".dot");
        while (dot.exists()) {
            contador++;
            dot = new File("src/Graphviz/Errores" + contador + ".dot");
        }
        try {
            fichero = new FileWriter("src/Graphviz/Errores" + contador + ".dot");
            pw = new PrintWriter(fichero);
            pw.println(errores);
            fichero.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
        try {
            File file = new File("src/Graphviz/Errores" + contador + ".dot");
            if (file.exists()) {
                String  comando = "dot.exe -Tpng src/Graphviz/Errores" + contador + ".dot -o src/Imagenes/ERRORES_201900289/Errores" + contador + ".jpg";
                Process process = Runtime.getRuntime().exec(comando);

            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        errores = "digraph {\n" +
                "\n" +
                "  tbl [\n" +
                "\n" +
                "    shape=plaintext\n" +
                "    label=<\n" +
                "      <table border='0' cellborder='1' color='blue' cellspacing='0'>\n" +
                "        <tr><td>#</td><td>Tipo de error</td><td>Descripcion</td><td>Linea</td><td>Columna</td></tr>";
        contador = 1;
    }

}
