package Regex;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class Conjuntos {
    String conjuntoValor;
    String conjuntoNombre;
    public static Map       conjuntosBusquda = new HashMap();
    public static ArrayList todosConjuntos   = new ArrayList();

    public Conjuntos(String conjuntoNombre, String conjuntoValor) {
        this.conjuntoValor = conjuntoValor;
        this.conjuntoNombre = conjuntoNombre;
        conjuntosBusquda.put(this.conjuntoNombre, separador());
        Map conjuntosLocal = conjuntosBusquda;
        System.out.println("solo pal breakpoint xd");
    }

    private ArrayList separador() {
        ArrayList todosElementos = new ArrayList();
        if (this.conjuntoValor.length() == 3) {
            char inicioGrupo = this.conjuntoValor.charAt(0);
            char finalGrupo  = this.conjuntoValor.charAt(2);
            for (int i = inicioGrupo; i < finalGrupo; i++) {
                todosElementos.add((char) i);
            }
            todosElementos.add(finalGrupo);
        } else {
            for (int i = 0; i < this.conjuntoValor.length(); i++) {
                if (this.conjuntoValor.charAt(i) != ',' && this.conjuntoValor.charAt(i) != ';') {
                    todosElementos.add(this.conjuntoValor.charAt(i));
                }
            }
        }
        return todosElementos;
    }
}
