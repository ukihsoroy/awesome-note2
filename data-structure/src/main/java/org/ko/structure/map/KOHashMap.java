package org.ko.structure.map;

import java.util.ArrayList;
import java.util.List;

@SuppressWarnings({"unchecked", "statics-access"})
public class KOHashMap<K, V> implements KOMap<K, V>{

	
	/**
	 * 默认数组的长度
	 */
	private static int defaultLength = 16;
	
	/**
	 * 负载因子
	 */
	private static double defaultLoad = 0.75;
	
	/**
	 * map的容器
	 */
	private Entry<K, V>[] table = null;
	
	/**
	 * map的大小
	 */
	private int size = 0;
		
	private KOHashMap(int defaultLength, double defaultLoad) {
		this.defaultLength = defaultLength;
		this.defaultLoad = defaultLoad;
		table =  new Entry[defaultLength];
	}
	
	public KOHashMap() {
		this(defaultLength, defaultLoad);
	}
	
	

	public V get(K key) {
		//根据这个key,根据定义的函数算法算出数组的下表
		int index = getIndex(key);
		return getEntry(table[index], key);
	}
	
	private V getEntry (Entry<K, V> entry, K key) {
		if (entry == null) {
			return null;
		}
		
		if (key == entry.getKey() || key.equals(entry.getKey())) {
			return entry.getValue();
		} else {
			if (entry.next != null) {
				return getEntry (entry.next, key); 
			} else {
				return null;
			}
		}
	}

	public V put(K key, V value) {
		
		if (size > defaultLength * defaultLoad) {
			doubleSize();
		}
		
		//根据这个key,根据定义的函数算法算出数组的下表
		int index = getIndex(key);
		//查看下当前位置是否有元素
		Entry<K, V> entry = table[index];
		
		if (entry != null) {
			//如果index位置上面有对应元素，那么新建一个新元素，把锌元素的next指针指向旧元素
			Entry<K, V> newEntry = newEntry(key, value, entry, index);
			table[index] = newEntry;
		} else {
			Entry<K, V> newEntry = newEntry(key, value, null, index);
			table[index] = newEntry;
			size++;
		}
		return null;
	}
	
	/**
	 * 扩容算法， 把以前的表格扩容成两倍
	 */
	private void doubleSize () {
		Entry<K, V>[] newTable = new Entry[defaultLength * 2];
		
		List<Entry<K, V>> list = new ArrayList<Entry<K, V>>();
		//将原来数组里面元素再散列
		for (int i = 0; i < table.length; i++) {
			if (table[i] == null) {
				continue;
			} else {
				findEntry(table[i], list);
			}
		}
		againHash(newTable, list);
	}
	
	private void findEntry (Entry<K, V> entry, List<Entry<K, V>> list) {
		if (entry.next == null) {
			list.add(entry);
		} else {
			list.add(entry);
			findEntry(entry.next, list);
		}
	}
	
	/**
	 * 对新数组再散列
	 */
	private void againHash (Entry<K, V>[] newTable, List<Entry<K, V>> list ) {
		if (list != null && list.size() > 0) {
			this.defaultLength = this.defaultLength * 2;
			size = 0;
			table = newTable;
			
			//将list里面就表格里面的所有元素放入我们的新表格中
			for (Entry<K, V> entry : list) {
				put (entry.getKey(), entry.getValue());
			}
			
		}
	}
	
	private Entry<K, V> newEntry (K key, V value, Entry<K, V> next, int index) {
		return new Entry<K, V> (key, value, next, index);
	}
	
	private int getIndex (K key) {
		int index = key.hashCode() % defaultLength;
		return index > 0 ? index : -index;
	}
	
	public int size() {
		return this.size;
	}
	
	@SuppressWarnings("hiding")
	class Entry<K, V> implements KOMap.Entry<K, V> {
		K key;
		V value;
		Entry<K, V> next;
		int index;
		
		public Entry (K key, V value, Entry<K, V> next, int index) {
			this.key = key;
			this.value = value;
			this.next = next;
			this.index = index;
		}
		
		public Entry() {
			super();
		}
		
		public K getKey() {
			return this.key;
		}

		public V getValue() {
			// TODO Auto-generated method stub
			return this.value;
		}
		
	}


}
