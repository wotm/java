package fr;

import fr.banque.BanqueException;
import fr.banque.Client;
import fr.banque.Compte;
import fr.banque.CompteASeuil;
import fr.banque.CompteASeuilRemunere;
import fr.banque.CompteRemunere;
import fr.banque.ICompteRemunere;

public class Run {
	public static void main(String[] args) throws BanqueException {
		// Instanciation d'un client
		Client client = new Client("Durand", "Jean", 1, 35);

		// Instanciation des comptes
		Compte compte1 = new Compte(1, 1250);
		Compte compte2 = new Compte(2, 850);
		Compte compte3 = new Compte(2, 850);

		CompteASeuilRemunere compteASeuilRemunere1 = new CompteASeuilRemunere(3, 1200, 0.2, 200);
		CompteRemunere compteRemunere1 = new CompteRemunere(4, 1200, 0.2);
		CompteASeuil compteASeuil1 = new CompteASeuil(5, 1200, 600);

		// Affichage des informations d'un client
		System.out.println(client.toString());

		// Ajout des comptes à un client
		client.ajouterCompte(compte1);
		System.out.println(compte1.toString());

		client.ajouterCompte(compte2);
		System.out.println(compte2.toString());

		client.ajouterCompte(compteASeuilRemunere1);
		System.out.println(compteASeuilRemunere1.toString());

		client.ajouterCompte(compteRemunere1);
		System.out.println(compteRemunere1.toString());

		client.ajouterCompte(compteASeuil1);
		System.out.println(compteASeuil1.toString());

		try {
			client.ajouterCompte(compte3);
		} catch (BanqueException e) {
			System.out.println(e.getMessage());
		}

		// Opérations bancaires
		System.out.println("Opérations bancaires : \n");
		client.getCompte(1).retirer(100);
		System.out.println("Débit de 100€ =>\n" + compte1.toString());

		client.getCompte(2).retirer(200);
		System.out.println("Débit de 200€ =>\n" + compte2.toString());

		client.getCompte(1).ajouter(100);
		System.out.println("Crédit de 100€ =>\n" + compte1.toString());

		client.getCompte(2).ajouter(200);
		System.out.println("Crédit de 200€ =>\n" + compte2.toString());

		System.out.println("Débit de 1000€ =>\n" + compteASeuilRemunere1.toString());
		try {
			client.getCompte(3).retirer(1000);
		} catch (BanqueException e) {
			System.out.println(e.getMessage());
		}

		// Versements des intérêts
		System.out.println("Versement des intérêts des comptes rénumérés du client " + client.getNumero());
		Compte[] listeComptes = client.getComptes();
		for (int i = 0; i < listeComptes.length; i++) {
			if (listeComptes[i] instanceof ICompteRemunere) {
				System.out.println("Compte " + listeComptes[i].getNumero() + " : "
						+ ((ICompteRemunere) listeComptes[i]).verserInterets() + "€");
			}
		}
	}
}
