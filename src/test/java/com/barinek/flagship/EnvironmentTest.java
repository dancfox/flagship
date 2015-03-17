package com.barinek.flagship;

import com.google.inject.Guice;
import com.google.inject.Inject;
import com.google.inject.Injector;
import com.google.inject.Key;
import com.google.inject.name.Named;
import org.junit.Test;

import java.io.IOException;

import static com.google.inject.name.Names.named;
import static junit.framework.Assert.assertEquals;

public class EnvironmentTest {
    @Test
    public void readsProperties() throws IOException {
        Injector injector = Guice.createInjector(new TestEnvironment("test"));
        A a = injector.getInstance(A.class);
        assertEquals("42", a.getValue());
        assertEquals("/bin/bash", a.getShell());
    }

    class TestEnvironment extends Environment {
        public TestEnvironment(String environment) throws IOException {
            super(environment);
        }

        @Override
        protected void configure() {
            super.configure();
            bind(Key.get(String.class, named("a.value"))).toInstance(properties.getProperty("a.value"));
            bind(Key.get(String.class, named("shell"))).toInstance(properties.getProperty("shell"));
        }
    }

    static class A {
        private String value;
        private String terminal;

        @Inject
        A(@Named("a.value") String value, @Named("shell") String terminal) {
            this.value = value;
            this.terminal = terminal;
        }

        String getValue() {
            return value;
        }

        String getShell() {
            return terminal;
        }
    }
}