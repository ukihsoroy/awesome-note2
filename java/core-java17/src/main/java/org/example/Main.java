package org.example;

import java.text.NumberFormat;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Locale;
import java.util.stream.Stream;

public class Main {
    public static void main(String[] args) {

        // 文本块
        String text = """
            {
              "name": "小黑说Java",
              "age": 18,
              "address": "北京市西城区"
            }
            """;
        System.out.println(text);

        // switch优化
        String fruit = "1";
        switch (fruit) {
            case "1", "2" -> System.out.println("普通水果");
            case "3", "4" -> System.out.println("进口水果");
            default -> System.out.println("未知水果");
        }

        // 使用 ->
        String t1 = switch (fruit) {
            case "1", "2" -> "普通水果";
            case "3", "4" -> "进口水果";
            default -> "未知水果";
        };
        System.out.println(t1);

        System.out.println(switch (fruit) {
            case "1", "2" -> "普通水果";
            case "3", "4" -> "进口水果";
            default -> "未知水果";
        });

        System.out.println(switch (fruit) {
            case "1", "2"-> {
                System.out.println("yield test");
                yield "普通水果";
            }
            case "3", "4" -> "进口水果";
            default -> "未知水果";
        });

        System.out.println(switch (fruit) {
            case "1", "2":
                yield "普通水果";
            case "3", "4":
                yield "进口水果";
            default:
                yield "未知水果";
        });

        //record关键字,用来快速定义类

        record PersonRecord(String name, String age){}
        PersonRecord r1 = new PersonRecord("a", "1");
        PersonRecord r2 = new PersonRecord("b", "2");
        System.out.println(r1);
        System.out.println(r2);

        //instanceof 代码简写，带了格式转换
        Object o = "1";
        if (o instanceof String f) {
            System.out.println("This furit is :" + f);
        }

        /*
            异常处理优化
            Helpful NullPointerExceptions
            Helpful NullPointerExceptions可以在我们遇到NPE时节省一些分析时间。如下的代码会导致一个NPE。
         */

        //时间处理函数
        DateTimeFormatter dtf = DateTimeFormatter.ofPattern("B");
        System.out.println(dtf.format(LocalTime.of(8, 0)));
        System.out.println(dtf.format(LocalTime.of(13, 0)));
        System.out.println(dtf.format(LocalTime.of(20, 0)));
        System.out.println(dtf.format(LocalTime.of(23, 0)));
        System.out.println(dtf.format(LocalTime.of(0, 0)));

        //在NumberFormat中添加了一个工厂方法，可以根据Unicode标准以紧凑的、人类可读的形式格式化数字。
        NumberFormat fmt = NumberFormat.getCompactNumberInstance(Locale.ENGLISH, NumberFormat.Style.SHORT);
        System.out.println(fmt.format(1000));
        System.out.println(fmt.format(100000));
        System.out.println(fmt.format(1000000));

        fmt = NumberFormat.getCompactNumberInstance(Locale.ENGLISH, NumberFormat.Style.LONG);
        System.out.println(fmt.format(1000));
        System.out.println(fmt.format(100000));
        System.out.println(fmt.format(1000000));

        Stream<String> stringStream = Stream.of("a", "b", "c");
        List<String> stringList =  stringStream.toList();
        for(String s : stringList) {
            System.out.println(s);
        }

    }

    //sealed 密封类
    public abstract sealed class Fruit permits Apple,Pear {
    }
    public non-sealed class Apple extends Fruit {
    }
    public final class Pear extends Fruit {

    }


}