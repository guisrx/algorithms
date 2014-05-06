package com.selau.algorithms.p1582;

/**
 * Node of a tree.
 * @author gribeiro
 *
 */
class TNode {

	final TNode left;
	final TNode right;
	final char value;

	TNode(TNode left, TNode right, char value) {

		this.left = left;
		this.right = right;
		this.value = value;
	}

}
