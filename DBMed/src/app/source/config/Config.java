package config;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

public class Config {

    private static final String PROPERTIES_FILE = System.getProperty("user.dir") + "/src/app/source/properties/prop";

    public static int PORT;

    static {
        Properties properties = new Properties();
        FileInputStream propFile = null;

        try{
            propFile = new FileInputStream(PROPERTIES_FILE);
            properties.load(propFile);

            PORT = Integer.parseInt(properties.getProperty("PORT"));
        }catch (IOException e){
            e.printStackTrace();
        }
    }

}
