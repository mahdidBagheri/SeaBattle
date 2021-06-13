package Game.Listener;

import Game.View.OpponentGamePanel;

public class BoardPanelListener {
    OpponentGamePanelListener opponentGamePanelListener;
    public BoardPanelListener(OpponentGamePanelListener opponentGamePanelListener) {
        this.opponentGamePanelListener = opponentGamePanelListener;
    }

    public void listen(int x, int y) {
        opponentGamePanelListener.listen(x,y);
    }
}
