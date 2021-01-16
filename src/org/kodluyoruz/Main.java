package org.kodluyoruz;

import javafx.scene.control.Tab;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Semaphore;

public class Main {

    public static void main(String[] args) throws InterruptedException {
        Restaurant restaurant=new Restaurant();

        Customer customer= new Customer(restaurant);
        Waiter waiter1=new Waiter(restaurant,"Garson1");
        //Waiter waiter2=new Waiter(restaurant,"Garson2");
        Chef chef=new Chef(restaurant);

        Thread customerT=new Thread(customer);
        Thread waiterT1=new Thread(waiter1);
        //Thread waiterT2=new Thread(waiter2);
        Thread chefT=new Thread(chef);

        customerT.start();
        waiterT1.start();
        //waiterT2.start();
        chefT.start();

    }
}
