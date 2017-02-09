package exceptions;

public class InvalidSession extends Exception {
	//Constructor that accepts a message
    public InvalidSession(String message) {
       super(message);
    }
}
