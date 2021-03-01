package Arbol;

import com.sun.javafx.tk.FontLoader;

import java.util.ArrayList;

public class TransitionTable {
    //codigo visto en clase
    public ArrayList<ArrayList> estados;
    public int                  cuenta;

    public TransitionTable(NodoMetodo root, ArrayList tabla, ArrayList<NodoMetodo> leaves) {
        this.estados = new ArrayList<>();
        ArrayList datos = new ArrayList();
        datos.add("S0");
        datos.add(root.primero);
        datos.add(new ArrayList());
        datos.add(false);
        this.estados.add(datos);
        this.cuenta = 1;
        for (int i = 0; i < this.estados.size(); i++) {
            ArrayList          estado    = this.estados.get(i);
            ArrayList<Integer> elementos = (ArrayList) estado.get(1);

            for (int hoja : elementos) {
                FollowTable tablaSiguientes = new FollowTable();
                ArrayList   siguienteLexema = (ArrayList) tablaSiguientes.next(hoja, tabla).clone();
                boolean     existe          = false;
                String      found           = "";

                for (ArrayList e : estados) {
                    if (e.get(1).equals(siguienteLexema.get(1))) {
                        existe = true;
                        found = (String) e.get(0);
                        break;
                    }
                }
                if (!existe) {
                    Leave hojas = new Leave();
                    if (hojas.isAccept(hoja, leaves)) {
                        estado.set(3, true);
                    }
                    if (siguienteLexema.get(0).equals("")) {
                        continue;
                    }
                    ArrayList nuevo = new ArrayList();
                    nuevo.add("S" + cuenta);
                    nuevo.add(siguienteLexema.get(1));
                    nuevo.add(new ArrayList());
                    nuevo.add(false);

                    Transition transition = new Transition(estado.get(0) + "", siguienteLexema.get(0) + "", nuevo.get(0) + "");
                    ((ArrayList) estado.get(2)).add(transition);
                    cuenta++;
                    estados.add(nuevo);
                } else {
                    Leave hojas = new Leave();
                    if (hojas.isAccept(hoja, leaves)) {
                        estado.set(3, true);
                    }
                    boolean transition_exist = false;
                    for (Object transition : (ArrayList) estado.get(2)) {
                        Transition t = (Transition) transition;
                        if (t.compare(estado.get(0) + "", siguienteLexema.get(0) + "")) {
                            transition_exist = true;
                            break;
                        }
                    }
                    if (!transition_exist) {
                        Transition trans = new Transition(estado.get(0) + "", siguienteLexema.get(0) + "", found + "");
                        ((ArrayList) estado.get(2)).add(trans);
                    }
                }
            }
        }
    }

    public void imprimirTabla() {
        for (ArrayList estado : estados) {
            String transicion = "[";
            for (Object tr : (ArrayList) estado.get(2)) {
                Transition t = (Transition) tr;
                transicion += t.toString() + ",";
            }
            transicion += "]";
            transicion = transicion.replace(", ]", "]");
            System.out.println(estado.get(0) + " " + estado.get(1) + " " + transicion + " " + estado.get(3));
        }
    }
}
