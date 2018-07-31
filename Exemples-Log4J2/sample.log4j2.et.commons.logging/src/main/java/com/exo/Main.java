package com.exo;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

/**
 * Exemple.
 */
public final class Main {
	// Nous sommes ici en commons-logging
	private static final Log LOG = LogFactory.getLog(Main.class);

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
		if (Main.LOG.isDebugEnabled()) {
			Main.LOG.debug("Un message avec deux trous : " + 45 + " bonjour");
		}
		try {
			double v = 5 / 0;
			if (Main.LOG.isTraceEnabled()) {
				Main.LOG.trace("Resultat = " + v);
			}
		} catch (Exception e) {
			Main.LOG.error("Message qui gere la pile d'exception", e);
		}
		Main.LOG.debug("-- Fin -- ");
		System.exit(0);
	}
}