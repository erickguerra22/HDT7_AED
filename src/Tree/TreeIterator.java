package Tree;

import java.util.Iterator;
import java.util.NoSuchElementException;

public class TreeIterator<E> implements Iterator<E>{
	private BinaryTree<E> next;
	
	public TreeIterator(BinaryTree<E> root) {
		next = root;
		if(next == null)
			return;
		while (!next.left.isEmpty())
			next = next.left;
	}

	@Override
	public boolean hasNext() {
		return next != null;
	}

	@Override
	public E next() {
		if(!hasNext()) throw new NoSuchElementException();
		BinaryTree<E> r = next;
		
		if(!next.right.isEmpty()) {
            next = next.right;
            while (!next.left.isEmpty())
                next = next.left;
            return r.val;
        }

        while(true) {
            if(next.parent == null) {
                next = null;
                return r.val;
            }
            if(next.parent.left == next) {
                next = next.parent;
               return r.val;
            }
            next = next.parent;
        }
	}
}
