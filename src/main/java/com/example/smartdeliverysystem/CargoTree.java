package com.example.smartdeliverysystem;

public class CargoTree {

    class Node {
        Cargo cargo;
        Node left, right;

        Node(Cargo cargo) {
            this.cargo = cargo;
        }
    }

    Node root;

    public void insert(Cargo cargo) {
        root = insertRec(root, cargo);
    }

    private Node insertRec(Node root, Cargo cargo) {
        if (root == null) return new Node(cargo);

        if (cargo.trackingId < root.cargo.trackingId)
            root.left = insertRec(root.left, cargo);
        else
            root.right = insertRec(root.right, cargo);

        return root;
    }

    public Cargo search(int id) {
        return searchRec(root, id);
    }

    private Cargo searchRec(Node root, int id) {
        if (root == null || root.cargo.trackingId == id)
            return root != null ? root.cargo : null;

        if (id < root.cargo.trackingId)
            return searchRec(root.left, id);

        return searchRec(root.right, id);
    }

    public void printDelivered(Node root) {
        if (root != null) {
            printDelivered(root.left);
            if (root.cargo.status.equals("Delivered")) {
                System.out.println("Delivered: " + root.cargo.trackingId);
            }
            printDelivered(root.right);
        }
    }
}
