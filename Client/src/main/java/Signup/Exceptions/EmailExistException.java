package Signup.Exceptions;

public class EmailExistException extends Exception{
    public EmailExistException(String msg){
        super(msg);
    }
}
