package fr.banque;

import java.math.BigDecimal;

public interface ICompteASeuil {
	// En Java 8, on pourrait écrire le corps de cette méthode ici afin
	// de ne pas dupliquer son code de la classe Compte vers la classe
	// CompteASeuilRemunere
	public void retirer(BigDecimal pMontant) throws BanqueException;

	public BigDecimal getSeuil();

	public void setSeuil(BigDecimal pSeuil);
}
