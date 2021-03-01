package Arbol;

import Traductor.Traductor;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Stack;

public class Tree {
//codigo visto en clase
    private NodoMetodo root;

    public Tree(String regex, ArrayList<NodoMetodo> leaves, ArrayList<ArrayList> table) {
        //codigo visto en clase
        NumLeave  numLeave  = new NumLeave(regex);
        Stack     pila      = new Stack();
        Traductor traductor = new Traductor();
        traductor.setEntrada(regex);
        ArrayList<String> regexList = traductor.separador();
        Collections.reverse(regexList);

        for (String character : regexList) {
            switch (character) {
                case "|":
                    NodoMetodo izquierdoOr = (NodoMetodo) pila.pop();
                    NodoMetodo derechoOr = (NodoMetodo) pila.pop();
                    NodoMetodo nodoOr = new NodoMetodo(character, Types.OR, 0, izquierdoOr, derechoOr, leaves, table);
                    pila.push(nodoOr);
                    break;
                case ".":
                    NodoMetodo izquierdoAnd = (NodoMetodo) pila.pop();
                    NodoMetodo derechoAnd = (NodoMetodo) pila.pop();
                    NodoMetodo nodoAnd = new NodoMetodo(character, Types.AND, 0, izquierdoAnd, derechoAnd, leaves, table);
                    pila.push(nodoAnd);
                    break;
                case "*":
                    NodoMetodo hijoKleene = (NodoMetodo) pila.pop();
                    NodoMetodo nodoKleene = new NodoMetodo(character, Types.KLEENE, 0, hijoKleene, null, leaves, table);
                    pila.push(nodoKleene);
                    break;
                case "+":
                    NodoMetodo hijoMas = (NodoMetodo) pila.pop();
                    NodoMetodo nodoMas = new NodoMetodo(character, Types.MAS, 0, hijoMas, null, leaves, table);
                    pila.push(nodoMas);
                    break;
                case "?":
                    NodoMetodo hijoInterrogacion = (NodoMetodo) pila.pop();
                    NodoMetodo nodoInterrogacion = new NodoMetodo(character, Types.INTERROGACION, 0, hijoInterrogacion, null, leaves, table);
                    pila.push(nodoInterrogacion);
                    break;
                default:
                    NodoMetodo nodoHoja = new NodoMetodo(character, Types.HOJA, numLeave.getNum(), null, null, leaves, table);
                    pila.push(nodoHoja);
                    Leave leave = new Leave();
                    leave.addLeave(nodoHoja, leaves);
                    break;
            }
        }
        this.root = (NodoMetodo) pila.pop();
    }

    public NodoMetodo getRoot() {
        return root;
    }
}
