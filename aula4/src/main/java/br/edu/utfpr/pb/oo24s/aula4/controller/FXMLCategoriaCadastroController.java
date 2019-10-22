/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.edu.utfpr.pb.oo24s.aula4.controller;

import br.edu.utfpr.pb.oo24s.aula4.dao.CategoriaDao;
import br.edu.utfpr.pb.oo24s.aula4.model.Categoria;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author vinic
 */
public class FXMLCategoriaCadastroController implements Initializable {

    
    @FXML
    private Button buttonCancelar;
    @FXML
    private Button buttonSalvar;
    
    @FXML
    private TextField textId;
    @FXML
    private TextField textDescricao;
    
    private Stage dialogStage;
    
    private CategoriaDao categoriaDao;
    
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        this.categoriaDao = new CategoriaDao();
        
         this.buttonSalvar.setOnAction(
            (t) -> {
                save();
            }
        );
    }    
    
    public void setDialogStage(Stage dialogStage) {
        this.dialogStage = dialogStage;
    }
    
    public void setCategoria(Categoria categoria) {
        if (categoria.getId() != null) {
            textId.setText(categoria.getId().toString());
            textDescricao.setText(categoria.getDescricao());
        }
    }
    
    private void save() {
        Categoria categoria = new Categoria();
        categoria.setId( (textId.getText().isEmpty() ? null : Integer.parseInt(textId.getText()) ));
        categoria.setDescricao(textDescricao.getText());
        if (categoria.getId() == null) {
            categoriaDao.insert(categoria);
        } else {
            categoriaDao.update(categoria);
        }
        dialogStage.close();
    }
    
}
