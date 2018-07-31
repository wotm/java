package fr.banque;

import java.math.BigDecimal;

public class Compte {
	private int numero;
	private String libelle;
	private BigDecimal solde;

	// CONSTRUCTORS
	public Compte() {
		this(-1, "Inconnu", new BigDecimal(0D));
	}

	public Compte(int pNumero, String pLibelle, BigDecimal pSolde) {
		this.setNumero(pNumero);
		this.setLibelle(pLibelle);
		this.setSolde(pSolde);
	}

	// GETTERS
	public int getNumero() {
		return this.numero;
	}

	public BigDecimal getSolde() {
		return this.solde;
	}

	public String getLibelle() {
		return this.libelle;
	}

	// SETTERS
	private void setNumero(int pNumero) {
		this.numero = pNumero;
	}

	protected void setSolde(BigDecimal pSolde) {
		this.solde = pSolde;
	}

	public void setLibelle(String libelle) {
		this.libelle = libelle;
	}

	// METHODS
	@Override
	public String toString() {
		return "Libellé : " + this.getLibelle() + "\nN°compte : " + this.getNumero() + "\nSolde : " + this.getSolde()
				+ "€\n";
	}

	public void ajouter(BigDecimal pMontant) {
		this.setSolde(this.getSolde().add(pMontant));
	}

	public void retirer(BigDecimal pMontant) throws BanqueException {
		this.setSolde(this.getSolde().subtract(pMontant));
	}

}
