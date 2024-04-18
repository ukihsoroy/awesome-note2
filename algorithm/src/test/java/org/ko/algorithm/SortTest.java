package org.ko.algorithm;

import org.junit.Test;
import org.ko.algorithm.sort.*;
//import org.ko.util.KOUtils;
public class SortTest {
	
	private static final int N = 500;
	private static final boolean PRINT = true;
	/**
	 * 测试插入排序
	 */
	@Test public void test1 () {
		int[] args = createArray(N);
		long startTime = System.currentTimeMillis();
 		InsertionSort.sort(args);
 		long endTime = System.currentTimeMillis();
 		print(args);
 		System.out.println("插入排序" + (endTime - startTime)/1000.0 + "秒！");
	}
	
	/**
	 * 测试选择排序
	 */
	@Test public void test2 () {
		int[] args = createArray(N);
		long startTime = System.currentTimeMillis();
 		//SelectionSort.sort(args);
 		long endTime = System.currentTimeMillis();
 		print(args);
 		System.out.println("选择排序" + (endTime - startTime)/1000.0 + "秒！");
	}
	
	/**
	 * 测试归并排序
	 */
	@Test public void test3 () {
		int[] args = createArray(N);
		long startTime = System.currentTimeMillis();
 		MergeSort.sort(args);
 		long endTime = System.currentTimeMillis();
 		print(args);
 		System.out.println("归并排序" + (endTime - startTime)/1000.0 + "秒！");
	}
	
	/**
	 * 测试快速排序
	 */
	@Test public void test4 () {
		int[] args = createArray(N);
		long startTime = System.currentTimeMillis();
 		QuickSort.sort(args);
 		long endTime = System.currentTimeMillis();
 		print(args);
 		System.out.println("快速排序" + (endTime - startTime)/1000.0 + "秒！");	
	}
	
	@Test public void test5 () {
		int[] args = createArray(N);
		long startTime = System.currentTimeMillis();
		//HeapSort.heapSort1(args);
 		long endTime = System.currentTimeMillis();
 		print(args);
 		System.out.println("堆排序" + (endTime - startTime)/1000.0 + "秒！");	
	}
	
	@Test public void testTest () {
		test1();
		test2();
		test3();
		test4();
	}
	
	
	/**
	 * 生成一个随机的数组
	 * @param n
	 * @return
	 */
	protected int[] createArray (int n) {
		int[] args = new int[n];
		for (int i = 0; i < n; i++) {
			args[i] = (int)(Math.random() * n);
		}
		print(args);
		return args;
	}
	
	/**
	 * 生成一个近乎有序的数组
	 * @param n
	 * @param x
	 * @return
	 */
	protected int[] createArray (int n, int x) {
		int[] args = new int[n];
		for (int i = 0; i < n; i++) {
			args[i] = i + 1;
		}
		
		for (int i = 0; i < x; i++) {
			int l = (int)(Math.random() * n);
			int j = (int)(Math.random() * n);
			//KOUtils.swap(args, l, j);
		}
		print(args);
		return args;
	}
	
	/**
	 * 生成一个值在0-10区间的数组
	 * @return
	 */
	protected int[] createArray () {
		int[] args = new int[N];
		for (int i = 0; i < N; i++) {
			args[i] = (int)(Math.random() * 10);
		}
		print(args);
		return args;
	}
	
	protected void print (int[] args) {
		if (!PRINT) return;
		for (int i = 0; i < args.length; i++) {
			System.out.println(args[i]);
		}
	}
}
