package Arbol;

import Graficador.GraficadorArbol;

import java.util.ArrayList;

public class Metodo {
    //codigo visto en clase
    public ArrayList<NodoMetodo> leaves = new ArrayList<NodoMetodo>();
    public ArrayList<ArrayList>  table  = new ArrayList<ArrayList>();
    public String                regex;

    public String getRegex() {
        return regex;
    }

    //todo tengo que setear la regex con Nodo.notacionPolaca(); y despues de eso ya puedo llamar al metodo
    public void setRegex(String regex_en_polaca) {
        this.regex = regex_en_polaca;
    }


    public void metodoArbol() {
        this.regex = "." + this.regex + "#";
        Tree       tree = new Tree(this.regex, this.leaves, this.table);
        NodoMetodo root = tree.getRoot();
        //todo tengo que ponerle el nombre a la estructura como nodo nada mas, para que me de el puntero del objeto
        root.getNodo();
        ArrayList paraGraficar = root.nodosNecesarios;
        root.graficarArbol();
        GraficadorArbol graficador     = new GraficadorArbol();
        String          cuerpoGraphviz = graficador.cuerpoGraphviz(paraGraficar);
        graficador.crearDot(cuerpoGraphviz);
        root.follow();
        System.out.println("----------------------------------------------------------------TABLA DE SIGUIENTES----------------------------------------------------------------");
        FollowTable followTable = new FollowTable();
        followTable.printTable(table);
        TransitionTable transitionTable = new TransitionTable(root, table, leaves);
        System.out.println("----------------------------------------------------------------TABLA DE TRANSICIONES----------------------------------------------------------------");
        transitionTable.imprimirTabla();
        root.nodosNecesarios = new ArrayList();
    }

}
