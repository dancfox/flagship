package com.barinek.flagship;

import com.sun.jersey.guice.JerseyServletModule;
import com.sun.jersey.guice.spi.container.servlet.GuiceContainer;

public class ResourceModule extends JerseyServletModule {
    @Override
    protected void configureServlets() {
        bind(NoopController.class);
        serve("/*").with(GuiceContainer.class);
    }
}
