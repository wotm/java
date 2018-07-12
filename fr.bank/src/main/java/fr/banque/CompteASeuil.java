package fr.banque;

public class CompteASeuil extends Compte implements ICompteASeuil {
	private double seuil;

	// CONSTRUCTORS
	public CompteASeuil() {
		this(0, 0, 0);
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
			this.setSolde(this.getSolde() - pVal);
		} else {
			throw new BanqueException("Vous avez atteint le seuil maximal de " + this.getSeuil() + " € !\n");
		}
	}
}
