package Game.Graphics;

import javax.swing.*;
import java.awt.*;

public class VBattleShip extends Sprite {
    private Image image ;

    public VBattleShip(int x, int y) {
        super(x, y);
        this.image = new ImageIcon("..\\SeaBattle\\Client\\src\\main\\resources\\pics\\VBattleShip.png").getImage();

    }

    @Override
    public void draw(Graphics2D g2D) {
        g2D.drawImage(this.image, getX(), getY(), null);
    }
}
