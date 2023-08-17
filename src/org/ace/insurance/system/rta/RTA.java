package org.ace.insurance.system.rta;

/*
 * @author Kyaw Yea Lwin
 * @date 14/07/2020
 */
import java.io.Serializable;

import javax.persistence.Access;
import javax.persistence.AccessType;
import javax.persistence.Column;
import javax.persistence.Embedded;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.TableGenerator;
import javax.persistence.Transient;
import javax.persistence.Version;
import javax.validation.constraints.NotNull;

import org.ace.insurance.common.TableName;
import org.ace.insurance.common.UserRecorder;
import org.ace.java.component.FormatID;

@Entity
@Table(name = TableName.RTA)
@TableGenerator(name = "RTA_GEN", table = "ID_GEN", pkColumnName = "GEN_NAME", valueColumnName = "GEN_VAL", pkColumnValue = "RTA_GEN", allocationSize = 10)
@Access(value = AccessType.FIELD)
public class RTA implements Serializable {
	private static final long serialVersionUID = 1L;

	private String id;

	@Transient
	private String prefix;

	@NotNull
	@Column(name = "REG_NO")
	private String reg_no;
	
	@Column(name = "MAKE_MODEL")
	private String make_model;
	
	@Column(name = "TYPE")
	private String type;
	
	@Column(name = "TYPE_8")
	private String type_8;
	
	@Column(name="LOCATION")
	private String location;
	
	@Column(name = "NAME")
	private String name;
	
	@NotNull
	@Column(name = "NRC_NO")
	private String nrc_no;

	@Column(name = "HOUSE_NO")
	private String house_no;
	
	@Column(name = "RD_ST")
	private String rd_st;

	@Column(name = "QTR")
	private String qtr;

	@Column(name = "TSP")
	private String tsp;

	@Column(name = "PAYLOAD")
	private String payload;
	
	//Date of Expire
	@Column(name = "D_E")
	private String d_e;

	@Column(name="OWNER")
	private String owner;
	
	@Column(name="I_RG")
	private String irg;
	
	@Column(name="STATUS")
	private String status;
	
	@Version
	private int version;

	@Embedded
	private UserRecorder recorder;
	
	public RTA() {
		super();
	}
	
	@Id
	@GeneratedValue(strategy = GenerationType.TABLE, generator = "RTA_GEN")
	@Access(value = AccessType.PROPERTY)
	public String getId() {
		return id;
	}

	public void setId(String id) {
		if (id != null) {
			this.id = FormatID.formatId(id, getPrefix(), 10);
		}
	}

	public String getPrefix() {
		return prefix;
	}

	public void setPrefix(String prefix) {
		this.prefix = prefix;
	}


	public String getNrc_no() {
		return nrc_no;
	}

	public void setNrc_no(String nrc_no) {
		this.nrc_no = nrc_no;
	}


	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
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

	public String getReg_no() {
		return reg_no;
	}

	public void setReg_no(String reg_no) {
		this.reg_no = reg_no;
	}

	public String getMake_model() {
		return make_model;
	}

	public void setMake_model(String make_model) {
		this.make_model = make_model;
	}

	public String getType_8() {
		return type_8;
	}

	public void setType_8(String type_8) {
		this.type_8 = type_8;
	}

	public String getLocation() {
		return location;
	}

