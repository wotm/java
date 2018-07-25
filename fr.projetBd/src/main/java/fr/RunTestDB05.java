package fr;

import java.sql.SQLException;
import java.util.List;

import fr.banque.Client;
import fr.banque.Compte;
import fr.banque.Operation;

public class RunTestDB05 {
	public static void main(String[] args) {
		TestDB05 testDB05 = new TestDB05();
		List<Client> listeClient = null;
		List<Compte> listeCompte = null;
		List<Operation> listeOperation = null;

		try {
			testDB05.seConnecter("root", "root", "jdbc:mysql://localhost:3308/banque?useSSL=false");
		} catch (Exception e) {
			e.printStackTrace();
		}

		try {
			listeClient = testDB05.recupererClients();
			listeCompte = testDB05.recupererComptes(1);
			listeOperation = testDB05.recupererOperations(15);

			// Liste de clients
			for (int i = 0; i < listeClient.size(); i++) {
				System.out.println(listeClient.get(i).toString());
				System.out.println("---------------------------------------");
			}
			// Liste de comptes
			for (int i = 0; i < listeCompte.size(); i++) {
				System.out.println(listeCompte.get(i).toString());
				System.out.println("---------------------------------------");
			}
			// Liste des opérations
			for (int i = 0; i < listeOperation.size(); i++) {
				System.out.println(listeOperation.get(i).toString());
				System.out.println("---------------------------------------");
			}
			testDB05.seDeconnecter();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
}
