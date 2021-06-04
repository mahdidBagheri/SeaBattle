package Config;

import java.io.FileReader;
import java.io.IOException;
import java.util.Properties;

public class MainConfig {
    private String mainConfigPath = "..\\SeaBattle\\Client\\src\\main\\resources\\Config\\MainConfig";

    private String frameConfigPath;
    private String colorConfigPath;
    private String netWorkConfigPath;
    private String fontConfigPath;

    public MainConfig() throws IOException {
        Properties properties = new Properties();
        FileReader fileReader = new FileReader(mainConfigPath);
        properties.load(fileReader);
        frameConfigPath = properties.getProperty("frameConfigPath");
        colorConfigPath = properties.getProperty("colorConfigPath");
        netWorkConfigPath = properties.getProperty("netWorkConfigPath");
        fontConfigPath = properties.getProperty("fontConfigPath");
    }

    public String getMainConfigPath() {
        return mainConfigPath;
    }

    public void setMainConfigPath(String mainConfigPath) {
        this.mainConfigPath = mainConfigPath;
    }

    public String getFrameConfigPath() {
        return frameConfigPath;
    }

    public void setFrameConfigPath(String frameConfigPath) {
        this.frameConfigPath = frameConfigPath;
    }

    public String getColorConfigPath() {
        return colorConfigPath;
    }

    public void setColorConfigPath(String colorConfigPath) {
        this.colorConfigPath = colorConfigPath;
    }

    public String getNetWorkConfigPath() {
        return netWorkConfigPath;
    }

    public void setNetWorkConfigPath(String netWorkConfigPath) {
        this.netWorkConfigPath = netWorkConfigPath;
    }

    public String getFontConfigPath() {
        return fontConfigPath;
    }

    public void setFontConfigPath(String fontConfigPath) {
        this.fontConfigPath = fontConfigPath;
    }
}
