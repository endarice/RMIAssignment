package exceptions;

public class InvalidAccount extends Exception {
	//Constructor that accepts a message
    public InvalidAccount(String message) {
       super(message);
    }
}
