package Config;

import java.io.FileReader;
import java.io.IOException;
import java.util.Properties;

public class ServerMainConfig {
    private String mainConfigPath = "..\\SeaBattle\\Server\\src\\main\\resources\\Config\\MainConfig";

    private String netWorkConfigPath;

    public ServerMainConfig() throws IOException {
        Properties properties = new Properties();
        FileReader fileReader = new FileReader(mainConfigPath);
        properties.load(fileReader);
        netWorkConfigPath = properties.getProperty("NetworkConfigPath");
    }

    public String getNetWorkConfigPath() {
        return netWorkConfigPath;
    }
}
