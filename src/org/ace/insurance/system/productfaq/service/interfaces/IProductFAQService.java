package org.ace.insurance.system.productfaq.service.interfaces;

import java.util.List;

import org.ace.insurance.system.productfaq.ProductFAQ;
import org.ace.java.component.persistence.exception.DAOException;

public interface IProductFAQService {
	public List<ProductFAQ> findAllProductFAQ() throws DAOException;
}
