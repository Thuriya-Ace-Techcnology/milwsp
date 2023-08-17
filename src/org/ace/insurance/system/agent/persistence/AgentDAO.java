package org.ace.insurance.system.agent.persistence;

import java.sql.Date;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import javax.persistence.NoResultException;
import javax.persistence.PersistenceException;
import javax.persistence.Query;
import org.ace.insurance.agent.Agent;
import org.ace.insurance.system.agent.persistence.interfaces.IAgentDAO;
import org.ace.java.component.persistence.BasicDAO;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

@Repository("AgentDAO")
public class AgentDAO extends BasicDAO implements IAgentDAO {

	@Override
	@Transactional(propagation = Propagation.REQUIRED)
	public Agent findAgentByLiscenceNoAndDateOfBirth(String liscenceNo, String dob) {
		Agent result;
		try {
			LocalDate ld = DateTimeFormatter.ofPattern("dd-MM-yyyy").parse(dob, LocalDate::from);
			result =  (Agent) em.createNamedQuery("Agent.findAgentByLiscenceNoAndDateOfBirth")
					.setParameter("liscenceNo", liscenceNo)
					.setParameter("dob", Date.valueOf(ld))
					.getSingleResult();
			em.flush();
		} catch (NoResultException pe) {
			result = null;
		}
		return result;
	}

	@Override
	@Transactional(propagation = Propagation.REQUIRED)
	public Agent findById(String id) {
		
		Agent result;
		try {
			Query q = em.createNamedQuery("Agent.findById");
			q.setParameter("id", id);
			result =  (Agent) q.getSingleResult();
			em.flush();
		} catch (PersistenceException pe) {
			return null;
		}
		return result;
	}
	
	
}
