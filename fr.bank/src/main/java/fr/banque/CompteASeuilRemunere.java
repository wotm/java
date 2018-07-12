package fr.banque;

// On hérite de CompteRemunere afin de bénéficier du polymorphisme sur la méthode verserInterets()
// de la classe CompteRemunere que nous utilisons dans la classe Run pour verser les intérêts de chaque compte  de type "remunere"
public class CompteASeuilRemunere extends CompteRemunere implements ICompteASeuil {
	private double seuil;

	// CONSTRUCTORS
	public CompteASeuilRemunere() {
		this(0, 0, 0, 0);
	}

	public CompteASeuilRemunere(int pNumero, double pSolde, double pTaux, double pSeuil) {
		super(pNumero, pSolde, pTaux);
		this.setSeuil(pSeuil);
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
	// On implémente la méthode retirer de l'interface ICompteASeuil
	// pour notre traitement particulier du retrait en fonction du seuil
	public void retirer(double pVal) throws BanqueException {
		if (this.getSolde() - pVal > this.getSeuil()) {
			this.setSolde(this.getSolde() - pVal);
		} else {
			throw new BanqueException("Vous avez atteint le seuil maximal de " + this.getSeuil() + " € !");
		}
	}

	@Override
	public String toString() {
		return super.toString() + "Seuil : " + this.getSeuil() + "€\n";
	}

}
