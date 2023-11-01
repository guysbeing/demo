import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.layout.HBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.stage.Stage;

public class Main extends Application {

    @Override
    public void start(Stage primaryStage) {
        Label label1 = createLabel("black", Color.BLACK);
        Label label2 = createLabel("blue", Color.BLUE);
        Label label3 = createLabel("cyan", Color.CYAN);
        Label label4 = createLabel("green", Color.GREEN);
        Label label5 = createLabel("magnta", Color.MAGENTA);
        Label label6 = createLabel("orange", Color.ORANGE);

        HBox hbox=new HBox(10);
        hbox.setPadding(new Insets(10));
        hbox.getChildren().addAll(label1, label2, label3, label4, label5, label6);

        Scene scene = new Scene(hbox, 400, 300);

        primaryStage.setTitle("demo");
        primaryStage.setScene(scene);

        primaryStage.show();
    }

    Label createLabel(String text, Color color) {
        Label label = new Label(text);
        label.setTextFill(color);
        label.setStyle("-fx-background-color: white; -fx-border-color: yellow; -fx-border-width: 5px;");
        label.setFont(Font.font("Times New Roman", FontWeight.BOLD,20));
        return label;
    }

    public static void main(String[] args) {
        launch(args);
    }
}
