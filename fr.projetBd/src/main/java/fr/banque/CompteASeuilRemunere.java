package fr.banque;

import java.math.BigDecimal;

// On hérite de CompteRemunere afin de bénéficier du polymorphisme sur la méthode verserInterets()
// de la classe CompteRemunere que nous utilisons dans la classe Run pour verser les intérêts de chaque compte  de type "remunere"
public class CompteASeuilRemunere extends CompteRemunere implements ICompteASeuil {
	private BigDecimal seuil;

	// CONSTRUCTORS
	public CompteASeuilRemunere() {
		this(-1, "Inconnu", new BigDecimal(0D), new BigDecimal(0D), new BigDecimal(0D));
	}

	public CompteASeuilRemunere(int pNumero, String pLibelle, BigDecimal pSolde, BigDecimal pTaux, BigDecimal pSeuil) {
		super(pNumero, pLibelle, pSolde, pTaux);
		this.setSeuil(pSeuil);
	}

	// GETTERS
	@Override
	public BigDecimal getSeuil() {
		return this.seuil;
	}

	// SETTERS
	@Override
	public void setSeuil(BigDecimal pSeuil) {
		this.seuil = pSeuil;
	}

	// METHODS
	@Override
	// On implémente la méthode retirer de l'interface ICompteASeuil
	// pour notre traitement particulier du retrait en fonction du seuil
	public void retirer(BigDecimal pVal) throws BanqueException {
		if (this.getSolde().subtract(pVal).compareTo(this.getSeuil()) == 1) {
			super.retirer(pVal);
		} else {
			throw new BanqueException("Vous avez atteint le seuil maximal de " + this.getSeuil() + " € !");
		}
	}

	@Override
	public String toString() {
		return super.toString() + "Seuil : " + this.getSeuil() + "€\n";
	}

}
