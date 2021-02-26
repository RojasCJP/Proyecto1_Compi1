package mainPage;

import Analizadores.Analizadores;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.control.*;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.stage.FileChooser;
import javafx.stage.Stage;

import java.io.File;
import java.io.FileNotFoundException;
import java.lang.reflect.Field;
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
        String textoEntrada = "Analisis Lexico:\n\n\nAnalisis Sintactico:";
        Analizadores.lexer();
        this.Salida.setText(textoEntrada);
    }

    @FXML
    public void generarAutomata(MouseEvent event) {
        this.Salida.setText("se esta generando su automata");
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
        //todo tengo que genererar el archivo json en la carpeta ../Imagenes/Salida
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
            //todo tengo que hacer que muestre los archivos y no nombres que yo queme, para eso tengo que ver manejo de archivos creo
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

}
