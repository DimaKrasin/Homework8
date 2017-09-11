import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.stage.Stage;

import java.util.ArrayList;
import java.util.Random;

public class GeometryInMotion extends Application {

    static ArrayList<GetReady> arr = new ArrayList<>();

    private int countOfIteration =0;

    Random random = new Random();

    public static void Geometry() {
        launch();
    }

    public void start(Stage primaryStage) throws Exception {

        Pane root = new Pane();
        Scene scene = new Scene(root, 800, 600);

        Button button = new Button("Multy Threads");
        button.setTranslateX(20);
        button.setTranslateY(20);
        button.setOnAction(event -> {

            Random random = new Random();
            int count = random.nextInt((4) + 3);

            MyThreat[] threads = new MyThreat[count];
            Rectangle[] rectangles = new Rectangle[count];

            GetReady getReady = new GetReady(threads, rectangles);

            arr.add(getReady);

            for (int i = 0; i <= arr.get(countOfIteration).rectangles.length-1; i++) {

                CreateRectangle(i,root);

                arr.get(countOfIteration).threads[i] = new MyThreat(i, (int) root.getWidth(), (int) root.getHeight(),countOfIteration);
            }

            for (int i = 0; i <= arr.get(countOfIteration).rectangles.length-1; i++) {
                arr.get(countOfIteration).threads[i].thrd.start();
            }

            countOfIteration = countOfIteration +1;
        });

        root.getChildren().addAll(button);
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    public void CreateRectangle(int i ,Pane root){
        arr.get(countOfIteration).rectangles[i] = new Rectangle();
        arr.get(countOfIteration).rectangles[i].setTranslateX(random.nextInt((int)root.getHeight()-100)+50);
        arr.get(countOfIteration).rectangles[i].setTranslateY(random.nextInt((int)root.getWidth()-100)+50);

        arr.get(countOfIteration).rectangles[i].setWidth(80);
        arr.get(countOfIteration).rectangles[i].setHeight(30);

        Color color = new Color(random.nextDouble(),random.nextDouble(),random.nextDouble(), 1);
        arr.get(countOfIteration).rectangles[i].setFill(color);

        root.getChildren().addAll(arr.get(countOfIteration).rectangles[i]);
    }
}
