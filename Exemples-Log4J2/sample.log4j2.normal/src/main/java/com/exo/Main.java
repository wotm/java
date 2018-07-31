package com.exo;

import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

/**
 * Exemple.
 */
public final class Main {
	// Nous sommes ici en log4j2 pur
	private static final Logger LOG = LogManager.getLogger();

	/**
	 * Constructeur.
	 */
	private Main() {
		super();
		Main.LOG.error("Ne pas utiliser le constructeur");
	}

	/**
	 * Charge un fichier Spring.
	 *
	 * @param args
	 *            ne sert a rien
	 */
	public static void main(String[] args) {
		// Le fichier de configuration est dans src/main/resources
		Main.LOG.debug("-- Debut -- ");
		Main.LOG.debug("Un message avec deux trous : {} {}", 45, "bonjour");
		Main.LOG.printf(Level.INFO, "Un message avec des %s, on peut même dire %d %s", "trous", 1, "trou");
		try {
			double v = 5 / 0;
			Main.LOG.trace("Resultat = %f", v);
		} catch (Exception e) {
			Main.LOG.error("Message qui gere la pile d'exception", e);
		}
		Main.LOG.debug("-- Fin -- ");
		System.exit(0);
	}
}