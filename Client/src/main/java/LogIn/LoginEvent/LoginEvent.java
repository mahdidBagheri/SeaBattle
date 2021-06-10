package LogIn.LoginEvent;

public class LoginEvent {
    private String userName;
    private String password;

    public LoginEvent(String userName, String password) {
        this.userName = userName;
        this.password = password;
    }

    public String getUserName() {
        return userName;
    }

    public String getPassword() {
        return password;
    }
}
