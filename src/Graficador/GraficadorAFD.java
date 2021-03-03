package Graficador;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.HashMap;
import java.util.Map;

public class GraficadorAFD {
    private static String              encabezado = "digraph structs {\nrankdir=LR;\n";
    public static  String              cuerpo     = "";
    public static  Map<String, String> estados    = new HashMap<String, String>();

    public void crearAFD() {
        encabezado += "node [shape=doublecircle];";
        for (Object entry : estados.entrySet()) {
            if (((Map.Entry<String, String>) entry).getValue().equals("true")) {
                encabezado += ((Map.Entry<String, String>) entry).getKey() + ";";
            }
        }
        encabezado += "\nnode [shape=circle];";
        encabezado += cuerpo;
        encabezado += "}";
    }

    public void crearDot() {
        FileWriter  fichero  = null;
        PrintWriter pw       = null;
        int         contador = 0;
        File        dot      = new File("src/Graphviz/AFD" + contador + ".dot");
        while (dot.exists()) {
            contador++;
            dot = new File("src/Graphviz/AFD" + contador + ".dot");
        }
        try {
            fichero = new FileWriter("src/Graphviz/AFD" + contador + ".dot");
            pw = new PrintWriter(fichero);
            pw.println(encabezado);
            fichero.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
        try {
            File file = new File("src/Graphviz/AFD" + contador + ".dot");
            if (file.exists()) {
                String  comando = "dot.exe -Tpng src/Graphviz/AFD" + contador + ".dot -o src/Imagenes/AFD_201900289/AFD" + contador + ".jpg";
                Process process = Runtime.getRuntime().exec(comando);

            }
        } catch (IOException e) {
            e.printStackTrace();
        }
//        System.out.println(encabezado);
        this.estados = new HashMap();
        this.cuerpo = "";
        encabezado = "digraph structs {\nrankdir=LR;\n";
    }
}

