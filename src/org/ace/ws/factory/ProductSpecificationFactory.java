package org.ace.ws.factory;

import java.util.ArrayList;
import java.util.List;

import org.ace.insurance.system.productinformation.maincategory.MainCategory;
import org.ace.ws.model.mainCategory.MC001;

public class ProductSpecificationFactory {
	public static MC001 convertMainCategoryDTO(MainCategory mainCategory) {
		MC001 mainCategory001 = new MC001(mainCategory);
		return mainCategory001;
	}

	public static List<MC001> convertMainCategoryDTOList(List<MainCategory> mainCategorylist) {
		List<MC001> mainCategory001list = new ArrayList<MC001>();
		for (MainCategory mainCategory : mainCategorylist) {
			MC001 mainCategory001 = convertMainCategoryDTO(mainCategory);
			mainCategory001list.add(mainCategory001);
		}
		return mainCategory001list;

	}
}
