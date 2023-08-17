package org.ace.java.component.service;

import javax.annotation.Resource;

import org.ace.java.component.idgen.service.interfaces.ICustomIDGenerator;

public class BaseService {
	protected final String PROPOSAL = "PROPOSAL";
	protected final String INFORM = "INFORM";
	protected final String CONFIRMATION = "CONFIRMATION";
	protected final String PAYMENT = "PAYMENT";
	protected final String FARMER = "FARMER";
	protected final String PERSONAL_ACCIDENT_KYT = "PERSONAL_ACCIDENT_KYT";
	protected final String PERSONAL_ACCIDENT_USD = "PERSONAL_ACCIDENT_USD";

	@Resource(name = "CustomIDGenerator")
	protected ICustomIDGenerator customIDGenerator;

	@SuppressWarnings("rawtypes")
	protected String getPrefix(Class cla) {
		return customIDGenerator.getPrefix(cla);
	}
}
