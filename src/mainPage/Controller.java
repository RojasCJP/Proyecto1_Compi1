package mainPage;

import Analizadores.Lexer;
import Analizadores.LexerCup;
import Analizadores.Sintax;
import Analizadores.Tokens;
import Arbol.Metodo;
import Arbol.Nodo;
import Graficador.GraficadorErrores;
import Graficador.GraficadorThompson;
import java_cup.runtime.Symbol;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.control.MenuButton;
import javafx.scene.control.TextArea;
import javafx.scene.control.TreeItem;
import javafx.scene.control.TreeView;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.stage.FileChooser;
import javafx.stage.Stage;

import java.io.*;
import java.util.ArrayList;
import java.util.Scanner;


public class Controller {
    @FXML
    private MenuButton MenuArchivo;
    @FXML
    private MenuButton MenuImagen;
    @FXML
    private ImageView  imagenes;
    @FXML
    private TreeView   Archivos;
    @FXML
    private TextArea   Entrada;
    @FXML
    private TextArea   Salida;

    private static boolean     menus = false;
    private static boolean     tree  = false;
    private        FileChooser fileChooser;
    private        Stage       stage;

    @FXML
    public void inicializar(MouseEvent event) {
        mostrarTreeView();
        this.stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
    }

    @FXML
    public void analizarEntradas(MouseEvent event) {
        try {
            ArrayList resultadoLexico = analizarLexico();
            //este arraylist tiene datos importantes del analizador lexico revisarlo si se necesita algo
            analizarSintactico();

        } catch (IOException e) {
            e.printStackTrace();
        }
        GraficadorErrores graficadorErrores = new GraficadorErrores();
        graficadorErrores.crearDot();
    }

    @FXML
    public void refrescarTreeView(MouseEvent event) {
        this.tree = false;
    }

    @FXML
    public void imagenAnterior(MouseEvent event) {
        this.Salida.setText("pasa a la imagen anterior");
    }

    @FXML
    public void imagenSiguiente(MouseEvent event) {
        this.Salida.setText("pasa a la siguiente imagen");
    }

