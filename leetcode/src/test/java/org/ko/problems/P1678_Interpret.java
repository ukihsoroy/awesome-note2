package org.ko.problems;

import org.junit.jupiter.api.Test;

public class P1678_Interpret {

    public String interpret(String command) {
        return command.replace("()", "o").replace("(al)", "al");
    }

    @Test
    public void test1() {
        System.out.println(interpret("G()()()()(al)"));
    }
}
