package fr.banque;

public interface ICompteASeuil {
	// En Java 8, on pourrait écrire le corps de cette méthode ici afin
	// de ne pas dupliquer son code de la classe Compte vers la classe
	// CompteASeuilRemunere
	public void retirer(double pMontant) throws BanqueException;

	public double getSeuil();

	public void setSeuil(double pSeuil);
}
