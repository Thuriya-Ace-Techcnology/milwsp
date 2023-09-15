package org.ace.insurance.common;

import java.io.Serializable;


import java.math.BigDecimal;

import javax.persistence.Access;
import javax.persistence.AccessType;
import javax.persistence.Embedded;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.persistence.TableGenerator;
import javax.persistence.Version;

import org.ace.accounting.system.chartaccount.BfRate;
import org.ace.accounting.system.chartaccount.BfSrcRate;
import org.ace.accounting.system.chartaccount.LymSrcRate;
import org.ace.accounting.system.chartaccount.MSrcRate;
import org.ace.accounting.system.chartaccount.MonthlyRate;
import org.ace.accounting.system.chartaccount.MrevRate;
import org.ace.insurance.common.TableName;
import org.ace.insurance.common.UserRecorder;
import org.apache.commons.lang.builder.EqualsBuilder;
import org.apache.commons.lang.builder.HashCodeBuilder;

@Entity
@Table(name = TableName.CCOA)
@TableGenerator(name = "CCOA_GEN", table = "ID_GEN", pkColumnName = "GEN_NAME", valueColumnName = "GEN_VAL", pkColumnValue = "CCOA_GEN", allocationSize = 10)
@NamedQuery (name = "CCOA.findAll", query = "SELECT c FROM CCOA c")
@Access(value = AccessType.FIELD)
public class CCOA implements Serializable {

	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.TABLE, generator = "CCOA_GEN")
	private String id;

	private String coaId;

	private String acName;

	private String branchId;

	private String departmentId;

	private String currencyId;

	/* other fields is not insert */

	/*
	 * Budget Figure
	 */
	private BigDecimal bF;

	/*
	 * Opening Balance
	 */
	private BigDecimal oBal;

	/*
	 * Home Opening Balance
	 */
	private BigDecimal hOBal;

	/*
	 * Closing Balance
	 */
	private BigDecimal cBal;

	@Embedded
	private MonthlyRate monthlyRate;

	@Embedded
	private MSrcRate msrcRate;

	@Embedded
	private BfRate bfRate;

	@Embedded
	private BfSrcRate bfsrcRate;

	@Embedded
	private MrevRate mrevRate;

	@Embedded
	private LymSrcRate lymsrcRate;

	private BigDecimal sccrBal;

	private BigDecimal accrued;

	private String budget;

	@Version
	private int version;

	@Embedded
	private UserRecorder recorder;

	public CCOA() {
		bF = BigDecimal.ZERO;
		oBal = BigDecimal.ZERO;
		hOBal = BigDecimal.ZERO;
		cBal = BigDecimal.ZERO;
		sccrBal = BigDecimal.ZERO;
		accrued = BigDecimal.ZERO;
		monthlyRate = new MonthlyRate();
		msrcRate = new MSrcRate();
		bfRate = new BfRate();
		bfsrcRate = new BfSrcRate();
		mrevRate = new MrevRate();
		lymsrcRate = new LymSrcRate();
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getCoaId() {
		return coaId;
	}

	public void setCoaId(String coaId) {
		this.coaId = coaId;
	}

	public String getAcName() {
		return acName;
	}

	public void setAcName(String acName) {
		this.acName = acName;
	}

	public String getBranchId() {
		return branchId;
	}

	public void setBranchId(String branchId) {
		this.branchId = branchId;
	}

	public String getDepartmentId() {
		return departmentId;
	}

	public void setDepartmentId(String departmentId) {
		this.departmentId = departmentId;
	}

	public String getCurrencyId() {
		return currencyId;
	}

	public void setCurrencyId(String currencyId) {
		this.currencyId = currencyId;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
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

	public String getBudget() {
		return budget;
	}

	public void setBudget(String budget) {
		this.budget = budget;
	}

	public BigDecimal getbF() {
		return bF;
	}

	public void setbF(BigDecimal bF) {
		this.bF = bF;
	}

	public BigDecimal getoBal() {
		return oBal;
	}

	public void setoBal(BigDecimal oBal) {
		this.oBal = oBal;
	}

	public BigDecimal gethOBal() {
		return hOBal;
	}

	public void sethOBal(BigDecimal hOBal) {
		this.hOBal = hOBal;
	}

	public BigDecimal getcBal() {
		return cBal;
	}

	public void setcBal(BigDecimal cBal) {
		this.cBal = cBal;
	}

	public MonthlyRate getMonthlyRate() {
		return monthlyRate;
	}

	public void setMonthlyRate(MonthlyRate monthlyRate) {
		this.monthlyRate = monthlyRate;
	}

	public MSrcRate getMsrcRate() {
		return msrcRate;
	}

	public void setMsrcRate(MSrcRate msrcRate) {
		this.msrcRate = msrcRate;
	}

	public BfRate getBfRate() {
		return bfRate;
	}

	public void setBfRate(BfRate bfRate) {
		this.bfRate = bfRate;
	}

	public BfSrcRate getBfsrcRate() {
		return bfsrcRate;
	}

	public void setBfsrcRate(BfSrcRate bfsrcRate) {
		this.bfsrcRate = bfsrcRate;
	}

	public MrevRate getMrevRate() {
		return mrevRate;
	}

	public void setMrevRate(MrevRate mrevRate) {
		this.mrevRate = mrevRate;
	}

	public LymSrcRate getLymsrcRate() {
		return lymsrcRate;
	}

	public void setLymsrcRate(LymSrcRate lymsrcRate) {
		this.lymsrcRate = lymsrcRate;
	}

	public BigDecimal getSccrBal() {
		return sccrBal;
	}

	public void setSccrBal(BigDecimal sccrBal) {
		this.sccrBal = sccrBal;
	}

	public BigDecimal getAccrued() {
		return accrued;
	}

	public void setAccrued(BigDecimal accrued) {
		this.accrued = accrued;
	}

	@Override
	public boolean equals(Object object) {
		return EqualsBuilder.reflectionEquals(this, object);
	}

	@Override
	public int hashCode() {
		return HashCodeBuilder.reflectionHashCode(this);
	}

}
