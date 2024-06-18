package org.ko.lombok;

import org.junit.jupiter.api.Test;

public class UserTests {

    @Test void testGetSet () {
        Duck duck = new Duck();

        //1. get set
        duck.setHead("red");
        duck.setFoot("green");
        System.out.println(duck.getHead());
        System.out.println(duck.getFoot());

        //2. toString
        System.out.println(duck.toString());

        Duck d1 = new Duck("1", "2");
        System.out.println(d1.toString());

    }

    @Test void testData () {


    }


}
