package com.barinek.flagship;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class App {
    private static final Logger logger = LoggerFactory.getLogger(App.class);

    public App() throws Exception {
    }

    public static void main(String[] args) throws Exception {
        App app = new App();
        app.start();
    }

    public void start() throws Exception {
        logger.info("App started.");
    }

    public void stop() throws Exception {
        logger.info("App stopped.");
    }
}