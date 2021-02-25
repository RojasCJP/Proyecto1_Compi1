package mainPage;

import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;

import java.io.File;


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

    private boolean  menus        = false;
    private boolean  tree         = false;
    private MenuItem abrir        = new MenuItem("Abrir");
    private MenuItem guardar      = new MenuItem("Guardar");
    private MenuItem guardarComo  = new MenuItem("Guardar Como...");
    private MenuItem generar      = new MenuItem("Generar XML de Salida");
    private MenuItem arboles      = new MenuItem("Arboles");
    private MenuItem siguientes   = new MenuItem("Siguientes");
    private MenuItem transiciones = new MenuItem("Transiciones");
    private MenuItem automatas    = new MenuItem("Automatas");


    @FXML
    public void inicializar(MouseEvent event) {
        agregarMenus();
        mostrarTreeView();
    }

    @FXML
    public void analizarEntradas(MouseEvent event){
        String textoEntrada = this.Entrada.getText();
        this.Salida.setText(textoEntrada);
    }

    @FXML
    public void generarAutomata(MouseEvent event){
        this.Salida.setText("se esta generando su automata");
    }

    @FXML
    public void imagenAnterior(MouseEvent event){
        this.Salida.setText("pasa a la imagen anterior");
    }

    @FXML
    public void imagenSiguiente(MouseEvent event){
        this.Salida.setText("pasa a la siguiente imagen");
    }

    private void agregarMenus() {
        if (this.menus == false) {
            this.MenuArchivo.getItems().remove(0, 2);
            this.MenuImagen.getItems().remove(0, 2);
            this.MenuArchivo.getItems().addAll(abrir, guardar, guardarComo, generar);
            this.MenuImagen.getItems().addAll(arboles, siguientes, transiciones, automatas);
            this.menus = true;
        }
    }

    private void mostrarTreeView() {
        if (this.tree == false) {
            TreeItem root               = new TreeItem("Imagenes");
            TreeItem arboles            = new TreeItem("Arboles");
            TreeItem automatas          = new TreeItem("Automatas");
            TreeItem siguientes         = new TreeItem("Tabla de Siguientes");
            TreeItem transiciones       = new TreeItem("Tabla de Transiciones");
            File     arbolesFolder      = new File("src/Imagenes/Arboles");
            File     automatasFolder    = new File("src/Imagenes/Automatas");
            File     siguientesFolder   = new File("src/Imagenes/Tabla de Siguientes");
            File     transicionesFolder = new File("src/Imagenes/Tabla de Transiciones");
            File[]   listaArboles       = arbolesFolder.listFiles();
            File[]   listaAutomatas     = automatasFolder.listFiles();
            File[]   listaSiguientes    = siguientesFolder.listFiles();
            File[]   listaTransiciones  = transicionesFolder.listFiles();

            setChilds(listaArboles, arboles);
            setChilds(listaAutomatas, automatas);
            setChilds(listaSiguientes, siguientes);
            setChilds(listaTransiciones, transiciones);

            root.setExpanded(true);
            arboles.setExpanded(true);
            automatas.setExpanded(true);
            siguientes.setExpanded(true);
            transiciones.setExpanded(true);
            this.Archivos.setRoot(root);
            root.getChildren().addAll(arboles, automatas, siguientes, transiciones);
            //todo tengo que hacer que muestre los archivos y no nombres que yo queme, para eso tengo que ver manejo de archivos creo
            this.tree = true;

        }

    }

    private void setChilds(File[] lista, TreeItem item) {
        for (int i = 0; i < lista.length; i++) {
            TreeItem child = new TreeItem(lista[i].getName());
            item.getChildren().add(child);
        }
    }

}
