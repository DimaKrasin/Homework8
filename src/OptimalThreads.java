import javafx.scene.shape.Rectangle;

public class OptimalThreads extends MyThreat{

    private static int countProcessors = Runtime.getRuntime().availableProcessors();
    private static Thread[] threads = new Thread[countProcessors];

    public static void OptimalMagic(int countOfIteration){

        System.out.println("Сгенерировано-"+GeometryInMotion.arr.get(countOfIteration).rectangles.length+" прямоугольников");

        int numberOfUsedThreat =0;

        Rectangle tmp;

        for (int i =0;i<=countProcessors;i++){
            threads[i].start();
        }

        for(int i =1;i<=GeometryInMotion.arr.get(countOfIteration).rectangles.length;i++){
            if(numberOfUsedThreat>=countProcessors){numberOfUsedThreat=0;}

            threads[numberOfUsedThreat].start();
            tmp = GeometryInMotion.arr.get(countOfIteration).rectangles[i-1];

            numberOfUsedThreat=numberOfUsedThreat+1;
        }
    }

    public void run(){

    }





}
