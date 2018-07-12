package fr.banque;

public class Compte {
	private int numero;
	private double solde;

	// CONSTRUCTORS
	public Compte() {
		this(0, 0);
	}

	public Compte(int pNumero, double pSolde) {
		this.numero = pNumero;
		this.solde = pSolde;
	}

	// GETTERS
	public int getNumero() {
		return this.numero;
	}

	public double getSolde() {
		return this.solde;
	}

	// SETTERS
	private void setNumero(int pNumero) {
		this.numero = pNumero;
	}

	protected void setSolde(double pSolde) {
		this.solde = pSolde;
	}

	// METHODS
	@Override
	public String toString() {
		return "N°compte : " + this.getNumero() + "\n" + "Solde : " + this.getSolde() + "€\n";
	}

	public void ajouter(double pMontant) {
		this.setSolde(this.getSolde() + pMontant);
	}

	public void retirer(double pMontant) {
		this.setSolde(this.getSolde() - pMontant);
	}

}
