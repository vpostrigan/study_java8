package evolving_junit_5;

import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Tag;

@DisplayName("Calculator")
class a3_MoreBasics {

    @Disabled // @Disabled instead of @Ignore (JUnit4)
    @Tag("feature-addition") // @Tag instead of @Category (JUnit4)
    @DisplayName("should return sum of two numbers when adding")
    void add() {
    }

}
