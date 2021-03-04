package Graficador;

import Arbol.Nodo;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;

public class GraficadorThompson {
    public        Nodo      raiz;
    public static ArrayList operaciones = new ArrayList();
    private       int       contador    = 0;
    private       String    primero     = "";
    private       String    ultimo      = "";
    private       String    cuerpo      = "";

    public GraficadorThompson(Nodo raiz) {
        this.raiz = raiz;
        raiz.paraThompson();
        ArrayList operacionesLocal = operaciones;
        for (Object entry : operacionesLocal) {
            Nodo operador = (Nodo) entry;
            switch (operador.getValue()) {
                case "|":
                    Nodo izquierdoOr = operador.getIzquierdo();
                    Nodo derechoOr = operador.getDerecho();
                    NodosThompson primeroOr1 = new NodosThompson(contador, NodosThompson.findByNode(izquierdoOr).primero, "ε", null);
                    NodosThompson primeroOr2 = new NodosThompson(contador, NodosThompson.findByNode(derechoOr).primero, "ε", null);
                    contador++;
                    NodosThompson ultimoOr1 = new NodosThompson(NodosThompson.findByNode(izquierdoOr).ultimo, contador, "ε", null);
                    NodosThompson ultimoOr2 = new NodosThompson(NodosThompson.findByNode(derechoOr).ultimo, contador, "ε", null);
                    NodosThompson completoOr = new NodosThompson(primeroOr1.primero, ultimoOr1.ultimo, "", operador);
                    contador++;
                    cuerpo += " " + primeroOr1.primero + "->" + primeroOr1.ultimo + " [label=\"" + primeroOr1.nombre + "\"];\n";
                    cuerpo += " " + primeroOr2.primero + "->" + primeroOr2.ultimo + " [label=\"" + primeroOr2.nombre + "\"];\n";
                    cuerpo += " " + ultimoOr1.primero + "->" + ultimoOr1.ultimo + " [label=\"" + ultimoOr1.nombre + "\"];\n";
                    cuerpo += " " + ultimoOr2.primero + "->" + ultimoOr2.ultimo + " [label=\"" + ultimoOr2.nombre + "\"];\n";
                    break;
                case ".":
                    Nodo izquierdoPunto = operador.getIzquierdo();
                    Nodo derechoPunto = operador.getDerecho();
                    NodosThompson completoPunto = new NodosThompson(NodosThompson.findByNode(izquierdoPunto).primero, NodosThompson.findByNode(derechoPunto).ultimo, "ε", operador);
                    cuerpo += " " + NodosThompson.findByNode(izquierdoPunto).ultimo + "->" + NodosThompson.findByNode(derechoPunto).primero + " [label=\"" + completoPunto.nombre + "\"];\n";
                    break;
                case "+":
                    Nodo izquierdoMas = operador.getIzquierdo();
                    NodosThompson antesMas = new NodosThompson(contador, NodosThompson.findByNode(izquierdoMas).primero, "ε", null);
                    contador++;
                    NodosThompson despuesMas = new NodosThompson(NodosThompson.findByNode(izquierdoMas).ultimo, contador, "ε", null);
                    contador++;
                    NodosThompson recursivoMas = new NodosThompson(NodosThompson.findByNode(izquierdoMas).ultimo, NodosThompson.findByNode(izquierdoMas).primero, "ε", null);
                    NodosThompson completoMas = new NodosThompson(antesMas.primero, despuesMas.ultimo, "ε", operador);
                    cuerpo += " " + antesMas.primero + "->" + antesMas.ultimo + " [label=\"" + antesMas.nombre + "\"];\n";
                    cuerpo += " " + despuesMas.primero + "->" + despuesMas.ultimo + " [label=\"" + despuesMas.nombre + "\"];\n";
                    cuerpo += " " + recursivoMas.primero + "->" + recursivoMas.ultimo + " [label=\"" + recursivoMas.nombre + "\"];\n";
                    break;
                case "?":
                    Nodo izquierdoInterrogacion = operador.getIzquierdo();
                    NodosThompson antesInterrogacion = new NodosThompson(contador, NodosThompson.findByNode(izquierdoInterrogacion).primero, "ε", null);
                    contador++;
                    NodosThompson despuesInterrogacion = new NodosThompson(NodosThompson.findByNode(izquierdoInterrogacion).ultimo, contador, "ε", null);
                    contador++;
                    NodosThompson completoInterrogacion = new NodosThompson(antesInterrogacion.primero, despuesInterrogacion.ultimo, "ε", operador);
                    cuerpo += " " + antesInterrogacion.primero + "->" + antesInterrogacion.ultimo + " [label=\"" + antesInterrogacion.nombre + "\"];\n";
                    cuerpo += " " + despuesInterrogacion.primero + "->" + despuesInterrogacion.ultimo + " [label=\"" + despuesInterrogacion.nombre + "\"];\n";
                    cuerpo += " " + completoInterrogacion.primero + "->" + completoInterrogacion.ultimo + " [label=\"" + completoInterrogacion.nombre + "\"];\n";
                    break;
                case "*":
                    Nodo izquierdoKleene = operador.getIzquierdo();
                    NodosThompson antesKleene = new NodosThompson(contador, NodosThompson.findByNode(izquierdoKleene).primero, "ε", null);
                    contador++;
                    NodosThompson despuesKleene = new NodosThompson(NodosThompson.findByNode(izquierdoKleene).ultimo, contador, "ε", null);
                    contador++;
                    NodosThompson recursivoKleene = new NodosThompson(NodosThompson.findByNode(izquierdoKleene).ultimo, NodosThompson.findByNode(izquierdoKleene).primero, "ε", null);
                    NodosThompson completoKleene = new NodosThompson(antesKleene.primero, despuesKleene.ultimo, "ε", operador);
                    cuerpo += " " + antesKleene.primero + "->" + antesKleene.ultimo + " [label=\"" + antesKleene.nombre + "\"];\n";
                    cuerpo += " " + despuesKleene.primero + "->" + despuesKleene.ultimo + " [label=\"" + despuesKleene.nombre + "\"];\n";
                    cuerpo += " " + recursivoKleene.primero + "->" + recursivoKleene.ultimo + " [label=\"" + recursivoKleene.nombre + "\"];\n";
                    cuerpo += " " + completoKleene.primero + "->" + completoKleene.ultimo + " [label=\"" + completoKleene.nombre + "\"];\n";
                    break;
                default:
                    NodosThompson valor = new NodosThompson(contador, contador + 1, operador.getValue(), operador);
                    contador = valor.ultimo + 1;
                    if (valor.nombre.charAt(0) == '\"') {
                        cuerpo += " " + valor.primero + "->" + valor.ultimo + " [label=\"\\" + valor.nombre.substring(0, valor.nombre.length() - 1) + "\\\"\"];\n";
                    } else {
                        cuerpo += " " + valor.primero + "->" + valor.ultimo + " [label=\"" + valor.nombre + "\"];\n";
                    }
                    break;
            }
        }


        System.out.println(cuerpo);
    }

    public void crearDot() {
        cuerpo = "digraph {\n" +
                "  rankdir = LR;" + cuerpo + "}";

        FileWriter  fichero  = null;
        PrintWriter pw       = null;
        int  contador = 0;
        File dot      = new File("src/Graphviz/AFND" + contador + ".dot");
        while (dot.exists()) {
            contador++;
            dot = new File("src/Graphviz/AFND" + contador + ".dot");
        }
        try {
            fichero = new FileWriter("src/Graphviz/AFND" + contador + ".dot");
            pw = new PrintWriter(fichero);
            pw.println(cuerpo);
            fichero.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
        try {
            File file = new File("src/Graphviz/AFND" + contador + ".dot");
            if (file.exists()) {
                String  comando = "dot.exe -Tpng src/Graphviz/AFND" + contador + ".dot -o src/Imagenes/AFND_201900289/AFND" + contador + ".jpg";
                Process process = Runtime.getRuntime().exec(comando);

            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        operaciones = new ArrayList();
        contador    = 0;
        primero     = "";
        ultimo      = "";
        cuerpo      = "";
    }
}
