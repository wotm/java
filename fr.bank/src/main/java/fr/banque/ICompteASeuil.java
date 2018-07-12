package fr.banque;

public interface ICompteASeuil {
	public void retirer(double pMontant);

	public double getSeuil();

	public void setSeuil(double pSeuil);
}
