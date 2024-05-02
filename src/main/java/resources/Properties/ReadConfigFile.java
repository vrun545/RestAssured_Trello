package resources.Properties;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

public class ReadConfigFile {

    private static final String path = "src/main/java/resources/Properties/Config.properties";

    private Properties properties;

    public ReadConfigFile() {
        properties = new Properties();
        loadProperties();
    }

    private void loadProperties() {
        try {
            FileInputStream fileInputStream = new FileInputStream(path);
            properties.load(fileInputStream);
            fileInputStream.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public String getAPI_KEY() {
		String API_KEY = properties.getProperty("API_Key");
		if (API_KEY != null)
			return API_KEY;
		else
			throw new RuntimeException("API_KEY not found in Config File");
	}
    
    public String getToken() {
		String Token = properties.getProperty("Token");
		if (Token != null)
			return Token;
		else
			throw new RuntimeException("Token not found in Config File");
	}
    
}
