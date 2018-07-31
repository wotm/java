package com.banque.service.ex;

/**
 * Erreur d'authentification.
 */
public class AuthentificationException extends FonctionnelleException {
	private static final long serialVersionUID = 1L;

	/**
	 * Constructeur de l'objet.
	 */
	public AuthentificationException() {
		this("AuthentificationException", null);
	}

	/**
	 * Constructeur de l'objet.
	 *
	 * @param pMessage
	 */
	public AuthentificationException(String pMessage) {
		super(pMessage, null);
	}

	/**
	 * Constructeur de l'objet.
	 *
	 * @param pCause
	 */
	public AuthentificationException(Throwable pCause) {
		super("AuthentificationException", pCause);
	}

	/**
	 * Constructeur de l'objet.
	 *
	 * @param pMessage
	 * @param pCause
	 */
	public AuthentificationException(String pMessage, Throwable pCause) {
		super(pMessage, pCause);
	}
}