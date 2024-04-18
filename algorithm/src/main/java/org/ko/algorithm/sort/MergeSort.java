package org.ko.algorithm.sort;

/**
 * 归并排序
 * @author K.O
 *
 */
public class MergeSort {
	
	
	public static void sort (int args[]) {
		__mergeSortBU(args);
	}
	
	public static void sortBU (int args[]) {
		__mergeSort(args, 0, args.length - 1);
	}
	
	/**
	 * 自底向上--------特别适合对链表进行排序
	 * @param args
	 */
	private static void __mergeSortBU (int args[]) {
		//merge的元素数量
		for (int sz = 1; sz <= args.length - 1; sz += sz) 
			//元素的位置
			for (int i = 0; i + sz < args.length - 1; i += sz + sz) 
				//对 args[i....i+sz-1] 和 args[i+sz...i+2*sz-1] 进行归并
				__merge(args, i, i + sz - 1, Math.min(i + sz + sz -1, args.length - 1));
	}
	
	/**
	 * 递归使用归并排序，对arr[l....r]的范围进行排序---自顶向下
	 * @param args
	 */
	private static void __mergeSort (int args[], int l, int r) {
		
		if (l >= r) return;
		
		//优化 当数组长度小于15的时候选择插入排序
//		if (r - l <= 15) {
//			InsertionSort.sort(args);
//			return;
//		}
		
		//取出中间变量
		int mid = (l + r) / 2;
		//对mid之前的元素进行排序
		__mergeSort (args, l, mid);
		//对mid之后的元素进行排序
		__mergeSort (args, mid+1, r);
		//排序完成后，只有mid > mid+1的时候 才需要对两个数组进行合并
		if (args[mid] > args[mid + 1])	//优化
			__merge (args, l, mid, r);
	}
	 
	private static void __merge (int [] args, int l, int mid, int r) {
		//定义一个中间数组
		int[] aux = new int [r - l +1];
		//复制数组
		for (int i = l; i <= r; i++) {
			aux [i -l] = args[i];
		}
		//定义i为第一个数组初始值，j为第二个数组初始值
		int i = l, j = mid + 1;
		//定义循环变量k从初始值开始
		for (int k = l; k <= r; k++) {
			if (i > mid) {
				//如果i大于中间变量了--说明第一列数组的值已经输出完毕了--只需要将第二段数组罗列就可以了
				args[k] = aux[j - l];
				j++;
			} else if (j > r) {
				//同理 如果j > 最大值了 说明第二段数组已经输出完毕，只需要将第一段数组罗列就ok
				args[k] = aux[i - l];
				i++;
			} else if (aux[i-l] < aux[j - l]) {
				//第一个数组值小于第二个数组的值--则第一个变量为第一个数组的第一个值
				args[k] = aux[i - l];
				i++;
			} else {
				//第一个数组值大于第二个数组的值--则第一个变量为第二个数组的第一个值
				args[k] = aux [j - l];
				j++;
			}
		}
	}
	
	private MergeSort () {};
	
}
