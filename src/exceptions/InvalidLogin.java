package exceptions;

public class InvalidLogin extends Exception{
	//Constructor that accepts a message
    public InvalidLogin(String message) {
       super(message);
    }
}
