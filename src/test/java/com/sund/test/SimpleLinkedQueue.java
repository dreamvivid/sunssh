package com.sund.test;

public class SimpleLinkedQueue {
	private class Node {
		private Object data;
		private Node next;
		
		public Node() {
			
		}
		
		public Node(Object data, Node next) {
			this.data = data;
			this.next = next;
		}
		
		public Object getData() {
			return data;
		}

		public void setData(Object data) {
			this.data = data;
		}

		public Node getNext() {
			return next;
		}

		public void setNext(Node next) {
			this.next = next;
		}

	}
	
	private Node front;
	private Node rear;
	private int size;
	
	public SimpleLinkedQueue() {
		front = null;
		rear = null;
	}
	
	public int length() {
		return size;
	}
	
	public boolean isEmpty() {
		return size==0;
	}
	
	public void add(Object data) {
		if(front==null) {
			// 空队列
			front = new Node(data, null);
			rear = front;
		} else {
			// 非空队列
			Node newNode = new Node(data, null);
			rear.next = newNode;
			rear = newNode;
		}
		size ++;
	}
	
	public Object remove() {
		Node oldNode = front;
		front = oldNode.next;
		size --;
		return oldNode.data;
	}

	public void clear() {
		front = null;
		rear = null;
		size = 0;
	}
	
	public String toString() {
		if(isEmpty()) {
			return "[]";
		}
		StringBuilder sb = new StringBuilder("[");
		for(Node currNode=front;currNode!=null;currNode=currNode.next) {
			sb.append(currNode.data).append("->");
		}
		sb = sb.delete(sb.length()-2, sb.length());
		sb.append("]");
		return sb.toString();
	}
	
	public static void main(String[] args) {
		SimpleLinkedQueue queue = new SimpleLinkedQueue();
		queue.add("first");
		queue.add("second");
		queue.add("third");
		queue.remove();
		System.out.println(queue.toString());
	}
}
