package org.ko.java8.def.method;

import org.junit.jupiter.api.Test;

public class DefaultMethodTests {

    @Test
    public void defaultMethod () {
        FormulaImpl formula = new FormulaImpl();

        System.out.println(formula.calculate(3));

        System.out.println(formula.sqrt(9));

        System.out.println(formula.get());
    }
}
