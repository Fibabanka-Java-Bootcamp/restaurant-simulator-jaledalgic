package org.kodluyoruz;

public class Chef implements Runnable{
    Restaurant restaurant;
    final int MAX_SIZE=2;
    int i=0;

    public Chef(Restaurant restaurant) {
        this.restaurant = restaurant;
    }

    public void prepareOrder(int i) throws InterruptedException {
    Restaurant res=new Restaurant(MAX_SIZE);
        if(restaurant.orderQueue==null){
        restaurant.orderQueue= res.orderQueue;
    }
    synchronized (restaurant.orderQueue){
        while (restaurant.orderQueue.isEmpty()){
            //System.out.println("Sipariş bekleniyor.");
            restaurant.orderQueue.wait();
        }
    }
    synchronized (restaurant.orderQueue){
        System.out.println("Müsteri " + (i+1) +" siparişi hazırlanıyor. ");
        Thread.sleep(1000);
        restaurant.orderQueue.remove(0);
        restaurant.orderQueue.notify();

    }
    }
    public  void orderDone(int i) throws InterruptedException {
        Restaurant res=new Restaurant(MAX_SIZE);
        if(restaurant.doneOrder==null){
            restaurant.doneOrder= res.doneOrder;
        }
        synchronized (restaurant.doneOrder){
            while (restaurant.doneOrder.size()==MAX_SIZE){
                //System.out.println("Sipariş yok.");
                restaurant.doneOrder.wait();
            }
        }
        synchronized (restaurant.doneOrder){
            System.out.println("Müsteri " + (i+1) +" siparişi hazır. ");
            restaurant.doneOrder.add(i);
            Thread.sleep(1000);
            restaurant.doneOrder.notify();
        }
    }
    @Override
    public void run() {
        try {
            while (true){

              prepareOrder(i++);
                orderDone(i++);
            }
          } catch (InterruptedException e) {
              e.printStackTrace();
          }


    }
}
