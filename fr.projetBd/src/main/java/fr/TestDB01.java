package fr;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class TestDB01 {
	public static void main(String[] args) {
		final String dbDriver = "com.mysql.jdbc.Driver";
		final String dbUrl = "jdbc:mysql://localhost:3308/banque?useSSL=false";
		final String dblogin = "root";
		final String dbPwd = "root";

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
			rs = stmt.executeQuery("SELECT nom, prenom FROM utilisateur");

			while (rs.next()) {
				System.out.print(rs.getString("nom") + " \t");
				System.out.println(rs.getString("prenom"));
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
