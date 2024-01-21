package spotifyOAuth2.utils;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Properties;

public class PropertyUtils {
    public static Properties propertyLoader(String filePath) throws IOException {
        FileInputStream fileInputStream = new FileInputStream(filePath);
        Properties properties =new Properties();
        properties.load(fileInputStream);

        return properties;
    }
}
