package Config.NetworkConfig;

import Config.ServerMainConfig;

import java.io.FileReader;
import java.io.IOException;
import java.util.Properties;

public class ServerNetworkConfig {
    private String networkConfigPath;
    private int port;

    public ServerNetworkConfig() throws IOException {
        ServerMainConfig mainConfig = new ServerMainConfig();
        networkConfigPath = mainConfig.getNetWorkConfigPath();

        Properties properties = new Properties();
        FileReader fileReader = new FileReader(networkConfigPath);
        properties.load(fileReader);

        port = Integer.parseInt(properties.getProperty("port"));

    }

    public int getPort() {
        return port;
    }
}
