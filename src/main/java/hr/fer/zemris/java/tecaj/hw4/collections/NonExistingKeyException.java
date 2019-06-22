package hr.fer.zemris.java.tecaj.hw4.collections;

public class NonExistingKeyException extends RuntimeException {

	/**
	 * Serial ID.
	 */
	private static final long serialVersionUID = -4098759272949522335L;

	/**
	 * Default constructor.
	 * 
	 * @param messege
	 *            that tells the source of error.
	 */
	public NonExistingKeyException(String messege) {
		super(messege);
	}

}
