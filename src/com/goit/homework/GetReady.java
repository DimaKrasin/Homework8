package com.goit.homework;

import com.goit.homework.MyThreat;
import javafx.scene.shape.Rectangle;

public class GetReady {

        MyThreat[] threads;
        Rectangle[] rectangles;

        GetReady(MyThreat[] threads,Rectangle[] rectangles){
            this.threads = threads;
            this.rectangles = rectangles;
        }

    GetReady(Rectangle[] rectangles){
        this.rectangles = rectangles;
    }

}
