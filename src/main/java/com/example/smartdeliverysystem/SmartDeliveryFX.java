package com.example.smartdeliverysystem;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.*;
import javafx.stage.Stage;
import java.util.*;

public class SmartDeliveryFX extends Application {

    CargoTree tree = new CargoTree();
    Queue<Cargo> queue = new LinkedList<>();

    int totalTime = 0;
    int deliveredCount = 0;


    @Override
    public void start(Stage stage) {

        TextField idField = new TextField();
        TextField senderField = new TextField();
        TextField receiverField = new TextField();

        TextArea output = new TextArea();

        Button addBtn = new Button("Add Cargo");
        Button processBtn = new Button("Process");
        Button searchBtn = new Button("Search");
        Button avgBtn = new Button("Average");

        // ➕ ADD
        addBtn.setOnAction(e -> {
            try {
                int id = Integer.parseInt(idField.getText());
                String sender = senderField.getText();
                String receiver = receiverField.getText();

                if (sender.isEmpty() || receiver.isEmpty()) {
                    output.appendText("Empty fields!\n");
                    return;
                }

                Cargo c = new Cargo(id, sender, receiver);
                queue.add(c);
                tree.insert(c);

                output.appendText("Added: " + id + "\n");

            } catch (Exception ex) {
                output.appendText("Invalid ID!\n");
            }
        });

        // 📦 PROCESS
        processBtn.setOnAction(e -> {
            if (!queue.isEmpty()) {
                Cargo c = queue.poll();
                Random rand = new Random();

                c.status = "Delivered";
                c.deliveryTime = rand.nextInt(5) + 1;

                totalTime += c.deliveryTime;
                deliveredCount++;

                output.appendText("Delivered: " + c.trackingId + "\n");
            } else {
                output.appendText("Queue empty\n");
            }
        });

        // 🔍 SEARCH
        searchBtn.setOnAction(e -> {
            try {
                int id = Integer.parseInt(idField.getText());
                Cargo c = tree.search(id);

                if (c != null) {
                    output.appendText("Found: " + c.receiver + " - " + c.status + "\n");
                } else {
                    output.appendText("Not found\n");
                }
            } catch (Exception ex) {
                output.appendText("Invalid ID!\n");
            }
        });

        // ⏱ AVG
        avgBtn.setOnAction(e -> {
            if (deliveredCount > 0) {
                output.appendText("Average: " + (totalTime / deliveredCount) + "\n");
            } else {
                output.appendText("No data\n");
            }
        });

        VBox layout = new VBox(10,
                new Label("Tracking ID"), idField,
                new Label("Sender"), senderField,
                new Label("Receiver"), receiverField,
                addBtn, processBtn, searchBtn, avgBtn,
                output
        );

        Scene scene = new Scene(layout, 400, 500);

        stage.setTitle("Smart Delivery FX");
        stage.setScene(scene);
        stage.show();
    }

    public static void main(String[] args) {
        launch();
    }
}
