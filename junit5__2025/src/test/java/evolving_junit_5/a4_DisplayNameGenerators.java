package evolving_junit_5;

import org.junit.jupiter.api.DisplayNameGeneration;
import org.junit.jupiter.api.DisplayNameGenerator;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

@DisplayNameGeneration(DisplayNameGenerator.ReplaceUnderscores.class)
class a4_DisplayNameGenerators {

    @Test
    void if_it_is_zero() {
    }

    @ParameterizedTest
    @ValueSource(ints = {-1, -4})
    void if_it_is_negative(int year) {
    }

}
