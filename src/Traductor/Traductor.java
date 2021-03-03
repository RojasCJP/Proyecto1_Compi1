package Traductor;

import java.util.ArrayList;
import java.util.HashMap;

public class Traductor {

    private String entrada;
    private String salida;

    private char[] operadores = new char[11];

    public Traductor() {
        operadores[0] = '.';
        operadores[1] = '|';
        operadores[2] = '*';
        operadores[3] = '+';
        operadores[4] = '?';
        operadores[5] = '{';
        operadores[6] = '}';
        operadores[7] = ';';
        operadores[8] = '#';
//        operadores[9] = '\\';
//        operadores[9] = '\"';
//        operadores[10] = '\'';
    }

    public String getEntrada() {
        return entrada;
    }

    public void setEntrada(String entrada) {
        this.entrada = entrada;
    }

    public String getSalida() {
        return salida;
    }

    public void setSalida(String salida) {
        this.salida = salida;
    }

    public boolean inOperadores(char palabra) {
        for (char i : operadores) {
            if (i == palabra) {
                return true;
            }
        }
        return false;
    }

    public ArrayList<String> separador2() {
        ArrayList<String> separado      = new ArrayList<String>();
        String            identificador = "";
        boolean           cadena        = true;
        boolean           especial      = false;
        for (int i = 0; i < entrada.length(); i++) {
            if (entrada.charAt(i) != ' ' && entrada.charAt(i) != '\n' && entrada.charAt(i) != '\t' && entrada.charAt(i) != '\r') {
                if (inOperadores(entrada.charAt(i))) {
                    if (cadena) {
                        if (identificador != "") {
                            separado.add(identificador);
                            identificador = "";
                        }
                        separado.add(Character.toString(entrada.charAt(i)));
                    } else {
                        if (entrada.charAt(i + 1) == '\'' && entrada.charAt(i) == '\\') {
                            identificador += entrada.charAt(i) + entrada.charAt(i + 1);
                            i++;
                            especial = true;
                        }
                        identificador += entrada.charAt(i);
                    }
                } else {
                    if ((entrada.charAt(i) == '\"' || entrada.charAt(i) == '\'') && cadena) {
                        cadena = false;
                    } else if ((entrada.charAt(i) == '\"' || entrada.charAt(i) == '\'') && !cadena) {
                        identificador += entrada.charAt(i);
                        cadena = true;
                        separado.add(identificador);
                        identificador = "";
                    }
                    identificador += entrada.charAt(i);
                    if (cadena && identificador.equals("\"")) {
                        identificador = "";
                    }
                }
            }
        }
        for (int i = 0; i < separado.size(); i++) {
            separado.remove("{");
            separado.remove("}");
            separado.remove("\"");
            separado.remove("\'");
        }
        return separado;
    }

    public ArrayList<String> separador() {
        ArrayList separado      = new ArrayList();
        String    identificador = "";
        String    cadena        = "";
        boolean   cadenaBool    = false;
        for (int i = 0; i < entrada.length(); i++) {
            if (cadenaBool) {
                cadena += entrada.charAt(i);
                if (entrada.charAt(i) == '\"' || entrada.charAt(i) == '}' || entrada.charAt(i) == '\'') {
                    cadenaBool = false;
                    if(cadena.charAt(0)=='{'){
                        cadena = cadena.substring(1,cadena.length()-1);
                    }
                    separado.add(cadena);
                    cadena = "";
                }
            } else {
                if (entrada.charAt(i) == '\"' || entrada.charAt(i) == '{' || entrada.charAt(i) == '\'') {
                    cadenaBool = true;
                    cadena += entrada.charAt(i);
                }
                if (entrada.charAt(i) == '\\') {
                    String especial = "\\" + entrada.charAt(i + 1);
                    i++;
                    separado.add(especial);
                }
                if (inOperadores(entrada.charAt(i))) {
                    separado.add(Character.toString(entrada.charAt(i)));
                }
            }
        }

        for (int i = 0; i < separado.size(); i++) {
            separado.remove("{");
            separado.remove("}");
            separado.remove("\"");
            separado.remove("\'");
        }
        return separado;
    }

    public int numeroDeExpresiones() {
        int       numero;
        ArrayList separado = separador();
        for (int i = 0; i < separado.size(); i++) {
            separado.remove(".");
            separado.remove("|");
            separado.remove("*");
            separado.remove("+");
            separado.remove("?");
            separado.remove("{");
            separado.remove("}");
            separado.remove(";");
        }
        numero = separado.size();
        return numero;
    }
}
