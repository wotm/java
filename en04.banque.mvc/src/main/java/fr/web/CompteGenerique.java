package fr.web;

public class CompteGenerique {
	// DTO - Data Transfert Object
	private int numero;
	private Double solde;
	private Double taux;
	private Double decouvert;
	private String libelle;

	// GETTERS
	public int getNumero() {
		return this.numero;
	}

	public Double getSolde() {
		return this.solde;
	}

	public Double getTaux() {
		return this.taux;
	}

	public Double getDecouvert() {
		return this.decouvert;
	}

	public String getLibelle() {
		return this.libelle;
	}

	// SETTERS
	public void setNumero(int numero) {
		this.numero = numero;
	}

	public void setSolde(Double solde) {
		this.solde = solde;
	}

	public void setTaux(Double taux) {
		this.taux = taux;
	}

	public void setDecouvert(Double decouvert) {
		this.decouvert = decouvert;
	}

	public void setLibelle(String libelle) {
		this.libelle = libelle;
	}

}
