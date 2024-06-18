//package org.ko.algorithm.sort;
//
//
//import org.ko.algorithm.util.Helper;
//import org.ko.structure.heap.MaxHeap;
//
//public class HeapSort {
//
//	public static void heapSort1 (int[] args) {
//		MaxHeap heap = new MaxHeap(args.length);
//
//		//赋值一份数组放到堆中
//		for (int i = 0; i < args.length; i ++) {
//			heap.insert(args[i]);
//		}
//
//		//进行排序--倒序排序
//		for (int i = args.length - 1; i > -1; i --) {
//			args [i] = heap.extractMax();
//		}
//	}
//
//	/**
//	 * 原地堆排序
//	 * @param args
//	 */
//	public static void heapSort (int[] args) {
//		for (int i = (args.length-1)/2; i>= 0; i--) {
//			__shiftDown(args, args.length, i);
//		}
//
//		for (int i = args.length- 1; i>0; i--) {
//			Helper.swap(args, 0, i);
//			__shiftDown (args, i, 0);
//		}
//	}
//
//	//删除元素时使用--元素向下移动
//	private static void __shiftDown (int[] args, int n, int k) {
//		while (k * 2 +1 <= n) {
//			int j = 2 * k + 1;
//			if (j + 1 < n && args[j + 1] > args[j])
//				j += 1;
//			if (args[k] >= args[j])
//				break;
//			Helper.swap(args, k, j);
//			k = j;
//		}
//	}
//}
