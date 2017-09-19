package com.goit.homework;

import java.util.Random;
import java.util.Scanner;
import java.util.concurrent.Semaphore;

public class Library1 {

    public static void Library() {

        Random rand = new Random();
        Scanner scanner = new Scanner(System.in);

        System.out.println("Введите peopleCOunt");
        int peopleCount=scanner.nextInt();
        if(peopleCount<=0){System.out.println("Неверно введено peopleCount!");}
        System.out.println("Введите maxAmount");
        int maxAmount=scanner.nextInt();
        if(maxAmount<=0){System.out.println("Неверно введено maxAmount!");}

        Semaphore semaphore = new Semaphore(maxAmount);
        Semaphore Semaphore_Door = new Semaphore(1);

        for (int i =1;i<=peopleCount;i++) {
            final  int x =i;
            new Thread(new Runnable() {
                @Override
                public void run() {
                    System.out.println("["+x+"] Пришел ко входу в библиотеку");
                    try {
                        if(semaphore.availablePermits()<=0){System.out.println("["+x+"] Стоит в очереди");}
                        semaphore.acquire();

                        System.out.println("["+x+"] подошел к двери с улицы");
                        Semaphore_Door.acquire();
                        System.out.println("["+x+"] проходит через дверь внутрь");
                        Thread.sleep(500);
                        Semaphore_Door.release();
                        System.out.println("["+x+"] прошел через дверь внутрь");

                        System.out.println("["+x+"] Вошел в библеотеку");

                        int random = rand.nextInt(5000)+1000;
                        Thread.sleep(random);
                        System.out.println("["+x+"] Читает книгу "+random/1000+" секунд" );

                        System.out.println("["+x+"] подошел к двери изнутри");
                        Semaphore_Door.acquire();
                        System.out.println("["+x+"] проходит через дверь наружу");
                        Thread.sleep(500);
                        Semaphore_Door.release();
                        System.out.println("["+x+"] прошел через дверь наружу");

                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }

                    semaphore.release();
                    System.out.println("["+x+"] Вышел");


                }
            }).start();
        }
    }


}
