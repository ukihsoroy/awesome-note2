package org.ko.power;

import java.util.ArrayList;
import java.util.List;

public class P1 {

    //100kg，
    //1kg 5kg 10kg
    //2 5 6
    public static void main(String[] args) {
        List<String> integers = new ArrayList<>();
        for (int i = 100 / 1; i >= 0; i-- ) {
            for (int j = 100 - i * 1 / 5; j >= 0; j--) {
                for (int k = 100 - (i * 1 + j * 5); k >= 0; k--) {
                    if (i * 1 + j * 5 + k * 10 == 100) {
                        integers.add(i + "," + j + "," + k);
                    }
                }
            }
        }

        integers.forEach(System.out::println);
    }


}
