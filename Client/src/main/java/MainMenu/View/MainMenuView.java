package MainMenu.View;

import Config.ColorConfig.ColorConfig;
import Config.FrameConfig.FrameConfig;
import Connection.Exceptions.CouldNotConnectToServerException;
import Interfaces.NextPanelListener;
import MainFrame.View.MainPanel;
import MainMenu.Events.NewGameEvent;
import MainMenu.Events.ViewGameEvent;
import MainMenu.Listener.NewGameListener;
import MainMenu.Listener.ViewGameListener;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;

public class MainMenuView extends JPanel implements ActionListener {
    NextPanelListener prevPanelListener;
    MainPanel mainPanel;

    JButton newGameBtn;
    JButton viewOtherGamesBtn;
    JButton viewAcountBtn;
    JButton viewScoresBtn;
    JButton logoutBtn;

    NewGameListener newGameListener;
    ViewGameListener viewGameListener;

    public MainMenuView(MainPanel mainPanel) throws IOException {
        this.mainPanel = mainPanel;
        ColorConfig colorConfig = new ColorConfig();
        FrameConfig frameConfig = new FrameConfig();

        this.setBackground(colorConfig.getColor02());
        this.setLayout(null);
        this.setBounds(0,0,(int)(frameConfig.getWidth()),(int)frameConfig.getHeight());

        newGameBtn = new JButton("newGame");
        newGameBtn.setText("newGame");
        newGameBtn.setFocusable(false);
        newGameBtn.setBounds(350,70,250,50);
        newGameBtn.addActionListener(this);

        viewOtherGamesBtn = new JButton("view Online games");
        viewOtherGamesBtn.setText("view Online games");
        viewOtherGamesBtn.setFocusable(false);
        viewOtherGamesBtn.setBounds(350,150,250,50);
        viewOtherGamesBtn.addActionListener(this);

        viewAcountBtn = new JButton("view Acounts");
        viewAcountBtn.setText("view Acounts");
        viewAcountBtn.setFocusable(false);
        viewAcountBtn.setBounds(350,220,250,50);
        viewAcountBtn.addActionListener(this);

        viewScoresBtn = new JButton("view scores");
        viewScoresBtn.setText("view scores");
        viewScoresBtn.setFocusable(false);
        viewScoresBtn.setBounds(350,220,250,50);
        viewScoresBtn.addActionListener(this);

        logoutBtn = new JButton("logout");
        logoutBtn.setText("logout");
        logoutBtn.setFocusable(false);
        logoutBtn.setBounds(350,300,250,50);
        logoutBtn.addActionListener(this);

        newGameListener = new NewGameListener(mainPanel);
        viewGameListener = new ViewGameListener(mainPanel);

        this.add(newGameBtn);
        this.add(viewOtherGamesBtn);
        this.add(viewAcountBtn);
        this.add(viewScoresBtn);
        this.add(logoutBtn);
    }

    public NextPanelListener getPrevPanelListener() {
        return prevPanelListener;
    }

    public void setPrevPanelListener(NextPanelListener prevPanelListener) {
        this.prevPanelListener = prevPanelListener;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if(e.getSource() == newGameBtn){
            NewGameEvent newGameEvent = new NewGameEvent();
            try {
                newGameListener.listen(newGameEvent);
            } catch (IOException ioException) {
                ioException.printStackTrace();
            }
        }
        else if(e.getSource() == viewOtherGamesBtn){
            ViewGameEvent viewGameEvent = new ViewGameEvent();
            try {
                viewGameListener.listen(viewGameEvent);
            } catch (IOException ioException) {
                ioException.printStackTrace();
            } catch (CouldNotConnectToServerException couldNotConnectToServerException) {
                couldNotConnectToServerException.printStackTrace();
            } catch (ClassNotFoundException classNotFoundException) {
                classNotFoundException.printStackTrace();
            }
        }
        else if(e.getSource() == viewAcountBtn){

        }
        else if(e.getSource() == viewScoresBtn){

        }
        else if(e.getSource() == logoutBtn){

        }
    }
}
