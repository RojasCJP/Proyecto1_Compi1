package Regex;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class NodoAFD {
    public Map     siguientes = new HashMap();
    public String  nombre;
    public boolean finalAFD;
//todo ya tengo los conjuntos solo falta hacer lo de las expresiones

    public NodoAFD(String nombre) {
        this.nombre = nombre;
        this.finalAFD = false;
    }
}
