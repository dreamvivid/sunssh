package com.sund.test;

import java.util.LinkedList;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

import org.junit.Test;

public class MyStack {
	private LinkedList list = new LinkedList();
	private Lock lock = new ReentrantLock();

	public Object pop() {
		lock.lock();
		try {
			return list.removeFirst();
		} finally {
			lock.unlock();
		}
	}

	public void push(Object o) {
		lock.lock();
		try {
			list.addFirst(o);
		} finally {
			lock.unlock();
		}
	}
	
	@Override
	public String toString() {
		StringBuffer sb = new StringBuffer();
		for(int i=0; i<list.size(); i++) {
			sb.append(i).append(":").append(list.get(i)).append("\n");
		}
		return sb.toString();
	}

	@Test
	public void testMyStack() {
		MyStack myStack = new MyStack();
		myStack.push("1");
		myStack.push("2");
		myStack.push("3");
		System.out.println(myStack.toString());
		
		myStack.pop();
		System.out.println(myStack.toString());
		
	}
}
