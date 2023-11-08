import javafx.application.Application;
import javafx.event.EventHandler;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Pane;
import javafx.scene.layout.StackPane;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import javafx.scene.paint.Color;

public class Main extends Application {

    public static void main(String[] args) {
        launch(args);
    }
    Label demo;
    String[] stringbox={"java is fun","java is powerful"};

    int current=0;
    @Override
    public void start(Stage primaryStage) {

        demo=new Label(stringbox[current]);
        demo.setFont(Font.font("roman time",50));

        StackPane op=new StackPane();
        op.getChildren().add(demo);
        op.setAlignment(demo, Pos.CENTER);

        final Scene scene = new Scene(op, 666, 666);

       scene.setOnMouseClicked(new EventHandler<MouseEvent>() {
           @Override
           public void handle(MouseEvent mouseEvent) {
               current=(current+1)%2;
               demo.setText(stringbox[current]);
           }
       });

        primaryStage.setScene(scene);
        primaryStage.show();
    }
}
