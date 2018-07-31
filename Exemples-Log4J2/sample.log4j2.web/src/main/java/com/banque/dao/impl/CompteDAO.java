package com.banque.dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Types;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.banque.dao.ICompteDAO;
import com.banque.entity.ICompteEntity;
import com.banque.entity.impl.CompteEntity;

/**
 * Gestion des comptes.
 */
public class CompteDAO extends AbstractDAO<ICompteEntity> implements ICompteDAO {
	@SuppressWarnings("unused")
	private static final Logger LOG = LogManager.getLogger();

	/**
	 * Constructeur de l'objet.
	 */
	public CompteDAO() {
		super();
	}

	@Override
	protected String getTableName() {
		return "compte";
	}

	@Override
	protected String getAllColumnNames() {
		return "id,libelle,solde,decouvert,taux,utilisateurId";
	}

	@Override
	protected ICompteEntity fromResultSet(ResultSet rs) throws SQLException {
		ICompteEntity result = new CompteEntity();
		result.setId(Integer.valueOf(rs.getInt("id")));
		result.setLibelle(rs.getString("libelle"));
		result.setSolde(Double.valueOf(rs.getDouble("solde")));
		result.setUtilisateurId(Integer.valueOf(rs.getInt("utilisateurId")));
		double vd = rs.getDouble("decouvert");
		// Le decouvert etait-il null ?
		boolean dnull = rs.wasNull();
		double vt = rs.getDouble("taux");
		// Le taux etait-il null ?
		boolean tnull = rs.wasNull();
		if (!dnull) {
			result.setDecouvert(Double.valueOf(vd));
		} else {
			result.setDecouvert(null);
		}
		if (!tnull) {
			result.setTaux(Double.valueOf(vt));
		} else {
			result.setTaux(null);
		}
		return result;
	}

	@Override
	protected PreparedStatement buildStatementForInsert(ICompteEntity pUneEntite, Connection connexion)
			throws SQLException {
		String request = "insert into " + this.getTableName()
				+ " (libelle, solde, decouvert, taux, utilisateurId) values (?,?,?,?,?);";
		PreparedStatement ps = connexion.prepareStatement(request, Statement.RETURN_GENERATED_KEYS);
		this.fillPreparedStatement(false, ps, pUneEntite);
		return ps;
	}

	@Override
	protected PreparedStatement buildStatementForUpdate(ICompteEntity pUneEntite, Connection connexion)
			throws SQLException {
		String request = "update " + this.getTableName()
				+ " set libelle=?, solde=?, decouvert=?, taux=?, utilisateurId=? where " + this.getPkName() + "=?;";
		PreparedStatement ps = connexion.prepareStatement(request);
		this.fillPreparedStatement(true, ps, pUneEntite);
		return ps;
	}

	/**
	 * Se charge de remplir les trous du prepare statement.
	 *
	 * @param forUpdate
	 *            true ajoutera l'id en derniere position.
	 * @param ps
	 *            le preparedstatement
	 * @param pUneEntite
	 *            l'entite
	 * @throws SQLException
	 *             si un probleme survient
	 */
	private void fillPreparedStatement(boolean forUpdate, PreparedStatement ps, ICompteEntity pUneEntite)
			throws SQLException {
		ps.setString(1, pUneEntite.getLibelle());
		ps.setDouble(2, pUneEntite.getSolde().doubleValue());
		if (pUneEntite.getDecouvert() != null) {
			ps.setDouble(3, pUneEntite.getDecouvert().doubleValue());
		} else {
			ps.setNull(3, Types.DOUBLE);
		}
		if (pUneEntite.getTaux() != null) {
			ps.setDouble(4, pUneEntite.getTaux().doubleValue());
		} else {
			ps.setNull(4, Types.DOUBLE);
		}
		ps.setInt(5, pUneEntite.getUtilisateurId().intValue());
		if (forUpdate) {
			ps.setInt(6, pUneEntite.getId().intValue());
		}
	}
}