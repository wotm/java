package fr.banque;

public class Client {
	private String nom;
	private String prenom;
	private int numero;
	private int age;
	private Compte[] comptes;

	// CONSTRUCTORS
	public Client() {
		this("", "", 0, 0);
	}

	public Client(String pNom, String pPrenom, int pNumero, int pAge) {
		this.nom = pNom;
		this.prenom = pPrenom;
		this.numero = pNumero;
		this.age = pAge;
		this.comptes = new Compte[5];
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

	public int getAge() {
		return this.age;
	}

	public Compte[] getComptes() {
		return this.comptes;
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

	public void setAge(int pAge) {
		this.age = pAge;
	}

	// METHODS
	@Override
	public String toString() {
		return "N°Client : " + this.getNumero() + "\nNom : " + this.getNom() + "\nPrénom : " + this.getPrenom()
				+ "\nAge : " + this.getAge() + "\n";
	}

	public void ajouterCompte(Compte pCompte) {
		Compte[] listeCompte = this.getComptes();
		int i;
		for (i = 0; i < listeCompte.length; i++) {
			if (null == listeCompte[i]) {
				listeCompte[i] = pCompte;
				break;
			}
		}

		if (i == 5 && null != listeCompte[i]) {
			System.out.println("Limite maximale de comptes atteinte ! \n");
		}
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
