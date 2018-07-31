package com.banque.entity.impl;

import java.sql.Timestamp;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.junit.Assert;
import org.junit.Test;

public class OperationEntityTest {
	private final static Logger LOG = LogManager.getLogger();

	@Test
	public void testConstructeur() {
		OperationEntityTest.LOG.debug("Passage dans OperationEntity");
		OperationEntity op = new OperationEntity(1, new Timestamp(1533027810), "Retrait", 100d);
		Assert.assertEquals("La valeur de l'id doit être de 1 !", new Integer(1), op.getId());
		Assert.assertEquals("La valeur du timestamp doit être égale à 1533027810 !", new Timestamp(1533027810),
				op.getDate());
		Assert.assertEquals("La valeur du libellé doit être égale à Retrait !", "Retrait", op.getLibelle());
		Assert.assertEquals("La valeur du montant doit être égale à 100 !", new Double(100), op.getMontant());
	}

	@Test
	public void testSetDate() {
		OperationEntityTest.LOG.debug("Passage dans SetDate");
		OperationEntity op = new OperationEntity();
		op.setDate(new Timestamp(1533027810));
		Assert.assertEquals("La valeur de la date est de 1533027810", new Timestamp(1533027810), op.getDate());
	}

	@Test
	public void testSetDateNull() {
		OperationEntityTest.LOG.debug("Passage dans SetDate");
		OperationEntity op = new OperationEntity();
		op.setDate(null);
		Assert.assertTrue("La date doit être une instance de Timestamp", op.getDate() instanceof Timestamp);
	}
}
