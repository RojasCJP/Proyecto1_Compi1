package Graficador;

import Arbol.Nodo;

import java.util.ArrayList;

public class NodosThompson {
    int    primero;
    int    ultimo;
    String nombre;
    Nodo   enlace;
    static ArrayList listaTodos = new ArrayList();

    public NodosThompson(int primero, int ultimo, String nombre, Nodo enlace) {
        this.primero = primero;
        this.ultimo = ultimo;
        this.nombre = nombre;
        this.enlace = enlace;
        listaTodos.add(this);
    }

    public static NodosThompson findByNode(Nodo busqueda) {
        NodosThompson auxiliar;
        for (Object entry : listaTodos) {
            auxiliar = (NodosThompson) entry;
            if (auxiliar.enlace == busqueda) {
                return auxiliar;
            }
        }
        return null;
    }
}
