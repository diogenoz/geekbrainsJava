package com.geek.dz7;

import java.util.concurrent.CyclicBarrier;
import java.util.concurrent.locks.Lock;

public class Car implements Runnable {
    private static int CARS_COUNT;
    private static boolean isStart = false;
    private static boolean isWinner = false;
    public static Lock winnerLock;
    private static int CARS_FINISH = 0;


    public static CyclicBarrier cb;
    private Race race;
    private int speed;
    private String name;
    public String getName() {
        return name;
    }

    public int getSpeed() {
        return speed;
    }

    public Car(Race race, int speed) {
        this.race = race;
        this.speed = speed;
        CARS_COUNT++;

        this.name = "Участник #" + CARS_COUNT;
    }

    @Override
    public void run() {
        try {
            System.out.println(this.name + " готовится");
            Thread.sleep(500 + (int) (Math.random() * 800));
            System.out.println(this.name + " готов");
            Car.cb.await();
        } catch (Exception e) {
            e.printStackTrace();
        }
        if (!Car.isStart) {
            Car.isStart = true;
            System.out.println("ВАЖНОЕ ОБЪЯВЛЕНИЕ >>> Гонка началась!!!");
        }

        for (int i = 0; i < race.getStages().size(); i++) {
            race.getStages().get(i).go(this);
        }

        try {
            winnerLock.lock();
            if (!Car.isWinner) {
                Car.isWinner = true;
                System.out.println(this.name + " WIN");
            }
        } finally {
            winnerLock.unlock();
        }
        Car.CARS_FINISH++;
        if (Car.CARS_FINISH == Car.CARS_COUNT) {
            System.out.println("ВАЖНОЕ ОБЪЯВЛЕНИЕ >>> Гонка закончилась!!!");
        }
    }
}