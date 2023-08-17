package org.ace.insurance.system.productfaq.service;

import java.util.List;

import javax.annotation.Resource;

import org.ace.insurance.system.productfaq.ProductFAQ;
import org.ace.insurance.system.productfaq.persistence.interfaces.IProductFAQDAO;
import org.ace.insurance.system.productfaq.service.interfaces.IProductFAQService;
import org.ace.java.component.SystemException;
import org.ace.java.component.persistence.exception.DAOException;
import org.ace.java.component.service.BaseService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

@Service(value = "ProductFAQService")
public class ProductFAQService extends BaseService implements IProductFAQService {

	@Resource(name = "ProductFAQDAO")
	private IProductFAQDAO productFAQDAO;

	@Transactional(propagation = Propagation.REQUIRED, readOnly = false)
	public List<ProductFAQ> findAllProductFAQ() {
		List<ProductFAQ> result = null;
		try {
			result = productFAQDAO.findAll();
		} catch (DAOException e) {
			throw new SystemException(e.getErrorCode(), "Faield to find all of ProductFAQ)", e);
		}
		return result;
	}
}
