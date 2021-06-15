package Game.Graphics;

import javax.swing.*;
import java.awt.*;

public class Cross extends Sprite {
    private Image image ;

    public Cross(int x, int y) {
        super(x, y);
        this.image = new ImageIcon("..\\SeaBattle\\Client\\src\\main\\resources\\pics\\cross1.png").getImage();
    }

    @Override
    public void draw(Graphics2D g2D) {
        g2D.drawImage(this.image, getX(), getY(), null);
    }

}
