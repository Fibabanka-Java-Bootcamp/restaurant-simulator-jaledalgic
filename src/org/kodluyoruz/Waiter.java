package org.kodluyoruz;

import java.util.concurrent.Semaphore;
import java.util.concurrent.atomic.AtomicInteger;

public class Waiter implements Runnable{

    Restaurant restaurant;
    final int MAX_SIZE=3;
    private String id;
    int i=0;

    public Waiter( Restaurant restaurant,String id) {
        this.restaurant=restaurant;
        this.id=id;
    }
    public void sendCustomers(int i) throws InterruptedException {
        Restaurant res=new Restaurant(MAX_SIZE);
        if(restaurant.costumerQueue==null){
            restaurant.costumerQueue=res.costumerQueue;
        }
        if(restaurant.orderQueue==null){
            restaurant.orderQueue=res.orderQueue;
        }
        synchronized (restaurant.costumerQueue){
            while (restaurant.costumerQueue.isEmpty()){
                //System.out.println("Müşteri Bekleniyor.");
                restaurant.costumerQueue.wait();
            }
        }
        synchronized (restaurant.costumerQueue){
                System.out.println("Müşteri " + (i+1) +" " +id + " tarafından masaya yerleştirildi.");
                Thread.sleep(1000);
                restaurant.costumerQueue.remove(0);
                restaurant.costumerQueue.notify();
                takeOrder(i++);
        }


    }
public void takeOrder(int i) throws InterruptedException {
    Restaurant res=new Restaurant(MAX_SIZE);
    if(restaurant.orderQueue==null){
        restaurant.orderQueue=res.orderQueue;
    }
    synchronized (restaurant.orderQueue){
        while (restaurant.orderQueue.size()==MAX_SIZE){
            System.out.println("Restaurant dolu sırada bekleyin");
            restaurant.orderQueue.wait();
        }
    }
    synchronized (restaurant.orderQueue){
        System.out.println("Müşteri "+(i+1)+" siparişi alındı.");
        restaurant.orderQueue.add(i);
        Thread.sleep(1000);
        restaurant.orderQueue.notify();

    }


}
public void orderDeliver(int i) throws InterruptedException {
        Restaurant res=new Restaurant(MAX_SIZE);
        if(restaurant.doneOrder==null){
        restaurant.doneOrder=res.doneOrder;
        }
        synchronized (restaurant.doneOrder) {
            while (restaurant.doneOrder.isEmpty()) {
                restaurant.doneOrder.wait();
            }
        }
        synchronized (restaurant.doneOrder){
        System.out.println("Müsteri " + (i+1) +" siparişi teslim edildi. ");
        Thread.sleep(1000);
        restaurant.doneOrder.remove(0);
        restaurant.doneOrder.notify();
    }
}
    @Override
    public void run() {
        try {
            while (true){
                sendCustomers(i++);
                orderDeliver(i++);
            }
        }catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
