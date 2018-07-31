package fr;

import java.math.BigDecimal;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import fr.banque.Compte;
import fr.banque.CompteASeuil;
import fr.banque.CompteASeuilRemunere;
import fr.banque.CompteRemunere;

public class TestDB03 {

	public static void main(String[] args) {
		final String dbDriver = "com.mysql.jdbc.Driver";
		final String dbUrl = "jdbc:mysql://localhost:3308/banque?useSSL=false";
		final String dblogin = "root";
		final String dbPwd = "root";

		List<Compte> listeCompte = new ArrayList<>();
		int id = -1;
		BigDecimal solde = new BigDecimal(0D);
		BigDecimal seuil = new BigDecimal(0D);
		BigDecimal taux = new BigDecimal(0D);
		String libelle = "Inconnu";

		try {
			Class.forName(dbDriver).newInstance();
		} catch (Exception ex) {
			ex.printStackTrace();
		}

		Connection conn = null;
		try {
			conn = DriverManager.getConnection(dbUrl, dblogin, dbPwd);

		} catch (SQLException ex) {
			System.out.println("SQLException: " + ex.getMessage());
			System.out.println("SQLState: " + ex.getSQLState());
			System.out.println("VendorError: " + ex.getErrorCode());
		}

		Statement stmt = null;
		ResultSet rs = null;

		try {
			stmt = conn.createStatement();
			rs = stmt.executeQuery("SET @a=1");
			rs = stmt.executeQuery("PREPARE STMT FROM 'SELECT * FROM compte WHERE utilisateurId = ?'");
			rs = stmt.executeQuery("EXECUTE STMT USING @a");

			while (rs.next()) {
				// Compte à seuil
				if (rs.getObject("decouvert") != null && rs.getObject("taux") == null) {
					id = rs.getInt("id");
					libelle = rs.getString("libelle");
					solde = (BigDecimal) rs.getObject("solde");
					seuil = (BigDecimal) rs.getObject("decouvert");

					Compte compteASeuil = new CompteASeuil(id, libelle, solde, seuil);
					listeCompte.add(compteASeuil);

					// Compte à seuil rémunéré
				} else if (rs.getObject("decouvert") != null && rs.getObject("taux") != null) {
					id = rs.getInt("id");
					libelle = rs.getString("libelle");
					solde = (BigDecimal) rs.getObject("solde");
					seuil = (BigDecimal) rs.getObject("decouvert");
					taux = (BigDecimal) rs.getObject("taux");
					Compte compteASeuilRemunere = new CompteASeuilRemunere(id, libelle, solde, seuil, taux);
					listeCompte.add(compteASeuilRemunere);

					// Compte rémunéré
				} else if (rs.getObject("decouvert") == null && rs.getObject("taux") != null) {
					id = rs.getInt("id");
					libelle = rs.getString("libelle");
					solde = (BigDecimal) rs.getObject("solde");
					taux = (BigDecimal) rs.getObject("taux");
					Compte compteRemunere = new CompteRemunere(id, libelle, solde, taux);
					listeCompte.add(compteRemunere);

					// Compte
				} else {
					id = rs.getInt("id");
					libelle = rs.getString("libelle");
					solde = (BigDecimal) rs.getObject("solde");
					Compte compte = new Compte(id, libelle, solde);
					listeCompte.add(compte);
				}
			}
			rs = stmt.executeQuery("DEALLOCATE PREPARE STMT");

			// Affichage de la liste des comptes
			for (int i = 0; i < listeCompte.size(); i++) {
				System.out.println(listeCompte.get(i).toString());
			}

		} catch (SQLException ex) {
			System.out.println("SQLException: " + ex.getMessage());
			System.out.println("SQLState: " + ex.getSQLState());
			System.out.println("VendorError: " + ex.getErrorCode());
		} finally {
			if (rs != null) {
				try {
					rs.close();
				} catch (SQLException sqlEx) {
					sqlEx.printStackTrace();
				}

				rs = null;
			}

			if (stmt != null) {
				try {
					stmt.close();
				} catch (SQLException sqlEx) {
					sqlEx.printStackTrace();
				}

				stmt = null;
			}
		}
	}
}
