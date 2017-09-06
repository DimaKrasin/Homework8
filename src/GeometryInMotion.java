import javafx.application.Application;
import javafx.application.Platform;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.stage.Stage;

import java.util.Random;

import static javafx.scene.paint.Color.color;


public class GeometryInMotion extends Application{

    public static void Geometry (){
        launch();
    }

    public void start(Stage primaryStage) throws Exception {
        Random rand = new Random();
        Pane root = new Pane();
        Thread main_thread = Thread.currentThread();

        Button button = new Button("Multy Threads");
        button.setTranslateX(20);
        button.setTranslateY(20);
        button.setOnAction(event->{

           // int random = rand.nextInt(10)+3;

            final Thread[] threads = new Thread[6];

            for(int i=1;i<=4;i++) {

                if(i>1){threads[i-1].start();}

                Rectangle rectangle = new Rectangle();
                rectangle.setX((20));
                rectangle.setY(100+(i*100));

                rectangle.setWidth(80);
                rectangle.setHeight(30);

                Color color = new Color(rand.nextDouble(), rand.nextDouble(), rand.nextDouble(), 1);
                rectangle.setFill(color);

                root.getChildren().addAll(
                        rectangle
                );

                for( int j = 1;j<root.getWidth();j++) {

                    threads[i] = new Thread(() -> {

                        try {
                            main_thread.sleep(2000);
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }

                        final double x = rectangle.getX() + 10;

                        Platform.runLater(() -> {
                            rectangle.setTranslateX(x);
                        });

                        try {
                            Thread.sleep(100);
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                    });
                    threads[i].start();
                }


            }

        });


        root.getChildren().addAll(button);
        primaryStage.setScene(new Scene(root ,800,600));
        primaryStage.show();
    }

}
