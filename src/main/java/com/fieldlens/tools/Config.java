package com.fieldlens.tools;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

/**
 * Created with IntelliJ IDEA.
 * User: Stanislav Fedii
 * Date: 9/13/17
 * Time: 11:33 PM
 */
public class Config {
    private static Config instance;
    private Properties properties;

    private Config() {
        properties = new Properties();
        try {
            InputStream stream = getClass()
                    .getClassLoader()
                    .getResourceAsStream("config.properties");
            properties.load(stream);
            stream.close();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    /**
     * Used to retrieve an instance of the Configuration class.
     *
     * @return instance of {@link Config}
     */
    public static Config instance() {
        if (instance == null) {
            instance = new Config();
        }

        return instance;
    }

    public String getProperty(String key) {
        return properties.getProperty(key);
    }

    public String getAppUrl() {
        return getProperty("app-url");
    }

    public long getPollingPeriod() {
        System.out.println(String.format("polling period: %s", Long.parseLong(getProperty("polling_period"))));
        return Long.parseLong(getProperty("polling_period"));
    }

    public long getLocationTimeout() {
        System.out.println(String.format("location timeout: %s", Long.parseLong(getProperty("location_timeout"))));
        return Long.parseLong(getProperty("location_timeout"));
    }
}
