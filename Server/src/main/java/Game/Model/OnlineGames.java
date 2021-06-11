package Game.Model;

import Game.Controller.ServerGameController;

import java.util.LinkedList;

public class OnlineGames {
    LinkedList<ServerGameController> onlineGames = new LinkedList<>();

    public LinkedList<ServerGameController> getOnlineGames() {
        return onlineGames;
    }

    public void setOnlineGames(LinkedList<ServerGameController> onlineGames) {
        this.onlineGames = onlineGames;
    }

    public void addOnlineGame(ServerGameController serverGameController) {
        onlineGames.add(serverGameController);
    }
}
