package fr;

import java.sql.Connection;
import java.sql.Date;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import fr.banque.Operation;

public class TestDB04 {

	public static void main(String[] args) {
		final String dbDriver = "com.mysql.jdbc.Driver";
		final String dbUrl = "jdbc:mysql://localhost:3308/banque?useSSL=false";
		final String dblogin = "root";
		final String dbPwd = "root";

		List<Operation> listeOperation = new ArrayList<>();
		int id = -1;
		int idCompte = -1;
		String libelle = "Inconnu";
		double montant = 0D;
		Date date = null;

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
			rs = stmt.executeQuery("SET @a=15");
			rs = stmt.executeQuery("PREPARE STMT FROM 'SELECT * FROM operation WHERE compteId = ?'");
			rs = stmt.executeQuery("EXECUTE STMT USING @a");
			while (rs.next()) {
				id = rs.getInt("id");
				libelle = rs.getString("libelle");
				montant = rs.getDouble("montant");
				date = rs.getDate("date");
				idCompte = rs.getInt("compteId");

				Operation operation = new Operation(id, libelle, montant, date, idCompte);
				listeOperation.add(operation);
			}
			rs = stmt.executeQuery("DEALLOCATE PREPARE STMT");

			// Affichage de la liste des opérations
			for (int i = 0; i < listeOperation.size(); i++) {
				System.out.println(listeOperation.get(i).toString());
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
				}

				rs = null;
			}

			if (stmt != null) {
				try {
					stmt.close();
				} catch (SQLException sqlEx) {
				}

				stmt = null;
			}
		}
	}
}
