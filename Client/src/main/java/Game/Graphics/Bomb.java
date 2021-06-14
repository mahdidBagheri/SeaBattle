package Game.Graphics;

import javax.swing.*;
import java.awt.*;

public class Bomb extends Sprite{
    private Image image ;

    public Bomb(int x, int y) {
        super(x, y);
        this.image = new ImageIcon("..\\SeaBattle\\Client\\src\\main\\resources\\pics\\bomb.png").getImage();
    }

    @Override
    public void draw(Graphics2D g2D) {
        g2D.drawImage(this.image, getX(), getY(), null);
    }

}
