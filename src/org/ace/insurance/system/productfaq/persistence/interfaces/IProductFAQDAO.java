package org.ace.insurance.system.productfaq.persistence.interfaces;

import java.util.List;

import org.ace.insurance.system.productfaq.ProductFAQ;
import org.ace.java.component.persistence.exception.DAOException;

public interface IProductFAQDAO {
	public List<ProductFAQ> findAll() throws DAOException;
}
