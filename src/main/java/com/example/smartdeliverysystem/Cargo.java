package com.example.smartdeliverysystem;

public class Cargo {
    int trackingId;
    String sender;
    String receiver;
    String status;
    int deliveryTime;

    public Cargo(int trackingId, String sender, String receiver) {
        this.trackingId = trackingId;
        this.sender = sender;
        this.receiver = receiver;
        this.status = "In Transit";
        this.deliveryTime = 0;
    }
}