package Connection.Server;

import Game.Model.GameData;

import java.io.Serializable;
import java.util.HashMap;

public class ServerPayLoad implements Serializable {
    HashMap<String,String> stringStringHashMap = new HashMap<>();
    GameData gameData;
    public HashMap<String, String> getStringStringHashMap() {
        return stringStringHashMap;
    }

    public void setStringStringHashMap(HashMap<String, String> stringStringHashMap) {
        this.stringStringHashMap = stringStringHashMap;
    }

    public void setGameData(GameData gameData) {
        this.gameData = gameData;
    }

    public GameData getGameData() {
        return gameData;
    }
}
