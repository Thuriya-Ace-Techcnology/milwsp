package org.ace.insurance.system.notification;

import java.util.Date;

import javax.persistence.Embedded;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import org.ace.insurance.common.TableName;
import org.ace.insurance.common.UserRecorder;
import org.eclipse.persistence.annotations.ReadOnly;

@Entity
@ReadOnly
@Table(name = TableName.NOTIFICATION)
@NamedQueries(value = { @NamedQuery(name = "Notification.findAll", query = "SELECT n FROM Notification n ORDER BY n.startDate ASC") })
public class Notification {
	@Id
	private String id;

	private String title;

	private String body;

	private boolean isPush;

	private boolean finishedPush;

	@Temporal(TemporalType.DATE)
	private Date startDate;

	@Temporal(TemporalType.DATE)
	private Date endDate;

	@Embedded
	private UserRecorder recorder;

	public Notification() {
		super();
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getBody() {
		return body;
	}

	public void setBody(String body) {
		this.body = body;
	}

	public boolean isPush() {
		return isPush;
	}

	public void setPush(boolean isPush) {
		this.isPush = isPush;
	}

	public boolean isFinishedPush() {
		return finishedPush;
	}

	public void setFinishedPush(boolean finishedPush) {
		this.finishedPush = finishedPush;
	}

	public Date getStartDate() {
		return startDate;
	}

	public void setStartDate(Date startDate) {
		this.startDate = startDate;
	}

	public Date getEndDate() {
		return endDate;
	}

	public void setEndDate(Date endDate) {
		this.endDate = endDate;
	}

	public UserRecorder getRecorder() {
		return recorder;
	}

	public void setRecorder(UserRecorder recorder) {
		this.recorder = recorder;
	}

}
