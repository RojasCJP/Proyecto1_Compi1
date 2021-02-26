package Traductor;

import org.junit.jupiter.api.Test;

import java.lang.reflect.Array;
import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.*;

class TraductorTest {

    @Test
    public void test() {
        Traductor test      = new Traductor();
        boolean   resultado = test.inOperadores('"');
        assertEquals(true, resultado);
    }

    @Test
    public void test1() {
        Traductor test      = new Traductor();
        boolean   resultado = test.inOperadores('h');
        assertEquals(false, resultado);
    }

    @Test
    public void separador() {
        Traductor test = new Traductor();
        test.setEntrada(". {letra} * | \"_\" | {letra} {digito};");
        ArrayList<String> array = new ArrayList<String>();
        array.add(".");
        array.add("{");
        array.add("letra");
        array.add("}");
        array.add("*");
        array.add("|");
        array.add("\"");
        array.add("_");
        array.add("\"");
        array.add("|");
        array.add("{");
        array.add("letra");
        array.add("}");
        array.add("{");
        array.add("digito");
        array.add("}");
        array.add(";");
        ArrayList<String> resultado = test.separador();
        System.out.println(resultado);
        assertEquals(array, resultado);
    }

}