package Config.NetWorkConfig;

import Config.MainConfig;

import java.io.FileReader;
import java.io.IOException;
import java.util.Properties;

public class NetworkConfig {
    private String networkConfigPath;

    private String serverIP;
    private int port;
    private String sessionAddress;
    private String session;

    public NetworkConfig() throws IOException {
        MainConfig mainConfig = new MainConfig();
        networkConfigPath = mainConfig.getNetWorkConfigPath();

        Properties properties = new Properties();
        FileReader fileReader = new FileReader(networkConfigPath);
        properties.load(fileReader);

        serverIP = properties.getProperty("serverIP");
        port = Integer.parseInt(properties.getProperty("port"));
        sessionAddress = properties.getProperty("sessionAddress");

        Properties properties1 = new Properties();
        FileReader fileReader1 = new FileReader(sessionAddress);
        properties1.load(fileReader1);
        session = properties1.getProperty("session");
        int a = 0;
    }

    public String getSessionAddress() {
        return sessionAddress;
    }

    public void setSessionAddress(String sessionAddress) {
        this.sessionAddress = sessionAddress;
    }

    public String getNetworkConfigPath() {
        return networkConfigPath;
    }

    public void setNetworkConfigPath(String networkConfigPath) {
        this.networkConfigPath = networkConfigPath;
    }

    public String getServerIP() {
        return serverIP;
    }

    public void setServerIP(String serverIP) {
        this.serverIP = serverIP;
    }

    public int getPort() {
        return port;
    }

    public void setPort(int port) {
        this.port = port;
    }

    public String getSession() {
        return session;
    }

    public void setSession(String session) {
        this.session = session;
    }
}
