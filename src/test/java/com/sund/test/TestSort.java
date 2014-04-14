package com.sund.test;

import org.junit.Test;

public class TestSort {
	/**
	 * √∞≈›≈≈–Ú o(n2)
	 */
	@Test
	public void testBubbleSort() {
		int[] arr = {3,1,4,99,2,4,9,10,4,2,22,0};
		long t1 = System.nanoTime();
		for(int i=0; i<arr.length; i++) {
			for(int j=i+1; j<arr.length; j++) {
				int tmp = 0;
				if(arr[i]>arr[j]) {
					tmp = arr[i];
					arr[i] = arr[j];
					arr[j] = tmp;
				}
			}
		}
		long t2 = System.nanoTime();
		System.out.println(t2-t1);
		for(int i=0; i<arr.length; i++) {
			System.out.print(arr[i]+" ");
		}
	}
	
	@Test
	public void testChoiceSort() {
		int[] arr = {3,1,4,99,2,4,9,10,4,2,22,0};
		long t1 = System.nanoTime();
		for(int i=0; i<arr.length; i++) {
			int min = i;
			for(int j=i+1; j<arr.length; j++) {
				if(arr[i]>arr[j]) {
					min = j;
				}
			}
			if(i!=min) {
				int tmp = arr[i];
				arr[i] = arr[min];
				arr[min] = tmp;
			}
		}
		long t2 = System.nanoTime();
		System.out.println(t2-t1);
		for(int i=0; i<arr.length; i++) {
			System.out.print(arr[i]+" ");
		}
	}
}
