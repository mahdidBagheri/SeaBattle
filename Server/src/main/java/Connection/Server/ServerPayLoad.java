package Connection.Server;

import java.io.Serializable;
import java.util.HashMap;

public class ServerPayLoad implements Serializable {
    HashMap<String,String> stringStringHashMap = new HashMap<>();

    public HashMap<String, String> getStringStringHashMap() {
        return stringStringHashMap;
    }

    public void setStringStringHashMap(HashMap<String, String> stringStringHashMap) {
        this.stringStringHashMap = stringStringHashMap;
    }
}
