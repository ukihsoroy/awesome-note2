# java8 新特性

1. 接口默认方法
```java
/**
 * java8 新特性 接口默认方法
 */
public interface IFormula {
    //实现类必须实现
    double calculate(int a);
    
    //实现类可以选择实现
    default double sqrt(int a) {
        return Math.sqrt(a);
    }

    //实现类可以选择实现
    default String get () {
        return "default methods.";
    }

}

```
> 译者注： 在Java中只有单继承，如果要让一个类赋予新的特性，通常是使用接口来实现，在C++中支持多继承，允许一个子类同时具有多个父类的接口与功能，在其他语言中，让一个类同时具有其他的可复用代码的方法叫做mixin。新的Java 8 的这个特新在编译器实现的角度上来说更加接近Scala的trait。 在C#中也有名为扩展方法的概念，允许给已存在的类型扩展方法，和Java 8的这个在语义上有差别。
