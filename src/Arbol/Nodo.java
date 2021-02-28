package Arbol;

public class Nodo {

    String value;
    Nodo   izquierdo;
    Nodo   derecho;

    public Nodo(String value, Nodo izquierdo, Nodo derecho) {
        this.value = value;
        this.izquierdo = izquierdo;
        this.derecho = derecho;
    }
//System.out.println("La expresion regular es: " + a.getValorNodo());
//                    if (a != null) {
//       meVaAServir.add(a.getValorNodo());
//    } todo tengo que insertar esto en sintax java si es que lo vuelvo a tener que hacer
    public String getValorNodo() {
        if ((this.izquierdo == null) && (this.derecho == null)) {
            return this.value;
        } else if ((this.izquierdo != null) && (this.derecho == null)) {
            String valorIzquierdo = this.izquierdo.getValorNodo();
            return "("+valorIzquierdo + this.value+")";
        } else if ((this.izquierdo != null) && (this.derecho != null)) {
            String valorIzquierdo = this.izquierdo.getValorNodo();
            String valorDerecho   = this.derecho.getValorNodo();
            return "("+valorIzquierdo + this.value + valorDerecho+")";
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
