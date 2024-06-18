package org.ko.concurrency.inmutable;

import com.google.common.collect.Maps;
import lombok.extern.slf4j.Slf4j;
import org.ko.concurrency.annoations.NotThreadSafe;

import java.util.Map;

@NotThreadSafe
@Slf4j
public class ImmutableExample1 {

    private final static Integer a = 1;

    private final static String b = "2";

    private final static Map<Integer, Integer> map = Maps.newHashMap();

    static {
        map.put(1, 2);
        map.put(3, 4);
        map.put(5, 6);
    }

    public static void main(String[] args) {
//        a = 2; 不允许修改基本数据类型的值
//        b = "3";
//        map = Maps.newHashMap() 引用类型不允许修改指向
        //但是可以修改里面的值
        map.put(1, 3);
        log.info("map(1): {}", map.get(1));

    }

    //final修饰方法参数
    private void test(final int a) {
//        a = 1;
    }
}
