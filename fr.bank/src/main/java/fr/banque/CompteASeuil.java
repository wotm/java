package fr.banque;

public class CompteASeuil extends Compte implements ICompteASeuil {
	private double seuil;

	// CONSTRUCTORS
	public CompteASeuil() {
		this(-1, 0D, 0D);
	}

	public CompteASeuil(int pNumero, double pSolde, double pSeuil) {
		super(pNumero, pSolde);
		this.seuil = pSeuil;
	}

	// GETTERS
	@Override
	public double getSeuil() {
		return this.seuil;
	}

	// SETTERS
	@Override
	public void setSeuil(double pSeuil) {
		this.seuil = pSeuil;
	}

	// METHODS
	@Override
	public String toString() {
		return super.toString() + "Seuil : " + this.getSeuil() + "€\n";
	}

	@Override
	public void retirer(double pVal) throws BanqueException {
		if (this.getSolde() - pVal > this.getSeuil()) {
			super.retirer(pVal);
		} else {
			throw new BanqueException("Vous avez atteint le seuil maximal de " + this.getSeuil() + " € !\n");
		}
	}
}
