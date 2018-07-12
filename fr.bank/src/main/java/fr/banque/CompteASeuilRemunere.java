package fr.banque;

public class CompteASeuilRemunere extends CompteRemunere implements ICompteASeuil {
	private double seuil;

	// CONSTRUCTORS
	public CompteASeuilRemunere() {
		this(0, 0, 0);
	}

	public CompteASeuilRemunere(int pNumero, double pSolde, double pTaux) {
		super(pNumero, pSolde, pTaux);
	}

	@Override
	public double getSeuil() {
		return this.seuil;
	}

	@Override
	public void setSeuil(double pSeuil) {
		this.setSeuil(pSeuil);
	}

	@Override
	public void retirer(double pVal) {
		if (this.getSolde() - pVal > this.getSeuil()) {
			this.setSolde(this.getSolde() - pVal);
		} else {
			System.out.println("Seuil atteint ! Vous ne pouvez pas retirer de l'argent !\n");
		}
	}
}
