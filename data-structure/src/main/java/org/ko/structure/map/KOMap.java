package org.ko.structure.map;

/**
 * Map 接口
 * @author K.O
 *
 * @param <K>
 * @param <V>
 */
public interface KOMap<K, V> {
	
	/**
	 * 获取value
	 * @param key
	 * @return
	 */
	V get(K key);

	/**
	 * 存入map
	 * @param key
	 * @param value
	 * @return
	 */
	V put(K key, V value);
	
	/**
	 * 返回Map大小
	 * @return
	 */
	int size();
	
	/**
	 * map存入值的对象
	 * @author K.O
	 *
	 * @param <K>
	 * @param <V>
	 */
	interface Entry<K, V> {

		/**
		 * 获取key
		 * @return
		 */
		K getKey();

		/**
		 * 获取value
		 * @return
		 */
		V getValue();
	}
	
}
