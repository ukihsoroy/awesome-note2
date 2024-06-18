package org.ko.java8.def.method;

/**
 * java8 新特性 接口默认方法
 */
public interface IFormula {

    double calculate(int a);

    default double sqrt(int a) {
        return Math.sqrt(a);
    }

    default String get () {
        return "default methods.";
    }

}
