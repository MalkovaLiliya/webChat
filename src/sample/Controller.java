package sample;

import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Priority;

import java.net.URL;
import java.util.ResourceBundle;

public class Controller implements Initializable {
    @FXML
    public TextField textFieldBottom;
    @FXML
    public TextArea textAreaCenter;
    @FXML
    public javafx.scene.layout.HBox hBox;

    public void sendMessage(ActionEvent actionEvent) {
        if (!textFieldBottom.getText().isEmpty()){
            textAreaCenter.appendText(textFieldBottom.getText() + "\n");}
        textFieldBottom.requestFocus();
        textFieldBottom.clear();
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        hBox.setHgrow(textFieldBottom, Priority.ALWAYS);
        Platform.runLater(() -> textFieldBottom.requestFocus());
    }
}
