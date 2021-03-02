package Arbol;

import java.util.ArrayList;

public class NodoMetodo {
    //codigo visto en clase
    public        ArrayList<Integer>    primero;
    public        ArrayList<Integer>    ultimo;
    public        boolean               anulable;
    public        String                lexema;
    public        Types                 type;
    public        int                   numero;
    public        boolean               acepta;
    public        Object                izquierda;
    public        Object                derecha;
    public        ArrayList<NodoMetodo> leaves;
    public        ArrayList<ArrayList>  table;
    public static ArrayList             nodosNecesarios = new ArrayList();

    public NodoMetodo(String lexema, Types type, int numero, Object izquierda, Object derecha, ArrayList<NodoMetodo> leaves, ArrayList<ArrayList> table) {
        this.primero = new ArrayList();
        this.ultimo = new ArrayList();
        this.anulable = true;
        this.lexema = lexema;
        this.acepta = "#".equals(this.lexema);
        this.type = type;
        this.numero = numero;
        this.izquierda = izquierda;
        this.derecha = derecha;
        this.leaves = leaves;
        this.table = table;
    }

    public NodoMetodo getNodo() {
        Object nodoIzquierdo = this.izquierda instanceof NodoMetodo ? ((NodoMetodo) this.izquierda).getNodo() : null;
        Object nodoDerecho   = this.derecha instanceof NodoMetodo ? ((NodoMetodo) this.derecha).getNodo() : null;
        if (null != this.type) {
            switch (this.type) {
                case HOJA:
                    this.anulable = false;
                    this.primero.add(this.numero);
                    this.ultimo.add(this.numero);
                    break;
                case AND:
                    if (nodoIzquierdo instanceof NodoMetodo && nodoDerecho instanceof NodoMetodo) {
                        this.anulable = ((NodoMetodo) nodoIzquierdo).anulable && ((NodoMetodo) nodoDerecho).anulable;

                        this.primero.addAll(((NodoMetodo) nodoIzquierdo).primero);
                        if (((NodoMetodo) nodoIzquierdo).anulable) {
                            this.primero.addAll(((NodoMetodo) nodoDerecho).primero);
                        }

                        if (((NodoMetodo) nodoDerecho).anulable) {
                            this.ultimo.addAll(((NodoMetodo) nodoIzquierdo).ultimo);
                        }
                        this.ultimo.addAll(((NodoMetodo) nodoDerecho).ultimo);
                    }
                    break;
                case OR:
                    if (nodoIzquierdo instanceof NodoMetodo && nodoDerecho instanceof NodoMetodo) {
                        this.anulable = ((NodoMetodo) nodoIzquierdo).anulable || ((NodoMetodo) nodoDerecho).anulable;

                        this.primero.addAll(((NodoMetodo) nodoIzquierdo).primero);
                        this.primero.addAll(((NodoMetodo) nodoDerecho).primero);


                        this.ultimo.addAll(((NodoMetodo) nodoIzquierdo).ultimo);
                        this.ultimo.addAll(((NodoMetodo) nodoDerecho).ultimo);
                    }
                    break;
                case KLEENE:
                case INTERROGACION:
                    if (nodoIzquierdo instanceof NodoMetodo) {
                        this.anulable = true;
                        this.primero.addAll(((NodoMetodo) nodoIzquierdo).primero);
                        this.ultimo.addAll(((NodoMetodo) nodoIzquierdo).ultimo);
                    }
                    break;
                case MAS:
                    if (nodoIzquierdo instanceof NodoMetodo) {
                        this.anulable = ((NodoMetodo) nodoIzquierdo).anulable;
                        this.primero.addAll(((NodoMetodo) nodoIzquierdo).primero);
                        this.ultimo.addAll(((NodoMetodo) nodoIzquierdo).ultimo);
                    }
                    break;
                default:
                    break;
            }
        }
        return this;
    }

    public Object follow() {
        Object leftFollow  = this.izquierda instanceof NodoMetodo ? ((NodoMetodo) this.izquierda).follow() : null;
        Object rightFollow = this.derecha instanceof NodoMetodo ? ((NodoMetodo) this.derecha).follow() : null;
        if (null != this.type) {
            switch (this.type) {
                case AND:
                    assert leftFollow != null;
                    for (int item : ((NodoMetodo) leftFollow).ultimo) {
                        Leave       hoja         = new Leave();
                        NodoMetodo  nodo         = hoja.getLeave(item, leaves);
                        FollowTable tabla        = new FollowTable();
                        ArrayList   rightPrimero = new ArrayList();
                        rightPrimero = ((NodoMetodo) rightFollow).primero;
                        tabla.append(nodo.numero, nodo.lexema, rightPrimero, table);
                    }
                    break;
                case KLEENE:
                case MAS:
                    assert leftFollow != null;
                    for (int item : ((NodoMetodo) leftFollow).ultimo) {
                        Leave       hoja  = new Leave();
                        NodoMetodo  nodo  = hoja.getLeave(item, leaves);
                        FollowTable tabla = new FollowTable();
                        tabla.append(nodo.numero, nodo.lexema, ((NodoMetodo) leftFollow).primero, table);
                    }
                    break;
                default:
                    break;
            }
        }

        return this;
    }

    public String graficarArbol() {
        if ((this.izquierda == null) && (this.derecha == null)) {
            ArrayList nodo = new ArrayList();
            nodo.add(this);
            nodosNecesarios.add(nodo);
            return this.lexema;
        } else if ((this.izquierda != null) && (this.derecha == null)) {
            String    valorIzquierdo = ((NodoMetodo) this.izquierda).graficarArbol();
            ArrayList nodo           = new ArrayList();
            nodo.add(this);
            nodosNecesarios.add(nodo);
            return "(" + valorIzquierdo + this.lexema + ")";
        } else if ((this.izquierda != null) && (this.derecha != null)) {
            String    valorIzquierdo = ((NodoMetodo) this.izquierda).graficarArbol();
            String    valorDerecho   = ((NodoMetodo) this.derecha).graficarArbol();
            ArrayList nodo           = new ArrayList();
            nodo.add(this);
            nodosNecesarios.add(nodo);
            return "(" + valorIzquierdo + this.lexema + valorDerecho + ")";
        }
        return "Ocurrio un error";
    }
}
