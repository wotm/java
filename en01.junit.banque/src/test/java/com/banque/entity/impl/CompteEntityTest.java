package com.banque.entity.impl;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.junit.Assert;
import org.junit.Test;

public class CompteEntityTest {
	private final static Logger LOG = LogManager.getLogger();

	@Test
	public void testConstructeur() {
		CompteEntityTest.LOG.debug("Passage dans CompteEntity");
		CompteEntity c = new CompteEntity(1, "Compte A Seuil Remunéré", 1200d, 600d, 0.2d);
		Assert.assertNotNull("p ne doit pas être null !", c);
		Assert.assertEquals("L'id doit être 1 !", new Integer(1), c.getId());
		Assert.assertEquals("Le libellé doit être Compte A Seuil Remunéré !", "Compte A Seuil Remunéré",
				c.getLibelle());
		Assert.assertEquals("Le solde doit être de 1200 !", new Double(1200), c.getSolde());
		Assert.assertEquals("Le découvert doit être de 600 !", new Double(600), c.getDecouvert());
		Assert.assertEquals("Le taux doit être de 0.2 !", new Double(0.2), c.getTaux());
	}

	@Test
	public void testSetLibelle() {
		CompteEntityTest.LOG.debug("Passage dans SetLibelle");
		CompteEntity c = new CompteEntity();
		c.setLibelle("Compte courant");
		Assert.assertEquals("Compte courant", c.getLibelle());
	}

	@Test
	public void testSetLibelleIsNull() {
		CompteEntityTest.LOG.debug("Passage dans SetLibelleIsNull");
		CompteEntity c = new CompteEntity();
		c.setLibelle(null);
		Assert.assertNull("Le libellé doit être null !", c.getLibelle());
	}

	@Test
	public void testSetLibelleTrim() {
		CompteEntityTest.LOG.debug("Passage dans SetLibelleTrim");
		CompteEntity c = new CompteEntity();
		c.setLibelle(" ");
		Assert.assertNull("Le libellé doit être null !", c.getLibelle());
	}

	@Test
	public void testEqualsInstanceOfFalse() {
		CompteEntityTest.LOG.debug("Passage dans EqualsInstanceOfFalse");
		CompteEntity c = new CompteEntity();
		UtilisateurEntity u = new UtilisateurEntity();
		Assert.assertFalse("La valeur de retour doit être false !", c.equals(u));
	}

	@Test
	public void testEqualsInstanceOfTrue() {
		CompteEntityTest.LOG.debug("Passage dans EqualsInstanceOfTrue");
		CompteEntity c1 = new CompteEntity();
		c1.setId(1);
		CompteEntity c2 = new CompteEntity();
		c2.setId(1);
		Assert.assertTrue("La valeur de retour doit être true !", c1.equals(c2));
	}

	@Test
	public void testEqualsIsNullId() {
		CompteEntityTest.LOG.debug("Passage dans EqualsIsNullId");
		CompteEntity c1 = new CompteEntity();
		c1.setId(1);
		CompteEntity c2 = new CompteEntity();
		Assert.assertFalse("La valeur de retour doit être false !", c1.equals(c2));
	}

	@Test
	public void testEqualsNotSameId() {
		CompteEntityTest.LOG.debug("Passage dans EqualsNotSameId");
		CompteEntity c1 = new CompteEntity();
		c1.setId(1);
		CompteEntity c2 = new CompteEntity();
		c1.setId(2);
		Assert.assertFalse("La valeur de retour doit être false !", c1.equals(c2));
	}

	@Test
	public void testEqualsIsNull() {
		CompteEntityTest.LOG.debug("Passage dans EqualsIsNull");
		CompteEntity c = new CompteEntity();
		Assert.assertEquals("La valeur retournée doit être false !", false, c.equals(null));
	}

	@Test
	public void testEqualsIsThisObj() {
		CompteEntityTest.LOG.debug("Passage dans IsThisObj");
		CompteEntity c = new CompteEntity();
		Assert.assertTrue("La valeur retournée doit être true !", c.equals(c));
	}
}
