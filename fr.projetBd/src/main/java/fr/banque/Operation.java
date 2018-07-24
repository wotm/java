package fr.banque;

import java.sql.Date;

public class Operation {
	private int id;
	private String libelle;
	private double montant;
	private Date date;
	private int idCompte;

	// CONSTRUCTORS
	public Operation() {
		this(-1, "Inconnu", 0D, null, -1);
	}

	public Operation(int pId, String pLibelle, double pMontant, Date pDate, int pIdCompte) {
		this.setId(pId);
		this.setLibelle(pLibelle);
		this.setMontant(pMontant);
		this.setDate(pDate);
		this.setIdCompte(pIdCompte);
	}

	// GETTERS
	public int getId() {
		return this.id;
	}

	public String getLibelle() {
		return this.libelle;
	}

	public double getMontant() {
		return this.montant;
	}

	public Date getDate() {
		return this.date;
	}

	public int getIdCompte() {
		return this.idCompte;
	}

	// SETTERS
	public void setId(int id) {
		this.id = id;
	}

	public void setLibelle(String libelle) {
		this.libelle = libelle;
	}

	public void setMontant(double montant) {
		this.montant = montant;
	}

	public void setDate(Date date) {
		this.date = date;
	}

	public void setIdCompte(int pIdCompte) {
		this.idCompte = pIdCompte;
	}

	// METHODS
	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("Id opération = ");
		builder.append(this.getId());
		builder.append("\nLibellé = ");
		builder.append(this.getLibelle());
		builder.append("\nMontant = ");
		builder.append(this.getMontant() + "€");
		builder.append("\nDate = ");
		builder.append(this.getDate());
		builder.append("\nId Compte  = ");
		builder.append(this.getIdCompte() + "\n");
		return builder.toString();
	}

}
