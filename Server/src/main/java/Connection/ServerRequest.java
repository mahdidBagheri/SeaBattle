package Connection;

public class ServerRequest {
    private String userName;
    private String command;

    public ServerRequest(String userName, String command) {
        this.userName = userName;
        this.command = command;
    }

    public String getUserName() {
        return userName;
    }

    public String getCommand() {
        return command;
    }
}
