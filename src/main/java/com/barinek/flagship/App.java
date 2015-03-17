package com.barinek.flagship;

import com.google.common.collect.Lists;
import com.google.inject.Guice;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.util.List;

public class App {
    private static final Logger logger = LoggerFactory.getLogger(App.class);

    public App(Environment environment) throws Exception {
        Guice.createInjector(environment);
    }

    public static void main(String[] args) throws Exception {
        App app = new App(loadEnvironment());
        app.start();
    }

    private static Environment loadEnvironment() throws IOException {
        String env = System.getenv("application_environment");

        if (env == null) {
            throw new RuntimeException("Missing environment.");
        }

        logger.info("Found {} environment.", env);

        List<String> environments = Lists.newArrayList("development", "test", "production");
        if (!environments.contains(env)) {
            throw new RuntimeException("Unknown environmentArg.");
        }
        return new Environment(env);
    }

    public void start() throws Exception {
        logger.info("App started.");
    }

    public void stop() throws Exception {
        logger.info("App stopped.");
    }
}