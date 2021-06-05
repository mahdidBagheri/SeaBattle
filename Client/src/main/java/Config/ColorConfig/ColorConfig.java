package Config.ColorConfig;

import Config.MainConfig;

import java.awt.*;
import java.io.FileReader;
import java.io.IOException;
import java.util.Properties;

public class ColorConfig {
    private String colorConfigPath;

    private Color color01;
    private Color color02;
    private Color color03;
    private Color color04;

    public ColorConfig() throws IOException {
        MainConfig mainConfig = new MainConfig();

        colorConfigPath = mainConfig.getColorConfigPath();

        Properties properties = new Properties();
        FileReader fileReader = new FileReader(colorConfigPath);
        properties.load(fileReader);

        color01 = Color.decode(properties.getProperty("color01"));
        color02 = Color.decode(properties.getProperty("color02"));
        color03 = Color.decode(properties.getProperty("color03"));
        color04 = Color.decode(properties.getProperty("color04"));
    }

    public Color getColor01() {
        return color01;
    }

    public Color getColor02() {
        return color02;
    }

    public Color getColor03() {
        return color03;
    }

    public Color getColor04() {
        return color04;
    }
}
