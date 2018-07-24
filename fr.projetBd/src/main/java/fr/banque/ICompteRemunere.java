package fr.banque;

import java.math.BigDecimal;

public interface ICompteRemunere {
	public BigDecimal calculerInterets();

	public BigDecimal verserInterets();

	public BigDecimal getTaux();

	public BigDecimal setTaux(BigDecimal pTaux);
}