	public void setLocation(String location) {
		this.location = location;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getHouse_no() {
		if(house_no == null) {
			return "";
		}
		return house_no + ",";
	}

	public void setHouse_no(String house_no) {
		this.house_no = house_no;
	}


	public String getQtr() {
		if(qtr == null) {
			return "";
		}
		return qtr ;
	}

	public void setQtr(String qtr) {
		this.qtr = qtr;
	}

	public String getTsp() {
		return tsp;
	}

	public void setTsp(String tsp) {
		this.tsp = tsp;
	}

	public String getPayload() {
		return payload;
	}

	public void setPayload(String payload) {
		this.payload = payload;
	}

	public String getD_e() {
		return d_e;
	}

	public void setD_e(String d_e) {
		this.d_e = d_e;
	}
	public String getOwner() {
		return owner;
	}
	public void setOwner(String owner) {
		this.owner = owner;
	}
	public String getIrg() {
		return irg;
	}
	public void setIrg(String irg) {
		this.irg = irg;
	}
	public String getRd_st() {
		if(rd_st == null) {
			return "";
		}
		return rd_st + ",";
	}
	public void setRd_st(String rd_st) {
		this.rd_st = rd_st;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	
	public String getAddress() {	
		return getHouse_no() + getRd_st() + getQtr()  ;
	}
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((d_e == null) ? 0 : d_e.hashCode());
		result = prime * result + ((house_no == null) ? 0 : house_no.hashCode());
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		result = prime * result + ((irg == null) ? 0 : irg.hashCode());
		result = prime * result + ((location == null) ? 0 : location.hashCode());
		result = prime * result + ((make_model == null) ? 0 : make_model.hashCode());
		result = prime * result + ((name == null) ? 0 : name.hashCode());
		result = prime * result + ((nrc_no == null) ? 0 : nrc_no.hashCode());
		result = prime * result + ((owner == null) ? 0 : owner.hashCode());
		result = prime * result + ((payload == null) ? 0 : payload.hashCode());
		result = prime * result + ((prefix == null) ? 0 : prefix.hashCode());
		result = prime * result + ((qtr == null) ? 0 : qtr.hashCode());
		result = prime * result + ((rd_st == null) ? 0 : rd_st.hashCode());
		result = prime * result + ((recorder == null) ? 0 : recorder.hashCode());
		result = prime * result + ((reg_no == null) ? 0 : reg_no.hashCode());
		result = prime * result + ((status == null) ? 0 : status.hashCode());
		result = prime * result + ((tsp == null) ? 0 : tsp.hashCode());
		result = prime * result + ((type == null) ? 0 : type.hashCode());
		result = prime * result + ((type_8 == null) ? 0 : type_8.hashCode());
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
		RTA other = (RTA) obj;
		if (d_e == null) {
			if (other.d_e != null)
				return false;
		} else if (!d_e.equals(other.d_e))
			return false;
		if (house_no == null) {
			if (other.house_no != null)
				return false;
		} else if (!house_no.equals(other.house_no))
			return false;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		if (irg == null) {
			if (other.irg != null)
				return false;
		} else if (!irg.equals(other.irg))
			return false;
		if (location == null) {
			if (other.location != null)
				return false;
		} else if (!location.equals(other.location))
			return false;
		if (make_model == null) {
			if (other.make_model != null)
				return false;
		} else if (!make_model.equals(other.make_model))
			return false;
		if (name == null) {
			if (other.name != null)
				return false;
		} else if (!name.equals(other.name))
			return false;
		if (nrc_no == null) {
			if (other.nrc_no != null)
				return false;
		} else if (!nrc_no.equals(other.nrc_no))
			return false;
		if (owner == null) {
			if (other.owner != null)
				return false;
		} else if (!owner.equals(other.owner))
			return false;
		if (payload == null) {
			if (other.payload != null)
				return false;
		} else if (!payload.equals(other.payload))
			return false;
		if (prefix == null) {
			if (other.prefix != null)
				return false;
		} else if (!prefix.equals(other.prefix))
			return false;
		if (qtr == null) {
			if (other.qtr != null)
				return false;
		} else if (!qtr.equals(other.qtr))
			return false;
		if (rd_st == null) {
			if (other.rd_st != null)
				return false;
		} else if (!rd_st.equals(other.rd_st))
			return false;
		if (recorder == null) {
			if (other.recorder != null)
				return false;
		} else if (!recorder.equals(other.recorder))
			return false;
		if (reg_no == null) {
			if (other.reg_no != null)
				return false;
		} else if (!reg_no.equals(other.reg_no))
			return false;
		if (status == null) {
			if (other.status != null)
				return false;
		} else if (!status.equals(other.status))
			return false;
		if (tsp == null) {
			if (other.tsp != null)
				return false;
		} else if (!tsp.equals(other.tsp))
			return false;
		if (type == null) {
			if (other.type != null)
				return false;
		} else if (!type.equals(other.type))
			return false;
		if (type_8 == null) {
			if (other.type_8 != null)
				return false;
		} else if (!type_8.equals(other.type_8))
			return false;
		if (version != other.version)
			return false;
		return true;
	}
	
}
