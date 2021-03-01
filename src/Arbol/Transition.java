package Arbol;

public class Transition {
    //codigo visto en clase
    public String estadoInicial;
    public String transicion;
    public String estadoFinal;

    public Transition(String estadoInicial, String transicion, String estadoFinal) {
        this.estadoInicial = estadoInicial;
        this.transicion = transicion;
        this.estadoFinal = estadoFinal;
    }

    public boolean compare(String estadoInicial, String transicion) {
        return this.estadoInicial.equals(estadoInicial) && this.transicion.equals(transicion);
    }

    @Override
    public String toString() {
        return this.estadoInicial + "=>" + this.transicion + "=>" + this.estadoFinal;
    }
}
