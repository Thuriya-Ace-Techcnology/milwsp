package org.ace.insurance.system.productinformation.maincategory;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Embedded;
import javax.persistence.Entity;
import javax.persistence.EntityListeners;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.persistence.TableGenerator;
import javax.persistence.Version;

import org.ace.insurance.common.UserRecorder;
import org.ace.insurance.product.Product;
import org.ace.insurance.system.productinformation.photoimage.PhotoImage;
import org.ace.insurance.system.productinformation.subcategory.SubCategory;
import org.ace.insurance.system.productinformation.text.NameText;
import org.ace.java.component.idgen.service.IDInterceptor;

@Entity
@Table(name = "MAINCATEGORY")
@TableGenerator(name = "MAINCATEGORY_GEN", table = "ID_GEN", pkColumnName = "GEN_NAME", pkColumnValue = "MAINCATEGORY_GEN", valueColumnName = "GEN_VAL", allocationSize = 10)
@NamedQueries(value = { @NamedQuery(name = "MainCategory.findAll", query = "SELECT m FROM MainCategory m"),
		@NamedQuery(name = "MainCategory.findById", query = "SELECT mc FROM MainCategory mc WHERE mc.id = :id") })
@EntityListeners(IDInterceptor.class)
public class MainCategory implements Serializable {
	private static final long serialVersionUID = 1L;
	@Id
	@GeneratedValue(strategy = GenerationType.TABLE, generator = "MAINCATEGORY_GEN")
	private String id;

	@OneToOne
	@JoinColumn(name = "PRODUCTID", referencedColumnName = "ID")
	private Product product;

	@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER, mappedBy = "mainCategory", orphanRemoval = true)
	private List<NameText> textList;

	@OneToOne(cascade = CascadeType.ALL)
	@JoinColumn(name = "IMAGEID")
	private PhotoImage image;

	@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER, mappedBy = "mainCategory", orphanRemoval = true)
	private List<SubCategory> subCategoryList;

	@Column(name = "ORDERNUMBER")
	private int order;

	@Embedded
	private UserRecorder recorder;

	@Version
	private int version;

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public List<NameText> getTextList() {
		if (textList == null) {
			textList = new ArrayList<NameText>();
		}
		return textList;
	}

	public void setTextList(List<NameText> textList) {
		this.textList = textList;
	}

	public int getOrder() {
		return order;
	}

	public void setOrder(int order) {
		this.order = order;
	}

	public List<SubCategory> getSubCategoryList() {
		if (subCategoryList == null) {
			subCategoryList = new ArrayList<SubCategory>();
		}
		return subCategoryList;
	}

	public void setSubCategoryList(List<SubCategory> subCategoryList) {
		this.subCategoryList = subCategoryList;
	}

	public PhotoImage getImage() {
		return image;
	}

	public void setImage(PhotoImage image) {
		this.image = image;
	}

	public Product getProduct() {
		return product;
	}

	public void setProduct(Product product) {
		this.product = product;
	}

	public UserRecorder getRecorder() {
		return recorder;
	}

	public void setRecorder(UserRecorder recorder) {
		this.recorder = recorder;
	}

	public int getVersion() {
		return version;
	}

	public void setVersion(int version) {
		this.version = version;
	}

	public void addNameAndText(NameText nameAndText) {
		if (this.textList == null) {
			textList = new ArrayList<NameText>();
		}
		this.textList.add(nameAndText);
		nameAndText.setMainCategory(this);
	}

	public void addSubCategory(SubCategory subCategory) {
		if (this.subCategoryList == null) {
			subCategoryList = new ArrayList<SubCategory>();
		}
		this.subCategoryList.add(subCategory);
		subCategory.setMainCategory(this);
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		result = prime * result + ((image == null) ? 0 : image.hashCode());
		result = prime * result + order;
		result = prime * result + ((product == null) ? 0 : product.hashCode());
		result = prime * result + ((recorder == null) ? 0 : recorder.hashCode());
		result = prime * result + version;
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		MainCategory other = (MainCategory) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		if (image == null) {
			if (other.image != null)
				return false;
		} else if (!image.equals(other.image))
			return false;
		if (order != other.order)
			return false;
		if (product == null) {
			if (other.product != null)
				return false;
		} else if (!product.equals(other.product))
			return false;
		if (recorder == null) {
			if (other.recorder != null)
				return false;
		} else if (!recorder.equals(other.recorder))
			return false;
		if (version != other.version)
			return false;
		return true;
	}

}