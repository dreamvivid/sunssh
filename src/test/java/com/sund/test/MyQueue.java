package com.sund.test;

import java.util.LinkedList;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

import org.junit.Test;

public class MyQueue {
	private LinkedList list = new LinkedList();
	private Lock lock = new ReentrantLock();
	public void add(Object o) {
		lock.lock();
		try {
			list.add(o);
		} finally {
			lock.unlock();
		}
	}
	
	public void remove() {
		lock.lock();
		try {
			list.remove(0);
		} finally {
			lock.unlock();
		}
	}
	
	public Object peek() {
		lock.lock();
		try {
			return list.get(0);
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
	public void testMyQueue() {
		MyQueue myQueue = new MyQueue();
		myQueue.add("1");
		myQueue.add("2");
		myQueue.add("3");
		myQueue.add("4");
		System.out.println(myQueue.peek());
		myQueue.remove();
		System.out.println(myQueue.toString());
	}
	
}
