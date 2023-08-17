package org.ace.insurance.system.productTypeRecords.persistence.interfaces;

import org.ace.insurance.system.productTypeRecords.ProductTypeRecords;
import org.ace.java.component.persistence.exception.DAOException;

public interface IProductTypeRecordsDAO {
	public ProductTypeRecords insert(ProductTypeRecords productTypeRecords) throws DAOException;

	public ProductTypeRecords update(ProductTypeRecords productTypeRecords) throws DAOException;

	public void delete(ProductTypeRecords productTypeRecords) throws DAOException;

	public ProductTypeRecords findById(String id) throws DAOException;

	public ProductTypeRecords findByOrderId(String orderId) throws DAOException;
	
}
