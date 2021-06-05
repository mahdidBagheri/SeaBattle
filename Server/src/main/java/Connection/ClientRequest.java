package Connection;

import java.io.Serializable;

public class ClientRequest implements Serializable {
    private String source;
    private String payLoad;
    private String session;
    private String command;
    private String username;
    private String password;

    public ClientRequest(String source, String payLoad, String session, String command, String username, String password ) {
        this.source = source;
        this.payLoad = payLoad;
        this.session = session;
        this.command = command;
        this.username = username;
        this.password = password;
    }

    public String getSource() {
        return source;
    }

    public String getPayLoad() {
        return payLoad;
    }

    public String getSession() {
        return session;
    }

    public String getCommand() {
        return command;
    }

    public String getUsername() {
        return username;
    }

    public String getPassword() {
        return password;
    }
}
