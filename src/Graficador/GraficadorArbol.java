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

    public String cuerpoGraphviz(ArrayList paraGraficar) {
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

    public String crearDot(String cuerpo) {
        String      nombre      = "";
        int         contador    = 0;
        FileWriter  fileWriter  = null;
        PrintWriter printWriter = null;
        File        imagen      = new File("src/Imagenes/ARBOLES_201900289/arbol" + contador + ".png");
        try {
            while (imagen.exists()) {
                contador++;
                imagen = new File("src/Imagenes/ARBOLES_201900289/arbol" + contador+".png");
            }
            fileWriter = new FileWriter("src/Graphviz/Arbol"+contador+".dot");
            printWriter = new PrintWriter(fileWriter);
            printWriter.println(cuerpo);
            String nombreImagen = imagen.getName();
            String comando      = "dot.exe -Tpng src/Graphviz/Arbol.dot -o src/Imagenes/ARBOLES_201900289/" + nombreImagen;
//            try{
//                Runtime.getRuntime().exec(comando);
//            }catch (IOException e){
//                System.out.println(e);
//            }

        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                // Nuevamente aprovechamos el finally para
                // asegurarnos que se cierra el fileWriter.
                if (null != fileWriter)
                    fileWriter.close();
            } catch (Exception e2) {
                e2.printStackTrace();
            }
        }


        return nombre;
    }
}
