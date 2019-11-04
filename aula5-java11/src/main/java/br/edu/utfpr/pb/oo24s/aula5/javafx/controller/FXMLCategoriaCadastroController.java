package br.edu.utfpr.pb.oo24s.aula5.javafx.controller;

import br.edu.utfpr.pb.oo24s.aula5.javafx.dao.CategoriaDao;
import br.edu.utfpr.pb.oo24s.aula5.javafx.model.Categoria;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author Vinicius
 */
public class FXMLCategoriaCadastroController implements Initializable {

    @FXML
    private TextField textId;
    @FXML
    private TextField textDescricao;

    private CategoriaDao categoriaDao;
    private Stage stage;
    private Categoria categoria;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        this.categoriaDao = new CategoriaDao();
        this.categoria = new Categoria();
    }

    public void setDialogStage(Stage stage) {
        this.stage = stage;
    }

    @FXML
    private void cancel() {
        this.stage.close();
    }

    @FXML
    private void save() {
        categoria.setDescricao(
                textDescricao.getText());
        this.categoriaDao.save(categoria);
        
        this.stage.close();
    }

    public void setCategoria(Categoria categoria) {
        this.categoria = categoria;
        if (categoria.getId() != null) {
            this.textId.setText(categoria.getId().toString());
            this.textDescricao.setText(
                    categoria.getDescricao());
        }
    }
}
