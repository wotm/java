package fr.banque;

import java.math.BigDecimal;

public class CompteASeuil extends Compte implements ICompteASeuil {
	private BigDecimal seuil;

	// CONSTRUCTORS
	public CompteASeuil() {
		this(-1, "Inconnu", new BigDecimal(0D), new BigDecimal(0D));
	}

	public CompteASeuil(int pNumero, String pLibelle, BigDecimal pSolde, BigDecimal pSeuil) {
		super(pNumero, pLibelle, pSolde);
		this.seuil = pSeuil;
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
	public String toString() {
		return super.toString() + "Seuil : " + this.getSeuil() + "€\n";
	}

	@Override
	public void retirer(BigDecimal pVal) throws BanqueException {
		if (this.getSolde().subtract(pVal).compareTo(this.getSeuil()) == -1) {
			super.retirer(pVal);
		} else {
			throw new BanqueException("Vous avez atteint le seuil maximal de " + this.getSeuil() + " € !\n");
		}
	}
}
