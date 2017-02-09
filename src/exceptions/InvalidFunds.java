package exceptions;

public class InvalidFunds extends Exception {
	//Constructor that accepts a message
    public InvalidFunds(String message) {
       super(message);
    }
}
