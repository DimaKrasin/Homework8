import javafx.application.Application;
import javafx.application.Platform;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.stage.Stage;

import java.util.Currency;
import java.util.Random;


public class GeometryInMotion extends Application {

    Random rand = new Random();
    Thread main_thread = Thread.currentThread();


    public static void Geometry() {
        launch();
    }

    public void start(Stage primaryStage) throws Exception {
        Pane root = new Pane();

        Button button = new Button("Multy Threads");
        button.setTranslateX(20);
        button.setTranslateY(20);
        button.setOnAction(event -> {
            Rectangle rectangle = CreateRectangle(root);

            while (rectangle.getX() < 800) {
                final double x = rectangle.getX() + 1;

                try {
                    main_thread.sleep(2000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }

                Platform.runLater(() -> {
                    rectangle.setTranslateX(x);
                });

                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }

        });

        root.getChildren().addAll(button);
        primaryStage.setScene(new Scene(root, 800, 600));
        primaryStage.show();
    }

    public Rectangle CreateRectangle(Pane root) {
        Rectangle rectangle = new Rectangle();
        rectangle.setX(rand.nextInt((int) root.getWidth()));
        rectangle.setY(rand.nextInt((int) root.getHeight()));

        rectangle.setWidth(80);
        rectangle.setHeight(30);

        Color color = new Color(rand.nextDouble(), rand.nextDouble(), rand.nextDouble(), 1);
        rectangle.setFill(color);

        root.getChildren().addAll(
                rectangle
        );

        return rectangle;
    }

}
