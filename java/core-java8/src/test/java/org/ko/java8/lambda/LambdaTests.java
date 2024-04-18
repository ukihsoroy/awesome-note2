package org.ko.java8.lambda;

import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

import static java.lang.System.out;

public class LambdaTests {

    @Test
    public void whenJava7Before () {
        List<Integer> ary = Arrays.asList(4, 3, 1, 2);
        Collections.sort(ary, new Comparator<Integer>() {
            @Override
            public int compare(Integer o1, Integer o2) {
                return o1.compareTo(o2);
            }
        });

        ary.forEach(out::println);
    }

    @Test void whenJava8 () {
        List<Integer> ary = Arrays.asList(4, 3, 1, 2);

        //简化1
//        Collections.sort(ary, (o1, o2) -> o1.compareTo(o2));

        //简化2
//        ary.sort((o1, o2) -> o1.compareTo(o2));

        //简化3
        ary.sort(Integer::compareTo);

        //输出
        ary.forEach(out::println);
    }

}
