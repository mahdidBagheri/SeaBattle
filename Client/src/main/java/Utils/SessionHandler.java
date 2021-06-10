package Utils;

import Config.NetWorkConfig.NetworkConfig;

import java.io.FileReader;
import java.io.IOException;
import java.util.Properties;

public class SessionHandler {
    public static void saveSession(String session) throws IOException {
        NetworkConfig networkConfig = new NetworkConfig();
        Properties properties = new Properties();
        FileReader fileReader = new FileReader(networkConfig.getSessionAddress());
        properties.load(fileReader);
        properties.setProperty("session",session);

    }
    public static String loadSession() throws IOException {
        NetworkConfig networkConfig = new NetworkConfig();
        Properties properties = new Properties();
        FileReader fileReader = new FileReader(networkConfig.getSessionAddress());
        properties.load(fileReader);
        return properties.getProperty("session");
    }
}
