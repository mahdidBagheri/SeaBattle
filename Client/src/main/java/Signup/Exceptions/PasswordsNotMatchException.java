package Signup.Exceptions;

public class PasswordsNotMatchException extends Exception {
    public PasswordsNotMatchException(String msg){
        super(msg);
    }
}
