package org.ko.algorithm.sort;

public class InsertionSort {

	/**
	 * 插入排序
	 * @param args
	 * @return
	 */
	public static int[] sort (int[] args) {
		
		for (int i = 1; i < args.length; i++) {
			//记录当前位置的元素
			int ret = args[i], j;
			//如果当前元素比前一个小-则向前挪一位
			for (j = i; j > 0 && args[j - 1] > ret; j--) 
				args[j] = args[j - 1];
			//将
			args[j] = ret;
		}
		
		return args;
	}
	
	private InsertionSort () {};
}
