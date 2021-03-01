package Arbol;

public class NumLeave {
//codigo visto en clase
    public int contenido;

    public NumLeave(String contenido) {
        this.contenido = clean(contenido) + 1;
    }

    public int getNum(){
        contenido -= 1;
        return contenido;
    }

    public int clean(String contenido) {
        return contenido.replace(".", "").replace("|", "").replace("*", "").replace("+", "").replace("?", "").length();
    }
}
