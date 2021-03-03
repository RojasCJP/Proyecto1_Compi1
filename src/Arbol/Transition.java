package Arbol;

import Graficador.GraficadorAFD;
import Graficador.GraficadorTablas;

import java.util.HashMap;
import java.util.Map;

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
        if(GraficadorTablas.transiciones.get(this.estadoInicial)==null){
        Map valores = new HashMap();
        valores.put(this.transicion, this.estadoFinal);
        GraficadorTablas.transiciones.put(this.estadoInicial, valores);
        }else{
            ((HashMap)GraficadorTablas.transiciones.get(this.estadoInicial)).put(this.transicion,this.estadoFinal);
        }
        if(this.transicion.equals("\\\"")){
        GraficadorAFD.cuerpo += this.estadoInicial + "->" + this.estadoFinal + "[label=\"" + this.transicion + "\"];\n";
        }else{
        GraficadorAFD.cuerpo += this.estadoInicial + "->" + this.estadoFinal + "[label=\"" + this.transicion.replaceAll("\"", "") + "\"];\n";
        }
        return this.estadoInicial + "->" + this.transicion + "->" + this.estadoFinal;
    }
}
