package Arbol;

import java.util.ArrayList;

public class Leave {
    //codigo dado en clase
    public void addLeave(NodoMetodo nodo, ArrayList<NodoMetodo> leaves){
        leaves.add(nodo);
    }

    public NodoMetodo getLeave(int numLeave, ArrayList<NodoMetodo> leaves){
        for (NodoMetodo item : leaves) {
            if(item.numero == numLeave) return item;
        }
        return null;
    }

    public boolean isAccept(int numLeave, ArrayList<NodoMetodo> leaves){
        for (NodoMetodo item : leaves) {
            if(item.numero == numLeave) return item.acepta;
        }
        return false;
    }

}
