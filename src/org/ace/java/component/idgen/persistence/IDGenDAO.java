package org.ace.java.component.idgen.persistence;

import javax.annotation.Resource;
import javax.persistence.LockModeType;
import javax.persistence.PersistenceException;
import javax.persistence.Query;

import org.ace.insurance.system.common.branch.Branch;
import org.ace.java.component.idgen.IDGen;
import org.ace.java.component.idgen.persistence.interfaces.IDGenDAOInf;
import org.ace.java.component.idgen.service.interfaces.IDConfigLoader;
import org.ace.java.component.persistence.BasicDAO;
import org.ace.java.component.persistence.exception.DAOException;
import org.apache.log4j.Logger;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

@Repository("IDGenDAO")
public class IDGenDAO extends BasicDAO implements IDGenDAOInf {

	@Resource(name = "IDConfigLoader")
	protected IDConfigLoader configLoader;
	
	private static final Logger logger = Logger.getLogger(IDGenDAO.class);
	
	@Transactional(propagation = Propagation.REQUIRED)
	public IDGen getNextId(String generateItem) throws DAOException {
		logger.info("Start NextId method");
		IDGen idGen = null;
		String branchCode = "";
		try {
			Query selectQuery = em.createQuery(
					"SELECT g FROM IDGen g WHERE g.generateItem = :generateItem AND g.branch.id IN " + " (SELECT b.id FROM Branch b Where b.branchCode = :branchCode)");
			//selectQuery.setLockMode(LockModeType.PESSIMISTIC_WRITE);
			selectQuery.setHint("javax.persistence.lock.timeout", 30000);
			if (configLoader.isCentralizedSystem()) {
				// branchCode =
				// userProcessService.getLoginUser().getBranch().getBranchCode();
			} else {
				branchCode = configLoader.getBranchCode();
			}
			logger.info("GenerateItem :"+generateItem);
			logger.info("BranchCode :"+branchCode);
			selectQuery.setParameter("generateItem", generateItem);
			selectQuery.setParameter("branchCode", branchCode);
			idGen = (IDGen) selectQuery.getSingleResult();
			logger.info("GET IDGEN VALUE");
			if(idGen == null) {
				logger.info("IDGEN is Null");
			}
			
			logger.info("IDGen " + idGen.getId());
			logger.info("MaxValue "+idGen.getMaxValue());
			logger.info("VERSION : "+idGen.getVersion());
			idGen.setMaxValue(idGen.getMaxValue() + 1);
			idGen = em.merge(idGen);
			em.flush();
			logger.info("End NextId method");
		} catch (PersistenceException pe) {
			throw translate("Failed to update ID Generation for " + generateItem, pe);
		}
		System.out.println("idGen" +idGen);
		return idGen;
	}

	@Transactional(propagation = Propagation.REQUIRED)
	public IDGen getNextId(String generateItem, Branch branch) throws DAOException {
		IDGen idGen = null;
		try {
			Query selectQuery = em
					.createQuery("SELECT g FROM IDGen g WHERE g.generateItem = :generateItem AND g.branch.id IN (SELECT b.id FROM Branch b Where b.branchCode = :branchCode)");
			selectQuery.setLockMode(LockModeType.PESSIMISTIC_WRITE);
			selectQuery.setHint("javax.persistence.lock.timeout", 30000);
			selectQuery.setParameter("generateItem", generateItem);
			selectQuery.setParameter("branchCode", branch.getBranchCode());
			idGen = (IDGen) selectQuery.getSingleResult();
			idGen.setMaxValue(idGen.getMaxValue() + 1);
			idGen = em.merge(idGen);
			em.flush();
		} catch (PersistenceException pe) {
			throw translate("Failed to update ID Generation for " + generateItem, pe);
		}
		return idGen;
	}

	@Transactional(propagation = Propagation.REQUIRED)
	public IDGen getIDGen(String generateItem) throws DAOException {
		IDGen idGen = null;
		try {
			Query selectQuery = em
					.createQuery("SELECT g FROM IDGen g WHERE g.generateItem = :generateItem AND g.branch.id IN (SELECT b.id FROM Branch b Where b.branchCode = :branchCode)");
			selectQuery.setParameter("generateItem", generateItem);
			selectQuery.setParameter("branchCode", configLoader.getBranchCode());
			idGen = (IDGen) selectQuery.getSingleResult();
			em.flush();
		} catch (PersistenceException pe) {
			throw translate("Failed to find Max Value for " + generateItem, pe);
		}
		return idGen;
	}

	@Transactional(propagation = Propagation.REQUIRED)
	public IDGen getIDGenForAutoRenewal(String generateItem) throws DAOException {
		IDGen idGen = null;
		try {
			Query selectQuery = em.createQuery("SELECT g FROM IDGen g WHERE g.generateItem = :generateItem");
			selectQuery.setParameter("generateItem", generateItem);
			idGen = (IDGen) selectQuery.getSingleResult();
			idGen.setMaxValue(idGen.getMaxValue() + 1);
			idGen = em.merge(idGen);
			em.flush();
			em.flush();
		} catch (PersistenceException pe) {
			throw translate("Failed to find Max Value for " + generateItem, pe);
		}
		return idGen;
	}

	@Transactional(propagation = Propagation.REQUIRED)
	public IDGen updateIDGen(IDGen idGen) throws DAOException {
		try {
			idGen = em.merge(idGen);
			em.flush();
		} catch (PersistenceException pe) {
			throw translate("Failed to update IDGen ", pe);
		}
		return idGen;
	}
}
