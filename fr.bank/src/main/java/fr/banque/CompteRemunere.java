package fr.banque;

public class CompteRemunere extends Compte implements ICompteRemunere {
	private double taux;

	// CONSTRUCTORS
	public CompteRemunere() {
		this(0, 0, 0);
	}

	public CompteRemunere(int pNumero, double pSolde, double pTaux) {
		super(pNumero, pSolde);
		this.taux = pTaux;
	}

	// GETTERS
	public double getTaux() {
		return this.taux;
	}

	// SETTERS
	public double setTaux(double pTaux) {
		this.taux = pTaux;

		return this.getTaux();
	}

	// METHODS
	@Override
	public String toString() {
		return super.toString() + "Taux : " + this.getTaux() * 100 + "%\nIntérêts : " + this.calculerInterets() + "€\n";
	}

	public double calculerInterets() {
		return this.taux * this.getSolde();
	}

	public double verserInterets() {
		return this.getSolde() + this.calculerInterets();
	}
}
