import javafx.application.Platform;

import java.util.Random;

public class MyThreat implements Runnable{

    Thread thrd;
    int count;
    int i;
    int root_wight;
    int root_height;
    MyThreat(int index, int w, int h,int countOfIteration){
        thrd = new Thread(this);
        i = index;
        root_wight = w;
        root_height = h;
        count = countOfIteration;
    }

    double countX;
    double countY;

    public void countX(int i){
        countX = countX +1;
    }

    public void de_countX(int i){
        countX = countX -1;
    }

    public void countY(int i){
        countY = countY +1;
    }

    public void de_countY(int i){
        countY = countY -1;
    }

    public void left_top(int i){
        de_countX(i);
        de_countY(i);
    }

    public void right_top(int i){
        countX(i);
        de_countY(i);
    }

    public void left_bottom(int i){
        de_countX(i);
        countY(i);
    }

    public void right_bottom(int i){
        countY(i);
        countX(i);
    }

    Random rnd = new Random();

    public void run(){

        countX = GeometryInMotion.arr.get(count).rectangles[i].getTranslateX();
        countY = GeometryInMotion.arr.get(count).rectangles[i].getTranslateX();

        int r = rnd.nextInt(4)+1;
        System.out.println(r);


        do{

            System.out.println("X "+ thrd.getName() + " = " +GeometryInMotion.arr.get(count).rectangles[i].getTranslateX());
            System.out.println("Y "+ thrd.getName() + " = " +GeometryInMotion.arr.get(count).rectangles[i].getTranslateY());

            Platform.runLater(() -> {

                GeometryInMotion.arr.get(count).rectangles[i].setTranslateX(countX);
                GeometryInMotion.arr.get(count).rectangles[i].setTranslateY(countY);
            });



            switch (r){
                case 1:
                    left_top(i);
                    break;
                case 2:
                    right_top(i);
                    break;
                case 3:
                    left_bottom(i);
                    break;
                case 4:
                    right_bottom(i);
                    break;
            }


            if(GeometryInMotion.arr.get(count).rectangles[i].getTranslateX() <= 0){
                if(r == 1) r = 2;  if(r == 3) r = 4;
            }
            if(GeometryInMotion.arr.get(count).rectangles[i].getTranslateX() >= root_wight - GeometryInMotion.arr.get(count).rectangles[i].getWidth()) { //800 - wight
                if(r == 2) r = 1;  if(r == 4) r = 3;
            }

            if(GeometryInMotion.arr.get(count).rectangles[i].getTranslateY() <= 0) {
                if(r == 1) r = 3;  if(r == 2) r = 4;
            }
            if(GeometryInMotion.arr.get(count).rectangles[i].getTranslateY() >= root_height - GeometryInMotion.arr.get(count).rectangles[i].getHeight()) { //600 - height
                if(r == 4) r = 2;  if(r == 3) r = 1;
            }

            try {
                Thread.sleep(24);
            } catch (InterruptedException e) {
                e.printStackTrace();}
        } while (true);


    }
}