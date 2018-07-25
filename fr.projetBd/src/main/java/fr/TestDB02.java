package fr;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import fr.banque.Client;

public class TestDB02 {
	public static void main(String[] args) {
		final String dbDriver = "com.mysql.jdbc.Driver";
		final String dbUrl = "jdbc:mysql://localhost:3308/banque?useSSL=false";
		final String dblogin = "root";
		final String dbPwd = "root";

		// POJO variables
		List<Client> listeClient = new ArrayList<>();
		long dateDeNaissance = 0L;
		int id = -1;
		String prenom = null;
		String nom = null;

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
			rs = stmt.executeQuery(
					"SELECT nom, prenom, id, year(curdate()) - year(dateDeNaissance) as dateDeNaissance FROM utilisateur");

			while (rs.next()) {
				if (rs.getObject("dateDeNaissance") == null) {
					dateDeNaissance = -1L;
				} else {
					dateDeNaissance = (long) rs.getObject("dateDeNaissance");
				}

				id = rs.getInt("id");
				nom = rs.getString("nom");
				prenom = rs.getString("prenom");

				Client client = new Client(nom, prenom, id, dateDeNaissance);
				listeClient.add(client);
			}

			for (int i = 0; i < listeClient.size(); i++) {
				System.out.println(listeClient.get(i).toString());
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
