package fr.banque;

public class CompteRemunere extends Compte implements ICompteRemunere {
	private double taux;

	// CONSTRUCTORS
	public CompteRemunere() {
		this(-1, 0D, 0D);
	}

	public CompteRemunere(int pNumero, double pSolde, double pTaux) {
		super(pNumero, pSolde);
		this.taux = pTaux;
	}

	// GETTERS
	@Override
	public double getTaux() {
		return this.taux;
	}

	// SETTERS
	@Override
	public double setTaux(double pTaux) {
		if (pTaux > 0 && pTaux <= 1) {
			this.taux = pTaux;
		} else {
			this.taux = 0D;
		}

		return this.getTaux();
	}

	// METHODS
	@Override
	public String toString() {
		return super.toString() + "Taux : " + this.getTaux() * 100 + "%\nIntérêts : " + this.calculerInterets() + "€\n";
	}

	@Override
	public double calculerInterets() {
		return this.getTaux() * this.getSolde();
	}

	@Override
	public double verserInterets() {
		return this.getSolde() + this.calculerInterets();
	}
}
