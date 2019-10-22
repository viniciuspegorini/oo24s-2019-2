package br.edu.utfpr.pb.oo24s.aula4.controller;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.animation.FadeTransition;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.MenuItem;
import javafx.scene.layout.VBox;
import javafx.util.Duration;

public class FXMLPrincipalController implements Initializable {

    @FXML
    private MenuItem menuCategoria;
    @FXML
    private Button buttonCategoria;
    @FXML
    private Button buttonProduto;
    @FXML
    private VBox vboxPrincipal;
    
    
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        this.menuCategoria.setOnAction(
            (t) -> {
                loadCategoria();
            }
        );
        this.buttonCategoria.setOnAction(
            (t) -> {
                loadCategoria();
            }
        );
        
    }

    private void setDataPane(Node node) {
        /*
            Atualiza o VBox com um novo form (FXML) de acordo com o menu/botão
            acionado pelo usuário
        */
        vboxPrincipal.getChildren().setAll(node);
    }
    
    private VBox fadeAnimate(String url) throws IOException {
        VBox v = (VBox) FXMLLoader.load(this.getClass().getResource(url));
        FadeTransition ft = new FadeTransition(Duration.millis(1500));
        ft.setNode(v);
        ft.setFromValue(0.1);
        ft.setToValue(1);
        ft.setCycleCount(1);
        ft.setAutoReverse(false);
        ft.play();
        return v;
    }
    
    private void loadCategoria() {
        try {
            setDataPane( fadeAnimate("/fxml/FXMLCategoriaLista.fxml") );
        } catch (Exception e) {
            e.printStackTrace();
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle(" .: Aula 4 - JavaFX :. ");
            alert.setHeaderText("Atenção, ocorreu um erro!");
            alert.setContentText("Falha ao abrir a tela de categorias");
            alert.showAndWait();
        }
    }
}
