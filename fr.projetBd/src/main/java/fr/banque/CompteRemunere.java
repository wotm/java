package fr.banque;

import java.math.BigDecimal;

public class CompteRemunere extends Compte implements ICompteRemunere {
	private BigDecimal taux;

	// CONSTRUCTORS
	public CompteRemunere() {
		this(-1, "Inconnu", new BigDecimal(0D), new BigDecimal(0D));
	}

	public CompteRemunere(int pNumero, String pLibelle, BigDecimal pSolde, BigDecimal pTaux) {
		super(pNumero, pLibelle, pSolde);
		this.taux = pTaux;
	}

	// GETTERS
	@Override
	public BigDecimal getTaux() {
		return this.taux;
	}

	// SETTERS
	@Override
	public BigDecimal setTaux(BigDecimal pTaux) {
		if (pTaux.compareTo(new BigDecimal(0D)) == 1 && pTaux.compareTo(new BigDecimal(1D)) == -1) {
			this.taux = pTaux;
		} else {
			this.taux = new BigDecimal(0D);
		}

		return this.getTaux();
	}

	// METHODS
	@Override
	public String toString() {
		return super.toString() + "Taux : " + this.getTaux().multiply(new BigDecimal(100)) + "%\nIntérêts : "
				+ this.calculerInterets() + "€\n";
	}

	@Override
	public BigDecimal calculerInterets() {
		return this.getTaux().multiply(this.getSolde());
	}

	@Override
	public BigDecimal verserInterets() {
		return this.getSolde().add(this.calculerInterets());
	}

}
