package Game.Graphics;

import javax.swing.*;
import java.awt.*;

public class HBattleShip extends Sprite{
    private Image image ;

    public HBattleShip(int x, int y) {
        super(x, y);
        this.image = new ImageIcon("..\\SeaBattle\\Client\\src\\main\\resources\\pics\\HBattleShip.png").getImage();

    }

    @Override
    public void draw(Graphics2D g2D) {
        g2D.drawImage(this.image, getX(), getY(), null);
    }

}
