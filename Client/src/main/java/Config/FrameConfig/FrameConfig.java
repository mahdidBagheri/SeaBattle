package Config.FrameConfig;

import Config.MainConfig;

import java.io.FileReader;
import java.io.IOException;
import java.util.Properties;

public class FrameConfig {
    String frameConfigPath;

    private int width;
    private int height;
    private int Xcoordinate;
    private int Ycoordinate;

    public FrameConfig() throws IOException {
        MainConfig mainConfig = new MainConfig();
        frameConfigPath = mainConfig.getFrameConfigPath();

        Properties properties = new Properties();
        FileReader fileReader = new FileReader(frameConfigPath);
        properties.load(fileReader);

        width = Integer.parseInt(properties.getProperty("width"));
        height = Integer.parseInt(properties.getProperty("height"));
        Xcoordinate = Integer.parseInt(properties.getProperty("Xcoordinate"));
        Ycoordinate = Integer.parseInt(properties.getProperty("Ycoordinate"));

    }

    public int getWidth() {
        return width;
    }

    public void setWidth(int width) {
        this.width = width;
    }

    public int getHeight() {
        return height;
    }

    public void setHeight(int height) {
        this.height = height;
    }

    public int getXcoordinate() {
        return Xcoordinate;
    }

    public void setXcoordinate(int xcoordinate) {
        Xcoordinate = xcoordinate;
    }

    public int getYcoordinate() {
        return Ycoordinate;
    }

    public void setYcoordinate(int ycoordinate) {
        Ycoordinate = ycoordinate;
    }
}
