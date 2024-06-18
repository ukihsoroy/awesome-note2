package org.ko.concurrency;

import lombok.extern.slf4j.Slf4j;

import java.util.Scanner;

@Slf4j
public class PowerTest {

    public static void main(String[] args) {
        int[] ary = new int[5];


        Scanner scan = new Scanner(System.in);

        for (int i = 0; i < ary.length; i++) {
            ary[i] = scan.nextInt();
        }

        int max = ary[0], min = ary[0];

        for (int i1 : ary) {
            if (max < i1) {
                max = i1;
            }
            if (min > i1) {
                min = i1;
            }

        }

        log.info("max: {}", max);
        log.info("min: {}", min);

    }
}
