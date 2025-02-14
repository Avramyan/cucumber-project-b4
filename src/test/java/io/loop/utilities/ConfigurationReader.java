package io.loop.utilities;

import java.io.FileInputStream;
import java.util.Properties;

public class ConfigurationReader {

    private static Properties properties;

    static {
        try{
            String path = "configuration.properties";
            FileInputStream input = new FileInputStream(path);
            properties = new Properties();
            properties.load(input);
        } catch (Exception e){
            e.printStackTrace();
        }
    }

    public static String getProperties(String keyName){
        return properties.getProperty(keyName);
    }

}