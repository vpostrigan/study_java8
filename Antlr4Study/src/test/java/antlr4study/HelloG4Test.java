package antlr4study;

import org.junit.Test;

public class HelloG4Test {

    @Test
    public void test() {
        new HelloG4().parse("hello, World!");
    }

}
