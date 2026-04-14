package com.example.smartdeliverysystem;

import java.util.LinkedList;
import java.util.Queue;

public class CargoQueue {
    Queue<Cargo> queue = new LinkedList<>();

    public void enqueue(Cargo c) {
        queue.add(c);
    }

    public Cargo dequeue() {
        return queue.poll();
    }

    public boolean isEmpty() {
        return queue.isEmpty();
    }
}