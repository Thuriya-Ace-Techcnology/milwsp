package org.ace.insurance.specailForeignTravel;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import org.ace.insurance.common.TableName;

@Entity
@Table(name = TableName.OUTBOUND_TPA_PREMIUM_RATE)
@NamedQueries(value = { 
					@NamedQuery(name = "OutboundTPAPremiumRate.findByName", query = "SELECT r FROM OutboundTPAPremiumRate r WHERE r.name = :name "),	
				})
public class OutboundTPAPremiumRate implements Serializable {
	
	private static final long serialVersionUID = 1L;
	@Id
	private int id;
	@Enumerated(EnumType.STRING)
	private OutboundPercent name;
	private double tpaPercent;
	
	public OutboundTPAPremiumRate() {}
	
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}

	public OutboundPercent getName() {
		return name;
	}
	public void setName(OutboundPercent name) {
		this.name = name;
	}
	public double getTpaPercent() {
		return tpaPercent;
	}
	public void setTpaPercent(double tpaPercent) {
		this.tpaPercent = tpaPercent;
	}
	
	
}
