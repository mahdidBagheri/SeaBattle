package Signup.Exceptions;

public class UsernameExistsException extends Exception{
    public UsernameExistsException(String msg){
        super(msg);
    }
}
