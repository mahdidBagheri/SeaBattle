package Game.Graphics;

import java.awt.*;

public abstract class Sprite {
    int x;
    int y;

    public Sprite(int x, int y) {
        this.x = x;
        this.y = y;
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    public abstract void draw(Graphics2D g2D);
}
