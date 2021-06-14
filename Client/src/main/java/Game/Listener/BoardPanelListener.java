package Game.Listener;

import Game.View.OpponentGamePanel;

import java.io.IOException;

public class BoardPanelListener {
    OpponentGamePanelListener opponentGamePanelListener;
    public BoardPanelListener(OpponentGamePanelListener opponentGamePanelListener) {
        this.opponentGamePanelListener = opponentGamePanelListener;
    }

    public void listen(int x, int y) throws IOException {
        opponentGamePanelListener.listen(x,y);
    }
}
