package Signup.Events;

public class SignupEvent {
    private String userName;
    private String password1;
    private String password2;
    private String email;

    public SignupEvent(String userName, String password1, String password2, String email) {
        this.userName = userName;
        this.password1 = password1;
        this.password2 = password2;
        this.email = email;
    }

    public String getUserName() {
        return userName;
    }

    public String getPassword1() {
        return password1;
    }

    public String getPassword2() {
        return password2;
    }

    public String getEmail() {
        return email;
    }
}
