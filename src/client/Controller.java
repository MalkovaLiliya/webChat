package client;

import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.layout.Priority;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;
import java.net.URL;
import java.util.ResourceBundle;

public class Controller implements Initializable {
    @FXML
    public TextField textFieldBottom;
    @FXML
    public TextArea textAreaCenter;
    @FXML
    public javafx.scene.layout.HBox hBox;

    private final  String IP_ADDRESS = "localhost";
    private final int PORT = 8189;

    private Socket socet;

    DataInputStream in;
    DataOutputStream out;

    public void sendMessage(ActionEvent actionEvent) {
        try {
            out.writeUTF(textFieldBottom.getText());
            textFieldBottom.clear();
            textFieldBottom.requestFocus();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        hBox.setHgrow(textFieldBottom, Priority.ALWAYS);
        Platform.runLater(() -> textFieldBottom.requestFocus());
        try {
            socet = new Socket(IP_ADDRESS, PORT);
            in = new DataInputStream(socet.getInputStream());
            out = new DataOutputStream(socet.getOutputStream());

            new Thread(new Runnable() {
                @Override
                public void run() {
                    try {
                    while (true) {

                        String str = in.readUTF();
                        if (str.equals("/end")) {
                            break;
                        }

                        textAreaCenter.appendText(str + "\n");
                    }
                        } catch (IOException e) {
                            e.printStackTrace();
                        } finally {
                            System.out.println("Мы отключились от сервера");
                            try {
                                socet.close();
                            } catch (IOException e) {
                                e.printStackTrace();
                            }
                        }
                }
            }).start();

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
