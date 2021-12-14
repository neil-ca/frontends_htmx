package todo.app;

import io.micronaut.runtime.EmbeddedApplication;
import io.micronaut.test.extensions.junit5.annotation.MicronautTest;
import org.junit.jupiter.api.Test;

import jakarta.inject.Inject;

import static org.junit.jupiter.api.Assertions.*;

@MicronautTest
class TodoAppTest {

    @Inject
    EmbeddedApplication<?> application;

    @Test
    void testItWorks() {

        assertTrue(application.isRunning());
        assertEquals(5, 5);
    }
    @Test
    void testDatabase() {

    }
}
