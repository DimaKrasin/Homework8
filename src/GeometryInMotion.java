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

    MyThreat[] threads;
    Rectangle[] rectangles;
    GetReady getReady;

    static ArrayList<GetReady> arr = new ArrayList<>();

    private static int countOfIteration =0;

    private static Random random = new Random();

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

            int count = random.nextInt((4) + 3);

            threads = new MyThreat[count];
            rectangles = new Rectangle[count];
            getReady = new GetReady(threads, rectangles);

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


        Button button3 = new Button("Optimal Threads");
        button3.setTranslateX(180+160);
        button3.setTranslateY(20);
        button3.setOnAction(event -> {

            int count = random.nextInt((4) + 3);

            rectangles = new Rectangle[count];
            getReady = new GetReady(rectangles);

            for (int i = 0; i <= arr.get(countOfIteration).rectangles.length-1; i++) {
                CreateRectangle(i,root);
            }

            arr.add(getReady);

            OptimalThreads.OptimalMagic(countOfIteration);
            countOfIteration = countOfIteration +1;
        });


        Button button2 = new Button("Single Thread");
        button2.setTranslateX(180);
        button2.setTranslateY(20);
        button2.setOnAction(event -> {


            Thread SingleTread = new Thread(()->{
                while(true){
                    for(int countOfI = 0;countOfI <= countOfIteration-1;countOfI++)
                        for(int i = 0; i <= arr.get(countOfI).rectangles.length-1; i++){

                            System.out.println("TranslateX of rectangle " + countOfI + i
                                    + " = " + GeometryInMotion.arr.get(countOfI).rectangles[i].getTranslateX());
                            System.out.println("TranslateY of rectangle " + countOfI + i
                                    + " = " + GeometryInMotion.arr.get(countOfI).rectangles[i].getTranslateY());
                        }

                    try {
                        Thread.sleep(1000);
                    } catch (InterruptedException e) {
                        e.printStackTrace();}
                }

            });
            SingleTread.start();});



        root.getChildren().addAll(button,button2,button3);
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    public static void CreateRectangle(int i ,Pane root){
        arr.get(countOfIteration).rectangles[i] = new Rectangle();
        arr.get(countOfIteration).rectangles[i].setTranslateX(random.nextInt((int)root.getHeight()-100)+50);
        arr.get(countOfIteration).rectangles[i].setTranslateY(random.nextInt((int)root.getWidth()-100)+50);

        arr.get(countOfIteration).rectangles[i].setWidth(random.nextInt(80)+40);
        arr.get(countOfIteration).rectangles[i].setHeight(random.nextInt(40)+20);

        Color color = new Color(random.nextDouble(),random.nextDouble(),random.nextDouble(), 1);
        arr.get(countOfIteration).rectangles[i].setFill(color);

        root.getChildren().addAll(arr.get(countOfIteration).rectangles[i]);
    }
}
