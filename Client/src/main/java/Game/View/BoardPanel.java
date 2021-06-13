package Game.View;

import Config.ColorConfig.ColorConfig;
import Config.FrameConfig.FrameConfig;
import Game.Graphics.Background;
import Game.Graphics.Sprite;
import Game.Listener.BoardPanelListener;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.io.IOException;

public class BoardPanel extends JPanel implements MouseListener {

    private final Sprite background = new Background(0,0);

    BoardPanelListener boardPanelListener;

    public BoardPanel() throws IOException {

        setDoubleBuffered(true);
        setFocusable(true);
        requestFocus();

        ColorConfig colorConfig = new ColorConfig();
        FrameConfig frameConfig = new FrameConfig();

        this.setBackground(colorConfig.getColor04());
        this.setLayout(null);
        this.setBounds(100,100,350,350);
        addMouseListener(this);

        repaint();
    }

    @Override
    public void paintComponent(Graphics g){
        super.paintComponent(g);
        Graphics2D g2D = (Graphics2D) g;
        onDraw(g2D);
    }


    @Override
    public void mouseClicked(MouseEvent e) {
    }

    @Override
    public void mousePressed(MouseEvent e) {
        boardPanelListener.listen(e.getX(), e.getY());
    }

    @Override
    public void mouseReleased(MouseEvent e) {
    }

    @Override
    public void mouseEntered(MouseEvent e) {
    }

    @Override
    public void mouseExited(MouseEvent e) {

    }

    protected void onDraw(Graphics2D g2D){
        background.draw(g2D);
    }

    public void setBoardPanelListener(BoardPanelListener boardPanelListener) {
        this.boardPanelListener = boardPanelListener;
    }
}
