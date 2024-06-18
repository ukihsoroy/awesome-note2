package org.ko.structure;


import org.junit.jupiter.api.Test;

import java.util.*;

class StructureTests {

    /**
     * 1) 枚举-Enumeration接口中定义了一些方法，通过这些方法可以枚举（一次获得一个）对象集合中的元素。
     */
    @Test void testEnumeration() {
        Enumeration<String> days;

        Vector<String> dayNames = new Vector<>();
        dayNames.add("Sunday");
        dayNames.add("Monday");
        dayNames.add("Tuesday");
        dayNames.add("Wednesday");
        dayNames.add("Thursday");
        dayNames.add("Friday");
        dayNames.add("Saturday");
        days = dayNames.elements();
        while (days.hasMoreElements()){
            System.out.println(days.nextElement());
        }
    }

    /**
     * 2) 位集合-一个Bitset类创建一种特殊类型的数组来保存位值。
     */
    @Test void testBitSet () {
        BitSet bits1 = new BitSet(16);
        BitSet bits2 = new BitSet(16);

        // set some bits
        for(int i=0; i<16; i++) {
            if((i%2) == 0) bits1.set(i);
            if((i%5) != 0) bits2.set(i);
        }
        System.out.println("Initial pattern in bits1: ");
        System.out.println(bits1);
        System.out.println("\nInitial pattern in bits2: ");
        System.out.println(bits2);

        // AND bits
        bits2.and(bits1);
        System.out.println("\nbits2 AND bits1: ");
        System.out.println(bits2);

        // OR bits
        bits2.or(bits1);
        System.out.println("\nbits2 OR bits1: ");
        System.out.println(bits2);

        // XOR bits
        bits2.xor(bits1);
        System.out.println("\nbits2 XOR bits1: ");
        System.out.println(bits2);

    }

    /**
     * 3) 向量-Vector类实现了一个动态数组。和ArrayList和相似，但是两者是不同的：
     *    - Vector是同步访问的。
     *    - Vector包含了许多传统的方法，这些方法不属于集合框架。
     */
    @Test void testVector () {
        // initial size is 3, increment is 2
        Vector<Number> v = new Vector<>(3, 2);
        System.out.println("Initial size: " + v.size());
        System.out.println("Initial capacity: " + v.capacity());
        v.addElement(1);
        v.addElement(2);
        v.addElement(3);
        v.addElement(4);
        System.out.println("Capacity after four additions: " + v.capacity());

        v.addElement(5.45);
        System.out.println("Current capacity: " +
                v.capacity());
        v.addElement(6.08);
        v.addElement(7);
        System.out.println("Current capacity: " +
                v.capacity());
        v.addElement(9.4f);
        v.addElement(10);
        System.out.println("Current capacity: " +
                v.capacity());
        v.addElement(11);
        v.addElement(12);
        System.out.println("First element: " + v.firstElement());
        System.out.println("Last element: " + v.lastElement());
        if(v.contains(3))
            System.out.println("Vector contains 3.");
        // enumerate the elements in the vector.
        Enumeration vEnum = v.elements();
        System.out.println("\nElements in vector:");
        while(vEnum.hasMoreElements())
            System.out.print(vEnum.nextElement() + " ");
        System.out.println();
    }

    /**
     * 4) 栈-栈是Vector的一个子类，它实现了一个标准的后进先出的栈。
     *    - 先进后出
     */
    @Test void testStack () {
        Stack<Integer> st = new Stack<>();
        System.out.println("stack: " + st);
        showPush(st, 42);
        showPush(st, 66);
        showPush(st, 99);
        showPop(st);
        showPop(st);
        showPop(st);
        try {
            showPop(st);
        } catch (EmptyStackException e) {
            System.out.println("empty stack");
        }
    }

    /**
     * 5) 字典-Dictionary 类是一个抽象类，用来存储键/值对，作用和Map类相似。
     *      - 已经过时了, 实际开发中使用map.
     */
    @Test void testDictionary () {
        Dictionary d;
    }

    /**
     * 6) 哈希表-Hashtable是原始的java.util的一部分， 是一个Dictionary具体的实现。
     *
     */
    @Test void testHashTable () {
        // Create a hash map
        Hashtable<String, Double> balance = new Hashtable<>();
        Enumeration names;
        String str;
        double bal;

        balance.put("Zara", 3434.34);
        balance.put("Mahnaz", 123.22);
        balance.put("Ayan", 1378.00);
        balance.put("Daisy", 99.22);
        balance.put("Qadir", -19.08);

        // Show all balances in hash table.
        names = balance.keys();
        while(names.hasMoreElements()) {
            str = (String) names.nextElement();
            System.out.println(str + ": " + balance.get(str));
        }
        System.out.println();
        // Deposit 1,000 into Zara's account
        bal = balance.get("Zara");
        balance.put("Zara", bal + 1000);
        System.out.println("Zara's new balance: " + balance.get("Zara"));
    }

    /**
     * 7) 属性-Properties继承于 Hashtable.表示一个持久的属性集.属性列表中每个键及其对应值都是一个字符串。
     *      -Properties 类被许多Java类使用。例如，在获取环境变量时它就作为System.getProperties()方法的返回值。
     *      -Properties 定义如下实例变量.这个变量持有一个Properties对象相关的默认属性列表。
     */
    @Test void testProperties () {
        Properties capitals = new Properties();
        Set states;
        String str;

        capitals.put("Illinois", "Springfield");
        capitals.put("Missouri", "Jefferson City");
        capitals.put("Washington", "Olympia");
        capitals.put("California", "Sacramento");
        capitals.put("Indiana", "Indianapolis");

        // Show all states and capitals in hashtable.
        states = capitals.keySet(); // get set-view of keys
        for (Object state : states) {
            str = (String) state;
            System.out.println("The capital of " + str + " is " + capitals.getProperty(str) + ".");
        }
        System.out.println();

        // look for state not in list -- specify default
        str = capitals.getProperty("Florida", "Not Found");
        System.out.println("The capital of Florida is " + str + ".");
    }


    @Test void testXor () {
        int[] ary1 = {1, 2, 3, 4, 5};
        int[] ary2 = {2, 4, 6, 8, 10};
        for (int i = 0; i < 5; i++) {
            ary1[i] ^= ary2[i];
        }
        for (int i : ary1) {
            System.out.println(i);
        }

        int a = 1;
        a ^= 2;
        System.out.println(a);
    }


    void showPush(Stack<Integer> st, int a) {
        st.push(a);
        System.out.println("push(" + a + ")");
        System.out.println("stack: " + st);
    }

    void showPop(Stack<Integer> st) {
        System.out.print("pop -> ");
        Integer a = st.pop();
        System.out.println(a);
        System.out.println("stack: " + st);
    }

}
