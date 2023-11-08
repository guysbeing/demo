import javafx.application.Application;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Pane;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import javafx.scene.paint.Color;

public class Main extends Application {

    static final int onemove = 10;

    public static void main(String[] args) {
        launch(args);
    }
    Text demo = new Text("A");
    double dX = 100;
    double dY = 100;

    @Override
    public void start(Stage primaryStage) {

        demo.setFont(Font.font("roman time",20));
        demo.setX(dX);
        demo.setY(dY);

        final Pane pane = new Pane();
        pane.getChildren().add(demo);

        final Scene scene = new Scene(pane, 666, 666);

        scene.setOnMouseDragged(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent mouseEvent) {
                dX=mouseEvent.getX();
                dY=mouseEvent.getY();
                demo.setX(dX);
                demo.setY(dY);
                Color color = Color.rgb((int)(Math.random() * 256), (int)(Math.random() * 256), (int)(Math.random() * 256));
                Color backg=color.invert();
                demo.setFill(color);
                scene.setFill(backg);
            }
        });

        primaryStage.setScene(scene);
        primaryStage.show();
    }
}
