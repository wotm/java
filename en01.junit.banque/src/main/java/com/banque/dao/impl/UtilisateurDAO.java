package com.banque.dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Types;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.banque.dao.IUtilisateurDAO;
import com.banque.dao.ex.ExceptionDao;
import com.banque.entity.ESex;
import com.banque.entity.IUtilisateurEntity;
import com.banque.entity.impl.UtilisateurEntity;

/**
 * Gestion des utilisateurs.
 */
public class UtilisateurDAO extends AbstractDAO<IUtilisateurEntity> implements IUtilisateurDAO {
	private static final Logger LOG = LogManager.getLogger();

	@Override
	protected String getTableName() {
		return "utilisateur";
	}

	@Override
	protected String getAllColumnNames() {
		return "id,nom,prenom,login,password,sex,derniereConnection,dateDeNaissance,adresse,codePostal,telephone";
	}

	@Override
	protected IUtilisateurEntity fromResultSet(ResultSet rs) throws SQLException {
		IUtilisateurEntity result = new UtilisateurEntity();
		result.setId(Integer.valueOf(rs.getInt("id")));
		result.setNom(rs.getString("nom"));
		result.setPrenom(rs.getString("prenom"));
		result.setLogin(rs.getString("login"));
		result.setPassword(rs.getString("password"));
		// Gestion de l'enumeration
		byte sex = rs.getByte("sex");
		result.setSex(ESex.fromValue(sex));
		result.setDerniereConnection(rs.getTimestamp("derniereConnection"));
		result.setAdresse(rs.getString("adresse"));
		int cp = rs.getInt("codePostal");
		if (rs.wasNull()) {
			result.setCodePostal(null);
		} else {
			result.setCodePostal(Integer.valueOf(cp));
		}
		result.setTelephone(rs.getString("telephone"));
		result.setDateDeNaissance(rs.getDate("dateDeNaissance"));
		return result;
	}

	@Override
	protected PreparedStatement buildStatementForInsert(IUtilisateurEntity pUneEntite, Connection connexion)
			throws SQLException {
		String request = "insert into " + this.getTableName()
				+ " (nom,prenom,login,password,sex,derniereConnection,dateDeNaissance,adresse,codePostal,telephone) values (?,?,?,?,?,?,?,?,?,?);";
		PreparedStatement ps = connexion.prepareStatement(request, Statement.RETURN_GENERATED_KEYS);
		ps.setString(1, pUneEntite.getNom());
		ps.setString(2, pUneEntite.getPrenom());
		ps.setString(3, pUneEntite.getLogin());
		ps.setString(4, pUneEntite.getPassword());
		ps.setByte(5, pUneEntite.getSex().getValue());
		ps.setTimestamp(6, pUneEntite.getDerniereConnection());
		ps.setDate(7, pUneEntite.getDateDeNaissance());
		ps.setString(8, pUneEntite.getAdresse());
		if (pUneEntite.getCodePostal() != null) {
			ps.setInt(9, pUneEntite.getCodePostal().intValue());
		} else {
			ps.setNull(9, Types.INTEGER);
		}
		ps.setString(10, pUneEntite.getTelephone());
		return ps;
	}

	@Override
	@SuppressWarnings("squid:S2068")
	protected PreparedStatement buildStatementForUpdate(IUtilisateurEntity pUneEntite, Connection connexion)
			throws SQLException {
		String request = "update " + this.getTableName()
				+ " set nom=?,prenom=?,login=?,password=?,sex=?,derniereConnection=?,dateDeNaissance=?,adresse=?,codePostal=?,telephone=? where "
				+ this.getPkName() + "=?;";
		PreparedStatement ps = connexion.prepareStatement(request);
		ps.setString(1, pUneEntite.getNom());
		ps.setString(2, pUneEntite.getPrenom());
		ps.setString(3, pUneEntite.getLogin());
		ps.setString(4, pUneEntite.getPassword());
		ps.setByte(5, pUneEntite.getSex().getValue());
		ps.setTimestamp(6, pUneEntite.getDerniereConnection());
		ps.setDate(7, pUneEntite.getDateDeNaissance());
		ps.setString(8, pUneEntite.getAdresse());
		if (pUneEntite.getCodePostal() != null) {
			ps.setInt(9, pUneEntite.getCodePostal().intValue());
		} else {
			ps.setNull(9, Types.INTEGER);
		}
		ps.setString(10, pUneEntite.getTelephone());
		ps.setInt(11, pUneEntite.getId().intValue());

		return ps;
	}

	@Override
	public IUtilisateurEntity selectLogin(String pLogin) throws ExceptionDao {
		IUtilisateurEntity result = null;
		UtilisateurDAO.LOG.debug("selectLogin pLogin={}", pLogin);
		boolean connexionCreated = this.getCurrentConnexion() == null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		Connection connexion = this.getCurrentConnexion();
		try {
			if (connexionCreated) {
				connexion = this.createConnexion();
				// FIXME Bug ou pas ?
				connexion.setReadOnly(true);
			}
			String request = "select " + this.getAllColumnNames() + " from " + this.getTableName() + " where login=?;";
			ps = connexion.prepareStatement(request);
			ps.setString(1, pLogin);
			rs = ps.executeQuery();
			if (rs.next()) {
				result = this.fromResultSet(rs);
			}
		} catch (Exception e) {
			throw new ExceptionDao(e);
		} finally {
			AbstractDAO.handleTransaction(connexionCreated, true, ps, rs, connexion);
		}
		return result;

	}

}