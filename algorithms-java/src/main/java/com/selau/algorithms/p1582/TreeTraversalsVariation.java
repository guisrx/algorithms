package com.selau.algorithms.p1582;

/**
 * Traversals algorithms of a tree (preorder, inorder, postorder)
 * @author gribeiro
 *
 */
class TreeTraversalsVariation {

	void preorder(TNode t) {
		output(t.value);
		if (t.left != null)
			preorder(t.left);
		if (t.right != null)
			postorder(t.right);
	}

	void inorder(TNode t) {
		if (t.left != null)
			inorder(t.left);
		output(t.value);
		if (t.right != null)
			postorder(t.right);
	}

	void postorder(TNode t) {
		if (t.left != null)
			inorder(t.left);
		if (t.right != null)
			preorder(t.right);
		output(t.value);
	}

	void output(char value) {
		System.out.print(value);
	}

}
