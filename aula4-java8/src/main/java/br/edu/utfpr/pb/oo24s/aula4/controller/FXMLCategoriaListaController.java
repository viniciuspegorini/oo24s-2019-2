package br.edu.utfpr.pb.oo24s.aula4.controller;

import br.edu.utfpr.pb.oo24s.aula4.dao.CategoriaDao;
import br.edu.utfpr.pb.oo24s.aula4.model.Categoria;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.SelectionMode;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;

/**
 * FXML Controller class
 *
 * @author vinic
 */
public class FXMLCategoriaListaController implements Initializable {

    private CategoriaDao categoriaDao;
    
    @FXML
    private Button buttonNovo;
    @FXML
    private Button buttonEditar;
    @FXML
    private Button buttonRemover;
    
    @FXML
    private TableView<Categoria> tableDados;
    @FXML
    private TableColumn<Categoria, Long> columnId;
    @FXML
    private TableColumn<Categoria, String> columnDescricao;
    
    private ObservableList<Categoria> list = FXCollections.observableArrayList();
    
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        this.categoriaDao = new CategoriaDao();
        
        tableDados.getSelectionModel().setSelectionMode(SelectionMode.SINGLE);
        
        setColumnProperties();
        
        loadData();
    }    

    private void setColumnProperties() {
        columnId.setCellValueFactory(new PropertyValueFactory<>("id"));
        columnDescricao.setCellValueFactory(new PropertyValueFactory<>("descricao"));
    }

    private void loadData() {
        list.clear();
        list.addAll( categoriaDao.getAll() );
        tableDados.setItems( list );
    }
    
}
