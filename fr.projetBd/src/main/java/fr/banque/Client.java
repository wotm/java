package fr.banque;

import java.util.Hashtable;
import java.util.Map;

public class Client {
	private String nom;
	private String prenom;
	private int numero;
	private long age;
	private Map<Integer, Compte> comptes = new Hashtable<>();

	// CONSTRUCTORS
	public Client() {
		this(null, null, -1, -1);
	}

	public Client(String pNom, String pPrenom, int pNumero, long dateDeNaissance) {
		this.setNom(pNom);
		this.setPrenom(pPrenom);
		this.setNumero(pNumero);
		this.setAge(dateDeNaissance);
		this.comptes = new Hashtable<>();
	}

	// GETTERS
	public String getNom() {
		return this.nom;
	}

	public String getPrenom() {
		return this.prenom;
	}

	public int getNumero() {
		return this.numero;
	}

	public long getAge() {
		return this.age;
	}

	public Compte[] getComptes() {
		return this.comptes.values().toArray(new Compte[5]);
	}

	// SETTERS
	public void setNom(String pNom) {
		this.nom = pNom;
	}

	public void setPrenom(String pPrenom) {
		this.prenom = pPrenom;
	}

	public void setNumero(int pNumero) {
		this.numero = pNumero;
	}

	public void setAge(long pAge) {
		this.age = pAge;
	}

	// METHODS
	@Override
	public String toString() {
		return "N°Client : " + this.getNumero() + "\nNom : " + this.getNom() + "\nPrénom : " + this.getPrenom()
				+ "\nAge : " + this.getAge() + "\n";
	}

	public void ajouterCompte(Compte pCompte) {
		this.comptes.put(pCompte.getNumero(), pCompte);
	}

	public Compte getCompte(int pNumero) {
		Compte compte = null;
		Compte[] listeComptes = this.getComptes();
		for (int i = 0; i < listeComptes.length; i++) {
			if (null != listeComptes[i] && listeComptes[i].getNumero() == pNumero) {
				compte = listeComptes[i];
			}
		}

		if (null == compte) {
			System.out.println("Le numéro fourni ne correspond à aucun compte !");
		}
		return compte;
	}
}
