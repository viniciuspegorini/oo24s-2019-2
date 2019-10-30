/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.edu.utfpr.pb.oo24s.aula4.controller;

import br.edu.utfpr.pb.oo24s.aula4.dao.CategoriaDao;
import br.edu.utfpr.pb.oo24s.aula4.dao.ProdutoDao;
import br.edu.utfpr.pb.oo24s.aula4.model.Categoria;
import br.edu.utfpr.pb.oo24s.aula4.model.Produto;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author vinic
 */
public class FXMLProdutoCadastroController implements Initializable {

    @FXML
    private TextField textId;
    
    @FXML
    private TextField textNome;
    
    @FXML
    private TextField textValor;
    
    @FXML
    private ComboBox<Categoria> comboCategoria;
    
    @FXML
    private TextArea textDescricao;
    
    @FXML
    private Button buttonCancelar;
    
    @FXML
    private Button buttonSalvar;
    
    private Stage dialogStage;
    private CategoriaDao categoriaDao;
    private ProdutoDao produtoDao;
    private Produto produto;
    
    
    
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        this.produto = new Produto();
        this.categoriaDao = new CategoriaDao();
        this.produtoDao = new ProdutoDao();
        
        ObservableList<Categoria> categorias = FXCollections
                .observableArrayList(this.categoriaDao.getAll());
        
        this.comboCategoria.setItems(categorias);
        
        buttonCancelar.setOnAction(
                (t) -> {
                    cancel();
                }
        );
        
        buttonSalvar.setOnAction(
                (t) -> {
                    save();
                }
        );
    }    
    
    public void setDialogStage(Stage dialogStage) {
        this.dialogStage = dialogStage;
    }
    
    public void setProduto(Produto produto) {
        this.produto = produto;
        
        if (produto.getId() != null) {
            textId.setText( produto.getId().toString() );
            textNome.setText( produto.getNome() );
            textValor.setText( produto.getValor().toString() );
            comboCategoria.setValue( produto.getCategoria() );
            textDescricao.setText( produto.getDescricao() );
        }
    }
    
    private void cancel() {
        this.dialogStage.close();
    }
    
    private void save() {
        
        produto.setNome( textNome.getText() );
        produto.setValor( Double.parseDouble( textValor.getText() ));
        produto.setCategoria( comboCategoria.getSelectionModel().getSelectedItem() );
        produto.setDescricao( textDescricao.getText() );
        
        if ( produtoDao.isValid(produto) ) {
            if (produto.getId() == null) {
                produtoDao.insert(produto);
            } else {
                produtoDao.update(produto);
            }
            this.dialogStage.close();
        } else {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Erro!");
            alert.setHeaderText("Ocorreu um erro ao remover o registro!");
            alert.setContentText( this.produtoDao.getErrors(produto) );
            alert.showAndWait();
        }
    }
}
