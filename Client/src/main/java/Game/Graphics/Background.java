package Game.Graphics;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

public class Background extends Sprite  {
    private final Image image ;
    private int x;
    private int y;

    public Background(int x, int y){
        super(x, y);
        this.image = new ImageIcon("..\\SeaBattle\\Client\\src\\main\\resources\\pics\\background.png").getImage();
    }

    @Override
    public void draw(Graphics2D g2D){
        g2D.drawImage(this.image, getX(), getY(), null);
    }

    public Image getImage() {
        return image;
    }

    @Override
    public int getX() {
        return x;
    }

    public void setX(int x) {
        this.x = x;
    }

    @Override
    public int getY() {
        return y;
    }

    public void setY(int y) {
        this.y = y;
    }

}
