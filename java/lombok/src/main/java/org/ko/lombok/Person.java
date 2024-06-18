package org.ko.lombok;

import lombok.Data;

//6. 包含了 @ToString、@EqualsAndHashCode、@Getter / @Setter和@RequiredArgsConstructor的功能
@Data
public class Person {

    private String name;

    private String age;
}
