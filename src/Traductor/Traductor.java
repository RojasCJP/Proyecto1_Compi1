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

    public ArrayList<String> separador() {
        ArrayList<String> separado      = new ArrayList<String>();
        String            identificador = "";
        boolean           cadena        = true;
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
                        identificador += entrada.charAt(i);
                    }
                } else {
                    if ((entrada.charAt(i) == '\"' || entrada.charAt(i) == '\'') && cadena) {
                        cadena = false;
                    } else if ((entrada.charAt(i) == '\"' || entrada.charAt(i) == '\'') && !cadena) {
                        cadena = true;
                    }
                    identificador += entrada.charAt(i);
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
}
