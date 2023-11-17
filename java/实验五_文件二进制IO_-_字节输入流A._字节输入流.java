import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.input.DragEvent;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import javafx.scene.input.DragEvent;
import javafx.scene.input.Dragboard;
import javafx.scene.input.TransferMode;
import javafx.scene.control.Label;
import java.io.*;
import java.util.List;
import java.util.ArrayList;
import java.util.Arrays;

public class Main extends Application {
    TextArea textArea,message;
    File CurrentFile;
    @Override
    public void start(final Stage primaryStage) {
        message= new TextArea();//信息反馈
        message.setEditable(false);
        message.setPrefHeight(50);
        message.setStyle("-fx-background-color: black;");

        Button openButton = new Button("Open File");//直接打开文件
        openButton.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                FileChooser fileChooser = new FileChooser();
                File selectedFile = fileChooser.showOpenDialog(primaryStage);
                if (selectedFile != null) {
                    copyFile(selectedFile);
                }
            }
        });

        final TextField filePathTextField = new TextField();//路径输入
        filePathTextField.setPromptText("Enter file path");
        filePathTextField.setPrefHeight(55);

        Button openPathButton = new Button("Open Path");//根据输入路径打开文件
        openPathButton.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                String filePath = filePathTextField.getText();
                if (!filePath.isEmpty()) {
                    File file = new File(filePath);
                    if (file.exists()) {
                        copyFile(file);
                    } else {
                        setMessage("File not found!",2);
                    }
                }
            }
        });

        textArea = new TextArea();//文件内容显示页面
        textArea.setOnDragOver(new EventHandler<DragEvent>() {//拖拽实现
            @Override
            public void handle(DragEvent dragEvent) {
                if (dragEvent.getGestureSource() != textArea && dragEvent.getDragboard().hasFiles()) {
                    dragEvent.acceptTransferModes(TransferMode.COPY);
                }
                dragEvent.consume();
            }
        });

        textArea.setOnDragDropped(new EventHandler<DragEvent>() {
            @Override
            public void handle(DragEvent dragEvent) {
                Dragboard db = dragEvent.getDragboard();
                boolean success = false;
                if (db.hasFiles()) {
                    success = true;
                    File file = db.getFiles().get(0);
                    copyFile(file);
                }
                dragEvent.setDropCompleted(success);
                dragEvent.consume();
            }
        });

        Button saveButton = new Button("Save Changes");//保存
        saveButton.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                if (CurrentFile != null) {
                    saveChangesToFile(CurrentFile);
                } else {
                    setMessage("No file is currently open!",2);
                }
            }
        });

        Button createButton = new Button("Create file and write");//新建保存
        createButton.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                FileChooser fileChooser = new FileChooser();
                fileChooser.setTitle("保存");
                fileChooser.setInitialFileName("text.txt");
                File selectedFile = fileChooser.showSaveDialog(primaryStage);
                if (selectedFile != null) {
                    try {
                        FileWriter fileWriter = new FileWriter(selectedFile);
                        fileWriter.write(textArea.getText());
                        fileWriter.close();
                        setMessage("File created and content written successfully.", 3);
                    } catch (IOException e) {
                        setMessage("Error creating file: " + e.getMessage(), 1);
                    }
                }
            }
        });


        Text text=new Text("Message:");

        HBox hBox=new HBox();
        hBox.getChildren().addAll(text,message);

        HBox savebox=new HBox(6);
        savebox.getChildren().addAll(saveButton,createButton);

        VBox vbox = new VBox();
        vbox.setPadding(new Insets(8));
        vbox.getChildren().addAll(openButton, filePathTextField, openPathButton, textArea,savebox,hBox);

        BorderPane root = new BorderPane();
        root.setCenter(vbox);

        Scene scene = new Scene(root, 400, 300);
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    void copyFile(File file) {
        try {
            CurrentFile=file;
            String filename=file.getName();

            FileInputStream fileInputStream = new FileInputStream(file);
            InputStreamReader inputStreamReader = new InputStreamReader(fileInputStream);
            BufferedReader bufferedReader = new BufferedReader(inputStreamReader);

            StringBuilder content = new StringBuilder();
            String line;
            while ((line = bufferedReader.readLine()) != null) {
                content.append(line).append("\n");
            }

            textArea.setText(content.toString());

            bufferedReader.close();
            inputStreamReader.close();
            fileInputStream.close();

            if(check_(filename))
                setMessage("The file has been opened",3);
            else
                setMessage("The file might can't be identified",1);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    void saveChangesToFile(File file) {
        try {
            FileWriter fileWriter = new FileWriter(file);
            fileWriter.write(textArea.getText());
            fileWriter.close();
            setMessage("Changes saved to file.",3);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    public static void main(String[] args) {
        launch(args);
    }

    boolean check_(String filename){
        String extend=filename.substring(filename.lastIndexOf(".")+1);
        List<String> ex= Arrays.asList(
                "txt", "csv", "xml", "log", "html", "css", "js", "json",
                "md", "java", "c", "cpp", "h", "hpp", "py", "rb", "php",
                "sql", "ini", "cfg", "yaml", "yml", "bat", "sh", "ps1"
        );
        return  ex.contains(extend);
    }
    void setMessage(String string,int level){
        message.setText(string);
        switch (level){
            case 1:
                message.setStyle("-fx-text-fill : red;-fx-background-color: black;");
                break;
            case 2:
                message.setStyle("-fx-text-fill : yellow;-fx-background-color: black;");
                break;
            case 3:
                message.setStyle("-fx-text-fill : green;-fx-background-color: black;");
        }

    }
}
