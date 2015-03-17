package com.barinek.flagship;

import com.google.common.collect.Lists;
import com.google.inject.Guice;
import com.google.inject.Injector;
import com.google.inject.servlet.GuiceFilter;
import org.eclipse.jetty.server.Server;
import org.eclipse.jetty.servlet.ServletContextHandler;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.servlet.DispatcherType;
import java.io.IOException;
import java.util.EnumSet;
import java.util.List;

public class App {
    private static final Logger logger = LoggerFactory.getLogger(App.class);
    private final Server server;

    public App(Environment environment) throws Exception {
        Injector injector = Guice.createInjector(environment, new ResourceModule());

        server = new Server(8080);
        ServletContextHandler context = new ServletContextHandler(server, "/", ServletContextHandler.NO_SESSIONS);
        context.addEventListener(new Listener(injector));
        context.addFilter(GuiceFilter.class, "/*", EnumSet.of(DispatcherType.REQUEST));

        Runtime.getRuntime().addShutdownHook(new Thread() {
            public void run() {
                try {
                    if (server.isRunning()) {
                        server.stop();
                    }
                    logger.info("App shutdown.");
                } catch (Exception e) {
                    logger.info("Error shutting down app.", e);
                }
            }
        });
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
        server.start();
    }

    public void stop() throws Exception {
        logger.info("App stopped.");
        server.stop();
    }
}