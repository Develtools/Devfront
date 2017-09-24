package Util;

import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;

public class Tree<T> implements Iterable<Tree<T>> {

	public T data;
	@SuppressWarnings("unused")
	private Tree<T> parent;
	private List<Tree<T>> children;

	public Tree(T data) {
		this.data = data;
		this.children = new LinkedList<Tree<T>>();
	}

	public Tree<T> addChild(T child) {
		Tree<T> childNode = new Tree<T>(child);
		childNode.parent = this;
		this.children.add(childNode);
		return childNode;
	}

	@Override
	public Iterator<Tree<T>> iterator() {
		return children.iterator();
	}
	
	public String toString() {
		return data.toString();
	}
}