package Game.Listener;

public class BoardPanelListener {
    UserGamePanelListener userGamePanelListener;
    public BoardPanelListener(UserGamePanelListener userGamePanelListener) {
        this.userGamePanelListener = userGamePanelListener;
    }

    public void listen(int x, int y) {
        userGamePanelListener.listen(x,y);
    }
}
