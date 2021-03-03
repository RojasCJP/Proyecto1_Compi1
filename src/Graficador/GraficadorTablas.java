package Graficador;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class GraficadorTablas {
    public static String            cuerpoSiguientes    = "digraph {\n" + "\n" + "  tbl [\n" + "\n" + "    shape=plaintext\n" + "    label=<\n" + "      <table border='0' cellborder='1' color='blue' cellspacing='0'>\n" + "        <tr><td>Numero Hoja</td><td>Hoja Cadena</td><td>Siguientes</td></tr>";
    public static Map               transiciones        = new HashMap();
    public static ArrayList<String> valoresTransiciones = new ArrayList<String>();
    public static ArrayList<String> estados             = new ArrayList<String>();
    public static String            cuerpoTransiciones  = "digraph {\n" + "\n" + "  tbl [\n" + "\n" + "    shape=plaintext\n" + "    label=<\n" + "      <table border='0' cellborder='1' color='blue' cellspacing='0'>\n";


    public void crearTablaSiguientes() {
        cuerpoSiguientes += "<tr><td>5</td><td>#</td><td></td></tr>\n" +
                "\n" +
                "      </table>\n" +
                "\n" +
                "    >];\n" +
                "\n" +
                "}";
    }

    public void crearDotSiguientes() {
        FileWriter  fichero  = null;
        PrintWriter pw       = null;
        int         contador = 0;
        File        dot      = new File("src/Graphviz/Siguientes" + contador + ".dot");
        while (dot.exists()) {
            contador++;
            dot = new File("src/Graphviz/Siguientes" + contador + ".dot");
        }
        try {
            fichero = new FileWriter("src/Graphviz/Siguientes" + contador + ".dot");
            pw = new PrintWriter(fichero);
            pw.println(cuerpoSiguientes);
            fichero.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
        try {
            File file = new File("src/Graphviz/Siguientes" + contador + ".dot");
            if (file.exists()) {
                String  comando = "dot.exe -Tpng src/Graphviz/Siguientes" + contador + ".dot -o src/Imagenes/SIGUIENTES_201900289/Siguientes" + contador + ".jpg";
                Process process = Runtime.getRuntime().exec(comando);

            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        cuerpoSiguientes    = "digraph {\n" + "\n" + "  tbl [\n" + "\n" + "    shape=plaintext\n" + "    label=<\n" + "      <table border='0' cellborder='1' color='blue' cellspacing='0'>\n" + "        <tr><td>Numero Hoja</td><td>Hoja Cadena</td><td>Siguientes</td></tr>";
    }

    public void crearTablaTransiciones() {
        cuerpoTransiciones += "<tr>";
        cuerpoTransiciones += "<td>";
        cuerpoTransiciones += "Estado";
        cuerpoTransiciones += "</td>";
        for (Object entry : transiciones.entrySet()) {
            String estadoInicial = (String) ((Map.Entry) entry).getKey();
            estados.add(estadoInicial);
            Map transicion = new HashMap();
            transicion = (HashMap) transiciones.get(estadoInicial);
            for (Object entry1 : transicion.entrySet()) {
                String transicionString = (String) ((Map.Entry) entry1).getKey();
                if (!valoresTransiciones.contains(transicionString)) {
                    valoresTransiciones.add(transicionString);
                    cuerpoTransiciones += "<td>";
                    cuerpoTransiciones += transicionString;
                    cuerpoTransiciones += "</td>";

                }
            }
        }
        cuerpoTransiciones += "</tr>\n";

        for (Object entry : transiciones.entrySet()) {
            cuerpoTransiciones += "<tr>";
            String estadoInicial = (String) ((Map.Entry) entry).getKey();
            cuerpoTransiciones += "<td>";
            cuerpoTransiciones += estadoInicial;
            cuerpoTransiciones += "</td>";
            Map transicion = (HashMap) transiciones.get(estadoInicial);
            for (String entry1 : valoresTransiciones) {
                if (transicion.get(entry1) != null) {
                    cuerpoTransiciones += "<td>";
                    cuerpoTransiciones += transicion.get(entry1);
                    cuerpoTransiciones += "</td>";
                } else {
                    cuerpoTransiciones += "<td>";
                    cuerpoTransiciones += "null";
                    cuerpoTransiciones += "</td>";
                }
            }
            cuerpoTransiciones += "</tr>\n";
        }
        cuerpoTransiciones += "</table>\n" + "\n" + "    >];\n" + "\n" + "}";
    }

    public void crearDotTransiciones() {
        FileWriter  fichero  = null;
        PrintWriter pw       = null;
        int         contador = 0;
        File        dot      = new File("src/Graphviz/Transiciones" + contador + ".dot");
        while (dot.exists()) {
            contador++;
            dot = new File("src/Graphviz/Transiciones" + contador + ".dot");
        }
        try {
            fichero = new FileWriter("src/Graphviz/Transiciones" + contador + ".dot");
            pw = new PrintWriter(fichero);
            pw.println(cuerpoTransiciones);
            fichero.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
        try {
            File file = new File("src/Graphviz/Transiciones" + contador + ".dot");
            if (file.exists()) {
                String  comando = "dot.exe -Tpng src/Graphviz/Transiciones" + contador + ".dot -o src/Imagenes/TRANSICIONES_201900289/Transiciones" + contador + ".jpg";
                Process process = Runtime.getRuntime().exec(comando);

            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        transiciones = new HashMap();
        valoresTransiciones = new ArrayList<String>();
        estados = new ArrayList<String>();
        cuerpoTransiciones = "digraph {\n" + "\n" + "  tbl [\n" + "\n" + "    shape=plaintext\n" + "    label=<\n" + "      <table border='0' cellborder='1' color='blue' cellspacing='0'>\n";
    }


}
