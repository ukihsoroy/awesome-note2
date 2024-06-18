package org.ko.java8.lambda;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.ko.java8.bean.People;

import java.util.*;
import java.util.stream.Collectors;

public class LambdaDemo {

    private List<People> peoples = null;

    @BeforeEach void before () {
        peoples = new ArrayList<>();
        peoples.add(new People("K.O1", 21, new Date()));
        peoples.add(new People("K.O3", 23, new Date()));
        peoples.add(new People("K.O4", 24, new Date()));
        peoples.add(new People("K.O5", 25, new Date()));
        peoples.add(new People("K.O2", 22, new Date()));
        peoples.add(new People("K.O6", 26, new Date()));
    }

    /**
     * 提取1列
     */
    @Test void whenExtractColumnSuccess () {
        //第一种写法
        List<Integer> ages1 = peoples.stream().map(people -> people.getAge()).collect(Collectors.toList());
        System.out.println("###println: args1----");
        ages1.forEach(System.out::println);

        //简单一点的写法
        List<Integer> ages2 = peoples.stream().map(People::getAge).collect(Collectors.toList());
        System.out.println("###println: args2----");
        ages1.forEach(System.out::println);
    }

    /**
     * 只要年纪大于25岁的人
     */
    @Test void whenFilterAgeGT25Success () {
        List<People> peoples1 = peoples.stream().filter(x -> x.getAge() > 25).collect(Collectors.toList());
        peoples1.forEach(x -> System.out.println(x.toString()));
    }

    /**
     * 求和全部年纪
     */
    @Test void sumAllPeopleAgeSuccess () {
        Integer sum1 = peoples.stream().collect(Collectors.summingInt(People::getAge));
        System.out.println("###sum1: " + sum1);
        Integer sum2 = peoples.stream().mapToInt(People::getAge).sum();
        System.out.println("###sum2: " + sum2);
    }

    /**
     * 取出年纪为25岁的人
     */
    @Test void extractAgeEQ25Success () {
        Optional<People> optionalPeople =  peoples.stream().filter(x -> x.getAge() == 25).findFirst();
        if (optionalPeople.isPresent()) System.out.println("###name1: " + optionalPeople.get().getName());

        //简写
        peoples.stream().filter(x -> x.getAge() == 25).findFirst().ifPresent(x -> System.out.println("###name2: " + x.getName()));
    }

    /**
     * 逗号拼接全部名字
     */
    @Test void printAllNameSuccess () {
        String names = peoples.stream().map(People::getName).collect(Collectors.joining(","));
        System.out.println(names);
    }

    /**
     * 将集合转成(name, age) 的map
     */
    @Test void list2MapSuccess () {
        Map<String, Integer> map1 = peoples.stream().collect(Collectors.toMap(People::getName, People::getAge));
        map1.forEach((k, v) -> System.out.println(k + ":" + v));

        System.out.println("--------");

        //(name object)
        Map<String, People> map2 = peoples.stream().collect(Collectors.toMap(People::getName, People::getThis));
        map2.forEach((k, v) -> System.out.println(k + ":" + v.toString()));
    }

    /**
     * 按名字分组
     */
    @Test void listGroupByNameSuccess() {
        //添加一个元素方便看效果
        peoples.add(new People("K.O1", 29, new Date()));
        Map<String, List<People>> map = peoples.stream().collect(Collectors.groupingBy(People::getName));

        map.forEach((k, v) -> System.out.println(k + ":" + v.size()));
    }

    /**
     * 求人平均年龄
     */
    @Test void averagingAgeSuccess () {
        Double avgAge = peoples.stream().collect(Collectors.averagingInt(People::getAge));
        System.out.println(avgAge);
    }

    /**
     * 按年龄排序
     */
    @Test void sortByAgeSuccess () {
        System.out.println("###排序前---");
        peoples.forEach(x -> System.out.println(x.getAge()));

        peoples.sort((x, y) -> {
            if (x.getAge() > y.getAge()) {
                return 1;
            } else if (x.getAge() == y.getAge()) {
                return 0;
            }
            return -1;
        });

        System.out.println("###排序后---");
        peoples.forEach(x -> System.out.println(x.getAge()));
    }

}
