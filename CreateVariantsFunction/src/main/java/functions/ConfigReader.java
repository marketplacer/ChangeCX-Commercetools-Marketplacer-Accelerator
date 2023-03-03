package functions;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public class ConfigReader {
    private static final String PROPERTIES_FILE = "config.properties";

    private Properties properties;

    public ConfigReader() {
        properties = new Properties();
        InputStream inputStream = getClass().getClassLoader().getResourceAsStream(PROPERTIES_FILE);
        try {
            if (inputStream != null) {
                properties.load(inputStream);
            }
        }catch(IOException e){
            e.printStackTrace();
        }
    }

    public String getClientId() {
        return properties.getProperty("clientId");
    }

    public String getSecret() {
        return properties.getProperty("secret");
    }

    public String getProjectId() {
        return properties.getProperty("projectId");
    }

    public String getMainProductType() {
        return properties.getProperty("mainProductType");
    }

    public String getRootCategory() {
        return properties.getProperty("rootCategory");
    }

    public String getChildCategory() {
        return properties.getProperty("childCategory");
    }
}
