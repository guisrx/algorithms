package com.selau.algorithms.binarytree;

import java.util.Stack;

import org.junit.Test;


public class DeepFirstTraversal {


    public void traverse(final Node root) {
        final Stack<Node> stack = new Stack<Node>();

        stack.push(root);

        while(!stack.isEmpty()) {
            Node currentNode = stack.pop();

            if (currentNode == null)
                continue;

            visit(currentNode);
            stack.push(currentNode.getRight());
            stack.push(currentNode.getLeft());
        }
    }

    private void visit(final Node node) {
        if (node == null)
            return;

        System.out.println(node.getPayload());
    }

    @Test
    public void test() {
        // given
        Node n4 = new Node(4, null, null);
        Node n5 = new Node(5, null, null);
        Node n6 = new Node(6, null, null);
        Node n10 = new Node(10, null, null);
        Node n11 = new Node(11, null, null);
        Node n12 = new Node(12, null, null);
        Node n3 = new Node(3, n4, n5);
        Node n9 = new Node(9, n10, n11);
        Node n2 = new Node(2, n3, n6);
        Node n8 = new Node(8, n9, n12);
        Node n1 = new Node(1, n2, n8);

        // when then
        traverse(n1);
    }

}
