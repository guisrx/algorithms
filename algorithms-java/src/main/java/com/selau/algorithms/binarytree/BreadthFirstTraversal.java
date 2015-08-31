package com.selau.algorithms.binarytree;

import java.util.HashSet;
import java.util.LinkedList;
import java.util.Queue;

import org.junit.Test;


class Node {

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

public class BreadthFirstTraversal {


    public void traverse(final Node root) {
        final HashSet<Node> visited = new HashSet<Node>();
        final Queue<Node> queue = new LinkedList<Node>();

        visit(root, queue, visited);

        while(!queue.isEmpty()) {
            Node currentNode = queue.poll();

            if (! visited.contains(currentNode.getLeft())) {
                visit(currentNode.getLeft(), queue, visited);
            }
            if (! visited.contains(currentNode.getRight())) {
                visit(currentNode.getRight(), queue, visited);
            }
        }
    }

    private void visit(final Node node, final Queue<Node> queue, HashSet<Node> visited) {
        if (node == null)
            return;

        queue.add(node);
        visited.add(node);
        System.out.println(node.getPayload());
    }

    @Test
    public void test() {
        // given
        Node n9 = new Node(9, null, null);
        Node n10 = new Node(10, null, null);
        Node n11 = new Node(11, null, null);
        Node n12 = new Node(12, null, null);
        Node n6 = new Node(6, null, null);
        Node n8 = new Node(8, null, null);
        Node n5 = new Node(5, n9, n10);
        Node n7 = new Node(7, n11, n12);
        Node n4 = new Node(4, n7, n8);
        Node n2 = new Node(2, n5, n6);
        Node n1 = new Node(1, n2, n4);

        // when then
        traverse(n1);
    }

}

