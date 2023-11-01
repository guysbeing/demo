import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class Main extends Application {


    @Override
    public void start(Stage primaryStage) {
        final Label wr=new Label();
        primaryStage.setTitle("demo");
        Button button = new Button("Please click me");
        button.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                System.out.println("Clicked");
                wr.setText("clicked");
            }
        });

        VBox vBox=new VBox();
        vBox.getChildren().addAll(button,wr);
        vBox.setAlignment(Pos.CENTER);
        primaryStage.setScene(new Scene(vBox, 300, 250));
        primaryStage.show();
    }
    public static void main(String[] args) {
        launch(args);
    }
}
