/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.edu.utfpr.pb.oo24s.aula4.controller;

import br.edu.utfpr.pb.oo24s.aula4.dao.ProdutoDao;
import br.edu.utfpr.pb.oo24s.aula4.model.Categoria;
import br.edu.utfpr.pb.oo24s.aula4.model.Produto;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.SelectionMode;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Modality;
import javafx.stage.Stage;

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
    
    @FXML
    private Button buttonNovo;
    
    @FXML
    private Button buttonEditar;
    
    @FXML
    private Button buttonRemover;
    
    
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        this.produtoDao = new ProdutoDao();
        
        this.tableDados.getSelectionModel().setSelectionMode(SelectionMode.SINGLE);
        
        setColumnProperties();
        
        loadData();
        
        buttonNovo.setOnAction(
                (t) -> {
                    newRecord(t);
                }
        );
        buttonEditar.setOnAction(
                (t) -> {
                    edit(t);
                }
        );
        buttonRemover.setOnAction(
                (t) -> {
                    delete(t);
                }
        );
        
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

    private void openForm(Produto produto, ActionEvent event) {
        try {
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(this.getClass().getResource("/fxml/FXMLProdutoCadastro.fxml"));
            AnchorPane page = (AnchorPane) loader.load();

            Stage dialogStage = new Stage();
            dialogStage.setTitle("Cadastro de Produtos");
            dialogStage.initModality(Modality.WINDOW_MODAL);
            dialogStage.initOwner(((Node) event.getSource()).getScene().getWindow());

            Scene scene = new Scene(page);
            dialogStage.setScene(scene);

            FXMLProdutoCadastroController produtoController = loader.getController();
            produtoController.setDialogStage(dialogStage);
            produtoController.setProduto(produto);

            dialogStage.showAndWait();

        } catch (Exception e) {
            e.printStackTrace();
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Erro!");
            alert.setHeaderText("Ocorreu um erro ao abrir a tela de cadastro!");
            alert.setContentText("Por favor, entre em contato com o suporte!");
            alert.showAndWait();
        }
        loadData();
    }

    private void newRecord(ActionEvent event) {
        this.openForm(new Produto(), event);
    }

    private void edit(ActionEvent event) {
        this.openForm(tableDados.getSelectionModel().getSelectedItem(), event);
    }

    private void delete(ActionEvent event) {
        int selectedIndex = tableDados.getSelectionModel().getSelectedIndex();
        if (selectedIndex >= 0) {
            try {
                produtoDao.delete(tableDados.getSelectionModel().getSelectedItem().getId());
                tableDados.getItems().remove(selectedIndex);
            } catch (Exception e) {
                e.printStackTrace();
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setTitle("Erro!");
                alert.setHeaderText("Ocorreu um erro ao remover o registro!");
                alert.setContentText("Por favor, entre em contato com o suporte!");
                alert.showAndWait();
            }
        }
    }
}
