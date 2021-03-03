package Graficador;

import Arbol.NodoMetodo;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;

public class GraficadorArbol {
    String graph = "digraph structs {\n" +
            "    node [shape=record];";

    public String cuerpoArchivo(ArrayList paraGraficar) {
        for (Object nodo : paraGraficar) {
            for (Object hoja : (ArrayList) nodo) {
                NodoMetodo hojaUtil = (NodoMetodo) hoja;
                if (hojaUtil.izquierda == null && hojaUtil.derecha == null) {
                    graph += hojaUtil.toString().replaceAll("@", "").replace(".", "");
                    graph += "[";
                    graph += "label=\"";
                    graph += hojaUtil.primero.toString();//va la lista de los primeros
                    graph += "|{";
                    graph += hojaUtil.anulable;//va la anulabilidad
                    graph += "|{";
                    if (hojaUtil.lexema.replaceAll("\"", "").equals("|")) {
                        graph += "\\" + hojaUtil.lexema.replaceAll("\"", "");//es el valor String del nodo
                    } else {
                        graph += hojaUtil.lexema.replaceAll("\"", "");//es el valor String del nodo
                    }
                    graph += "}|";
                    graph += hojaUtil.numero;//este es el id del numero
                    graph += "}|";
                    graph += hojaUtil.ultimo.toString();//estos son los siguentes
                    graph += "\"];\n";
                } else if (hojaUtil.izquierda != null && hojaUtil.derecha == null) {
                    graph += hojaUtil.toString().replaceAll("@", "").replace(".", "");
                    graph += "[";
                    graph += "label=\"";
                    graph += hojaUtil.primero.toString();//va la lista de los primeros
                    graph += "|{";
                    graph += hojaUtil.anulable;//va la anulabilidad
                    graph += "|{";
                    if (hojaUtil.lexema.replaceAll("\"", "").equals("|")) {
                        graph += "\\" + hojaUtil.lexema.replaceAll("\"", "");//es el valor String del nodo
                    } else {
                        graph += hojaUtil.lexema.replaceAll("\"", "");//es el valor String del nodo
                    }
                    graph += "}|";
                    graph += hojaUtil.numero;//este es el id del numero
                    graph += "}|";
                    graph += hojaUtil.ultimo.toString();//estos son los siguentes
                    graph += "\"];\n";
                    graph += hojaUtil.toString().replaceAll("@", "").replace(".", "") + " -> " + hojaUtil.izquierda.toString().replaceAll("@", "").replace(".", "") + ";\n";
                } else if (hojaUtil.izquierda != null && hojaUtil.derecha != null) {
                    graph += hojaUtil.toString().replaceAll("@", "").replace(".", "");
                    graph += "[";
                    graph += "label=\"";
                    graph += hojaUtil.primero.toString();//va la lista de los primeros
                    graph += "|{";
                    graph += hojaUtil.anulable;//va la anulabilidad
                    graph += "|{";
                    if (hojaUtil.lexema.replaceAll("\"", "").equals("|")) {
                        graph += "\\" + hojaUtil.lexema.replaceAll("\"", "");//es el valor String del nodo
                    } else {
                        graph += hojaUtil.lexema.replaceAll("\"", "");//es el valor String del nodo
                    }
                    graph += "}|";
                    graph += hojaUtil.numero;//este es el id del numero
                    graph += "}|";
                    graph += hojaUtil.ultimo.toString();//estos son los siguentes
                    graph += "\"];\n";
                    graph += hojaUtil.toString().replaceAll("@", "").replace(".", "") + " -> " + hojaUtil.izquierda.toString().replaceAll("@", "").replace(".", "") + ";\n";
                    graph += hojaUtil.toString().replaceAll("@", "").replace(".", "") + " -> " + hojaUtil.derecha.toString().replaceAll("@", "").replace(".", "") + ";\n";
                } else {
                    System.out.println("aiuda hay un error en la creacion del arbol en graphviz");
                }
            }
        }
        graph += "}";
        return (graph);
    }

    public void crearDot(String cuerpo) {
        FileWriter  fichero  = null;
        PrintWriter pw       = null;
        int         contador = 0;
        File        dot      = new File("src/Graphviz/Arbol" + contador + ".dot");
        while (dot.exists()) {
            contador++;
            dot = new File("src/Graphviz/Arbol" + contador + ".dot");
        }
        try {
            fichero = new FileWriter("src/Graphviz/Arbol" + contador + ".dot");
            pw = new PrintWriter(fichero);
            pw.println(cuerpo);
            fichero.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
        try {
            File file = new File("src/Graphviz/Arbol" + contador + ".dot");
            if (file.exists()) {
                String  comando = "dot.exe -Tpng src/Graphviz/Arbol" + contador + ".dot -o src/Imagenes/ARBOLES_201900289/Arbol" + contador + ".jpg";
                Process process = Runtime.getRuntime().exec(comando);

            }
        } catch (IOException e) {
            e.printStackTrace();
        }
//        System.out.println(cuerpo);
    }
}
