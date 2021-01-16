package org.kodluyoruz;


import java.util.LinkedList;

public class Restaurant {
    public LinkedList<Integer> costumerQueue, orderQueue, doneOrder;
    private int capacity;

    public Restaurant() {
    }

    public Restaurant(int capacity) {
        this.capacity = capacity;
        this.costumerQueue = new LinkedList<Integer>();
        this.orderQueue = new LinkedList<Integer>();
        this.doneOrder=new LinkedList<Integer>();
    }

    int getCapacity() {
        return capacity;
    }

}
