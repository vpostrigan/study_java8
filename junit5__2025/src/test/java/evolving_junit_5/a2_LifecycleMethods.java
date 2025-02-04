package evolving_junit_5;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.nio.file.Path;
import java.nio.file.Paths;

import static org.junit.jupiter.api.Assertions.assertNotNull;

class a2_LifecycleMethods {
    Path resource;

    @BeforeEach
    void createResource() {
        resource = Paths.get("resource");
    }

    @Test
    void doSmthWithResource() {
        assertNotNull(resource);
    }

}
