package br.edu.utfpr.pb.oo24s.aula4.controller;

import br.edu.utfpr.pb.oo24s.aula4.dao.CategoriaDao;
import br.edu.utfpr.pb.oo24s.aula4.model.Categoria;
import java.net.URL;
import java.util.ResourceBundle;
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
        list.addAll(categoriaDao.getAll());
        tableDados.setItems(list);
    }

    private void openForm(Categoria categoria, ActionEvent event) {
        try {
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(this.getClass().getResource("/fxml/FXMLCategoriaCadastro.fxml"));
            AnchorPane page = (AnchorPane) loader.load();

            Stage dialogStage = new Stage();
            dialogStage.setTitle("Cadastro de Categoria");
            dialogStage.initModality(Modality.WINDOW_MODAL);
            dialogStage.initOwner(((Node) event.getSource()).getScene().getWindow());

            Scene scene = new Scene(page);
            dialogStage.setScene(scene);

            FXMLCategoriaCadastroController categoriaController = loader.getController();
            categoriaController.setDialogStage(dialogStage);
            categoriaController.setCategoria(categoria);

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
        this.openForm(new Categoria(), event);
    }

    private void edit(ActionEvent event) {
        this.openForm(tableDados.getSelectionModel().getSelectedItem(), event);
    }

    private void delete(ActionEvent event) {
        int selectedIndex = tableDados.getSelectionModel().getSelectedIndex();
        if (selectedIndex >= 0) {
            try {
                categoriaDao.delete(tableDados.getSelectionModel().getSelectedItem().getId());
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
