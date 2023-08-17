package org.ace.insurance.system.productinformation.maincategory.service.interfaces;

import java.util.List;

import org.ace.insurance.system.productinformation.maincategory.MainCategory;
import org.ace.ws.model.mainCategory.MC001;

public interface IMainCategoryService {

	public MainCategory addNewMainCategory(MainCategory mainCategory);

	public MainCategory updateMainCategory(MainCategory mainCategory);

	public void deleteMainCategory(MainCategory mainCategory);

	public MainCategory findMainCategoryById(String id);

	public List<MainCategory> findAllMainCategory();

	public List<MC001> findMainCategoryByProductId(String productId);

}