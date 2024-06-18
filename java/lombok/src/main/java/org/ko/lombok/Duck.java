package org.ko.lombok;


import lombok.*;

//1. 动态生成GetSet
@Getter
@Setter
//2. 动态生成toString();
@ToString
//3. 重写equals和hashCode
@EqualsAndHashCode
//4. 无参构造方法, force为常量赋值
@NoArgsConstructor(force = true)
//5. 全参构造放
@AllArgsConstructor
public class Duck {

    private String head;

    private String foot;
}
