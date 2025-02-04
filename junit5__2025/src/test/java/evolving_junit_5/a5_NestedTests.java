package evolving_junit_5;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;

import java.util.Stack;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

@DisplayName("A stack")
class a5_NestedTests {
    Stack<Object> stack = new Stack();

    @Nested
    @DisplayName("when new")
    class WhenNew {
        @Test
        @DisplayName("is empty")
        void isEmpty() {
            assertTrue(stack.isEmpty());
        }

        @Nested
        @DisplayName("after pushing an element")
        class AfterPushing {
            @BeforeEach
            void pushAnElement() {
                stack.push("an element");
            }

            @Test
            @DisplayName("returns the element when popped and is empty")
            void returnElementWhenPopped() {
                assertEquals("an element", stack.pop());
                assertTrue(stack.isEmpty());
            }
        }
    }

}