package Game.View;

import Config.ColorConfig.ColorConfig;
import Config.FrameConfig.FrameConfig;
import Game.Graphics.*;
import Game.Listener.BoardPanelListener;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.io.IOException;

public class BoardPanel extends JPanel implements MouseListener {

    private final Sprite background = new Background(0,0);
    private Sprite HBattleShip;
    private Sprite VBattleShip;
    private Sprite HCruiser1;
    private Sprite HCruiser2;
    private Sprite VCruiser1;
    private Sprite VCruiser2;

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
        try {
            boardPanelListener.listen(e.getX(), e.getY());
        }catch (NullPointerException nullPointerException){

        }
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
        if(HBattleShip != null){
            HBattleShip.draw(g2D);
        }
        if(VBattleShip != null){
            VBattleShip.draw(g2D);
        }
        if(VCruiser1 != null){
            VCruiser1.draw(g2D);
        }
        if(VCruiser2 != null){
            VCruiser2.draw(g2D);
        }
        if(HCruiser1 != null){
            HCruiser1.draw(g2D);
        }
        if(HCruiser2 != null){
            HCruiser2.draw(g2D);
        }
    }

    public void setBoardPanelListener(BoardPanelListener boardPanelListener) {
        this.boardPanelListener = boardPanelListener;
    }

    public void setHBattleShip(Sprite hBattleShip) {
        this.HBattleShip = hBattleShip;
        this.repaint();
    }

    public void setVBattleShip(Sprite vBattleShip) {
        this.VBattleShip = vBattleShip;
        this.repaint();
    }

    public void setHCruiser(HCruiser hCruiser) {
        if(HCruiser1 == null){
            this.HCruiser1 = hCruiser;
        }
        else {
            this.HCruiser2 = hCruiser;
        }
        this.repaint();
    }

    public void setVCruiser(VCruiser vCruiser) {
        if(VCruiser1 == null){
            this.VCruiser1 = vCruiser;
        }
        else {
            this.VCruiser2 = vCruiser;
        }        this.repaint();
    }
}
