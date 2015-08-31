package com.selau.algorithms.binarytree;

public class Node {

    private final int payload;

    private Node left;
    private Node right;

    public Node(final int payload, final Node left, final Node right) {
        this.payload = payload;
        this.left = left;
        this.right = right;
    }

    public Node getLeft() {
        return left;
    }

    public void setLeft(final Node left) {
        this.left = left;
    }

    public Node getRight() {
        return right;
    }

    public void setRight(final Node right) {
        this.right = right;
    }

    public int getPayload() {
        return payload;
    }


}
