package fr.banque;

public interface ICompteRemunere {
	public double calculerInterets();

	public double verserInterets();

	public double getTaux();

	public double setTaux(double pTaux);
}
