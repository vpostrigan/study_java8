package evolving_junit_5;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

/**
 * 1) @Test - in org.junit.jupiter.api
 * 2) Assertions instead of Assert
 * 3) public modifier isn't required anymore
 */
class a1_JUnitBasics {

    @Test
    void test() {
        assertEquals(2, 1 + 1);
    }

}
