package Utils;

import Config.NetWorkConfig.NetworkConfig;

import java.io.FileReader;
import java.io.IOException;
import java.util.HashMap;
import java.util.Properties;

public class UserInfoHandler {
    public static void saveInfo(String username,String password ,String session) throws IOException {
        NetworkConfig networkConfig = new NetworkConfig();
        Properties properties = new Properties();
        FileReader fileReader = new FileReader(networkConfig.getSessionAddress());
        properties.load(fileReader);
        properties.setProperty("session",session);
        properties.setProperty("username",username);
        properties.setProperty("password",password);

    }
    public static HashMap<String, String> loadInfo() throws IOException {
        HashMap<String, String> info = new HashMap<>();

        NetworkConfig networkConfig = new NetworkConfig();
        Properties properties = new Properties();
        FileReader fileReader = new FileReader(networkConfig.getSessionAddress());
        properties.load(fileReader);
        info.put("username", properties.getProperty("username"));
        info.put("password", properties.getProperty("password"));
        info.put("session", properties.getProperty("session"));
        return info;
    }
}
