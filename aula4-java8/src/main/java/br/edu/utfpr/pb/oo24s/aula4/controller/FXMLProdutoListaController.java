/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.edu.utfpr.pb.oo24s.aula4.controller;

import br.edu.utfpr.pb.oo24s.aula4.dao.ProdutoDao;
import br.edu.utfpr.pb.oo24s.aula4.model.Produto;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.SelectionMode;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;

/**
 * FXML Controller class
 *
 * @author vinic
 */
public class FXMLProdutoListaController implements Initializable {

    
    @FXML
    private TableView<Produto> tableDados;
    @FXML
    private TableColumn<Produto, Long> columnId;
    @FXML
    private TableColumn<Produto, String> columnNome;
    @FXML
    private TableColumn<Produto, String> columnCategoriaDescricao;
    @FXML
    private TableColumn<Produto, Double> columnValor;

    private ObservableList<Produto> list = FXCollections.observableArrayList();
    
    private ProdutoDao produtoDao;
    
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        this.produtoDao = new ProdutoDao();
        
        this.tableDados.getSelectionModel().setSelectionMode(SelectionMode.SINGLE);
        
        setColumnProperties();
        
        loadData();
    }    

    private void setColumnProperties() {
        columnId.setCellValueFactory(new PropertyValueFactory<>("id"));
        columnNome.setCellValueFactory(new PropertyValueFactory<>("nome"));
        columnValor.setCellValueFactory(new PropertyValueFactory<>("valor"));
        
        columnCategoriaDescricao.setCellValueFactory( 
                    cellData -> new SimpleStringProperty( cellData.getValue().getCategoria().getDescricao()));
    }

    private void loadData() {
        list.clear();
        list.addAll(produtoDao.getAll());
        tableDados.setItems(list);
    }
    
}
