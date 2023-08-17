package org.ace.ws.factory;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.List;

import org.ace.insurance.system.productfaq.FAQ;
import org.ace.insurance.system.productfaq.ProductFAQ;
import org.ace.ws.client.HttpUtility;
import org.ace.ws.model.faq.FAQ001;
import org.ace.ws.model.faq.PFAQ001;

public class ProductFAQFactory {

	public static FAQ001 convertFAQ001(FAQ faq) {
		FAQ001 faq001 = new FAQ001();
		try {
			faq001.setId(faq.getId());
			faq001.setCodeNo(faq.getCodeNo());
			faq001.setQuestion(faq.getQuestion() != null ? URLEncoder.encode(faq.getQuestion(), "UTF-8") : "");
			faq001.setAnswer(faq.getAnswer() != null ? URLEncoder.encode(faq.getAnswer(), "UTF-8") : "");
			faq001.setLanguage(faq.getLanguage());
			faq001.setVersion(faq.getVersion());
		} catch (UnsupportedEncodingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return faq001;
	}

	public static PFAQ001 convertProductFAQ001(ProductFAQ productFAQ) {
		PFAQ001 productFAQ001 = new PFAQ001();
		try {
			productFAQ001.setId(productFAQ.getId());
			productFAQ001.setTitle(productFAQ.getTitle() != null ? URLEncoder.encode(productFAQ.getTitle(), "UTF-8") : "");
			productFAQ001.setProductId(productFAQ.getProduct().getId());
			productFAQ001.setFilePath(productFAQ.getFilepath());
			productFAQ001.setImage(HttpUtility.requestMessage(productFAQ.getImage()));
			productFAQ001.setLanguage(productFAQ.getLanguage());
			productFAQ001.setVersion(productFAQ.getVersion());
			List<FAQ001> faq001List = new ArrayList<FAQ001>();
			for (FAQ f : productFAQ.getFaqList()) {
				faq001List.add(convertFAQ001(f));
			}
			productFAQ001.setFaqList(faq001List);
		} catch (UnsupportedEncodingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return productFAQ001;
	}

	public static List<PFAQ001> convertProductFAQ001List(List<ProductFAQ> productFAQList) {
		List<PFAQ001> productFAQ001list = new ArrayList<PFAQ001>();
		for (ProductFAQ productFAQ : productFAQList) {
			productFAQ001list.add(convertProductFAQ001(productFAQ));
		}
		return productFAQ001list;
	}
}
