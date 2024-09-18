package utility;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Properties;

public class ConfigReader {

    public Properties init_prop(){
        Properties properties = new Properties();
        try {
            FileInputStream file  = new FileInputStream("src/test/java/resources/configuration.properties");
            properties.load(file);
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        return properties;
    }

}