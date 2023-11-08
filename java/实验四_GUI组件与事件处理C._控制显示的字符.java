import javafx.application.Application;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.Pane;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import javafx.stage.Stage;

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

        Pane pane = new Pane();
        pane.getChildren().add(demo);

        Scene scene = new Scene(pane, 666, 666);

        scene.setOnKeyPressed(new EventHandler<KeyEvent>() {
            @Override
            public void handle(KeyEvent keyEvent) {
                KeyCode keyCode = keyEvent.getCode();
                switch (keyCode) {
                    case UP:
                        dY -= onemove;
                        break;
                    case DOWN:
                        dY += onemove;
                        break;
                    case LEFT:
                        dX -= onemove;
                        break;
                    case RIGHT:
                        dX += onemove;
                        break;
                }

                demo.setX(dX);
                demo.setY(dY);
            }
        });

        primaryStage.setScene(scene);
        primaryStage.show();
    }
}
