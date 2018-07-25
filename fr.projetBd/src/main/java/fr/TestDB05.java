package fr;

import java.math.BigDecimal;
import java.sql.Connection;
import java.sql.Date;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import fr.banque.Client;
import fr.banque.Compte;
import fr.banque.CompteASeuil;
import fr.banque.CompteASeuilRemunere;
import fr.banque.CompteRemunere;
import fr.banque.Operation;

public class TestDB05 {
	private String loginBdd;
	private String passwordBdd;
	private String urlBdd;

	// En cas de multi-threading, on a un risque d'incohérence dans le comportement
	// des objets rs et stmt
	private static Statement stmt = null;
	private static ResultSet rs = null;
	private static Connection conn = null;

	private final static String driverBdd = "com.mysql.jdbc.Driver";

	// CONSTRUCTOR
	public TestDB05() {
	}

	// GETTERS
	public String getLoginBdd() {
		return this.loginBdd;
	}

	public String getPasswordBdd() {
		return this.passwordBdd;
	}

	public String getUrlBdd() {
		return this.urlBdd;
	}

	// SETTERS
	public void setLoginBdd(String loginBdd) {
		this.loginBdd = loginBdd;
	}

	public void setPasswordBdd(String passwordBdd) {
		this.passwordBdd = passwordBdd;
	}

	public void setUrlBdd(String urlBdd) {
		this.urlBdd = urlBdd;
	}

	public void seDeconnecter() throws SQLException {
		if (TestDB05.rs != null) {
			TestDB05.rs.close();
		}
		TestDB05.rs = null;

		if (TestDB05.stmt != null) {
			TestDB05.stmt.close();
		}
		TestDB05.stmt = null;
	}

	// METHODS
	public void seConnecter(String pLogin, String pPassword, String pUrl) throws Exception {
		this.setLoginBdd(pLogin);
		this.setPasswordBdd(pPassword);
		this.setUrlBdd(pUrl);

		Class.forName(TestDB05.driverBdd).newInstance();
		TestDB05.conn = DriverManager.getConnection(this.getUrlBdd(), this.getLoginBdd(), this.getPasswordBdd());
	}

	public List<Client> recupererClients() throws SQLException {
		List<Client> listeClient = new ArrayList<>();
		long dateDeNaissance = 0L;
		int id = -1;
		String prenom = null;
		String nom = null;

		TestDB05.stmt = TestDB05.conn.createStatement();
		TestDB05.rs = TestDB05.stmt.executeQuery(
				"SELECT nom, prenom, id, year(curdate()) - year(dateDeNaissance) as dateDeNaissance FROM utilisateur");

		while (TestDB05.rs.next()) {
			if (TestDB05.rs.getObject("dateDeNaissance") == null) {
				dateDeNaissance = -1L;
			} else {
				dateDeNaissance = (long) TestDB05.rs.getObject("dateDeNaissance");
			}

			id = TestDB05.rs.getInt("id");
			nom = TestDB05.rs.getString("nom");
			prenom = TestDB05.rs.getString("prenom");

			Client client = new Client(nom, prenom, id, dateDeNaissance);
			listeClient.add(client);
		}

		return listeClient;

	}

	public List<Compte> recupererComptes(int pIdClient) throws SQLException {
		List<Compte> listeCompte = new ArrayList<>();
		int id = -1;
		BigDecimal solde = new BigDecimal(0D);
		BigDecimal seuil = new BigDecimal(0D);
		BigDecimal taux = new BigDecimal(0D);
		String libelle = "Inconnu";

		TestDB05.stmt = TestDB05.conn.createStatement();
		TestDB05.rs = TestDB05.stmt.executeQuery("SET @a=" + pIdClient);
		TestDB05.rs = TestDB05.stmt.executeQuery("PREPARE STMT FROM 'SELECT * FROM compte WHERE utilisateurId = ?'");
		TestDB05.rs = TestDB05.stmt.executeQuery("EXECUTE STMT USING @a");

		while (TestDB05.rs.next()) {
			// Compte à seuil
			if (TestDB05.rs.getObject("decouvert") != null && TestDB05.rs.getObject("taux") == null) {
				id = TestDB05.rs.getInt("id");
				libelle = TestDB05.rs.getString("libelle");
				solde = (BigDecimal) TestDB05.rs.getObject("solde");
				seuil = (BigDecimal) TestDB05.rs.getObject("decouvert");

				Compte compteASeuil = new CompteASeuil(id, libelle, solde, seuil);
				listeCompte.add(compteASeuil);

				// Compte à seuil rémunéré
			} else if (TestDB05.rs.getObject("decouvert") != null && TestDB05.rs.getObject("taux") != null) {
				id = TestDB05.rs.getInt("id");
				libelle = TestDB05.rs.getString("libelle");
				solde = (BigDecimal) TestDB05.rs.getObject("solde");
				seuil = (BigDecimal) TestDB05.rs.getObject("decouvert");
				taux = (BigDecimal) TestDB05.rs.getObject("taux");
				Compte compteASeuilRemunere = new CompteASeuilRemunere(id, libelle, solde, seuil, taux);
				listeCompte.add(compteASeuilRemunere);

				// Compte rémunéré
			} else if (TestDB05.rs.getObject("decouvert") == null && TestDB05.rs.getObject("taux") != null) {
				id = TestDB05.rs.getInt("id");
				libelle = TestDB05.rs.getString("libelle");
				solde = (BigDecimal) TestDB05.rs.getObject("solde");
				taux = (BigDecimal) TestDB05.rs.getObject("taux");
				Compte compteRemunere = new CompteRemunere(id, libelle, solde, taux);
				listeCompte.add(compteRemunere);

				// Compte
			} else {
				id = TestDB05.rs.getInt("id");
				libelle = TestDB05.rs.getString("libelle");
				solde = (BigDecimal) TestDB05.rs.getObject("solde");
				Compte compte = new Compte(id, libelle, solde);
				listeCompte.add(compte);
			}
		}
		TestDB05.rs = TestDB05.stmt.executeQuery("DEALLOCATE PREPARE STMT");

		return listeCompte;
	}

	public List<Operation> recupererOperations(int pIdCompte) throws SQLException {
		List<Operation> listeOperation = new ArrayList<>();
		int id = -1;
		int idCompte = -1;
		String libelle = "Inconnu";
		double montant = 0D;
		Date date = null;

		TestDB05.stmt = TestDB05.conn.createStatement();
		TestDB05.rs = TestDB05.stmt.executeQuery("SET @a=" + pIdCompte);
		TestDB05.rs = TestDB05.stmt.executeQuery("PREPARE STMT FROM 'SELECT * FROM operation WHERE compteId = ?'");
		TestDB05.rs = TestDB05.stmt.executeQuery("EXECUTE STMT USING @a");
		while (TestDB05.rs.next()) {
			id = TestDB05.rs.getInt("id");
			libelle = TestDB05.rs.getString("libelle");
			montant = TestDB05.rs.getDouble("montant");
			date = TestDB05.rs.getDate("date");
			idCompte = TestDB05.rs.getInt("compteId");

			Operation operation = new Operation(id, libelle, montant, date, idCompte);
			listeOperation.add(operation);
		}
		TestDB05.rs = TestDB05.stmt.executeQuery("DEALLOCATE PREPARE STMT");

		return listeOperation;
	}
}
