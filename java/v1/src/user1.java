import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.net.Socket;
import java.util.Scanner;

public class user1 extends Application {
    private DataInputStream in;
    private DataOutputStream out;

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) {
        int port = 8000;
        String host = "localhost";
        Socket socket;

        try {
            socket = new Socket(host, port);
            in = new DataInputStream(socket.getInputStream());
            out = new DataOutputStream(socket.getOutputStream());

            final TextArea chatArea = new TextArea();
            chatArea.setEditable(false);

            final TextField messageField = new TextField();
            messageField.setPromptText("Type your message...");

            Button sendButton = new Button("Send");
            sendButton.setOnAction(new EventHandler<ActionEvent>() {
                @Override
                public void handle(ActionEvent event) {
                    chatArea.appendText("我："+messageField.getText()+"\n");
                    sendMessage(messageField.getText());
                    messageField.clear();
                }
            });

            VBox root = new VBox(10);
            root.setPadding(new Insets(10));
            root.getChildren().addAll(chatArea, messageField, sendButton);

            primaryStage.setScene(new Scene(root, 400, 300));
            primaryStage.setTitle("Chat Application");
            primaryStage.show();

            new Thread(new Runnable() {
                @Override
                public void run() {
                    while (true) {
                        try {
                            String message = in.readUTF();
                            chatArea.appendText(message + "\n");
                        } catch (Exception ex) {
                            ex.printStackTrace();
                        }
                    }
                }
            }).start();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void sendMessage(String message) {
        try {
            out.writeUTF(message);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}