package org.ko.structure.heap;

/**
 * 实现一个完全二叉树最大堆
 * @author K.O
 *
 */
public class MaxHeap {
	
	//堆的實現數組
	private int[] data;
	
	//数组的长度
	private int count;
	
	int capacity;
	
	public MaxHeap(int capacity) {
		this.data = new int[capacity + 1];
		this.capacity = capacity;
		this.count = 0;
	}
	
	public int size () {
		return count;
	}
	
	boolean isEmpty () {
		return count == 0;
	}
	
	public void insert (int item) {
		if (count > capacity) return;
		
		data[count + 1] = item;
		count ++;
		shiftUp (count);
	}
	
	public int extractMax () {
		if (count <= 0) return -1;
		int ret = data[1];
		swap(data, 1, -- count);
		shiftDown (1);
		return ret;
	}
	
	//新添加元素在最后位置--使用这个函数向上移动
	private void shiftUp (int k) {
		while (data[k / 2] < data[k]) {
			swap(data, k / 2, k);
			k /= 2;
		}
	}
	
	//删除元素时使用--元素向下移动
	private void shiftDown (int k) {
		while (k * 2 <= count) {
			//在此轮循环中， data[k]和data[j]交换位置
			int j = 2 * k;		
			//判断是否有右孩子-并且右孩子比左孩子大-则索引记录右孩子的值
			if (j + 1 <= count && data[j + 1] > data[j]) j += 1;
			//如果孩子小于自己-则说明符合最大堆-返回
			if (data[k] >= data[j]) break;
			//交换位置
			swap(data, k, j);
			//记录K的位置-进行下个节点判断循环
			k = j;
		}
	}

	private void swap (int[] args, int i, int j) {
		int ret = args[i];
		args[i] = args[j];
		args[j] = ret;
	}
	
}
