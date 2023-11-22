import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.input.*;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import javafx.scene.input.DragEvent;
import javafx.scene.control.Label;
import java.io.*;
import java.util.List;
import java.util.ArrayList;
import java.util.Arrays;

public class Main extends Application {
    TextArea textArea,message;
    File CurrentFile;
    private int step;
    private int ROWS ;
    private int COLS ;
    private char[][] maze;
    private int playerRow;
    private int playerCol;

    private MessageStore history=new MessageStore();

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
                else {
                    setMessage("File not found!",2);
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
        String fontStyles = "-fx-font-family: monospace; -fx-font-size: 12px;";
        textArea.setStyle(fontStyles);
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
                    if (CurrentFile.exists()) {
                        saveChangesToFile(CurrentFile);
                    }
                    else {
                        setMessage("No such a file!",2);
                    }
                }
                else {
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

        final Button gameButton=new Button("play");
        gameButton.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                gameButton.requestFocus();
                resetGame();
            }
        });

        HBox savebox=new HBox(6);
        savebox.getChildren().addAll(saveButton,createButton,gameButton);

        Text text=new Text("Message:");
        HBox hBox=new HBox();
        hBox.getChildren().addAll(text,message);

        VBox vbox = new VBox();
        vbox.setPadding(new Insets(8));
        vbox.getChildren().addAll(openButton, filePathTextField, openPathButton, textArea,savebox,hBox);

        BorderPane root = new BorderPane();
        root.setCenter(vbox);

        Scene scene = new Scene(root, 400, 300);
        scene.setOnKeyPressed(new EventHandler<KeyEvent>() {
            @Override
            public void handle(KeyEvent keyEvent) {
                handleKeyPress(keyEvent.getCode());
                System.out.println(keyEvent.getCode());
            }
        });
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    void copyFile(File file) {// copy txt to the textarea
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
                setMessage("The file might can't be identified",2);
        } catch (IOException e) {
            setMessage("Error copying file: " + e.getMessage(), 1);
        }
    }
    void saveChangesToFile(File file) {
        try {
            FileWriter fileWriter = new FileWriter(file);
            fileWriter.write(textArea.getText());
            fileWriter.close();
            setMessage("Changes saved to file.",3);
        } catch (IOException e) {
            setMessage("Error saving file: " + e.getMessage(), 1);
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

    //以下是游戏代码


    public void resetGame() {
        String text = textArea.getText();
        maze = parseMaze(text);
        for (int row = 0; row < ROWS; row++) {
            for (int col = 0; col < COLS; col++) {
                System.out.println(row+col+maze[row][col]);
                if (maze[row][col] == 'S') {
                    playerRow = row;
                    playerCol = col;
                }
            }
        }
        step=0;
        history=new MessageStore();
        updateTextArea();
    }

    public void updateTextArea() {
        StringBuilder sb = new StringBuilder();
        for (int row = 0; row < ROWS; row++) {
            for (int col = 0; col < COLS; col++) {
                sb.append(maze[row][col]);
            }
            sb.append("\n");
        }
        textArea.setText(sb.toString());
    }

    public void handleKeyPress(KeyCode keyCode) {
        int newRow = playerRow;
        int newCol = playerCol;
        System.out.println(newCol);
        System.out.println(newRow);

        switch (keyCode) {
            case W:
            case UP:
                history.storeMessage("↑");
                newRow--;
                break;
            case S:
            case DOWN:
                history.storeMessage("↓");
                newRow++;
                break;
            case A:
            case LEFT:
                history.storeMessage("←");
                newCol--;
                break;
            case D:
            case RIGHT:
                history.storeMessage("→");
                newCol++;
                break;
            default:
                return;
        }
        checkGameResult(newRow, newCol);
        if (isValidMove(newRow, newCol)) {
            step++;
            maze[playerRow][playerCol] = ' ';
            playerRow = newRow;
            playerCol = newCol;
            maze[playerRow][playerCol] = 'S';
            updateTextArea();
        }
    }

    public char[][] parseMaze(String text) {
        String[] lines = text.split("\n");
        System.err.println(text);
        if (lines.length < 2) {
            setMessage("Invalid maze format.",1);
            return null;
        }

        ROWS = Integer.parseInt(lines[0]);
        COLS = Integer.parseInt(lines[1]);

        if (lines.length != ROWS + 2) {
            setMessage("Invalid maze format.",1);
            return null;
        }
        char[][] map = new char[ROWS+10][COLS+10];

        for (int row = 0; row < ROWS; row++) {
            String line = lines[row + 2];

            for (int col = 0; col < COLS; col++) {
                map[row][col] = line.charAt(col);
            }
        }
        setMessage("Game begins",3);
        return map;
    }

    public boolean isValidMove(int row, int col) {
        return row >= 0 && row < ROWS && col >= 0 && col < COLS && maze[row][col] != '#';
    }

    public void checkGameResult(int row, int col) {
        if (maze[row][col] == 'x') {
            setMessage("Congratulations!step:"+step,3);
        }
    }

    public class MessageStore {
        private List<String> messageList;

        public MessageStore() {
            this.messageList = new ArrayList<>();
        }

        public void storeMessage(String message) {
            messageList.add(message);
            if (messageList.size() > 10) {
                messageList.remove(0);
            }
            setMessage(history.toString()+"step:"+step,3);
        }

        @Override
        public String toString() {
            StringBuilder sb = new StringBuilder();
            for (int i = messageList.size()-1; i >= 0; i--) {
                sb.append(messageList.get(i));
            }
            return sb.toString();
        }
    }
    void setMessage(String string,int level){// 反馈区
        message.setText(string);
        switch (level){
            case 1:
                message.setStyle("-fx-text-fill : red;-fx-background-color: black;");
                break;
            case 2:
                message.setStyle("-fx-text-fill : yellow;-fx-background-color: black;");
                break;
            case 3:
                message.setStyle("-fx-text-fill : Chartreuse ;-fx-background-color: black;");
        }
    }
}
