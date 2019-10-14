package br.edu.utfpr.pb.oo24s.aula3.controller;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;

public class FXMLPrincipalController implements Initializable {

    @FXML
    private TextField textField;
    @FXML
    private DatePicker datePicker;
    @FXML
    private CheckBox checkBox;
    
    @FXML
    private TextArea textArea;
    @FXML
    private Button button2;
    
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // textField.setText("TEXTO adicionado no controller!!! ");
        
        this.button2.setOnAction(
                (final ActionEvent e) -> {
                    actionButton2();
                }
        );
    }    
    
    @FXML
    private void actionButton1() {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle(".: Exemplo JavaFX :.");
        alert.setHeaderText("JavaFX");
        alert.setContentText(
                this.textField.getText() + "\n" + 
                this.datePicker.getValue() + "\n" + 
                (checkBox.isSelected() ? "Check: TRUE" : "Check: FALSE")
        );
        alert.showAndWait();
    }

    private void actionButton2() {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle(".: Exemplo JavaFX - Button 2 :.");
        alert.setHeaderText("JavaFX");
        alert.setContentText(
                this.textArea.getText()
        );
        alert.showAndWait();
    }
}
