package com.entity;

import java.sql.Timestamp;

public class Logs {
	
	private int auditId;
	private String auditUser;
	private String auditRole;
	private String actionName;
	private String action_affected;
	private String dateTime;
	
	
	
	
	
	/* Attribute use to Insert Audit Logs*/
	
	public Logs(String auditUser, String auditRole, String actionName, String action_affected) {
		super();
		this.auditUser = auditUser;
		this.auditRole = auditRole;
		this.actionName = actionName;
		this.action_affected = action_affected;
	}
	
	
	
	
	
	public Logs(int auditId, String auditUser, String auditRole, String actionName, String action_affected,
			String dateTime) {
		super();
		this.auditId = auditId;
		this.auditUser = auditUser;
		this.auditRole = auditRole;
		this.actionName = actionName;
		this.action_affected = action_affected;
		this.dateTime = dateTime;
	}





	public int getAuditId() {
		return auditId;
	}
	public String getAuditUser() {
		return auditUser;
	}
	public String getAuditRole() {
		return auditRole;
	}
	public String getActionName() {
		return actionName;
	}
	public String getAction_affected() {
		return action_affected;
	}

	public String getDateTime() {
		return dateTime;
	}
	
	
	
	
	
	
}
