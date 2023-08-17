package org.ace.insurance.common;

public class OutboundAgentInfo {
	private String agentCode;
	private String password;
	private String agentId;
	private String agentName;
	
	
	public OutboundAgentInfo() {
		super();
		// TODO Auto-generated constructor stub
	}
	public OutboundAgentInfo(String agentCode, String password, String agentId,String agentName) {
		super();
		this.agentCode = agentCode;
		this.password = password;
		this.agentId = agentId;
		this.agentName = agentName;
	}
	public String getAgentId() {
		return agentId;
	}
	public void setAgentId(String agentId) {
		this.agentId = agentId;
	}
	public String getAgentCode() {
		return agentCode;
	}
	public void setAgentCode(String agentCode) {
		this.agentCode = agentCode;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public String getAgentName() {
		return agentName;
	}
	public void setAgentName(String agentName) {
		this.agentName = agentName;
	}
}
