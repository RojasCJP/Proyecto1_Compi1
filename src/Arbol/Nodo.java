package Arbol;

import Graficador.GraficadorThompson;

import java.util.ArrayList;

public class Nodo {

    String value;
    Nodo   izquierdo;
    Nodo   derecho;

    public Nodo(String value, Nodo izquierdo, Nodo derecho) {
        this.value = value;
        this.izquierdo = izquierdo;
        this.derecho = derecho;
    }

    public String getValorNodo() {
        if ((this.izquierdo == null) && (this.derecho == null)) {
            return this.value;
        } else if ((this.izquierdo != null) && (this.derecho == null)) {
            String valorIzquierdo = this.izquierdo.getValorNodo();
            return "(" + valorIzquierdo + this.value + ")";
        } else if ((this.izquierdo != null) && (this.derecho != null)) {
            String valorIzquierdo = this.izquierdo.getValorNodo();
            String valorDerecho   = this.derecho.getValorNodo();
            return "(" + valorIzquierdo + this.value + valorDerecho + ")";
        }
        return "ocurrio un error";
    }

    public String notacionPolaca() {
        if ((this.izquierdo == null) && (this.derecho == null)) {
            return this.value;
        } else if ((this.izquierdo != null) && (this.derecho == null)) {
            String valorIzquierdo = this.izquierdo.notacionPolaca();
            return this.value + valorIzquierdo;
        } else if ((this.izquierdo != null) && (this.derecho != null)) {
            String valorIzquierdo = this.izquierdo.notacionPolaca();
            String valorDerecho   = this.derecho.notacionPolaca();
            return this.value + valorIzquierdo + valorDerecho;
        }
        return "ocurrio un error";
    }

    public String paraThompson() {
        if ((this.izquierdo == null) && (this.derecho == null)) {
            GraficadorThompson.operaciones.add(this);
            return this.value;
        } else if ((this.izquierdo != null) && (this.derecho == null)) {
            String valorIzquierdo = this.izquierdo.paraThompson();
            GraficadorThompson.operaciones.add(this);
            return "(" + valorIzquierdo + this.value + ")";
        } else if ((this.izquierdo != null) && (this.derecho != null)) {
            String valorIzquierdo = this.izquierdo.paraThompson();
            String valorDerecho   = this.derecho.paraThompson();
            GraficadorThompson.operaciones.add(this);
            return "(" + valorIzquierdo + this.value + valorDerecho + ")";
        }
        return "ocurrio un error";
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }

    public Nodo getIzquierdo() {
        return izquierdo;
    }

    public void setIzquierdo(Nodo izquierdo) {
        this.izquierdo = izquierdo;
    }

    public Nodo getDerecho() {
        return derecho;
    }

    public void setDerecho(Nodo derecho) {
        this.derecho = derecho;
    }
}
