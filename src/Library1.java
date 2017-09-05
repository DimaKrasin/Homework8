import java.util.Scanner;
import java.util.concurrent.Semaphore;

public class Library1 {

    public static void main(String[] args) {

        Scanner scanner = new Scanner(System.in);
        System.out.println("Введите peopleCOunt");
        int peopleCount=scanner.nextInt();
        if(peopleCount<=0){System.out.println("Неверно введено peopleCount!");}
        System.out.println("Введите maxAmount");
        int maxAmount=scanner.nextInt();
        if(maxAmount<=0){System.out.println("Неверно введено maxAmount!");}

        Semaphore SEMAPHORE = new Semaphore(maxAmount);
        for (int i =1;i<=peopleCount;i++) {
            final  int x =i;
            new Thread(new Runnable() {
                @Override
                public void run() {
                    System.out.println("["+x+"] Пришел ко входу в библиотеку");
                    try {
                        if(SEMAPHORE.availablePermits()<=0){System.out.println("["+x+"] Стоит в очереди?");}
                        SEMAPHORE.acquire();
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    System.out.println("["+x+"] Вошел в библеотеку?");

                    try {
                        Thread.sleep(1000);
                        System.out.println("["+x+"] Читает книгу 1 сек?");
                        Thread.sleep(1000);
                        System.out.println("["+x+"] Читает книгу 2 сек?");
                        Thread.sleep(1000);
                        System.out.println("["+x+"] Читает книгу 3 сек?");
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    System.out.println("["+x+"] Вышел");
                    SEMAPHORE.release();
                }
            }).start();
        }
    }


}
