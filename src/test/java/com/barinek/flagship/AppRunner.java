package com.barinek.flagship;

import org.junit.AfterClass;
import org.junit.BeforeClass;

public class AppRunner {
    protected static App app;

    @BeforeClass
    public static void setUp() throws Exception {
        app = new App();
        app.start();
    }

    @AfterClass
    public static void tearDown() throws Exception {
        app.stop();
    }
}
