package Regex;

import Arbol.Nodo;

import java.io.File;
import java.io.FileWriter;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class AnalizadorRegex {
    public static ArrayList nombres    = new ArrayList();
    public static ArrayList todosNodos = new ArrayList();
    public static ArrayList nodos      = new ArrayList();
    public static Map       mapaEntero = new HashMap();
    public static Map       cadenas    = new HashMap();
    public static boolean   arregladas = false;
    public static String    cuerpoJSON = "[";

    public static NodoAFD findByName(String nombre) {
        for (int i = 0; i < nodos.size(); i++) {
            if (((NodoAFD) nodos.get(i)).nombre.equals(nombre)) {
                return (NodoAFD) nodos.get(i);
            }
        }
        return null;
    }

    public static void arreglarMapa() {
        if (nombres.size() == todosNodos.size()) {
            for (int i = 0; i < nombres.size(); i++) {
                mapaEntero.put(nombres.get(i), todosNodos.get(i));
            }
        } else {
            System.out.println("Ocurrio un problema con las expresiones regulares");
            System.out.println("Revisalas por favor");
        }
    }

    public void evaluarExpresiones() {
        arreglarMapa();
        arreglarCadenas();
        if (mapaEntero.size() != 0) {
            evaluacion();
        }
        System.out.println(mapaEntero);
        System.out.println(cadenas);

    }

    private void escribirJSON() {
        cuerpoJSON = cuerpoJSON.substring(0, (cuerpoJSON.length() - 1));
        cuerpoJSON += "]";
        FileWriter  fichero  = null;
        PrintWriter pw       = null;
        int         contador = 0;
        File        dot      = new File("src/Imagenes/SALIDAS_201900289/salida" + contador + ".json");
        while (dot.exists()) {
            contador++;
            dot = new File("src/Imagenes/SALIDAS_201900289/salida" + contador + ".json");
        }
        try {
            fichero = new FileWriter("src/Imagenes/SALIDAS_201900289/salida" + contador + ".json");
            pw = new PrintWriter(fichero);
            pw.println(cuerpoJSON);
            fichero.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void arreglarCadenas() {
        String key = "";
        for (Object entry : cadenas.entrySet()) {
            key = ((Map.Entry) entry).getKey().toString();
            for (int i = 0; i < ((ArrayList) ((Map.Entry) entry).getValue()).size(); i++) {
                String stringCompleto = ((String) ((ArrayList) ((Map.Entry) entry).getValue()).get(i));
                String subString      = stringCompleto.substring(1, stringCompleto.length() - 1);
                if (stringCompleto.charAt(0) == '"') {
                    ((ArrayList) cadenas.get(key)).set(i, subString);
                }
            }
        }
    }

    private void evaluacion() {
        boolean   cadenaValida   = true;
        String    key            = "";
        ArrayList cadenasEvaluar = new ArrayList();
        for (Object entry : cadenas.entrySet()) {
            key = ((Map.Entry) entry).getKey().toString();
            nodos = ((ArrayList) mapaEntero.get(key));
            cadenasEvaluar = ((ArrayList) cadenas.get(key));
            NodoAFD primerNodo = new NodoAFD("suplente");
            if (((ArrayList) mapaEntero.get(key)) != null) {
                primerNodo = ((NodoAFD) ((ArrayList) mapaEntero.get(key)).get(0));
            }

            for (int i = 0; i < cadenasEvaluar.size(); i++) {
                String cadenaEvaluada = ((String) cadenasEvaluar.get(i));
                cadenaValida = true;
                for (int j = 0; j < cadenaEvaluada.length(); j++) {
                    if (cadenaValida) {

                        if (cadenaEvaluada.charAt(j) != '\\') {
                            char    caracterEvaluar = cadenaEvaluada.charAt(j);
                            boolean trigger         = false;
                            if (primerNodo != null) {
                                for (Object entrySiguiente : primerNodo.siguientes.entrySet()) {
                                    String    keySiguiente       = ((Map.Entry) entrySiguiente).getKey().toString();
                                    ArrayList posiblesSiguientes = ((ArrayList) primerNodo.siguientes.get(keySiguiente));
                                    for (int k = 0; k < posiblesSiguientes.size(); k++) {

                                        String siguiente = ((String) posiblesSiguientes.get(k));
                                        if (!trigger) {
                                            if (siguiente.charAt(0) == '"') {
                                                siguiente = siguiente.substring(1, (siguiente.length() - 1));
                                                if (siguiente.charAt(0) != caracterEvaluar) {
                                                    cadenaValida = false;
                                                } else {
                                                    cadenaValida = true;
                                                    trigger = true;
                                                }

                                            } else if (siguiente.charAt(0) == 'n' || siguiente.charAt(0) == '\'' || siguiente.charAt(0) == '"') {
                                                if (siguiente.charAt(0) != caracterEvaluar) {
                                                    cadenaValida = false;
                                                } else {
                                                    cadenaValida = true;
                                                    trigger = true;
                                                }
                                            } else {
                                                ArrayList conjuntoEnUso = new ArrayList();
                                                if (((ArrayList) Conjuntos.conjuntosBusquda.get(siguiente)) != null) {
                                                    conjuntoEnUso = ((ArrayList) Conjuntos.conjuntosBusquda.get(siguiente));
                                                }
                                                if (!conjuntoEnUso.contains(caracterEvaluar)) {
                                                    cadenaValida = false;
                                                } else {
                                                    cadenaValida = true;
                                                    trigger = true;
                                                }
                                            }
                                        }

                                    }
                                    if (cadenaValida) {
                                        primerNodo = findByName(keySiguiente);
                                    }
                                }
                            }
                        }
                    }
                    if ((j == (cadenaEvaluada.length() - 1)) && cadenaValida) {
                        cuerpoJSON += "{";
                        cuerpoJSON += "\"Valor\":\"" + cadenaEvaluada + "\",";
                        cuerpoJSON += "\"ExpresionRegular\":\"" + key + "\",";
                        cuerpoJSON += "\"Resultado\":\"Cadena Valida\"";
                        cuerpoJSON += "},";
                        System.out.println("la cadena " + cadenaEvaluada + " es valida");
                    } else if ((j == (cadenaEvaluada.length() - 1)) && !cadenaValida) {
                        cuerpoJSON += "{";
                        cuerpoJSON += "\"Valor\":\"" + cadenaEvaluada + "\",";
                        cuerpoJSON += "\"ExpresionRegular\":\"" + key + "\",";
                        cuerpoJSON += "\"Resultado\":\"Cadena Valida\"";
                        cuerpoJSON += "},";
                        System.out.println("la cadena " + cadenaEvaluada + " no es valida");
                    }

                }
            }
        }
        escribirJSON();
        AnalizadorRegex.nombres = new ArrayList();
        AnalizadorRegex.todosNodos = new ArrayList();
        AnalizadorRegex.nodos = new ArrayList();
        AnalizadorRegex.mapaEntero = new HashMap();
        AnalizadorRegex.cadenas = new HashMap();
    }
}
