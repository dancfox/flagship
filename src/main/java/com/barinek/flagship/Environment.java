package com.barinek.flagship;

import com.google.inject.AbstractModule;
import com.google.inject.Key;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import static com.google.inject.name.Names.named;

public class Environment extends AbstractModule {
    private static final Logger logger = LoggerFactory.getLogger(Environment.class);
    private static final Pattern envPattern = Pattern.compile("\\$\\{(\\w+)\\}");
    protected Properties properties;

    public Environment(String environment) throws IOException {
        properties = getProperties("/" + environment + ".properties");
    }

    @Override
    protected void configure() {
        bind(Key.get(String.class, named("mybatis.environment.id"))).toInstance(properties.getProperty("mybatis.environment.id"));
        bind(Key.get(String.class, named("JDBC.url"))).toInstance(properties.getProperty("jdbc.url"));
    }

    private Properties getProperties(String file) throws IOException {
        logger.info("Loading properties for {}", file);
        Properties properties = new Properties();
        InputStream resourceAsStream = getClass().getResourceAsStream(file);
        if (resourceAsStream != null) {
            properties.load(resourceAsStream);
        }
        for (Object key : properties.keySet()) {
            String property = properties.getProperty(key.toString());
            properties.setProperty(key.toString(), findEnvironmentVariables(property));
        }
        logger.info(properties.toString());
        return properties;
    }

    private String findEnvironmentVariables(String value) {
        Matcher matcher = envPattern.matcher(value);
        while (matcher.find()) {
            return System.getenv(matcher.group(1));
        }
        return value;
    }
}
