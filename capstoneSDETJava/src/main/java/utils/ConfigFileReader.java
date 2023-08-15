package utils;


import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.Properties;

public class ConfigFileReader {

    private Properties properties;
    private final String propertyFilePath = System.getProperty("user.dir") + "\\src\\main\\resources\\configs\\configuration.properties";
    private static ConfigFileReader configFileReaderInstance = null;

    public static synchronized ConfigFileReader getInstance() {
        if (configFileReaderInstance == null)
            configFileReaderInstance = new ConfigFileReader();

        return configFileReaderInstance;
    }

    public ConfigFileReader() {
        BufferedReader reader;
        try {
            reader = new BufferedReader(new FileReader(propertyFilePath));
            properties = new Properties();
            try {
                properties.load(reader);
                Log.info("Properties File Loaded");
                reader.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
            throw new RuntimeException("Configuration.properties not found at " + propertyFilePath);
        }
    }

    public String getDriverPath() {
        String driverPath = properties.getProperty("driverPath");
        if (driverPath != null)
            return driverPath;
        else throw new RuntimeException("driverPath not specified in the Configuration.properties file.");
    }

    public long getImplicitlyWait() {
        String implicitlyWait = properties.getProperty("implicitlyWait");
        if (implicitlyWait != null)
            return Long.parseLong(implicitlyWait);
        else throw new RuntimeException("implicitlyWait not specified in the Configuration.properties file.");
    }

    public String getApplicationUrl() {
        String url = properties.getProperty("url");
        if (url != null)
            return url;
        else throw new RuntimeException("url not specified in the Configuration.properties file.");
    }
    public String getBrowserType() {
        String browserType = properties.getProperty("browserType");
        if (browserType != null)
            return browserType;
        else throw new RuntimeException("browserType not specified in the Configuration.properties file.");
    }
    public String getReportsPath() {
        String reportsPath = properties.getProperty("reportsPath");
        if (reportsPath != null)
            return reportsPath;
        else throw new RuntimeException("reportsPath not specified in the Configuration.properties file.");
    }
    public String getLog4jPath() {
        String log4jPath = properties.getProperty("log4jPath");
        if (log4jPath != null)
            return log4jPath;
        else throw new RuntimeException("log4jPath not specified in the Configuration.properties file.");
    }
}

