package fr.banque;

public class BanqueException extends Exception {

	/**
	 *
	 */
	private static final long serialVersionUID = 1L;

	public BanqueException() {
		super();
	}

	public BanqueException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
		super(message, cause, enableSuppression, writableStackTrace);
	}

	public BanqueException(String message, Throwable cause) {
		super(message, cause);
	}

	public BanqueException(String message) {
		super(message);
	}

	public BanqueException(Throwable cause) {
		super(cause);
	}

}
