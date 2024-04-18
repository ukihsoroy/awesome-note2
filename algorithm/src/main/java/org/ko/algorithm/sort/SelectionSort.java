package org.ko.algorithm.sort;

import org.ko.algorithm.util.Helper;

/**
 * 选择排序
 * @author K.O
 */
public class SelectionSort {

	/**
	 * 选择排序 On方
	 * @param args
	 * @return
	 */
	public static int[] sort (int[] args) {
		for (int i = 0; i < args.length; i++) {
			//找到[1...n)的最小值
			int minIndex = i;
			for (int j = i + 1; j < args.length; j++) 
				if (args[j] < args[minIndex]) 
					minIndex = j;
			Helper.swap(args, minIndex, i);
		}
		
		return args;
	}
	
	private SelectionSort () {}
}