    @FXML
    private void abrirMethod(ActionEvent event) {
        String data = "";
        try {
            fileChooser = new FileChooser();
            fileChooser.setTitle("Abrir documento");
            File    selectedFile = fileChooser.showOpenDialog(this.stage);
            Scanner fileScanner  = new Scanner(selectedFile);
            while (fileScanner.hasNextLine()) {
                data += fileScanner.nextLine() + "\n";
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        Entrada.setText(data);

    }

    @FXML
    private void guardarMethod(ActionEvent event) {
        //todo tego que hacer el metodo para ver si ya existia el archivo y guardarlo
    }

    @FXML
    private void guardarComoMethod(ActionEvent event) {
        //todo tengo hacer que pueda guardar el archivo en algun folder
    }

    @FXML
    private void generarMethod(ActionEvent event) {
        try {
            ArrayList resultadoLexico = analizarLexicoGenerar();
            //este arraylist tiene datos importantes del analizador lexico revisarlo si se necesita algo
            analizarSintacticoGenerar();

        } catch (IOException e) {
            e.printStackTrace();
        }
        GraficadorErrores graficadorErrores = new GraficadorErrores();
        graficadorErrores.crearDot();
    }

    private void mostrarTreeView() {
        if (this.tree == false) {
            TreeItem root         = new TreeItem("FOLDERS");
            TreeItem arboles      = new TreeItem("ARBOLES");
            TreeItem afnd         = new TreeItem("AFND");
            TreeItem siguientes   = new TreeItem("SIGUIENTES");
            TreeItem transiciones = new TreeItem("TRANSICIONES");
            TreeItem afd          = new TreeItem("AFD");
            TreeItem errores      = new TreeItem("ERRORES");
            TreeItem salidas      = new TreeItem("SALIDAS");

            File arbolesFolder      = new File("src/Imagenes/ARBOLES_201900289");
            File afndFolder         = new File("src/Imagenes/AFND_201900289");
            File siguientesFolder   = new File("src/Imagenes/SIGUIENTES_201900289");
            File transicionesFolder = new File("src/Imagenes/TRANSICIONES_201900289");
            File afdFolder          = new File("src/Imagenes/AFD_201900289");
            File erroresFolder      = new File("src/Imagenes/ERRORES_201900289");
            File salidasFolder      = new File("src/Imagenes/SALIDAS_201900289");

            File[] listaArboles      = arbolesFolder.listFiles();
            File[] listaAfnd         = afndFolder.listFiles();
            File[] listaSiguientes   = siguientesFolder.listFiles();
            File[] listaTransiciones = transicionesFolder.listFiles();
            File[] listaAfd          = afdFolder.listFiles();
            File[] listaErrores      = erroresFolder.listFiles();
            File[] listaSalidas      = salidasFolder.listFiles();

            setChilds(listaArboles, arboles);
            setChilds(listaAfnd, afnd);
            setChilds(listaSiguientes, siguientes);
            setChilds(listaTransiciones, transiciones);
            setChilds(listaAfd, afd);
            setChilds(listaErrores, errores);
            setChilds(listaSalidas, salidas);


            root.setExpanded(true);
            arboles.setExpanded(true);
            afnd.setExpanded(true);
            siguientes.setExpanded(true);
            transiciones.setExpanded(true);
            afd.setExpanded(true);
            errores.setExpanded(true);
            salidas.setExpanded(true);


            this.Archivos.setRoot(root);
            root.getChildren().addAll(arboles, afnd, siguientes, transiciones, afd, errores, salidas);
            this.tree = true;

        }

    }

    private void setChilds(File[] lista, TreeItem item) {
        if (lista != null) {
            for (int i = 0; i < lista.length; i++) {
                TreeItem child = new TreeItem(lista[i].getName());
                item.getChildren().add(child);
            }
        }

    }

    //este metodo se usa para analizar entradas
    private ArrayList analizarLexico() throws IOException {
        int       contador  = 1;
        int       columna   = 1;
        String    expresion = Entrada.getText();
        Lexer     lexer     = new Lexer(new StringReader(expresion));
        String    resultado = "Analizador Lexico\n LINEA 1\n";
        ArrayList tokens    = new ArrayList();
        while (true) {
            Tokens token = lexer.yylex();
            if (token == null) {
                Salida.setText(resultado);
                return tokens;
            }
            switch (token) {
                case CONJ:
                    resultado += "\t\t>>Palabra reservada\t\t" + lexer.lexeme + "\n";
                    columna += lexer.lexeme.length();
                    break;
                case Identificador:
                    resultado += "\t\t>>Identificador\t\t" + lexer.lexeme + "\n";
                    columna += lexer.lexeme.length();
                    break;
                case Numero:
                    resultado += "\t\t>>Numero\t\t" + lexer.lexeme + "\n";
                    columna += lexer.lexeme.length();
                    break;
                case Llave_Abre:
                    resultado += "\t\t>>Token Llave Inicio\t\t" + lexer.lexeme + "\n";
                    columna += lexer.lexeme.length();
                    break;
                case Llave_Cierra:
                    resultado += "\t\t>>Token Llave Final\t\t" + lexer.lexeme + "\n";
                    columna += lexer.lexeme.length();
                    break;
                case Guion:
                    resultado += "\t\t>>Conjunto\t\t" + lexer.lexeme + "\n";
                    columna += lexer.lexeme.length();
                    break;
                case Punto:
                    resultado += "\t\t>>Token Punto\t\t" + lexer.lexeme + "\n";
                    columna += lexer.lexeme.length();
                    break;
                case Coma:
                    resultado += "\t\t>>Token Coma\t\t" + lexer.lexeme + "\n";
                    columna += lexer.lexeme.length();
                    break;
                case Punto_Coma:
                    resultado += "\t\t>>Token Punto y Coma\t\t" + lexer.lexeme + "\n";
                    columna += lexer.lexeme.length();
                    break;
                case Dos_Puntos:
                    resultado += "\t\t>>Token Dos Puntos\t\t" + lexer.lexeme + "\n";
                    columna += lexer.lexeme.length();
                    break;
                case Or:
                    resultado += "\t\t>>Token de Disyuncion\t\t" + lexer.lexeme + "\n";
                    columna += lexer.lexeme.length();
                    break;
                case Asterizco:
                    resultado += "\t\t>>Token de 0 o mas Veces\t\t" + lexer.lexeme + "\n";
                    columna += lexer.lexeme.length();
                    break;
                case Suma:
                    resultado += "\t\t>>Token de 1 o mas Veces\t\t" + lexer.lexeme + "\n";
                    columna += lexer.lexeme.length();
                    break;
                case Interrogacion:
                    resultado += "\t\t>>Toke de 1 o 0 Veces\t\t" + lexer.lexeme + "\n";
                    columna += lexer.lexeme.length();
                    break;
                case Comentario_Multi_Abre:
                    resultado += "\t\t>>Comienza un Comentario\t\t" + lexer.lexeme + "\n";
                    columna += lexer.lexeme.length();
                    break;
                case Comentario_Multi_Cierra:
                    resultado += "\t\t>>Termina un Comentario\t\t" + lexer.lexeme + "\n";
                    columna += lexer.lexeme.length();
                    break;
                case Porcentaje:
                    resultado += "\t\t>>Token de Porcentaje\t\t" + lexer.lexeme + "\n";
                    columna += lexer.lexeme.length();
                    break;
                case Linea:
                    contador++;
                    resultado += "LINEA" + contador + "\n";
                    columna = 0;
                    break;
                case Cadena:
                    resultado += "\t\t>>Cadena\t\t" + lexer.lexeme + "\n";
                    columna += lexer.lexeme.length();
                    break;
                case Asignacion:
                    resultado += "\t\t>>Token de Asignacion\t\t" + lexer.lexeme + "\n";
                    columna += lexer.lexeme.length();
                    break;
                case Especial:
                    resultado += "\t\t>>Token Especial\t\t" + lexer.lexeme + "\n";
                    columna += lexer.lexeme.length();
                    break;
                case ERROR:
                    resultado += "SE HA ENCONTRADO UN ERROR EN LA LINEA:\t\t" + contador + "\t COLUMNA: " + columna + "\tERROR ENCONTRADO DESPUES DE:\t\t" + lexer.lexeme + "\n";
                    GraficadorErrores.errores += "<tr><td>" + GraficadorErrores.contador + "</td><td>Lexico</td><td>El caracter siguiente a " + lexer.lexeme + " no es del lenguaje</td><td>" + contador + "</td><td>" + columna + "</td></tr>\n";
                    GraficadorErrores.contador++;
                    break;
            }
            String[] conjuntoTokens = {token.name(), lexer.lexeme};
            tokens.add(conjuntoTokens);
        }
    }

    //este metodo se usa para analizar entradas
    private void analizarSintactico() {
        String    expresion            = Entrada.getText();
        String    resultado            = Salida.getText();
        Sintax    sintax               = new Sintax(new LexerCup(new StringReader(expresion)));
        ArrayList expresionesRegulares = new ArrayList();
        try {
            sintax.parse();
            resultado += "ANALISIS SINTACTICO REALIZADO CORRECTAMENTE";
            expresionesRegulares = sintax.meVaAServir;
            for (int i = 0; i < expresionesRegulares.size(); i++) {
                Metodo metodo      = new Metodo();
                Nodo   nodo        = (Nodo) expresionesRegulares.get(i);
                String regexPolaca = nodo.notacionPolaca();
                metodo.setRegex(regexPolaca);
                metodo.metodoArbol();
            }
        } catch (Exception e) {
            Symbol syms = sintax.getS();
            resultado += "ERROR DE SINTAXIS EN LA LINEA: " + (syms.right + 1) + " COLUMNA: " + (syms.left + 1) + ", TEXTO: \"" + syms.value + "\"";
            GraficadorErrores.errores += "<tr><td>" + GraficadorErrores.contador + "</td><td>Sintactico</td><td>El caracter " + syms.value + " no se esperaba</td><td>" + syms.right + "</td><td>" + syms.left + "</td></tr>\n";
            GraficadorErrores.contador++;
        }
        Salida.setText(resultado);
    }

    //este metodo se usa para generar automata
    private ArrayList analizarLexicoGenerar() throws IOException {
        int       contador  = 1;
        int       columna   = 1;
        String    expresion = Entrada.getText();
        Lexer     lexer     = new Lexer(new StringReader(expresion));
        String    resultado = "Analizador Lexico\n LINEA 1\n";
        ArrayList tokens    = new ArrayList();
        while (true) {
            Tokens token = lexer.yylex();
            if (token == null) {
                Salida.setText(resultado);
                return tokens;
            }
            switch (token) {
                case CONJ:
                    resultado += "\t\t>>Palabra reservada\t\t" + lexer.lexeme + "\n";
                    columna += lexer.lexeme.length();
                    break;
                case Identificador:
                    resultado += "\t\t>>Identificador\t\t" + lexer.lexeme + "\n";
                    columna += lexer.lexeme.length();
                    break;
                case Numero:
                    resultado += "\t\t>>Numero\t\t" + lexer.lexeme + "\n";
                    columna += lexer.lexeme.length();
                    break;
                case Llave_Abre:
                    resultado += "\t\t>>Token Llave Inicio\t\t" + lexer.lexeme + "\n";
                    columna += lexer.lexeme.length();
                    break;
                case Llave_Cierra:
                    resultado += "\t\t>>Token Llave Final\t\t" + lexer.lexeme + "\n";
                    columna += lexer.lexeme.length();
                    break;
                case Guion:
                    resultado += "\t\t>>Conjunto\t\t" + lexer.lexeme + "\n";
                    columna += lexer.lexeme.length();
                    break;
                case Punto:
                    resultado += "\t\t>>Token Punto\t\t" + lexer.lexeme + "\n";
                    columna += lexer.lexeme.length();
                    break;
                case Coma:
                    resultado += "\t\t>>Token Coma\t\t" + lexer.lexeme + "\n";
                    columna += lexer.lexeme.length();
                    break;
                case Punto_Coma:
                    resultado += "\t\t>>Token Punto y Coma\t\t" + lexer.lexeme + "\n";
                    columna += lexer.lexeme.length();
                    break;
                case Dos_Puntos:
                    resultado += "\t\t>>Token Dos Puntos\t\t" + lexer.lexeme + "\n";
                    columna += lexer.lexeme.length();
                    break;
                case Or:
                    resultado += "\t\t>>Token de Disyuncion\t\t" + lexer.lexeme + "\n";
                    columna += lexer.lexeme.length();
                    break;
                case Asterizco:
                    resultado += "\t\t>>Token de 0 o mas Veces\t\t" + lexer.lexeme + "\n";
                    columna += lexer.lexeme.length();
                    break;
                case Suma:
                    resultado += "\t\t>>Token de 1 o mas Veces\t\t" + lexer.lexeme + "\n";
                    columna += lexer.lexeme.length();
                    break;
                case Interrogacion:
                    resultado += "\t\t>>Toke de 1 o 0 Veces\t\t" + lexer.lexeme + "\n";
                    columna += lexer.lexeme.length();
                    break;
                case Comentario_Multi_Abre:
                    resultado += "\t\t>>Comienza un Comentario\t\t" + lexer.lexeme + "\n";
                    columna += lexer.lexeme.length();
                    break;
                case Comentario_Multi_Cierra:
                    resultado += "\t\t>>Termina un Comentario\t\t" + lexer.lexeme + "\n";
                    columna += lexer.lexeme.length();
                    break;
                case Porcentaje:
                    resultado += "\t\t>>Token de Porcentaje\t\t" + lexer.lexeme + "\n";
                    columna += lexer.lexeme.length();
                    break;
                case Linea:
                    contador++;
                    resultado += "LINEA" + contador + "\n";
                    columna = 0;
                    break;
                case Cadena:
                    resultado += "\t\t>>Cadena\t\t" + lexer.lexeme + "\n";
                    columna += lexer.lexeme.length();
                    break;
                case Asignacion:
                    resultado += "\t\t>>Token de Asignacion\t\t" + lexer.lexeme + "\n";
                    columna += lexer.lexeme.length();
                    break;
                case Especial:
                    resultado += "\t\t>>Token Especial\t\t" + lexer.lexeme + "\n";
                    columna += lexer.lexeme.length();
                    break;
                case ERROR:
                    resultado += "SE HA ENCONTRADO UN ERROR EN LA LINEA:\t\t" + contador + "\t COLUMNA: " + columna + "\tERROR ENCONTRADO DESPUES DE:\t\t" + lexer.lexeme + "\n";
                    GraficadorErrores.errores += "<tr><td>" + GraficadorErrores.contador + "</td><td>Lexico</td><td>El caracter siguiente a " + lexer.lexeme + " no es del lenguaje</td><td>" + contador + "</td><td>" + columna + "</td></tr>\n";
                    GraficadorErrores.contador++;
                    break;
            }
            String[] conjuntoTokens = {token.name(), lexer.lexeme};
            tokens.add(conjuntoTokens);
        }
    }

    //este metodo se usa para generar automata
    private void analizarSintacticoGenerar() {
        String    expresion            = Entrada.getText();
        String    resultado            = Salida.getText();
        Sintax    sintax               = new Sintax(new LexerCup(new StringReader(expresion)));
        ArrayList expresionesRegulares = new ArrayList();
        try {
            sintax.parse();
            resultado += "ANALISIS SINTACTICO REALIZADO CORRECTAMENTE";
        } catch (Exception e) {
            Symbol syms = sintax.getS();
            resultado += "ERROR DE SINTAXIS EN LA LINEA: " + (syms.right + 1) + " COLUMNA: " + (syms.left + 1) + ", TEXTO: \"" + syms.value + "\"";
            GraficadorErrores.errores += "<tr><td>" + GraficadorErrores.contador + "</td><td>Sintactico</td><td>El caracter " + syms.value + " no se esperaba</td><td>" + syms.right + "</td><td>" + syms.left + "</td></tr>\n";
            GraficadorErrores.contador++;
        }
            expresionesRegulares = sintax.meVaAServir;
            for (int i = 0; i < expresionesRegulares.size(); i++) {
                Metodo metodo      = new Metodo();
                Nodo   nodo        = (Nodo) expresionesRegulares.get(i);
                GraficadorThompson graficadorThompson = new GraficadorThompson(nodo);
                graficadorThompson.crearDot();
                String regexPolaca = nodo.notacionPolaca();
                metodo.setRegex(regexPolaca);
                metodo.metodoArbol();
            }
        Salida.setText(resultado);
    }
}
