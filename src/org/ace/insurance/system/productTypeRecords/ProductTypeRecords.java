package org.ace.insurance.system.productTypeRecords;
/*
 * @author Kyaw Yea Lwin
 * @date 06/10/2020
 */
import java.io.Serializable;

import javax.persistence.Access;
import javax.persistence.AccessType;
import javax.persistence.Column;
import javax.persistence.Embedded;
import javax.persistence.Entity;
import javax.persistence.EntityListeners;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.persistence.TableGenerator;
import javax.persistence.Transient;
import javax.persistence.Version;

import org.ace.insurance.common.TableName;
import org.ace.insurance.common.UserRecorder;
import org.ace.java.component.FormatID;
import org.ace.java.component.idgen.service.IDInterceptor;

@Entity
@Table(name = TableName.PRODUCTTYPE_RECORDS)
@TableGenerator(name = "PRODUCTTYPE_RECORDS_GEN", table = "ID_GEN", pkColumnName = "GEN_NAME", valueColumnName = "GEN_VAL", pkColumnValue = "PRODUCTTYPE_RECORDS_GEN", allocationSize = 10)
@NamedQueries(value = { @NamedQuery(name = "ProductTypeRecords.findByOrderId", query = "SELECT t FROM ProductTypeRecords t where t.twoCtwoPorderId = :twoCtwoPorderId "),})
@Access(value = AccessType.FIELD)
@EntityListeners(IDInterceptor.class)
public class ProductTypeRecords implements Serializable {
	private static final long serialVersionUID = 1L;

	private String id;

	@Transient
	private String prefix;
	
	@Column(name = "TWOCTWOPORDERID")
	private String twoCtwoPorderId;
	
	@Column(name = "PRODUCTTYPE")
	private String productType;
	
	@Version
	private int version;

	@Embedded
	private UserRecorder recorder;

	public ProductTypeRecords() {
		super();
	}

	@Id
	@GeneratedValue(strategy = GenerationType.TABLE, generator = "PRODUCTTYPE_RECORDS_GEN")
	@Access(value = AccessType.PROPERTY)
	public String getId() {
		return id;
	}

	public void setId(String id) {
		if (id != null) {
			this.id = FormatID.formatId(id, getPrefix(), 10);
		}
	}
	
	public String getTwoCtwoPorderId() {
		return twoCtwoPorderId;
	}

	public void setTwoCtwoPorderId(String twoCtwoPorderId) {
		this.twoCtwoPorderId = twoCtwoPorderId;
	}

	public String getProductType() {
		return productType;
	}

	public void setProductType(String productType) {
		this.productType = productType;
	}

	public String getPrefix() {
		return prefix;
	}

	public void setPrefix(String prefix) {
		this.prefix = prefix;
	}

	
	public int getVersion() {
		return version;
	}

	public void setVersion(int version) {
		this.version = version;
	}

	public UserRecorder getRecorder() {
		return recorder;
	}

	public void setRecorder(UserRecorder recorder) {
		this.recorder = recorder;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		result = prime * result + ((prefix == null) ? 0 : prefix.hashCode());
		result = prime * result + ((productType == null) ? 0 : productType.hashCode());
		result = prime * result + ((recorder == null) ? 0 : recorder.hashCode());
		result = prime * result + ((twoCtwoPorderId == null) ? 0 : twoCtwoPorderId.hashCode());
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
		ProductTypeRecords other = (ProductTypeRecords) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		if (prefix == null) {
			if (other.prefix != null)
				return false;
		} else if (!prefix.equals(other.prefix))
			return false;
		if (productType == null) {
			if (other.productType != null)
				return false;
		} else if (!productType.equals(other.productType))
			return false;
		if (recorder == null) {
			if (other.recorder != null)
				return false;
		} else if (!recorder.equals(other.recorder))
			return false;
		if (twoCtwoPorderId == null) {
			if (other.twoCtwoPorderId != null)
				return false;
		} else if (!twoCtwoPorderId.equals(other.twoCtwoPorderId))
			return false;
		if (version != other.version)
			return false;
		return true;
	}

	

	

}
