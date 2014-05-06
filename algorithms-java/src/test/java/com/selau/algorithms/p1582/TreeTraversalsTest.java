package com.selau.algorithms.p1582;

import org.junit.Test;

/**
 * Tests of {@link TreeTraversals} algorithms.
 * @author gribeiro
 *
 */
public class TreeTraversalsTest {


	@Test
	public void basicTest() {
		// given
		TNode t = buildTree();

		// when
		TreeTraversals algorithms = new TreeTraversals();

		// then
		System.out.println("preorder");
		algorithms.preorder(t);

		System.out.println("\n\ninorder");
		algorithms.inorder(t);

		System.out.println("\n\npostorder");
		algorithms.postorder(t);
	}

	/**
	 * 						a
	 * 				b				c
	 * 			d		e		f		g
	 */
	public TNode buildTree() {

		TNode d = new TNode(null, null, 'd');
		TNode e = new TNode(null, null, 'e');
		TNode f = new TNode(null, null, 'f');
		TNode g = new TNode(null, null, 'g');

		TNode b = new TNode(d, e, 'b');
		TNode c = new TNode(f, g, 'c');

		TNode a = new TNode(b, c, 'a');

		return a;
	}

}
