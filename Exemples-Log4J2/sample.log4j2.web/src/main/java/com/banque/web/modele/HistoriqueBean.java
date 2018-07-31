package com.banque.web.modele;

import java.io.Serializable;

import javax.servlet.http.HttpServletRequest;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

/**
 * Form pour l'historique. <br/>
 */
public class HistoriqueBean implements Serializable {
	private static final Logger LOG = LogManager.getLogger();
	private static final long serialVersionUID = 1L;

	private Integer cptId;

	private String dateDebut;
	private String dateFin;
	private Boolean credit;
	private Boolean debit;

	/**
	 * Constructeur de l'objet.
	 */
	public HistoriqueBean() {
		super();
	}

	/**
	 * Constructeur de l'objet.
	 *
	 * @param request
	 *            la request
	 */
	public HistoriqueBean(HttpServletRequest request) {
		super();
		String sid = request.getParameter("inNumeroCompte");
		String sdd = request.getParameter("inDateDebut");
		String sdf = request.getParameter("inDateFin");
		String sc = request.getParameter("inCredit");
		String sd = request.getParameter("inDebit");
		// On ne gere pas les problemes de validation ici
		this.setCptId(Integer.valueOf(sid));
		this.setDateDebut(sdd);
		this.setDateFin(sdf);
		this.setCredit("true".equals(sc) ? Boolean.TRUE : Boolean.FALSE);
		this.setDebit("true".equals(sd) ? Boolean.TRUE : Boolean.FALSE);
	}

	/**
	 * Constructeur de l'objet.
	 *
	 * @param aCptId
	 *            un id de compte.
	 */
	public HistoriqueBean(String aCptId) {
		super();
		try {
			this.setCptId(Integer.valueOf(aCptId));
		} catch (Exception e) {
			HistoriqueBean.LOG.trace("Erreur sur l'id", e);
		}
	}

	/**
	 * Recupere la propriete <i>cptId</i>.
	 *
	 * @return the cptId la valeur de la propriete.
	 */
	public Integer getCptId() {
		return this.cptId;
	}

	/**
	 * Fixe la propriete <i>cptId</i>.
	 *
	 * @param pCptId
	 *            la nouvelle valeur pour la propriete cptId.
	 */
	public void setCptId(Integer pCptId) {
		this.cptId = pCptId;
	}

	/**
	 * Recupere la propriete <i>dateDebut</i>.
	 *
	 * @return the dateDebut la valeur de la propriete.
	 */
	public String getDateDebut() {
		return this.dateDebut;
	}

	/**
	 * Fixe la propriete <i>dateDebut</i>.
	 *
	 * @param pDateDebut
	 *            la nouvelle valeur pour la propriete dateDebut.
	 */
	public void setDateDebut(String pDateDebut) {
		if (pDateDebut == null || pDateDebut.trim().length() == 0) {
			this.dateDebut = null;
		} else {
			this.dateDebut = pDateDebut;
		}
	}

	/**
	 * Recupere la propriete <i>dateFin</i>.
	 *
	 * @return the dateFin la valeur de la propriete.
	 */
	public String getDateFin() {
		return this.dateFin;
	}

	/**
	 * Fixe la propriete <i>dateFin</i>.
	 *
	 * @param pDateFin
	 *            la nouvelle valeur pour la propriete dateFin.
	 */
	public void setDateFin(String pDateFin) {
		if (pDateFin == null || pDateFin.trim().length() == 0) {
			this.dateFin = null;
		} else {
			this.dateFin = pDateFin;
		}
	}

	/**
	 * Recupere la propriete <i>credit</i>.
	 *
	 * @return the credit la valeur de la propriete.
	 */
	public Boolean getCredit() {
		return this.credit;
	}

	/**
	 * Fixe la propriete <i>credit</i>.
	 *
	 * @param pCredit
	 *            la nouvelle valeur pour la propriete credit.
	 */
	public void setCredit(Boolean pCredit) {
		this.credit = pCredit;
	}

	/**
	 * Recupere la propriete <i>debit</i>.
	 *
	 * @return the debit la valeur de la propriete.
	 */
	public Boolean getDebit() {
		return this.debit;
	}

	/**
	 * Fixe la propriete <i>debit</i>.
	 *
	 * @param pDebit
	 *            la nouvelle valeur pour la propriete debit.
	 */
	public void setDebit(Boolean pDebit) {
		this.debit = pDebit;
	}

}
