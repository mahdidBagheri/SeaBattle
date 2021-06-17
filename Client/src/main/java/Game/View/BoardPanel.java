package Game.View;

import Config.ColorConfig.ColorConfig;
import Config.FrameConfig.FrameConfig;
import Game.Graphics.*;
import Game.Listener.BoardPanelListener;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.io.IOException;
import java.util.LinkedList;

public class BoardPanel extends JPanel {

    private final Sprite background = new Background(0,0);
    private Sprite HBattleShip;
    private Sprite VBattleShip;
    private Sprite HCruiser1;
    private Sprite HCruiser2;
    private Sprite VCruiser1;
    private Sprite VCruiser2;
    private Sprite HDestroyer1;
    private Sprite HDestroyer2;
    private Sprite HDestroyer3;
    private Sprite VDestroyer1;
    private Sprite VDestroyer2;
    private Sprite VDestroyer3;
    private Sprite Frigate1;
    private Sprite Frigate2;
    private Sprite Frigate3;
    private Sprite Frigate4;
    LinkedList<Bomb> bombs = new LinkedList<>();
    LinkedList<Cross> crosses = new LinkedList<>();

    BoardPanelListener boardPanelListener;

    boolean mouseListenerSwitch = true;


    public BoardPanel(int x, int y) throws IOException {

        setDoubleBuffered(true);
        setFocusable(true);
        requestFocus();

        ColorConfig colorConfig = new ColorConfig();
        FrameConfig frameConfig = new FrameConfig();

        this.setBackground(colorConfig.getColor04());
        this.setLayout(null);
        this.setBounds(x,y,350,350);

        repaint();


        this.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                super.mouseClicked(e);
                if(mouseListenerSwitch){
                    try {
                        boardPanelListener.listen(e.getX(), e.getY());

                    }catch (NullPointerException | IOException nullPointerException){

                    }
                }
            }
        });
    }

    @Override
    public void paintComponent(Graphics g){
        super.paintComponent(g);
        Graphics2D g2D = (Graphics2D) g;
        onDraw(g2D);
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
        if(HDestroyer1 != null){
            HDestroyer1.draw(g2D);
        }
        if(HDestroyer2 != null){
            HDestroyer2.draw(g2D);
        }
        if(HDestroyer3 != null){
            HDestroyer3.draw(g2D);
        }
        if(VDestroyer1 != null){
            VDestroyer1.draw(g2D);
        }
        if(VDestroyer2 != null){
            VDestroyer2.draw(g2D);
        }
        if(VDestroyer3 != null){
            VDestroyer3.draw(g2D);
        }
        if(Frigate1 != null){
            Frigate1.draw(g2D);
        }
        if(Frigate2 != null){
            Frigate2.draw(g2D);
        }
        if(Frigate3 != null){
            Frigate3.draw(g2D);
        }
        if(Frigate4 != null){
            Frigate4.draw(g2D);
        }
        for(Bomb bomb:bombs){
            bomb.draw(g2D);
        }
        for(Cross cross:crosses){
            cross.draw(g2D);
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

    public void setHDestroyer(HDestroyer hDestroyer) {
        if(HDestroyer1 == null){
            this.HDestroyer1 = hDestroyer;
        }
        else if(HDestroyer2 == null) {
            this.HDestroyer2 = hDestroyer;
        }
        else if(HDestroyer3 == null) {
            this.HDestroyer3 = hDestroyer;
        }
        this.repaint();
    }

    public void setVDestroyer(VDestroyer vDestroyer) {
        if(VDestroyer1 == null){
            this.VDestroyer1 = vDestroyer;
        }
        else if(VDestroyer2 == null) {
            this.VDestroyer2 = vDestroyer;
        }
        else if(VDestroyer3 == null) {
            this.VDestroyer3 = vDestroyer;
        }
        this.repaint();
    }

    public void setFrigate(Frigate frigate) {
        if(Frigate1 == null){
            this.Frigate1 = frigate;
        }
        else if(Frigate2 == null) {
            this.Frigate2 = frigate;
        }
        else if(Frigate3 == null) {
            this.Frigate3 = frigate;
        }
        else if(Frigate4 == null) {
            this.Frigate4 = frigate;
        }
        this.repaint();
    }

    public void clear() {
        HBattleShip = null;
        VBattleShip = null;
        HCruiser1 = null;
        HCruiser2 = null;
        VCruiser1 = null;
        VCruiser2 = null;
        HDestroyer1 = null;
        HDestroyer2 = null;
        HDestroyer3 = null;
        VDestroyer1 = null;
        VDestroyer2 = null;
        VDestroyer3 = null;
        Frigate1 = null;
        Frigate2 = null;
        Frigate3 = null;
        Frigate4 = null;
        bombs = new LinkedList<>();
        crosses = new LinkedList<>();
    }

    public void addBomb(Bomb bomb){
        bombs.add(bomb);
        repaint();
    }

    public void addCrosses(Cross cross){
        crosses.add(cross);
        repaint();
    }

    public void removeMouseListener(){
        mouseListenerSwitch = false;
    }

    public void addMouseListener(){
        mouseListenerSwitch = true;
    }
}
