package com.sund.test;

import org.junit.Test;

public class TestBinarySearch {
	
	public int bs(int[] arr, int dest) {
		int low = 0;
		int high = arr.length-1;
		while(low<=high) {
			int middle = (low+high)/2;
			if(dest==arr[middle]) {
				return middle;
			} else if(dest>arr[middle]) {
				low = middle+1;
			} else {
				high = middle-1;
			}
		}
		return -1;
	}
	
	@Test
	public void testBs() {
		int[] x = {1,2,2,4};
		System.out.println("pos: "+bs(x, 2));
	}
}
