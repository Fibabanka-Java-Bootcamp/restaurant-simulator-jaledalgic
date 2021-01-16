package org.kodluyoruz;

import java.util.LinkedList;

public class Customer implements Runnable{
    Restaurant restaurant;
    int i=0;

   final int MAX_SIZE=5;
    public Customer() {
    }

    public Customer( Restaurant restaurant) {

        this.restaurant=restaurant;
    }
    public void makeCustomers(int i) throws InterruptedException {
        Restaurant res=new Restaurant(MAX_SIZE);
        if(restaurant.costumerQueue==null){
            restaurant.costumerQueue=res.costumerQueue;
        }
        synchronized (restaurant.costumerQueue){
            while (restaurant.costumerQueue.size()==MAX_SIZE){
                System.out.println("Restaurant dolu sırada bekleyin");
                restaurant.costumerQueue.wait();
            }
        }
        synchronized (restaurant.costumerQueue){
            System.out.println("Müşteri "+(i+1)+" restauranta alındı.");
            restaurant.costumerQueue.add(i);
            Thread.sleep(1000);
            restaurant.costumerQueue.notify();

        }


    }

    @Override
    public void run() {
        while (true){
            try {
                makeCustomers(i++);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}
