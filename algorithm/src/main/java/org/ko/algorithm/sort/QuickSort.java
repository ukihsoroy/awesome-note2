package org.ko.algorithm.sort;

/**
 * 快速排序
 * @author K.O
 */
public class QuickSort {

	
	public static void sort (int[] args) {
		__quickSort (args, 0, args.length - 1);
	}
	
	public static void sort2 (int[] args) {
		__quickSort2 (args, 0, args.length - 1);
	}
	
	public static void sort3Ways (int[] args) {
		__quickSort3Ways (args, 0, args.length - 1);
	}
	
	//对args[l...r]部分进行Partition操作
	//返回p, 使得args[l...p-1] < args[p]; args[p+1...r] > args[p]
	private static int __partition (int[] args, int l, int r) {
		
		//优化---选取一个随机数值与第一个元素互换
		swap(args, l, ((int)(Math.random() * (r - l + 1) + l)));
		
		//初始化记录数组第一个元素
		int v = args[l];
		//args[l+1...j] < v; args[j+1...i] > v
		//记录合适的位置
		int j = l;
		for (int i = l + 1; i <= r; i++) {
		    /* 
		     * -遍历元素和第一个元素比叫
			 * -如果比第一个元素小则两个元素互换并且j+1
			 * -满足j的左边都比j小右边都比j大
			 */
			if (args[i] < v) {
				swap(args, ++j, i);
			}
		}
		//最后将J的位置和第一个元素互换
		swap(args, l, j);
		return j;
	}
	
	private static void __quickSort (int[] args, int l, int r) {
		
		if (l >= r) return;
		//优化 当数组长度小于15的时候选择插入排序
//		if (r - l <= 15) {
//			InsertionSort.sort(args);
//			return;
//		}
		
		//获取parttion
		int p = __partition (args, l, r);
		//分别对两端进行递归
		__quickSort (args, l, p - 1);
		__quickSort (args, p + 1, r);
	}
	
	
	//对args[l...r]部分进行Partition操作---很多重复键值优化
	//返回p, 使得args[l...p-1] < args[p]; args[p+1...r] > args[p]
	private static int __partition2 (int[] args, int l, int r) {
		
		//优化---选取一个随机数值与第一个元素互换
		swap(args, l, ((int)(Math.random() * (r - l + 1) + l)));
		
		//初始化记录数组第一个元素
		int v = args[l];
		//args[l+1...i] <= v; args[j....r] > v
		//记录合适的位置
		int i = l +1, j = r;
		while (true) {
			while (i <= r && args[i] < v) i ++;
			while (j >= l +1 && args[j] > v) j --;
			if (i > j) break;
			swap(args, i, j);
			i ++;
			j --;
		}
		swap(args, l, j);
		return j;
	}
	
	private static void __quickSort2 (int[] args, int l, int r) {
		if (l >= r) return;
		//优化 当数组长度小于15的时候选择插入排序
//		if (r - l <= 15) {
//			InsertionSort.sort(args);
//			return;
//		}
		
		//获取parttion
		int p = __partition2 (args, l, r);
		//分别对两端进行递归
		__quickSort2 (args, l, p - 1);
		__quickSort2 (args, p + 1, r);
	}
	
	
	// 三路快速排序处理 arr[l...r]
	// 将arr[l...r]分为 <v; ==v; >v 三部分
	// 之后递归对 <v; >v 两部分继续进行三路快速排序
	private static void __quickSort3Ways (int[] args, int l, int r) {
		if (l >= r) return;
		//优化 当数组长度小于15的时候选择插入排序
//		if (r - l <= 15) {
//			InsertionSort.sort(args);
//			return;
//		}
		swap(args, l, ((int)(Math.random() * (r - l + 1) + l)));
		int v = args[l];
		
		int lt = l; 				//arr[l+1...lt] < v
		int gt = r + 1; 		//arr[gt...r] > v
		int i = l + 1; 			//arr[lt+1...i] == v
		while (i < gt) {
			if (args[i] < v) {
				swap(args, i, lt + 1);
				lt ++;
				i ++;
			} else if (args[i] > v) {
				swap(args, i, gt - 1);
				gt --;
			} else {
				i ++;
			}
		}
		swap(args, l, --lt);
		
		__quickSort3Ways(args, l, lt);
		__quickSort3Ways(args, gt, r);
	}

	
	private QuickSort () {};

	protected static void swap (int[] ary, int i, int j) {
		int r = ary[i];
		ary[i] = ary[j];
		ary[j] = r;
	}
}
 